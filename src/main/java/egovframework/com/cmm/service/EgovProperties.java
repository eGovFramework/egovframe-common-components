package egovframework.com.cmm.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 *  Class Name : EgovProperties.java
 *  Description : properties값들을 파일로부터 읽어와   Globals클래스의 정적변수로 로드시켜주는 클래스로
 *   문자열 정보 기준으로 사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다.
 *  Modification Information
 *
 *   수정일              수정자          수정내용
 *   ----------  --------  ---------------------------
 *   2009.01.19  박지욱          최초 생성
 *	 2011.07.20    서준식 	   Globals파일의 상대경로를 읽은 메서드 추가
 *	 2014.10.13    이기하 	   Globals.properties 값이 null일 경우 오류처리
 *   2019.04.26    신용호 	   RELATIVE_PATH_PREFIX Path 적용 방식 개선
 *   2022.01.21    윤주호 	   Try-catch-resource 조치 및 Method Refactoring
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 01. 19
 *  @version 1.0
 *  @see
 *
 */

public class EgovProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovProperties.class);

	//파일구분자
	final static String FILE_SEPARATOR = System.getProperty("file.separator");

	//프로퍼티 파일의 물리적 위치
	//public static final String GLOBALS_PROPERTIES_FILE = System.getProperty("user.home") + FILE_SEPARATOR + "egovProps" +FILE_SEPARATOR + "globals.properties";

	public static final String RELATIVE_PATH_PREFIX = EgovProperties.class.getResource("") == null ? ""
		: EgovProperties.class.getResource("").getPath().substring(0,
			EgovProperties.class.getResource("").getPath().lastIndexOf("com"));
	//public static final String RELATIVE_PATH_PREFIX = EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0,EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath().indexOf("WEB-INF/classes/")+"WEB-INF/classes/".length())+"egovframework/";

	public static final String GLOBALS_PROPERTIES_FILE = RELATIVE_PATH_PREFIX + "egovProps" + FILE_SEPARATOR
		+ "globals.properties";

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
	 * @param keyName String
	 * @return String
	 */
	public static String getProperty(String keyName) {
		LOGGER.debug("===>>> getProperty" + EgovProperties.class.getProtectionDomain().getCodeSource() == null ? ""
			: EgovStringUtil
				.isNullToString(EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
		LOGGER.debug("getProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);

		return getPropertyValueByKey(keyName);
	}

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 상대경로 프로퍼티 값을 절대경로로 반환한다(Globals.java 전용)
	 * @param keyName String
	 * @return String
	 */
	public static String getPathProperty(String keyName) {
		LOGGER.debug("getPathProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);

		return RELATIVE_PATH_PREFIX + "egovProps" + FILE_SEPARATOR + getProperty(keyName);
	}

	/**
	 * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
	 * @param fileName String
	 * @param key String
	 * @return String
	 */
	public static String getProperty(String fileName, String keyName) {
		return getPropertyValueByKey(fileName, keyName);
	}

	/**
	 * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 상대 경로값을 절대 경로값으로 반환한다
	 * @param fileName String
	 * @param key String
	 * @return String
	 */
	public static String getPathProperty(String fileName, String keyName) {
		return RELATIVE_PATH_PREFIX + "egovProps" + FILE_SEPARATOR + getProperty(fileName, keyName);
	}

	/**
	 * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
	 * @param property String
	 * @return ArrayList
	 */
	public static ArrayList<Map<String, String>> loadPropertyFile(String property) {

		// key - value 형태로 된 배열 결과
		ArrayList<Map<String, String>> keyList = new ArrayList<Map<String, String>>();

		String src = property.replace('\\', File.separatorChar).replace('/', File.separatorChar);

		if (Files.exists(Paths.get(EgovWebUtil.filePathBlackList(src)))) { //2022.01 Potential Path Traversal
			Properties props = loadPropertiesFromFile(src);

			Enumeration<?> plist = props.propertyNames();
			if (plist != null) {
				while (plist.hasMoreElements()) {
					Map<String, String> map = new HashMap<String, String>();
					String key = (String)plist.nextElement();
					map.put(key, props.getProperty(key));
					keyList.add(map);
				}
			}
		}

		return keyList;
	}

	/**
	 * 기본 Property 에서 Property Key로 Property value 받아온다.
	 * @param keyName
	 * @return
	 */
	public static String getPropertyValueByKey(String keyName) {
		return getPropertyValueByKey(GLOBALS_PROPERTIES_FILE, keyName);
	}

	/**
	 * Property 파일을 지정하여 Property Key로 Property value 받아온다.
	 * @param fileName
	 * @param keyName
	 * @return
	 */
	public static String getPropertyValueByKey(String fileName, String keyName) {
		String propertyValue = "";
		Properties props = loadPropertiesFromFile(fileName);

		if (props.containsKey(keyName)) {
			propertyValue = props.getProperty(keyName).trim();
		}

		return propertyValue;
	}

	/**
	 * Property 파일패스로 Properties 객체를 리턴한다.
	 * @param fileName
	 * @return
	 */
	private static Properties loadPropertiesFromFile(String fileName) {
		Properties props = new Properties();

		try (FileInputStream fis = new FileInputStream(EgovWebUtil.filePathBlackList(fileName));
			BufferedInputStream bis = new BufferedInputStream(fis);) {
			props.load(bis);
		} catch (FileNotFoundException fne) {
			LOGGER.debug("Property file not found.", fne);
			throw new RuntimeException("Property file not found", fne);
		} catch (IOException ioe) {
			LOGGER.debug("Property file IO exception", ioe);
			throw new RuntimeException("Property file IO exception", ioe);
		}

		return props;
	}
}
