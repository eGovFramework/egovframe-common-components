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

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ContextConfiguration(classes = { EgovFileMngServiceImplTest_selectFileInf.class })

//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })

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
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovFileMngService.class, FileManageDAO.class }) })

public class EgovFileMngServiceImplTest_selectFileInf extends EgovTestV1 {

	@Autowired
	private EgovFileMngService egovFileMngService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = getFileVO();
//		FileVO fvo = getFileVO2();

		// when
		FileVO fileInf = egovFileMngService.selectFileInf(fvo);

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

	FileVO getFileVO() {
		FileVO fvo = new FileVO();
		fvo.setAtchFileId("FILE_000000000000001");
		fvo.setFileSn("1");
		return fvo;
	}

	FileVO getFileVO2() {
		FileVO fvo = new FileVO();
		fvo.setAtchFileId("FILE_000000000000031");
		fvo.setFileSn("1");
		return fvo;
	}

}
