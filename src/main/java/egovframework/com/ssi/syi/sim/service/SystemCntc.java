package egovframework.com.ssi.syi.sim.service;

import java.io.Serializable;

/**
 * 시스템연계 모델 클래스
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
public class SystemCntc implements Serializable {

	private static final long serialVersionUID = 3756509144236517156L;

	/*
	 * 연계ID
	 */
	private String cntcId            = "";

	/*
	 * 연계명
	 */
	private String cntcNm            = "";

	/*
	 * 연계유형코드
	 */
	private String cntcType          = "";

	/*
	 * 제공기관ID
	 */
	private String provdInsttId      = "";

	/*
	 * 제공시스템ID
	 */
	private String provdSysId        = "";

	/*
	 * 제공서비스ID
	 */
	private String provdSvcId        = "";

	/*
	 * 요청기관ID
	 */
	private String requstInsttId     = "";

	/*
	 * 요청시스템ID
	 */
	private String requstSysId       = "";

	/*
	 * 승인여부
	 */
	private String confmAt           = "";

	/*
	 * 사용여부
	 */
	private String useAt             = "";

	/*
	 * 유효시작일자
	 */
	private String validBeginDe      = "";

	/*
	 * 유효종료일자
	 */
	private String validEndDe        = "";

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId    = "";

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId      = "";

	/**
	 * cntcId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCntcId() {
		return cntcId;
	}

	/**
	 * cntcId attribute 값을 설정한다.
	 * @param cntcId String
	 */
	public void setCntcId(String cntcId) {
		this.cntcId = cntcId;
	}

	/**
	 * cntcNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCntcNm() {
		return cntcNm;
	}

	/**
	 * cntcNm attribute 값을 설정한다.
	 * @param cntcNm String
	 */
	public void setCntcNm(String cntcNm) {
		this.cntcNm = cntcNm;
	}

	/**
	 * cntcType attribute 를 리턴한다.
	 * @return String
	 */
	public String getCntcType() {
		return cntcType;
	}

	/**
	 * cntcType attribute 값을 설정한다.
	 * @param cntcType String
	 */
	public void setCntcType(String cntcType) {
		this.cntcType = cntcType;
	}

	/**
	 * provdInsttId attribute 를 리턴한다.
	 * @return String
	 */
	public String getProvdInsttId() {
		return provdInsttId;
	}

	/**
	 * provdInsttId attribute 값을 설정한다.
	 * @param provdInsttId String
	 */
	public void setProvdInsttId(String provdInsttId) {
		this.provdInsttId = provdInsttId;
	}

	/**
	 * provdSysId attribute 를 리턴한다.
	 * @return String
	 */
	public String getProvdSysId() {
		return provdSysId;
	}

	/**
	 * provdSysId attribute 값을 설정한다.
	 * @param provdSysId String
	 */
	public void setProvdSysId(String provdSysId) {
		this.provdSysId = provdSysId;
	}

	/**
	 * provdSvcId attribute 를 리턴한다.
	 * @return String
	 */
	public String getProvdSvcId() {
		return provdSvcId;
	}

	/**
	 * provdSvcId attribute 값을 설정한다.
	 * @param provdSvcId String
	 */
	public void setProvdSvcId(String provdSvcId) {
		this.provdSvcId = provdSvcId;
	}

	/**
	 * requstInsttId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRequstInsttId() {
		return requstInsttId;
	}

	/**
	 * requstInsttId attribute 값을 설정한다.
	 * @param requstInsttId String
	 */
	public void setRequstInsttId(String requstInsttId) {
		this.requstInsttId = requstInsttId;
	}

	/**
	 * requstSysId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRequstSysId() {
		return requstSysId;
	}

	/**
	 * requstSysId attribute 값을 설정한다.
	 * @param requstSysId String
	 */
	public void setRequstSysId(String requstSysId) {
		this.requstSysId = requstSysId;
	}

	/**
	 * confmAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getConfmAt() {
		return confmAt;
	}

	/**
	 * confmAt attribute 값을 설정한다.
	 * @param confmAt String
	 */
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * useAt attribute 값을 설정한다.
	 * @param useAt String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * validBeginDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getValidBeginDe() {
		return validBeginDe;
	}

	/**
	 * validBeginDe attribute 값을 설정한다.
	 * @param validBeginDe String
	 */
	public void setValidBeginDe(String validBeginDe) {
		this.validBeginDe = validBeginDe;
	}

	/**
	 * validEndDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getValidEndDe() {
		return validEndDe;
	}

	/**
	 * validEndDe attribute 값을 설정한다.
	 * @param validEndDe String
	 */
	public void setValidEndDe(String validEndDe) {
		this.validEndDe = validEndDe;
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
