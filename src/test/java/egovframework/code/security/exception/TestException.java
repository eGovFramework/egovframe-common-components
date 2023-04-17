package egovframework.code.security.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.service.EgovProperties;

/**
 * TestException Test Class 구현
 * Catch a list of specific exception subtypes instead.
 * 
 * @author 표준프레임워크 신용호
 * @since 2022.11.11
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.11.11  신용호          최초 생성
 *
   
 * </pre>
 */

public class TestException {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestException.class);
	
	public static void main(String[] args) {
		
		System.setProperty("my.active", "OK");
		try {
			System.setProperty("my.active", EgovProperties.getProperty("egov.none.id"));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LOGGER.error("[" + e.getClass() +"] search fail : " + e.getMessage());
		}
	}

}
