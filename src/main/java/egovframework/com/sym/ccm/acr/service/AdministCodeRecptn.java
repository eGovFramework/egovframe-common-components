package egovframework.com.sym.ccm.acr.service;

import java.io.Serializable;

/**
 * 법정동코드수신로그 모델 클래스
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
public class AdministCodeRecptn implements Serializable {

	private static final long serialVersionUID = -8112169445756353554L;

	/**
     * 발생일자
     */
    private String  occrrDe = "";

    /**
     * 행정구역구분
     */
    private String  administZoneSe = "";

    /**
     * 행정구역코드
     */
    private String  administZoneCode = "";

    /**
     * 작업일련번호
     */
    int	opertSn = 0;

    /**
     * 변경구분코드
     */
    private String  changeSeCode = "";

    /**
     * 처리구분
     */
    private String  processSe = "";

    /**
     * 행정구역명
     */
    private String  administZoneNm = "";

    /**
     * 최하위행정구역명
     */
    private String  lowestAdministZoneNm = "";

    /**
     * 시도코드
     */
    private String  ctprvnCode = "";

    /**
     * 시군구코드
     */
    private String  signguCode = "";

    /**
     * 읍면동코드
     */
    private String  emdCode = "";

    /**
     * 리코드
     */
    private String  liCode = "";

    /**
     * 생성일자
     */
    private String  creatDe = "";

    /**
     * 폐지일자
     */
    private String  ablDe = "";

    /**
     * 폐지유무
     */
    private String  ablEnnc = "";

	/*
	 * 상위행정구역코드
	 */
    private String upperAdministZoneCode = "";

	/*
	 * 상위행정구역명
	 */
    private String upperAdministZoneNm = "";

    /*
	 * 사용여부
	 */
    private String useAt = "";

    /**
     * 최초등록자ID
     */
    private String frstRegisterId = "" ;

    /**
     * 최종수정자ID
     */
    private String lastUpdusrId = "" ;

	/**
	 * occrrDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getOccrrDe() {
		return occrrDe;
	}

	/**
	 * occrrDe attribute 값을 설정한다.
	 * @param occrrDe String
	 */
	public void setOccrrDe(String occrrDe) {
		this.occrrDe = occrrDe;
	}

	/**
	 * administZoneSe attribute 를 리턴한다.
	 * @return String
	 */
	public String getAdministZoneSe() {
		return administZoneSe;
	}

	/**
	 * administZoneSe attribute 값을 설정한다.
	 * @param administZoneSe String
	 */
	public void setAdministZoneSe(String administZoneSe) {
		this.administZoneSe = administZoneSe;
	}

	/**
	 * administZoneCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAdministZoneCode() {
		return administZoneCode;
	}

	/**
	 * administZoneCode attribute 값을 설정한다.
	 * @param administZoneCode String
	 */
	public void setAdministZoneCode(String administZoneCode) {
		this.administZoneCode = administZoneCode;
	}

	/**
	 * opertSn attribute 를 리턴한다.
	 * @return int
	 */
	public int getOpertSn() {
		return opertSn;
	}

	/**
	 * opertSn attribute 값을 설정한다.
	 * @param opertSn int
	 */
	public void setOpertSn(int opertSn) {
		this.opertSn = opertSn;
	}

	/**
	 * changeSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getChangeSeCode() {
		return changeSeCode;
	}

	/**
	 * changeSeCode attribute 값을 설정한다.
	 * @param changeSeCode String
	 */
	public void setChangeSeCode(String changeSeCode) {
		this.changeSeCode = changeSeCode;
	}

	/**
	 * processSe attribute 를 리턴한다.
	 * @return String
	 */
	public String getProcessSe() {
		return processSe;
	}

	/**
	 * processSe attribute 값을 설정한다.
	 * @param processSe String
	 */
	public void setProcessSe(String processSe) {
		this.processSe = processSe;
	}

	/**
	 * administZoneNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getAdministZoneNm() {
		return administZoneNm;
	}

	/**
	 * administZoneNm attribute 값을 설정한다.
	 * @param administZoneNm String
	 */
	public void setAdministZoneNm(String administZoneNm) {
		this.administZoneNm = administZoneNm;
	}

	/**
	 * lowestAdministZoneNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLowestAdministZoneNm() {
		return lowestAdministZoneNm;
	}

	/**
	 * lowestAdministZoneNm attribute 값을 설정한다.
	 * @param lowestAdministZoneNm String
	 */
	public void setLowestAdministZoneNm(String lowestAdministZoneNm) {
		this.lowestAdministZoneNm = lowestAdministZoneNm;
	}

	/**
	 * ctprvnCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getCtprvnCode() {
		return ctprvnCode;
	}

	/**
	 * ctprvnCode attribute 값을 설정한다.
	 * @param ctprvnCode String
	 */
	public void setCtprvnCode(String ctprvnCode) {
		this.ctprvnCode = ctprvnCode;
	}

	/**
	 * signguCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getSignguCode() {
		return signguCode;
	}

	/**
	 * signguCode attribute 값을 설정한다.
	 * @param signguCode String
	 */
	public void setSignguCode(String signguCode) {
		this.signguCode = signguCode;
	}

	/**
	 * emdCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmdCode() {
		return emdCode;
	}

	/**
	 * emdCode attribute 값을 설정한다.
	 * @param emdCode String
	 */
	public void setEmdCode(String emdCode) {
		this.emdCode = emdCode;
	}

	/**
	 * liCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getLiCode() {
		return liCode;
	}

	/**
	 * liCode attribute 값을 설정한다.
	 * @param liCode String
	 */
	public void setLiCode(String liCode) {
		this.liCode = liCode;
	}

	/**
	 * creatDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getCreatDe() {
		return creatDe;
	}

	/**
	 * creatDe attribute 값을 설정한다.
	 * @param creatDe String
	 */
	public void setCreatDe(String creatDe) {
		this.creatDe = creatDe;
	}

	/**
	 * ablDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getAblDe() {
		return ablDe;
	}

	/**
	 * ablDe attribute 값을 설정한다.
	 * @param ablDe String
	 */
	public void setAblDe(String ablDe) {
		this.ablDe = ablDe;
	}

	/**
	 * ablEnnc attribute 를 리턴한다.
	 * @return String
	 */
	public String getAblEnnc() {
		return ablEnnc;
	}

	/**
	 * ablEnnc attribute 값을 설정한다.
	 * @param ablEnnc String
	 */
	public void setAblEnnc(String ablEnnc) {
		this.ablEnnc = ablEnnc;
	}

	/**
	 * upperAdministZoneCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpperAdministZoneCode() {
		return upperAdministZoneCode;
	}

	/**
	 * upperAdministZoneCode attribute 값을 설정한다.
	 * @param upperAdministZoneCode String
	 */
	public void setUpperAdministZoneCode(String upperAdministZoneCode) {
		this.upperAdministZoneCode = upperAdministZoneCode;
	}

	/**
	 * upperAdministZoneNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpperAdministZoneNm() {
		return upperAdministZoneNm;
	}

	/**
	 * upperAdministZoneNm attribute 값을 설정한다.
	 * @param upperAdministZoneNm String
	 */
	public void setUpperAdministZoneNm(String upperAdministZoneNm) {
		this.upperAdministZoneNm = upperAdministZoneNm;
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
