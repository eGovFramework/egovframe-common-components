<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovSystemCntcList.jsp
  * @Description : EgovSystemCntcList 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호             최초 생성
  *   2018.09.10   신용호             표준프레임워크 v3.8 개선
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
<title><spring:message code="comSsiSyiSim.systemCntcList.title"/></title><!-- 시스템연계 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='${selfUri}'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_SystemCntc(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='${selfUri}'/>";
   	document.listForm.submit();
}

<c:set var="detailUri" value="/ssi/syi/sim/getSystemCntcDetail.do" />
<c:set var="confirmTF" value="F"/>

/*
 * 관리자 모드 처리
 */
<c:if test="${selfUri eq '/ssi/syi/scm/getConfirmSystemCntcList.do'}">
	<c:set var="confirmTF" value="T"/>
	<c:set var="detailUri" value="/ssi/syi/scm/getConfirmSystemCntcDetail.do" />
</c:if>

/*
 * 업무사용자 모드 처리
 */
<c:if test="${selfUri eq '/ssi/syi/sim/getSystemCntcList.do'}">

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_SystemCntc(){
	location.href = "<c:url value='/ssi/syi/sim/addSystemCntc.do'/>";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_SystemCntc(){
	location.href = "<c:url value='/ssi/syi/sim/updateSystemCntc.do'/>";
}
</c:if>


/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_SystemCntc(cntcId){
	var varForm				    = document.all['Form'];
	varForm.action              = "<c:url value='${detailUri}'/>";
	varForm.cntcId.value 		= cntcId;
	varForm.submit();
}

-->
</script>
</head>

<body>
<DIV id="content" style="display">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSsiSyiSim.systemCntcList.pageTop.title"/></h1><!-- 시스템연계 목록 -->

	<form name="Form" action="<c:url value='${detailUri}'/>" method="post">
		<input type="hidden" name="cntcId">
	</form>
	<form name="listForm" action="<c:url value='${selfUri}'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSsiSyiSim.systemCntcList.cntcNm"/></option><!-- 시스템연계명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value='${searchVO.searchKeyword}'/>' maxlength="35" size="35" onkeypress="press();" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="fn_egov_search_SystemCntc(); return false;" /><!-- 조회 -->
				
				<% /** * 일반사용자 모드 처리 */ %>
				<c:if test="${confirmTF eq 'F'}">
				<span class="btn_b"><a href="<c:url value='/ssi/syi/sim/addSystemCntc.do?pageIndex='/><c:out value='${searchVO.pageIndex}'/>" onclick="" title="<spring:message code="title.create"/>"><spring:message code="button.create"/></a></span>
				</c:if>
			</li>
		</ul>
	</div>
	<input name="cntcId"    type="hidden" value=""/>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:50%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSsiSyiSim.systemCntcList.index"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comSsiSyiSim.systemCntcList.cntcId"/></th><!-- 시스템연계ID -->
			   <th scope="col"><spring:message code="comSsiSyiSim.systemCntcList.cntcNm"/></th><!-- 시스템연계명 -->
			   <th scope="col"><spring:message code="comSsiSyiSim.systemCntcList.confmAt"/></th><!-- 승인여부 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
			    	<form name="subForm"      method="post" action="<c:url value='${detailUri}'/>">
			    	<input name="cntcId"      type="hidden" value="<c:out value='${resultInfo.cntcId}'/>">
			    	<input name="pageIndex"   type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			    	<span class="link"><input type="submit" value="<c:out value='${resultInfo.cntcId}'/>" onclick="fn_egov_detail_SystemCntc('<c:out value="${resultInfo.cntcId}"/>'); return false;"></span>
			    	</form>
				</td>
				<td><c:out value="${resultInfo.cntcNm}"/></td>
			    <td>
			    	<c:if test="${resultInfo.confmAt == 'Y'}"><spring:message code="comSsiSyiSim.systemCntcList.confmAt.Y"/></c:if>
			    	<c:if test="${resultInfo.confmAt == 'N'}"><spring:message code="comSsiSyiSim.systemCntcList.confmAt.N"/></c:if>
			    </td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="4">
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

</DIV>

</body>
</html>
