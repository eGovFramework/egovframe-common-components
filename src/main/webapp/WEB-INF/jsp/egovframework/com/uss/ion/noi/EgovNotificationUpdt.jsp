<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="double-submit" uri="http://www.egovframe.go.kr/tags/double-submit/jsp" %>
<%
/**
 * @Class Name : EgovNotificationUpdt.jsp
 * @Description : 정보알림이 수정화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.06.17   한성곤          최초 생성
 * @ 2018.08.21   이정은          공통컴포넌트 3.8 개선          
 *
 *  @author 공통컴포넌트개발팀 한성곤
 *  @since 2009.06.17
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
<title>정보알림이 수정</title><!-- 정보알림이 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
	function fn_egov_SelectBoxValue(sbName) {
		var el = document.getElementById(sbName);
		if (!el || !el.options) return "";
		for (var i = 0; i < el.length; i++) {
			if (el.options[i].selected) return el.options[i].value;
		}
		return "";
	}
	function fn_egov_update_notification(){
		var form = document.getElementById('notificationVO') || document.notificationVO;
		if (!validateNotification(form)){
			return;
		}
		if (confirm('<spring:message code="common.update.msg" />')) {
			form.action = "<c:url value='/uss/ion/noi/updateNotification.do'/>";
			form.ntfcTime.value = fn_egov_SelectBoxValue('ntfcHH') + ":" + fn_egov_SelectBoxValue('ntfcMM');
			form.submit();
		}
	}
	function fn_egov_select_notification(){
		var form = document.getElementById('notificationVO') || document.notificationVO;
		form.action = "<c:url value='/uss/ion/noi/selectNotificationList.do'/>";
		form.submit();
	}
	$(function() {
		if ("<c:out value='${msg}'/>" != "") alert("<c:out value='${msg}'/>");
		$("#ntfcDate").datepicker({
			dateFormat:'yy-mm-dd', showOn: 'button',
			buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>',
			buttonImageOnly: true, showMonthAfterYear: true, showOtherMonths: true, selectOtherMonths: true,
			changeMonth: true, changeYear: true, showButtonPanel: true
		});
		var timeVal = document.getElementById('ntfcTime').value;
		if (timeVal && timeVal.indexOf(':') >= 0) {
			var parts = timeVal.split(':');
			var hh = parts[0] ? String(parseInt(parts[0], 10)) : '';
			var mm = parts[1] !== undefined && parts[1] !== '' ? String(parseInt(parts[1], 10)) : '';
			var selHH = document.getElementById('ntfcHH'), selMM = document.getElementById('ntfcMM');
			if (selHH && hh !== '') { for (var i = 0; i < selHH.length; i++) { if (selHH.options[i].value == hh) { selHH.selectedIndex = i; break; } } }
			if (selMM && mm !== '') { for (var i = 0; i < selMM.length; i++) { if (selMM.options[i].value == mm) { selMM.selectedIndex = i; break; } } }
		}
	});
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form id="notificationVO" modelAttribute="notificationVO" name="notificationVO" method="post" action="${pageContext.request.contextPath}/uss/ion/noi/updateNotification.do">

<double-submit:preventer tokenKey="EgovNotification"/>

<input name="pageIndex" type="hidden" value="<c:out value='${notificationVO.pageIndex}'/>">
<form:hidden path="ntfcNo" />
<c:set var="ntfcTimeDisplay" value="${notificationVO.ntfcTime}" />
<c:if test="${fn:length(notificationVO.ntfcTime) >= 16}"><c:set var="ntfcTimeDisplay" value="${fn:substring(notificationVO.ntfcTime,11,16)}" /></c:if>
<input name="ntfcTime" id="ntfcTime" type="hidden" value="<c:out value='${ntfcTimeDisplay}'/>">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>정보알림이 수정</h2><!-- 정보알림이 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcSj" /> <span class="pilsu">*</span></th><!-- 제목 -->
			<td class="left" colspan="3">
			    <form:input path="ntfcSj" size="25" cssStyle="width:100%" />
			    <div><form:errors path="ntfcSj" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcCn" /> <span class="pilsu">*</span></th><!-- 내용 -->
			<td class="left" colspan="3">
			    <form:textarea path="ntfcCn" cols="75" rows="2" cssStyle="width:100%" />
			    <div><form:errors path="ntfcCn" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcDate" /> <span class="pilsu">*</span></th><!-- 알림일자 -->
			<td class="left">
			    <form:input path="ntfcDate" id="ntfcDate" size="73" maxlength="10" style="width:80px;" readonly="true" />
			    <div><form:errors path="ntfcDate" cssClass="error" /></div>
			</td>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcTime" /> <span class="pilsu">*</span></th><!-- 알림시간 -->
			<td class="left">
			    <select name="ntfcHH" id="ntfcHH">
					<option value=""><spring:message code="input.cSelect"/></option>
					<c:forEach var="h" begin="0" end="23" step="1">
					<option value="${h}" <c:if test="${notificationVO.ntfcHH != null && notificationVO.ntfcHH != '' && h == (0 + notificationVO.ntfcHH)}">selected="selected"</c:if>>${h}시</option>
					</c:forEach>
				</select>
				<select name="ntfcMM" id="ntfcMM">
					<option value=""><spring:message code="input.cSelect"/></option>
					<option value="0" <c:if test="${notificationVO.ntfcMM != null && notificationVO.ntfcMM != '' && 0 == (0 + notificationVO.ntfcMM)}">selected="selected"</c:if>>0분</option>
					<c:forEach var="m" begin="1" end="59" step="1">
					<option value="${m}" <c:if test="${notificationVO.ntfcMM != null && notificationVO.ntfcMM != '' && m == (0 + notificationVO.ntfcMM)}">selected="selected"</c:if>>${m}분</option>
					</c:forEach>
				</select>
			    <div><form:errors path="ntfcHH" cssClass="error" /><form:errors path="ntfcMM" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.bhNtfcIntrvlString" /> <span class="pilsu">*</span></th><!-- 사전알림간격 -->
			<td class="left" colspan="3">
			    <c:set var="data">,<c:out value="${notificationVO.bhNtfcIntrvlString}" /></c:set>
				1분:<form:checkbox path="bhNtfcIntrvl" value="1" />&nbsp;&nbsp;
				3분:<form:checkbox path="bhNtfcIntrvl" value="3" />&nbsp;&nbsp;
				5분:<form:checkbox path="bhNtfcIntrvl" value="5" />&nbsp;&nbsp;
				10분:<form:checkbox path="bhNtfcIntrvl" value="10" />&nbsp;&nbsp;
				30분:<form:checkbox path="bhNtfcIntrvl" value="30" />&nbsp;&nbsp;
			    <div><form:errors path="bhNtfcIntrvl" cssClass="error" /></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_update_notification(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/noi/selectNotificationList.do'/>?pageIndex=1" onclick="fn_egov_select_notification(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</form:form>
</html>
