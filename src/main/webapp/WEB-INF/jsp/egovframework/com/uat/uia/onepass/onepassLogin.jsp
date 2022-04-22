<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge;"/>
</head>
<body>
<form id="onepassForm" method="post" action="${redirectUrl}">
<input type="hidden" name="${inputName}" value="${inputValue}"/>
<input type="hidden" name="pageType" value="${pageType}"/>
</form>
</body>
<script type="text/javascript">
document.getElementById('onepassForm').submit();
</script>
</html>
