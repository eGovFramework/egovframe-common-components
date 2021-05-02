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

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ContextConfiguration(classes = { FileManageDAOTest_updateFileInfs.class })

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

		"classpath*:egovframework/spring/com/idgn/context-idgn-File.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { FileManageDAO.class }) })

public class FileManageDAOTest_updateFileInfs extends EgovTestV1 {

	@Autowired
	private FileManageDAO dao;

	@Autowired
	@Qualifier("egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		int maxFileSN = dao.getMaxFileSN(fvo);
		log.debug("maxFileSN={}", maxFileSN);
		fvo.setFileSn(String.valueOf(maxFileSN++));
		fvo.setFileMg(fvo.getFileSn());
		dao.insertFileInf(fvo);

		List<FileVO> fileList = new ArrayList<>();
		FileVO file = new FileVO();
		file.setAtchFileId(fvo.getAtchFileId());
		file.setFileSn(String.valueOf(maxFileSN++));
		file.setFileMg(file.getFileSn());
		fileList.add(file);

		file = new FileVO();
		file.setAtchFileId(fvo.getAtchFileId());
		file.setFileSn(String.valueOf(maxFileSN++));
		file.setFileMg(file.getFileSn());
		fileList.add(file);

		for (int i = 0; i < 10; i++) {
			file = new FileVO();
			file.setAtchFileId(fvo.getAtchFileId());
			file.setFileSn(String.valueOf(maxFileSN++));
			file.setFileMg(file.getFileSn());
			fileList.add(file);
		}

		// when
		boolean result = false;
		try {
			dao.updateFileInfs(fileList);
			result = true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		// then
		assertEquals(result, true);

		log.debug("result={}", result);
	}

}
