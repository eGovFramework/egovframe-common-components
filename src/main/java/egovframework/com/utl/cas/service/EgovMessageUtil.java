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

	private static String getMessage(String wrkCode, String strCode, String[] arrParam) {

		String message = null;

		String strMsg = "";
		if (!"".equals(EgovStringUtil.isNullToString(strCode.trim()))) {

			strMsg = EgovProperties.getProperty(EgovProperties.RELATIVE_PATH_PREFIX + "egovProps" + PATH_SEP + "conf" + PATH_SEP + wrkCode + "message.properties", strCode);

			for (int i = (arrParam != null && arrParam.length > 0 ? arrParam.length - 1 : -1); i >= 0; i--) {
				strMsg = EgovStringUtil.replace(EgovStringUtil.isNullToString(strMsg), "{" + i + "}", arrParam[i]);//KISA 보안약점 조치 (2018-10-29, 윤창원)
			}
			message = strMsg;
		} else {
			message = "";
		}

		return message;
	}
}
