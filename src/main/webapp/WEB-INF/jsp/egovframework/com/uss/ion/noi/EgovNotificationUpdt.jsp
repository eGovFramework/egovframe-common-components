<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
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
 *  Copyright (C) 2009 by MOPAS  All right reserved.
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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="notification" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function onloading() {
		if ("<c:out value='${msg}'/>" != "") {
			alert("<c:out value='${msg}'/>");
		}
	}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
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
 * 수정
 ******************************************************** */
	function fn_egov_update_notification(){
		if (!validateNotification(document.notification)){
			return;
		}

		var checked = false;
		for (var i = 0; i < document.notification.bhNtfcIntrvl.length; i++) {
			if (document.notification.bhNtfcIntrvl[i].checked) {
				checked = true;
				break;
			}
		}

		if (!checked) {
			alert('<spring:message code="ussIonNoi.notificationUpdt.validate.bhNtfcIntrvlmsg" />');/* 사전알림간격 지정이 필요합니다. */
			return;
		}

		if (confirm('<spring:message code="common.update.msg" />')) {
			form = document.notification;
			form.action = "<c:url value='/uss/ion/noi/updateNotification.do'/>";

			form.ntfcTime.value = fn_egov_SelectBoxValue('ntfcHH') + ":" + fn_egov_SelectBoxValue('ntfcMM');

			form.submit();
		}
	}
/* ********************************************************
 * 목록
 ******************************************************** */
	function fn_egov_select_notification(){
		var form = document.notification;
		form.action = "<c:url value='/uss/ion/noi/selectNotificationList.do'/>";
		form.submit();
	}
/* ********************************************************
 * 초기화
 ******************************************************** */
	function init() {
		onloading();

		var form = document.notification;

		var hh = form.ntfcTime.value.substr(0, 2);
		var mm = form.ntfcTime.value.substr(3, 2);

		if (hh.charAt(0) == '0') {
			hh = hh.charAt(1);
		}

		if (mm.charAt(0) == '0') {
			mm = mm.charAt(1);
		}

		for (var i = 0; i < form.ntfcHH.length; i++) {
			if (form.ntfcHH[i].value == hh) {
				form.ntfcHH[i].selected = true;
			}
		}

		for (var i = 0; i < form.ntfcMM.length; i++) {
			if (form.ntfcMM[i].value == mm) {
				form.ntfcMM[i].selected = true;
			}
		}
	}
/* ********************************************************
 * 달력
 ******************************************************** */
	function fn_egov_init_date(){
		
		$("#ntfcDate").datepicker(  
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		         
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
					
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});
}
</script>
</head>
<body onload="init(); fn_egov_init_date();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="notification" name="notification" method="post" action="${pageContext.request.contextPath}/uss/ion/noi/updateNotification.do' />">

<double-submit:preventer tokenKey="EgovNotification"/>

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
<input name="ntfcNo" type="hidden" value="<c:out value='${result.ntfcNo}'/>" >

<input name="ntfcTime" id="ntfcTime" type="hidden" value='<c:out value="${fn:substring(result.ntfcTime,11,16)}"/>'>

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
			    <input id="ntfcSj" name="ntfcSj" size="25" value='<c:out value="${result.ntfcSj}"/>' style="width:100%" >
	    		<br><form:errors path="ntfcSj" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcCn" /> <span class="pilsu">*</span></th><!-- 내용 -->
			<td class="left" colspan="3">
			    <textarea id="ntfcCn" name="ntfcCn" class="textarea" cols="75" rows="2" style="width:100%"><c:out value="${result.ntfcCn}" escapeXml="true" /></textarea>
	       		<br><form:errors path="ntfcCn" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcDate" /> <span class="pilsu">*</span></th><!-- 알림일자 -->
			<td class="left">
			    <input name="ntfcDate" id="ntfcDate" type="text" size="73" value="<c:out value='${fn:substring(result.ntfcTime, 0, 10)}'/>" maxlength="10" style="width:80px;" readonly="readonly" class="readOnlyClass">
	  	   		<br><form:errors path="ntfcDate" />
			</td>
			<th><spring:message code="ussIonNoi.notificationUpdt.ntfcTime" /> <span class="pilsu">*</span></th><!-- 알림시간 -->
			<td class="left">
			    <select name="ntfcHH" id="ntfcHH">
					<option value=""><spring:message code="input.cSelect"/></option>
					<c:forEach var="h" begin="1" end="24" step="1">
					<option value="${h}">${h}시</option>
					</c:forEach>
				</select>
	
				<select name="ntfcMM" id="ntfcMM">
					<option value=""><spring:message code="input.cSelect"/></option>
					<option value="0">0분</option>
					<c:forEach var="m" begin="1" end="60" step="1">
					<option value="${m}">${m}분</option>
					</c:forEach>
				</select>
		  	    <br><form:errors path="ntfcHH" /><form:errors path="ntfcMM" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationUpdt.bhNtfcIntrvlString" /> <span class="pilsu">*</span></th><!-- 사전알림간격 -->
			<td class="left" colspan="3">
			    <c:set var="data">,<c:out value="${result.bhNtfcIntrvlString}" /></c:set>
				1분:<input id="bhNtfcIntrvl" name="bhNtfcIntrvl" type="checkbox" value="1" <c:if test="${fn:contains(data, ',1분')}">checked="checked"</c:if> title="시간간격체크(1분)">&nbsp;&nbsp;
				3분:<input name="bhNtfcIntrvl" type="checkbox" value="3" <c:if test="${fn:contains(data, ',3분')}">checked="checked"</c:if> title="시간간격체크(3분)">&nbsp;&nbsp;
				5분:<input name="bhNtfcIntrvl" type="checkbox" value="5" <c:if test="${fn:contains(data, ',5분')}">checked="checked"</c:if> title="시간간격체크(5분)">&nbsp;&nbsp;
				10분:<input name="bhNtfcIntrvl" type="checkbox" value="10" <c:if test="${fn:contains(data, ',10분')}">checked="checked"</c:if> title="시간간격체크(10분)">&nbsp;&nbsp;
				30분:<input name="bhNtfcIntrvl" type="checkbox" value="30" <c:if test="${fn:contains(data, ',30분')}">checked="checked"</c:if> title="시간간격체크(30분)">&nbsp;&nbsp;
		  	   <br><form:errors path="bhNtfcIntrvl" />
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
