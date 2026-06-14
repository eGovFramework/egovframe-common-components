package egovframework.com.cop.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.Board;

/**
 * deleteArticle()의 첨부파일 삭제 가드 검증.
 *
 * <p>첨부파일 식별자(atchFileId)가 없는(null/빈문자열) 게시물을 삭제할 때는
 * deleteAllFileInf()가 호출되지 않아야 한다. 기존 조건식이 OR(||)로 작성되어
 * 항상 참이 되던 버그(가드 무력화)에 대한 회귀 방지 테스트.</p>
 *
 * <p>Spring 컨텍스트·DB 없이 경량 페이크(DAO 서브클래스, 서비스 동적 프록시)와
 * ReflectionTestUtils 필드 주입으로 가드 분기만 결정적으로 검증한다.</p>
 */
public class EgovArticleServiceImplTest_deleteArticleGuard {

	/** deleteAllFileInf 호출 횟수 및 인자를 기록하는 EgovFileMngService 페이크 생성. */
	private EgovFileMngService recordingFileService(AtomicInteger calls, AtomicReference<FileVO> captured) {
		return (EgovFileMngService) Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class<?>[] { EgovFileMngService.class },
				(proxy, method, args) -> {
					if ("deleteAllFileInf".equals(method.getName())) {
						calls.incrementAndGet();
						captured.set((FileVO) args[0]);
						return null;
					}
					Class<?> rt = method.getReturnType();
					if (rt == int.class) {
						return 0;
					}
					return null;
				});
	}

	private EgovArticleServiceImpl newService(EgovFileMngService fileService) {
		EgovArticleServiceImpl service = new EgovArticleServiceImpl();
		// deleteArticle 경로에서 DAO는 update만 수행 → no-op 서브클래스로 DB 의존 제거
		EgovArticleDAO dao = new EgovArticleDAO() {
			@Override
			public void deleteArticle(Board board) {
				// no-op
			}
		};
		ReflectionTestUtils.setField(service, "egovArticleDao", dao);
		ReflectionTestUtils.setField(service, "fileService", fileService);
		return service;
	}

	@Test
	public void deleteArticle_atchFileIdNull_doesNotDeleteFiles() throws Exception {
		AtomicInteger calls = new AtomicInteger();
		AtomicReference<FileVO> captured = new AtomicReference<>();
		EgovArticleServiceImpl service = newService(recordingFileService(calls, captured));

		Board board = new Board();
		board.setAtchFileId(null);

		service.deleteArticle(board);

		assertEquals(0, calls.get(), "첨부ID가 null이면 deleteAllFileInf를 호출하지 않아야 한다");
		assertNull(captured.get());
	}

	@Test
	public void deleteArticle_atchFileIdEmpty_doesNotDeleteFiles() throws Exception {
		AtomicInteger calls = new AtomicInteger();
		AtomicReference<FileVO> captured = new AtomicReference<>();
		EgovArticleServiceImpl service = newService(recordingFileService(calls, captured));

		Board board = new Board();
		board.setAtchFileId("");

		service.deleteArticle(board);

		assertEquals(0, calls.get(), "첨부ID가 빈 문자열이면 deleteAllFileInf를 호출하지 않아야 한다");
	}

	@Test
	public void deleteArticle_atchFileIdPresent_deletesFiles() throws Exception {
		AtomicInteger calls = new AtomicInteger();
		AtomicReference<FileVO> captured = new AtomicReference<>();
		EgovArticleServiceImpl service = newService(recordingFileService(calls, captured));

		Board board = new Board();
		board.setAtchFileId("FILE_000000000000001");

		service.deleteArticle(board);

		assertEquals(1, calls.get(), "첨부ID가 있으면 deleteAllFileInf를 1회 호출해야 한다");
		assertEquals("FILE_000000000000001", captured.get().getAtchFileId());
	}
}
