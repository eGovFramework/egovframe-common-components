package egovframework.com.cop.smt.lsm.service;

/**
 * 개요
 * - 간부일정에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 간부일정의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:06
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class LeaderSchdulVO extends LeaderSchdul {
	
	/** 월별/주별/일별 일정조회 조회조건 */
	private String searchMode;
	/** 월 조회조건	 */
	private String searchMonth;
	/** 시작일자 조회조건 */
	private String searchBgnDe;
	/** 종료일자 조회조건	*/
	private String searchEndDe;
	/** 일자 조회조건 */
	private String searchDay;
	/** 년 조회조건 */
	private String year;
	/** 월 조회조건 */
	private String month;
	/** 주 조회조건 */
	private String week;
	/** 일 조회조건 */
	private String day;
	/** 검색조건 */
	private String searchCondition;
	/** 검색단어 */
	private String searchKeyword;
	/** 보조검색단어 */
	private String searchKeywordEx;
	
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getSearchMonth() {
		return searchMonth;
	}
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}
	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}
	public String getSearchDay() {
		return searchDay;
	}
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchKeywordEx() {
		return searchKeywordEx;
	}
	public void setSearchKeywordEx(String searchKeywordEx) {
		this.searchKeywordEx = searchKeywordEx;
	}


	
}