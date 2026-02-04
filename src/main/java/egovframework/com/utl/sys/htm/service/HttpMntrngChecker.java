package egovframework.com.utl.sys.htm.service;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import egovframework.com.cmm.EgovWebUtil;

/**
 * @Class Name : HttpMntrngChecker.java
 * @Description : HTTP서비스모니터링을 위한 Check 클래스
 * @Modification Information
 *
 *    수정일         수정자      수정내용
 *    ----------   -------   -------------------
 *    2010.09.06   박종선      최초생성
 *    2019.12.05   신용호      KISA 보안약점 조치 (경로조작및 자원 삽입,부적절한 예외처리)
 *    2026.01.23   신용호      KISA 보안약점 조치 (SSRF 공격 취약점)
 *
 * @author  박종선
 * @since 2010.05.01
 * @version
 * @see
 *
 */

/*

* SSRF(Server-Side Request Forgery)란?
- SSRF는 "서버가 공격자를 대신해서 요청을 보내게 만드는 취약점"이다

* 중요 : 사용자 화면에서 URL을 전달받아 호출하는 기능을 구현하지 않는다.
* 본 기능은 관리자 페이지에서 HTTP 모니터링 기능을 위한 예시이다.
 
*/

public class HttpMntrngChecker {

	//@Resource(name = "HttpMonService")
	//private EgovHttpMonService httpMonService;

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 100;

	// 연결 타임아웃 (밀리초)
	private static final int CONNECTION_TIMEOUT = 5000;  // 5초

	// 읽기 타임아웃 (밀리초)
	private static final int READ_TIMEOUT = 10000;       // 10초

	// 화이트 리스트 (호스트명만 저장)
	static private String[] whiteListHost = { "wwww.egovwebserver.go.kr"
												,"wwww.egovwasserver.go.kr"
												,"192.168.100.50" };

	/**
	 * 내부 IP 주소 여부를 확인하는 기능 (SSRF 방어, IPv4/IPv6 모두 지원)
	 * @param address 검사할 InetAddress 객체
	 * @return boolean 내부 IP 여부 (true: 내부 IP, false: 외부 IP)
	 */
	private static boolean isInternalAddress(InetAddress address) {
		return address.isLoopbackAddress() ||      // 127.x.x.x, ::1
			   address.isSiteLocalAddress() ||     // 10.x, 172.16-31.x, 192.168.x, fd00::/8
			   address.isLinkLocalAddress() ||     // 169.254.x.x, fe80::/10
			   address.isAnyLocalAddress();        // 0.0.0.0, ::
	}

	/**
	 * URL 유효성 검증 및 파싱 (SSRF 방어)
	 * @param siteUrl 검증할 사이트 URL
	 * @return URL 검증된 URL 객체
	 * @exception IOException 유효하지 않은 URL인 경우
	 */
	private static URL validateAndParseUrl(String siteUrl) throws IOException {
		// 0. Null 체크 및 길이 검증
		if (siteUrl == null || siteUrl.isEmpty()) {
			throw new IOException("URL is required");
		}
		if (siteUrl.length() > MAX_STR_LEN) {
			throw new IOException("URL length exceeds maximum allowed length");
		}

		// 1. URL 파싱 (잘못된 URL 형식 검증)
		siteUrl = EgovWebUtil.filePathBlackList(siteUrl);
		URL parsedUrl = new URL(siteUrl);

		// 2. 프로토콜 검증 (http/https만 허용)
		String protocol = parsedUrl.getProtocol();
		if (!"http".equals(protocol) && !"https".equals(protocol)) {
			throw new IOException("Only HTTP/HTTPS protocols are allowed");
		}

		// 3. 호스트 추출 및 화이트리스트 검증 (정확 일치)
		String host = parsedUrl.getHost();
		if (host == null || host.isEmpty()) {
			throw new IOException("Invalid host in URL");
		}

		boolean isAuth = false;
		for (String allowedHost : whiteListHost) {
			if (host.equalsIgnoreCase(allowedHost)) {
				isAuth = true;
				break;
			}
		}

		if (!isAuth) {
			throw new IOException("UnRegistered site URL");
		}

		return parsedUrl;
	}

	/**
	 * 호스트명을 IP로 resolve하고 내부 IP 여부를 검증 (DNS Rebinding 방어)
	 * @param host 검증할 호스트명
	 * @return InetAddress resolve된 IP 주소
	 * @exception IOException 내부 IP이거나 resolve 실패시
	 */
	private static InetAddress resolveAndValidateHost(String host) throws IOException {
		try {
			InetAddress resolvedAddress = InetAddress.getByName(host);

			// 내부 IP 차단 (화이트리스트에 등록된 내부 IP는 허용)
			if (isInternalAddress(resolvedAddress) && !isWhiteListedInternalIP(host)) {
				throw new IOException("Internal IP access is not allowed");
			}

			return resolvedAddress;
		} catch (UnknownHostException e) {
			throw new IOException("Unable to resolve host");
		}
	}

	/**
	 * 시스템에 존재하는 서버의 실행상태 정보를 조회하는 기능
	 * @param String siteUrl 사이트URL
	 * @return String status 실행상태 (01: 정상, 02: 비정상)
	 * @exception IOException
	 */
	public static String getPrductStatus(String siteUrl) throws IOException {

		// 1. URL 검증 및 파싱
		URL validatedUrl = validateAndParseUrl(siteUrl);

		// 2. DNS Rebinding 방어를 위한 호스트 검증
		resolveAndValidateHost(validatedUrl.getHost());

		// 3. 안전한 연결 수행
		return executeSecureConnection(validatedUrl);
	}

	/**
	 * 안전한 HTTP 연결 수행 (타임아웃, 리다이렉트 제한, 리소스 관리)
	 * @param url 연결할 URL
	 * @return String 연결 상태 (01: 정상, 02: 비정상)
	 */
	private static String executeSecureConnection(URL url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();

			// 타임아웃 설정 (DoS 방어)
			conn.setConnectTimeout(CONNECTION_TIMEOUT);
			conn.setReadTimeout(READ_TIMEOUT);

			// 리다이렉트 비활성화 (화이트리스트 우회 방지)
			conn.setInstanceFollowRedirects(false);

			// HEAD 요청으로 변경 (불필요한 데이터 전송 방지)
			conn.setRequestMethod("HEAD");

			int responseCode = conn.getResponseCode();

			// 2xx, 3xx 응답은 서버가 살아있는 것으로 판단
			return (responseCode >= 200 && responseCode < 400) ? "01" : "02";

		} catch (IOException e) {
			return "02";
		} finally {
			// 리소스 정리
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * 화이트리스트에 등록된 내부 IP인지 확인
	 * @param host 확인할 호스트
	 * @return boolean 화이트리스트에 등록된 내부 IP 여부
	 */
	private static boolean isWhiteListedInternalIP(String host) {
		for (String allowedHost : whiteListHost) {
			if (host.equalsIgnoreCase(allowedHost)) {
				return true;
			}
		}
		return false;
	}

}
