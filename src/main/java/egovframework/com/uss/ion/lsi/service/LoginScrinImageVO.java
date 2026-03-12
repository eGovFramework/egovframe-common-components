package egovframework.com.uss.ion.lsi.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

/**
 * <pre>
 * 개요
 * - 로그인화면이미지에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인화면이미지의 목록 항목을 관리한다.
 * - 로그인화면이미지의 일련번호, 이미지명, 링크URL, 이미지설명, 반영여부 항목을 관리한다.
 * </pre>
 * 
 * @author 이문준
 * @since 2010.08.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.03  이문준          최초 생성
 *   2025.08.07  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.08.07  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *
 *      </pre>
 */
public class LoginScrinImageVO extends ComDefaultVO {

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
	@EgovNullCheck
	@Size(max=10)
	private String imageNm;
	
	/**
	 * 로그인 이미지
	 */	
	@EgovNullCheck
	private String image;
	
	/**
	 * 로그인 이미지 파일
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
	 * 로그인화면이미지 목록
	 */
	private List<LoginScrinImageVO> loginScrinImageList;

	/**
	 * 삭제대상 목록
	 */
	@Getter
	@Setter
	private String[] delYn;

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

	/**
	 * @return the loginScrinImageList
	 */
	public List<LoginScrinImageVO> getLoginScrinImageList() {
		return loginScrinImageList;
	}

	/**
	 * @param loginScrinImageList the loginScrinImageList to set
	 */
	public void setLoginScrinImageList(List<LoginScrinImageVO> loginScrinImageList) {
		this.loginScrinImageList = loginScrinImageList;
	}

}
