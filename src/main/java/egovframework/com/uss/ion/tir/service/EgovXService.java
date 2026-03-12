package egovframework.com.uss.ion.tir.service;

/**
 * Twitter(X) API 호출 기능을 제공하는 서비스 인터페이스.
 */
public interface EgovXService {

    /**
     * 액세스 토큰으로 내 계정 정보를 조회한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @return 계정 정보 JSON 문자열
     */
    String getAccount(String bearerToken);

    /**
     * 트윗을 등록한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param text 등록할 트윗 본문
     * @return 등록 결과 JSON 문자열
     */
    String writeTweet(String bearerToken, String text);

    /**
     * 트윗 ID로 트윗을 삭제한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param tweetId 삭제할 트윗 ID
     * @return 삭제 결과 JSON 문자열
     */
    String deleteTweet(String bearerToken, String tweetId);

    /**
     * 사용자 ID 기준 최근 트윗 목록을 조회한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param userId 조회 대상 사용자 ID
     * @return 트윗 목록 JSON 문자열
     */
    String getLatestTweets(String bearerToken, String userId);

    /**
     * pagination token 기준 다음 트윗 목록을 조회한다.
     *
     * @param bearerToken OAuth 액세스 토큰
     * @param userId 조회 대상 사용자 ID
     * @param token 다음 페이지 조회 토큰
     * @return 다음 트윗 목록 JSON 문자열
     */
    String getNextTweets(String bearerToken, String userId, String token);
}
