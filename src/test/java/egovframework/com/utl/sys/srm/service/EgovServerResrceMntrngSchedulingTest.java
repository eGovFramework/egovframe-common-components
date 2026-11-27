package egovframework.com.utl.sys.srm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * EgovServerResrceMntrngScheduling.sendEmail 단위 테스트.
 *
 * <p>Spring/DB/메일서버 없이 동작한다. MailSender 를 발송 메시지를 캡처하는
 * 페이크로 주입하여, 전달한 ServerResrceMntrng 의 값이 메일 본문에 반영되는지 검증한다.</p>
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일      	         수정자              수정내용
 *  ----------   ---------   ---------------------------
 *  2026.06.18   z3rotig4r           sendEmail NPE 회귀 방지 테스트 추가
 *
 * </pre>
 */
class EgovServerResrceMntrngSchedulingTest {

	/** 발송 요청된 메시지를 캡처하는 페이크 MailSender. */
	private static class CapturingMailSender implements MailSender {
		private final List<SimpleMailMessage> sent = new ArrayList<>();

		@Override
		public void send(SimpleMailMessage simpleMessage) {
			sent.add(simpleMessage);
		}

		@Override
		public void send(SimpleMailMessage... simpleMessages) {
			for (SimpleMailMessage m : simpleMessages) {
				sent.add(m);
			}
		}
	}

	private EgovServerResrceMntrngScheduling scheduling;
	private CapturingMailSender mailSender;

	@BeforeEach
	void setUp() throws Exception {
		scheduling = new EgovServerResrceMntrngScheduling();
		mailSender = new CapturingMailSender();

		// context-mail.xml 의 mntrngMessage 빈과 동일한 템플릿
		SimpleMailMessage template = new SimpleMailMessage();
		template.setFrom("SYSTEM < orgpig@naver.com >");
		template.setSubject("{모니터링종류} 상태통보.");
		template.setText("* {모니터링종류}  상태통보.\r{에러내용}");

		setField("mntrngMessage", template);
		setField("mntrngMailSender", mailSender);
	}

	private void setField(String name, Object value) throws Exception {
		Field field = EgovServerResrceMntrngScheduling.class.getDeclaredField(name);
		field.setAccessible(true);
		field.set(scheduling, value);
	}

	private ServerResrceMntrng buildTarget() {
		ServerResrceMntrng target = new ServerResrceMntrng();
		target.setServerNm("WAS-01");
		target.setServerEqpmnIp("192.168.0.10");
		target.setCpuUseRt("95");
		target.setMoryUseRt("92");
		target.setLogInfo("적정수치를 초과하였습니다.");
		target.setCreatDt("20260618120000");
		target.setMngrEamilAddr("admin@example.com");
		return target;
	}

	/**
	 * 수정 후: 파라미터로 전달한 모니터링 정보가 메일 본문/수신자에 반영되어야 한다.
	 * (수정 전에는 항상 null 인 인스턴스 필드를 읽어 NPE 가 발생했다.)
	 */
	@Test
	void sendEmail_파라미터값이_메일본문에_반영된다() {
		scheduling.sendEmail(buildTarget());

		assertEquals(1, mailSender.sent.size(), "메일이 1건 발송되어야 한다");
		SimpleMailMessage msg = mailSender.sent.get(0);

		assertNotNull(msg.getTo());
		assertEquals("admin@example.com", msg.getTo()[0]);
		assertEquals("서버자원서비스모니터링 상태통보.", msg.getSubject());

		String text = msg.getText();
		assertNotNull(text);
		assertTrue(text.contains("WAS-01"), "서버명이 본문에 포함되어야 한다");
		assertTrue(text.contains("192.168.0.10"), "서버IP가 본문에 포함되어야 한다");
		assertTrue(text.contains("95"), "CPU사용률이 본문에 포함되어야 한다");
		assertTrue(text.contains("92"), "메모리사용률이 본문에 포함되어야 한다");
		assertTrue(text.contains("적정수치를 초과하였습니다."), "로그정보가 본문에 포함되어야 한다");
		assertTrue(text.contains("서버자원 서비스 상태가 비정상입니다."), "비정상 안내문이 포함되어야 한다");
	}

	/**
	 * 페이크 MailSender 가 MailMessage 가 아닌 SimpleMailMessage 시그니처로 호출됨을 보증한다.
	 * (회귀 시 send 미호출/예외를 조기에 드러낸다.)
	 */
	@Test
	void sendEmail_send가_정확히_한번_호출된다() {
		scheduling.sendEmail(buildTarget());

		assertEquals(1, mailSender.sent.size());
		MailMessage msg = mailSender.sent.get(0);
		assertNotNull(msg);
	}
}
