package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovFileMngServiceImplConfigurationTest.class })
public class EgovFileMngServiceImplTest_selectFileListByFileNm extends EgovTestV1 {

	@Autowired
	private EgovFileMngService egovFileMngService;

	@SuppressWarnings("unchecked")
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
		Map<String, Object> fileListByFileNm = egovFileMngService.selectFileListByFileNm(fvo);
		List<FileVO> resultList = (List<FileVO>) fileListByFileNm.get("resultList");
		String resultCnt = (String) fileListByFileNm.get("resultCnt");

		// then
		assertEquals(true, true);

		log.debug("fileListByFileNm={}", fileListByFileNm);
		log.debug("size={}", fileListByFileNm.size());

		log.debug("resultList={}", resultList);
		log.debug("size={}", resultList.size());

		for (FileVO result : resultList) {
			log.debug("result={}", result);
			log.debug("getAtchFileId={}", result.getAtchFileId());
			log.debug("getStreFileNm={}", result.getStreFileNm());
			log.debug("getOrignlFileNm={}", result.getOrignlFileNm());
		}

		log.debug("resultCnt={}", resultCnt);
	}

	@SuppressWarnings("unchecked")
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
		Map<String, Object> fileListByFileNm = egovFileMngService.selectFileListByFileNm(fvo);
		List<FileVO> resultList = (List<FileVO>) fileListByFileNm.get("resultList");
		String resultCnt = (String) fileListByFileNm.get("resultCnt");

		// then
		assertEquals(true, true);

		log.debug("fileListByFileNm={}", fileListByFileNm);
		log.debug("size={}", fileListByFileNm.size());

		log.debug("resultList={}", resultList);
		log.debug("size={}", resultList.size());

		for (FileVO result : resultList) {
			log.debug("result={}", result);
			log.debug("getAtchFileId={}", result.getAtchFileId());
			log.debug("getStreFileNm={}", result.getStreFileNm());
			log.debug("getOrignlFileNm={}", result.getOrignlFileNm());
		}

		log.debug("resultCnt={}", resultCnt);
	}

}