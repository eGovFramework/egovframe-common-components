package egovframework.com.cmm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import egovframework.com.cmm.filter.HTMLTagFilter;
import egovframework.com.cmm.filter.SessionTimeoutCookieFilter;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.uat.uap.filter.EgovLoginPolicyFilter;
import egovframework.com.utl.wed.filter.CkFilter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

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
 *
 *      </pre>
 */
public class EgovWebApplicationInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovWebApplicationInitializer.class);

    private static final String TMP_LOCATION = "";				// 업로드 임시 디렉터리, 빈 문자열이면 컨테이너 기본 tmp 사용
	private static final long MAX_FILE_SIZE = 104857600L;		// 개별파일 최대크기 (100MB)
    private static final long MAX_REQUEST_SIZE = 104857600L;	// 전체요청 쵀대크기 (100MB)
    private static final int  FILE_SIZE_THRESHOLD = 104876;		// 메모리 임계값 (1MB)

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
		// Spring ServletContextListener 설정
		// -------------------------------------------------------------
		XmlWebApplicationContext rootContext = new XmlWebApplicationContext(); // NOPMD - CloseResource(리소스 닫기)
		rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/com/**/context-*.xml" });
		//rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/com/context-*.xml","classpath*:egovframework/spring/com/*/context-*.xml" });
		rootContext.refresh();
		rootContext.start();

		servletContext.addListener(new ContextLoaderListener(rootContext));

		// -------------------------------------------------------------
		// Spring ServletContextListener 설정
		// -------------------------------------------------------------
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext(); // NOPMD - CloseResource
		xmlWebApplicationContext.setConfigLocation("/WEB-INF/config/egovframework/springmvc/egov-com-*.xml");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(xmlWebApplicationContext));
		//dispatcher.addMapping("*.do");
		dispatcher.addMapping("/"); // Facebook OAuth 에서 사용
		dispatcher.setLoadOnStartup(1);

		// StandardServletMultipartResolver를 위한 Multipart 설정 추가
		MultipartConfigElement multipartConfig = new MultipartConfigElement(TMP_LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		dispatcher.setMultipartConfig(multipartConfig);

		if("security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {

			//-------------------------------------------------------------
			// springSecurityFilterChain 설정
			//-------------------------------------------------------------
			DelegatingFilterProxy securityFilter = new DelegatingFilterProxy("springSecurityFilterChain");
	        securityFilter.setContextAttribute(FrameworkServlet.SERVLET_CONTEXT_PREFIX + "dispatcher");
	        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", securityFilter);
	        security.addMappingForUrlPatterns(null, false, "/*");

			//-------------------------------------------------------------
			// HttpSessionEventPublisher 설정
			//-------------------------------------------------------------
			servletContext.addListener(new org.springframework.security.web.session.HttpSessionEventPublisher());

		} else if("session".equals(EgovProperties.getProperty("Globals.Auth").trim())) {

			//-------------------------------------------------------------
			// EgovLoginPolicyFilter 설정
			//-------------------------------------------------------------
			FilterRegistration.Dynamic egovLoginPolicyFilter = servletContext.addFilter("LoginPolicyFilter", new EgovLoginPolicyFilter());
			egovLoginPolicyFilter.addMappingForUrlPatterns(null, false, "/uat/uia/actionLogin.do");

		}

		//-------------------------------------------------------------
		// CkFilter 설정 (CKEditor 사용시 설정)
		//-------------------------------------------------------------
		FilterRegistration.Dynamic regCkFilter = servletContext.addFilter("CKFilter", new CkFilter());
		regCkFilter.setInitParameter("properties", "egovframework/egovProps/ck.properties");
		regCkFilter.addMappingForUrlPatterns(null, false, "/ckUploadImage");

		//-------------------------------------------------------------
		// HiddenHttpMethodFilter 설정 (Facebook OAuth 사용시 설정)
		//-------------------------------------------------------------
		//FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
		//hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");

		//-------------------------------------------------------------
		// Tomcat의 경우 allowCasualMultipartParsing="true" 추가
		// <Context docBase="" path="/" reloadable="true" allowCasualMultipartParsing="true">
		//-------------------------------------------------------------
		MultipartFilter springMultipartFilter = new MultipartFilter();
		springMultipartFilter.setMultipartResolverBeanName("multipartResolver");
		FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("springMultipartFilter", springMultipartFilter);
		multipartFilter.addMappingForUrlPatterns(null, false, "*.do");

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
