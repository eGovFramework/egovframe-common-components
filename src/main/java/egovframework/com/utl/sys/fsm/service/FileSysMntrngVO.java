package egovframework.com.utl.sys.fsm.service;

/**
 * 개요
 * - 파일시스템 모니터링대상에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 파일시스템 모니터링대상의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
@SuppressWarnings("serial")
public class FileSysMntrngVO extends FileSysMntrng {

	/** 검색조건 */
    private String searchCnd = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
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
	 * 검색 조건을 가져옵니다.
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
	 * 검색어를 가져옵니다.
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
	 * 현재 페이지 인덱스를 가져옵니다.
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
	 * 페이지 단위를 가져옵니다.
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * 페이지 단위를 설정합니다.
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * 한 페이지의 최대 크기를 가져옵니다.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 한 페이지의 최대 크기를 설정합니다.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 페이지 범위 내의 첫번째 인덱스를 가져옵니다.
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * 페이지 범위 내의 첫번째 인덱스를 설정합니다.
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * 페이지 범위 내의 마지막 인덱스를 가져옵니다.
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * 페이지 범위 내의 마지막 인덱스를 설정합니다.
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * 페이지당 기록(레코드) 수를 가져옵니다.
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * 페이지당 기록(레코드) 수를 설정합니다.
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	
}