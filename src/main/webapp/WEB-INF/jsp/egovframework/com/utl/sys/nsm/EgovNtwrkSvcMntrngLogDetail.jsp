<% 
/**
 * @Class Name : EgovNtwrkSvcMntrngLogDetail.jsp
 * @Description : 네트워크서비스모니터로그 상세보기
 * @Modification Information
 * @
 * @ 수정일               수정자          수정내용
 * @ ----------   --------  ---------------------------
 * @ 2010.08.17   장철호          최초 생성
 * @ 2018.11.02   신용호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.17
 *  @version 1.0 
 *  @see
 *  
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ntwrkSvcMntrngLogVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_NtwrkSvcMntrngLog(){
	
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_ntwrksvcmntrnglog(){
		document.ntwrkSvcMntrngLogVO.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>";
		document.ntwrkSvcMntrngLogVO.submit();	
	}	

</script>
<title>${pageTitle} <spring:message code="title.detail" /></title>
</head>
<body onLoad="fn_egov_init_NtwrkSvcMntrngLog()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form modelAttribute="ntwrkSvcMntrngLogVO" name="ntwrkSvcMntrngLogVO" method="post" action="${pageContext.request.contextPath}/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2><!-- 네트워크서비스모니터링로그 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable" summary="이 표는 네트워크서비스모니터링 로그 정보를 제공하며, 로그ID, 시스템IP, 시스템포트, 시스템명, 모니터링상태, 로그정보, 생성일시 정보로 구성되어 있습니다 .">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.logId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngLog.logId}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngLog.sysIp}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngLog.sysPort}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngLog.sysNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.mntrngSttus.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngLog.mntrngSttus}" escapeXml="false" />
			</td>
		</tr>
		<c:if test="${ntwrkSvcMntrngLog.mntrngSttus} ne '정상'">
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.logInfo.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <textarea id="logInfo" name="logInfo" rows="10" cols="75" title="로그정보"><c:out value="${ntwrkSvcMntrngLog.logInfo}" escapeXml="false" /></textarea>
			</td>
		</tr>
		</c:if>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.creatDt.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngLog.creatDt}" escapeXml="false" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_ntwrksvcmntrnglog(); return false;" /><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" id="sysIp" name="sysIp" value="<c:out value='${ntwrkSvcMntrngLog.sysIp}'/>"/>
<input type="hidden" id="sysPort" name="sysPort" value="<c:out value='${ntwrkSvcMntrngLog.sysPort}'/>"/>
<input type="hidden" id="logId" name="logId" value="<c:out value='${ntwrkSvcMntrngLog.logId}'/>"/>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchWrd" value="<c:out value='${ntwrkSvcMntrngLogVO.searchWrd}'/>" />
<input type="hidden" name="searchCnd" value="<c:out value='${ntwrkSvcMntrngLogVO.searchCnd}'/>" />
<input type="hidden" name="pageIndex" value="<c:out value='${ntwrkSvcMntrngLogVO.pageIndex}'/>" />
<input name="searchBgnDe" type="hidden" value="<c:out value='${ntwrkSvcMntrngLogVO.searchBgnDe}'/>">
<input name="searchEndDe" type="hidden" value="<c:out value='${ntwrkSvcMntrngLogVO.searchEndDe}'/>">
<input name="searchBgnHour" type="hidden" value="<c:out value='${ntwrkSvcMntrngLogVO.searchBgnHour}'/>">
<input name="searchEndHour" type="hidden" value="<c:out value='${ntwrkSvcMntrngLogVO.searchEndHour}'/>">
<!-- 검색조건 유지 -->
</form:form>

</body>
</html>