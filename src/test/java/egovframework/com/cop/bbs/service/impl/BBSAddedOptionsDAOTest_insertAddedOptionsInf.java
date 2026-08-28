package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.test.EgovAbstractTestJUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BBSAddedOptionsDAOTest_insertAddedOptionsInf extends EgovAbstractTestJUnit {

	@Autowired
	BBSAddedOptionsDAO bbsAddedOptionsDAO;

	@Autowired
	EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	public void test() {
		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		// when
		int result = bbsAddedOptionsDAO.insertAddedOptionsInf(boardMaster);

		log.debug("result={}", result);

		// then
		assertTrue(result > 0);
	}

}