package egovframework.com.sym.prm.service;

/** 
 * 프로그램목록 처리를 위한 VO 클래스르를 정의한다
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

public class ProgrmManageVO{

	/** 프로그램파일명 */
	private String progrmFileNm;
	/** 프로그램저장경로 */
	private String progrmStrePath;
	/** 프로그램한글명 */
	private String progrmKoreanNm;
	/** URL */
	private String url;
	/** 프로그램설명	 */	
	private String progrmDc;

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
	 * progrmStrePath attribute를 리턴한다.
	 * @return String
	 */
	public String getProgrmStrePath() {
		return progrmStrePath;
	}
	/**
	 * progrmStrePath attribute 값을 설정한다.
	 * @param progrmStrePath String
	 */
	public void setProgrmStrePath(String progrmStrePath) {
		this.progrmStrePath = progrmStrePath;
	}
	/**
	 * progrmKoreanNm attribute를 리턴한다.
	 * @return String
	 */
	public String getProgrmKoreanNm() {
		return progrmKoreanNm;
	}
	/**
	 * progrmKoreanNm attribute 값을 설정한다.
	 * @param progrmKoreanNm String
	 */
	public void setProgrmKoreanNm(String progrmKoreanNm) {
		this.progrmKoreanNm = progrmKoreanNm;
	}
	/**
	 * url attribute를 리턴한다.
	 * @return String
	 */
	public String getURL() {
		return url;
	}
	/**
	 * URL attribute 값을 설정한다.
	 * @param URL String
	 */
	public void setURL(String URL) {
		this.url = URL;
	}
	/**
	 * progrmDc attribute를 리턴한다.
	 * @return String
	 */
	public String getProgrmDc() {
		return progrmDc;
	}
	/**
	 * progrmDc attribute 값을 설정한다.
	 * @param progrmDc String
	 */
	public void setProgrmDc(String progrmDc) {
		this.progrmDc = progrmDc;
	}
  
}