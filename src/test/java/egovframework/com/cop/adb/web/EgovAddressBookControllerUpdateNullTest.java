package egovframework.com.cop.adb.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;

/**
 * 주소록 수정화면이 존재하지 않는 주소록을 받았을 때 처리를 확인한다.
 *
 * <p>{@code EgovAddressBookServiceImpl.selectAdressBook}은 {@code if(adbkVO != null)}로
 * 조회 결과가 없을 수 있음을 스스로 밝히고 그대로 돌려준다. 그런데 수정화면은 그 결과를
 * 확인 없이 바로 역참조해 {@code NullPointerException}이 났다.</p>
 */
class EgovAddressBookControllerUpdateNullTest {

	private EgovAddressBookController controller;

	@BeforeEach
	void setUp() {
		EgovUserDetailsService auth = (EgovUserDetailsService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					if ("isAuthenticated".equals(method.getName())) {
						return Boolean.TRUE;
					}
					if ("getAuthenticatedUser".equals(method.getName())) {
						LoginVO user = new LoginVO();
						user.setId("USER");
						return user;
					}
					return null;
				});
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", auth);

		// 없는 주소록을 조회한 상황.
		EgovAddressBookService service = (EgovAddressBookService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovAddressBookService.class },
				(proxy, method, args) -> null);

		controller = new EgovAddressBookController();
		ReflectionTestUtils.setField(controller, "adbkService", service);
	}

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(EgovUserDetailsHelper.class, "egovUserDetailsService", null);
	}

	@Test
	void addressBookUpdateViewFallsBackToTheListWhenTheRecordIsGone() throws Exception {
		AddressBookVO vo = new AddressBookVO();
		vo.setAdbkId("ADBK_00000000000000");

		String view = controller.updateAdbkInf(vo, new ModelMap());

		assertNotNull(view, "없는 주소록으로 수정화면을 열면 예외 대신 화면 이동이 나와야 한다.");
		assertEquals("forward:/cop/adb/selectAdbkList.do", view);
	}
}
