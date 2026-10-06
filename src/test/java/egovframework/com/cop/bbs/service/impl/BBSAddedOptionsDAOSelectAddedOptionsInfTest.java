package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@ContextConfiguration(classes = { BBSAddedOptionsDAOConfigTest.class })
@Slf4j
class BBSAddedOptionsDAOSelectAddedOptionsInfTest extends EgovTestV1 {

	@Autowired
	private BBSAddedOptionsDAO bbsAddedOptionsDAO;

	@Autowired
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	void selectAddedOptionsInf() {
		// given
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

//		boardMaster.setOption(""); // 댓글여부 N, 만족도여부 N
		boardMaster.setOption("comment"); // 댓글여부 Y, 만족도여부 N
//		boardMaster.setOption("stsfdg"); // 댓글여부 N, 만족도여부 Y

		boardMaster.setFrstRegisterId("USRCNFRM_00000000000"); // TEST1

		// when
		bbsAddedOptionsDAO.insertAddedOptionsInf(boardMaster);
		BoardMasterVO result = bbsAddedOptionsDAO.selectAddedOptionsInf(boardMaster);

		log.debug("getBbsId={}", result.getBbsId());
		log.debug("getCommentAt={}", result.getCommentAt());
		log.debug("getStsfdgAt={}", result.getStsfdgAt());
		log.debug("getFrstRegisterId={}", result.getFrstRegisterId());
		log.debug("getFrstRegisterNm={}", result.getFrstRegisterNm());
		log.debug("getFrstRegisterPnttm={}", result.getFrstRegisterPnttm());

		// then
		assertEquals(boardMaster.getBbsId(), result.getBbsId());
	}

}