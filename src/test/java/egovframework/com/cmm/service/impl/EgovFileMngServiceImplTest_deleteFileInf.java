package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovFileMngServiceImplConfigurationTest.class })
public class EgovFileMngServiceImplTest_deleteFileInf extends EgovTestV1 {

	@Autowired
	private EgovFileMngService egovFileMngService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId("FILE_000000000000001");
//		fvo.setAtchFileId("FILE_000000000000031");
		fvo.setFileSn("1");

		// when
		boolean result = false;
		try {
			egovFileMngService.deleteFileInf(fvo);
			result = true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		// then
		assertEquals(result, true);

		log.debug("result={}", result);
	}

}