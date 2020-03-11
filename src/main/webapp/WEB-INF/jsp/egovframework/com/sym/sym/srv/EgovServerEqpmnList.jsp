<%--
/**
 * @Class Name : EgovServerEqpmnList.java
 * @Description : EgovServerEqpmnList jsp
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
<title>서버장비 목록조회</title><!-- 서버장비 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncSelectServerEqpmnList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>";
    document.listForm.submit();
}

function fncSelectServerEqpmn(serverEqpmnId) {
    document.listForm.serverEqpmnId.value = serverEqpmnId;
    document.listForm.action = "<c:url value='/sym/sym/srv/getServerEqpmn.do'/>";
    document.listForm.submit();   
}

function fncAddServerEqpmnInsert() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	} 
    document.listForm.action = "<c:url value='/sym/sym/srv/addViewServerEqpmn.do'/>";
    document.listForm.submit(); 
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
    	fncSelectServerEqpmnList('1');
    }
}
-->
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymSymSrv.serverEqpmnList.pageTop.title"/></h1><!-- 서버H/W 관리 -->

	<form name="listForm" action="<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>" method="post">
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymSymSrv.serverEqpmnList.serverEqpmnNm"/> : </label><!-- 서버H/W 명 -->
				<input id="strServerEqpmnNm" class="s_input2 vat" name="strServerEqpmnNm" type="text" value='<c:out value="${serverEqpmnVO.strServerEqpmnNm}"/>' size="15" onkeypress="press();" title="<spring:message code="title.search"/>" /><!-- 검색 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectServerEqpmnList('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/sym/srv/addViewServerEqpmn.do'/>?pageIndex=<c:out value='${serverEqpmnVO.pageIndex}'/>&amp;strServerEqpmnNm=<c:out value="${serverEqpmnVO.strServerEqpmnNm}"/>" onclick="fncAddServerEqpmnInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>

	<input type="hidden" name="serverEqpmnId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty serverEqpmnVO.pageIndex }">1</c:if><c:if test="${!empty serverEqpmnVO.pageIndex }"><c:out value='${serverEqpmnVO.pageIndex}'/></c:if>">
	</form>
	
	<table class="board_list">
		<caption><spring:message code="comSymSymSrv.serverEqpmnList.caption"/></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnList.serverEqpmnId"/></th><!-- 서버H/W ID -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnList.serverEqpmnNm"/></th><!-- 서버H/W 명 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnList.serverEqpmnIp"/></th><!-- 서버H/W IP -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnList.serverEqpmnMngrNm"/></th><!-- 관리자 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnList.regstYmd"/></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="serverEqpmn" items="${serverEqpmnList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/sym/sym/srv/getServerEqpmn.do'/>">
			            <input type="hidden" name="serverEqpmnId" value="<c:out value="${serverEqpmn.serverEqpmnId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${serverEqpmnVO.pageIndex}'/>">
			            <input type="hidden" name="strServerEqpmnNm" value="<c:out value="${serverEqpmnVO.strServerEqpmnNm}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${serverEqpmn.serverEqpmnId}"/>" onclick="fncSelectServerEqpmn('<c:out value="${serverEqpmn.serverEqpmnId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${serverEqpmn.serverEqpmnNm}"/></td>
			    <td><c:out value="${serverEqpmn.serverEqpmnIp}"/></td>
			    <td><c:out value="${serverEqpmn.serverEqpmnMngrNm}"/></td>
			    <td><c:out value="${serverEqpmn.regstYmd}"/></td>
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
