<%--
/**
 * @Class Name  : EgovSynchrnServerDetail.java
 * @Description : EgovSynchrnServerDetail jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01     lee.m.j     최초 생성
 * @ 2018.11.02		이정은	        공통컴포넌트 3.8 개선
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
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<title><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.detail" /></title> <!-- 동기화대상 서버 상세보기 -->

<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectSynchrnServerList() {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.action = "<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>";
    varFrom.submit();       
}

function fncSynchrnServerUpdateView(serverId) {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.serverId.value = serverId;
    varFrom.action = "<c:url value='/utl/sys/ssy/updtViewSynchrnServer.do'/>";
    varFrom.submit();
}

function fncSynchrnServerDelete(serverId) {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.serverId.value = serverId;
    varFrom.action = "<c:url value='/utl/sys/ssy/removeSynchrnServer.do'/>";
    if(confirm("<spring:message code='common.delete.msg' />")){
        varFrom.submit();
    }
}

function fncRemoveSynchrnServerFile(fileNm) {
    var varFrom = document.getElementById("fileList");
    varFrom.fileNm.value = fileNm;
    varFrom.action = "<c:url value='/utl/sys/ssy/removeSynchrnServerFile.do'/>";
    if(confirm("<spring:message code='common.delete.msg' />")){
        varFrom.submit();
    }
}

function fncDownSynchrnServerFile(fileNm) {
    var varFrom = document.getElementById("fileList");
    varFrom.fileNm.value = fileNm;
    varFrom.action = "<c:url value='/utl/sys/ssy/getSynchrnServerFile.do'/>";
    if(confirm("<spring:message code='comUtlSysSsy.download.msg' />")){
        varFrom.submit();
    }
}

-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.detail" /></h2><!-- 동기화대상 서버정보 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.serverId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.serverNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.serverIp}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.serverPort}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.ftpId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.ftpId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.ftpPassword.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.ftpPassword}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.synchrnLc.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${synchrnServer.synchrnLc}'/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/utl/sys/ssy/updtViewSynchrnServer.do'/>?serverId=<c:out value='${synchrnServer.serverId}'/>" onclick="fncSynchrnServerUpdateView('${synchrnServer.serverId}'); return false;"><spring:message code="button.update" /></a></span>
		<span class="btn_s"><a href="<c:url value='/utl/sys/ssy/removeSynchrnServer.do'/>?serverId=<c:out value='${synchrnServer.serverId}'/>" onclick="fncSynchrnServerDelete('${synchrnServer.serverId}'); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>?pageIndex=<c:out value='${synchrnServerVO.pageIndex}'/>&amp;strSynchrnServerNm=<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>" onclick="fncSelectSynchrnServerList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<div class="board">
<form:form modelAttribute="fileList" method="post" action="${pageContext.request.contextPath}/utl/sys/ssy/removeSynchrnServerFile.do">
	<h1><spring:message code="comUtlSysSsy.synchrn.title" />  <spring:message code="title.list" /></h1><!-- 동기화 파일 목록 -->

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:100px" />
			<col style="" />
			<col style="width:100px" />
			<col style="width:100px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.seq.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.fileList.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.fileDownload.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.fileDelete.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="file" items="${fileList}" varStatus="status">
	        <tr>
	            <c:if test="${file != 'noList'}">
	                <td><c:out value="${status.count}"/></td>
	                <td><c:out value="${file}"/>&nbsp;</td>
	                <td><span class="button"><a href="<c:url value='/utl/sys/ssy/getSynchrnServerFile.do'/>?pageIndex=<c:out value='${synchrnServerVO.pageIndex}'/>&amp;strSynchrnServerNm=<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>" onclick="fncDownSynchrnServerFile('<c:out value="${file}"/>'); return false;"><spring:message code="comUtlSysSsy.synchrnServer.fileDownload.label" /></a></span>&nbsp;</td><!-- 다운로드 -->
	                <!-- 동기화대상서버 파일 삭제 기능 -->
	                <td><span class="button"><input type="submit" value="<spring:message code="button.delete" />" onclick="fncRemoveSynchrnServerFile('<c:out value="${file}"/>'); return false;"></span></td>
	            </c:if>
	            <c:if test="${file == 'noList'}">
	                <td colspan="4"><spring:message code="comUtlSysSsy.synchrnServer.nofile.label" /></td>
	            </c:if>
	        </tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="hidden" name="serverId" value="<c:out value='${synchrnServer.serverId}'/>" />
    <input type="hidden" name="fileNm" />    
</form:form>
</div>

</body>
</html>

