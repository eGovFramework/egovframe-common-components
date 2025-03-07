package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;

@Repository("EgovBBSMasterDAO")
public class EgovBBSMasterDAO extends EgovComAbstractDAO {

	public List<BoardMasterVO> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		return selectList("BBSMaster.selectBBSMasterList", boardMasterVO);
	}

	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBBSMasterListTotCnt", boardMasterVO);
	}
	
	public BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectBBSMasterDetail", boardMasterVO);
	}

	public void insertBBSMasterInf(BoardMaster boardMaster) {
		insert("BBSMaster.insertBBSMaster", boardMaster);
	}

	public void updateBBSMaster(BoardMaster boardMaster) {
		update("BBSMaster.updateBBSMaster", boardMaster);
	}

	public void deleteBBSMaster(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMaster", boardMaster);
	}
	
	/*
	 * 블로그 관련
	 */
	public List<BlogVO> selectBlogMasterInfs(BoardMasterVO boardMasterVO) {
		return selectList("BBSMaster.selectBlogMasterList", boardMasterVO);
	}
	
	public int selectBlogMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBlogMasterListTotCnt", boardMasterVO);
	}
	
	public int checkExistUser(BlogVO blogVO) {
		return (Integer)selectOne("BBSMaster.checkExistUser", blogVO);
	}
	
	public BlogVO checkExistUser2(BlogVO blogVO) {
		return (BlogVO) selectOne("BBSMaster.checkExistUser2", blogVO);
	}
	
	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		insert("BBSMaster.insertBoardBlogUserRqst", blogUser);
	}
	
	public void insertBlogMaster(Blog blog) {
		insert("BBSMaster.insertBlogMaster", blog);
	}
	
	public BlogVO selectBlogDetail(BlogVO blogVO) {
		return (BlogVO) selectOne("BBSMaster.selectBlogDetail", blogVO);
	}

	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception{
		return selectList("BBSMaster.selectBlogListPortlet", blogVO);
	}

	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) {
		return selectList("BBSMaster.selectBBSListPortlet", boardMasterVO);
	}
}
