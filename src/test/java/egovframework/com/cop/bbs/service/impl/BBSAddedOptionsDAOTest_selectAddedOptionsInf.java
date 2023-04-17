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
public class BBSAddedOptionsDAOTest_selectAddedOptionsInf extends EgovTestV1 {

	@Autowired
	private BBSAddedOptionsDAO bbsAddedOptionsDAO;

	@Autowired
	@Qualifier("egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardMaster vo = new BoardMaster();
		vo.setBbsId(egovBBSMstrIdGnrService.getNextStringId());

//		vo.setOption(""); // 댓글여부 N, 만족도여부 N
		vo.setOption("comment"); // 댓글여부 Y, 만족도여부 N
//		vo.setOption("stsfdg"); // 댓글여부 N, 만족도여부 Y

		vo.setFrstRegisterId("USRCNFRM_00000000000"); // TEST1

		// when
		bbsAddedOptionsDAO.insertAddedOptionsInf(vo);
		BoardMasterVO addedOptionsInf = bbsAddedOptionsDAO.selectAddedOptionsInf(vo);

		log.debug("addedOptionsInf={}", addedOptionsInf);
		log.debug("getBbsId={}", addedOptionsInf.getBbsId());
		log.debug("getCommentAt={}", addedOptionsInf.getCommentAt());
		log.debug("getStsfdgAt={}", addedOptionsInf.getStsfdgAt());
		log.debug("getFrstRegisterId={}", addedOptionsInf.getFrstRegisterId());
		log.debug("getFrstRegisterNm={}", addedOptionsInf.getFrstRegisterNm());
		log.debug("getFrstRegisterPnttm={}", addedOptionsInf.getFrstRegisterPnttm());

		// then
		assertEquals(addedOptionsInf.getBbsId(), vo.getBbsId());
	}

}