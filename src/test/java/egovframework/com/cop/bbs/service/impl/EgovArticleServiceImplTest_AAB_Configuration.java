package egovframework.com.cop.bbs.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import egovframework.com.cmm.service.impl.EgovFileMngServiceImpl;
import egovframework.com.cmm.service.impl.FileManageDAO;

@Configuration

@ImportResource({

//	"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:egovframework/spring/com/context-crypto.xml",
		"classpath*:egovframework/spring/com/context-datasource.xml",
		"classpath*:egovframework/spring/com/context-egovuserdetailshelper.xml",
		"classpath*:egovframework/spring/com/context-mapper.xml",
		"classpath*:egovframework/spring/com/context-properties.xml",
		"classpath*:egovframework/spring/com/context-transaction.xml",

		"classpath*:egovframework/spring/com/idgn/context-idgn-bbs.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cop.bbs.service.impl",
"egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovArticleDAO.class, EgovBBSMasterDAO.class,
				EgovArticleServiceImplTest_AAC_TestData.class, EgovArticleDAOTest_AaaTestData.class,
				EgovArticleServiceImpl.class, EgovFileMngServiceImpl.class, FileManageDAO.class }) })

public class EgovArticleServiceImplTest_AAB_Configuration {

}