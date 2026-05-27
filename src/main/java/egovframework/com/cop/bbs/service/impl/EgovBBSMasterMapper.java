package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;

/**
 * 게시판 마스터 관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("egovBBSMasterMapper")
public interface EgovBBSMasterMapper {

	List<BoardMasterVO> selectBBSMasterInfs(BoardMasterVO boardMasterVO);

	int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO);

	BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO);

	void insertBBSMasterInf(BoardMaster boardMaster);

	void updateBBSMaster(BoardMaster boardMaster);

	void deleteBBSMaster(BoardMaster boardMaster);

	List<BlogVO> selectBlogMasterInfs(BoardMasterVO boardMasterVO);

	int selectBlogMasterInfsCnt(BoardMasterVO boardMasterVO);

	int checkExistUser(BlogVO blogVO);

	BlogVO checkExistUser2(BlogVO blogVO);

	void insertBoardBlogUserRqst(BlogUser blogUser);

	void insertBlogMaster(Blog blog);

	BlogVO selectBlogDetail(BlogVO blogVO);

	List<BlogVO> selectBlogListPortlet(BlogVO blogVO);

	List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO);

}
