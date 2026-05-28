package egovframework.com.uss.ion.rmm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.ion.rmm.service.RoughMapDefaultVO;
import egovframework.com.uss.ion.rmm.service.RoughMapVO;

/**
 * 건물 위치정보(약도)에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("roughMapMapper")
public interface EgovRoughMapMapper {

	List<EgovMap> selectRoughMapList(RoughMapDefaultVO searchVO);

	int selectRoughMapListTotCnt(RoughMapDefaultVO searchVO);

	RoughMapVO selectRoughMapDetail(RoughMapVO roughMapVO);

	void insertRoughMap(RoughMapVO roughMap);

	void updateRoughMap(RoughMapVO roughMap);

	void deleteRoughMap(RoughMapVO roughMap);
}
