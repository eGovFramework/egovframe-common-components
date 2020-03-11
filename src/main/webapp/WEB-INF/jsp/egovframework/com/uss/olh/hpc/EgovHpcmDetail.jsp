<%
 /**
  * @Class Name : EgovHpcmDetail.jsp
  * @Description : EgovHpcmDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUssOlhHpc.hpcmVO.title"/></c:set>
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
 function fn_egov_delete_hpcm(hpcmId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.hpcmForm.hpcmId.value = hpcmId;	
		document.hpcmForm.action = "<c:url value='/uss/olh/hpc/deleteHpcm.do'/>";
		document.hpcmForm.submit();	
	}	
}	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="hpcmForm" action="<c:url value='/uss/olh/hpc/updateHpcmView.do'/>" method="post">
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
		<!-- 도움말구분 -->
		<tr>
			<th><spring:message code="comUssOlhHpc.hpcmVO.hpcmSeCode" /></th>
			<td class="left"><c:out value="${result.hpcmSeCodeNm}"/></td>
		</tr>
		<!-- 도움말정의 -->
		<tr>
			<th><spring:message code="comUssOlhHpc.hpcmVO.hpcmDf" /></th>
			<td class="left"><c:out value="${result.hpcmDf}"/></td>
		</tr>
		<!-- 도움말설명 -->
		<tr>
			<th class="vtop"><spring:message code="comUssOlhHpc.hpcmVO.hpcmDc" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.hpcmDc , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<!-- 등록일자 -->
		<tr>
			<th class="vtop"><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
			</td>
		</tr>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olh/hpc/deleteHpcm.do' />" onClick="fn_egov_delete_hpcm('<c:out value="${result.hpcmId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/olh/hpc/selectHpcmList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="hpcmId" type="hidden" value="<c:out value="${result.hpcmId}" />">
<input name="cmd" type="hidden" value="">
</form>

</body>
</html>
