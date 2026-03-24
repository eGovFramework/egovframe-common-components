package egovframework.com.security;

import egovframework.com.security.config.EgovSecurityTestConfig;
import egovframework.com.security.config.EgovSecurityTestDatasource;
import egovframework.com.security.userdetails.EgovUserDetailsVO;
import org.egovframe.rte.fdl.security.config.EgovSecurityConfig;
import org.egovframe.rte.fdl.security.config.EgovSecurityConfiguration;
import org.egovframe.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 원본: org.egovframe.rte.fdl.security.EgovSecurityServiceTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EgovSecurityTestDatasource.class, EgovSecurityConfiguration.class, EgovSecurityTestConfig.class })
public class EgovSecurityServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovSecurityServiceTest.class);

    @Autowired
    private ApplicationContext applicationContext;

    @AfterEach
    public void clear() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testPropertiesConfigurationLoading() {
        EgovSecurityConfig config = applicationContext.getBean(EgovSecurityConfig.class);
        assertNotNull(config, "EgovSecurityConfig 빈이 로드되어야 함");
        assertEquals("egovSecurityConfig", config.getId(), "conf 설정 파일에서 id 로드");
        assertEquals("dataSource", config.getDataSource());
        assertNotNull(config.getJdbcUsersByUsernameQuery());
        assertTrue(config.getJdbcUsersByUsernameQuery().contains("USERS"), "USERS 테이블 기반 쿼리");
        assertNotNull(config.getJdbcAuthoritiesByUsernameQuery());
        assertTrue(config.getJdbcAuthoritiesByUsernameQuery().contains("AUTHORITIES"), "AUTHORITIES 테이블 기반 쿼리");
        assertNotNull(config.getSqlHierarchicalRoles());
        assertTrue(config.getSqlHierarchicalRoles().contains("ROLESHIERARCHY"), "ROLESHIERARCHY 기반 계층 쿼리");
        assertNotNull(config.getSqlRolesAndUrl());
        assertTrue(config.getSqlRolesAndUrl().contains("ROLES") && config.getSqlRolesAndUrl().contains("AUTHROLES"),
                "ROLES/AUTHROLES 기반 URL-역할 매핑 쿼리");

        LOGGER.debug("### properties 설정 파일에서 로드된 security 설정: id={}", config.getId());
    }

    @Test
    public void testAllowAccessForAuthorizedUser() {
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);

        for (String[] cred : new String[][] { { "user", "1" }, { "admin", "1" }, { "jimi", "jimi" }, { "test", "test" }, { "buyer", "buyer" } }) {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(cred[0], cred[1]);
            SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
            LOGGER.debug("### EgovSecurityServiceTest.testAllowAccessForAuthorizedUser {}'s password is right!!", cred[0]);
        }
    }

    @Test
    void testRejectAccessForUnauthorizedUser() {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken("user", "wrongpw");
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);

        assertThrows(BadCredentialsException.class, () -> {
            SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        });
    }

    @Test
    public void testLoginUser() {
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);
        SecurityContextHolder.getContext().setAuthentication(
                authManager.authenticate(new UsernamePasswordAuthenticationToken("user", "1")));

        List<String> authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities, "user 권한 목록");
        assertTrue(authorities.contains("ROLE_USER"), "user는 ROLE_USER 보유");
        assertFalse(authorities.contains("ROLE_ADMIN"), "user는 ROLE_ADMIN 미보유");
        LOGGER.debug("#### user 로그인 후 권한: {}", authorities);
    }

    @Test
    public void testLoginAdmin() {
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);
        SecurityContextHolder.getContext().setAuthentication(
                authManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "1")));

        List<String> authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities, "admin 권한 목록");
        assertTrue(authorities.contains("ROLE_ADMIN"), "admin은 ROLE_ADMIN 보유");
        LOGGER.debug("#### admin 로그인 후 권한: {}", authorities);
    }

    @Test
    public void testAccessRightsForUser() {
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);
        SecurityContextHolder.getContext().setAuthentication(
                authManager.authenticate(new UsernamePasswordAuthenticationToken("user", "1")));

        assertTrue(canAccess("/"), "user: / 접근 허용");
        assertTrue(canAccess("/sample/list"), "user: /sample/list 접근 허용");
        assertTrue(canAccess("/sample/detail"), "user: /sample/detail 접근 허용");
        assertFalse(canAccess("/sample/add"), "user: /sample/add 접근 차단");
        assertFalse(canAccess("/sample/update"), "user: /sample/update 접근 차단");
        assertFalse(canAccess("/sample/delete"), "user: /sample/delete 접근 차단");

        LOGGER.debug("### user 접근권한 테스트 통과");
    }

    @Test
    public void testAccessRightsForAdmin() {
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);
        SecurityContextHolder.getContext().setAuthentication(
                authManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "1")));

        assertTrue(canAccess("/"), "admin: / 접근 허용");
        assertTrue(canAccess("/sample/list"), "admin: /sample/list 접근 허용");
        assertTrue(canAccess("/sample/detail"), "admin: /sample/detail 접근 허용");
        assertTrue(canAccess("/sample/add"), "admin: /sample/add 접근 허용");
        assertTrue(canAccess("/sample/update"), "admin: /sample/update 접근 허용");
        assertTrue(canAccess("/sample/delete"), "admin: /sample/delete 접근 허용");

        LOGGER.debug("### admin 접근권한 테스트 통과");
    }

    @Test
    public void testUserDetailsExt() {
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        assertFalse(isAuthenticated.booleanValue());
        EgovUserDetailsVO user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        assertNull(user);

        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken("user", "1");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        assertTrue(EgovUserDetailsHelper.isAuthenticated().booleanValue());
        user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("user", user.getUserId());
        assertNotNull(user.getUserName());
        assertNotNull(user.getBirthDay());
        assertNotNull(user.getSsn());

        login = new UsernamePasswordAuthenticationToken("admin", "1");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        assertTrue(EgovUserDetailsHelper.isAuthenticated().booleanValue());
        user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("admin", user.getUserId());
        assertEquals("Admin", user.getUserName());

        login = new UsernamePasswordAuthenticationToken("jimi", "jimi");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("jimi", user.getUserId());
        assertEquals("jimi test", user.getUserName());
        assertEquals("19800102", user.getBirthDay());
        assertEquals("1234567890123", user.getSsn());

        login = new UsernamePasswordAuthenticationToken("test", "test");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("test", user.getUserId());
        assertEquals("Kim, Young-Su", user.getUserName());
        assertEquals("19800103", user.getBirthDay());
        assertEquals("1234567890123", user.getSsn());

        login = new UsernamePasswordAuthenticationToken("buyer", "buyer");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        user = (EgovUserDetailsVO) EgovUserDetailsHelper.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("buyer", user.getUserId());
        assertEquals("Lee, Man-hong", user.getUserName());
        assertEquals("19800104", user.getBirthDay());
        assertEquals("1234567890123", user.getSsn());
    }

    @Test
    public void testAuthoritiesAndRoleHierarchy() {
        AuthenticationManager authManager = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken("user", "1");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        List<String> authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.contains("ROLE_USER"));
        assertTrue(authorities.contains("ROLE_RESTRICTED"));

        login = new UsernamePasswordAuthenticationToken("admin", "1");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.contains("ROLE_ADMIN"));

        login = new UsernamePasswordAuthenticationToken("buyer", "buyer");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.contains("ROLE_USER"));
        assertFalse(authorities.contains("ROLE_ADMIN"));
        assertTrue(authorities.contains("ROLE_RESTRICTED"));

        login = new UsernamePasswordAuthenticationToken("test", "test");
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(login));
        authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.contains("ROLE_USER"));
        assertTrue(authorities.contains("ROLE_ADMIN"));
        assertTrue(authorities.contains("ROLE_RESTRICTED"));
    }

    private boolean canAccess(String url) {
        FilterSecurityInterceptor interceptor = applicationContext.getBean("filterSecurityInterceptor", FilterSecurityInterceptor.class);
        FilterInvocationSecurityMetadataSource metadataSource = interceptor.getSecurityMetadataSource();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath(url);
        FilterInvocation fi = new FilterInvocation(request, new MockHttpServletResponse(), new MockFilterChain());
        Collection<org.springframework.security.access.ConfigAttribute> attrs = metadataSource.getAttributes(fi);

        if (attrs == null || attrs.isEmpty()) {
            return true;
        }
        List<String> userAuthorities = EgovUserDetailsHelper.getAuthorities();
        if (userAuthorities == null) {
            return false;
        }
        for (org.springframework.security.access.ConfigAttribute attr : attrs) {
            String role = attr.getAttribute();
            if (role != null && userAuthorities.contains(role)) {
                return true;
            }
        }
        return false;
    }
}
