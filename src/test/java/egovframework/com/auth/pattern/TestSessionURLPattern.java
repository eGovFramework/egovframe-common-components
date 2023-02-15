package egovframework.com.auth.pattern;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.egovframe.rte.fdl.access.interceptor.EgovAccessUtil;

/**
 * @TestSessionURLPattern Test Class 구현 (AntPattern 검증 및 RegexPattern URL 검증)
 * @author 표준프레임워크 신용호
 * @since 2019.12.23
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.12.23  신용호          최초 생성
 *
   
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations = { 
		"file:src/main/resources/egovframework/spring/com/context-*.xml",
		"file:src/main/resources/egovframework/spring/com/idgn/context-*.xml"
})*/
@ContextConfiguration(locations={"classpath*:egovframework/spring/session/*.xml"})
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
    public void testAntMatcher() {
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
    public void testRegexMatcher() {
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
