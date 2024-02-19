package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;

@Service("EgovBBSMasterService")
public class EgovBBSMasterServiceImpl extends EgovAbstractServiceImpl implements EgovBBSMasterService {

	@Resource(name = "EgovBBSMasterDAO")
	private EgovBBSMasterDAO egovBBSMasterDao;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService idgenService;

	// ---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	// ---------------------------------
	@Resource(name = "BBSAddedOptionsDAO")
	private BBSAddedOptionsDAO addedOptionsDAO;
	//// -------------------------------

	@Override
	public Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBBSMasterInf(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMaster(boardMaster);
	}

	@Override
	public void updateBBSMasterInf(BoardMaster boardMaster) {
		egovBBSMasterDao.updateBBSMaster(boardMaster);

		// ---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		// ---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
			addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}

	@Override
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) {
		BoardMasterVO resultVO = egovBBSMasterDao.selectBBSMasterDetail(boardMasterVO);
		if (resultVO == null) {
			throw new BaseRuntimeException("info.nodata.msg");
		}

		if (EgovComponentChecker.hasComponent("EgovBBSCommentService")
				|| EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")) {// 2011.09.15
			BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);

			if (options != null) {
				if (options.getCommentAt().equals("Y")) {
					resultVO.setOption("comment");
				}

				if (options.getStsfdgAt().equals("Y")) {
					resultVO.setOption("stsfdg");
				}
			} else {
				resultVO.setOption("na"); // 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
			}
		}

		return resultVO;
	}

	@Override
	public Map<String, Object> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		List<BoardMasterVO> result = egovBBSMasterDao.selectBBSMasterInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBBSMasterInfsCnt(boardMasterVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public Map<String, Object> selectBlogMasterInfs(BoardMasterVO boardMasterVO) {
		List<BlogVO> result = egovBBSMasterDao.selectBlogMasterInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBlogMasterInfsCnt(boardMasterVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public void insertBBSMasterInf(BoardMaster boardMaster) {

		// 2021 github 반영
		// String bbsId = idgenService.getNextStringId();
		// 게시판 ID 채번
		String bbsId;
		try {
			bbsId = idgenService.getNextStringId() + RandomStringUtils.randomAlphabetic(10);
		} catch (FdlException e) {
			throw new BaseRuntimeException("FdlException egovBBSMstrIdGnrService", e);
		}
		boardMaster.setBbsId(bbsId);

		egovBBSMasterDao.insertBBSMasterInf(boardMaster);

		// ---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		// ---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
			addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}

	@Override
	public String checkBlogUser(BlogVO blogVO) {

		int userCnt = egovBBSMasterDao.checkExistUser(blogVO);

		if (userCnt == 0) {
			return "";
		} else {
			return "EXIST";
		}
	}

	@Override
	public BlogVO checkBlogUser2(BlogVO blogVO) {
		BlogVO userBlog = egovBBSMasterDao.checkExistUser2(blogVO);
		return userBlog;
	}

	@Override
	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		egovBBSMasterDao.insertBoardBlogUserRqst(blogUser);
	}

	@Override
	public void insertBlogMaster(Blog blog) {
		egovBBSMasterDao.insertBlogMaster(blog);
	}

	@Override
	public BlogVO selectBlogDetail(BlogVO blogVO) {
		BlogVO resultVO = egovBBSMasterDao.selectBlogDetail(blogVO);
		if (resultVO == null) {
			throw new BaseRuntimeException("info.nodata.msg");
		}
		return resultVO;
	}

	@Override
	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) {
		return egovBBSMasterDao.selectBlogListPortlet(blogVO);
	}

	@Override
	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) {
		return egovBBSMasterDao.selectBBSListPortlet(boardMasterVO);
	}

}
