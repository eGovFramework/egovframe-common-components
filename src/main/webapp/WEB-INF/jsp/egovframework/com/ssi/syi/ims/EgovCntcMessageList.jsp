<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcMessageList.jsp
  * @Description : EgovCntcMessageList 화면
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
<title><spring:message code="comSsiSyiIms.cntcMessageList.title"/></title><!-- 연계메시지 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_CntcMessage(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_CntcMessage(){
	location.href = "<c:url value='/ssi/syi/ims/addCntcMessage.do'/>";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_CntcMessage(){
	location.href = "<c:url value='/ssi/syi/ims/updateCntcMessage.do'/>";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_CntcMessage(cntcMessageId){
	var varForm				    = document.all["Form"];
	varForm.action              = "<c:url value='/ssi/syi/ims/getCntcMessageDetail.do'/>";
	varForm.cntcMessageId.value = cntcMessageId;
	varForm.submit();
}
-->
</script>
</head>

<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="Form" action="" method="post">
	<input type="hidden" name="cntcMessageId">
</form>

<div class="board">
	<h1><spring:message code="comSsiSyiIms.cntcMessageList.pageTop.title"/></h1>

	<form name="listForm" action="<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>" ><!-- 검색조건 -->
				<option selected value=''>--<spring:message code="input.cSelect"/>--</option><!-- 선택하세요 -->
				<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSsiSyiIms.cntcMessageList.cntcMessageNm"/></option><!-- 연계메시지명 -->
				</select>				
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value='${searchVO.searchKeyword}'/>' size="35" maxlength="35" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire"/>" onclick="fn_egov_search_CntcMessage(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/ssi/syi/ims/addCntcMessage.do'/>" onclick="" title="<spring:message code="title.create"/>"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	<input name="cntcMessageId" type="hidden" >
	<input name="pageIndex"     type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
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
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageList.index"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageList.cntcMessageId"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageList.cntcMessageNm"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;cursor:hand;" onclick="fn_egov_detail_CntcMessage('<c:out value="${resultInfo.cntcMessageId}"/>');">
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
			    	<form name="subForm" method="post" action="<c:url value='/ssi/syi/ims/getCntcMessageDetail.do'/>">
			    	<input name="cntcMessageId" type="hidden" value="<c:out value="${resultInfo.cntcMessageId}"/>">
			    	<input name="pageIndex"     type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			    	<span class="link">
			    		<input   type="submit" value="<c:out value="${resultInfo.cntcMessageId}"/>" title="<c:out value="${resultInfo.cntcMessageId}"/>" onclick="fn_egov_detail_CntcMessage('<c:out value="${resultInfo.cntcMessageId}"/>'); return false;">
			    	</span>
			    	</form>
				</td>
				<td class="left"><c:out value="${resultInfo.cntcMessageNm}"/></td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="3">
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
