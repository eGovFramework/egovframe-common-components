package egovframework.com.ssi.syi.sim.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.ssi.syi.iis.service.EgovCntcInsttService;
import egovframework.com.ssi.syi.sim.service.EgovSystemCntcService;
import egovframework.com.ssi.syi.sim.service.SystemCntc;
import egovframework.com.ssi.syi.sim.service.SystemCntcVO;

/**
 * 지워진 시스템연계를 상세·수정화면·승인상세로 열었을 때 목록으로 되돌아가는지 확인한다.
 *
 * <p>selectSystemCntcDetail 은 매칭 행이 없으면 null 을 준다
 * (SystemCntcDAO:56 의 selectOne, EgovSystemCntc_SQL_*.xml 의 WHERE CNTC_ID = #{cntcId}).
 * 형제 deleteSystemCntc(:97-101)는 그 null 을 검사하고 목록으로 되돌린다.</p>
 *
 * <p>스프링 컨텍스트·DB 없이 동적 프록시 페이크와 ReflectionTestUtils 필드 주입으로 돌린다.</p>
 */
class EgovSystemCntcControllerMissingRecordTest {

	private static final String LIST = "forward:/ssi/syi/sim/getSystemCntcList.do";
	private static final String CONFIRM_LIST = "forward:/ssi/syi/scm/getConfirmSystemCntcList.do";

	private EgovSystemCntcController controller;
	private EgovUserDetailsService originalUserDetailsService;

	@BeforeEach
	void setUp() {
		EgovUserDetailsHelper helper = new EgovUserDetailsHelper();
		originalUserDetailsService = helper.getEgovUserDetailsService();
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000001");
		helper.setEgovUserDetailsService(stub(EgovUserDetailsService.class, (proxy, method, args) -> {
			switch (method.getName()) {
			case "isAuthenticated":
				return Boolean.TRUE;
			case "getAuthenticatedUser":
				return loginVO;
			case "getAuthorities":
				return new ArrayList<String>();
			default:
				return null;
			}
		}));

		controller = new EgovSystemCntcController();
		// 요청한 cntcId 의 행이 이미 없는 상태
		ReflectionTestUtils.setField(controller, "systemCntcService",
				stub(EgovSystemCntcService.class, (proxy, method, args) -> null));
		ReflectionTestUtils.setField(controller, "cntcInsttService",
				stub(EgovCntcInsttService.class, (proxy, method, args) -> new ArrayList<>()));
		ReflectionTestUtils.setField(controller, "egovMessageSource", new EgovMessageSource() {
			@Override
			public String getMessage(String code) {
				return code;
			}
		});
	}

	@AfterEach
	void restoreUserDetailsService() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(originalUserDetailsService);
	}

	@Test
	void detailFallsBackToListWhenRecordIsGone() throws Exception {
		ModelMap model = new ModelMap();

		String view = controller.selectSystemCntcDetail(new SystemCntcVO(), gone(), model, redirect());

		assertEquals(LIST, view, "없는 시스템연계 상세는 목록으로 되돌려야 한다");
		assertEquals("fail.common.select", model.get("message"));
	}

	@Test
	void updateViewFallsBackToListWhenRecordIsGone() throws Exception {
		ModelMap model = new ModelMap();

		String view = controller.updateSystemCntcView(new SystemCntcVO(), gone(), model);

		assertEquals(LIST, view, "없는 시스템연계 수정화면은 목록으로 되돌려야 한다");
		assertEquals("fail.common.select", model.get("message"));
	}

	@Test
	void confirmDetailFallsBackToListWhenRecordIsGone() throws Exception {
		ModelMap model = new ModelMap();

		String view = controller.selectConfirmSystemCntcDetail(new SystemCntcVO(), gone(), "", model, redirect());

		assertEquals(CONFIRM_LIST, view, "없는 시스템연계 승인상세는 승인목록으로 되돌려야 한다");
		assertEquals("fail.common.select", model.get("message"));
	}

	private SystemCntc gone() {
		SystemCntc systemCntc = new SystemCntc();
		systemCntc.setCntcId("SYSTEMCNTC_00000000099");
		return systemCntc;
	}

	private RedirectAttributes redirect() {
		return new RedirectAttributesModelMap();
	}

	@SuppressWarnings("unchecked")
	private static <T> T stub(Class<T> type, InvocationHandler handler) {
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] { type }, handler);
	}
}
