package egovframework.com.uss.olp.opm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.opm.service.OnlinePollItem;
import egovframework.com.uss.olp.opm.service.OnlinePollManage;

/**
 * 온라인POLL관리를 위한 Mapper 인터페이스
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
public interface OnlinePollManageMapper {

	/**
	 * 온라인POLL관리 목록을 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollManage(ComDefaultVO searchVO);

	/**
	 * 온라인POLL관리 상세 정보를 조회한다.
	 * @param onlinePollManage - 온라인POLL관리 VO
	 * @return OnlinePollManage
	 */
	OnlinePollManage selectOnlinePollManageDetail(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL관리 목록 총 건수를 조회한다.
	 * @param searchVO - 조회 조건 VO
	 * @return int
	 */
	int selectOnlinePollManageCnt(ComDefaultVO searchVO);

	/**
	 * 온라인POLL관리를 등록한다.
	 * @param onlinePollManage - 온라인POLL관리 VO
	 */
	void insertOnlinePollManage(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL관리를 수정한다.
	 * @param onlinePollManage - 온라인POLL관리 VO
	 */
	void updateOnlinePollManage(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL관리 통계를 조회한다.
	 * @param onlinePollManage - 온라인POLL관리 VO
	 * @return List
	 */
	List<OnlinePollManage> selectOnlinePollManageStatistics(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL결과를 전체 삭제한다(POLL ID 기준).
	 * @param onlinePollManage - 온라인POLL관리 VO
	 */
	void deleteOnlinePollResultAll(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL항목을 전체 삭제한다(POLL ID 기준).
	 * @param onlinePollManage - 온라인POLL관리 VO
	 */
	void deleteOnlinePollItemAll(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL관리를 삭제한다.
	 * @param onlinePollManage - 온라인POLL관리 VO
	 */
	void deleteOnlinePollManage(OnlinePollManage onlinePollManage);

	/**
	 * 온라인POLL항목 목록을 조회한다.
	 * @param onlinePollItem - 온라인POLL항목 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollItem(OnlinePollItem onlinePollItem);

	/**
	 * 온라인POLL항목을 등록한다.
	 * @param onlinePollItem - 온라인POLL항목 VO
	 */
	void insertOnlinePollItem(OnlinePollItem onlinePollItem);

	/**
	 * 온라인POLL항목을 수정한다.
	 * @param onlinePollItem - 온라인POLL항목 VO
	 */
	void updateOnlinePollIteme(OnlinePollItem onlinePollItem);

	/**
	 * 온라인POLL결과를 삭제한다(항목 ID 기준).
	 * @param onlinePollItem - 온라인POLL항목 VO
	 */
	void deleteOnlinePollResultIemid(OnlinePollItem onlinePollItem);

	/**
	 * 온라인POLL항목을 삭제한다.
	 * @param onlinePollItem - 온라인POLL항목 VO
	 */
	void deleteOnlinePollItem(OnlinePollItem onlinePollItem);

}
