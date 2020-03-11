/**
 * 개요
 * - 인터넷서비스안내에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 인터넷서비스안내의 일련번호, 인터넷서비스명, 인터넷서비스설명, 반영여부 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:51
 */

package egovframework.com.uss.ion.isg.service;

import egovframework.com.cmm.ComDefaultVO;

public class IntnetSvcGuidance extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 인터넷서비스ID
	 */	
	private String intnetSvcId;
	/**
	 * 인터넷서비스명
	 */		
	private String intnetSvcNm;
	/**
	 * 인터넷서비스설명
	 */	
	private String intnetSvcDc;
	/**
	 * 반영여부
	 */		
	private String reflctAt;
	/**
	 * 사용자 ID
	 */
	private String userId;
	/**
	 * 등록일자
	 */
	private String regDate;
	
	/**
	 * @return the intnetSvcId
	 */
	public String getIntnetSvcId() {
		return intnetSvcId;
	}
	/**
	 * @param intnetSvcId the intnetSvcId to set
	 */
	public void setIntnetSvcId(String intnetSvcId) {
		this.intnetSvcId = intnetSvcId;
	}
	/**
	 * @return the intnetSvcNm
	 */
	public String getIntnetSvcNm() {
		return intnetSvcNm;
	}
	/**
	 * @param intnetSvcNm the intnetSvcNm to set
	 */
	public void setIntnetSvcNm(String intnetSvcNm) {
		this.intnetSvcNm = intnetSvcNm;
	}
	/**
	 * @return the intnetSvcDc
	 */
	public String getIntnetSvcDc() {
		return intnetSvcDc;
	}
	/**
	 * @param intnetSvcDc the intnetSvcDc to set
	 */
	public void setIntnetSvcDc(String intnetSvcDc) {
		this.intnetSvcDc = intnetSvcDc;
	}
	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
