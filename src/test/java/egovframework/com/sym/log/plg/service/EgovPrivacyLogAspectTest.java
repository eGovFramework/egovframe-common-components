package egovframework.com.sym.log.plg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * EgovPrivacyLogAspect 단위 테스트
 *
 * @author Chung10kr
 * @since 2026.06.20
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.06.20  Chung10kr   2025년 컨트리뷰션 keySet → entrySet 전환 테스트 작성 (@Nested 구조)
 *
 *      </pre>
 */
class EgovPrivacyLogAspectTest {

    /** 테스트 대상(SUT) */
    private EgovPrivacyLogAspect sut;

    /** 테스트용 target 맵: 필드키 → 레이블 */
    private Map<String, String> target;

    @BeforeEach
    void setUp() {
        sut = new EgovPrivacyLogAspect();

        // target: 개인정보 항목 키 → 항목명 매핑 (LinkedHashMap으로 순서 보장)
        target = new LinkedHashMap<>();
        target.put("name", "이름");
        target.put("email", "이메일");
        target.put("phone", "전화번호");

        sut.setTarget(target);
    }

    // ─────────────────────────────────────────────
    // 테스트용 VO (모든 중첩 클래스에서 공유)
    // ─────────────────────────────────────────────

    static class SampleVO {
        private final String name;
        private final String email;
        private final String phone;

        SampleVO(String name, String email, String phone) {
            this.name  = name;
            this.email = email;
            this.phone = phone;
        }

        public String getName()  { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
    }

    // ─────────────────────────────────────────────
    // getItemValues(Map<?, ?> data, String serviceName)
    // ─────────────────────────────────────────────

    @Nested
    @DisplayName("getItemValues(Map) - Map 형태의 데이터에서 target 설정에 해당하는 개인정보 항목명 목록을 반환한다")
    class GetItemValuesWithMap {

        @Test
        @DisplayName("정상_입력_모든_키_존재")
        void 정상_입력_모든_키_존재() {
            // given
            Map<String, Object> data = new HashMap<>();
            data.put("name",  "홍길동");
            data.put("email", "hong@example.com");
            data.put("phone", "010-1234-5678");

            // when
            List<String> result = sut.getItemValues(data, "TestService.method");

            // then
            assertNotNull(result);
            assertEquals(3, result.size());
            assertTrue(result.contains("이름"));
            assertTrue(result.contains("이메일"));
            assertTrue(result.contains("전화번호"));
        }

        @Test
        @DisplayName("일부_키만_존재")
        void 일부_키만_존재() {
            // given
            Map<String, Object> data = new HashMap<>();
            data.put("name", "홍길동");
            // email, phone 없음

            // when
            List<String> result = sut.getItemValues(data, "TestService.method");

            // then
            assertEquals(1, result.size());
            assertEquals("이름", result.get(0));
        }

        @Test
        @DisplayName("공백_값은_제외")
        void 공백_값은_제외() {
            // given
            Map<String, Object> data = new HashMap<>();
            data.put("name",  "  ");              // 공백만
            data.put("email", "hong@example.com");
            data.put("phone", "");                // 빈 문자열

            // when
            List<String> result = sut.getItemValues(data, "TestService.method");

            // then
            assertEquals(1, result.size());
            assertEquals("이메일", result.get(0));
        }

        @Test
        @DisplayName("null_값은_제외")
        void null_값은_제외() {
            // given
            Map<String, Object> data = new HashMap<>();
            data.put("name",  null);
            data.put("email", "hong@example.com");

            // when
            List<String> result = sut.getItemValues(data, "TestService.method");

            // then
            assertEquals(1, result.size());
            assertEquals("이메일", result.get(0));
        }

        @Test
        @DisplayName("data가_null이면_빈_리스트_반환")
        void data가_null이면_빈_리스트_반환() {
            // when
            List<String> result = sut.getItemValues((Map<?, ?>) null, "TestService.method");

            // then
            assertNotNull(result);
            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("빈_맵이면_빈_리스트_반환")
        void 빈_맵이면_빈_리스트_반환() {
            // given
            Map<String, Object> data = new HashMap<>();

            // when
            List<String> result = sut.getItemValues(data, "TestService.method");

            // then
            assertNotNull(result);
            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("target에_없는_키는_무시")
        void target에_없는_키는_무시() {
            // given
            Map<String, Object> data = new HashMap<>();
            data.put("address", "서울시");
            data.put("age",     "30");

            // when
            List<String> result = sut.getItemValues(data, "TestService.method");

            // then
            assertEquals(0, result.size());
        }
    }

    // ─────────────────────────────────────────────
    // getItemValues(Object data, String serviceName)
    // ─────────────────────────────────────────────

    @Nested
    @DisplayName("getItemValues(Object) - VO 객체의 getter를 리플렉션으로 호출하여 target 설정에 해당하는 개인정보 항목명 목록을 반환한다")
    class GetItemValuesWithVO {

        @Test
        @DisplayName("정상_입력")
        void 정상_입력() {
            // given
            SampleVO vo = new SampleVO("홍길동", "hong@example.com", "010-1234-5678");

            // when
            List<String> result = sut.getItemValues((Object) vo, "TestService.method");

            // then
            assertNotNull(result);
            assertEquals(3, result.size());
            assertTrue(result.contains("이름"));
            assertTrue(result.contains("이메일"));
            assertTrue(result.contains("전화번호"));
        }

        @Test
        @DisplayName("null_필드는_제외")
        void null_필드는_제외() {
            // given
            SampleVO vo = new SampleVO(null, "hong@example.com", null);

            // when
            List<String> result = sut.getItemValues((Object) vo, "TestService.method");

            // then
            assertEquals(1, result.size());
            assertEquals("이메일", result.get(0));
        }

        @Test
        @DisplayName("공백_필드는_제외")
        void 공백_필드는_제외() {
            // given
            SampleVO vo = new SampleVO("홍길동", "  ", "");

            // when
            List<String> result = sut.getItemValues((Object) vo, "TestService.method");

            // then
            assertEquals(1, result.size());
            assertEquals("이름", result.get(0));
        }

        @Test
        @DisplayName("data가_null이면_빈_리스트_반환")
        void data가_null이면_빈_리스트_반환() {
            // when
            List<String> result = sut.getItemValues((Object) null, "TestService.method");

            // then
            assertNotNull(result);
            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("getter_없는_필드는_건너뜀")
        void getter_없는_필드는_건너뜀() {
            // given: name getter만 있고 email, phone getter가 없는 익명 객체
            Object noGetterObj = new Object() {
                @SuppressWarnings("unused")
                public String getName() { return "홍길동"; }
            };

            // when
            List<String> result = sut.getItemValues(noGetterObj, "TestService.method");

            // then
            assertEquals(1, result.size());
            assertEquals("이름", result.get(0));
        }
    }

}
