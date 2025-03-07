<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovInsttCodeRecptnList.jsp
  * @Description : EgovInsttCodeRecptnList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.08.11   이중호			최초 생성
  * @ 2024.10.29   권태성			중첩된 form 제거, insttCode & pageIndex 파라미터 추가, onkeypress에서 호출할 press 함수를 페이지에 추가
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmIcr.insttCodeRecptn.title"/></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">

<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/ccm/icr/getInsttCodeRecptnList.do'/>";
    document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_InsttCodeRecptn(){
    document.listForm.pageIndex.value = 1;
    document.listForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_InsttCodeRecptn(insttCode){
    var varForm              = document.all["listForm"];
    varForm.action           = "<c:url value='/sym/ccm/icr/getInsttCodeDetail.do'/>";
    varForm.insttCode.value  = insttCode;
    varForm.submit();
}

function press(event) {
    if (event.keyCode == 13) {
        fn_egov_pageview(1);
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/sym/ccm/icr/getInsttCodeRecptnList.do'/>" method="post">
<input name="insttCode" type="hidden" value=""/>
<input name="pageIndex" type="hidden" value="${searchVO.pageIndex}"/>
<div class="board">
	<h1>${pageTitle}</h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="검색조건구분">
					<option selected value=''>--<spring:message code="comSymCcmIcr.insttCodeRecptn.select"/>--</option> <!-- 선택하세요 -->
					<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmIcr.insttCodeRecptn.orgNm"/></option> <!-- 기관명 -->
				</select>
				
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' size="25" onkeypress="press(event);" title="<spring:message code="title.search"/>" />
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_search_InsttCodeRecptn(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption><spring:message code="comSymCcmIcr.insttCodeRecptn.title"/></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:70%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptn.results.col1"/></th> <!-- 순번 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptn.results.col2"/></th> <!-- 기관코드 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptn.results.col3"/></th> <!-- 기관명 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer" onclick="fn_egov_detail_InsttCodeRecptn('<c:out value="${resultInfo.insttCode}"/>');">
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
					<span class="link"><a href="#" onclick="fn_egov_detail_InsttCodeRecptn('<c:out value="${resultInfo.insttCode}"/>'); return false;">${resultInfo.insttCode}</a></span>
				</td>
				<td class="lt_text" ><c:out value="${resultInfo.allInsttNm}"/></td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan=3>
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
		</ul>
	</div>
</div>

</form>
</body>
</html>
