<%--
/**
 * @Class Name : EgovNtwrkList.java
 * @Description : EgovNtwrkList jsp
 * @Modification Information
 * @
 * @ 수정일               수정자              수정내용
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
<title><spring:message code="comSymSymNwk.ntwrkList.title"/></title><!-- 네트워크 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncSelectNtwrkList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>";
    document.listForm.submit();
}

function fncSelectNtwrk(ntwrkId) {
    document.listForm.ntwrkId.value = ntwrkId;
    document.listForm.action = "<c:url value='/sym/sym/nwk/getNtwrk.do'/>";
    document.listForm.submit();   
}

function fncAddNtwrkInsert() {
    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    } 
    document.listForm.action = "<c:url value='/sym/sym/nwk/addViewNtwrk.do'/>";
    document.listForm.submit(); 
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
        fncSelectNtwrkList('1');
    }
}
-->
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymSymNwk.NtwrkList.pageTop.title"/></h1><!-- 네트워크 관리 -->

<form name="listForm" action="<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymSymNwk.NtwrkList.manageIem"/> : </label><!-- 관리항목 -->
				<select name="strManageIem" id="strManageIem">
				<option value="00"><spring:message code="comSymSymNwk.NtwrkList.strManageIem.all"/></option><!-- 전체 -->
				<c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
				<option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.code == ntwrkVO.strManageIem}">selected</c:if>><c:out value="${cmmCodeDetail.codeNm}"/></option>
				</c:forEach>
				</select>
				
				<label for="" style="margin-left:10px"><spring:message code="comSymSymNwk.NtwrkList.strUserNm"/> : </label><!-- 사용자 명 -->
				<input id="strUserNm" class="s_input2 vat" name="strUserNm" type="text" value='<c:out value="${ntwrkVO.strUserNm}"/>' size="15" onkeypress="press();" title="<spring:message code="comSymSymNwk.NtwrkList.strUserNm"/>" /><!-- 사용자 명 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectNtwrkList('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/sym/nwk/addViewNtwrk.do'/>?pageIndex=<c:out value='${ntwrkVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${ntwrkVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncAddNtwrkInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
<input type="hidden" name="ntwrkId">
<input type="hidden" name="pageIndex" value="<c:if test="${empty ntwrkVO.pageIndex }">1</c:if><c:if test="${!empty ntwrkVO.pageIndex }"><c:out value='${ntwrkVO.pageIndex}'/></c:if>">
<input type="hidden" name="searchCondition" value="1">
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:20%" />
			<col style="width:12%" />
			<col style="width:15%" />
			<col style="width:9%" />
			<col style="width:19%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymSymNwk.NtwrkList.ntwrkId"/></th><!-- 네트워크ID -->
			   <th scope="col"><spring:message code="comSymSymNwk.NtwrkList.ntwrkIp"/></th><!-- IP -->
			   <th scope="col"><spring:message code="comSymSymNwk.NtwrkList.manageIem"/></th><!-- 관리항목 -->
			   <th scope="col"><spring:message code="comSymSymNwk.NtwrkList.userNm"/></th><!-- 사용자 -->
			   <th scope="col"><spring:message code="comSymSymNwk.NtwrkList.useAt"/></th><!-- 사용여부 -->
			   <th scope="col"><spring:message code="comSymSymNwk.NtwrkList.regstYmd"/></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ntwrk" items="${ntwrkList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/sym/sym/nwk/getNtwrk.do'/>">
			            <input type="hidden" name="ntwrkId" value="<c:out value="${ntwrk.ntwrkId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${ntwrkVO.pageIndex}'/>">
			            <input type="hidden" name="strManageIem" value="<c:out value='${ntwrkVO.strManageIem}'/>">
			            <input type="hidden" name="strUserNm" value="<c:out value="${ntwrkVO.strUserNm}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${ntwrk.ntwrkId}"/>" onclick="fncSelectNtwrk('<c:out value="${ntwrk.ntwrkId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${ntwrk.ntwrkIp}"/></td>
			    <td><c:out value="${ntwrk.manageIem}"/></td>
			    <td><c:out value="${ntwrk.userNm}"/></td>
			    <td><c:out value="${ntwrk.useAt}"/></td>
			    <td><c:out value="${ntwrk.regstYmd}"/></td>
			  </tr>
			 </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>

</div>

</body>
</html>
