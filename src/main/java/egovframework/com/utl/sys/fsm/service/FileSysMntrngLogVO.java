package egovframework.com.utl.sys.fsm.service;

/**
 * 개요
 * - 파일시스템 모니터링 로그에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 파일시스템 모니터링 로그의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
@SuppressWarnings("serial")
public class FileSysMntrngLogVO extends FileSysMntrngLog {

	/** 검색조건 */
    private String searchCnd = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 시작일자 조회조건 */
    private String searchBgnDe = "";
    
    /** 시작시간 조회조건 */
    private String searchBgnHour = "";
    
    /** 시작일시 조회조건 */
    private String searchBgnDt = "";
    
    /** 종료일자 조회조건 */
    private String searchEndDe = "";
    
    /** 종료시간 조회조건 */
    private String searchEndHour = "";
    
    /** 종료일시 조회조건 */
    private String searchEndDt = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** 첫페이지 인덱스 */
    private int firstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int lastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchBgnHour() {
		return searchBgnHour;
	}

	public void setSearchBgnHour(String searchBgnHour) {
		this.searchBgnHour = searchBgnHour;
	}

	public String getSearchBgnDt() {
		return searchBgnDt;
	}

	public void setSearchBgnDt(String searchBgnDt) {
		this.searchBgnDt = searchBgnDt;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}

	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchEndHour() {
		return searchEndHour;
	}

	public void setSearchEndHour(String searchEndHour) {
		this.searchEndHour = searchEndHour;
	}

	public String getSearchEndDt() {
		return searchEndDt;
	}

	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	
}