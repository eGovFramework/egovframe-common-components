package egovframework.com.cop.bbs.service;

import java.util.List;
import java.util.Map;

public interface EgovBBSMasterService {

	Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO);

	void deleteBBSMasterInf(BoardMaster boardMaster);

	void updateBBSMasterInf(BoardMaster boardMaster);

	BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) throws Exception;

	Map<String, Object> selectBBSMasterInfs(BoardMasterVO boardMasterVO);

	void insertBBSMasterInf(BoardMaster boardMaster) throws Exception;

	/*
	 * 블로그 관련
	 */
	Map<String, Object> selectBlogMasterInfs(BoardMasterVO boardMasterVO);

	String checkBlogUser(BlogVO blogVO);

	BlogVO checkBlogUser2(BlogVO blogVO);

	void insertBoardBlogUserRqst(BlogUser blogUser);

	void insertBlogMaster(Blog blog);

	BlogVO selectBlogDetail(BlogVO blogVO) throws Exception;

	List<BlogVO> selectBlogListPortlet(BlogVO blogVO);

	List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO);

}
