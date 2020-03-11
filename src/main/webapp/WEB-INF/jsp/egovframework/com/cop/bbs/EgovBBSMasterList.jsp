<%
 /**
  * @Class Name : EgovBBSMasterList.jsp
  * @Description : EgovBBSMasterList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  * @ 2016.06.13   김연호              표준프레임워크 v3.6 개선
  * @ 2018.10.15   최두영             표준프레임워크 V3.8 개선
  *  @author 공통서비스팀
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 게시판 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.BBSMasterForm.searchCnd.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.BBSMasterForm.pageIndex.value = pageNo;
	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>";
   	document.BBSMasterForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_bbssj(){
	document.BBSMasterForm.pageIndex.value = 1;
	document.BBSMasterForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_bbsdetail(bbsId) {
	// 사이트 키값(siteId) 셋팅.
	document.BBSMasterForm.bbsId.value = bbsId;
  	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectBBSMasterDetail.do'/>";
  	document.BBSMasterForm.submit();
}
</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="BBSMasterForm" action="<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>" method="post" onSubmit="fn_egov_search_bbssj(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 게시판 목록 -->
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.bbsNm" /></option><!-- 게시판명 -->
					<option value="1"  <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.bbsIntrcn" /></option><!-- 게시판 소개내용 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/cop/bbs/insertBBSMasterView.do?cmmntyId=${searchVO.cmmntyId}' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 40%;">
		<col style="width: 13%;">
		<col style="width: 13%;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopBbs.boardMasterVO.list.bbsNm" /></th><!-- 게시판명 -->
		<th><spring:message code="table.reger" /></th><!-- 작성자명 -->
		<th><spring:message code="table.regdate" /></th><!-- 작성시각 -->
		<th><spring:message code="comCopBbs.boardMasterVO.list.useAt" /></th><!-- 사용여부 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left"><a href="<c:url value='/cop/bbs/selectBBSMasterDetail.do?bbsId=${resultInfo.bbsId}'/>" onClick="fn_egov_inquire_bbsdetail('<c:out value="${resultInfo.bbsId}"/>');return false;"><c:out value='${fn:substring(resultInfo.bbsNm, 0, 40)}'/></a></td>
		<td><c:out value='${resultInfo.frstRegisterNm}'/></td>
		<td><c:out value='${resultInfo.frstRegisterPnttm}'/></td>
		<td><c:out value='${resultInfo.useAt}'/></td>		
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
		</ul>
	</div>
	
	<!-- 등록버튼 -->
	<!-- 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/cop/bbs/insertBBSMasterView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
	</div>
	-->
	
</div>
<input name="cmmntyId" type="hidden" value="<c:out value='${searchVO.cmmntyId}'/>">
<input name="bbsId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>