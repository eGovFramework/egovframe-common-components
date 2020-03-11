<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovMtgPlaceManageList.java
 * @Description : EgovMtgPlaceManageList jsp
 * @Modification Information
 * @
 * @  수정일           수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용                최초 생성
 * @ 2018.07.26    최 두  영                퍼블리싱 에러 수정(조회기능) 
 * @ 2018.09.11    최 두  영                다국어처리 적용 
 *  @author 이      용
 *  @since 2010.06.29
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회의실관리 목록</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*설명 : 회의실  목록 조회 */
function fncSelectMtgPlaceManageList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>";
    document.listForm.submit();
}

/*설명 : 회의실 상세조회 */
function fncSelectMtgPlaceManage(mtgPlaceId) {
    document.listForm.mtgPlaceId.value = mtgPlaceId;
    document.listForm.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceManage.do'/>";
    document.listForm.submit();   
}
/*설명 : 회의실 신규등록 화면 호출 */
function fncInsertMtgPlace() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	} 
    document.listForm.action = "<c:url value='/uss/ion/mtg/insertViewMtgPlace.do'/>";
    document.listForm.submit(); 
}
/*설명 : 회의실 목록 페이지 조회 */
function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>";
    document.listForm.submit();
}
/*설명 : 회의실 목록 조회 enter키 처리 */
function press() {
    if (event.keyCode==13) {
    	fncSelectMtgPlaceManageList('1');
    }
}

-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>" method="post">    
	<h1><spring:message code="comUssIonMtg.mtgPlaceManageList.title"/></h1><!-- 회의실 관리목록  -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /></label><!-- 회의실명 -->
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${mtgPlaceManageVO.searchKeyword }"/>' size="25" onkeypress="press();" title="<spring:message code="comUssIonMtg.mtgPlaceManageList.searchUser" />" />
				<input type="submit" class="s_btn"  value='<spring:message code="button.inquire" />'  title='<spring:message code="button.inquire" />'  onclick="fncSelectMtgPlaceManageList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/mtg/insertViewMtgPlace.do'/>?searchCondition=1" onclick="fncInsertMtgPlace(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	<input type="hidden" name="pageIndex" value="<c:if test="${empty mtgPlaceManageVO.pageIndex }">1</c:if><c:if test="${!empty mtgPlaceManageVO.pageIndex }"><c:out value='${mtgPlaceManageVO.pageIndex}'/></c:if>">
	</form>
	<table class="board_list">
		<caption><spring:message code="comUssIonMtg.mtgPlaceManageList.meetingManagementList" /></caption><!-- 회의실 목차 관리 -->
		<colgroup>
			<col style="width:7%" />
			<col style="width:20%" />
			<col style="width:25%" />
			<col style="width:10%" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonMtg.mtgPlaceManageList.number" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /></th><!-- 회의실명 -->
			   <th scope="col"><spring:message code="comUssIonMtg.mtgPlaceManageList.time" /></th><!-- 개방시간 -->
			   <th scope="col"><spring:message code="comUssIonMtg.mtgPlaceManageList.capacity" /></th><!-- 수용인원 -->
			   <th scope="col"><spring:message code="comUssIonMtg.mtgPlaceManageList.location" /></th><!-- 위치 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="mtgPlaceManage" items="${mtgPlaceManageList}" varStatus="status">
			<tr>
				<td><c:out value="${(mtgPlaceManageVO.pageIndex - 1) * mtgPlaceManageVO.pageSize + status.count}"/></td>
				<td class="left">
					<form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceManage.do'/>">
						<input type="hidden" name="mtgPlaceId"  value="<c:out value="${mtgPlaceManage.mtgPlaceId}"/>">
						<span class="link"><input type="submit" value="<c:out value="${mtgPlaceManage.mtgPlaceNm}"/>" onclick="fncSelectMtgPlaceManage('<c:out value="${mtgPlaceManage.mtgPlaceId}"/>'); return false;" style="text-align : left;"></span>
					</form>
				</td>
				<td><c:out value="${mtgPlaceManage.opnBeginTm}"/> ~ <c:out value="${mtgPlaceManage.opnEndTm}"/></td>
				<td><c:out value="${mtgPlaceManage.aceptncPosblNmpr}"/><spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></td><!-- 명 -->
				<td><c:out value="${mtgPlaceManage.mtgPlaceTemp3}"/> <c:out value="${mtgPlaceManage.lcDetail}"/></td> 
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
</body>
</html>