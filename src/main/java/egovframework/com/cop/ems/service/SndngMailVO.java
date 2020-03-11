package egovframework.com.cop.ems.service;

/**
 * 발송메일 VO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *  2011.12.06  이기하          첨부파일경로(fileStreCours), 첨부파일이름(orignlFileNm) 추가
 *
 *  </pre>
 */
public class SndngMailVO {

	/** 메세지ID */
	private String mssageId;
	/** 발신자 */
	private String dsptchPerson;
	/** 수신자 */
	private String recptnPerson;
	/** 제목 */
	private String sj;
	/** 발송결과코드 */
	private String sndngResultCode;
	/** 메일내용 */
	private String emailCn;
	/** 첨부파일ID */
	private String atchFileId;
	/** 첨부파일경로 */
	private String fileStreCours;
	/** 첨부파일이름 */
	private String orignlFileNm;
	/** 발신일자 */
	private String sndngDe;
	/** 첨부파일ID 리스트 */
	private String atchFileIdList;
	/** 발송요청XML내용 */
	private String xmlContent;
	/** 팝업링크여부(Y/N) */
	private String link;

	/**
	 * mssageId attribute 를 리턴한다.
	 * @return String
	 */
	public String getMssageId() {
		return mssageId;
	}

	/**
	 * mssageId attribute 값을 설정한다.
	 * @param mssageId String
	 */
	public void setMssageId(String mssageId) {
		this.mssageId = mssageId;
	}

	/**
	 * dsptchPerson attribute 를 리턴한다.
	 * @return String
	 */
	public String getDsptchPerson() {
		return dsptchPerson;
	}

	/**
	 * dsptchPerson attribute 값을 설정한다.
	 * @param dsptchPerson String
	 */
	public void setDsptchPerson(String dsptchPerson) {
		this.dsptchPerson = dsptchPerson;
	}

	/**
	 * recptnPerson attribute 를 리턴한다.
	 * @return String
	 */
	public String getRecptnPerson() {
		return recptnPerson;
	}

	/**
	 * recptnPerson attribute 값을 설정한다.
	 * @param recptnPerson String
	 */
	public void setRecptnPerson(String recptnPerson) {
		this.recptnPerson = recptnPerson;
	}

	/**
	 * sj attribute 를 리턴한다.
	 * @return String
	 */
	public String getSj() {
		return sj;
	}

	/**
	 * sj attribute 값을 설정한다.
	 * @param sj String
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}

	/**
	 * sndngResultCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getSndngResultCode() {
		return sndngResultCode;
	}

	/**
	 * sndngResultCode attribute 값을 설정한다.
	 * @param sndngResultCode String
	 */
	public void setSndngResultCode(String sndngResultCode) {
		this.sndngResultCode = sndngResultCode;
	}

	/**
	 * emailCn attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmailCn() {
		return emailCn;
	}

	/**
	 * emailCn attribute 값을 설정한다.
	 * @param emailCn String
	 */
	public void setEmailCn(String emailCn) {
		this.emailCn = emailCn;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * atchFileId attribute 값을 설정한다.
	 * @param atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getFileStreCours() {
		return fileStreCours;
	}

	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	public String getOrignlFileNm() {
		return orignlFileNm;
	}

	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	/**
	 * sndngDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getSndngDe() {
		return sndngDe;
	}

	/**
	 * sndngDe attribute 값을 설정한다.
	 * @param sndngDe String
	 */
	public void setSndngDe(String sndngDe) {
		this.sndngDe = sndngDe;
	}

	/**
	 * atchFileIdList attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchFileIdList() {
		return atchFileIdList;
	}

	/**
	 * atchFileIdList attribute 값을 설정한다.
	 * @param atchFileIdList String
	 */
	public void setAtchFileIdList(String atchFileIdList) {
		this.atchFileIdList = atchFileIdList;
	}

	/**
	 * xmlContent attribute 를 리턴한다.
	 * @return String
	 */
	public String getXmlContent() {
		return xmlContent;
	}

	/**
	 * xmlContent attribute 값을 설정한다.
	 * @param xmlContent String
	 */
	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	/**
	 * link attribute 를 리턴한다.
	 * @return String
	 */
	public String getLink() {
		return link;
	}

	/**
	 * link attribute 값을 설정한다.
	 * @param link String
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
