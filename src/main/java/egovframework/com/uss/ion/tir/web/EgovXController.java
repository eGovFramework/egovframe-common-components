package egovframework.com.uss.ion.tir.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.uss.ion.tir.service.EgovXService;
import jakarta.servlet.http.HttpSession;

/**
 * Twitter(X) API v2 데모 화면에서 사용하는 트윗/계정 API 요청을 처리하는 컨트롤러.
 */
@Controller
@RequestMapping("/twitter")
public class EgovXController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovXController.class);

    @Autowired
    private EgovXService twitterService;
    
    /**
     * 세션에 저장된 액세스 토큰으로 내 계정 정보를 조회한다.
     *
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @return Twitter 사용자 정보 JSON 문자열 또는 오류 JSON 문자열
     */
    @GetMapping("/me.do")
    @ResponseBody
    public String getMe(HttpSession session) {
        String accessToken = getAccessToken(session);
        if (accessToken == null) {
            return "{\"error\":\"twitter_access_token not found in session\"}";
        }
        return twitterService.getAccount(accessToken);
    }

    /**
     * 트윗을 등록한다.
     *
     * @param text 등록할 트윗 본문
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @return 등록 결과 JSON 문자열 또는 오류 JSON 문자열
     */
    @PostMapping("/tweet")
    @ResponseBody
    public String writeTweet(@RequestParam(name = "text") String text, HttpSession session) {
        String accessToken = getAccessToken(session);
        if (accessToken == null) {
            return "{\"error\":\"twitter_access_token not found in session\"}";
        }
        if (text == null || text.trim().isEmpty()) {
            return "{\"error\":\"text is required\"}";
        }
        return twitterService.writeTweet(accessToken, text);
    }

    /**
     * 트윗 ID로 트윗을 삭제한다.
     *
     * @param tweetId 삭제할 트윗 ID
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @return 삭제 결과 JSON 문자열 또는 오류 JSON 문자열
     */
    @DeleteMapping("/tweet")
    @ResponseBody
    public String deleteTweet(@RequestParam(name = "tweetId") String tweetId, HttpSession session) {
        String accessToken = getAccessToken(session);
        if (accessToken == null) {
            return "{\"error\":\"twitter_access_token not found in session\"}";
        }
        if (tweetId == null || tweetId.trim().isEmpty()) {
            return "{\"error\":\"tweetId is required\"}";
        }
        return twitterService.deleteTweet(accessToken, tweetId);
    }

    /**
     * 사용자 ID 기준 최근 트윗 목록을 조회한다.
     *
     * @param userId 조회 대상 Twitter 사용자 ID
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @return 트윗 목록 JSON 문자열 또는 오류 JSON 문자열
     */
    @GetMapping("/tweets")
    @ResponseBody
    public String getTweets(@RequestParam(name = "userId") String userId, HttpSession session) {
        String accessToken = getAccessToken(session);
        if (accessToken == null) {
            return "{\"error\":\"twitter_access_token not found in session\"}";
        }
        if (userId == null || userId.trim().isEmpty()) {
            return "{\"error\":\"userId is required\"}";
        }
        return twitterService.getLatestTweets(accessToken, userId);
    }

    /**
     * 페이징 토큰 기준 다음 트윗 목록을 조회한다.
     *
     * @param userId 조회 대상 Twitter 사용자 ID
     * @param token 다음 페이지 조회용 pagination token
     * @param session 현재 사용자 세션(액세스 토큰 저장소)
     * @return 다음 트윗 목록 JSON 문자열 또는 오류 JSON 문자열
     */
    @GetMapping("/tweets/next")
    @ResponseBody
    public String getNextTweets(@RequestParam(name = "userId") String userId,
                                @RequestParam(name = "token") String token,
                                HttpSession session) {
        String accessToken = getAccessToken(session);
        if (accessToken == null) {
            return "{\"error\":\"twitter_access_token not found in session\"}";
        }
        if (userId == null || userId.trim().isEmpty()) {
            return "{\"error\":\"userId is required\"}";
        }
        if (token == null || token.trim().isEmpty()) {
            return "{\"error\":\"token is required\"}";
        }
        return twitterService.getNextTweets(accessToken, userId, token);
    }

    /**
     * 세션에서 Twitter 액세스 토큰을 읽어온다.
     *
     * @param session 현재 사용자 세션
     * @return 액세스 토큰 문자열(없으면 {@code null})
     */
    private String getAccessToken(HttpSession session) {
        Object token = session.getAttribute("twitter_access_token");
        if (token == null) {
            LOGGER.warn("twitter_access_token not found in session");
            return null;
        }
        return String.valueOf(token);
    }
}
