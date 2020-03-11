<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>eGovFrame 공통 컴포넌트</title>
</head>

<frameset frameborder="0" framespacing="0" rows="75, *, 45">
	<frame name="_top" src="${pageContext.request.contextPath}/EgovTop.do" scrolling="no" title="헤더">
		<frameset frameborder="0" framespacing="0" cols="20%, 80%">
			<frame name="_left" src="${pageContext.request.contextPath}/EgovLeft.do" scrolling="yes" title="메뉴페이지">
			<frame name="_content" src="${pageContext.request.contextPath}/EgovContent.do" title="메인페이지">
		</frameset>
	<frame name="_bottom" src="${pageContext.request.contextPath}/EgovBottom.do" scrolling="no" title="푸터">
</frameset>
