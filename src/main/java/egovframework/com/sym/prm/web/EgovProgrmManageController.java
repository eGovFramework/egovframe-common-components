package egovframework.com.sym.prm.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 프로그램목록 관리및 변경을 처리하는 비즈니스 구현 클래스
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.08.22  서준식          selectProgrmChangRequstProcess() 메서드 처리일자 trim 처리
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovProgrmManageController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovProgrmManageService */
	@Resource(name = "progrmManageService")
    private EgovProgrmManageService progrmManageService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovSndngMailRegistService */
	@Resource(name = "sndngMailRegistService")
    private EgovSndngMailRegistService sndngMailRegistService;

    /**
     * 프로그램목록을 상세화면 호출 및 상세조회한다.
     * @param tmp_progrmNm  String
     * @return 출력페이지정보 "sym/prm/EgovProgramListDetailSelectUpdt"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramListDetailSelect.do")
    public String selectProgrm(
    		@RequestParam("tmp_progrmNm") String tmp_progrmNm ,
   		    @ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
    	ProgrmManageVO vo = new ProgrmManageVO();
    	vo.setProgrmFileNm(tmp_progrmNm);
    	ProgrmManageVO progrmManageVO = progrmManageService.selectProgrm(vo);
        model.addAttribute("progrmManageVO", progrmManageVO);
        return "egovframework/com/sym/prm/EgovProgramListDetailSelectUpdt";
    }

    /**
     * 프로그램목록 리스트조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovProgramListManage"
     * @exception Exception
     */
    @IncludedInfo(name="프로그램관리",order = 1111 ,gid = 60)
    @RequestMapping(value="/sym/prm/EgovProgramListManageSelect.do")
    public String selectProgrmList(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	// 내역 조회
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_progrmmanage = progrmManageService.selectProgrmList(searchVO);
        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

      	return "egovframework/com/sym/prm/EgovProgramListManage";

    }

    /**
     * 프로그램목록 멀티 삭제한다.
     * @param checkedProgrmFileNmForDel String
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramListManageSelect.do"
     * @exception Exception
     */
    @RequestMapping("/sym/prm/EgovProgrmManageListDelete.do")
    public String deleteProgrmManageList(
            @RequestParam("checkedProgrmFileNmForDel") String checkedProgrmFileNmForDel ,
            @ModelAttribute("progrmManageVO") ProgrmManageVO progrmManageVO,
            ModelMap model)
            throws Exception {
		String sLocationUrl = null;
    	String resultMsg    = "";
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	String [] delProgrmFileNm = checkedProgrmFileNmForDel.split(",");
		if (delProgrmFileNm == null || (delProgrmFileNm.length ==0)){
    		resultMsg = egovMessageSource.getMessage("fail.common.delete");
    		sLocationUrl = "forward:/sym/prm/EgovProgramListManageSelect.do";
		}else{
    	   progrmManageService.deleteProgrmManageList(checkedProgrmFileNmForDel);
    	   resultMsg = egovMessageSource.getMessage("success.common.delete");
    	   sLocationUrl ="forward:/sym/prm/EgovProgramListManageSelect.do";
		}
		model.addAttribute("resultMsg", resultMsg);
        //status.setComplete();
        return sLocationUrl ;
    }

    /**
     * 프로그램목록을 등록화면으로 이동 및 등록 한다.
     * @param progrmManageVO ProgrmManageVO
     * @param commandMap     Map
     * @return 출력페이지정보 등록화면 호출시 "sym/prm/EgovProgramListRegist",
     *         출력페이지정보 등록처리시 "forward:/sym/prm/EgovProgramListManageSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramListRegist.do")
    public String insertProgrmList(
    		@RequestParam Map<?, ?> commandMap,
    		@ModelAttribute("progrmManageVO") ProgrmManageVO progrmManageVO,
			BindingResult bindingResult,
			ModelMap model)
            throws Exception {
        String resultMsg = "";
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

        String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
        if(sCmd.equals("insert")){
	        beanValidator.validate(progrmManageVO, bindingResult);
			if (bindingResult.hasErrors()){
				sLocationUrl = "egovframework/com/sym/prm/EgovProgramListRegist";
				return sLocationUrl;
			}
			if(progrmManageVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	progrmManageService.insertProgrm(progrmManageVO);
			resultMsg = egovMessageSource.getMessage("success.common.insert");
	        sLocationUrl = "forward:/sym/prm/EgovProgramListManageSelect.do";
        }else{
            sLocationUrl = "egovframework/com/sym/prm/EgovProgramListRegist";
        }
    	model.addAttribute("resultMsg", resultMsg);
		return sLocationUrl;
    }

    /**
     * 프로그램목록을 수정 한다.
     * @param progrmManageVO ProgrmManageVO
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramListManageSelect.do"
     * @exception Exception
     */
    /*프로그램목록수정*/
    @RequestMapping(value="/sym/prm/EgovProgramListDetailSelectUpdt.do")
    public String updateProgrmList(
    		@ModelAttribute("progrmManageVO") ProgrmManageVO progrmManageVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
		String resultMsg = "";
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
   	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

        beanValidator.validate(progrmManageVO, bindingResult);
		if (bindingResult.hasErrors()){
			sLocationUrl = "forward:/sym/prm/EgovProgramListDetailSelect.do";
			return sLocationUrl;
		}
		if(progrmManageVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
		progrmManageService.updateProgrm(progrmManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.update");
        sLocationUrl = "forward:/sym/prm/EgovProgramListManageSelect.do";
    	model.addAttribute("resultMsg", resultMsg);
		return sLocationUrl;
    }

    /**
     * 프로그램목록을 삭제 한다.
     * @param progrmManageVO ProgrmManageVO
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramListManageSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramListManageDelete.do")
    public String deleteProgrmList(
    		@ModelAttribute("progrmManageVO")
    		ProgrmManageVO progrmManageVO,
    		ModelMap model)
            throws Exception {
    	String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
        progrmManageService.deleteProgrm(progrmManageVO);
        resultMsg = egovMessageSource.getMessage("success.common.delete");
    	model.addAttribute("resultMsg", resultMsg);
        return "forward:/sym/prm/EgovProgramListManageSelect.do";
    }

    /**
     * 프로그램변경요청목록 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovProgramChangeRequst"
     * @exception Exception
     */
    @IncludedInfo(name="프로그램변경요청관리",order = 1112 ,gid = 60)
    @RequestMapping(value="/sym/prm/EgovProgramChangeRequstSelect.do")
    public String selectProgrmChangeRequstList(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	// 내역 조회
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_changerequst = progrmManageService.selectProgrmChangeRequstList(searchVO);
        model.addAttribute("list_changerequst", list_changerequst);

        int totCnt = progrmManageService.selectProgrmChangeRequstListTotCnt(searchVO);
 		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

         return "egovframework/com/sym/prm/EgovProgramChangeRequst";
    }

    /**
     * 프로그램변경요청목록을 상세조회한다.
     * @param progrmManageDtlVO ProgrmManageDtlVO
     * @return 출력페이지정보 "sym/prm/EgovProgramChangRequstDetailSelectUpdt"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramChangRequstDetailSelect.do")
    public String selectProgrmChangeRequst(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		ModelMap model)
            throws Exception {
         // 0. Spring Security 사용자권한 처리
    	 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	 if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	 }
         if(progrmManageDtlVO.getProgrmFileNm()== null||progrmManageDtlVO.getProgrmFileNm().equals("")){
	    	 String FileNm = progrmManageDtlVO.getTmpProgrmNm();
	         progrmManageDtlVO.setProgrmFileNm(FileNm);
	         int tmpNo = progrmManageDtlVO.getTmpRqesterNo();
	         progrmManageDtlVO.setRqesterNo(tmpNo);
         }
         ProgrmManageDtlVO resultVO = progrmManageService.selectProgrmChangeRequst(progrmManageDtlVO);
         model.addAttribute("progrmManageDtlVO", resultVO);
         return "egovframework/com/sym/prm/EgovProgramChangRequstDetailSelectUpdt";
    }

    /**
     * 프로그램변경요청 화면을 호출및 프로그램변경요청을 등록한다.
     * @param progrmManageDtlVO ProgrmManageDtlVO
     * @param commandMap        Map
     * @return 출력페이지정보 등록화면 호출시 "sym/prm/EgovProgramChangRequstStre",
     *         출력페이지정보 등록처리시 "forward:/sym/prm/EgovProgramChangeRequstSelect.do"
     * @exception Exception
     */
    /*프로그램변경요청등록*/
    @RequestMapping(value="/sym/prm/EgovProgramChangRequstStre.do")
    public String insertProgrmChangeRequst(
    		@RequestParam Map<?, ?> commandMap,
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
    	String resultMsg = "";
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
       	    return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        String sLocationUrl = null;
        String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
        if(sCmd.equals("insert")){
    		//beanValidator 처리
	        beanValidator.validate(progrmManageDtlVO, bindingResult);
			if (bindingResult.hasErrors()){
				sLocationUrl = "egovframework/com/sym/prm/EgovProgramChangRequstStre";
				return sLocationUrl;
			}
			if(progrmManageDtlVO.getChangerqesterCn()==null || progrmManageDtlVO.getChangerqesterCn().equals("")){progrmManageDtlVO.setChangerqesterCn("");}
			if(progrmManageDtlVO.getRqesterProcessCn()==null || progrmManageDtlVO.getRqesterProcessCn().equals("")){progrmManageDtlVO.setRqesterProcessCn("");}
			progrmManageService.insertProgrmChangeRequst(progrmManageDtlVO);
	    	resultMsg = egovMessageSource.getMessage("success.common.insert");
	        sLocationUrl = "forward:/sym/prm/EgovProgramChangeRequstSelect.do";
        }else{
	        /* MAX요청번호 조회 */
	    	ProgrmManageDtlVO tmp_vo = progrmManageService.selectProgrmChangeRequstNo(progrmManageDtlVO);
			int _tmp_no = tmp_vo.getRqesterNo();
			progrmManageDtlVO.setRqesterNo(_tmp_no);
			progrmManageDtlVO.setRqesterPersonId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
            sLocationUrl = "egovframework/com/sym/prm/EgovProgramChangRequstStre";
        }
    	model.addAttribute("resultMsg", resultMsg);
		return sLocationUrl;
    }

    /**
     * 프로그램변경 요청을 수정 한다.
     * @param progrmManageDtlVO  ProgrmManageDtlVO
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramChangeRequstSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramChangRequstDetailSelectUpdt.do")
    public String updateProgrmChangeRequst(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
        String sLocationUrl = null;
    	String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//beanValidator 처리
        beanValidator.validate(progrmManageDtlVO, bindingResult);
		if (bindingResult.hasErrors()){
			sLocationUrl = "forward:/sym/prm/EgovProgramChangRequstDetailSelect.do";
			return sLocationUrl;
		}

		//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	if(EgovStringUtil.isNullToString(progrmManageDtlVO.getRqesterPersonId()).equals(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getId()))){
			if(progrmManageDtlVO.getChangerqesterCn()==null || progrmManageDtlVO.getChangerqesterCn().equals("")){progrmManageDtlVO.setChangerqesterCn(" ");}
			if(progrmManageDtlVO.getRqesterProcessCn()==null || progrmManageDtlVO.getRqesterProcessCn().equals("")){progrmManageDtlVO.setRqesterProcessCn(" ");}
	        progrmManageService.updateProgrmChangeRequst(progrmManageDtlVO);
    		resultMsg = egovMessageSource.getMessage("success.common.update");
	        sLocationUrl = "forward:/sym/prm/EgovProgramChangeRequstSelect.do";
    	}else{
    		resultMsg = "수정이 실패하였습니다. 변경요청 수정은 변경요청자만 수정가능합니다.";
            progrmManageDtlVO.setTmpProgrmNm(progrmManageDtlVO.getProgrmFileNm());
            progrmManageDtlVO.setTmpRqesterNo(progrmManageDtlVO.getRqesterNo());
			sLocationUrl = "forward:/sym/prm/EgovProgramChangRequstDetailSelect.do";
    	}
    	model.addAttribute("resultMsg", resultMsg);
		return sLocationUrl;
    }

    /**
     * 프로그램변경 요청을 삭제 한다.
     * @param progrmManageDtlVO  ProgrmManageDtlVO
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramChangeRequstSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramChangRequstDelete.do")
    public String deleteProgrmChangeRequst(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	if(EgovStringUtil.isNullToString(progrmManageDtlVO.getRqesterPersonId()).equals(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getId()))){
    	//progrmManageDtlVO.setRqesterPersonId(user.getId());
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("success.common.delete"));
	        progrmManageService.deleteProgrmChangeRequst(progrmManageDtlVO);
	        sLocationUrl = "forward:/sym/prm/EgovProgramChangeRequstSelect.do";
    	}else{
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("comSymPrm.progrmManageController.checkRqesterPersonId")); //삭제에 실패하였습니다. 변경요청자만 삭제가능합니다.
			sLocationUrl = "forward:/sym/prm/EgovProgramChangRequstDetailSelect.do";
    	}
        return sLocationUrl;
    }

    /**
     * 프로그램변경 요청에 대한 처리 사항을 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovProgramChangeRequstProcess"
     * @exception Exception
     */
    @IncludedInfo(name="프로그램변경요청처리",order = 1113 ,gid = 60)
    @RequestMapping(value="/sym/prm/EgovProgramChangeRequstProcessListSelect.do")
    public String selectProgrmChangeRequstProcessList(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
         // 0. Spring Security 사용자권한 처리
    	 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	 if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	 }
     	 // 내역 조회
     	 /** EgovPropertyService.sample */
     	 searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
     	 searchVO.setPageSize(propertiesService.getInt("pageSize"));

     	 /** pageing */
     	 PaginationInfo paginationInfo = new PaginationInfo();
 		 paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
 		 paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
 		 paginationInfo.setPageSize(searchVO.getPageSize());

 		 searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
 		 searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
 		 searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

         List<?> list_changerequst = progrmManageService.selectChangeRequstProcessList(searchVO);
         model.addAttribute("list_changerequst", list_changerequst);

         int totCnt = progrmManageService.selectChangeRequstProcessListTotCnt(searchVO);
  		 paginationInfo.setTotalRecordCount(totCnt);
         model.addAttribute("paginationInfo", paginationInfo);

         return "egovframework/com/sym/prm/EgovProgramChangeRequstProcess";
    }

    /**
     * 프로그램변경 요청에 대한 처리 사항을 상세조회한다.
     * @param progrmManageDtlVO ProgrmManageDtlVO
     * @return 출력페이지정보 "sym/prm/EgovProgramChangRequstProcessDetailSelectUpdt"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramChangRequstProcessDetailSelect.do")
    public String selectProgrmChangRequstProcess(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		ModelMap model)
            throws Exception {
         // 0. Spring Security 사용자권한 처리
    	 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	 if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	 }
         if(progrmManageDtlVO.getProgrmFileNm()==null){
	    	 String _FileNm = progrmManageDtlVO.getTmpProgrmNm();
	         progrmManageDtlVO.setProgrmFileNm(_FileNm);
	         int _Tmp_no = progrmManageDtlVO.getTmpRqesterNo();
	         progrmManageDtlVO.setRqesterNo(_Tmp_no);
         }
         ProgrmManageDtlVO resultVO = progrmManageService.selectProgrmChangeRequst(progrmManageDtlVO);
         if(resultVO.getProcessDe() != null){
        	 resultVO.setProcessDe(resultVO.getProcessDe().trim());//2011.08.22
         }

         if(resultVO.getOpetrId()== null){
	     	 LoginVO user =
				(LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	         resultVO.setOpetrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
         }
         model.addAttribute("progrmManageDtlVO", resultVO);
         return "egovframework/com/sym/prm/EgovProgramChangRequstProcessDetailSelectUpdt";
    }

    /**
     * 프로그램변경요청처리 내용을 수정 한다.
     * @param progrmManageDtlVO ProgrmManageDtlVO
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramChangeRequstProcessListSelect.do"
     * @exception Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value="/sym/prm/EgovProgramChangRequstProcessDetailSelectUpdt.do")
    public String updateProgrmChangRequstProcess(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
        String sLocationUrl = null;
    	boolean result = true;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

        beanValidator.validate(progrmManageDtlVO, bindingResult);
		if (bindingResult.hasErrors()){
			sLocationUrl = "forward:/sym/prm/EgovProgramChangRequstProcessDetailSelect.do";
			return sLocationUrl;
		}

    	LoginVO user =
			(LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	if (progrmManageDtlVO.getOpetrId() != null) {
	    	if(progrmManageDtlVO.getOpetrId().equals(user == null ? "" : EgovStringUtil.isNullToString(user.getId()))){
				if(progrmManageDtlVO.getChangerqesterCn()==null || progrmManageDtlVO.getChangerqesterCn().equals("")){progrmManageDtlVO.setChangerqesterCn(" ");}
				if(progrmManageDtlVO.getRqesterProcessCn()==null || progrmManageDtlVO.getRqesterProcessCn().equals("")){progrmManageDtlVO.setRqesterProcessCn(" ");}
		        progrmManageService.updateProgrmChangeRequstProcess(progrmManageDtlVO);
		        model.addAttribute("resultMsg", egovMessageSource.getMessage("success.common.update"));
	
		        ProgrmManageDtlVO vo = new ProgrmManageDtlVO();
		        vo = progrmManageService.selectRqesterEmail(progrmManageDtlVO);
		        String sTemp = null;
		        //KISA 보안약점 조치 (2018-10-29, 윤창원)
		        if("A".equals(progrmManageDtlVO.getProcessSttus())){
		        	sTemp = egovMessageSource.getMessage("comSymPrm.progrmManageController.processSttusA"); //신청중
		        }else if("P".equals(progrmManageDtlVO.getProcessSttus())){
		        	sTemp = egovMessageSource.getMessage("comSymPrm.progrmManageController.processSttusP"); //진행중
		        }else if("R".equals(progrmManageDtlVO.getProcessSttus())){
		        	sTemp = egovMessageSource.getMessage("comSymPrm.progrmManageController.processSttusR"); //반려
		        }else if("C".equals(progrmManageDtlVO.getProcessSttus())){
		        	sTemp = egovMessageSource.getMessage("comSymPrm.progrmManageController.processSttusC"); //처리완료
		        }
		    	// 프로그램 변경요청 사항을 이메일로  발송한다.(메일연동솔루션 활용)
		    	SndngMailVO sndngMailVO = new SndngMailVO();
		    	sndngMailVO.setDsptchPerson(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
		    	sndngMailVO.setRecptnPerson(vo.getTmpEmail());
		    	sndngMailVO.setSj(egovMessageSource.getMessage("comSymPrm.progrmManageController.email.Sj")); //프로그램변경요청  처리.
		    	sndngMailVO.setEmailCn(egovMessageSource.getMessage("comSymPrm.progrmManageController.email.emailCn")+" : "+sTemp); //프로그램 변경요청 사항이 처리 되었습니다
		    	sndngMailVO.setAtchFileId(null);
		    	result = sndngMailRegistService.insertSndngMail(sndngMailVO);
		        sLocationUrl = "forward:/sym/prm/EgovProgramChangeRequstProcessListSelect.do";
	    	}else{
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("comSymPrm.progrmManageController.updateProgrmChangRequstProcess.fail")); //수정이 실패하였습니다. 변경요청처리 수정은 변경처리해당 담당자만 처리가능합니다.
	            progrmManageDtlVO.setTmpProgrmNm(progrmManageDtlVO.getProgrmFileNm());
	            progrmManageDtlVO.setTmpRqesterNo(progrmManageDtlVO.getRqesterNo());
				sLocationUrl = "forward:/sym/prm/EgovProgramChangRequstProcessDetailSelect.do";
	    	}
    	}
		return sLocationUrl;
    }

    /**
     * 프로그램변경요청처리를 삭제 한다.
     * @param progrmManageDtlVO  ProgrmManageDtlVO
     * @return 출력페이지정보 "forward:/sym/prm/EgovProgramChangeRequstProcessListSelect.do"
     * @exception Exception
     */
    /*프로그램변경요청처리 삭제*/
    @RequestMapping(value="/sym/prm/EgovProgramChangRequstProcessDelete.do")
    public String deleteProgrmChangRequstProcess(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		ModelMap model)
            throws Exception {
         // 0. Spring Security 사용자권한 처리
    	 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	 if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	 }
         progrmManageService.deleteProgrmChangeRequst(progrmManageDtlVO);

         return "forward:/sym/prm/EgovProgramChangeRequstProcessListSelect.do";
    }

    /**
     * 프로그램변경이력리스트를 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovProgramChgHst"
     * @exception Exception
     */
    @IncludedInfo(name="프로그램변경이력",order = 1114 ,gid = 60)
    @RequestMapping(value="/sym/prm/EgovProgramChgHstListSelect.do")
    public String selectProgrmChgHstList(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
         // 0. Spring Security 사용자권한 처리
    	 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	 if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	 }
     	 // 내역 조회
     	 /** EgovPropertyService.sample */
     	 searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
     	 searchVO.setPageSize(propertiesService.getInt("pageSize"));

     	 /** pageing */
     	 PaginationInfo paginationInfo = new PaginationInfo();
 		 paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
 		 paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
 		 paginationInfo.setPageSize(searchVO.getPageSize());

 		 searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
 		 searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
 		 searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

         List<?> list_changerequst = progrmManageService.selectProgrmChangeRequstList(searchVO);
         model.addAttribute("list_changerequst", list_changerequst);

         int totCnt = progrmManageService.selectProgrmChangeRequstListTotCnt(searchVO);
  		 paginationInfo.setTotalRecordCount(totCnt);
         model.addAttribute("paginationInfo", paginationInfo);

         return "egovframework/com/sym/prm/EgovProgramChgHst";
    }

    /*프로그램변경이력상세조회*/
    /**
     * 프로그램변경이력을 상세조회한다.
     * @param progrmManageDtlVO ProgrmManageDtlVO
     * @return 출력페이지정보 "sym/prm/EgovProgramChgHstDetail"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramChgHstListDetailSelect.do")
    public String selectProgramChgHstListDetail(
    		@ModelAttribute("progrmManageDtlVO") ProgrmManageDtlVO progrmManageDtlVO,
    		ModelMap model)
            throws Exception {
         // 0. Spring Security 사용자권한 처리
    	 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	 if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	 }
         String _FileNm = progrmManageDtlVO.getTmpProgrmNm();
         progrmManageDtlVO.setProgrmFileNm(_FileNm);
         int _tmp_no = progrmManageDtlVO.getTmpRqesterNo();
         progrmManageDtlVO.setRqesterNo(_tmp_no);

         ProgrmManageDtlVO resultVO = progrmManageService.selectProgrmChangeRequst(progrmManageDtlVO);
         model.addAttribute("resultVO", resultVO);
         return "egovframework/com/sym/prm/EgovProgramChgHstDetail";
    }

    /**
     * 프로그램파일명을 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovFileNmSearch"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramListSearch.do")
    public String selectProgrmListSearch(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	// 내역 조회
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_progrmmanage = progrmManageService.selectProgrmList(searchVO);
        model.addAttribute("list_progrmmanage", list_progrmmanage);

        int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

      	return "egovframework/com/sym/prm/EgovFileNmSearch";

    }

    /**
     * 프로그램파일명을 조회한다. (New)
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovFileNmSearch"
     * @exception Exception
     */
    @RequestMapping(value="/sym/prm/EgovProgramListSearchNew.do")
    public String selectProgrmListSearchNew(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	// 내역 조회
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_progrmmanage = progrmManageService.selectProgrmList(searchVO);
        model.addAttribute("list_progrmmanage", list_progrmmanage);

        int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

      	return "egovframework/com/sym/prm/EgovFileNmSearchNew";

    }
}