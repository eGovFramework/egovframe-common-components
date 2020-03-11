<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle"><spring:message code="comExtMsg.webSocket.Title"/></c:set>

<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} into</title><!--WebSocket messenger into -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/ext/msg/button.css'/>"/>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function() {
		$('#connectMsgBtn').click(function() {
			var form = $("form[name=msgForm]");
			form.attr("action", "<c:url value='/cop/msg/websocketMessengerMain.do'/>");
			form.attr("method", "post");
			form.submit();
		});		
	});
</script>
</head>
<body>
<form name="msgForm" id="msgForm" action="<c:url value='  '/>" method="post">
	<c:if test="${loginVO!= null}">
		${loginVO.name}<spring:message code="comExtMsg.webSocketInto.msg1"/>  <!-- 님, 메신저에 접속 합니다. -->
		<input type="button" id="connectMsgBtn" name="connectMsgBtn" value="<spring:message code="comExtMsg.webSocket.btnInto"/>"/>
	</c:if>
	<c:if test="${loginVO==null}">
		<spring:message code="comExtMsg.webSocketInto.msg2"/> <br/> <!-- 접속 후 가능한 메뉴입니다.  -->
		<spring:message code="comExtMsg.webSocketInto.msg3"/> <!-- 로그인 후 이용해주시기 바랍니다. -->
	</c:if>
</form>
</body>
</html>