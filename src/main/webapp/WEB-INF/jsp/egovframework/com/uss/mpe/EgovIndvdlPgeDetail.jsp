<%
 /**
  * @Class Name : EgovIndvdlPgeDetail.jsp
  * @Description : EgovIndvdlPgeDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUssMpe.indvdlPgeVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="indvdlPgeForm" action="<c:url value='/uss/mpe/updateIndvdlPgeView.do'/>" method="post">
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
		<!-- 컨텐츠명 -->
		<tr>
			<th><spring:message code="comUssMpe.indvdlPgeVO.cntntsNm" /></th>
			<td class="left"><c:out value="${result.cntntsNm}"/></td>
		</tr>
		<!-- 컨텐츠URL -->
		<tr>
			<th><spring:message code="comUssMpe.indvdlPgeVO.cntcUrl" /></th>
			<td class="left"><c:out value="${result.cntcUrl}"/></td>
		</tr>
		<!-- 미리보기URL -->
		<tr>
			<th><spring:message code="comUssMpe.indvdlPgeVO.cntntsLinkUrl" /></th>
			<td class="left"><c:out value="${result.cntntsLinkUrl}"/></td>
		</tr>
		<!-- 사이트설명 -->
		<tr>
			<th class="vtop"><spring:message code="comUssMpe.indvdlPgeVO.cntntsDc" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.cntntsDc , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 사용여부 -->
		<tr>
			<th><spring:message code="comUssMpe.indvdlPgeVO.cntntsUseAt" /></th>
			<td class="left"><c:out value="${result.cntntsUseAt}"/></td>
		</tr>
		
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/mpe/selectIndvdlPgeList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="cntntsId" type="hidden" value="<c:out value="${result.cntntsId}" />">
<input name="cmd" type="hidden" value="">
</form>

</body>
</html>
