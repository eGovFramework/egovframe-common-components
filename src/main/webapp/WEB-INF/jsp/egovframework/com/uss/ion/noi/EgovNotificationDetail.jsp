<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovNotificationDetail.jsp
 * @Description : 정보알림이 상세조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.06.15   한성곤          최초 생성
 * @ 2018.08.21   이정은          공통컴포넌트 3.8 개선    
 *
 *  @author 공통컴포넌트개발팀 한성곤
 *  @since 2009.06.15
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
<title><spring:message code="ussIonNoi.notificationDetail.notificationDetail"/></title><!-- 정보알림이 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function onloading() {
		if ("<c:out value='${msg}'/>" != "") {
			alert("<c:out value='${msg}'/>");
		}
	}
	
	function fn_egov_select_notificationList(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/uss/ion/noi/selectNotificationList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_delete_notification() {		
		if (confirm('<spring:message code="common.delete.msg" />')) {/* 삭제 하시겠습니까? */
			document.frm.action = "<c:url value='/uss/ion/noi/deleteNotification.do'/>";
			document.frm.submit();
		}	
	}
	
	function fn_egov_moveUpdt_notification() {
		document.frm.action = "<c:url value='/uss/ion/noi/forUpdateNotification.do'/>";
		document.frm.submit();			
	}
</script>

</head>
<body onload="onloading();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="frm" method="post" action="">
	<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="ntfcNo" value="<c:out value='${result.ntfcNo}'/>">
</form>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonNoi.notificationDetail.notificationDetail"/></h2><!-- 정보알림이 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonNoi.notificationDetail.ntfcSj"/></th><!-- 제목 -->
			<td class="left" colspan="3"><c:out value="${result.ntfcSj}" /></td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationDetail.ntfcCn"/></th><!-- 내용 -->
			<td class="left" colspan="3"><c:out value="${result.ntfcCn}"/></td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationDetail.ntfcTime"/></th><!-- 알림시간 -->
			<td class="left"><c:out value="${fn:substring(result.ntfcTime,0,16)}" /></td>
			<th><spring:message code="ussIonNoi.notificationDetail.bhNtfcIntrvlString"/></th><!-- 사전알림간격 -->
			<td class="left"><c:out value="${result.bhNtfcIntrvlString}" /></td>
		</tr>
		<tr>
			<th><spring:message code="ussIonNoi.notificationDetail.frstRegisterNm"/></th><!-- 작성자 -->
			<td class="left"><c:out value="${result.frstRegisterNm}" /></td>
			<th><spring:message code="ussIonNoi.notificationDetail.frstRegisterPnttm"/></th><!-- 작성시간 -->
			<td class="left"><c:out value="${result.frstRegisterPnttm}" /></td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="modifyFrm" method="post" action="<c:url value='/uss/ion/noi/forUpdateNotification.do'/>" style="display:inline-block; vertical-align:top;">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			<input type="hidden" name="ntfcNo" value="<c:out value='${result.ntfcNo}'/>" >
			<input class="s_submit" type="submit" value="<spring:message code="button.update"/>" onclick="fn_egov_moveUpdt_notification(); return false;" />
		</form>
		
		<form name="deleteFrm" method="post" action="<c:url value='/uss/ion/noi/deleteNotification.do'/>" style="display:inline-block; vertical-align:top;">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			<input type="hidden" name="ntfcNo" value="<c:out value='${result.ntfcNo}'/>" >
			<input class="s_submit" type="submit" value="<spring:message code="button.delete"/>" onclick="fn_egov_delete_notification(); return false;" />
		</form>
		
		<form name="listFrm" method="post" action="<c:url value='/uss/ion/noi/selectNotificationList.do'/>" style="display:inline-block; vertical-align:top;">
			<input name="pageIndex" type="hidden" value="1" >
			<input class="s_submit" type="submit" value="<spring:message code="button.list"/>" onclick="fn_egov_select_notificationList('1'); return false;" />
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

</body>
</html>
