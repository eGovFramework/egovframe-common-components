package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일			수정자		수정내용
 *  -------			--------	---------------------------
 *   2024.10.29		inganyoyo	Transaction 처리 오류 수정(Article)
 * </pre>
 */

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
  @Resource(name = "EgovFileMngUtil")
  private EgovFileMngUtil fileUtil;
  @Resource(name = "EgovFileMngService")
  private EgovFileMngService fileMngService;
	
	@Override
	public Map<String, Object> selectArticleList(BoardVO boardVO) {
		List<BoardVO> list = egovArticleDao.selectArticleList(boardVO);


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
  public void insertArticleAndFiles(Board board, List<MultipartFile> files) throws Exception {
    List<FileVO> result = null;
    String atchFileId = "";

    if (files != null && !files.isEmpty()) {
      result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
      atchFileId = fileMngService.insertFileInfs(result);
    }
    board.setAtchFileId(atchFileId);

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
  public void updateArticleAndFiles(Board board, List<MultipartFile> files, String atchFileId)
      throws Exception {
    if (!files.isEmpty()) {
      if (atchFileId == null || "".equals(atchFileId)) {
        List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
        atchFileId = fileMngService.insertFileInfs(result);
        board.setAtchFileId(atchFileId);
      } else {
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        int cnt = fileMngService.getMaxFileSN(fvo);
        List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
        fileMngService.updateFileInfs(_result);
      }
    }
		
    this.updateArticle(board);
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
		List<BoardVO> list = egovArticleDao.selectGuestArticleList(vo);


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
		List<BoardMasterVO> result = egovArticleDao.selectBlogListManager(vo);
		int cnt = egovArticleDao.selectBlogListManagerCnt(vo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

}
