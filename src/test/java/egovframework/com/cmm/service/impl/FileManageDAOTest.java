package egovframework.com.cmm.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovAbstractTestJUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class FileManageDAOTest extends EgovAbstractTestJUnit {

	@Autowired
	FileManageDAO fileManageDAO;

	@Autowired
	EgovIdGnrService egovFileIdGnrService;

	FileVO testData;

	@BeforeEach
	void setUpTestData(TestInfo testInfo) {
		String methodName = testInfo.getTestMethod().map(Method::getName).orElseThrow();
		if (Set.of("insertFileInfs", "insertFileInf").contains(methodName)) {
			return;
		}

		// given
		testData = new FileVO();

		String atchFileId;
		try {
			atchFileId = egovFileIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		testData.setAtchFileId(atchFileId);

		int fileSn = fileManageDAO.getMaxFileSN(testData);
		testData.setFileSn(String.valueOf(fileSn));

		testData.setFileMg("0");

		LocalDateTime now = LocalDateTime.now();

		testData.setStreFileNm("test 이백행 저장파일명 " + now);

		testData.setFileExtsn("GIF");

		if (log.isDebugEnabled()) {
			log.debug("testData={}", testData);
			log.debug("atchFileId={}", testData.getAtchFileId());
			log.debug("fileSn={}", testData.getFileSn());
			log.debug("fileMg={}", testData.getFileMg());
			log.debug("streFileNm={}", testData.getStreFileNm());
			log.debug("fileExtsn={}", testData.getFileExtsn());
		}

		// when
		assertDoesNotThrow(() -> fileManageDAO.insertFileInf(testData));

		// then
	}

	@Test
	void insertFileInfs() {
		// given
		FileVO fileVO = new FileVO();

		String atchFileId;
		try {
			atchFileId = egovFileIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		fileVO.setAtchFileId(atchFileId);

		int fileSn = fileManageDAO.getMaxFileSN(fileVO);

		List<FileVO> fileList = new ArrayList<>();
		FileVO file = new FileVO();
		file.setAtchFileId(fileVO.getAtchFileId());
		file.setFileSn(String.valueOf(fileSn));
		file.setFileMg("0");
		fileList.add(file);
		fileSn++;

		file = new FileVO();
		file.setAtchFileId(fileVO.getAtchFileId());
		file.setFileSn(String.valueOf(fileSn));
		file.setFileMg("0");
		fileList.add(file);
		fileSn++;

		for (int i = 0; i < 10; i++) {
			file = new FileVO();
			file.setAtchFileId(fileVO.getAtchFileId());
			file.setFileSn(String.valueOf(fileSn));
			file.setFileMg("0");
			fileList.add(file);
			fileSn++;
		}

		// when
		assertDoesNotThrow(() -> fileManageDAO.insertFileInfs(fileList));

		// then
	}

	@Test
	void insertFileInf() {
		// given
		FileVO fileVO = new FileVO();

		String atchFileId;
		try {
			atchFileId = egovFileIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		fileVO.setAtchFileId(atchFileId);

		int fileSn = fileManageDAO.getMaxFileSN(fileVO);
		fileVO.setFileSn(String.valueOf(fileSn));

		fileVO.setFileMg("0");

		LocalDateTime now = LocalDateTime.now();

		fileVO.setStreFileNm("test 이백행 저장파일명 " + now);

		fileVO.setFileExtsn("GIF");

		if (log.isDebugEnabled()) {
			log.debug("fileVO={}", fileVO);
			log.debug("atchFileId={}", fileVO.getAtchFileId());
			log.debug("fileSn={}", fileVO.getFileSn());
			log.debug("fileMg={}", fileVO.getFileMg());
			log.debug("streFileNm={}", fileVO.getStreFileNm());
			log.debug("fileExtsn={}", fileVO.getFileExtsn());
		}

		// when
		assertDoesNotThrow(() -> fileManageDAO.insertFileInf(fileVO));

		// then
	}

	@Test
	void updateFileInfs() {
		// given
		int fileSn = fileManageDAO.getMaxFileSN(testData);
		log.debug("fileSN={}", fileSn);

		List<FileVO> fileList = new ArrayList<>();
		FileVO file = new FileVO();
		file.setAtchFileId(testData.getAtchFileId());
		file.setFileSn(String.valueOf(fileSn));
		file.setFileMg("0");
		fileList.add(file);
		fileSn++;

		file = new FileVO();
		file.setAtchFileId(testData.getAtchFileId());
		file.setFileSn(String.valueOf(fileSn));
		file.setFileMg("0");
		fileList.add(file);
		fileSn++;

		for (int i = 0; i < 10; i++) {
			file = new FileVO();
			file.setAtchFileId(testData.getAtchFileId());
			file.setFileSn(String.valueOf(fileSn));
			file.setFileMg("0");
			fileList.add(file);
			fileSn++;
		}

		// when
		assertDoesNotThrow(() -> fileManageDAO.updateFileInfs(fileList));

		// then
	}

	@Test
	void deleteFileInfs() {
		// given
		List<FileVO> fileList = new ArrayList<>();
		FileVO file = new FileVO();
		file.setAtchFileId(testData.getAtchFileId());
		file.setFileSn(testData.getFileSn());
		fileList.add(file);

		// when
		assertDoesNotThrow(() -> fileManageDAO.deleteFileInfs(fileList));

		// then
	}

	@Test
	void deleteFileInf() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(testData.getAtchFileId());
		fileVO.setFileSn(testData.getFileSn());

		// when
		assertDoesNotThrow(() -> fileManageDAO.deleteFileInf(fileVO));

		// then
	}

	@Test
	void selectFileInfs() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(testData.getAtchFileId());

		// when
		List<FileVO> results = fileManageDAO.selectFileInfs(fileVO);

		log.debug("results={}", results);

		// then
		assertFalse(results.isEmpty());

		for (FileVO result : results) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("expected, actual");
				log.debug("atchFileId={}, {}", fileVO.getAtchFileId(), result.getAtchFileId());
			}

			assertEquals(fileVO.getAtchFileId(), result.getAtchFileId());
		}
	}

	@Test
	void getMaxFileSN() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(testData.getAtchFileId());

		// when
		int fileSN = fileManageDAO.getMaxFileSN(fileVO);

		log.debug("fileSN={}", fileSN);

		// then
		assertEquals(2, fileSN);
	}

	@Test
	void selectFileInf() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(testData.getAtchFileId());
		fileVO.setFileSn(testData.getFileSn());

		// when
		FileVO result = fileManageDAO.selectFileInf(fileVO);

		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
			log.debug("expected, actual");
			log.debug("atchFileId={}, {}", fileVO.getAtchFileId(), result.getAtchFileId());
			log.debug("fileSn={}, {}", fileVO.getFileSn(), result.getFileSn());
		}

		// then
		assertEquals(fileVO.getAtchFileId(), result.getAtchFileId());
		assertEquals(fileVO.getFileSn(), result.getFileSn());
	}

	@Test
	void deleteAllFileInf() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(testData.getAtchFileId());

		// when
		assertDoesNotThrow(() -> fileManageDAO.deleteAllFileInf(fileVO));

		// then
	}

	@Test
	void selectFileListByFileNm() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setFirstIndex(0);
		fileVO.setRecordCountPerPage(10);

		fileVO.setSearchCondition("streFileNm");
		fileVO.setSearchKeyword(testData.getStreFileNm());

		// when
		List<FileVO> results = fileManageDAO.selectFileListByFileNm(fileVO);

		// then
		assertFalse(results.isEmpty());

		for (FileVO result : results) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("expected, actual");
				log.debug("streFileNm={}, {}", fileVO.getSearchKeyword(), result.getStreFileNm());
			}

			assertEquals(fileVO.getSearchKeyword(), result.getStreFileNm());
		}
	}

	@Test
	void selectFileListCntByFileNm() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setSearchCondition("streFileNm");
		fileVO.setSearchKeyword(testData.getStreFileNm());

		// when
		int totalRecordCount = fileManageDAO.selectFileListCntByFileNm(fileVO);

		// then
		assertEquals(1, totalRecordCount);
	}

	@Test
	void selectImageFileList() {
		// given
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(testData.getAtchFileId());

		// when
		List<FileVO> results = fileManageDAO.selectImageFileList(fileVO);

		// then
		assertFalse(results.isEmpty());

		for (FileVO result : results) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("expected, actual");
				log.debug("atchFileId={}, {}", fileVO.getAtchFileId(), result.getAtchFileId());
				log.debug("fileExtsn={}, {}", testData.getFileExtsn(), result.getFileExtsn());
			}

			assertEquals(fileVO.getAtchFileId(), result.getAtchFileId());
			assertEquals(testData.getFileExtsn(), result.getFileExtsn());
		}
	}

}
