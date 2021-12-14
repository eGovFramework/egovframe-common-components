package egovframework.com.cop.ems.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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
public class SndngMailDtlsDAOTest_selectSndngMailListTotCnt extends EgovTestV1 {

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
	int sndngMailsTotCnt;

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
	}

	void when() {
		try {
			sndngMailsTotCnt = sndngMailDtlsDAO.selectSndngMailListTotCnt(vo);
		} catch (Exception e) {
			log.error("selectSndngMailListTotCnt Exception");
		}
	}

	void then() {
		log.debug("sndngMailsTotCnt={}, {}", sndngMailsTotCnt, 1);

		assertEquals(sndngMailsTotCnt, 1);
	}

}