<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comCmm.left.3300"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wTableFrm">
	<h2><spring:message code="comCmm.left.3300"/> 검증 결과</h2>
	<div style="font-size: 20px; color: ${result ? 'blue' : 'red'};">${message}</div>
	<div class="btn"><input type="button" class="s_submit" value="돌아가기" onclick="document.location.href='<c:url value='/ext/captcha/input.do'/>';"></div>
	</div>
</body>
</html>
