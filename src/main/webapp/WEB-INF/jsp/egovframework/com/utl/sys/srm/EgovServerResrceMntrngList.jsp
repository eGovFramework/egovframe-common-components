<%--
/**
 * @Class Name  : EgovServerResrceMntrngList.java
 * @Description : EgovServerResrceMntrngList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    lee.m.j     최초 생성
 * @ 2018.10.05                공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2010.09.01
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
<c:set var="pageTitle"><spring:message code="ultSysScm.mntrngResrceServerList.Title"/></c:set>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	$("#strStartDt").datepicker(  
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


	$("#strEndDt").datepicker( 
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

function fncSelectServerResrceMntrngList(pageNo) {

    var fromDate = document.listForm.strStartDt.value;
    var endDate = document.listForm.strEndDt.value;

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
        alert("<spring:message code="ultSysScm.mntrngResrceServerList.validate.datatFail" />");
        return;
    } else {
        if(strTmpFromDate > strTmpEndDate) {
            alert("<spring:message code="ultSysScm.mntrngResrceServerList.validate.datatCheck" />");
            return;
        }
    }

    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/srm/selectServerResrceMntrngList.do'/>";
    document.listForm.submit();
   
}

function fncSelectServerResrceMntrng(logId) {
    document.listForm.logId.value = logId;
    document.listForm.action = "<c:url value='/utl/sys/srm/getServerResrceMntrng.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/srm/selectServerResrceMntrngList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
        fncSelectServerResrceMntrngList('1');
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

</script>
</head>

<body onload="fn_egov_init()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">

	<h1>${pageTitle} <spring:message code="title.list" /></h1>

	<form name="listForm" action="<c:url value='/utl/sys/srm/selectServerResrceMntrngList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="ultSysScm.mntrngResrceServerList.scServerName" /> : </label><!-- 서버H/W 명  -->
				<input id="strServerNm" class="s_input2 vat" name="strServerNm" type="text" value='<c:out value="${serverResrceMntrngVO.strServerNm}"/>' size="8" onkeypress="press();" title="<spring:message code="ultSysScm.mntrngResrceServerList.scServerName" /> <spring:message code="input.input" />" />
				
				<label for=""><spring:message code="ultSysScm.mntrngResrceServerList.scBetween" /> : </label><!-- 기간  -->
				<input type="text" name="strStartDt" id="strStartDt" value="<c:out value='${pmServerResrceMntrng.strStartDt}'/>" size="12" maxlength="10" title="<spring:message code="ultSysScm.mntrngResrceServerList.scFromDate" /> <spring:message code="input.input" />" />
				
				
				~ <input type="text" name="strEndDt" id="strEndDt" value="<c:out value='${pmServerResrceMntrng.strEndDt}'/>" size="12" maxlength="10" title="<spring:message code="ultSysScm.mntrngResrceServerList.scToDate" /> <spring:message code="input.input" />" />
				
				<input type="hidden" name="strServerResrceMntrngDate" value=""/>
			
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectServerResrceMntrngList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/srm/selectMntrngServerList.do'/>" onclick="" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
			</li>
		</ul>
	</div>
	<input type="hidden" name="logId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty serverResrceMntrngVO.pageIndex }">1</c:if><c:if test="${!empty serverResrceMntrngVO.pageIndex }"><c:out value='${serverResrceMntrngVO.pageIndex}'/></c:if>">
	</form>

	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<caption></caption>
		<colgroup>
			<col style="width:20%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ultSysScm.mntrngResrceServerList.listServerName" /></th><!-- 서버H/W 명 -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngResrceServerList.listServerIp" /></th><!-- >서버H/W IP -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngResrceServerList.listCpuUse" /></th><!--  CPU사용률 -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngResrceServerList.lisMemoryUse" /></th><!--메모리사용률  -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngResrceServerList.listServerStatus" /></th><!-- 서비스상태 -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngResrceServerList.listCreateDatetime" /></th><!-- 생성일시 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="serverResrceMntrng" items="${serverResrceMntrngList}" varStatus="status">
			  <tr>
			     <td>
			        <form name="item" method="post" action="<c:url value='/utl/sys/srm/getServerResrceMntrng.do'/>">
			            <input type="hidden" name="logId" value="<c:out value="${serverResrceMntrng.logId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${serverResrceMntrngVO.pageIndex}'/>">
			            <input type="hidden" name="strServerNm" value="<c:out value="${serverResrceMntrngVO.strServerNm}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${serverResrceMntrng.serverNm}"/>" onclick="fncSelectServerResrceMntrng('<c:out value="${serverResrceMntrng.logId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${serverResrceMntrng.serverEqpmnIp}"/></td>
			    <td><c:out value="${serverResrceMntrng.cpuUseRt}"/><c:if test="${serverResrceMntrng.cpuUseRt != null}">%</c:if></td>
			    <td><c:out value="${serverResrceMntrng.moryUseRt}"/><c:if test="${serverResrceMntrng.cpuUseRt != null}">%</c:if></td>
			    <td><c:out value="${serverResrceMntrng.svcSttusNm}"/></td>
			    <td><c:out value="${serverResrceMntrng.creatDt}"/></td>
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