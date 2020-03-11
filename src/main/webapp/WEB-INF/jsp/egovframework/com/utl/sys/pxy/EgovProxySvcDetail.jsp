<%--
/**
 * @Class Name  : EgovProxySvcDetail.java
 * @Description : EgovProxySvcDetail jsp
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
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<title>${pageTitle} <spring:message code="title.detail" /></title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectProxySvcList() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>";
    varFrom.submit();       
}

function fncProxySvcUpdateView(proxyId) {
    var varFrom = document.getElementById("proxySvc");
    varFrom.proxyId.value = proxyId;
    varFrom.action = "<c:url value='/utl/sys/pxy/updtViewProxySvc.do'/>";
    varFrom.submit();
}

function fncProxySvcDelete(proxyId) {
    var varFrom = document.getElementById("proxySvc");
    varFrom.proxyId.value = proxyId;
    varFrom.action = "<c:url value='/utl/sys/pxy/removeProxySvc.do'/>";
    if(confirm("<spring:message code='common.delete.msg' />")){
        varFrom.submit();
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUtlSysPxy.proxySvcDetail.title" /></h2><!-- 프록시설정 상세조회 -->
	
	<form:form commandName="proxySvc" method="post" action="${pageContext.request.contextPath}/utl/sys/pxy/removeProxySvc.do">
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.proxyId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.proxyNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.proxyIp}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.proxyPort}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.trgetSvcNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.trgetSvcNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcDc.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.svcDc}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.svcIp}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			   <c:out value='${proxySvc.svcPort}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcSttusNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${proxySvc.svcSttusNm}'/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/utl/sys/pxy/updtViewProxySvc.do'/>?proxyId=<c:out value='${proxySvc.proxyId}'/>" onclick="fncProxySvcUpdateView('${proxySvc.proxyId}'); return false;"><spring:message code="button.update" /></a></span>
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fncProxySvcDelete('${proxySvc.proxyId}')" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>?pageIndex=<c:out value='${proxySvcVO.pageIndex}'/>&amp;strProxyNm=<c:out value="${proxySvcVO.strProxyNm}"/>" onclick="fncSelectProxySvcList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	
	<input type="hidden" name="proxyId" value="<c:out value='${proxySvc.proxyId}'/>" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strProxyNm" value="<c:out value='${proxySvcVO.strProxyNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${proxySvcVO.pageIndex}'/>" />
  </form:form>
  
</div>
</body>
</html>