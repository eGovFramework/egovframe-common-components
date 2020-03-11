package egovframework.com.sts.ust.service;

/**
 * 사용자집계정보에 대한 모델 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.04.15
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.04.15  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.ust)
 *
 *  </pre>
 */
public class UserSummary {

	/**
	 * 발생일자
	 */
	private String occrrncDe;
	/**
	 * 세부통계구분
	 */
	private String statsDetailSe;
	/**
	 * 통계구분
	 */
	private String statsSe;
	/**
	 * 사용자수
	 */
	private int userCo;
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
	 * statsDetailSe attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatsDetailSe() {
		return statsDetailSe;
	}
	/**
	 * statsDetailSe attribute 값을 설정한다.
	 * @param statsDetailSe String
	 */
	public void setStatsDetailSe(String statsDetailSe) {
		this.statsDetailSe = statsDetailSe;
	}
	/**
	 * statsSe attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatsSe() {
		return statsSe;
	}
	/**
	 * statsSe attribute 값을 설정한다.
	 * @param statsSe String
	 */
	public void setStatsSe(String statsSe) {
		this.statsSe = statsSe;
	}
	/**
	 * userCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getUserCo() {
		return userCo;
	}
	/**
	 * userCo attribute 값을 설정한다.
	 * @param userCo int
	 */
	public void setUserCo(int userCo) {
		this.userCo = userCo;
	}
}
