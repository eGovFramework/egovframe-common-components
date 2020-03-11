package egovframework.com.utl.sys.trm.service.impl;


import java.util.Date;
import java.util.Random;

import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngChecker;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : EgovTrsmrcvMntrngCheckerImpl.java
 * @Description : 송수신모니터링을 위한 Check interface 예제 구현클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2010.08.16     김진만   최초생성
 *
 * @author  김진만
 * @version
 * @see
 *
 */

public class TrsmrcvMntrngCheckerTestImpl implements TrsmrcvMntrngChecker {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrsmrcvMntrngCheckerTestImpl.class);

	/**
	 * 송수신모니터링을 수행한다.
	 *
	 * 연계ID를 이용하여 연계기관과 통신에 필요한 정보를 얻은 다음 연계기관과 통신을 수행한다.
	 * 통신결과를 TrsmrcvMntrngResult 클래스 객체에 담아서 리턴한다.
	 *
	 * 통신결과가 true일때 : TrsmrcvMntrngResult의 nrmltAt에 true, cause에 null을 저장.
	 * 통신결과가 false일때: TrsmrcvMntrngResult의 nrmltAt에 false, cause에 에러원인 Exception을 저장한다.
	 *
	 *
	 * @return 모니터링결과
	 *
	 * @param cntcId   모니터링 대상 연계ID
	 *
	 */
	public TrsmrcvMntrngResult check(String cntcId) {
		Random oRandom = new Random();
		oRandom.setSeed(new Date().getTime());	// 2011.10.10 보안점검 후속조치
		Boolean b = oRandom.nextBoolean();
		TrsmrcvMntrngResult result = null;

		if (b.booleanValue()) {
			result = new TrsmrcvMntrngResult(b.booleanValue(), null);
		} else {
			result = new TrsmrcvMntrngResult(b.booleanValue(), new UnsupportedOperationException("송수신샘플Check클래스에서 발생한 Exception입니다."));
		}
		LOGGER.debug("result cause : {}", result.getCause());
		return result ;
	}
}
