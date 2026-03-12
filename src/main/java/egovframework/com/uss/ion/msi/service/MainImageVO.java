package egovframework.com.uss.ion.msi.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 메인화면이미지에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지의 일련번호, 이미지명, 링크URL, 이미지설명, 반영여부 및 목록 항목을 관리한다.
 *
 * @author 이문준
 * @since 2010.08.03
 * @version 1.0
 */
public class MainImageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 이미지 ID */
	private String imageId;

	/** 이미지명 */
	@EgovNullCheck
	@Size(max = 10)
	private String imageNm;

	/** 메인 이미지 */
	@NotBlank(message = "{uss.ion.msi.mainImageRegist.ImageReq}")
	private String image;

	/** 메인 이미지 파일 */
	private String imageFile;

	/** 이미지 설명 */
	private String imageDc;

	/** 반영여부 */
	private String reflctAt;

	/** 사용자 ID */
	private String userId;

	/** 등록일자 */
	private String regDate;

	/** 파일첨부여부 */
	private boolean isAtchFile;

	/** 메인화면이미지 목록 */
	private List<MainImageVO> mainImageList;

	/** 삭제대상 목록 */
	@Getter
	@Setter
	private String[] delYn;

	public String getImageId() { return imageId; }
	public void setImageId(String imageId) { this.imageId = imageId; }
	public String getImageNm() { return imageNm; }
	public void setImageNm(String imageNm) { this.imageNm = imageNm; }
	public String getImage() { return image; }
	public void setImage(String image) { this.image = image; }
	public String getImageFile() { return imageFile; }
	public void setImageFile(String imageFile) { this.imageFile = imageFile; }
	public String getImageDc() { return imageDc; }
	public void setImageDc(String imageDc) { this.imageDc = imageDc; }
	public String getReflctAt() { return reflctAt; }
	public void setReflctAt(String reflctAt) { this.reflctAt = reflctAt; }
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }
	public String getRegDate() { return regDate; }
	public void setRegDate(String regDate) { this.regDate = regDate; }
	public boolean isAtchFile() { return isAtchFile; }
	public void setAtchFile(boolean atchFile) { this.isAtchFile = atchFile; }
	public List<MainImageVO> getMainImageList() { return mainImageList; }
	public void setMainImageList(List<MainImageVO> mainImageList) { this.mainImageList = mainImageList; }
}
