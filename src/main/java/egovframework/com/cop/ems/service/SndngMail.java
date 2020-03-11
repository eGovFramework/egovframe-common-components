package egovframework.com.cop.ems.service;

/**
 * 발송메일 모델 클래스
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
 *
 *  </pre>
 */
public class SndngMail {

	/** 발신자 */
	public String dsptchPerson;
	/** 내용 */
	public String emailCn;
	/** 메시지ID */
	public String mssageId;
	/** 수신자 */
	public String recptnPerson;
	/** 제목 */
	public String sj;
	/** 발송결과코드 */
	public String sndngResultCode;
	/** 발신일자 */
	private String sndngDe;

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
}
