package egovframework.com.http;

import java.io.IOException;
import java.net.URL;

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
 *  2021.08.31  신용호          최초 생성
 *
 * </pre>
 */

public class TestHttpTimeoutStatus1 {

	public static void main(String[] args) {
		System.out.println("Start Chceck URL");
		String httpSttusCd = null;
		String siteUrl = "http://googlezzz.com:81";
		long start = System.currentTimeMillis();

		System.out.println("sun.net.client.defaultConnectTimeout = "+System.getProperty("sun.net.client.defaultConnectTimeout"));
		System.out.println("sun.net.client.defaultReadTimeout = "+System.getProperty("sun.net.client.defaultReadTimeout"));
		
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		try {
			URL url = new URL(siteUrl);
			url.openStream();

			httpSttusCd = "01";

		} catch (IOException e) {
			httpSttusCd = "02";
		}

		System.out.println("실행 결과 : " + httpSttusCd );

		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + ( end - start ) / 1000.0 + "초" );
		System.out.println("Finish Chceck URL");

	}

}
