package egovframework.com.cop.ems.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { SndngMailDtlsDAOTest_Configuration.class })
public class SndngMailDtlsDAOTest_selectSndngMailList extends EgovTestV1 {

	@Resource(name = "sndngMailDtlsDAO")
	private SndngMailDtlsDAO sndngMailDtlsDAO;

	@Resource(name = "egovMailMsgIdGnrService")
	private EgovIdGnrService egovMailMsgIdGnrService;

	@Resource(name = "sndngMailRegistDAO")
	private SndngMailRegistDAO sndngMailRegistDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	SndngMailVO sndngMailVO;

	// given
	ComDefaultVO vo;

	// when
	List<SndngMailVO> sndngMails;

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
		vo = new ComDefaultVO();

		vo.setSearchCondition("1");
		vo.setSearchKeyword(sndngMailVO.getSj());

//		vo.setSearchCondition("2");
//		vo.setSearchKeyword(sndngMailVO.getEmailCn());

//		vo.setSearchCondition("3");
//		vo.setSearchKeyword(sndngMailVO.getDsptchPerson());

		vo.setRecordCountPerPage(10);
		vo.setFirstIndex(0);
	}

	void when() {
		try {
			sndngMails = sndngMailDtlsDAO.selectSndngMailList(vo);
		} catch (Exception e) {
			log.error("selectSndngMailList Exception");
		}
	}

	void then() {
		log.debug("mssageId={}, {}", sndngMails.get(0).getMssageId(), sndngMailVO.getMssageId());
		log.debug("sndngResultCode={}, {}", sndngMails.get(0).getSndngResultCode(), sndngMailVO.getSndngResultCode());
		log.debug("dsptchPerson={}, {}", sndngMails.get(0).getDsptchPerson(), sndngMailVO.getDsptchPerson());
		log.debug("recptnPerson={}, {}", sndngMails.get(0).getRecptnPerson(), sndngMailVO.getRecptnPerson());
		log.debug("sj={}, {}", sndngMails.get(0).getSj(), sndngMailVO.getSj());
		log.debug("sndngDe={}, {}", sndngMails.get(0).getSndngDe(), sndngMailVO.getSndngDe());
		log.debug("atchFileId={}, {}", sndngMails.get(0).getAtchFileId(), sndngMailVO.getAtchFileId());

		assertEquals(sndngMails.get(0).getMssageId(), sndngMailVO.getMssageId());
//		assertEquals(sndngMails.get(0).getSndngResultCode(), sndngMailVO.getSndngResultCode());
		assertEquals(sndngMails.get(0).getDsptchPerson(), sndngMailVO.getDsptchPerson());
		assertEquals(sndngMails.get(0).getRecptnPerson(), sndngMailVO.getRecptnPerson());
		assertEquals(sndngMails.get(0).getSj(), sndngMailVO.getSj());
//		assertEquals(sndngMails.get(0).getSndngDe(), sndngMailVO.getSndngDe());
		assertEquals(sndngMails.get(0).getAtchFileId(), sndngMailVO.getAtchFileId());
	}

}