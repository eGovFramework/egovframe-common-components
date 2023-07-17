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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-07-17
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

@ContextConfiguration(locations = {

//		"classpath*:egovframework/spring/com/**/context-*.xml",

        "classpath*:egovframework/spring/com/context-*.xml",

        "classpath*:egovframework/spring/com/idgn/context-*.xml",

        "classpath*:egovframework/spring/com/scheduling/context-*.xml",

})

@Transactional

@RequiredArgsConstructor
@Slf4j

public abstract class EgovTestAbstractDAO2 {

    /**
     * STOP_WATCH
     */
    private static final StopWatch STOP_WATCH = new StopWatch();

    /**
     * stopWatch
     */
    private final StopWatch stopWatch = new StopWatch();

    /**
     * beanDefinitionNames
     */
    private static String[] beanDefinitionNames;

    /**
     * ApplicationContext
     */
    @Autowired
    private ApplicationContext context;

    /**
     * setUpBeforeClass
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        STOP_WATCH.start();

        log.debug("setUpBeforeClass start");
    }

    /**
     * tearDownAfterClass
     */
    @AfterClass
    public static void tearDownAfterClass() {
        STOP_WATCH.stop();

        log.debug("tearDownAfterClass stop");

        log.debug("totalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
        log.debug("totalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
    }

    /**
     * setUp
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
            log.debug("length={}", beanDefinitionNames.length);
        }
    }

    /**
     * tearDown
     */
    @After
    public void tearDown() {
        stopWatch.stop();

        log.debug("tearDown stop");

        log.debug("totalTimeMillis={}", stopWatch.getTotalTimeMillis());
        log.debug("totalTimeSeconds={}", stopWatch.getTotalTimeSeconds());
    }

}
