package egovframework.com.utl.sim.service;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * EgovClntInfo 단위 테스트
 *
 * <p>수정 전 현재 동작을 검증하는 테스트입니다.
 * getClntIP() 의 X-Forwarded-For 다중 IP 미분리 버그를 포함하여
 * 현재 동작 기준으로 작성되었습니다.</p>
 *
 * <p>MockHttpServletRequest(spring-test) 는 Servlet 6.0 이상을 요구하므로,
 * Servlet 5.0 환경과 호환되는 JDK Proxy 기반 스텁을 사용합니다.</p>
 *
 * @see EgovClntInfo
 */
@DisplayName("EgovClntInfo 테스트")
class EgovClntInfoTest {

    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        // 기본: 헤더 없음, remoteAddr = "127.0.0.1"
        request = stubRequest("127.0.0.1");
        // getClntIP() 내부에서 RequestContextHolder 를 통해 req 를 획득하므로
        // 동일한 스텁을 RequestContextHolder 에 바인딩
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterEach
    void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    // -----------------------------------------------------------------------
    // 헬퍼: JDK Proxy 기반 HttpServletRequest 스텁 (Servlet 5.0 호환)
    // getHeader(name) 과 getRemoteAddr() 만 구현하고 나머지는 null 반환
    // -----------------------------------------------------------------------
    private static HttpServletRequest stubRequest(String remoteAddr, String... headerPairs) {
        Map<String, String> headers = new LinkedHashMap<>();
        for (int i = 0; i + 1 < headerPairs.length; i += 2) {
            headers.put(headerPairs[i], headerPairs[i + 1]);
        }
        return (HttpServletRequest) Proxy.newProxyInstance(
            Thread.currentThread().getContextClassLoader(),
            new Class[]{HttpServletRequest.class},
            (proxy, method, args) -> {
                switch (method.getName()) {
                    case "getHeader":
                        return headers.get(args[0]);
                    case "getRemoteAddr":
                        return remoteAddr;
                    default:
                        return null;
                }
            }
        );
    }

    // -----------------------------------------------------------------------

    @Nested
    @DisplayName("getClntIP() —")
    class GetClntIP {

        @Nested
        @DisplayName("X-Forwarded-For 헤더 처리 —")
        class XForwardedFor {

            @Test
            @DisplayName("단일 IP인 경우 해당 IP 문자열을 그대로 반환한다")
            void 단일IP_반환() throws Exception {
                // given
                request = stubRequest("127.0.0.1", "X-Forwarded-For", "192.168.1.1");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("192.168.1.1", result);
            }

            @Test
            @DisplayName("콤마 구분 다중 IP인 경우 첫 번째 IP만 반환한다")
            void 다중IP_첫번째IP_반환() throws Exception {
                // given
                // 프록시 경유 시 X-Forwarded-For = "클라이언트IP, 프록시1, 프록시2" 형태
                request = stubRequest("127.0.0.1", "X-Forwarded-For", "192.168.1.1, 10.0.0.1, 172.16.0.1");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                // 수정 후: 첫 번째 IP만 추출
                assertEquals("192.168.1.1", result);
            }

            @Test
            @DisplayName("콤마 구분 다중 IP (공백 없음) 인 경우 첫 번째 IP만 반환한다")
            void 다중IP_공백없음_첫번째IP_반환() throws Exception {
                // given
                request = stubRequest("127.0.0.1", "X-Forwarded-For", "1.2.3.4,5.6.7.8");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("1.2.3.4", result);
            }

            @Test
            @DisplayName("헤더값이 'unknown'인 경우 다음 헤더로 폴백한다")
            void unknown값_폴백() throws Exception {
                // given
                request = stubRequest("127.0.0.1",
                    "X-Forwarded-For", "unknown",
                    "Proxy-Client-IP", "10.0.0.5"
                );
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("10.0.0.5", result);
            }

            @Test
            @DisplayName("헤더가 없는 경우 다음 헤더 체인으로 폴백한다")
            void 헤더없음_폴백() throws Exception {
                // given: X-Forwarded-For 헤더 미설정, Proxy-Client-IP 만 설정
                request = stubRequest("127.0.0.1", "Proxy-Client-IP", "10.0.0.9");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("10.0.0.9", result);
            }
        }

        @Nested
        @DisplayName("프록시 헤더 폴백 체인 —")
        class ProxyHeaderFallback {

            @Test
            @DisplayName("Proxy-Client-IP 헤더가 있으면 해당 IP를 반환한다")
            void proxyClientIP_반환() throws Exception {
                // given: X-Forwarded-For 없음, Proxy-Client-IP 설정
                request = stubRequest("127.0.0.1", "Proxy-Client-IP", "10.10.10.10");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("10.10.10.10", result);
            }

            @Test
            @DisplayName("Proxy-Client-IP가 unknown이면 WL-Proxy-Client-IP로 폴백한다")
            void wlProxyClientIP_폴백() throws Exception {
                // given
                request = stubRequest("127.0.0.1",
                    "Proxy-Client-IP", "unknown",
                    "WL-Proxy-Client-IP", "10.20.30.40"
                );
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("10.20.30.40", result);
            }

            @Test
            @DisplayName("WL-Proxy-Client-IP가 없으면 HTTP_CLIENT_IP로 폴백한다")
            void httpClientIP_폴백() throws Exception {
                // given: X-Forwarded-For, Proxy-Client-IP, WL-Proxy-Client-IP 없음
                request = stubRequest("127.0.0.1", "HTTP_CLIENT_IP", "172.16.0.100");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("172.16.0.100", result);
            }

            @Test
            @DisplayName("HTTP_X_FORWARDED_FOR 헤더로 폴백한다")
            void httpXForwardedFor_폴백() throws Exception {
                // given
                request = stubRequest("127.0.0.1", "HTTP_X_FORWARDED_FOR", "172.16.0.200");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("172.16.0.200", result);
            }

            @Test
            @DisplayName("X-Real-IP 헤더로 폴백한다")
            void xRealIP_폴백() throws Exception {
                // given
                request = stubRequest("127.0.0.1", "X-Real-IP", "203.0.113.1");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("203.0.113.1", result);
            }

            @Test
            @DisplayName("X-RealIP 헤더로 폴백한다")
            void xRealIP_하이픈없이_폴백() throws Exception {
                // given
                request = stubRequest("127.0.0.1", "X-RealIP", "203.0.113.2");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("203.0.113.2", result);
            }

            @Test
            @DisplayName("REMOTE_ADDR 헤더로 폴백한다")
            void remoteAddr헤더_폴백() throws Exception {
                // given
                request = stubRequest("127.0.0.1", "REMOTE_ADDR", "10.0.1.1");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("10.0.1.1", result);
            }
        }

        @Nested
        @DisplayName("getRemoteAddr() 최종 폴백 —")
        class RemoteAddrFallback {

            @Test
            @DisplayName("모든 헤더가 없는 경우 getRemoteAddr()를 반환한다")
            void 모든헤더_없음_remoteAddr_반환() throws Exception {
                // given: 어떤 헤더도 설정하지 않고 remoteAddr 만 설정
                request = stubRequest("127.0.0.1");
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("127.0.0.1", result);
            }

            @Test
            @DisplayName("모든 헤더가 unknown인 경우 getRemoteAddr()를 반환한다")
            void 모든헤더_unknown_remoteAddr_반환() throws Exception {
                // given: 모든 헤더를 unknown으로 설정
                request = stubRequest("192.0.2.1",
                    "X-Forwarded-For",       "unknown",
                    "Proxy-Client-IP",       "unknown",
                    "WL-Proxy-Client-IP",    "unknown",
                    "HTTP_CLIENT_IP",        "unknown",
                    "HTTP_X_FORWARDED_FOR",  "unknown",
                    "X-Real-IP",             "unknown",
                    "X-RealIP",              "unknown",
                    "REMOTE_ADDR",           "unknown"
                );
                RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                // when
                String result = EgovClntInfo.getClntIP(request);
                // then
                assertEquals("192.0.2.1", result);
            }
        }
    }
}
