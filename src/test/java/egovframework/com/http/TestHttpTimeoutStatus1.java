package egovframework.com.http;

import java.io.IOException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

/**
 * TestHttpTimeoutStatus1 Class 구현 ( Timeout 처리 테스트 )
 * @author 표준프레임워크 신용호
 * @since 2021.08.31
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *   2021.08.31  신용호          최초 생성
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * </pre>
 */
@Slf4j
public class TestHttpTimeoutStatus1 {

	public static void main(String[] args) {
		log.debug("Start Chceck URL");
		String httpSttusCd = null;
		String siteUrl = "http://googlezzz.com:81";
		long start = System.currentTimeMillis();

		log.debug("sun.net.client.defaultConnectTimeout = {}", System.getProperty("sun.net.client.defaultConnectTimeout"));
		log.debug("sun.net.client.defaultReadTimeout = {}", System.getProperty("sun.net.client.defaultReadTimeout"));
		
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		try {
			URL url = new URL(siteUrl);
			url.openStream();

			httpSttusCd = "01";

		} catch (IOException e) {
			httpSttusCd = "02";
		}

		log.debug("실행 결과 : {}", httpSttusCd );

		long end = System.currentTimeMillis();
		log.debug("실행 시간 : {}초", ( end - start ) / 1000.0);
		log.debug("Finish Chceck URL");

	}

}
