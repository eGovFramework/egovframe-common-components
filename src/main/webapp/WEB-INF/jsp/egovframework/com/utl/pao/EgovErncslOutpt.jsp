<%--
  Filename : ComUtlPaoErncslOutpt.jsp
  Description : 전자관인 출력 TEST JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.26    이중호          최초 생성
 
    author   : 이중호
    since    : 2009.02.26
   
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@page import="egovframework.com.utl.pao.service.EgovPrntngOutpt"  %>
<%@page import="java.util.*"  %>
<%@page import="java.io.*"  %>
<%@page import="javax.imageio.*"  %>
<%@page import="java.awt.*"  %>
<%@page import="java.awt.image.*"  %>

<link href="/css/egovframework/com/cmm/utl/com.css" rel="stylesheet" type="text/css">

<%
// 준비화면, 실행결과 출력화면의 구분
String execFlag = request.getParameter("execFlag");
String CmdStr   = "ComUtlPaoErncslOutpt";
if(execFlag==null || execFlag.equals("")) {
	execFlag="READY";
}
%>

<%	
if(!execFlag.equals("EgovPrntngOutpt")){
%>

<!-- 준비화면  시작-->
<form name="ready" action ="/utl/pao/EgovPrntngOutpt.do" method="post">
<input type = "hidden" name="execFlag" value="EgovPrntngOutpt">
<input type = "hidden" name="cmdStr" value="<%=CmdStr%>">
<table border="1">
	<tr>
		<td>
		기능설명:
		</td>
		<td>
			전자관인 출력<br>
		</td>
	</tr>
	<tr>
		<td>
		테스트:
		</td>
		<td>
			기관코드:10, 관인구분:1 로 관인이미지 확인
		</td>
	</tr>
 	<tr>
		<td>
		    기관코드 (10): 
		</td>
		<td> <input type = "text" name="sOrgCode"  size=10>
		</td>				
	</tr> 
 	<tr>
		<td>
		    관인구분 (1): 
		</td>
		<td> <input type = "text" name="sErncslSe"  size=2><input type = "button" method="post"  value="실행!" onclick="ready.submit()">
		</td>				
	</tr> 
	 
</table>
</form>
<!--  준비화면 끝 -->
<%
}
%>

<%	
if(execFlag.equals("EgovPrntngOutpt")){
%>
<%
//실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인
String	sOrgCode  = request.getParameter("sOrgCode");
String  sErncslSe = request.getParameter("sErncslSe");

%>

<!-- 결과화면 시작 -->
<form name="result" action ="/utl/pao/EgovPrntngOutpt.do">
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" value="<%=CmdStr%>">
<table border="1">
   	<tr>
   		<td>기관코드 : (<c:out value="${sOrgCode}"/>) 
   		</td>
   	</tr>
   	<tr>
   		<td>관인구분 : (<c:out value="${sErncslSe}"/>) 
   		</td>
   	</tr>
   	<tr>
   		<td>
			<img src="/utl/pao/EgovErncsl.do?sOrgCode=<c:out value="${sOrgCode}"/>&sErncslSe=<c:out value="${sErncslSe}"/>">
   		</td>
   	</tr>
</table> 
<br>
<input type = "button" method="post"  value="테스트로 돌아가기" onclick="result.submit()">
</form>
<!--  결과화면 끝 -->
<%
}
%>


<%--
테스트를 위한 테이블 정보

DROP TABLE IMGTEMP;

CREATE TABLE IMGTEMP (
	ORG_CODE VARCHAR(7) NULL, 
	ERNCSL_SE VARCHAR(2) NULL, 
	IMG_INFO MEDIUMBLOB NOT NULL,
	IMG_TYPE VARCHAR(16) NULL 
);

ALTER TABLE IMGTEMP
ADD  PRIMARY KEY (ORG_CODE,ERNCSL_SE)
;

--%>