package egovframework.com.auth.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.egovframe.rte.fdl.access.interceptor.EgovAccessUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @TestSessionURLPattern Test Class 구현 (AntPattern 검증 및 RegexPattern URL 검증)
 * @author 표준프레임워크 신용호
 * @since 2019.12.23
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2019.12.23  신용호      최초 생성
 *  2026.01.26  신용호      JUnit 4 => JUnit 5 마이그레이션 및 Spring 컨텍스트 제거
 *                             (EgovAccessUtil의 정적 메서드만 사용하므로 Spring 컨텍스트 불필요)
 *
 *   
 * </pre>
 */
public class TestSessionURLPattern {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSessionURLPattern.class);
	
	private String[] testAntPatternURL = {"/cop/stf/selectSatisfactionList.do"
			,"/cop/cmt/selectArticleCommentList.do"
			,"/cmm/fms/getImage.do"};
	private String[] antPatternRule = {"/cop/stf/**"
			,"/cop/cmt/**"
			,"/cmm/fms/**"};

	private String[] testRegexPatternURL = {"/cop/stf/selectSatisfactionList.do"
			,"/cop/cmt/selectArticleCommentList.do"
			,"/cop/cmt/test.inc"
			//,"/cop/cmt/test.inx"
			};
	private String[] regexPatternRule = {"\\A/.*\\.do.*\\Z"
			,"\\A/.*\\.inc.*\\Z"};

    @Test
    void testAntMatcher() {
    	int totalUrlCount = testAntPatternURL.length;
    	int checkOkCount = 0;
    	LOGGER.debug("===>>> totalUrlCount(AntMatcher) = "+totalUrlCount);
    	
    	for(String testAntUrl : testAntPatternURL) {
	    	for(String antRule : antPatternRule) {
	    		boolean matchStatus = EgovAccessUtil.antMatcher(antRule, testAntUrl);
	    		if (matchStatus) {
	    	    	LOGGER.debug("===>>> URL access allowed(AntMatcher) = "+testAntUrl);
	    			checkOkCount++;
	    			break;
	    		}
	    	}
    	}
    	
    	LOGGER.debug("===>>> checkOkCount(AntMatcher) = "+checkOkCount);
    	assertEquals(totalUrlCount, checkOkCount);
    }
    
    @Test
    void testRegexMatcher() {
    	int totalUrlCount = testRegexPatternURL.length;
    	int checkOkCount = 0;
    	LOGGER.debug("===>>> totalUrlCount(RegexMatcher) = "+totalUrlCount);
    	
    	for(String testRegexUrl : testRegexPatternURL) {
	    	for(String regexRule : regexPatternRule) {
	    		boolean matchStatus = EgovAccessUtil.regexMatcher(regexRule, testRegexUrl);
	    		if (matchStatus) {
	    	    	LOGGER.debug("===>>> URL access allowed(RegexMatcher) = "+testRegexUrl);
	    			checkOkCount++;
	    			break;
	    		}
	    	}
    	}
    	
    	LOGGER.debug("===>>> checkOkCount(RegexMatcher) = "+checkOkCount);
    	assertEquals(totalUrlCount, checkOkCount);
    }
    
}
