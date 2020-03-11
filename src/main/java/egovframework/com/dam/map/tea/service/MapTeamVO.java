package egovframework.com.dam.map.tea.service;

/**
 * 개요
 * - 지식맵(조직별)에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 지식맵(조직별)의 목록 항목, 조회조건 등을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 22-7-2010 오전 10:57:44
 */

public class MapTeamVO extends MapTeam {
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";    

    /** 페이지갯수 */
    private int pageUnit = 10;

	/** 페이지사이즈 */
    private int pageSize = 10;
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** firstIndex */
    private int firstIndex = 1;

	/** lastIndex */
    private int lastIndex = 1;

	/** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    public int getFirstIndex() {
		return firstIndex;
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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

}