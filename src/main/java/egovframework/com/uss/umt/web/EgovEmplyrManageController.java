package egovframework.com.uss.umt.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EgovEmplyrManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.EmplyrManageVO;
import egovframework.com.uss.umt.service.EmplyrManageInsertVO;
import egovframework.com.uss.umt.service.EmplyrPasswordManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import jakarta.annotation.Resource;

/**
 * 업무사용자관련 요청을 비지니스 클래스로 전달하고 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
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
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2025.08.29  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovEmplyrManageController {

	/** emplyrManageService */
	@Resource(name = "emplyrManageService")
	private EgovEmplyrManageService emplyrManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "egovNextUrlWhitelist")
    protected List<String> nextUrlWhitelist;

	/**
	 * 사용자목록을 조회한다. (pageing)
	 * 
	 * @param userSearchVO 검색조건정보
	 * @param model        화면모델
	 * @return cmm/uss/umt/EgovUserManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "업무사용자관리", order = 460, gid = 50)
	@RequestMapping(value = "/uss/umt/EgovEmplyrManage.do")
	public String selectEmplyrList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model)
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

		List<EgovMap> emplyrList = emplyrManageService.selectEmplyrList(userSearchVO);
		model.addAttribute("resultList", emplyrList);

		int totCnt = emplyrManageService.selectEmplyrListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 사용자상태코드를 코드정보로부터 조회
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setCodeId("COM013");
		List<CmmnDetailCode> emplyrSttusCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		model.addAttribute("emplyrSttusCode_result", emplyrSttusCodeResult);// 사용자상태코드목록

		return "egovframework/com/uss/umt/EgovEmplyrManage";
	}

	/**
	 * 사용자등록화면으로 이동한다.
	 * 
	 * @param userSearchVO         검색조건정보
	 * @param emplyrManageInsertVO 사용자초기화정보
	 * @param model                화면모델
	 * @return cmm/uss/umt/EgovUserInsert
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovEmplyrInsertView.do")
	public String insertUserView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("emplyrManageVO") EmplyrManageInsertVO emplyrManageInsertVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();

		// 패스워드힌트목록을 코드정보로부터 조회
		comDefaultCodeVO.setCodeId("COM022");
		List<CmmnDetailCode> passwordHintResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		// 성별구분코드를 코드정보로부터 조회
		comDefaultCodeVO.setCodeId("COM014");
		List<CmmnDetailCode> sexdstnCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		// 사용자상태코드를 코드정보로부터 조회
		comDefaultCodeVO.setCodeId("COM013");
		List<CmmnDetailCode> emplyrSttusCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		// 소속기관코드를 코드정보로부터 조회 - COM025
		comDefaultCodeVO.setCodeId("COM025");
		List<CmmnDetailCode> insttCodeResult = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
		// 조직정보를 조회 - ORGNZT_ID정보
		comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
		List<CmmnDetailCode> orgnztIdResult = cmmUseService.selectOgrnztIdDetail(comDefaultCodeVO);
		// 그룹정보를 조회 - GROUP_ID정보
		comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
		List<CmmnDetailCode> groupIdResult = cmmUseService.selectGroupIdDetail(comDefaultCodeVO);

		model.addAttribute("passwordHint_result", passwordHintResult); // 패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCodeResult); // 성별구분코드목록
		model.addAttribute("emplyrSttusCode_result", emplyrSttusCodeResult);// 사용자상태코드목록
		model.addAttribute("insttCode_result", insttCodeResult); // 소속기관코드목록
		model.addAttribute("orgnztId_result", orgnztIdResult); // 조직정보 목록
		model.addAttribute("groupId_result", groupIdResult); // 그룹정보 목록

		return "egovframework/com/uss/umt/EgovEmplyrInsert";
	}

	/**
	 * 사용자등록처리후 목록화면으로 이동한다.
	 * 
	 * @param emplyrManageInsertVO 사용자등록정보 (비밀번호 검증 포함)
	 * @param bindingResult        입력값검증용 bindingResult
	 * @param model                화면모델
	 * @return forward:/uss/umt/EgovEmplyrManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovEmplyrInsert.do")
	public String insertUser(@Valid @ModelAttribute("userManageVO") EmplyrManageInsertVO emplyrManageInsertVO, 
			BindingResult bindingResult,
			Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovEmplyrInsert";
		} else {
			if ("".equals(emplyrManageInsertVO.getOrgnztId())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
				emplyrManageInsertVO.setOrgnztId(null);
			}
			if ("".equals(emplyrManageInsertVO.getGroupId())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
				emplyrManageInsertVO.setGroupId(null);
			}
			emplyrManageService.insertEmplyr(emplyrManageInsertVO);
			// Exception 없이 진행시 등록성공메시지
			model.addAttribute("resultMsg", "success.common.insert");
		}
		return "forward:/uss/umt/EgovEmplyrManage.do";
	}

	/**
	 * 사용자정보 수정을 위해 사용자정보를 상세조회한다.
	 * 
	 * @param uniqId       상세조회대상 사용자아이디
	 * @param userSearchVO 검색조건
	 * @param model        화면모델
	 * @return uss/umt/EgovUserSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovEmplyrSelectUpdtView.do")
	public String updateUserView(@RequestParam("selectedId") String uniqId,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		// 패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<CmmnDetailCode> passwordHintResult = cmmUseService.selectCmmCodeDetail(vo);
		// 성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<CmmnDetailCode> sexdstnCodeResult = cmmUseService.selectCmmCodeDetail(vo);
		// 사용자상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		List<CmmnDetailCode> emplyrSttusCodeResult = cmmUseService.selectCmmCodeDetail(vo);
		// 소속기관코드를 코드정보로부터 조회 - COM025
		vo.setCodeId("COM025");
		List<CmmnDetailCode> insttCodeResult = cmmUseService.selectCmmCodeDetail(vo);
		// 조직정보를 조회 - ORGNZT_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<CmmnDetailCode> orgnztIdResult = cmmUseService.selectOgrnztIdDetail(vo);
		// 그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<CmmnDetailCode> groupIdResult = cmmUseService.selectGroupIdDetail(vo);

		model.addAttribute("passwordHint_result", passwordHintResult); // 패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCodeResult); // 성별구분코드목록
		model.addAttribute("emplyrSttusCode_result", emplyrSttusCodeResult);// 사용자상태코드목록
		model.addAttribute("insttCode_result", insttCodeResult); // 소속기관코드목록
		model.addAttribute("orgnztId_result", orgnztIdResult); // 조직정보 목록
		model.addAttribute("groupId_result", groupIdResult); // 그룹정보 목록

		EmplyrManageVO emplyrManageVO = new EmplyrManageVO();
		emplyrManageVO = emplyrManageService.selectEmplyr(uniqId);
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("emplyrManageVO", emplyrManageVO);

		return "egovframework/com/uss/umt/EgovEmplyrSelectUpdt";
	}

	/**
	 * 로그인인증제한 해제
	 * 
	 * @param userManageVO 사용자정보
	 * @param model        화면모델
	 * @return uss/umt/EgovUserSelectUpdtView.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovEmplyrLockIncorrect.do")
	public String updateLockIncorrect(EmplyrManageVO emplyrManageVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		emplyrManageService.updateLockIncorrect(emplyrManageVO);

		return "forward:/uss/umt/EgovEmplyrSelectUpdtView.do";
	}

	/**
	 * 사용자정보 수정후 목록조회 화면으로 이동한다.
	 * 
	 * @param userManageVO  사용자수정정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model         화면모델
	 * @return forward:/uss/umt/EgovEmplyrManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovEmplyrSelectUpdt.do")
	public String updateUser(@Valid @ModelAttribute("emplyrManageVO") EmplyrManageVO emplyrManageVO, BindingResult bindingResult,
			Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		if (bindingResult.hasErrors()) {
			String resultMsgCode = bindingResult.getAllErrors().get(0).getCode();
			model.addAttribute("resultMsg", resultMsgCode);
			return "forward:/uss/umt/EgovEmplyrManage.do";
		} else {
			// 업무사용자 수정시 히스토리 정보를 등록한다.
			emplyrManageService.insertEmplyrHistory(emplyrManageVO);
			if ("".equals(emplyrManageVO.getOrgnztId())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
				emplyrManageVO.setOrgnztId(null);
			}
			if ("".equals(emplyrManageVO.getGroupId())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
				emplyrManageVO.setGroupId(null);
			}
			emplyrManageService.updateEmplyr(emplyrManageVO);
			// Exception 없이 진행시 수정성공메시지
			model.addAttribute("resultMsg", "success.common.update");
			return "forward:/uss/umt/EgovEmplyrManage.do";
		}
	}

	/**
	 * 사용자정보삭제후 목록조회 화면으로 이동한다.
	 * 
	 * @param checkedIdForDel 삭제대상아이디 정보
	 * @param userSearchVO    검색조건
	 * @param model           화면모델
	 * @return forward:/uss/umt/EgovEmplyrManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovEmplyrDelete.do")
	public String deleteUser(@RequestParam("checkedIdForDel") String checkedIdForDel,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		emplyrManageService.deleteEmplyr(checkedIdForDel);
		// Exception 없이 진행시 등록성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/uss/umt/EgovEmplyrManage.do";
	}

	/**
	 * 입력한 사용자아이디의 중복확인화면 이동
	 * 
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovIdDplctCnfirmView.do")
	public String checkIdDplct(ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		model.addAttribute("checkId", "");
		model.addAttribute("usedCnt", "-1");
		return "egovframework/com/uss/umt/EgovIdDplctCnfirm";
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model      화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovIdDplctCnfirm.do")
	public String checkIdDplct(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		// 2022.11.11 시큐어코딩 처리
		String checkId = (String) commandMap.get("checkId");
		if (checkId == null || checkId.equals("")) {
			return "forward:/uss/umt/EgovIdDplctCnfirmView.do";
		} else {
			checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");
		}

		int usedCnt = emplyrManageService.checkIdDplct(checkId);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "egovframework/com/uss/umt/EgovIdDplctCnfirm";
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model      화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovIdDplctCnfirmAjax.do")
	public ModelAndView checkIdDplctAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		String checkId = (String) commandMap.get("checkId");
		// checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		int usedCnt = emplyrManageService.checkIdDplct(checkId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("checkId", checkId);

		return modelAndView;
	}

	/**
	 * 업무사용자 암호 수정처리 후 화면 이동
	 * 
	 * @param model                   화면모델
	 * @param commandMap              파라메터전달용 commandMap
	 * @param userSearchVO            검색조건
	 * @param emplyrPasswordManageVO  비밀번호 변경정보
	 * @param bindingResult           입력값검증용 bindingResult
	 * @return uss/umt/EgovUserPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovEmplyrPasswordUpdt.do")
	public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap,
			@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@Valid @ModelAttribute("emplyrPasswordManageVO") EmplyrPasswordManageVO emplyrPasswordManageVO,
			BindingResult bindingResult) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		if (bindingResult.hasErrors()) {
			// Validation 오류 시에도 사용자 정보 유지
			return "egovframework/com/uss/umt/EgovEmplyrPasswordUpdt";
		}

		boolean isCorrectPassword = false;
		EmplyrPasswordManageVO resultVO = new EmplyrPasswordManageVO();

		String resultMsg = "";
		resultVO = emplyrManageService.selectPassword(emplyrPasswordManageVO);
		// 패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(emplyrPasswordManageVO.getOldPassword(), emplyrPasswordManageVO.getEmplyrId());
		if (encryptPass.equals(resultVO.getEmplyrPassword())) {
			if (emplyrPasswordManageVO.getPassword().equals(emplyrPasswordManageVO.getPassword2())) {
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
			emplyrPasswordManageVO.setEmplyrPassword(
					EgovFileScrty.encryptPassword(emplyrPasswordManageVO.getPassword(), emplyrPasswordManageVO.getEmplyrId()));
			emplyrManageService.updatePassword(emplyrPasswordManageVO);
			resultMsg = "success.common.update";
		}

		model.addAttribute("resultMsg", resultMsg);

		return "egovframework/com/uss/umt/EgovEmplyrPasswordUpdt";
	}

	/**
	 * 업무사용자 암호 수정 화면 이동
	 * 
	 * @param model        화면모델
	 * @param commandMap   파라메터전달용 commandMap
	 * @param userSearchVO 검색조건
	 * @param userManageVO 사용자수정정보(비밀번호)
	 * @return uss/umt/EgovUserPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovEmplyrPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("emplyrPasswordManageVO") EmplyrPasswordManageVO emplyrPasswordManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		emplyrPasswordManageVO.setUserTy(userTyForPassword);

		// 명시적으로 model에 추가 (JSP에서 접근 가능하도록)
		model.addAttribute("emplyrPasswordManageVO", emplyrPasswordManageVO);
		model.addAttribute("userSearchVO", userSearchVO);
		return "egovframework/com/uss/umt/EgovEmplyrPasswordUpdt";
	}

	/**
	 * 약관동의 후 화면 이동
	 * 
	 * @return 이동할 화면은 화이트리스트로 처리함
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovRlnmCnfirm.do")
	public String rlnmCnfirm(Model model, @RequestParam Map<String, Object> commandMap) throws Exception {

		model.addAttribute("ihidnum", commandMap.get("ihidnum")); // 주민번호
		model.addAttribute("realname", commandMap.get("realname")); // 사용자이름
		model.addAttribute("sbscrbTy", commandMap.get("sbscrbTy")); // 사용자유형
		model.addAttribute("nextUrlName", commandMap.get("nextUrlName")); // 다음단계버튼명(이동할 URL에 따른)
		Integer linkIndex = Integer.parseInt((String) commandMap.get("nextUrl"));
		model.addAttribute("nextUrl", linkIndex); // 다음단계로 이동할 URL

		// 화이트 리스트 처리
		String link = "";
		// 화이트 리스트가 비었는지 확인
		if (nextUrlWhitelist == null || nextUrlWhitelist.isEmpty() || nextUrlWhitelist.size() <= linkIndex) {
			link = "egovframework/com/cmm/egovError";
			return link;
		}

		link = nextUrlWhitelist.get(linkIndex);

		link = link.replace(";", "");
		link = link.replace("%", "");

		// 안전한 경로 문자열로 조치
		link = EgovWebUtil.filePathBlackList(link);

		// 실명인증기능 미탑재로 바로 회원가입 페이지로 이동.
		return "forward:" + link;
	}

}
