package egovframework.com.cmm;

import org.junit.Test;

import egovframework.com.test.EgovTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovComponentCheckerTest extends EgovTest {

	@Test
	public void test() {
		log.debug("test");

		// hasComponent
		log.debug("cmmUseDAO={}", EgovComponentChecker.hasComponent("cmmUseDAO"));
		log.debug("CmmUseDAO={}", EgovComponentChecker.hasComponent("CmmUseDAO"));

		// getBeanDefinitionCount
		int beanDefinitionCount = EgovComponentChecker.context.getBeanDefinitionCount();
		log.debug("beanDefinitionCount={}", beanDefinitionCount);

		// getBeanDefinitionNames
		String[] beanDefinitionNames = EgovComponentChecker.context.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}
	}

}