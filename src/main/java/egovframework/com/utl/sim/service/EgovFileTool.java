/**
 *  Class Name : EgovFileTool.java
 *  Description : 시스템 디렉토리 정보를 확인하여 제공하는  Business class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.13    조재영          최초 생성
 *   2017.03.03    조성원 	     시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2017.03.03    조성원          시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *   2018.03.19    신용호          createDirectories() 추가 : 여러 레벨의 디렉토리를 한번에 생성
 *
 *
 *  @author 공통 서비스 개발팀 조재영,박지욱
 *  @since 2009. 01. 13
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * EgovFileTool 클래스를 정의한다.
 *
 * @author 김진만
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2020.12.07   신용호       KISA 보안약점 조치
 *  2022.11.11   김혜준       시큐어코딩 처리
 *  2024.10.29   win777	    디렉토리 생성 성공 시 생성된 절대경로를 리턴하도록 변경
 *  2025.02.06   신용호       deleteFile() KISA 시큐어코딩 처리
 *
 * </pre>
 */

public class EgovFileTool {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileTool.class);
	
	private static final String FILE_STORE_PATH = EgovProperties.getProperty("Globals.fileStorePath");


	/**
	 * <pre>
	 * Comment : 디렉토리(파일)를 삭제한다. (파일,디렉토리 구분없이 존재하는 경우 무조건 삭제한다)
	 * </pre>
	 *
	 * @param filePath 삭제하고자 하는 파일의 절대경로 + 파일명
	 * @return 성공하면 삭제된 절대경로, 아니면블랭크
	 */
	public static String deletePath(String filePath) {
		return deletePath(FILE_STORE_PATH, filePath);
	}
	
	/**
	 * <pre>
	 * Comment : 디렉토리(파일)를 삭제한다. (파일,디렉토리 구분없이 존재하는 경우 무조건 삭제한다)
	 * </pre>
	 *
	 * @param basePath 기본 경로
	 * @param filePath 삭제하고자 하는 파일의 절대경로 + 파일명
	 * @return 성공하면 삭제된 절대경로, 아니면블랭크
	 */
	public static String deletePath(String basePath, String filePath) {
		
		// 인자 값이 없는 경우 "Globals.fileStorePath" 기본 경로를 지정한다.
		if (basePath == null || basePath.equals("")) {
			basePath = FILE_STORE_PATH;
		}
		
		String result = "";

		File file = new File(EgovWebUtil.filePathBlackList(basePath + filePath));
		if (file.exists()) {
			result = file.getAbsolutePath();
			if (!file.delete()) {
				result = "";
			}
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다. (여러 레벨의 경로를 동시에 생성)
	 * </pre>
	 *
	 * @param basePath 기본 경로
	 * @param dirPath 생성하고자 하는 절대경로
	 * @return 성공하면 생성된 절대경로, 아니면 블랭크
	 */
	public static String createDirectories(String dirPath) {
		return createDirectories(FILE_STORE_PATH, dirPath);
	}
	
	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다. (여러 레벨의 경로를 동시에 생성)
	 * </pre>
	 *
	 * @param basePath 기본 경로
	 * @param dirPath 생성하고자 하는 절대경로
	 * @return 성공하면 생성된 절대경로, 아니면 블랭크
	 */
	public static String createDirectories(String basePath, String dirPath) {
		String result = "";
		
		// 인자 값이 없는 경우 "Globals.fileStorePath" 기본 경로를 지정한다.
		if (basePath == null || basePath.equals("")) {
			basePath = FILE_STORE_PATH;
		}

		File file = new File(EgovWebUtil.filePathBlackList(basePath + dirPath));
		if (!file.exists()) {
			if (file.mkdirs()) {
				LOGGER.debug("[file.mkdirs] file : Path Creation Success");
				file.getAbsolutePath();
			} else {
				LOGGER.error("[file.mkdirs] file : Path Creation Fail");
			}
		}

		return result;
	}

	/**
	 * 디렉토리 내부 하위목록들 중에서 파일을 찾는 기능(모든 목록 조회)
	 *
	 * @param fileArray fileArray 파일목록
	 * @return ArrayList list 파일목록(절대경로)
	 */
	public static List<String> getSubFilesByAll(File[] fileArray) throws Exception {
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File[] tmpArray = fileArray[i].listFiles();
				list.addAll(getSubFilesByAll(tmpArray));
				// 파일이면 담는다.
			} else {
				list.add(fileArray[i].getAbsolutePath());
			}
		}

		return list;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다.
	 * </pre>
	 *
	 * @param dirPath 생성하고자 하는 절대경로
	 * @return 성공하면 새성된 절대경로, 아니면 블랭크
	 */
	public static String createNewDirectory(String dirPath) {

		return createNewDirectory(FILE_STORE_PATH, dirPath);
	}
	
	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다.
	 * </pre>
	 *
	 * @param basePath 기본 경로
	 * @param dirPath 생성하고자 하는 절대경로
	 * @return 성공하면 새성된 절대경로, 아니면 블랭크
	 */
	public static String createNewDirectory(String basePath, String dirPath) {

		// 인자 값이 없는 경우 "Globals.fileStorePath" 기본 경로를 지정한다.
		if (basePath == null || basePath.equals("")) {
			basePath = FILE_STORE_PATH;
		}
		
		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (dirPath == null || dirPath.equals("")) {
			return "";
		}

		File file = new File(EgovWebUtil.filePathBlackList(basePath + dirPath));
		String result = "";
		// 없으면 생성
		if (file.exists()) {
			// 혹시 존재해도 파일이면 생성 - 생성되지 않는다.(아래는 실질적으로는 진행되지 않음)
			if (file.isFile()) {
				//new File(file.getParent()).mkdirs();
				if (file.mkdirs()) {
					result = file.getAbsolutePath();
				}
			} else {
				result = file.getAbsolutePath();
			}
		} else {
			// 존해하지 않으면 생성
			if (file.mkdirs()) {
				result = file.getAbsolutePath();
			}
		}

		return result;
	}
	
	/**
	 * <pre>
	 * Comment : 파일을 생성한다.
	 * </pre>
	 *
	 * @param filePath fileName 파일의 절대경로 + 파일명
	 * @return 성공하면 생성된 파일의 절대경로, 아니면블랭크
	 */
	public static String createNewFile(String filePath) {
		return createNewFile(FILE_STORE_PATH, filePath);
	}
	
	/**
	 * <pre>
	 * Comment : 파일을 생성한다.
	 * </pre>
	 *
	 * @param basePath 기본 경로
	 * @param filePath fileName 파일의 절대경로 + 파일명
	 * @return 성공하면 생성된 파일의 절대경로, 아니면블랭크
	 */
	public static String createNewFile(String basePath, String filePath) {
		
		// 인자 값이 없는 경우 "Globals.fileStorePath" 기본 경로를 지정한다.
		if (basePath == null || basePath.equals("")) {
			basePath = FILE_STORE_PATH;
		}

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (filePath == null || filePath.equals("")) {
			return "";
		}

		File file = new File(EgovWebUtil.filePathBlackList(basePath + filePath));
		String result = "";
		try {
			if (file.exists()) {
				result = filePath;
			} else {
				// 존재하지 않으면 생성함
				// 2017.02.08 이정은 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				if (new File(file.getParent()).mkdirs()) {
					LOGGER.debug("[file.mkdirs] file : File Creation Success");
				} else {
					LOGGER.error("[file.mkdirs] file : File Creation Fail");
				}

				if (file.createNewFile()) {
					result = file.getAbsolutePath();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 파일을 삭제한다.
	 * </pre>
	 *
	 * @param fileDeletePath 삭제하고자 하는파일의 절대경로
	 * @return 성공하면 삭제된 파일의 절대경로, 아니면블랭크
	 */
	public static String deleteFile(String fileDeletePath) {
		return deleteFile(FILE_STORE_PATH, fileDeletePath);
	}
	
	/**
	 * <pre>
	 * Comment : 파일을 삭제한다.
	 * </pre>
	 *
	 * @param basePath 기본 경로
	 * @param fileDeletePath 삭제하고자 하는파일의 절대경로
	 * @return 성공하면 삭제된 파일의 절대경로, 아니면블랭크
	 */
	public static String deleteFile(String basePath, String fileDeletePath) {
		
		// 인자 값이 없는 경우 "Globals.fileStorePath" 기본 경로를 지정한다.
		if (basePath == null || basePath.equals("")) {
			basePath = FILE_STORE_PATH;
		}

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (fileDeletePath == null || fileDeletePath.equals("")) {
			return "";
		}
		String result = "";
		File file = new File(EgovWebUtil.filePathBlackList(fileDeletePath));
		if (file.isFile()) {
			result = deletePath(basePath, fileDeletePath);
		} else {
			result = "";
		}

		return result;
	}

	/**
	 * 파일을 특정 구분자(',', '|', 'TAB')로 파싱하는 기능
	 *
	 * @param parFile 파일
	 * @param parChar 구분자(',', '|', 'TAB')
	 * @param parField 필드수
	 * @return Vector parResult 파싱결과 구조체
	 * @exception Exception
	 */
	public static Vector<List<String>> parsFileByChar(String parFile, String parChar, int parField) throws Exception {

		// 파싱결과 구조체
		Vector<List<String>> parResult = new Vector<List<String>>();

		// 파일 오픈
		String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(EgovWebUtil.filePathBlackList(parFile1));
		BufferedReader br = null;
		try {
			// 파일이며, 존재하면 파싱 시작
			if (file.exists() && file.isFile()) {

				// 1. 파일 텍스트 내용을 읽어서 StringBuffer에 쌓는다.
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				StringBuffer strBuff = new StringBuffer();
				String line = "";
				while ((line = br.readLine()) != null) {
					if (line.length() < MAX_STR_LEN) {
						strBuff.append(line);
					}
				}

				// 2. 쌓은 내용을 특정 구분자로 파싱하여 String 배열로 얻는다.
				String[] strArr = EgovStringUtil.split(strBuff.toString(), parChar);

				// 3. 필드 수 만큼 돌아가며 Vector<ArrayList> 형태로 만든다.
				int filedCnt = 1;
				List<String> arr = new ArrayList<String>();
				for (int i = 0; i < strArr.length; i++) {

					if (parField != 1) {
						if ((filedCnt % parField) == 1) {
							if (strArr[i] != null) {
								arr.add(strArr[i]);
							}
							if (i == (strArr.length - 1)) {
								parResult.add(arr);
							}
						} else if ((filedCnt % parField) == 0) {
							if (strArr[i] != null) {
								arr.add(strArr[i]);
								parResult.add(arr);
							}
						} else {
							if (strArr[i] != null) {
								arr.add(strArr[i]);
								if (i == (strArr.length - 1)) {
									parResult.add(arr);
								}
							}
						}
					} else {
						arr = new ArrayList<String>();
						if (strArr[i] != null) {
							arr.add(strArr[i]);
						}
						parResult.add(arr);
					}

					filedCnt++;
				}
			}
		} finally {
			EgovResourceCloseHelper.close(br);
		}

		return parResult;
	}

}
