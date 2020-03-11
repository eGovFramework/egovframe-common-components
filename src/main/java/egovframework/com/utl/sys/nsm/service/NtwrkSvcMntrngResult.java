package egovframework.com.utl.sys.nsm.service;

/**
 * 개요
 * - 네트워크서비스 모니터링에 대한 결과 클래스를 정의한다.
 * 
 * 상세내용
 * - 모니터링 정상여부, 원인 Exception 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */

public class NtwrkSvcMntrngResult {

	/**
	 * 모니터링 정상여부
	 */
	private boolean nrmltAt;
	/**
	 * 원인 Exception
	 */
	private Throwable cause;
	
	public boolean isNrmltAt() {
		return nrmltAt;
	}
	public Throwable getCause() {
		return cause;
	}
	public void setNrmltAt(boolean nrmltAt) {
		this.nrmltAt = nrmltAt;
	}
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public NtwrkSvcMntrngResult( boolean nrmltAt, Throwable cause) {
		this.nrmltAt = nrmltAt;
		this.cause = cause;
	}
	
}