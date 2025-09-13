package egovframework.com.utl.sys.htm.service;

import java.io.IOException;
import java.net.URL;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.Globals;

/**
 * @Class Name : HttpMntrngChecker.java
 * @Description : HTTP서비스모니터링을 위한 Check 클래스
 * @Modification Information
 * 
 *               <pre>
 *    수정일                수정자         수정내용
 *    ----------   -------   -------------------
 *    2010.09.06   박종선          최초생성
 *    2019.12.05   신용호          KISA 보안약점 조치 (경로조작및 자원 삽입,부적절한 예외처리)
 *               </pre>
 * 
 * @author 박종선
 * @since 2010.05.01
 * @version
 * @see
 *
 */

public class HttpMntrngChecker {

	/**
	 * 시스템에 존재하는 서버의 실행상태 정보를 조회하는 기능
	 * 
	 * @param String sitUrl 사용포트
	 * @return String status 실행상태
	 * @exception Exception
	 */
	public static String getPrductStatus(String siteUrl) throws IOException {

		boolean isAuth = false;

		for (String urlPattern : Globals.HTTP_MNTRNG_CHECKER_WHITE_LIST_URL) {
			if (siteUrl.contains(urlPattern)) {
				isAuth = true;
				break;
			}
		}
		if (!isAuth) {
			throw new IOException("UnRegistered site URL : " + siteUrl);
		}

		String httpSttusCd = null;

		try {
			URL url = new URL(EgovWebUtil.filePathBlackList(siteUrl));
			url.openStream();

			httpSttusCd = "01";

		} catch (IOException e) {
			httpSttusCd = "02";
		}

		return httpSttusCd;
	}

}
