<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="double-submit" uri="http://www.egovframe.go.kr/tags/double-submit/jsp" %>
<%
/**
 * @Class Name : EgovNotificationRegist.jsp
 * @Description : 정보알림이 등록화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.06.09   한성곤          최초 생성
 * @ 2018.08.29   이정은          공통컴포넌트 3.8 개선
 *
 *  @author 공통컴포넌트개발팀 한성곤
 *  @since 2009.06.09
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonNoi.notificationRegist.notificationRegist"/></title><!-- 정보알림이 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
	function fn_egov_SelectBoxValue(sbName) {
		var fValue = "";

		for (var i=0; i < document.getElementById(sbName).length; i++) {
			if (document.getElementById(sbName).options[i].selected == true) {
				fValue = document.getElementById(sbName).options[i].value;
			}
		}

		return  fValue;
	}

/* ********************************************************
 * 저장처리
 ******************************************************** */	
	function fn_egov_regist_notification(){
		var form = document.getElementById('notificationVO') || document.notificationVO;
		if (!validateNotification(form)){
			return;
		}
		if (confirm('<spring:message code="common.regist.msg" />')) {/* 등록 하시겠습니까? */
			form.action = "<c:url value='/uss/ion/noi/insertNotification.do'/>";
			form.ntfcTime.value = fn_egov_SelectBoxValue('ntfcHH') + ":" + fn_egov_SelectBoxValue('ntfcMM');
			form.submit();
		}
	}
/* ********************************************************
 * 목록
 ******************************************************** */	
	function fn_egov_select_notification(){
		var form = document.getElementById('notificationVO') || document.notificationVO;
		form.action = "<c:url value='/uss/ion/noi/selectNotificationList.do'/>";
		form.submit();
	}
/* ********************************************************
 * 달력
 ******************************************************** */
	$(function() {
		if ("<c:out value='${msg}'/>" != "") {
			alert("<c:out value='${msg}'/>");
		}
		$("#ntfcDate").datepicker(
			{dateFormat:'yy-mm-dd'
			 , showOn: 'button'
			 , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
			 , buttonImageOnly: true
			 , showMonthAfterYear: true
			 , showOtherMonths: true
			 , selectOtherMonths: true
			 , changeMonth: true
			 , changeYear: true
			 , showButtonPanel: true
			});
	});
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form id="notificationVO" modelAttribute="notificationVO" name="notificationVO" method="post" action="${pageContext.request.contextPath}/uss/ion/noi/insertNotification.do">

	<double-submit:preventer/>

	<input name="pageIndex" type="hidden" value="<c:out value='${notificationVO.pageIndex}'/>">
	<input name="ntfcTime" id="ntfcTime" type="hidden" value="<c:out value='${notificationVO.ntfcTime}'/>">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonNoi.notificationRegist.notificationRegist"/></h2><!-- 정보알림이 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcSj" /> <span class="pilsu">*</span></th><!-- 제목 -->
			<td class="left" colspan="3">
			    <form:input path="ntfcSj" size="25" cssStyle="width:100%" />
			    <div><form:errors path="ntfcSj" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcCn" /> <span class="pilsu">*</span></th><!-- 내용 -->
			<td class="left" colspan="3">
			    <form:textarea path="ntfcCn" cols="75" rows="2" cssStyle="width:100%" />
			    <div><form:errors path="ntfcCn" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcDate" /> <span class="pilsu">*</span></th><!-- 알림일자 -->
			<td class="left">
			    <form:input path="ntfcDate" id="ntfcDate" size="73" maxlength="10" style="width:80px;" readonly="true" cssClass="readOnlyClass" />
			    <div><form:errors path="ntfcDate" cssClass="error" /></div>
			</td>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcTime" /> <span class="pilsu">*</span></th><!-- 알림시간 -->
			<td class="left">
			    <select name="ntfcHH" id="ntfcHH" title="시간선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<c:forEach var="h" begin="0" end="23" step="1">
					<option value="${h}" <c:if test="${notificationVO.ntfcHH != null && notificationVO.ntfcHH != '' && notificationVO.ntfcHH == h}">selected="selected"</c:if>>${h}시</option>
					</c:forEach>
				</select>
				<select name="ntfcMM" id="ntfcMM" title="분선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<option value="0" <c:if test="${notificationVO.ntfcMM != null && notificationVO.ntfcMM == '0'}">selected="selected"</c:if>>0분</option>
					<c:forEach var="m" begin="1" end="59" step="1">
					<option value="${m}" <c:if test="${notificationVO.ntfcMM != null && notificationVO.ntfcMM != '' && notificationVO.ntfcMM == m}">selected="selected"</c:if>>${m}분</option>
					</c:forEach>
				</select>
			    <div><form:errors path="ntfcHH" cssClass="error" /><form:errors path="ntfcMM" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationRegist.bhNtfcIntrvlString" /> <span class="pilsu">*</span></th><!-- 사전알림간격 -->
			<td class="left" colspan="3">
			    1분:<form:checkbox id="bhNtfcIntrvl" class="check2" path="bhNtfcIntrvl" value="1"/>&nbsp;&nbsp;
				3분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="3"/>&nbsp;&nbsp;
				5분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="5"/>&nbsp;&nbsp;
				10분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="10"/>&nbsp;&nbsp;
				30분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="30"/>&nbsp;&nbsp;
			    <div><form:errors path="bhNtfcIntrvl" cssClass="error" /></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_regist_notification(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/noi/selectNotificationList.do'/>?pageIndex=<c:out value='${notificationVO.pageIndex}'/>" onclick="fn_egov_select_notification(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>
