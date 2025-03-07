package egovframework.com.uss.ion.ulm.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ulm.service.EgovUnityLinkService;
import egovframework.com.uss.ion.ulm.service.UnityLink;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 통합링크관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한			최초 생성
 *   2011.08.26  정진오			IncludedInfo annotation 추가
 *   2024.10.29  권태성			등록 화면과 데이터를 처리하는 method 분리, validation 적용
 *
 * </pre>
 */

@Controller
public class EgovUnityLinkController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovUnityLinkController.class);

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovUnityLinkService")
    private EgovUnityLinkService egovUnityLinkService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Egov Common Code Service */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    /**
     * 통합링크관리 메인 셈플 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param unityLinkVO
     * @param model
     * @return "egovframework/com/uss/ion/ulm/UnityLinkSample"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/ion/ulm/listUnityLinkSample.do")
    public String egovUnityLinkSample1List(
            UnityLink unityLinkVO,
            ModelMap model)
            throws Exception {

        List<?> reusltList = egovUnityLinkService.selectUnityLinkSample(unityLinkVO);
        model.addAttribute("resultList", reusltList);

        return "egovframework/com/uss/ion/ulm/UnityLinkSample";
    }

    /**
     * 통합링크관리 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param unityLinkVO
     * @param model
     * @return "egovframework/com/uss/ion/ulm/EgovOnlinePollList"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@IncludedInfo(name="통합링크관리", order = 780 ,gid = 50)
    @RequestMapping(value = "/uss/ion/ulm/listUnityLink.do")
    public String egovUnityLinkList(
            @ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
            UnityLink unityLinkVO, ModelMap model)
            throws Exception {

        String sSearchMode = commandMap.get("searchMode") == null ? "" : (String) commandMap.get("searchMode");

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

        List<?> reusltList = egovUnityLinkService.selectUnityLinkList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovUnityLinkService.selectUnityLinkListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        //통합링크구분설정
        ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
        voComCode = new ComDefaultCodeVO();
        voComCode.setCodeId("COM039");
        List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("unityLinkSeCodeList", listComCode );

        return "egovframework/com/uss/ion/ulm/EgovUnityLinkList";
    }

    /**
     * 통합링크관리 목록을 상세조회 조회한다.
     * @param searchVO
     * @param unityLinkVO
     * @param commandMap
     * @param model
     * @return
     *         "/uss/ion/ulm/EgovOnlinePollDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/ion/ulm/detailUnityLink.do")
    public String egovUnityLinkDetail(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            UnityLink unityLink, @RequestParam Map<?, ?> commandMap,
            ModelMap model) throws Exception {

        String sLocationUrl = "egovframework/com/uss/ion/ulm/EgovUnityLinkDetail";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

        if (sCmd.equals("del")) {
            egovUnityLinkService.deleteUnityLink(unityLink);
            sLocationUrl = "forward:/uss/ion/ulm/listUnityLink.do";
        } else {
            //통합링크구분설정
            ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
            voComCode = new ComDefaultCodeVO();
            voComCode.setCodeId("COM039");
            List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
            model.addAttribute("unityLinkSeCodeList", listComCode );
            //상세정보 불러오기
            UnityLink unityLinkVO = egovUnityLinkService.selectUnityLinkDetail(unityLink);
            model.addAttribute("unityLink", unityLinkVO);
        }

        return sLocationUrl;
    }

	/**
	 * 통합링크관리 수정화면
	 * 
	 * @param searchVO
	 * @param unityLinkVO
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/updtUnityLinkView.do")
	public String egovUnityLinkModify(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("unityLink") UnityLink unityLink, ModelMap model)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 통합링크구분설정
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM039");
		List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("unityLinkSeCodeList", listComCode);
		// 수정정보 불러오기
		UnityLink unityLinkVO = egovUnityLinkService.selectUnityLinkDetail(unityLink);
		model.addAttribute("unityLink", unityLinkVO);

		return "egovframework/com/uss/ion/ulm/EgovUnityLinkUpdt";
	}
	
	/**
	 * 통합링크관리를 수정한다.
	 * 
	 * @param searchVO
	 * @param unityLinkVO
	 * @param bindingResult
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/updtUnityLink.do")
	public String egovUnityLinkModify(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("unityLink") UnityLink unityLink, BindingResult bindingResult, ModelMap model)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 서버 validate 체크
		beanValidator.validate(unityLink, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/ion/ulm/EgovUnityLinkUpdt";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		String uniqId = (loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		unityLink.setFrstRegisterId(uniqId);
		unityLink.setLastUpdusrId(uniqId);
		// 저장
		egovUnityLinkService.updateUnityLink(unityLink);

		return "redirect:/uss/ion/ulm/listUnityLink.do";
	}

	/**
	 * 통합링크관리 등록 화면
	 * 
	 * @param searchVO
	 * @param unityLinkVO
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/registUnityLinkView.do")
	public String egovUnityLinkRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("unityLink") UnityLink unityLink, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 통합링크구분설정
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM039");
		List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("unityLinkSeCodeList", listComCode);

		return "egovframework/com/uss/ion/ulm/EgovUnityLinkRegist";
	}

	/**
	 * 통합링크관리를 등록한다.
	 * 
	 * @param searchVO
	 * @param unityLink
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/registUnityLink.do")
	public String egovUnityLinkRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("unityLink") UnityLink unityLink,
			BindingResult bindingResult, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 서버 validate 체크
		beanValidator.validate(unityLink, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/ion/ulm/EgovUnityLinkRegist";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		String uniqId = (loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		unityLink.setFrstRegisterId(uniqId);
		unityLink.setLastUpdusrId(uniqId);
		// 저장
		egovUnityLinkService.insertUnityLink(unityLink);

		return "redirect:/uss/ion/ulm/listUnityLink.do";
	}

}