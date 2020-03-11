<%--
/**
 * @Class Name  : EgovServerDetail.java
 * @Description : EgovServerDetail jsp
 * @Modification Information
 * @
 * @ 수정일                수정자             수정내용
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
<title><spring:message code="comSymSymSrv.serverDetail.title"/></title><!-- 서버정보 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectServerList() {
    var varFrom = document.getElementById("server");
    varFrom.action = "<c:url value='/sym/sym/srv/selectServerList.do'/>";
    varFrom.submit();       
}

function fncServerUpdateView(serverId) {
    var varFrom = document.getElementById("server");
    varFrom.serverId.value = serverId;
    varFrom.action = "<c:url value='/sym/sym/srv/updtViewServer.do'/>";
    varFrom.submit();
}

function fncServerDelete(serverId) {
    var varFrom = document.getElementById("server");

    if(varFrom.serverEqpmnCount.value > 0) {
        alert("서버장비가 등록되어 삭제할 수 없습니다."); //서버장비가 등록되어 삭제할 수 없습니다.
        return;
    }

    varFrom.serverId.value = serverId;
    varFrom.action = "<c:url value='/sym/sym/srv/removeServer.do'/>";
    if(confirm("삭제 하시겠습니까?")){ //삭제 하시겠습니까?
        varFrom.submit();
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="server" method="post" action="${pageContext.request.contextPath}/sym/sym/srv/updtViewServer.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymSymSrv.serverDetail.pageTop.title1"/></h2><!-- 서버S/W 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymSymSrv.serverDetail.serverId"/> <span class="pilsu">*</span></th><!-- 서버S/W ID -->
			<td class="left">
			    <c:out value='${server.serverId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverDetail.serverNm"/> <span class="pilsu">*</span></th><!-- 서버S/W 명 -->
			<td class="left">
			    <c:out value='${server.serverNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverDetail.serverKndNm"/> <span class="pilsu">*</span></th><!-- 서버S/W 종류 -->
			<td class="left">
			    <c:out value='${server.serverKndNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverDetail.regstYmd"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
			<td class="left">
			    <c:out value="${server.regstYmd}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/updtViewServer.do'/>?serverId=<c:out value='${server.serverId}'/>" onclick="fncServerUpdateView('${server.serverId}'); return false;"><spring:message code="button.update" /></a></span><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/removeServer.do'/>?serverId=<c:out value='${server.serverId}'/>" onclick="fncServerDelete('${server.serverId}'); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/selectServerList.do'/>?pageIndex=<c:out value='${serverVO.pageIndex}'/>&amp;strServerNm=<c:out value="${serverVO.strServerNm}"/>" onclick="fncSelectServerList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<div class="board">
	<h1><spring:message code="comSymSymSrv.serverDetail.pageTop.title2"/></h1><!-- 서버H/W 목록 -->

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:35%" />
			<col style="width:35%" />			
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.serverEqpmnNm"/></th><!-- 서버H/W 명 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.serverEqpmnIp"/></th><!-- 서버H/W IP -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.cpuInfo"/></th><!-- CPU정보 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.moryInfo"/></th><!-- 메모리정보 -->
			</tr>
			<tr>
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.serverEqpmnMngrNm"/></th><!-- 관리자 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.opersysmInfo"/></th><!-- OS정보 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.hdDisk"/></th><!-- 하드디스크 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverDetail.etcInfo"/></th><!-- 기타정보 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="serverEqpmnRelateDetail" items="${serverEqpmnRelateDetailList}" varStatus="status">
		      <tr>
		        <td><c:out value="${serverEqpmnRelateDetail.serverEqpmnNm}"/></td>
		        <td><c:out value="${serverEqpmnRelateDetail.serverEqpmnIp}"/></td>
		        <td><c:out value="${serverEqpmnRelateDetail.cpuInfo}"/></td>
		        <td><c:out value="${serverEqpmnRelateDetail.moryInfo}"/></td>
		      </tr>
		      <tr>
		        <td><c:out value="${serverEqpmnRelateDetail.serverEqpmnMngrNm}"/></td>
		        <td><c:out value="${serverEqpmnRelateDetail.opersysmInfo}"/></td>
		        <td><c:out value="${serverEqpmnRelateDetail.hdDisk}"/></td>
		        <td><c:out value="${serverEqpmnRelateDetail.etcInfo}"/></td>
		      </tr>
		     </c:forEach>
		</tbody>
	</table>

</div>

<input type="hidden" name="serverId" />
<!-- 검색조건 유지 -->
<input type="hidden" name="strServerNm" value="<c:out value='${serverVO.strServerNm}'/>" />
<input type="hidden" name="pageIndex" value="<c:out value='${serverVO.pageIndex}'/>" />
<input type="hidden" name="serverEqpmnCount" value="<c:out value="${serverEqpmnRelateDetailCount}"/>" />
</form:form>

</body>
</html>

