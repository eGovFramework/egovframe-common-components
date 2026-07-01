package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시물 관리를 위한 VO 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------      --------    ---------------------------
 *   2009.3.19  이삼섭          최초 생성
 *   2009.06.29  한성곤		2단계 기능 추가 (댓글관리, 만족도조사)
 *
 * </pre>
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class BoardVO extends Board implements Serializable {

    /** 검색시작일 */
    private String searchBgnDe = "";

    /** 검색조건 */
    private String searchCnd = "";

    /** 검색종료일 */
    private String searchEndDe = "";

    /** 검색단어 */
    private String searchWrd = "";

    /** 정렬순서(DESC,ASC) */
    private Long sortOrdr = 0L;

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private Integer pageIndex = 1;

    /** 페이지개수 */
    private Integer pageUnit = 10;

    /** 페이지사이즈 */
    private Integer pageSize = 10;

    /** 첫페이지 인덱스 */
    private Integer firstIndex = 1;

    /** 마지막페이지 인덱스 */
    private Integer lastIndex = 1;

    /** 페이지당 레코드 개수 */
    private Integer recordCountPerPage = 10;

    /** 레코드 번호 */
    private int rowNo = 0;

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 최종 수정자명 */
    private String lastUpdusrNm = "";

    /** 유효여부 */
    private String isExpired = "N";

    /** 상위 정렬 순서 */
    private String parntsSortOrdr = "";

    /** 상위 답변 위치 */
    private String parntsReplyLc = "";

    /** 게시판 유형코드 */
    private String bbsTyCode = "";

    /** 게시판 속성코드 */
    private String bbsAttrbCode = "";

    /** 게시판 명 */
    private String bbsNm = "";

    /** 파일첨부가능여부 */
    private String fileAtchPosblAt = "";

    /** 첨부가능파일숫자 */
    private int posblAtchFileNumber = 0;

    /** 답장가능여부 */
    private String replyPosblAt = "";

    /** 조회 수 증가 여부 */
    private boolean plusCount = false;

    /** 익명등록 여부 */
    private String anonymousAt = "";

    /** 하위 페이지 인덱스 (댓글 및 만족도 조사 여부 확인용) */
    private String subPageIndex = "";

    /** 게시글 댓글개수 */
    private String commentCo = "";

    /** 볼드체 여부 */
    private String sjBoldAt;

    /** 공지 여부 */
    private String noticeAt;

    /** 비밀글 여부 */
    private String secretAt;

    /**
     * sortOrdr attribute를 리턴한다.
     *
     * @return the sortOrdr
     */
    public Long getSortOrdr() {
	return sortOrdr == null ? 0L : sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     *
     * @param sortOrdr
     *            the sortOrdr to set
     */
    public void setSortOrdr(Long sortOrdr) {
	this.sortOrdr = sortOrdr == null ? 0L : sortOrdr;
    }

    /**
     * firstIndex attribute를 리턴한다.
     *
     * @return the firstIndex
     */
    public Integer getFirstIndex() {
    	return firstIndex == null ? 1 : firstIndex;
    }

    /**
     * firstIndex attribute 값을 설정한다.
     *
     * @param firstIndex
     *            the firstIndex to set
     */
    public void setFirstIndex(Integer firstIndex) {
    	this.firstIndex = firstIndex == null ? 1 : firstIndex;
    }

    /**
     * lastIndex attribute를 리턴한다.
     *
     * @return the lastIndex
     */
    public Integer getLastIndex() {
    	return lastIndex == null ? 1 : lastIndex;
    }

    /**
     * lastIndex attribute 값을 설정한다.
     *
     * @param lastIndex
     *            the lastIndex to set
     */
    public void setLastIndex(Integer lastIndex) {
    	this.lastIndex = lastIndex == null ? 1 : lastIndex;
    }

    /**
     * recordCountPerPage attribute를 리턴한다.
     *
     * @return the recordCountPerPage
     */
    public Integer getRecordCountPerPage() {
    	return recordCountPerPage == null ? 10 : recordCountPerPage;
    }

    /**
     * recordCountPerPage attribute 값을 설정한다.
     *
     * @param recordCountPerPage
     *            the recordCountPerPage to set
     */
    public void setRecordCountPerPage(Integer recordCountPerPage) {
    	this.recordCountPerPage = recordCountPerPage == null ? 10 : recordCountPerPage;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

}
