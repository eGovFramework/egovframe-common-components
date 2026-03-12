package egovframework.com.uss.ion.ulm.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ulm.service.EgovUnityLinkService;
import egovframework.com.uss.ion.ulm.service.UnityLink;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 통합링크관리를 처리하는 Controller Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2024.10.29  권태성          등록 화면과 데이터를 처리하는 method 분리, validation 적용
 *   2025.08.18  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UselessParentheses(불필요한 괄호사용)
 *
 *      </pre>
 */
@Controller
public class EgovUnityLinkController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovUnityLinkController.class);

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
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 공통코드를 조회한다.
	 * @return List<?>
	 */
	private List<?> unityLinkSeCode() {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM039");
		return cmmUseService.selectCmmCodeDetail(vo);
	}

	/**
	 * 통합링크관리 메인 셈플 목록을 조회한다.
	 * 
	 * @param unityLink
	 * @param model
	 * @return "egovframework/com/uss/ion/ulm/UnityLinkSample"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/listUnityLinkSample.do")
	public String egovUnityLinkSample1List(UnityLink unityLink, ModelMap model) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("unityLink={}", unityLink);
		}

		List<?> reusltList = egovUnityLinkService.selectUnityLinkSample(unityLink);
		model.addAttribute("resultList", reusltList);

		return "egovframework/com/uss/ion/ulm/UnityLinkSample";
	}

	/**
	 * 통합링크관리 목록을 조회한다.
	 * 
	 * @param unityLink
	 * @param model
	 * @return "egovframework/com/uss/ion/ulm/EgovOnlinePollList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "통합링크관리", order = 780, gid = 50)
	@RequestMapping(value = "/uss/ion/ulm/listUnityLink.do")
	public String egovUnityLinkList(@ModelAttribute("unityLink") UnityLink unityLink, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		unityLink.setPageUnit(propertiesService.getInt("pageUnit"));
		unityLink.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(unityLink.getPageIndex());
		paginationInfo.setRecordCountPerPage(unityLink.getPageUnit());
		paginationInfo.setPageSize(unityLink.getPageSize());

		unityLink.setFirstIndex(paginationInfo.getFirstRecordIndex());
		unityLink.setLastIndex(paginationInfo.getLastRecordIndex());
		unityLink.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> reusltList = egovUnityLinkService.selectUnityLinkList(unityLink);
		model.addAttribute("resultList", reusltList);
		model.addAttribute("unityLink", unityLink);

		int totCnt = egovUnityLinkService.selectUnityLinkListCnt(unityLink);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 통합링크구분설정
		model.addAttribute("unityLinkSeCodeList", unityLinkSeCode());

		return "egovframework/com/uss/ion/ulm/EgovUnityLinkList";
	}

	/**
	 * 통합링크관리 목록을 상세조회 조회한다.
	 * 
	 * @param unityLink
	 * @param commandMap
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/detailUnityLink.do")
	public String egovUnityLinkDetail(@ModelAttribute("unityLink") UnityLink unityLink,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		String sLocationUrl = "egovframework/com/uss/ion/ulm/EgovUnityLinkDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		if (sCmd.equals("del")) {
			egovUnityLinkService.deleteUnityLink(unityLink);
			sLocationUrl = "forward:/uss/ion/ulm/listUnityLink.do";
		} else {
			// 통합링크구분설정
			model.addAttribute("unityLinkSeCodeList", unityLinkSeCode());
			// 상세정보 불러오기
			UnityLink resultUnityLink = egovUnityLinkService.selectUnityLinkDetail(unityLink);
			model.addAttribute("unityLink", resultUnityLink);
		}

		return sLocationUrl;
	}

	/**
	 * 통합링크관리 수정화면
	 * 
	 * @param unityLink
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/updtUnityLinkView.do")
	public String egovUnityLinkModify(@ModelAttribute("unityLink") UnityLink unityLink, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 통합링크구분설정
		model.addAttribute("unityLinkSeCodeList", unityLinkSeCode());
		// 수정정보 불러오기
		UnityLink resultUnityLink = egovUnityLinkService.selectUnityLinkDetail(unityLink);
		model.addAttribute("unityLink", resultUnityLink);

		return "egovframework/com/uss/ion/ulm/EgovUnityLinkUpdt";
	}

	/**
	 * 통합링크관리를 수정한다.
	 * 
	 * @param unityLink
	 * @param bindingResult
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/updtUnityLink.do")
	public String egovUnityLinkModify(
			@Valid @ModelAttribute("unityLink") UnityLink unityLink, BindingResult bindingResult, ModelMap model)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("unityLinkSeCodeList", unityLinkSeCode());
			model.addAttribute("unityLink", unityLink);
			return "egovframework/com/uss/ion/ulm/EgovUnityLinkUpdt";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		// 아이디 설정
		unityLink.setFrstRegisterId(uniqId);
		unityLink.setLastUpdusrId(uniqId);

		// 저장
		egovUnityLinkService.updateUnityLink(unityLink);

		return "redirect:/uss/ion/ulm/listUnityLink.do";
	}

	/**
	 * 통합링크관리 등록 화면
	 * 
	 * @param unityLink
	 * @param model
	 * @return "/uss/ion/ulm/EgovOnlinePollRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/registUnityLinkView.do")
	public String egovUnityLinkRegist(@ModelAttribute("unityLink") UnityLink unityLink, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 통합링크구분설정
		model.addAttribute("unityLinkSeCodeList", unityLinkSeCode());

		return "egovframework/com/uss/ion/ulm/EgovUnityLinkRegist";
	}

	/**
	 * 통합링크관리를 등록한다.
	 * 
	 * @param unityLink
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ulm/registUnityLink.do")
	public String egovUnityLinkRegist(
			@Valid @ModelAttribute("unityLink") UnityLink unityLink, BindingResult bindingResult, ModelMap model)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("unityLinkSeCodeList", unityLinkSeCode());
			model.addAttribute("unityLink", unityLink);
			return "egovframework/com/uss/ion/ulm/EgovUnityLinkRegist";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		// 아이디 설정
		unityLink.setFrstRegisterId(uniqId);
		unityLink.setLastUpdusrId(uniqId);

		// 저장
		egovUnityLinkService.insertUnityLink(unityLink);

		return "redirect:/uss/ion/ulm/listUnityLink.do";
	}

}