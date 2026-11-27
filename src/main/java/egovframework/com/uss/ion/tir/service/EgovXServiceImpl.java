package egovframework.com.uss.ion.tir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EgovXService 구현체로 실제 외부 API 호출은 TwitterClient에 위임한다.
 */
@Service
public class EgovXServiceImpl implements EgovXService {

    @Autowired
    private EgovXClient twitterClient;

    /**
     * 내 계정 정보를 조회한다.
     */
    @Override
    public String getAccount(String bearerToken) {
        return twitterClient.getMe(bearerToken);
    }

    /**
     * 트윗을 등록한다.
     */
    @Override
    public String writeTweet(String bearerToken, String text) {
        return twitterClient.createTweet(bearerToken, text);
    }

    /**
     * 트윗을 삭제한다.
     */
    @Override
    public String deleteTweet(String bearerToken, String tweetId) {
        return twitterClient.deleteTweet(bearerToken, tweetId);
    }

    /**
     * 최근 트윗 목록을 조회한다.
     */
    @Override
    public String getLatestTweets(String bearerToken, String userId) {
        return twitterClient.getUserTweets(bearerToken, userId, 5);
    }

    /**
     * 다음 페이지 트윗 목록을 조회한다.
     */
    @Override
    public String getNextTweets(String bearerToken, String userId, String paginationToken) {
        return twitterClient.getUserTweetsNext(bearerToken, userId, paginationToken);
    }
}
