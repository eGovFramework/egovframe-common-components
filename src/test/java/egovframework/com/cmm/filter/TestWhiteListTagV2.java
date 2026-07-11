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
public class TestWhiteListTagV2 {

	// Tag 화이트 리스트 ( 허용할 태그 등록 )
	static private String[] whiteListTag = { "<p>","</p>","<script>","</script>","<a>","</a>" };
	
	public static void main(String[] args) {
		String paramData = ">Hello world<p>test</p>"
						+"<script type='javascript'>alert('OK');</script>"
						+"<a href='http://www.abc.com'>mypage</a><br>"
						+"<a href='http://www.img.com'><img src='http://test.img.com'></a>"
						+ "Good day~!<";
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
		String extractData = searchTagForward(index, data);
		log.debug("checkNextWhiteListTag FIND = {}", extractData);
		
		int resultIndex = 0;
		String compareString = "";
		for(String whiteListData: whiteListTag) {
		    log.debug("===>>> whiteListData={}", whiteListData);
		    compareString = whiteListData.substring(0, whiteListData.length()-1);
		    log.debug("===>>> whiteListData compare String ={}", compareString);
		    resultIndex = extractData.indexOf(compareString);
		    log.debug("===>>> resultIndex ={}", resultIndex);
			if ( resultIndex == 0 )
		    	return true;
		}
		
		return false;
	}
	
	static private boolean checkPrevWhiteListTag(int index, String data) {
		log.debug("[checkPrevWhiteListTag]---------------------------------------------");
		String extractData = searchTagBackward(index, data);
		log.debug("checkPrevWhiteListTag FIND = {}", extractData);
		
		int resultIndex = 0;
		String compareString = "";
		for(String whiteListData: whiteListTag) {
		    log.debug("===>>> whiteListData={}", whiteListData);
		    compareString = whiteListData.substring(0, whiteListData.length()-1);
		    log.debug("===>>> whiteListData compare String ={}", compareString);
		    
		    resultIndex = extractData.indexOf(compareString);
		    log.debug("===>>> resultIndex ={}", resultIndex);
			if ( resultIndex == 0 )
		    	return true;
		}
		
		return false;
	}
	
	static private String searchTagForward(int index, String data) {
		int endIndex = data.indexOf(">", index);
		if ( endIndex < 0 )
			log.debug("===>>> searchTagForward TAG= NOT FOUND");
		else {
			log.debug("===>>> searchTagForward TAG={}", data.substring(index,endIndex+1));
			return data.substring(index,endIndex+1);
		}
		return "";
	}
	
	static private String searchTagBackward(int index, String data) {
		int beginIndex = data.lastIndexOf("<", index);
		if ( beginIndex < 0 )
			log.debug("===>>> searchTagBackward TAG= NOT FOUND");
		else {
			log.debug("===>>> searchTagBackward TAG={}", data.substring(beginIndex,index+1));
			return data.substring(beginIndex,index+1);
		}
		return "";
	}
	
}
