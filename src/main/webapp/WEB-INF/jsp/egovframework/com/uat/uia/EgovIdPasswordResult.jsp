<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovIdPasswordResult.jsp
  * @Description : 아이디/비밀번호 찾기 결과화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.17    박지욱          최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.17
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" type="text/css">
<c:set var="pageTitle"><spring:message code="comUatUia.idPw.title"/>  <spring:message code="comUatUia.idPw.result"/></c:set>
<title>${pageTitle}</title>
<script>
/* ********************************************************
 * 뒤로 처리 함수
 ******************************************************** */
function fncGoAfterPage(){
    history.back(-2);
}

function fncGoIdPwd(){
    location.href="<c:url value='/uat/uia/egovIdPasswordSearch.do'/>";
}

function fncGoLogin(){
	location.href="<c:url value='/uat/uia/egovLoginUsr.do'/>";
}

</script>
</head>
<body>
	<div style="width: 1000px; margin: 50px auto 50px;">
	<p style="font-size: 18px; color: #000; margin-bottom: 10px; "><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" /></p>
	<div style="border: 0px solid #666; padding: 20px;">
		<p style="color:red; margin-bottom: 8px; "></p>

		<div class="boxType1" style="width: 500px;">
			<div class="box">
				<div class="error">
					<p class="title">${pageTitle}</p>
					<p class="cont mb20">${resultInfo}<br /></p>
					<span class="btn_style1 blue"><a href="javascript:fncGoIdPwd();"><spring:message code="comUatUia.idPasswordResult.searchIdPwd" /></a></span><!-- 아이디 및 비밀번호 찾기 -->
					<span class="btn_style1 blue"><a href="javascript:fncGoLogin();"><spring:message code="comUatUia.loginForm.login" /></a></span><!-- 로그인 -->
				</div>
			</div>
		</div>
	</div>

	</div>

</body>
</html>

