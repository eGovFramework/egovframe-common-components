package egovframework.com.ssi.syi.iis.service;

import java.io.Serializable;

/**
 * 연계기관 모델 클래스
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
public class CntcInstt implements Serializable {

	private static final long serialVersionUID = -4176567860232641639L;

	/*
	 * 기관ID
	 */
	private String insttId        = "";

	/*
	 * 기관명
	 */
	private String insttNm        = "";

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId = "";

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId   = "";

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
	 * insttNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttNm() {
		return insttNm;
	}

	/**
	 * insttNm attribute 값을 설정한다.
	 * @param insttNm String
	 */
	public void setInsttNm(String insttNm) {
		this.insttNm = insttNm;
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
