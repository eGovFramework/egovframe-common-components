package egovframework.com.uss.ion.tir.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.tir.service.EgovXOAuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

/**
 * Twitter(X) OAuth 인증 시작/콜백 처리와 인증키 입력 팝업을 담당하는 컨트롤러.
 */
@Controller
@RequestMapping("/twitter")
public class EgovXAuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovXAuthController.class);

    @Resource(name = "egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Autowired
    private EgovXOAuthService twitterOAuthService;

    /**
     * OAuth 인증 팝업(인증키 입력 화면)을 조회한다.
     * 임시 운영 정책에 따라 저장값 자동 채움은 하지 않고 안내만 표시한다.
     *
     * @param model 팝업 화면 데이터 전달용 모델
     * @return 팝업 JSP 경로 또는 로그인 페이지 리다이렉트
     */
    @GetMapping("/login.do")
    public String login(ModelMap model) {
        LoginVO loginVO = requireLogin(model);
        if (loginVO == null) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        model.addAttribute("saveFeatureEnabled", Boolean.FALSE);
        return "egovframework/com/uss/ion/tir/EgovTwitterPopup";
    }

    /**
     * 팝업에서 입력한 인증키로 1회성 OAuth 인증을 시작한다.
     *
     * @param commandMap 팝업 폼 파라미터(Map)
     * @param model 오류 발생 시 팝업 재표시용 모델
     * @param session 현재 사용자 세션(OAuth 임시 컨텍스트 저장용)
     * @return Twitter 인증 페이지 리다이렉트 또는 팝업 JSP
     * @throws Exception 처리 중 오류
     */
    @PostMapping("/login.do")
    public String loginSubmit(@RequestParam Map<String, String> commandMap,
                              ModelMap model,
                              HttpSession session) throws Exception {
        LoginVO loginVO = requireLogin(model);
        if (loginVO == null) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        // 기존 테이블 파라미터명을 그대로 사용한다.
        // ConsumerKey = Twitter OAuth Client ID, ConsumerSecret = Twitter OAuth Client Secret
        String inputConsumerKey = trim(commandMap.get("ConsumerKey"));
        String inputConsumerSecret = trim(commandMap.get("ConsumerSecret"));

        if (inputConsumerKey.isEmpty() || inputConsumerSecret.isEmpty()) {
            model.addAttribute("saveFeatureEnabled", Boolean.FALSE);
            model.addAttribute("errorMessage", "Client ID/Client Secret(기존 ConsumerKey/ConsumerSecret)를 모두 입력하세요.");
            return "egovframework/com/uss/ion/tir/EgovTwitterPopup";
        }

        model.addAttribute("saveFeatureEnabled", Boolean.FALSE);

        String authUrl = twitterOAuthService.getAuthorizationUrl(session, inputConsumerKey, inputConsumerSecret);
        return "redirect:" + authUrl;
    }

    /**
     * 연동 라우팅 확인용 간단한 응답을 반환한다.
     *
     * @return 고정 응답 문자열
     */
    @GetMapping("/ping.do")
    @ResponseBody
    public String ping() {
        return "twitter-ping-ok";
    }

    /**
     * 디버그용 OAuth 콜백 처리 메서드로 토큰 발급 및 세션 저장만 수행한다.
     *
     * @param code OAuth 인가 코드
     * @param state OAuth state 값(PKCE verifier 조회 키)
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @return 디버그 처리 결과 문자열
     */
    @GetMapping("/callbackDebug.do")
    @ResponseBody
    public String callbackDebug(@RequestParam(name = "code", required = false) String code,
                                @RequestParam(name = "state", required = false) String state,
                                HttpSession session) {
        try {
            if (code == null || state == null) {
                return "missing-param code/state";
            }
            String accessToken = twitterOAuthService.requestAccessToken(session, code, state);
            session.setAttribute("twitter_access_token", accessToken);
            return "ok-token-saved";
        } catch (Exception e) {
            return "callback-debug-error: " + e.getClass().getName() + " / " + e.getMessage();
        }
    }

    /**
     * OAuth 콜백을 처리하고 인증 성공/실패 결과를 콜백 JSP에 전달한다.
     *
     * @param code OAuth 인가 코드
     * @param state OAuth state 값(PKCE verifier 조회 키)
     * @param oauthError OAuth 오류 코드
     * @param errorDescription OAuth 오류 상세 설명
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @param model 콜백 결과 화면에 전달할 모델 객체
     * @return 인증 결과를 표시할 콜백 JSP 경로
     */
    @GetMapping("/callback.do")
    public String callback(@RequestParam(name = "code", required = false) String code,
                           @RequestParam(name = "state", required = false) String state,
                           @RequestParam(required = false, name = "error") String oauthError,
                           @RequestParam(required = false, name = "error_description") String errorDescription,
                           HttpSession session,
                           Model model) {
        LOGGER.info("Twitter callback called. state={}, codePresent={}, oauthError={}", state, code != null, oauthError);

        if (oauthError != null) {
            model.addAttribute("authSuccess", false);
            model.addAttribute("errorMessage", "OAuth 오류: " + oauthError + " / " + errorDescription);
            return "egovframework/com/uss/ion/tir/EgovXAuthCallback";
        }

        if (code == null || state == null) {
            model.addAttribute("authSuccess", false);
            model.addAttribute("errorMessage", "콜백 파라미터(code/state)가 누락되었습니다.");
            return "egovframework/com/uss/ion/tir/EgovXAuthCallback";
        }

        try {
            String accessToken = twitterOAuthService.requestAccessToken(session, code, state);
            session.setAttribute("twitter_access_token", accessToken);
            model.addAttribute("authSuccess", true);
            model.addAttribute("redirectUrl", "/uss/ion/tir/selectTwitterV2Demo.do");
            return "egovframework/com/uss/ion/tir/EgovXAuthCallback";
        } catch (Exception e) {
            LOGGER.error("Twitter callback failed: {}", e.getMessage(), e);
            model.addAttribute("authSuccess", false);
            model.addAttribute("errorMessage", e.getMessage());
            return "egovframework/com/uss/ion/tir/EgovXAuthCallback";
        }
    }

    /**
     * 로그인 여부를 확인하고 미로그인 시 공통 메시지를 모델에 담는다.
     */
    private LoginVO requireLogin(ModelMap model) {
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return null;
        }
        return (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    /*
     * TODO(필수): CNSMRY_KEY / CNSMRY_SECRET 를 DB에 저장시 암복호화 저장 로직 복구 필요
     * - Client ID / Client Secret 은 비밀번호급 정보이므로 평문 저장 금지, 암호화 저장 필수
     * 
     *
     * [복구 시 컨트롤러 내에 다시 둘 조회/저장 로직 스켈레톤]
     * - DAO/Service는 기존 egovTwitterTrnsmitService.selectTwitterAccount / updt / insert / delete 재사용
     *
     * // 조회 (저장값 존재 여부 확인 + 필요 시 복호화)
     * // ConsumerKey = Client ID, ConsumerSecret = Client Secret (기존 컬럼명 유지)
     * // private Map<?, ?> getSavedTwitterClient(LoginVO loginVO) throws Exception {
     * //     HashMap<String, String> param = new HashMap<String, String>();
     * //     param.put("usid", EgovStringUtil.isNullToString(loginVO.getUniqId()));
     * //     return egovTwitterTrnsmitService.selectTwitterAccount(param);
     * // }
     *
     * // 저장 (암호화 후 upsert)
     * // private void saveTwitterClient(LoginVO loginVO, String consumerKey, String consumerSecret) throws Exception {
     * //     HashMap<String, String> param = new HashMap<String, String>();
     * //     String uniqId = EgovStringUtil.isNullToString(loginVO.getUniqId());
     * //     param.put("usid", uniqId);
     * //     param.put("consumerKey", cryptoService.encrypt(trim(consumerKey)));     // 필수: 암호화 저장
     * //     param.put("consumerSecret", cryptoService.encrypt(trim(consumerSecret))); // 필수: 암호화 저장
     * //     param.put("frstRegisterId", uniqId);
     * //     param.put("lastUpdusrId", uniqId);
     * //
     * //     if (egovTwitterTrnsmitService.selectTwitterAccountCheck(param) > 0) {
     * //         egovTwitterTrnsmitService.updtTwitterAccount(param);
     * //     } else {
     * //         egovTwitterTrnsmitService.insertTwitterAccount(param);
     * //     }
     * // }
     *
     * // 삭제
     * // private void deleteTwitterClient(LoginVO loginVO) throws Exception {
     * //     HashMap<String, String> param = new HashMap<String, String>();
     * //     param.put("usid", EgovStringUtil.isNullToString(loginVO.getUniqId()));
     * //     egovTwitterTrnsmitService.deleteTwitterAccount(param);
     * // }
     * 
     * 암호화 예시:
     * private String encryptForStorage(String plainValue) { return cryptoService.encrypt(trim(plainValue)); }
     * private String decryptStoredValue(String storedValue) { return cryptoService.decrypt(storedValue); }
     */
}

