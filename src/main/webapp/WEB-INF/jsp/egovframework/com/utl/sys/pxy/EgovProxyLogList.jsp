<%--
/**
 * @Class Name  : EgovProxyLogList.java
 * @Description : EgovProxyLogList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    lee.m.j     최초 생성
 *
 *  @author lee.m.j
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comUtlSysPxy.proxyLog.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fn_egov_init_calendar(){
	
	$("#strStartDate").datepicker(  
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

	$("#strEndDate").datepicker( 
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

function fncSelectProxyLogList(pageNo){

    var fromDate = document.listForm.strStartDate.value;
    var endDate = document.listForm.strEndDate.value;

    // var tmpFromDate = fromDate.substring(0,4)+fromDate.substring(5,7)+fromDate.substring(8,10);
    // var tmpEndDate = endDate.substring(0,4)+endDate.substring(5,7)+endDate.substring(8,10);

    var tmpFromDate = fromDate.split("-");
    var tmpEndDate = endDate.split("-");

    var strTmpFromDate = "";
    var strTmpEndDate = "";

    for(var i=0;i<tmpFromDate.length;i++) {
        strTmpFromDate = strTmpFromDate + tmpFromDate[i];
    }

    for(var j=0;j<tmpEndDate.length;j++) {
        strTmpEndDate = strTmpEndDate + tmpEndDate[j];
    }

    if(strTmpFromDate.length != 8 || strTmpEndDate.length != 8 || !checknum(strTmpFromDate) || !checknum(strTmpEndDate)) {
        alert("<spring:message code="comUtlSysPxy.proxySvcList.checknum"/>");/* 날짜 형식이 잘못되었습니다 */
        return;
    } else {
        if(strTmpFromDate > strTmpEndDate) {
            alert("<spring:message code="comUtlSysPxy.proxySvcList.strTmpFromDateEndDate"/>");/* 시작일자는 종료일자보다 클 수 없습니다 */
            return;
        }
    }

    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/pxy/selectProxyLogList.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/pxy/selectProxyLogList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
        fncSelectProxyLogList('1');
    }
}

function checknum(number) {

    var rtnMsg = true;
    var pattern = /^[0-9]+$/;

    if(!pattern.test(number)) {
        rtnMsg = false;
    }

    return rtnMsg;
}
-->
</script>
</head>
<body onLoad="fn_egov_init_calendar();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

    <form name="listForm" action="<c:url value='/utl/sys/pxy/selectProxyLogList.do'/>" method="post">

	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<label for=""><spring:message code="comUtlSysPxy.proxyLog.period.label" /> : </label>
				<input type="text" name="strStartDate" id="strStartDate" value="<c:out value='${pmProxyLogVO.strStartDate}'/>" size="10" maxlength="10" title="<spring:message code="comUtlSysPxy.proxySvcList.pmProxyLogVO.strStartDate"/>" ><!-- 프록시로그 시작일자 -->
				~ 
				<input type="text" name="strEndDate" id="strEndDate" value="<c:out value='${pmProxyLogVO.strEndDate}'/>" size="10" maxlength="10" title="<spring:message code="comUtlSysPxy.proxySvcList.pmProxyLogVO.strEndDate"/>" ><!-- 프록시로그 종료일자 -->
				<input type="hidden" name="strProxyDate" value=""/>      
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectProxyLogList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>" onclick="" title="<spring:message code="title.list"/>"><spring:message code="title.list"/></a></span><!-- 목록 -->
			</li>
		</ul>
	</div>

    </form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:20%" />
			<col style="width:25%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxyLog.proxyId.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxyLog.proxyNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxyLog.clntPort.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxyLog.clntIp.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxyLog.conectTime.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="proxyLog" items="${proxyLogList}" varStatus="status">
		      <tr>
		        <td><c:out value="${proxyLog.proxyId}"/></td>
		        <td><c:out value="${proxyLog.proxyNm}"/></td>
		        <td><c:out value="${proxyLog.clntPort}"/></td>
		        <td><c:out value="${proxyLog.clntIp}"/></td>
		        <td><c:out value="${proxyLog.conectTime}"/></td>
		      </tr>
		     </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>
</body>
</html>