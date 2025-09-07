package egovframework.com.utl.sim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;

import egovframework.com.cmm.EgovWebUtil;

/**
 * 파일(디렉토리)의 압축 및 압축해제 하는 Business Interface class
 * 
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009.02.04
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.04  박지욱          최초 생성
 *   2017.03.03  조성원          시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2017.03.03  조성원          시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *   2018.03.19  신용호          createDirectories() 호출및 예외처리 수정
 *   2020.08.28  신용호          시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2020.10.29  신용호          KISA 보안약점 조치 (경로 조작 및 자원 삽입)
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2025.09.07  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *   2025.09.07  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AssignmentInOperand(피연산자내에 할당문이 사용됨. 해당 코드를 복잡하고 가독성이 떨어지게 만듬)
 *
 *      </pre>
 */
public class EgovFileCmprs {

	/**
	 * 파일(디렉토리)을 압축해제하는 기능
	 * 
	 * @param source 압축파일명
	 * @param target 압출이 풀릴 디렉토리
	 * @return boolean result 압축해제성공여부 True / False
	 */
	public static boolean decmprsFile(String source, String target) {
		// 압축해제성공여부
		boolean result = false;

		String source1 = source.replace('\\', File.separatorChar).replace('/', File.separatorChar);
		String target1 = target.replace('\\', File.separatorChar).replace('/', File.separatorChar);
		File srcFile = new File(source1);

		if (srcFile.exists() && srcFile.isFile()) {
			String target2 = EgovFileTool.createNewDirectory(target1);
			File tarFile = new File(target2);

			try (FileInputStream finput = new FileInputStream(srcFile);
					ZipInputStream zinput = new ZipInputStream(finput);) {
				ZipEntry entry = zinput.getNextEntry();

				File efile;
				while (entry != null) {

					String filename = entry.getName();
					String entryFilePath = tarFile.getAbsolutePath() + File.separatorChar + filename;
					entryFilePath = EgovWebUtil.filePathBlackList(entryFilePath);
					efile = new File(entryFilePath);
					if (entry.isDirectory()) {
						EgovFileTool.createDirectories(efile.getAbsolutePath());
					} else {
						IOUtils.copy(zinput, new FileOutputStream(efile));
					}

					entry = zinput.getNextEntry();
				}

				result = true;
			} catch (FileNotFoundException e) {
				throw new BaseRuntimeException(e);
			} catch (IOException e) {
				throw new BaseRuntimeException(e);
			}
		}

		return result;
	}

}
