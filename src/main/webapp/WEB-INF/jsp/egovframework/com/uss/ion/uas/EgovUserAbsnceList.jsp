<%
 /**
  * @Class Name : EgovUserAbsnceList.jsp
  * @Description : EgovUserAbsnceList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.07.01   lee.m.j              최초 생성
  *   2018.08.16   이정은              공통컴포넌트 3.8 개선
  *  @author lee.m.j
  *  @since 2009.07.01
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
<c:set var="pageTitle"><spring:message code="ussIonUas.userAbsnceList.userAbsnceList"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.userAbsnceForm.pageIndex.value = pageNo;
	document.userAbsnceForm.action = "<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>";
   	document.userAbsnceForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_userAbsnce(){
	document.userAbsnceForm.pageIndex.value = 1;
	document.userAbsnceForm.submit();
}
/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_egov_inquire_userAbsncedetail(userId, regYn) {
	if(regYn == 'N') {
        if(confirm('<spring:message code="ussIonUas.userAbsnceList.goToregisPage" />')) {/* 등록된 사용자부재 정보가 없습니다. 등록페이지로 이동하시겠습니까? */
        	location.replace("<c:url value='/uss/ion/uas/addViewUserAbsnce.do'/>?userId="+userId);
        } else {
            return false;
        }
	}
	return true;
}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="userAbsnceForm" action="<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>" method="post" onSubmit="fn_egov_search_userAbsnce(); return false;"> 
<div class="board">
	<h1>${pageTitle}</h1>
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="1"  <c:if test="${userAbsnceVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="ussIonUas.userAbsnceList.userNm" /></option><!-- 사용자명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${userAbsnceVO.searchKeyword}"/>'  maxlength="155" >
				<select name="selAbsnceAt" title="<spring:message code="ussIonUas.userAbsnceList.userAbsnceAt" />">
					<option value="A" <c:if test="${userAbsnceVO.selAbsnceAt eq 'A'}">selected</c:if>><spring:message code="ussIonUas.userAbsnceList.all" /></option>
					<option value="Y" <c:if test="${userAbsnceVO.selAbsnceAt eq 'Y'}">selected</c:if>>Y</option>
					<option value="N" <c:if test="${userAbsnceVO.selAbsnceAt eq 'N'}">selected</c:if>>N</option>
				</select>
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="ussIonUas.userAbsnceList.userId" /></th><!-- 사용자 ID -->
		<th class="board_th_link"><spring:message code="ussIonUas.userAbsnceList.userNm" /></th><!-- 사용자 명 -->
		<th><spring:message code="ussIonUas.userAbsnceList.userAbsnceAt" /></th><!-- 부재여부 -->
		<th><spring:message code="ussIonUas.userAbsnceList.regYn" /></th><!-- 등록여부 -->
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
		<td><a href="<c:url value='/uss/ion/uas/getUserAbsnce.do?userId=${resultInfo.userId}'/>" onClick="return fn_egov_inquire_userAbsncedetail('<c:out value="${resultInfo.userId}"/>', '<c:out value="${resultInfo.regYn}"/>');"><c:out value='${resultInfo.userId}'/></a></td>
		<td><c:out value='${resultInfo.userNm}'/></td>
		<td>
			<c:if test="${resultInfo.userAbsnceAt eq 'Y'}" ><c:out value="Y"/></c:if>
			<c:if test="${resultInfo.userAbsnceAt eq 'N'}" ><c:out value="N"/></c:if>
		</td>
		<td>
			<c:if test="${resultInfo.regYn eq 'Y'}" ><c:out value="Y"/></c:if>
			<c:if test="${resultInfo.regYn eq 'N'}" ><c:out value="N"/></c:if>
		</td>
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

<input name="userId" type="hidden" value="<c:out value='${userAbsnceVO.userId}'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${userAbsnceVO.pageIndex}'/>">
</form>

</body>
</html>
