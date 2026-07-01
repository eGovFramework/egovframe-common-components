package egovframework.com.cop.smt.mtm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 메모할일에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 메모할일의 목록 항목, 조회조건 등을 관리한다.
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
@Getter
@Setter
@SuppressWarnings("serial")
public class MemoTodoVO extends MemoTodo {

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

}
