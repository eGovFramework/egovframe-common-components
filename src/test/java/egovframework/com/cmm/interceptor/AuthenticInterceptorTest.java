package egovframework.com.cmm.interceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.ServletRequestPathUtils;

import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.MappingMatch;

/** DB나 Spring 컨텍스트 없이 관리자 URL 매칭과 권한별 리다이렉트를 검증한다. */
class AuthenticInterceptorTest {

	private EgovUserDetailsService previousUserDetailsService;

	@BeforeEach
	void saveUserDetailsService() {
		previousUserDetailsService = new EgovUserDetailsHelper().getEgovUserDetailsService();
	}

	@AfterEach
	void restoreUserDetailsService() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previousUserDetailsService);
	}

	@ParameterizedTest(name = "{0}, role={1}, authenticated={2}")
	@CsvSource(nullValues = "ALLOW", value = {
			"/sec/ram/EgovAuthorList.do, ROLE_USER, true, redirect:/uat/uia/egovLoginUsr.do?auth_error=1",
			"/sec/ram/EgovAuthorList.do, ROLE_ADMIN, true, ALLOW",
			"/sec/ram/.do, ROLE_USER, true, redirect:/uat/uia/egovLoginUsr.do?auth_error=1",
			"/sec/ram/.do, ROLE_ADMIN, true, ALLOW",
			"/cop/bbs/SelectBBSMasterInfs.do, ROLE_USER, true, ALLOW",
			"/cop/bbs/SelectBBSMasterInfs.do, ROLE_ADMIN, true, ALLOW",
			"/sec/ram/nested/EgovAuthorList.do, ROLE_USER, true, ALLOW",
			"/sec/ram/nested/EgovAuthorList.do, ROLE_ADMIN, true, ALLOW",
			"/SEC/ram/EgovAuthorList.do, ROLE_USER, true, ALLOW",
			"/SEC/ram/EgovAuthorList.do, ROLE_ADMIN, true, ALLOW",
			"/sec/ram/EgovAuthorList.do/, ROLE_USER, true, ALLOW",
			"/sec/ram/EgovAuthorList.do/, ROLE_ADMIN, true, ALLOW",
			"/sec/ram/EgovAuthorList.do.json, ROLE_USER, true, ALLOW",
			"/sec/ram/EgovAuthorList.do.json, ROLE_ADMIN, true, ALLOW",
			"/sec/ram/EgovAuthorList.do, ROLE_USER, false, redirect:/uat/uia/egovLoginUsr.do",
			"/sec/ram/EgovAuthorList.do, ROLE_ADMIN, false, redirect:/uat/uia/egovLoginUsr.do",
			"/cop/bbs/SelectBBSMasterInfs.do, ROLE_USER, false, redirect:/uat/uia/egovLoginUsr.do",
			"/cop/bbs/SelectBBSMasterInfs.do, ROLE_ADMIN, false, redirect:/uat/uia/egovLoginUsr.do"
	})
	void urlAuthorizationPreservesExistingDecisions(String path, String role, boolean authenticated,
			String expectedRedirect) throws Exception {
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return null;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of(role);
			}

			@Override
			public Boolean isAuthenticated() {
				return authenticated;
			}
		});
		AuthenticInterceptor interceptor = new AuthenticInterceptor();
		// 실제 관리자 패턴 앞에 불일치 패턴을 두어 목록의 OR 매칭도 확인한다.
		interceptor.setAdminAuthPatternList(List.of("/not-admin/**", "/sec/ram/*.do"));

		for (String contextPath : List.of("", "/egov")) {
			for (String method : List.of("GET", "POST")) {
				for (boolean parsedPath : List.of(false, true)) {
					HttpServletRequest request = request(contextPath, path, method);
					if (parsedPath) {
						ServletRequestPathUtils.parseAndCache(request);
					}
					String scenario = method + " " + contextPath + path + ", cached=" + parsedPath;
					if (expectedRedirect == null) {
						assertTrue(interceptor.preHandle(request, null, null), scenario);
					} else {
						ModelAndViewDefiningException exception = assertThrows(ModelAndViewDefiningException.class,
								() -> interceptor.preHandle(request, null, null), scenario);
						assertEquals(expectedRedirect, exception.getModelAndView().getViewName(), scenario);
					}
				}
			}
		}
	}

	// spring-test의 Servlet 6.0 전용 Mock 대신 Servlet 5.0에서도 동작하는 요청 스텁을 사용한다.
	private static HttpServletRequest request(String contextPath, String path, String httpMethod) {
		Map<String, Object> attributes = new HashMap<>();
		HttpServletMapping mapping = new HttpServletMapping() {
			@Override
			public String getMatchValue() {
				return "";
			}

			@Override
			public String getPattern() {
				return "/";
			}

			@Override
			public String getServletName() {
				return "dispatcher";
			}

			@Override
			public MappingMatch getMappingMatch() {
				return MappingMatch.DEFAULT;
			}
		};
		return (HttpServletRequest) Proxy.newProxyInstance(AuthenticInterceptorTest.class.getClassLoader(),
				new Class<?>[] { HttpServletRequest.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "getMethod": return httpMethod;
					case "getContextPath": return contextPath;
					case "getRequestURI": return contextPath + path;
					case "getServletPath": return path;
					case "getPathInfo": return null;
					case "getQueryString": return "pageIndex=1";
					case "getHttpServletMapping": return mapping;
					case "getAttribute": return attributes.get(args[0]);
					case "setAttribute":
						attributes.put((String) args[0], args[1]);
						return null;
					case "removeAttribute":
						attributes.remove(args[0]);
						return null;
					default: throw new UnsupportedOperationException(method.getName());
					}
				});
	}
}
