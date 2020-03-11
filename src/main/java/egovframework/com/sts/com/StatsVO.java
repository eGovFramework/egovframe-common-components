package egovframework.com.sts.com;

/**
 * 통계 결과 VO 클래스
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
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.com)
 *
 *  </pre>
 */
public class StatsVO {

	/** 결과통계수 */
	private int statsCo;
	/** 결과일자 */
	private String statsDate;
	/** 최대통계수 */
	private int maxStatsCo;
	/** 최소통계수 */
	private int minStatsCo;
	/** 생성글수 */
	private int creatCo;
	/** 총조회수 */
	private int totInqireCo;
	/** 평균조회수 */
	private float avrgInqireCo;
	/** 최대조회게시물ID */
	private String mxmmInqireBbsId;
	/** 최대조회게시물제목 */
	private String mxmmInqireBbsNm;
	/** 최소조회게시물ID */
	private String mummInqireBbsId;
	/** 최소조회게시물제목 */
	private String mummInqireBbsNm;
	/** 최고게시자ID */
	private String topNtcepersonId;
	/** 최고게시글수 */
	private int topNtcepersonCo;
	/** 접속프로그램메소드 */
	private String conectMethod;
	/** 수정글수 */
	private int updtCo;
	/** 조회글수 */
	private int inqireCo;
	/** 삭제글수 */
	private int deleteCo;
	/** 출력횟수 */
	private int outptCo;
	/** 에러횟수 */
	private int errorCo;
	/** 시작일자 */
	private String fromDate;
	/** 종료일자 */
	private String toDate;
	/** 기간구분 */
	private String pdKind;
	/** 통계구분 */
	private String statsKind;
	/** 세부통계구분 */
	private String detailStatsKind;
	/** 탭구분 */
	private String tabKind;
	/** 그래프길이 */
	private float maxUnit;
	/**
	 * statsCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getStatsCo() {
		return statsCo;
	}
	/**
	 * statsCo attribute 값을 설정한다.
	 * @param statsCo int
	 */
	public void setStatsCo(int statsCo) {
		this.statsCo = statsCo;
	}
	/**
	 * statsDate attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatsDate() {
		return statsDate;
	}
	/**
	 * statsDate attribute 값을 설정한다.
	 * @param statsDate String
	 */
	public void setStatsDate(String statsDate) {
		this.statsDate = statsDate;
	}
	/**
	 * maxStatsCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getMaxStatsCo() {
		return maxStatsCo;
	}
	/**
	 * maxStatsCo attribute 값을 설정한다.
	 * @param maxStatsCo int
	 */
	public void setMaxStatsCo(int maxStatsCo) {
		this.maxStatsCo = maxStatsCo;
	}
	/**
	 * minStatsCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getMinStatsCo() {
		return minStatsCo;
	}
	/**
	 * minStatsCo attribute 값을 설정한다.
	 * @param minStatsCo int
	 */
	public void setMinStatsCo(int minStatsCo) {
		this.minStatsCo = minStatsCo;
	}
	/**
	 * creatCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getCreatCo() {
		return creatCo;
	}
	/**
	 * creatCo attribute 값을 설정한다.
	 * @param creatCo int
	 */
	public void setCreatCo(int creatCo) {
		this.creatCo = creatCo;
	}
	/**
	 * totInqireCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getTotInqireCo() {
		return totInqireCo;
	}
	/**
	 * totInqireCo attribute 값을 설정한다.
	 * @param totInqireCo int
	 */
	public void setTotInqireCo(int totInqireCo) {
		this.totInqireCo = totInqireCo;
	}
	/**
	 * avrgInqireCo attribute 를 리턴한다.
	 * @return float
	 */
	public float getAvrgInqireCo() {
		return avrgInqireCo;
	}
	/**
	 * avrgInqireCo attribute 값을 설정한다.
	 * @param avrgInqireCo float
	 */
	public void setAvrgInqireCo(float avrgInqireCo) {
		this.avrgInqireCo = avrgInqireCo;
	}
	/**
	 * mxmmInqireBbsId attribute 를 리턴한다.
	 * @return String
	 */
	public String getMxmmInqireBbsId() {
		return mxmmInqireBbsId;
	}
	/**
	 * mxmmInqireBbsId attribute 값을 설정한다.
	 * @param mxmmInqireBbsId String
	 */
	public void setMxmmInqireBbsId(String mxmmInqireBbsId) {
		this.mxmmInqireBbsId = mxmmInqireBbsId;
	}
	/**
	 * mxmmInqireBbsNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getMxmmInqireBbsNm() {
		return mxmmInqireBbsNm;
	}
	/**
	 * mxmmInqireBbsNm attribute 값을 설정한다.
	 * @param mxmmInqireBbsNm String
	 */
	public void setMxmmInqireBbsNm(String mxmmInqireBbsNm) {
		this.mxmmInqireBbsNm = mxmmInqireBbsNm;
	}
	/**
	 * mummInqireBbsId attribute 를 리턴한다.
	 * @return String
	 */
	public String getMummInqireBbsId() {
		return mummInqireBbsId;
	}
	/**
	 * mummInqireBbsId attribute 값을 설정한다.
	 * @param mummInqireBbsId String
	 */
	public void setMummInqireBbsId(String mummInqireBbsId) {
		this.mummInqireBbsId = mummInqireBbsId;
	}
	/**
	 * mummInqireBbsNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getMummInqireBbsNm() {
		return mummInqireBbsNm;
	}
	/**
	 * mummInqireBbsNm attribute 값을 설정한다.
	 * @param mummInqireBbsNm String
	 */
	public void setMummInqireBbsNm(String mummInqireBbsNm) {
		this.mummInqireBbsNm = mummInqireBbsNm;
	}
	/**
	 * topNtcepersonId attribute 를 리턴한다.
	 * @return String
	 */
	public String getTopNtcepersonId() {
		return topNtcepersonId;
	}
	/**
	 * topNtcepersonId attribute 값을 설정한다.
	 * @param topNtcepersonId String
	 */
	public void setTopNtcepersonId(String topNtcepersonId) {
		this.topNtcepersonId = topNtcepersonId;
	}
	/**
	 * topNtcepersonCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getTopNtcepersonCo() {
		return topNtcepersonCo;
	}
	/**
	 * topNtcepersonCo attribute 값을 설정한다.
	 * @param topNtcepersonCo int
	 */
	public void setTopNtcepersonCo(int topNtcepersonCo) {
		this.topNtcepersonCo = topNtcepersonCo;
	}
	/**
	 * conectMethod attribute 를 리턴한다.
	 * @return String
	 */
	public String getConectMethod() {
		return conectMethod;
	}
	/**
	 * conectMethod attribute 값을 설정한다.
	 * @param conectMethod String
	 */
	public void setConectMethod(String conectMethod) {
		this.conectMethod = conectMethod;
	}
	/**
	 * updtCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getUpdtCo() {
		return updtCo;
	}
	/**
	 * updtCo attribute 값을 설정한다.
	 * @param updtCo int
	 */
	public void setUpdtCo(int updtCo) {
		this.updtCo = updtCo;
	}
	/**
	 * inqireCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getInqireCo() {
		return inqireCo;
	}
	/**
	 * inqireCo attribute 값을 설정한다.
	 * @param inqireCo int
	 */
	public void setInqireCo(int inqireCo) {
		this.inqireCo = inqireCo;
	}
	/**
	 * deleteCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getDeleteCo() {
		return deleteCo;
	}
	/**
	 * deleteCo attribute 값을 설정한다.
	 * @param deleteCo int
	 */
	public void setDeleteCo(int deleteCo) {
		this.deleteCo = deleteCo;
	}
	/**
	 * outptCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getOutptCo() {
		return outptCo;
	}
	/**
	 * outptCo attribute 값을 설정한다.
	 * @param outptCo int
	 */
	public void setOutptCo(int outptCo) {
		this.outptCo = outptCo;
	}
	/**
	 * errorCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getErrorCo() {
		return errorCo;
	}
	/**
	 * errorCo attribute 값을 설정한다.
	 * @param errorCo int
	 */
	public void setErrorCo(int errorCo) {
		this.errorCo = errorCo;
	}
	/**
	 * fromDate attribute 를 리턴한다.
	 * @return String
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * fromDate attribute 값을 설정한다.
	 * @param fromDate String
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * toDate attribute 를 리턴한다.
	 * @return String
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * toDate attribute 값을 설정한다.
	 * @param toDate String
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * pdKind attribute 를 리턴한다.
	 * @return String
	 */
	public String getPdKind() {
		return pdKind;
	}
	/**
	 * pdKind attribute 값을 설정한다.
	 * @param pdKind String
	 */
	public void setPdKind(String pdKind) {
		this.pdKind = pdKind;
	}
	/**
	 * statsKind attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatsKind() {
		return statsKind;
	}
	/**
	 * statsKind attribute 값을 설정한다.
	 * @param statsKind String
	 */
	public void setStatsKind(String statsKind) {
		this.statsKind = statsKind;
	}
	/**
	 * detailStatsKind attribute 를 리턴한다.
	 * @return String
	 */
	public String getDetailStatsKind() {
		return detailStatsKind;
	}
	/**
	 * detailStatsKind attribute 값을 설정한다.
	 * @param detailStatsKind String
	 */
	public void setDetailStatsKind(String detailStatsKind) {
		this.detailStatsKind = detailStatsKind;
	}
	/**
	 * tabKind attribute 를 리턴한다.
	 * @return String
	 */
	public String getTabKind() {
		return tabKind;
	}
	/**
	 * tabKind attribute 값을 설정한다.
	 * @param tabKind String
	 */
	public void setTabKind(String tabKind) {
		this.tabKind = tabKind;
	}
	/**
	 * maxUnit attribute 를 리턴한다.
	 * @return float
	 */
	public float getMaxUnit() {
		return maxUnit;
	}
	/**
	 * maxUnit attribute 값을 설정한다.
	 * @param maxUnit float
	 */
	public void setMaxUnit(float maxUnit) {
		this.maxUnit = maxUnit;
	}
}
