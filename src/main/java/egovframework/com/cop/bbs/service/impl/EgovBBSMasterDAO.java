package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import jakarta.annotation.Resource;

/**
 * 게시판 마스터 관리에 대한 DAO 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("EgovBBSMasterDAO")
public class EgovBBSMasterDAO {

	@Resource(name = "egovBBSMasterMapper")
	private EgovBBSMasterMapper mapper;

	public List<BoardMasterVO> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		return mapper.selectBBSMasterInfs(boardMasterVO);
	}

	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return mapper.selectBBSMasterInfsCnt(boardMasterVO);
	}

	public BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO) {
		return mapper.selectBBSMasterDetail(boardMasterVO);
	}

	public void insertBBSMasterInf(BoardMaster boardMaster) {
		mapper.insertBBSMasterInf(boardMaster);
	}

	public void updateBBSMaster(BoardMaster boardMaster) {
		mapper.updateBBSMaster(boardMaster);
	}

	public void deleteBBSMaster(BoardMaster boardMaster) {
		mapper.deleteBBSMaster(boardMaster);
	}

	/*
	 * 블로그 관련
	 */
	public List<BlogVO> selectBlogMasterInfs(BoardMasterVO boardMasterVO) {
		return mapper.selectBlogMasterInfs(boardMasterVO);
	}

	public int selectBlogMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return mapper.selectBlogMasterInfsCnt(boardMasterVO);
	}

	public int checkExistUser(BlogVO blogVO) {
		return mapper.checkExistUser(blogVO);
	}

	public BlogVO checkExistUser2(BlogVO blogVO) {
		return mapper.checkExistUser2(blogVO);
	}

	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		mapper.insertBoardBlogUserRqst(blogUser);
	}

	public void insertBlogMaster(Blog blog) {
		mapper.insertBlogMaster(blog);
	}

	public BlogVO selectBlogDetail(BlogVO blogVO) {
		return mapper.selectBlogDetail(blogVO);
	}

	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception {
		return mapper.selectBlogListPortlet(blogVO);
	}

	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) {
		return mapper.selectBBSListPortlet(boardMasterVO);
	}

}
