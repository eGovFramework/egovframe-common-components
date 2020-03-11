package egovframework.com.cmm;

import java.net.URLEncoder;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 웹브라우저 종류및 버전 파악하기 ( IE및 Edge, Safari, Chrome, Firefox, Opera )
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자              수정내용
 *  -----------  --------    ---------------------------
 *   2018.08.27  신용호              최초 생성
 *
 * </pre>
 */

public class EgovBrowserUtil {
	
	public static final String FIREFOX = "Firefox";
	public static final String SAFARI = "Safari";
	public static final String CHROME = "Chrome";
	public static final String OPERA = "Opera";
	public static final String MSIE = "MSIE";
	public static final String EDGE = "Edge";
	public static final String OTHER = "Other";
	
	public static final String TYPEKEY = "type";
	public static final String VERSIONKEY = "version";

	public static HashMap<String,String> getBrowser(String userAgent) {
		
		HashMap<String,String> result = new HashMap<String,String>();
		Pattern pattern = null;
		Matcher matcher = null;
		//System.out.println("=====>>>>> userAgent = "+userAgent);
		
		pattern = Pattern.compile("MSIE ([0-9]{1,2}.[0-9])");
		matcher = pattern.matcher(userAgent);
		if (matcher.find())
		{
		    result.put(TYPEKEY,MSIE);
		    result.put(VERSIONKEY,matcher.group(1));
			return result;
		}
		
		if (userAgent.indexOf("Trident/7.0") > -1) {
		    result.put(TYPEKEY,MSIE);
		    result.put(VERSIONKEY,"11.0");
		    return result;
		}
		
		pattern = Pattern.compile("Edge/([0-9]{1,3}.[0-9]{1,5})");
		matcher = pattern.matcher(userAgent);
		if (matcher.find())
		{
		    result.put(TYPEKEY,EDGE);
		    result.put(VERSIONKEY,matcher.group(1));
			return result;
		}
		
		pattern = Pattern.compile("Firefox/([0-9]{1,3}.[0-9]{1,3})");
		matcher = pattern.matcher(userAgent);
		if (matcher.find())
		{
		    result.put(TYPEKEY,FIREFOX);
		    result.put(VERSIONKEY,matcher.group(1));
			return result;		    
		}

		pattern = Pattern.compile("OPR/([0-9]{1,3}.[0-9]{1,3})");
		matcher = pattern.matcher(userAgent);
		if (matcher.find())
		{
		    result.put(TYPEKEY,OPERA);
		    result.put(VERSIONKEY,matcher.group(1));
			return result;		    
		}
		
		pattern = Pattern.compile("Chrome/([0-9]{1,3}.[0-9]{1,3})");
		matcher = pattern.matcher(userAgent);
		if (matcher.find())
		{
		    result.put(TYPEKEY,CHROME);
		    result.put(VERSIONKEY,matcher.group(1));
			return result;		    
		}
		
		pattern = Pattern.compile("Version/([0-9]{1,2}.[0-9]{1,3})");
		matcher = pattern.matcher(userAgent);
		if (matcher.find())
		{
		    result.put(TYPEKEY,SAFARI);
		    result.put(VERSIONKEY,matcher.group(1));
			return result;		    
		}

	    result.put(TYPEKEY,OTHER);
	    result.put(VERSIONKEY,"0.0");
		return result;
	}
	
	public static String getDisposition(String filename, String userAgent, String charSet) throws Exception {
		
		String encodedFilename = null;
		HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
		float version = Float.parseFloat(result.get(EgovBrowserUtil.VERSIONKEY));
		//System.out.println("=====>>>>> browser type = "+result.get(TYPEKEY));
		//System.out.println("=====>>>>> browser version = "+result.get(VERSIONKEY));
		//System.out.println("=====>>>>> filename = "+filename);
		
		if ( EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) && version <= 8.0f ) {
			encodedFilename = "Content-Disposition: attachment; filename="+URLEncoder.encode(filename, charSet).replaceAll("\\+", "%20");
		} else if ( EgovBrowserUtil.OTHER.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
			throw new RuntimeException("Not supported browser");
		} else {
			encodedFilename = "attachment; filename*="+charSet+"''"+URLEncoder.encode(filename, charSet);
		}
		
		return encodedFilename;
	}

	//KISA 보안약점 조치 (2018-10-29, 윤창원)
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testUserAgent[] = {
		// IE 7.0
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)"
		,"Mozilla/4.0 (Mozilla/4.0; MSIE 7.0; Windows NT 5.1; FDM; SV1; .NET CLR 3.0.04506.30)"
		// IE 8.0
		,"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)"
		// IE 9.0
		,"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)"
		// IE 10.0
		,"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)"
		// IE 11.0
		,"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"
		// Chrome 68.0.3440.106
		,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"
		// Edge 17.17134
		,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134"
		// Opera 55.0.2994.44
		,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36 OPR/55.0.2994.44"
		,"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 OPR/26.0.1656.60"
		// Firefox 61.0
		,"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0"
		// Safari 11.1.2
		,"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.2 Safari/605.1.15"
		// iPhone 11.0
		,"Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1"
		// iPad 9.0
		,"Mozilla/5.0 (iPad; CPU OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1"
		// Window Pohone 10
		,"Mozilla/5.0 (Windows Phone 10.0;  Android 4.2.1; Nokia; Lumia 520) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Mobile Safari/537.36 Edge/12.0"
		// Window Pohone 8.1
		,"Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 520) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537"
		// Window Pohone 8
		,"Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch)"
		// Window Pohone 7
		,"Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)"
		// XBOX One
		,"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0; Xbox; Xbox One)"
		// XBOX 360
		,"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; Xbox)"
		};
		
		HashMap<String,String> result = null;
		for (int i = 0; i < testUserAgent.length; i++) {
			result = getBrowser(testUserAgent[i]);
			System.out.println("1. User Agent : "+testUserAgent[i]);
			System.out.println("2. Browser Type : "+result.get(TYPEKEY));
			System.out.println("2. Browser Version : "+result.get(VERSIONKEY));
			//System.out.println("2. Browser Version convert: "+Float.parseFloat(result.get(VERSIONKEY)));
			System.out.println("");
		}

	}*/

}
