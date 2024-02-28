package egovframework.com.utl.sim;

//import java.io.File;

/*
import org.jodconverter.JodConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeUtils;
*/

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
 	<dependency>
	  <groupId>org.jodconverter</groupId>
	  <artifactId>jodconverter-local</artifactId>
	  <version>4.2.2</version>
	</dependency>
	테스트시 pom.xml 추가 및 주석 해제후 사용
	@ 참고 사이트
	https://github.com/sbraconnier/jodconverter/wiki/Java-Library
 * </pre>
 */

public class TestPdfConverter2 {

	public static void main(String[] args) {

//		File inputFile = new File("C:/egovframework/test1.xls");
//		File outputFile = new File("C:/egovframework/result1.pdf");

		// Create an office manager using the default configuration.
		// The default port is 2002. Note that when an office manager
		// is installed, it will be the one used by default when
		// a converter is created.
		/*
		final LocalOfficeManager officeManager = LocalOfficeManager.install(); 
		try {

		    // Start an office process and connect to the started instance (on port 2002).
		    officeManager.start();

		    // Convert
		    JodConverter
		             .convert(inputFile)
		             .to(outputFile)
		             .execute();
		} catch (OfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    // Stop the office process
		    OfficeUtils.stopQuietly(officeManager);
		}*/
	}

}
