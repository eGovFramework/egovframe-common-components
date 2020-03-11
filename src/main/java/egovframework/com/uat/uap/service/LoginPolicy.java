/**
 * 개요
 * - 로그인정책에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인정책정보의 사용자ID, IP정보, 중복허용여부, 제한여부 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:53
 *   <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2009.8.3    이문준     최초 생성
 * </pre>
 */

package egovframework.com.uat.uap.service;

import egovframework.com.cmm.ComDefaultVO;

public class LoginPolicy extends ComDefaultVO {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
    /**
	 * 사용자 ID
	 */	
	private String emplyrId;
    /**
	 * 사용자 명
	 */	
	private String emplyrNm;	
    /**
	 * 사용자 구분
	 */	
	private String emplyrSe;		
    /**
	 * IP정보
	 */	
    private String ipInfo;
    /**
	 * 중복허용여부
	 */	
    private String dplctPermAt;
    /**
	 * 제한여부
	 */	
    private String lmttAt;
    /**
	 * 등록자 ID
	 */	
    private String userId;
    /**
	 * 등록일시
	 */	
    private String regDate;
    /**
	 * 등록여부
	 */	
    private String regYn;
    
	/**
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * @param emplyrId the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}
	/**
	 * @return the emplyrNm
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * @param emplyrNm the emplyrNm to set
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}
	/**
	 * @return the emplyrSe
	 */
	public String getEmplyrSe() {
		return emplyrSe;
	}
	/**
	 * @param emplyrSe the emplyrSe to set
	 */
	public void setEmplyrSe(String emplyrSe) {
		this.emplyrSe = emplyrSe;
	}
	/**
	 * @return the ipInfo
	 */
	public String getIpInfo() {
		return ipInfo;
	}
	/**
	 * @param ipInfo the ipInfo to set
	 */
	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}
	/**
	 * @return the dplctPermAt
	 */
	public String getDplctPermAt() {
		return dplctPermAt;
	}
	/**
	 * @param dplctPermAt the dplctPermAt to set
	 */
	public void setDplctPermAt(String dplctPermAt) {
		this.dplctPermAt = dplctPermAt;
	}
	/**
	 * @return the lmttAt
	 */
	public String getLmttAt() {
		return lmttAt;
	}
	/**
	 * @param lmttAt the lmttAt to set
	 */
	public void setLmttAt(String lmttAt) {
		this.lmttAt = lmttAt;
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
