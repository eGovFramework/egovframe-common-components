package egovframework.com.utl.sys.prm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.FileSystemUtils;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

/**
 * 개요
 * - 프로세스 모니터링을 위한 Check 클래스
 *
 * 상세내용
 * - 프로세스의 상태 결과를 제공한다.
 * @author 박종선
 * @version 1.0
 * @created 7-9-2010
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2019.12.06   신용호             KISA 보안약점 조치 (부적절한 예외처리)
 *  2022.11.11   김혜준             시큐어코딩 처리
 *
 * </pre>
 * 
 */

public class ProcessMonChecker {

	// Log
	//private static final Logger LOGGER = LoggerFactory.getLogger(ProcessMonChecker.class);

	/**
	 * <pre>
	 * Comment : 프로세스 정보를 확인한다. (
	 * </pre>
	 * @param String processName
	 * @return List<String[]> 프로세스 정보를 리턴한다.
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	public static String getProcessId(String processNm) throws IOException {

		Process p = null;
		String procsSttus = null;
		BufferedReader buf = null;
		int cnt = 0;

		try {
			if (Globals.OS_TYPE == null) {
				throw new RuntimeException("Globals.OS_TYPE property value is needed!!!");
			}
			// 2011.10.10 보안점검 후속조치 끝

			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				cnt = -1; //윈도우의 경우 정상 프로세스 일때 두번째 줄에 결과를 리턴한다.
				String execStr = "tasklist /fo table /nh /fi \"imagename eq " + processNm + "\"";
				// 2022.11.11 시큐어코딩 처리
				FileSystemUtils util = new FileSystemUtils();
				p = util.processOperate("EgovNetworkState", execStr);

			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				String cmd = "/bin/csh" + "-c" + "ps -A | grep " + EgovWebUtil.removeOSCmdRisk(processNm); 
				// 2022.11.11 시큐어코딩 처리
				FileSystemUtils util = new FileSystemUtils();
				p = util.processOperate("EgovNetworkState", cmd);
			}

			if (p == null) {
				return "02";
			}
			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((buf.readLine()) != null) {
				cnt++;
			}
			if (cnt > 0) {
				procsSttus = "01";
			} else {
				procsSttus = "02";
			}

		} catch (IOException e) {
			procsSttus = "02";
		} finally {
			EgovResourceCloseHelper.close(buf);
		}

		return procsSttus;
	}

}
