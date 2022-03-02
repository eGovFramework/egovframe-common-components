package egovframework.com.cop.smt.wmr.web;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.wmr.service.EgovWikMnthngReprtService;
import egovframework.com.cop.smt.wmr.service.ReportrVO;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprt;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprtVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 주간월간보고에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 주간월간보고에 대한 등록, 수정, 삭제, 조회, 승인기능을 제공한다.
 * - 주간월간보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:47
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2010.07.19   장철호            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.12.06   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *
 * </pre>
 */

@Controller
public class EgovWikMnthngReprtController {

	@Resource(name="EgovWikMnthngReprtService")
    protected EgovWikMnthngReprtService wikMnthngReprtService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	@Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    // 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    //Logger log = Logger.getLogger(this.getClass());

    /**
	 * 보고자 정보에 대한 팝업 목록을 조회한다.
	 * @param ReportrVO
	 * @return  String
	 *
	 * @param reportrVO
	 */
	@RequestMapping("/cop/smt/wmr/selectReportrListPopup.do")
	public String selectReportrListPopup(@ModelAttribute("searchVO") ReportrVO reportrVO, ModelMap model) throws Exception{
		return "egovframework/com/cop/smt/wmr/EgovReportrListPopup";
	}

	/**
	 * 보고자 정보에 대한 목록을 조회한다.
	 * @param ReportrVO
	 * @return  String
	 *
	 * @param reportrVO
	 */
	@RequestMapping("/cop/smt/wmr/selectReportrList.do")
	public String selectReportrList(@ModelAttribute("searchVO") ReportrVO reportrVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//reportrVO.setUniqId(user.getUniqId());

		reportrVO.setPageUnit(propertyService.getInt("pageUnit"));
		reportrVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reportrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(reportrVO.getPageUnit());
		paginationInfo.setPageSize(reportrVO.getPageSize());

		reportrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reportrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		reportrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = wikMnthngReprtService.selectReportrList(reportrVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/wmr/EgovReportrList";
	}

	/**
	 * 주간월간보고 정보에 대한 목록을 조회한다.
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprtVO
	 */
	@IncludedInfo(name="주간/월간보고관리", order = 410 ,gid = 40)
    @RequestMapping("/cop/smt/wmr/selectWikMnthngReprtList.do")
	public String selectWikMnthngReprtList(@ModelAttribute("searchVO") WikMnthngReprtVO wikMnthngReprtVO, ModelMap model) throws Exception{
    	//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		wikMnthngReprtVO.setPageUnit(propertyService.getInt("pageUnit"));
		wikMnthngReprtVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(wikMnthngReprtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(wikMnthngReprtVO.getPageUnit());
		paginationInfo.setPageSize(wikMnthngReprtVO.getPageSize());

		wikMnthngReprtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		wikMnthngReprtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		wikMnthngReprtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		wikMnthngReprtVO.setSearchId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));


		Map<String, Object> map = wikMnthngReprtService.selectWikMnthngReprtList(wikMnthngReprtVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/wmr/EgovWikMnthngReprtList";
	}

	/**
	 * 주간월간보고 정보의 등록페이지로 이동한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprt
	 */
    @RequestMapping("/cop/smt/wmr/addWikMnthngReprt.do")
	public String addWikMnthngReprt(@ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	String sLocationUrl = "egovframework/com/cop/smt/wmr/EgovWikMnthngReprtRegist";

    	// 파일업로드 제한
    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
		wikMnthngReprtVO.setReprtDe(formatter.format(new java.util.Date()));
    	wikMnthngReprtVO.setWrterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    	wikMnthngReprtVO.setWrterNm(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
    	wikMnthngReprtVO.setWrterClsfNm(wikMnthngReprtService.selectWrterClsfNm(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())));

        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
        
    	return sLocationUrl;
	}

	/**
	 * 주간월간보고 정보의 수정페이지로 이동한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprt
	 */
    @RequestMapping("/cop/smt/wmr/modifyWikMnthngReprt.do")
	public String modifyWikMnthngReprt(@ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");
    	
    	WikMnthngReprtVO resultVO = wikMnthngReprtService.selectWikMnthngReprt(wikMnthngReprtVO);
		resultVO.setSearchCnd(wikMnthngReprtVO.getSearchCnd());
		resultVO.setSearchWrd(wikMnthngReprtVO.getSearchWrd());
		resultVO.setSearchDe(wikMnthngReprtVO.getSearchDe());
		resultVO.setSearchBgnDe(wikMnthngReprtVO.getSearchBgnDe());
		resultVO.setSearchEndDe(wikMnthngReprtVO.getSearchEndDe());
		resultVO.setSearchSttus(wikMnthngReprtVO.getSearchSttus());
		resultVO.setPageIndex(wikMnthngReprtVO.getPageIndex());
        model.addAttribute("wikMnthngReprtVO", resultVO);
        
        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return "egovframework/com/cop/smt/wmr/EgovWikMnthngReprtUpdt";
	}


    /**
	 * 주간월간보고 정보를 조회한다.
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprtVO
	 */
    @RequestMapping("/cop/smt/wmr/selectWikMnthngReprt.do")
	public String selectWikMnthngReprt(@ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, ModelMap model) throws Exception{
    	WikMnthngReprt wikMnthngReprt = wikMnthngReprtService.selectWikMnthngReprt(wikMnthngReprtVO);
		model.addAttribute("wikMnthngReprt", wikMnthngReprt);

		/*
    	 * 공통코드
    	 * 우선순위 조회
    	 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM060");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("reprtSe", listComCode);

    	// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("uniqId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		return "egovframework/com/cop/smt/wmr/EgovWikMnthngReprtDetail";
	}

	/**
	 * 주간월간보고 정보를 수정한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprt
	 */
    @RequestMapping("/cop/smt/wmr/updateWikMnthngReprt.do")
	public String updateWikMnthngReprt(final MultipartHttpServletRequest multiRequest, @RequestParam Map<?, ?> commandMap, @ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(wikMnthngReprtVO, bindingResult);
		if (bindingResult.hasErrors()) {
			WikMnthngReprt wikMnthngReprt = wikMnthngReprtService.selectWikMnthngReprt(wikMnthngReprtVO);
		    model.addAttribute("wikMnthngReprt", wikMnthngReprt);

		    // 파일업로드 제한
	    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
	    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

	        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
	        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
		    
		    return "egovframework/com/cop/smt/wmr/EgovWikMnthngReprtUpdt";
		}

		if (isAuthenticated) {
			/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = wikMnthngReprtVO.getAtchFileId();


    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
    			if("N".equals(atchFileAt)){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");
    				_atchFileId = fileMngService.insertFileInfs(_result);

    				// 첨부파일 ID 셋팅
    				wikMnthngReprtVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

    			}else{
    				FileVO fvo = new FileVO();
    				fvo.setAtchFileId(_atchFileId);
    				int _cnt = fileMngService.getMaxFileSN(fvo);
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", _cnt, _atchFileId, "");
    				fileMngService.updateFileInfs(_result);
    			}
    		}

    		wikMnthngReprtVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    		wikMnthngReprtService.updateWikMnthngReprt(wikMnthngReprtVO);
		}

		return "forward:/cop/smt/wmr/selectWikMnthngReprtList.do";
	}

	/**
	 * 주간월간보고 정보를 등록한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprt
	 */
    @RequestMapping("/cop/smt/wmr/insertWikMnthngReprt.do")
	public String insertWikMnthngReprt(final MultipartHttpServletRequest multiRequest, @ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/wmr/EgovWikMnthngReprtRegist";

		//서버  validate 체크
        beanValidator.validate(wikMnthngReprtVO, bindingResult);
		if(bindingResult.hasErrors()){
			
			// 파일업로드 제한
	    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
	    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

	        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
	        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
			return sLocationUrl;
		}

		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> _result = null;
		String _atchFileId = "";

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if(!files.isEmpty()){
		 _result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
		 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}

    	// 리턴받은 첨부파일ID를 셋팅한다..
		wikMnthngReprtVO.setAtchFileId(_atchFileId);			// 첨부파일 ID

		//아이디 설정
		wikMnthngReprtVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		wikMnthngReprtVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		wikMnthngReprtService.insertWikMnthngReprt(wikMnthngReprtVO);
    	sLocationUrl = "forward:/cop/smt/wmr/selectWikMnthngReprtList.do";

        return sLocationUrl;
	}

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprt
	 */
    @RequestMapping("/cop/smt/wmr/deleteWikMnthngReprt.do")
	public String deleteWikMnthngReprt(@ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	// 첨부파일 삭제를 위한 ID 생성 start....
		String _atchFileId = wikMnthngReprtVO.getAtchFileId();

		// 첨부파일을 삭제하기 위한  Vo
    	FileVO fvo = new FileVO();
    	fvo.setAtchFileId(_atchFileId);

    	fileMngService.deleteAllFileInf(fvo);
    	// 첨부파일 삭제 End.............

    	wikMnthngReprtService.deleteWikMnthngReprt(wikMnthngReprtVO);
		return "forward:/cop/smt/wmr/selectWikMnthngReprtList.do";
	}

	/**
	 * 주간월간보고 정보를 승인한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * @return  String - 리턴 URL
	 *
	 * @param wikMnthngReprt
	 */
    @SuppressWarnings("unused")
	@RequestMapping("/cop/smt/wmr/confirmWikMnthngReprt.do")
	public String confirmWikMnthngReprt(@ModelAttribute("wikMnthngReprtVO") WikMnthngReprtVO wikMnthngReprtVO, ModelMap model) throws Exception{
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {
    		wikMnthngReprtService.confirmWikMnthngReprt(wikMnthngReprtVO);
		}

		return "forward:/cop/smt/wmr/selectWikMnthngReprtList.do";
	}

}