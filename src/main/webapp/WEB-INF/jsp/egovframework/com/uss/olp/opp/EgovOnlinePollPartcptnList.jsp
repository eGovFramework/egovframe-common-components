<%
 /**
  * @Class Name : EgovOnlinePollPartcptnList.jsp
  * @Description : 온라인POLL참여 목록	
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09	장동한		최초 생성
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
<c:set var="pageTitle"><spring:message code="comUssOlpOpp.title"/></c:set>
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
	document.listForm.action = "<c:url value='/uss/olp/opp/listOnlinePollPartcptn.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_regist_OnlinePollPartcptn(pollId,sDate,eDate){
	var iToDate = <fmt:formatDate value="${now}" pattern="yyyyMMdd" />;
	var iBeginDate = Number(sDate.replaceAll("-",""));
	var iEndDate = Number(eDate.replaceAll("-",""));


	if(iToDate >= iBeginDate && iToDate <= iEndDate){

		var vFrom = document.listForm;
		vFrom.pollId.value = pollId;
		vFrom.action = "<c:url value='/uss/olp/opp/registOnlinePollPartcptn.do' />";
		vFrom.submit();
	}else{
		alert("<spring:message code="comUssOlpOpp.validate.onlinePollPartcptn" />");
		return;
	}

}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_OnlinePollPartcptn(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/opp/listOnlinePollPartcptn.do' />";
	vFrom.submit();

}

/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
</script>
</head>
<body>
<div class="board">

<form name="listForm" action="<c:url value='/uss/olp/opp/listOnlinePollPartcptn.do'/>" method="post"> 
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
			</li>
		</ul>
	</div>
<input name="pollId" type="hidden" value="">
<input name="searchMode" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comUssOlpOpm.list.pollNm" /></th><!-- 온라인POLL명 -->
		<th><spring:message code="comUssOlpOpm.list.pollBeginDe" /></th><!-- 시작일 -->
		<th><spring:message code="comUssOlpOpm.list.pollEndDe" /></th><!-- 종료일 -->
		<th><spring:message code="comUssOlpOpm.list.pollStatistics" /></th><!-- 통계 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="leftElli"> 
		<form name="subForm" method="post" action="<c:url value='/uss/olp/opp/registOnlinePollPartcptn.do'/>">
			<input name="pollId" type="hidden" value="<c:out value="${resultInfo.pollId}"/>">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	    	<span class="link"><input type="submit" value="<c:out value="${resultInfo.pollNm}"/>"></span>
	    </form>
		</td>
		<td><c:out value="${resultInfo.pollBeginDe}"/></td>
		<td><c:out value="${resultInfo.pollEndDe}"/></td>
		<td>
		<form name="subFormStatistics" method="post" action="<c:url value='/uss/olp/opp/statisticsOnlinePollPartcptn.do'/>">
			<input name="pollId" type="hidden" value="<c:out value="${resultInfo.pollId}"/>">
			<input name="linkType" type="hidden" value="2">
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

<%--  자마스크립트 메세지 출력 --%>
${reusltScript}
</body>
</html>
