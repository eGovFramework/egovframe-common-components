package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EgovFileMngServiceImplTest_insertFileInfs.class })
@ActiveProfiles({ "mysql", "dummy" })
@Transactional

@Configuration
@ImportResource({ "classpath*:egovframework/spring/com/test-context-dao.xml",
		"classpath*:egovframework/spring/com/idgn/context-idgn-File.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovFileMngService.class, FileManageDAO.class }) })

public class EgovFileMngServiceImplTest_insertFileInfs {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private EgovFileMngService service;

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
		int maxFileSN = service.getMaxFileSN(fvo);
		log.debug("maxFileSN={}", maxFileSN);

		List<FileVO> fileList = new ArrayList<>();
		FileVO file = new FileVO();
		file.setAtchFileId(fvo.getAtchFileId());
		file.setFileSn(String.valueOf(maxFileSN++));
		file.setFileMg(file.getFileSn());
		fileList.add(file);

		file = new FileVO();
		file.setAtchFileId(fvo.getAtchFileId());
		file.setFileSn(String.valueOf(maxFileSN++));
		file.setFileMg(file.getFileSn());
		fileList.add(file);

		for (int i = 0; i < 10; i++) {
			file = new FileVO();
			file.setAtchFileId(fvo.getAtchFileId());
			file.setFileSn(String.valueOf(maxFileSN++));
			file.setFileMg(file.getFileSn());
			fileList.add(file);
		}

		// when
		String result = service.insertFileInfs(fileList);

		// then
		assertEquals(result, fvo.getAtchFileId());

		log.debug("result={}", result);
	}

}
