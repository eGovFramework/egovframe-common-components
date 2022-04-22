package egovframework.com.cmm;

import java.util.regex.Pattern;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자           수정내용
 *  -----------  --------  ---------------------------
 *   2011.10.10  한성곤           최초 생성
 *	 2017-02-07   이정은           시큐어코딩(ES) - 시큐어코딩 경로 조작 및 자원 삽입[CWE-22, CWE-23, CWE-95, CWE-99]
 *   2018.08.17  신용호           filePathBlackList 수정
 *   2018.10.10  신용호           . => \\.으로 수정
 * </pre>
 */

public class EgovWebUtil {
	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}

		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		returnValue = returnValue.replaceAll("\\.", "&#46;");
		returnValue = returnValue.replaceAll("%2E", "&#46;");
		returnValue = returnValue.replaceAll("%2F", "&#47;");
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replaceAll("%00", null);

		returnValue = returnValue.replaceAll("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("\\./", ""); // ./
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\.", "");

		return returnValue;
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

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String fileInjectPathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\..", ""); // ..
		returnValue = returnValue.replaceAll("\\\\", "");// \
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String filePathWhiteList(String value) {
		return value;
	}

	public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

		return ipPattern.matcher(str).matches();
	}

	public static String removeCRLF(String parameter) {
		return parameter.replaceAll("\r", "").replaceAll("\n", "");
	}

	public static String removeSQLInjectionRisk(String parameter) {
		return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("%", "").replaceAll(";", "")
			.replaceAll("-", "").replaceAll("\\+", "").replaceAll(",", "");
	}

	public static String removeOSCmdRisk(String parameter) {
		return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("|", "").replaceAll(";", "");
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
		returnValue = returnValue.replaceAll("*", "");
		returnValue = returnValue.replaceAll("&", "");
		returnValue = returnValue.replaceAll("|", "");
		returnValue = returnValue.replaceAll("//", "");
		//...
		//개별로 필요한 항목들 추가 필요

		return returnValue;
	}

}