<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/main_portal.css' />">
<title><spring:message code="comSymMnuMpm.main_bottom.mainBottomTitle"/></title><!-- 아래메인 -->
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight= "0">
    <div id="new_footer">
    	<ul>
        	<li style="float:left"><img src="<c:url value='/images/egovframework/com/cmm/main/bottom_logo.png' />" alt="안전행전부" /></li>
    		<li style="font-size:11px; float:left; margin: 10px"><spring:message code="comSymMnuMpm.main_bottom.address"/></p>
    		© Ministry of the Interior and Safety. All rights reserved.</li>
    	</ul>
    </div>
</body>
</html>