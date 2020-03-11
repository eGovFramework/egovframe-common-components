package egovframework.com.utl.sys.trm.service;



/**
 * @Class Name : EgovTrsmrcvMntrngChecker.java
 * @Description : 송수신모니터링을 위한 Check interface
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

public interface TrsmrcvMntrngChecker {

	/**
	 * 송수신모니터링을 수행한다.
	 * 
	 * 연계ID를 이용하여 연계기관과 통신에 필요한 정보를 얻은 다음 연계기관과 통신을 수행한다. 
	 * 통신결과를 TrsmrcvMntrngResult 클래스 객체에 담아서 리턴한다. 
	 * 
	 * 통신결과가 true일때 : TrsmrcvMntrngResult의 nrmltAt에 true, cause에 null을 저장.
	 * 통신결과가 false일때: TrsmrcvMntrngResult의 nrmltAt에 false, cause에 에러원인 Exception을 저장한다. 
	 * 
	 * @return 모니터링결과
	 * 
	 * @param cntcId   모니터링 대상 연계ID
	 * 
	 */
	public TrsmrcvMntrngResult check(String cntcId) ;
}
