<%--
/**
 * @Class Name : EgovServerList.java
 * @Description : EgovServerList jsp
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
<title><spring:message code="comSymSymSrv.serverList.title"/></title><!-- 서버 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncSelectServerList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerList.do'/>";
    document.listForm.submit();
}

function fncSelectServer(serverId) {
    document.listForm.serverId.value = serverId;
    document.listForm.action = "<c:url value='/sym/sym/srv/getServer.do'/>";
    document.listForm.submit();   
}

function fncAddServerInsert() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	} 
    document.listForm.action = "<c:url value='/sym/sym/srv/addViewServer.do'/>";
    document.listForm.submit(); 
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
    	fncSelectServerList('1');
    }
}

function windowOpen(serverId) {
	var url = "<c:url value='/sym/sym/srv/selectServerEqpmnRelateList.do'/>?strServerId="+serverId;
	window.open(url,'','toolbar=no,menubar=no,location=no,directions=no, scrollbars=yes,status=yes,fullscreen=no,width=770,height=520'); 
}

-->
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymSymSrv.serverList.pageTop.title"/></h1><!-- 서버S/W 관리 -->

	<form name="listForm" action="<c:url value='/sym/sym/srv/selectServerList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymSymSrv.serverList.serverNm"/> : </label><!-- 서버S/W 명 -->
				<input id="strServerNm" class="s_input2 vat" name="strServerNm" type="text" value='<c:out value="${serverVO.strServerNm}"/>' size="15" onkeypress="press();" title="검색" /><!-- 검색 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectServerList('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/sym/srv/addViewServer.do'/>?pageIndex=<c:out value='${serverVO.pageIndex}'/>&amp;strServerNm=<c:out value="${serverVO.strServerNm}"/>" onclick="fncAddServerInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	<input type="hidden" name="serverId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty serverVO.pageIndex }">1</c:if><c:if test="${!empty serverVO.pageIndex }"><c:out value='${serverVO.pageIndex}'/></c:if>">
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:30%" />
			<col style="width:20%" />
			<col style="width:14%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymSymSrv.serverList.serverId"/></th><!-- 서버S/W ID -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverList.serverNm"/></th><!-- 서버S/W 명 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverList.serverKndNm"/></th><!-- 서버S/W 종류 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverList.regstYmd"/></th><!-- 등록일자 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverList.regServerHW"/></th><!-- 서버H/W 등록 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="server" items="${serverList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/sym/sym/srv/getServer.do'/>">
			            <input type="hidden" name="serverId" value="<c:out value="${server.serverId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${serverVO.pageIndex}'/>">
			            <input type="hidden" name="strServerNm" value="<c:out value="${serverVO.strServerNm}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${server.serverId}"/>" onclick="fncSelectServer('<c:out value="${server.serverId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${server.serverNm}"/></td>
			    <td><c:out value="${server.serverKndNm}"/></td>
			    <td><c:out value="${server.regstYmd}"/></td>
			    <td><a href="#" onclick="windowOpen('<c:out value="${server.serverId}"/>')"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif'/>" alt="" /></a></td>
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
