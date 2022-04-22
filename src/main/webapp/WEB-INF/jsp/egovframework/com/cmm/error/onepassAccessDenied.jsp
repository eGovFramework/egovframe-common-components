<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : onepassAccessDenide.jsp
  * @Description : 디지털원패스 인증오류 화면
  * @Modification Information
  * 
  * @수정일       수정자           수정내용
  * ----------   --------   ---------------------------
  *  2021.05.30   정진오           최초 생성
  *
  */
%>
<c:set var="pageTitle"><spring:message code="digital.onepass.access.denied.code"/></c:set><!-- 디지털원패스 에러 -->
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
</head>
<body>
<div style="width: 1000px; margin: 50px auto 50px;">
	<p style="font-size: 18px; color: #000; margin-bottom: 10px; "><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" /></p>
	<div style="border: ppx solid #666; padding: 20px;">
		
		<p style="color:red; margin-bottom: 8px; ">${pageTitle}<br /></p>

		<div class="boxType1" style="width: 700px;">
			<div class="box">
				<div class="onepass">
					<p class="title"><spring:message code="digital.onepass.access.denied.title" /></p><!-- 디지털원패스 인증에 필요한 파일이나 설정 등의 확인이 필요합니다. -->
					<p class="cont mb20">${pageTitle}<br /></p>
					<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();"><spring:message code="comCmmErr.button" /><!-- 이전 페이지 --></a></span>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>