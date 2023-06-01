<%
 /**
  * @Class Name : EgovDeptSchdulManageListPopup.jsp
  * @Description : 일정관리 팝업 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.09   장동한          최초 생성
  *   2016.08.08   장동한          표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comCopSmtSdm.popup.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
 
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_DeptSchdulManage(){
	location.href = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_DeptSchdulManage(){
	location.href = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_DeptSchdulManage(schdulId){
	var vFrom = document.listForm;
	vFrom.schdulId.value = schdulId;
	vFrom.action = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageDetail.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_DeptSchdulManage(){
	var vFrom = document.listForm;

	if(vFrom.searchKeyword.value == ""){
		alert('<spring:message code="comCopSmtSdm.popupListValidate.searchKeyword" />'); <%-- 검색어를 입력해주세요! --%>
		vFrom.searchKeyword.focus();
		return;
	}

	if(vFrom.searchCondition.selectedIndex == 0){
		alert('<spring:message code="comCopSmtSdm.popupListValidate.searchCondition" />'); <%-- 검색 구분을 선택해주세요! --%>
		vFrom.searchCondition.focus();
		return;
	}

	vFrom.action = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageListPopup.do' />";
	vFrom.submit();

}

/* ********************************************************
* 선택 처리 함수
******************************************************** */
function fn_egov_open_Popup(cnt, schdulId){

	window.opener.document.getElementById("schdulId").value = schdulId;
	window.opener.document.getElementById("schdulCn").value = document.getElementById("iptText_"+ cnt).value;

	window.close();
}
</script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="StplatListForm" action="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageListPopup.do'/>" method="post"> 
<div class="board">
	<h1>${pageTitle}</h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value='SCHDUL_NM' <c:if test="${searchVO.searchCondition == 'SCHDUL_NM'}">selected</c:if>><spring:message code="comCopSmtSdm.popupSearchCondition.diaryNm" /></option><!-- 일지명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 15%;">
		<col style="width: ;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 --> 

		<th><spring:message code="comCopSmtSdm.popupList.diaryType" /></th><!-- 일정구분 -->
		<th><spring:message code="comCopSmtSdm.popupList.diaryNm" /></th><!-- 일지명 -->
		<th><spring:message code="table.select" /></th><!-- 선택 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td>
		 <c:if test="${resultInfo.schdulKindCode == '1'}"><spring:message code="comCopSmtSdm.popupList.diaryPersonal" /></c:if><!-- 개인일정 -->
		 <c:if test="${resultInfo.schdulKindCode == '2'}"><spring:message code="comCopSmtSdm.popupList.diaryDept" /></c:if>    <!-- 부서일정 -->
		</td>
		<td>${resultInfo.schdulNm}</td>
		<td><button class="btn_s2" onClick="fn_egov_open_Popup('${status.count}', '${resultInfo.schdulId}');return false;" title="<spring:message code="button.select" /> <spring:message code="input.button" />"><spring:message code="button.select" /></button></td>
		<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.schdulNm}">
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
</div><!-- end div board -->

<input name="schdulId" id="schdulId" type="hidden" value="">
<input name="pageIndex" id="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>


</body>
</html>