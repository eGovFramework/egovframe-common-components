<%
 /**
  * @Class Name : EgovOnlinePollResultList.jsps
  * @Description : 온라인POLL참여 목록	
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09	장동한		최초 생성
  *   2016.08.23 	장동한        표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comUssOlpOpm.result.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	 * 삭제함수
	 ******************************************************** */
	function fn_egov_del_OnlinePollResult(pollResultId){
		var vFrom = document.listForm;
	
		if(confirm("삭제 하시겠습니까?")){
			vFrom.pollResultId.value = pollResultId;
			vFrom.action = "<c:url value='/uss/olp/opr/delOnlinePollResult.do' />";	
			vFrom.submit();
		}
		
	}
</script>
</head>
<div class="board">
	
	<!-- javascript warning tag  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

	<h1>${pageTitle} <spring:message code="title.list" /></h1>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 13%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comUssOlpOpm.list.pollItem" /></th><!-- 온라인POLL항목 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
		<th></th><!-- 삭제 -->
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
		<td class="left"><c:out value="${resultInfo.pollIemNm}"/></td>
		<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
		<td><c:out value="${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}"/></td>
		<td>
		<form name="subForm" method="post" action="<c:url value='/uss/olp/opr/delOnlinePollResult.do'/>">
			<input name="pollId" type="hidden" value="<c:out value="${resultInfo.pollId}"/>">
			<input name="pollResultId" type="hidden" value="<c:out value="${resultInfo.pollResultId}"/>">
	    	<input type="submit" class="btn_submit" value="<spring:message code="button.delete" />" onclick="fn_egov_del_OnlinePollResult('<c:out value="${resultInfo.pollResultId}"/>'); return false;" />
	    </form>
		</td>		
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	
	<!--  목록버튼  -->
	<form name="listForm" action="<c:url value='/uss/olp/opr/delOnlinePollResult.do'/>" method="post">
		<input name="pollId" type="hidden" value="${resultList[0].pollId}">
		<input name="pollResultId" type="hidden" value="">
	</form>


	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/olp/opm/listOnlinePollManage.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div>


</DIV>
</html>
