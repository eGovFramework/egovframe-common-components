package egovframework.com.test;

import static org.junit.Assert.assertEquals;

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

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.FileManageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 구성요소 스캔 테스트
 * 
 * @author 이백행
 * @since 2023-04-17
 *
 */

@Configuration
@ImportResource({ "classpath*:/egovframework/spring/com/test-context-dao.xml" })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { FileManageDAO.class }) })

@RequiredArgsConstructor
@Slf4j

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ComponentScanTestFileManageDAOTest.class })
@ActiveProfiles({ "mysql", "dummy" })
public class ComponentScanTestFileManageDAOTest {

	/**
	 * 애플리케이션 컨텍스트
	 */
	@Autowired
	private ApplicationContext context;

	/**
	 * 파일정보 관리를 위한 데이터 처리 클래스
	 */
	@Autowired
	private FileManageDAO dao;

	/**
	 * 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		log.debug("test");

		// getBeanDefinitionNames
		final String[] beanDefinitionNames = context.getBeanDefinitionNames();

		for (final String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}

		// FileManageDAO
		final FileVO fileVO = new FileVO();
		dao.selectFileInfs(fileVO);

		assertEquals("", "", "");
	}

}