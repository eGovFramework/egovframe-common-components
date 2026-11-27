package egovframework.com.uss.ion.ism.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 결재자에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 결재자의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class SanctnerVO extends Sanctner {

	/** 검색조건 */
    private String searchCnd = "";

    /** 검색단어 */
    private String searchWrd = "";

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
