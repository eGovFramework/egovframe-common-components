<%
 /**
  * @Class Name : EgovCommuUserList.jsp
  * @Description : EgovCommuUserList 화면
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
<c:set var="pageTitle"><spring:message code="comCopCmy.commuUserVO.title"/></c:set>
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
	document.CommuUserForm.searchCnd.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.CommuUserForm.pageIndex.value = pageNo;
	document.CommuUserForm.action = "<c:url value='/cop/cmy/selectCommuMasterList.do'/>";
   	document.CommuUserForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_user(){
	document.CommuUserForm.pageIndex.value = 1;
	document.CommuUserForm.submit();
}
</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="CommuUserForm" action="<c:url value='/cop/cmy/selectCommuUserList.do'/>" method="post" onSubmit="fn_egov_search_user(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopCmy.commuUserVO.emplyrNm" /></option><!-- 사용자명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopCmy.commuUserVO.emplyrId" /></th><!-- 아이디 -->
		<th><spring:message code="comCopCmy.commuUserVO.emplyrNm" /></th><!-- 사용자명 -->
		<th><spring:message code="comCopCmy.commuUserVO.mberSttus" /></th><!-- 회원상태 -->
		<th><spring:message code="comCopCmy.commuUserVO.etc" /></th><!-- 비고 -->
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
		<td><c:out value='${resultInfo.userId}'/></td>
		<td><c:out value='${resultInfo.emplyrNm}'/></td>
		<td><c:out value='${resultInfo.mberSttusNm}'/></td>
		<c:choose>
			<c:when test="${resultInfo.mberSttus == 'A' }">
			<!-- 회원가입 신청상태일 경우 -->
				<td>
					<a class="btn02" href="<c:url value='/cop/cmy/insertCommuUser.do?cmmntyId=${resultInfo.cmmntyId}&emplyrId=${resultInfo.emplyrId}' />">가입승인</a>&nbsp;	
					<a class="btn02" href="<c:url value='/cop/cmy/deleteCommuUser.do?cmmntyId=${resultInfo.cmmntyId}&emplyrId=${resultInfo.emplyrId}' />">가입거절</a>	
				</td>		
			</c:when>

			<c:when test="${resultInfo.mberSttus == 'P' && resultInfo.mngrAt == 'N'}">
			<!-- 일반회원 상태일 경우 -->
				<td>
					<a class="btn02" href="<c:url value='/cop/cmy/insertCommuUserAdmin.do?cmmntyId=${resultInfo.cmmntyId}&emplyrId=${resultInfo.emplyrId}' />">관리자등록</a>&nbsp;	
					<a class="btn02" href="<c:url value='/cop/cmy/deleteCommuUser.do?cmmntyId=${resultInfo.cmmntyId}&emplyrId=${resultInfo.emplyrId}' />">탈퇴</a>	
				</td>		
			</c:when>
			
			<c:when test="${resultInfo.mngrAt == 'Y' }">
			<!-- 관리자일 경우 -->
				<td>
					<a class="btn02" href="<c:url value='/cop/cmy/deleteCommuUserAdmin.do?cmmntyId=${resultInfo.cmmntyId}&emplyrId=${resultInfo.emplyrId}' />">관리자해제</a>
				</td>		
			</c:when>
			
			<c:otherwise>
			<!--  나머지 경우... -->
				<td>
				</td>
			</c:otherwise>
		</c:choose>
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

<input name="cmmntyId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>