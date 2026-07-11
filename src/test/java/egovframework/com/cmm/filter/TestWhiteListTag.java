package egovframework.com.cmm.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * HTMLTagFilter whiteList처리를 위한 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.01.31
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *   2019.01.31  신용호          최초 생성
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * </pre>
 */
@Slf4j
public class TestWhiteListTag {

	// Tag 화이트 리스트 ( 허용할 태그 등록 )
	static private String[] whiteListTag = { "<p>","</p>" };
	
	public static void main(String[] args) {
		String paramData = ">Hello world <p>test</p>. <script>alert('OK');</script> <br> Good day~!<";
		log.debug("paramData = {}", paramData);
		String safeParamData = getSafeParamData(paramData);
		log.debug("safeParamData = {}", safeParamData);
		
	}

	static private String getSafeParamData(String value) {
		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '<':
				if ( checkNextWhiteListTag(i, value) == false )
					strBuff.append("&lt;");
				else 
					strBuff.append(c);
				log.debug("checkNextWhiteListTag = {}", checkNextWhiteListTag(i, value));
				break;
			case '>':
				if ( checkPrevWhiteListTag(i, value) == false )
					strBuff.append("&gt;");
				else 
					strBuff.append(c);
				log.debug("checkPrevWhiteListTag = {}", checkPrevWhiteListTag(i, value));
				break;
			//case '&':
			//	strBuff.append("&amp;");
			//	break;
			case '"':
				strBuff.append("&quot;");
				break;
			case '\'':
				strBuff.append("&apos;");
				break;	
			default:
				strBuff.append(c);
				break;
			}
		}
		
		value = strBuff.toString();
		return value;
	}

	static private boolean checkNextWhiteListTag(int index, String data) {
		log.debug("[checkNextWhiteListTag]---------------------------------------------");
		String extractData = "";
		//int beginIndex = 0;
		int endIndex = 0;
		for(String whiteListData: whiteListTag) {
		    log.debug("===>>> whiteListData={}", whiteListData);
			endIndex = index+whiteListData.length();
		    if ( data.length() > endIndex )
		    	extractData = data.substring(index, endIndex);
		    else
		    	extractData = "";
		    log.debug("extractData={}", extractData);
		    if ( whiteListData.equals(extractData) ) return true; // whiteList 대상으로 판정
		}
		
		return false;
	}
	
	static private boolean checkPrevWhiteListTag(int index, String data) {
		log.debug("[checkPrevWhiteListTag]---------------------------------------------");
		String extractData = "";
		int beginIndex = 0;
		int endIndex = 0;
		for(String whiteListData: whiteListTag) {
		    log.debug("===>>> whiteListData={}", whiteListData);
			beginIndex = index-whiteListData.length()+1;
			endIndex = index+1;
		    log.debug("  range [{} ~ {}]", beginIndex, endIndex);
		    if ( beginIndex >= 0 )
		    	extractData = data.substring(beginIndex, endIndex);
		    else
		    	extractData = "";
		    log.debug("extractData={}", extractData);
		    if ( whiteListData.equals(extractData) ) return true; // whiteList 대상으로 판정
		}
		
		return false;
	}
}
