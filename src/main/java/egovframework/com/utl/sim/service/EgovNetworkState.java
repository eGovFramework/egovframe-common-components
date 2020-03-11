/**
 *  Class Name : EgovNetworkState.java
 *  Description : 네트워크(Network)상태 체크 Business Interface class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.02.02    이 용          최초 생성
 *
 *  @author 공통 서비스 개발팀 이 용
 *  @since 2009. 02. 02
 *  @version 1.0
 *  @see
 * The type com.sun.star.lang.XeventListener cannot be resolved. It is indirectly referenced from required .class files
 *  Copyright (C) 2009 by EGOV  All right reserved.
 */

package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

public class EgovNetworkState {
	public static String addrIP = "";
	static final char FILE_SEPARATOR = File.separatorChar;
	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	public static final int BUFF_SIZE = 2048;
	// Log
	// protected static final Log log = LogFactory.getLog(EgovNetworkState.class);

	/**
	 * <pre>
	 * Comment : Local MAC Address를 확인한다.
	 * </pre>
	 * @param String localIP  로컬 IP주소
	 * @return String mac        MAC Address를 리턴한다.
	 * @version 1.0 (2009.02.03.)
	 * @see
	 */
	public static String getMyMACAddress(String localIP) {
		//log.debug("getMyMACAddress Start!! : ");
		String mac = null;
		try {
			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				Process p = null;
				Runtime rt = Runtime.getRuntime();
				String[] execStr = { "nbtstat", "-A", localIP };
				p = rt.exec(execStr); // 여기다 아이피 주소 넣주세여

				InputStream in = p.getInputStream();
				String out = null;
				int c;
				while ((c = in.read()) != -1) {
					out = out + new String(new Character((char) c).toString());
				}
				in.close();
				if (out == null || out.indexOf("MAC Address = ") == -1) {
					throw new IllegalArgumentException("String Split Error!");
				}
				mac = out.substring(out.indexOf("MAC Address = ") + 14, out.indexOf("MAC Address = ") + 31);

			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				//log.debug("getMyMACAddress IP : " + localIP);
				mac = getNetWorkInfo("MAC");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return mac;
	}

	/**
	 * <pre>
	 * Comment : Local Port를 확인한다.
	 * </pre>

	 * @return String port       port를 리턴한다.
	 * @version 1.0 (2009.02.03.)
	 * @see
	 */
	public static List<String> getMyPortScan() {

		List<String> processes = new ArrayList<String>();
		BufferedReader input = null;
		
		try {

			Process p = null;
			Runtime rt = Runtime.getRuntime();
			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				String[] execStr = { "netstat", "-an" };
				p = rt.exec(execStr);
				input = new BufferedReader(new InputStreamReader(p.getInputStream()));

				while (true) {
					String str = input.readLine();
					if (str == null)
						break;
					if (str.length() >= MAX_STR_LEN) {
						throw new RuntimeException("input too long");
					}
					if (!str.trim().equals("")) {
						processes.add(str);
					}
				}
			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getNetWorkInfo");
				String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "SCAN" };
				p = Runtime.getRuntime().exec(command);
				p = rt.exec(command);

				input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while (true) {
					String str = input.readLine();
					if (str == null)
						break;
					if (str.length() >= MAX_STR_LEN) {
						throw new RuntimeException("input too long");
					}
					if (!str.trim().equals("")) {
						processes.add(str);
					}
				}
				//log.debug("getMyPortScan 6");
			}
		} catch (IOException e) {
			throw new RuntimeException("IO Exception", e);
		} finally {
			EgovResourceCloseHelper.close(input);
		}
		
		return processes;
	}

	/**
	 * <pre>
	 * Comment : Local IPAddress를 확인한다.
	 * </pre>
	 * @return String mac        Local IPAddress를 리턴한다.
	 * @version 1.0 (2009.02.03.)
	 * @see
	 */
	public static String getMyIPaddress() {
		try {

			if (!EgovWebUtil.isIPAddress(InetAddress.getLocalHost().getHostAddress())) {
				throw new RuntimeException("IP is needed. (" + InetAddress.getLocalHost().getHostAddress() + ")");
			}

			InetAddress InetA = InetAddress.getLocalHost();
			addrIP = InetA.getHostAddress();

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
		return addrIP;
	}

	/**
	 * <pre>
	 * Comment : 네트워크 상태체크를 확인한다.
	 * </pre>
	 * @param String localIP           localhost, gateway, host 주소
	 * @return boolean  status         true/false 를 리턴한다.
	 * @version 1.0 (2009.02.03.)
	 * @see
	 */
	public static boolean getPingTest(String requestIP) throws Exception {

		boolean status = false;

		if (!EgovWebUtil.isIPAddress(requestIP)) {
			throw new RuntimeException("IP is needed. (" + requestIP + ")");
		}

		status = InetAddress.getByName(requestIP).isReachable(3000);

		return status;
	}

	/**
	 * <pre>
	 * Comment : 네트워크(MAC,IP,S/M,G/W,DNS) 정보를 확인한다.
	 * </pre>
	 * @param String stringOne         확인할 네트웍 정보 표기 ( ex:"MAC","IP","S/M","G/W","DNS")
	 * @return String (MAC,IP,S/M,G/W,DNS) 정보를 리턴한다.
	 * @version 1.0 (2009.02.07.)
	 * @see
	 */
	public static String getNetWorkInfo(String stringOne) throws IOException {
		// 실행할 명령을 프로퍼티 파일에서 확인한다.
		Process p = null;
		
		BufferedReader b_out = null;

		String tmp = "";
		String outValue = "";
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getNetWorkInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), stringOne };
			p = Runtime.getRuntime().exec(command);
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (true) {
				tmp = b_out.readLine();
				if (tmp == null)
					break;
				if (tmp.length() >= MAX_STR_LEN) {
					throw new IllegalArgumentException("input too long");
				}
				// netstat -v ent0 | grep "하드웨어 주소"   -MAC
				// prtconf | grep "IP 주소"                 -IP
				// prtconf | grep "서브넷 마스크"           -SM
				// prtconf | grep "게이트웨이"              -GW
				if ("MAC".equals(stringOne)) {
					outValue = getCharFilter(tmp);
				} else if ("IP".equals(stringOne)) {
					outValue = getCharFilter(tmp);
				} else if ("SM".equals(stringOne)) {
					outValue = getCharFilter(tmp);
				} else if ("GW".equals(stringOne)) {
					outValue = getCharFilter(tmp);
				} else if ("DNS".equals(stringOne)) {
					//tmp = "was은(는) 192.168.200.21입니다";
					outValue = getCharFilter(tmp);
				} else {
					outValue = "데이타가 존재하지 않습니다.";
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}
		return outValue;
	}

	/**
	 * <pre>
	 * Comment : String 타입의 str값 중 숫자 정보만 필터링, 담아서 리턴.
	 * </pre>
	 * @param String str         필터링 대상 정보
	 * @return String outValue   숫자 정보를 필터링 리턴한다.
	 * @version 1.0 (2009.02.07.)
	 * @see
	 */
	private static String getCharFilter(String str) {
		String outValue = "";

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c > 45 && c < 59) {
				Character cr = new Character(c);
				outValue += cr.toString();
			}
		}
		return outValue;
	}
}