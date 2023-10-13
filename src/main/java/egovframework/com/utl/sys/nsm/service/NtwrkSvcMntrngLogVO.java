package egovframework.com.utl.sys.nsm.service;

/**
 * 개요
 * - 네트워크서비스 모니터링 로그에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 네트워크서비스 모니터링 로그의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
@SuppressWarnings("serial")
public class NtwrkSvcMntrngLogVO extends NtwrkSvcMntrngLog {

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

    /** 페이지개수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** 첫페이지 인덱스 */
    private int firstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int lastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;
	/**
	 * 검색 조건을 반환합니다.
	 */
	public String getSearchCnd() {
		return searchCnd;
	}

	/**
	 * 검색 조건을 설정합니다.
	 */
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	/**
	 * 검색어를 반환합니다.
	 */
	public String getSearchWrd() {
		return searchWrd;
	}

	/**
	 * 검색어를 설정합니다.
	 */
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	/**
	 * 검색 시작일을 반환합니다.
	 */
	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	/**
	 * 검색 시작일을 설정합니다.
	 */
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	/**
	 * 검색 시작 시간을 반환합니다.
	 */
	public String getSearchBgnHour() {
		return searchBgnHour;
	}

	/**
	 * 검색 시작 시간을 설정합니다.
	 */
	public void setSearchBgnHour(String searchBgnHour) {
		this.searchBgnHour = searchBgnHour;
	}

	/**
	 * 검색 시작 날짜와 시간을 반환합니다.
	 */
	public String getSearchBgnDt() {
		return searchBgnDt;
	}

	/**
	 * 검색 시작 날짜와 시간을 설정합니다.
	 */
	public void setSearchBgnDt(String searchBgnDt) {
		this.searchBgnDt = searchBgnDt;
	}

	/**
	 * 검색 종료일을 반환합니다.
	 */
	public String getSearchEndDe() {
		return searchEndDe;
	}

	/**
	 * 검색 종료일을 설정합니다.
	 */
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	/**
	 * 검색 종료 시간을 반환합니다.
	 */
	public String getSearchEndHour() {
		return searchEndHour;
	}

	/**
	 * 검색 종료 시간을 설정합니다.
	 */
	public void setSearchEndHour(String searchEndHour) {
		this.searchEndHour = searchEndHour;
	}

	/**
	 * 검색 종료 날짜와 시간을 반환합니다.
	 */
	public String getSearchEndDt() {
		return searchEndDt;
	}

	/**
	 * 검색 종료 날짜와 시간을 설정합니다.
	 */
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

	/**
	 * 현재 페이지 인덱스를 반환합니다.
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 현재 페이지 인덱스를 설정합니다.
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 페이지 당 보여질 항목의 수를 반환합니다.
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * 페이지 당 보여질 항목의 수를 설정합니다.
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * 페이지 크기를 반환합니다.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 페이지 크기를 설정합니다.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 첫번째 인덱스를 반환합니다.
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * 첫번째 인덱스를 설정합니다.
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * 마지막 인덱스를 반환합니다.
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * 마지막 인덱스를 설정합니다.
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * 페이지당 레코드 수를 반환합니다.
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * 페이지당 레코드 수를 설정합니다.
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
}