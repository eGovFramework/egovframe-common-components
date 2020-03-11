<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcInsttList.jsp
  * @Description : EgovCntcInsttList 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2018.09.10   신용호              표준프레임워크 v3.8 개선
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
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comSsiSyiIis.cntcInsttList.title"/></title><!-- 연계기관 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">

<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_CntcInstt(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_CntcInstt(){
	location.href = "<c:url value='/ssi/syi/iis/updateCntcInstt.do'/>";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_CntcInstt(insttId){
	var varForm				 = document.all["listForm"];
	varForm.action           = "<c:url value='/ssi/syi/iis/getCntcInsttDetail.do'/>";
	varForm.insttId.value    = insttId;
	varForm.submit();
}
-->
</script>
</head>

<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSsiSyiIis.cntcInsttList.pageTop.title"/></h1><!-- 연계기관 목록 -->

	<form name="listForm" action="<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>">
				<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
				<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSsiSyiIis.cntcInsttList.insttNm"/></option><!-- 기관명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value='${searchVO.searchKeyword}'/>' size="35" maxlength="35" onkeypress="press();" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire"/>" onclick="fn_egov_search_CntcInstt(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/ssi/syi/iis/addCntcInstt.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="" title="<spring:message code="title.create"/>"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	<input type="hidden" name="insttId">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>" >
	</form>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:30%" />
			<col style="width:60%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSsiSyiIis.cntcInsttList.index"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comSsiSyiIis.cntcInsttList.insttId"/></th><!-- 기관ID -->
			   <th scope="col"><spring:message code="comSsiSyiIis.cntcInsttList.insttNm"/></th><!-- 기관명 -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan=3>
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
			</c:if>
			
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;cursor:hand;" onclick="fn_egov_detail_CntcInstt('<c:out value="${resultInfo.insttId}"/>');">
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
			
			    	<form name="subForm" method="post" action="<c:url value='/ssi/syi/iis/getCntcInsttDetail.do'/>">
			    	<input name="insttId"     type="hidden" value="<c:out value='${resultInfo.insttId}'/>">
			    	<input name="pageIndex"   type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			    	<span class="link"><input type="submit" value="<c:out value='${resultInfo.insttId}'/>" onclick="fn_egov_detail_CntcInstt('<c:out value="${resultInfo.insttId}"/>'); return false;"></span>
			    	</form>
			
				</td>
				<td class="left"><c:out value="${resultInfo.insttNm}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
		</ul>
	</div>
</div>

</body>
</html>
