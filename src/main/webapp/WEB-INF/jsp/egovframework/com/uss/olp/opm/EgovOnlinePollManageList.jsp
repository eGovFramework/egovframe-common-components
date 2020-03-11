<%
 /**
  * @Class Name : EgovOnlinePollManageList.jsp
  * @Description : POLL관리 목록 페이지
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09	장동한		최초 생성
  *   2011.07.06 	옥찬우		Tag 변수값수정( 160 Line : frstRegisterPnttm -> frstRegistPnttm )
  *   2016.06.13 	장동한        표준프레임워크 v3.6 개선
  *  
  *  @author 공통서비스 개발팀 장동한
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
<c:set var="pageTitle"><spring:message code="comUssOlpOpm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="comUssOlpOpm.title" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/olp/opm/listOnlinePollManage.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_OnlinePollManage(){
	location.href = "<c:url value='/uss/olp/opm/registOnlinePollManage.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_OnlinePollManage(pollId){
	var vFrom = document.listForm;
	vFrom.pollId.value = pollId;
	vFrom.action = "<c:url value='/uss/olp/opm/detailOnlinePollManage.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_OnlinePollManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/opm/listOnlinePollManage.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="board">

<form name="listForm" action="<c:url value='/uss/olp/opm/listOnlinePollManage.do'/>" method="post"> 
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value="POLL_NM"  <c:if test="${searchVO.searchCondition == 'POLL_NM'}">selected="selected"</c:if> ><spring:message code="comUssOlpOpm.searchCondition.POLL_NM" /></option><!-- POLL명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="<c:url value='/uss/olp/opm/registOnlinePollManage.do' />?pageIndex=${searchVO.pageIndex}"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input name="pollId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 8%;">
		<col style="width: 8%;">
		<col style="width: 8%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comUssOlpOpm.list.pollNm" /></th><!-- 온라인POLL명 -->
		<th><spring:message code="comUssOlpOpm.list.pollBeginDe" /></th><!-- 시작일 -->
		<th><spring:message code="comUssOlpOpm.list.pollEndDe" /></th><!-- 종료일 -->
		<th><spring:message code="comUssOlpOpm.list.pollStatistics" /></th><!-- 통계 -->
		<th><spring:message code="comUssOlpOpm.list.pollResult" /></th><!-- 결과 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="leftElli">

 		<form name="subForm" method="post" action="<c:url value='/uss/olp/opm/detailOnlinePollManage.do'/>">
			<input name="pollId" type="hidden" value="<c:out value="${resultInfo.pollId}"/>">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	    	<span class="link"><input type="submit" value="<c:out value="${resultInfo.pollNm}"/>" style="border:0px solid #e0e0e0;"></span>
	    </form>
	    
		</td>
		<td><c:out value="${resultInfo.pollBeginDe}"/></td>
		<td><c:out value="${resultInfo.pollEndDe}"/></td>
		<td>
		<form name="subFormStatistics" method="post" action="<c:url value='/uss/olp/opp/statisticsOnlinePollPartcptn.do'/>">
			<input name="pollId" type="hidden" value="<c:out value="${resultInfo.pollId}"/>">
			<input name="linkType" type="hidden" value="1">
	    	<input type="submit" class="btn_submit" value="<spring:message code="comUssOlpOpm.btn.view" />" />
	    </form>
		</td>
		<td>
		<form name="subFormPollResult" method="post" action="<c:url value='/uss/olp/opr/listOnlinePollResult.do'/>">
			<input name="pollId" type="hidden" value="<c:out value="${resultInfo.pollId}"/>">
			<input name="linkType" type="hidden" value="1">
	    	<input type="submit" class="btn_submit" value="<spring:message code="comUssOlpOpm.btn.view" />" />
	    </form>
		</td>
		<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
		<td><c:out value="${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}"/></td>
</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	





</DIV>
</body>
</html>

