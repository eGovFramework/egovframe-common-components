package egovframework.com.uss.ion.tir.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 트위터수신 Model and VO Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 * 
 * </pre>
 */
@SuppressWarnings("serial")
public class TwitterInfo extends ComDefaultVO implements Serializable{
 
	/** 트위터 아이디 */
	private String twitterId;
	
	/** 트위터 비밀번호 */
	private String twitterPw;
	
	/** 트위터 이름 */
	private String twitterNmae;
	
	/** 트위터 스크린명 */
	private String twitterScreenName;
	
	/** 트위터 URL */
	private String twitterURL;
	
	/** 트위터 텍스트 */
	private String twitterText;
	
	/** 트위터 등록일 */
	private String twitterCreatedAt;
	
	/** 트위터 프로파일 이미지명 */
	private String twitterProfileImageURL;
	
	/** 트위터 소스 */
	 private String twitterSource;

	/**
	 * @return the twitterId
	 */
	public String getTwitterId() {
		return twitterId;
	}

	/**
	 * @param twitterId the twitterId to set
	 */
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	/**
	 * @return the twitterPw
	 */
	public String getTwitterPw() {
		return twitterPw;
	}

	/**
	 * @param twitterPw the twitterPw to set
	 */
	public void setTwitterPw(String twitterPw) {
		this.twitterPw = twitterPw;
	}

	/**
	 * @return the twitterNmae
	 */
	public String getTwitterNmae() {
		return twitterNmae;
	}

	/**
	 * @param twitterNmae the twitterNmae to set
	 */
	public void setTwitterNmae(String twitterNmae) {
		this.twitterNmae = twitterNmae;
	}

	/**
	 * @return the twitterScreenName
	 */
	public String getTwitterScreenName() {
		return twitterScreenName;
	}

	/**
	 * @param twitterScreenName the twitterScreenName to set
	 */
	public void setTwitterScreenName(String twitterScreenName) {
		this.twitterScreenName = twitterScreenName;
	}

	/**
	 * @return the twitterURL
	 */
	public String getTwitterURL() {
		return twitterURL;
	}

	/**
	 * @param twitterURL the twitterURL to set
	 */
	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}

	/**
	 * @return the twitterText
	 */
	public String getTwitterText() {
		return twitterText;
	}

	/**
	 * @param twitterText the twitterText to set
	 */
	public void setTwitterText(String twitterText) {
		this.twitterText = twitterText;
	}

	/**
	 * @return the twitterCreatedAt
	 */
	public String getTwitterCreatedAt() {
		return twitterCreatedAt;
	}

	/**
	 * @param twitterCreatedAt the twitterCreatedAt to set
	 */
	public void setTwitterCreatedAt(String twitterCreatedAt) {
		this.twitterCreatedAt = twitterCreatedAt;
	}

	/**
	 * @return the twitterProfileImageURL
	 */
	public String getTwitterProfileImageURL() {
		return twitterProfileImageURL;
	}

	/**
	 * @param twitterProfileImageURL the twitterProfileImageURL to set
	 */
	public void setTwitterProfileImageURL(String twitterProfileImageURL) {
		this.twitterProfileImageURL = twitterProfileImageURL;
	}

	/**
	 * @return the twitterSource
	 */
	public String getTwitterSource() {
		return twitterSource;
	}

	/**
	 * @param twitterSource the twitterSource to set
	 */
	public void setTwitterSource(String twitterSource) {
		this.twitterSource = twitterSource;
	}
	 

	 
}
