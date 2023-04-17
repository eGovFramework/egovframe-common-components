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
public class FileManageDAOTest_selectImageFileList extends EgovTestV1 {

	@Autowired
	private FileManageDAO fileManageDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO vo = new FileVO();
		vo.setAtchFileId("FILE_000000000000001");
//		vo.setAtchFileId("FILE_000000000000031");

		// when
		List<FileVO> imageFileList = fileManageDAO.selectImageFileList(vo);
		int size = imageFileList.size();

		log.debug("imageFileList={}", imageFileList);
		log.debug("size={}", size);

		if (size == 0) {
			return;
		}

		// then
		assertEquals(imageFileList.get(0).getAtchFileId(), vo.getAtchFileId());

		for (FileVO imageFile : imageFileList) {
			log.debug("imageFile={}", imageFile);
			log.debug("getAtchFileId={}", imageFile.getAtchFileId());
		}
	}

}