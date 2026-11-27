package egovframework.com.cmm;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name : ComDefaultVO.java
 * @Description : ComDefaultVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    조재영         최초 생성
 *
 *  @author 공통서비스 개발팀 조재영
 *  @since 2009.02.01
 *  @version 1.0
 *  @see
 *
 */
@SuppressWarnings("serial")
public class ComDefaultVO implements Serializable {

	/** 검색조건 */
	@Getter @Setter
    private String searchCondition = "";

    /** 검색Keyword */
	@Getter @Setter
    private String searchKeyword = "";

    /** 검색사용여부 */
	@Getter @Setter
    private String searchUseYn = "";

    /** 현재페이지 */
    private Integer pageIndex = 1;

    /** 페이지개수 */
    private Integer pageUnit = 10;

    /** 페이지사이즈 */
    private Integer pageSize = 10;

    /** firstIndex */
    private Integer firstIndex = 1;

    /** lastIndex */
    private Integer lastIndex = 1;

    /** recordCountPerPage */
    private Integer recordCountPerPage = 10;

    /** 검색KeywordFrom */
	@Getter @Setter
    private String searchKeywordFrom = "";

	/** 검색KeywordTo */
	@Getter @Setter
    private String searchKeywordTo = "";

	public Integer getFirstIndex() {
		return firstIndex == null ? 1 : firstIndex;
	}

	public void setFirstIndex(Integer firstIndex) {
		this.firstIndex = firstIndex == null ? 1 : firstIndex;
	}

	public Integer getLastIndex() {
		return lastIndex == null ? 1 : lastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		this.lastIndex = lastIndex == null ? 1 : lastIndex;
	}

	public Integer getRecordCountPerPage() {
		return recordCountPerPage == null ? 10 : recordCountPerPage;
	}

	public void setRecordCountPerPage(Integer recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage == null ? 10 : recordCountPerPage;
	}

	public Integer getPageIndex() {
        return pageIndex == null ? 1 : pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex == null ? 1 : pageIndex;
    }

    public Integer getPageUnit() {
        return pageUnit == null ? 10 : pageUnit;
    }

    public void setPageUnit(Integer pageUnit) {
        this.pageUnit = pageUnit == null ? 10 : pageUnit;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null ? 10 : pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
