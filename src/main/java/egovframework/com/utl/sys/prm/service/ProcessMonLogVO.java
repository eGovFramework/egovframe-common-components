package egovframework.com.utl.sys.prm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - PROCESS모니터링 로그에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - PROCESS모니터링의 목록 항목, 조회조건 등을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:47
 */
@Getter
@Setter
public class ProcessMonLogVO extends ProcessMonLog {

	private static final long serialVersionUID = -7374180958172370475L;

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 시작일자 조회조건 */
    private String searchBgnDe = "";

    /** 시작시간 조회조건 */
    private String searchBgnHour = "";

    /** 시작일시 조회조건 */
    private String searchBgnDt = "";

    /** 종료일자 조회조건 */
    private String searchEndDe = "";

    /** 종료시간 조회조건 */
    private String searchEndHour = "";

    /** 종료일시 조회조건 */
    private String searchEndDt = "";

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
