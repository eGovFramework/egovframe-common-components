<%
 /**
  * @Class Name : EgovAdministrationWordDetail.jsp
  * @Description : EgovAdministrationWordDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUssOlhAwm.administrationWordVO.title"/></c:set>
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

<form name="administrationWordForm" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: 15%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
	
	
		<!-- 행정용어명 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.administWordNm" /></th>
			<td class="left"><c:out value="${result.administWordNm}"/></td>
		</tr>
		<!-- 행정용어영문명 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.administWordEngNm" /></th>
			<td class="left"><c:out value="${result.administWordEngNm}"/></td>
		</tr>
		<!-- 행정용어약어명 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.administWordAbrv" /></th>
			<td class="left"><c:out value="${result.administWordAbrv}"/></td>
		</tr>
		<!-- 주제영역 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.themaRelm" /></th>
			<td class="left"><c:out value="${result.themaRelm}"/></td>
		</tr>
		<!-- 용어구분 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.wordDomn" /></th>
			<td class="left"><c:out value="${result.wordDomnNm}"/></td>
		</tr>
		<!-- 관련표준어 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.stdWord" /></th>
			<td class="left"><c:out value="${result.stdWord}"/></td>
		</tr>
		<!-- 행정용어 정의 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.administWordDf" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.administWordDf , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 행정용어 설명 -->
		<tr>
			<th><spring:message code="comUssOlhAwm.administrationWordVO.administWordDc" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.administWordDc , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/olh/awm/selectAdministrationWordList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>
<input name="administWordId" type="hidden" value="<c:out value="${result.administWordId}" />">
<input name="cmd" type="hidden" value="">
</form>
</body>
</html>
