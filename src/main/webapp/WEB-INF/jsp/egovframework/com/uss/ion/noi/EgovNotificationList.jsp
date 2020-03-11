<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovNotificationList.jsp
  * @Description : 정보알림이 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.06.08   한성곤          최초 생성
  * @ 2018.09.17   이정은          공통컴포넌트 3.8 개선
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.08
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
<title><spring:message code="ussIonNoi.notificationList.notificationList"/></title><!-- 정보알림이 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<!-- contextPath 설정 -->
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>

<!-- 정보알림이 표시를 위한 스크립트  -->
<script type="text/javascript">
var noi_url = "<c:url value='/uss/ion/noi/getNotifications.do'/>";
</script>
<script type="text/javascript" language="javaScript" src="<c:url value='/js/egovframework/com/uss/ion/noi/EgovNotification.js'/>" ></script>

<script type="text/javascript">
	function init() {
		if (document.frm.searchCnd.value == '0' && document.frm.searchWrd.value != '') {
			var str = document.frm.searchWrd.value;

			document.frm.searchWrd.value = str.substr(0,4) + '-' + str.substr(4,2) + '-' + str.substr(6,2);
		}
	}

	function isValidDate(str) {
	   	// var test = /^\d{4}-\d{2}-\d{2}$/;
	    var test = /^\d{4}\d{2}\d{2}$/;

	    if (!test.test(str)) {
		    return false;
	    }

	    var y, m, d;

	    y =  parseInt(str.substr(0,4), 10);
		m = parseInt(str.substr(4,2), 10) - 1;
		d = parseInt(str.substr(6,2), 10);

	    var dt = new Date(y, m, d);

	    if (dt.getFullYear() == y && dt.getMonth() == m && dt.getDate() == d) {
	        return true;
	    } else {
	        return false;
	    }
	}

	function rawDateString(obj) {
		var intValue = '0123456789';

		var result = "";
		var str =  String(obj.value);

	    if (str.length < 1) {
	    	result = "";
	    	return true;
	    }

	    for (var i = 0; i < str.length; i++) {
		    if (intValue.indexOf(str.charAt(i)) >= 0) {	// 숫자
				result += str.charAt(i);
		    }
	    }

	    if (isValidDate(result)) {
		    obj.value = result;
		    return true;
	    }

	    return false;
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_notification('1');
		}
	}

	function fn_egov_insert_notification() {
		document.frm.action = "<c:url value='/uss/ion/noi/addNotification.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_notification(pageNo) {
		if (document.frm.searchCnd.value == '0' && document.frm.searchWrd.value != '') {
			if (rawDateString(document.frm.searchWrd)) {
				// no-op
			} else {
				alert('<spring:message code="errors.date" arguments="알림일자" />');
				document.frm.searchWrd.focus();
				return;
			}
		}

		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/uss/ion/noi/selectNotificationList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_notification(ntfcNo) {
		document.frm.ntfcNo.value = ntfcNo;
		document.frm.action = "<c:url value='/uss/ion/noi/selectNotification.do'/>";
		document.frm.submit();
	}
</script>
</head>
<body onload="init()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="ussIonNoi.notificationList.notificationList"/>
</h1><!-- 정보알림이 목록 -->
		
	<form name="frm" method="post" action="<c:url value='/uss/ion/noi/selectNotificationList.do'/>">
	<input type="hidden" name="ntfcNo">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		
	<div class="search_box" title="<spring:message code="common.searchCondition.msg"/>"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- --선택하세요-- -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="ussIonNoi.notificationList.ntfcTime"/></option><!-- 알림일자 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="ussIonNoi.notificationList.ntfcSj"/></option><!-- 제목 -->
					<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="ussIonNoi.notificationList.ntfcCn"/></option><!-- 내용 -->
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value="<c:out value="${searchVO.searchWrd}"/>" size="25" onkeypress="press(event);" title="<spring:message code="input.input"/>" /><!-- 검색단어입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire"/>" title="<spring:message code="button.inquire"/>" onclick="fn_egov_select_notification('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/noi/addNotification.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="fn_egov_insert_notification(); return false;" title="<spring:message code="button.create"/>"><spring:message code="button.create"/></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	</form>
	<table class="board_list">
		<caption><spring:message code="ussIonNoi.notificationList.notificationList"/></caption><!-- 정보알림이 목록 -->
		<colgroup>
			<col style="width:5%" />
			<col style="width:40%" />
			<col style="width:20%" />
			<col style="width:20%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 번호 -->
			   <th scope="col"><spring:message code="ussIonNoi.notificationList.ntfcSj"/></th><!-- 제목 -->
			   <th scope="col"><spring:message code="ussIonNoi.notificationList.ntfcTime"/></th><!-- 알림시간 -->
			   <th scope="col"><spring:message code="ussIonNoi.notificationList.bhNtfcIntrvlString"/></th><!-- 사전알림간격 -->
			   <th scope="col"><spring:message code="ussIonNoi.notificationList.frstRegisterPnttm"/></th><!-- 생성일 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					<td class="left">
						<form name="item" method="post" action="<c:url value='/uss/ion/noi/selectNotification.do'/>">
							<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
							<input type="hidden" name="ntfcNo" value="<c:out value="${result.ntfcNo}"/>">
							<input class="link" type="submit" value="<c:out value="${result.ntfcSj}"/>" onclick="fn_egov_inqire_notification('<c:out value="${result.ntfcNo}"/>'); return false;">
						</form>
					</td>
					<td><c:out value="${fn:substring(result.ntfcTime,0,16)}"/></td>
					<td><c:out value="${result.bhNtfcIntrvlString}"/></td>
					<td><c:out value="${result.frstRegisterPnttm}"/></td>
					
				</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="5"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_notification"/>
		</ul>
	</div>
</div>
</body>
</html>
