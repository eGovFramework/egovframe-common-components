package egovframework.com.utl.sys.htm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - HTTP서비스모니터링에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - HTTP서비스모니터링의 목록 항목, 조회조건 등을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:45
 */
@Getter
@Setter
public class HttpMonVO extends HttpMon {

	private static final long serialVersionUID = 4909404979727991138L;

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지개수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

}
