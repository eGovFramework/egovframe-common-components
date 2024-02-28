<%--
  Class Name : EgovTwitterRecptn.jsp
  Description : 트위터 수신(목록) 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.07    장동한          최초 생성
     2018.10.29    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.07

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%
String sCmd = request.getParameter("cmd") == null ? "": (String)request.getParameter("cmd");

String rtoken =  session.getAttribute("rstoken") == null ? "": (String)session.getAttribute("rstoken");
String rstoken = session.getAttribute("rstoken") == null ? "": (String)session.getAttribute("rstoken");

String atoken =  session.getAttribute("atoken") == null ? "": (String)session.getAttribute("atoken");
String astoken = session.getAttribute("astoken") == null ? "": (String)session.getAttribute("astoken");

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterMain.twitterMain"/></title><!-- 트위터(Twitter) 앱등록 안내 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
* 팝업창  오픈
******************************************************** */
function fn_egov_popupOpen_TwitterMain(url,name,width,height){
	var left = (screen.width-900)/2;
	var top = (screen.height-400)/2;

	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if(openWindows) {
		openWindows.focus();
	}
}

/* ********************************************************
* 초기화
******************************************************** */
function fn_egov_popupOpen_Init(){

	fn_egov_popupOpen_TwitterMain('<c:url value='/uss/ion/tir/selectTwitterPopup.do'/>?cmd=<c:out value="${sCmd}"/>','TwitterPopupAuth',900,300);
}

<%
if( !rtoken.equals("") && !rtoken.equals("") &&
		!atoken.equals("") && !astoken.equals("") ){

	if(sCmd.equals("RECPTN")){
		%>location.href = '<c:url value='/uss/ion/tir/listTwitterRecptn.do'/>';<%
	}else{
		%>location.href = '<c:url value='/uss/ion/tir/registTwitterTrnsmit.do'/>';<%
	}

}
%>
</script>
</head>
<body>
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonTir.twitterMain.twitterMain"/></h2><!-- 트위터(Twitter) 앱등록 안내 -->
	<sup><spring:message code="ussIonTir.twitterMain.version"/></sup>
	
	<ol class="twit_list mt20">
		<li>1) <a style="text-decoration: underline; color:blue;" href="https://developer.twitter.com" target="_blank"  title="새 창으로 이동" >https://developer.twitter.com</a> <spring:message code="ussIonTir.twitterMain.app"/></li><!-- 트위터 사이트에 로그인한다. -->
				<img src="<c:url value='/images/egovframework/com/uss/ion/tir/twitter_developer.png'/>" alt="트위터 어플 등록 사이트 이미지" title="트위터 어플 등록 사이트">
		<li>2) Developer Portal  <spring:message code="ussIonTir.twitterMain.buttonClick"/><br><!-- 버튼을 클릭하고 등록 페이지에 아래와 같이 트위터 어플리케이션 서비스를 등록한다. -->
			<ul style = "margin: 20px 0;">
				<h3 style="margin: 10px 0;">※<spring:message code="ussIonTir.twitterMain.notice" />※</h3>
				<li>- App Permissions : Read and Write</li>
				<li>- Type of App : Navtive App</li>
				<li>- CallBack URI : http://common.egovframe.go.kr/uss/ion/tir/selectTwitterPopupProcess.do<spring:message code="ussIonTir.twitterMain.returnUrl"/></li><!-- (키 발급 후 리턴되는 URL) -->
			</ul>
		<li>3) <spring:message code="ussIonTir.twitterMain.key"/><br><!-- Consumer key, Consumer secret 등이 아래와 같이 발급된다. -->
	</ol>
		<span class="btn_s"><a onclick = "fn_egov_popupOpen_Init()"><spring:message code="ussIonTir.twitterMain.auth"/></a></span>
</div>

</DIV>
</body>
</html>
