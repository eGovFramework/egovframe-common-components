package egovframework.com.uss.ion.tir.service;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

/**
 * Twitter(X) OAuth 2.0 + PKCE 인증 URL 생성 및 토큰 발급을 처리하는 서비스.
 */
@Service
public class EgovXOAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovXOAuthService.class);

    private static final String REDIRECT_URI = "http://localhost:8080/egovframe-template-common-components/twitter/callback.do";

    private static final String AUTH_URL = "https://twitter.com/i/oauth2/authorize";
    private static final String TOKEN_URL = "https://api.x.com/2/oauth2/token";
    private static final String SESSION_OAUTH_CTX_PREFIX = "TWITTER_OAUTH_CTX_";
    private static final Pattern ACCESS_TOKEN_PATTERN =
            Pattern.compile("\"access_token\"\\s*:\\s*\"([^\"]+)\"");

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * state별로 PKCE verifier와 client 인증정보를 함께 보관하기 위한 컨텍스트 객체.
     * OAuth 콜백 완료 시 세션에서 즉시 제거한다.
     */
    private static class OAuthContext implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String codeVerifier;
        private final String clientId;
        private final String clientSecret;

        private OAuthContext(String codeVerifier, String clientId, String clientSecret) {
            this.codeVerifier = codeVerifier;
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }
    }

    /**
     * PKCE 파라미터를 포함한 Twitter 인증 URL을 생성한다.
     * OAuth 콜백에서 토큰 교환에 필요한 값은 세션에 임시 저장한다.
     *
     * @param session 현재 사용자 세션
     * @param clientId Twitter OAuth Client ID (기존 ConsumerKey 컬럼 사용)
     * @param clientSecret Twitter OAuth Client Secret (기존 ConsumerSecret 컬럼 사용)
     * @return OAuth 인증 URL
     * @throws Exception 해시 생성 등 URL 조합 중 오류
     */
    public String getAuthorizationUrl(HttpSession session, String clientId, String clientSecret) throws Exception {
        if (session == null) {
            throw new IllegalArgumentException("session is required");
        }
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("clientId is required");
        }
        if (clientSecret == null || clientSecret.trim().isEmpty()) {
            throw new IllegalArgumentException("clientSecret is required");
        }

        String trimmedClientId = clientId.trim();
        String trimmedClientSecret = clientSecret.trim();
        String state = UUID.randomUUID().toString();
        String codeVerifier = UUID.randomUUID().toString().replace("-", "");

        session.setAttribute(SESSION_OAUTH_CTX_PREFIX + state,
                new OAuthContext(codeVerifier, trimmedClientId, trimmedClientSecret));

        String challenge = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(
                        MessageDigest.getInstance("SHA-256")
                                .digest(codeVerifier.getBytes(StandardCharsets.UTF_8))
                );
        //앱 권한 범위
        String scope = URLEncoder.encode("tweet.read tweet.write users.read offline.access", StandardCharsets.UTF_8.name());

        return AUTH_URL +
                "?response_type=code" +
                "&client_id=" + trimmedClientId +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.name()) +
                "&scope=" + scope +
                "&state=" + state +
                "&code_challenge=" + challenge +
                "&code_challenge_method=S256";
    }

    /**
     * OAuth 인가 코드와 state를 이용해 액세스 토큰을 발급받는다.
     * 세션에 임시 저장된 OAuth 컨텍스트는 사용 후 즉시 제거한다.
     *
     * @param session 현재 사용자 세션
     * @param code OAuth 인가 코드
     * @param state OAuth state 값(PKCE verifier 조회 키)
     * @return 발급된 access token 문자열
     */
    public String requestAccessToken(HttpSession session, String code, String state) {
        if (session == null) {
            throw new IllegalArgumentException("session is required");
        }

        String attrKey = SESSION_OAUTH_CTX_PREFIX + state;
        OAuthContext context = (OAuthContext) session.getAttribute(attrKey);

        if (context == null) {
            throw new IllegalStateException("Invalid or expired state, cannot find oauth context");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(context.clientId, context.clientSecret, StandardCharsets.UTF_8);

        String body = "grant_type=authorization_code" +
                "&client_id=" + context.clientId +
                "&client_secret=" + URLEncoder.encode(context.clientSecret, StandardCharsets.UTF_8) +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8) +
                "&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8) +
                "&code_verifier=" + URLEncoder.encode(context.codeVerifier, StandardCharsets.UTF_8);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_URL, entity, String.class);
            session.removeAttribute(attrKey);
            return extractAccessToken(response.getBody());
        } catch (HttpStatusCodeException e) {
            LOGGER.error("Token API failed. status={}, body={}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new IllegalStateException("토큰 발급 실패: " + e.getStatusCode());
        }
    }

    /**
     * 토큰 응답 JSON 문자열에서 access_token 값을 추출한다.
     *
     * @param json 토큰 발급 응답 JSON 문자열
     * @return access_token 값
     */
    private String extractAccessToken(String json) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalStateException("토큰 응답이 비어 있습니다.");
        }

        Matcher matcher = ACCESS_TOKEN_PATTERN.matcher(json);
        if (!matcher.find()) {
            throw new IllegalStateException("토큰 응답에서 access_token을 찾을 수 없습니다. response=" + json);
        }
        return matcher.group(1);
    }
}
