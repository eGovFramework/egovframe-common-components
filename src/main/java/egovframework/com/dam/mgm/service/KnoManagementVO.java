package egovframework.com.dam.mgm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 지식정보에 대한 VO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보의 목록 항목 및 조회 조건 등을 관리한다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:49
 */
@Getter
@Setter
public class KnoManagementVO extends KnoManagement {

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 페이지개수 */
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

}