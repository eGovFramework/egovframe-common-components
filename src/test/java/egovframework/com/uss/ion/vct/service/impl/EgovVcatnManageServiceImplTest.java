package egovframework.com.uss.ion.vct.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;

/**
 * 개인연차 정보가 없는 사용자의 휴가신청 회귀 테스트.
 *
 * selectIndvdlYrycManage는 (발생연도, 사용자ID)로 한 건을 조회하므로 개인연차가 등록되지 않은
 * 사용자에게는 null을 돌려준다. 목록 화면은 이 상태를 정상으로 보고 개인연차관리로 안내하는데,
 * 신청 처리는 결과를 그대로 역참조해 NullPointerException을 냈다.
 */
class EgovVcatnManageServiceImplTest {

	/** 개인연차가 등록되지 않아 selectIndvdlYrycManage가 null을 돌려주는 상황을 재현한다. */
	static class NullIndvdlYrycVcatnManageDAO extends VcatnManageDAO {
		private VcatnManageVO insertedVO;

		@Override
		public VcatnManageVO selectIndvdlYrycManage(VcatnManageVO vcatnManageVO) {
			return null;
		}

		@Override
		public void insertVcatnManage(VcatnManageVO vcatnManageVO) {
			insertedVO = vcatnManageVO;
		}
	}

	private static EgovInfrmlSanctnService stubSanctnService() {
		return (EgovInfrmlSanctnService) Proxy.newProxyInstance(
				EgovInfrmlSanctnService.class.getClassLoader(),
				new Class<?>[] { EgovInfrmlSanctnService.class },
				(proxy, method, args) -> {
					if ("insertInfrmlSanctn".equals(method.getName())) {
						InfrmlSanctn infrmlSanctn = new InfrmlSanctn();
						infrmlSanctn.setInfrmlSanctnId("SANCTN_0000000000001");
						return infrmlSanctn;
					}
					return null;
				});
	}

	@Test
	void insertVcatnManage_withoutIndvdlYryc_registersInsteadOfNpe() throws Exception {
		// 무급휴가(03)는 연차를 차감하지 않으므로 개인연차가 없어도 등록되어야 한다.
		// 수정 전에는 연차 차감 여부를 가리기 전에 조회 결과를 역참조해 NullPointerException이 났다.
		bindLoginUser("USRCNFRM_00000000001");
		EgovVcatnManageServiceImpl service = new EgovVcatnManageServiceImpl();
		NullIndvdlYrycVcatnManageDAO dao = new NullIndvdlYrycVcatnManageDAO();
		setField(service, "vcatnManageDAO", dao);
		setField(service, "infrmlSanctnService", stubSanctnService());

		VcatnManageVO vcatnManageVO = new VcatnManageVO();
		vcatnManageVO.setVcatnSe("03");
		vcatnManageVO.setBgnde("20260901");
		vcatnManageVO.setEndde("20260901");

		assertEquals("01", service.insertVcatnManage(vcatnManageVO),
				"개인연차가 없어도 무급휴가는 NPE 없이 등록되어야 한다");
		assertEquals("SANCTN_0000000000001", dao.insertedVO.getInfrmlSanctnId(),
				"등록된 휴가에 결재 식별자가 그대로 실려야 한다");
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
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
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static void setField(Object target, String name, Object value) throws Exception {
		Field field = EgovVcatnManageServiceImpl.class.getDeclaredField(name);
		field.setAccessible(true);
		field.set(target, value);
	}
}
