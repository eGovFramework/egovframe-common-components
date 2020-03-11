/**
 *  Class Name : EgovMenuGov.java
 *  Description : 메뉴관리 Business Interface class
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
 *
 *  Copyright (C) 2009 by EGOV  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Vector;

import egovframework.com.cmm.util.EgovResourceCloseHelper;

public class EgovMenuGov {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	/**
	 * <pre>
	 * Comment : DAT 파일을 파싱하여 메뉴관리화면에 리턴.
	 * </pre>
	 * @param String parFile   DAT파일명
	 * @param String parChar   구분자
	 * @param Int    parField  필드수
	 * @return Vector list
	 * @version 1.0 (2009.02.04.)
	 * @see
	 */
	public static Vector<List<String>> parsFileByMenuChar(String parFile, String parChar, int parField) throws Exception {
		Vector<List<String>> list = null;
		String FileName = null;
		
		FileName = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(FileName);

		// 파일이며, 존재하면 파싱 시작
		if (file.exists() && file.isFile()) {
			list = EgovFileTool.parsFileByChar(parFile, parChar, parField);
		} else {
			list = new Vector<List<String>>();
		}
		
		return list;
	}

	/**
	 * <pre>
	 * Comment : 메뉴관리 화면의 데이타를 DAT 파일로 생성.
	 * </pre>
	 * @param String[] menuIDArray     ID Array
	 * @param String[] menuNameArray   Name Array
	 * @param String[] menuLevelArray  Lefel Array
	 * @param String[] menuURLArray    URL Array
	 * @return boolean true/false
	 * @version 1.0 (2009.02.04.)
	 * @see
	 */

	public static boolean setDataByDATFile(String parFile, String[] menuIDArray, String[] menuNameArray, String[] menuLevelArray, String[] menuURLArray) throws Exception {
		boolean success = false;
		String FileName = null;

		FileName = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(FileName);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		try {

			for (int i = 0; i < menuIDArray.length; i++) { //nodeId | parentNodeId | nodeName | nodeUrl
				out.write(menuIDArray[i] + "|" + menuLevelArray[i] + "|" + menuNameArray[i] + "|" + menuURLArray[i] + "|");
				out.newLine();
			}
			success = true;
		} finally {
			EgovResourceCloseHelper.close(out);
		}
		return success;
	}
}