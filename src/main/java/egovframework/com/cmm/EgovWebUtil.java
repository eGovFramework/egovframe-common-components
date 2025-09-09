package egovframework.com.cmm;

import java.io.File;
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
 * </pre>
 */

public class EgovWebUtil {
	
	/**
	 * 문자열이 null이거나 빈 문자열인지 확인하는 유틸리티 메서드
	 * 
	 * @param value 검사할 문자열
	 * @return null이거나 빈 문자열이면 true, 그렇지 않으면 false
	 */
	private static boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
	
	public static String clearXSSMinimum(String value) {
		if (isEmpty(value)) {
			return "";
		}

		// 1단계: URL 인코딩된 문자 변환
		String step1 = value
			.replace("%2E", "&#46;")
			.replace("%2F", "&#47;");
		
		// 2단계: 일반 문자 변환 (순서 중요: &를 먼저 변환)
		return step1
			.replace("&", "&amp;")
			.replace("<", "&lt;")
			.replace(">", "&gt;")
			.replace("\"", "&#34;")
			.replace("'", "&#39;")
			.replace(".", "&#46;");
	}

	public static String clearXSSMaximum(String value) {
		if (isEmpty(value)) {
			return "";
		}

		// 1단계: 기본 XSS 방지 처리
		String step1 = clearXSSMinimum(value);
		
		// 2단계: 추가 보안 처리 (순서 중요)
		return step1
			.replace("%00", "")      // null 문자 제거
			.replace("%", "&#37;")   // % 문자 변환
			.replace("../", "")      // 상위 디렉토리 접근 방지
			.replace("..\\", "")     // 상위 디렉토리 접근 방지 (Windows)
			.replace("./", "");      // 현재 디렉토리 접근 방지
	}

	public static String clearXSS(String value) {
		if (isEmpty(value)) {
			return "";
		}

		// 1단계: URL 인코딩된 문자 변환
		String step1 = value
			.replace("%3C", "&lt;")
			.replace("%3E", "&gt;")
			.replace("%2E", "&#46;")
			.replace("%2F", "&#47;");
		
		// 2단계: 일반 문자 변환 (순서 중요: &를 먼저 변환)
		return step1
			.replace("&", "&amp;")
			.replace("<", "&lt;")
			.replace(">", "&gt;");
	}

	public static String filePathBlackList(String value) {
		if (isEmpty(value)) {
			return "";
		}

		return value
			.replace("../", "")
			.replace("..\\", "")
			.replace("..", "");
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
		if ( basePath == null || "".equals(basePath) )
			throw new SecurityException("base path is empty.");
		if ( File.separator.equals(basePath) || "/".equals(basePath) )
			throw new SecurityException("base path does not allow Root.");
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

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\\\", "");
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
		return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("\\|", "").replaceAll(";", "").replaceAll("&", "");
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
		returnValue = returnValue.replaceAll("\\*", "");
		returnValue = returnValue.replaceAll("&", "");
		returnValue = returnValue.replaceAll("|", "");
		returnValue = returnValue.replaceAll("//", "");
		//...
		//개별로 필요한 항목들 추가 필요

		return returnValue;
	}

}