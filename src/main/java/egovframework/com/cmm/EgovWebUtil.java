package egovframework.com.cmm;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일         수정자     수정내용
 *  ----------   --------  ---------------------------
 *  2011.10.10   한성곤      최초 생성
 *	2017-02-07   이정은      시큐어코딩(ES) - 시큐어코딩 경로 조작 및 자원 삽입[CWE-22, CWE-23, CWE-95, CWE-99]
 *  2018.08.17   신용호      filePathBlackList 수정
 *  2018.10.10   신용호      . => \\.으로 수정
 *  2022.05.10   정진오      clearXSS() 메소드 추가
 *  2022.06.09   김장하      NSR 보안조치 (removeOSCmdRisk 함수에 윈도우 다중 명령 실행 키워드 추가)
 *  2023.08.10   신용호      removeLDAPInjectionRisk() 오류 수정
 *  2024.12.04   신용호      filePathBlackList() basePath 추가
 *  2026.07.10   유지보수    NCSC 보안점검 반영 (XSS/SSRF/세션고정 대응 유틸 추가)
 * </pre>
 */

public class EgovWebUtil {

	// 성능: 매 호출마다 Pattern.compile이 반복되지 않도록 정규식은 클래스 로딩 시 1회만 컴파일한다.
	// (정규식 의미가 없는 문자 그대로의 치환은 String.replace로 대체 — 패턴 컴파일 자체가 불필요)
	private static final Pattern SPACE_PATTERN = Pattern.compile("\\p{Space}");
	private static final Pattern DOT_ANY_CHAR_PATTERN = Pattern.compile("\\.."); // '.' + 임의 1문자 (기존 정규식 의미 그대로 보존)
	private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}

		String returnValue = value;

		returnValue = returnValue.replace("&", "&amp;");
		returnValue = returnValue.replace("<", "&lt;");
		returnValue = returnValue.replace(">", "&gt;");
		returnValue = returnValue.replace("\"", "&#34;");
		returnValue = returnValue.replace("'", "&#39;");
		returnValue = returnValue.replace(".", "&#46;");
		returnValue = returnValue.replace("%2E", "&#46;");
		returnValue = returnValue.replace("%2F", "&#47;");
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replace("%00", "");

		returnValue = returnValue.replace("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replace("../", "");
		returnValue = returnValue.replace("..\\", "");
		returnValue = returnValue.replace("./", "");
		returnValue = returnValue.replace("%2F", "");

		return returnValue;
	}

	public static String clearXSS(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}

		String returnValue = value;
		returnValue = returnValue.replace("&", "&amp;");
		returnValue = returnValue.replace("%2E", "&#46;");
		returnValue = returnValue.replace("%2F", "&#47;");
		returnValue = returnValue.replace("<", "&lt;");
		returnValue = returnValue.replace(">", "&gt;");
		returnValue = returnValue.replace("%3C", "&lt;");
		returnValue = returnValue.replace("%3E", "&gt;");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replace("..", "");

		return returnValue;
	}

	/**
	 * 파일경로 보안취약점 조치
	 * # 주의사항
	 * 1. basePath는 반드시 지정해야 한다.
	 * 2. basePath는 ROOT Path "/" 사용 금지 한다.
	 * 3. basePath 하위 디렉토리는 업로드한 파일이 존재하도록 구성하며 중요파일이 존재하지 않도록 관리한다.
	 *
	 * @param value 파일명
	 * @param basePath 기본 경로
	 * @return
	 */
	public static String filePathBlackList(String value, String basePath) {
		if ( basePath == null || "".equals(basePath) ) {
			throw new SecurityException("base path is empty.");
		}
		if ( File.separator.equals(basePath) || "/".equals(basePath) ) {
			throw new SecurityException("base path does not allow Root.");
		}
		return filePathBlackList(basePath + value);
	}

	/**
	 * 행안부 보안취약점 점검 조치 방안.
	 *
	 * @param value
	 * @return
	 */
	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replace("/", "");
		returnValue = returnValue.replace("\\", "");
		returnValue = returnValue.replace("..", "");
		returnValue = returnValue.replace("&", "");

		return returnValue;
	}

	public static String fileInjectPathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replace("/", "");
		returnValue = DOT_ANY_CHAR_PATTERN.matcher(returnValue).replaceAll(""); // '.' + 임의 1문자 (기존 의미 보존)
		returnValue = returnValue.replace("\\", "");
		returnValue = returnValue.replace("&", "");

		return returnValue;
	}

	public static boolean isIPAddress(String str) {
		return IP_PATTERN.matcher(str).matches();
	}

	/**
	 * 내부(사설/루프백/링크로컬) IP 여부 확인
	 */
	public static boolean isInternalAddress(InetAddress address) {
		return address.isLoopbackAddress()
				|| address.isSiteLocalAddress()
				|| address.isLinkLocalAddress()
				|| address.isAnyLocalAddress();
	}

	/**
	 * FTP/SSRF 방어를 위한 공개 IP 검증
	 */
	public static void validatePublicFtpHost(String serverIp) {
		if (!isIPAddress(serverIp)) {
			throw new IllegalArgumentException("Invalid IP address: " + serverIp);
		}
		try {
			InetAddress host = InetAddress.getByName(serverIp);
			if (isInternalAddress(host)) {
				throw new IllegalArgumentException("Internal IP access is not allowed: " + serverIp);
			}
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Unable to resolve host: " + serverIp, e);
		}
	}

	/**
	 * 팝업 requestUrl 상대경로 검증
	 */
	public static String sanitizeRelativeRequestUrl(String requestUrl) {
		if (requestUrl == null || requestUrl.isBlank()) {
			throw new IllegalArgumentException("requestUrl is required");
		}
		String trimmed = requestUrl.trim();
		if (!trimmed.startsWith("/") || trimmed.contains("://") || trimmed.contains("..")
				|| trimmed.contains("\"") || trimmed.contains("'") || trimmed.contains("<")
				|| trimmed.contains(">") || trimmed.contains("\r") || trimmed.contains("\n")) {
			throw new IllegalArgumentException("Invalid requestUrl");
		}
		return trimmed;
	}

	/**
	 * XML 특수문자 이스케이프
	 */
	public static String escapeXml(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		return value.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot;")
				.replace("'", "&apos;");
	}

	/**
	 * JavaScript 문자열 이스케이프
	 */
	public static String escapeJavaScript(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		StringBuilder sb = new StringBuilder(value.length() + 16);
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			switch (ch) {
				case '\\':
					sb.append("\\\\");
					break;
				case '\'':
					sb.append("\\'");
					break;
				case '"':
					sb.append("\\\"");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '<':
					sb.append("\\x3c");
					break;
				case '>':
					sb.append("\\x3e");
					break;
				case '&':
					sb.append("\\x26");
					break;
				default:
					if (ch < 32 || ch == '\u2028' || ch == '\u2029') {
						sb.append(String.format("\\u%04x", (int) ch));
					} else {
						sb.append(ch);
					}
			}
		}
		return sb.toString();
	}

	public static String removeCRLF(String parameter) {
		return parameter.replace("\r", "").replace("\n", "");
	}

	public static String removeSQLInjectionRisk(String parameter) {
		return SPACE_PATTERN.matcher(parameter).replaceAll("").replace("*", "").replace("%", "").replace(";", "")
			.replace("-", "").replace("+", "").replace(",", "");
	}

	public static String removeOSCmdRisk(String parameter) {
		return SPACE_PATTERN.matcher(parameter).replaceAll("").replace("*", "").replace("|", "").replace(";", "").replace("&", "");
	}

	/**
	 * LDAP 파라미터에서 특수문자 제거.
	 * 파라미터 별로 제거를 해야 함.
	 * 일괄 연결된 파라미터들은 따로 처리해야 함.
	 * TODO : LDAP Injection Prevent 로직 추가 필요
	 * @param value
	 * @return
	 */
	public static String removeLDAPInjectionRisk(String value) {

		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		/*모든 특수문자 제거*/
//		String match = "[^\uAC00-\uD7A30-9a-zA-Z]";//특수문자 = 한글,숫자,영문 제외
//		returnValue = returnValue.replaceAll(match, "");

		/*특수문자 선택적 제거*/
		returnValue = returnValue.replace("*", "");
		returnValue = returnValue.replace("&", "");
		returnValue = returnValue.replace("|", "");
		returnValue = returnValue.replace("//", "");
		//...
		//개별로 필요한 항목들 추가 필요

		return returnValue;
	}

}