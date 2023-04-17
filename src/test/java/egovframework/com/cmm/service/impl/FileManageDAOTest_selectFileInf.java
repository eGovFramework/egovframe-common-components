package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { FileManageDAOConfigurationTest.class })
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

}