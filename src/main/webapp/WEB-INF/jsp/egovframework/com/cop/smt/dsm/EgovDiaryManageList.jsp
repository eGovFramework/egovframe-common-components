<%
 /**
  * @Class Name : EgovDiaryManageList.jsp
  * @Description : 일지관리 목록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.09   장동한          최초 생성
  *   2016.08.05   장동한          표준프레임워크 v3.6 개선
  *  
  *  @author 공통서비스팀
  *  @since 2009.03.09
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
<c:set var="pageTitle"><spring:message code="comCopSmtDsm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cop/smt/dsm/EgovDiaryManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_DiaryManage(){
	location.href = "<c:url value='/cop/smt/dsm/EgovDiaryManageRegist.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_DiaryManage(diaryId){
	var vFrom = document.subForm;
	vFrom.diaryId.value = diaryId;
	vFrom.action = "<c:url value='/cop/smt/dsm/EgovDiaryManageDetail.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_DiaryManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/cop/smt/dsm/EgovDiaryManageList.do' />";
	vFrom.submit();

}


</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<form name="StplatListForm" action="<c:url value='/cop/smt/dsm/EgovDiaryManageList.do'/>" method="post"> 
		<!-- 검색영역 -->
		<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
			<ul>
				<li>
					<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
						<option selected value=''>--<spring:message code="input.select" />--</option>
						<option value='DIARY_NM' <c:if test="${searchCondition == 'DIARY_NM'}">selected</c:if>><spring:message code="comCopSmtDsm.searchCondition.diaryNm" /></option><!-- 일지명 -->
			   			<option value='DRCT_MATTER' <c:if test="${searchCondition == 'DRCT_MATTER'}">selected</c:if>><spring:message code="comCopSmtDsm.searchCondition.drctMatter" /></option><!-- 지시사항  -->
			   			<option value='PARTCLR_MATTER' <c:if test="${searchCondition == 'PARTCLR_MATTER'}">selected</c:if>><spring:message code="comCopSmtDsm.searchCondition.partclrMatter" /></option><!-- 특이사항 -->
			   			<option value='SCHDUL_ID' <c:if test="${searchVO.searchCondition == 'SCHDUL_ID'}">selected</c:if>><spring:message code="comCopSmtDsm.searchCondition.schdulId" /></option><!-- 일정ID -->
					</select>
				</li>
				<!-- 검색키워드 및 조회버튼 -->
				<li>
					<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
					<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
					<span class="btn_b"><a href="<c:url value='/cop/smt/dsm/EgovDiaryManageRegist.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 목록 -->
				</li>
			</ul>
		</div>
	<input name="diaryId" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		
		<col style="width: ;">
		<col style="width: 20%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 --> 

		<th class="board_th_link"><spring:message code="comCopSmtDsm.list.diaryNm" /></th><!-- 일지명 -->
		<th><spring:message code="comCopSmtDsm.list.diaryProcsPte" /></th><!-- 진척률 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
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
		<td class="left">

		<form name="subForm" method="post" action="<c:url value='/cop/smt/dsm/EgovDiaryManageDetail.do'/>">
		    <input name="diaryId" type="hidden" value="<c:out value="${resultInfo.diaryId}"/>">
		    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		    <span class="link"><input type="submit" value="<c:out value="${resultInfo.diaryNm}"/>" style="border:0px solid #e0e0e0;"></span>
		</form>
		
		</td>
		<td><c:out value='${resultInfo.diaryProcsPte}'/>%</td>
		<td><c:out value='${resultInfo.frstRegisterNm}'/></td>	
		<td><c:out value='${fn:substring(resultInfo.frstRegisterPnttm,0,10)}'/></td>	
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
</div><!-- end div board -->




</body>
</html>