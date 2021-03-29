package egovframework.com.uss.ion.rwd.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rwd.service.EgovRwardManageService;
import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 개요
 * - 포상관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 포상관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 포상관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *
 *  << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.16  정진오          VcatnManageVO Dependency 제거, 사용하지 않는 객체 선언
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>

 */

@Controller
public class EgovRwardManageController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovRwardManageService")
    private EgovRwardManageService egovRwardManageService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    @Autowired
	 private DefaultBeanValidator beanValidator;

    /**
	 * 포상관리 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/rwd/EgovRwardManageListView.do")
    public String selectRwardManageListView(/*@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,*/ // 2011.8.16 수정분
			                                ModelMap model) throws Exception {
    	List<?> rwardCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("rwardCodeList",    rwardCdCodeList);

        return "egovframework/com/uss/ion/rwd/EgovRwardManageList";
    }

	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="포상관리", order = 920 ,gid = 50)
    @RequestMapping(value="/uss/ion/rwd/selectRwardManageList.do")
	 public String selectRwardManageList(@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
			                             ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rwardManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rwardManageVO.getPageUnit());
		paginationInfo.setPageSize(rwardManageVO.getPageSize());

		rwardManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rwardManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rwardManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		rwardManageVO.setRwardManageList(egovRwardManageService.selectRwardManageList(rwardManageVO));

		model.addAttribute("rwardManageList", rwardManageVO.getRwardManageList());

		int totCnt = egovRwardManageService.selectRwardManageListTotCnt(rwardManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

    	List<?> rwardCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("rwardCodeList",    rwardCdCodeList);
		model.addAttribute("paginationInfo",   paginationInfo );
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/rwd/EgovRwardManageList";
	}

	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/rwd/EgovRwardManageDetail.do")
	 public String selectRwardManage(   @ModelAttribute("rwardManage") RwardManage rwardManage,
                                        @ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
                                        @RequestParam Map<?, ?> commandMap,
			                            ModelMap model) throws Exception {
    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	rwardManageVO.setRwardDe(EgovStringUtil.removeMinusChar(rwardManageVO.getRwardDe()));



        // 등록 상세정보
    	RwardManageVO rwardManageVOTemp = egovRwardManageService.selectRwardManage(rwardManageVO);

    	model.addAttribute("rwardManageVO", rwardManageVOTemp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(sCmd.equals("updt")){
			RwardManage rwardManage_1 = new RwardManage();

			rwardManage_1.setRwardId(rwardManageVOTemp.getRwardId());
			rwardManage_1.setRwardNm(rwardManageVOTemp.getRwardNm());
			rwardManage_1.setPblenCn(rwardManageVOTemp.getPblenCn());
			rwardManage_1.setRwardManId(rwardManageVOTemp.getRwardManId());
			rwardManage_1.setRwardCd(rwardManageVOTemp.getRwardCd());
			rwardManage_1.setRwardDe(rwardManageVOTemp.getRwardDe());
			rwardManage_1.setInfrmlSanctnId(rwardManageVOTemp.getInfrmlSanctnId());
			rwardManage_1.setSanctnerId(rwardManageVOTemp.getSanctnerId());

	    	List<?> rwardCdCodeList = null;
	    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM055");
			rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("rwardCodeList",    rwardCdCodeList);
			model.addAttribute("rwardManage", rwardManage_1);
			return "egovframework/com/uss/ion/rwd/EgovRwardUpdt";
		}else{
			return "egovframework/com/uss/ion/rwd/EgovRwardDetail";
		}

	}

	/**
	 * 포상관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/rwd/EgovRwardRegist.do")
	 public String insertViewRwardManage(@ModelAttribute("rwardManage")   RwardManage   rwardManage,
			                             @ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
			 							 ModelMap model) throws Exception {
    	List<?> rwardCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("rwardCodeList",    rwardCdCodeList);
    	return "egovframework/com/uss/ion/rwd/EgovRwardRegist";
	}

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/rwd/insertRwardManage.do")
	 public String insertRwardManage(final MultipartHttpServletRequest multiRequest,
			                         @ModelAttribute("rwardManage")   RwardManage rwardManage,
						             @ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
						             BindingResult bindingResult,
									 SessionStatus status,
									 ModelMap model) throws Exception {

    	beanValidator.validate(rwardManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("rwardManageVO", rwardManageVO);
			return "egovframework/com/uss/ion/rwd/EgovRwardRegist";
		} else {
	    	// 첨부파일 관련 첨부파일ID 생성
			List<FileVO> _result = null;
			String _atchFileId = "";

			//final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");
			
			if(!files.isEmpty()){
			 _result = fileUtil.parseFileInf(files, "RWD_", 0, "", "");
			 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			}
	    	// 리턴받은 첨부파일ID를 셋팅한다..
			rwardManage.setAtchFileId(_atchFileId);			// 첨부파일 ID

			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			rwardManage.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());		// 최초등록자ID
            egovRwardManageService.insertRwardManage(rwardManage);
			status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

	    	return "forward:/uss/ion/rwd/selectRwardManageList.do";

		}
	}

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
    @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/rwd/updtRwardManage.do")
	 public String updtRwardManage(@RequestParam("atchFileAt") String atchFileAt ,
                                   final MultipartHttpServletRequest multiRequest,
			                       @ModelAttribute("rwardManage") RwardManage rwardManage,
			                       @ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
		 						   BindingResult bindingResult,
			                       SessionStatus status,
		                           ModelMap model) throws Exception {
		beanValidator.validate(rwardManage, bindingResult); //validation 수행
    	if (bindingResult.hasErrors()) {
        	model.addAttribute("rwardManageVO", rwardManageVO);
			model.addAttribute("rwardManage",   rwardManage);
			return "egovframework/com/uss/ion/rwd/EgovRwardUpdt";
		} else {
	    	// 첨부파일 관련 ID 생성 start....
			String _atchFileId = rwardManage.getAtchFileId();

			//final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");
			//System.out.println("updtRwardManage 1");
			if(!files.isEmpty()){
				//System.out.println("updtRwardManage 2");
				if("N".equals(atchFileAt)){

					//System.out.println("updtRwardManage 3");
					List<FileVO> _result = fileUtil.parseFileInf(files, "RWD_", 0, _atchFileId, "");
					_atchFileId = fileMngService.insertFileInfs(_result);

					// 첨부파일 ID 셋팅
					rwardManage.setAtchFileId(_atchFileId);    	// 첨부파일 ID

				}else{
					//System.out.println("updtRwardManage 4");
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(_atchFileId);
					int _cnt = fileMngService.getMaxFileSN(fvo);
					List<FileVO> _result = fileUtil.parseFileInf(files, "RWD_", _cnt, _atchFileId, "");
					fileMngService.updateFileInfs(_result);
				}
			}
			// 첨부파일 관련 ID 생성 end...
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));
	    	egovRwardManageService.updtRwardManage(rwardManage);
	    	return "forward:/uss/ion/rwd/selectRwardManageList.do";
		}
	}

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/rwd/deleteRwardManage.do")
	 public String deleteRwardManage(   @ModelAttribute("rwardManage") RwardManage rwardManage,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {
    	rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));

    	// 첨부파일 삭제를 위한 ID 생성 start....
		String _atchFileId = rwardManage.getAtchFileId();

		//포상 삭제 처리
    	egovRwardManageService.deleteRwardManage(rwardManage);

    	// 첨부파일을 삭제하기 위한  Vo
    	FileVO fvo = new FileVO();
    	fvo.setAtchFileId(_atchFileId);

    	fileMngService.deleteAllFileInf(fvo);
    	// 첨부파일 삭제 End.............

    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/rwd/selectRwardManageList.do";
	}

    /*** 승인관련 ***/
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="포상승인관리", order = 921 ,gid = 50)
    @RequestMapping(value="/uss/ion/rwd/EgovRwardConfmList.do")
	 public String selectRwardManageConfmList(@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
			                                  ModelMap model) throws Exception {
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rwardManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rwardManageVO.getPageUnit());
		paginationInfo.setPageSize(rwardManageVO.getPageSize());

		rwardManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rwardManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rwardManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if (user == null) {
    		return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
    	rwardManageVO.setSanctnerId(user.getUniqId()); //사용자가 승인권자인지 조건값 setting   selectRwardManageList

    	rwardManageVO.setRwardManageList(egovRwardManageService.selectRwardManageConfmList(rwardManageVO));

		model.addAttribute("rwardManageList", rwardManageVO.getRwardManageList());

		int totCnt = egovRwardManageService.selectRwardManageConfmListTotCnt(rwardManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

    	List<?> rwardCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("rwardCodeList",    rwardCdCodeList);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/rwd/EgovRwardConfmList";
	}

	/**
	 * 포상승인관리 상세정보를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/rwd/EgovRwardConfm.do")
	 public String selectRwardConfm( @ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
			                         @ModelAttribute("rwardManage")   RwardManage   rwardManage,
							         ModelMap model) throws Exception {
    	rwardManageVO.setRwardDe(EgovStringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

        // 등록 상세정보
    	RwardManageVO rwardManageVOTemp = egovRwardManageService.selectRwardManage(rwardManageVO);

		RwardManage rwardManageTemp = new RwardManage();

		rwardManageTemp.setRwardId(rwardManageVOTemp.getRwardId());
		rwardManageTemp.setRwardNm(rwardManageVOTemp.getRwardNm());
		rwardManageTemp.setPblenCn(rwardManageVOTemp.getPblenCn());
		rwardManageTemp.setRwardManId(rwardManageVOTemp.getRwardManId());
		rwardManageTemp.setRwardCd(rwardManageVOTemp.getRwardCd());
		rwardManageTemp.setRwardDe(rwardManageVOTemp.getRwardDe());
		rwardManageTemp.setSanctnerId(rwardManageVOTemp.getSanctnerId());
		rwardManageTemp.setInfrmlSanctnId(rwardManageVOTemp.getInfrmlSanctnId());

		model.addAttribute("rwardManage",   rwardManageTemp);
    	model.addAttribute("rwardManageVO", rwardManageVOTemp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/rwd/EgovRwardConfm";
    }

	/**
	 * 신청된 포상을 승인처리한다.
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/rwd/updtRwardConfm.do")
	 public String updtRwardManageConfm( @ModelAttribute("rwardManage")   RwardManage   rwardManage,
			                               BindingResult bindingResult,
			                               SessionStatus status,
		                                   ModelMap model) throws Exception {
		beanValidator.validate(rwardManage, bindingResult); //validation 수행

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("rwardManageVO", rwardManage);
			return "egovframework/com/uss/ion/vct/EgovRwardConfm";
		} else {

	    	rwardManage.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	rwardManage.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));

	    	egovRwardManageService.updtRwardManageConfm(rwardManage);
	    	return "forward:/uss/ion/rwd/EgovRwardConfmList.do";
		}
	}
}
