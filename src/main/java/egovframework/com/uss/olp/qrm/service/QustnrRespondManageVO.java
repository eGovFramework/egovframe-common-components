package egovframework.com.uss.olp.qrm.service;

import java.io.Serializable;
/**
 * 설문응답자관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
public class QustnrRespondManageVO implements Serializable {

	private static final long serialVersionUID = -4378392561239344699L;

	/** 설문지ID */
	private String qestnrId = "";

	/** 설문응답자아이디 */
	private String qestnrRespondId = "";

	/** 설별코드 */
	private String sexdstnCode = "";

	/** 직업유형코드 */
	private String occpTyCode = "";

	/** 응답자명 */
	private String respondNm = "";

	/** 생년월일 */
	private String brth = "";

	/** 첫번째전화번호 */
	private String areaNo = "";

	/** 두번째전화번호 */
	private String middleTelno = "";

	/** 마지막전화번호 */
	private String endTelno = "";

	/** 최초등록시점 */
	private String frstRegisterPnttm = "";

	/** 최초등록자ID */
	private String frstRegisterId = "";

	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";

	/** 최종수정ID */
	private String lastUpdusrId = "";

	/** 설문템플릿ID */
	private String qestnrTmplatId = "";

	/**
	 * qestnrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQestnrId() {
		return qestnrId;
	}

	/**
	 * qestnrId attribute 값을 설정한다.
	 * @return qestnrId String
	 */
	public void setQestnrId(String qestnrId) {
		this.qestnrId = qestnrId;
	}

	/**
	 * qestnrRespondId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQestnrRespondId() {
		return qestnrRespondId;
	}

	/**
	 * qestnrRespondId attribute 값을 설정한다.
	 * @return qestnrRespondId String
	 */
	public void setQestnrRespondId(String qestnrRespondId) {
		this.qestnrRespondId = qestnrRespondId;
	}

	/**
	 * sexdstnCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSexdstnCode() {
		return sexdstnCode;
	}

	/**
	 * sexdstnCode attribute 값을 설정한다.
	 * @return sexdstnCode String
	 */
	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}

	/**
	 * occpTyCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getOccpTyCode() {
		return occpTyCode;
	}

	/**
	 * occpTyCode attribute 값을 설정한다.
	 * @return occpTyCode String
	 */
	public void setOccpTyCode(String occpTyCode) {
		this.occpTyCode = occpTyCode;
	}

	/**
	 * respondNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getRespondNm() {
		return respondNm;
	}

	/**
	 * respondNm attribute 값을 설정한다.
	 * @return respondNm String
	 */
	public void setRespondNm(String respondNm) {
		this.respondNm = respondNm;
	}

	/**
	 * brth attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBrth() {
		return brth;
	}

	/**
	 * brth attribute 값을 설정한다.
	 * @return brth String
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}

	/**
	 * areaNo attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * areaNo attribute 값을 설정한다.
	 * @return areaNo String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * middleTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}

	/**
	 * middleTelno attribute 값을 설정한다.
	 * @return middleTelno String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEndTelno() {
		return endTelno;
	}

	/**
	 * endTelno attribute 값을 설정한다.
	 * @return endTelno String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
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

	/**
	 * qestnrTmplatId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getQestnrTmplatId() {
		return qestnrTmplatId;
	}

	/**
	 * qestnrTmplatId attribute 값을 설정한다.
	 * @return qestnrTmplatId String
	 */
	public void setQestnrTmplatId(String qestnrTmplatId) {
		this.qestnrTmplatId = qestnrTmplatId;
	}



}
