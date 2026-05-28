package egovframework.com.uss.olp.opr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.olp.opr.service.OnlinePollResult;


/**
 * 온라인POLL결과를 위한 Mapper 인터페이스
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
public interface OnlinePollResultMapper {

	/**
	 * 온라인POLL관리 목록을 조회한다.
	 * @param onlinePollResult - 온라인POLL결과 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollManageList(OnlinePollResult onlinePollResult);

	/**
	 * 온라인POLL결과 목록을 조회한다.
	 * @param onlinePollResult - 온라인POLL결과 VO
	 * @return List
	 */
	List<EgovMap> selectOnlinePollResult(OnlinePollResult onlinePollResult);

	/**
	 * 온라인POLL결과를 삭제한다.
	 * @param onlinePollResult - 온라인POLL결과 VO
	 */
	void deleteOnlinePollResult(OnlinePollResult onlinePollResult);

}
