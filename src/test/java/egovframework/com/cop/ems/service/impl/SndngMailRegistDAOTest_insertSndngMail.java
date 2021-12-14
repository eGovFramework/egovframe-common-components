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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { SndngMailRegistDAOTest_Configuration.class })
public class SndngMailRegistDAOTest_insertSndngMail extends EgovTestV1 {

	@Resource(name = "sndngMailRegistDAO")
	private SndngMailRegistDAO sndngMailRegistDAO;

	@Resource(name = "egovMailMsgIdGnrService")
	private EgovIdGnrService egovMailMsgIdGnrService;

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;

	// testData
	String today;
	LoginVO authenticatedUser;

	// given
	SndngMailVO vo;

	// when
	boolean result = false;
	SndngMailVO sndngMailVO;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		int pageUnit = propertiesService.getInt("pageUnit");
		int pageSize = propertiesService.getInt("pageSize");
		log.debug("pageUnit={}", pageUnit);
		log.debug("pageSize={}", pageSize);
	}

	void given() {
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
	}

	void when() {
		try {
			sndngMailVO = sndngMailRegistDAO.insertSndngMail(vo);
			result = true;
		} catch (Exception e) {
			log.error("insertSndngMail Exception");
		}
	}

	void then() {
		log.debug("result={}, {}", result, true);

		assertEquals(result, true);

		log.debug("sndngMailVO={}", sndngMailVO);
//		log.debug("getMssageId={}, {}", sndngMailVO.getMssageId(), null);
		log.debug("getMssageId={}, {}", sndngMailVO.getMssageId(), vo.getMssageId());

//		assertEquals(sndngMailVO.getMssageId(), null);
		assertEquals(sndngMailVO.getMssageId(), vo.getMssageId());
	}

}