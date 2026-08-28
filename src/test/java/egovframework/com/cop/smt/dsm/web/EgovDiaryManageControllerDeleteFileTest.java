package egovframework.com.cop.smt.dsm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;
import egovframework.com.cop.smt.dsm.service.EgovDiaryManageService;

/**
 * 일지 삭제 시 첨부파일 정리 회귀 테스트.
 *
 * <p>같은 cop/smt 패키지의 형제 삭제 핸들러(mrm·wmr·djm)는 레코드를 지우기 전에
 * {@code fileMngService.deleteAllFileInf}로 첨부그룹을 미사용 처리한다. 일지 삭제 경로만 그
 * 호출이 없어, 등록·수정에서 만든 {@code DIARY_} 첨부그룹이 COMTNFILE에 USE_AT='Y'로 남는다.</p>
 *
 * <p>삭제 폼은 atchFileId를 전송하지 않으므로, 정리에 쓰는 값은 요청 VO가 아니라 소유권 검증을
 * 위해 이미 조회해 둔 원본이어야 한다. 하드닝된 형제 djm이 같은 방식이다.</p>
 */
class EgovDiaryManageControllerDeleteFileTest {

	private static final String LOGIN_UNIQ_ID = "USRCNFRM_TEST";
	private static final String STORED_ATCH_FILE_ID = "FILE_000000000000123";

	private final InvocationHandler authStub = (proxy, method, args) -> {
		switch (method.getName()) {
		case "isAuthenticated":
			return Boolean.TRUE;
		case "getAuthenticatedUser":
			LoginVO loginVO = new LoginVO();
			loginVO.setUniqId(LOGIN_UNIQ_ID);
			return loginVO;
		case "getAuthorities":
			return Collections.emptyList();
		default:
			return null;
		}
	};

	@BeforeEach
	void bindAuthenticatedUser() {
		EgovUserDetailsService stub = (EgovUserDetailsService) Proxy.newProxyInstance(
				EgovUserDetailsService.class.getClassLoader(),
				new Class<?>[] { EgovUserDetailsService.class }, authStub);
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	@AfterEach
	void clearAuthenticatedUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	/** 삭제된 첨부그룹ID를 기록하는 파일 서비스 스텁. */
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

	private EgovDiaryManageService diaryServiceStub(String storedAtchFileId, List<String> deletedDiaryIds) {
		DiaryManageVO stored = new DiaryManageVO();
		stored.setFrstRegisterId(LOGIN_UNIQ_ID);
		stored.setDiaryId("DIARY_00000000000001");
		stored.setAtchFileId(storedAtchFileId);

		return (EgovDiaryManageService) Proxy.newProxyInstance(
				EgovDiaryManageService.class.getClassLoader(),
				new Class<?>[] { EgovDiaryManageService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectDiaryManageDetail":
						return stored;
					case "deleteDiaryManage":
						deletedDiaryIds.add(((DiaryManageVO) args[0]).getDiaryId());
						return null;
					default:
						return null;
					}
				});
	}

	private String callDelete(String storedAtchFileId, List<String> deletedDiaryIds) throws Exception {
		EgovDiaryManageController controller = new EgovDiaryManageController();
		ReflectionTestUtils.setField(controller, "egovDiaryManageService",
				diaryServiceStub(storedAtchFileId, deletedDiaryIds));
		ReflectionTestUtils.setField(controller, "fileMngService", fileServiceStub());

		// 삭제 폼은 atchFileId를 전송하지 않으므로 요청 VO의 값은 비어 있다.
		DiaryManageVO requestVO = new DiaryManageVO();
		requestVO.setDiaryId("DIARY_00000000000001");

		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "del");

		return controller.egovDiaryManageDetail(new ComDefaultVO(), requestVO, commandMap, new ModelMap());
	}

	@Test
	void deletingADiaryReleasesTheAttachmentGroupRecordedOnTheServer() throws Exception {
		List<String> deletedDiaryIds = new ArrayList<>();

		String view = callDelete(STORED_ATCH_FILE_ID, deletedDiaryIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"일지를 삭제하면 서버에 저장된 첨부그룹이 미사용 처리돼야 한다. 형제(mrm·wmr·djm)는 모두 이렇게 한다.");
		assertTrue(deletedDiaryIds.contains("DIARY_00000000000001"), "일지 자체도 삭제돼야 한다.");
		assertEquals("redirect:/cop/smt/dsm/EgovDiaryManageList.do", view);
	}

	@Test
	void deletingADiaryWithoutAttachmentsDoesNotCallTheFileService() throws Exception {
		List<String> deletedDiaryIds = new ArrayList<>();

		callDelete("", deletedDiaryIds);

		assertTrue(deletedAtchFileIds.isEmpty(),
				"첨부가 없는 일지는 파일 서비스를 부르지 않아야 한다.");
		assertTrue(deletedDiaryIds.contains("DIARY_00000000000001"), "일지 자체는 삭제돼야 한다.");
	}
}
