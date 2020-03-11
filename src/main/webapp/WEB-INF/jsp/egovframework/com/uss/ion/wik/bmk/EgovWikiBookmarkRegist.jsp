<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head><title></title>
<script language=javascript>
<c:if test="${S_DUPL eq 'N'}">
	alert( decodeURI('%EC%9C%84%ED%82%A4%20%EB%B6%81%EB%A7%88%ED%81%AC%EC%97%90%20%EC%B6%94%EA%B0%80%20%EB%90%98%EC%97%88%EC%8A%B5%EB%8B%88%EB%8B%A4!') );
</c:if>

<c:if test="${S_DUPL eq 'Y'}">
	alert( decodeURI('%EC%9D%B4%EB%AF%B8%20%EC%B6%94%EA%B0%80%EB%90%9C%20%EB%B6%81%EB%A7%88%ED%81%AC%20%EC%9E%85%EB%8B%88%EB%8B%A4!') );
</c:if>
</script>
</head>
<body>
OK
</body>
</html>