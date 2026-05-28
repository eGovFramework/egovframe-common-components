package egovframework.com.uss.ion.rss.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.rss.service.RssManage;

/**
 * RSS태그관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("rssTagManageMapper")
public interface RssTagManageMapper {

	List<?> selectRssTagManage(RssManage rssManage);

	int selectRssTagManageCnt(RssManage rssManage);

	RssManage selectRssTagManageDetail(RssManage rssManage);

	void insertRssTagManage(RssManage rssManage);

	void updateRssTagManage(RssManage rssManage);

	void deleteRssTagManage(RssManage rssManage);
}
