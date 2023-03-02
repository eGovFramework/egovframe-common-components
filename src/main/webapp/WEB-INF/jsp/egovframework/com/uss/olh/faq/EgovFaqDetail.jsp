<%
 /**
  * @Class Name : EgovFaqDetail.jsp
  * @Description : EgovFaqDetail 화면
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
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlhFaq.faqVO.title"/></c:set>
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
 function fn_egov_delete_faq(faqId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.faqForm.faqId.value = faqId;	
		document.faqForm.action = "<c:url value='/uss/olh/faq/deleteFaq.do'/>";
		document.faqForm.submit();	
	}	
}	
	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="faqForm" action="<c:url value='/uss/olh/faq/updateFaqView.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: ;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 질문제목 -->
		<tr>
			<th><spring:message code="comUssOlhFaq.faqVO.qestnSj" /></th>
			<td class="left"><c:out value="${result.qestnSj}"/></td>
		</tr>
		<!-- 조회수 -->
		<tr>
			<th><spring:message code="comUssOlhFaq.faqVO.inqireCo" /></th>
			<td class="left"><c:out value="${result.inqireCo}"/></td>
		</tr>
		<!-- 질문 내용 -->
		<tr>
			<th><spring:message code="comUssOlhFaq.faqVO.qestnCn" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.qestnCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 답변 내용 -->
		<tr>
			<th><spring:message code="comUssOlhFaq.faqVO.answerCn" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.answerCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 등록일자 -->
		<tr>
			<th><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
		</tr>
		<!-- 첨부파일  -->
		<c:if test="${not empty result.atchFileId}">
		<tr>
			<th><spring:message code="comUssOlhFaq.faqVO.atchFile" /></th>
			<td>
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				<c:param name="param_atchFileId" value="${egovc:encrypt(result.atchFileId)}" />
			</c:import>
			</td>
		</tr>
	  	</c:if>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olh/faq/deleteFaq.do' />" onClick="fn_egov_delete_faq('<c:out value="${result.faqId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/olh/faq/selectFaqList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>
<input name="faqId" type="hidden" value="<c:out value="${result.faqId}" />">
<input name="cmd" type="hidden" value="">
</form>
</body>
</html>
