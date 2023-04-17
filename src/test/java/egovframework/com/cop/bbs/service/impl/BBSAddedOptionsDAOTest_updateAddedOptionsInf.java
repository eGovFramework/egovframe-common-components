package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { BBSAddedOptionsDAOTest_Configuration.class })
@Rollback(true)
//@Rollback(false)
public class BBSAddedOptionsDAOTest_updateAddedOptionsInf extends EgovTestV1 {

	@Autowired
	private BBSAddedOptionsDAO bbsAddedOptionsDAO;

	@Autowired
	@Qualifier("egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardMaster boardMaster = new BoardMaster();

//		boardMaster.setOption(""); // 댓글여부 N, 만족도여부 N
//		boardMaster.setOption("comment"); // 댓글여부 Y, 만족도여부 N
//		boardMaster.setOption("stsfdg"); // 댓글여부 N, 만족도여부 Y

		boardMaster.setFrstRegisterId("USRCNFRM_00000000000"); // TEST1
	}

	@Test
	public void test2() throws Exception {
		log.debug("test2");

		// given
		BoardMaster boardMaster = new BoardMaster();

		boardMaster.setOption(""); // 댓글여부 N, 만족도여부 N
//		boardMaster.setOption("comment"); // 댓글여부 Y, 만족도여부 N
//		boardMaster.setOption("stsfdg"); // 댓글여부 N, 만족도여부 Y
	}

	@Test
	public void test3() throws Exception {
		log.debug("test3");

		// given
		BoardMaster boardMaster = new BoardMaster();

//		boardMaster.setOption(""); // 댓글여부 N, 만족도여부 N
		boardMaster.setOption("comment"); // 댓글여부 Y, 만족도여부 N
//		boardMaster.setOption("stsfdg"); // 댓글여부 N, 만족도여부 Y
	}

	@Test
	public void test4() throws Exception {
		log.debug("test4");

		// given
		BoardMaster boardMaster = new BoardMaster();

//		boardMaster.setOption(""); // 댓글여부 N, 만족도여부 N
//		boardMaster.setOption("comment"); // 댓글여부 Y, 만족도여부 N
		boardMaster.setOption("stsfdg"); // 댓글여부 N, 만족도여부 Y
	}

	public void test(BoardMaster boardMaster) throws Exception {
		log.debug("test");

		// given
		boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());

		boardMaster.setFrstRegisterId("USRCNFRM_00000000000"); // TEST1

		log.debug("boardMaster={}", boardMaster);

		// when
		bbsAddedOptionsDAO.insertAddedOptionsInf(boardMaster);
		bbsAddedOptionsDAO.updateAddedOptionsInf(boardMaster);
		BoardMasterVO addedOptionsInf = bbsAddedOptionsDAO.selectAddedOptionsInf(boardMaster);

		log.debug("addedOptionsInf={}", addedOptionsInf);
		log.debug("getBbsId={}", addedOptionsInf.getBbsId());
		log.debug("getCommentAt={}", addedOptionsInf.getCommentAt());
		log.debug("getStsfdgAt={}", addedOptionsInf.getStsfdgAt());
		log.debug("getFrstRegisterId={}", addedOptionsInf.getFrstRegisterId());
		log.debug("getFrstRegisterNm={}", addedOptionsInf.getFrstRegisterNm());
		log.debug("getFrstRegisterPnttm={}", addedOptionsInf.getFrstRegisterPnttm());

		// then
		assertEquals(addedOptionsInf.getBbsId(), boardMaster.getBbsId());

		if ("".equals(boardMaster.getOption())) {
			assertEquals(addedOptionsInf.getCommentAt(), "N");
			assertEquals(addedOptionsInf.getStsfdgAt(), "N");
		} else if ("comment".equals(boardMaster.getOption())) {
			assertEquals(addedOptionsInf.getCommentAt(), "Y");
			assertEquals(addedOptionsInf.getStsfdgAt(), "N");
		} else if ("stsfdg".equals(boardMaster.getOption())) {
			assertEquals(addedOptionsInf.getCommentAt(), "N");
			assertEquals(addedOptionsInf.getStsfdgAt(), "Y");
		}
	}

}