package egovframework.com.cop.bbs.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 만족도조사 서비스를 위한 VO 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class SatisfactionVO extends Satisfaction {
    /** 정렬순서(DESC,ASC) */
    private long sortOrdr = 0L;

    /** 현재페이지 */
    private int subPageIndex = 1;

    /** 페이지갯수 */
    private int subPageUnit = 10;

    /** 페이지사이즈 */
    private int subPageSize = 10;

    /** 첫페이지 인덱스 */
    private int subFirstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int subLastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int subRecordCountPerPage = 10;

    /** 레코드 번호 */
    private int subRowNo = 0;
    
    /** 호출 TYPE (head or body)*/
    private String type = "";
    
    /** 수정 처리 여부 */
    private boolean isModified = false;
    
    /**
     * sortOrdr attribute를 리턴한다.
     * @return the sortOrdr
     */
    public long getSortOrdr() {
        return sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     * @param sortOrdr the sortOrdr to set
     */
    public void setSortOrdr(long sortOrdr) {
        this.sortOrdr = sortOrdr;
    }

    /**
     * subPageIndex attribute를 리턴한다.
     * @return the subPageIndex
     */
    public int getSubPageIndex() {
        return subPageIndex;
    }

    /**
     * subPageIndex attribute 값을 설정한다.
     * @param subPageIndex the subPageIndex to set
     */
    public void setSubPageIndex(int subPageIndex) {
        this.subPageIndex = subPageIndex;
    }

    /**
     * subPageUnit attribute를 리턴한다.
     * @return the subPageUnit
     */
    public int getSubPageUnit() {
        return subPageUnit;
    }

    /**
     * subPageUnit attribute 값을 설정한다.
     * @param subPageUnit the subPageUnit to set
     */
    public void setSubPageUnit(int subPageUnit) {
        this.subPageUnit = subPageUnit;
    }

    /**
     * subPageSize attribute를 리턴한다.
     * @return the subPageSize
     */
    public int getSubPageSize() {
        return subPageSize;
    }

    /**
     * subPageSize attribute 값을 설정한다.
     * @param subPageSize the subPageSize to set
     */
    public void setSubPageSize(int subPageSize) {
        this.subPageSize = subPageSize;
    }

    /**
     * subFirstIndex attribute를 리턴한다.
     * @return the subFirstIndex
     */
    public int getSubFirstIndex() {
        return subFirstIndex;
    }

    /**
     * subFirstIndex attribute 값을 설정한다.
     * @param subFirstIndex the subFirstIndex to set
     */
    public void setSubFirstIndex(int subFirstIndex) {
        this.subFirstIndex = subFirstIndex;
    }

    /**
     * subLastIndex attribute를 리턴한다.
     * @return the subLastIndex
     */
    public int getSubLastIndex() {
        return subLastIndex;
    }

    /**
     * subLastIndex attribute 값을 설정한다.
     * @param subLastIndex the subLastIndex to set
     */
    public void setSubLastIndex(int subLastIndex) {
        this.subLastIndex = subLastIndex;
    }

    /**
     * subRecordCountPerPage attribute를 리턴한다.
     * @return the subRecordCountPerPage
     */
    public int getSubRecordCountPerPage() {
        return subRecordCountPerPage;
    }

    /**
     * subRecordCountPerPage attribute 값을 설정한다.
     * @param subRecordCountPerPage the subRecordCountPerPage to set
     */
    public void setSubRecordCountPerPage(int subRecordCountPerPage) {
        this.subRecordCountPerPage = subRecordCountPerPage;
    }

    /**
     * subRowNo attribute를 리턴한다.
     * @return the subRowNo
     */
    public int getSubRowNo() {
        return subRowNo;
    }

    /**
     * subRowNo attribute 값을 설정한다.
     * @param subRowNo the subRowNo to set
     */
    public void setSubRowNo(int subRowNo) {
        this.subRowNo = subRowNo;
    }

    /**
     * type attribute를 리턴한다.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * type attribute 값을 설정한다.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * isModified attribute를 리턴한다.
     * @return the isModified
     */
    public boolean isModified() {
        return isModified;
    }

    /**
     * isModified attribute 값을 설정한다.
     * @param isModified the isModified to set
     */
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
