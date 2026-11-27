package egovframework.com.http;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * TestHttpTimeoutStatus2 Class 구현 ( Timeout 처리 테스트 )
 * @author 표준프레임워크 신용호
 * @since 2021.08.31
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2021.08.31  신용호          최초 생성
 *
 * </pre>
 */

@Slf4j
public class TestHttpTimeoutStatus2 {

	public static void main(String[] args) {
		log.debug("Start Chceck URL");
		String httpSttusCd = null;
		String siteUrl = "http://googlezzz.com:81";
		long start = System.currentTimeMillis();

		try {
			URL url = new URL(siteUrl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(1000);
			connection.getInputStream();
			httpSttusCd = "01";

		} catch (IOException e) {
			httpSttusCd = "02";
		}
		
		log.debug("실행 결과 : {}", httpSttusCd);
		
		long end = System.currentTimeMillis();
		log.debug("실행 시간 : {}초", ( end - start ) / 1000.0);
		log.debug("Finish Chceck URL");

	}

}
