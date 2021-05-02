package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ContextConfiguration(classes = { FileManageDAOTest_selectFileInf.class })

//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-transaction.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl", }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { FileManageDAO.class, }) })

public class FileManageDAOTest_selectFileInf extends EgovTestV1 {

	@Autowired
	private FileManageDAO fileManageDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId("FILE_000000000000001");
//		fvo.setAtchFileId("FILE_000000000000031");
		fvo.setFileSn("1");

		// when
		FileVO fileInf = fileManageDAO.selectFileInf(fvo);

		// then
		if (fileInf == null) {
			return;
		}

		assertEquals(fileInf.getAtchFileId(), fvo.getAtchFileId());
		assertEquals(fileInf.getFileSn(), fvo.getFileSn());

		log.debug("fileInf={}", fileInf);
		log.debug("getAtchFileId={}", fileInf.getAtchFileId());
		log.debug("getFileSn={}", fileInf.getFileSn());
	}

	@Test
	public void test2() {
		log.debug("test2");
	}

}
