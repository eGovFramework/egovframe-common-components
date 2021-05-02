package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ContextConfiguration(classes = { EgovFileMngServiceImplTest_deleteFileInfs.class })

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

		"classpath*:/egovframework/spring/com/idgn/context-idgn-File.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovFileMngService.class, FileManageDAO.class }) })

public class EgovFileMngServiceImplTest_deleteFileInfs extends EgovTestV1 {

	@Autowired
	private EgovFileMngService egovFileMngService;

	@Autowired
	@Qualifier("egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		int maxFileSN = egovFileMngService.getMaxFileSN(fvo);
		log.debug("maxFileSN={}", maxFileSN);
		fvo.setFileSn(String.valueOf(maxFileSN++));
		fvo.setFileMg(fvo.getFileSn());
		egovFileMngService.insertFileInf(fvo);

		List<FileVO> fileList = new ArrayList<>();
		FileVO file = new FileVO();
		file.setAtchFileId(fvo.getAtchFileId());
		file.setFileSn(String.valueOf(maxFileSN++));
		fileList.add(file);

		file = new FileVO();
		file.setAtchFileId(fvo.getAtchFileId());
		file.setFileSn(String.valueOf(maxFileSN++));
		fileList.add(file);

		for (int i = 0; i < 10; i++) {
			file = new FileVO();
			file.setAtchFileId(fvo.getAtchFileId());
			file.setFileSn(String.valueOf(maxFileSN++));
			fileList.add(file);
		}

		// when
		boolean result = false;
		try {
			egovFileMngService.deleteFileInfs(fileList);
			result = true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		// then
		assertEquals(result, true);

		log.debug("result={}", result);
	}

}
