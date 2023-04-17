package egovframework.com.cmm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.test.EgovTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovComponentCheckerTest_egovUtil extends EgovTest {

	@Autowired
	EgovComponentChecker egovUtil;

	@SuppressWarnings("static-access")
	@Test
	public void test() {
		log.debug("test");

		// hasComponent
		log.debug("cmmUseDAO={}", egovUtil.hasComponent("cmmUseDAO"));
		log.debug("CmmUseDAO={}", egovUtil.hasComponent("CmmUseDAO"));

		// getBeanDefinitionCount
		int beanDefinitionCount = egovUtil.context.getBeanDefinitionCount();
		log.debug("beanDefinitionCount={}", beanDefinitionCount);

		// getBeanDefinitionNames
		String[] beanDefinitionNames = egovUtil.context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

}