<%--
/**
 * @Class Name  : EgovServerEqpmnDetail.java
 * @Description : EgovServerEqpmnDetail jsp
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
<title><spring:message code="comSymSymSrv.serverEqpmnDetail.title"/></title><!-- 서버장비 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectServerEqpmnList() {
    var varFrom = document.getElementById("serverEqpmn");
    varFrom.action = "<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>";
    varFrom.submit();       
}

function fncServerEqpmnUpdateView(serverEqpmnId) {
    var varFrom = document.getElementById("serverEqpmn");
    varFrom.serverEqpmnId.value = serverEqpmnId;
    varFrom.action = "<c:url value='/sym/sym/srv/updtViewServerEqpmn.do'/>";
    varFrom.submit();
}

function fncServerEqpmnDelete(serverEqpmnId) {
    var varFrom = document.getElementById("serverEqpmn");
    varFrom.serverEqpmnId.value = serverEqpmnId;
    varFrom.action = "<c:url value='/sym/sym/srv/removeServerEqpmn.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="serverEqpmn" method="post" action="${pageContext.request.contextPath}/sym/sym/srv/updtViewServerEqpmn.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymSymSrv.serverEqpmnDetail.pageTop.title"/></h2><!-- 서버H/W 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.serverEqpmnId"/> <span class="pilsu">*</span></th><!-- 서버H/W ID -->
			<td class="left">
			    <c:out value='${serverEqpmn.serverEqpmnId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.serverEqpmnNm"/> <span class="pilsu">*</span></th><!-- 서버H/W 명 -->
			<td class="left">
			    <c:out value='${serverEqpmn.serverEqpmnNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.serverEqpmnIp"/> <span class="pilsu">*</span></th><!-- 서버H/W IP -->
			<td class="left">
			    <c:out value='${serverEqpmn.serverEqpmnIp}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.serverEqpmnMngrNm"/> <span class="pilsu">*</span></th><!-- 관리자 -->
			<td class="left">
			    <c:out value='${serverEqpmn.serverEqpmnMngrNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.mngrEmailAddr"/> <span class="pilsu">*</span></th><!-- 관리자이메일주소 -->
			<td class="left">
			    <c:out value='${serverEqpmn.mngrEmailAddr}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.opersysmInfo"/> <span class="pilsu">*</span></th><!-- OS정보 -->
			<td class="left">
			    <c:out value='${serverEqpmn.opersysmInfo}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.cpuInfo"/> <span class="pilsu">*</span></th><!-- CPU정보 -->
			<td class="left">
			    <c:out value='${serverEqpmn.cpuInfo}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.moryInfo"/> <span class="pilsu">*</span></th><!-- 메모리정보 -->
			<td class="left">
			    <c:out value='${serverEqpmn.moryInfo}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.hdDisk"/> <span class="pilsu">*</span></th><!-- 하드디스크 -->
			<td class="left">
			    <c:out value='${serverEqpmn.hdDisk}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.etcInfo"/></th><!-- 기타정보 -->
			<td class="left">
			    <c:out value='${serverEqpmn.etcInfo}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnDetail.regstYmd"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
			<td class="left">
			    <c:out value="${serverEqpmn.regstYmd}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/updtViewServerEqpmn.do'/>?serverEqpmnId=<c:out value='${serverEqpmn.serverEqpmnId}'/>" onclick="fncServerEqpmnUpdateView('${serverEqpmn.serverEqpmnId}'); return false;"><spring:message code="button.update" /></a></span><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/removeServerEqpmn.do'/>?serverEqpmnId=<c:out value='${serverEqpmn.serverEqpmnId}'/>" onclick="fncServerEqpmnDelete('${serverEqpmn.serverEqpmnId}'); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>?pageIndex=<c:out value='${serverEqpmnVO.pageIndex}'/>&amp;strServerEqpmnNm=<c:out value="${serverEqpmnVO.strServerEqpmnNm}"/>" onclick="fncSelectServerEqpmnList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" name="serverEqpmnId" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strServerEqpmnNm" value="<c:out value='${serverEqpmnVO.strServerEqpmnNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${serverEqpmnVO.pageIndex}'/>" />
</form:form>

</body>
</html>

