package egovframework.com.cop.smt.mrm.web;
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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.mrm.service.EgovMemoReprtService;
import egovframework.com.cop.smt.mrm.service.MemoReprt;
import egovframework.com.cop.smt.mrm.service.MemoReprtVO;
import egovframework.com.cop.smt.mrm.service.ReportrVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 메모보고에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 메모보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:14:53
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자             수정내용
 *  ----------   --------   ---------------------------
 *  2010.07.19   장철호            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.12.09   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *
 * </pre>
 */

@Controller
public class EgovMemoReprtController {

	@Resource(name="EgovMemoReprtService")
    protected EgovMemoReprtService memoReprtService;
	
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
	@RequestMapping("/cop/smt/mrm/selectReportrListPopup.do")
	public String selectReportrListPopup(@ModelAttribute("searchVO") ReportrVO reportrVO, ModelMap model) throws Exception{
		return "egovframework/com/cop/smt/mrm/EgovReportrListPopup";
	}
	
	/**
	 * 보고자 정보에 대한 목록을 조회한다.
	 * @param ReportrVO
	 * @return  String
	 * 
	 * @param reportrVO
	 */
	@RequestMapping("/cop/smt/mrm/selectReportrList.do")
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

		Map<String, Object> map = memoReprtService.selectReportrList(reportrVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/mrm/EgovReportrList";
	}
	
	/**
	 * 메모보고 정보에 대한 목록을 조회한다.
	 * @param MemoReprtVO - 메모보고 VO
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprtVO
	 * @param model
	 */
	@IncludedInfo(name="메모보고", order = 430 ,gid = 40)
    @RequestMapping("/cop/smt/mrm/selectMemoReprtList.do")
	public String selectMemoReprtList(@ModelAttribute("searchVO") MemoReprtVO memoReprtVO, ModelMap model) throws Exception{
    	//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
		
		memoReprtVO.setPageUnit(propertyService.getInt("pageUnit"));
		memoReprtVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(memoReprtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(memoReprtVO.getPageUnit());
		paginationInfo.setPageSize(memoReprtVO.getPageSize());

		memoReprtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		memoReprtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		memoReprtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		memoReprtVO.setSearchId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
	
		Map<String, Object> map = memoReprtService.selectMemoReprtList(memoReprtVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/mrm/EgovMemoReprtList";
	}

	/**
	 * 메모보고 정보를 조회한다.
	 * @param MemoReprtVO - 메모보고 VO
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprtVO
	 * @param model
	 */
    @RequestMapping("/cop/smt/mrm/selectMemoReprt.do")
	public String selectMemoReprt(@ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, ModelMap model) throws Exception{
    	MemoReprt memoReprt = memoReprtService.selectMemoReprt(memoReprtVO);
		model.addAttribute("memoReprt", memoReprt);
    	
    	// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
		
		model.addAttribute("uniqId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    	
		if((loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())).equals(memoReprt.getReportrId())){
			memoReprtService.readMemoReprt(memoReprt);
		}
		return "egovframework/com/cop/smt/mrm/EgovMemoReprtDetail";
	}

	/**
	 * 메모보고 정보의 등록페이지로 이동한다.
	 * @param MemoReprt - 메모보고 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprt
	 * @param model
	 */
    @RequestMapping("/cop/smt/mrm/addMemoReprt.do")
	public String addMemoReprt(@ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	String sLocationUrl = "egovframework/com/cop/smt/mrm/EgovMemoReprtRegist";
		
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
		memoReprtVO.setReprtDe(formatter.format(new java.util.Date()));
    	memoReprtVO.setWrterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    	memoReprtVO.setWrterNm(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
    	memoReprtVO.setWrterClsfNm(memoReprtService.selectWrterClsfNm(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())));
    	
    	model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
        
    	return sLocationUrl; 	
	}

	/**
	 * 메모보고 정보의 수정페이지로 이동한다.
	 * @param MemoReprt - 메모보고 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprt
	 * @param model
	 */
    @RequestMapping("/cop/smt/mrm/modifyMemoReprt.do")
	public String modifyMemoReprt(@ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		
    	MemoReprtVO resultVO = memoReprtService.selectMemoReprt(memoReprtVO);
		resultVO.setSearchCnd(memoReprtVO.getSearchCnd());
		resultVO.setSearchWrd(memoReprtVO.getSearchWrd());
		resultVO.setSearchBgnDe(memoReprtVO.getSearchBgnDe());
		resultVO.setSearchEndDe(memoReprtVO.getSearchEndDe());
		resultVO.setSearchSttus(memoReprtVO.getSearchSttus());
		resultVO.setSearchDrctMatter(memoReprtVO.getSearchDrctMatter());
		resultVO.setPageIndex(memoReprtVO.getPageIndex());
        model.addAttribute("memoReprtVO", resultVO);
        
		return "egovframework/com/cop/smt/mrm/EgovMemoReprtUpdt";
	}

	/**
	 * 메모보고 정보를 수정한다.
	 * @param MemoReprt - 메모보고 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprt
	 * @param model
	 */
    @RequestMapping("/cop/smt/mrm/updateMemoReprt.do")
	public String updateMemoReprt(final MultipartHttpServletRequest multiRequest, @RequestParam Map<?, ?> commandMap, @ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(memoReprtVO, bindingResult);
		if (bindingResult.hasErrors()) {
			MemoReprt memoReprt = memoReprtService.selectMemoReprt(memoReprtVO);
		    model.addAttribute("memoReprt", memoReprt);
		    return "egovframework/com/cop/smt/mrm/EgovMemoReprtUpdt";
		}

		if (isAuthenticated) {
			/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = memoReprtVO.getAtchFileId();	
    		
    		
    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");
    		
    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
    			if("N".equals(atchFileAt)){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");	
    				_atchFileId = fileMngService.insertFileInfs(_result);
    							
    				// 첨부파일 ID 셋팅
    				memoReprtVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID
    				
    			}else{				
    				FileVO fvo = new FileVO();
    				fvo.setAtchFileId(_atchFileId);
    				int _cnt = fileMngService.getMaxFileSN(fvo);
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", _cnt, _atchFileId, "");	
    				fileMngService.updateFileInfs(_result);
    			}
    		}	
    		
    		memoReprtVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));		    
    		memoReprtService.updateMemoReprt(memoReprtVO);
		}

		return "forward:/cop/smt/mrm/selectMemoReprtList.do";
	}

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * @param MemoReprt - 메모보고 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprt
	 * @param model
	 */
    @SuppressWarnings("unused")
	@RequestMapping("/cop/smt/mrm/updateMemoReprtDrctMatter.do")
	public String updateMemoReprtDrctMatter(@ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, ModelMap model) throws Exception{
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {	    
			memoReprtService.updateMemoReprtDrctMatter(memoReprtVO);
		}

		return "forward:/cop/smt/mrm/selectMemoReprtList.do";
	}

	/**
	 * 메모보고 정보를 등록한다.
	 * @param MemoReprt - 메모보고 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprt
	 * @param model
	 */
    @RequestMapping("/cop/smt/mrm/insertMemoReprt.do")
	public String insertMemoReprt(final MultipartHttpServletRequest multiRequest, @ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String sLocationUrl = "egovframework/com/cop/smt/mrm/EgovMemoReprtRegist"; 
		
		//서버  validate 체크
        beanValidator.validate(memoReprtVO, bindingResult);
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
		memoReprtVO.setAtchFileId(_atchFileId);			// 첨부파일 ID
		
		//아이디 설정
		memoReprtVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		memoReprtVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		memoReprtService.insertMemoReprt(memoReprtVO);
    	sLocationUrl = "forward:/cop/smt/mrm/selectMemoReprtList.do";
        
        return sLocationUrl;
	}

	/**
	 * 메모보고 정보를 삭제한다.
	 * @param MemoReprt - 메모보고 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoReprt
	 * @param model
	 */
    @RequestMapping("/cop/smt/mrm/deleteMemoReprt.do")
	public String deleteMemoReprt(@ModelAttribute("memoReprtVO") MemoReprtVO memoReprtVO, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
    	// 첨부파일 삭제를 위한 ID 생성 start....
		String _atchFileId = memoReprtVO.getAtchFileId();	
    	
		// 첨부파일을 삭제하기 위한  Vo
    	FileVO fvo = new FileVO();
    	fvo.setAtchFileId(_atchFileId);
    			
    	fileMngService.deleteAllFileInf(fvo);
    	// 첨부파일 삭제 End.............
    	
    	memoReprtService.deleteMemoReprt(memoReprtVO);
		return "forward:/cop/smt/mrm/selectMemoReprtList.do";
	}

}