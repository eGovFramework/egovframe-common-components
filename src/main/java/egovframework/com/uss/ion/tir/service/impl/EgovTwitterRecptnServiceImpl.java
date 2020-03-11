package egovframework.com.uss.ion.tir.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.com.uss.ion.tir.service.EgovTwitterRecptnService;
import egovframework.com.uss.ion.tir.service.TwitterInfo;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
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
 *
 * </pre>
 */
@Service("egovTwitterRecptnService")
public class EgovTwitterRecptnServiceImpl extends EgovAbstractServiceImpl
        implements EgovTwitterRecptnService {

    /**
	 * 트위터 목록을 조회한다.
	 * @param sTwitterId 	-트위터 아이디
	 * @param sTwitterPw 	-트위터 비밀번호
	 * @param nPageSize 	-페이징 갯수
     * @return List 		-조회 결과
     * @throws Exception	-Exception Throws
	 */
    @Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<?> twitterRecptnList(Map<?, ?> map, int nPageSize) throws Exception {

    	String sCONSUMER_KEY = (String)map.get("sCONSUMER_KEY");
    	String sCONSUMER_SECRET = (String)map.get("sCONSUMER_SECRET");
    	
    	//트위터 객체선언
    	Twitter twitter = new TwitterFactory().getInstance();
		
    	//CONSUMER KEY, CONSUMER SECRET 설정
		twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
    	//twitter.setOAuthConsumer((String)map.get("sCONSUMER_KEY"), (String)map.get("sCONSUMER_SECRET"));
    	//엑서스 토큰 키 설정
    	AccessToken accessToken = new AccessToken((String)map.get("atoken"), (String)map.get("astoken"));
		
    	//엑서스 토큰 설정
    	twitter.setOAuthAccessToken(accessToken);

    	//트위터 Status 객체
    	List<Status> statuses;
    	//반환 트위터 모록
    	List<TwitterInfo> listRtn = new ArrayList();
    	//트위터 페이징 객체
        Paging page = new Paging();
        //페이지 갯수 설정
        page.count(nPageSize);
        //인덱스 설정
        page.setPage(1);
        //트위터 타임라인 설정
		statuses = twitter.getUserTimeline(page);
		//statuses = twitter.getUserTimeline(new Paging(1, nPageSize));
    	//statuses = twitter.getFriendsTimeline((new Paging(1, nPageSize));

        for(Status status : statuses) {

        	//트위터 정보 객체 선언
        	TwitterInfo twitterInfo = new TwitterInfo();
        	// 트위터 이름
        	twitterInfo.setTwitterNmae(status.getUser().getName());
        	// 트위터 스크린명
        	twitterInfo.setTwitterScreenName(status.getUser().getScreenName());
        	// 트위터 URL
        	twitterInfo.setTwitterURL( String.valueOf(status.getUser().getURL()) );
        	// 트위터 텍스트
        	twitterInfo.setTwitterText(status.getText());
        	///DATE -> 변환을 하기위한 SimpleDateFormat 설정
        	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	// 트위터 등록일
        	twitterInfo.setTwitterCreatedAt( format.format(status.getCreatedAt()) );
        	// 트위터 프로파일 이미지명
        	twitterInfo.setTwitterProfileImageURL(String.valueOf(status.getUser().getProfileImageURL()));
        	// 트위터 소스 status.getSource()
        	twitterInfo.setTwitterSource("");

        	listRtn.add(twitterInfo);
        }


    	return listRtn;
    }

}
