package egovframework.com.utl.sys.trm.service;


/**
 * 송수신모니터링 에 대한 결과 클래스
 * 
 * @author 김진만
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.08.16   김진만     최초 생성
 * </pre>
 */
public class TrsmrcvMntrngResult {

	/**
	 * 모니터링 정상여부
	 */
	private boolean nrmltAt;
	/**
	 * 원인 Exception
	 */
	private Throwable cause;
	
	/**
	 * @return the nrmltAt
	 */
	public boolean isNrmltAt() {
		return nrmltAt;
	}
	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}
	/**
	 * @param nrmltAt the nrmltAt to set
	 */
	public void setNrmltAt(boolean nrmltAt) {
		this.nrmltAt = nrmltAt;
	}
	/**
	 * @param cause the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public TrsmrcvMntrngResult( boolean nrmltAt, Throwable cause) {
		this.nrmltAt = nrmltAt;
		this.cause = cause;
	}
	
}