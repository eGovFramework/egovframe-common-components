package egovframework.com.uss.ion.rmm.service;

import java.io.Serializable;

/**
 * 개요
 * - 약도관리에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 약도정보 조회를 위해 필요한 정보를 관리한다.
 *  
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일			수정자		수정내용
 *  -----------		------		---------
 *   2014.08.27		옥찬우		최초 생성
 *
 * </pre>
 */

public class RoughMapDefaultVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 첫페이지 인덱스 */
    private int firstIndex = 1;
    
    /** * 마지막페이지 인덱스 */
    private int lastIndex = 1;
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지 사이즈 */
    private int pageSize = 10;
    
    /** 페이지 개수 */
    private int pageUnit = 10;
    
    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;
    
    /** 검색조건 */
    private String searchCondition = "";
    
    /** 검색단어 */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";

    /**
     * 첫페이지 인덱스를 가져온다.
     * @return int 첫페이지 인덱스
     */
    public int getFirstIndex(){
        return firstIndex;
    }

    /**
     * 첫페이지 인덱스를 저장한다.
     * @param firstIndex
     */
    public void setFirstIndex(int firstIndex){
        this.firstIndex = firstIndex;
    }

    /**
     * 마지막페이지 인덱스를 가져온다.
     * @return int 마지막페이지 인덱스
     */
    public int getLastIndex(){
        return lastIndex;
    }

    /**
     * 마지막페이지 인덱스를 저장한다.
     * @param lastIndex
     */
    public void setLastIndex(int lastIndex){
        this.lastIndex = lastIndex;
    }

    /**
     * 현재페이지를 가져온다.
     * @return int 현재페이지
     */
    public int getPageIndex(){
        return pageIndex;
    }

    /**
     * 현재페이지를 저장한다.
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex){
        this.pageIndex = pageIndex;
    }

    /**
     * 페이지 사이즈를 가져온다.
     * @return int 페이지 사이즈
     */
    public int getPageSize(){
        return pageSize;
    }

    /**
     * 페이지 사이즈를 저장한다.
     * @param pageSize
     */
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    /**
     * 페이지 개수를 가져온다.
     * @return int 페이지 개수
     */
    public int getPageUnit(){
        return pageUnit;
    }

    /**
     * 페이지 개수를 저장한다.
     * @param pageUnit
     */
    public void setPageUnit(int pageUnit){
        this.pageUnit = pageUnit;
    }

    /**
     * 페이지당 레코드 개수를 가져온다.
     * @return int 페이지당 레코드 개수
     */
    public int getRecordCountPerPage(){
        return recordCountPerPage;
    }

    /**
     * 페이지당 레코드 개수를 저장한다.
     * @param recordCountPerPage
     */
    public void setRecordCountPerPage(int recordCountPerPage){
        this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * 검색조건을 가져온다.
     * @return String 검색조건
     */
    public String getSearchCondition(){
        return searchCondition;
    }

    /**
     * 검색조건을 저장한다.
     * @param searchCondition
     */
    public void setSearchCondition(String searchCondition){
        this.searchCondition = searchCondition;
    }

    /**
     * 검색단어를 가져온다.
     * @return String 검색단어
     */
    public String getSearchKeyword(){
        return searchKeyword;
    }

    /**
     * 검색단어를 저장한다.
     * @param searchKeyword
     */
    public void setSearchKeyword(String searchKeyword){
        this.searchKeyword = searchKeyword;
    }

    /**
     * 검색사용여부를 가져온다.
     * @return String 검색사용여부
     */
    public String getSearchUseYn(){
        return searchUseYn;
    }

    /**
     * 검색사용여부를 저장한다.
     * @param searchUseYn
     */
    public void setSearchUseYn(String searchUseYn){
        this.searchUseYn = searchUseYn;
    }
    
}