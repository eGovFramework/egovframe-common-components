package egovframework.com.cmm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

/**
 * parseFileInf() 계열 메서드만 검증한다. downFile/writeFile/uploadFile 등은 클래스 로딩 시점에
 * 고정되는 정적 Globals.fileStorePath(테스트 설정상 "C:/egovframework/upload/")에 실제 디스크
 * 쓰기를 수행하므로, 실행 환경(OS)에 따라 프로젝트 바깥에 의도치 않은 디렉토리를 생성할 위험이 있어 제외했다.
 * storePath에 존재하지 않는 프로퍼티 키를 넘기면 EgovProperties가 빈 문자열을 반환하고
 * new File("")은 mkdirs()를 타지 않으므로(이미 존재하는 것으로 취급) 부작용 없이 검증할 수 있다.
 */
class EgovFileMngUtilTest {

	private static final String NO_SUCH_STORE_PATH_KEY = "test.no.such.store.path.key";

	private final EgovFileMngUtil util = new EgovFileMngUtil();

	@Test
	void parseFileInfMap_buildsFileVoFromMultipartFile() throws Exception {
		Map<String, MultipartFile> files = new LinkedHashMap<>();
		files.put("file1", fakeFile("hello.TXT", 11));

		List<FileVO> result = util.parseFileInf(files, "KEY", 1, "ATCH001", NO_SUCH_STORE_PATH_KEY);

		assertEquals(1, result.size());
		FileVO vo = result.get(0);
		assertEquals("TXT", vo.getFileExtsn());
		assertEquals("hello.TXT", vo.getOrignlFileNm());
		assertEquals("11", vo.getFileMg());
		assertEquals("ATCH001", vo.getAtchFileId());
		assertEquals("1", vo.getFileSn());
		assertTrue(vo.getStreFileNm().startsWith("KEY"), "저장파일명은 keyStr로 시작해야 한다: " + vo.getStreFileNm());
		assertTrue(vo.getStreFileNm().endsWith("1"), "저장파일명은 fileKey로 끝나야 한다: " + vo.getStreFileNm());
	}

	@Test
	void parseFileInfMap_skipsEntryWithBlankOriginalFilename() throws Exception {
		Map<String, MultipartFile> files = new LinkedHashMap<>();
		files.put("blank", fakeFile("", 5));
		files.put("valid", fakeFile("keep.txt", 5));

		List<FileVO> result = util.parseFileInf(files, "KEY", 3, "ATCH002", NO_SUCH_STORE_PATH_KEY);

		assertEquals(1, result.size());
		assertEquals("keep.txt", result.get(0).getOrignlFileNm());
		// 스킵된 항목은 fileKey를 소비하지 않으므로 최초 전달값(3)이 그대로 사용되어야 한다
		assertEquals("3", result.get(0).getFileSn());
	}

	@Test
	void parseFileInfMap_emptyMap_returnsEmptyListWithoutError() throws Exception {
		List<FileVO> result = util.parseFileInf(new LinkedHashMap<String, MultipartFile>(), "KEY", 1, "ATCH003",
				NO_SUCH_STORE_PATH_KEY);

		assertTrue(result.isEmpty());
	}

	@Test
	void parseFileInfList_incrementsFileSequenceInOrder() throws Exception {
		List<MultipartFile> files = List.of(fakeFile("a.png", 1), fakeFile("b.jpg", 2));

		List<FileVO> result = util.parseFileInf(files, "KEY", 5, "ATCH004", NO_SUCH_STORE_PATH_KEY);

		assertEquals(2, result.size());
		assertEquals("5", result.get(0).getFileSn());
		assertEquals("PNG", result.get(0).getFileExtsn());
		assertEquals("6", result.get(1).getFileSn());
		assertEquals("JPG", result.get(1).getFileExtsn());
	}

	@Test
	void parseFileInfList_skipsEntryWithBlankOriginalFilename() throws Exception {
		List<MultipartFile> files = List.of(fakeFile("first.txt", 1), fakeFile(null, 2), fakeFile("third.txt", 3));

		List<FileVO> result = util.parseFileInf(files, "KEY", 10, "ATCH005", NO_SUCH_STORE_PATH_KEY);

		assertEquals(2, result.size());
		assertEquals("first.txt", result.get(0).getOrignlFileNm());
		assertEquals("10", result.get(0).getFileSn());
		assertEquals("third.txt", result.get(1).getOrignlFileNm());
		// 중간에 스킵된 항목이 있어도 fileKey는 유효한 항목 수만큼만 증가한다
		assertEquals("11", result.get(1).getFileSn());
	}

	private static Object defaultValue(Class<?> returnType) {
		if (!returnType.isPrimitive()) {
			return null;
		}
		if (returnType == boolean.class) {
			return false;
		}
		return 0L;
	}

	private static MultipartFile fakeFile(String originalFilename, long size) {
		return (MultipartFile) Proxy.newProxyInstance(MultipartFile.class.getClassLoader(),
				new Class<?>[] { MultipartFile.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "getOriginalFilename":
						return originalFilename;
					case "getSize":
						return size;
					case "transferTo":
						// 실제 디스크에 쓰지 않는다: parseFileInf의 반환 값 매핑만 검증 대상이다
						return null;
					default:
						return defaultValue(method.getReturnType());
					}
				});
	}
}
