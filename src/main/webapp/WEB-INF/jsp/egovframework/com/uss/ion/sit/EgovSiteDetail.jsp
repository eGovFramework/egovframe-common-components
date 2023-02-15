<%
 /**
  * @Class Name : EgovSiteDetail.jsp
  * @Description : EgovSiteDetail 화면
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssIonSit.siteVO.title"/></c:set>
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
 function fn_egov_delete_site(siteId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.siteForm.siteId.value = siteId;	
		document.siteForm.action = "<c:url value='/uss/ion/sit/deleteSite.do'/>";
		document.siteForm.submit();	
	}	
}	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="siteForm" action="<c:url value='/uss/ion/sit/updateSiteView.do'/>" method="post">
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
		<!-- 사이트명 -->
		<tr>
			<th><spring:message code="comUssIonSit.siteVO.siteNm" /></th>
			<td class="left"><c:out value="${result.siteNm}"/></td>
		</tr>
		<!-- 사이트URL -->
		<tr>
			<th><spring:message code="comUssIonSit.siteVO.siteUrl" /></th>
			<td class="left"><c:out value="${result.siteUrl}"/></td>
		</tr>
		<!-- 사이트설명 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonSit.siteVO.siteDc" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.siteDc , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 사이트주제분류 -->
		<tr>
			<th><spring:message code="comUssIonSit.siteVO.siteThemaClCode" /></th>
			<td class="left"><c:out value="${result.siteThemaClNm}"/></td>
		</tr>
		<!-- 활성여부 -->
		<tr>
			<th><spring:message code="comUssIonSit.siteVO.actvtyAt" /></th>
			<td class="left"><c:out value="${result.actvtyAt}"/></td>
		</tr>
		<!-- 사용여부 -->
		<tr>
			<th><spring:message code="comUssIonSit.siteVO.useAt" /></th>
			<td class="left"><c:out value="${result.useAt}"/></td>
		</tr>
		<!-- 등록일자 -->
		<tr>
			<th class="vtop"><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
			</td>
		</tr>
		<!-- 등록자 -->
		<tr>
			<th><spring:message code="table.reger" /></th>
			<td class="left"><c:out value="${result.emplyrNm}"/></td>
		</tr>
		
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/sit/deleteSite.do' />" onclick="fn_egov_delete_site('<c:out value="${result.siteId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/sit/selectSiteList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="siteId" type="hidden" value="<c:out value="${result.siteId}" />">
<input name="cmd" type="hidden" value="">
</form>

</body>
</html>
