package egovframework.com.utl.sim;

import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * PDF Convert Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.03.26
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.03.26  신용호          최초 생성
 *
 * @ 사전조건
 *   1. Apache OpenOffice를 OS별로 설치하여야 한다. (Windows, macOS, Linux 지원)
 *      https://www.openoffice.org/ko/download/index.html
 *   2. 윈도우의 경우 commandline 명령어로 다음과 같이 연동 서비스를 구동한다.
 *      "C:\Program Files\OpenOffice.org 3\program\soffice.exe" -headless -accept=socket,port=8100;urp; 
 * @ 특징
 *   xls는 잘되지만 xlsx, 이미지파일은 지원되지 않는다.
 * @ eGov WIKI
 *   http://www.egovframe.go.kr/wiki/doku.php?id=egovframework:%ED%8C%8C%EC%9D%BC%EB%B3%80%ED%99%98
 * @ 참고 URL
 *   https://wiki.openoffice.org/wiki/Documentation/DevGuide/ProUNO/Starting_OpenOffice.org_in_Listening_Mode
 * </pre>
 */

public class TestPdfConverter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sourcePath = "C:/egovframework/";
		//String sourceFileName = "test1.xls";
		//String sourceFileName = "test2.png";
		String sourceFileName = "test3.hwp";
		String targetPath = "targetPdf/";
		//String targetFileName = "convert1.pdf";
		//String targetFileName = "convert2.pdf";
		String targetFileName = "convert3.pdf";

		File inputFile = new File(sourcePath + sourceFileName);

		if (inputFile.exists()) {
			// connect to an OpenOffice.org instance running on port 8100
			SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
			try {
				connection.connect();
			} catch (ConnectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//원본 디렉토리에 targetPdf 명칭지정
			File outputFile = new File(sourcePath + targetPath + targetFileName);
			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			// close the connection
			connection.disconnect();

		}
	}

}
