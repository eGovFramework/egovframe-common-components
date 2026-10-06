package egovframework.com.uss.olp.qim.web;

import egovframework.com.cmm.annotation.RequireAdmin;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qim.service.EgovQustnrItemManageService;
import egovframework.com.uss.olp.qim.service.QustnrItemManageVO;
import egovframework.com.uss.olp.qmc.service.EgovQustnrManageService;
import egovframework.com.uss.olp.qmc.service.QustnrManageVO;
import egovframework.com.uss.olp.qqm.service.EgovQustnrQestnManageService;
import egovframework.com.uss.olp.qqm.service.QustnrQestnManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 설문항목관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한         최초 생성
 *   2011.08.26  정진오         IncludedInfo annotation 추가
 *   2024.10.29  권태성         등록 & 수정의 화면과 데이터를 처리하는 method 분리, validation 적용
 * </pre>
 */
@Controller
public class EgovQustnrItemManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrItemManageController.class);

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrItemManageService")
	private EgovQustnrItemManageService egovQustnrItemManageService;

	@Resource(name = "egovQustnrManageService")
	private EgovQustnrManageService egovQustnrManageService;

	@Resource(name = "egovQustnrQestnManageService")
	private EgovQustnrQestnManageService egovQustnrQestnManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/**
	 * 설문항목 팝업 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qim/EgovQustnrItemManageListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/qim/EgovQustnrItemManageListPopup.do")
	@RequireAdmin
	public String egovQustnrItemManageListPopup(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrItemManageVO qustnrItemManageVO,
    		ModelMap model)
    throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if(sCmd.equals("del")){
			// 2026.07.13 KISA 보안취약점 조치 - 삭제는 POST만 허용
			jakarta.servlet.http.HttpServletRequest _req = ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes()).getRequest();
			if (!"POST".equalsIgnoreCase(_req.getMethod())) {
				throw new org.springframework.web.HttpRequestMethodNotSupportedException(_req.getMethod());
			}
			// 형제 경로 egovQustnrItemManageDetail의 cmd=del과 동일하게 설문 등록자만 삭제
			List<EgovMap> storedList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
			Object ownerId = qustnrOwnerId((storedList == null || storedList.isEmpty()) ? null : storedList.get(0).get("qestnrId"));
			EgovAuthorizationHelper.assertOwner(ownerId == null ? null : ownerId.toString());
			egovQustnrItemManageService.deleteQustnrItemManage(qustnrItemManageVO);
		}

		//팝업창 url 검색 강제주입
        String searchCondition = commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition");
        String searchKeyword = commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword");

        searchVO.setSearchCondition(searchCondition);
        searchVO.setSearchKeyword(searchKeyword);

        LOGGER.info("### popup searchCondition={}, searchKeyword={}",
                searchVO.getSearchCondition(),
                searchVO.getSearchKeyword());
	
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

        List<EgovMap> sampleList = egovQustnrItemManageService.selectQustnrItemManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrItemManageService.selectQustnrItemManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qim/EgovQustnrItemManageListPopup";
	}

	/**
	 * 설문항목 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qim/EgovQustnrItemManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name="항목관리", order = 640 ,gid = 50)
	@RequestMapping(value = "/uss/olp/qim/EgovQustnrItemManageList.do")
	public String egovQustnrItemManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrItemManageVO qustnrItemManageVO,
    		ModelMap model)
    throws Exception {

		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		//설문문항에 넘어온 건에 대해 조회
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QUSTNR_QESITM_ID");//qestnrQesitmId
			searchVO.setSearchKeyword(qustnrItemManageVO.getQestnrQesitmId());
		}

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

        List<EgovMap> sampleList = egovQustnrItemManageService.selectQustnrItemManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrItemManageService.selectQustnrItemManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qim/EgovQustnrItemManageList";
	}

	/**
	 * 설문항목 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrItemManageVO
	 * @param commandMap
	 * @param model
	 * @return  "/uss/olp/qim/EgovQustnrItemManageDetail"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qim/EgovQustnrItemManageDetail.do")
	@RequireAdmin
	public String egovQustnrItemManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrItemManageVO qustnrItemManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qim/EgovQustnrItemManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			// 2026.07.13 KISA 보안취약점 조치 - 삭제는 POST만 허용
			jakarta.servlet.http.HttpServletRequest _req = ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes()).getRequest();
			if (!"POST".equalsIgnoreCase(_req.getMethod())) {
				throw new org.springframework.web.HttpRequestMethodNotSupportedException(_req.getMethod());
			}
			List<EgovMap> storedList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
			Object ownerId = qustnrOwnerId((storedList == null || storedList.isEmpty()) ? null : storedList.get(0).get("qestnrId"));
			EgovAuthorizationHelper.assertOwner(ownerId == null ? null : ownerId.toString());
			egovQustnrItemManageService.deleteQustnrItemManage(qustnrItemManageVO);
			sLocationUrl = "redirect:/uss/olp/qim/EgovQustnrItemManageList.do";
		}else{
	        List<EgovMap> sampleList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 설문항목 수정화면
	 * @param searchVO
	 * @param qustnrItemManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qim/EgovQustnrItemManageModify"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qim/EgovQustnrItemManageModifyView.do")
	@RequireAdmin
	public String qustnrItemManageModifyView(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrItemManageVO") QustnrItemManageVO qustnrItemManageVO, ModelMap model)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		List<EgovMap> sampleList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
		Object ownerId = qustnrOwnerId((sampleList == null || sampleList.isEmpty()) ? null : sampleList.get(0).get("qestnrId"));
		EgovAuthorizationHelper.assertOwner(ownerId == null ? null : ownerId.toString());
		model.addAttribute("resultList", sampleList);

		// 설문항목(을)를 정보 불러오기
		List<?> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
		model.addAttribute("listQustnrTmplat", listQustnrTmplat);

		return "egovframework/com/uss/olp/qim/EgovQustnrItemManageModify";
	}


	/**
	 * 설문항목을 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qim/EgovQustnrItemManageModify.do")
	@RequireAdmin
	public String qustnrItemManageModify(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@Valid @ModelAttribute("qustnrItemManageVO") QustnrItemManageVO qustnrItemManageVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 형제 경로 egovQustnrItemManageDetail의 cmd=del과 동일한 관리자 검증
		EgovAuthorizationHelper.assertAdmin();

		List<EgovMap> storedList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
		Object ownerId = qustnrOwnerId((storedList == null || storedList.isEmpty()) ? null : storedList.get(0).get("qestnrId"));
		EgovAuthorizationHelper.assertOwner(ownerId == null ? null : ownerId.toString());

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (bindingResult.hasErrors()) {
			// 설문항목(을)를 정보 불러오기
			List<EgovMap> listQustnrTmplat = egovQustnrItemManageService
					.selectQustnrTmplatManageList(qustnrItemManageVO);
			model.addAttribute("listQustnrTmplat", listQustnrTmplat);
			// 게시물 불러오기
			List<EgovMap> sampleList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
			model.addAttribute("resultList", sampleList);

			return "egovframework/com/uss/olp/qim/EgovQustnrItemManageModify";
		}

		// 아이디 설정
		qustnrItemManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		qustnrItemManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		egovQustnrItemManageService.updateQustnrItemManage(qustnrItemManageVO);

		return "redirect:/uss/olp/qim/EgovQustnrItemManageList.do";
	}

	/**
	 * 설문항목 등록 화면
	 * @param searchVO
	 * @param qustnrItemManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qim/EgovQustnrItemManageRegist"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qim/EgovQustnrItemManageRegistView.do")
	@RequireAdmin
	public String qustnrItemManageRegistView(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrItemManageVO") QustnrItemManageVO qustnrItemManageVO, ModelMap model,
			RedirectAttributes redirectAttributes)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 설문항목(을)를 정보 불러오기
		List<?> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
		model.addAttribute("listQustnrTmplat", listQustnrTmplat);

		return "egovframework/com/uss/olp/qim/EgovQustnrItemManageRegist";
	}

	/**
	 * 설문항목를 등록한다.
	 * @param searchVO
	 * @param qustnrItemManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qim/EgovQustnrItemManageRegist.do")
	@RequireAdmin
	public String qustnrItemManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@Valid @ModelAttribute("qustnrItemManageVO") QustnrItemManageVO qustnrItemManageVO,
			BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		LOGGER.info("####설문항목 등록 컨트롤러 진입");
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		// 형제 경로 egovQustnrItemManageDetail의 cmd=del과 동일한 관리자 검증
		EgovAuthorizationHelper.assertAdmin();
		// validate 체크
		if (bindingResult.hasErrors()) {
			LOGGER.error("####설문항목 등록 컨트롤러 유효성에러 - 에러수 : {}, 에러목록: {}",bindingResult.getErrorCount(),bindingResult.getAllErrors() );
			// 설문항목(을)를 정보 불러오기
			List<EgovMap> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
			model.addAttribute("listQustnrTmplat", listQustnrTmplat);
			return "egovframework/com/uss/olp/qim/EgovQustnrItemManageRegist";
		}
		// 항목은 질문이 속한 설문의 등록자만 붙인다. 설문·템플릿 ID 는 요청값이 아니라 저장된 질문에서 가져온다
		QustnrQestnManageVO qustnrQestnManageVO = new QustnrQestnManageVO();
		qustnrQestnManageVO.setQestnrQesitmId(qustnrItemManageVO.getQestnrQesitmId());
		List<EgovMap> qestnList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
		EgovMap qestn = (qestnList == null || qestnList.isEmpty()) ? null : qestnList.get(0);
		EgovAuthorizationHelper.assertOwner(qustnrOwnerId(qestn == null ? null : qestn.get("qestnrId")));
		qustnrItemManageVO.setQestnrId(String.valueOf(qestn.get("qestnrId")));
		qustnrItemManageVO.setQestnrTmplatId(String.valueOf(qestn.get("qestnrTmplatId")));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		LOGGER.info("####설문항목 등록 컨트롤러: {}", qustnrItemManageVO.toString());
		// 아이디 설정
		qustnrItemManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		qustnrItemManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		LOGGER.info("####설문항목 등록 컨트롤러 랜덤아이디 생성");
		
		egovQustnrItemManageService.insertQustnrItemManage(qustnrItemManageVO);
		LOGGER.info("####설문항목 등록 컨트롤러 리턴전");
		
		return "redirect:/uss/olp/qim/EgovQustnrItemManageList.do";
	}

	/**
	 * 질문·항목은 상위 설문을 등록한 사람이 관리한다. 설문 등록자를 돌려주고, 설문이 없으면 null 이다(소유권 검사에서 거부).
	 */
	private String qustnrOwnerId(Object qestnrId) throws Exception {
		if (qestnrId == null || qestnrId.toString().isEmpty()) {
			return null;
		}
		QustnrManageVO qustnrManageVO = new QustnrManageVO();
		qustnrManageVO.setQestnrId(qestnrId.toString());
		List<EgovMap> qustnrList = egovQustnrManageService.selectQustnrManageDetail(qustnrManageVO);
		Object ownerId = (qustnrList == null || qustnrList.isEmpty()) ? null : qustnrList.get(0).get("frstRegisterId");
		return ownerId == null ? null : ownerId.toString();
	}

}
