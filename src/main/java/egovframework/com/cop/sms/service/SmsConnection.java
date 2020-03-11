package egovframework.com.cop.sms.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 문자메시지 서비스 데이터 처리 모델
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.19
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.19  한성곤          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class SmsConnection implements Serializable {
    /** 수신번호 */
    private String callTo = "";
    
    /** 발신번호 */
    private String callFrom = "";
    
    /** 콜백번호 */
    private String callBack = "";
    
    /** 무선인터넷 주소 */
    private String callBackUrl = "";
    
    /** Message */
    private String text = "";
    
    /** serial 번호 : must be unique in single SME */
    private String messageId = "";
    
    /** 결과코드 */
    private int result = 0;
    
    /** 결과메시지 */
    private String resultMessage = "";

    /**
     * callTo attribute를 리턴한다.
     * @return the callTo
     */
    public String getCallTo() {
        return callTo;
    }

    /**
     * callTo attribute 값을 설정한다.
     * @param callTo the callTo to set
     */
    public void setCallTo(String callTo) {
        this.callTo = callTo;
    }

    /**
     * callFrom attribute를 리턴한다.
     * @return the callFrom
     */
    public String getCallFrom() {
        return callFrom;
    }

    /**
     * callFrom attribute 값을 설정한다.
     * @param callFrom the callFrom to set
     */
    public void setCallFrom(String callFrom) {
        this.callFrom = callFrom;
    }

    /**
     * callBack attribute를 리턴한다.
     * @return the callBack
     */
    public String getCallBack() {
        return callBack;
    }

    /**
     * callBack attribute 값을 설정한다.
     * @param callBack the callBack to set
     */
    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    /**
     * callBackUrl attribute를 리턴한다.
     * @return the callBackUrl
     */
    public String getCallBackUrl() {
        return callBackUrl;
    }

    /**
     * callBackUrl attribute 값을 설정한다.
     * @param callBackUrl the callBackUrl to set
     */
    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    /**
     * text attribute를 리턴한다.
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * text attribute 값을 설정한다.
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * messageId attribute를 리턴한다.
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * messageId attribute 값을 설정한다.
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * result attribute를 리턴한다.
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * result attribute 값을 설정한다.
     * @param result the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * resultMessage attribute를 리턴한다.
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * resultMessage attribute 값을 설정한다.
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
