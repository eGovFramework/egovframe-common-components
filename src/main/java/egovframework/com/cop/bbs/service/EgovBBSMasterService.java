package egovframework.com.cop.bbs.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

import egovframework.com.cmm.LoginVO;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일			수정자		수정내용
 *  -------			--------	---------------------------
 *   2024.10.29		inganyoyo	Controller는 Transaction 처리를 하지 않아 Controller에서 오류 발생 시 데이터 정합성 오류 문제 발생
 * </pre>
 */

public interface EgovBBSMasterService {

	Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO);

	void deleteBBSMasterInf(BoardMaster boardMaster);

	void updateBBSMasterInf(BoardMaster boardMaster) throws Exception;

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
	
	void insertBlogMaster(Blog blog) throws FdlException;

  void insertBlogMasterAndBoardBlogUserRqst(Blog blog, LoginVO user) throws Exception;

	BlogVO selectBlogDetail(BlogVO blogVO) throws Exception;

	List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception;

	List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) throws Exception;

}
