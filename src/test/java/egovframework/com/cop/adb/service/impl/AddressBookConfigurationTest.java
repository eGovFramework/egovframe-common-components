package egovframework.com.cop.adb.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:egovframework/spring/com/context-*.xml",

		"classpath*:egovframework/spring/com/idgn/context-*.xml",

//	"file:src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-*.xml",

})

//@ComponentScan(useDefaultFilters = false, basePackages = {
//		"egovframework.com.cop.adb.service.impl" }, includeFilters = {
//				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AddressBookDAO.class,
//						EgovAddressBookService.class }) })

public class AddressBookConfigurationTest {

}