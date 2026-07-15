package egovframework.com.sym.ccm.acr.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;

/**
 * 법정동코드 수신 배치의 N+1 제거와 등록/수정 분기를 검증한다. 단위 테스트
 *
 * @author 공통서비스 개발팀
 * @since 2026.06.30
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.06.30  공통서비스 개발팀   최초 생성
 *
 *      </pre>
 */
public class TestRegistAdministCodeNPlus1 {

	@Test
	public void registAdministCode_removesNPlus1AndPreservesBehavior() throws Exception {
		FakeAdministCodeRecptnDAO dao = new FakeAdministCodeRecptnDAO(List.of("A"));
		EgovAdministCodeRecptnServiceImpl service = newService(dao);

		service.registAdministCode(List.of(row("A"), row("B"), row("C")));

		assertEquals(1, dao.selectExistingCalls);
		assertEquals(0, dao.totCntCalls);
		assertEquals(List.of("A"), dao.updated);
		assertEquals(List.of("B", "C"), dao.inserted);
	}

	@Test
	public void registAdministCode_duplicateNewCode_insertsThenUpdates() throws Exception {
		FakeAdministCodeRecptnDAO dao = new FakeAdministCodeRecptnDAO(List.of());
		EgovAdministCodeRecptnServiceImpl service = newService(dao);

		service.registAdministCode(List.of(row("D"), row("D")));

		assertEquals(List.of("D"), dao.inserted);
		assertEquals(List.of("D"), dao.updated);
	}

	private EgovAdministCodeRecptnServiceImpl newService(FakeAdministCodeRecptnDAO dao) {
		EgovAdministCodeRecptnServiceImpl service = new EgovAdministCodeRecptnServiceImpl();
		ReflectionTestUtils.setField(service, "administCodeRecptnDAO", dao);
		ReflectionTestUtils.setField(service, "idgenService", new FakeEgovIdGnrService());
		return service;
	}

	private HashMap<String, String> row(String code) {
		HashMap<String, String> row = new HashMap<>();
		row.put("regionCd", code);
		return row;
	}

	private static class FakeAdministCodeRecptnDAO extends AdministCodeRecptnDAO {

		private final List<String> existing;
		private final List<String> inserted = new ArrayList<>();
		private final List<String> updated = new ArrayList<>();
		private int selectExistingCalls;
		private int totCntCalls;

		FakeAdministCodeRecptnDAO(List<String> existing) {
			this.existing = existing;
		}

		@Override
		public List<String> selectExistingAdministCodes() {
			selectExistingCalls++;
			return existing;
		}

		@Override
		public int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO) {
			totCntCalls++;
			return 0;
		}

		@Override
		public void insertAdministCodeRecptn(AdministCodeRecptn administCodeRecptn) {
			inserted.add(administCodeRecptn.getAdministZoneCode());
		}

		@Override
		public void insertAdministCode(AdministCodeRecptn administCodeRecptn) {
			// no-op
		}

		@Override
		public void updateAdministCode(AdministCodeRecptn administCodeRecptn) {
			updated.add(administCodeRecptn.getAdministZoneCode());
		}
	}

	private static class FakeEgovIdGnrService implements EgovIdGnrService {

		private int id;

		@Override
		public BigDecimal getNextBigDecimalId() {
			return BigDecimal.ZERO;
		}

		@Override
		public long getNextLongId() {
			return 0;
		}

		@Override
		public int getNextIntegerId() {
			return ++id;
		}

		@Override
		public short getNextShortId() {
			return 0;
		}

		@Override
		public byte getNextByteId() {
			return 0;
		}

		@Override
		public String getNextStringId() {
			return null;
		}

		@Override
		public String getNextStringId(String strategyId) {
			return null;
		}

		@Override
		public String getNextStringId(EgovIdGnrStrategy strategy) {
			return null;
		}
	}
}
