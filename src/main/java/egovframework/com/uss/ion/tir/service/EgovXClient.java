package egovframework.com.uss.ion.tir.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Twitter(X) REST API를 직접 호출하는 HTTP 클라이언트 컴포넌트.
 */
@Component
public class EgovXClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovXClient.class);

    private static final String BASE_URL = "https://api.x.com/2";

    private final RestTemplate restTemplate;

    /**
     * REST API 호출용 RestTemplate을 초기화한다.
     */
    public EgovXClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Bearer 토큰 기반 공통 요청 헤더를 생성한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @return Authorization/Content-Type 헤더가 포함된 객체
     */
    private HttpHeaders createHeaders(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * 내 계정 정보를 조회한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @return 계정 정보 JSON 문자열
     */
    public String getMe(String bearerToken) {
        String url = BASE_URL + "/users/me";

        LOGGER.info("=== Twitter API CALL START ===");
        LOGGER.info("URL = {}", url);

        HttpEntity<?> entity = new HttpEntity<>(createHeaders(bearerToken));

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            LOGGER.info("STATUS = {}", response.getStatusCode());
            LOGGER.info("BODY = {}", response.getBody());

            return response.getBody();
        } catch (HttpStatusCodeException e) {
            LOGGER.error("API ERROR STATUS = {}", e.getStatusCode());
            LOGGER.error("API ERROR BODY = {}", e.getResponseBodyAsString());
            throw e;
        }
    }

    /**
     * 트윗을 등록한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param text 등록할 트윗 본문
     * @return 등록 결과 JSON 문자열
     */
    public String createTweet(String bearerToken, String text) {
        String url = BASE_URL + "/tweets";

        Map<String, Object> body = new HashMap<>();
        body.put("text", text);

        HttpEntity<?> entity = new HttpEntity<>(body, createHeaders(bearerToken));

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }

    /**
     * 트윗을 삭제한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param tweetId 삭제할 트윗 ID
     * @return 삭제 결과 JSON 문자열
     */
    public String deleteTweet(String bearerToken, String tweetId) {
        String url = BASE_URL + "/tweets/" + tweetId;

        HttpEntity<?> entity = new HttpEntity<>(createHeaders(bearerToken));

        return restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class).getBody();
    }

    /**
     * 사용자의 최근 트윗 목록을 조회한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param userId 조회 대상 사용자 ID
     * @param maxResults 조회 건수
     * @return 트윗 목록 JSON 문자열
     */
    public String getUserTweets(String bearerToken, String userId, int maxResults) {
        String url = BASE_URL + "/users/" + userId + "/tweets?max_results=" + maxResults;

        HttpEntity<?> entity = new HttpEntity<>(createHeaders(bearerToken));

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }

    /**
     * pagination token으로 다음 트윗 목록을 조회한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param userId 조회 대상 사용자 ID
     * @param paginationToken 다음 페이지 조회 토큰
     * @return 다음 트윗 목록 JSON 문자열
     */
    public String getUserTweetsNext(String bearerToken, String userId, String paginationToken) {
        String url = BASE_URL + "/users/" + userId + "/tweets?pagination_token=" + paginationToken;

        HttpEntity<?> entity = new HttpEntity<>(createHeaders(bearerToken));

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
