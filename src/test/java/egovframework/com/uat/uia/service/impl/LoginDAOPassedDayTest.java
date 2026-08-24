package egovframework.com.uat.uia.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.LoginVO;

class LoginDAOPassedDayTest {

	// selectOne이 결과 0건에서 null을 반환하는 상황(계정 미존재)을 재현
	static class NullSelectOneLoginDAO extends LoginDAO {
		@Override
		@SuppressWarnings("unchecked")
		public <T> T selectOne(String queryId, Object parameterObject) {
			return null;
		}
	}

	@Test
	void 계정이_없으면_NPE_없이_0을_반환한다() {
		LoginDAO dao = new NullSelectOneLoginDAO();
		LoginVO vo = new LoginVO();
		vo.setId("nonexistent-id");
		vo.setUserSe("GNR");

		assertEquals(0, dao.selectPassedDayChangePWD(vo));
	}
}
