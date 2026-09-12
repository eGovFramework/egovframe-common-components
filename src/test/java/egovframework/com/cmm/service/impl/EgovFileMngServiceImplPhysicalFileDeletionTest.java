package egovframework.com.cmm.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import egovframework.com.cmm.service.FileVO;

class EgovFileMngServiceImplPhysicalFileDeletionTest {

	private File storedFile;

	@AfterEach
	void cleanUp() {
		if (storedFile != null && storedFile.exists()) {
			storedFile.delete();
		}
	}

	private EgovFileMngServiceImpl serviceStoring(FileVO stored) throws Exception {
		FileManageDAO stubDao = new FileManageDAO() {
			@Override
			public FileVO selectFileInf(FileVO fvo) {
				return stored;
			}

			@Override
			public void deleteFileInf(FileVO fvo) {
				// DB 삭제는 이 테스트의 관심사가 아니다.
			}
		};

		EgovFileMngServiceImpl service = new EgovFileMngServiceImpl();
		Field daoField = EgovFileMngServiceImpl.class.getDeclaredField("fileMngDAO");
		daoField.setAccessible(true);
		daoField.set(service, stubDao);
		return service;
	}

	@Test
	void deleteFileInfRemovesThePhysicalFile() throws Exception {
		storedFile = File.createTempFile("egov-attach-", ".txt");
		Files.write(storedFile.toPath(), "본문".getBytes());

		FileVO stored = new FileVO();
		stored.setFileStreCours(storedFile.getParent());
		stored.setStreFileNm(storedFile.getName());

		EgovFileMngServiceImpl service = serviceStoring(stored);

		FileVO request = new FileVO();
		request.setAtchFileId("FILE_000000000000099");
		request.setFileSn("1");

		service.deleteFileInf(request);

		assertFalse(storedFile.exists(),
				"첨부파일 상세정보를 삭제하면 배너·메인이미지 삭제와 마찬가지로 실제 파일도 지워야 한다.");
	}
}
