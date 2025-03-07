package egovframework.com.dam.per.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.com.dam.per.service.EgovKnoPersonalService;
import egovframework.com.dam.per.service.KnoPersonal;
import egovframework.com.dam.per.service.KnoPersonalVO;

/**
 * 개요
 * - 개인지식정보에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 개인지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 개인지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:40
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------        --------    ---------------------------
 *   2010.08.12  박종선			최초 생성
 *   2011.08.26	 정진오			IncludedInfo annotation 추가
 *   2024.10.29	 권태성			목록으로 돌아올 때 검색 조건이 유지되도록 수정(#1)
 *
 * </pre>
 */

@Controller
public class EgovKnoPersonalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovKnoPersonalController.class);

	@Resource(name = "KnoPersonalService")
	public EgovKnoPersonalService knoPersonalService;

	@Resource(name = "MapTeamService")
    private EgovMapTeamService mapTeamService;

	@Resource(name = "MapMaterialService")
	public EgovMapMaterialService mapMaterialService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 등록된 개인지식 정보를 조회 한다.
	 * @param KnoPersonalVO - 개인지식 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoPersonalVO
	 */
    @IncludedInfo(name="개인지식관리", listUrl="/dam/per/EgovComDamPersonalList.do", order = 1250,gid = 80)
	@RequestMapping(value="/dam/per/EgovComDamPersonalList.do")
	public String selectKnoPersonalList(
			@ModelAttribute("searchVO") KnoPersonalVO searchVO
			, ModelMap model
			) throws Exception {
    	LOGGER.debug("searchVO={}", searchVO);
		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/** EgovPropertyService.mapMaterial */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		searchVO.setFrstRegisterId(loginVO.getUniqId());
		List<KnoPersonalVO> resultList = knoPersonalService.selectKnoPersonalList(searchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = knoPersonalService.selectKnoPersonalTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("searchVO", searchVO);

		return "egovframework/com/dam/per/EgovComDamPersonalList";
	}

	/**
	 * 개인지식정보 상세 정보를 조회 한다.
	 * @param KnoPersonalVO - 개인지식정보 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoPersonalVO
	 */
	@RequestMapping(value="/dam/per/EgovComDamPersonal.do")
	public String selectKnoPersonal(KnoPersonalVO knoPersonal
			, ModelMap model
			) throws Exception {
		KnoPersonal result = knoPersonalService.selectKnoPersonal(knoPersonal);
		model.addAttribute("result", result);
		model.addAttribute("searchVO", knoPersonal);
		return "egovframework/com/dam/per/EgovComDamPersonalDetail";
	}

	/**
	 * 개인지식 정보를 등록폼.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param KnoNm
	 */
	@RequestMapping(value="/dam/per/EgovComDamPersonalRegistView.do")
	public String insertKnoPersonalView(
			KnoPersonalVO knoPersonal
			, ModelMap model
			) throws Exception {
		setInsertKnoPersonalViewModel(knoPersonal, model);
		return "egovframework/com/dam/per/EgovComDamPersonalRegist";
	}
	
	/**
     * 개인지식 정보를 등록폼. 초기값
     * 
     * @param model
     * @throws Exception
     */
    private void setInsertKnoPersonalViewModel(KnoPersonalVO knoPersonal, ModelMap model) throws Exception {
    	model.addAttribute("knoPersonal", knoPersonal);
    	MapTeamVO mapTeamVO = new MapTeamVO();
        mapTeamVO.setRecordCountPerPage(Integer.MAX_VALUE);
        mapTeamVO.setFirstIndex(0);
        List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
        model.addAttribute("mapTeamList", mapTeamList);

        MapMaterialVO mapMaterialVO = new MapMaterialVO();
        mapMaterialVO.setRecordCountPerPage(Integer.MAX_VALUE);
        mapMaterialVO.setFirstIndex(0);

        List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(mapMaterialVO);
        model.addAttribute("mapMaterialList", mapMaterialList);
    }

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param KnoNm
	 */
	@PostMapping(value="/dam/per/EgovComDamPersonalRegist.do")
	public String insertKnoPersonal(
			final MultipartHttpServletRequest multiRequest
			, KnoPersonalVO knoPersonal
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {
    	// Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/dam/per/EgovComDamPersonalRegist";

		beanValidator.validate(knoPersonal, bindingResult);
		if (bindingResult.hasErrors()){
			setInsertKnoPersonalViewModel(knoPersonal, model);
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
		knoPersonal.setAtchFileId(_atchFileId);					// 첨부파일 ID

		//아이디 설정
		knoPersonal.setFrstRegisterId(loginVO.getUniqId());
		knoPersonal.setLastUpdusrId(loginVO.getUniqId());

		knoPersonalService.insertKnoPersonal(knoPersonal);
		return "forward:/dam/per/EgovComDamPersonalList.do";
	}

	/**
	 * 기 등록 된 개인지식 정보를 수정폼.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Ur
	 *
	 * @param KnoNm
	 */
	@PostMapping(value="/dam/per/EgovComDamPersonalModifyView.do")
	public String updateKnoPersonalView(KnoPersonalVO knoPersonal
			, ModelMap model
			) throws Exception {
		updateKnoPersonalViewInit(knoPersonal, model);
		model.addAttribute("searchVO", knoPersonal);
		return "egovframework/com/dam/per/EgovComDamPersonalModify";
	}
	
	/**
     * 기 등록 된 개인지식 정보를 수정폼. 초기값
     * 
     * @param knoPersonal
     * @param model
     * @throws Exception
     */
    private void updateKnoPersonalViewInit(KnoPersonal knoPersonal, ModelMap model) throws Exception {
        KnoPersonal result = knoPersonalService.selectKnoPersonal(knoPersonal);
        model.addAttribute("knoPersonal", result);
    }

	/**
	 * 기 등록 된 개인지식 정보를 수정 한다.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Ur
	 *
	 * @param KnoNm
	 */
	@PostMapping(value="/dam/per/EgovComDamPersonalModify.do")
	public String updateKnoPersonal(
			final MultipartHttpServletRequest multiRequest
			, @RequestParam Map<String, String> commandMap
			, @ModelAttribute("knoPersonal") KnoPersonal knoPersonal
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {

        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/dam/per/EgovComDamPersonalModify";

		beanValidator.validate(knoPersonal, bindingResult);
		if (bindingResult.hasErrors()){
            updateKnoPersonalViewInit(knoPersonal, model);
			return sLocationUrl;
		}

		/* *****************************************************************
    	// 아이디 설정
		****************************************************************** */
		if (loginVO != null) {
            knoPersonal.setFrstRegisterId(loginVO.getUniqId());
            knoPersonal.setLastUpdusrId(loginVO.getUniqId());
		}

		/* *****************************************************************
    	// 첨부파일 관련 ID 생성 start....
		****************************************************************** */
		String _atchFileId = knoPersonal.getAtchFileId();

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if(!files.isEmpty()){
			String atchFileAt = commandMap.get("atchFileAt");
			if("N".equals(atchFileAt)){
				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");
				_atchFileId = fileMngService.insertFileInfs(_result);

				// 첨부파일 ID 셋팅
				knoPersonal.setAtchFileId(_atchFileId);    	// 첨부파일 ID

			}else{
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(_atchFileId);
				int _cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", _cnt, _atchFileId, "");
				fileMngService.updateFileInfs(_result);
			}
		}
		
		//저장
		knoPersonalService.updateKnoPersonal(knoPersonal);
        sLocationUrl = "forward:/dam/per/EgovComDamPersonalList.do";
		return sLocationUrl;
	}

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param KnoNm
	 */
	@PostMapping(value="/dam/per/EgovComDamPersonalRemove.do")
	public String deleteKnoPersonal(KnoPersonal knoPersonal) throws Exception {
		knoPersonalService.deleteKnoPersonal(knoPersonal);
		return "forward:/dam/per/EgovComDamPersonalList.do";
	}

}