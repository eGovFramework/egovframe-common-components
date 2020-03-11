package egovframework.com.sym.prm.service;

/** 
 * 프로그램변경관리 처리를 위한 VO 클래스르를 정의한다
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *
 * </pre>
 */

public class ProgrmManageDtlVO{

	/** 프로그램파일명 */
	private String progrmFileNm;
	/** 요청번호 */
	private int rqesterNo;
	/** 요청제목	 */
	private String rqesterSj;
	/** 요청자ID */
	private String rqesterPersonId;
	/** 요청일자 */
	private String rqesterDe;
	/** 변경요청내용	 */
	private String changerqesterCn;
	/** 처리자ID	 */
	private String opetrId;
	/** 처리상태코드	 */
	private String processSttus;
	/** 처리일자	 */
	private String processDe;
	/** 요청처리내용 */
	private String rqesterProcessCn;

	/** 요청시작일자 */
	private String rqesterDeBegin;
	/** 요청종료일자 */
	private String rqesterDeEnd;
	
	/** 프로그램파일명 */
	private String tmpProgrmNm;
	/** 요청번호 */
	private int tmpRqesterNo;
    /** tmp_Email */
    private   String   tmpEmail;
	
	/**
	 * progrmFileNm attribute를 리턴한다.
	 * @return String
	 */
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	/**
	 * progrmFileNm attribute 값을 설정한다.
	 * @param progrmFileNm String
	 */
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}
	/**
	 * rqesterNo attribute를 리턴한다.
	 * @return int
	 */
	public int getRqesterNo() {
		return rqesterNo;
	}
	/**
	 * rqesterNo attribute 값을 설정한다.
	 * @param rqesterNo int
	 */
	public void setRqesterNo(int rqesterNo) {
		this.rqesterNo = rqesterNo;
	}
	/**
	 * rqesterSj attribute를 리턴한다.
	 * @return String
	 */
	public String getRqesterSj() {
		return rqesterSj;
	}
	/**
	 * rqesterSj attribute 값을 설정한다.
	 * @param rqesterSj String
	 */
	public void setRqesterSj(String rqesterSj) {
		this.rqesterSj = rqesterSj;
	}
	/**
	 * rqesterPersonId attribute를 리턴한다.
	 * @return String
	 */
	public String getRqesterPersonId() {
		return rqesterPersonId;
	}
	/**
	 * rqesterPersonId attribute 값을 설정한다.
	 * @param rqesterPersonId String
	 */
	public void setRqesterPersonId(String rqesterPersonId) {
		this.rqesterPersonId = rqesterPersonId;
	}
	/**
	 * rqesterDe attribute를 리턴한다.
	 * @return String
	 */
	public String getRqesterDe() {
		return rqesterDe;
	}
	/**
	 * rqesterDe attribute 값을 설정한다.
	 * @param rqesterDe String
	 */
	public void setRqesterDe(String rqesterDe) {
		this.rqesterDe = rqesterDe;
	}
	/**
	 * changerqesterCn attribute를 리턴한다.
	 * @return String
	 */
	public String getChangerqesterCn() {
		return changerqesterCn;
	}
	/**
	 * changerqesterCn attribute 값을 설정한다.
	 * @param changerqesterCn String
	 */
	public void setChangerqesterCn(String changerqesterCn) {
		this.changerqesterCn = changerqesterCn;
	}
	/**
	 * opetrId attribute를 리턴한다.
	 * @return String
	 */
	public String getOpetrId() {
		return opetrId;
	}
	/**
	 * opetrId attribute 값을 설정한다.
	 * @param opetrId String
	 */
	public void setOpetrId(String opetrId) {
		this.opetrId = opetrId;
	}
	/**
	 * processSttus attribute를 리턴한다.
	 * @return String
	 */
	public String getProcessSttus() {
		return processSttus;
	}
	/**
	 * processSttus attribute 값을 설정한다.
	 * @param processSttus String
	 */
	public void setProcessSttus(String processSttus) {
		this.processSttus = processSttus;
	}
	/**
	 * processDe attribute를 리턴한다.
	 * @return String
	 */
	public String getProcessDe() {
		return processDe;
	}
	/**
	 * processDe attribute 값을 설정한다.
	 * @param processDe String
	 */
	public void setProcessDe(String processDe) {
		this.processDe = processDe;
	}
	/**
	 * rqesterProcessCn attribute를 리턴한다.
	 * @return String
	 */
	public String getRqesterProcessCn() {
		return rqesterProcessCn;
	}
	/**
	 * rqesterProcessCn attribute 값을 설정한다.
	 * @param rqesterProcessCn String
	 */
	public void setRqesterProcessCn(String rqesterProcessCn) {
		this.rqesterProcessCn = rqesterProcessCn;
	}
	/**
	 * rqesterDeBegin attribute를 리턴한다.
	 * @return String
	 */
	public String getRqesterDeBegin() {
		return rqesterDeBegin;
	}
	/**
	 * rqesterDeBegin attribute 값을 설정한다.
	 * @param rqesterDeBegin String
	 */
	public void setRqesterDeBegin(String rqesterDeBegin) {
		this.rqesterDeBegin = rqesterDeBegin;
	}
	/**
	 * rqesterDeEnd attribute를 리턴한다.
	 * @return String
	 */
	public String getRqesterDeEnd() {
		return rqesterDeEnd;
	}
	/**
	 * rqesterDeEnd attribute 값을 설정한다.
	 * @param rqesterDeEnd String
	 */
	public void setRqesterDeEnd(String rqesterDeEnd) {
		this.rqesterDeEnd = rqesterDeEnd;
	}
	/**
	 * tmp_progrmNm attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpProgrmNm() {
		return tmpProgrmNm;
	}
	/**
	 * tmp_progrmNm attribute 값을 설정한다.
	 * @param tmp_progrmNm String
	 */
	public void setTmpProgrmNm(String tmp_progrmNm) {
		this.tmpProgrmNm = tmp_progrmNm;
	}
	/**
	 * tmp_rqesterNo attribute를 리턴한다.
	 * @return int
	 */
	public int getTmpRqesterNo() {
		return tmpRqesterNo;
	}
	/**
	 * tmp_rqesterNo attribute 값을 설정한다.
	 * @param tmp_rqesterNo int
	 */
	public void setTmpRqesterNo(int tmp_rqesterNo) {
		this.tmpRqesterNo = tmp_rqesterNo;
	}

	/**
	 * tmp_Email attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpEmail() {
		return tmpEmail;
	}
	/**
	 * tmp_Email attribute 값을 설정한다.
	 * @param tmp_Email String
	 */
	public void setTmpEmail(String tmp_Email) {
		this.tmpEmail = tmp_Email;
	}
}