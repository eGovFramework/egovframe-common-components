package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { FileManageDAOConfigurationTest.class })
public class FileManageDAOTest_deleteFileInfs extends EgovTestV1 {

	@Autowired
	private FileManageDAO fileManageDAO;

	@Autowired
	@Qualifier("egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		int maxFileSN = fileManageDAO.getMaxFileSN(fvo);
		log.debug("maxFileSN={}", maxFileSN);
		fvo.setFileSn(String.valueOf(maxFileSN++));
		fvo.setFileMg(fvo.getFileSn());
		fileManageDAO.insertFileInf(fvo);

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
			fileManageDAO.deleteFileInfs(fileList);
			result = true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		// then
		assertEquals(result, true);

		log.debug("result={}", result);
	}

}