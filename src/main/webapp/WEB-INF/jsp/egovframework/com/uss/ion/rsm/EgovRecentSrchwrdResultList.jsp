<%--
  Class Name : EgovRecentSrchwrdResultList.jsp
  Description : 최근검색어결과 목록 페이지
  Modification Information

       수정일                수정자               수정내용
    ----------   --------     ---------------------------
    2008.03.09   장동한               최초 생성
    2018.08.16   이정은               공통컴포넌트 3.8 개선
    2019.12.11   신용호               KISA 보안약점 조치 (크로스사이트 스크립트)

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
<title><spring:message code="ussIonRsm.recentSrchwrdResultList.recentSrchwrdResultList"/></title><!-- 최근검색어 결과 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/rsm/listRecentSrchwrdResult.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
* 삭제처리
******************************************************** */
function fn_egov_delete_RecentSrchwrd(srchwrdId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code="ussIonRsm.recentSrchwrdResultList.validate.deleteMsg"/>")){/* 삭제 하시겠습니까? */
		vFrom.cmd.value = 'del';
		vFrom.srchwrdId.value = srchwrdId;
		vFrom.action = "<c:url value='/uss/ion/rsm/listRecentSrchwrdResult.do'/>";
		vFrom.submit();
		
	}else{
		vFrom.cmd.value = '';
	}
}

/* ********************************************************
* 전체삭제처리
******************************************************** */
function fn_egov_deleteAll_RecentSrchwrd(){
	var vFrom = document.listForm;
	if(confirm("<spring:message code="ussIonRsm.recentSrchwrdResultList.validate.deleteAMsg"/>")){/* 모두 삭제 하시겠습니까? */
		vFrom.cmd.value = 'delAll';
		vFrom.action = "<c:url value='/uss/ion/rsm/listRecentSrchwrdResult.do'/>";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}

/* ********************************************************
* 검색 함수
******************************************************** */
function fn_egov_search_RecentSrchwrd(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/ion/rsm/listRecentSrchwrdResult.do' />";
	vFrom.submit();

}

</script>
</head>

<sic id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<form name="listForm" action="<c:url value=''/>" method="post">
	
	<h1><spring:message code="ussIonRsm.recentSrchwrdResultList.recentSrchwrdResultList"/></h1><!-- 최근검색어결과 목록 -->
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg"/>"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- --선택하세요-- -->
					<option value='RECENT_SRCHWRD_NM' <c:if test="${searchCondition == 'RECENT_SRCHWRD_NM'}">selected</c:if>><spring:message code="ussIonRsm.recentSrchwrdResultList.srchwrdNm"/></option><!-- 최근검색어명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value='${searchKeyword}'/>" size="10" title="<spring:message code="input.input"/>" /><!-- 검색단어입력 -->
				
				<span class="btn_b"><a href="#" onclick="fn_egov_search_RecentSrchwrd(); return false;" title="<spring:message code="button.inquire" />"><spring:message code="button.inquire" /></a></span>
				<span class="btn_b"><a href="#" onclick="fn_egov_deleteAll_RecentSrchwrd(); return false;" title="<spring:message code="ussIonRsm.recentSrchwrdResultList.deleteAllButton"/>"><spring:message code="ussIonRsm.recentSrchwrdResultList.deleteAllButton"/></a></span><!-- 전체삭제 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:35px" />
			<col style="" />
			<col style="width:75px" />
			<col style="width:75px" />
			<col style="width:50px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussIonRsm.recentSrchwrdResultList.srchwrdNum"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussIonRsm.recentSrchwrdResultList.srchwrdNm"/></th><!-- 최근검색어 -->
			   <th scope="col"><spring:message code="ussIonRsm.recentSrchwrdResultList.frstRegisterNm"/></th><!-- 등록자 -->
			   <th scope="col"><spring:message code="ussIonRsm.recentSrchwrdResultList.frstRegisterPnttm"/></th><!-- 등록일자 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>

			<c:if test="${fn:length(resultList) == 0}">
			<tr>
			<td colspan="5">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			
			 <%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td>
					<c:out value="${resultInfo.srchwrdNm}"/>
				</td>
				<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
				<td><c:out value="${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}"/></td>
				<td>
					<input class="btn01" type="button" value='<spring:message code="ussIonRsm.recentSrchwrdResultList.deleteButton"/>' onclick="fn_egov_delete_RecentSrchwrd('${resultInfo.recentSrchwrdId}')" /><!-- 삭제 -->
				</td>
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

<input name="srchwrdManageId" type="hidden" value="<c:out value='${srchwrdManageId}'/>">
<input name="srchwrdId" type="hidden" value="">
<input name="searchMode" type="hidden" value="">
<input name="cmd" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>
