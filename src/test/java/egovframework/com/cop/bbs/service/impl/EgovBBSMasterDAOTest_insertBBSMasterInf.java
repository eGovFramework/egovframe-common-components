package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
public class EgovBBSMasterDAOTest_insertBBSMasterInf extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	public void test() {
		log.debug("test");

		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		// when
		boolean result = false;
		try {
			egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		boardMaster.setBbsTyCode("BBST01"); // COM101 BBST01 통합게시판
		boardMaster.setBbsNm("");
		boardMaster.setBbsIntrcn("");
		boardMaster.setReplyPosblAt("");
		boardMaster.setFileAtchPosblAt("");
		boardMaster.setAtchPosblFileNumber(0);
		boardMaster.setTmplatId("");
		boardMaster.setUseAt("");
		boardMaster.setCmmntyId("");
		boardMaster.setFrstRegisterId("");
		boardMaster.setBlogId("");
		boardMaster.setBlogAt("");

		// then
		assertEquals(result, true);
	}

}