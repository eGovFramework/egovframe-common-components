package egovframework.com.ssi.syi.iis.service;

import java.io.Serializable;

/**
 * 연계서비스 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class CntcService implements Serializable {

	private static final long serialVersionUID = 5832384226958481369L;

	/*
	 * 기관ID
	 */
	private String insttId          = "";

	/*
	 * 시스템ID
	 */
	private String sysId            = "";

	/*
	 * 서비스ID
	 */
	private String svcId            = "";

	/*
	 * 서비스명
	 */
	private String svcNm            = "";

	/*
	 * 요청메시지ID
	 */
	private String requestMessageId = "";

	/*
	 * 응답메시지ID
	 */
	private String rspnsMessageId   = "";

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId   = "";

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId     = "";

	/**
	 * insttId attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttId() {
		return insttId;
	}

	/**
	 * insttId attribute 값을 설정한다.
	 * @param insttId String
	 */
	public void setInsttId(String insttId) {
		this.insttId = insttId;
	}

	/**
	 * sysId attribute 를 리턴한다.
	 * @return String
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * sysId attribute 값을 설정한다.
	 * @param sysId String
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * svcId attribute 를 리턴한다.
	 * @return String
	 */
	public String getSvcId() {
		return svcId;
	}

	/**
	 * svcId attribute 값을 설정한다.
	 * @param svcId String
	 */
	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}

	/**
	 * svcNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getSvcNm() {
		return svcNm;
	}

	/**
	 * svcNm attribute 값을 설정한다.
	 * @param svcNm String
	 */
	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}

	/**
	 * requestMessageId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRequestMessageId() {
		return requestMessageId;
	}

	/**
	 * requestMessageId attribute 값을 설정한다.
	 * @param requestMessageId String
	 */
	public void setRequestMessageId(String requestMessageId) {
		this.requestMessageId = requestMessageId;
	}

	/**
	 * rspnsMessageId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRspnsMessageId() {
		return rspnsMessageId;
	}

	/**
	 * rspnsMessageId attribute 값을 설정한다.
	 * @param rspnsMessageId String
	 */
	public void setRspnsMessageId(String rspnsMessageId) {
		this.rspnsMessageId = rspnsMessageId;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

}
