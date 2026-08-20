package egovframework.com.sym.log.slg.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.slg.service.EgovSysHistoryService;
import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;

/**
 * 시스템이력을 삭제할 때 첨부파일도 미사용 처리하는지 검증.
 *
 * <p>회의실·포상·상담·FAQ 등은 도메인을 지운 뒤 deleteAllFileInf로 첨부를 정리하는데
 * 시스템이력만 빠져 있었다. 삭제 폼이 첨부파일 ID를 실어보내지 않으므로 저장본을
 * 조회해서 가져와야 한다.</p>
 *
 * <p>Spring 컨텍스트·DB 없이 동적 프록시 페이크와 ReflectionTestUtils 필드 주입으로
 * 삭제 경로만 검증한다.</p>
 */
public class EgovSysHistoryDeleteFileTest {

	private static final String ATCH_FILE_ID = "FILE_000000000000001";

	private EgovUserDetailsService originalUserDetailsService;

	@AfterEach
	public void restoreUserDetailsService() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(originalUserDetailsService);
	}

	@Test
	public void deleteRemovesAttachedFiles() throws Exception {
		List<String> deletedFileIds = new ArrayList<>();

		SysHistoryVO stored = new SysHistoryVO();
		stored.setAtchFileId(ATCH_FILE_ID);

		EgovUserDetailsHelper helper = new EgovUserDetailsHelper();
		originalUserDetailsService = helper.getEgovUserDetailsService();
		helper.setEgovUserDetailsService(stub(EgovUserDetailsService.class, (method, args) ->
				"getAuthenticatedUser".equals(method.getName()) ? new LoginVO() : null));

		EgovSysHistoryController controller = new EgovSysHistoryController();
		ReflectionTestUtils.setField(controller, "sysHistoryService", stub(EgovSysHistoryService.class,
				(method, args) -> "selectSysHistory".equals(method.getName()) ? stored : null));
		ReflectionTestUtils.setField(controller, "fileMngService", stub(EgovFileMngService.class, (method, args) -> {
			if ("deleteAllFileInf".equals(method.getName())) {
				deletedFileIds.add(((FileVO) args[0]).getAtchFileId());
			}
			return null;
		}));

		SessionStatus status = new SimpleSessionStatus();
		controller.deleteSysHistory(new SysHistory(), status, new ModelMap());

		assertFalse(deletedFileIds.isEmpty(), "시스템이력을 지우면 첨부파일도 정리해야 한다.");
		assertEquals(ATCH_FILE_ID, deletedFileIds.get(0), "저장된 첨부파일 ID로 정리해야 한다.");
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
