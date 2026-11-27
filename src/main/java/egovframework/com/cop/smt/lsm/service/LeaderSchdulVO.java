package egovframework.com.cop.smt.lsm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 간부일정에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 간부일정의 목록 항목, 조회조건 등을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:06
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
public class LeaderSchdulVO extends LeaderSchdul {

    /** 월별/주별/일별 일정조회 조회조건 */
    private String searchMode;
    /** 월 조회조건 */
    private String searchMonth;
    /** 시작일자 조회조건 */
    private String searchBgnDe;
    /** 종료일자 조회조건 */
    private String searchEndDe;
    /** 일자 조회조건 */
    private String searchDay;
    /** 년 조회조건 */
    private String year;
    /** 월 조회조건 */
    private String month;
    /** 주 조회조건 */
    private String week;
    /** 일 조회조건 */
    private String day;
    /** 검색조건 */
    private String searchCondition;
    /** 검색단어 */
    private String searchKeyword;
    /** 보조검색단어 */
    private String searchKeywordEx;

}
