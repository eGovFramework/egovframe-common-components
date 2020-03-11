package egovframework.com.cop.smt.wmr.service;

/**
 * 개요
 * - 주간월간보고에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 주간월간보고의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:48
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class WikMnthngReprtVO extends WikMnthngReprt {
	
	/** 검색조건 */
    private String searchCnd = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 사용자ID조회조건 */
    private String searchId = "";
    
    /** 일자 조회조건 */
    private String searchDe = "";
    
    /** 시작일자 조회조건 */
    private String searchBgnDe = "";
    
    /** 종료일자 조회조건 */
    private String searchEndDe = "";
    
    /** 주간/월간보고서 상태 조회조건 */
    private String searchSttus = "";
    
    /** 보고유형 조회조건 */
    private String searchSe = "";
    
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

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getSearchDe() {
		return searchDe;
	}

	public void setSearchDe(String searchDe) {
		this.searchDe = searchDe;
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

	public String getSearchSttus() {
		return searchSttus;
	}

	public void setSearchSttus(String searchSttus) {
		this.searchSttus = searchSttus;
	}

	public String getSearchSe() {
		return searchSe;
	}

	public void setSearchSe(String searchSe) {
		this.searchSe = searchSe;
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