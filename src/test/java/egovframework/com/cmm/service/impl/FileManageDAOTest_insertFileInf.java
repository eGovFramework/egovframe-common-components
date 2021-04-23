package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
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

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FileManageDAOTest_insertFileInf.class })
@ActiveProfiles({ "mysql", "dummy" })
@Transactional

@Configuration
@ImportResource({ "classpath*:egovframework/spring/com/test-context-dao.xml",
		"classpath*:egovframework/spring/com/idgn/context-idgn-File.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { FileManageDAO.class }) })

public class FileManageDAOTest_insertFileInf {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private FileManageDAO dao;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Before
	public void setUp() throws Exception {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		int maxFileSN = dao.getMaxFileSN(fvo);
		log.debug("maxFileSN={}", maxFileSN);

		FileVO vo = new FileVO();
		vo.setAtchFileId(fvo.getAtchFileId());
		vo.setFileSn(String.valueOf(maxFileSN++));
		vo.setFileMg(vo.getFileSn());

		// when
		boolean result = false;
		try {
			dao.insertFileInf(vo);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// then
		assertEquals(result, true);

		log.debug("result={}", result);
	}

}
