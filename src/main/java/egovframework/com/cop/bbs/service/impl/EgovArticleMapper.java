package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;

/**
 * 게시물 관리를 위한 Mapper 인터페이스
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
public interface EgovArticleMapper {

	/**
	 * 게시물 목록을 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return List - 게시물 목록
	 */
	List<BoardVO> selectArticleList(BoardVO boardVO);

	/**
	 * 게시물 목록 총 개수를 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return int - 게시물 카운트 수
	 */
	int selectArticleListCnt(BoardVO boardVO);

	/**
	 * 최대 조회수를 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return int - 최대 조회수
	 */
	int selectMaxInqireCo(BoardVO boardVO);

	/**
	 * 조회수를 업데이트한다.
	 * @param boardVO - 게시물 VO
	 */
	void updateInqireCo(BoardVO boardVO);

	/**
	 * 게시물 상세 정보를 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return BoardVO - 게시물 VO
	 */
	BoardVO selectArticleDetail(BoardVO boardVO);

	/**
	 * 답글 게시물을 등록한다.
	 * @param board - 게시물 model
	 */
	void replyArticle(Board board);

	/**
	 * 게시물을 신규 등록한다.
	 * @param board - 게시물 model
	 */
	void insertArticle(Board board);

	/**
	 * 게시물을 수정한다.
	 * @param board - 게시물 model
	 */
	void updateArticle(Board board);

	/**
	 * 게시물을 삭제한다.
	 * @param board - 게시물 model
	 */
	void deleteArticle(Board board);

	/**
	 * 공지 게시물 목록을 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return List - 공지 게시물 목록
	 */
	List<BoardVO> selectNoticeArticleList(BoardVO boardVO);

	/**
	 * 방명록 게시물 목록을 조회한다.
	 * @param vo - 게시물 VO
	 * @return List - 방명록 게시물 목록
	 */
	List<BoardVO> selectGuestArticleList(BoardVO vo);

	/**
	 * 방명록 게시물 목록 총 개수를 조회한다.
	 * @param vo - 게시물 VO
	 * @return int - 방명록 게시물 카운트 수
	 */
	int selectGuestArticleListCnt(BoardVO vo);

	/**
	 * 블로그 게시물 본문을 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return BoardVO - 게시물 VO
	 */
	BoardVO selectArticleCnOne(BoardVO boardVO);

	/**
	 * 블로그 게시판 명 목록을 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return List - 게시판 명 목록
	 */
	List<BoardVO> selectBlogNmList(BoardVO boardVO);

	/**
	 * 블로그 관리자용 게시판 목록을 조회한다.
	 * @param vo - 게시물 VO
	 * @return List - 게시판 목록
	 */
	List<BoardMasterVO> selectBlogListManager(BoardVO vo);

	/**
	 * 블로그 관리자용 게시판 목록 총 개수를 조회한다.
	 * @param vo - 게시물 VO
	 * @return int - 게시판 카운트 수
	 */
	int selectBlogListManagerCnt(BoardVO vo);

	/**
	 * 기본 게시물 상세 목록을 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return List - 게시물 목록
	 */
	List<BoardVO> selectArticleDetailDefault(BoardVO boardVO);

	/**
	 * 기본 게시물 상세 목록 총 개수를 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return int - 게시물 카운트 수
	 */
	int selectArticleDetailDefaultCnt(BoardVO boardVO);

	/**
	 * 게시물 본문 상세 목록을 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return List - 게시물 목록
	 */
	List<BoardVO> selectArticleDetailCn(BoardVO boardVO);

	/**
	 * 로그인 사용자 여부를 조회한다.
	 * @param boardVO - 게시물 VO
	 * @return int - 로그인 사용자 카운트 수
	 */
	int selectLoginUser(BoardVO boardVO);

}
