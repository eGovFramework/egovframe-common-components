package egovframework.com.ssi.syi.sim.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.ssi.syi.sim.service.SystemCntc;
import egovframework.com.ssi.syi.sim.service.SystemCntcVO;

/**
 * 시스템연계에 대한 매퍼 인터페이스를 정의한다
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("SystemCntcMapper")
public interface SystemCntcMapper {

	/**
	 * 시스템연계를 삭제한다.
	 * @param systemCntc
	 */
	void deleteSystemCntc(SystemCntc systemCntc);

	/**
	 * 시스템연계를 등록한다.
	 * @param systemCntc
	 */
	void insertSystemCntc(SystemCntc systemCntc);

	/**
	 * 시스템연계 상세항목을 조회한다.
	 * @param systemCntc
	 * @return SystemCntc(시스템연계)
	 */
	SystemCntc selectSystemCntcDetail(SystemCntc systemCntc);

	/**
	 * 시스템연계 승인/승인취소한다.
	 * @param systemCntc
	 */
	void confirmSystemCntc(SystemCntc systemCntc);

	/**
	 * 시스템연계 목록을 조회한다.
	 * @param searchVO
	 * @return List(시스템연계 목록)
	 */
	List<EgovMap> selectSystemCntcList(SystemCntcVO searchVO);

	/**
	 * 시스템연계 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(시스템연계 총 개수)
	 */
	int selectSystemCntcListTotCnt(SystemCntcVO searchVO);

	/**
	 * 시스템연계를 수정한다.
	 * @param systemCntc
	 */
	void updateSystemCntc(SystemCntc systemCntc);

}
