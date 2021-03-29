<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovUnitContent.jsp
  * @Description : 로그인 성공후 컨텐츠 영역
  * @Modification Information
  * 
  * @수정일               수정자            수정내용
  *  ----------   --------   ---------------------------
  *  2020.07.08   신용호            비밀번호 만료 처리
  *
  *  @author 공통서비스 개발팀 신용호
  *  @since 2020.07.08
  *  @version 3.10
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>eGovFrame <spring:message code="comCmm.unitContent.20"/></title>
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
	document.pwdManage.action = "<c:url value='/uss/umt/EgovUserPasswordUpdtView.do'/>";
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
    	console.log("<spring:message code="comCmm.expirePwdContent.10"/>"); //업무사용자
    	fnPasswordMoveUser();
        break;
    case "ENT" : // 기업회원
    	console.log("<spring:message code="comCmm.expirePwdContent.11"/>"); //기업회원
    	fnPasswordMoveEnt();
        break;
    case "GNR" : //일반회원
    	console.log("<spring:message code="comCmm.expirePwdContent.12"/>"); //일반회원
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
	
	<form id="pwdManage" name="pwdManage" method="post" target="_parent">
		<input type="hidden" id="loginId" name="loginId" readonly="readonly"  value="${loginVO.id}"/>
		<input type="hidden" id="uniqId" name="uniqId" readonly="readonly"  value="${loginVO.uniqId}"/>
		<input type="hidden" id="userSe" name="userSe" readonly="readonly"  value="${loginVO.userSe}"/>
		<br><br><br>
		<!-- 일반회원 --><input type="hidden" id="mberId" name="mberId" readonly="readonly"  value=""/><!-- USER -->
		<!-- 기업회원 --><input type="hidden" id="entrprsmberId" name="entrprsmberId" readonly="readonly" value=""/><!-- ENTERPRISE -->
		<!-- 업무사용자 --><input type="hidden" id="emplyrId" name="emplyrId" readonly="readonly" value=""/><!-- TEST1/webmaster -->
	</form>
</body>
</html>