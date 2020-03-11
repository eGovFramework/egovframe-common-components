<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcSttusList.jsp
  * @Description : EgovCntcSttusList 화면
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
<title><spring:message code="comSsiSyiIst.cntcSttusList.title"/></title><!-- 연계현황 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/ssi/syi/ist/getCntcSttusList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_CntcSttus(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_CntcSttus(cntcId){
	var varForm				    = document.all["listForm"];
	varForm.action              = "<c:url value='/ssi/syi/ist/getCntcSttusDetail.do'/>";
	varForm.cntcId.value 		= cntcId;
	varForm.submit();
}
-->
</script>
</head>

<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSsiSyiIst.cntcSttusList.pageTop.title"/></h1>

	<form name="listForm" action="<c:url value='/ssi/syi/ist/getCntcSttusList.do'/>" method="post">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>" >
				<option selected value=''>--<spring:message code="input.cSelect"/>--</option><!-- 선택하세요 -->
				<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSsiSyiIst.cntcSttusList.cntcNm"/></option><!-- 연계명 -->
				</select>
				
				<input class="s_input2 vat" name="searchKeyword" type="text" size="35" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="35"  title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" onclick="fn_egov_search_CntcSttus(); return false;">
			</li>
		</ul>
	</div>
	<input type="hidden" name="cntcId">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.index"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.cntcNm"/></th><!-- 연계명 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.cntcType"/></th><!-- 연계유형 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.provdInsttNm"/></th><!-- 제공기관 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.provdSysNm"/></th><!-- 제공시스템 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.provdSvcNm"/></th><!-- 제공서비스 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.requstInsttNm"/></th><!-- 요청기관 -->
			   <th scope="col"><spring:message code="comSsiSyiIst.cntcSttusList.requstSysNm"/></th><!-- 요청시스템 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
			    	<form name="subForm" method="post" action="<c:url value='/ssi/syi/ist/getCntcSttusDetail.do'/>">
			    	<input name="cntcId"      type="hidden" value="<c:out value="${resultInfo.cntcId}"/>">
			    	<input name="pageIndex"   type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			    	<span class="link"><input type="submit" value="<c:out value="${resultInfo.cntcNm}"/>" onclick="fn_egov_detail_CntcSttus('<c:out value="${resultInfo.cntcId}"/>'); return false;"></span>
			    	</form>
			
				</td>
				<td><c:out value="${resultInfo.cntcType}"/></td>
				<td><c:out value="${resultInfo.provdInsttNm}"/></td>
				<td><c:out value="${resultInfo.provdSysNm}"/></td>
				<td><c:out value="${resultInfo.provdSvcNm}"/></td>
				<td><c:out value="${resultInfo.requstInsttNm}"/></td>
				<td><c:out value="${resultInfo.requstSysNm}"/></td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="8">
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

</body>
</html>
