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
@ContextConfiguration(classes = { SndngMailDetailDAOTest_Configuration.class })
public class SndngMailDetailDAOTest_selectSndngMail extends EgovTestV1 {

	@Resource(name = "sndngMailDetailDAO")
	private SndngMailDetailDAO sndngMailDetailDAO;

	@Resource(name = "egovMailMsgIdGnrService")
	private EgovIdGnrService egovMailMsgIdGnrService;

	@Resource(name = "sndngMailRegistDAO")
	private SndngMailRegistDAO sndngMailRegistDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

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
		vo = new SndngMailVO();
		try {
			vo.setMssageId(egovMailMsgIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("egovMailMsgIdGnrService FdlException");
		}
		vo.setDsptchPerson("test 발신자" + today);
		vo.setRecptnPerson("test 수신자" + today);
		vo.setSj("test 제목" + today);
		vo.setSndngResultCode("R"); // 발송결과코드(COM024): C(완료), F(실패), R(요청)
		vo.setEmailCn("test 이메일내용" + today);
		vo.setAtchFileId(null); // 첨부파일ID

		try {
			sndngMailRegistDAO.insertSndngMail(vo);
		} catch (Exception e) {
			log.error("insertSndngMail Exception");
		}
	}

	void given() {
		log.debug("getMssageId={}", vo.getMssageId());
	}

	void when() {
		try {
			sndngMail = sndngMailDetailDAO.selectSndngMail(vo);
		} catch (Exception e) {
			log.error("selectSndngMail Exception");
		}
	}

	void then() {
		vo.setSndngResultCode("요청"); // 발송결과코드(COM024): C(완료), F(실패), R(요청)

		log.debug("mssageId={}, {}", sndngMail.getMssageId(), vo.getMssageId());
		log.debug("dsptchPerson={}, {}", sndngMail.getDsptchPerson(), vo.getDsptchPerson());
		log.debug("recptnPerson={}, {}", sndngMail.getRecptnPerson(), vo.getRecptnPerson());
		log.debug("sj={}, {}", sndngMail.getSj(), vo.getSj());
		log.debug("sndngResultCode={}, {}", sndngMail.getSndngResultCode(), vo.getSndngResultCode());
		log.debug("emailCn={}, {}", sndngMail.getEmailCn(), vo.getEmailCn());
		log.debug("atchFileId={}, {}", sndngMail.getAtchFileId(), vo.getAtchFileId());

		assertEquals(sndngMail.getMssageId(), vo.getMssageId());
		assertEquals(sndngMail.getDsptchPerson(), vo.getDsptchPerson());
		assertEquals(sndngMail.getRecptnPerson(), vo.getRecptnPerson());
		assertEquals(sndngMail.getSj(), vo.getSj());
		assertEquals(sndngMail.getSndngResultCode(), vo.getSndngResultCode());
		assertEquals(sndngMail.getEmailCn(), vo.getEmailCn());
		assertEquals(sndngMail.getAtchFileId(), vo.getAtchFileId());
	}

}