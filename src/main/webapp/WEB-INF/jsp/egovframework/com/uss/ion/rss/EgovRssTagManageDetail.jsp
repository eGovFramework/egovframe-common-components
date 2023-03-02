<%--
  Class Name : EgovRssTagManageDetail.jsp
  Description : RSS태그관리 상세조회
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.05    장동한          최초 생성
     2018.10.24    이정은          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.05
    
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
<title><spring:message code="ussIonRss.rssTagManageDetail.rssTagManageDetail"/></title><!-- RSS태그관리 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_RssTagManage(){

}
/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_modify_RssTagManage(){
	var vFrom = document.RssTagManageForm;
	vFrom.action = "<c:url value='/uss/ion/rss/updtRssTagManage.do'/>";
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_RssTagManage(){
	var vFrom = document.RssTagManageForm;
	if(confirm("<spring:message code="ussIonRss.rssTagManageDetail.validate.deleteAlert"/>")){/* 삭제 하시겠습니까? */
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/ion/rss/detailRssTagManage.do'/>";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_RssTagManage();">

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="RssTagManageForm" action="<c:url value='/uss/ion/rss/detailRssTagManage.do'/>" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonRss.rssTagManageDetail.rssTagManageDetail"/></h2><!-- RSS태그관리 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.trgetSvcNm"/></th><!-- 대상서비스명 -->
			<td class="left">
			    <c:out value="${rssManage.trgetSvcNm}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.trgetSvcTable"/></th><!-- 대상테이블명 -->
			<td class="left">
			    <c:out value="${rssManage.trgetSvcTable}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.trgetSvcListCo"/></th><!-- 대상서비스목록개수 -->
			<td class="left">
			    <c:out value="${rssManage.trgetSvcListCo}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.hderTitle"/></th><!-- 헤더TITLE -->
			<td class="left">
			    <c:out value="${rssManage.hderTitle}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.hderLink"/></th><!-- 헤더LINK -->
			<td class="left">
			    <c:out value="${rssManage.hderLink}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.hderDescription"/></th><!-- 헤더DESCRIPTION -->
			<td class="left">
			    <c:out value="${fn:replace(rssManage.hderDescription , crlf , '<br/>')}" escapeXml="true" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.hderTag"/></th><!-- 헤더TAG -->
			<td class="left">
			    <c:out value="${rssManage.hderTag}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.hderEtc"/></th><!-- 헤더ETC -->
			<td class="left">
			    <c:set var="hderEtc" value="${fn:escapeXml(rssManage.hderEtc)}"/>
				<c:set var="hderEtc" value="${fn:replace(hderEtc , crlf , '<br>')}"/>
				<c:out value="${hderEtc}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.bdtTitle"/></th><!-- 본문TITLE -->
			<td class="left">
			    <c:out value="${rssManage.bdtTitle}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.bdtLink"/></th><!-- 본문LINK -->
			<td class="left">
			    <c:out value="${rssManage.bdtLink}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.bdtDescription"/></th><!-- 본문DESCRIPTION -->
			<td class="left">
			    <c:out value="${fn:replace(rssManage.bdtDescription , crlf , '<br/>')}" escapeXml="true" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.bdtTag"/></th><!-- 본문TAG -->
			<td class="left">
			    <c:out value="${rssManage.bdtTag}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageDetail.bdtEtc"/></th><!-- 본문ETC -->
			<td class="left">
			    <c:set var="bdtEtc" value="${fn:escapeXml(rssManage.bdtEtc)}"/>
				<c:set var="bdtEtc" value="${fn:replace(bdtEtc , crlf , '<br>')}"/>
				<c:out value="${bdtEtc}" escapeXml="false" />&nbsp;
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="formUpdt" action="<c:url value='/uss/ion/rss/updtRssTagManage.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_RssTagManage(); return false;">
		<input name="rssId" type="hidden" value="${rssManage.rssId}">
		</form>
			
		<form name="formDelete" action="<c:url value='/uss/ion/rss/detailRssTagManage.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete_RssTagManage(); return false;" />
		<input name="rssId" type="hidden" value="${rssManage.rssId}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
	
		<form name="formList" action="<c:url value='/uss/ion/rss/listRssTagManage.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="" />
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

<input name="rssId" type="hidden" value="${rssManage.rssId}">
<input name="cmd" type="hidden" value="<c:out value=''/>">
</form>

</body>
</html>