package egovframework.com.uss.olp.cns.web;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;
import egovframework.com.uss.olp.cns.service.EgovCnsltManageService;

/**
 * updateCnsltDtls의 소유권 검증 회귀 테스트.
 *
 * 수정 전 코드는 소유자를 전혀 대조하지 않아, 로그인만 하면(또는 비로그인 상태로도)
 * 임의의 cnsltId를 넣어 타인의 상담정보를 수정할 수 있었다(IDOR).
 * ROLE_ADMIN 보유자는 소유자가 아니어도 통과해야 한다(다른 카드와 동일 관례).
 */
class EgovCnsltManageControllerOwnershipTest {

	private static final String OWNER = "USRCNFRM_00000000001";
	private static final String ATTACKER = "USRCNFRM_00000000009";

	/** selectCnsltListDetail은 저장된 등록자를 돌려주고, updateCnsltDtls 호출 여부를 기록하는 스텁. */
	private static final class StubService implements EgovCnsltManageService {
		private final CnsltManageVO stored;
		private boolean updateCalled = false;
		private CnsltManageVO lastUpdateArg;

		StubService(String ownerUniqId) {
			this.stored = new CnsltManageVO();
			this.stored.setFrstRegisterId(ownerUniqId);
		}

		@Override
		public CnsltManageVO selectCnsltListDetail(CnsltManageVO vo) {
			return stored;
		}

		@Override
		public void updateCnsltInqireCo(CnsltManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<org.egovframe.rte.psl.dataaccess.util.EgovMap> selectCnsltList(CnsltManageDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectCnsltListTotCnt(CnsltManageDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertCnsltDtls(CnsltManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectCnsltPasswordConfirmCnt(CnsltManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateCnsltDtls(CnsltManageVO vo) {
			updateCalled = true;
			lastUpdateArg = vo;
		}

		@Override
		public void deleteCnsltDtls(CnsltManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public CnsltManageVO selectCnsltAnswerListDetail(CnsltManageVO vo) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<org.egovframe.rte.psl.dataaccess.util.EgovMap> selectCnsltAnswerList(
				CnsltManageDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectCnsltAnswerListTotCnt(CnsltManageDefaultVO searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateCnsltDtlsAnswer(CnsltManageVO vo) {
			throw new UnsupportedOperationException();
		}
	}

	private static void bindLoginUser(String uniqId, List<String> authorities) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return authorities;
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovCnsltManageController controllerWith(StubService service) throws Exception {
		EgovCnsltManageController controller = new EgovCnsltManageController();
		Field serviceField = EgovCnsltManageController.class.getDeclaredField("cnsltManageService");
		serviceField.setAccessible(true);
		serviceField.set(controller, service);
		return controller;
	}

	/** getFiles("file_1")만 빈 리스트를 돌려주는 최소 스텁. 그 외 메서드는 호출되지 않아야 한다(그러면 실패). */
	private static MultipartHttpServletRequest emptyMultiRequest() {
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				if ("getFiles".equals(method.getName())) {
					return Collections.<MultipartFile>emptyList();
				}
				throw new UnsupportedOperationException("Unexpected call: " + method.getName());
			}
		};
		return (MultipartHttpServletRequest) Proxy.newProxyInstance(
				EgovCnsltManageControllerOwnershipTest.class.getClassLoader(),
				new Class<?>[] { MultipartHttpServletRequest.class }, handler);
	}

	private static CnsltManageVO requestFor(String cnsltId) {
		CnsltManageVO vo = new CnsltManageVO();
		vo.setCnsltId(cnsltId);
		vo.setCnsltSj("edited subject");
		vo.setCnsltCn("edited content");
		vo.setWrterNm("attacker");
		vo.setAreaNo("02");
		vo.setMiddleTelno("1234");
		vo.setEndTelno("5678");
		vo.setWritngPassword("pw");
		return vo;
	}

	@Test
	void updateByNonOwnerDoesNotReachTheUpdateService() throws Exception {
		StubService service = new StubService(OWNER);
		EgovCnsltManageController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of());

		CnsltManageVO vo = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(vo, "cnsltManageVO");
		ModelMap model = new ModelMap();

		assertThrows(IllegalStateException.class,
				() -> controller.updateCnsltDtls("N", emptyMultiRequest(), new CnsltManageDefaultVO(), vo,
						bindingResult, model),
				"A logged-in non-owner must not be able to update another member's consultation record.");
		assertTrue(!service.updateCalled, "updateCnsltDtls service must not be reached by a non-owner.");
	}

	@Test
	void updateByOwnerReachesTheUpdateService() throws Exception {
		StubService service = new StubService(OWNER);
		EgovCnsltManageController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		CnsltManageVO vo = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(vo, "cnsltManageVO");
		ModelMap model = new ModelMap();

		controller.updateCnsltDtls("N", emptyMultiRequest(), new CnsltManageDefaultVO(), vo, bindingResult, model);
		assertTrue(service.updateCalled, "The owner must be able to update their own consultation record.");
	}

	/**
	 * CnsltDtlsUpdt.jsp 폼에는 frstRegisterId 히든필드가 없어 요청 바인딩 결과는 항상 null이다.
	 * 그 null을 그대로 서비스에 넘기면 UPDATE문이 FRST_REGISTER_ID를 매번 지워버려,
	 * 같은 소유자가 두 번째로 수정을 시도할 때 저장된 등록자가 null이 되어 영원히 차단된다.
	 * 컨트롤러는 조회해둔 stored의 값을 복원해서 넘겨야 한다.
	 */
	@Test
	void updateRestoresFrstRegisterIdSoTheNextEditByTheSameOwnerStillWorks() throws Exception {
		StubService service = new StubService(OWNER);
		EgovCnsltManageController controller = controllerWith(service);
		bindLoginUser(OWNER, List.of());

		CnsltManageVO vo = requestFor("1");
		assertTrue(vo.getFrstRegisterId() == null, "요청 바인딩은 frstRegisterId를 채우지 않는다(JSP에 히든필드 없음).");
		BindingResult bindingResult = new BeanPropertyBindingResult(vo, "cnsltManageVO");
		ModelMap model = new ModelMap();

		controller.updateCnsltDtls("N", emptyMultiRequest(), new CnsltManageDefaultVO(), vo, bindingResult, model);

		assertTrue(service.lastUpdateArg.getFrstRegisterId() != null
				&& OWNER.equals(service.lastUpdateArg.getFrstRegisterId()),
				"수정 시 등록자ID를 보존하지 않으면 다음 수정부터 소유권 검증이 실패한다(자기 글 락아웃).");
	}

	@Test
	void updateByAdminReachesTheUpdateServiceEvenWhenNotOwner() throws Exception {
		StubService service = new StubService(OWNER);
		EgovCnsltManageController controller = controllerWith(service);
		bindLoginUser(ATTACKER, List.of("ROLE_ADMIN"));

		CnsltManageVO vo = requestFor("1");
		BindingResult bindingResult = new BeanPropertyBindingResult(vo, "cnsltManageVO");
		ModelMap model = new ModelMap();

		controller.updateCnsltDtls("N", emptyMultiRequest(), new CnsltManageDefaultVO(), vo, bindingResult, model);
		assertTrue(service.updateCalled, "An admin must be able to update any member's consultation record.");
	}
}
