package egovframework.com.uss.ion.mtg.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.mtg.service.EgovMtgPlaceManageService;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;

/**
 * 회의실관리 삭제 시 첨부파일이 미사용 처리되지 않는 문제 회귀 테스트.
 *
 * <p>삭제 폼은 atchFileId를 전송하지 않으므로, 요청 VO의 값을 그대로 쓰면 항상 비어 있다.
 * 서버에 저장된 값을 다시 조회해서 써야 한다.</p>
 */
class EgovMtgPlaceManageControllerDeleteFileTest {

	private static final String LOGIN_UNIQ_ID = "USRCNFRM_00000000001";

	private EgovUserDetailsService previousUserDetailsService;

	/** 삭제는 등록자 본인만 가능하므로 로그인 사용자를 묶고, 저장본의 등록자를 같은 사용자로 둔다. */
	@BeforeEach
	void bindLoginUser() {
		previousUserDetailsService = new EgovUserDetailsHelper().getEgovUserDetailsService();
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(LOGIN_UNIQ_ID);
		new EgovUserDetailsHelper().setEgovUserDetailsService((EgovUserDetailsService) Proxy.newProxyInstance(
				EgovUserDetailsService.class.getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					if ("getAuthenticatedUser".equals(method.getName())) {
						return loginVO;
					}
					if ("isAuthenticated".equals(method.getName())) {
						return Boolean.TRUE;
					}
					if ("getAuthorities".equals(method.getName())) {
						return java.util.Collections.emptyList();
					}
					return null;
				}));
	}

	@AfterEach
	void restoreLoginUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(previousUserDetailsService);
	}

	private static final String STORED_ATCH_FILE_ID = "FILE_000000000000123";

	private final List<String> deletedAtchFileIds = new ArrayList<>();

	private EgovFileMngService fileServiceStub() {
		return (EgovFileMngService) Proxy.newProxyInstance(
				EgovFileMngService.class.getClassLoader(),
				new Class<?>[] { EgovFileMngService.class },
				(proxy, method, args) -> {
					if ("deleteAllFileInf".equals(method.getName())) {
						deletedAtchFileIds.add(((FileVO) args[0]).getAtchFileId());
						return 0;
					}
					return null;
				});
	}

	private EgovMtgPlaceManageService mtgPlaceManageServiceStub(String storedAtchFileId,
			List<String> deletedMtgPlaceIds) {
		MtgPlaceManageVO stored = new MtgPlaceManageVO();
		stored.setMtgPlaceId("MTG_00000000000001");
		stored.setAtchFileId(storedAtchFileId);
		stored.setFrstRegisterId(LOGIN_UNIQ_ID);

		return (EgovMtgPlaceManageService) Proxy.newProxyInstance(
				EgovMtgPlaceManageService.class.getClassLoader(),
				new Class<?>[] { EgovMtgPlaceManageService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectMtgPlaceResveCnt":
						return 0;
					case "selectMtgPlaceManage":
						return stored;
					case "deleteMtgPlaceManage":
						deletedMtgPlaceIds.add(((MtgPlaceManageVO) args[0]).getMtgPlaceId());
						return null;
					default:
						return null;
					}
				});
	}

	private SessionStatus noopSessionStatus() {
		return (SessionStatus) Proxy.newProxyInstance(
				SessionStatus.class.getClassLoader(),
				new Class<?>[] { SessionStatus.class },
				(proxy, method, args) -> null);
	}

	private String callDelete(String storedAtchFileId, List<String> deletedMtgPlaceIds) throws Exception {
		EgovMtgPlaceManageController controller = new EgovMtgPlaceManageController();
		ReflectionTestUtils.setField(controller, "egovMtgPlaceManageService",
				mtgPlaceManageServiceStub(storedAtchFileId, deletedMtgPlaceIds));
		ReflectionTestUtils.setField(controller, "fileMngService", fileServiceStub());
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});

		// 삭제 폼은 atchFileId를 전송하지 않으므로 요청 VO의 값은 비어 있다.
		MtgPlaceManageVO requestVO = new MtgPlaceManageVO();
		requestVO.setMtgPlaceId("MTG_00000000000001");

		return controller.deleteMtgPlaceManage(requestVO, noopSessionStatus(), new ModelMap());
	}

	@Test
	void deletingAMeetingRoomReleasesTheAttachmentGroupRecordedOnTheServer() throws Exception {
		List<String> deletedMtgPlaceIds = new ArrayList<>();

		callDelete(STORED_ATCH_FILE_ID, deletedMtgPlaceIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"회의실을 삭제하면 서버에 저장된 첨부그룹이 미사용 처리돼야 한다.");
		assertTrue(deletedMtgPlaceIds.contains("MTG_00000000000001"), "회의실 자체도 삭제돼야 한다.");
	}

	@Test
	void deletingAMeetingRoomWithoutAttachmentsDoesNotCallTheFileService() throws Exception {
		List<String> deletedMtgPlaceIds = new ArrayList<>();

		callDelete("", deletedMtgPlaceIds);

		assertTrue(deletedAtchFileIds.isEmpty(), "첨부가 없는 회의실은 파일 서비스를 부르지 않아야 한다.");
		assertTrue(deletedMtgPlaceIds.contains("MTG_00000000000001"), "회의실 자체는 삭제돼야 한다.");
	}
}
