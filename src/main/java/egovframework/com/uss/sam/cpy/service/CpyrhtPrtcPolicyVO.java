package egovframework.com.uss.sam.cpy.service;


/**
 *  
 * 저작권보호정책내용을 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public class CpyrhtPrtcPolicyVO extends CpyrhtPrtcPolicyDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 저작권 ID */
    private String cpyrhtId;
    
    /** 저작권보호정책내용 */
    private String cpyrhtPrtcPolicyCn;    
        
    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

	/**
	 * cpyrhtId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCpyrhtId() {
		return cpyrhtId;
	}

	/**
	 * cpyrhtId attribute 값을 설정한다.
	 * @return cpyrhtId String
	 */
	public void setCpyrhtId(String cpyrhtId) {
		this.cpyrhtId = cpyrhtId;
	}

	/**
	 * cpyrhtPrtcPolicyCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCpyrhtPrtcPolicyCn() {
		return cpyrhtPrtcPolicyCn;
	}

	/**
	 * cpyrhtPrtcPolicyCn attribute 값을 설정한다.
	 * @return cpyrhtPrtcPolicyCn String
	 */
	public void setCpyrhtPrtcPolicyCn(String cpyrhtPrtcPolicyCn) {
		this.cpyrhtPrtcPolicyCn = cpyrhtPrtcPolicyCn;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	
    
   
}
