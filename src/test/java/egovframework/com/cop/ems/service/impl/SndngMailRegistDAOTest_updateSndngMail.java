package egovframework.com.cop.ems.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { SndngMailRegistDAOTest_Configuration.class })
public class SndngMailRegistDAOTest_updateSndngMail extends EgovTestV1 {

	@Resource(name = "sndngMailRegistDAO")
	private SndngMailRegistDAO sndngMailRegistDAO;

	@Resource(name = "egovMailMsgIdGnrService")
	private EgovIdGnrService egovMailMsgIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;

	SndngMailVO sndngMailVO;

	// given
	SndngMailVO vo;

	// when
	SndngMailVO sndngMail;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertSndngMail();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertSndngMail() {
		sndngMailVO = new SndngMailVO();
		try {
			sndngMailVO.setMssageId(egovMailMsgIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("egovMailMsgIdGnrService FdlException");
		}
		sndngMailVO.setDsptchPerson("test 발신자" + today);
		sndngMailVO.setRecptnPerson("test 수신자" + today);
		sndngMailVO.setSj("test 제목" + today);
		sndngMailVO.setSndngResultCode("R"); // 발송결과코드(COM024): C(완료), F(실패), R(요청)
		sndngMailVO.setEmailCn("test 이메일내용" + today);
		sndngMailVO.setAtchFileId(null); // 첨부파일ID

		try {
			sndngMailRegistDAO.insertSndngMail(sndngMailVO);
		} catch (Exception e) {
			log.error("insertSndngMail Exception");
		}
	}

	void given() {
		vo = new SndngMailVO();
		vo.setSndngResultCode("C");
		vo.setMssageId(sndngMailVO.getMssageId());
	}

	void when() {
		try {
			sndngMail = sndngMailRegistDAO.updateSndngMail(vo);
		} catch (Exception e) {
			log.error("updateSndngMail Exception");
		}
	}

	void then() {
		log.debug("atchFileId={}, {}", sndngMail.getSndngResultCode(), vo.getSndngResultCode());
		log.debug("fileSn={}, {}", sndngMail.getMssageId(), vo.getMssageId());

		assertEquals(sndngMail.getSndngResultCode(), vo.getSndngResultCode());
		assertEquals(sndngMail.getMssageId(), vo.getMssageId());
	}

}