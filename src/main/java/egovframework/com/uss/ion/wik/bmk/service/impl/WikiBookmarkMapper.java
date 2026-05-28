package egovframework.com.uss.ion.wik.bmk.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.wik.bmk.service.WikiBookmark;

/**
 * 위키북마크에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("wikiBookmarkMapper")
public interface WikiBookmarkMapper {

	List<?> selectWikiBookmarkList(WikiBookmark wikiBookmark);

	int selectWikiBookmarkListCnt(WikiBookmark wikiBookmark);

	int selectWikiBookmarkDuplicationCnt(WikiBookmark wikiBookmark);

	String selectWikiBookmarkEmpUniqId(WikiBookmark wikiBookmark);

	void insertWikiBookmark(WikiBookmark wikiBookmark);

	void deleteWikiBookmark(WikiBookmark wikiBookmark);
}
