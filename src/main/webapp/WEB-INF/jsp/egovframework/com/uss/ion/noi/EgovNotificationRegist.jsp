<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
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
 *  Copyright (C) 2009 by MOPAS  All right reserved.
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
			alert('<spring:message code="ussIonNoi.notificationRegist.validate.bhNtfcIntrvlmsg" />');/* 사전알림간격 지정이 필요합니다. */
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {/* 등록 하시겠습니까? */
			form = document.notification;
			form.action = "<c:url value='/uss/ion/noi/insertNotification.do'/>";

			form.ntfcTime.value = fn_egov_SelectBoxValue('ntfcHH') + ":" + fn_egov_SelectBoxValue('ntfcMM');

			form.submit();
		}
	}
/* ********************************************************
 * 목록
 ******************************************************** */	
	function fn_egov_select_notification(){
		form = document.notification;
		form.action = "<c:url value='/uss/ion/noi/selectNotificationList.do'/>";
		form.submit();
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
<body onload="onloading(); fn_egov_init_date();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="notification" name="notification" method="post" action="${pageContext.request.contextPath}/uss/ion/noi/insertNotification.do">

	<double-submit:preventer/>
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input name="ntfcTime" id="ntfcTime" type="hidden" value="">

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
	    		<br /><form:errors path="ntfcSj" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcCn" /> <span class="pilsu">*</span></th><!-- 내용 -->
			<td class="left" colspan="3">
			    <form:textarea path="ntfcCn" cols="75" rows="2" cssStyle="width:100%" />
	       		<br /><form:errors path="ntfcCn" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcDate" /> <span class="pilsu">*</span></th><!-- 알림일자 -->
			<td class="left">
			    <input name="ntfcDate" id="ntfcDate" type="text" size="73" value="" maxlength="10" style="width:80px;" readonly="readonly" class="readOnlyClass">
	  	   		<br /><form:errors path="ntfcDate" />
			</td>
			<th><spring:message code="ussIonNoi.notificationRegist.ntfcTime" /> <span class="pilsu">*</span></th><!-- 알림시간 -->
			<td class="left">
			    <select name="ntfcHH" id="ntfcHH" title="시간선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<c:forEach var="h" begin="1" end="24" step="1">
					<option value="${h}">${h}시</option>
					</c:forEach>
				</select>
	
				<select name="ntfcMM" id="ntfcMM" title="분선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<option value="0">0분</option>
					<c:forEach var="m" begin="1" end="60" step="1">
					<option value="${m}">${m}분</option>
					</c:forEach>
				</select>
		  	    <br /><form:errors path="ntfcHH" /><form:errors path="ntfcMM" />
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
		  	   <br /><form:errors path="bhNtfcIntrvl" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_regist_notification(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/noi/selectNotificationList.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="fn_egov_select_notification(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>
