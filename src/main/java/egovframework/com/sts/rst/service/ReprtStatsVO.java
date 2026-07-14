/**
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2026.05.27  기여자          Lombok @Getter/@Setter 클래스 레벨 적용
 *
 *  </pre>
 */

package egovframework.com.sts.rst.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 개요
 * - 보고서통계에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 보고서통계정보의 목록 항목을 관리한다.
 * </pre>
 *
 * @author lee.m.j
 * @since 2009.08.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.03  lee.m.j       최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.07.02  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.07.02  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *   2026.05.27  기여자          Lombok @Getter/@Setter 클래스 레벨 적용
 *
 *      </pre>
 */
@Getter
@Setter
public class ReprtStatsVO extends ReprtStats {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 보고서유형 */
	private String pmReprtTy;
	/** 기간구분 */
	private String pmDateTy;
	/** 보고서통계 시작일자 */
	private String pmFromDate;
	/** 보고서통계 종료일자 */
	private String pmToDate;
	/** 보고서통계 목록 */
	List<ReprtStatsVO> reprtStatsList;
	/** 보고서통계 상세 목록 */
	List<ReprtStatsVO> reprtStatsDetailList;
	/** 등록일자별 통계 그래프 목록 */
	List<ReprtStatsVO> reprtStatsBarList;
	/** 보고서유형별 통계 그래프 목록 */
	List<ReprtStatsVO> reprtStatsByReprtTyList;
	/** 진행상태별 통계 그래프 목록 */
	List<ReprtStatsVO> reprtStatsByReprtSttusList;

	/** 삭제여부 */
	String[] delYn;

	/** 보고서통계 그래프 사이즈 단위 */
	float maxUnit = 50.0f;
}
