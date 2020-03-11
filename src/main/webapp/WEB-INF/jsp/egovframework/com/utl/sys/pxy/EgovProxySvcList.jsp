<%--
/**
 * @Class Name  : EgovProxySvcList.java
 * @Description : EgovProxySvcList jsp
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
<c:set var="pageTitle"><spring:message code="comUtlSysPxy.proxySvc.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">

<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncSelectProxySvcList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>";
    document.listForm.submit();
}

function fncSelectProxySvc(proxyId) {
    document.listForm.proxyId.value = proxyId;
    document.listForm.action = "<c:url value='/utl/sys/pxy/getProxySvc.do'/>";
    document.listForm.submit();
}

function fncAddProxySvcInsert() {
    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    }
    document.listForm.action = "<c:url value='/utl/sys/pxy/addViewProxySvc.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
        fncSelectProxySvcList('1');
    }
}

-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<form name="listForm" action="<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>" method="post">
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<label for=""><spring:message code="comUtlSysPxy.proxySvcList.proxyNm.label" /> : </label>
				<input class="s_input2 vat" name="strProxyNm" type="text" value='<c:out value="${proxySvcVO.strProxyNm}"/>' size="30" onkeypress="press();" title="검색" />				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectProxySvcList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/pxy/addViewProxySvc.do'/>?pageIndex=<c:out value='${proxySvcVO.pageIndex}'/>&amp;strProxyNm=<c:out value="${proxySvcVO.strProxyNm}"/>" onclick="fncAddProxySvcInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				<span class="btn_b"><a href="<c:url value='/utl/sys/pxy/selectProxyLogList.do'/>" onclick="" title="<spring:message code="comUtlSysPxy.proxyLog.log"/>"><spring:message code="comUtlSysPxy.proxyLog.log"/></a></span><!-- 로그 -->
			</li>
		</ul>
	</div>
	</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:17%" />
			<col style="width:11%" />
			<col style="width:19%" />
			<col style="width:20%" />
			<col style="width:8%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxySvcList.proxyId.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxySvcList.proxyNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxySvcList.proxyPort.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxySvcList.trgetSvcNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxySvcList.svcIp.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysPxy.proxySvcList.svcSttusNm.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="proxySvc" items="${proxySvcList}" varStatus="status">
			<tr>
			  <td>
			      <form name="item" method="post" action="<c:url value='/utl/sys/pxy/getProxySvc.do'/>">
			          <input type="hidden" name="proxyId" value="<c:out value="${proxySvc.proxyId}"/>">
			          <input type="hidden" name="pageIndex" value="<c:out value='${proxySvcVO.pageIndex}'/>">
			          <input type="hidden" name="strProxyNm" value="<c:out value="${proxySvcVO.strProxyNm}"/>">
			          <span class="link"><input type="submit" value="<c:out value="${proxySvc.proxyId}"/>" onclick="fncSelectProxySvc('<c:out value="${proxySvc.proxyId}"/>'); return false;"></span>
			      </form>
			  </td>
			  <td><c:out value="${proxySvc.proxyNm}"/></td>
			  <td><c:out value="${proxySvc.proxyPort}"/></td>
			  <td><c:out value="${proxySvc.trgetSvcNm}"/></td>
			  <td><c:out value="${proxySvc.svcIp}"/>:<c:out value="${proxySvc.svcPort}"/></td>
			  <td><c:out value="${proxySvc.svcSttusNm}"/></td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(proxySvcList) == 0}">
			   <tr>
			     <td colspan="6"><spring:message code="common.nodata.msg" /></td>
			   </tr>
			</c:if>
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