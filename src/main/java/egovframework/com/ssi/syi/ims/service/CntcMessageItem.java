package egovframework.com.ssi.syi.ims.service;

import java.io.Serializable;

/**
 * 연계메시지항목 모델 클래스
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
public class CntcMessageItem implements Serializable {
	private static final long serialVersionUID = -7407577168160335040L;

	/*
	 * 연계메시지ID
	 */
	private String cntcMessageId     = "";

	/*
	 * 항목ID
	 */
	private String itemId            = "";

	/*
	 * 항목명
	 */
	private String itemNm            = "";

	/*
	 * 항목타입
	 */
	private String itemType          = "";

	/*
	 * 항목길이
	 */
	private int    itemLt            = 0 ;

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId    = "";

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId      = "";

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
	 * itemId attribute 를 리턴한다.
	 * @return String
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * itemId attribute 값을 설정한다.
	 * @param itemId String
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * itemNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getItemNm() {
		return itemNm;
	}

	/**
	 * itemNm attribute 값을 설정한다.
	 * @param itemNm String
	 */
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	/**
	 * itemType attribute 를 리턴한다.
	 * @return String
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * itemType attribute 값을 설정한다.
	 * @param itemType String
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * itemLt attribute 를 리턴한다.
	 * @return int
	 */
	public int getItemLt() {
		return itemLt;
	}

	/**
	 * itemLt attribute 값을 설정한다.
	 * @param itemLt int
	 */
	public void setItemLt(int itemLt) {
		this.itemLt = itemLt;
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
