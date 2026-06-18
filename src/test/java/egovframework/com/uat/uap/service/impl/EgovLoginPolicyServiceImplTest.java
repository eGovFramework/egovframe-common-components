package egovframework.com.uat.uap.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.junit.jupiter.api.Test;

import egovframework.com.uat.uap.service.LoginPolicy;
import egovframework.com.uat.uap.service.LoginPolicyVO;

class EgovLoginPolicyServiceImplTest {

	@Test
	void updateLoginPolicyCompletesWhenPersistedValuesMatch() {
		EgovLoginPolicyServiceImpl service = new EgovLoginPolicyServiceImpl();
		StubLoginPolicyDAO loginPolicyDAO = new StubLoginPolicyDAO();
		service.loginPolicyDAO = loginPolicyDAO;

		LoginPolicy loginPolicy = loginPolicy("TEST1", "192.168.0.10", "Y");
		loginPolicyDAO.selectedLoginPolicy = loginPolicyVO("TEST1", "192.168.0.10", "Y");

		assertDoesNotThrow(() -> service.updateLoginPolicy(loginPolicy));
		assertSame(loginPolicy, loginPolicyDAO.updatedLoginPolicy);
		assertEquals("TEST1", loginPolicyDAO.selectedLoginPolicyRequest.getEmplyrId());
	}

	@Test
	void updateLoginPolicyThrowsWhenPersistedValuesDoNotMatch() {
		EgovLoginPolicyServiceImpl service = new EgovLoginPolicyServiceImpl();
		StubLoginPolicyDAO loginPolicyDAO = new StubLoginPolicyDAO();
		service.loginPolicyDAO = loginPolicyDAO;

		LoginPolicy loginPolicy = loginPolicy("TEST1", "192.168.0.10", "Y");
		loginPolicyDAO.selectedLoginPolicy = loginPolicyVO("TEST1", "10.0.0.1", "N");

		assertThrows(EgovBizException.class, () -> service.updateLoginPolicy(loginPolicy));
		assertSame(loginPolicy, loginPolicyDAO.updatedLoginPolicy);
	}

	private static LoginPolicy loginPolicy(String emplyrId, String ipInfo, String lmttAt) {
		LoginPolicy loginPolicy = new LoginPolicy();
		loginPolicy.setEmplyrId(emplyrId);
		loginPolicy.setIpInfo(ipInfo);
		loginPolicy.setLmttAt(lmttAt);
		return loginPolicy;
	}

	private static LoginPolicyVO loginPolicyVO(String emplyrId, String ipInfo, String lmttAt) {
		LoginPolicyVO loginPolicyVO = new LoginPolicyVO();
		loginPolicyVO.setEmplyrId(emplyrId);
		loginPolicyVO.setIpInfo(ipInfo);
		loginPolicyVO.setLmttAt(lmttAt);
		return loginPolicyVO;
	}

	private static class StubLoginPolicyDAO extends LoginPolicyDAO {

		private LoginPolicy updatedLoginPolicy;
		private LoginPolicyVO selectedLoginPolicy;
		private LoginPolicyVO selectedLoginPolicyRequest;

		@Override
		public void updateLoginPolicy(LoginPolicy loginPolicy) {
			updatedLoginPolicy = loginPolicy;
		}

		@Override
		public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) {
			selectedLoginPolicyRequest = loginPolicyVO;
			return selectedLoginPolicy;
		}
	}
}
