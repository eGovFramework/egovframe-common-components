package egovframework.com.utl.sys.dbm.service;


/**
 * DB서비스모니터링 에 대한 결과 클래스
 * 
 * @author 김진만
 * @since 2010.07.15
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.07.15   김진만     최초 생성
 * </pre>
 */
public class DbMntrngResult {

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

	public DbMntrngResult( boolean nrmltAt, Throwable cause) {
		this.nrmltAt = nrmltAt;
		this.cause = cause;
	}
	
}