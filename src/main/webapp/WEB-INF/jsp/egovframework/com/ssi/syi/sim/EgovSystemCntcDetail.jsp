<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovSystemCntcDetail.jsp
  * @Description : EgovSystemCntcDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
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
<title><spring:message code="comSsiSyiSim.systemCntcDetail.title"/></title><!-- 시스템연계 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	varForm.cmd.value			= "";
	document.listForm.action 	= "<c:url value='${selfUri}'/>";
   	document.listForm.submit();
}

<c:set var="listUri" value="/ssi/syi/sim/getSystemCntcList.do" />
<c:set var="confirmTF" value="F"/>

/*
 * 관리자로 접근시 처리
 */
<c:if test="${selfUri eq '/ssi/syi/scm/getConfirmSystemCntcDetail.do'}">
	<c:set var="listUri" value="/ssi/syi/scm/getConfirmSystemCntcList.do" />
	<c:set var="confirmTF" value="T"/>

/* ********************************************************
 * 승인/승인취소 처리 함수
 ******************************************************** */
function fn_egov_confirm_SystemCntc(confmAt){
	if (confirm((confmAt=='Y')?"<spring:message code="common.acknowledgement.msg"/>":"<spring:message code="common.acknowledgementcancel.msg"/>")) {
		var varForm				    = document.all["Form"];
		varForm.cmd.value			= "Confirm";
		varForm.action              = "<c:url value='${selfUri}'/>";
		varForm.cntcId.value		= "<c:out value='${result.cntcId}'/>";
		varForm.confmAt.value		= confmAt;
		varForm.submit();
	}
}
</c:if>

/*
 * 업무사용자로 접근시 처리
 */
<c:if test="${selfUri eq '/ssi/syi/sim/getSystemCntcDetail.do'}">
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_modify_SystemCntc(){
	var varForm				    = document.all["Form"];
	varForm.action              = "<c:url value='/ssi/syi/sim/updateSystemCntc.do'/>";
	varForm.cntcId.value		= "<c:out value='${result.cntcId}'/>";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_SystemCntc(){
	if (confirm("<spring:message code="common.delete.msg" />")) {
		var varForm				    = document.all["Form"];
		varForm.action			    = "<c:url value='/ssi/syi/sim/removeSystemCntc.do'/>";
		varForm.cntcId.value		= "<c:out value='${result.cntcId}'/>";
		varForm.submit();
	}
}
</c:if>


/* ********************************************************
* 목록 으로 가기
******************************************************** */
function fn_egov_list_SystemCntc(){
	location.href = "<c:url value='${listUri}'/>";
}
-->
</script>
</head>
<body>
<form name="Form" action="" method="post">
	<input name="cmd"     type="hidden">
	<input name="cntcId"  type="hidden">
	<input name="confmAt" type="hidden">
</form>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiSim.systemCntcDetail.pageTop.title"/></h2><!-- 시스템연계 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.cntcId"/> <span class="pilsu">*</span></th><!-- 연계ID -->
			<td class="left">
			    <c:out value="${result.cntcId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.cntcNm"/> <span class="pilsu">*</span></th><!-- 연계명 -->
			<td class="left">
			    <c:out value="${result.cntcNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.cntcType"/> <span class="pilsu">*</span></th><!-- 연계유형 -->
			<td class="left">
			    <c:out value="${result.cntcType}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.provdInsttId"/> <span class="pilsu">*</span></th><!-- 제공기관 -->
			<td class="left">
			    <select name="provdInsttId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectProvdInsttId"/>"><!-- 제공기관선택 -->
				<c:forEach var="resultList" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${resultList.insttId}"/>' <c:if test="${resultList.insttId == result.provdInsttId}">selected="selected"</c:if> ><c:out value="${resultList.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.provdSysId"/> <span class="pilsu">*</span></th><!-- 제공시스템 -->
			<td class="left">
			    <select name="provdSysId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectProvdSysId"/>"><!-- 제공시스템선택 -->
				<c:forEach var="resultList" items="${cntcProvdSystemList}" varStatus="status">
				<option value='<c:out value="${resultList.sysId}"/>' <c:if test="${resultList.sysId == result.provdSysId}">selected="selected"</c:if> ><c:out value="${resultList.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.provdSvcId"/> <span class="pilsu">*</span></th><!-- 제공서비스 -->
			<td class="left">
			    <select name="provdSvcId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectProvdSvcId"/>"><!-- 제공서비스선택 -->
				<c:forEach var="resultList" items="${cntcProvdServiceList}" varStatus="status">
				<option value='<c:out value="${resultList.svcId}"/>' <c:if test="${resultList.svcId == result.provdSvcId}">selected="selected"</c:if> ><c:out value="${resultList.svcNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.requstInsttId"/> <span class="pilsu">*</span></th><!-- 요청기관 -->
			<td class="left">
			    <select name="requstInsttId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectRequstInsttId"/>"><!-- 요청기관선택 -->
				<c:forEach var="resultList" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${resultList.insttId}"/>' <c:if test="${resultList.insttId == result.requstInsttId}">selected="selected"</c:if> ><c:out value="${resultList.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.requstSysId"/> <span class="pilsu">*</span></th><!-- 요청시스템 -->
			<td class="left">
			    <select name="requstSysId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectRequstSysId"/>"><!-- 요청시스템선택 -->
				<c:forEach var="resultList" items="${cntcRequstSystemList}" varStatus="status">
				<option value='<c:out value="${resultList.sysId}"/>' <c:if test="${resultList.sysId == result.requstSysId}">selected="selected"</c:if> ><c:out value="${resultList.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.validBeginDe"/> <span class="pilsu">*</span></th><!-- 유효시작일자 -->
			<td class="left">
			    <c:out value='${fn:substring(result.validBeginDe, 0,4)}'/>-<c:out value='${fn:substring(result.validBeginDe, 4,6)}'/>-<c:out value='${fn:substring(result.validBeginDe, 6,8)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.validEndDe"/> <span class="pilsu">*</span></th><!-- 유효종료일자 -->
			<td class="left">
			    <c:out value='${fn:substring(result.validEndDe, 0,4)}'/>-<c:out value='${fn:substring(result.validEndDe, 4,6)}'/>-<c:out value='${fn:substring(result.validEndDe, 6,8)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
			    <select name="useAt" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectUseAt"/>"><!-- 사용여부선택 -->
				<option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >Y</option>
				<option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >N</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcDetail.confmAt"/> <span class="pilsu">*</span></th><!-- 승인여부 -->
			<td class="left">
			    <select name="confmAt" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcDetail.selectConfmAt"/>"><!-- 승인여부선택 -->
				<option value="Y" <c:if test="${result.confmAt == 'Y'}">selected="selected"</c:if> >Y</option>
				<option value="N" <c:if test="${result.confmAt == 'N'}">selected="selected"</c:if> >N</option>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		
		<% /** * 업무사용자 처리 */ %>
		<c:if test="${confirmTF eq 'F'}">

			<% /** * 승인여부 처리 */ %>
			<c:if test="${result.confmAt eq 'N'}">
				<form name="formUpdt" action="<c:url value='/ssi/syi/sim/updateSystemCntc.do'/>" method="post" style="display:inline-block; vertical-align:top">
				<input class="s_submit" type="submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" />" onclick="fn_egov_modify_SystemCntc(); return false;"><!-- 수정 -->
				<input name="cntcId"        type="hidden" value="<c:out value='${result.cntcId}'/>">
				</form>
				
				<form name="formDelete" action="<c:url value='/ssi/syi/sim/removeSystemCntc.do'/>" method="post" style="display:inline-block; vertical-align:top">
				<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" onclick="fn_egov_delete_SystemCntc(); return false;"><!-- 삭제 -->
				<input name="cntcId"        type="hidden" value="<c:out value='${result.cntcId}'/>">
				</form>
			</c:if>
		</c:if>
		
		
		<% /** * 업무사용자 처리 */ %>
		<c:if test="${confirmTF eq 'T'}">

			<% /** * 승인여부 처리 */ %>
			<c:if test="${result.confmAt eq 'N'}">
				<form name="formConfirm" action="<c:url value='${selfUri}'/>" method="post" style="display:inline-block; vertical-align:top">
				<input class="s_submit" type="submit" value="<spring:message code="button.acknowledgment" />" title="<spring:message code="button.acknowledgment" />" onclick="fn_egov_confirm_SystemCntc('Y'); return false;"><!-- 승인 -->
				<input name="cntcId"  type="hidden" value="<c:out value='${result.cntcId}'/>">
				<input name="confmAt" type="hidden" value="Y">
				<input name="cmd"     type="hidden" value="Confirm">
				</form>
			</c:if>

			<c:if test="${result.confmAt eq 'Y'}">
				<form name="formConfirmCancel" action="<c:url value='${selfUri}'/>" method="post" style="display:inline-block; vertical-align:top">
				<input class="s_submit" type="submit" value="<spring:message code="button.cancelAcknowledgment" />" title="<spring:message code="button.cancelAcknowledgment" />" onclick="fn_egov_confirm_SystemCntc('N'); return false;"><!-- 승인취소 -->
				<input name="cntcId"  type="hidden" value="<c:out value='${result.cntcId}'/>">
				<input name="confmAt" type="hidden" value="N">
				<input name="cmd"     type="hidden" value="Confirm">
				</form>
			</c:if>
		</c:if>
		
		<form name="formList" action="<c:url value='${listUri}'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick="fn_egov_list_SystemCntc(); return false;"></span><!-- 목록 -->
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

</body>
</html>
