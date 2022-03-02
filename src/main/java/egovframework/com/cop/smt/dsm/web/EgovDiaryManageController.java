package egovframework.com.cop.smt.dsm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;
import egovframework.com.cop.smt.dsm.service.EgovDiaryManageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 일지관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.04.10   장동한            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.12.09   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *  2020.10.28   신용호            파일 업로드 수정 (multiRequest.getFiles)
 *
 * </pre>
 */

@Controller
public class EgovDiaryManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovDiaryManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovDiaryManageService")
	private EgovDiaryManageService egovDiaryManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/**
	 * 일지관리 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param diaryManageVO
	 * @param model
	 * @return "egovframework/com/cop/smt/dsm/EgovDiaryManageList"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@IncludedInfo(name="일지관리", order = 340 ,gid = 40)
	@RequestMapping(value="/cop/smt/dsm/EgovDiaryManageList.do")
	public String egovDiaryManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DiaryManageVO diaryManageVO,
    		ModelMap model)
    throws Exception {

		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

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

        if(commandMap.get("schdulId") != null){
        	searchVO.setSearchCondition("SCHDUL_ID");
        	searchVO.setSearchKeyword((String)commandMap.get("schdulId"));
        }

        List<?> resultList = egovDiaryManageService.selectDiaryManageList(searchVO);
        model.addAttribute("resultList", resultList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = (Integer)egovDiaryManageService.selectDiaryManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/dsm/EgovDiaryManageList";
	}

	/**
	 * 일지관리 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param diaryManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/cop/smt/dsm/EgovDiaryManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/dsm/EgovDiaryManageDetail.do")
	public String egovDiaryManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			DiaryManageVO diaryManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/cop/smt/dsm/EgovDiaryManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			egovDiaryManageService.deleteDiaryManage(diaryManageVO);
			sLocationUrl = "redirect:/cop/smt/dsm/EgovDiaryManageList.do";
		}else{
	        model.addAttribute("diaryManageVO",  (DiaryManageVO)egovDiaryManageService.selectDiaryManageDetail(diaryManageVO));
		}

		return sLocationUrl;
	}

	/**
	 * 일지관리를 수정한다. / 초기페이지
	 * @param searchVO
	 * @param commandMap
	 * @param diaryManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/dsm/EgovDiaryManageModify"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/cop/smt/dsm/EgovDiaryManageModify.do")
	public String diaryManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DiaryManageVO diaryManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/dsm/EgovDiaryManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		model.addAttribute("diaryManageVO",  (DiaryManageVO)egovDiaryManageService.selectDiaryManageDetail(diaryManageVO));

		// 파일업로드 제한
    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
		
		return sLocationUrl;
	}

	/**
	 * 일지관리를 수정한다. / 수정처리작업
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param diaryManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/dsm/EgovDiaryManageModifyActor"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/dsm/EgovDiaryManageModifyActor.do")
	public String diaryManageModifyActor(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("diaryManageVO") DiaryManageVO diaryManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 파일업로드 제한
    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
		
		String sLocationUrl = "egovframework/com/cop/smt/dsm/EgovDiaryManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(diaryManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    			return sLocationUrl;
    		}
    		/* *****************************************************************
        	// 아이디설정
			****************************************************************** */
    		diaryManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		diaryManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = diaryManageVO.getAtchFileId();


    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
    			if("N".equals(atchFileAt)){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DIARY_", 0, _atchFileId, "");
    				_atchFileId = fileMngService.insertFileInfs(_result);

    				// 첨부파일 ID 셋팅
    				diaryManageVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

    			}else{
    				FileVO fvo = new FileVO();
    				fvo.setAtchFileId(_atchFileId);
    				int _cnt = fileMngService.getMaxFileSN(fvo);
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DIARY_", _cnt, _atchFileId, "");
    				fileMngService.updateFileInfs(_result);
    			}
    		}

    		/* *****************************************************************
        	// 일지정보 업데이트
			****************************************************************** */
        	egovDiaryManageService.updateDiaryManage(diaryManageVO);
        	sLocationUrl = "redirect:/cop/smt/dsm/EgovDiaryManageList.do";
		}

		return sLocationUrl;
	}

	/**
	 * 일지관리를 등록한다. / 등록 초기페이지
	 * @param searchVO
	 * @param commandMap
	 * @param diaryManageVO
	 * @param bindingResult
	 * @param model
	 * @return  "/cop/smt/dsm/EgovDiaryManageRegist"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/cop/smt/dsm/EgovDiaryManageRegist.do")
	public String diaryManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("diaryManageVO") DiaryManageVO diaryManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 파일업로드 제한
    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
		
		String sLocationUrl = "egovframework/com/cop/smt/dsm/EgovDiaryManageRegist";

		return sLocationUrl;
	}

	/**
	 * 일지관리를 등록한다. / 등록처리작업
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param diaryManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/cop/smt/dsm/DiaryManageRegistActor"
	 * @throws Exception
	 */
	@RequestMapping(value="/cop/smt/dsm/EgovDiaryManageRegistActor.do")
	public String diaryManageRegistActor(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("diaryManageVO") DiaryManageVO diaryManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 파일업로드 제한
    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
		
		String sLocationUrl = "egovframework/com/cop/smt/dsm/EgovDiaryManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(diaryManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    			return sLocationUrl;
    		}

        	// 첨부파일 관련 첨부파일ID 생성
    		List<FileVO> _result = null;
    		String _atchFileId = "";

    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			_result = fileUtil.parseFileInf(files, "DIARY_", 0, "", "");
    			_atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
    		}

        	// 리턴받은 첨부파일ID를 셋팅한다..
    		diaryManageVO.setAtchFileId(_atchFileId);

    		//아이디 설정
    		diaryManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		diaryManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovDiaryManageService.insertDiaryManage(diaryManageVO);
        	sLocationUrl = "redirect:/cop/smt/dsm/EgovDiaryManageList.do";
        }

		return sLocationUrl;
	}

}


