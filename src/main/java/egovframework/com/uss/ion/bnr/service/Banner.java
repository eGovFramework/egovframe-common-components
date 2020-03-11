/**
 * 개요
 * - 배너에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 배너의 일련번호, 배너명, 링크URL, 배너설명, 반영여부 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:10
 */

package egovframework.com.uss.ion.bnr.service;

import egovframework.com.cmm.ComDefaultVO;

public class Banner extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 배너 ID
	 */
	private String bannerId;
	/**
	 * 배너 명
	 */
	private String bannerNm;
	/**
	 * 링크 URL
	 */
	private String linkUrl;
	/**
	 * 배너 이미지
	 */
	private String bannerImage;
	/**
	 * 배너 이미지 파일
	 */
	private String bannerImageFile;	
	/**
	 * 배너 설명
	 */
	private String bannerDc;
	/**
	 * 정렬 순서
	 */
	private String sortOrdr;
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
	 * 파일첨부여부
	 */
	private boolean isAtchFile;
	
	/**
	 * @return the bannerId
	 */
	public String getBannerId() {
		return bannerId;
	}
	/**
	 * @param bannerId the bannerId to set
	 */
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	/**
	 * @return the bannerNm
	 */
	public String getBannerNm() {
		return bannerNm;
	}
	/**
	 * @param bannerNm the bannerNm to set
	 */
	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
	}
	/**
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * @return the bannerImage
	 */
	public String getBannerImage() {
		return bannerImage;
	}
	/**
	 * @param bannerImage the bannerImage to set
	 */
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	/**
	 * @return the bannerImageFile
	 */
	public String getBannerImageFile() {
		return bannerImageFile;
	}
	/**
	 * @param bannerImageFile the bannerImageFile to set
	 */
	public void setBannerImageFile(String bannerImageFile) {
		this.bannerImageFile = bannerImageFile;
	}
	/**
	 * @return the bannerDc
	 */
	public String getBannerDc() {
		return bannerDc;
	}
	/**
	 * @param bannerDc the bannerDc to set
	 */
	public void setBannerDc(String bannerDc) {
		this.bannerDc = bannerDc;
	}
	/**
	 * @return the sortOrdr
	 */
	public String getSortOrdr() {
		return sortOrdr;
	}
	/**
	 * @param sortOrdr the sortOrdr to set
	 */
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
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
	/**
	 * @return the isAtchFile
	 */
	public boolean isAtchFile() {
		return isAtchFile;
	}
	/**
	 * @param isAtchFile the isAtchFile to set
	 */
	public void setAtchFile(boolean isAtchFile) {
		this.isAtchFile = isAtchFile;
	}

}
