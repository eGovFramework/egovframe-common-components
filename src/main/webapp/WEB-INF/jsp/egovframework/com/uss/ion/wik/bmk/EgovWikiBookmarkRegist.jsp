<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonWikBmk.wikiBookmarkRegist.wikiBookmarkRegist"/></title>
<script type="text/javascript">
<c:if test="${S_DUPL eq 'N'}">
	alert('<spring:message code="ussIonWikBmk.wikiBookmarkRegist.successAdd"/>');
</c:if>

<c:if test="${S_DUPL eq 'Y'}">
	alert('<spring:message code="ussIonWikBmk.wikiBookmarkRegist.duplicateBookmark"/>');
</c:if>
</script>
</head>
<body>
OK
</body>
</html>