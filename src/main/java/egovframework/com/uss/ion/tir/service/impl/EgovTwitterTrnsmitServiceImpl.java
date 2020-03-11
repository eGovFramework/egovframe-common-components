package egovframework.com.uss.ion.tir.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.tir.service.EgovTwitterTrnsmitService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
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
	public Status twitterTrnsmitRegist(Map<?, ?> map, String sTwitterText) throws Exception{

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
        return twitter.updateStatus(sTwitterText);

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
