package egovframework.com.cop.ems.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.EgovSndngMailDetailService;
import egovframework.com.cop.ems.service.SndngMailVO;

/**
 * 발송메일 삭제 시 첨부파일이 미사용 처리되지 않는 문제 회귀 테스트.
 *
 * <p>삭제 폼의 hidden 필드 이름이 {@code atchFileIdList}라 요청 VO의 {@code atchFileId}는
 * 항상 비어 있다. 위에서 소유권 확인에 이미 쓴 서버 조회값({@code resultMailVO})을 대신
 * 써야 한다.</p>
 */
class EgovSndngMailDetailControllerDeleteFileTest {

	private static final String LOGIN_ID = "dsptchUser";
	private static final String STORED_ATCH_FILE_ID = "FILE_000000000000123";

	private final InvocationHandler authStub = (proxy, method, args) -> {
		switch (method.getName()) {
		case "isAuthenticated":
			return Boolean.TRUE;
		case "getAuthenticatedUser":
			LoginVO loginVO = new LoginVO();
			loginVO.setId(LOGIN_ID);
			loginVO.setUniqId(LOGIN_ID);
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

	private final List<String> deletedAtchFileIds = new ArrayList<>();

	private EgovSndngMailDetailService sndngMailDetailServiceStub(String storedAtchFileId,
			List<String> deletedMssageIds) {
		SndngMailVO stored = new SndngMailVO();
		stored.setMssageId("MSG_00000000000001");
		stored.setDsptchPerson(LOGIN_ID);
		stored.setAtchFileId(storedAtchFileId);

		return (EgovSndngMailDetailService) Proxy.newProxyInstance(
				EgovSndngMailDetailService.class.getClassLoader(),
				new Class<?>[] { EgovSndngMailDetailService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectSndngMail":
						return stored;
					case "deleteSndngMail":
						deletedMssageIds.add(((SndngMailVO) args[0]).getMssageId());
						return null;
					case "deleteAtchmnFile":
						// 이 인자가 요청 VO가 아니라 서버 재조회값(stored)이어야 첨부그룹ID가 채워져 있다.
						deletedAtchFileIds.add(((SndngMailVO) args[0]).getAtchFileId());
						return null;
					default:
						return null;
					}
				});
	}

	private String callDelete(String storedAtchFileId, List<String> deletedMssageIds) throws Exception {
		EgovSndngMailDetailController controller = new EgovSndngMailDetailController();
		ReflectionTestUtils.setField(controller, "sndngMailDetailService",
				sndngMailDetailServiceStub(storedAtchFileId, deletedMssageIds));

		// 삭제 폼은 atchFileIdList에 값을 채우고 atchFileId는 비워 보낸다.
		SndngMailVO requestVO = new SndngMailVO();
		requestVO.setMssageId("MSG_00000000000001");
		requestVO.setAtchFileIdList(storedAtchFileId);

		return controller.deleteSndngMail(requestVO, new ModelMap());
	}

	@Test
	void deletingASentMailReleasesTheAttachmentGroupRecordedOnTheServer() throws Exception {
		List<String> deletedMssageIds = new ArrayList<>();

		callDelete(STORED_ATCH_FILE_ID, deletedMssageIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"발송메일을 삭제하면 서버에 저장된 첨부그룹이 미사용 처리돼야 한다.");
		assertTrue(deletedMssageIds.contains("MSG_00000000000001"), "발송메일 자체도 삭제돼야 한다.");
	}
}
