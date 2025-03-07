<%--
  Class Name : EgovNoteTrnsmitCnfirm.jsp
  Description : 수신자목록 팝업
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.05    장동한          최초 생성
     2017.09.14    장동한          공통컴포넌트 3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.05

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<c:set var="pageTitle"><spring:message code="comUssIonNts.popupTrnsmitCnfirm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css' />">
<script type="text/javascript">
/* ********************************************************
* 화면 닫기 함수
******************************************************** */
function fn_egov_close_TrnsmitCnfirm(){
	window.close();
}
/* ********************************************************
* 보낸쪽지함 삭제
******************************************************** */
function fn_egov_delete_TrnsmitCnfirm(vFrom){

	if(confirm("선택된 보낸쪽지 삭제 하시겠습니까?")){
		vFrom.submit();
	}

}

function fn_egov_close(){
	window.close();
}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="popup">

	<!-- 타이틀 -->
	<h1>${pageTitle}</h1>

	<table class="popwTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>

		<!-- 제목 -->
		<c:set var="title"><spring:message code="comUssIonNts.detail.noteSj"/></c:set>
		<tr>
			<th><label for="schdulCn">${title}</label> </th>
			<td class="left"><c:out value="${resultList[0].noteSj}"/></td>
		</tr>
		
		<!-- 발신자 아이디-->
		<c:set var="title"><spring:message code="comUssIonNts.detail.trnsmitNm"/>ID</c:set>
		<tr>
			<th><label for="schdulCn">${title}</label> </th>
			<td class="left"><c:out value="${resultList[0].trnsmiterIds}"/></td>
		</tr>
		
		<!-- 발신자 명 -->
		<c:set var="title"><spring:message code="comUssIonNts.detail.trnsmitNm"/>명</c:set>
		<tr>
			<th><label for="schdulCn">${title}</label> </th>
			<td class="left"><c:out value="${resultList[0].trnsmiterNm}"/></td>
		</tr>
		
		<!-- 발신시각 -->
		<c:set var="title"><spring:message code="comUssIonNts.detail.trnsmitPnttm"/></c:set>
		<tr>
			<th><label for="schdulCn">${title}</label> </th>
			<td class="left"><c:out value="${resultList[0].trnsmiterPnttm}"/></td>
		</tr>
		
	</tbody>
	</table>
	<br/>
	

	<!-- 목록영역 -->
	<table class="pop_board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 10%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 30%;">
		<col style="width: 20%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="comUssIonNts.popupTrnsmitCnfirmList.seq" /></th><!-- 순번 -->
		<th><spring:message code="comUssIonNts.popupTrnsmitCnfirmList.rcverID" /></th><!-- 수신자ID -->
		<th><spring:message code="comUssIonNts.popupTrnsmitCnfirmList.rcverNm" /></th><!-- 받는사람 -->
		<th><spring:message code="comUssIonNts.popupTrnsmitCnfirmList.openAt" /></th><!-- 개봉/미개봉 -->
		<th><spring:message code="comUssIonNts.popupTrnsmitCnfirmList.gbn" /></th><!-- 구분 -->
		<th><spring:message code="comUssIonNts.popupTrnsmitCnfirmList.rcverDateTime" /></th><!-- 수신시각 -->
		
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td><c:out value="${resultInfo.rcverIds}"/></td>
		<td><c:out value="${resultInfo.rcverNm}"/></td>
		<td>
			<c:if test="${resultInfo.openYn eq 'Y'}">개봉</c:if>
			<c:if test="${resultInfo.openYn eq 'N'}">미개봉</c:if>
		</td>
		<td>
			<c:if test="${resultInfo.recptnSe eq '1'}">수신</c:if>
			<c:if test="${resultInfo.recptnSe eq '2'}">참조</c:if>
		</td>
	    <td><c:if test="${resultInfo.openYn eq 'Y'}"><c:out value="${resultInfo.rcverPnttm}"/></c:if></td>
    
	</tr>
	</c:forEach>
	</tbody>
	</table>



	<!-- 하단 버튼 -->
	<div class="btn">
		<button class="btn_style3" onClick="fn_egov_close();" title="<spring:message code="button.close" /> <spring:message code="input.button" />"><spring:message code="button.close" /></button>
		<div style="clear:both;"></div>
	</div>
</div>




</body>
</html>
