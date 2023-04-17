package egovframework.com.cop.adb.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import egovframework.com.cop.adb.service.EgovAddressBookService;

@Configuration

@ImportResource({

//	"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-transaction.xml",

		"classpath*:/egovframework/spring/com/idgn/context-idgn-Adbk.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.cop.adb.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AddressBookDAO.class,
						EgovAddressBookService.class }) })

public class AddressBookConfigurationTest {

}