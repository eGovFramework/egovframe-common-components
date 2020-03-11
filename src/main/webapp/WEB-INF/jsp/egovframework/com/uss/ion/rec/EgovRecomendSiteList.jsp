<%
 /**
  * @Class Name : EgovRecomendSiteList.jsp
  * @Description : EgovRecomendSiteList 화면
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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssIonRec.recomendSiteVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.recomendSiteForm.searchCondition.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.recomendSiteForm.pageIndex.value = pageNo;
	document.recomendSiteForm.action = "<c:url value='/uss/ion/rec/selectRecomendSiteList.do'/>";
   	document.recomendSiteForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_site(){
	document.recomendSiteForm.pageIndex.value = 1;
	document.recomendSiteForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_sitedetail(recomendSiteId) {
	// 사이트 키값(siteId) 셋팅.
	document.recomendSiteForm.recomendSiteId.value = recomendSiteId;
  	document.recomendSiteForm.action = "<c:url value='/uss/ion/rec/selectRecomendSiteDetail.do'/>";
  	document.recomendSiteForm.submit();
}
</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="recomendSiteForm" action="<c:url value='/uss/ion/rec/selectRecomendSiteList.do'/>" method="post" onSubmit="fn_egov_search_site(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteNm" /></option><!-- 추천사이트명 -->
					<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteUrl" /></option><!-- 추천사이트URL -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/rec/insertRecomendSiteView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 8%;">
		<col style="width: 35%;">
		<col style="width: 35%;">
		<col style="width: 12%;">
		<col style="width: 11%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteNm" /></th><!-- 추천사이트명 -->
		<th><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteUrl" /></th><!-- 추천사이트URL -->
		<th><spring:message code="comUssIonRec.recomendSiteVO.recomendConfmAt" /></th><!-- 승인여부 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
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
		<td><a href="<c:url value='/uss/ion/rec/selectRecomendSiteDetail.do?recomendSiteId=${resultInfo.recomendSiteId}'/>" onClick="fn_egov_inquire_sitedetail('<c:out value="${resultInfo.recomendSiteId}"/>');return false;"><c:out value='${fn:substring(resultInfo.recomendSiteNm, 0, 40)}'/></a></td>
		<td><c:out value='${fn:substring(resultInfo.recomendSiteUrl, 0, 40)}'/></td>
		<td><c:out value='${resultInfo.recomendConfmAt}'/></td>
		<td><c:out value='${resultInfo.frstRegisterPnttm}'/></td>
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
	
	
</div>

<input name="recomendSiteId" type="hidden" value="<c:out value='${searchVO.recomendSiteId}'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>