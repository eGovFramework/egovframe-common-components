<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/main.css' />">
<title>eGovFrame 공통 컴포넌트</title>
</head>
<body>
<div id="header">
	<div class="header_box"> 
	<h1>
		<a href="<c:url value='/EgovContent.do' />" target="_content"><img src="<c:url value='/images/egovframework/com/cmm/main/top_logo.png' />" alt="eGovframe"></a>
	</h1>
	<div style="margin-top:4px;"><strong class="top_title_strong"><spring:message code="comCmm.top.title"/></strong></div>
	</div>
</div>
</body>
</html>