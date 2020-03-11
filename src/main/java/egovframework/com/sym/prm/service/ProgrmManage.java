package egovframework.com.sym.prm.service;

/**
 * 프로그램목록 관리 생성을 위한 모델 클래스를 정의한다.
 * @author 공통서비스 개발팀 이 용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이용          최초 생성
 *
 * </pre>
 */

public class ProgrmManage {
	/**
	 * 프로그램설명
	 */
	private String progrmDc;
	/**
	 * 프로그램파일명
	 */
	private String progrmFileNm;
	/**
	 * 프로그램한글명
	 */
	private String progrmKoreanNm;
	/**
	 * 프로그램저장경로
	 */
	private String progrmStrePath;
	/**
	 * URL
	 */
	private String url;

	public String getProgrmDc() {
		return progrmDc;
	}
	public void setProgrmDc(String progrmDc) {
		this.progrmDc = progrmDc;
	}
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}
	public String getProgrmKoreanNm() {
		return progrmKoreanNm;
	}
	public void setProgrmKoreanNm(String progrmKoreanNm) {
		this.progrmKoreanNm = progrmKoreanNm;
	}
	public String getProgrmStrePath() {
		return progrmStrePath;
	}
	public void setProgrmStrePath(String progrmStrePath) {
		this.progrmStrePath = progrmStrePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String urlTemp) {
		url = urlTemp;
	}
}