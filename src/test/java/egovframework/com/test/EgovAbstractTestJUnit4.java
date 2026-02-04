package egovframework.com.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * JUnit4
 * 
 * @author 이백행
 * @since 2025.07.15
 * @version 4.3.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.15  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@OrderWith(Alphanumeric.class)

@ContextConfiguration(locations = {

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:egovframework/spring/com/context-*.xml",

		"classpath*:egovframework/spring/com/idgn/context-*.xml",

		"classpath*:egovframework/spring/com/scheduling/context-*.xml",

		"file:src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-*.xml",

})

@WebAppConfiguration

@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "goldilocks", "dummy" })

@Transactional
public class EgovAbstractTestJUnit4 {

	private static final StopWatch STOP_WATCH = new StopWatch();
	private final StopWatch stopWatch = new StopWatch();

	@Autowired
	private ApplicationContext context;
	private String[] beanDefinitionNames;

	@Autowired
	private WebApplicationContext wac;
	protected MockMvc mockMvc;

	@BeforeClass
	public static void setUpBeforeClass() {
		STOP_WATCH.start();

		if (log.isDebugEnabled()) {
			log.debug("setUpBeforeClass, @BeforeClass");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() {
		STOP_WATCH.stop();

		if (log.isDebugEnabled()) {
			log.debug("tearDownAfterClass, @AfterClass");

			log.debug("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
			log.debug("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
		}
	}

	@Before
	public void setUp() {
		stopWatch.start();

		if (log.isDebugEnabled()) {
			log.debug("setUp, @Before");
		}

		beanDefinitionNames = context.getBeanDefinitionNames();
		if (log.isDebugEnabled()) {
			log.debug("beanDefinitionNames.length={}", beanDefinitionNames.length);
		}
		for (String beanDefinitionName : beanDefinitionNames) {
			if (log.isDebugEnabled()) {
				log.debug("beanDefinitionName={}", beanDefinitionName);
			}
		}

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@After
	public void tearDown() {
		stopWatch.stop();

		if (log.isDebugEnabled()) {
			log.debug("tearDown, @After");

			log.debug("getTotalTimeMillis2={}", stopWatch.getTotalTimeMillis());
			log.debug("getTotalTimeSeconds2={}", stopWatch.getTotalTimeSeconds());
		}
	}

}
