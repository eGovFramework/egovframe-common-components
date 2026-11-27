package egovframework.com.cmm.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

import egovframework.com.cmm.crypto.EgovCryptoKeyGuardFilter;
import egovframework.com.cmm.filter.HTMLTagFilter;
import egovframework.com.cmm.filter.SessionTimeoutCookieFilter;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.sec.security.filter.EgovSpringSecurityLoginFilter;
import egovframework.com.sec.security.filter.EgovSpringSecurityLogoutFilter;
import egovframework.com.uat.uap.filter.EgovLoginPolicyFilter;
import egovframework.com.utl.wed.filter.CkFilter;

/**
 * EgovWebApplicationInitializer 클래스
 * <Notice>
 * 	   사용자 인증 권한처리를 분리(session, spring security) 하기 위해서 web.xml의 기능을
 * 	   Servlet3.x WebApplicationInitializer 기능으로 처리
 * <Disclaimer>
 *		N/A
 *
 * @author 장동한
 * @since 2016.06.23
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2016.06.23  장동한          최초 생성
 *   2018.10.02  신용호          Facebook 관련 HiddenHttpMethodFilter 추가
 *   2018.10.26  신용호          EgovLoginPolicyFilter 추가 (IP접근처리)
 *   2018.12.03  신용호          springMultipartFilter,HTMLTagFilter 추가 (XSS방지처리)
 *   2025.05.23  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(리소스 닫기)
 *   2026.11.08  유지보수        실행환경 4.3.2 적용 - javax.servlet, XML 네임스페이스 간소화 설정(context-security.xml) 기준 필터 구성
 *   2026.11.12  유지보수        암호화 키 안내 페이지 필터(EgovCryptoKeyGuardFilter) 추가 - 기본 키면 모든 요청에 안내
 *
 *      </pre>
 */
public class EgovWebApplicationInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovWebApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		LOGGER.debug("EgovWebApplicationInitializer START-============================================");

		//-------------------------------------------------------------
		// Egov Web ServletContextListener 설정
		//-------------------------------------------------------------
		servletContext.addListener(new egovframework.com.cmm.context.EgovWebServletContextListener());

		//-------------------------------------------------------------
		// Spring CharacterEncodingFilter 설정
		//-------------------------------------------------------------
		FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("encodingFilter", new org.springframework.web.filter.CharacterEncodingFilter());
		characterEncoding.setInitParameter("encoding", "UTF-8");
		characterEncoding.setInitParameter("forceEncoding", "true");
		characterEncoding.addMappingForUrlPatterns(null, false, "*.do");
		//characterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "*.do");

		//-------------------------------------------------------------
		// Spring ServletContextListener 설정 (루트 컨텍스트 : context-*.xml, context-security.xml/context-crypto.xml 포함)
		// -------------------------------------------------------------
		XmlWebApplicationContext rootContext = new XmlWebApplicationContext(); // NOPMD - CloseResource(리소스 닫기)
		rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/com/**/context-*.xml" });
		//rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/com/context-*.xml","classpath*:egovframework/spring/com/*/context-*.xml" });
		rootContext.refresh();
		rootContext.start();

		servletContext.addListener(new ContextLoaderListener(rootContext));

		//-------------------------------------------------------------
		// 암호화 키 안내 페이지 필터 - 배포 기본 키(egovframe)이면 어떤 주소로 접근해도 이유와 조치 방법(init-crypto-key)을
		// 안내한다(HTTP 503). 보안·로그인 필터보다 앞에 둔다. 키를 초기화하면 아무것도 하지 않는다.
		//-------------------------------------------------------------
		FilterRegistration.Dynamic cryptoKeyGuardFilter = servletContext.addFilter("egovCryptoKeyGuardFilter", new EgovCryptoKeyGuardFilter(rootContext));
		cryptoKeyGuardFilter.addMappingForUrlPatterns(null, false, "/*");

		// -------------------------------------------------------------
		// Spring DispatcherServlet 설정 (egov-com-servlet.xml, egov-com-access.xml 등)
		// -------------------------------------------------------------
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext(); // NOPMD - CloseResource
		xmlWebApplicationContext.setConfigLocation("/WEB-INF/config/egovframework/springmvc/egov-com-*.xml");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(xmlWebApplicationContext));
		//dispatcher.addMapping("*.do");
		dispatcher.addMapping("/"); // Facebook OAuth 에서 사용
		dispatcher.setLoadOnStartup(1);

		//-------------------------------------------------------------
		// Spring MultipartFilter 설정
		// springSecurityFilterChain 보다 먼저 등록해야 한다. CSRF 검사가 multipart 본문의 _csrf 값을 읽을 수 있고,
		// 첨부파일 파트도 EgovMultipartResolver 가 해석한다.
		// (Tomcat allowCasualMultipartParsing="true" 는 넣지 않는다. Tomcat 이 본문을 먼저 읽어 첨부파일이 유실된다)
		// 인증 전 요청의 multipart 본문도 해석하므로 업로드 크기 제한(Globals.fileUpload.maxRequestSize 등)을 유지해야 한다.
		//-------------------------------------------------------------
		MultipartFilter springMultipartFilter = new MultipartFilter();
		springMultipartFilter.setMultipartResolverBeanName("multipartResolver");
		FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("springMultipartFilter", springMultipartFilter);
		multipartFilter.addMappingForUrlPatterns(null, false, "*.do");

		if("security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {

			//-------------------------------------------------------------
			// springSecurityFilterChain 설정 (context-security.xml <egov-security:config> 가 루트 컨텍스트에 생성)
			//-------------------------------------------------------------
			FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
			springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");

			//-------------------------------------------------------------
			// HttpSessionEventPublisher 설정
			//-------------------------------------------------------------
			servletContext.addListener(new org.springframework.security.web.session.HttpSessionEventPublisher());

			//-------------------------------------------------------------
			// EgovSpringSecurityLoginFilter 설정 (로그인 폼 id/password → DB 인증 → Spring Security 인증)
			//-------------------------------------------------------------
			FilterRegistration.Dynamic egovSpringSecurityLoginFilter = servletContext.addFilter("egovSpringSecurityLoginFilter", new EgovSpringSecurityLoginFilter());
			//로그인 실패시 반환 될 URL설정
			egovSpringSecurityLoginFilter.setInitParameter("loginURL", "/uat/uia/egovLoginUsr.do");
			//로그인 처리 URL설정
			egovSpringSecurityLoginFilter.setInitParameter("loginProcessURL", "/uat/uia/actionLogin.do");
			//처리 Url Pattern
			egovSpringSecurityLoginFilter.addMappingForUrlPatterns(null, false, "*.do");

			//-------------------------------------------------------------
			// EgovSpringSecurityLogoutFilter 설정 (/uat/uia/actionLogout.do POST → 로그아웃 후 logoutSuccessURL 이동)
			//-------------------------------------------------------------
			FilterRegistration.Dynamic egovSpringSecurityLogoutFilter = servletContext.addFilter("egovSpringSecurityLogoutFilter", new EgovSpringSecurityLogoutFilter());
			egovSpringSecurityLogoutFilter.setInitParameter("logoutSuccessURL", "/uat/uia/egovLoginUsr.do");
			egovSpringSecurityLogoutFilter.addMappingForUrlPatterns(null, false, "/uat/uia/actionLogout.do");

		} else if("session".equals(EgovProperties.getProperty("Globals.Auth").trim())) {

			//-------------------------------------------------------------
			// EgovLoginPolicyFilter 설정
			//-------------------------------------------------------------
			FilterRegistration.Dynamic egovLoginPolicyFilter = servletContext.addFilter("LoginPolicyFilter", new EgovLoginPolicyFilter());
			egovLoginPolicyFilter.addMappingForUrlPatterns(null, false, "/uat/uia/actionLogin.do");

		}

		//-------------------------------------------------------------
		// CkFilter 설정 (CKEditor 사용시 설정) - ck.image.url 경로로 매핑
		//-------------------------------------------------------------
		String ckImageUrlPattern = egovframework.com.utl.wed.web.EgovCkEditorControllerAdvice.resolveCkImageUrlPattern();
		FilterRegistration.Dynamic regCkFilter = servletContext.addFilter("CKFilter", new CkFilter());
		regCkFilter.setInitParameter("properties", "egovframework/egovProps/ck.properties");
		regCkFilter.addMappingForUrlPatterns(null, false, ckImageUrlPattern);
		LOGGER.debug("CKFilter mapped to {}", ckImageUrlPattern);

		//-------------------------------------------------------------
		// HiddenHttpMethodFilter 설정 (Facebook OAuth 사용시 설정)
		//-------------------------------------------------------------
		//FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
		//hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");

		//-------------------------------------------------------------
	    // HTMLTagFilter의 경우는 파라미터에 대하여 XSS 오류 방지를 위한 변환을 처리합니다.
		//-------------------------------------------------------------
	    // HTMLTagFIlter의 경우는 JSP의 <c:out /> 등을 사용하지 못하는 특수한 상황에서 사용하시면 됩니다.
	    // (<c:out />의 경우 뷰단에서 데이터 출력시 XSS 방지 처리가 됨)
		FilterRegistration.Dynamic htmlTagFilter = servletContext.addFilter("htmlTagFilter", new HTMLTagFilter());
		htmlTagFilter.addMappingForUrlPatterns(null, false, "*.do");

		//-------------------------------------------------------------
	    // SessionTimeoutCookieFilter는 쿠키에 타임아웃 시간을 기록한다.
		//-------------------------------------------------------------
	    // latestServerTime - 서버 최근 시간
	    // expireSessionTime - 세션이 만료되는 시간
		FilterRegistration.Dynamic sessionTimeoutFilter = servletContext.addFilter("sessionTimeoutFilter", new SessionTimeoutCookieFilter());
		sessionTimeoutFilter.addMappingForUrlPatterns(null, false, "*.do");

		//-------------------------------------------------------------
		// Spring RequestContextListener 설정
		//-------------------------------------------------------------
		servletContext.addListener(new org.springframework.web.context.request.RequestContextListener());

		LOGGER.debug("EgovWebApplicationInitializer END-============================================");

	}

}
