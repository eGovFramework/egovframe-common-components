<%
 /**
  * @Class Name : EgovCommuMasterDetail.jsp
  * @Description : EgovCommuMasterDetail 화면
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
<c:set var="pageTitle"><spring:message code="comCopCmy.commuMasterVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title><!-- 커뮤니티 마스터 상세조회 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_commu(cmmntyId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.CommuMasterForm.cmmntyId.value = cmmntyId;	
		document.CommuMasterForm.action = "<c:url value='/cop/cmy/deleteCommuMaster.do'/>";
		document.CommuMasterForm.submit();	
	}	
}	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="CommuMasterForm" action="<c:url value='/cop/cmy/updateCommuMasterView.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2><!-- 커뮤니티 마스터 상세조회 -->

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 커뮤니티명 -->
		<tr>
			<th><spring:message code="comCopCmy.commuMasterVO.detail.cmmntyNm" /></th>
			<td colspan="5" class="left"><c:out value="${result.cmmntyNm}"/></td>
		</tr>
		<!-- 등록자, 등록일, 사용여부 -->
		<tr>
			<th><spring:message code="table.reger" /></th>
			<td class="left"><c:out value="${result.frstRegisterNm}"/></td>
			<th><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
			<th><spring:message code="comCopCmy.commuMasterVO.detail.useAt" /></th>
			<td class="left"><c:out value="${result.useAt}"/></td>
		</tr>
		<!-- 커뮤니티 소개내용 -->
		<tr>
			<th class="vtop"><spring:message code="comCopCmy.commuMasterVO.detail.cmmntyIntrcn" /></th>
			<td colspan="5" class="cnt">
				<c:out value="${fn:replace(result.cmmntyIntrcn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<c:if test="${result.useAt == 'Y' }">
		<!-- 커뮤니티 주소 -->
		<tr>
			<th class="vtop"><spring:message code="comCopCmy.commuMasterVO.detail.commuAdres" /></th>
			<td colspan="5" class="cnt">
			<a href="<c:url value='/cop/cmy/cmmntyMain.do?cmmntyId=${result.cmmntyId}' />">/cop/cmy/cmmntyMain.do?cmmntyId=${result.cmmntyId }</a>	
			</td>
		</tr>
		</c:if>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/cop/cmy/selectCommuMasterList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmmntyId" type="hidden" value="<c:out value="${result.cmmntyId}" />">
<input name="cmd" type="hidden" value="">
</form>

</body>
</html>
