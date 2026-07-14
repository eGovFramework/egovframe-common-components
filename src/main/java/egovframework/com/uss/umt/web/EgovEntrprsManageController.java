package egovframework.com.uss.umt.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovUmtAuthorizationHelper;
import egovframework.com.cmm.web.EgovComUtlController;
import egovframework.com.uss.umt.service.EgovEntrprsManageService;
import egovframework.com.uss.umt.service.PasswordManageVO;
import egovframework.com.uss.umt.service.EntrprsManageVO;
import egovframework.com.uss.umt.service.EntrprsManageInsertVO;
import egovframework.com.uss.umt.service.EntrprsPasswordManageVO;
import egovframework.com.uss.umt.service.StplatVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * 기업회원관련 요청을 비지니스 클래스로 전달하고 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2014.12.08  이기하          암호화방식 변경(EgovFileScrty.encryptPassword)
 *   2015.06.16  조정국          수정시 유효성체크 후 에러발생 시 목록으로 이동하여 에러메시지 표시
 *   2015.06.19  조정국          미인증 사용자에 대한 보안처리 기준 수정 (!isAuthenticated)
 *   2017.07.21  장동한          로그인인증제한 작업
 *   2020.07.18  윤주호          암호 설정 규칙 강화 및 버그 수정
 *   2021.05.30  정진오          디지털원패스 정보 조회
 *   2022.07.13  김해준          디지털원패스 정보 조회 null 판별 수정
 *   2025.08.28  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnusedPrivateMethod(사용되지 않는 Private Method 선언을 탐지)
 *
 *      </pre>
 */
@Controller
public class EgovEntrprsManageController {

	/** entrprsManageService */
	@Resource(name = "entrprsManageService")
	private EgovEntrprsManageService entrprsManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 기업회원목록을 조회한다. (pageing)
	 *
	 * @param userSearchVO 검색조건정보
	 * @param model        화면모델
	 * @return uss/umt/EgovEntrprsManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "기업회원관리", order = 450, gid = 50)
	@RequestMapping(value = "/uss/umt/EgovEntrprsManage.do")
	public String selectEntrprsMberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		/** EgovPropertyService */
		userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EntrprsManageVO> resultList = entrprsManageService.selectEntrprsMberList(userSearchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = entrprsManageService.selectEntrprsMberListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 기업회원 상태코드를 코드정보로부터 조회
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setCodeId("COM013");
		List<CmmnDetailCode> entrprsMberSttusResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		model.addAttribute("entrprsMberSttus_result", entrprsMberSttusResult);

		return "egovframework/com/uss/umt/EgovEntrprsManage";
	}

	/**
	 * 기업회원 등록화면으로 이동한다.
	 *
	 * @param userSearchVO          검색조건정보
	 * @param entrprsManageInsertVO 기업회원 초기화정보
	 * @param model                 화면모델
	 * @return uss/umt/EgovEntrprsInsert
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsInsertView.do")
	@RequireAdmin
	public String insertEntrprsMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("entrprsManageVO") EntrprsManageInsertVO entrprsManageInsertVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		addEntrprsCodeListToModel(model);

		return "egovframework/com/uss/umt/EgovEntrprsInsert";
	}

	/**
	 * 기업회원등록처리후 목록화면으로 이동한다.
	 *
	 * @param entrprsManageInsertVO 신규기업회원정보 (비밀번호 검증 포함)
	 * @param bindingResult         입력값검증용 bindingResult
	 * @param model                 화면모델
	 * @return forward:/uss/umt/EgovEntrprsManage.do
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsInsert.do")
	@RequireAdmin
	public String insertEntrprsMber(
			@Valid @ModelAttribute("entrprsManageVO") EntrprsManageInsertVO entrprsManageInsertVO,
			BindingResult bindingResult,
			Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		if (bindingResult.hasErrors()) {
			addEntrprsCodeListToModel(model);
			return "egovframework/com/uss/umt/EgovEntrprsInsert";
		} else {
			if ("".equals(entrprsManageInsertVO.getGroupId())) {
				entrprsManageInsertVO.setGroupId(null);
			}
			entrprsManageService.insertEntrprsmber(entrprsManageInsertVO);
			model.addAttribute("resultMsg", "success.common.insert");
		}
		return "forward:/uss/umt/EgovEntrprsManage.do";
	}

	/**
	 * 기업회원정보 수정을 위해기업회원정보를 상세조회한다.
	 *
	 * @param entrprsmberId 상세조회 대상 기업회원아이디
	 * @param userSearchVO  조회조건정보
	 * @param model         화면모델
	 * @return uss/umt/EgovEntrprsSelectUpdt
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsSelectUpdtView.do")
	public String updateEntrprsMberView(@RequestParam("selectedId") String entrprsmberId,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, HttpServletRequest request, Model model)
			throws Exception {
		entrprsmberId = decryptSelectedId(entrprsmberId);
		if (entrprsmberId == null) {
			model.addAttribute("resultMsg", "fail.common.select");
			return "forward:/uss/umt/EgovEntrprsManage.do";
		}

		if (!EgovUmtAuthorizationHelper.canModifyUser(entrprsmberId)) {
			return "egovframework/com/cmm/error/accessDenied";
		}

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		addEntrprsCodeListToModel(model);

		EntrprsManageVO entrprsManageVO = entrprsManageService.selectEntrprsmber(entrprsmberId);
		model.addAttribute("entrprsManageVO", entrprsManageVO);
		model.addAttribute("userSearchVO", userSearchVO);

		// 2021.05.30, 정진오, 디지털원패스 정보 조회
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		String onepassUserId = loginVO.getUniqId();
		String onepassUserkey = loginVO.getOnepassUserkey();
		String onepassIntfToken = loginVO.getOnepassIntfToken();
		if (entrprsmberId.equals(onepassUserId)) {
			model.addAttribute("onepassUserkey", onepassUserkey);
			model.addAttribute("onepassIntfToken", onepassIntfToken);
		} else {
			model.addAttribute("onepassUserkey", "");
			model.addAttribute("onepassIntfToken", "");
		}

		return "egovframework/com/uss/umt/EgovEntrprsSelectUpdt";
	}

	/**
	 * 로그인인증제한 해제
	 *
	 * @param entrprsManageVO 기업회원정보
	 * @param model           화면모델
	 * @return uss/umt/EgovEntrprsSelectUpdtView.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovEntrprsLockIncorrect.do", method = RequestMethod.POST)
	@RequireAdmin
	public String updateLockIncorrect(EntrprsManageVO entrprsManageVO, Model model) throws Exception {
		// 2026.07.13 KISA 보안취약점 조치 - 계정 잠금해제는 관리자만
		egovAssertAdminOrOwner(null);


		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		entrprsManageService.updateLockIncorrect(entrprsManageVO);

		return "forward:/uss/umt/EgovEntrprsSelectUpdtView.do";
	}

	/**
	 * 기업회원정보 수정후 목록조회 화면으로 이동한다.
	 *
	 * @param entrprsManageVO 수정할 기업회원정보
	 * @param bindingResult   입력값 검증용 bindingResult
	 * @param model           화면모델
	 * @return forward:/uss/umt/EgovEntrprsManage.do
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsSelectUpdt.do")
	public String updateEntrprsMber(@Valid @ModelAttribute("entrprsManageVO") EntrprsManageVO entrprsManageVO,
			BindingResult bindingResult,
			Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		EntrprsManageVO currentEntrprs = entrprsManageService.selectEntrprsmber(entrprsManageVO.getUniqId());
		if (currentEntrprs == null) {
			model.addAttribute("resultMsg", "fail.common.select");
			return "forward:/uss/umt/EgovEntrprsManage.do";
		}

		if (!EgovUmtAuthorizationHelper.canModifyUser(currentEntrprs.getUniqId())) {
			return "egovframework/com/cmm/error/accessDenied";
		}

		if (bindingResult.hasErrors()) {
			String resultMsgCode = bindingResult.getAllErrors().get(0).getCode();
			model.addAttribute("resultMsg", resultMsgCode);
			return "forward:/uss/umt/EgovEntrprsManage.do";
		} else {
			if ("".equals(entrprsManageVO.getGroupId())) {
				entrprsManageVO.setGroupId(null);
			}
			entrprsManageService.updateEntrprsmber(entrprsManageVO);
			model.addAttribute("resultMsg", "success.common.update");
			return "forward:/uss/umt/EgovEntrprsManage.do";
		}
	}

	/**
	 * 기업회원정보삭제후 목록조회 화면으로 이동한다.
	 *
	 * @param checkedIdForDel 삭제대상아이디 정보
	 * @param userSearchVO    조회조건정보
	 * @param model           화면모델
	 * @return forward:/uss/umt/EgovEntrprsManage.do
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsDelete.do")
	@RequireAdmin
	public String deleteEntrprsMber(@RequestParam("checkedIdForDel") String checkedIdForDel,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, HttpServletRequest request, Model model)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		// 2021.05.30, 정진오, 디지털원패스 정보 조회
		// 2022.07.13, 김해준, null 판별 수정
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		String onepassUserkey = loginVO.getOnepassUserkey();
		String onepassIntfToken = loginVO.getOnepassIntfToken();
		if (StringUtils.isNotEmpty(onepassUserkey) || StringUtils.isNotEmpty(onepassIntfToken)) {
			model.addAttribute("resultMsg", "digital.onepass.delete.alert");
		} else {
			entrprsManageService.deleteEntrprsmber(checkedIdForDel);
			model.addAttribute("resultMsg", "success.common.delete");
		}

		return "forward:/uss/umt/EgovEntrprsManage.do";
	}

	// 탈퇴 처리 기능에 대한 예시
	@RequestMapping(value = "/uss/umt/EgovEntrprsWithdraw.do", method = RequestMethod.POST)
	public String withdrawEntrprsMber(Model model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			model.addAttribute("resultMsg", "fail.common.delete");
			return "redirect:/";
		}

		entrprsManageService.deleteEntrprsmber(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		model.addAttribute("resultMsg", "success.common.delete");

		return "redirect:/";
	}

	/**
	 * 기업회원가입신청 등록화면으로 이동한다.
	 *
	 * @param userSearchVO          검색조건정보
	 * @param entrprsManageInsertVO 기업회원초기화정보
	 * @param commandMap            파라메터전송 commandMap
	 * @param model                 화면모델
	 * @return uss/umt/EgovEntrprsSbscrb
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovEntrprsSbscrbView.do", method = RequestMethod.POST)
	public String sbscrbEntrprsMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("entrprsManageVO") EntrprsManageInsertVO entrprsManageInsertVO,
			@RequestParam Map<String, Object> commandMap, Model model) throws Exception {

		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();

		comDefaultCodeVO.setCodeId("COM022");
		List<CmmnDetailCode> passwordHintResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM014");
		List<CmmnDetailCode> sexdstnCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM026");
		List<CmmnDetailCode> entrprsSeCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM027");
		List<CmmnDetailCode> indutyCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);

		model.addAttribute("passwordHint_result", passwordHintResult);
		model.addAttribute("sexdstnCode_result", sexdstnCodeResult);
		model.addAttribute("entrprsSeCode_result", entrprsSeCodeResult);
		model.addAttribute("indutyCode_result", indutyCodeResult);

		if (!"".equals(commandMap.get("realname"))) {
			model.addAttribute("applcntNm", commandMap.get("realname"));
			model.addAttribute("applcntIhidnum", commandMap.get("ihidnum"));
		}
		if (!"".equals(commandMap.get("realName"))) {
			model.addAttribute("applcntNm", commandMap.get("realName"));
		}
		entrprsManageInsertVO.setEntrprsMberSttus("DEFAULT");

		return "egovframework/com/uss/umt/EgovEntrprsSbscrb";
	}

	/**
	 * 기업회원가입신청 등록처리후 로그인화면으로 이동한다.
	 * 
	 * @param entrprsManageInsertVO 기업회원가입신청정보 (비밀번호 검증 포함)
	 * @param bindingResult         입력값검증용 bindingResult
	 * @return forward:/uat/uia/egovLoginUsr.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovEntrprsSbscrb.do", method = RequestMethod.POST)
	public String sbscrbEntrprsMber(@Valid @ModelAttribute("entrprsManageVO") EntrprsManageInsertVO entrprsManageInsertVO,
			BindingResult bindingResult)
			throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovEntrprsSbscrb";
		}

		entrprsManageInsertVO.setEntrprsMberSttus("A");
		entrprsManageService.insertEntrprsmber(entrprsManageInsertVO);
		return "forward:/uat/uia/egovLoginUsr.do";
	}

	/**
	 * 기업회원 약관확인 화면을 조회한다.
	 * 
	 * @param model 화면모델
	 * @return uss/umt/EgovStplatCnfirm
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovStplatCnfirmEntrprs.do")
	public String sbscrbEntrprsMber(Model model) throws Exception {

		String stplatId = "STPLAT_0000000000002";
		String sbscrbTy = "USR02";
		List<StplatVO> stplatList = entrprsManageService.selectStplat(stplatId);

		model.addAttribute("stplatList", stplatList);
		model.addAttribute("sbscrbTy", sbscrbTy);

		return "egovframework/com/uss/umt/EgovStplatCnfirm";
	}

	/**
	 * 기업회원 암호 수정처리 후 화면 이동한다.
	 * 
	 * @param model                   화면모델
	 * @param commandMap              파라메터전달용 commandMap
	 * @param userSearchVO            검색조건정보
	 * @param entrprsPasswordManageVO 기업회원수정정보
	 * @param bindingResult           입력값검증용 bindingResult
	 * @return uss/umt/EgovEntrprsPasswordUpdt
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsPasswordUpdt.do")
	public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap,
			@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@Valid @ModelAttribute("entrprsPasswordManageVO") EntrprsPasswordManageVO entrprsPasswordManageVO,
			BindingResult bindingResult) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovEntrprsPasswordUpdt";
		}

		boolean isCorrectPassword = false;
		EntrprsPasswordManageVO resultVO = new EntrprsPasswordManageVO();

		String resultMsg = "";
		resultVO = entrprsManageService.selectPassword(entrprsPasswordManageVO);
		String encryptPass = EgovFileScrty.encryptPassword(entrprsPasswordManageVO.getOldPassword(),
				entrprsPasswordManageVO.getEntrprsmberId());
		if (encryptPass.equals(resultVO.getEntrprsMberPassword())) {
			if (entrprsPasswordManageVO.getPassword().equals(entrprsPasswordManageVO.getPassword2())) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				resultMsg = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			resultMsg = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			entrprsPasswordManageVO.setEntrprsMberPassword(
					EgovFileScrty.encryptPassword(entrprsPasswordManageVO.getPassword(),
							entrprsPasswordManageVO.getEntrprsmberId()));
			entrprsManageService.updatePassword(entrprsPasswordManageVO);
			resultMsg = "success.common.update";
			clearUmtPasswordFields(entrprsPasswordManageVO);
		}

		model.addAttribute("resultMsg", resultMsg);

		return "egovframework/com/uss/umt/EgovEntrprsPasswordUpdt";
	}

	/**
	 * 기업회원암호 수정 화면 이동
	 * 
	 * @param model                   화면모델
	 * @param commandMap              파라메터전송용 commandMap
	 * @param userSearchVO            검색조건정보
	 * @param entrprsPasswordManageVO 기업회원수정정보
	 * @return uss/umt/EgovEntrprsPasswordUpdt
	 * @throws Exception
	 */
	@PostMapping("/uss/umt/EgovEntrprsPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("entrprsPasswordManageVO") EntrprsPasswordManageVO entrprsPasswordManageVO) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		entrprsPasswordManageVO.setUserTy(userTyForPassword);
		clearUmtPasswordFields(entrprsPasswordManageVO);

		model.addAttribute("entrprsPasswordManageVO", entrprsPasswordManageVO);
		model.addAttribute("userSearchVO", userSearchVO);
		return "egovframework/com/uss/umt/EgovEntrprsPasswordUpdt";
	}

	private void addEntrprsCodeListToModel(Model model) throws Exception {
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();

		comDefaultCodeVO.setCodeId("COM022");
		List<CmmnDetailCode> passwordHintResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM014");
		List<CmmnDetailCode> sexdstnCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM013");
		List<CmmnDetailCode> entrprsMberSttusResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
		List<CmmnDetailCode> groupIdResult = cmmUseService.selectGroupIdDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM026");
		List<CmmnDetailCode> entrprsSeCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		comDefaultCodeVO.setCodeId("COM027");
		List<CmmnDetailCode> indutyCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);

		model.addAttribute("passwordHint_result", passwordHintResult);
		model.addAttribute("sexdstnCode_result", sexdstnCodeResult);
		model.addAttribute("entrprsMberSttus_result", entrprsMberSttusResult);
		model.addAttribute("groupId_result", groupIdResult);
		model.addAttribute("entrprsSeCode_result", entrprsSeCodeResult);
		model.addAttribute("indutyCode_result", indutyCodeResult);
	}

	private String decryptSelectedId(String selectedId) {
		String decryptId = EgovComUtlController.decryptId(selectedId);
		if (decryptId != null && !decryptId.startsWith("CIPHER_ID_DECRIPT_EXCEPTION")) {
			return decryptId;
		}
		return null;
	}

	private static void clearUmtPasswordFields(PasswordManageVO vo) {
		vo.setOldPassword("");
		vo.setPassword("");
		vo.setPassword2("");
	}


	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 로그인 사용자 확인
	 */
	private LoginVO egovAssertLoginUser() {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null || "".equals(loginVO.getUniqId())) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		return loginVO;
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 관리자 또는 소유자
	 */
	private void egovAssertAdminOrOwner(String ownerUniqId) {
		LoginVO loginVO = egovAssertLoginUser();
		if (ownerUniqId != null && ownerUniqId.equals(loginVO.getUniqId())) {
			return;
		}
		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
		if (auth != null && auth.contains("ROLE_ADMIN")) {
			return;
		}
		throw new IllegalStateException("권한이 없습니다.");
	}

}