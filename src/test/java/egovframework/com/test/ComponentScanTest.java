package egovframework.com.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.config.EgovConfigCryptoTest;
import egovframework.com.cmm.service.impl.CmmUseDAO;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml", })
@Import(EgovConfigCryptoTest.class)
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl", }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { CmmUseDAO.class, }) })

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ComponentScanTest.class, })
@ActiveProfiles({ "mysql", "dummy" })
class ComponentScanTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private CmmUseDAO dao;

	@Test
	void test() {
		// getBeanDefinitionNames
		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}

		// CmmUseDAO
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM001");
		dao.selectCmmCodeDetail(vo);
	}

}