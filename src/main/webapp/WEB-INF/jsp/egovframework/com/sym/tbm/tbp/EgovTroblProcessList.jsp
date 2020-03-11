<%--
/**
 * @Class Name  : EgovTroblProcessList.java
 * @Description : EgovTroblProcessList jsp
 * @Modification Information
 * @
 * @  수정일              수정자             수정내용
 * @ ----------   --------    ---------------------------
 * @ 2010.07.01   lee.m.j     최초 생성
 *   2018.09.07   신용호             공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymTbmTbp.troblProcessList.title"/></title><!-- 장애처리 관리 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncSelectTroblProcessList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/tbm/tbp/selectTroblProcessList.do'/>";
    document.listForm.submit();
}

function fncSelectTroblProcess(troblId) {
    document.listForm.troblId.value = troblId;
    document.listForm.action = "<c:url value='/sym/tbm/tbp/getTroblProcess.do'/>";
    document.listForm.submit();   
}

function fncAddTroblProcessInsert() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	} 
    document.listForm.action = "<c:url value='/sym/tbm/tbp/addViewTroblProcess.do'/>";
    document.listForm.submit(); 
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/tbm/tbp/selectTroblProcessList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
    	fncSelectTroblProcessList('1');
    }
}
-->
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymTbmTbp.troblProcessList.pageTop.title"/></h1><!-- 장애처리 관리 -->

	<form name="listForm" action="<c:url value='/sym/tbm/tbp/selectTroblProcessList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymTbmTbp.troblProcessList.troblNm"/> : </label><!-- 장애명 -->
				<input class="s_input2 vat" name="strTroblNm" id="strTroblNm" type="text" value="<c:out value="${troblProcessVO.strTroblNm}"/>" size="20" title="<spring:message code="title.search"/>" onkeypress="press();" /><!-- 검색 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comSymTbmTbp.troblProcessList.troblKndNm"/> : </label><!-- 장애종류 -->
				<select name="strTroblKnd" id="strTroblKnd">
					<option value="00"><spring:message code="comSymTbmTbp.troblProcessList.strTroblKnd.all"/></option><!-- 전체 -->
					<c:forEach var="cmmCodeDetail1" items="${cmmCodeDetailList1}" varStatus="status">
					<option value="<c:out value="${cmmCodeDetail1.code}"/>" <c:if test="${cmmCodeDetail1.code == troblProcessVO.strTroblKnd}">selected</c:if>><c:out value="${cmmCodeDetail1.codeNm}"/></option>
					</c:forEach>
				</select>
				
				<label for="" style="margin-left:10px"><spring:message code="comSymTbmTbp.troblProcessList.processSttusNm"/> : </label><!-- 처리상태 -->
				<select name="strProcessSttus" id="strProcessSttus">
					<option value="00"><spring:message code="comSymTbmTbp.troblProcessList.strTroblKnd.all"/></option><!-- 전체 -->
					<c:forEach var="cmmCodeDetail2" items="${cmmCodeDetailList2}" varStatus="status">
					<option value="<c:out value="${cmmCodeDetail2.code}"/>" <c:if test="${cmmCodeDetail2.code == troblProcessVO.strProcessSttus}">selected</c:if>><c:out value="${cmmCodeDetail2.codeNm}"/></option>
					</c:forEach>
				</select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="<spring:message code="button.inquire" />" onclick="fncSelectTroblProcessList('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	<input type="hidden" name="troblId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty troblProcessVO.pageIndex }">1</c:if><c:if test="${!empty troblProcessVO.pageIndex }"><c:out value='${troblProcessVO.pageIndex}'/></c:if>">
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:24%" />
			<col style="width:20%" />
			<col style="width:18%" />
			<col style="width:20%" />
			<col style="width:8%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymTbmTbp.troblProcessList.troblId"/></th><!-- 장애ID -->
			   <th scope="col"><spring:message code="comSymTbmTbp.troblProcessList.troblNm"/></th><!-- 장애명 -->
			   <th scope="col"><spring:message code="comSymTbmTbp.troblProcessList.troblKndNm"/></th><!-- 장애종류 -->
			   <th scope="col"><spring:message code="comSymTbmTbp.troblProcessList.troblOccrrncTime"/></th><!-- 장애발생시간 -->
			   <th scope="col"><spring:message code="comSymTbmTbp.troblProcessList.troblRqesterNm"/></th><!-- 등록자 -->
			   <th scope="col"><spring:message code="comSymTbmTbp.troblProcessList.processSttusNm"/></th><!-- 처리상태 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="troblProcess" items="${troblProcessList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/sym/tbm/tbp/getTroblProcess.do'/>">
			            <input type="hidden" name="troblId" value="<c:out value="${troblProcess.troblId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${troblProcessVO.pageIndex}'/>">
			            <input type="hidden" name="strTroblNm" value="<c:out value='${troblProcessVO.strTroblNm}'/>">
			            <span class="link"><input type="submit" value="<c:out value="${troblProcess.troblId}"/>" onclick="fncSelectTroblProcess('<c:out value="${troblProcess.troblId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${troblProcess.troblNm}"/></td>
			    <td><c:out value="${troblProcess.troblKndNm}"/></td>
			    <td><c:out value="${troblProcess.troblOccrrncTime}"/></td>
			    <td><c:out value="${troblProcess.troblRqesterNm}"/></td>
			    <td><c:out value="${troblProcess.processSttusNm}"/></td>
			  </tr>
			 </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<c:if test="${!empty troblProcessVO.pageIndex }">
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
	</c:if>
</div>

</body>
</html>
