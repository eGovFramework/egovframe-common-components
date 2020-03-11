<%--
  Class Name : EgovRecentSrchwrdDetail.jsp
  Description : 최근검색어관리 상세보기
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2011.12.14    이기하          최근검색어관리번호 추가
     2018.08.16    이정은          공통컴포넌트 3.8 개선(퍼블리싱 확인 및 다국어처리)
 
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
<title><spring:message code="ussIonRsm.recentSrchwrdDetail.recentSrchwrdDetail"/></title><!-- 최근검색어관리 관리 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_RecentSrchwrd(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_RecentSrchwrd(){
	location.href = "<c:url value='/uss/ion/rsm/listRecentSrchwrd.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_RecentSrchwrd(){
	var vFrom = document.RecentSrchwrdForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/ion/rsm/updtRecentSrchwrd.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_RecentSrchwrd(){
	var vFrom = document.RecentSrchwrdForm;
	if(confirm("<spring:message code="ussIonRsm.recentSrchwrdDetail.validate.deleteMsg"/>")){/* 삭제 하시겠습니까? */
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/ion/rsm/detailRecentSrchwrd.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_RecentSrchwrd();">
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonRsm.recentSrchwrdDetail.recentSrchwrdDetail"/></h2><!-- 최근검색어관리 상세보기 -->

	<!-- 등록폼 -->
	<form name="RecentSrchwrdForm" action="<c:url value=''/>" method="post">
	
	<table class="wTable">
		<colgroup>
			<col style="width:22%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdDetail.srchwrdManageId"/></th><!-- 최근검색어관리번호 -->
			<td class="left">
			    <c:out value="${recentSrchwrd.srchwrdManageId}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdDetail.srchwrdManageNm"/> <span class="pilsu">*</span></th><!-- 최근검색어관리명 -->
			<td class="left">
			    <c:out value="${recentSrchwrd.srchwrdManageNm}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdDetail.srchwrdManageUrl"/> <span class="pilsu">*</span></th><!-- 최근검색어관리URL -->
			<td class="left">
			    <c:out value="${recentSrchwrd.srchwrdManageUrl}"  />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdDetail.srchwrdManageUseYn"/> <span class="pilsu">*</span></th><!-- 사용자검색여부 -->
			<td class="left">
			    <c:out value="${recentSrchwrd.srchwrdManageUseYn}" />
			</td>
		</tr>
	</table>
	
	<input name="srchwrdManageId" type="hidden" value="${recentSrchwrd.srchwrdManageId}">
	<input name="cmd" type="hidden" value="<c:out value=''/>"/>
	</form>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="formUpdt" action="<c:url value='/uss/ion/rsm/updtRecentSrchwrd.do'/>" method="post" style="display:inline-block">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_RecentSrchwrd(); return false;" />
		<input name="srchwrdManageId" type="hidden" value="${recentSrchwrd.srchwrdManageId}">
		</form>
		
		<form name="formDelete" action="<c:url value='/uss/ion/rsm/detailRecentSrchwrd.do'/>" method="post" style="display:inline-block">
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete_RecentSrchwrd(); return false;" />
		<input name="srchwrdManageId" type="hidden" value="${recentSrchwrd.srchwrdManageId}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
		
		<form name="formList" action="<c:url value='/uss/ion/rsm/listRecentSrchwrd.do'/>" method="post" style="display:inline-block">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_RecentSrchwrd(); return false;" />
		</form>
	</div>
	<div style="clear:both;"></div>
</div>
</DIV>
</body>
</html>
