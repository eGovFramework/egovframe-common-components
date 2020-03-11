<%
 /**
  * @Class Name : EgovQnaAnswerDetail.jsp
  * @Description : EgovQnaAnswerDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUssOlhQna.qnaAnswerVO.title"/></c:set>
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

<form name="qnaForm" action="<c:url value='/uss/olh/qna/updateQnaAnswerView.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: 15%;">
		<col style="width: ;">
		<col style="width: 15%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
	
	
		<!-- 작성자명 -->
		<tr>
			<th><spring:message code="table.reger" /></th>
			<td class="left" colspan="3"><c:out value="${result.wrterNm}"/></td>
		</tr>
		<!-- 전화 -->
		<tr>
			<th><spring:message code="comUssOlhQna.qnaVO.telNo" /></th>
			<td class="left" colspan="3"><c:out value="${result.areaNo}"/> - <c:out value="${result.middleTelno}"/> - <c:out value="${result.endTelno}"/></td>
		</tr>
		<!-- 이메일 -->
		<tr>
			<th><spring:message code="comUssOlhQna.qnaVO.emailAdres" /></th>
			<td class="left"><c:out value="${result.emailAdres}"/></td>
			<th><spring:message code="comUssOlhQna.qnaVO.emailAnswerAt" /></th>
			<td class="left"><input name="emailAnswerAt" type="checkbox"  disabled <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if> title="<spring:message code="comUssOlhQna.qnaVO.emailAnswerAt" /> "></td>
		</tr>
		<!-- 작성일자 -->
		<tr>
			<th><spring:message code="table.regdate" /></th>
			<td class="left" colspan="3"><c:out value="${result.frstRegisterPnttm}"/></td>
		</tr>
		<!-- 조회수 -->
		<tr>
			<th><spring:message code="comUssOlhQna.qnaVO.inqireCo" /></th>
			<td class="left" colspan="3"><c:out value="${result.inqireCo}"/></td>
		</tr>
		<!-- 처리상태 -->
		<tr>
			<th><spring:message code="comUssOlhQna.qnaVO.qnaProcessSttusCode" /></th>
			<td class="left" colspan="3"><c:out value="${result.qnaProcessSttusCodeNm}"/></td>
		</tr>
		<!-- 질문제목 -->
		<tr>
			<th><spring:message code="comUssOlhQna.qnaVO.qestnSj" /></th>
			<td class="left" colspan="3"><c:out value="${result.qestnSj}"/></td>
		</tr>
		<!-- 질문 내용 -->
		<tr>
			<th class="vtop"><spring:message code="comUssOlhQna.qnaVO.qestnCn" /></th>
			<td class="cnt" colspan="3">
				<c:out value="${fn:replace(result.qestnCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>

		<!-- 답변 내용 -->
		<c:if test="${result.qnaProcessSttusCode ==  '3'}">
		<tr>
			<th class="vtop"><spring:message code="comUssOlhQna.qnaVO.answerCn" /></th>
			<td class="cnt" colspan="3">
				<c:out value="${fn:replace(result.answerCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		</c:if>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.reply" />" title="<spring:message code="title.reply" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olh/qna/selectQnaAnswerList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>
<input name="qaId" type="hidden" value="<c:out value="${result.qaId}" />">
<input name="cmd" type="hidden" value="">
</form>
</body>
</html>
