<%--
  Class Name : govWebStandardInspectionUri.jsp
  Description : 웹표준검사
  Modification Information

      수정일              수정자              수정내용
   ----------   --------    ---------------------------
   2010.10.05   박종선              최초 생성
   2020.10.29   신용호              KISA 보안약점 조치 (경로 조작 및 자원 삽입)

    author   : 박종선
    since    : 2010.10.05
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="none"%>
<%@page import="egovframework.com.utl.fcc.service.EgovNumberCheckUtil"%>
<%@page import="java.util.*"  %>
<%@page import="java.util.regex.*"  %>
<%@page import="java.io.*"  %>
<%@page import="java.net.*"  %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%

// [SSRF 수정] rawUri: URL 연산 전용 / sUri: 화면 출력(XSS 방어) 전용으로 분리
// clearXSSMinimum()은 '.'을 &#46;으로 변환하여 URL을 파괴하므로 URL 처리에 사용 불가.
// clearXSS()는 <, >, & 만 이스케이프하므로 URL 표시에 적합.
String rawUri = EgovWebUtil.removeCRLF(
    request.getParameter("uri") == null ? "" : (String)request.getParameter("uri")
);
String sNum = EgovWebUtil.clearXSSMinimum(
    request.getParameter("num") == null ? "" : (String)request.getParameter("num")
);
String sUri = EgovWebUtil.clearXSS(rawUri);

BufferedReader buf = null;
String sMatcherFind = "";
int nLine = 0;
String[] arrMatcherFind;

try {
	// [SSRF 검증: Public - 내부 IP 블랙리스트 방식]
	// 1. URL 파싱: 형식 오류 시 MalformedURLException → 하단 catch에서 처리
	URL parsedUrl = new URL(rawUri);
	// 2. 스키마 검증: http/https 외 file://, ftp:// 등 차단
	String scheme = parsedUrl.getProtocol();
	if (!"http".equals(scheme) && !"https".equals(scheme)) {
		throw new MalformedURLException("허용되지 않는 URL 스키마입니다.");
	}
	// 3. DNS 해석 후 내부 IP 차단 (DNS Rebinding 공격 방어)
	//    isLoopbackAddress: 127.x.x.x, ::1
	//    isSiteLocalAddress: 10.x, 172.16~31.x, 192.168.x (RFC 1918 사설 IP)
	//    isLinkLocalAddress: 169.254.x.x (AWS 메타데이터 서버 포함)
	//    isAnyLocalAddress: 0.0.0.0
	InetAddress addr = InetAddress.getByName(parsedUrl.getHost());
	if (addr.isLoopbackAddress() || addr.isSiteLocalAddress()
			|| addr.isLinkLocalAddress() || addr.isAnyLocalAddress()) {
		throw new UnknownHostException("내부 주소로의 요청은 허용되지 않습니다.");
	}
	// 4. 검증 완료: 서버가 직접 openStream() 하지 않고 validator에 uri 파라미터로만 전달.
	//    https로 통일, URLEncoder.encode()로 URI 안전 전달
	URL url = new URL("https://validator.w3.org/check?uri=" + URLEncoder.encode(rawUri, "UTF-8"));

	URLConnection uc = url.openConnection();
	uc.setRequestProperty("Cookie","JSESSIONID="+session.getId());

	buf = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
	String str = null;

	Pattern pattern;
	Matcher matcher;
	if(!rawUri.equals("")){
    while((str = buf.readLine())!=null){

	    	if(str != null){
    		//1차검색
    		pattern = Pattern.compile("\\sErrors*|\\swarning*|Passed");
    		matcher = pattern.matcher(str);

	        if(matcher.find()){sMatcherFind = str;break;}
    	}

    	if(nLine > 150){break;}

    	nLine++;
    }
    }



	   if(sMatcherFind.trim().equals("Passed")){

%>
<script type="text/javaScript" language="javascript">
 parent.document.getElementById("divErr<%=sNum%>").innerHTML = '0 Errors';
 parent.document.getElementById("divWar<%=sNum%>").innerHTML = '0 warning(s)';
</script>

<%

    	}else  if(!sMatcherFind.equals("")){


    		if(sMatcherFind.indexOf(",") > -1 ){
    		arrMatcherFind = sMatcherFind.split(",");
%>

<script type="text/javaScript" language="javascript">
 parent.document.getElementById("divErr<%=sNum%>").innerHTML = '<%=arrMatcherFind[0]%>';
 parent.document.getElementById("divWar<%=sNum%>").innerHTML = '<%=arrMatcherFind[1]%>';
</script>
	<%}else if(sMatcherFind.indexOf("E") > -1){ %>
	<script type="text/javaScript" language="javascript">
	 parent.document.getElementById("divErr<%=sNum%>").innerHTML = '<%=sMatcherFind%>';
	 parent.document.getElementById("divWar<%=sNum%>").innerHTML = '0 warning(s)';
	</script>
	<%}else if(sMatcherFind.indexOf("s") > -1){ %>
	<script type="text/javaScript" language="javascript">
	 parent.document.getElementById("divErr<%=sNum%>").innerHTML = '0 Errors';
	 parent.document.getElementById("divWar<%=sNum%>").innerHTML = '<%=sMatcherFind%>';
	</script>
	<%}%>
<%
  	 	}

} catch (java.net.UnknownHostException ex){
%>
	<script type="text/javaScript" language="javascript">
	 alert("입력하신 URL[<%=sUri%>] \n\n잘못 되었습니다!");
	</script>
<%
} catch (java.net.MalformedURLException ex){
	%>
		<script type="text/javaScript" language="javascript">
		 alert("입력하신 URL[<%=sUri%>] \n\n잘못 되었습니다!");
		</script>
	<%
} catch (Exception ex) {
    //ex.printStackTrace();
    throw new RuntimeException(ex);	// 2011.10.10 보안점검 후속조치
} finally {	// 2012.11 KISA 보안조치
	if (buf != null) {
		buf.close();
	}
}
%>

