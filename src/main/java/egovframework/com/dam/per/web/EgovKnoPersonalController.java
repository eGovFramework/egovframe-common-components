package egovframework.com.dam.per.web;

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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.com.dam.per.service.EgovKnoPersonalService;
import egovframework.com.dam.per.service.KnoPersonal;
import egovframework.com.dam.per.service.KnoPersonalVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
 *   2010.8.12  박종선          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
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

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

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

		searchVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		List<?> KnoPersonalList = knoPersonalService.selectKnoPersonalList(searchVO);
		model.addAttribute("resultList", KnoPersonalList);

		int totCnt = knoPersonalService.selectKnoPersonalTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

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
	public String selectKnoPersonal(@ModelAttribute("loginVO") LoginVO loginVO
			, KnoPersonal knoPersonal
			, ModelMap model
			, @RequestParam Map<?, ?> commandMap
			) throws Exception {
		KnoPersonal vo = knoPersonalService.selectKnoPersonal(knoPersonal);
		model.addAttribute("result", vo);
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
			@ModelAttribute("knoPersonal") KnoPersonal knoPersonal
			, @ModelAttribute("mapMaterial") MapMaterial mapMaterial
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if (knoPersonal.getKnoId() == null
			||knoPersonal.getKnoId().equals("")
			||sCmd.equals("")) {

			MapTeamVO searchVO;
			searchVO = new MapTeamVO();
			searchVO.setRecordCountPerPage(999999);
			searchVO.setFirstIndex(0);
			searchVO.setSearchCondition("MaterialList");
            List<?> MapTeamList = mapTeamService.selectMapTeamList(searchVO);
            model.addAttribute("mapTeamList", MapTeamList);

            MapMaterialVO searchMatVO;
            searchMatVO = new MapMaterialVO();
            searchMatVO.setRecordCountPerPage(999999);
            searchMatVO.setFirstIndex(0);
            searchMatVO.setSearchCondition("orgnztId");
            searchMatVO.setSearchKeyword(mapMaterial.getOrgnztId());

            //if (mapMaterial.getOrgnztId().equals("")) {
            //	EgovMap emp = (EgovMap)MapTeamList.get(0);
            //	mapMaterial.setOrgnztId(emp.get("orgnztId").toString());
            //}

            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
            model.addAttribute("mapMaterialList", MapMaterialList);

			return "egovframework/com/dam/per/EgovComDamPersonalRegist";

		} else if (sCmd.equals("Regist")) {

			beanValidator.validate(knoPersonal, bindingResult);
			if (bindingResult.hasErrors()){

				MapTeamVO searchVO;
				searchVO = new MapTeamVO();
				searchVO.setRecordCountPerPage(999999);
				searchVO.setFirstIndex(0);
	            List<?> MapTeamList = mapTeamService.selectMapTeamList(searchVO);
	            model.addAttribute("mapTeamList", MapTeamList);

	            MapMaterialVO searchMatVO;
	            searchMatVO = new MapMaterialVO();
	            searchMatVO.setRecordCountPerPage(999999);
	            searchMatVO.setFirstIndex(0);
	            searchMatVO.setSearchCondition("orgnztId");

	            if (mapMaterial.getOrgnztId().equals("")) {
	            	EgovMap emp = (EgovMap)MapTeamList.get(0);
	            	mapMaterial.setOrgnztId(emp.get("orgnztId").toString());
	            }
	            searchMatVO.setSearchKeyword(mapMaterial.getOrgnztId());

	            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
	            model.addAttribute("mapMaterialList", MapMaterialList);

				return "egovframework/com/dam/per/EgovComDamPersonalRegist";
			}

			knoPersonalService.insertKnoPersonal(knoPersonal);
			return "forward:/dam/per/EgovComDamPersonalList.do";
		} else {
			return "forward:/dam/per/EgovComDamPersonalList.do";
		}

	}

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param KnoNm
	 */
	@RequestMapping(value="/dam/per/EgovComDamPersonalRegist.do")
	public String insertKnoPersonal(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("knoPersonal") KnoPersonal knoPersonal
			, @RequestParam Map<?, ?> commandMap
			, KnoPersonal knoPersonal1
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {
    	// Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/dam/per/EgovComDamPersonalRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("Regist")){
    		//서버  validate 체크

		beanValidator.validate(knoPersonal, bindingResult);
		if (bindingResult.hasErrors()){
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
		knoPersonal.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		knoPersonal.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		knoPersonalService.insertKnoPersonal(knoPersonal);
		return "forward:/dam/per/EgovComDamPersonalList.do";
        }
        return sLocationUrl;
	}

	/**
	 * 기 등록 된 개인지식 정보를 수정폼.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Ur
	 *
	 * @param KnoNm
	 */
	@RequestMapping(value="/dam/per/EgovComDamPersonalModifyView.do")
	public String updateKnoPersonalView(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("knoPersonal") KnoPersonal knoPersonal
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "": (String)commandMap.get("cmd");
		if (sCmd.equals("")) {
			KnoPersonal vo = knoPersonalService.selectKnoPersonal(knoPersonal);
			model.addAttribute("knoPersonal", vo);
		}
		return "egovframework/com/dam/per/EgovComDamPersonalModify";
	}

	/**
	 * 기 등록 된 개인지식 정보를 수정 한다.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Ur
	 *
	 * @param KnoNm
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dam/per/EgovComDamPersonalModify.do")
	public String updateKnoPersonal(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("searchVO") KnoPersonal searchVO
			, @RequestParam Map<?, ?> commandMap
			, @ModelAttribute("knoPersonal") KnoPersonal knoPersonal
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {

        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/dam/per/EgovComDamPersonalModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

		if (sCmd.equals("Modify")) {
			beanValidator.validate(knoPersonal, bindingResult);
			if (bindingResult.hasErrors()){
				//KnoPersonal vo = knoPersonalService.selectKnoPersonal(knoPersonal);
				//model.addAttribute("knoPersonal2", vo);
				return sLocationUrl;
			}

    		/* *****************************************************************
        	// 아이디 설정
			****************************************************************** */
			knoPersonal.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			knoPersonal.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

    		/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = knoPersonal.getAtchFileId();


    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
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
		} else {
            //수정정보 불러오기
			knoPersonalService.selectKnoPersonal(knoPersonal);
            model.addAttribute("knoPersonal", knoPersonal);
		}
		return sLocationUrl;
	}

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * @param KnoNm - 개인지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param KnoNm
	 */
	@RequestMapping(value="/dam/per/EgovComDamPersonalRemove.do")
	public String deleteKnoPersonal(@ModelAttribute("loginVO") LoginVO loginVO
			, KnoPersonal knoPersonal
			, ModelMap model
			) throws Exception {
		knoPersonalService.deleteKnoPersonal(knoPersonal);
		return "forward:/dam/per/EgovComDamPersonalList.do";
	}

}