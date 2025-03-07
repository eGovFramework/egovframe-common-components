package egovframework.com.uss.ion.tir.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.tir.service.EgovTwitterTrnsmitService;
import twitter4j.BooleanResponse;
import twitter4j.CreateTweetResponse;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterV2;
import twitter4j.TwitterV2ExKt;
import twitter4j.UsersResponse;
import twitter4j.auth.AccessToken;
//import twitter4j.conf.Configuration;
//import twitter4j.conf.ConfigurationBuilder;
/**
 * 트위터수신을 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2024.10.29	LeeBaekHaeng	미사용 import 정리
 *
 * </pre>
 */
@Service("egovTwitterTrnsmitService")
public class EgovTwitterTrnsmitServiceImpl extends EgovAbstractServiceImpl
        implements EgovTwitterTrnsmitService {

	/* 트위터 DAO */
    @Resource(name = "twitterDao")
    private EgovTwitterDao dao;

    /**
	 * 트위터를 송신하다.
	 * @param sTwitterId 	-트위터 아이디
	 * @param sTwitterPw 	-트위터 비밀번호
	 * @param sTwitterText 	-트위터 등록 메세지
	 */
	@Override
	public CreateTweetResponse twitterTrnsmitRegist(Map<?, ?> map, String sTwitterText) throws Exception{

		String sCONSUMER_KEY = (String)map.get("sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String)map.get("sCONSUMER_SECRET");
		//트위터 객체선언
		Twitter twitter = new TwitterFactory().getInstance();
		//CONSUMER KEY, CONSUMER SECRET 설정
		twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
    	//엑서스 토큰 키 설정
    	AccessToken accessToken = new AccessToken((String)map.get("atoken"), (String)map.get("astoken"));
    	//엑서스 토큰 설정
    	twitter.setOAuthAccessToken(accessToken);
    	
        //트위터  글 게시
    	final TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
    	final CreateTweetResponse tweets = v2.createTweet(null, null, null, null, null, null, null, null, null, null, null, sTwitterText);
    	
        return tweets;

	}
	
	/**
	 * 트위터 계정 정보를 조회한다.
	 * @param map - 인증 정보가 담긴 Map
	 */
	@Override
	public Map<?, ?> twitterUserAccount(Map<?, ?> map) throws Exception {
		
		// 유저정보
		String sCONSUMER_KEY = (String)map.get("sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String)map.get("sCONSUMER_SECRET");
		//트위터 객체선언
		Twitter twitter = new TwitterFactory().getInstance();
		//CONSUMER KEY, CONSUMER SECRET 설정
		twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
    	//엑서스 토큰 키 설정
    	AccessToken accessToken = new AccessToken((String)map.get("atoken"), (String)map.get("astoken"));
    	//엑서스 토큰 설정
    	twitter.setOAuthAccessToken(accessToken);
    	
    	final TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
    	
    	final UsersResponse users = v2.getMe("pinned_tweet_id", "author_id", "created_at,profile_image_url");
    	
    	Long userId = users.getUsers().get(0).getId();
    	String userName = users.getUsers().get(0).getName();
    	String userScreenName = users.getUsers().get(0).getScreenName();
    	Date userCreate_at = users.getUsers().get(0).getCreatedAt();
    	String userProfile_url = users.getUsers().get(0).getProfileImageUrl();
    	
    	Map<String, Object> userResult = new HashMap<String, Object>();
    	userResult.put("userId", userId);
    	userResult.put("userName", userName);
    	userResult.put("userScreenName", userScreenName);
    	userResult.put("userCreate_At", userCreate_at);
    	userResult.put("userProfile_url", userProfile_url);
    	
		return userResult;
	}

	/**
	 * 트윗 내용삭제
	 * 
	 */
	@Override
	public boolean twitterDelete(Map<?, ?> map, String tID) throws Exception {

				String sCONSUMER_KEY = (String)map.get("sCONSUMER_KEY");
				String sCONSUMER_SECRET = (String)map.get("sCONSUMER_SECRET");
				//트위터 객체선언
				Twitter twitter = new TwitterFactory().getInstance();
				//CONSUMER KEY, CONSUMER SECRET 설정
				twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
		    	//엑서스 토큰 키 설정
		    	AccessToken accessToken = new AccessToken((String)map.get("atoken"), (String)map.get("astoken"));
		    	//엑서스 토큰 설정
		    	twitter.setOAuthAccessToken(accessToken);
		    	
		    	final TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
		    	final BooleanResponse deleteResult = v2.deleteTweet(Long.parseLong(tID));

		    	return deleteResult.getResult();
		
	}

    /**
     * 트위터 계정을 건수를 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return Map - 조회 정보가 담긴 Map
     * @throws Exception
     */
    @Override
	public Map<?, ?> selectTwitterAccount(Map<?, ?> param) throws Exception {
    	return dao.selectTwitterAccount(param);
    }

    /**
     * 트위터 계정을 건수를 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return int - 조회 정보가 담긴 Integer
     * @throws Exception
     */
    @Override
	public int selectTwitterAccountCheck(Map<?, ?> param) throws Exception {
    	return dao.selectTwitterAccountCheck(param);
    }

	/**
	 * 트위터 계정을 신규로 등록한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	@Override
	public void insertTwitterAccount(Map<?, ?> param) throws Exception {
		dao.insertTwitterAccount(param);
	}

	/**
	 * 트위터 계정을 수정한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	@Override
	public void updtTwitterAccount(Map<?, ?> param) throws Exception {
		dao.updtTwitterAccount(param);
	}

	/**
	 * 트위터 계정을 삭제한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	@Override
	public void deleteTwitterAccount(Map<?, ?> param) throws Exception {
        dao.deleteTwitterAccount(param);
	}

}
