<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcMessageDetail.jsp
  * @Description : EgovCntcMessageDetail 화면
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
<title><spring:message code="comSsiSyiIms.cntcMessageDetail.title"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/ssi/syi/ims/getCntcMessageDetail.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CntcMessage(){
	location.href = "<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_modify_CntcMessage(){
	var varForm				    = document.all["Form"];
	varForm.action              = "<c:url value='/ssi/syi/ims/updateCntcMessage.do'/>";
	varForm.cntcMessageId.value	= "<c:out value='${result.cntcMessageId}'/>";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_CntcMessage(){
	if (confirm("<spring:message code='common.delete.msg' />")) {
		var varForm				    = document.all["Form"];
		varForm.action			    = "<c:url value='/ssi/syi/ims/removeCntcMessage.do'/>";
		varForm.cntcMessageId.value	= "<c:out value='${result.cntcMessageId}'/>";
		varForm.submit();
	}
}
/* ********************************************************
* 연계메시지항목 등록 화면으로  바로가기
******************************************************** */
function fn_egov_regist_CntcMessageItem(){
	var varForm					= document.all["Form"];
	varForm.action          	= "<c:url value='/ssi/syi/ims/addCntcMessageItem.do'/>";
	varForm.cntcMessageId.value	= "<c:out value='${result.cntcMessageId}'/>";
	varForm.submit();
}
/* ********************************************************
* 연계메시지항목 수정화면으로  바로가기
******************************************************** */
function fn_egov_modify_CntcMessageItem(itemId){
	var varForm					= document.all["Form"];
	varForm.action          	= "<c:url value='/ssi/syi/ims/updateCntcMessageItem.do'/>";
	varForm.cntcMessageId.value	= "<c:out value='${result.cntcMessageId}'/>";
	varForm.itemId.value		= itemId;
	varForm.submit();
}
/* ********************************************************
* 연계메시지항목 삭제 처리 함수
******************************************************** */
function fn_egov_delete_CntcMessageItem(itemId){
	if (confirm("<spring:message code='common.delete.msg' />")) {
		var varForm					= document.all["Form"];
		varForm.action				= "<c:url value='/ssi/syi/ims/removeCntcMessageItem.do'/>";
		varForm.cntcMessageId.value	= "<c:out value='${result.cntcMessageId}'/>";
		varForm.itemId.value		= itemId;
		varForm.submit();
	}
}

-->
</script>
</head>
<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="Form" action="" method="post">
	<input name="cntcMessageId" type="hidden">
	<input name="itemId"        type="hidden">
</form>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIms.cntcMessageDetail.pageTop.title"/></h2><!-- 연계메시지 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageDetail.cntcMessageId"/> <span class="pilsu">*</span></th><!-- 연계메시지ID -->
			<td class="left">
			    <c:out value="${result.cntcMessageId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageDetail.cntcMessageNm"/> <span class="pilsu">*</span></th><!-- 연계메시지명 -->
			<td class="left">
			    <c:out value="${result.cntcMessageNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageDetail.upperCntcMessageId"/></th><!-- 상위연계메시지ID -->
			<td class="left">
			    <c:out value="${result.upperCntcMessageId}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="formUpdt" action="<c:url value='/ssi/syi/ims/updateCntcMessage.do'/>" method="post" style="display:inline-block; vertical-align:top">
			<input class="s_submit" type="submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" />" onclick="fn_egov_modify_CntcMessage(); return false;"><!-- 수정 -->
			<input name="cntcMessageId" type="hidden" value="<c:out value='${result.cntcMessageId}'/>">
			<input name="itemId" type="hidden" value="">
		</form>

		<form name="formDelete" action="<c:url value='/ssi/syi/ims/removeCntcMessage.do'/>" method="post" style="display:inline-block; vertical-align:top">
			<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" title="<spring:message code="title.delete" />" onclick="fn_egov_delete_CntcMessage(); return false;"><!-- 삭제 -->
			<input name="cntcMessageId" type="hidden" value="<c:out value='${result.cntcMessageId}'/>">
			<input name="itemId"        type="hidden" value="">
		</form>
	
		<form name="formList" action="<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" title="<spring:message code="title.list" />" onclick="fn_egov_list_CntcMessage(); return false;"><!-- 목록 -->
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

<div class="board">
	<h1><spring:message code="comSsiSyiIms.cntcMessageDetail.pageTop.title.sub"/></h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<input class="s_btn" type="submit" value="<spring:message code="button.create" />" title="<spring:message code="title.create"/>" onclick="fn_egov_regist_CntcMessageItem(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageDetail.itemIndex"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageDetail.itemId"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageDetail.itemNm"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageDetail.itemType"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageDetail.itemLt"/></th>
			   <th scope="col"><spring:message code="comSsiSyiIms.cntcMessageDetail.process"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cntcMessageItemList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(cntcMessageItemVO.pageIndex - 1) * cntcMessageItemVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.itemId}"/></td>
				<td><c:out value="${resultInfo.itemNm}"/></td>
				<td><c:out value="${resultInfo.itemType}"/></td>
				<td><c:out value="${resultInfo.itemLt}"/></td>
				<td>
					<form name="formUpdt" action="<c:url value='/ssi/syi/ims/updateCntcMessageItem.do'/>" method="post" style="display:inline-block; vertical-align:top">
					<span class="button"><input type="submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" />" onclick="fn_egov_modify_CntcMessageItem('<c:out value="${resultInfo.itemId}"/>'); return false;"></span><!-- 수정 -->
					<input name="cntcMessageId" type="hidden" value="<c:out value='${result.cntcMessageId}'/>">
					<input name="itemId"        type="hidden" value="<c:out value='${resultInfo.itemId}'/>">
					</form>
					
					<form name="formDelete" action="<c:url value='/ssi/syi/ims/removeCntcMessageItem.do'/>" method="post" style="display:inline-block; vertical-align:top">
					<span class="button"><input type="submit" value="<spring:message code="button.delete" />" title="<spring:message code="title.delete" />" onclick="fn_egov_delete_CntcMessageItem('<c:out value="${resultInfo.itemId}"/>'); return false;"></span><!-- 삭제 -->
					<input name="cntcMessageId" type="hidden" value="<c:out value='${result.cntcMessageId}'/>">
					<input name="itemId"        type="hidden" value="<c:out value='${resultInfo.itemId}'/>">
					</form>
				</td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(cntcMessageItemList) == 0}">
				<tr>
					<td class="lt_text3" colspan="6">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>

</div>

</body>
</html>
