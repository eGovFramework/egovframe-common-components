<%--
  Class Name : EgovUnityLinkDetail.jsp
  Description : 통합링크관리 상세보기
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
  2008.03.09    장동한          최초 생성
  2018.08.16    이정은          공통컴포넌트 3.8 개선
  2024.10.29    권태성			수정 페이지 신규 경로로 변경

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="uss.ion.ulm.unityLinkDetail.unityLinkDetail" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_UnityLink(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_UnityLink(){
	location.href = "<c:url value='/uss/ion/ulm/listUnityLink.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_UnityLink(){
	var vFrom = document.UnityLinkForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/ion/ulm/updtUnityLinkView.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_UnityLink(){
	var vFrom = document.UnityLinkForm;
	if(confirm("<spring:message code="uss.ion.ulm.unityLinkDetail.validate.deleteMsg"/>")){/* 삭제 하시겠습니까? */
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/ion/ulm/detailUnityLink.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_UnityLink();">

<%-- noscript 태그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="UnityLinkForm" action="<c:url value=''/>" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.ulm.unityLinkDetail.unityLinkDetail" /></h2>
<input name="unityLinkId" type="hidden" value="${unityLink.unityLinkId}">
<input name="cmd" type="hidden" value="<c:out value=''/>"/>
</form>
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkDetail.unityLinkNm" /><span class="pilsu">*</span></th><!-- 통합링크명 -->
			<td class="left">
			    <c:out value="${unityLink.unityLinkNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkDetail.unityLinkGroup" /> <span class="pilsu">*</span></th><!-- 통합링크그룹 -->
			<td class="left">
			    <c:forEach items="${unityLinkSeCodeList}" var="resultInfo1" varStatus="pollKindStatus">
					<c:if test="${resultInfo1.code eq unityLink.unityLinkSeCode}">
					<c:out value="${resultInfo1.codeNm}" escapeXml="false" />
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkDetail.unityLinkUrl" /> <span class="pilsu">*</span></th><!-- 통합링크URL -->
			<td class="left">
			    <c:out value="${unityLink.unityLinkUrl}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkDetail.unityLinkDc" /><span class="pilsu">*</span></th><!-- 통합링크설명  -->
			<td class="left">
			    <c:out value="${unityLink.unityLinkDc}" escapeXml="false" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="formUpdt" action="<c:url value='/uss/ion/ulm/updtUnityLinkView.do'/>" method="post" style="display:inline-block; vertical-align:top">
			<input name="unityLinkId" type="hidden" value="${unityLink.unityLinkId}">
			<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_UnityLink(); return false;">
		</form>
		
		<form name="formDelete" action="<c:url value='/uss/ion/ulm/detailUnityLink.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input name="unityLinkId" type="hidden" value="${unityLink.unityLinkId}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete_UnityLink(); return false;">
		</form>
	
		<form name="formList" action="<c:url value='/uss/ion/ulm/listUnityLink.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_UnityLink(); return false;">
		</form>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>
