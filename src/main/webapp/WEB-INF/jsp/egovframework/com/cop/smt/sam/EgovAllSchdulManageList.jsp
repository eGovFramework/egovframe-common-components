<%
 /**
  * @Class Name : EgovStplatListInqire.jsp
  * @Description : EgovStplatListInqire 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09    장동한          최초 생성
  * @ 2016.08.02    장동한          표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comCopSmtSam.title"/></c:set>
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
	document.listForm.action = "<c:url value='/cop/smt/sam/EgovAllSchdulManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_AllSchdulManage(){
	location.href = "<c:url value='/cop/smt/sam/EgovAllSchdulManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_AllSchdulManage(){
	location.href = "<c:url value='/cop/smt/sam/EgovAllSchdulManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_AllSchdulManage(schdulKindCode, schdulId){
	var vFrom = document.subForm;
	vFrom.schdulId.value = schdulId;

	if(schdulKindCode == "1"){
		vFrom.action = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageDetail.do' />";
	}else{
		vFrom.action = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />";
	}
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_AllSchdulManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/cop/smt/sam/EgovAllSchdulManageList.do' />";
	vFrom.submit();

}

/* ********************************************************
* 선택 처리 함수
******************************************************** */
function fn_egov_open_Popup(cnt, schdulId){

	/* var opener = window.dialogArguments */
	var opener;
 
	if (window.dialogArguments) {
	    opener = window.dialogArguments; // Support IE
	} else {
	    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
	}

	opener.document.getElementById("schdulId").value = schdulId;
	opener.document.getElementById("schdulCn").value = document.getElementById("iptText_"+ cnt).value;

	window.returnValue=true;
	window.close();
}
</script>
</head>
<body>

<form name="listForm" action="<c:url value='/cop/smt/sam/EgovAllSchdulManageList.do'/>" method="post" onSubmit="fn_egov_search_stplatcn(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value="SCHDUL_NM"  <c:if test="${searchVO.searchCondition == 'SCHDUL_NM'}">selected="selected"</c:if> ><spring:message code="comCopSmtSam.searchCondition.schdulNm" /></option><!-- 일졍명 -->
					<option value="SCHDUL_CN"  <c:if test="${searchVO.searchCondition == 'SCHDUL_CN'}">selected="selected"</c:if> ><spring:message code="comCopSmtSam.searchCondition.schdulCn" /></option><!-- 일정내용 -->
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
		<col style="width: 20%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comCopSmtSam.list.schdulKind" /></th><!--일정구분 -->
		<th class="board_th_link"><spring:message code="comCopSmtSam.list.schdulNm" /></th><!--일정명 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
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
			<c:if test="${resultInfo.schdulKindCode == '1'}">부서일정</c:if>
		 	<c:if test="${resultInfo.schdulKindCode == '2'}">개인일정</c:if>
		</td>
		<td class="left">
			<a href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageDetail.do'/>?schdulId=${resultInfo.schdulId}&pageIndex=${searchVO.pageIndex}" onclick="JavaScript:fn_egov_detail_AllSchdulManage('${resultInfo.schdulKindCode}','${resultInfo.schdulId}'); return false;"><c:out value='${fn:substring(resultInfo.schdulNm, 0, 40)}'/>
		</a></td>
		<td>${resultInfo.frstRegisterNm}</td>
		<td>${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
	
</div><!-- end div board -->

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>
 
 </body>
 </html>


