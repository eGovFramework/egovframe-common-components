package egovframework.com.cop.cmt.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.impl.BBSAddedOptionsDAO;
import egovframework.com.cop.bbs.service.impl.EgovArticleDAO;
import egovframework.com.cop.bbs.service.impl.EgovBBSMasterDAO;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 댓글 DAO 단위 테스트
 * 
 * @author 이백행
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, EgovArticleCommentDAOTest.class, })

@Configuration

@ImportResource({

        "classpath*:egovframework/spring/com/idgn/context-idgn-AnswerNo.xml",

        "classpath*:egovframework/spring/com/idgn/context-idgn-bbs.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.cmt.service.impl",

                "egovframework.com.cop.bbs.service.impl",

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                EgovArticleCommentDAO.class,

                                EgovArticleCommentServiceImpl.class,

                                BBSAddedOptionsDAO.class,

                                EgovBBSMasterDAO.class,

                                EgovArticleDAO.class,

                        }

                )

        }

)

@RequiredArgsConstructor
@Slf4j
//@Commit
public class EgovArticleCommentDAOTest extends EgovTestAbstractDAO {

    /**
     * EgovArticleCommentDAO
     */
    @Autowired
    private EgovArticleCommentDAO egovArticleCommentDAO;

    /**
     * 답글 ANSWER_NO 생성
     */
//    @Resource(name = "egovAnswerNoGnrService")
    @Autowired
    @Qualifier("egovAnswerNoGnrService")
    private EgovIdGnrService egovAnswerNoGnrService;

    /**
     * EgovBBSMasterDAO
     */
    @Autowired
    private EgovBBSMasterDAO egovBBSMasterDAO;

    /**
     * EgovArticleDAO
     */
    @Autowired
    private EgovArticleDAO egovArticleDAO;

    /**
     * 게시판용 BBS_ID 생성
     */
    @Autowired
    @Qualifier("egovBBSMstrIdGnrService")
    private EgovIdGnrService egovBBSMstrIdGnrService;

    /**
     * 게시판용 NTT_ID 생성
     */
    @Autowired
    @Qualifier("egovNttIdGnrService")
    private EgovIdGnrService egovNttIdGnrService;

    private void testData(final Comment comment) {
        final BoardMaster boardMaster = new BoardMaster();
        final Board board = new Board();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        if (loginVO != null) {
            boardMaster.setFrstRegisterId(loginVO.getUniqId());
            boardMaster.setLastUpdusrId(loginVO.getUniqId());

            board.setFrstRegisterId(loginVO.getUniqId());
            board.setLastUpdusrId(loginVO.getUniqId());
        }

        try {
            boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
        } catch (FdlException e) {
            log.error("FdlException egovBBSMstrIdGnrService");
        }
        egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

        try {
            board.setNttId(egovNttIdGnrService.getNextLongId());
        } catch (FdlException e) {
            log.error("FdlException egovNttIdGnrService");
        }
        board.setBbsId(boardMaster.getBbsId());
        egovArticleDAO.insertArticle(board);

        comment.setNttId(board.getNttId());
        comment.setBbsId(board.getBbsId());
    }

    private void testData2(final Comment comment) {
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        testData(comment);

        try {
            comment.setCommentNo(String.valueOf(egovAnswerNoGnrService.getNextLongId()));
        } catch (FdlException e) {
            log.error("FdlException egovAnswerNoGnrService");
        }

        comment.setCommentPassword("rhdxhd12");
        comment.setCommentCn("test 이백행 댓글 " + LocalDateTime.now());

        if (loginVO != null) {
            comment.setFrstRegisterId(loginVO.getUniqId());
            comment.setLastUpdusrId(loginVO.getUniqId());

            comment.setWrterId(loginVO.getUniqId());
            comment.setWrterNm(loginVO.getName());
        }

        egovArticleCommentDAO.insertArticleComment(comment);
    }

    /**
     * 댓글 DAO 단위 테스트: 등록
     */
    @Test
//    @Commit
    public void test_a10_insert() {
        // given
        final Comment comment = new Comment();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        testData(comment);

        try {
            comment.setCommentNo(String.valueOf(egovAnswerNoGnrService.getNextLongId()));
        } catch (FdlException e) {
            log.error("FdlException egovAnswerNoGnrService");
        }

        comment.setCommentPassword("rhdxhd12");
        comment.setCommentCn("test 이백행 댓글 " + LocalDateTime.now());

        if (loginVO != null) {
            comment.setFrstRegisterId(loginVO.getUniqId());
            comment.setLastUpdusrId(loginVO.getUniqId());

            comment.setWrterId(loginVO.getUniqId());
            comment.setWrterNm(loginVO.getName());
        }

        // when
        int result = 1;
        try {
            egovArticleCommentDAO.insertArticleComment(comment);
        } catch (DataAccessException e) {
            result = 0;
            error(e);
        }

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 댓글 DAO 단위 테스트: 조회(멀티건)
     */
    @Test
    public void test_a20_selectList() {
        // given
        final CommentVO commentVO = new CommentVO();
        final Comment comment = new Comment();

        testData2(comment);

        commentVO.setBbsId(comment.getBbsId());
        commentVO.setNttId(comment.getNttId());
        commentVO.setSubRecordCountPerPage(1);
        commentVO.setSubFirstIndex(0);

        // when
        final List<CommentVO> results = egovArticleCommentDAO.selectArticleCommentList(commentVO);
        for (final CommentVO result : results) {
            debug(result);
//            debug2(result);

            // then
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), comment.getCommentNo(),
                    result.getCommentNo());
        }
    }

    /* default */ void debug(final CommentVO result) {
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);

            log.debug("getCommentNo={}", result.getCommentNo());
            log.debug("getNttId={}", result.getNttId());
            log.debug("getBbsId={}", result.getBbsId());

            log.debug("getWrterId={}", result.getWrterId());
            log.debug("getWrterNm={}", result.getWrterNm());
            log.debug("getCommentPassword={}", result.getCommentPassword());
            log.debug("getCommentCn={}", result.getCommentCn());

            log.debug("getUseAt={}", result.getUseAt());

            log.debug("getFrstRegisterPnttm={}", result.getFrstRegisterPnttm());
            log.debug("getFrstRegisterNm={}", result.getFrstRegisterNm());
        }
    }

    /* default */ void debug2(final CommentVO result) {
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);

            log.debug("getNttId={}", result.getNttId());
            log.debug("getBbsId={}", result.getBbsId());
            log.debug("getCommentNo={}", result.getCommentNo());

            log.debug("getWrterId={}", result.getWrterId());
            log.debug("getWrterNm={}", result.getWrterNm());
            log.debug("getCommentCn={}", result.getCommentCn());

            log.debug("getUseAt={}", result.getUseAt());

            log.debug("getFrstRegisterPnttm={}", result.getFrstRegisterPnttm());
            log.debug("getFrstRegisterId={}", result.getFrstRegisterId());
            log.debug("getFrstRegisterNm={}", result.getFrstRegisterNm());

            log.debug("getLastUpdusrPnttm={}", result.getLastUpdusrPnttm());
            log.debug("getLastUpdusrId={}", result.getLastUpdusrId());

            log.debug("getCommentPassword={}", result.getCommentPassword());
        }
    }

    /**
     * 댓글 DAO 단위 테스트: 조회(멀티건) 총 수
     */
    @Test
    public void test_a30_selectListTotCnt() {
        // given
        final CommentVO commentVO = new CommentVO();
        final Comment comment = new Comment();

        testData2(comment);

        commentVO.setBbsId(comment.getBbsId());
        commentVO.setNttId(comment.getNttId());

        // when
        final int totCnt = egovArticleCommentDAO.selectArticleCommentListCnt(commentVO);

        log.debug("totCnt={}", totCnt);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, totCnt);
    }

    /**
     * 댓글 DAO 단위 테스트: 조회(단건)
     */
    @Test
    public void test_a40_select() {
        // given
        final CommentVO commentVO = new CommentVO();
        final Comment comment = new Comment();

        testData2(comment);

        commentVO.setCommentNo(comment.getCommentNo());

        // when
        final CommentVO result = egovArticleCommentDAO.selectArticleCommentDetail(commentVO);

        debug2(result);

        // then
        if (result != null) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), commentVO.getCommentNo(),
                    result.getCommentNo());
        }
    }

    /**
     * 댓글 DAO 단위 테스트: 수정
     */
    @Test
//    @Commit
    public void test_a50_update() {
        // given
        final Comment comment = new Comment();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        testData2(comment);

        comment.setCommentCn(comment.getCommentCn() + " > 수정 test 이백행 댓글 " + LocalDateTime.now());

        if (loginVO != null) {
            comment.setLastUpdusrId(loginVO.getUniqId());
        }

        // when
        int result = 1;
        try {
            egovArticleCommentDAO.updateArticleComment(comment);
        } catch (DataAccessException e) {
            result = 0;
            error(e);
        }

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 댓글 DAO 단위 테스트: 삭제
     */
    @Test
//    @Commit
    public void test_a60_delete() {
        // given
        final CommentVO commentVO = new CommentVO();
        final Comment comment = new Comment();

        testData2(comment);

        commentVO.setCommentNo(comment.getCommentNo());

        // when
        int result = 1;
        try {
            egovArticleCommentDAO.deleteArticleComment(commentVO);
        } catch (DataAccessException e) {
            result = 0;
            error(e);
        }

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }

}
