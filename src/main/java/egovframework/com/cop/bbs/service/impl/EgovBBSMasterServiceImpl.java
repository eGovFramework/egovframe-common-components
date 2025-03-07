package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogUserVO;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일			수정자		수정내용
 *  -------			--------	---------------------------
 *   2024.10.29		inganyoyo	Controller는 Transaction 처리를 하지 않아 Controller에서 오류 발생 시 데이터 정합성 오류 문제 발생
 * </pre>
 */

@Service("EgovBBSMasterService")
public class EgovBBSMasterServiceImpl extends EgovAbstractServiceImpl implements EgovBBSMasterService {

	@Resource(name = "EgovBBSMasterDAO")
    private EgovBBSMasterDAO egovBBSMasterDao;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;
	
    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    @Resource(name = "BBSAddedOptionsDAO")
    private BBSAddedOptionsDAO addedOptionsDAO;
    ////-------------------------------
    
  @Resource(name = "egovBlogIdGnrService")
  private EgovIdGnrService idgenServiceBlog;

  @Resource(name = "egovBBSMstrIdGnrService")
  private EgovIdGnrService idgenServiceBbs;

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
	public void updateBBSMasterInf(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateBBSMaster(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}

	@Override
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectBBSMasterDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
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
	public void insertBBSMasterInf(BoardMaster boardMaster) throws Exception {
		
		//2021 github 반영
		//String bbsId = idgenService.getNextStringId();
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId() + RandomStringUtils.randomAlphabetic(10);
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBBSMasterInf(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
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
	public void insertBlogMaster(Blog blog) throws FdlException {
		egovBBSMasterDao.insertBlogMaster(blog);
	}

  @Override
  public void insertBlogMasterAndBoardBlogUserRqst(Blog blog, LoginVO user) throws Exception {

    String blogId = idgenServiceBlog.getNextStringId(); //블로그 아이디 채번
    String bbsId = idgenServiceBbs.getNextStringId(); //게시판 아이디 채번

    blog.setRegistSeCode("REGC02");
    blog.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    blog.setBbsId(bbsId);
    blog.setBlogId(blogId);
    blog.setBlogAt("Y");

    // 블로그 개설자의 정보를 등록한다.
    // 2022.11.11 시큐어코딩 처리
    BlogUserVO blogUserVO = new BlogUserVO();
    blogUserVO.setBlogId(blogId);
    blogUserVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    blogUserVO.setMngrAt("Y");
    blogUserVO.setMberSttus("P");
    blogUserVO.setUseAt("Y");
    blogUserVO.setFrstRegisterId(
        user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    // 블로그 정보와 개설자 정보 등록한다
    // Controller는 Transaction처리를 하지 않아 Controller에서 오류 발생 시 데이터 정합성 오류 문제 발생

    this.insertBlogMaster(blog);
    this.insertBoardBlogUserRqst(blogUserVO);
  }

	@Override
	public BlogVO selectBlogDetail(BlogVO blogVO) throws Exception {
		BlogVO resultVO = egovBBSMasterDao.selectBlogDetail(blogVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception{
		return egovBBSMasterDao.selectBlogListPortlet(blogVO);
	}

	@Override
	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) throws Exception {
		return egovBBSMasterDao.selectBBSListPortlet(boardMasterVO);
	}
	
}
