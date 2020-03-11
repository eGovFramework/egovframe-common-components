<%--
  Class Name : govWebStandardInspectionUri.jsp
  Description : 웹표준검사
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.10.05    박종선          최초 생성

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

String sUri = request.getParameter("uri") == null ? "" : (String)request.getParameter("uri");
String sNum = request.getParameter("num") == null ? "" : (String)request.getParameter("num");

// 2011.10.25 보안점검 후속조치
sUri = EgovWebUtil.clearXSSMinimum(sUri);
sNum = EgovWebUtil.clearXSSMinimum(sNum);


BufferedReader buf = null;
String sMatcherFind = "";
int nLine = 0;
String[] arrMatcherFind;

try {

	URL urlCheck = new URL(sUri);
	urlCheck.openStream();

    //URLConnection 객체를 생성하기 위해 URL객체의 openConnection메소드 이용
    //반환형은 InputStream 1차 스트림 형태이므로 BufferedRead형태 사용 가능
    URL url = new URL("http://validator.w3.org/check?uri="+sUri);

    URLConnection uc = url.openConnection();
    uc.setRequestProperty("Cookie","JSESSIONID="+session.getId());


    buf = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
    String str = null;


    Pattern pattern;
    Matcher matcher;
    if(!sUri.equals("")){
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

