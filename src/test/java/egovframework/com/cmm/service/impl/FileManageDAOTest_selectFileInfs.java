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
public class FileManageDAOTest_selectFileInfs extends EgovTestV1 {

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
		List<FileVO> results = fileManageDAO.selectFileInfs(vo);
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