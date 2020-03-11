<%--
/**
 * @Class Name  : EgovNtwrkDetail.java
 * @Description : EgovNtwrkDetail jsp
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
<title><spring:message code="comSymSymNwk.ntwrkDetail.title"/></title><!-- 네트워크 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectNtwrkList() {
    var varFrom = document.getElementById("ntwrk");
    varFrom.action = "<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>";
    varFrom.submit();       
}

function fncNtwrkUpdateView(ntwrkId) {
    var varFrom = document.getElementById("ntwrk");
    varFrom.ntwrkId.value = ntwrkId;
    varFrom.action = "<c:url value='/sym/sym/nwk/updtViewNtwrk.do'/>";
    varFrom.submit();
}

function fncNtwrkDelete(ntwrkId) {
    var varFrom = document.getElementById("ntwrk");
    varFrom.ntwrkId.value = ntwrkId;
    varFrom.action = "<c:url value='/sym/sym/nwk/removeNtwrk.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="ntwrk" method="post" action="${pageContext.request.contextPath}/sym/sym/nwk/updtViewNtwrk.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymSymNwk.ntwrkDetail.pageTop.title"/></h2><!-- 네트워크 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.ntwrkId"/> <span class="pilsu">*</span></th><!-- 네트워크ID -->
			<td class="left">
			    <c:out value='${ntwrk.ntwrkId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.ntwrkIp"/> <span class="pilsu">*</span></th><!-- IP -->
			<td class="left">
			    <c:out value='${ntwrk.ntwrkIp}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.gtwy"/> <span class="pilsu">*</span></th><!-- 게이트웨이 -->
			<td class="left">
			    <c:out value='${ntwrk.gtwy}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.subnet"/> <span class="pilsu">*</span></th><!-- 서브넷 -->
			<td class="left">
			    <c:out value='${ntwrk.subnet}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.domnServer"/> <span class="pilsu">*</span></th><!-- DNS -->
			<td class="left">
			    <c:out value='${ntwrk.domnServer}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.manageIem"/> <span class="pilsu">*</span></th><!-- 관리항목 -->
			<td class="left">
			    <c:out value='${ntwrk.manageIem}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.userNm"/> <span class="pilsu">*</span></th><!-- 사용자명 -->
			<td class="left">
			    <c:out value='${ntwrk.userNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
			    <c:out value='${ntwrk.useAt}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkDetail.regstYmd"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
			<td class="left">
			    <c:out value="${ntwrk.regstYmd}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sym/sym/nwk/updtViewNtwrk.do'/>?ntwrkId=<c:out value='${ntwrk.ntwrkId}'/>" onclick="fncNtwrkUpdateView('${ntwrk.ntwrkId}'); return false;"><spring:message code="button.update" /></a></span><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/nwk/removeNtwrk.do'/>?ntwrkId=<c:out value='${ntwrk.ntwrkId}'/>" onclick="fncNtwrkDelete('${ntwrk.ntwrkId}'); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>?pageIndex=<c:out value='${ntwrkVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${ntwrkVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectNtwrkList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" name="ntwrkId" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strManageIem" value="<c:out value='${ntwrkVO.strManageIem}'/>" />
    <input type="hidden" name="strUserNm" value="<c:out value='${ntwrkVO.strUserNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${ntwrkVO.pageIndex}'/>" />
</form:form>

</body>
</html>

