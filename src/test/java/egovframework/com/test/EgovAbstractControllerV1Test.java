package egovframework.com.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StopWatch;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@OrderWith(Alphanumeric.class)

@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "goldilocks", "dummy" })

@WebAppConfiguration

@ContextConfiguration(locations = {

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:egovframework/spring/com/context-*.xml",

		"classpath*:egovframework/spring/com/idgn/context-*.xml",

		"classpath*:egovframework/spring/com/scheduling/context-*.xml",

		"file:src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-*.xml",

})

public abstract class EgovAbstractControllerV1Test {

	protected static Logger EGOV_LOGGER = LoggerFactory.getLogger(EgovAbstractControllerV1Test.class);
	protected Logger egovLogger = LoggerFactory.getLogger(EgovAbstractControllerV1Test.class);

	protected static final StopWatch STOP_WATCH = new StopWatch();
	protected final StopWatch stopWatch = new StopWatch();

	private static String[] beanDefinitionNames = null;

	@Autowired
	private WebApplicationContext context;

	protected static MockMvc mockMvc;

	@BeforeClass
	public static void setUpBeforeClass() {
		STOP_WATCH.start();

		EGOV_LOGGER.debug("setUpBeforeClass start");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		STOP_WATCH.stop();

		EGOV_LOGGER.debug("tearDownAfterClass stop");

		EGOV_LOGGER.debug("totalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		EGOV_LOGGER.debug("totalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() {
		stopWatch.start();

		egovLogger.debug("setUp start");

		if (beanDefinitionNames == null) {
			beanDefinitionNames = context.getBeanDefinitionNames();
			for (String beanDefinitionName : beanDefinitionNames) {
				egovLogger.debug("beanDefinitionName={}", beanDefinitionName);
			}
			egovLogger.debug("length={}", beanDefinitionNames.length);

			mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		}
	}

	@After
	public void tearDown() {
		stopWatch.stop();

		egovLogger.debug("tearDown stop");

		egovLogger.debug("totalTimeMillis={}", stopWatch.getTotalTimeMillis());
		egovLogger.debug("totalTimeSeconds={}", stopWatch.getTotalTimeSeconds());
	}

}
