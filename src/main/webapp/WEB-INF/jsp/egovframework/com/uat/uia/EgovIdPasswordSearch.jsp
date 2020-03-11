<%
 /**
  * @Class Name : EgovIdPasswordSearch.jsp
  * @Description : 아이디/비밀번호 찾기 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.09    박지욱          최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUatUia.idPw.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/idpw.css' />">
<script>

function fnCheckUsrId(userSe) {
	// 일반회원
	if (userSe == "GNR") {
		document.getElementById("idGnr").className = "on";
		document.getElementById("idEnt").className = "";
		document.getElementById("idUsr").className = "";
		document.idForm.userSe.value = "GNR";
	// 기업회원
	} else if (userSe == "ENT") {
		document.getElementById("idGnr").className = "";
		document.getElementById("idEnt").className = "on";
		document.getElementById("idUsr").className = "";
		document.idForm.userSe.value = "ENT";
	// 업무사용자
	} else if (userSe == "USR") {
		document.getElementById("idGnr").className = "";
		document.getElementById("idEnt").className = "";
		document.getElementById("idUsr").className = "on";
		document.idForm.userSe.value = "USR";
	}
}

function fnCheckUsrPassword(userSe) {
	// 일반회원
	if (userSe == "GNR") {
		document.getElementById("pwGnr").className = "on";
		document.getElementById("pwEnt").className = "";
		document.getElementById("pwUsr").className = "";
		document.passwordForm.userSe.value = "GNR";
	// 기업회원
	} else if (userSe == "ENT") {
		document.getElementById("pwGnr").className = "";
		document.getElementById("pwEnt").className = "on";
		document.getElementById("pwUsr").className = "";
		document.passwordForm.userSe.value = "ENT";
	// 업무사용자
	} else if (userSe == "USR") {
		document.getElementById("pwGnr").className = "";
		document.getElementById("pwEnt").className = "";
		document.getElementById("pwUsr").className = "on";
		document.passwordForm.userSe.value = "USR";
	}
}

function fnSearchId() {
	if (document.idForm.name.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.name" />");
	} else if (document.idForm.email.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.email" />");
	} else {
		document.idForm.submit();
	}
}

function fnSearchPassword() {
	if (document.passwordForm.id.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.id" />");
	} else if (document.passwordForm.name.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.name" />");
	} else if (document.passwordForm.email.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.email" />");
	} else if (document.passwordForm.passwordHint.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.passwordHint" />");
	} else if (document.passwordForm.passwordCnsr.value =="") {
		alert("<spring:message code="comUatUia.idPw.validate.passwordCnsr" />");
	} else {
		document.passwordForm.submit();
	}
}

</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="idpw_form">

	<!-- 아이디 찾기 -->
	<fieldset class="id_search">
	<form name="idForm" action ="<c:url value='/uat/uia/searchId.do'/>" method="post">
		<legend><spring:message code="comUatUia.idPw.searchId" /></legend>
		<h2><spring:message code="comUatUia.idPw.searchId" /></h2>
		<div class="login_type">
			<ul>
				<li><a id="idGnr" onClick="fnCheckUsrId('GNR');" class="on"><spring:message code="comUatUia.idPw.gnr" /></a></li>
				<li><a id="idEnt" onClick="fnCheckUsrId('ENT');"><spring:message code="comUatUia.idPw.ent" /></a></li>
				<li><a id="idUsr" onClick="fnCheckUsrId('USR');"><spring:message code="comUatUia.idPw.usr" /></a></li>
			</ul>
		</div>
		<div class="login_input">
			<ul>
				<li>
					<label for="name"><spring:message code="comUatUia.idPw.name" /></label>
					<input type="text" name="name" maxlength="20" title="<spring:message code="comUatUia.idPw.name" />" placeholder="<spring:message code="comUatUia.idPw.name" />" />
				</li>
				<li>
					<label for="email"><spring:message code="comUatUia.idPw.email" /></label>
					<input type="text" name="email" maxlength="30" title="<spring:message code="comUatUia.idPw.email" />" placeholder="<spring:message code="comUatUia.idPw.email" />" />
				</li>
				<li>
					<input type="button" class="btn_login" onClick="fnSearchId();" value="<spring:message code="comUatUia.idPw.searchId" />" />
				</li>
			</ul>
		</div>
	<input name="userSe" type="hidden" value="GNR">
	</form>
	</fieldset>
	<!-- 아이디 찾기 //-->

	<!-- 비밀번호 찾기 -->
	<fieldset class="pw_search">
		<form name="passwordForm" action ="<c:url value='/uat/uia/searchPassword.do'/>" method="post">
		<legend><spring:message code="comUatUia.idPw.searchPassword" /></legend>
		<h2><spring:message code="comUatUia.idPw.searchPassword" /></h2>
		<div class="login_type">
			<ul>
				<li><a id="pwGnr" onClick="fnCheckUsrPassword('GNR');" class="on"><spring:message code="comUatUia.idPw.gnr" /></a></li>
				<li><a id="pwEnt" onClick="fnCheckUsrPassword('ENT');"><spring:message code="comUatUia.idPw.ent" /></a></li>
				<li><a id="pwUsr" onClick="fnCheckUsrPassword('USR');"><spring:message code="comUatUia.idPw.usr" /></a></li>
			</ul>
		</div>
		<div class="login_input">
			<ul>
				<li>
					<label for="id"><spring:message code="comUatUia.idPw.id" /></label>
					<input type="text" name="id" maxlength="15" title="<spring:message code="comUatUia.idPw.id" />" placeholder="<spring:message code="comUatUia.idPw.id" />" />
				</li>
				<li>
					<label for="name"><spring:message code="comUatUia.idPw.name" /></label>
					<input type="text" name="name" maxlength="20" title="<spring:message code="comUatUia.idPw.name" />" placeholder="<spring:message code="comUatUia.idPw.name" />" />
				</li>
				<li>
					<label for="email"><spring:message code="comUatUia.idPw.email" /></label>
					<input type="text" name="email" maxlength="30" title="<spring:message code="comUatUia.idPw.email" />" placeholder="<spring:message code="comUatUia.idPw.email" />" />
				</li>
				<li>
					<select name="passwordHint" title="<spring:message code="comUatUia.idPw.passwordHint" />">
						<option selected value=''><spring:message code="comUatUia.idPw.validate.passwordHint" /></option><!-- --선택하세요-- -->
						<c:forEach var="result" items="${pwhtCdList}" varStatus="status">
						<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label for="passwordCnsr"><spring:message code="comUatUia.idPw.passwordCnsr" /></label>
					<input type="text" name="passwordCnsr" maxlength="50" title="<spring:message code="comUatUia.idPw.passwordCnsr" />" placeholder="<spring:message code="comUatUia.idPw.passwordCnsr" />">
				</li>
				<li>
					<input type="button" class="btn_login" onClick="fnSearchPassword();" value="<spring:message code="comUatUia.idPw.searchPassword" />">
				</li>
			</ul>
		</div>
		<input name="userSe" type="hidden" value="GNR">
		</form>
	</fieldset>
	<!-- 비밀번호 찾기 //-->
</div>
</html>

