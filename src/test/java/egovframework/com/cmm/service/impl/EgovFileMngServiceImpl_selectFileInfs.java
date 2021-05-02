package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

@ContextConfiguration(classes = { EgovFileMngServiceImpl_selectFileInfs.class })

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

public class EgovFileMngServiceImpl_selectFileInfs extends EgovTestV1 {

	@Autowired
	private EgovFileMngService egovFileMngService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO vo = new FileVO();
		vo.setAtchFileId("FILE_000000000000001");
//		vo.setAtchFileId("FILE_000000000000031");

		// when
		List<FileVO> results = egovFileMngService.selectFileInfs(vo);
		int size = results.size();

		// then
		if (size > 0) {
			assertEquals(results.get(0).getAtchFileId(), vo.getAtchFileId());
		}

		log.debug("results={}", results);
		log.debug("size={}", size);

		results.forEach(result -> {
			log.debug("result={}", result);
			log.debug("getAtchFileId={}", result.getAtchFileId());
			log.debug("getFileCn={}", result.getFileCn());
		});
	}

}
