package egovframework.com.security;

import egovframework.com.security.config.EgovSecurityTestConfig;
import egovframework.com.security.config.EgovSecurityTestDatasource;
import egovframework.com.security.userdetails.EgovUserDetailsVO;
import egovframework.com.security.web.CategoryController;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.egovframe.rte.fdl.security.config.EgovSecurityConfig;
import org.egovframe.rte.fdl.security.config.EgovSecurityConfiguration;
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 원본: org.egovframe.rte.fdl.security.EgovSecurityConfigTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EgovSecurityTestDatasource.class, EgovSecurityConfiguration.class, EgovSecurityTestConfig.class })
public class EgovSecurityConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovSecurityConfigTest.class);

    @Autowired
    private ApplicationContext context;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @AfterEach
    public void onTearDown() {
        SecurityContextHolder.clearContext();
    }

    private <T extends Filter> T getSecurityFilter(Class<T> type) {
        Map<String, DefaultSecurityFilterChain> filterChainMap = context.getBeansOfType(DefaultSecurityFilterChain.class);
        for (DefaultSecurityFilterChain filterChain : filterChainMap.values()) {
            for (Filter filter : filterChain.getFilters()) {
                if (type.isInstance(filter)) {
                    return type.cast(filter);
                }
            }
        }
        throw new NoSuchBeanDefinitionException("##### EgovSecurityConfigTest No bean of type [" + type.getName() + "] is defined.");
    }

    @Test
    public void testBeanList() {
        String[] list = context.getBeanDefinitionNames();
        for (String bean : list) {
            LOGGER.debug("### EgovSecurityConfigTest.testBeanList {} : {}", bean, context.getBean(bean).getClass());
            if (context.getBean(bean) instanceof DefaultSecurityFilterChain chain) {
                for (Filter filter : chain.getFilters()) {
                    LOGGER.debug("### EgovSecurityConfigTest.testBeanList filter : {}", filter);
                }
            }
        }
    }

    @Test
    public void testGetSecurityFilter() {
        LogoutFilter logout = getSecurityFilter(LogoutFilter.class);
        assertNotNull(logout);
    }

    @Test
    public void testMethodAndRoleMapping() {
        DelegatingMethodSecurityMetadataSource definitionsource = (DelegatingMethodSecurityMetadataSource) context.getBean("delegatingMethodSecurityMetadataSource");
        Method method = null;

        try {
            method = CategoryController.class.getMethod("selectCategoryList", (Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            LOGGER.debug("[{}] EgovSecurityConfigTest testMethodAndRoleMapping() : {}", e.getClass().getName(), e.getMessage());
        }

        Collection<ConfigAttribute> role = definitionsource.getAttributes(method, CategoryController.class);

        if (!role.isEmpty()) {
            assertEquals("ROLE_USER", role.toArray()[0].toString());
            LOGGER.debug("### EgovSecurityConfigTest.testMethodAndRoleMapping : {} is {}", method != null ? method.getName() : null, role.toArray()[0].toString());
        }
    }

    @Test
    public void testFailedMethodAndRoleMapping() {
        DelegatingMethodSecurityMetadataSource definitionsource = (DelegatingMethodSecurityMetadataSource) context.getBean("delegatingMethodSecurityMetadataSource");
        Method method = null;

        try {
            method = CategoryController.class.getMethod("addCategoryView", (Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            LOGGER.debug("[{}] EgovSecurityConfigTest testFailedMethodAndRoleMapping() : {}", e.getClass().getName(), e.getMessage());
        }

        Collection<ConfigAttribute> role = definitionsource.getAttributes(method, CategoryController.class);

        assertEquals(0, role.size());
        LOGGER.debug("### EgovSecurityConfigTest.testFailedMethodAndRoleMapping : {} is no roles", method != null ? method.getName() : null);
    }

    @Test
    public void testURLAndRoleMapping() {
        FilterSecurityInterceptor interceptor = (FilterSecurityInterceptor) context.getBean("filterSecurityInterceptor");
        FilterInvocationSecurityMetadataSource definitionsource = interceptor.getSecurityMetadataSource();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setServletPath("/sample/detail");
        FilterInvocation filterInvocation = new FilterInvocation(request, new MockHttpServletResponse(), new MockFilterChain());
        Collection<ConfigAttribute> attrs = definitionsource.getAttributes(filterInvocation);
        assertNotNull(attrs);
        assertTrue(attrs.contains(new SecurityConfig("ROLE_USER")));

        request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setServletPath("/sample/add");
        filterInvocation = new FilterInvocation(request, new MockHttpServletResponse(), new MockFilterChain());
        attrs = definitionsource.getAttributes(filterInvocation);
        assertNotNull(attrs);
        assertTrue(attrs.contains(new SecurityConfig("ROLE_ADMIN")));

        request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setRequestURI("/test.do");
        request.setServletPath("/test.do");
        filterInvocation = new FilterInvocation(request, new MockHttpServletResponse(), new MockFilterChain());
        attrs = definitionsource.getAttributes(filterInvocation);
        assertNotNull(attrs, "Request map should return attributes for /test.do");
        assertTrue(attrs.contains(new SecurityConfig("ROLE_USER")));

        request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setRequestURI("/sale/index.do");
        request.setServletPath("/sale/index.do");
        filterInvocation = new FilterInvocation(request, new MockHttpServletResponse(), new MockFilterChain());
        attrs = definitionsource.getAttributes(filterInvocation);
        assertNotNull(attrs, "Request map should return attributes for /sale/index.do");
        assertTrue(attrs.contains(new SecurityConfig("ROLE_RESTRICTED")));
    }

    @Test
    public void testFailedURLAndRoleMapping() {
        FilterSecurityInterceptor interceptor = (FilterSecurityInterceptor) context.getBean("filterSecurityInterceptor");
        FilterInvocationSecurityMetadataSource definitionsource = interceptor.getSecurityMetadataSource();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setServletPath("/index.do");
        FilterInvocation filterInvocation = new FilterInvocation(request, new MockHttpServletResponse(), new MockFilterChain());
        Collection<ConfigAttribute> attrs = definitionsource.getAttributes(filterInvocation);
        assertNull(attrs);
    }

    @Test
    public void testSuccessfulUrlInvocation() throws Exception {
        String loginPage = context.getBean(EgovSecurityConfig.class).getLoginUrl();

        FilterChainProxy filterChainProxy = (FilterChainProxy) context.getBean(BeanIds.SPRING_SECURITY_FILTER_CHAIN);

        for (String path : new String[] { "/sample/detail", "/sample/add", "/test.do" }) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setMethod("GET");
            request.setServletPath(path);
            MockHttpServletResponse response = new MockHttpServletResponse();
            MockFilterChain chain = new MockFilterChain();
            filterChainProxy.doFilter(request, response, chain);
            assertTrue(Objects.requireNonNull(response.getRedirectedUrl()).contains(loginPage), "path: " + path);
        }
    }

    @Test
    public void testFailureUrlInvocation() throws Exception {
        FilterChainProxy filterChainProxy = (FilterChainProxy) context.getBean(BeanIds.FILTER_CHAIN_PROXY);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setServletPath("/index.do");
        request.setRequestURI("/index.do");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filterChainProxy.doFilter(request, response, chain);

        assertNull(response.getRedirectedUrl());
        LOGGER.debug("### EgovSecurityConfigTest.testFailureUrlInvocation getRedirectedUrl is null");

        request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setServletPath("/sale/index.doit");
        request.setRequestURI("/sale/index.doit");

        response = new MockHttpServletResponse();
        chain = new MockFilterChain();

        filterChainProxy.doFilter(request, response, chain);

        assertNull(response.getRedirectedUrl());
        LOGGER.debug("### EgovSecurityConfigTest.testFailureUrlInvocation getRedirectedUrl is null");
    }

    @Test
    public void testPropertiesConfigurationLoading() {
        EgovSecurityConfig config = context.getBean(EgovSecurityConfig.class);
        assertNotNull(config);

        assertEquals("egovSecurityConfig", config.getId());
        assertEquals("/uat/uia/egovLoginUsr.do", config.getLoginUrl());
        assertEquals("/uat/uia/egovLoginUsr.do", config.getLogoutSuccessUrl());
        assertEquals("/uat/uia/egovLoginUsr.do?login_error=1", config.getLoginFailureUrl());
        assertEquals("/sec/ram/accessDenied.do", config.getAccessDeniedUrl());
        assertEquals("dataSource", config.getDataSource());

        assertTrue(config.getJdbcUsersByUsernameQuery().contains("USERS") && config.getJdbcUsersByUsernameQuery().contains("USER_PASSWORD"));
        assertTrue(config.getJdbcAuthoritiesByUsernameQuery().contains("AUTHORITIES") && config.getJdbcAuthoritiesByUsernameQuery().contains("AUTHOR_CODE"));
        assertTrue(config.getJdbcMapClass().contains("UserDetailsMapping"));

        assertEquals("regex", config.getRequestMatcherType());
        assertEquals("plaintext", config.getHash());
        assertFalse(config.isHashBase64());
        assertEquals(1, config.getConcurrentMaxSessons());
        assertEquals("/EgovContent.do", config.getConcurrentExpiredUrl());
        assertFalse(config.isErrorIfMaximumExceeded());
        assertEquals("/EgovContent.do", config.getDefaultTargetUrl());
        assertTrue(config.isAlwaysUseDefaultTargetUrl());
        assertTrue(config.isSniff());
        assertEquals("SAMEORIGIN", config.getXframeOptions());
        assertTrue(config.isXssProtection());
        assertFalse(config.isCacheControl());
        assertFalse(config.isCsrf());
        assertEquals("", config.getCsrfAccessDeniedUrl());

        assertNotNull(config.getSqlHierarchicalRoles());
        assertTrue(config.getSqlHierarchicalRoles().contains("ROLESHIERARCHY"));
        assertNotNull(config.getSqlRolesAndUrl());
        assertTrue(config.getSqlRolesAndUrl().contains("ROLES") && config.getSqlRolesAndUrl().contains("AUTHROLES"));

        String permitAllList = config.getPermitAllList();
        assertNotNull(permitAllList);
        assertTrue(permitAllList.contains("/css/**"));
        assertTrue(permitAllList.contains("/js/**"));
        assertTrue(permitAllList.contains("/uat/uia/**"));
        assertTrue(permitAllList.contains("/index.do"));
        assertTrue(permitAllList.contains("/EgovContent.do"));
        assertTrue(permitAllList.contains("/sec/ram/accessDenied.do"));
    }

    @Test
    public void testPermitAllListFromPropertiesConfig() {
        EgovSecurityConfig config = context.getBean(EgovSecurityConfig.class);
        String permitAllList = config.getPermitAllList();

        assertNotNull(permitAllList, "permitAllList가 null입니다");
        assertFalse(permitAllList.trim().isEmpty(), "permitAllList가 비어있습니다");

        String[] patterns = permitAllList.split(",");
        assertTrue(patterns.length > 0, "permitAllList 패턴이 없습니다");

        List<String> patternList = Arrays.asList(patterns);
        List<String> expectedPatterns = Arrays.asList(
                "/css/**", "/js/**", "/images/**", "/resource/**",
                "/public/**", "/uat/uia/**", "/index.do", "/EgovContent.do", "/sec/ram/accessDenied.do"
        );

        for (String expectedPattern : expectedPatterns) {
            boolean found = patternList.stream()
                    .anyMatch(pattern -> pattern.trim().equals(expectedPattern));
            assertTrue(found, "예상 패턴 '" + expectedPattern + "'이 permitAllList에 없습니다");
        }
    }

    @Test
    public void testAnonymousUserHandling() {
        SecurityContextHolder.clearContext();

        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        LOGGER.debug("### 익명 사용자 인증 상태: {}", isAuthenticated);

        EgovUserDetailsVO user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        LOGGER.debug("### 익명 사용자 정보: {}", user);

        List<String> authorities = EgovUserDetailsHelper.getAuthorities();
        LOGGER.debug("### 익명 사용자 권한: {}", authorities);

        FilterChainProxy filterChainProxy = (FilterChainProxy) context.getBean(BeanIds.SPRING_SECURITY_FILTER_CHAIN);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setServletPath("/index.do");

        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        try {
            filterChainProxy.doFilter(request, response, chain);
            LOGGER.debug("### 익명 사용자 /index.do 접근 결과 - 리다이렉트 URL: {}", response.getRedirectedUrl());
            LOGGER.debug("### 익명 사용자 /index.do 접근 결과 - 상태 코드: {}", response.getStatus());
        } catch (Exception e) {
            LOGGER.debug("### 익명 사용자 접근 테스트 실패", e);
        }
    }
}
