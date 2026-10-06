package egovframework.com.cop.smt.sim.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.sim.service.EgovIndvdlSchdulManageService;
import egovframework.com.cop.smt.sim.service.IndvdlSchdulManageVO;

/**
 * 개인일정 상세·삭제 경로의 일정 종류 검사 회귀 테스트.
 *
 * <p>개인일정과 부서일정은 같은 테이블을 쓴다. 개인일정 경로는 작성자 또는 관리자를 통과시키므로,
 * 종류를 확인하지 않으면 관리자가 이 경로로 다른 부서의 부서일정을 지워 부서일정의 같은 부서 검사를 우회한다.</p>
 */
class EgovIndvdlSchdulManageControllerKindTest {

	private static final String ME = "USRCNFRM_00000000001";
	private static final String OTHER = "USRCNFRM_00000000002";

	private final List<String> deleted = new ArrayList<>();

	private static void bindLoginUser(String uniqId, List<String> authorities) {
		EgovUserDetailsService stub = (EgovUserDetailsService) Proxy.newProxyInstance(
				EgovUserDetailsService.class.getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "isAuthenticated":
						return Boolean.TRUE;
					case "getAuthenticatedUser":
						LoginVO loginVO = new LoginVO();
						loginVO.setUniqId(uniqId);
						return loginVO;
					case "getAuthorities":
						return authorities;
					default:
						return null;
					}
				});
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	@AfterEach
	void clearLoginUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	/** kindCode 가 null 이면 일정이 없는 것으로 본다. */
	private EgovIndvdlSchdulManageController controllerWith(String kindCode, String registerId) {
		IndvdlSchdulManageVO stored = new IndvdlSchdulManageVO();
		stored.setSchdulId("SCHDUL_0000000000001");
		stored.setSchdulKindCode(kindCode);
		stored.setFrstRegisterId(registerId);

		EgovIndvdlSchdulManageController controller = new EgovIndvdlSchdulManageController();
		ReflectionTestUtils.setField(controller, "egovIndvdlSchdulManageService",
				Proxy.newProxyInstance(EgovIndvdlSchdulManageService.class.getClassLoader(),
						new Class<?>[] { EgovIndvdlSchdulManageService.class },
						(proxy, method, args) -> {
							switch (method.getName()) {
							case "selectIndvdlSchdulManageDetailVO":
								return kindCode == null ? null : stored;
							case "deleteIndvdlSchdulManage":
								deleted.add(((IndvdlSchdulManageVO) args[0]).getSchdulId());
								return null;
							default:
								return null;
							}
						}));
		return controller;
	}

	private static IndvdlSchdulManageVO request() {
		IndvdlSchdulManageVO vo = new IndvdlSchdulManageVO();
		vo.setSchdulId("SCHDUL_0000000000001");
		return vo;
	}

	@Test
	void deptScheduleDetailIsDeniedThroughPersonalSchedulePathEvenForItsRegistrant() throws Exception {
		bindLoginUser(ME, List.of());
		String view = controllerWith("1", ME).egovIndvdlSchdulManageDetail(new ComDefaultVO(), request(), new ModelMap());
		assertEquals("egovframework/com/cmm/error/accessDenied", view, "부서일정 상세는 관리자 전용 부서일정 경로로만 본다.");
	}

	@Test
	void adminDeletesSomeoneElsesPersonalSchedule() throws Exception {
		bindLoginUser(ME, List.of("ROLE_ADMIN"));
		controllerWith("2", OTHER).egovIndvdlSchdulManageDelete(request(), new ModelMap());
		assertTrue(deleted.contains("SCHDUL_0000000000001"));
	}

	@Test
	void deptScheduleCannotBeDeletedThroughPersonalSchedulePath() {
		bindLoginUser(ME, List.of("ROLE_ADMIN"));
		EgovIndvdlSchdulManageController controller = controllerWith("1", OTHER);
		assertThrows(EgovAccessDeniedException.class, () -> controller.egovIndvdlSchdulManageDelete(request(), new ModelMap()));
		assertTrue(deleted.isEmpty(), "개인일정 경로로 부서일정을 지우면 안 된다.");
	}

	@Test
	void missingScheduleIsRejectedEvenForAdmin() {
		bindLoginUser(ME, List.of("ROLE_ADMIN"));
		EgovIndvdlSchdulManageController controller = controllerWith(null, null);
		assertThrows(EgovAccessDeniedException.class, () -> controller.egovIndvdlSchdulManageDelete(request(), new ModelMap()));
		assertTrue(deleted.isEmpty());
	}

	@Test
	void otherUsersPersonalScheduleIsRejectedForNonAdmin() {
		bindLoginUser(ME, List.of());
		EgovIndvdlSchdulManageController controller = controllerWith("2", OTHER);
		assertThrows(EgovAccessDeniedException.class, () -> controller.egovIndvdlSchdulManageDelete(request(), new ModelMap()));
		assertTrue(deleted.isEmpty());
	}
}
