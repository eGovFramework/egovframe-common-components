package egovframework.com.cop.cmy.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

import egovframework.com.cop.bbs.service.impl.EgovBBSMasterDAO;

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

		"classpath*:egovframework/spring/com/idgn/context-idgn-bbs.xml",
		"classpath*:egovframework/spring/com/idgn/context-idgn-Cmmnty.xml",
//		"classpath*:egovframework/spring/com/idgn/context-idgn-AnswerNo.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cop.bbs.service.impl",
		"egovframework.com.cop.cmy.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovBBSMasterDAO.class,
						EgovCommuBBSMasterDAO.class }) })

public class EgovCommuBBSMasterDAOTest_Configuration {

}