<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Twitter Auth Callback</title>
</head>
<body>
<%-- Twitter OAuth 인증 콜백 결과를 부모창에 전달하고 데모 화면으로 이동시키는 중간 처리 페이지 --%>
<script type="text/javascript">
// 인증 결과를 부모창(opener)에 전달하고 성공 시 데모 화면으로 리다이렉트한다.
(function() {
    var isSuccess = <c:out value="${authSuccess}" default="false" />;
    var redirectUrl = "<c:url value='${redirectUrl}'/>";
    var message = isSuccess ? "TWITTER_AUTH_SUCCESS" : "TWITTER_AUTH_FAIL";

    if (window.opener && !window.opener.closed) {
        window.opener.postMessage(message, window.location.origin);
        if (isSuccess && redirectUrl) {
            window.opener.location.href = redirectUrl;
        }
        window.close();
        return;
    }

    if (isSuccess && redirectUrl) {
        window.location.replace(redirectUrl);
        return;
    }
    window.close();
})();
</script>
</body>
</html>



