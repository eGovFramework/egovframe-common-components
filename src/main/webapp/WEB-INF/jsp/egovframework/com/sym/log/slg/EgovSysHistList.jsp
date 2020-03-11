<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovSysHistList.jsp
  * @Description : 시스템 이력 정보목록 화면
  * @Modification Information
  * @
  * @ 수정일              수정자            수정내용
  * @ ----------  --------   ---------------------------
  * @ 2009.03.06  이삼섭             최초 생성
  *   2011.07.08  이기하             패키지 분리로 경로 수정(sym.log -> sym.log.slg)
  *   2018.09.28  신용호             공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.06
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymLogSlg.sysHistList.title"/></title><!-- 시스템 이력 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
function fn_egov_insert_sysHist(){
	document.frm.action = "<c:url value='/sym/log/slg/AddSysHistory.do'/>";
	document.frm.submit();
}

function fn_egov_select_sysHist(pageNo){
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/sym/log/slg/SelectSysHistoryList.do'/>";
	document.frm.submit();
}

function fn_egov_inqire_sysHist(histId){
	document.frm.histId.value = histId;
	document.frm.action = "<c:url value='/sym/log/slg/InqireSysHistory.do'/>";
	document.frm.submit();
}
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymLogSlg.sysHistList.pageTop.title"/></h1><!-- 시스템이력 조회 -->

	<form name="frm" action ="<c:url value='/sym/log/slg/SelectSysHistoryList.do'/>" method="post">
	<input name="histId" type="hidden" />

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition" />">
				<option selected value=''>--<spring:message code="input.cSelect"/>--</option><!-- 선택하세요 -->
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comSymLogSlg.sysHistList.sysNm"/></option><!-- 시스템명 -->
				<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comSymLogSlg.sysHistList.histSeCodeNm"/></option><!-- 이력구분 -->
				</select>
				<input id="searchWrd" class="s_input2 vat" name="searchWrd" type="text" value='<c:out value='${searchVO.searchWrd}'/>' maxlength="35" size="35" onkeypress="press();" title="<spring:message code="title.search"/>" /><!-- 사용자명검색 -->
				
				<span class="btn_b"><a href="" onclick="fn_egov_select_sysHist('1'); return false;" title="<spring:message code="title.inquire"/>"><spring:message code="button.inquire" /></a></span><!-- 조회 -->
				<input class="s_btn" type="submit" value="<spring:message code="button.create" />" title="<spring:message code="title.create" />" onclick="fn_egov_insert_sysHist(); return false;" /><!-- 등록 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:7%" />
			<col style="width:25%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:8%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.index"/></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.histId"/></th><!-- 이력 ID -->
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.sysNm"/></th><!-- 시스템명 -->
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.histSeCodeNm"/></th><!-- 이력구분 -->
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.frstRegisterNm"/></th><!-- 등록자 -->
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.frstRegisterPnttm"/></th><!-- 등록일자 -->
			   <th scope="col"><spring:message code="comSymLogSlg.sysHistList.fnEgovInqireSysHist"/></th><!-- 상세보기 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="7">
					<spring:message code="common.nodata.msg" />
					</td>
				</tr>
			</c:if>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					<td><c:out value="${result.histId}"/></td>
					<td><c:out value="${result.sysNm}"/></td>
					<td><c:out value="${result.histSeCodeNm}"/></td>
					<td><c:out value="${result.frstRegisterNm}"/></td>
					<td><c:out value="${result.frstRegisterPnttm}"/></td>
					<td>
					<a href="" onclick="fn_egov_inqire_sysHist('<c:out value="${result.histId}"/>'); return false;">
					<img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif'/>"  alt="<spring:message code="comSymLogSlg.sysHistList.fnEgovInqireSysHist"/>" /></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_sysHist"/>
		</ul>
	</div>
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>
	
</div>

</body>
</html>
