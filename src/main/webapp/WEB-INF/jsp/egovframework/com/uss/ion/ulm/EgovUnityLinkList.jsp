<%--
  Class Name : EgovUnityLinkList.jsp
  Description : 통합링크관리 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
-------    --------    ---------------------------
2008.03.09  장동한			최초 생성
2018.08.16  이정은			공통컴포넌트 3.8 개선
2024.10.29  권태성			목록의 등록일자 미표시 수정, 등록 페이지 신규 경로로 변경

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="uss.ion.ulm.unityLinkList.unityLinkList" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/ulm/listUnityLink.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_UnityLink(unityLinkId){
	var vFrom = document.listForm;
	vFrom.unityLinkId.value = unityLinkId;
	vFrom.action = "<c:url value='/uss/ion/ulm/detailUnityLink.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_UnityLink(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/ion/ulm/listUnityLink.do' />";
	vFrom.submit();

}
</script>

</head>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form name="listForm" action="<c:url value='/uss/ion/ulm/listUnityLink.do'/>" method="post">
<div class="board">
	<h1><spring:message code="uss.ion.ulm.unityLinkList.unityLinkList" /></h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보에 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition" />">
				   <option value=''><spring:message code="input.select" /></option>
				   <option value='UNITY_LINK_NM' <c:if test="${searchCondition == 'UNITY_LINK_NM'}">selected</c:if>><spring:message code="uss.ion.ulm.unityLinkList.unityLinkNm" /></option><!-- 통합링크명 -->
				   <option value='UNITY_LINK_DC' <c:if test="${searchCondition == 'UNITY_LINK_DC'}">selected</c:if>><spring:message code="uss.ion.ulm.unityLinkList.unityLinkDc" /></option><!-- 통합링크설명 -->
			   </select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value="${searchVO.searchKeyword}"/>" size="10" maxlength="35" onkeypress="press();" title="<spring:message code="title.search" />" /><!-- 검색어 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_search_UnityLink(); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/ulm/registUnityLinkView.do' />?pageIndex=<c:out value='${searchVO.pageIndex}' />" onclick="" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input name="unityLinkId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:35px" />
			<col style="width:60px" />
			<col style="width:150px" />
			<col style="" />
			<col style="width:70px" />
			<col style="width:90px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="uss.ion.ulm.unityLinkList.unityLinkNum" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="uss.ion.ulm.unityLinkList.unityLinkGroup" /></th><!-- 그룹 -->
			   <th scope="col"><spring:message code="uss.ion.ulm.unityLinkList.unityLinkNm" /></th><!-- 통합링크명 -->
			   <th scope="col"><spring:message code="uss.ion.ulm.unityLinkList.unityLinkUrl" /></th><!-- 통합링크URL -->
			   <th scope="col"><spring:message code="uss.ion.ulm.unityLinkList.unityLinkRegNm" /></th><!-- 등록자 -->
			   <th scope="col"><spring:message code="uss.ion.ulm.unityLinkList.unityLinkRegDate" /></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>

			<c:if test="${fn:length(resultList) == 0}">
			<tr>
			<td colspan="6">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			 <%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td>
			 	<c:forEach items="${unityLinkSeCodeList}" var="resultInfo1" varStatus="pollKindStatus">
					<c:if test="${resultInfo1.code eq resultInfo.unityLinkSeCode}">
					<c:out value="${resultInfo1.codeNm}" escapeXml="false" />
					</c:if>
				</c:forEach>
			    <td>
			    	<form name="subForm" method="post" action="<c:url value='/uss/ion/ulm/detailUnityLink.do'/>">
					<input name="unityLinkId" type="hidden" value="${resultInfo.unityLinkId}">
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
					<span class="link"><input type="submit" style="text-align:center;" value="<c:out value="${resultInfo.unityLinkNm}"/>" onclick="fn_egov_detail_UnityLink('<c:out value="${resultInfo.unityLinkId}"/>'); return false;"></span>
			    	</form>
			    </td>
			    <td class="left"><c:out value="${resultInfo.unityLinkUrl}"/></td>
			    <td><c:out value="${resultInfo.frstRegisterNm}"/></td>
			    <td><c:out value="${resultInfo.frstRegistPnttm}"/></td>
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
</html>
