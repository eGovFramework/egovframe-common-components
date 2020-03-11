package egovframework.com.uss.ion.ism.service;

import java.io.Serializable;

/**
 * 개요
 * - 결재자에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 조직명, 직위명, 사용자명, 사용자ID 의 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 */
@SuppressWarnings("serial")
public class Sanctner implements Serializable  {

	/**
	 * 조직명
	 */
	private String orgnztNm;
	/**
	 * 직위명
	 */
	private String ofcpsNm;
	/**
	 * 사용자명
	 */
	private String emplyrNm;
	/**
	 * 사용자ID
	 */
	private String uniqId;
	/**
	 * 사원번호
	 */
	private String emplNo;
	
	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	public String getOfcpsNm() {
		return ofcpsNm;
	}
	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}
	public String getEmplyrNm() {
		return emplyrNm;
	}
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}
	public String getUniqId() {
		return uniqId;
	}
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	public String getEmplNo() {
		return emplNo;
	}
	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}

	
}