package egovframework.com.utl.sys.htm.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * HTTP서비스모니터링을 위한 Check 클래스 단위 테스트
 * 
 * @author 공통컴포넌트 개발팀 홍길동
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.09.13  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j
public class HttpMntrngCheckerTest {

	@Test
	public void test() {
		// given
//		String siteUrl = "http://wwww.egovwebserver.go.kr";
//		String siteUrl = "https://wwww.egovwebserver.go.kr";
//		String siteUrl = "http://wwww.egovwasserver.go.kr";
//		String siteUrl = "https://wwww.egovwasserver.go.kr";
//		String siteUrl = "http://192.168.100.133";
//		String siteUrl = "http://www.egovframe.go.kr";
//		String siteUrl = "https://www.egovframe.go.kr";
//		String siteUrl = "http://www.god.go.kr";
		String siteUrl = "https://www.null.go.kr";

		// when
		String httpSttusCd = null;
		try {
			httpSttusCd = HttpMntrngChecker.getPrductStatus(siteUrl);
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException", e);
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("httpSttusCd={}", httpSttusCd);
		}

		// then
		assertEquals(null, httpSttusCd);
	}

	@Test
	public void test2() {
		// given
		// String siteUrl = "http://211.188.83.139";
		// String siteUrl = "https://211.188.83.139";
		// String siteUrl = "http://www.egovframe.go.kr";
		String siteUrl = "https://www.egovframe.go.kr";

		// when
		String httpSttusCd = null;
		try {
			httpSttusCd = HttpMntrngChecker.getPrductStatus(siteUrl);
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException", e);
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("httpSttusCd={}", httpSttusCd);
		}

		// then
		assertEquals("01", httpSttusCd);
	}

	@Test
	public void test3() {
		// given
		String siteUrl = "https://wwww.egovwebserver.go.kr";

		// when
		String httpSttusCd = null;
		try {
			httpSttusCd = HttpMntrngChecker.getPrductStatus(siteUrl);
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException", e);
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("httpSttusCd={}", httpSttusCd);
		}

		// then
		assertEquals("02", httpSttusCd);
	}

}
