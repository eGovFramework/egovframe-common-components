<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.03    박지욱          최초 생성
  * @ 2011.09.25    서준식          사용자 관리 패키지가 미포함 되었을때에 회원가입 오류 메시지 표시
  * @ 2011.10.27    서준식          사용자 입력 탭 순서 변경
  * @ 2017.07.21    장동한 	    	로그인인증제한 작업
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="comUatUia.title" /></title><!-- 로그인 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/login.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<script type="text/javaScript">

function fnInit() {

    <c:if test="${not empty fn:trim(message) &&  message ne ''}">
    alert("${message}");    
    </c:if>
    
}

</script>
</head>
<body onLoad="fnInit();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


<!-- 일반로그인 -->
<div class="login_form">
	<input type="hidden" id="message" name="message" value="<c:out value='${message}'/>">
	
	<fieldset>
		<img src="<c:url value='/images/egovframework/com/uat/uia/login_tit.png'/>" style="margin:30px 0 0px 60px" alt="login title image"  title="login title image">
		<br><br><br><br><br>
		<div class="login_input">
			<ul>
				<li>
					<b>OAuth Sign-in Result : <c:out value='${message}'/></b>
				</li>
				
			</ul>
		</div>
		
		<div class="login_input" style="display: none">
			<ul>
				<li>
					<label for="password"><spring:message code="comUatUia.loginForm.pw"/></label><!-- 비밀번호 -->
					<input type="password" name="pwd" id="" maxlength="20" title="${title} ${inputTxt}" placeholder="<spring:message code="comUatUia.loginForm.pw"/>"><!-- 비밀번호 -->
				</li>
				<li>
					<input type="button" class="btn_login" value="<spring:message code="comUatUia.loginForm.login.gpki"/>" onclick="actionLogin()"><!-- 인증서로그인 -->
				</li>
				<li>
					<ul class="btn_idpw" >
						<li><a href="#" onclick="fnShowLogin(0);"><spring:message code="comUatUia.loginForm.login.normal"/></a></li><!-- 일반로그인 -->
					</ul>
					<ul class="btn_idpw" >
						<li>※ <spring:message code="comUatUia.loginForm.gpki.descrption"/></li>
					</ul>
				</li>
			</ul>
			
		</div>
	</fieldset>
</div>

</body>
</html>


