package egovframework.com.sts.sst.service;

/**
 * 웹로그집계정보에 대한 모델 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.04.15
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------     --------    ---------------------------
 *  2009.04.15   박지욱          최초 생성
 *  2011.07.01   이기하          패키지 분리(sts -> sts.sst)
 *
 *  </pre>
 */
public class WebLogSummary {

	/**
	 * 횟수
	 */
	private int rdCnt;
	/**
	 * 발생일자
	 */
	private String occrrncDe;
	/**
	 * URL
	 */
	private String url;
	/**
	 * rdCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getRdCnt() {
		return rdCnt;
	}
	/**
	 * rdCnt attribute 값을 설정한다.
	 * @param rdCnt int
	 */
	public void setRdCnt(int rdCnt) {
		this.rdCnt = rdCnt;
	}
	/**
	 * occrrncDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getOccrrncDe() {
		return occrrncDe;
	}
	/**
	 * occrrncDe attribute 값을 설정한다.
	 * @param occrrncDe String
	 */
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}
	/**
	 * url attribute 를 리턴한다.
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url attribute 값을 설정한다.
	 * @param url String
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
