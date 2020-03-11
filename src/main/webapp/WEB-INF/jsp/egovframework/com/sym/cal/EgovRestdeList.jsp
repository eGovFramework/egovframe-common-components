<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovRestdeList.jsp
  * @Description : EgovRestdeList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *   2011.08.12   서준식              페이징 번호 정렬이 리스트와 일치하도록 수정
  							   CSS 경로 수정
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="sym.cal.title"/></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/cal/EgovRestdeList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_Restde(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_Restde(){
	location.href = "<c:url value='/sym/cal/EgovRestdeRegist.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_Restde(restdeNo){
	var varForm				 = document.all["Form"];
	varForm.action           = "<c:url value='/sym/cal/EgovRestdeDetail.do'/>";
	varForm.restdeNo.value   = restdeNo;
	varForm.submit();
}
-->
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>

<form name="listForm" action="<c:url value='/sym/cal/EgovRestdeList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="" id = "searchCondition" >
				<option selected value=''><spring:message code="sym.cal.select" /></option><!-- --선택하세요-- -->
				<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="sym.cal.restDay" /></option><!-- 휴일일자 -->
				<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="sym.cal.restName" /></option><!-- 휴일명 -->
				</select>
				<input id="searchKeyword" class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword}" maxlength="35" size="35" onkeypress="press();" />
				
				<span class="btn_b"><a href="#noscript" onclick="fn_egov_search_Restde(); return false;" title="<spring:message code="button.inquire" />"><spring:message code="button.inquire" /></a></span>
				<input class="s_btn" type="submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" />" onclick="fn_egov_regist_Restde(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:30%" />
			<col style="width:25%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="sym.cal.column1" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="sym.cal.column2" /></th><!-- 휴일일자 -->
			   <th scope="col"><spring:message code="sym.cal.column3" /></th><!-- 휴일명 -->
			   <th scope="col"><spring:message code="sym.cal.column4" /></th><!-- 휴일구분 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;cursor:hand;" onclick="fn_egov_detail_Restde('${resultInfo.restdeNo}');">
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td><c:out value='${fn:substring(resultInfo.restdeDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.restdeDe,  4,6)}'/>-<c:out value='${fn:substring(resultInfo.restdeDe, 6, 8)}'/></td>
				<td>${resultInfo.restdeNm}</td>
				<td>${resultInfo.restdeSe}</td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan=4>
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
		</ul>
	</div>
	
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	
</form>
<form name="Form" method="post">
	<input type="hidden" name="restdeNo">
	<input type="submit" id="invisible" class="invisible"/>
</form>
	
</div>




</body>
</html>
