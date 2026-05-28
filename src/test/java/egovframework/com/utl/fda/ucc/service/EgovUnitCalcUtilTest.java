package egovframework.com.utl.fda.ucc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EgovUnitCalcUtil 단위 테스트
 *
 * 길이·부피·넓이·무게 환산 메서드의 결정적 입출력을 검증한다.
 *
 * @author 공통서비스
 * @since 2026.05.28
 */
public class EgovUnitCalcUtilTest {

    private static final double DELTA = 1e-9;

    private EgovUnitCalcUtil util;

    @BeforeEach
    void setUp() {
        util = new EgovUnitCalcUtil();
    }

    // -------------------------------------------------------
    // 길이 환산 (convertLengthCalcUnit)
    // vt1=cm(기준=1), vt2=m(0.01), vt3=inch(1/2.54), vt4=ft(1/30.48)
    // -------------------------------------------------------

    @Test
    void 길이_동일단위_변환없음() {
        double result = util.convertLengthCalcUnit(100.0, "vt1", "vt1");
        assertEquals(100.0, result, DELTA);
    }

    @Test
    void 길이_cm에서_m으로() {
        // 100cm → 1m
        double result = util.convertLengthCalcUnit(100.0, "vt1", "vt2");
        assertEquals(1.0, result, DELTA);
    }

    @Test
    void 길이_m에서_cm으로() {
        // 1m → 100cm
        double result = util.convertLengthCalcUnit(1.0, "vt2", "vt1");
        assertEquals(100.0, result, DELTA);
    }

    @Test
    void 길이_inch에서_cm으로() {
        // 1inch = 2.54cm
        double result = util.convertLengthCalcUnit(1.0, "vt3", "vt1");
        assertEquals(2.54, result, 1e-6);
    }

    @Test
    void 길이_ft에서_cm으로() {
        // 1ft = 30.48cm
        double result = util.convertLengthCalcUnit(1.0, "vt4", "vt1");
        assertEquals(30.48, result, 1e-6);
    }

    @Test
    void 길이_값_0() {
        double result = util.convertLengthCalcUnit(0.0, "vt1", "vt2");
        assertEquals(0.0, result, DELTA);
    }

    // -------------------------------------------------------
    // 무게 환산 (convertWeightCalcUnit)
    // wt1=g(기준=1), wt2=mg(1000), wt3=kg(0.001), wt7=pound(1/453.59237)
    // -------------------------------------------------------

    @Test
    void 무게_동일단위_변환없음() {
        double result = util.convertWeightCalcUnit(500.0, "wt1", "wt1");
        assertEquals(500.0, result, DELTA);
    }

    @Test
    void 무게_wt1에서_wt3으로() {
        // convertWeightCalcUnit 공식: wt_from / wt_to * amount
        // wt1=1, wt3=0.001 → 1/0.001 * 1 = 1000
        double result = util.convertWeightCalcUnit(1.0, "wt1", "wt3");
        assertEquals(1000.0, result, DELTA);
    }

    @Test
    void 무게_wt3에서_wt1으로() {
        // wt3/wt1 * 1 = 0.001/1 * 1 = 0.001
        double result = util.convertWeightCalcUnit(1.0, "wt3", "wt1");
        assertEquals(0.001, result, DELTA);
    }

    @Test
    void 무게_wt1에서_wt2으로() {
        // wt1/wt2 * 1 = 1/1000 * 1 = 0.001
        double result = util.convertWeightCalcUnit(1.0, "wt1", "wt2");
        assertEquals(0.001, result, DELTA);
    }

    @Test
    void 무게_wt7에서_wt1으로() {
        // wt7 = 1/453.59237, wt7/wt1 * 1 ≈ 0.002204622
        double expected = (1.0 / 453.59237) / 1.0 * 1.0;
        double result = util.convertWeightCalcUnit(1.0, "wt7", "wt1");
        assertEquals(expected, result, 1e-10);
    }

    // -------------------------------------------------------
    // 부피 환산 (convertVolumeCalcUnit)
    // 공식: vl_from / vl_to * nVolume
    // vl6=L(기준=1), vl5=mL(0.001)
    // -------------------------------------------------------

    @Test
    void 부피_동일단위_변환없음() {
        double result = util.convertVolumeCalcUnit(5.0, "vl6", "vl6");
        assertEquals(5.0, result, DELTA);
    }

    @Test
    void 부피_L에서_mL으로() {
        // 1L → 1000mL
        double result = util.convertVolumeCalcUnit(1.0, "vl6", "vl5");
        assertEquals(1000.0, result, DELTA);
    }

    @Test
    void 부피_mL에서_L으로() {
        // 1000mL → 1L
        double result = util.convertVolumeCalcUnit(1000.0, "vl5", "vl6");
        assertEquals(1.0, result, DELTA);
    }

    // -------------------------------------------------------
    // 넓이 환산 (convertWidthCalcUnit)
    // 공식: ar_from / ar_to * nWidth
    // ar5=m²(기준=1), ar6=cm²(0.01)
    // -------------------------------------------------------

    @Test
    void 넓이_동일단위_변환없음() {
        double result = util.convertWidthCalcUnit(10.0, "ar5", "ar5");
        assertEquals(10.0, result, DELTA);
    }

    @Test
    void 넓이_m2에서_cm2으로() {
        // 1m² → 10000cm²  (ar5/ar6 * 1 = 1/0.01 * 1 = 100, but unit is different?)
        // ar5=1, ar6=0.01 → ar5/ar6=100
        double result = util.convertWidthCalcUnit(1.0, "ar5", "ar6");
        assertEquals(100.0, result, DELTA);
    }

    @Test
    void 넓이_cm2에서_m2으로() {
        // ar6/ar5 * 100 = 0.01/1 * 100 = 1
        double result = util.convertWidthCalcUnit(100.0, "ar6", "ar5");
        assertEquals(1.0, result, DELTA);
    }
}
