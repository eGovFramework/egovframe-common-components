package egovframework.com.uss.ion.rsn.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.rsn.service.RssInfo;

/**
 * RSS서비스에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("rssInfoMapper")
public interface RssMapper {

	List<?> selectRssTagServiceTable(Map<?, ?> param);

	List<?> selectRssTagService(RssInfo rssInfo);

	int selectRssTagServiceCnt(RssInfo rssInfo);

	Map<?, ?> selectRssTagServiceDetail(RssInfo rssInfo);
}
