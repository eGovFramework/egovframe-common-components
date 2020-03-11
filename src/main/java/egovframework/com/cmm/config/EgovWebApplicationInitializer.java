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
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

import egovframework.com.cmm.filter.HTMLTagFilter;
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
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자           수정내용
 *  -------      -------------  ----------------------
 *   2016.06.23  장동한           최초 생성
 *   2018.10.02  신용호           Facebook 관련 HiddenHttpMethodFilter 추가
 *   2018.10.26  신용호           EgovLoginPolicyFilter 추가 (IP접근처리)
 *   2018.12.03  신용호           springMultipartFilter,HTMLTagFilter 추가 (XSS방지처리)
 * </pre>
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
		// Spring ServletContextListener 설정
		//-------------------------------------------------------------
		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/com/**/context-*.xml" });
		//rootContext.setConfigLocations(new String[] { "classpath*:egovframework/spring/com/context-*.xml","classpath*:egovframework/spring/com/*/context-*.xml" });
		rootContext.refresh();
		rootContext.start();
		
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//-------------------------------------------------------------
		// Spring ServletContextListener 설정
		//-------------------------------------------------------------
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
		xmlWebApplicationContext.setConfigLocation("/WEB-INF/config/egovframework/springmvc/egov-com-*.xml");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(xmlWebApplicationContext));
		//dispatcher.addMapping("*.do");
		dispatcher.addMapping("/"); // Facebook OAuth 사용시 변경
		dispatcher.setLoadOnStartup(1);
		
		if("security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
			
			//-------------------------------------------------------------
			// springSecurityFilterChain 설정
			//-------------------------------------------------------------		
			FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
			springSecurityFilterChain.addMappingForUrlPatterns(null, false, "*");
			//servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");

			//-------------------------------------------------------------
			// HttpSessionEventPublisher 설정
			//-------------------------------------------------------------	
			servletContext.addListener(new org.springframework.security.web.session.HttpSessionEventPublisher());
			
			//-------------------------------------------------------------
			// EgovSpringSecurityLoginFilter 설정
			//-------------------------------------------------------------
			FilterRegistration.Dynamic egovSpringSecurityLoginFilter = servletContext.addFilter("egovSpringSecurityLoginFilter", new EgovSpringSecurityLoginFilter());
			//로그인 실패시 반활 될 URL설정
			egovSpringSecurityLoginFilter.setInitParameter("loginURL", "/uat/uia/egovLoginUsr.do");
			//로그인 처리 URL설정
			egovSpringSecurityLoginFilter.setInitParameter("loginProcessURL", "/uat/uia/actionLogin.do");
			//처리 Url Pattern
			egovSpringSecurityLoginFilter.addMappingForUrlPatterns(null, false, "*.do");
			
			//-------------------------------------------------------------
			// EgovSpringSecurityLogoutFilter 설정
			//-------------------------------------------------------------	
			FilterRegistration.Dynamic egovSpringSecurityLogoutFilter = servletContext.addFilter("egovSpringSecurityLogoutFilter", new EgovSpringSecurityLogoutFilter());
			egovSpringSecurityLogoutFilter.addMappingForUrlPatterns(null, false, "/uat/uia/actionLogout.do");
		
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
		regCkFilter.addMappingForUrlPatterns(null, false, "/ckupload");
		
		//-------------------------------------------------------------
		// HiddenHttpMethodFilter 설정 (Facebook OAuth 사용시 설정)
		//-------------------------------------------------------------
		FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
		hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
		
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
		// Spring RequestContextListener 설정
		//-------------------------------------------------------------
		servletContext.addListener(new org.springframework.web.context.request.RequestContextListener());
		
		LOGGER.debug("EgovWebApplicationInitializer END-============================================");
	
	}
	
}
