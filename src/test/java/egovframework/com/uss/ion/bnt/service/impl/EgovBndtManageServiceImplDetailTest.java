package egovframework.com.uss.ion.bnt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.uss.ion.bnt.service.BndtManageVO;

/**
 * 당직관리 단건조회가 해당 행이 없을 때 NPE를 내지 않는지 확인한다.
 *
 * <p>조회 매퍼는 {@code BNDT_ID}와 {@code BNDT_DE} 조합으로 한 건을 찾으므로 해당 행이 없으면
 * {@code null}을 돌려준다. 그런데 서비스가 그 결과를 확인 없이 바로 역참조해
 * {@code NullPointerException}이 났다. 같은 클래스의 엑셀 일괄등록 경로는 같은 DAO 결과를
 * {@code == null}로 확인한 뒤 사용한다.</p>
 */
class EgovBndtManageServiceImplDetailTest {

	/** 지정한 결과만 돌려주는 DAO 스텁. */
	private static final class StubDAO extends BndtManageDAO {
		private final BndtManageVO result;

		StubDAO(BndtManageVO result) {
			this.result = result;
		}

		@Override
		public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO) {
			return result;
		}
	}

	private static EgovBndtManageServiceImpl serviceWith(BndtManageVO daoResult) {
		EgovBndtManageServiceImpl service = new EgovBndtManageServiceImpl();
		ReflectionTestUtils.setField(service, "bndtManageDAO", new StubDAO(daoResult));
		return service;
	}

	@Test
	void detailLookupReturnsNullInsteadOfThrowingWhenTheRecordIsGone() throws Exception {
		BndtManageVO request = new BndtManageVO();
		request.setBndtId("USRCNFRM_00000000001");
		request.setBndtDe("2026-08-27");

		assertNull(serviceWith(null).selectBndtManage(request),
				"해당 당직 행이 없으면 예외 대신 결과 없음이 돌아와야 한다.");
	}

	@Test
	void detailLookupStillFormatsTheDutyDateWhenTheRecordExists() throws Exception {
		BndtManageVO stored = new BndtManageVO();
		stored.setBndtDe("20260827");

		BndtManageVO request = new BndtManageVO();
		request.setBndtId("USRCNFRM_00000000001");
		request.setBndtDe("2026-08-27");

		BndtManageVO result = serviceWith(stored).selectBndtManage(request);

		assertEquals("2026-08-27", result.getBndtDe(),
				"조회에 성공하면 기존과 같이 당직일자가 구분자 포함 형식으로 돌아와야 한다.");
	}
}
