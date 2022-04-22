<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge;"/>
<script type="text/javascript">
<c:if test="${not empty fn:trim(resultMessage) && resultMessage ne ''}">
alert("<c:out value='${resultMessage}'/>");
</c:if>
</script>
</head>
<body>
<form id="onepassForm" method="post" action="${redirectUrl}"></form>
</body>
<script type="text/javascript">
document.getElementById('onepassForm').submit();
</script>
</html>
