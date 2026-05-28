package egovframework.com.uss.olp.opp.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.opp.service.OnlinePollPartcptn;

/**
 * 온라인POLL참여를 위한 Mapper 인터페이스
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel     @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper
public interface OnlinePollPartcptnMapper {

	/**
	 * 온라인POLL관리 목록을 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollManageList(ComDefaultVO searchVO);

	/**
	 * 온라인POLL관리 목록 총 건수를 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return int
	 */
	int selectOnlinePollManageListCnt(ComDefaultVO searchVO);

	/**
	 * 온라인POLL관리 상세 정보를 조회한다.
	 * @param onlinePollPartcptn - 온라인POLL 참여 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollManageDetail(OnlinePollPartcptn onlinePollPartcptn);

	/**
	 * 온라인POLL항목 상세 정보를 조회한다.
	 * @param onlinePollPartcptn - 온라인POLL 참여 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollItem(OnlinePollPartcptn onlinePollPartcptn);

	/**
	 * 온라인POLL참여 결과를 등록한다.
	 * @param onlinePollPartcptn - 온라인POLL 참여 VO
	 */
	void insertOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn);

	/**
	 * 온라인POLL참여 통계를 조회한다.
	 * @param onlinePollPartcptn - 온라인POLL 참여 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollPartcptnStatistics(OnlinePollPartcptn onlinePollPartcptn);

	/**
	 * 온라인POLL참여 여부를 조회한다.
	 * @param onlinePollPartcptn - 온라인POLL 참여 VO
	 * @return int
	 */
	int selectOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn);

}
