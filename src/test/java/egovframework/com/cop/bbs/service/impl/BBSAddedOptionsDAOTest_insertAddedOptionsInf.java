package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { BBSAddedOptionsDAOTest_Configuration.class })
public class BBSAddedOptionsDAOTest_insertAddedOptionsInf extends EgovTestV1 {

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
		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());

		// when
		String addedOptionsInf = bbsAddedOptionsDAO.insertAddedOptionsInf(boardMaster);

		log.debug("addedOptionsInf={}", addedOptionsInf);

		// then
		assertEquals(addedOptionsInf, "1");
	}

}