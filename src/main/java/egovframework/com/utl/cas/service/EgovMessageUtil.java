/**
 * @Class Name  : EgovMessageUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.02.13       이삼섭                  최초 생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see
 *
 */

package egovframework.com.utl.cas.service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovStringUtil;

public class EgovMessageUtil {

	private static final String PATH_SEP = System.getProperty("file.separator");

	/**
	 * 해당되는 속성키로부터 에러 메시지를 얻는다.
	 *
	 * @param strCode
	 * @return
	 */
	public static String getErrorMsg(String strCode) {

		return getMessage("error", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 에러 메시지(파라미터 변환 포함)를 얻는다.
	 *
	 * @param strCode
	 * @param arrParam
	 * @return
	 */
	public static String getErrorMsg(String strCode, String[] arrParam) {

		return getMessage("error", strCode, arrParam);
	}

	/**
	 * 해당되는 속성키로부터 정보 메시지를 얻는다.
	 *
	 * @param strCode
	 * @return
	 */
	public static String getInfoMsg(String strCode) {

		return getMessage("info", strCode, null);
	}

	/**
	 *해당되는 속성키로부터 정보 메시지(파라미터 변환 포함)를 얻는다.
	 *
	 * @param strCode
	 * @param arrParam
	 * @return
	 */
	public static String getInfoMsg(String strCode, String[] arrParam) {

		return getMessage("info", strCode, arrParam);
	}

	/**
	 * 해당되는 속성키로부터 경고 메시지를 얻는다.
	 *
	 * @param strCode
	 * @return
	 */
	public static String getWarnMsg(String strCode) {

		return getMessage("warn", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 경고 메시지(파라미터 변환 포함)를 얻는다.
	 *
	 * @param strCode
	 * @param arrParam
	 * @return
	 */
	public static String getWarnMsg(String strCode, String[] arrParam) {

		return getMessage("warn", strCode, arrParam);
	}

	/**
	 * 해당되는 속성키로부터 확인 메시지를 얻는다.
	 *
	 * @param strCode
	 * @return
	 */
	public static String getConfirmMsg(String strCode) {

		return getMessage("confirm", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 확인 메시지(파라미터 변환 포함)를 얻는다.
	 *
	 * @param strCode
	 * @param arrParam
	 * @return
	 */
	public static String getConfirmMsg(String strCode, String[] arrParam) {

		return getMessage("confirm", strCode, arrParam);
	}
	/**
	 * 주어진 작업 코드, 문자열 코드, 그리고 파라미터 배열을 사용하여 메시지를 반환합니다.
	 * 문자열 코드를 사용하여 메시지 속성 파일에서 메시지를 가져옵니다.
	 * 파라미터 배열이 제공되면 해당 파라미터로 메시지를 교체합니다.
	 *
	 * @param wrkCode  작업을 지정하는 코드
	 * @param strCode  메시지 속성 파일에서 메시지를 찾는데 사용되는 코드
	 * @param arrParam 메시지 내의 파라미터를 교체하는데 사용되는 배열
	 * @return 교체된 메시지 또는 기본 메시지를 반환
	 */
	private static String getMessage(String wrkCode, String strCode, String[] arrParam) {

		String message = null;

		String strMsg = "";
		if (!"".equals(EgovStringUtil.isNullToString(strCode.trim()))) {

			strMsg = EgovProperties.getProperty(EgovProperties.RELATIVE_PATH_PREFIX + "egovProps" + PATH_SEP + "conf" + PATH_SEP + wrkCode + "message.properties", strCode);

			if(arrParam != null) {
				for (int i = (arrParam.length > 0 ? arrParam.length - 1 : -1); i >= 0; i--) {
					strMsg = EgovStringUtil.replace(EgovStringUtil.isNullToString(strMsg), "{" + i + "}", arrParam[i]);//KISA 보안약점 조치 (2018-10-29, 윤창원)
				}
			}
			message = strMsg;
		} else {
			message = "";
		}

		return message;
	}
}
