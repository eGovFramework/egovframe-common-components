package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Service("EgovArticleService")
public class EgovArticleServiceImpl extends EgovAbstractServiceImpl implements EgovArticleService {

	@Resource(name = "EgovArticleDAO")
    private EgovArticleDAO egovArticleDao;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name = "egovNttIdGnrService")
    private EgovIdGnrService nttIdgenService;
	
	@Override
	public Map<String, Object> selectArticleList(BoardVO boardVO) {
		List<?> list = egovArticleDao.selectArticleList(boardVO);


		int cnt = egovArticleDao.selectArticleListCnt(boardVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public BoardVO selectArticleDetail(BoardVO boardVO) {
	    int iniqireCo = egovArticleDao.selectMaxInqireCo(boardVO);

	    boardVO.setInqireCo(iniqireCo);
	    egovArticleDao.updateInqireCo(boardVO);

		return egovArticleDao.selectArticleDetail(boardVO);
	}
	
	@Override
	public BoardVO selectArticleCnOne(BoardVO boardVO) {
		return egovArticleDao.selectArticleCnOne(boardVO);
	}
	
	@Override
	public List<BoardVO> selectArticleDetailDefault(BoardVO boardVO) {
		return egovArticleDao.selectArticleDetailDefault(boardVO);
	}
	
	@Override
	public int selectArticleDetailDefaultCnt(BoardVO boardVO){
		return egovArticleDao.selectArticleDetailDefaultCnt(boardVO);
	}
	
	@Override
	public List<BoardVO> selectArticleDetailCn(BoardVO boardVO) {
		return egovArticleDao.selectArticleDetailCn(boardVO);
	}

	@Override
	public void insertArticle(Board board) throws FdlException {

		if ("Y".equals(board.getReplyAt())) {
		    // 답글인 경우 1. Parnts를 세팅, 2.Parnts의 sortOrdr을 현재글의 sortOrdr로 가져오도록, 3.nttNo는 현재 게시판의 순서대로
		    // replyLc는 부모글의 ReplyLc + 1

		    board.setNttId(nttIdgenService.getNextIntegerId());	// 답글에 대한 nttId 생성
		    egovArticleDao.replyArticle(board);

		} else {
		    // 답글이 아닌경우 Parnts = 0, replyLc는 = 0, sortOrdr = nttNo(Query에서 처리)
		    board.setParnts("0");
		    board.setReplyLc("0");
		    board.setReplyAt("N");
		    board.setNttId(nttIdgenService.getNextIntegerId());//2011.09.22

		    egovArticleDao.insertArticle(board);
		}
	}

	@Override
	public void updateArticle(Board board) {
		egovArticleDao.updateArticle(board);
	}

	@Override
	public void deleteArticle(Board board) throws Exception {
		FileVO fvo = new FileVO();

		fvo.setAtchFileId(board.getAtchFileId());

		board.setNttSj("이 글은 작성자에 의해서 삭제되었습니다.");

		egovArticleDao.deleteArticle(board);

		if (!"".equals(fvo.getAtchFileId()) || fvo.getAtchFileId() != null) {
		    fileService.deleteAllFileInf(fvo);
		}
		
	}

	@Override
	public List<BoardVO> selectNoticeArticleList(BoardVO boardVO) {
		return egovArticleDao.selectNoticeArticleList(boardVO);
	}
	
	@Override
	public List<BoardVO> selectBlogNmList(BoardVO boardVO) {
		return egovArticleDao.selectBlogNmList(boardVO);
	}

	@Override
	public Map<String, Object> selectGuestArticleList(BoardVO vo) {
		List<?> list = egovArticleDao.selectGuestArticleList(vo);


		int cnt = egovArticleDao.selectGuestArticleListCnt(vo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public int selectLoginUser(BoardVO boardVO){
		return egovArticleDao.selectLoginUser(boardVO);
	}
	
	@Override
	public Map<String, Object> selectBlogListManager(BoardVO vo) {
		List<?> result = egovArticleDao.selectBlogListManager(vo);
		int cnt = egovArticleDao.selectBlogListManagerCnt(vo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

}
