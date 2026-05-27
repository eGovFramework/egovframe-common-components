package egovframework.com.cop.scp.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;

/**
 * 개요
 * - 게시글스크랩에 대한 Mapper 인터페이스를 정의한다.
 *
 * 상세내용
 * - 게시글스크랩에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 게시글스크랩의 조회기능은 목록조회, 상세조회로 구분된다.
 */
@EgovMapper
public interface EgovArticleScrapMapper {

	/**
	 * 스크랩목록을 조회한다.
	 * @param scrapVO - 스크랩 VO
	 * @return List - 스크랩 목록
	 */
	List<ScrapVO> selectArticleScrapList(ScrapVO scrapVO);

	/**
	 * 스크랩목록 총 개수를 조회한다.
	 * @param scrapVO - 스크랩 VO
	 * @return int - 스크랩 카운트 수
	 */
	int selectArticleScrapListCnt(ScrapVO scrapVO);

	/**
	 * 스크랩을 등록한다.
	 * @param scrap - 스크랩 모델
	 */
	void insertArticleScrap(Scrap scrap);

	/**
	 * 스크랩 상세내용을 조회한다.
	 * @param scrapVO - 스크랩 VO
	 * @return ScrapVO - 스크랩 VO
	 */
	ScrapVO selectArticleScrapDetail(ScrapVO scrapVO);

	/**
	 * 스크랩을 삭제한다.
	 * @param scrapVO - 스크랩 VO
	 */
	void deleteArticleScrap(ScrapVO scrapVO);

	/**
	 * 스크랩을 수정한다.
	 * @param scrap - 스크랩 모델
	 */
	void updateArticleScrap(Scrap scrap);

}
