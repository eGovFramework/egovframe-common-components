package egovframework.com.uss.ion.mtg.web;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.mtg.service.EgovMtgPlaceManageService;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManage;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceResve;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 회의실관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.6.15	이용          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */


@Controller
public class EgovMtgPlaceManageController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovMtgPlaceManageService")
    private EgovMtgPlaceManageService egovMtgPlaceManageService;

    @Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    /**
	 * 회의실관리 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/mtg/selectMtgPlaceManageListView.do")
    public String selectMtgPlaceManageListView() throws Exception {

        return "egovframework/com/uss/ion/mtg/EgovMtgPlaceManageList";
    }

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="회의실관리", order = 870 ,gid = 50)
    @RequestMapping(value="/uss/ion/mtg/selectMtgPlaceManageList.do")
	 public String selectMtgPlaceManageList(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
											@ModelAttribute("mtgPlaceManage") MtgPlaceManage mtgPlaceManage,
											BindingResult bindingResult,
			                                ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mtgPlaceManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mtgPlaceManageVO.getPageUnit());
		paginationInfo.setPageSize(mtgPlaceManageVO.getPageSize());

		mtgPlaceManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mtgPlaceManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mtgPlaceManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		mtgPlaceManageVO.setMtgPlaceManageList(egovMtgPlaceManageService.selectMtgPlaceManageList(mtgPlaceManageVO));

		int totCnt = egovMtgPlaceManageService.selectMtgPlaceManageListTotCnt(mtgPlaceManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("mtgPlaceManageList", mtgPlaceManageVO.getMtgPlaceManageList());
		model.addAttribute("mtgPlaceManageVO"  , mtgPlaceManageVO);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceManageList";
	}

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/mtg/selectMtgPlaceManage.do")
	 public String selectMtgPlaceManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
									    @ModelAttribute("mtgPlaceManage") MtgPlaceManage mtgPlaceManage,
										@RequestParam Map<?, ?> commandMap,
						                ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM070");
        List<?> lcSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
                
        model.addAttribute("lcSeCode",          lcSeCodeList);
    	model.addAttribute("mtgPlaceManage",  egovMtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(sCmd.equals("update")){
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceUpdt";
		}else{
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceDetail";
		}
	}

	/**
	 * 회의실관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/mtg/insertViewMtgPlace.do")
	 public String insertViewMtgPlaceManage(@ModelAttribute("mtgPlaceManage") MtgPlaceManage mtgPlaceManage,
                                            @ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			                                ModelMap model) throws Exception {

    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM070");
        List<?> lcSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

        model.addAttribute("lcSeCode",        lcSeCodeList);

    	return "egovframework/com/uss/ion/mtg/EgovMtgPlaceRegist";
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 * @return String - 리턴 Url
	 */
    @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/mtg/insertMtgPlace.do")
	 public String insertMtgPlaceManage(final MultipartHttpServletRequest multiRequest,			                            
			 							@ModelAttribute("mtgPlaceManage") MtgPlaceManage mtgPlaceManage,
			                            @ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	beanValidator.validate(mtgPlaceManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceRegist";
		} else {
	    	// 첨부파일 관련 첨부파일ID 생성
			List<FileVO> _result = null;
			String _atchFileId = "";

			//final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");
			if(!files.isEmpty()){
			 _result = fileUtil.parseFileInf(files, "MTG_", 0, "", "");
			 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			}
	    	// 리턴받은 첨부파일ID를 셋팅한다..
			mtgPlaceManage.setAtchFileId(_atchFileId);			// 첨부파일 ID
 
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	status.setComplete();
	    	egovMtgPlaceManageService.insertMtgPlaceManage(mtgPlaceManage, mtgPlaceManageVO);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

	    	return "redirect:/uss/ion/mtg/selectMtgPlaceManageList.do";
		}
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 * @return String - 리턴 Url
	 */
	 @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/mtg/updtMtgPlace.do")
	 public String updateMtgPlaceManage(final MultipartHttpServletRequest multiRequest,
                                        @RequestParam("atchFileAt") String atchFileAt ,
							            @ModelAttribute("mtgPlaceManage") MtgPlaceManage mtgPlaceManage,
			                            @ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
		                                ModelMap model) throws Exception {

		beanValidator.validate(mtgPlaceManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("mtgPlaceManageVO", mtgPlaceManage);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceUpdt";
		} else {

	    	// 첨부파일 관련 ID 생성 start....
			String _atchFileId = mtgPlaceManage.getAtchFileId();

			//final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");

			if(!files.isEmpty()){

				if("N".equals(atchFileAt)){
					List<FileVO> _result = fileUtil.parseFileInf(files, "MTG_", 0, _atchFileId, "");
					_atchFileId = fileMngService.insertFileInfs(_result);

					// 첨부파일 ID 셋팅
					mtgPlaceManage.setAtchFileId(_atchFileId);    	// 첨부파일 ID

				}else{
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(_atchFileId);
					int _cnt = fileMngService.getMaxFileSN(fvo);
					List<FileVO> _result = fileUtil.parseFileInf(files, "MTG_", _cnt, _atchFileId, "");
					fileMngService.updateFileInfs(_result);
				}
			}
			// 첨부파일 관련 ID 생성 end...

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	status.setComplete();
	    	egovMtgPlaceManageService.updtMtgPlaceManage(mtgPlaceManage, mtgPlaceManageVO);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

	    	return "redirect:/uss/ion/mtg/selectMtgPlaceManageList.do";
		}
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/mtg/deleteMtgPlaceManage.do")
	 public String deleteMtgPlaceManage(@ModelAttribute("mtgPlaceManage") MtgPlaceManage mtgPlaceManage,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {
    	// 첨부파일 삭제를 위한 ID 생성 start....
		String _atchFileId = mtgPlaceManage.getAtchFileId();

    	egovMtgPlaceManageService.deleteMtgPlaceManage(mtgPlaceManage);

    	// 첨부파일을 삭제하기 위한  Vo
    	FileVO fvo = new FileVO();
    	fvo.setAtchFileId(_atchFileId);

    	fileMngService.deleteAllFileInf(fvo);
    	// 첨부파일 삭제 End.............

    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/mtg/selectMtgPlaceManageList.do";
	}

	/**
	 * 등록된 회의실관리의 이미지 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/mtg/selectMtgPlaceImage.do")
	 public String selectMtgPlaceImage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			                           @RequestParam("sTmMtgPlaceId")    String sTmMtgPlaceId,
			                           @RequestParam Map<?, ?> commandMap,
						                ModelMap model) throws Exception {
    	mtgPlaceManageVO.setMtgPlaceId(sTmMtgPlaceId);

    	MtgPlaceManage mtgPlaceManage_Temp = egovMtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO);

    	FileVO fileVO = new FileVO();
    	fileVO.setAtchFileId(mtgPlaceManage_Temp.getAtchFileId());
    	List<FileVO> result = fileMngService.selectImageFileList(fileVO);

    	model.addAttribute("fileList", result);
    	model.addAttribute("mtgPlaceManage",  egovMtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceImageDetail";
	}

	/**** 회의실 예약  ****/

		/**
		 * 회의실예약 정보를 관리하기 위해 등록된 회의실예약 목록을 조회한다.
		 * @param mtgPlaceManageVO - 회의실관리 VO
		 * @return String - 리턴 Url
		 */
    @IncludedInfo(name="회의실예약관리", order = 871 ,gid = 50)
    @RequestMapping(value="/uss/ion/mtg/selectMtgPlaceResveManageList.do")
	public String selectMtgPlaceResveManageList(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			                                     ModelMap model) throws Exception {
		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
/*
			String strYear = (String)commandMap.get("year");
			String strMonth = (String)commandMap.get("month");
			String strDay =( String)commandMap.get("day");
*/
		String strSearchDay = "";

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDay = calNow.get(Calendar.DATE);

		if(mtgPlaceManageVO.getResveDe() == null)
		{
			strSearchDay = Integer.toString(iNowYear);
			strSearchDay += dateTypeIntForString(iNowMonth+1);
			strSearchDay += dateTypeIntForString(iNowDay);
			mtgPlaceManageVO.setResveDe(strSearchDay);
			mtgPlaceManageVO.setResveDeView(EgovDateUtil.formatDate(strSearchDay, "-"));
		}else{
			mtgPlaceManageVO.setResveDeView(EgovDateUtil.formatDate(mtgPlaceManageVO.getResveDe(), "-"));
		}
		
		mtgPlaceManageVO.setResveDe(EgovDateUtil.formatDate(mtgPlaceManageVO.getResveDe(), "-"));  //formatDate
		//mtgPlaceManageVO.setResveDe(mtgPlaceManageVO.getResveDe());
		mtgPlaceManageVO.setMtgPlaceManageList(egovMtgPlaceManageService.selectMtgPlaceResveManageList(mtgPlaceManageVO));
		model.addAttribute("mtgPlaceManageList", mtgPlaceManageVO.getMtgPlaceManageList());
		model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
		//model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveManageList";
	}

	/**
	 * 회의실예약 신청 화면을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
     @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/mtg/selectMtgPlaceResveManage.do")
	 public String selectMtgPlaceResveManage(	@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
											    @ModelAttribute("mtgPlaceResve") MtgPlaceResve mtgPlaceResve,
					 							BindingResult bindingResult,
					 							@RequestParam Map<?, ?> commandMap,
								                ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	String sTempResveDe = mtgPlaceManageVO.getResveDe();
    	String sTempResveBeginTm = mtgPlaceManageVO.getResveBeginTm();
    	String sTempResveEndTm = mtgPlaceManageVO.getResveEndTm();
    	MtgPlaceManageVO mtgPlaceManageVO_Temp = new MtgPlaceManageVO();

    	mtgPlaceManageVO_Temp = egovMtgPlaceManageService.selectMtgPlaceResve(mtgPlaceManageVO);
        mtgPlaceManageVO_Temp.setResveDe(sTempResveDe);
    	mtgPlaceManageVO_Temp.setResveBeginTm(sTempResveBeginTm);
    	mtgPlaceManageVO_Temp.setResveEndTm(sTempResveEndTm);
    	mtgPlaceManageVO_Temp.setResveDe(EgovDateUtil.formatDate(mtgPlaceManageVO_Temp.getResveDe(), "-"));

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		mtgPlaceManageVO_Temp.setMtgPlaceTemp4(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
		mtgPlaceManageVO_Temp.setMtgPlaceTemp5(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztNm()));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

    	model.addAttribute("mtgPlaceManageVO",  mtgPlaceManageVO_Temp);
		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveRegist";
	}

	/**
	 * 등록된 회의실예약 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
     @RequestMapping(value="/uss/ion/mtg/selectMtgPlaceResveManageDetail.do")
	 public String selectMtgPlaceResveManageDetail(	@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
											    @ModelAttribute("mtgPlaceResve") MtgPlaceResve mtgPlaceResve,
					 							BindingResult bindingResult,
					 							@RequestParam Map<?, ?> commandMap,
								                ModelMap model) throws Exception {
    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	MtgPlaceManageVO mtgPlaceManageVO_Temp = new MtgPlaceManageVO();

    	mtgPlaceManageVO_Temp = egovMtgPlaceManageService.selectMtgPlaceResveDetail(mtgPlaceManageVO);
    	mtgPlaceManageVO_Temp.setResveDe(EgovDateUtil.formatDate(mtgPlaceManageVO_Temp.getResveDe(), "-"));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(sCmd.equals("detail")){
			LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		mtgPlaceManageVO_Temp.setUsidTemp(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			String resveBeginTm = mtgPlaceManageVO_Temp.getResveBeginTm();
			String resveEndTm   = mtgPlaceManageVO_Temp.getResveEndTm();
			if( resveBeginTm.length()      == 3) resveBeginTm = "0"+resveBeginTm.substring(0,1)+":"+resveBeginTm.substring(1,3);
			else if( resveBeginTm.length() == 4) resveBeginTm =     resveBeginTm.substring(0,2)+":"+resveBeginTm.substring(2,4);
			if( resveEndTm.length()        == 3) resveEndTm   = "0"+resveEndTm.substring(0,1)+":"+resveEndTm.substring(1,3);
			else if( resveEndTm.length()   == 4) resveEndTm   =     resveEndTm.substring(0,2)+":"+resveEndTm.substring(2,4);

			mtgPlaceManageVO_Temp.setResveBeginTm(resveBeginTm);
			mtgPlaceManageVO_Temp.setResveEndTm(resveEndTm);
	    	model.addAttribute("mtgPlaceManageVO",  mtgPlaceManageVO_Temp);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveDetail";
		}else{
		    model.addAttribute("mtgPlaceManageVO",  mtgPlaceManageVO_Temp);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveUpdt";
		}
	}

	/**
	 * 회의실예약 정보를 신규로 등록한다.
	 * @param mtgPlaceResve - 회의실예약 model
	 * @return String - 리턴 Url
	 */
     @RequestMapping(value="/uss/ion/mtg/insertMtgPlaceResve.do")
	 public String insertMtgPlaceResveManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
									         @ModelAttribute("mtgPlaceResve") MtgPlaceResve mtgPlaceResve,
											 BindingResult bindingResult,
			                                 SessionStatus status,
						                     ModelMap model) throws Exception {

    	beanValidator.validate(mtgPlaceResve, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
			return "forward:/uss/ion/mtg/selectMtgPlaceResveManage.do";
		} else {

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	mtgPlaceResve.setResveManId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    	mtgPlaceResve.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

	    	egovMtgPlaceManageService.insertMtgPlaceResve(mtgPlaceResve);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

	    	return "forward:/uss/ion/mtg/selectMtgPlaceResveManageList.do";
		}
	}

	/**
	 * 기 등록된 회의실예약 정보를 수정한다.
	 * @param mtgPlaceResve - 회의실예약 model
	 * @return String - 리턴 Url
	 */
	 @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/mtg/updtMtgPlaceResve.do")
	 public String updtMtgPlaceResveManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
							         @ModelAttribute("mtgPlaceResve") MtgPlaceResve mtgPlaceResve,
									 BindingResult bindingResult,
					                 SessionStatus status,
					                 ModelMap model) throws Exception {

		beanValidator.validate(mtgPlaceResve, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("mtgPlaceManageVO", mtgPlaceResve);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveUpdt";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	status.setComplete();
	    	egovMtgPlaceManageService.updtMtgPlaceResve(mtgPlaceResve);
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

	    	return "forward:/uss/ion/mtg/selectMtgPlaceResveManageList.do";
		}
	}

	/**
	 * 기 등록된 회의실예약 정보를 삭제한다.
	 * @param mtgPlaceResve - 회의실예약 model
	 * @return String - 리턴 Url
	 */
     @RequestMapping(value="/uss/ion/mtg/deleteMtgPlaceResve.do")
	 public String deleteMtgPlaceResveManage(@ModelAttribute("mtgPlaceResve") MtgPlaceResve mtgPlaceResve,
			                             SessionStatus status,
			                             ModelMap model) throws Exception {

    	egovMtgPlaceManageService.deleteMtgPlaceResve(mtgPlaceResve);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/mtg/selectMtgPlaceResveManageList.do";
	}



	/**
	 * 회의실 중복여부 체크.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
     @RequestMapping(value="/uss/ion/mtg/mtgPlaceResveDplactCeck.do")
	 public String mtgPlaceResveDplactCeck(  @ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
											 @RequestParam("sTmResveDe")      String sTempResveDe ,
											 @RequestParam("sTmResveBeginTm") String sTempResveBeginTm ,
											 @RequestParam("sTmResveEndTm")   String sTempResveEndTm ,
											 @RequestParam("sTmMtgPlaceId")   String sTempMtgPlaceId,
											 @RequestParam("sTmResveId")    String sTempResveId,
							                 ModelMap model) throws Exception {
    	mtgPlaceManageVO.setResveDe(sTempResveDe);
    	mtgPlaceManageVO.setMtgPlaceId(sTempMtgPlaceId);
    	mtgPlaceManageVO.setResveBeginTm(sTempResveBeginTm);
    	mtgPlaceManageVO.setResveEndTm(sTempResveEndTm);
    	mtgPlaceManageVO.setResveId(sTempResveId);
    	int dplactCeckCnt = egovMtgPlaceManageService.mtgPlaceResveDplactCeck(mtgPlaceManageVO);
    	model.addAttribute("dplactCeck", dplactCeckCnt);
		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveDplactCeck";
	}


	/**
	 * 0을 붙여 반환
	 * @return  String
	 * @throws
	 */
     private String dateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}
       return sOutput;
    }
}
