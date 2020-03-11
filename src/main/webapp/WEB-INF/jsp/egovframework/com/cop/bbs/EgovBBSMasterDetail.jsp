<%
 /**
  * @Class Name : EgovBBSMasterDetail.jsp
  * @Description : EgovBBSMasterDetail 화면
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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title><!-- 게시판 상세조회 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_bbs(bbsId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.BBSMasterForm.bbsId.value = bbsId;	
		document.BBSMasterForm.action = "<c:url value='/cop/bbs/deleteBBSMaster.do'/>";
		document.BBSMasterForm.submit();	
	}	
}	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="BBSMasterForm" action="<c:url value='/cop/bbs/updateBBSMasterView.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2><!-- 게시판 상세조회 -->

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
		<!-- 게시판명 -->
		<tr>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.bbsNm" /></th>
			<td colspan="3" class="left"><c:out value="${result.bbsNm}"/></td>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.bbsTyCode" /></th>
			<td class="left"><c:out value="${result.bbsTyCodeNm}"/></td>
		</tr>
		<!-- 등록자, 등록일, 사용여부 -->
		<tr>
			<th><spring:message code="table.reger" /></th>
			<td class="left"><c:out value="${result.frstRegisterNm}"/></td>
			<th><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.useAt" /></th>
			<td class="left"><c:out value="${result.useAt}"/></td>
		</tr>
		<!-- 답장가능여부, 파일첨부가능여부, 첨부가능파일숫자 -->
		<tr>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.replyPosblAt" /></th>
			<td class="left"><c:out value="${result.replyPosblAt}"/></td>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.fileAtchPosblAt" /></th>
			<td class="left"><c:out value="${result.fileAtchPosblAt}"/></td>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.atchPosblFileNumber" /></th>
			<td class="left"><c:out value="${result.atchPosblFileNumber}"/></td>
		</tr>
		<!-- 게시판 소개내용 -->
		<tr>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.bbsIntrcn" /></th>
			<td colspan="5" class="cnt">
				<c:out value="${fn:replace(result.bbsIntrcn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<c:if test="${result.useAt == 'Y' }">
			<tr>
				<th><spring:message code="comCopBbs.boardMasterVO.detail.bbsAdres" /></th>
				<td colspan="5" class="cnt">
				<a href="<c:url value='/cop/bbs/selectArticleList.do?bbsId=${result.bbsId}' />">/cop/bbs/selectArticleList.do?bbsId=${result.bbsId }</a>	
				</td>
			</tr>
		</c:if>
		<tr>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.option" /></th><!-- 추가선택사항 -->
			<td colspan="5" class="cnt">
				<c:if test="${result.option == ''}"><spring:message code="comCopBbs.boardMasterVO.detail.option1" /></c:if><!-- 미선택 -->
				<c:if test="${result.option == 'comment'}"><spring:message code="comCopBbs.boardMasterVO.detail.option2" /></c:if><!-- 댓글 -->
				<c:if test="${result.option == 'stsfdg'}"><spring:message code="comCopBbs.boardMasterVO.detail.option3" /></c:if><!-- 만족도조사 -->
			</td>
		</tr>
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/cop/bbs/selectBBSMasterInfs.do' /><c:if test='${result.cmmntyId != null}'>?cmmntyId=${result.cmmntyId}</c:if>"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmmntyId" type="hidden" value="<c:out value="${result.cmmntyId}" />">
<input name="bbsId" type="hidden" value="<c:out value="${result.bbsId}" />">
<input name="cmd" type="hidden" value="">
</form>

</body>
</html>
