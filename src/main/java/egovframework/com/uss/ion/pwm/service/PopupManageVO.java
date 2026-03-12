package egovframework.com.uss.ion.pwm.service;

import egovframework.com.cmm.ComDefaultVO;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 팝업창에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 팝업창의 팝업창아이디, 팝업창 타이틀명, 실파일 URL, 파업창이 화면에 보여지는 위치정보, 팝업창의 사이즈, 게시시작일, 게시종료일,
 * 그만보기 설정 여부, 게시여부 및 목록 항목을 관리한다.
 *
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:21:04
 */
public class PopupManageVO extends ComDefaultVO {

	private static final long serialVersionUID = -4822974866080666897L;

	/**
	 * 팝업창아이디
	 */
	private String popupId;
	/**
	 * 팝업창 타이틀명
	 */
	@EgovNullCheck
	@Size(max = 1024)
	private String popupTitleNm;
	/**
	 * 실파일 URL
	 */
	@EgovNullCheck
	@Size(max = 1024)
	private String fileUrl;
	/**
	 * 팝업창이 화면에 보여지는 가로 위치정보
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupWlc;

	/**
	 * 팝업창이 화면에 보여지는 세로 위치정보
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupHlc;

	/**
	 * 팝업창의 높이
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupHSize;

	/**
	 * 팝업창의 넚이
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupWSize;

	/**
	 * 게시시작일
	 */
	private String ntceBgnde;
	/**
	 * 게시종료일
	 */
	private String ntceEndde;

	/** 게시시작일(시간) */
	private String ntceBgndeHH;

	/** 게시시작일(분) */
	private String ntceBgndeMM;

	/** 게시종료일(시간) */
	private String ntceEnddeHH;

	/** 게시종료일(분) */
	private String ntceEnddeMM;

	/**
	 * 그만보기 설정 여부
	 */
	@EgovNullCheck
	@Size(max = 1)
	private String stopVewAt;
	/**
	 * 게시여부
	 */
	@EgovNullCheck
	@Size(max = 1)
	private String ntceAt;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록아이디 */
	private String frstRegisterId;

	/** 최종수정일 */
	private String lastUpdusrPnttm;

	/** 최종수정자 아이디 */
	private String lastUpdusrId;

	public PopupManageVO() {
	}

	public String getPopupId() {
		return popupId;
	}

	public void setPopupId(String popupId) {
		this.popupId = popupId;
	}

	public String getPopupTitleNm() {
		return popupTitleNm;
	}

	public void setPopupTitleNm(String popupTitleNm) {
		this.popupTitleNm = popupTitleNm;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPopupWlc() {
		return popupWlc;
	}

	public void setPopupWlc(String popupWlc) {
		this.popupWlc = popupWlc;
	}

	public String getPopupHlc() {
		return popupHlc;
	}

	public void setPopupHlc(String popupHlc) {
		this.popupHlc = popupHlc;
	}

	public String getPopupHSize() {
		return popupHSize;
	}

	public void setPopupHSize(String popupHSize) {
		this.popupHSize = popupHSize;
	}

	public String getPopupWSize() {
		return popupWSize;
	}

	public void setPopupWSize(String popupWSize) {
		this.popupWSize = popupWSize;
	}

	public String getNtceBgnde() {
		return ntceBgnde;
	}

	public void setNtceBgnde(String ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}

	public String getNtceEndde() {
		return ntceEndde;
	}

	public void setNtceEndde(String ntceEndde) {
		this.ntceEndde = ntceEndde;
	}

	public String getNtceBgndeHH() {
		return ntceBgndeHH;
	}

	public void setNtceBgndeHH(String ntceBgndeHH) {
		this.ntceBgndeHH = ntceBgndeHH;
	}

	public String getNtceBgndeMM() {
		return ntceBgndeMM;
	}

	public void setNtceBgndeMM(String ntceBgndeMM) {
		this.ntceBgndeMM = ntceBgndeMM;
	}

	public String getNtceEnddeHH() {
		return ntceEnddeHH;
	}

	public void setNtceEnddeHH(String ntceEnddeHH) {
		this.ntceEnddeHH = ntceEnddeHH;
	}

	public String getNtceEnddeMM() {
		return ntceEnddeMM;
	}

	public void setNtceEnddeMM(String ntceEnddeMM) {
		this.ntceEnddeMM = ntceEnddeMM;
	}

	public String getStopVewAt() {
		return stopVewAt;
	}

	public void setStopVewAt(String stopVewAt) {
		this.stopVewAt = stopVewAt;
	}

	public String getNtceAt() {
		return ntceAt;
	}

	public void setNtceAt(String ntceAt) {
		this.ntceAt = ntceAt;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
}
