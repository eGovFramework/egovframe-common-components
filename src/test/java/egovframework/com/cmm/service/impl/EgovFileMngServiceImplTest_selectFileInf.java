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