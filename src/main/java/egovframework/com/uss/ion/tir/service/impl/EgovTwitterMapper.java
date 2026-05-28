package egovframework.com.uss.ion.tir.service.impl;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

/**
 * 트위터 계정관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("twitterMapper")
public interface EgovTwitterMapper {

	Map<?, ?> selectTwitterAccount(Map<?, ?> param);

	int selectTwitterAccountCheck(Map<?, ?> param);

	void insertTwitterAccount(Map<?, ?> param);

	void updateTwitterAccount(Map<?, ?> param);

	void deleteTwitterAccount(Map<?, ?> param);
}
