package egovframework.com.cop.bbs.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import egovframework.com.cmm.config.EgovConfigCryptoTest;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.impl.EgovFileMngServiceImpl;
import egovframework.com.cmm.service.impl.FileManageDAO;

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/context-datasource.xml",
		"classpath*:egovframework/spring/com/context-egovuserdetailshelper.xml",
		"classpath*:egovframework/spring/com/context-mapper.xml",
		"classpath*:egovframework/spring/com/context-properties.xml",
		"classpath*:egovframework/spring/com/context-transaction.xml",

		"classpath*:egovframework/spring/com/idgn/context-idgn-bbs.xml",
		"classpath*:egovframework/spring/com/idgn/context-idgn-File.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@Import(EgovConfigCryptoTest.class)

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.cop.bbs.service.impl",

				"egovframework.com.cmm.service.impl",

				"egovframework.com.cmm.service",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								EgovArticleDAO.class,

								EgovBBSMasterDAO.class,

								EgovArticleServiceImplTest_AAC_TestData.class,

								EgovArticleDAOTest_AaaTestData.class,

								EgovArticleServiceImpl.class,

								EgovFileMngServiceImpl.class,

								FileManageDAO.class,

								EgovFileMngUtil.class,

						}

				)

		}

)

public class EgovArticleServiceImplTest_AAB_Configuration {

}