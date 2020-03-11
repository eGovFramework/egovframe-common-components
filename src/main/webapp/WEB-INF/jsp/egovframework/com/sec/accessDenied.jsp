<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetailsService" %>

<%@ page import="egovframework.rte.fdl.string.EgovStringUtil" %>
<%@ page import="java.lang.String" %>
<%
    boolean authenticateFail = false;
    if(request.getAttribute("authenticateFail")!=null && !request.getAttribute("authenticateFail").toString().equals("")){
        authenticateFail = true;
    }

    boolean authFail = false;
    if(request.getAttribute("authFail")!=null && !request.getAttribute("authFail").toString().equals("")){
        authFail = true;
    }

    String target = EgovStringUtil.null2void((String)request.getAttribute("target"));
    target = target.equals("") ? "_top" : target;
%>
<c:set var="pageTitle"><spring:message code="comCmmErr.accessDenied.code"/></c:set><!-- 사용자접근권한 에러 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><spring:message code="title.html"/></title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css" />
<script language="javascript">
function fncGoAfterErrorPage(){
    if('<%=authenticateFail%>' == 'true' ){
        document.dummyForm.target="_top";
        document.dummyForm.action = "<c:url value='/empaftererrorpage.do'/>";
        document.dummyForm.submit();
    }else if('<%=authFail%>' == 'true'){
        document.dummyForm.target="<%=target%>";
        document.dummyForm.action = "<c:url value='/empaftererrorpage.do'/>";
        document.dummyForm.submit();
    }else{
        //document.location.href = "<c:url value='/empaftererrorpage.do'/>";
        history.back(-2);
    }
}
</script>
<body>
<div style="width: 1000px; margin: 50px auto 50px;">
	<p style="font-size: 18px; color: #000; margin-bottom: 10px; "><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" /></p>
	<div style="border: ppx solid #666; padding: 20px;">
		
		<p style="color:red; margin-bottom: 8px; ">${pageTitle}<br /></p>

		<div class="boxType1" style="width: 700px;">
			<div class="box">
				<div class="error">
					<p class="title">
	                 ${SPRING_SECURITY_403_EXCEPTION}
                    <br>
                    <%
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    Object principal = auth.getPrincipal();
                    if (principal instanceof UserDetails) {
                        String username = ((UserDetails) principal).getUsername();
                        String password = ((UserDetails) principal).getPassword();
                        out.println("Account : " + username.toString() + "<br>");
                    }
                    %>
					</p>
					<p class="cont mb20">${pageTitle}<br /></p>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>