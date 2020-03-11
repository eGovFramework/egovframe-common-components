/**
 * 개요
 * - 메인화면이미지에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지의 일련번호, 이미지명, 링크URL, 이미지설명, 반영여부 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:57
 */

package egovframework.com.uss.ion.msi.service;

import egovframework.com.cmm.ComDefaultVO;

public class MainImage extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 이미지 ID
	 */
	private String imageId;
	/**
	 * 이미지명
	 */	
	private String imageNm;
	/**
	 * 메인 이미지
	 */	
	private String image;
	/**
	 * 메인 이미지 파일
	 */	
	private String imageFile;
	/**
	 * 이미지 설명
	 */	
	private String imageDc;
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
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	/**
	 * @return the imageNm
	 */
	public String getImageNm() {
		return imageNm;
	}
	/**
	 * @param imageNm the imageNm to set
	 */
	public void setImageNm(String imageNm) {
		this.imageNm = imageNm;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the imageFile
	 */
	public String getImageFile() {
		return imageFile;
	}
	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	/**
	 * @return the imageDc
	 */
	public String getImageDc() {
		return imageDc;
	}
	/**
	 * @param imageDc the imageDc to set
	 */
	public void setImageDc(String imageDc) {
		this.imageDc = imageDc;
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
