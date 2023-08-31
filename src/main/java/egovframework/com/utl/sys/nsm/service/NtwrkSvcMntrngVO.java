package egovframework.com.utl.sys.nsm.service;


/**
 * 개요
 * - 네트워크서비스 모니터링대상에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 네트워크서비스 모니터링대상의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
@SuppressWarnings("serial")
public class NtwrkSvcMntrngVO extends NtwrkSvcMntrng {

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
	 * 검색 조건을 반환합니다.
	 *
	 * @return 검색 조건
	 */
	public String getSearchCnd() {
		return searchCnd;
	}

	/**
	 * 검색 조건을 설정합니다.
	 *
	 * @param searchCnd 설정할 검색 조건
	 */
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	/**
	 * 검색어를 반환합니다.
	 *
	 * @return 검색어
	 */
	public String getSearchWrd() {
		return searchWrd;
	}

	/**
	 * 검색어를 설정합니다.
	 *
	 * @param searchWrd 설정할 검색어
	 */
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	/**
	 * 현재 페이지 인덱스를 반환합니다.
	 *
	 * @return 페이지 인덱스
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 현재 페이지 인덱스를 설정합니다.
	 *
	 * @param pageIndex 설정할 페이지 인덱스
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 페이지 당 보여질 항목의 수를 반환합니다.
	 *
	 * @return 페이지 당 항목 수
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * 페이지 당 보여질 항목의 수를 설정합니다.
	 *
	 * @param pageUnit 설정할 페이지 당 항목 수
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * 페이지 크기를 반환합니다.
	 *
	 * @return 페이지 크기
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 페이지 크기를 설정합니다.
	 *
	 * @param pageSize 설정할 페이지 크기
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 첫번째 인덱스를 반환합니다.
	 *
	 * @return 첫번째 인덱스
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * 첫번째 인덱스를 설정합니다.
	 *
	 * @param firstIndex 설정할 첫번째 인덱스
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * 마지막 인덱스를 반환합니다.
	 *
	 * @return 마지막 인덱스
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * 마지막 인덱스를 설정합니다.
	 *
	 * @param lastIndex 설정할 마지막 인덱스
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * 페이지당 레코드 수를 반환합니다.
	 *
	 * @return 페이지당 레코드 수
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * 페이지당 레코드 수를 설정합니다.
	 *
	 * @param recordCountPerPage 설정할 페이지당 레코드 수
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

}