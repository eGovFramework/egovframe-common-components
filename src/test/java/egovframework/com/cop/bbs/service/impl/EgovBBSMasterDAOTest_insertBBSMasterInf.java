package egovframework.com.cop.bbs.service.impl;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
class EgovBBSMasterDAOTest_insertBBSMasterInf extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	void insertBBSMasterInf() {
		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}

		// when
		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

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
	}

}