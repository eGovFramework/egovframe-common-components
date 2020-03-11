package egovframework.com.sym.ccm.icr.service;

import java.io.Serializable;

/**
 * 기관코드수신로그 모델 클래스
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
public class InsttCodeRecptn implements Serializable {

	private static final long serialVersionUID = 1370791089416059647L;

	/*
	 * 발생일자
	 */
	private String occrrDe = "" ;

	/*
	 * 기관코드
	 */
	private String insttCode = "" ;

    /**
     * 작업일련번호
     */
    int	opertSn = 0;

	/*
	 * 변경구분코드
	 */
	private String changeSeCode = "" ;

	/*
	 * 처리구분
	 */
	private String processSe = "" ;

	/*
	 * 기타코드
	 */
	private String etcCode = "" ;

	/*
	 * 전체기관명
	 */
	private String allInsttNm = "" ;

	/*
	 * 최하위기관명
	 */
	private String lowestInsttNm = "" ;

	/*
	 * 기관약칭명
	 */
	private String insttAbrvNm = "" ;

	/*
	 * 차수
	 */
	private String odr = "" ;

	/*
	 * 서열
	 */
	private String ord = "" ;

	/*
	 * 기관차수
	 */
	private String insttOdr = "" ;

	/*
	 * 최상위기관코드
	 */
	private String bestInsttCode = "" ;

	/*
	 * 상위기관코드
	 */
	private String upperInsttCode = "" ;

	/*
	 * 대표기관코드
	 */
	private String reprsntInsttCode = "" ;

	/*
	 * 기관유형대분류
	 */
	private String insttTyLclas = "" ;

	/*
	 * 기관유형중분류
	 */
	private String insttTyMclas = "" ;

	/*
	 * 기관유형소분류
	 */
	private String insttTySclas = "" ;

	/*
	 * 전화번호
	 */
	private String telno = "" ;

	/*
	 * 팩스번호
	 */
	private String fxnum = "" ;

	/*
	 * 생성일자
	 */
	private String creatDe = "" ;

	/*
	 * 폐지일자
	 */
	private String ablDe = "" ;

	/*
	 * 폐지유무
	 */
	private String ablEnnc = "" ;

	/*
	 * 변경일자
	 */
	private String changede = "" ;

	/*
	 * 변경시간
	 */
	private String changeTime = "" ;

	/*
	 * 기초일자
	 */
	private String bsisDe = "" ;

	/*
	 * 정렬순서
	 */
	private int sortOrdr = 0 ;

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId = "" ;

	/*
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
	 * insttCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttCode() {
		return insttCode;
	}

	/**
	 * insttCode attribute 값을 설정한다.
	 * @param insttCode String
	 */
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
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
	 * etcCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getEtcCode() {
		return etcCode;
	}

	/**
	 * etcCode attribute 값을 설정한다.
	 * @param etcCode String
	 */
	public void setEtcCode(String etcCode) {
		this.etcCode = etcCode;
	}

	/**
	 * allInsttNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getAllInsttNm() {
		return allInsttNm;
	}

	/**
	 * allInsttNm attribute 값을 설정한다.
	 * @param allInsttNm String
	 */
	public void setAllInsttNm(String allInsttNm) {
		this.allInsttNm = allInsttNm;
	}

	/**
	 * lowestInsttNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLowestInsttNm() {
		return lowestInsttNm;
	}

	/**
	 * lowestInsttNm attribute 값을 설정한다.
	 * @param lowestInsttNm String
	 */
	public void setLowestInsttNm(String lowestInsttNm) {
		this.lowestInsttNm = lowestInsttNm;
	}

	/**
	 * insttAbrvNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttAbrvNm() {
		return insttAbrvNm;
	}

	/**
	 * insttAbrvNm attribute 값을 설정한다.
	 * @param insttAbrvNm String
	 */
	public void setInsttAbrvNm(String insttAbrvNm) {
		this.insttAbrvNm = insttAbrvNm;
	}

	/**
	 * odr attribute 를 리턴한다.
	 * @return String
	 */
	public String getOdr() {
		return odr;
	}

	/**
	 * odr attribute 값을 설정한다.
	 * @param odr String
	 */
	public void setOdr(String odr) {
		this.odr = odr;
	}

	/**
	 * ord attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrd() {
		return ord;
	}

	/**
	 * ord attribute 값을 설정한다.
	 * @param ord String
	 */
	public void setOrd(String ord) {
		this.ord = ord;
	}

	/**
	 * insttOdr attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttOdr() {
		return insttOdr;
	}

	/**
	 * insttOdr attribute 값을 설정한다.
	 * @param insttOdr String
	 */
	public void setInsttOdr(String insttOdr) {
		this.insttOdr = insttOdr;
	}

	/**
	 * bestInsttCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getBestInsttCode() {
		return bestInsttCode;
	}

	/**
	 * bestInsttCode attribute 값을 설정한다.
	 * @param bestInsttCode String
	 */
	public void setBestInsttCode(String bestInsttCode) {
		this.bestInsttCode = bestInsttCode;
	}

	/**
	 * upperInsttCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpperInsttCode() {
		return upperInsttCode;
	}

	/**
	 * upperInsttCode attribute 값을 설정한다.
	 * @param upperInsttCode String
	 */
	public void setUpperInsttCode(String upperInsttCode) {
		this.upperInsttCode = upperInsttCode;
	}

	/**
	 * reprsntInsttCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getReprsntInsttCode() {
		return reprsntInsttCode;
	}

	/**
	 * reprsntInsttCode attribute 값을 설정한다.
	 * @param reprsntInsttCode String
	 */
	public void setReprsntInsttCode(String reprsntInsttCode) {
		this.reprsntInsttCode = reprsntInsttCode;
	}

	/**
	 * insttTyLclas attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttTyLclas() {
		return insttTyLclas;
	}

	/**
	 * insttTyLclas attribute 값을 설정한다.
	 * @param insttTyLclas String
	 */
	public void setInsttTyLclas(String insttTyLclas) {
		this.insttTyLclas = insttTyLclas;
	}

	/**
	 * insttTyMclas attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttTyMclas() {
		return insttTyMclas;
	}

	/**
	 * insttTyMclas attribute 값을 설정한다.
	 * @param insttTyMclas String
	 */
	public void setInsttTyMclas(String insttTyMclas) {
		this.insttTyMclas = insttTyMclas;
	}

	/**
	 * insttTySclas attribute 를 리턴한다.
	 * @return String
	 */
	public String getInsttTySclas() {
		return insttTySclas;
	}

	/**
	 * insttTySclas attribute 값을 설정한다.
	 * @param insttTySclas String
	 */
	public void setInsttTySclas(String insttTySclas) {
		this.insttTySclas = insttTySclas;
	}

	/**
	 * telno attribute 를 리턴한다.
	 * @return String
	 */
	public String getTelno() {
		return telno;
	}

	/**
	 * telno attribute 값을 설정한다.
	 * @param telno String
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}

	/**
	 * fxnum attribute 를 리턴한다.
	 * @return String
	 */
	public String getFxnum() {
		return fxnum;
	}

	/**
	 * fxnum attribute 값을 설정한다.
	 * @param fxnum String
	 */
	public void setFxnum(String fxnum) {
		this.fxnum = fxnum;
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
	 * changede attribute 를 리턴한다.
	 * @return String
	 */
	public String getChangede() {
		return changede;
	}

	/**
	 * changede attribute 값을 설정한다.
	 * @param changede String
	 */
	public void setChangede(String changede) {
		this.changede = changede;
	}

	/**
	 * changeTime attribute 를 리턴한다.
	 * @return String
	 */
	public String getChangeTime() {
		return changeTime;
	}

	/**
	 * changeTime attribute 값을 설정한다.
	 * @param changeTime String
	 */
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * bsisDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getBsisDe() {
		return bsisDe;
	}

	/**
	 * bsisDe attribute 값을 설정한다.
	 * @param bsisDe String
	 */
	public void setBsisDe(String bsisDe) {
		this.bsisDe = bsisDe;
	}

	/**
	 * sortOrdr attribute 를 리턴한다.
	 * @return int
	 */
	public int getSortOrdr() {
		return sortOrdr;
	}

	/**
	 * sortOrdr attribute 값을 설정한다.
	 * @param sortOrdr int
	 */
	public void setSortOrdr(int sortOrdr) {
		this.sortOrdr = sortOrdr;
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
