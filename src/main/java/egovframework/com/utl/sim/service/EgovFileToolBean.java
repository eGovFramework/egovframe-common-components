package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.exception.UncheckedException;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.aop.EgovFileBasePathSecurityValidator;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템 디렉토리 정보를 확인하여 제공하는 Business class
 * <p>
 * EgovFileToolBean 클래스를 정의한다.
 * 
 * @author 공통 서비스 개발팀 조재영,박지욱
 * @author 김진만
 * @since 2009. 01. 13
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.01.13  조재영          최초 생성
 *   2017.03.03  조성원          시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2017.03.03  조성원          시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *   2018.03.19  신용호          createDirectories() 추가 : 여러 레벨의 디렉토리를 한번에 생성
 *   2020.12.07  신용호          KISA 보안약점 조치
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2024.10.29  win777        디렉토리 생성 성공 시 생성된 절대경로를 리턴하도록 변경
 *   2025.02.06  신용호          deleteFile() KISA 시큐어코딩 처리
 *   2025.09.09  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(넘겨받는 메소드 parameter 값을 직접 변경하는 코드 탐지)
 *   2025.09.09  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *   2025.09.09  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AssignmentInOperand(피연산자내에 할당문이 사용됨. 해당 코드를 복잡하고 가독성이 떨어지게 만듬)
 *
 *      </pre>
 */
@Component
@Slf4j
public class EgovFileToolBean {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	private static final String FILE_STORE_PATH = EgovProperties.getProperty("Globals.fileStorePath");

	/**
	 * 파일을 특정 구분자(',', '|', 'TAB')로 파싱하는 기능
	 *
	 * @param parFile  파일
	 * @param parChar  구분자(',', '|', 'TAB')
	 * @param parField 필드수
	 * @return Vector parResult 파싱결과 구조체
	 */
	public Vector<List<String>> parsFileByChar(String basePath, String parFile, String parChar, int parField) {
		String basePath2 = null;

		// 인자 값이 없는 경우 "Globals.fileStorePath" 기본 경로를 지정한다.
		if (basePath == null || basePath.equals("")) {
			basePath2 = FILE_STORE_PATH;
		}

		// AOP 적용시 주석 처리 한다.
		if (!EgovFileBasePathSecurityValidator.validate(basePath2)) {
			throw new SecurityException("Unacceptable base path : " + basePath2);
		}

		// 파싱결과 구조체
		Vector<List<String>> parResult = new Vector<List<String>>();

		// 파일 오픈
		String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(EgovWebUtil.filePathBlackList(basePath2 + parFile1));

		// 파일이며, 존재하면 파싱 시작
		if (file.exists() && file.isFile()) {
			// 1. 파일 텍스트 내용을 읽어서 StringBuffer에 쌓는다.
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
				StringBuffer strBuff = new StringBuffer();
				String line = br.readLine();
				while (line != null) {
					if (line.length() < MAX_STR_LEN) {
						strBuff.append(line);
					}

					line = br.readLine();
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
			} catch (FileNotFoundException e) {
				throw new UncheckedException(e);
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}

		return parResult;
	}

}
