package egovframework.com.cop.sms.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 문자메시지 서비스 데이터 처리 모델
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.18  한성곤          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class SmsRecptn implements Serializable {
    /** 문자메시지 ID */
    private String smsId = "";
    
    /** 수신 전화번호 */
    private String recptnTelno = "";
    
    /** 결과코드 */
    private String resultCode = "";
    
    /** 결과메시지 */
    private String resultMssage = "";

    /**
     * smsId attribute를 리턴한다.
     * @return the smsId
     */
    public String getSmsId() {
        return smsId;
    }

    /**
     * smsId attribute 값을 설정한다.
     * @param smsId the smsId to set
     */
    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    /**
     * recptnTelno attribute를 리턴한다.
     * @return the recptnTelno
     */
    public String getRecptnTelno() {
        return recptnTelno;
    }

    /**
     * recptnTelno attribute 값을 설정한다.
     * @param recptnTelno the recptnTelno to set
     */
    public void setRecptnTelno(String recptnTelno) {
        this.recptnTelno = recptnTelno;
    }

    /**
     * resultCode attribute를 리턴한다.
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * resultCode attribute 값을 설정한다.
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    /**
     * resultMssage attribute를 리턴한다.
     * @return the resultMssage
     */
    public String getResultMssage() {
        return resultMssage;
    }

    /**
     * resultMssage attribute 값을 설정한다.
     * @param resultMssage the resultMssage to set
     */
    public void setResultMssage(String resultMssage) {
        this.resultMssage = resultMssage;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
