package egovframework.com.ssi.syi.ims.service;

import java.io.Serializable;

/**
 * 연계메시지 모델 클래스
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
public class CntcMessage implements Serializable {

	private static final long serialVersionUID = 8864230247777324500L;

	/*
	 * 연계메시지ID
	 */
	private String cntcMessageId      = "";

	/*
	 * 연계메시지명
	 */
	private String cntcMessageNm      = "";

	/*
	 * 상위연계메시지ID
	 */
	private String upperCntcMessageId = "";

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId     = "";

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId       = "";

	/**
	 * cntcMessageId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCntcMessageId() {
		return cntcMessageId;
	}

	/**
	 * cntcMessageId attribute 값을 설정한다.
	 * @param cntcMessageId String
	 */
	public void setCntcMessageId(String cntcMessageId) {
		this.cntcMessageId = cntcMessageId;
	}

	/**
	 * cntcMessageNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCntcMessageNm() {
		return cntcMessageNm;
	}

	/**
	 * cntcMessageNm attribute 값을 설정한다.
	 * @param cntcMessageNm String
	 */
	public void setCntcMessageNm(String cntcMessageNm) {
		this.cntcMessageNm = cntcMessageNm;
	}

	/**
	 * upperCntcMessageId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpperCntcMessageId() {
		return upperCntcMessageId;
	}

	/**
	 * upperCntcMessageId attribute 값을 설정한다.
	 * @param upperCntcMessageId String
	 */
	public void setUpperCntcMessageId(String upperCntcMessageId) {
		this.upperCntcMessageId = upperCntcMessageId;
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
