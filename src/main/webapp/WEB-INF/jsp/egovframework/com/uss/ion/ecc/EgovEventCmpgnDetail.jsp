<%
 /**
  * @Class Name : EgovEventCmpgnDetail.jsp
  * @Description : EgovEventCmpgnDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssIonEcc.eventCmpgnVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_event(eventId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.eventCmpgnForm.eventId.value = eventId;	
		document.eventCmpgnForm.action = "<c:url value='/uss/ion/ecc/deleteEventCmpgn.do'/>";
		document.eventCmpgnForm.submit();	
	}	
}	
	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="eventCmpgnForm" action="<c:url value='/uss/ion/ecc/updateEventCmpgnView.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 행사유형 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.eventTyCode" /></th>
			<td class="left"><c:out value="${result.eventTyCodeNm}"/></td>
		</tr>
		
		<!-- 행사내용 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonEcc.eventCmpgnVO.eventCn" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.eventCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<!-- 행사시작일자 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonEcc.eventCmpgnVO.eventSvcBeginDe" /></th>
			<td class="left"><c:out value="${result.eventSvcBeginDe}"/></td>
		</tr>
		
		<!-- 행사종료일자 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonEcc.eventCmpgnVO.eventSvcEndDe" /></th>
			<td class="left"><c:out value="${result.eventSvcEndDe}"/></td>
		</tr>

		<!-- 서비스이용인원수 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.svcUseNmprCo" /></th>
			<td class="left"><c:out value="${result.svcUseNmprCo}"/></td>
		</tr>

		<!-- 담당자명 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.chargerNm" /></th>
			<td class="left"><c:out value="${result.chargerNm}"/></td>
		</tr>

		<!-- 준비물내용 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.prparetgCn" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.prparetgCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>

		<!-- 승인여부 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.eventConfmAt" /></th>
			<td class="left"><c:out value="${result.eventConfmAt}"/></td>
		</tr>

		<!-- 승인일 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.eventConfmDe" /></th>
			<td class="left"><c:out value="${result.eventConfmDe}"/></td>
		</tr>

		<!-- 외부인사정보 -->
		<tr>
			<th><spring:message code="comUssIonEcc.tnextrlHrVO.title" /></th>
			<td class="left"><a href="<c:url value='/uss/ion/ecc/selectTnextrlHrList.do?eventId=${result.eventId}' />" title="<spring:message code="comUssIonEcc.tnextrlHrVO.title" /> <spring:message code="title.inquire" />"><spring:message code="comUssIonEcc.tnextrlHrVO.title" /> <spring:message code="title.inquire" /></a></td>
		</tr>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ecc/deleteEventCmpgn.do' />" onClick="fn_egov_delete_event('<c:out value="${result.eventId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/ecc/selectEventCmpgnList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>
<input name="eventId" type="hidden" value="<c:out value="${result.eventId}" />">
<input name="cmd" type="hidden" value="">
</form>
</body>
</html>
