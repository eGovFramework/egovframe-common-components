package egovframework.com.cmm.filter;

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
 *  2019.01.31  신용호          최초 생성
 *
 * </pre>
 */

public class TestWhiteListTag {

	// Tag 화이트 리스트 ( 허용할 태그 등록 )
	static private String[] whiteListTag = { "<p>","</p>" };
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String paramData = ">Hello world <p>test</p>. <script>alert('OK');</script> <br> Good day~!<";
		System.out.println("paramData = "+paramData);
		String safeParamData = getSafeParamData(paramData);
		System.out.println("safeParamData = "+safeParamData);
		
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
				System.out.println("checkNextWhiteListTag = "+checkNextWhiteListTag(i, value));
				break;
			case '>':
				if ( checkPrevWhiteListTag(i, value) == false )
					strBuff.append("&gt;");
				else 
					strBuff.append(c);
				System.out.println("checkPrevWhiteListTag = "+checkPrevWhiteListTag(i, value));
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
		System.out.println("[checkNextWhiteListTag]---------------------------------------------");
		String extractData = "";
		//int beginIndex = 0;
		int endIndex = 0;
		for(String whiteListData: whiteListTag) {
		    System.out.println("===>>> whiteListData="+whiteListData);
			endIndex = index+whiteListData.length();
		    if ( data.length() > endIndex )
		    	extractData = data.substring(index, endIndex);
		    else
		    	extractData = "";
		    System.out.println("extractData="+extractData);
		    if ( whiteListData.equals(extractData) ) return true; // whiteList 대상으로 판정
		}
		
		return false;
	}
	
	static private boolean checkPrevWhiteListTag(int index, String data) {
		System.out.println("[checkPrevWhiteListTag]---------------------------------------------");
		String extractData = "";
		int beginIndex = 0;
		int endIndex = 0;
		for(String whiteListData: whiteListTag) {
		    System.out.println("===>>> whiteListData="+whiteListData);
			beginIndex = index-whiteListData.length()+1;
			endIndex = index+1;
		    System.out.println("  range ["+beginIndex+" ~ "+endIndex+"]");
		    if ( beginIndex >= 0 )
		    	extractData = data.substring(beginIndex, endIndex);
		    else
		    	extractData = "";
		    System.out.println("extractData="+extractData);
		    if ( whiteListData.equals(extractData) ) return true; // whiteList 대상으로 판정
		}
		
		return false;
	}
}
