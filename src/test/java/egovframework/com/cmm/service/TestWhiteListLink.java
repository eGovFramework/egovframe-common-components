package egovframework.com.cmm.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 화이트 리스트를 체크하는 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.25
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.04.25  신용호          최초 생성
 *
 * @ testWhiteListTrue()
 *   - 링크 화이트 리스트 성공 테스트 : true인경우 성공
 *     => 존재하는 링크를 적용하여 검증
 *   
 * @ testWhiteListFalse()
 *   - 링크 화이트 리스트 성공 테스트 : false인경우 성공
 *     => 존재하지 않는 링크를 적용하여 검증
 * 
 */

public class TestWhiteListLink {

	protected Logger egovLogger = LoggerFactory.getLogger(TestWhiteListLink.class);
	
	@Test
	public void testWhiteListTrue() {
		//fail("Not yet implemented");

		//egovLogger.debug(">>> "+EgovProperties.class.getResource("").getPath());
		//EgovWhiteList.RELATIVE_PATH_PREFIX = "C:/eGovFrameDev-3.7.0-64bit_dev/workspace/egovframework-all-in-one-AllNew/target/classes/egovframework";
		egovLogger.debug(">>> TEST RELATIVE_PATH_PREFIX = "+EgovWhiteList.RELATIVE_PATH_PREFIX);
		
		String link = "/egovframework/com/main_bottom";
		egovLogger.debug("===>>> link = "+link);

		boolean resultTrue = EgovWhiteList.check(link);
		egovLogger.debug("===>>> result = "+resultTrue);
		
		boolean resultTrueNew = EgovWhiteList.checkNew(link);
		egovLogger.debug("===>>> resultNew = "+resultTrueNew);

		assertTrue(resultTrue);
		
	}
	
	@Test
	public void testWhiteListFalse() {

		String link = "/egovframework/com/main_bottomXXXXX";
		egovLogger.debug("===>>> link = "+link);
		
		boolean resultFalse = EgovWhiteList.check(link);
		egovLogger.debug("===>>> result = "+resultFalse);
		
		boolean resultFalseNew = EgovWhiteList.checkNew(link);
		egovLogger.debug("===>>> resultNew = "+resultFalseNew);
		
		assertFalse(resultFalse);
	}
	
	// 주석처리 인식여부
	@Test
	public void testWhiteListRemarkFalse() {

		String link = "#utl/sys/wsi/EgovWebStandardInspectionUriDirectLink";
		egovLogger.debug("===>>> link = "+link);

		boolean resultFalse = EgovWhiteList.check(link);
		egovLogger.debug("===>>> result = "+resultFalse);
		
		boolean resultFalseNew = EgovWhiteList.checkNew(link);
		egovLogger.debug("===>>> resultNew = "+resultFalseNew);
		
		assertFalse(resultFalse);
	}

}
