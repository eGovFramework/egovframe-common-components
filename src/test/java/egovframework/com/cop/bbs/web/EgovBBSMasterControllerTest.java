package egovframework.com.cop.bbs.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;

/**
 * 게시판 마스터 등록/수정의 입력검증 실패 재표시 시, 등록/수정 폼 조회(GET)와 동일하게
 * 추가 선택사항(댓글/만족도조사) 컴포넌트 설치 여부가 모델에 담기는지 확인한다.
 */
class EgovBBSMasterControllerTest {

	private static final String REGIST_VIEW = "egovframework/com/cop/bbs/EgovBBSMasterRegist";
	private static final String UPDT_VIEW = "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
	private static final String USER_ID = "USRCNFRM_00000000000";

	private EgovBBSMasterController controller;
	private ApplicationContext savedContext;
	private Object savedUserDetailsService;

	@BeforeEach
	void setUp() throws Exception {
		controller = new EgovBBSMasterController();
		setField(controller, "cmmUseService",
			proxy(EgovCmmUseService.class, (p, method, args) ->
				"selectCmmCodeDetail".equals(method.getName()) ? Collections.emptyList() : null));
		setField(controller, "egovBBSMasterService",
			proxy(EgovBBSMasterService.class, (p, method, args) ->
				"selectBBSMasterInf".equals(method.getName()) ? existingBoard() : null));

		// 인증 스텁 : EgovUserDetailsHelper 의 static 필드는 package-private 이라 리플렉션으로 주입한다.
		savedUserDetailsService = readStaticField(EgovUserDetailsHelper.class, "egovUserDetailsService");
		setStaticField(EgovUserDetailsHelper.class, "egovUserDetailsService",
			proxy(EgovUserDetailsService.class, (p, method, args) -> {
				switch (method.getName()) {
					case "getAuthenticatedUser":
						return loginVO();
					case "isAuthenticated":
						return Boolean.TRUE;
					case "getAuthorities":
						return Collections.singletonList("ROLE_ADMIN");
					default:
						return null;
				}
			}));

		// 댓글/만족도조사 컴포넌트가 모두 설치된 환경을 만든다.
		savedContext = EgovComponentChecker.context;
		EgovComponentChecker.context = proxy(ApplicationContext.class,
			(p, method, args) -> "getBean".equals(method.getName()) ? new Object() : null);
	}

	@AfterEach
	void tearDown() throws Exception {
		EgovComponentChecker.context = savedContext;
		setStaticField(EgovUserDetailsHelper.class, "egovUserDetailsService", savedUserDetailsService);
	}

	@Test
	void insertBBSMasterKeepsAddedOptionsAttributesOnValidationError() throws Exception {
		ModelMap model = new ModelMap();

		assertEquals(REGIST_VIEW, controller.insertBBSMaster(new BoardMasterVO(), new BoardMaster(), errors(), model));

		assertTrue(model.containsAttribute("useComment"), "useComment 가 모델에 없다");
		assertTrue(model.containsAttribute("useSatisfaction"), "useSatisfaction 이 모델에 없다");
	}

	@Test
	void updateBBSMasterKeepsAddedOptionsAttributesOnValidationError() throws Exception {
		ModelMap model = new ModelMap();

		assertEquals(UPDT_VIEW, controller.updateBBSMaster(new BoardMasterVO(), new BoardMaster(), errors(), model));

		assertTrue(model.containsAttribute("useComment"), "useComment 가 모델에 없다");
		assertTrue(model.containsAttribute("useSatisfaction"), "useSatisfaction 이 모델에 없다");
	}

	private BindingResult errors() {
		BindingResult bindingResult = new BeanPropertyBindingResult(new BoardMaster(), "boardMasterVO");
		bindingResult.reject("errors.required");
		return bindingResult;
	}

	private LoginVO loginVO() {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(USER_ID);
		return loginVO;
	}

	private BoardMasterVO existingBoard() {
		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setFrstRegisterId(USER_ID);
		return boardMasterVO;
	}

	@SuppressWarnings("unchecked")
	private static <T> T proxy(Class<T> type, InvocationHandler handler) {
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] { type },
			(p, method, args) -> {
				switch (method.getName()) {
					case "hashCode":
						return System.identityHashCode(p);
					case "equals":
						return p == args[0];
					case "toString":
						return type.getSimpleName() + "-stub";
					default:
						return handler.invoke(p, method, args);
				}
			});
	}

	private static void setField(Object target, String name, Object value) throws Exception {
		Field field = target.getClass().getDeclaredField(name);
		field.setAccessible(true);
		field.set(target, value);
	}

	private static Object readStaticField(Class<?> type, String name) throws Exception {
		Field field = type.getDeclaredField(name);
		field.setAccessible(true);
		return field.get(null);
	}

	private static void setStaticField(Class<?> type, String name, Object value) throws Exception {
		Field field = type.getDeclaredField(name);
		field.setAccessible(true);
		field.set(null, value);
	}
}
