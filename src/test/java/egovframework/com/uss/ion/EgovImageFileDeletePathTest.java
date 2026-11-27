package egovframework.com.uss.ion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import egovframework.com.uss.ion.bnr.service.impl.BannerDAO;
import egovframework.com.uss.ion.bnr.service.impl.EgovBannerServiceImpl;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;
import egovframework.com.uss.ion.lsi.service.impl.EgovLoginScrinImageServiceImpl;
import egovframework.com.uss.ion.lsi.service.impl.LoginScrinImageDAO;
import egovframework.com.uss.ion.msi.service.MainImageVO;
import egovframework.com.uss.ion.msi.service.impl.EgovMainImageServiceImpl;
import egovframework.com.uss.ion.msi.service.impl.MainImageDAO;

/**
 * 메인화면이미지·배너·로그인화면이미지의 이미지파일 삭제 경로 검증.
 *
 * <p>EgovFileMngUtil.parseFileInf()는 파일을 {@code 저장경로 + File.separator + 저장파일명}에
 * 기록하고 저장경로(FILE_STRE_COURS)와 저장파일명(STRE_FILE_NM)을 분리해 보관한다.
 * 삭제 시 두 값을 구분자 없이 이어 붙이면 존재하지 않는 경로를 가리켜 파일이 남는다.</p>
 *
 * <p>Spring 컨텍스트·DB 없이 DAO 서브클래스와 ReflectionTestUtils 필드 주입으로
 * 삭제 경로 계산만 검증한다.</p>
 */
class EgovImageFileDeletePathTest {

	@TempDir
	Path storeDir;

	/** parseFileInf()가 저장한 형태 그대로(경로/파일명 분리) 실제 파일을 만든다. */
	private FileVO storedFile(String streFileNm) throws Exception {
		Files.write(storeDir.resolve(streFileNm), new byte[] {1, 2, 3});

		FileVO fileVO = new FileVO();
		fileVO.setFileStreCours(storeDir.toString());
		fileVO.setStreFileNm(streFileNm);
		return fileVO;
	}

	@Test
	void deleteMainImageFileRemovesStoredFile() throws Exception {
		final FileVO fileVO = storedFile("MSI_0");

		EgovMainImageServiceImpl service = new EgovMainImageServiceImpl();
		ReflectionTestUtils.setField(service, "mainImageDAO", new MainImageDAO() {
			@Override
			public FileVO selectMainImageFile(MainImageVO mainImageVO) {
				return fileVO;
			}
		});

		assertTrue(Files.exists(storeDir.resolve("MSI_0")));
		service.deleteMainImageFile(new MainImageVO());

		assertFalse(Files.exists(storeDir.resolve("MSI_0")), "메인화면이미지 파일이 디스크에서 삭제되어야 한다");
	}

	@Test
	void deleteBannerFileRemovesStoredFile() throws Exception {
		final FileVO fileVO = storedFile("BNR_0");

		EgovBannerServiceImpl service = new EgovBannerServiceImpl();
		ReflectionTestUtils.setField(service, "bannerDAO", new BannerDAO() {
			@Override
			public FileVO selectBannerFile(BannerVO bannerVO) {
				return fileVO;
			}
		});

		assertTrue(Files.exists(storeDir.resolve("BNR_0")));
		service.deleteBannerFile(new BannerVO());

		assertFalse(Files.exists(storeDir.resolve("BNR_0")), "배너 파일이 디스크에서 삭제되어야 한다");
	}

	@Test
	void deleteLoginScrinImageFileRemovesStoredFile() throws Exception {
		final FileVO fileVO = storedFile("LSI_0");

		EgovLoginScrinImageServiceImpl service = new EgovLoginScrinImageServiceImpl();
		ReflectionTestUtils.setField(service, "loginScrinImageDAO", new LoginScrinImageDAO() {
			@Override
			public FileVO selectLoginScrinImageFile(LoginScrinImageVO loginScrinImageVO) {
				return fileVO;
			}
		});

		assertTrue(Files.exists(storeDir.resolve("LSI_0")));
		service.deleteLoginScrinImageFile(new LoginScrinImageVO());

		assertFalse(Files.exists(storeDir.resolve("LSI_0")), "로그인화면이미지 파일이 디스크에서 삭제되어야 한다");
	}
}
