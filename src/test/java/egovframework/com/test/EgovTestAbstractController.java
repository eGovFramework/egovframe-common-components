package egovframework.com.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StopWatch;
import org.springframework.web.context.WebApplicationContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller 단위 테스트
 * 
 * @author 이백행
 * @since 2023-05-30
 *
 */

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

@RequiredArgsConstructor
@Slf4j

public class EgovTestAbstractController {

	/**
	 * 스톱워치
	 */
	protected static final StopWatch STOP_WATCH = new StopWatch();

	/**
	 * 스톱워치
	 */
	protected final StopWatch stopWatch = new StopWatch();

	/**
	 * 빈 정의 이름
	 */
	private static String[] beanDefinitionNames;

	/**
	 * 웹 애플리케이션 컨텍스트
	 */
	@Autowired
	private WebApplicationContext context;

	/**
	 * 모의 Mvc
	 */
	protected static MockMvc mockMvc;

	/**
	 * 수업 전, 수업 전 설정
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		STOP_WATCH.start();

		log.debug("setUpBeforeClass start");
	}

	/**
	 * 방과후, 수업 후 해체
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		STOP_WATCH.stop();

		if (log.isDebugEnabled()) {
			log.debug("tearDownAfterClass stop");

			log.debug("totalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
			log.debug("totalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
		}
	}

	/**
	 * 전에, 설정
	 */
	@Before
	public void setUp() {
		stopWatch.start();

		log.debug("setUp start");

		if (beanDefinitionNames == null) {
			beanDefinitionNames = context.getBeanDefinitionNames();
			for (final String beanDefinitionName : beanDefinitionNames) {
				log.debug("beanDefinitionName={}", beanDefinitionName);
			}
			if (log.isDebugEnabled()) {
				log.debug("length={}", beanDefinitionNames.length);
			}

			mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		}
	}

	/**
	 * 후에, 분해
	 */
	@After
	public void tearDown() {
		stopWatch.stop();

		if (log.isDebugEnabled()) {
			log.debug("tearDown stop");

			log.debug("totalTimeMillis={}", stopWatch.getTotalTimeMillis());
			log.debug("totalTimeSeconds={}", stopWatch.getTotalTimeSeconds());
		}
	}

}
