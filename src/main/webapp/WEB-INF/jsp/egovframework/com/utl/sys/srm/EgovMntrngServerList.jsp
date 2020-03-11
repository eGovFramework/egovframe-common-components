<%--
/**
 * @Class Name  : EgovMntrngServerList.java
 * @Description : EgovMntrngServerList jsp
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
<c:set var="pageTitle"><spring:message code="ultSysScm.mntrngServer.Title"/></c:set>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">

function fncSelectMntrngServerList(pageNo) {

    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/srm/selectMntrngServerList.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/srm/selectMntrngServerList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
    	fncSelectMntrngServerList('1');
    }
}


</script>
</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

	<form name="listForm" action="<c:url value='/utl/sys/srm/selectMntrngServerList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="ultSysScm.mntrngServerList.ServerName" /> : </label>
				<input id="strServerNm" class="s_input2 vat" name="strServerNm" type="text" value='<c:out value="${serverResrceMntrngVO.strServerNm}"/>' size="20" onkeypress="press();" title="<spring:message code="ultSysScm.mntrngServerList.ServerName" /> <spring:message code="input.input" />" /><!-- 서버H/W 명 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectMntrngServerList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/srm/selectServerResrceMntrngList.do'/>" onclick="" title="<spring:message code="ultSysScm.mntrngServerList.btnLog" /> <spring:message code="input.button" />"><spring:message code="ultSysScm.mntrngServerList.btnLog" /></a></span><!-- 로그 -->
			</li>
		</ul>
	</div>
	<input type="hidden" name="logId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty serverResrceMntrngVO.pageIndex }">1</c:if><c:if test="${!empty serverResrceMntrngVO.pageIndex }"><c:out value='${serverResrceMntrngVO.pageIndex}'/></c:if>">
	</form>

	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:25%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ultSysScm.mntrngServerList.ServerId" /></th><!-- 서버H/W ID -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngServerList.ServerName" /></th><!-- 서버H/W 명 -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngServerList.ServerIp" /></th><!-- 서버H/W IP  -->
			   <th scope="col"><spring:message code="ultSysScm.mntrngServerList.ServerEmail" /></th><!--담당자 E-Mail  -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="mntrngServer" items="${mntrngServerList}" varStatus="status">
			<tr>
				<td><c:out value="${mntrngServer.serverEqpmnId}"/></td>
				<td><c:out value="${mntrngServer.serverNm}"/><c:if test="${serverResrceMntrng.cpuUseRt != null}">%</c:if></td>
				<td><c:out value="${mntrngServer.serverEqpmnIp}"/><c:if test="${serverResrceMntrng.cpuUseRt != null}">%</c:if></td>
				<td><c:out value="${mntrngServer.mngrEamilAddr}"/></td>
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