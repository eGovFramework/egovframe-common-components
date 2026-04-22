<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comCmmErr.accessDenied.code"/></c:set><!-- 사용자접근권한 에러 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><spring:message code="title.html"/></title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css" />

<script language="javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
<script>
// 2026.04.22 추가
(function () {
  try {
    var topFrame = parent && parent.frames ? parent.frames["_top"] : null;
    if (!topFrame || !topFrame.document) return;
    // leftTimeInfo가 없으면(로그인 직후 미갱신 상태) 그때만 갱신
    var hasTimer = topFrame.document.getElementById("leftTimeInfo") !== null;
    if (!hasTimer) {
      topFrame.location.reload();
    }
  } catch (e) {
    // cross-frame 예외 무시
  }
})();
</script>

</head>
<body>
<div style="width: 1000px; margin: 50px auto 50px;">
	<p style="font-size: 18px; color: #000; margin-bottom: 10px; "><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" /></p>
	<div style="border: ppx solid #666; padding: 20px;">
		
		<p style="color:red; margin-bottom: 8px; ">${pageTitle}<br /></p>

		<div class="boxType1" style="width: 700px;">
			<div class="box">
				<div class="error">
					<p class="title"><spring:message code="comCmmErr.accessDenied.title" /></p><!-- 현재 페이지에 대한 접근권한이 없습니다! -->
					<p class="cont mb20">${pageTitle}<br /></p>
					<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();"><spring:message code="comCmmErr.button" /><!-- 이전 페이지 --></a></span>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>