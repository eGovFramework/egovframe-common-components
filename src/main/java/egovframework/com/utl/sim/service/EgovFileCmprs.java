/**
 *  Class Name : EgovFileCmprs.java
 *  Description : 파일(디렉토리)의 압축 및 압축해제 하는 Business Interface class
 *  Modification Information
 *
 *   수정일               수정자              수정내용
 *   ----------   --------    ---------------------------
 *   2009.02.04   박지욱              최초 생성
 *   2017.03.03   조성원              시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2017.03.03   조성원              시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *   2018.03.19   신용호              createDirectories() 호출및 예외처리 수정
 *   2020.08.28   신용호              시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2020.10.29   신용호              KISA 보안약점 조치 (경로 조작 및 자원 삽입)
 *   2022.11.11   김혜준			  시큐어코딩 처리
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 02. 04
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

public class EgovFileCmprs {

	final static int BUFFER_SIZE = 64 * 1024;
	final static char FILE_SEPARATOR = File.separatorChar;

	/**
	 * 파일(디렉토리)을 압축해제하는 기능
	 * @param source 압축파일명
	 * @param target 압출이 풀릴 디렉토리
	 * @return boolean result 압축해제성공여부 True / False
	 */
	public static boolean decmprsFile(String source, String target) throws Exception {

		// 압축해제성공여부
		boolean result = false;
		int cnt = 0;
		// 읽어들일 byte 버퍼
		byte[] buffer = new byte[BUFFER_SIZE];

		FileInputStream finput = null;
		FileOutputStream foutput = null;
		ZipInputStream zinput = null;

		String source1 = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String target1 = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(source1);

		if (srcFile.exists() && srcFile.isFile()) {

			// 절대 경로인 경우 직접 디렉토리 생성, 상대 경로인 경우 EgovFileTool 사용
			String target2;
			File targetFile = new File(target1);
			if (targetFile.isAbsolute()) {
				// 절대 경로인 경우 직접 생성
				targetFile = new File(EgovWebUtil.filePathBlackList(target1));
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				target2 = targetFile.getAbsolutePath();
			} else {
				// 상대 경로인 경우 EgovFileTool 사용
				target2 = EgovFileTool.createNewDirectory(target1);
			}
			
			if (target2 == null || target2.isEmpty()) {
				throw new Exception("Failed to create target directory: " + target1);
			}
			
			File tarFile = new File(target2);
			finput = new FileInputStream(srcFile);
			zinput = new ZipInputStream(finput);

			ZipEntry entry;

			try {

				File efile;
				while ((entry = zinput.getNextEntry()) != null) {

					String filename = entry.getName();
					String entryFilePath = tarFile.getAbsolutePath() + FILE_SEPARATOR + filename;
					entryFilePath = EgovWebUtil.filePathBlackList(entryFilePath);
					efile = new File(entryFilePath);
					if (entry.isDirectory()) {
						EgovFileTool.createDirectories(efile.getAbsolutePath());
					} else {
						// 상위 디렉토리가 없으면 생성
						File parentDir = efile.getParentFile();
						if (parentDir != null && !parentDir.exists()) {
							parentDir.mkdirs();
						}
						
						foutput = new FileOutputStream(efile);
						try {
							// 2022.11.11 시큐어코딩 처리
							while ((cnt = zinput.read(buffer)) != -1) {
								foutput.write(buffer, 0, cnt);
							}
							foutput.flush();
						} finally {
							// 각 entry의 FileOutputStream을 닫음
							EgovResourceCloseHelper.close(foutput);
							foutput = null;
						}
					}
					zinput.closeEntry();
				}

				result = true;

			} finally {
				EgovResourceCloseHelper.close(finput, zinput, foutput);
			}
		}
		return result;
	}

}
