package egovframework.com.cop.smt.wmr.web;

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
import egovframework.com.cop.smt.wmr.service.EgovWikMnthngReprtService;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprtVO;

/**
 * 주간월간보고 삭제 시 첨부파일이 미사용 처리되지 않는 문제 회귀 테스트.
 *
 * <p>삭제 폼은 atchFileId를 전송하지 않으므로, 요청 VO의 값을 그대로 쓰면 항상 비어 있다.
 * 서버에 저장된 값을 다시 조회해서 써야 한다.</p>
 */
class EgovWikMnthngReprtControllerDeleteFileTest {

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
						return null;
					}
					return null;
				});
	}

	/**
	 * 실제 조회 매퍼(EgovWikMnthngReprt_SQL_mysql.xml selectWikMnthngReprt)는
	 * {@code WHERE ... AND (WRTER_ID = #{searchId} OR REPORTR_ID = #{searchId}) AND REPRT_ID = #{reprtId}}
	 * 로, searchId가 비어 있으면 매칭되는 행이 없다. 컨트롤러가 setSearchId보다 먼저 재조회를 부르면
	 * 이 스텁도 null을 돌려주게 해서, 순서가 틀리면 테스트가 실패하게 한다.
	 */
	private EgovWikMnthngReprtService wikMnthngReprtServiceStub(String storedAtchFileId,
			List<String> deletedReprtIds) {
		WikMnthngReprtVO stored = new WikMnthngReprtVO();
		stored.setReprtId("REPRT_00000000000001");
		stored.setAtchFileId(storedAtchFileId);

		return (EgovWikMnthngReprtService) Proxy.newProxyInstance(
				EgovWikMnthngReprtService.class.getClassLoader(),
				new Class<?>[] { EgovWikMnthngReprtService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectWikMnthngReprt":
						String searchId = ((WikMnthngReprtVO) args[0]).getSearchId();
						return (searchId != null && !searchId.isEmpty()) ? stored : null;
					case "deleteWikMnthngReprt":
						deletedReprtIds.add(((WikMnthngReprtVO) args[0]).getReprtId());
						return null;
					default:
						return null;
					}
				});
	}

	private String callDelete(String storedAtchFileId, List<String> deletedReprtIds) throws Exception {
		EgovWikMnthngReprtController controller = new EgovWikMnthngReprtController();
		ReflectionTestUtils.setField(controller, "wikMnthngReprtService",
				wikMnthngReprtServiceStub(storedAtchFileId, deletedReprtIds));
		ReflectionTestUtils.setField(controller, "fileMngService", fileServiceStub());

		// 삭제 폼은 atchFileId를 전송하지 않으므로 요청 VO의 값은 비어 있다.
		WikMnthngReprtVO requestVO = new WikMnthngReprtVO();
		requestVO.setReprtId("REPRT_00000000000001");

		return controller.deleteWikMnthngReprt(requestVO, new ModelMap());
	}

	@Test
	void deletingAWeeklyMonthlyReportReleasesTheAttachmentGroupRecordedOnTheServer() throws Exception {
		List<String> deletedReprtIds = new ArrayList<>();

		callDelete(STORED_ATCH_FILE_ID, deletedReprtIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"주간월간보고를 삭제하면 서버에 저장된 첨부그룹이 미사용 처리돼야 한다.");
		assertTrue(deletedReprtIds.contains("REPRT_00000000000001"), "보고 자체도 삭제돼야 한다.");
	}

	@Test
	void deletingAWeeklyMonthlyReportWithoutAttachmentsDoesNotCallTheFileService() throws Exception {
		List<String> deletedReprtIds = new ArrayList<>();

		callDelete("", deletedReprtIds);

		assertTrue(deletedAtchFileIds.isEmpty(), "첨부가 없는 보고는 파일 서비스를 부르지 않아야 한다.");
		assertTrue(deletedReprtIds.contains("REPRT_00000000000001"), "보고 자체는 삭제돼야 한다.");
	}

	@Test
	void searchIdIsSetBeforeTheReQueryRunsSoTheLookupPredicateCanMatch() throws Exception {
		// 재조회가 setSearchId보다 먼저 실행되면(순서 회귀) 스텁이 null을 돌려주고,
		// 가드 때문에 파일 서비스가 안 불려 이 단언이 깨진다.
		List<String> deletedReprtIds = new ArrayList<>();

		callDelete(STORED_ATCH_FILE_ID, deletedReprtIds);

		assertEquals(List.of(STORED_ATCH_FILE_ID), deletedAtchFileIds,
				"setSearchId가 재조회보다 먼저 실행돼야 조회 조건(WRTER_ID/REPORTR_ID)이 채워진다.");
	}
}
