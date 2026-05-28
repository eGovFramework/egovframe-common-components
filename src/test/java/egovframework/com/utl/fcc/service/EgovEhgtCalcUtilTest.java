package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * EgovEhgtCalcUtil 단위 테스트
 *
 * EgovEhgtCalcUtil.getEhgtCalc()는 한국수출입은행 환율 API(네트워크)에 의존하므로
 * 해당 메서드는 통합 테스트 대상이다. 본 테스트는 클래스에 정의된 통화 코드 상수와
 * 클래스 로딩 가능 여부를 검증한다.
 *
 * @author 공통컴포넌트 개발팀
 * @since 2025.05.28
 */
public class EgovEhgtCalcUtilTest {

    @Test
    void 통화코드_미국_USD() {
        assertEquals('U', EgovEhgtCalcUtil.EGHT_USD);
    }

    @Test
    void 통화코드_일본_JPY() {
        assertEquals('J', EgovEhgtCalcUtil.EGHT_JPY);
    }

    @Test
    void 통화코드_유럽_EUR() {
        assertEquals('E', EgovEhgtCalcUtil.EGHT_EUR);
    }

    @Test
    void 통화코드_중국_CNY() {
        assertEquals('C', EgovEhgtCalcUtil.EGHT_CNY);
    }

    @Test
    void 통화코드_대한민국_KRW() {
        assertEquals('K', EgovEhgtCalcUtil.EGHT_KWR);
    }

    @Test
    void 통화코드_값_중복없음() {
        char[] codes = {
            EgovEhgtCalcUtil.EGHT_USD,
            EgovEhgtCalcUtil.EGHT_JPY,
            EgovEhgtCalcUtil.EGHT_EUR,
            EgovEhgtCalcUtil.EGHT_CNY,
            EgovEhgtCalcUtil.EGHT_KWR
        };
        long distinct = new String(codes).chars().distinct().count();
        assertEquals(5, distinct, "통화 코드는 모두 고유해야 한다");
    }

    @Test
    void getEhgtCalc_네트워크없이_예외발생_확인() {
        // 외부 API 호출 메서드는 네트워크 연결 없이 RuntimeException을 던진다
        assertThrows(Exception.class, () ->
            EgovEhgtCalcUtil.getEhgtCalc("KRW", 1000L, "USD")
        );
    }
}
