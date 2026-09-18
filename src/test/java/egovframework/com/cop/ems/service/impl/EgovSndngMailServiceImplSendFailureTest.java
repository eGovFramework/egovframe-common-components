package egovframework.com.cop.ems.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail2.core.EmailException;
import org.apache.commons.mail2.jakarta.EmailAttachment;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.cop.ems.service.EgovMultiPartEmail;
import egovframework.com.cop.ems.service.SndngMailVO;

/**
 * 메일 발송이 실패하면 발송결과코드를 실패로 기록하고 false 를 돌려주는지 확인한다.
 *
 * 호출부(EgovSndngMailRegistServiceImpl)는 반환값이 false 일 때만 실패 처리를 하므로,
 * 예외가 서비스 밖으로 나가면 실패가 기록되지 않는다.
 */
class EgovSndngMailServiceImplSendFailureTest {

	/** 발송 시도마다 EmailException 을 던지는 대역. */
	private static final class ThrowingEmail extends EgovMultiPartEmail {

		private static final long serialVersionUID = 1L;

		@Override
		public String send(String addTo, String subject, String msg) throws EmailException {
			throw new EmailException("send failed");
		}

		@Override
		public String send(String addTo, String subject, String msg, EmailAttachment attachment)
				throws EmailException {
			throw new EmailException("send failed");
		}
	}

	/** updateSndngMail 호출을 기록하는 대역. */
	private static final class CapturingDAO extends SndngMailRegistDAO {

		private final List<SndngMailVO> updated = new ArrayList<>();

		@Override
		public SndngMailVO updateSndngMail(SndngMailVO vo) {
			updated.add(vo);
			return vo;
		}
	}

	@Test
	void sendFailureRecordsFailureCodeAndReturnsFalse() {
		EgovSndngMailServiceImpl service = new EgovSndngMailServiceImpl();
		CapturingDAO dao = new CapturingDAO();
		ReflectionTestUtils.setField(service, "egovMultiPartEmail", new ThrowingEmail());
		ReflectionTestUtils.setField(service, "sndngMailRegistDAO", dao);

		SndngMailVO sndngMailVO = new SndngMailVO();
		sndngMailVO.setRecptnPerson("receiver@example.com");
		sndngMailVO.setSj("제목");
		sndngMailVO.setEmailCn("내용");

		assertFalse(service.sndngMail(sndngMailVO), "발송에 실패하면 false 를 반환해야 한다.");
		assertEquals("F", sndngMailVO.getSndngResultCode(), "발송결과코드가 실패로 기록돼야 한다.");
		assertEquals(1, dao.updated.size(), "실패 상태가 발송메일 정보에 반영돼야 한다.");
	}
}
