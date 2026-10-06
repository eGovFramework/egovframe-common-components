package egovframework.com.cop.smt.dsm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;
import egovframework.com.cop.smt.dsm.service.EgovDiaryManageService;
import egovframework.com.cop.smt.sim.service.EgovIndvdlSchdulManageService;
import egovframework.com.cop.smt.sim.service.IndvdlSchdulManageVO;

/**
 * 일지 등록 시 연결할 일정 검사 회귀 테스트.
 *
 * <p>일지 목록·상세는 연결된 일정의 제목·내용을 일정 테이블에서 바로 가져온다. 일정 선택 팝업은 본인이 등록한
 * 일정만 보여 주지만, 저장 요청의 일정 ID 를 바꾸면 남의 일정을 붙여 그 내용을 볼 수 있으므로 저장 때 다시 확인한다.</p>
 */
class EgovDiaryManageControllerScheduleLinkTest {

	private static final String ME = "USRCNFRM_00000000001";
	private static final String OTHER = "USRCNFRM_00000000002";

	private final List<DiaryManageVO> inserted = new ArrayList<>();

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

	private EgovDiaryManageController controllerWithScheduleOf(String scheduleRegisterId) {
		IndvdlSchdulManageVO schedule = new IndvdlSchdulManageVO();
		schedule.setSchdulId("SCHDUL_0000000000001");
		schedule.setFrstRegisterId(scheduleRegisterId);

		EgovDiaryManageController controller = new EgovDiaryManageController();
		ReflectionTestUtils.setField(controller, "egovIndvdlSchdulManageService",
				Proxy.newProxyInstance(EgovIndvdlSchdulManageService.class.getClassLoader(),
						new Class<?>[] { EgovIndvdlSchdulManageService.class },
						(proxy, method, args) -> "selectIndvdlSchdulManageDetailVO".equals(method.getName())
								&& scheduleRegisterId != null ? schedule : null));
		ReflectionTestUtils.setField(controller, "egovDiaryManageService",
				Proxy.newProxyInstance(EgovDiaryManageService.class.getClassLoader(),
						new Class<?>[] { EgovDiaryManageService.class },
						(proxy, method, args) -> {
							if ("insertDiaryManage".equals(method.getName())) {
								inserted.add((DiaryManageVO) args[0]);
							}
							return null;
						}));
		return controller;
	}

	private String registActor(EgovDiaryManageController controller) throws Exception {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) Proxy.newProxyInstance(
				MultipartHttpServletRequest.class.getClassLoader(), new Class<?>[] { MultipartHttpServletRequest.class },
				(proxy, method, args) -> "getFiles".equals(method.getName()) ? Collections.emptyList() : null);
		DiaryManageVO diary = new DiaryManageVO();
		diary.setSchdulId("SCHDUL_0000000000001");
		Map<String, String> commandMap = new HashMap<>();
		commandMap.put("cmd", "save");
		return controller.diaryManageRegistActor(request, new ComDefaultVO(), commandMap, diary,
				new BeanPropertyBindingResult(diary, "diaryManageVO"), new RedirectAttributesModelMap(), new ModelMap());
	}

	@Test
	void linkingMyOwnScheduleIsSaved() throws Exception {
		bindLoginUser(ME, List.of());
		String view = registActor(controllerWithScheduleOf(ME));
		assertEquals("redirect:/cop/smt/dsm/EgovDiaryManageList.do", view);
		assertEquals(1, inserted.size());
	}

	@Test
	void linkingSomeoneElsesScheduleIsRejected() {
		bindLoginUser(ME, List.of());
		EgovDiaryManageController controller = controllerWithScheduleOf(OTHER);
		assertThrows(EgovAccessDeniedException.class, () -> registActor(controller));
		assertTrue(inserted.isEmpty(), "남의 일정을 붙인 일지는 저장되면 안 된다.");
	}

	@Test
	void linkingAMissingScheduleIsRejected() {
		bindLoginUser(ME, List.of());
		EgovDiaryManageController controller = controllerWithScheduleOf(null);
		assertThrows(EgovAccessDeniedException.class, () -> registActor(controller));
		assertTrue(inserted.isEmpty());
	}

	@Test
	void adminCanLinkSomeoneElsesSchedule() throws Exception {
		bindLoginUser(ME, List.of("ROLE_ADMIN"));
		registActor(controllerWithScheduleOf(OTHER));
		assertEquals(1, inserted.size());
	}
}
