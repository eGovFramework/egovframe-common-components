package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 커뮤니티 관리를 위한 VO 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.4.2  이삼섭          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BlogVO extends Blog implements Serializable {

    /** 검색시작일 */
    private String searchBgnDe = "";
    
    /** 검색조건 */
    private String searchCnd = "";
    
    /** 검색종료일 */
    private String searchEndDe = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 정렬순서(DESC,ASC) */
    private long sortOrdr = 0L;

    /** 검색사용여부 */
    private String searchUseYn = "";

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

    /** 레코드 번호 */
    private int rowNo = 0;

    /** 등록구분 코드명 */
    private String registSeCodeNm = "";

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 게시판 아이드 */
    private String bbsId = "";

    /** 게시판 이름 */
    private String bbsNm = "";
    
    /** 제공 URL */
    private String provdUrl = "";
    
    private String blogId = "";
    

    /**
     * searchBgnDe attribute를 리턴한다.
     * 
     * @return the searchBgnDe
     */
    public String getSearchBgnDe() {
	return searchBgnDe;
    }

    /**
     * searchBgnDe attribute 값을 설정한다.
     * 
     * @param searchBgnDe
     *            the searchBgnDe to set
     */
    public void setSearchBgnDe(String searchBgnDe) {
	this.searchBgnDe = searchBgnDe;
    }

    /**
     * searchCnd attribute를 리턴한다.
     * 
     * @return the searchCnd
     */
    public String getSearchCnd() {
	return searchCnd;
    }

    /**
     * searchCnd attribute 값을 설정한다.
     * 
     * @param searchCnd
     *            the searchCnd to set
     */
    public void setSearchCnd(String searchCnd) {
	this.searchCnd = searchCnd;
    }

    /**
     * searchEndDe attribute를 리턴한다.
     * 
     * @return the searchEndDe
     */
    public String getSearchEndDe() {
	return searchEndDe;
    }

    /**
     * searchEndDe attribute 값을 설정한다.
     * 
     * @param searchEndDe
     *            the searchEndDe to set
     */
    public void setSearchEndDe(String searchEndDe) {
	this.searchEndDe = searchEndDe;
    }

    /**
     * searchWrd attribute를 리턴한다.
     * 
     * @return the searchWrd
     */
    public String getSearchWrd() {
	return searchWrd;
    }

    /**
     * searchWrd attribute 값을 설정한다.
     * 
     * @param searchWrd
     *            the searchWrd to set
     */
    public void setSearchWrd(String searchWrd) {
	this.searchWrd = searchWrd;
    }

    /**
     * sortOrdr attribute를 리턴한다.
     * 
     * @return the sortOrdr
     */
    public long getSortOrdr() {
	return sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     * 
     * @param sortOrdr
     *            the sortOrdr to set
     */
    public void setSortOrdr(long sortOrdr) {
	this.sortOrdr = sortOrdr;
    }

    /**
     * searchUseYn attribute를 리턴한다.
     * 
     * @return the searchUseYn
     */
    public String getSearchUseYn() {
	return searchUseYn;
    }

    /**
     * searchUseYn attribute 값을 설정한다.
     * 
     * @param searchUseYn
     *            the searchUseYn to set
     */
    public void setSearchUseYn(String searchUseYn) {
	this.searchUseYn = searchUseYn;
    }

    /**
     * pageIndex attribute를 리턴한다.
     * 
     * @return the pageIndex
     */
    public int getPageIndex() {
	return pageIndex;
    }

    /**
     * pageIndex attribute 값을 설정한다.
     * 
     * @param pageIndex
     *            the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
	this.pageIndex = pageIndex;
    }

    /**
     * pageUnit attribute를 리턴한다.
     * 
     * @return the pageUnit
     */
    public int getPageUnit() {
	return pageUnit;
    }

    /**
     * pageUnit attribute 값을 설정한다.
     * 
     * @param pageUnit
     *            the pageUnit to set
     */
    public void setPageUnit(int pageUnit) {
	this.pageUnit = pageUnit;
    }

    /**
     * pageSize attribute를 리턴한다.
     * 
     * @return the pageSize
     */
    public int getPageSize() {
	return pageSize;
    }

    /**
     * pageSize attribute 값을 설정한다.
     * 
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * firstIndex attribute를 리턴한다.
     * 
     * @return the firstIndex
     */
    public int getFirstIndex() {
	return firstIndex;
    }

    /**
     * firstIndex attribute 값을 설정한다.
     * 
     * @param firstIndex
     *            the firstIndex to set
     */
    public void setFirstIndex(int firstIndex) {
	this.firstIndex = firstIndex;
    }

    /**
     * lastIndex attribute를 리턴한다.
     * 
     * @return the lastIndex
     */
    public int getLastIndex() {
	return lastIndex;
    }

    /**
     * lastIndex attribute 값을 설정한다.
     * 
     * @param lastIndex
     *            the lastIndex to set
     */
    public void setLastIndex(int lastIndex) {
	this.lastIndex = lastIndex;
    }

    /**
     * recordCountPerPage attribute를 리턴한다.
     * 
     * @return the recordCountPerPage
     */
    public int getRecordCountPerPage() {
	return recordCountPerPage;
    }

    /**
     * recordCountPerPage attribute 값을 설정한다.
     * 
     * @param recordCountPerPage
     *            the recordCountPerPage to set
     */
    public void setRecordCountPerPage(int recordCountPerPage) {
	this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * rowNo attribute를 리턴한다.
     * 
     * @return the rowNo
     */
    public int getRowNo() {
	return rowNo;
    }

    /**
     * rowNo attribute 값을 설정한다.
     * 
     * @param rowNo
     *            the rowNo to set
     */
    public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
    }

    /**
     * registSeCodeNm attribute를 리턴한다.
     * 
     * @return the registSeCodeNm
     */
    public String getRegistSeCodeNm() {
	return registSeCodeNm;
    }

    /**
     * registSeCodeNm attribute 값을 설정한다.
     * 
     * @param registSeCodeNm
     *            the registSeCodeNm to set
     */
    public void setRegistSeCodeNm(String registSeCodeNm) {
	this.registSeCodeNm = registSeCodeNm;
    }

    /**
     * frstRegisterNm attribute를 리턴한다.
     * 
     * @return the frstRegisterNm
     */
    public String getFrstRegisterNm() {
	return frstRegisterNm;
    }

    /**
     * frstRegisterNm attribute 값을 설정한다.
     * 
     * @param frstRegisterNm
     *            the frstRegisterNm to set
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
	this.frstRegisterNm = frstRegisterNm;
    }

    /**
     * bbsId attribute를 리턴한다.
     * 
     * @return the bbsId
     */
    public String getBbsId() {
	return bbsId;
    }

    /**
     * bbsId attribute 값을 설정한다.
     * 
     * @param bbsId
     *            the bbsId to set
     */
    public void setBbsId(String bbsId) {
	this.bbsId = bbsId;
    }

 
    public String getBlogId() {
    	return blogId;
        }

        /**
         * bbsId attribute 값을 설정한다.
         * 
         * @param bbsId
         *            the bbsId to set
         */
        public void setBlogId(String blogId) {
    	this.blogId = blogId;
        }
        
    /**
     * bbsNm attribute를 리턴한다.
     * 
     * @return the bbsNm
     */
    public String getBbsNm() {
	return bbsNm;
    }

    /**
     * bbsNm attribute 값을 설정한다.
     * 
     * @param bbsNm
     *            the bbsNm to set
     */
    public void setBbsNm(String bbsNm) {
	this.bbsNm = bbsNm;
    }

    /**
     * provdUrl attribute를 리턴한다.
     * @return the provdUrl
     */
    public String getProvdUrl() {
        return provdUrl;
    }

    /**
     * provdUrl attribute 값을 설정한다.
     * @param provdUrl the provdUrl to set
     */
    public void setProvdUrl(String provdUrl) {
        this.provdUrl = provdUrl;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
