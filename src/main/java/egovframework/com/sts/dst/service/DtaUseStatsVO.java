/**
 * 개요
 * -자료이용현황 통계에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 자료이용현황 통계정보의 목록 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 08-9-2009 오후 1:40:19
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2026.05.27  기여자          Lombok @Getter/@Setter 적용
 *
 *  </pre>
 */

package egovframework.com.sts.dst.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtaUseStatsVO extends DtaUseStats {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 기간구분
	 */
	private String pmDateTy;
	/**
	 * 자료이용현황 통계 시작일자
	 */
	private String pmFromDate;
	/**
	 * 자료이용현황 통계 종료일자
	 */
	private String pmToDate;
	/**
	 * 자료이용현황 통계
	 */
	List<DtaUseStatsVO> dtaUseStatsList;
	/**
	 * 등록일자별 통계 그래프 목록
	 */
	List<DtaUseStatsVO> dtaUseStatsBarList;
	/**
	 * 등록일자별 통계 그래프 사이즈 단위
	 */
	float maxUnit = 50.0f;
}
