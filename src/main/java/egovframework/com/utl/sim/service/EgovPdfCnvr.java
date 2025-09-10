package egovframework.com.utl.sim.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * xls,doc,ppt를 Pdf로 변환하는 화면 Business Interface class
 * 
 * @author 공통 서비스 개발팀 이용
 * @since 2009.02.02
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.02  이용           최초 생성
 *   2017.03.03  조성원          시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2025.09.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *   2025.09.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *   2025.09.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AssignmentInOperand(피연산자내에 할당문이 사용됨. 해당 코드를 복잡하고 가독성이 떨어지게 만듬)
 *
 *      </pre>
 */
@Slf4j
public class EgovPdfCnvr {
	public static String addrIP = "";
	static final char FILE_SEPARATOR = File.separatorChar;
	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;
	public static final int BUFF_SIZE = 2048;
	private static final String STORE_FILE_PATH = EgovProperties.getProperty("Globals.fileStorePath");

	/**
	 * <pre>
	 * Comment : doc, xls 파일등을 PDF변환 변환한다.
	 * </pre>
	 * 
	 * @param String pdfFileSrc doc, xls 파일 전체경로
	 * @param String targetPdf 변환파일명(확장자 제외)
	 * @return boolean status true/false 를 리턴한다.
	 * @version 1.0 (2009.02.10)
	 * @see
	 */
	public static boolean getPDF(String targetPdf, HttpServletRequest request, HttpServletResponse response) {
		boolean status = false;

		try {
			MultipartHttpServletRequest mptRequest = WebUtils.getNativeRequest(request,
					MultipartHttpServletRequest.class);

			// 2022.01 Possible null pointer dereference due to return value of called
			// method 조치
			if (mptRequest != null) {
				Iterator<String> fileIter = mptRequest.getFileNames();

				while (fileIter.hasNext()) {

					MultipartFile mFile = mptRequest.getFile(fileIter.next());

					// 2022.11.11 김혜준 시큐어코딩 처리
					if (mFile == null) {
						continue;
					}

					String newName = "";

					// newName 은 Naming Convention에 의해서 생성
					newName = EgovStringUtil.getTimeStamp();
					writeFile(mFile, newName);

					File inputFile = new File(
							EgovWebUtil.filePathBlackList(STORE_FILE_PATH + FilenameUtils.getName(newName)));

					if (inputFile.exists()) {

						// connect to an OpenOffice.org instance running on port 8100
						SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
						connection.connect();
						// 원본 디렉토리에 targetPdf 명칭지정
						String valueFile = null;
						// KISA 보안약점 조치 (2018-10-29, 윤창원)
						valueFile = EgovStringUtil.isNullToString(inputFile.getParent()).replace('\\', FILE_SEPARATOR)
								.replace('/', FILE_SEPARATOR);
						File outputFile = new File(valueFile + "/" + targetPdf + ".pdf");
						// convert
						DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
						converter.convert(inputFile, outputFile);
						// close the connection
						connection.disconnect();

						if (inputFile.exists()) {
							// 3. 삭제해줍니다.
							status = inputFile.delete();
						}

						status = true;

					} else {
						status = false;
					}

				}

			}
		} catch (IOException ex) {
			EgovBasicLogger.debug("PDF converting error", ex);
			status = false;
		}

		// 메소드 종료 Log
		return status;
	}

	/**
	 * 파일을 실제 물리적인 경로에 생성한다.
	 * 
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 */
	protected static void writeFile(MultipartFile file, String newName) {
		if (log.isDebugEnabled()) {
			log.debug("file={}", file);
			log.debug("newName={}", newName);
		}

		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(
					EgovWebUtil.filePathBlackList(STORE_FILE_PATH + File.separator + FilenameUtils.getName(newName))));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}