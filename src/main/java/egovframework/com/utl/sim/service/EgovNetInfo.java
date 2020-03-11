/**
 *  Class Name : ComUtlSimNetInfo.java
 *  Description : 시스템 네트워크 정보를 확인하여 제공하는  Business class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.13    조재영          최초 생성
 *   2017.03.03         조성원 	      시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *
 *  @author 공통 서비스 개발팀 조재영
 *  @since 2009. 01. 13
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by EGOV  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

public class EgovNetInfo {
	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	/**
	 * <pre>
	 * Comment : 호스트 정보를 확인한다.
	 * </pre>
	 * @return String hostStr 호스트명을 리턴한다.(테스트용 함수)
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	public static String getHostName() throws Exception {
		// 실행할 명령을 프로퍼티 파일에서 확인한다.
		String command = EgovProperties.getPathProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".getHostName");

		// 출력할 결과 (파싱대상)
		String hostStr = "";
		String tmp = "";
		BufferedReader b_err = null;
		BufferedReader b_out = null;
		try {
			Process p = Runtime.getRuntime().exec(command);
			//프로세스가 처리될때까지 대기
			p.waitFor();

			//프로세스 에러시 종료
			if (p.exitValue() != 0) {
				b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while (b_err.ready()) {
				}
			}
			//프로세스 실행 성공시 결과 확인
			else {
				b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
				int i = 0;
				while (b_out.ready()) {
					//도스명령어 실행시 결과는 3번째 라인부터 출력됨..
					tmp = b_out.readLine();
					//2017.03.03 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
					if(tmp != null){
						if (tmp.length() <= MAX_STR_LEN) {
							if (i > 1) {
								hostStr += tmp + "\n";
							}
							i++;
						}
					}
				}
				// 시스템 로그 출력
			}
		} finally {
			EgovResourceCloseHelper.close(b_err, b_out);
		}
		return hostStr;
	}
}
