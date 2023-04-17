package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { FileManageDAOConfigurationTest.class })
public class FileManageDAOTest_selectFileListByFileNm extends EgovTestV1 {

	@Autowired
	private FileManageDAO fileManageDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setFirstIndex(0);
		fvo.setRecordCountPerPage(10);
		fvo.setSearchCondition("streFileNm");
		fvo.setSearchKeyword("저장파일명");

		// when
		List<FileVO> fileListByFileNm = fileManageDAO.selectFileListByFileNm(fvo);

		// then
		assertEquals(true, true);

		log.debug("fileListByFileNm={}", fileListByFileNm);
		log.debug("size={}", fileListByFileNm.size());

		for (FileVO file : fileListByFileNm) {
			log.debug("file={}", file);
			log.debug("getAtchFileId={}", file.getAtchFileId());
			log.debug("getStreFileNm={}", file.getStreFileNm());
			log.debug("getOrignlFileNm={}", file.getOrignlFileNm());
		}
	}

	@Test
	public void test2() throws Exception {
		log.debug("test2");

		// given
		FileVO fvo = new FileVO();
		fvo.setFirstIndex(0);
		fvo.setRecordCountPerPage(10);
		fvo.setSearchCondition("orignlFileNm");
		fvo.setSearchKeyword("원파일명");

		// when
		List<FileVO> fileListByFileNm = fileManageDAO.selectFileListByFileNm(fvo);

		// then
		assertEquals(true, true);

		log.debug("fileListByFileNm={}", fileListByFileNm);
		log.debug("size={}", fileListByFileNm.size());

		for (FileVO file : fileListByFileNm) {
			log.debug("file={}", file);
			log.debug("getAtchFileId={}", file.getAtchFileId());
			log.debug("getStreFileNm={}", file.getStreFileNm());
			log.debug("getOrignlFileNm={}", file.getOrignlFileNm());
		}
	}

}