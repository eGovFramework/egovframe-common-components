package egovframework.com.cop.adb.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import egovframework.com.cop.adb.service.EgovAddressBookService;

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/context-*.xml",

		"classpath*:egovframework/spring/com/idgn/context-*.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.cop.adb.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AddressBookDAO.class,
						EgovAddressBookService.class }) })

public class AddressBookConfigurationTest {

}