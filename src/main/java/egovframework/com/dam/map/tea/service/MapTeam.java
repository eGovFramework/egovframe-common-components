package egovframework.com.dam.map.tea.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <pre>
 * 개요
 * - 지식맵(조직별)에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 조직분류, 등록일자, 지식URL 항목을 관리한다.
 * </pre>
 * 
 * @author 박종선
 * @version 1.0
 * @created 22-7-2010 오전 10:57:44
 * 
 * @테이블 COMTNDAMMAPTEAM 지식맵(조직별)
 */
public class MapTeam {

	/**
	 * 조직ID
	 */
	private String orgnztId;
	/**
	 * 조직분류
	 */
	private String orgnztNm;

	/**
	 * 분류일
	 * 
	 * @컬럼 `CL_DE` char(20) DEFAULT NULL COMMENT '분류일',
	 */
	private String clDe;

	/**
	 * 분류일자
	 */
	private String clYmd;
	/**
	 * 지식URL
	 */
	private String knoUrl;
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId = "";

	/**
	 * 최초등록시점
	 * 
	 * @컬럼 `FRST_REGIST_PNTTM` datetime DEFAULT NULL COMMENT '최초등록시점',
	 */
	private LocalDateTime frstRegistPnttm;

	/**
	 * 최초등록시점
	 * 
	 * @컬럼 `FRST_REGIST_PNTTM` datetime DEFAULT NULL COMMENT '최초등록시점',
	 */
	private String frstRegistPnttm2;

	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnt;

	public String getOrgnztId() {
		return orgnztId;
	}

	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	public String getOrgnztNm() {
		return orgnztNm;
	}

	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * 분류일 값읽기
	 * 
	 * @return
	 * 
	 * @컬럼 `CL_DE` char(20) DEFAULT NULL COMMENT '분류일',
	 */
	public String getClDe() {
		return clDe;
	}

	/**
	 * 분류일 값설정
	 * 
	 * @param clDe
	 * 
	 * @컬럼 `CL_DE` char(20) DEFAULT NULL COMMENT '분류일',
	 */
	public void setClDe(String clDe) {
		this.clDe = clDe;
	}

	public String getClYmd() {
		return clYmd;
	}

	public void setClYmd(String clYmd) {
		this.clYmd = clYmd;
	}

	public String getKnoUrl() {
		return knoUrl;
	}

	public void setKnoUrl(String knoUrl) {
		this.knoUrl = knoUrl;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * 최초등록시점 값읽기
	 * 
	 * @return
	 * 
	 * @컬럼 `FRST_REGIST_PNTTM` datetime DEFAULT NULL COMMENT '최초등록시점',
	 */
	public LocalDateTime getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * 최초등록시점 값설정
	 * 
	 * @param frstRegistPnttm
	 * 
	 * @컬럼 `FRST_REGIST_PNTTM` datetime DEFAULT NULL COMMENT '최초등록시점',
	 */
	public void setFrstRegistPnttm(LocalDateTime frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
		this.frstRegistPnttm2 = frstRegistPnttm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	}

	/**
	 * 최초등록시점 값읽기
	 * 
	 * @return
	 * 
	 * @컬럼 `FRST_REGIST_PNTTM` datetime DEFAULT NULL COMMENT '최초등록시점',
	 */
	public String getFrstRegistPnttm2() {
		return frstRegistPnttm2;
	}

	/**
	 * 최초등록시점 값설정
	 * 
	 * @param frstRegistPnttm2
	 * 
	 * @컬럼 `FRST_REGIST_PNTTM` datetime DEFAULT NULL COMMENT '최초등록시점',
	 */
	public void setFrstRegistPnttm2(String frstRegistPnttm2) {
		this.frstRegistPnttm2 = frstRegistPnttm2;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdusrPnt() {
		return lastUpdusrPnt;
	}

	public void setLastUpdusrPnt(String lastUpdusrPnt) {
		this.lastUpdusrPnt = lastUpdusrPnt;
	}

}