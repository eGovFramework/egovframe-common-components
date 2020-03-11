package egovframework.com.cop.sms.service;

import java.io.Serializable;
import java.util.List;

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
 *	 2011.10.07	 이기하		보안취약점 수정(private 배열 처리)
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class Sms implements Serializable {
    /** 문자메시지 ID */
    private String smsId = "";

    /** 전송 전화번호 */
    private String trnsmitTelno = "";

    /** 전송 내용 */
    private String trnsmitCn = "";

    /** 수신 전화번호 개수 */
    private int recptnCnt = 0;

    /** 유일 아이디 */
    private String uniqId = "";

    /** 최초등록자 아이디 */
    private String frstRegisterId = "";

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 최초등록시점 */
    private String frstRegisterPnttm = "";

    /** 수신 정보 List */
    private List<SmsRecptn> recptn = null;

    /** 수전 전화번호 배열 */
    private String[] recptnTelno = null;

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
     * trnsmitTelno attribute를 리턴한다.
     * @return the trnsmitTelno
     */
    public String getTrnsmitTelno() {
        return trnsmitTelno;
    }

    /**
     * trnsmitTelno attribute 값을 설정한다.
     * @param trnsmitTelno the trnsmitTelno to set
     */
    public void setTrnsmitTelno(String trnsmitTelno) {
        this.trnsmitTelno = trnsmitTelno;
    }

    /**
     * trnsmitCn attribute를 리턴한다.
     * @return the trnsmitCn
     */
    public String getTrnsmitCn() {
        return trnsmitCn;
    }

    /**
     * trnsmitCn attribute 값을 설정한다.
     * @param trnsmitCn the trnsmitCn to set
     */
    public void setTrnsmitCn(String trnsmitCn) {
        this.trnsmitCn = trnsmitCn;
    }

    /**
     * frstRegisterId attribute를 리턴한다.
     * @return the frstRegisterId
     */
    public String getFrstRegisterId() {
        return frstRegisterId;
    }

    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId the frstRegisterId to set
     */
    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }

    /**
     * frstRegisterNm attribute를 리턴한다.
     * @return the frstRegisterNm
     */
    public String getFrstRegisterNm() {
        return frstRegisterNm;
    }

    /**
     * frstRegisterNm attribute 값을 설정한다.
     * @param frstRegisterNm the frstRegisterNm to set
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
        this.frstRegisterNm = frstRegisterNm;
    }

    /**
     * frstRegisterPnttm attribute를 리턴한다.
     * @return the frstRegisterPnttm
     */
    public String getFrstRegisterPnttm() {
        return frstRegisterPnttm;
    }

    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @param frstRegisterPnttm the frstRegisterPnttm to set
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
        this.frstRegisterPnttm = frstRegisterPnttm;
    }

    /**
     * recptn attribute를 리턴한다.
     * @return the recptn
     */
    public List<SmsRecptn> getRecptn() {
        return recptn;
    }

    /**
     * recptn attribute 값을 설정한다.
     * @param recptn the recptn to set
     */
    public void setRecptn(List<SmsRecptn> recptn) {
        this.recptn = recptn;
    }

    /**
     * uniqId attribute를 리턴한다.
     * @return the uniqId
     */
    public String getUniqId() {
        return uniqId;
    }

    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId the uniqId to set
     */
    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    /**
     * recptnCnt attribute를 리턴한다.
     * @return the recptnCnt
     */
    public int getRecptnCnt() {
        return recptnCnt;
    }

    /**
     * recptnCnt attribute 값을 설정한다.
     * @param recptnCnt the recptnCnt to set
     */
    public void setRecptnCnt(int recptnCnt) {
        this.recptnCnt = recptnCnt;
    }

    /**
     * recptnTelno attribute를 리턴한다.
     * @return the recptnTelno
     */
//    public String[] getRecptnTelno() {
//        return recptnTelno;
//    }
    // 2011.10.07 private 배열을 public 함수가 반환되지 않도록 함
    public String[] getRecptnTelno() {
    	// 메소드를 private으로 하거나, 복제본을 반환하거나,
    	// 수정을 제어하는 public메소드를 별도로 만든다.
    	String[] ret = null;
    	if(this.recptnTelno != null) {
    		ret = new String[recptnTelno.length];
    		for (int i=0; i<recptnTelno.length; i++) {
    			ret[i] = this.recptnTelno[i];
    		}
    	}
    	return ret;
    }

    /**
     * recptnTelno attribute 값을 설정한다.
     * @param recptnTelno the recptnTelno to set
     */
//    public void setRecptnTelno(String[] recptnTelno) {
//        this.recptnTelno = recptnTelno;
//    }
    // 2011.10.07 private 배열-유형 필드에 공용 데이터 할당되지 않도록 함
	public void setRecptnTelno(String[] recptnTelno) {
		this.recptnTelno = new String[recptnTelno.length];
		for (int i = 0; i < recptnTelno.length; ++i)
			this.recptnTelno[i] = recptnTelno[i];
	}

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
