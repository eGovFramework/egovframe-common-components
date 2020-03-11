package egovframework.com.utl.sys.htm.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import egovframework.com.cmm.EgovWebUtil;

/**
 * @Class Name : HttpMntrngChecker.java
 * @Description : HTTP서비스모니터링을 위한 Check 클래스
 * @Modification Information
 *
 *    수정일                수정자         수정내용
 *    ----------   -------   -------------------
 *    2010.09.06   박종선          최초생성
 *    2019.12.05   신용호          KISA 보안약점 조치 (경로조작및 자원 삽입,부적절한 예외처리)
 *
 * @author  박종선
 * @since 2010.05.01
 * @version
 * @see
 *
 */

public class HttpMntrngChecker {

	//@Resource(name = "HttpMonService")
	//private EgovHttpMonService httpMonService;	

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	/**
	 * 시스템에 존재하는 서버의 실행상태 정보를 조회하는 기능
	 * @param String sitUrl 사용포트
	 * @return String status 실행상태
	 * @exception Exception
	*/
	public static String getPrductStatus(String siteUrl) throws IOException {

		String httpSttusCd = null;

		try {
			siteUrl = EgovWebUtil.filePathBlackList(siteUrl);
			URL url = new URL(siteUrl);
			url.openStream();

			httpSttusCd = "01";

		} catch (IOException e) {
			httpSttusCd = "02";
		}

		return httpSttusCd;
	}

}
