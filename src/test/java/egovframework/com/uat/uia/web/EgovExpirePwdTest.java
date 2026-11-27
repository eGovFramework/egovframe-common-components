package egovframework.com.uat.uia.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.uss.umt.web.EgovEmplyrManageController;
import egovframework.com.uss.umt.web.EgovEntrprsManageController;
import egovframework.com.uss.umt.web.EgovMberManageController;

class EgovExpirePwdTest {

	private static final String EXPIRE_PWD_JSP = "src/main/webapp/WEB-INF/jsp/egovframework/com/uat/uia/EgovExpirePwd.jsp";

	private static final Pattern FORM_ACTION = Pattern.compile("\\.action\\s*=\\s*\"<c:url\\s+value='([^']+)'\\s*/>\"");

	/**
	 * 비밀번호 만료 팝업은 사용자 구분(업무사용자/기업회원/일반회원)에 따라 비밀번호 변경화면으로 전송한다.
	 * 세 구분 모두 실제 등록된 요청 URL로 전송하는지 확인한다.
	 */
	@Test
	void everyPasswordUpdtViewActionHasHandler() throws IOException {
		Set<String> mappings = mappingsOf(EgovEmplyrManageController.class, EgovEntrprsManageController.class,
				EgovMberManageController.class);

		List<String> actions = formActionsOf(EXPIRE_PWD_JSP);
		assertFalse(actions.isEmpty(), EXPIRE_PWD_JSP + " 에서 전송 URL을 찾지 못했습니다.");

		for (String action : actions) {
			assertTrue(mappings.contains(action), "처리할 핸들러가 없는 전송 URL 입니다 : " + action);
		}
	}

	private List<String> formActionsOf(String jsp) throws IOException {
		List<String> actions = new ArrayList<>();
		Matcher matcher = FORM_ACTION.matcher(new String(Files.readAllBytes(Paths.get(jsp)), StandardCharsets.UTF_8));
		while (matcher.find()) {
			actions.add(matcher.group(1));
		}
		return actions;
	}

	private Set<String> mappingsOf(Class<?>... controllers) {
		Set<String> mappings = new HashSet<>();
		for (Class<?> controller : controllers) {
			for (Method method : controller.getDeclaredMethods()) {
				PostMapping postMapping = method.getAnnotation(PostMapping.class);
				if (postMapping != null) {
					mappings.addAll(Arrays.asList(postMapping.value()));
				}
				RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
				if (requestMapping != null) {
					mappings.addAll(Arrays.asList(requestMapping.value()));
				}
			}
		}
		return mappings;
	}

}
