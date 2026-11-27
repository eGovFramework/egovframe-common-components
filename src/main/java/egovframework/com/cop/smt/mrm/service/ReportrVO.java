package egovframework.com.cop.smt.mrm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 보고자에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 보고자의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28  장철호          최초 생성
 *   2025.05.26  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 제거
 *
 * </pre>
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class ReportrVO extends Reportr {

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
