package egovframework.com.cmm.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("EgovCommonValidator 비밀번호 규칙 검사 테스트")
public class EgovCommonValidatorTest {

    private EgovCommonValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EgovCommonValidator();
    }

    // ─── hasSeries ────────────────────────────────────────────────────

    @Test
    @DisplayName("hasSeries: 오름차순 연속 문자 포함 시 true")
    void hasSeries_ascending_returnsTrue() {
        assertTrue(validator.hasSeries("abc"));
    }

    @Test
    @DisplayName("hasSeries: 내림차순 연속 문자 포함 시 true")
    void hasSeries_descending_returnsTrue() {
        assertTrue(validator.hasSeries("cba"));
    }

    @Test
    @DisplayName("hasSeries: 연속 숫자 오름차순 포함 시 true")
    void hasSeries_ascendingDigits_returnsTrue() {
        assertTrue(validator.hasSeries("123"));
    }

    @Test
    @DisplayName("hasSeries: 연속 숫자 내림차순 포함 시 true")
    void hasSeries_descendingDigits_returnsTrue() {
        assertTrue(validator.hasSeries("321"));
    }

    @Test
    @DisplayName("hasSeries: 연속 없는 비밀번호는 false")
    void hasSeries_noSeries_returnsFalse() {
        assertFalse(validator.hasSeries("aceg"));
    }

    @Test
    @DisplayName("hasSeries: 2자 이하 문자열은 false")
    void hasSeries_tooShort_returnsFalse() {
        assertFalse(validator.hasSeries("ab"));
    }

    @Test
    @DisplayName("hasSeries: 혼합 문자열 내 연속 부분 포함 시 true")
    void hasSeries_partialSeries_returnsTrue() {
        assertTrue(validator.hasSeries("x1abc2"));
    }

    // ─── hasRepeat ────────────────────────────────────────────────────

    @Test
    @DisplayName("hasRepeat: 3개 동일 문자 반복 시 true")
    void hasRepeat_threeChars_returnsTrue() {
        assertTrue(validator.hasRepeat("aaa"));
    }

    @Test
    @DisplayName("hasRepeat: 3개 동일 숫자 반복 시 true")
    void hasRepeat_threeDigits_returnsTrue() {
        assertTrue(validator.hasRepeat("111"));
    }

    @Test
    @DisplayName("hasRepeat: 2개 반복 후 다른 문자는 false")
    void hasRepeat_twoRepeat_returnsFalse() {
        assertFalse(validator.hasRepeat("aab"));
    }

    @Test
    @DisplayName("hasRepeat: 반복 없는 비밀번호는 false")
    void hasRepeat_noRepeat_returnsFalse() {
        assertFalse(validator.hasRepeat("abcd"));
    }

    @Test
    @DisplayName("hasRepeat: 중간에 반복 부분 포함 시 true")
    void hasRepeat_partialRepeat_returnsTrue() {
        assertTrue(validator.hasRepeat("xaaab"));
    }

    // ─── hasComb3 ─────────────────────────────────────────────────────

    @Test
    @DisplayName("hasComb3: 영문+숫자+특수문자 조합은 true")
    void hasComb3_allThree_returnsTrue() {
        assertTrue(validator.hasComb3("Abc1!"));
    }

    @Test
    @DisplayName("hasComb3: 영문+숫자만 2가지 조합은 false")
    void hasComb3_twoTypes_returnsFalse() {
        assertFalse(validator.hasComb3("Abc123"));
    }

    @Test
    @DisplayName("hasComb3: 영문만은 false")
    void hasComb3_alphaOnly_returnsFalse() {
        assertFalse(validator.hasComb3("abcdef"));
    }

    @Test
    @DisplayName("hasComb3: 숫자+특수문자만 2가지는 false")
    void hasComb3_numericAndSpecial_returnsFalse() {
        assertFalse(validator.hasComb3("123!@#"));
    }

    @Test
    @DisplayName("hasComb3: 빈 문자열은 false")
    void hasComb3_empty_returnsFalse() {
        assertFalse(validator.hasComb3(""));
    }

    @Test
    @DisplayName("hasComb3: 대소문자 혼합+숫자+특수문자 조합은 true")
    void hasComb3_mixedCaseAndAll_returnsTrue() {
        assertTrue(validator.hasComb3("Password1@"));
    }

}
