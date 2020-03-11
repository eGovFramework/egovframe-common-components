/**
 *  Class Name : EgovSysInfo.java
 *  Description : 시스템정보를 조회하는 Business Interface class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.02.02    박지욱          최초 생성
 *   2017.03.03          조성원 	      시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 02. 11
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * @Class Name : EgovSysInfo.java
 * @Description : 
 * @Modification Information
 *
 *    수정일                수정자          수정내용
 *    ----------   -------   -------------------
 *    2019.12.06   신용호          KISA 보안약점 조치 (부적절한 예외처리)
 *
 * @author  박종선
 * @since 2010.05.01
 * @version
 * @see
 *
 */

public class EgovSysInfo {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	// Log
	//protected static final Log log = LogFactory.getLog(EgovSysInfo.class);

	/**
	 * 시스템에 존재하는 서버목록을 조회하는 기능
	 * @return Vector server_list 서버목록
	 * @exception Exception
	*/
	public static Vector<Map<String, String>> getPrductList() throws Exception {

		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		String strlist = EgovStringUtil.isNullToString(EgovProperties.getProperty(Globals.SERVER_CONF_PATH, "SERVER_LIST"));
		String[] list = strlist.split("\\$");

		Vector<Map<String, String>> serverList = new Vector<Map<String, String>>();

		for (int i = 0; i < list.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("name", list[i]);
			//KISA 보안약점 조치 (2018-10-29, 윤창원)
			map.put("version", EgovStringUtil.isNullToString(getPrductVersion(list[i])));
			map.put("port", EgovStringUtil.isNullToString(getPrductPort(list[i])));
			map.put("status", getPrductStatus(EgovStringUtil.isNullToString(getPrductPort(list[i]))));
			
			serverList.add(map);
		}

		return serverList;
	}

	/**
	 * 시스템에 존재하는 서버의 제품명, 버전정보를 조회하는 기능
	 * @param String server 서버명
	 * @return String version 버전
	 * @exception Exception
	*/
	public static String getPrductVersion(String server) throws Exception {

		String version = EgovProperties.getProperty(Globals.SERVER_CONF_PATH, server.toUpperCase() + ".VERSION");
		
		return version;
	}

	/**
	 * 시스템에 존재하는 서버의 포트 정보를 조회하는 기능
	 * @param String server 서버명
	 * @return String port 포트
	 * @exception Exception
	*/
	public static String getPrductPort(String server) throws Exception {

		String port = EgovProperties.getProperty(Globals.SERVER_CONF_PATH, server.toUpperCase() + ".PORT");
		
		return port;
	}

	/**
	 * 시스템에 존재하는 서버의 실행상태 정보를 조회하는 기능
	 * @param String port 사용포트
	 * @return String status 실행상태
	 * @exception Exception
	*/
	public static String getPrductStatus(String port) throws Exception {

		String status = "CLOSE";
		Process p = null;
		
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getPrductStatus");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), port };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && Integer.parseInt(str) > 0 && str.length() <= MAX_STR_LEN) {
						status = "LISTENING";
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf(port) != -1 && str.indexOf("LISTENING") != -1 && str.length() <= MAX_STR_LEN) {
						status = "LISTENING";
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return status;
	}

	/**
	 * 시스템의 호스트명을 조회하는 기능
	 * @return String hostName 호스트명
	 * @exception Exception
	*/
	public static String getHostName() throws Exception {

		// 호스트명 결과
		String hostName = "";
		Process p = null;
		
		BufferedReader b_out = null;
		try {
			String[] command = { "hostname" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (true) {
				String str = b_out.readLine();
				if (str == null)
					break;
				if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
					hostName = str;
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return hostName;
	}

	/**
	 * 시스템의 OS 이름을 조회하는 기능
	 * @return String osname OS이름
	 * @exception Exception
	*/
	public static String getOSName() throws Exception {

		// OS 이름
		String osname = "";
		Process p = null;
		BufferedReader b_out = null;
		
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "NAME" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						osname = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("OS 이름:") != -1 && str.length() <= MAX_STR_LEN) {
						osname = str.replaceAll("OS 이름:", "");
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return osname;
	}

	/**
	 * 시스템의 OS 버전을 조회하는 기능
	 * @return String osversion OS버전
	 * @exception Exception
	*/
	public static String getOSVersion() throws Exception {

		// OS 버전
		String osversion = "";
		Process p = null;
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "VERSION" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						osversion = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("OS 버전:") != -1 && str.length() <= MAX_STR_LEN) {
						osversion = str.replaceAll("OS 버전:", "");
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return osversion;
	}

	/**
	 * 시스템의 OS 제조사를 조회하는 기능
	 * @return String osprductor OS제조사
	 * @exception Exception
	*/
	public static String getOSPrductor() throws Exception {

		// OS 제조사
		String osprductor = "";
		Process p = null;
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "PRDUCTOR" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						osprductor = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("OS 제조업체:") != -1 && str.length() <= MAX_STR_LEN) {
						osprductor = str.replaceAll("OS 제조업체:", "");
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return osprductor;
	}

	/**
	 * 시스템의 Processor ID를 조회하는 기능
	 * @return String processor 프로세서ID
	 * @exception Exception
	*/
	public static String getProcessorID() throws Exception {

		// 프로세서ID
		String processor = "";
		Process p = null;
		BufferedReader b_out = null;
		
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "PROCESSOR" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						processor = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("프로세서:") != -1 && str.length() <= MAX_STR_LEN) {
						processor = str.replaceAll("\\[01\\]:", "");
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return processor;
	}

	/**
	 * 시스템의 디스크명을 확인
	 * @return ArrayList list 디스크명이 담긴 목록
	 * @exception Exception
	*/
	public static List<String> getDiskName() throws Exception {

		// 디스크명
		List<String> list = new ArrayList<String>();

		Process p = null;
		BufferedReader b_out = null;
		
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "NAME" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						list.add(str);
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return list;
	}

	/**
	 * 시스템의 디스크 전체용량을 확인
	 * @param String disk 디스크명
	 * @return long cpcty 디스크 전체용량(MB)
	 * @exception Exception
	*/
	public static float getDiskFullCpcty(String disk) throws Exception {

		// 디스크 전체용량
		float cpcty = 0;

		Process p = null;
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FULL", disk };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return cpcty;
	}

	/**
	 * 시스템의 디스크 사용중 용량을 확인
	 * @param String disk 디스크명
	 * @return long cpcty 디스크 사용중 용량(MB)
	 * @exception Exception
	*/
	public static float getDiskUsedCpcty(String disk) throws Exception {

		// 디스크 사용중 용량
		float cpcty = 0;

		Process p = null;
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "USED", disk };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return cpcty;
	}

	/**
	 * 시스템의 디스크 유효 용량을 확인
	 * @param String disk 디스크명
	 * @return long cpcty 디스크 유효 용량(MB)
	 * @exception Exception
	*/
	public static float getDiskFreeCpcty(String disk) throws Exception {

		// 디스크 유효 용량
		float cpcty = 0;

		Process p = null;
		BufferedReader b_out = null;
		
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FREE", disk };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return cpcty;
	}

	/**
	 * 시스템의 메모리 전체용량을 확인
	 * @return long cpcty 메모리 전체용량(MB)
	 * @exception Exception
	*/
	public static float getMoryFullCpcty() throws Exception {

		// 메모리 전체 용량
		float cpcty = 0;
		cpcty = getMoryUsedCpcty() + getMoryFreeCpcty();
		return cpcty;

		/*
		// 메모리 전체 용량
		float cpcty = 0;

		Process p = null;
		try
		{
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL."+Globals.OS_TYPE+".getMoryInfo");
		    String[] command = {cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR),
		    					"FULL"};
		    p = Runtime.getRuntime().exec(command);
		    //p.waitFor();

		    boolean result = false;
		    BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

		    if ("UNIX".equals(Globals.OS_TYPE)) {
		    	while (true){
		    		String str = b_out.readLine();
		    		if (str == null) break;
		    	    if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
		    	    	str = str.replaceAll("mem=", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
		    	    	cpcty = Float.parseFloat(str);
		    	    	result = true;
		    	    }
		        }
		    } else if ("WINDOWS".equals(Globals.OS_TYPE)) {
		    	// 메모리 전체용량 = 사용중 용량 + 유효 용량
				cpcty = getMoryUsedCpcty() + getMoryFreeCpcty();
		    }

		    b_out.close();

		}catch(Exception ex){
		    ex.printStackTrace();
		}finally{
			if (p != null) p.destroy();
		}

		return cpcty;
		*/
	}

	/**
	 * 시스템의 메모리 사용중 용량을 확인
	 * @return long cpcty 메모리 사용중 용량(MB)
	 * @exception Exception
	*/
	public static float getMoryUsedCpcty() throws Exception {

		// 메모리 사용중 용량
		float cpcty = 0;

		Process p = null;
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getMoryInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "USED" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			//boolean result = false;
			b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
						//result = true;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				long fullcpcty = 0;
				long usedcpcty = 0;
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("총 실제 메모리:") != -1 && str.length() <= MAX_STR_LEN) {
						str = str.replaceAll("총 실제 메모리:", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
						fullcpcty = Long.parseLong(str);
					}
					if (str != null && !"".equals(str) && str.indexOf("사용 가능한 실제 메모리:") != -1) {
						str = str.replaceAll("사용 가능한 실제 메모리:", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
						usedcpcty = Long.parseLong(str);
					}
				}
				cpcty = fullcpcty - usedcpcty;
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return cpcty;
	}

	/**
	 * 시스템의 메모리 유효 용량을 확인
	 * @return long cpcty 메모리 유효 용량
	 * @exception Exception
	*/
	public static float getMoryFreeCpcty() throws Exception {

		// 메모리 유효 용량
		float cpcty = 0;

		Process p = null;
		BufferedReader b_out = null;
		try {
			String cmdStr = EgovProperties.getPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getMoryInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FREE" };
			p = Runtime.getRuntime().exec(command);
			//p.waitFor();

			 b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("사용 가능한 실제 메모리:") != -1 && str.length() <= MAX_STR_LEN) {
						str = str.replaceAll("사용 가능한 실제 메모리:", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
						cpcty = Long.parseLong(str);
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_out);
			
			if (p != null) {
				p.destroy();
			}
		}

		return cpcty;
	}

	/**
	 * 특정 프로그램을 실행시키기 위해 메모리용량이 충분한지 확인
	 * @param String memory 메모리용량(MB)
	 * @return boolean 가용여부 True/False
	 * @exception Exception
	*/
	public static boolean checkMoryCpcty(long memory) throws Exception {

		// 가용여부
		boolean result = false;

		if (memory <= 0) {
			//log.debug("+++ Memory Capacity is Not Valid..");
			return false;
		}

		if (memory < getMoryFreeCpcty()) {
			result = true;
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디스크속성정보를 확인한다.
	 * </pre>
	 * @return ArrayList result  디스크속성정보를 라인단위로 담은 ArrayList를 리턴한다.
	 */
	public static List<String> getDiskAttribute() {

		List<String> result = new ArrayList<String>();
		String[] command = { "" }; // 인자 없음
		
		
		try {
			result = executeProgram("getDiskAttrb", command);
		} catch (IOException | InterruptedException e1) {
			throw new RuntimeException(e1);
		}
		
		return result;
	}

	/**
	 * <pre>
	 * Comment : 디스크용량정보를 확인한다.
	 * </pre>
	 * @return ArrayList result  디스크용량정보를 라인단위로 담은 ArrayList를 리턴한다.
	 */
	public static List<String> getDiskCapacity() {

		List<String> result = new ArrayList<String>();
		String[] command = { "" }; // 인자 없음
		try {
			result = executeProgram("getDiskCpcty", command);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디스크존재여부를 확인한다.
	 * </pre>
	 * @return ArrayList result  디스크존재여부를 라인단위로 담은 ArrayList를 리턴한다.
	 */
	public static List<String> getExistDisk(String diskName) {

		List<String> result = new ArrayList<String>();
		String[] command = { "", diskName }; // 인자 없음
		try {
			result = executeProgram("getDiskExst", command);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * 시스템에서 특정 쉘프로그램을 동작하고 콘솔에 출력된 결과를 라인단위로 ArrayList에 담아서 전달한다.
	 * @param String propertyKeyword 프로퍼티키워드 -프로퍼티에서 해당키에 대한 프로그램정보를 확인)
	 * @param String[] command 실행할  프로그램의 argument
	 * @return ArrayList resultTxtList 콘솔상에 출력된 결과를 라인단위 문자열로 보관
	 * @exception Exception
	*/
	public static List<String> executeProgram(String propertyKeyword, String[] command) throws IOException , InterruptedException {

		List<String> resultTxtList = new ArrayList<String>();
		
		BufferedReader b_err = null;
		BufferedReader b_out = null;
		try {
			Process p = null;
			String cmdStr = EgovProperties.getPathProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + "." + propertyKeyword);
			command[0] = cmdStr;
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			//프로세스 에러시 종료
			if (p.exitValue() != 0) {
				b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while (b_err.ready()) {
					//String line = b_err.readLine();
					//if (line.length() <= MAX_STR_LEN) log.debug("ERR\n" + line);
				}
			} else {	//프로세스 실행 성공시 결과 확인
				String tmpLine = "";
				b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while (b_out.ready()) {
					// 결과문자가 있으면 생성자가 있다는 의미
					tmpLine = b_out.readLine();
					//2017.03.03 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
					if(tmpLine != null){
						if (tmpLine.length() <= MAX_STR_LEN) {
							resultTxtList.add(tmpLine);
						}
					}
				}
			}
		} finally {
			EgovResourceCloseHelper.close(b_err, b_out);
		}

		return resultTxtList;
	}

	/**
	 * <pre>
	 * Comment : 프로세스 정보를 확인한다. (
	 * </pre>
	 * @param
	 * @return List<String[]> 프로세스 정보를 리턴한다.
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	public static List<String[]> getProcessId() {

		List<String[]> processes = new ArrayList<String[]>();
		String[] command = { "", "-all" }; // 인자 없음
		try {
			String line;
			String separator = null;

			Process p = null;

			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
				separator = "\",\"";
				
				if (p == null) {
					throw new RuntimeException("Can't execute process...");
				}
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				if (input != null) {
					while ((line = input.readLine()) != null) {
						if (!line.trim().equals("") && line.length() <= MAX_STR_LEN) {

							line = line.substring(1, line.length() - 1);
							String[] operator = line.split(separator);

							processes.add(operator);
						}
					}

					input.close();
				}
				
			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				/*
				String tmp = "ps -eo \"%p %G %U %c %a\"|awk -F\" \" '{print $1,$2,$3,$4,$5}'";
				log.debug(tmp);
				command [0] = tmp;
				p = Runtime.getRuntime().exec(tmp);
				separator = " ";
				*/
				separator = " ";
				List<String>  resultList = executeProgram("getProcInfo", command);
				
				for (String result : resultList) {
					String[] operator = result.split(separator);

					processes.add(operator);
				}

			}


		} catch (IOException err) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			throw new RuntimeException(err);
		} catch (Exception err) {
			throw new RuntimeException(err);
		}

		return processes;
	}

	/**
	 * <pre>
	 * Comment : 프로세스 정보를 확인한다. (
	 * </pre>
	 * @param String processName
	 * @return List<String[]> 프로세스 정보를 리턴한다.
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	public static List<String[]> getProcessId(String processName) {
		//log.debug("getProcessId_start");
		List<String[]> processes = new ArrayList<String[]>();
		String[] command = { "", "" }; // 인자 없음
		//String tmp = "";
		BufferedReader input = null;
		try {
			String line;
			String separator = null;

			Process p = null;

			if (Globals.OS_TYPE == null) {
				throw new RuntimeException("Globals.OS_TYPE property value is needed!!!");
			}

			//log.debug("Globals.OS_TYPE:"+Globals.OS_TYPE);
			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				p = Runtime.getRuntime().exec("tasklist /fo csv /nh /fi \"imagename eq " + processName + "*\"");
				separator = "\",\"";
				
				if (p == null) { // 2012.11 KISA 보안조치
					throw new RuntimeException("Can't execute process...");
				}
				input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				if (input != null) {
					while ((line = input.readLine()) != null) {
						//log.debug("LINE__"+line);
						if (line.length() <= MAX_STR_LEN) {

							line = line.substring(1, line.length() - 1);
							String[] operator = line.split(separator);

							processes.add(operator);
						}
					}

					input.close();
				}
				
			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				//tmp = "ps -eo \"%p %G %U %c %a\"|awk -F\" \" '{print $1,$2,$3,$4,$5}'|grep "+processName;
				//command [0] = tmp;
				//p = Runtime.getRuntime().exec(tmp);
				command[1] = processName;

				separator = " ";
				
				List<String>  resultList = executeProgram("getProcInfo", command);
				
				for (String result : resultList) {
					String[] operator = result.split(separator);

					processes.add(operator);
				}	
			}

		} catch (IOException err) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			throw new RuntimeException(err);
		} catch (Exception err) {
			throw new RuntimeException(err);
		}

		return processes;
	}
}
