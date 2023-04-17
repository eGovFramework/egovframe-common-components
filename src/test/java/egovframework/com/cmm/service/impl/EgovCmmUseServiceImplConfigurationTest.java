package egovframework.com.cmm.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import egovframework.com.cmm.service.EgovCmmUseService;

@Configuration

@ImportResource({

//	"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-transaction.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovCmmUseService.class, CmmUseDAO.class }) })

public class EgovCmmUseServiceImplConfigurationTest {

}