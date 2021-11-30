package egovframework.com.cop.com.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import egovframework.com.cop.cmy.service.impl.EgovCommuManageDAO;
import egovframework.com.cop.cmy.service.impl.EgovCommuMasterDAO;
import egovframework.com.uss.umt.service.impl.MberManageDAO;

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

		"classpath*:egovframework/spring/com/idgn/context-idgn-Cmmnty.xml",
//		"classpath*:egovframework/spring/com/idgn/context-idgn-Clb.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cop.com.service.impl",
		"egovframework.com.uss.umt.service.impl", "egovframework.com.cop.cmy.service.impl" }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovUserInfManageDAO.class, MberManageDAO.class,
						EgovCommuManageDAO.class, EgovCommuMasterDAO.class }) })

public class EgovUserInfManageDAOTest_Configuration {

}