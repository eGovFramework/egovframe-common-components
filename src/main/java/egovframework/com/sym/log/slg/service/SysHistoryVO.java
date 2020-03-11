package egovframework.com.sym.log.slg.service;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * @Class Name : SysHistoryVO.java
 * @Description : 시스템 처리 이력관리를 위한 데이터 객체
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 9.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
public class SysHistoryVO extends SysHistory {

	private static final long serialVersionUID = 3236791243469450106L;

	/**
	 * 최초 등록자 명
	 */
	private String frstRegisterNm = "";

	/**
	 * 최종 수정자 명
	 */
	private String lastUpdusrNm = "";

	/**
	 * 등록 구분코드 명
	 */
	private String histSeCodeNm = "";

	/**
	 * 첨부파일ID
	 */
	private String atchFileId = "";
	/**
	 * 검색시작일
	 */
	private String searchBgnDe = "";
	/**
	 * 검색조건
	 */
	private String searchCnd = "";
	/**
	 * 검색종료일
	 */
	private String searchEndDe = "";
	/**
	 * 검색단어
	 */
	private String searchWrd = "";
	/**
	 * 정렬순서(DESC,ASC)
	 */
	private String sortOrdr = "";

	/** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** rowNo  */
	private int rowNo = 0;

    /**
	 * @return the searchUseYn
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}

	/**
	 * @param searchUseYn the searchUseYn to set
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
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



	/**
	 * @return the frstRegisterNm
	 */
	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}

	/**
	 * @param frstRegisterNm the frstRegisterNm to set
	 */
	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	/**
	 * @return the lastUpdusrNm
	 */
	public String getLastUpdusrNm() {
		return lastUpdusrNm;
	}

	/**
	 * @param lastUpdusrNm the lastUpdusrNm to set
	 */
	public void setLastUpdusrNm(String lastUpdusrNm) {
		this.lastUpdusrNm = lastUpdusrNm;
	}

	/**
	 * @return the histSeCodeNm
	 */
	public String getHistSeCodeNm() {
		return histSeCodeNm;
	}

	/**
	 * @param histSeCodeNm the histSeCodeNm to set
	 */
	public void setHistSeCodeNm(String histSeCodeNm) {
		this.histSeCodeNm = histSeCodeNm;
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
	 * @return the searchCnd
	 */
	public String getSearchCnd() {
		return searchCnd;
	}

	/**
	 * @param searchCnd the searchCnd to set
	 */
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
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
	 * @return the searchWrd
	 */
	public String getSearchWrd() {
		return searchWrd;
	}

	/**
	 * @param searchWrd the searchWrd to set
	 */
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	/**
	 * @return the sortOrdr
	 */
	public String getSortOrdr() {
		return sortOrdr;
	}

	/**
	 * @param sortOrdr the sortOrdr to set
	 */
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}



	@Override
	public String getAtchFileId() {
		return atchFileId;
	}

	@Override
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * @return the rowNo
	 */
	public int getRowNo() {
		return rowNo;
	}

	/**
	 * @param rowNo the rowNo to set
	 */
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 *
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
