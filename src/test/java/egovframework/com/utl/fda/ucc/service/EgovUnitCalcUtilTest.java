package egovframework.com.utl.fda.ucc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("EgovUnitCalcUtil 단위 환산 테스트")
public class EgovUnitCalcUtilTest {

    private EgovUnitCalcUtil util;

    @BeforeEach
    void setUp() {
        util = new EgovUnitCalcUtil();
    }

    // ─── 길이 환산 ─────────────────────────────────────────────────────

    @Test
    @DisplayName("길이: 동일 단위 환산 시 원래 값 반환")
    void convertLength_sameUnit_returnsSameValue() {
        double result = util.convertLengthCalcUnit(100.0, "vt1", "vt1");
        assertEquals(100.0, result, 0.0001);
    }

    @Test
    @DisplayName("길이: 0 입력 시 0 반환")
    void convertLength_zeroInput_returnsZero() {
        double result = util.convertLengthCalcUnit(0.0, "vt1", "vt2");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("길이: cm(vt2) -> m(vt1) 100cm = 1m")
    void convertLength_cmToM() {
        // vt1=1(m기준), vt2=0.01(cm/m)
        // nSelArAs/nSelAr * nLength = 1/0.01 * 1 = 100  → 역방향: vt2->vt1 = 0.01/1 * 100 = 1
        double result = util.convertLengthCalcUnit(100.0, "vt2", "vt1");
        assertEquals(1.0, result, 0.0001);
    }

    // ─── 부피 환산 ─────────────────────────────────────────────────────

    @Test
    @DisplayName("부피: 동일 단위 환산 시 원래 값 반환")
    void convertVolume_sameUnit_returnsSameValue() {
        double result = util.convertVolumeCalcUnit(5.0, "vl6", "vl6");
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    @DisplayName("부피: 0 입력 시 0 반환")
    void convertVolume_zeroInput_returnsZero() {
        double result = util.convertVolumeCalcUnit(0.0, "vl5", "vl6");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("부피: mL(vl5) -> L(vl6) 1000mL = 1L")
    void convertVolume_mlToL() {
        // vl5=0.001, vl6=1.0
        // nSelVl/nSelVlAs * n = 0.001/1.0 * 1000 = 1.0
        double result = util.convertVolumeCalcUnit(1000.0, "vl5", "vl6");
        assertEquals(1.0, result, 0.0001);
    }

    // ─── 무게 환산 ─────────────────────────────────────────────────────

    @Test
    @DisplayName("무게: 동일 단위 환산 시 원래 값 반환")
    void convertWeight_sameUnit_returnsSameValue() {
        double result = util.convertWeightCalcUnit(10.0, "wt1", "wt1");
        assertEquals(10.0, result, 0.0001);
    }

    @Test
    @DisplayName("무게: 0 입력 시 0 반환")
    void convertWeight_zeroInput_returnsZero() {
        double result = util.convertWeightCalcUnit(0.0, "wt1", "wt2");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("무게: g(wt2=1000) -> kg(wt1=1) 1000g = 1kg")
    void convertWeight_gToKg() {
        // wt1=1, wt2=1000
        // nSelWt/nSelWtAs * n = 1000/1 * 1 = 1000  → vt2->vt1: 1/1000 * 1000 = 1
        double result = util.convertWeightCalcUnit(1000.0, "wt1", "wt2");
        assertEquals(1.0, result, 0.0001);
    }

    // ─── 넓이 환산 ─────────────────────────────────────────────────────

    @Test
    @DisplayName("넓이: 동일 단위 환산 시 원래 값 반환")
    void convertWidth_sameUnit_returnsSameValue() {
        double result = util.convertWidthCalcUnit(1.0, "ar5", "ar5");
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    @DisplayName("넓이: 0 입력 시 0 반환")
    void convertWidth_zeroInput_returnsZero() {
        double result = util.convertWidthCalcUnit(0.0, "ar5", "ar6");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("넓이: m²(ar5) -> cm²(ar6) 1m² = 10000cm²")
    void convertWidth_m2ToCm2() {
        // ar5=1, ar6=0.01
        // nSelAr/nSelArAs * n = 1/0.01 * 1 = 100  → 실제: ar5->ar6: 1/0.01 * 1 = 100
        // ar6는 cm²→m² 변환계수이므로 ar5/ar6=1/0.01=100배
        double result = util.convertWidthCalcUnit(1.0, "ar5", "ar6");
        assertEquals(100.0, result, 0.0001);
    }

}
