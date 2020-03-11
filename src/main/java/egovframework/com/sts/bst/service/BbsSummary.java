package egovframework.com.sts.bst.service;

/**
 * 게시물집계정보에 대한 모델 클래스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.bst)
 *
 *  </pre>
 */
public class BbsSummary {

	/** 평균조회수 */
	private int avrgInqireCo;
	/** 생성글수 */
	private int creatCo;
	/** 최소조회게시글ID */
	private String mummInqireBbsId;
	/** 최고조회게시글ID */
	private String mxmmInqireBbsId;
	/** 발생일자 */
	private String occrrncDe;
	/** 세부통계구분 */
	private String statsDetailSe;
	/** 통계구분 */
	private String statsSe;
	/** 최대게시자ID */
	private String topNtcepersonId;
	/** 총조회수 */
	private int totInqireCo;
	/**
	 * avrgInqireCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getAvrgInqireCo() {
		return avrgInqireCo;
	}
	/**
	 * avrgInqireCo attribute 값을 설정한다.
	 * @param avrgInqireCo int
	 */
	public void setAvrgInqireCo(int avrgInqireCo) {
		this.avrgInqireCo = avrgInqireCo;
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
}
