package egovframework.com.auth.session;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.access.bean.AuthorityResourceMetadata;
import org.egovframe.rte.fdl.access.interceptor.EgovAccessUtil;
import org.egovframe.rte.fdl.access.service.EgovUserDetailsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * EgovAccessConfigTest Test Class 구현 (실행환경 세션접근제어 테스트)
 * @author 표준프레임워크 정진오
 * @since 2019.10.01
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2019.11.20  정진오      최초 생성
 *  2026.01.26  신용호      JUnit 4 => JUnit 5 마이그레이션
 *
   
 * </pre>
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"classpath*:egovframework/spring/session/*.xml"})
public class EgovAccessConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovAccessConfigTest.class);

    protected MockHttpServletRequest request;
    protected MockHttpSession session;

    private ApplicationContext context;

    @BeforeEach
    void setUp() throws Exception{
        session = new MockHttpSession();
        session.setAttribute("accessUser","GNRUSER"); // 일반회원

        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterEach
    void clear(){
        session.clearAttributes();
        session = null;
    }

    @Test
    void test() throws Exception {

        context = new ClassPathXmlApplicationContext(new String[] {"classpath*:egovframework/spring/session/*.xml"});

        String[] contextList = context.getBeanDefinitionNames();
        for (String bean : contextList) {
            LOGGER.debug("##### EgovAccessConfigTest context list >>> {} ", context.getBean(bean).getClass());
        }

        String requestMatchType = "regex";
        String url = "/uss/umt/dpt/selectDeptManageListView.do"; // 일반회원 허용 URL
        boolean matchStatus = false;
        List<String> authorityList = EgovUserDetailsHelper.getAuthorities();
        LOGGER.debug("##### EgovAccessConfigTest authorityList : {} #####", authorityList);
        String authority = "";
        for (String str : authorityList) {
            authority = str;
        }

        AuthorityResourceMetadata authorityResourceMetadata = context.getBean(AuthorityResourceMetadata.class);
        List<Map<String, Object>> list = authorityResourceMetadata.getResourceMap();
        Iterator<Map<String, Object>> iterator = list.iterator();
        Map<String, Object> tempMap;
        while (iterator.hasNext()) {
            tempMap = iterator.next();
            if (authority.equals(tempMap.get("authority"))) {
            	System.out.println("===>>> "+tempMap.get("url"));
                if ("ant".equals(requestMatchType)) {
                    LOGGER.debug("##### EgovAccessConfigTest Ant pattern #####");
                    matchStatus = EgovAccessUtil.antMatcher((String) tempMap.get("url"), url);
                    LOGGER.debug("##### EgovAccessConfigTest ant pattern : {} #####", tempMap.get("url"));
                    LOGGER.debug("##### EgovAccessConfigTest ant url : {} #####", url);
                    LOGGER.debug("##### EgovAccessConfigTest ant match status : {} #####", matchStatus);
                } else {
                    LOGGER.debug("##### EgovAccessConfigTest Regex pattern #####");
                    matchStatus = EgovAccessUtil.regexMatcher((String) tempMap.get("url"), url);
                    LOGGER.debug("##### EgovAccessConfigTest regex pattern : {} #####", tempMap.get("url"));
                    LOGGER.debug("##### EgovAccessConfigTest regex url : {} #####", url);
                    LOGGER.debug("##### EgovAccessConfigTest regex match status : {} #####", matchStatus);
                }
            }
        }
        
        Assertions.assertTrue(matchStatus);

    }

}
