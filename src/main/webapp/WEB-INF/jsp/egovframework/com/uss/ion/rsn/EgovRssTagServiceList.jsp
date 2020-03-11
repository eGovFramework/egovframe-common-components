<%--
  Class Name : EgovRssTagServiceList.jsp
  Description : RSS서비스 목록조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.05    장동한          최초 생성
     2018.10.23    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.05

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
<title><spring:message code="ussIonRsn.rssTagServiceList.rssTagServiceList"/></title><!-- RSS태그서비스 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/rsn/listRssTagService.do'/>";
   	document.listForm.submit();
}


/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_RssTagManage(){
	var vFrom = document.listForm;
	vFrom.pageIndex.value = "1";

	if( vFrom.searchCondition.selectedIndex == 0){
		alert("<spring:message code="ussIonRsn.rssTagServiceList.validate.searchCondition"/>");/* 검색조건을 선택해주세요! */
		vFrom.searchCondition.focus();
		return;
	}

	if( vFrom.searchKeyword.value == ""){
		alert("<spring:message code="ussIonRsn.rssTagServiceList.validate.searchKeyword"/>");/* 검색어를 입력해주세요! */
		vFrom.searchKeyword.focus();
		return;
	}

	vFrom.action = "<c:url value='/uss/ion/rsn/listRssTagService.do'/>";
	vFrom.submit();

}
</script>
</head>
<body>

<!-- 자바스크립트 지원 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/rss/listRssTagManage.do'/>" method="post">
	<h1><spring:message code="ussIonRsn.rssTagServiceList.rssTagServiceList"/></h1><!-- RSS태그서비스 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다 -->
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="select.searchCondition" />" class="select"><!-- 검색조건 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value='A.TRGET_SVC_NM' 		<c:if test="${searchCondition == 'A.TRGET_SVC_NM'}">selected</c:if>><spring:message code="ussIonRsn.rssTagServiceList.trgetSvcNm"/></option><!-- 대상서비스명 -->
					<option value='A.TRGET_SVC_TABLE'	<c:if test="${searchCondition == 'A.TRGET_SVC_TABLE'}">selected</c:if>><spring:message code="ussIonRsn.rssTagServiceList.trgetSvcTable"/></option><!-- 대상테이블명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value='${searchKeyword}'/>' size="10" maxlength="35" onkeyup="if(window.event.keyCode==13){fn_egov_search_RssTagManage()}" title="<spring:message code="title.search"/>" /><!-- 검색어 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="<spring:message code="button.inquire"/>" onclick="fn_egov_search_RssTagManage()" /><!-- 조회 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:35px" />
			<col style="" />
			<col style="width:200px" />
			<col style="width:70px" />
			<col style="width:70px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussIonRsn.rssTagServiceList.rssTagNum"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussIonRsn.rssTagServiceList.trgetSvcNm"/></th><!-- 대상서비스명 -->
			   <th scope="col"><spring:message code="ussIonRsn.rssTagServiceList.trgetSvcTable"/></th><!-- 대상테이블명 -->
			   <th scope="col"><spring:message code="ussIonRsn.rssTagServiceList.frstRegisterNm"/></th><!-- 작성자 -->
			   <th scope="col"><spring:message code="ussIonRsn.rssTagServiceList.frstRegisterPnttm"/></th><!-- 등록일자 -->
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
				<div style="visibility:hidden;display:none;"><a href="#LINK_PAGE${status.count}"></a></div>
				<a href="<c:url value='/uss/ion/rsn/detailRssTagService.do'/>?rssId=${resultInfo.rssId}" target="_blank" title="새 창으로 이동"><c:out value="${resultInfo.trgetSvcNm}"/></a>
				</td>
				<td>
				<a href="<c:url value='/uss/ion/rsn/detailRssTagService.do'/>?rssId=${resultInfo.rssId}" target="_blank" title="새 창으로 이동"><c:out value="${resultInfo.trgetSvcTable}"/></a>
				</td>
				<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
			   <td><c:out value="${resultInfo.frstRegisterPnttm}"/></td>
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
<input name="cmd" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>
</body>
</html>