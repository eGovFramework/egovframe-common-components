package egovframework.com.uss.olh.wor.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * 용어사전정보 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public class WordDicaryVO extends WordDicaryDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 용어ID */
	private String wordId;

	/** 용어명 */
	private String wordNm;

	/** 영문명 */
	private String engNm;

	/** 용어설명 */
	private String wordDc;

	/** 동의어 */
	private String synonm;

	/** 등록자명 */
	private String emplyrNm;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최종수정시점 */
	private String lastUpdusrPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/**
	 * wordId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getWordId() {
		return wordId;
	}

	/**
	 * wordId attribute 값을 설정한다.
	 * @return wordId String
	 */
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

	/**
	 * wordNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getWordNm() {
		return wordNm;
	}

	/**
	 * wordNm attribute 값을 설정한다.
	 * @return wordNm String
	 */
	public void setWordNm(String wordNm) {
		this.wordNm = wordNm;
	}

	/**
	 * engNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEngNm() {
		return engNm;
	}

	/**
	 * engNm attribute 값을 설정한다.
	 * @return engNm String
	 */
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}

	/**
	 * wordDc attribute 를 리턴한다.
	 * @return the String
	 */
	public String getWordDc() {
		return wordDc;
	}

	/**
	 * wordDc attribute 값을 설정한다.
	 * @return wordDc String
	 */
	public void setWordDc(String wordDc) {
		this.wordDc = wordDc;
	}

	/**
	 * synonm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSynonm() {
		return synonm;
	}

	/**
	 * synonm attribute 값을 설정한다.
	 * @return synonm String
	 */
	public void setSynonm(String synonm) {
		this.synonm = synonm;
	}

	/**
	 * emplyrNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}

	/**
	 * emplyrNm attribute 값을 설정한다.
	 * @return emplyrNm String
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
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
	 * toString 메소드를 대치한다.
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
