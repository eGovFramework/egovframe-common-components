package egovframework.com.uss.ion.ctn.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ctn.service.CtsnnManageVO;
import egovframework.com.uss.ion.ctn.service.EgovCtsnnManageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 개요
 * - 경조관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 경조관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *  * <pre>
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
public class EgovCtsnnManageController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovCtsnnManageService")
    private EgovCtsnnManageService egovCtsnnManageService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /**
	 * 경조관리 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/ctn/EgovCtsnnManageListView.do")
    public String selectCtsnnManageListView(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
                                            ModelMap model) throws Exception {
    	List<CmmnDetailCode> ctsnnCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("ctsnnCodeList",    ctsnnCdCodeList);
        return "egovframework/com/uss/ion/ctn/EgovCtsnnManageList";
    }

	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="직원경조사관리",order = 890 ,gid = 50)
    @RequestMapping(value="/uss/ion/ctn/selectCtsnnManageList.do")
	 public String selectCtsnnManageList(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                                 ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ctsnnManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctsnnManageVO.getPageUnit());
		paginationInfo.setPageSize(ctsnnManageVO.getPageSize());

		ctsnnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctsnnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctsnnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		ctsnnManageVO.setCtsnnManageList(egovCtsnnManageService.selectCtsnnManageList(ctsnnManageVO));

		int totCnt = egovCtsnnManageService.selectCtsnnManageListTotCnt(ctsnnManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

    	List<CmmnDetailCode> ctsnnCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("paginationInfo" ,   paginationInfo );
		model.addAttribute("ctsnnManageList",   ctsnnManageVO.getCtsnnManageList());
        model.addAttribute("ctsnnCodeList"  ,   ctsnnCdCodeList);
 		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/ctn/EgovCtsnnManageList";
	}

	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnManageDetail.do")
	 public String selectCtsnnManage(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                         @RequestParam Map<?, ?> commandMap,
			                         ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	ctsnnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManageVO.getReqstDe()));

        // 등록 상세정보
    	CtsnnManageVO ctsnnManageVOTemp = egovCtsnnManageService.selectCtsnnManage(ctsnnManageVO);

    	model.addAttribute("ctsnnManageVO", ctsnnManageVOTemp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(sCmd.equals("updt")){

	    	List<CmmnDetailCode> ctsnnCdCodeList = null;
	    	List<CmmnDetailCode> relateCodeList  = null;
	    	ComDefaultCodeVO vo  = new ComDefaultCodeVO();
			vo.setCodeId("COM054");
			ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
			vo.setCodeId("COM073");
			relateCodeList = cmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("ctsnnCodeList",    ctsnnCdCodeList);
			model.addAttribute("relateCodeList",    relateCodeList);

			return "egovframework/com/uss/ion/ctn/EgovCtsnnUpdt";
		}else{
			return "egovframework/com/uss/ion/ctn/EgovCtsnnDetail";
		}

	}

	/**
	 * 경조관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnRegist.do")
	 public String insertViewCtsnnManage(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                             ModelMap model) throws Exception {

		// 신청자: 로그인 사용자로 설정 (EgovVcatnRegist 방식)
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (user != null) {
			ctsnnManageVO.setUsid(user.getUniqId() != null ? user.getUniqId() : "");
			ctsnnManageVO.setUsNm(user.getName() != null ? user.getName() : "");
			ctsnnManageVO.setOrgnztNm(user.getOrgnztNm() != null ? user.getOrgnztNm() : "");
		}

    	List<CmmnDetailCode> ctsnnCdCodeList = null;
    	List<CmmnDetailCode> relateCodeList  = null;
    	ComDefaultCodeVO vo  = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
		vo.setCodeId("COM073");
		relateCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("ctsnnCodeList",    ctsnnCdCodeList);
		model.addAttribute("relateCodeList",    relateCodeList);
    	return "egovframework/com/uss/ion/ctn/EgovCtsnnRegist";
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/insertCtsnnManage.do")
	 public String insertCtsnnManage(@Valid @ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                            BindingResult bindingResult,
			                            SessionStatus status,
			                            HttpServletRequest request,
						                ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
    		// 유효성 오류 시 신청자 표시용: 로그인 사용자로 유지 (EgovVcatnRegist 방식)
    		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		if (user != null) {
    			ctsnnManageVO.setUsid(user.getUniqId() != null ? user.getUniqId() : "");
    			ctsnnManageVO.setUsNm(user.getName() != null ? user.getName() : "");
    			ctsnnManageVO.setOrgnztNm(user.getOrgnztNm() != null ? user.getOrgnztNm() : "");
    		}
    		ComDefaultCodeVO vo = new ComDefaultCodeVO();
    		vo.setCodeId("COM054");
    		List<CmmnDetailCode> ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
    		vo.setCodeId("COM073");
    		List<CmmnDetailCode> relateCodeList = cmmUseService.selectCmmCodeDetail(vo);
    		model.addAttribute("ctsnnManageVO", ctsnnManageVO);
    		model.addAttribute("ctsnnCodeList", ctsnnCdCodeList);
    		model.addAttribute("relateCodeList", relateCodeList);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnRegist";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	ctsnnManageVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	egovCtsnnManageService.insertCtsnnManage(ctsnnManageVO);
	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	return "forward:/uss/ion/ctn/selectCtsnnManageList.do";
		}
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/ctn/updtCtsnnManage.do")
	 public String updtCtsnnManage(@Valid @ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                        BindingResult bindingResult,
			                        SessionStatus status,
		                            ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
    		// 유효성 오류 시 코드 목록·표시용 필드(usNm, orgnztNm 등) 유지를 위해 상세 재조회 후 제출값 반영
    		CtsnnManageVO detail = egovCtsnnManageService.selectCtsnnManage(ctsnnManageVO);
    		if (detail != null) {
    			detail.setCtsnnNm(ctsnnManageVO.getCtsnnNm());
    			detail.setCtsnnCd(ctsnnManageVO.getCtsnnCd());
    			detail.setOccrrDe(ctsnnManageVO.getOccrrDe());
    			detail.setTrgterNm(ctsnnManageVO.getTrgterNm());
    			detail.setRelate(ctsnnManageVO.getRelate());
    			detail.setBrth(ctsnnManageVO.getBrth());
    			detail.setRemark(ctsnnManageVO.getRemark());
    			ctsnnManageVO = detail;
    		}
    		ComDefaultCodeVO vo = new ComDefaultCodeVO();
    		vo.setCodeId("COM054");
    		List<CmmnDetailCode> ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
    		vo.setCodeId("COM073");
    		List<CmmnDetailCode> relateCodeList = cmmUseService.selectCmmCodeDetail(vo);
    		model.addAttribute("ctsnnManageVO", ctsnnManageVO);
    		model.addAttribute("ctsnnCodeList", ctsnnCdCodeList);
    		model.addAttribute("relateCodeList", relateCodeList);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnUpdt";
		} else {
	    	egovCtsnnManageService.updtCtsnnManage(ctsnnManageVO);
	    	return "forward:/uss/ion/ctn/selectCtsnnManageList.do";
		}
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/deleteCtsnnManage.do")
	 public String deleteCtsnnManage(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                         SessionStatus status,
			                         ModelMap model) throws Exception {

    	egovCtsnnManageService.deleteCtsnnManage(ctsnnManageVO);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/ctn/selectCtsnnManageList.do";
	}


    /*** 승인관련 ***/
	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="직원경조사승인관리",order = 891 ,gid = 50)
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnConfmList.do")
	 public String selectCtsnnManageConfmList(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                                  ModelMap model) throws Exception {
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ctsnnManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctsnnManageVO.getPageUnit());
		paginationInfo.setPageSize(ctsnnManageVO.getPageSize());

		ctsnnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctsnnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctsnnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	ctsnnManageVO.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId()); //사용자가 승인권자인지 조건값 setting   selectCtsnnManageList
    	ctsnnManageVO.setCtsnnManageList(egovCtsnnManageService.selectCtsnnManageConfmList(ctsnnManageVO));
		model.addAttribute("ctsnnManageList", ctsnnManageVO.getCtsnnManageList());

		int totCnt = egovCtsnnManageService.selectCtsnnManageConfmListTotCnt(ctsnnManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

    	List<CmmnDetailCode> ctsnnCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

        model.addAttribute("ctsnnCodeList"  ,   ctsnnCdCodeList);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/ctn/EgovCtsnnConfmList";
	}

	/**
	 * 경조승인관리 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnConfm.do")
	 public String selectCtsnnConfm(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
							         ModelMap model) throws Exception {
    	ctsnnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManageVO.getReqstDe()));

        // 등록 상세정보
    	CtsnnManageVO ctsnnManageVOTemp = egovCtsnnManageService.selectCtsnnManage(ctsnnManageVO);

    	model.addAttribute("ctsnnManageVO", ctsnnManageVOTemp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/ctn/EgovCtsnnConfm";
    }

	/**
	 * 기 등록된 경조관리정보를 승인 처리한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/ctn/updtCtsnnConfm.do")
	 public String updateCtsnnManageConfm(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                               BindingResult bindingResult,
			                               SessionStatus status,
		                                   ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("ctsnnManageVO", ctsnnManageVO);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnConfm";
		} else {

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	ctsnnManageVO.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	ctsnnManageVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	ctsnnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManageVO.getReqstDe()));

	    	egovCtsnnManageService.updtCtsnnManageConfm(ctsnnManageVO);
	    	return "forward:/uss/ion/ctn/EgovCtsnnConfmList.do";
		}
	}

	/**
	 * 경조관리정보 반려처리 화면을 호출한다.
	 * @param ctsnnManage
	 * @return  String
	 */
	@RequestMapping("/uss/ion/ctn/EgovCtsnnReturn.do")
	public String selectSanctnerListPopup(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
										  @RequestParam Map<?, ?> commandMap,
                                          ModelMap model) throws Exception{
		return "egovframework/com/uss/ion/ctn/EgovCtsnnReturn";
	}

}
