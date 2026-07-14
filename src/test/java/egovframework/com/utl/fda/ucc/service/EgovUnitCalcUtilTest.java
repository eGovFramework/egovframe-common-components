package egovframework.com.utl.fda.ucc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EgovUnitCalcUtil 단위 환산 테스트.
 *
 * <p>
 * 환산 정의: convert(value, From, As) = factor[As] / factor[From] * value.
 * factor[u] 는 기준 단위 1을 단위 u 로 표현한 값이므로, factor 가 클수록 더 작은 물리 단위를 의미한다.
 * 모든 기대값은 생성자의 factor 정의에서 수학적으로 도출하였다.
 * </p>
 */
public class EgovUnitCalcUtilTest {

	private static final double DELTA = 1e-9;

	private final EgovUnitCalcUtil util = new EgovUnitCalcUtil();

	// ── 길이(회귀: 정답 유지) ── vt1=1(작은 단위), vt2=0.01(큰 단위)
	@Test
	@DisplayName("길이: vt1 -> vt2 정방향")
	void length_forward() {
		// convert(100, vt1, vt2) = 0.01/1*100 = 1.0
		assertEquals(1.0, util.convertLengthCalcUnit(100, "vt1", "vt2"), DELTA);
	}

	@Test
	@DisplayName("길이: vt2 -> vt1 역방향")
	void length_reverse() {
		// convert(1, vt2, vt1) = 1/0.01*1 = 100.0
		assertEquals(100.0, util.convertLengthCalcUnit(1, "vt2", "vt1"), DELTA);
	}

	@Test
	@DisplayName("길이: 동일 단위 항등")
	void length_identity() {
		assertEquals(7.0, util.convertLengthCalcUnit(7, "vt1", "vt1"), DELTA);
	}

	// ── 무게 ── wt1=1, wt2=1000, wt3=0.001
	@Test
	@DisplayName("무게: wt1 -> wt3 정방향")
	void weight_forward() {
		// convert(1, wt1, wt3) = 0.001/1 = 0.001
		assertEquals(0.001, util.convertWeightCalcUnit(1, "wt1", "wt3"), DELTA);
	}

	@Test
	@DisplayName("무게: wt3 -> wt1 역방향")
	void weight_reverse() {
		// convert(1, wt3, wt1) = 1/0.001 = 1000.0
		assertEquals(1000.0, util.convertWeightCalcUnit(1, "wt3", "wt1"), DELTA);
	}

	@Test
	@DisplayName("무게: wt1 -> wt2 / wt2 -> wt1")
	void weight_pair() {
		// convert(1, wt1, wt2) = 1000/1 = 1000.0
		assertEquals(1000.0, util.convertWeightCalcUnit(1, "wt1", "wt2"), DELTA);
		// convert(2, wt2, wt1) = 1/1000*2 = 0.002
		assertEquals(0.002, util.convertWeightCalcUnit(2, "wt2", "wt1"), DELTA);
	}

	@Test
	@DisplayName("무게: 동일 단위 항등")
	void weight_identity() {
		assertEquals(5.0, util.convertWeightCalcUnit(5, "wt1", "wt1"), DELTA);
	}

	// ── 부피 ── vl6=1, vl5=0.001, vl4=10000
	@Test
	@DisplayName("부피: vl6 -> vl5 정방향")
	void volume_forward() {
		// convert(1, vl6, vl5) = 0.001/1 = 0.001
		assertEquals(0.001, util.convertVolumeCalcUnit(1, "vl6", "vl5"), DELTA);
	}

	@Test
	@DisplayName("부피: vl5 -> vl6 역방향")
	void volume_reverse() {
		// convert(1, vl5, vl6) = 1/0.001 = 1000.0
		assertEquals(1000.0, util.convertVolumeCalcUnit(1, "vl5", "vl6"), DELTA);
	}

	@Test
	@DisplayName("부피: vl6 -> vl4")
	void volume_large() {
		// convert(1, vl6, vl4) = 10000/1 = 10000.0
		assertEquals(10000.0, util.convertVolumeCalcUnit(1, "vl6", "vl4"), DELTA);
	}

	@Test
	@DisplayName("부피: 동일 단위 항등")
	void volume_identity() {
		assertEquals(3.0, util.convertVolumeCalcUnit(3, "vl6", "vl6"), DELTA);
	}

	// ── 넓이 ── ar5=1, ar6=0.01, ar10=0.0001
	@Test
	@DisplayName("넓이: ar5 -> ar6 정방향")
	void width_forward() {
		// convert(1, ar5, ar6) = 0.01/1 = 0.01
		assertEquals(0.01, util.convertWidthCalcUnit(1, "ar5", "ar6"), DELTA);
	}

	@Test
	@DisplayName("넓이: ar6 -> ar5 역방향")
	void width_reverse() {
		// convert(1, ar6, ar5) = 1/0.01 = 100.0
		assertEquals(100.0, util.convertWidthCalcUnit(1, "ar6", "ar5"), DELTA);
	}

	@Test
	@DisplayName("넓이: ar5 -> ar10 / ar10 -> ar5")
	void width_pair() {
		// convert(1, ar5, ar10) = 0.0001/1 = 0.0001
		assertEquals(0.0001, util.convertWidthCalcUnit(1, "ar5", "ar10"), DELTA);
		// convert(2, ar10, ar5) = 1/0.0001*2 = 20000.0
		assertEquals(20000.0, util.convertWidthCalcUnit(2, "ar10", "ar5"), DELTA);
	}

	@Test
	@DisplayName("넓이: 동일 단위 항등")
	void width_identity() {
		assertEquals(4.0, util.convertWidthCalcUnit(4, "ar5", "ar5"), DELTA);
	}
}
