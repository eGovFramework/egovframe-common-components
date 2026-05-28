package egovframework.com.uss.ion.isg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.isg.service.IntnetSvcGuidanceVO;

/**
 * 인터넷서비스안내에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("intnetSvcGuidanceMapper")
public interface IntnetSvcGuidanceMapper {

	List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	int selectIntnetSvcGuidanceListTotCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	void insertIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	void updateIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	void deleteIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO);
}
