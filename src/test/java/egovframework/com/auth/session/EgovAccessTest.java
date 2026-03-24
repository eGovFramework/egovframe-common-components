package egovframework.com.auth.session;

import egovframework.com.auth.session.config.EgovAccessTestConfig;
import egovframework.com.auth.session.config.EgovAccessTestDatasource;
import org.egovframe.rte.fdl.access.config.EgovAccessConfig;
import org.egovframe.rte.fdl.access.config.EgovAccessConfiguration;
import org.egovframe.rte.fdl.access.interceptor.EgovAccessUtil;
import org.egovframe.rte.fdl.access.service.EgovUserDetailsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * egov-access 모듈 통합 테스트
 *
 * <p>- property에서 설정한 위치의 설정 파일 로드 검증</p>
 * <p>- user / admin 로그인(세션) 및 접근권한 검증</p>
 *
 * <p>원본: org.egovframe.rte.fdl.access.EgovAccessTest (egovframe-rte-fdl-access)</p>
 *
 * @author 유지보수
 * @version 5.0
 * @since 2025.06.01
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EgovAccessTestDatasource.class, EgovAccessConfiguration.class, EgovAccessTestConfig.class })
public class EgovAccessTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovAccessTest.class);

    @Autowired
    private EgovAccessConfig accessConfig;

    protected MockHttpServletRequest request;
    protected MockHttpSession session;

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterEach
    public void clear() {
        if (session != null) {
            session.clearAttributes();
        }
        session = null;
        RequestContextHolder.resetRequestAttributes();
    }

    /**
     * globals.properties의 Globals.AccessConfigPath 값을 바탕으로 해당 경로 설정 파일을 읽어
     * EgovAccessConfig 빈에 반영되었는지 getter로 검증
     */
    @Test
    public void testAccessConfigValues() {
        assertNotNull(accessConfig, "EgovAccessConfig 빈 주입");

        assertEquals("egovAccessConfig", accessConfig.getId(), "id");
        assertEquals("session", accessConfig.getGlobalAuthen(), "globalAuthen");
        assertEquals("/**/*.do", accessConfig.getMappingPath(), "mappingPath");
        assertEquals("dataSource", accessConfig.getDataSource(), "dataSource");
        assertEquals("/uat/uia/egovLoginUsr.do", accessConfig.getLoginUrl(), "loginUrl");
        assertEquals("/uat/uia/egovLoginUsr.do?auth_error=1", accessConfig.getAccessDeniedUrl(), "accessDeniedUrl");
        assertEquals("ant", accessConfig.getRequestMatcherType(), "requestMatcherType");

        assertNotNull(accessConfig.getSqlAuthorityUser(), "sqlAuthorityUser");
        assertTrue(accessConfig.getSqlAuthorityUser().contains("USER_ID") && accessConfig.getSqlAuthorityUser().contains("AUTHORITIES"), "sqlAuthorityUser 내용");

        assertNotNull(accessConfig.getSqlRoleAndUrl(), "sqlRoleAndUrl");
        assertTrue(accessConfig.getSqlRoleAndUrl().contains("ROLE_PTTRN") && accessConfig.getSqlRoleAndUrl().contains("AUTHROLES"), "sqlRoleAndUrl 내용");

        assertNotNull(accessConfig.getExcludeList(), "excludeList");
        assertTrue(accessConfig.getExcludeList().contains("/uat/uia/**"), "excludeList에 /uat/uia/** 포함");
    }

    /**
     * user 로그인(세션에 accessUser 설정) 후 해당 권한이 부여되는지 확인
     */
    @Test
    public void testLoginUser() {
        session.setAttribute("accessUser", "user");

        List<String> authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities, "user 권한 목록");
        assertTrue(authorities.contains("ROLE_USER"), "user는 ROLE_USER 보유");
        assertFalse(authorities.contains("ROLE_ADMIN"), "user는 ROLE_ADMIN 미보유");

        LOGGER.debug("### EgovAccessTest testLoginUser() User Authority : {}", authorities);
    }

    /**
     * admin 로그인(세션에 accessUser 설정) 후 해당 권한이 부여되는지 확인
     */
    @Test
    public void testLoginAdmin() {
        session.setAttribute("accessUser", "admin");

        List<String> authorities = EgovUserDetailsHelper.getAuthorities();
        assertNotNull(authorities, "admin 권한 목록");
        assertTrue(authorities.contains("ROLE_ADMIN"), "admin은 ROLE_ADMIN 보유");

        LOGGER.debug("### EgovAccessTest testLoginAdmin() Admin Authority : {}", authorities);
    }

    /**
     * user(ROLE_USER)로 로그인 후 설정된 롤에 따라 접근 가능/불가 URL 검증
     * - ROLE_USER: role-root, role-list, role-detail, role-access-denied
     * - /, /sample/list, /sample/detail, /sample/accessDenied 허용
     * - /sample/add, /sample/update, /sample/delete 차단
     */
    @Test
    public void testAccessRightsForUser() {
        session.setAttribute("accessUser", "user");
        String requestMatchType = "ant";

        assertTrue(authCheck(requestMatchType, "/"), "user: / 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/list"), "user: /sample/list 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/detail"), "user: /sample/detail 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/accessDenied"), "user: /sample/accessDenied 접근 허용");

        assertFalse(authCheck(requestMatchType, "/sample/add"), "user: /sample/add 접근 차단");
        assertFalse(authCheck(requestMatchType, "/sample/update"), "user: /sample/update 접근 차단");
        assertFalse(authCheck(requestMatchType, "/sample/delete"), "user: /sample/delete 접근 차단");

        LOGGER.debug("### EgovAccessTest testAccessRightsForUser() User authorization test passed ");
    }

    /**
     * admin(ROLE_ADMIN)으로 로그인 후 설정된 롤에 따라 모든 샘플 URL 접근 허용 검증
     */
    @Test
    public void testAccessRightsForAdmin() {
        session.setAttribute("accessUser", "admin");
        String requestMatchType = "ant";

        assertTrue(authCheck(requestMatchType, "/"), "admin: / 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/list"), "admin: /sample/list 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/detail"), "admin: /sample/detail 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/add"), "admin: /sample/add 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/update"), "admin: /sample/update 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/delete"), "admin: /sample/delete 접근 허용");
        assertTrue(authCheck(requestMatchType, "/sample/accessDenied"), "admin: /sample/accessDenied 접근 허용");

        LOGGER.debug("### EgovAccessTest testAccessRightsForAdmin() Admin authorization test passed ");
    }

    private boolean authCheck(String requestMatchType, String url) {
        boolean authCheck = false;
        List<String> authList = EgovUserDetailsHelper.getAuthorities();
        List<Map<String, Object>> rolesList = EgovUserDetailsHelper.getRoles();

        if (!ObjectUtils.isEmpty(authList) && !ObjectUtils.isEmpty(rolesList)) {
            List<String> roleList = new ArrayList<>();
            for (String auth : authList) {
                Iterator<Map<String, Object>> iterator = rolesList.iterator();
                Map<String, Object> roleMap;
                while (iterator.hasNext()) {
                    roleMap = iterator.next();
                    if (auth.equals(roleMap.get("authority"))) {
                        roleList.add((String) roleMap.get("url"));
                    }
                }
            }

            List<String> urlList = roleList.stream().distinct().toList();
            for (String authUrl : urlList) {
                if ("ant".equals(requestMatchType)) {
                    authCheck = EgovAccessUtil.antMatcher(authUrl, url);
                } else {
                    authCheck = EgovAccessUtil.regexMatcher(authUrl, url);
                }
                if (authCheck) break;
            }
        }

        return authCheck;
    }

}
