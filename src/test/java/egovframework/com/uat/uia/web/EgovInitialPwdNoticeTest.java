package egovframework.com.uat.uia.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.jupiter.api.Test;

/**
 * 초기 비밀번호 변경 안내 화면 고정 테스트
 *
 * <p>초기 비밀번호 사용 중일 때는 안내 팝업을 닫거나 "다음에 변경" 할 수 없어야 한다. 화면은 JSP 라 컨테이너 없이
 * 렌더링할 수 없으므로, 그 계약이 되는 부분을 소스로 고정한다.</p>
 */
class EgovInitialPwdNoticeTest {

	private static final String UNIT_CONTENT_JSP = "src/main/webapp/WEB-INF/jsp/egovframework/com/cmm/EgovUnitContent.jsp";
	private static final String EXPIRE_PWD_JSP = "src/main/webapp/WEB-INF/jsp/egovframework/com/uat/uia/EgovExpirePwd.jsp";
	private static final String MESSAGE_DIR = "src/main/resources/egovframework/message/com/cmm/";

	private static String read(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
	}

	@Test
	void 첫_화면은_초기_비밀번호면_유효기간과_무관하게_닫을_수_없는_팝업을_연다() throws IOException {
		String jsp = read(UNIT_CONTENT_JSP);
		assertTrue(jsp.contains("var initialPassword = \"${initialPassword}\" === \"true\";"), "서버 판정값을 쓴다");
		assertTrue(jsp.contains("if ( initialPassword || ${elapsedTimeExpiration} > 0 )"), "초기 비밀번호면 유효기간과 무관하게 연다");
		assertTrue(jsp.contains("closeOnEscape: !initialPassword"), "ESC 로 닫을 수 없다");
		assertTrue(jsp.contains(".ui-dialog-titlebar-close').hide()"), "닫기(X) 버튼을 숨긴다");
	}

	@Test
	void 초기_비밀번호_안내에는_지금_변경만_있고_다음에_변경이_없다() throws IOException {
		String jsp = read(EXPIRE_PWD_JSP);
		// <title> 에도 같은 분기가 있으므로 본문(<body) 이후에서 찾는다
		int when = jsp.indexOf("<c:when test=\"${initialPassword}\">", jsp.indexOf("<body"));
		int otherwise = jsp.indexOf("<c:otherwise>", when);
		assertTrue(when > 0 && otherwise > when, "초기 비밀번호 분기가 있어야 한다");
		String initialBranch = jsp.substring(when, otherwise);

		assertTrue(initialBranch.contains("fn_egov_change_pwd()"), "지금 즉시 변경하기");
		assertFalse(initialBranch.contains("expirePwdContent.51"), "다음에 변경하기 버튼이 없어야 한다");
		assertFalse(initialBranch.contains("dialog('close')"), "팝업을 닫는 동작이 없어야 한다");
		assertTrue(jsp.substring(otherwise).contains("expirePwdContent.51"), "유효기간 만료 안내는 종전처럼 다음에 변경할 수 있다");
	}

	@Test
	void 안내_문구가_한국어와_영어에_모두_있다() throws IOException {
		for (String lang : new String[] { "ko", "en" }) {
			Properties messages = new Properties();
			try (Reader reader = Files.newBufferedReader(Paths.get(MESSAGE_DIR + "message-common_" + lang + ".properties"),
					StandardCharsets.ISO_8859_1)) {
				messages.load(reader);
			}
			for (String key : new String[] { "comCmm.unitContent.21", "comCmm.initialPwdContent.1",
					"comCmm.initialPwdContent.2", "comCmm.initialPwdContent.3", "comCmm.initialPwdContent.4" }) {
				String value = messages.getProperty(key);
				assertTrue(value != null && !value.isBlank(), lang + " 메시지 누락: " + key);
			}
		}
	}
}
