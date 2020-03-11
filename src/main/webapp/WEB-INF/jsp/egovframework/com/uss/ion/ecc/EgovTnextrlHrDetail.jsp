<%
 /**
  * @Class Name : EgovTnextrlHrDetail.jsp
  * @Description : EgovTnextrlHrDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUssIonEcc.tnextrlHrVO.title"/></c:set>
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
 function fn_egov_delete_hr(extrlHrId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.tnextrlHrForm.extrlHrId.value = extrlHrId;	
		document.tnextrlHrForm.action = "<c:url value='/uss/ion/ecc/deleteTnextrlHr.do'/>";
		document.tnextrlHrForm.submit();	
	}	
}	
	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="tnextrlHrForm" action="<c:url value='/uss/ion/ecc/updateTnextrlHrView.do'/>" method="post">
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
		<!-- 행사/이벤트/캠페인 -->
		<tr>
			<th><spring:message code="comUssIonEcc.eventCmpgnVO.title" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.eventCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<!-- 성별 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonEcc.tnextrlHrVO.sexdstnCode" /></th>
			<td class="left"><c:out value="${result.sexdstnCodeNm}"/></td>
		</tr>
		
		<!-- 외부인사명 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonEcc.tnextrlHrVO.extrlHrNm" /></th>
			<td class="left"><c:out value="${result.extrlHrNm}"/></td>
		</tr>

		<!-- 생년월일 -->
		<tr>
			<th><spring:message code="comUssIonEcc.tnextrlHrVO.brth" /></th>
			<td class="left"><c:out value="${result.brth}"/></td>
		</tr>

		<!-- 직업유형 -->
		<tr>
			<th><spring:message code="comUssIonEcc.tnextrlHrVO.occpTyCode" /></th>
			<td class="left"><c:out value="${result.occpTyCodeNm}"/></td>
		</tr>

		<!-- 소속기관 -->
		<tr>
			<th><spring:message code="comUssIonEcc.tnextrlHrVO.psitnInsttNm" /></th>
			<td class="left"><c:out value="${result.psitnInsttNm}"/></td>
		</tr>

		<!-- 연락처 -->
		<tr>
			<th><spring:message code="comUssIonEcc.tnextrlHrVO.telNo" /></th>
			<td class="left"><c:out value="${result.areaNo}"/>-<c:out value="${result.middleTelno}"/>-<c:out value="${result.endTelno}"/></td>
		</tr>
		
		<!-- 이메일주소 -->
		<tr>
			<th><spring:message code="comUssIonEcc.tnextrlHrVO.emailAdres" /></th>
			<td class="left"><c:out value="${result.emailAdres}"/></td>
		</tr>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ecc/deleteTnextrlHr.do' />" onClick="fn_egov_delete_hr('<c:out value="${result.extrlHrId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/ecc/selectTnextrlHrList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>
<input name="extrlHrId" type="hidden" value="<c:out value="${result.extrlHrId}" />">
<input name="cmd" type="hidden" value="">
</form>
</body>
</html>
