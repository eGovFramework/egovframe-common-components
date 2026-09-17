package egovframework.com.uss.olh.faq.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.olh.faq.service.EgovFaqService;
import egovframework.com.uss.olh.faq.service.FaqVO;

/**
 * FAQ 삭제 시 첨부파일이 미사용 처리되지 않는 문제 회귀 테스트.
 *
 * <p>삭제 폼은 atchFileId를 전송하지 않으므로, 요청 VO의 값을 그대로 쓰면 항상 비어 있다.
 * 서버에 저장된 값을 다시 조회해서 써야 한다.</p>
 */
class EgovFaqControllerDeleteFileTest {

	private static final String STORED_ATCH_FILE_ID = "FILE_000000000000123";

	private final List<String> deletedAtchFileIds = new ArrayList<>();

	private EgovFileMngService fileServiceStub() {
		return (EgovFileMngService) Proxy.newProxyInstance(
				EgovFileMngService.class.getClassLoader(),
				new Class<?>[] { EgovFileMngService.class },
				(proxy, method, args) -> {
					if ("deleteAllFileInf".equals(method.getName())) {
						deletedAtchFileIds.add(((FileVO) args[0]).getAtchFileId());
						return null;
					}
					return null;
				});
	}

	private EgovFaqService faqServiceStub(String storedAtchFileId, List<String> deletedFaqIds) {
		FaqVO stored = new FaqVO();
		stored.setFaqId("FAQ_00000000000001");
		stored.setAtchFileId(storedAtchFileId);

		return (EgovFaqService) Proxy.newProxyInstance(
				EgovFaqService.class.getClassLoader(),
				new Class<?>[] { EgovFaqService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectFaqDetail":
						return stored;
					case "deleteFaq":
						deletedFaqIds.add(((FaqVO) args[0]).getFaqId());
						return null;
					default:
						return null;
					}
				});
	}

	private String callDelete(String storedAtchFileId, List<String> deletedFaqIds) throws Exception {
		EgovFaqController controller = new EgovFaqController();
		ReflectionTestUtils.setField(controller, "egovFaqService",
				faqServiceStub(storedAtchFileId, deletedFaqIds));
		ReflectionTestUtils.setField(controller, "fileMngService", fileServiceStub());

		// 삭제 폼은 atchFileId를 전송하지 않으므로 요청 VO의 값은 비어 있다.
		FaqVO requestVO = new FaqVO();
		requestVO.setFaqId("FAQ_00000000000001");

		return controller.deleteFaq(requestVO, new FaqVO());
	}

	@Test
	void deletingAFaqReleasesTheAttachmentGroupRecordedOnTheServer() throws Exception {
		List<String> deletedFaqIds = new ArrayList<>();

		callDelete(STORED_ATCH_FILE_ID, deletedFaqIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"FAQ를 삭제하면 서버에 저장된 첨부그룹이 미사용 처리돼야 한다.");
		assertTrue(deletedFaqIds.contains("FAQ_00000000000001"), "FAQ 자체도 삭제돼야 한다.");
	}

	@Test
	void deletingAFaqWithoutAttachmentsDoesNotCallTheFileService() throws Exception {
		List<String> deletedFaqIds = new ArrayList<>();

		callDelete("", deletedFaqIds);

		assertTrue(deletedAtchFileIds.isEmpty(), "첨부가 없는 FAQ는 파일 서비스를 부르지 않아야 한다.");
		assertTrue(deletedFaqIds.contains("FAQ_00000000000001"), "FAQ 자체는 삭제돼야 한다.");
	}
}
