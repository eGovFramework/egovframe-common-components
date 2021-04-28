package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EgovFileMngServiceImpl_selectFileInfs.class })
@ActiveProfiles({ "mysql", "dummy" })
@Transactional

@Configuration
@ImportResource({

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-transaction.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovFileMngService.class, FileManageDAO.class }) })

public class EgovFileMngServiceImpl_selectFileInfs {

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@Autowired
	private EgovFileMngService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.debug("setUpBeforeClass");

		log.debug("start");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.debug("tearDownAfterClass");

		log.debug("stop");
		STOP_WATCH.stop();

		log.debug("getTotalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
		log.debug("getTotalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.debug("setUp");

		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@After
	public void tearDown() throws Exception {
		log.debug("tearDown");
	}

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO vo = new FileVO();
		vo.setAtchFileId("FILE_000000000000001");
//		vo.setAtchFileId("FILE_000000000000031");

		// when
		List<FileVO> results = service.selectFileInfs(vo);
		int size = results.size();

		// then
		if (size > 0) {
			assertEquals(results.get(0).getAtchFileId(), vo.getAtchFileId());
		}

		log.debug("results={}", results);
		log.debug("size={}", size);

		results.forEach(result -> {
			log.debug("result={}", result);
			log.debug("getAtchFileId={}", result.getAtchFileId());
			log.debug("getFileCn={}", result.getFileCn());
		});
	}

}
