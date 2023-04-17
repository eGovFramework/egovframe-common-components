package egovframework.com.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)

//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })

@Transactional
public class EgovTestV1 {

	public static final StopWatch STOP_WATCH = new StopWatch();
	public static final StopWatch STOP_WATCH2 = new StopWatch();

	private static String[] beanDefinitionNames = null;

	@Autowired
	private ApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("setUpBeforeClass");
		log.info("");

		log.info("start");
		log.info("");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("tearDownAfterClass");
		log.info("");

		log.info("stop");
		log.info("");
		STOP_WATCH.stop();

		log.info("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		log.info("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.info("setUp");
		log.info("");

		log.info("start2");
		log.info("");
		STOP_WATCH2.start();

		if (beanDefinitionNames == null) {
			beanDefinitionNames = context.getBeanDefinitionNames();
			log.info("beanDefinitionNames.length={}", beanDefinitionNames.length);
			for (String beanDefinitionName : beanDefinitionNames) {
				log.info("beanDefinitionName={}", beanDefinitionName);
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		log.info("tearDown");
		log.info("");

		log.info("stop2");
		log.info("");
		STOP_WATCH2.stop();

		log.info("getTotalTimeMillis2={}", STOP_WATCH2.getTotalTimeMillis());
		log.info("getTotalTimeSeconds2={}", STOP_WATCH2.getTotalTimeSeconds());
		log.info("");
	}

}