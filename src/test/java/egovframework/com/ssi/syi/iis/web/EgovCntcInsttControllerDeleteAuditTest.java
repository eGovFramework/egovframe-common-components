package egovframework.com.ssi.syi.iis.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.ssi.syi.iis.service.CntcInstt;
import egovframework.com.ssi.syi.iis.service.CntcService;
import egovframework.com.ssi.syi.iis.service.CntcSystem;
import egovframework.com.ssi.syi.iis.service.EgovCntcInsttService;
import jakarta.servlet.ServletRequest;

/**
 * 연계기관/연계시스템/연계서비스 삭제 핸들러가 감사 컬럼 LAST_UPDUSR_ID 에
 * 무엇을 넘기는지 확인한다.
 *
 * 같은 컨트롤러의 insert/update 6개 핸들러는 예외 없이
 * EgovUserDetailsHelper.getAuthenticatedUser() 의 uniqId 로 덮어쓴다.
 */
class EgovCntcInsttControllerDeleteAuditTest {

	/** 로그인 세션이 가진 식별자 */
	private static final String SESSION_UNIQ_ID = "USRCNFRM_00000000001";

	private EgovCntcInsttController controller;
	private final AtomicReference<Object> deleted = new AtomicReference<>();

	@BeforeEach
	void setUp() throws Exception {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(SESSION_UNIQ_ID);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			public Object getAuthenticatedUser() {
				return loginVO;
			}

			public java.util.List<String> getAuthorities() {
				return Collections.singletonList("ROLE_ADMIN");
			}

			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		});

		EgovCntcInsttService service = (EgovCntcInsttService) Proxy.newProxyInstance(
				EgovCntcInsttService.class.getClassLoader(),
				new Class<?>[] { EgovCntcInsttService.class },
				(proxy, method, args) -> {
					if (method.getName().startsWith("delete")) {
						deleted.set(args[0]);
					}
					return null;
				});

		controller = new EgovCntcInsttController();
		Field f = EgovCntcInsttController.class.getDeclaredField("cntcInsttService");
		f.setAccessible(true);
		f.set(controller, service);
	}

	/** 커맨드 객체에 실제 요청 파라미터를 Spring 바인더로 바인딩한다. */
	private <T> T bind(T target, Map<String, String> params) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(target);
		binder.bind(stubRequest(params));
		return target;
	}

	private ServletRequest stubRequest(Map<String, String> params) {
		return (ServletRequest) Proxy.newProxyInstance(
				ServletRequest.class.getClassLoader(),
				new Class<?>[] { ServletRequest.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "getParameterNames":
						return Collections.enumeration(params.keySet());
					case "getParameterValues":
						String v = params.get(args[0]);
						return v == null ? null : new String[] { v };
					case "getParameter":
						return params.get(args[0]);
					case "getParameterMap":
						Map<String, String[]> m = new LinkedHashMap<>();
						params.forEach((k, val) -> m.put(k, new String[] { val }));
						return m;
					case "getAttribute":
						return null;
					default:
						return null;
					}
				});
	}

	private static Map<String, String> params(String... kv) {
		Map<String, String> m = new LinkedHashMap<>();
		for (int i = 0; i < kv.length; i += 2) {
			m.put(kv[i], kv[i + 1]);
		}
		return m;
	}

	// ---------------------------------------------------------------
	// EgovCntcInsttDetail.jsp 의 삭제 버튼이 제출하는 Form(143행)이
	// 실제로 담고 있는 항목만 보낸 경우 — lastUpdusrId 항목은 없다
	// ---------------------------------------------------------------

	@Test
	@DisplayName("연계기관 삭제 - 삭제 폼 그대로 보냈을 때 LAST_UPDUSR_ID")
	void deleteCntcInstt_formPost() throws Exception {
		CntcInstt vo = bind(new CntcInstt(), params("insttId", "INSTT_0001", "sysId", "", "svcId", ""));
		controller.deleteCntcInstt(vo, new ModelMap());
		assertEquals(SESSION_UNIQ_ID, ((CntcInstt) deleted.get()).getLastUpdusrId(),
				"LAST_UPDUSR_ID 로 저장되는 값");
	}

	@Test
	@DisplayName("연계시스템 삭제 - 삭제 폼 그대로 보냈을 때 LAST_UPDUSR_ID")
	void deleteCntcSystem_formPost() throws Exception {
		CntcSystem vo = bind(new CntcSystem(), params("insttId", "INSTT_0001", "sysId", "SYS_0001"));
		controller.deleteCntcSystem(vo, new ModelMap());
		assertEquals(SESSION_UNIQ_ID, ((CntcSystem) deleted.get()).getLastUpdusrId(),
				"LAST_UPDUSR_ID 로 저장되는 값");
	}

	@Test
	@DisplayName("연계서비스 삭제 - 삭제 폼 그대로 보냈을 때 LAST_UPDUSR_ID")
	void deleteCntcService_formPost() throws Exception {
		CntcService vo = bind(new CntcService(),
				params("insttId", "INSTT_0001", "sysId", "SYS_0001", "svcId", "SVC_0001"));
		controller.deleteCntcService(vo, new ModelMap());
		assertEquals(SESSION_UNIQ_ID, ((CntcService) deleted.get()).getLastUpdusrId(),
				"LAST_UPDUSR_ID 로 저장되는 값");
	}

	// ---------------------------------------------------------------
	// 요청에 lastUpdusrId 파라미터를 함께 보낸 경우
	// ---------------------------------------------------------------

	@Test
	@DisplayName("연계기관 삭제 - 요청에 lastUpdusrId 를 실었을 때 LAST_UPDUSR_ID")
	void deleteCntcInstt_paramWins() throws Exception {
		CntcInstt vo = bind(new CntcInstt(),
				params("insttId", "INSTT_0001", "lastUpdusrId", "USRCNFRM_00000000999"));
		controller.deleteCntcInstt(vo, new ModelMap());
		assertEquals(SESSION_UNIQ_ID, ((CntcInstt) deleted.get()).getLastUpdusrId(),
				"LAST_UPDUSR_ID 로 저장되는 값");
	}

	@Test
	@DisplayName("연계시스템 삭제 - 요청에 lastUpdusrId 를 실었을 때 LAST_UPDUSR_ID")
	void deleteCntcSystem_paramWins() throws Exception {
		CntcSystem vo = bind(new CntcSystem(),
				params("insttId", "INSTT_0001", "sysId", "SYS_0001", "lastUpdusrId", "USRCNFRM_00000000999"));
		controller.deleteCntcSystem(vo, new ModelMap());
		assertEquals(SESSION_UNIQ_ID, ((CntcSystem) deleted.get()).getLastUpdusrId(),
				"LAST_UPDUSR_ID 로 저장되는 값");
	}

	@Test
	@DisplayName("연계서비스 삭제 - 요청에 lastUpdusrId 를 실었을 때 LAST_UPDUSR_ID")
	void deleteCntcService_paramWins() throws Exception {
		CntcService vo = bind(new CntcService(), params("insttId", "INSTT_0001", "sysId", "SYS_0001",
				"svcId", "SVC_0001", "lastUpdusrId", "USRCNFRM_00000000999"));
		controller.deleteCntcService(vo, new ModelMap());
		assertEquals(SESSION_UNIQ_ID, ((CntcService) deleted.get()).getLastUpdusrId(),
				"LAST_UPDUSR_ID 로 저장되는 값");
	}
}
