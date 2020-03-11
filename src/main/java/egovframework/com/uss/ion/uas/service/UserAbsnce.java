/**
 * 개요
 * - 사용자부재에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 사용자부재의 사용자ID, 사용자부재여부 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:09:34
 */

package egovframework.com.uss.ion.uas.service;

import egovframework.com.cmm.ComDefaultVO;

public class UserAbsnce extends ComDefaultVO {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 사용자명
	 */
	private String userNm;	
	/**
	 * 사용자부재여부
	 */
	private String userAbsnceAt;
	/**
	 * 최종등록자ID
	 */
	private String lastUpdusrId;
	/**
	 * 최종등록시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * 등록여부
	 */
	private String regYn;	

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
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the userAbsnceAt
	 */
	public String getUserAbsnceAt() {
		return userAbsnceAt;
	}
	/**
	 * @param userAbsnceAt the userAbsnceAt to set
	 */
	public void setUserAbsnceAt(String userAbsnceAt) {
		this.userAbsnceAt = userAbsnceAt;
	}
	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * @return the regYn
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * @param regYn the regYn to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
}
