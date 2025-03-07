<%
 /**
  * @Class Name : EgovCcmCmmnDetailCodeList.jsp
  * @Description : 공통상세코드 목록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.08.31   이정은              표준프레임워크 v3.7 개선
  *   2024.10.29	LeeBaekHaeng	검색조건 유지
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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.title"/></c:set>
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
	document.CcmDeCodeForm.searchCondition.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.CcmDeCodeForm.pageIndex.value = pageNo;
	document.CcmDeCodeForm.action = "<c:url value='/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do'/>";
   	document.CcmDeCodeForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_code(){
	document.CcmDeCodeForm.pageIndex.value = 1;
	document.CcmDeCodeForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_codedetail(codeId, code) {
	// 사이트 키값(siteId) 셋팅.
	document.CcmDeCodeForm.codeId.value = codeId;
	document.CcmDeCodeForm.code.value = code;
  	document.CcmDeCodeForm.action = "<c:url value='/sym/ccm/cde/SelectCcmCmmnDetailCodeDetail.do'/>";
  	document.CcmDeCodeForm.submit();
}
</script>
</head>
<body onload="fn_egov_init()">

<form name="CcmDeCodeForm" action="<c:url value='/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do'/>" method="get" onsubmit="fn_egov_search_code(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	
	<!-- 검색영역 -->
			<!-- 검색조건선택 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" />">
					<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
					<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId" /></option><!-- 코드ID -->
					<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code" /></option><!-- 코드ID -->
					<option value="3"  <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm" /></option><!-- 코드명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="<c:url value='/sym/ccm/cde/RegistCcmCmmnDetailCodeView.do' />?searchCondition=<c:out value="${searchVO.searchCondition}" />&searchKeyword=<c:out value="${searchVO.searchKeyword}" />&pageIndex=<c:out value="${searchVO.pageIndex}" />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
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
		<col style="width: 40%;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId" /></th><!-- 코드ID -->
		<th class="board_th_link"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code" /></th><!-- 코드 -->
		<th><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm"/></th><!-- 코드명 -->
		<th><spring:message code="comSymCcmCde.cmmnDetailCodeVO.useAt" /></th><!-- 사용여부 -->
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
		<td><c:out value='${resultInfo.codeId}'/></td>
		<td><c:out value='${resultInfo.code}'/></td>
		<td><a href="<c:url value='/sym/ccm/cde/SelectCcmCmmnDetailCodeDetail.do'/>?codeId=${resultInfo.codeId}&amp;code=${resultInfo.code}" onClick="fn_egov_inquire_codedetail('<c:out value="${resultInfo.codeId}"/>','<c:out value="${resultInfo.code}"/>');return false;"><c:out value='${fn:substring(resultInfo.codeNm, 0, 40)}'/></a></td>
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
	 
</div>

<input name="codeId" type="hidden" value="">
<input name="code" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>