package egovframework.com.uss.ion.rwd.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SimpleSessionStatus;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rwd.service.EgovRwardManageService;
import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;

/**
 * 포상관리 수정화면(EgovRwardUpdt)의 포상구분 코드목록 회귀 테스트.
 *
 * EgovRwardUpdt.jsp의 포상구분(필수)은 form:select/form:options가 ${rwardCodeList}만 보고
 * option을 만든다. 수정화면 진입(EgovRwardManageDetail.do?cmd=updt)은 이 목록을 담아주는데,
 * 같은 화면을 다시 그리는 updtRwardManage.do의 검증실패 분기는 담지 않아 목록이 비어 있었다.
 */
class EgovRwardManageControllerRwardCodeListTest {

	private static final String APPLICANT = "USRCNFRM_00000000001";
	private static final String UPDT_VIEW = "egovframework/com/uss/ion/rwd/EgovRwardUpdt";

	/** 포상구분 공통코드(COM055) 스텁. */
	private static final List<CmmnDetailCode> RWARD_CODES = List.of(detailCode("01", "표창"), detailCode("02", "포상"));

	/** cmmUseService가 실제로 조회한 공통코드 ID. */
	private final List<String> requestedCodeIds = new ArrayList<>();

	private static CmmnDetailCode detailCode(String code, String codeNm) {
		CmmnDetailCode detailCode = new CmmnDetailCode();
		detailCode.setCode(code);
		detailCode.setCodeNm(codeNm);
		return detailCode;
	}

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		});
	}

	/** 이 테스트가 쓰는 메서드는 selectRwardManage 하나뿐이라 프록시로 최소 구현한다. */
	private static EgovRwardManageService serviceReturning(RwardManageVO stored) {
		return (EgovRwardManageService) Proxy.newProxyInstance(
				EgovRwardManageControllerRwardCodeListTest.class.getClassLoader(),
				new Class<?>[] { EgovRwardManageService.class },
				(proxy, method, args) -> "selectRwardManage".equals(method.getName()) ? stored : null);
	}

	private EgovCmmUseService cmmUseServiceStub() {
		return (EgovCmmUseService) Proxy.newProxyInstance(
				EgovRwardManageControllerRwardCodeListTest.class.getClassLoader(),
				new Class<?>[] { EgovCmmUseService.class }, (proxy, method, args) -> {
					if ("selectCmmCodeDetail".equals(method.getName())) {
						requestedCodeIds.add(((ComDefaultCodeVO) args[0]).getCodeId());
						return RWARD_CODES;
					}
					return null;
				});
	}

	private EgovRwardManageController controllerWith(RwardManageVO stored) {
		EgovRwardManageController controller = new EgovRwardManageController();
		setPrivateField(controller, "egovRwardManageService", serviceReturning(stored));
		setPrivateField(controller, "cmmUseService", cmmUseServiceStub());
		controller.egovMessageSource = new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		};
		return controller;
	}

	/** 수정화면 진입 경로는 포상구분 목록을 담아준다 — 같은 화면을 그리는 쪽이 지켜야 할 계약. */
	@Test
	void updateFormEntrySuppliesRwardCodeList() throws Exception {
		RwardManageVO stored = new RwardManageVO();
		stored.setRwardId("1");
		EgovRwardManageController controller = controllerWith(stored);

		ModelMap model = new ModelMap();
		String view = controller.selectRwardManage(new RwardManage(), new RwardManageVO(), Map.of("cmd", "updt"),
				model);

		assertEquals(UPDT_VIEW, view);
		assertNotNull(model.get("rwardCodeList"), "수정화면 진입은 포상구분 목록을 담아야 한다.");
		assertEquals(List.of("COM055"), requestedCodeIds, "포상구분은 공통코드 COM055로 조회한다.");
	}

	/** 검증실패 재표시도 같은 화면이므로 포상구분 목록이 그대로 있어야 한다. */
	@Test
	void validationErrorRedisplaySuppliesRwardCodeList() throws Exception {
		RwardManageVO stored = new RwardManageVO();
		stored.setRwardId("1");
		stored.setFrstRegisterId(APPLICANT);
		EgovRwardManageController controller = controllerWith(stored);
		bindLoginUser(APPLICANT);

		RwardManage rwardManage = new RwardManage();
		rwardManage.setRwardId("1");
		rwardManage.setRwardDe("2026-13-99"); // 포상일자 형식 오류
		BindingResult bindingResult = new BeanPropertyBindingResult(rwardManage, "rwardManage");
		bindingResult.rejectValue("rwardDe", "validation.pattern.date");

		RwardManageVO rwardManageVO = new RwardManageVO();
		rwardManageVO.setRwardId("1");
		ModelMap model = new ModelMap();

		// 검증실패 분기는 multiRequest를 건드리지 않으므로 null로 충분하다.
		String view = controller.updtRwardManage("N", null, rwardManage, bindingResult, rwardManageVO,
				new SimpleSessionStatus(), model);

		assertEquals(UPDT_VIEW, view);
		assertNotNull(model.get("rwardCodeList"),
				"검증실패로 수정화면을 다시 그릴 때 포상구분 목록이 없으면 필수값을 선택할 수 없다.");
		assertEquals(RWARD_CODES.size(), ((List<?>) model.get("rwardCodeList")).size(),
				"재표시된 포상구분 목록은 진입 때와 같은 항목 수여야 한다.");
	}
}
