package egovframework.com.utl.sys.prm.service;

/**
 * 개요
 * - PROCESS모니터링 로그에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - PROCESS모니터링의 목록 항목, 조회조건 등을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:47
 */

public class ProcessMonLogVO extends ProcessMonLog {

	private static final long serialVersionUID = -7374180958172370475L;

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

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

	/**
	 * @return the searchCondition
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * @param searchCondition the searchCondition to set
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * @return the searchKeyword
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * @param searchKeyword the searchKeyword to set
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * @return the searchBgnDe
	 */
	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	/**
	 * @param searchBgnDe the searchBgnDe to set
	 */
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	/**
	 * @return the searchBgnHour
	 */
	public String getSearchBgnHour() {
		return searchBgnHour;
	}

	/**
	 * @param searchBgnHour the searchBgnHour to set
	 */
	public void setSearchBgnHour(String searchBgnHour) {
		this.searchBgnHour = searchBgnHour;
	}

	/**
	 * @return the searchBgnDt
	 */
	public String getSearchBgnDt() {
		return searchBgnDt;
	}

	/**
	 * @param searchBgnDt the searchBgnDt to set
	 */
	public void setSearchBgnDt(String searchBgnDt) {
		this.searchBgnDt = searchBgnDt;
	}

	/**
	 * @return the searchEndDe
	 */
	public String getSearchEndDe() {
		return searchEndDe;
	}

	/**
	 * @param searchEndDe the searchEndDe to set
	 */
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	/**
	 * @return the searchEndHour
	 */
	public String getSearchEndHour() {
		return searchEndHour;
	}

	/**
	 * @param searchEndHour the searchEndHour to set
	 */
	public void setSearchEndHour(String searchEndHour) {
		this.searchEndHour = searchEndHour;
	}

	/**
	 * @return the searchEndDt
	 */
	public String getSearchEndDt() {
		return searchEndDt;
	}

	/**
	 * @param searchEndDt the searchEndDt to set
	 */
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * @param pageUnit the pageUnit to set
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the firstIndex
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * @param firstIndex the firstIndex to set
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * @return the lastIndex
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * @param lastIndex the lastIndex to set
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * @return the recordCountPerPage
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * @param recordCountPerPage the recordCountPerPage to set
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

}
