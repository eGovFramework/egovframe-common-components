package egovframework.com.cop.bbs.service;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface EgovArticleService {

  Map<String, Object> selectArticleList(BoardVO boardVO);

  BoardVO selectArticleDetail(BoardVO boardVO);

  void insertArticleAndFiles(Board board, List<MultipartFile> files) throws Exception;

  void updateArticle(Board board);

  void updateArticleAndFiles(Board board, List<MultipartFile> files, String atchFileId)
      throws Exception;

  void deleteArticle(Board board) throws Exception;

  List<BoardVO> selectNoticeArticleList(BoardVO boardVO);

  Map<String, Object> selectGuestArticleList(BoardVO vo);

  /*
   * 블로그 관련
   */
  BoardVO selectArticleCnOne(BoardVO boardVO);

  List<BoardVO> selectBlogNmList(BoardVO boardVO);

  Map<String, Object> selectBlogListManager(BoardVO boardVO);

  List<BoardVO> selectArticleDetailDefault(BoardVO boardVO);

  int selectArticleDetailDefaultCnt(BoardVO boardVO);

  List<BoardVO> selectArticleDetailCn(BoardVO boardVO);

  int selectLoginUser(BoardVO boardVO);


}
