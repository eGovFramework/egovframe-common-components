package egovframework.com.uss.ion.bnr.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * <pre>
 * 개요
 * - 배너에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 배너의 일련번호, 배너명, 링크URL, 배너설명, 반영여부 및 목록 항목을 관리한다.
 * </pre>
 *
 * @author 이문준
 * @since 2009.08.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.03  이문준          최초 생성
 *   2025.08.04  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.08.04  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *
 *      </pre>
 */
public class BannerVO extends ComDefaultVO {

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
	@EgovNullCheck
	@Size(max = 30)
	private String bannerNm;
	/**
	 * 링크 URL
	 */
	@EgovNullCheck
	private String linkUrl;
	/**
	 * 배너 이미지 (필수)
	 */
	@NotBlank(message = "{ussIonBnr.bannerRegist.ImageReq}")
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
	@EgovNullCheck
	@Size(max = 100)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
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
	 * 배너 목록
	 */
	private List<BannerVO> bannerList;

	/**
	 * 삭제대상 목록
	 */
	@Getter
	@Setter
	private String[] delYn;

	/**
	 * 결과 반영 타입 vertical : 세로 horizontal : 가로
	 */
	private String resultType = "horizontal";

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerNm() {
		return bannerNm;
	}

	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getBannerImageFile() {
		return bannerImageFile;
	}

	public void setBannerImageFile(String bannerImageFile) {
		this.bannerImageFile = bannerImageFile;
	}

	public String getBannerDc() {
		return bannerDc;
	}

	public void setBannerDc(String bannerDc) {
		this.bannerDc = bannerDc;
	}

	public String getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public String getReflctAt() {
		return reflctAt;
	}

	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public boolean isAtchFile() {
		return isAtchFile;
	}

	public void setAtchFile(boolean isAtchFile) {
		this.isAtchFile = isAtchFile;
	}

	public List<BannerVO> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<BannerVO> bannerList) {
		this.bannerList = bannerList;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
}
