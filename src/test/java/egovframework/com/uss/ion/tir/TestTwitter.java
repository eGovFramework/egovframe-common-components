package egovframework.com.uss.ion.tir;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * 트위터 수신, 송신를 처리하는 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2018.09.05
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2018.10.05  신용호          최초 생성
 *
 * </pre>
 */

public class TestTwitter {

	private static final String CONSUMER_KEY = "9G8MJEljV1NczcXOIH2V8RoP8";
	private static final String CONSUMER_SECRET = "dXZkxVqN3IfdTb5F7e7IIdIMG1jt9YLiOwon3i1aG9mYY7lsYa";
	private static final String ACCESS_TOKEN = "107365154-EuzxCwgsGUPYuOTzExTRNbNM1nETXGsg10Fpfqzt";
	private static final String ACCESS_TOKEN_SECRET = "U3sUX2AVpPbyLIeQzE5njPGvCB0VSu4s2DzvRrCAF9uU9";
	private static final String REQUEST_TOKEN = "f3D48gAAAAAA8pP9AAABZnYvLqU";
	private static final String REQUEST_TOKEN_SECRET = "mkWsF20FJCvO57g8WDmDykNskIqgSdti";
	private static final String OAUTH_VERIFIER = "EeHj1nvrTw9wGyq8krdmixcMfAvz9eQr";
	
	public static void main(String ar[]) {
		
		// Twitter TimeLine 글 가져오기
		//listTimeLine();
		// TwitterAccessToken 가져오기
		getAccessToken();
	}
	
	public static void listTimeLine() {

		try {
	        //AccessToken accesstoken = new AccessToken("Access Token", "Access Token Secret");
	    	AccessToken accesstoken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
	        Twitter twitter = TwitterFactory.getSingleton();
	        //twitter.setOAuthConsumer("Consumer Key (API Key)", "Consumer Secret (API Secret)");
	        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	        twitter.setOAuthAccessToken(accesstoken);
	        User user = twitter.verifyCredentials();
	        
	        List<Status> list = twitter.getUserTimeline(); 
	        System.out.println("타임라인 계정:"+user.getScreenName());
	        for(Status status : list) {
	            System.out.println("작성자:"+status.getUser().getScreenName());
	            System.out.println("타임라인내용:"+status.getText());
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void getAccessToken() {
		
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET); //저장된 consumerKey, consumerSecret
		AccessToken accessToken = null;		

		//callbackURL에서 전달받은 oauth_verifier
		String oauth_verifier = OAUTH_VERIFIER;

		//트위터 로그인 연동시 담은 requestToken 의 세션값을 가져온다.
		//RequestToken requestToken = (RequestToken )request.getSession().getAttribute("requestToken");			
		RequestToken requestToken = new RequestToken(REQUEST_TOKEN, REQUEST_TOKEN_SECRET);
		try {
			//requestToken = twitter.getOAuthRequestToken();
			accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//twitter.setOAuthAccessToken(accessToken);

		//해당 트위터 사용자의 이름과 아이디를 가져온다.
		System.out.println(accessToken.getUserId());    //트위터의 사용자 아이디
		System.out.println(accessToken.getScreenName()); //트워터에 표시되는 사용자명
	}
	
}
