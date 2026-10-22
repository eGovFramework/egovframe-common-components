<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovUnitContent.jsp
  * @Description : 로그인 성공후 컨텐츠 영역
  * @Modification Information
  * 
  * @수정일               수정자            수정내용
  *  ----------   --------   ---------------------------
  *  2020.07.08   신용호            비밀번호 만료 처리
  *  2026.10.02   개발팀           초기 비밀번호 사용 안내(다음에 변경 불가)
  *
  *  @author 공통서비스 개발팀 신용호
  *  @since 2020.07.08
  *  @version 3.10
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All rights reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>eGovFrame <c:choose><c:when test="${initialPassword}"><spring:message code="comCmm.unitContent.21"/></c:when><c:otherwise><spring:message code="comCmm.unitContent.20"/></c:otherwise></c:choose></title>
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<script type="text/javascript">
var flagTopFrame = false;

// 기업회원 (ENTERPRISE)
function fnPasswordMoveEnt(){
    document.pwdManage.action = "<c:url value='/uss/umt/EgovEntrprsPasswordUpdtView.do'/>";
    document.pwdManage.submit();
}
// 일반회원 (USER)
function fnPasswordMoveMber(){
    document.pwdManage.action = "<c:url value='/uss/umt/EgovMberPasswordUpdtView.do'/>";
    document.pwdManage.submit();
}
// 업무사용자 (TEST1/webmaster)
function fnPasswordMoveUser(){
	document.pwdManage.action = "<c:url value='/uss/umt/EgovEmplyrPasswordUpdtView.do'/>";
    document.pwdManage.submit();
}

function fn_egov_init() {

    switch ( $("#userSe").val() ) {
    case "USR" :
    	$("#emplyrId").val($("#loginId").val());
    	$("#userSeName").text("<spring:message code="comCmm.expirePwdContent.10"/>"); //업무사용자
        break;
    case "ENT" :
    	$("#entrprsmberId").val($("#loginId").val());
    	$("#userSeName").text("<spring:message code="comCmm.expirePwdContent.11"/>"); //기업회원
        break;
    case "GNR" :
    	$("#mberId").val($("#loginId").val());
    	$("#userSeName").text("<spring:message code="comCmm.expirePwdContent.12"/>"); //일반회원
        break;
	}
	
}

function fn_egov_change_pwd() {
	
    switch ( $("#userSe").val() ) {
    case "USR" : // 업무사용자
    	fnPasswordMoveUser();
        break;
    case "ENT" : // 기업회원
    	fnPasswordMoveEnt();
        break;
    case "GNR" : //일반회원
    	fnPasswordMoveMber();
        break;
	}
}

</script>
</head>
<body onload="fn_egov_init()">
	<c:if test="${loginVO != null}">
		<spring:message code="comCmm.expirePwdContent.1"/> ID : ${loginVO.id}<br><!-- 로그인 -->
		<spring:message code="comCmm.expirePwdContent.2"/> : <span id="userSeName"></span><!-- 로그인 구분 -->
		<!--
		<br>passedDay = ${passedDay}
		<br>expirePwdDay = ${expirePwdDay}
		<br>elapsedTimeExpiration = ${elapsedTimeExpiration}
		-->
		<script type="text/javaScript" language="javascript">
			flagTopFrame = true;
		</script>
	</c:if>
	<c:choose>
	<c:when test="${initialPassword}">
	<%-- 초기(공개) 비밀번호 사용 중: 변경 화면으로 가는 것 외에는 닫을 수 없다 --%>
	<p/><p/><p/>
	<b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> <spring:message code="comCmm.initialPwdContent.1"/></b><p/><!-- 초기 비밀번호 사용 중 -->
	<spring:message code="comCmm.initialPwdContent.2"/><p/><!-- 지금 사용 중인 비밀번호는 공개된 초기 비밀번호입니다. -->
	<spring:message code="comCmm.initialPwdContent.3"/><p/><!-- 누구나 이 계정으로 로그인할 수 있으니 지금 비밀번호를 변경해 주세요. -->
	<spring:message code="comCmm.initialPwdContent.4"/><p/><!-- 비밀번호를 변경하기 전에는 이 안내가 계속 표시됩니다. -->
	<br/>
	<div align="center">
		<input class="btn_03" type="submit" value="<spring:message code="comCmm.expirePwdContent.50"/>" title="<spring:message code="comCmm.expirePwdContent.50"/>" onclick="fn_egov_change_pwd(); return false;" /><!-- 지금 즉시 변경하기 -->
	</div>
	</c:when>
	<c:otherwise>
	<p/><p/><p/>
	<b><spring:message code="comCmm.expirePwdContent.21"/></b><br/><!-- 비밀번호 유효기간의 변경은 다음 파일을 참조하여 주세요. -->
	src/main/resources/egovframework/egovProps/globals.properties
	<p/>
	<b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> <spring:message code="comCmm.expirePwdContent.22"/> </b><p/><!-- 비밀번호 유효기간 만료 -->
	<spring:message code="comCmm.expirePwdContent.23"/><p/><!-- 비밀번호 유효기간이 만료 되었습니다. -->
	<spring:message code="comCmm.expirePwdContent.24"/><p/><!-- 안전한 개인정보 보호를 위해 지금 비밀번호를 변경해 주세요! -->

	<br /><b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> <spring:message code="comCmm.expirePwdContent.25"/></b><p/><!-- 비밀번호 유효기간 초과일수 -->

	<spring:message code="comCmm.expirePwdContent.26"/> : ${expirePwdDay}<spring:message code="comCmm.expirePwdContent.30"/><br /><!-- 비밀번호 유효기간 -->
	<spring:message code="comCmm.expirePwdContent.27"/> : ${passedDay}<spring:message code="comCmm.expirePwdContent.30"/><br /><!-- 비밀번호 변경후 경과일수 -->
	<spring:message code="comCmm.expirePwdContent.28"/> : ${elapsedTimeExpiration}<spring:message code="comCmm.expirePwdContent.30"/><br /><p/><!-- 비밀번호 유효기간 초과일수 -->
	<spring:message code="comCmm.expirePwdContent.29"/><p/><!-- 주기적으로 비밀번호를 변경해 주세요. -->
	<br/>
	<div align="center">
		<input class="btn_03" type="submit" value="<spring:message code="comCmm.expirePwdContent.50"/>" title="<spring:message code="comCmm.expirePwdContent.50"/>" onclick="fn_egov_change_pwd(); return false;" /><!-- 지금 즉시 변경하기 -->
		<input class="btn_03" type="submit" value="<spring:message code="comCmm.expirePwdContent.51"/>" title="<spring:message code="comCmm.expirePwdContent.51"/>" onclick="parent.$dialog.dialog('close'); return false;" /><!-- 다음에 변경하기 -->
	</div>
	
	</c:otherwise>
	</c:choose>

	<form:form id="pwdManage" name="pwdManage" modelAttribute="loginVO" method="post" target="_parent">
		<input type="hidden" id="loginId" name="loginId" readonly="readonly"  value="${loginVO.id}"/>
		<input type="hidden" id="uniqId" name="uniqId" readonly="readonly"  value="${loginVO.uniqId}"/>
		<input type="hidden" id="userSe" name="userSe" readonly="readonly"  value="${loginVO.userSe}"/>
		<br><br><br>
		<!-- 일반회원 --><input type="hidden" id="mberId" name="mberId" readonly="readonly"  value=""/><!-- USER -->
		<!-- 기업회원 --><input type="hidden" id="entrprsmberId" name="entrprsmberId" readonly="readonly" value=""/><!-- ENTERPRISE -->
		<!-- 업무사용자 --><input type="hidden" id="emplyrId" name="emplyrId" readonly="readonly" value=""/><!-- TEST1/webmaster -->
	</form:form>
</body>
</html>