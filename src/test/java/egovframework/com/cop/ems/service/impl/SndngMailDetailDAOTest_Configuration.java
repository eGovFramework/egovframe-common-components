package egovframework.com.cop.ems.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@Configuration

@ImportResource({

//	"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:egovframework/spring/com/context-crypto.xml",
		"classpath*:egovframework/spring/com/context-datasource.xml",
		"classpath*:egovframework/spring/com/context-egovuserdetailshelper.xml",
		"classpath*:egovframework/spring/com/context-mapper.xml",
//		"classpath*:egovframework/spring/com/context-properties.xml",
		"classpath*:egovframework/spring/com/context-transaction.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

		"classpath*:egovframework/spring/com/idgn/context-idgn-MailMsg.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.com.cop.ems.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { SndngMailDetailDAO.class,
						SndngMailRegistDAO.class }) })

public class SndngMailDetailDAOTest_Configuration {

}