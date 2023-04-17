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
public class FileManageDAOTest_selectFileListCntByFileNm extends EgovTestV1 {

	@Autowired
	private FileManageDAO fileManageDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setRecordCountPerPage(10);
		fvo.setSearchCondition("streFileNm");
		fvo.setSearchKeyword("저장파일명");

		// when
		int fileListCnt = fileManageDAO.selectFileListCntByFileNm(fvo);

		// then
		assertEquals(true, true);

		log.debug("fileListCnt={}", fileListCnt);
	}

	@Test
	public void test2() throws Exception {
		log.debug("test2");

		// given
		FileVO fvo = new FileVO();
		fvo.setSearchCondition("orignlFileNm");
		fvo.setSearchKeyword("원파일명");

		// when
		int fileListCnt = fileManageDAO.selectFileListCntByFileNm(fvo);

		// then
		assertEquals(true, true);

		log.debug("fileListCnt={}", fileListCnt);
	}

}