package egovframework.com.cmm.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { FileManageDAOConfigurationTest.class })
public class FileManageDAOTest_deleteAllFileInf extends EgovTestV1 {

	@Autowired
	private FileManageDAO fileManageDAO;

	@Test
	public void test() {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId("FILE_000000000000001");
//		fvo.setAtchFileId("FILE_000000000000031");

		// when
		int result = fileManageDAO.deleteAllFileInf(fvo);

		// then
//		assertTrue(result > 0);
		assertTrue(result == 0);

		log.debug("result={}", result);
	}

}