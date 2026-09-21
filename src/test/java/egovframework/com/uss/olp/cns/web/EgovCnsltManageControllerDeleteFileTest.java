package egovframework.com.uss.olp.cns.web;

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
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;
import egovframework.com.uss.olp.cns.service.EgovCnsltManageService;

/**
 * 상담정보 삭제 시 첨부파일이 미사용 처리되지 않는 문제 회귀 테스트.
 *
 * <p>삭제 폼(CnsltManageForm)은 cnsltId·writngPassword·passwordConfirmAt만 전송하고
 * atchFileId는 없다. 요청 VO의 값을 그대로 쓰면 항상 비어 있으므로, XSS 권한체크에 이미
 * 쓴 서버 조회값(vo)을 대신 써야 한다.</p>
 */
class EgovCnsltManageControllerDeleteFileTest {

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

	private EgovCnsltManageService cnsltManageServiceStub(String storedAtchFileId, List<String> deletedCnsltIds) {
		CnsltManageVO stored = new CnsltManageVO();
		stored.setCnsltId("CNSLT_00000000000001");
		stored.setFrstRegisterId(LOGIN_UNIQ_ID);
		stored.setAtchFileId(storedAtchFileId);

		return (EgovCnsltManageService) Proxy.newProxyInstance(
				EgovCnsltManageService.class.getClassLoader(),
				new Class<?>[] { EgovCnsltManageService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectCnsltListDetail":
						return stored;
					case "deleteCnsltDtls":
						deletedCnsltIds.add(((CnsltManageVO) args[0]).getCnsltId());
						return null;
					default:
						return null;
					}
				});
	}

	private String callDelete(String storedAtchFileId, List<String> deletedCnsltIds) throws Exception {
		EgovCnsltManageController controller = new EgovCnsltManageController();
		ReflectionTestUtils.setField(controller, "cnsltManageService",
				cnsltManageServiceStub(storedAtchFileId, deletedCnsltIds));
		ReflectionTestUtils.setField(controller, "fileMngService", fileServiceStub());

		// 삭제 폼(CnsltManageForm)은 atchFileId를 전송하지 않으므로 요청 VO의 값은 비어 있다.
		CnsltManageVO requestVO = new CnsltManageVO();
		requestVO.setCnsltId("CNSLT_00000000000001");

		return controller.deleteCnsltDtls(null, requestVO, new CnsltManageDefaultVO());
	}

	@Test
	void deletingAConsultationReleasesTheAttachmentGroupRecordedOnTheServer() throws Exception {
		List<String> deletedCnsltIds = new ArrayList<>();

		callDelete(STORED_ATCH_FILE_ID, deletedCnsltIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"상담을 삭제하면 서버에 저장된 첨부그룹이 미사용 처리돼야 한다.");
		assertTrue(deletedCnsltIds.contains("CNSLT_00000000000001"), "상담 자체도 삭제돼야 한다.");
	}

	@Test
	void deletingAConsultationWithoutAttachmentsDoesNotCallTheFileService() throws Exception {
		List<String> deletedCnsltIds = new ArrayList<>();

		callDelete("", deletedCnsltIds);

		assertTrue(deletedAtchFileIds.isEmpty(), "첨부가 없는 상담은 파일 서비스를 부르지 않아야 한다.");
		assertTrue(deletedCnsltIds.contains("CNSLT_00000000000001"), "상담 자체는 삭제돼야 한다.");
	}
}
