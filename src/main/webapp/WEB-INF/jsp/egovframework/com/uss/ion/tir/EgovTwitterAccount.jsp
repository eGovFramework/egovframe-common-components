<%--
  Class Name : EgovTwitterAccount.jsp
  Description : 트위터(Twitter)-인증키ConsumerKey/ConsumerSecret) 관리
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.09.27    장동한          최초 생성
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.09.27
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
String sConsumerKey = request.getAttribute("consumerKey") == null ? "": (String)request.getAttribute("consumerKey");
String sConsumerSecret = request.getAttribute("consumerSecret") == null ? "": (String)request.getAttribute("consumerSecret");
%>
<%--  자바스크립트 메세지 출력 --%>
${reusltScript}
<!DOCTYPE html>
<html lang="ko">
<head>
<title>트위터 인증키ConsumerKey/ConsumerSecret) 관리</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css'/>">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css'/>">
<script type="text/javaScript" language="javascript">
//초기화
function fn_init_TwitterAccount(){


}
/* ********************************************************
* 전송 체크
******************************************************** */
function fn_egov_search_TwitterAccount(){
	var vFrom = document.twitterInfo;

	if(vFrom.ConsumerKey.value == ""){
		alert("Consumer key 를 입력해주세요!");
		vFrom.ConsumerKey.focus();
		return;
	}

	if(vFrom.ConsumerSecret.value == ""){
		alert("Consumer secret 를 입력해주세요!");
		vFrom.ConsumerSecret.focus();
		return;
	}

	vFrom.submit();
}
</script>
<style type="text/css">
.txaIpt80 {
width : 99%;
}

#divVerify {
font-size : 12px;
color : red;
}
</style>
</head>
<body>
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form id="twitterInfo" name="twitterInfo" action="/uss/ion/tir/selectTwitterAccount.do" method="post" enctype="multipart/form-data">
<!--  타이틀  -->
<div class="title_left" style="width:100%;margin:0 0 0 0;">
	<h1><img src="<c:url value="/images/egovframework/com/cmm/icon/tit_icon.gif"/>" width="16" height="16" hspace="3" style="vertical-align:middle; display:inline-block;" alt=" ">&nbsp;트위터 인증키(ConsumerKey/ConsumerSecret) 관리</h1>
</div>

<!--  줄간격조정  -->
<div style="width:100%;border:solid 0px #000000;height:3px"></div>

<!--  등록폼  -->
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="트위터  입력을 제공한다">
<caption>트위터  입력을 제공한다</caption>
<tr> 
	<th scope="row" width="20%" height="23" class="required_text"><label for="ConsumerKey">Consumer key</label><img src="<c:url value="/images/egovframework/com/cmm/icon/required.gif"/>" alt="필수입력" title="필수입력" width="15" height="15"></th>
	<td width="80%">
		<input type="text" id="ConsumerKey" name="ConsumerKey" title="ConsumerKey" value="<c:out value="${sConsumerKey}"/>" size="10" maxlength="255" class="txaIpt80">
	</td>
</tr>
<tr> 
	<th scope="row" width="20%" height="23" class="required_text"><label for="ConsumerSecret">Consumer secret</label><img src="<c:url value="/images/egovframework/com/cmm/icon/required.gif"/>" alt="필수입력" title="필수입력" width="15" height="15"></th>
	<td width="80%">
		<input type="password" id="ConsumerSecret" name="ConsumerSecret" title="ConsumerSecret" value="<c:out value="${sConsumerSecret}"/>" size="10" maxlength="255" class="txaIpt80">
	</td>
</tr>
</table>

<!--  줄간격조정  -->
<div style="width:100%;border:solid 0px #000000;height:3px"></div>

<center>
<!--  목록/저장버튼  -->
<table border="0" cellspacing="0" cellpadding="0" align="center" summary="전송 버튼을 제공한다">
<caption>전송 버튼을 제공한다</caption>
<tr> 
	<th scope="row"> </th>	
	<td><img src="<c:url value="/images/egovframework/com/cmm/btn/bu2_left.gif"/>"  width="8" height="20" alt="버튼이미지"></td>
	<td class="btnBackground" nowrap><input type="submit" value="저장" onclick="fn_egov_search_TwitterAccount(); return false;" class="btnNew" style="height:20px;width:26px;" > 
	</td>
	<td><img src="<c:url value="/images/egovframework/com/cmm/btn/bu2_right.gif"/>"  width="8" height="20" alt="버튼이미지"></td>      
</tr>
</table>
</center>
<input type="hidden" name="cmd" value="">
</form>
<br>
<br>

<div class="title_left" style="width:100%;margin:0 0 0 0;">
	<img src="<c:url value="/images/egovframework/com/cmm/icon/tit_icon.gif"/>" width="7" hspace="3" style="vertical-align:middle; display:inline-block;" alt=" ">&nbsp;트위터 OAuth 인증키 발급절차
</div>

<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="인증키 발급 절차를 제공한다">
<caption>인증키 발급 절차를 제공한다</caption>
<tr>
	<th scope="col"> </th>
	<th scope="col"> </th>
</tr>
<tr>
	<td colspan="2">
		테스트 Consumer key : saaeVLrxerfGqBaTO2SQQ<br>
		테스트 Consumer secret : wKMiMTnO44Bs4znEkx9NP1Aag4klPX5tisfccrokdxg<br>
	</td>
</tr>
<tr>
	<td width="20px" valign="top">1)</td>
	<td><a href="http://twitter.com/apps" target="_blank"  title="새 창으로 이동" >http://twitter.com/apps</a> 트위터 사이트에 로그인한다.</td>
</tr>
<tr>
	<td width="20px" valign="top">2)</td>
	<td>
	Register a new application >> 버튼을 클릭하고 등록 페이지에 아래와 같이 트위터 어플리케이션 서비스를 등록한다.<br>
	<img src="<c:url value="/images/egovframework/com/uss/ion/tir/twitter_regist.jpg"/>" alt="트위터 어플 등록 이미지" title="트위터 어플 등록 이미지">
	<br>※ Callback URL: http://common.egovframe.go.kr/uss/ion/tir/selectTwitterPopupProcess.do
	<br>(키 발급 후 리턴되는 URL)<br><br>
	</td>
</tr>
<tr>
	<td width="20px" valign="top">3)</td>
	<td> 
	Consumer key, Consumer secret 등이 아래와 같이 발급된다.<br> 
	<img src="<c:url value="/images/egovframework/com/uss/ion/tir/twitter_regist_detail.jpg"/>" alt="트위터 어플 등록 완료 이미지" title="트위터 어플 등록 완료 이미지">
	</td>
</tr>
</table>

</DIV>
</body>
</html>
