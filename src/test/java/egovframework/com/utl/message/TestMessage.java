package egovframework.com.utl.message;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import egovframework.com.utl.cas.service.EgovMessageUtil;

/**
 * 정보 메시지 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2022.06.14
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2022.06.14  신용호      최초 생성
 *
 * </pre>
 */


public class TestMessage {
	
	@Test
	public void testInfoMessage() {
		String message1 = null;
		String message2 = null;
		
		// 1. 정보메시지
		// 일반 정보 메시지 취득
		message1 = EgovMessageUtil.getInfoMsg("test.message");
		System.out.println("Info Message Test = "+message1);
		
		// 파라미터 처리 정보 메시지 취득 : String 배열의 값이 각각 {0}, {1}로 대치됨
		message2 = EgovMessageUtil.getInfoMsg("param.message", new String[] {"Information", "No expected value."});
		System.out.println("Info Message Test = "+message2);
		
		assertEquals(message1,"Hello, Test~!");
		assertEquals(message2,"Information : No expected value.");

	}

	@Test
	public void testWarnMessage() {
		String message1 = null;
		String message2 = null;
		
		// 2. 경고 메시지
		// 일반 경고 메시지 취득
		message1 = EgovMessageUtil.getWarnMsg("test.message");
		System.out.println("Warn Message Test = "+message1);
		
		// 파라미터 처리 경고 메시지 취득 : String 배열의 값이 각각 {0}, {1}로 대치됨
		message2 = EgovMessageUtil.getWarnMsg("param.message", new String[] {"Warn", "No expected value."});
		System.out.println("Warn Message Test = "+message2);
		
		assertEquals(message1,"Hello, Test~!");
		assertEquals(message2,"Warn : No expected value.");

	}

	@Test
	public void testErrorMessage() {
		String message1 = null;
		String message2 = null;
		
		// 3. 에러 메시지
		// 일반 에러 메시지 취득
		message1 = EgovMessageUtil.getErrorMsg("test.message");
		System.out.println("Error Message Test = "+message1);
		
		// 파라미터 처리 에러 메시지 취득 : String 배열의 값이 각각 {0}, {1}로 대치됨
		message2 = EgovMessageUtil.getErrorMsg("param.message", new String[] {"Error", "No expected value."});
		System.out.println("Error Message Test = "+message2);
		
		assertEquals(message1,"Hello, Test~!");
		assertEquals(message2,"Error : No expected value.");

	}

	@Test
	public void testConfirmMessage() {
		String message1 = null;
		String message2 = null;
		
		// 4. 확인 메시지
		// 일반 정보 메시지 취득
		message1 = EgovMessageUtil.getConfirmMsg("test.message");
		System.out.println("Confirm Message Test = "+message1);
		
		// 파라미터 처리 정보 메시지 취득 : String 배열의 값이 각각 {0}, {1}로 대치됨
		message2 = EgovMessageUtil.getConfirmMsg("param.message", new String[] {"Confirm", "No expected value."});
		System.out.println("Confirm Message Test = "+message2);
		
		assertEquals(message1,"Hello, Test~!");
		assertEquals(message2,"Confirm : No expected value.");

	}
	
}
