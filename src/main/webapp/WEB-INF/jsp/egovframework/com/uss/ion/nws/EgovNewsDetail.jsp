<%
 /**
  * @Class Name : EgovNewsDetail.jsp
  * @Description : EgovNewsDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUssIonNws.newsVO.title"/></c:set>
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
 function fn_egov_delete_news(newsId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.newsForm.newsId.value = newsId;	
		document.newsForm.action = "<c:url value='/uss/ion/nws/deleteNews.do'/>";
		document.newsForm.submit();	
	}	
}	
	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="newsForm" action="<c:url value='/uss/ion/nws/updateNewsView.do'/>" method="post">
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
		<!-- 뉴스제목 -->
		<tr>
			<th><spring:message code="comUssIonNws.newsVO.newsSj" /></th>
			<td class="left"><c:out value="${result.newsSj}"/></td>
		</tr>
		
		<!-- 뉴스 내용 -->
		<tr>
			<th><spring:message code="comUssIonNws.newsVO.newsCn" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.newsCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<!-- 뉴스출처 -->
		<tr>
			<th><spring:message code="comUssIonNws.newsVO.newsOrigin" /></th>
			<td class="left"><c:out value="${result.newsOrigin}"/></td>
		</tr>
		
		<!-- 게시일자 -->
		<tr>
			<th><spring:message code="comUssIonNws.newsVO.ntceDe" /></th>
			<td class="left"><c:out value="${result.ntceDe}"/></td>
		</tr>
		<!-- 등록일자 -->
		<tr>
			<th><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
		</tr>
		<!-- 첨부파일  -->
		<c:if test="${not empty result.atchFileId}">
		<tr>
			<th><spring:message code="comUssIonNws.newsVO.atchFile" /></th>
			<td>
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				<c:param name="param_atchFileId" value="${result.atchFileId}" />
			</c:import>
			</td>
		</tr>
	  	</c:if>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/nws/deleteNews.do' />" onClick="fn_egov_delete_news('<c:out value="${result.newsId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/nws/selectNewsList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>
<input name="newsId" type="hidden" value="<c:out value="${result.newsId}" />">
<input name="cmd" type="hidden" value="">
</form>
</body>
</html>
