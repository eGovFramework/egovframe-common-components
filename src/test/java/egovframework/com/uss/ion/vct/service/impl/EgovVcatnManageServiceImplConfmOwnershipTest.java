package egovframework.com.uss.ion.vct.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.junit.jupiter.api.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;

/**
 * updtVcatnManageConfm(휴가 승인·반려)의 소유권 검증 회귀 테스트.
 *
 * deleteVcatnManage는 신청자(APPLCNT_ID) 본인 또는 관리자만 통과시키는데, 승인·반려 처리
 * updtVcatnManageConfm은 지정된 승인권자(SANCTNER_ID) 대조 없이 어떤 로그인 사용자든 그대로
 * 처리해, 담당 승인권자가 아닌 사용자가 타인의 휴가를 승인·반려하고 잔여연차까지 바꿀 수 있었다.
 */
class EgovVcatnManageServiceImplConfmOwnershipTest {

	private static final String SANCTNER = "USRCNFRM_00000000001";
	private static final String OUTSIDER = "USRCNFRM_00000000009";

	/** selectVcatnManage는 저장된 승인권자를 돌려주고, updtVcatnManageConfm 호출 여부를 기록하는 스텁. */
	private static final class StubDAO extends VcatnManageDAO {
		private final VcatnManageVO stored;
		private boolean confmCalled = false;

		StubDAO(String sanctnerUniqId) {
			this.stored = new VcatnManageVO();
			this.stored.setSanctnerId(sanctnerUniqId);
		}

		@Override
		public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO) {
			return stored;
		}

		@Override
		public void updtVcatnManageConfm(VcatnManageVO vcatnManageVO) {
			confmCalled = true;
		}
	}

	private static EgovInfrmlSanctnService stubSanctnService() {
		return (EgovInfrmlSanctnService) Proxy.newProxyInstance(
				EgovInfrmlSanctnService.class.getClassLoader(),
				new Class<?>[] { EgovInfrmlSanctnService.class },
				(proxy, method, args) -> {
					if ("updateInfrmlSanctnConfm".equals(method.getName())) {
						InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
						infrmlSanctn.setSanctnDt("20260914");
						infrmlSanctn.setConfmAt("C");
						return infrmlSanctn;
					}
					return null;
				});
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

	private static void setField(Object target, String name, Object value) throws Exception {
		Field field = EgovVcatnManageServiceImpl.class.getDeclaredField(name);
		field.setAccessible(true);
		field.set(target, value);
	}

	private static VcatnManageVO approveRequest() {
		VcatnManageVO vo = new VcatnManageVO();
		vo.setConfmAt("C");
		vo.setBgnde("20260901");
		vo.setEndde("20260901");
		return vo;
	}

	@Test
	void confmByOutsiderIsRejected() throws Exception {
		bindLoginUser(OUTSIDER, List.of());
		EgovVcatnManageServiceImpl service = new EgovVcatnManageServiceImpl();
		StubDAO dao = new StubDAO(SANCTNER);
		setField(service, "vcatnManageDAO", dao);
		setField(service, "infrmlSanctnService", stubSanctnService());

		assertThrows(EgovBizException.class, () -> service.updtVcatnManageConfm(approveRequest()),
				"A logged-in user who is not the designated sanctioner must not be able to confirm another member's vacation request.");
		assertFalse(dao.confmCalled, "updtVcatnManageConfm must not be reached by a non-sanctioner.");
	}

	@Test
	void confmBySanctionerSucceeds() throws Exception {
		bindLoginUser(SANCTNER, List.of());
		EgovVcatnManageServiceImpl service = new EgovVcatnManageServiceImpl();
		StubDAO dao = new StubDAO(SANCTNER);
		setField(service, "vcatnManageDAO", dao);
		setField(service, "infrmlSanctnService", stubSanctnService());

		assertDoesNotThrow(() -> service.updtVcatnManageConfm(approveRequest()));
		assertTrue(dao.confmCalled, "The designated sanctioner must be able to confirm the vacation request.");
	}

	@Test
	void confmByAdminSucceedsEvenWhenNotSanctioner() throws Exception {
		bindLoginUser(OUTSIDER, List.of("ROLE_ADMIN"));
		EgovVcatnManageServiceImpl service = new EgovVcatnManageServiceImpl();
		StubDAO dao = new StubDAO(SANCTNER);
		setField(service, "vcatnManageDAO", dao);
		setField(service, "infrmlSanctnService", stubSanctnService());

		assertDoesNotThrow(() -> service.updtVcatnManageConfm(approveRequest()));
		assertTrue(dao.confmCalled, "An admin must be able to confirm any member's vacation request.");
	}
}
