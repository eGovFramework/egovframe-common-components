<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCcmAdministCodeList.jsp
  * @Description : EgovCcmAdministCodeList 화면
  * @Modification Information
  * @
  * @  수정일    수정자         수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호		최초 생성
  * @ 2024.10.25   권태성		사용하지 않고 있는 fnDelete 함수를 제거, 등록 페이지 신규 경로로 변경
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmAdc.ccmAdministCode.title"/> <spring:message code="title.list" /></c:set>
<html lang="ko">
<head>
<title>${pageTitle}</title> <!-- 행정코드 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fnSearch(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnRegist(){
	location.href = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeRegistView.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fnModify(){
	location.href = "";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fnDetail(administZoneSe,administZoneCode){
	var varForm				       = document.getElementById("Form");
	varForm.action                 = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeDetail.do'/>";
	varForm.administZoneSe.value   = administZoneSe;
	varForm.administZoneCode.value = administZoneCode;
	varForm.submit();
}

function press(event) {
	if (event.keyCode == 13) {
		linkPage(1);
	}
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
<form name="listForm" action="<c:url value='/sym/ccm/adc/EgovCcmAdministCodeList.do'/>" method="post">
	<h1>${pageTitle}</h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="" id="searchCondition">
					<option selected value=''>--선택하세요--</option>
					<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddrName" /></option> <!-- 법정동 지역명 -->
					<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmAdc.ccmAdministCode.admAddrName" /></option> <!-- 행정동 지역명 -->
				</select>
				<input id="searchKeyword" class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword}" size="25" onkeypress="press(event);" title="사용자명검색" />
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fnSearch(); return false;" /> <!-- 조회 -->
				<input class="s_btn" type="submit" value="<spring:message code="title.create" />" title="<spring:message code="title.create" />" onclick="fnRegist(); return false;" /> <!-- 등록 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption>로그인정책 관리</caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:50%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th>
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.cls" /></th> <!-- 구분 -->
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.administCode" /></th> <!-- 행정구역코드 -->
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.administName" /></th> <!-- 행정구역명 -->
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.useAt" /></th> <!-- 사용여부 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fnDetail('${resultInfo.administZoneSe}', '${resultInfo.administZoneCode}');">
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
					<c:choose>
						<c:when test='${resultInfo.administZoneSe == "1"}'><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddr" /></c:when>
						<c:when test='${resultInfo.administZoneSe == "2"}'><spring:message code="comSymCcmAdc.ccmAdministCode.admAddr" /></c:when>
					</c:choose>
				</td>
				<td>${resultInfo.administZoneCode}</td>
				<td class="left">${resultInfo.administZoneNm}</td>
				<td><c:if test="${resultInfo.useAt == 'Y'}"><spring:message code="comSymCcmAdc.ccmAdministCode.use" /></c:if><c:if test="${resultInfo.useAt == 'N'}"><spring:message code="comSymCcmAdc.ccmAdministCode.notUse" /></c:if></td> <!-- 미사용 -->
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="5">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>


<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>
<form name="Form" id="Form" method="post" action="<c:url value='/sym/ccm/adc/EgovCcmAdministCodeList.do'/>">
	<input type=hidden name="administZoneSe">
	<input type=hidden name="administZoneCode">
	<input type="submit" id="invisible" class="invisible">
</form>

</DIV>
</body>
</html>
