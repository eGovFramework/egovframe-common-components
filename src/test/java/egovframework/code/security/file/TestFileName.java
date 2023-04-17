package egovframework.code.security.file;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.code.security.exception.TestException;

/**
 * TestFileName Test Class 구현
 * 파일명 추출 테스트
 * 
 * @author 표준프레임워크 신용호
 * @since 2022.11.16
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.11.16  신용호          최초 생성
 *
   
 * </pre>
 */


public class TestFileName {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestException.class);
	
	public static void main(String[] args) {
		
		String file1 = "../../../config/test.png";
		String file2 = "../../../config/overide_file";
		String file3 = "shell.jsp\u0000expected.gif";
		String fn1 = FilenameUtils.getName(file1);
		String fn2 = FilenameUtils.getName(file2);
		String fn3 = "-";
		try {
			fn3 = FilenameUtils.getName(file3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.debug("safe filename1 = "+fn1);
		LOGGER.debug("safe filename2 = "+fn2);
		LOGGER.debug("safe filename3 = "+fn3);
		
	}

}
