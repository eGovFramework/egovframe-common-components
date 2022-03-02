<%--
/**
 * @Class Name  : EgovServerResrceMntrngDetail.java
 * @Description : EgovServerResrceMntrngDetail jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    lee.m.j     최초 생성
 * @ 2018.10.05                공통컴포넌트 3.8 개선
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
<c:set var="pageTitle"><spring:message code="ultSysScm.mntrngServerDetail.Title"/></c:set>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.detail" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">

function fncSelectServerResrceMntrngList() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/srm/selectServerResrceMntrngList.do'/>";
    varFrom.submit();
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>
	
	<form:form modelAttribute="serverResrceMntrng" method="post" action="${pageContext.request.contextPath}/utl/sys/srm/selectServerResrceMntrngList.do">
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<!-- 로그ID -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.logId" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.logId}'/>
			</td>
		</tr>
		<!-- 서버H/W 명 -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.serverName" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.serverNm}'/>
			</td>
		</tr>
		<!-- 서버H/W IP -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.serverIp" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.serverEqpmnIp}'/>
			</td>
		</tr>
		<!-- CPU사용률 -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.cpuUse" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.cpuUseRt}'/>
			</td>
		</tr>
		<!-- 메모리사용률 -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.memoryUse" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.moryUseRt}'/>
			</td>
		</tr>
		<!-- 서비스상태 -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.serviceStatus" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.svcSttusNm}'/>
			</td>
		</tr>
		 <c:if test="${serverResrceMntrng.svcSttus == '02' }">
		 <!-- 로그정보 -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.logInfo" /></th>
			<td class="left">
			    <textarea rows="5" cols="80" readOnly><c:out value='${serverResrceMntrng.logInfo}'/></textarea>
			</td>
		</tr>
		</c:if>
		<!-- 생성일시 -->
		<tr>
			<th><spring:message code="ultSysScm.mntrngServerDetail.crateDate" /></th>
			<td class="left">
			    <c:out value='${serverResrceMntrng.creatDt}'/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fncSelectServerResrceMntrngList(); return false;" />
	</div>
	<div style="clear:both;"></div>
	
	
	<input type="hidden" name="logId" value="<c:out value='${serverResrceMntrng.logId}'/>" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strServerNm" value="<c:out value='${serverResrceMntrngVO.strServerNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${serverResrceMntrngVO.pageIndex}'/>" />
    <input type="hidden" name="strStartDt" value="<c:out value='${pmServerResrceMntrng.strStartDt}'/>" />
    <input type="hidden" name="strEndDt" value="<c:out value='${pmServerResrceMntrng.strEndDt}'/>" />
  </form:form>
</div>





</body>
</html>

