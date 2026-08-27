package egovframework.com.dam.per.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.per.service.EgovKnoPersonalService;
import egovframework.com.dam.per.service.KnoPersonal;

/**
 * 개인지식을 삭제할 때 딸린 첨부파일도 함께 정리하는지 확인한다.
 *
 * 회의실(EgovMtgPlaceManageController)·포상(EgovRwardManageController)·
 * 상담(EgovCnsltManageController)·FAQ(EgovFaqController) 등은 도메인을 지운 뒤
 * fileMngService.deleteAllFileInf로 첨부를 정리하는데 개인지식만 빠져 있었다.
 */
class EgovKnoPersonalDeleteFileTest {

	private EgovUserDetailsService originalUserDetailsService;

	@AfterEach
	void restoreUserDetailsService() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(originalUserDetailsService);
	}

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String ATCH_FILE_ID = "FILE_000000000000001";

	@Test
	void deleteRemovesAttachedFiles() throws Exception {
		List<String> deletedFileIds = new ArrayList<>();

		KnoPersonal stored = new KnoPersonal();
		stored.setFrstRegisterId(OWNER);
		stored.setAtchFileId(ATCH_FILE_ID);

		LoginVO login = new LoginVO();
		login.setUniqId(OWNER);

		EgovUserDetailsHelper helper = new EgovUserDetailsHelper();
		originalUserDetailsService = helper.getEgovUserDetailsService();
		helper.setEgovUserDetailsService(stub(EgovUserDetailsService.class, (method, args) -> {
			switch (method.getName()) {
			case "isAuthenticated":
				return Boolean.TRUE;
			case "getAuthenticatedUser":
				return login;
			case "getAuthorities":
				return Collections.<String>emptyList();
			default:
				return null;
			}
		}));

		EgovKnoPersonalController controller = new EgovKnoPersonalController();
		controller.knoPersonalService = stub(EgovKnoPersonalService.class,
				(method, args) -> "selectKnoPersonal".equals(method.getName()) ? stored : null);
		ReflectionTestUtils.setField(controller, "fileMngService", stub(EgovFileMngService.class, (method, args) -> {
			if ("deleteAllFileInf".equals(method.getName())) {
				deletedFileIds.add(((FileVO) args[0]).getAtchFileId());
			}
			return null;
		}));

		KnoPersonal request = new KnoPersonal();
		controller.deleteKnoPersonal(request);

		assertTrue(deletedFileIds.size() == 1, "개인지식을 지우면 첨부파일도 지워야 한다. 실제 호출: " + deletedFileIds);
		assertEquals(ATCH_FILE_ID, deletedFileIds.get(0), "저장된 첨부파일 ID로 지워야 한다.");
	}

	private interface Handler {
		Object handle(java.lang.reflect.Method method, Object[] args) throws Throwable;
	}

	@SuppressWarnings("unchecked")
	private static <T> T stub(Class<T> type, Handler handler) {
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] { type },
				(proxy, method, args) -> handler.handle(method, args));
	}

}
