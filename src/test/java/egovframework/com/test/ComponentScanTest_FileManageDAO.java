package egovframework.com.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.FileManageDAO;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { FileManageDAO.class }) })

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ComponentScanTest_FileManageDAO.class })
@ActiveProfiles({ "mysql", "dummy" })
public class ComponentScanTest_FileManageDAO {

	@Autowired
	ApplicationContext context;

	@Autowired
	FileManageDAO dao;

	@Test
	void test() throws Exception {
		log.debug("test");

		// getBeanDefinitionNames
		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}

		// FileManageDAO
		FileVO vo = new FileVO();
		dao.selectFileInfs(vo);
	}

}