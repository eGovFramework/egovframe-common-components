package egovframework.com.uss.mpe.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요 - 마이페이지에 대한 model 클래스를 정의한다.
 * 
 * 상세내용 - 마이페이지의 컨텐츠아이디, 컨텐츠 명, 컨텐츠 URL, 컨텐츠 사용 여부 항목을 관리한다.
 * 
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:20:27
 */
public class IndvdlPgeVO extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 컨텐츠 아이디
	 */
	private String cntntsId;
	/**
	 * 컨텐츠 명
	 */
	private String cntntsNm;
	/**
	 * 컨텐츠 미리보기 URL
	 */
	private String cntntsLinkUrl;
	/**
	 * 컨텐츠 URL
	 */
	private String cntcUrl;
	/**
	 * 컨텐츠 설명
	 */
	private String cntntsDc;
	/**
	 * 컨텐츠 사용 여부
	 */
	private String cntntsUseAt;

	public String getCntntsId() {
		return cntntsId;
	}

	public void setCntntsId(String cntntsId) {
		this.cntntsId = cntntsId;
	}

	public String getCntntsNm() {
		return cntntsNm;
	}

	public void setCntntsNm(String cntntsNm) {
		this.cntntsNm = cntntsNm;
	}

	public String getCntntsLinkUrl() {
		return cntntsLinkUrl;
	}

	public void setCntntsLinkUrl(String cntntsLinkUrl) {
		this.cntntsLinkUrl = cntntsLinkUrl;
	}

	public String getCntcUrl() {
		return cntcUrl;
	}

	public void setCntcUrl(String cntcUrl) {
		this.cntcUrl = cntcUrl;
	}

	public String getCntntsDc() {
		return cntntsDc;
	}

	public void setCntntsDc(String cntntsDc) {
		this.cntntsDc = cntntsDc;
	}

	public String getCntntsUseAt() {
		return cntntsUseAt;
	}

	public void setCntntsUseAt(String cntntsUseAt) {
		this.cntntsUseAt = cntntsUseAt;
	}
}