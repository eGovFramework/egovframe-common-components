<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcServiceRegist.jsp
  * @Description : EgovCntcServiceRegist 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2011.09.14   서준식              저장 버튼 CSS 수정
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comSsiSyiIis.cntcServiceRegist.title"/></title><!-- 연계서비스 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntcService" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CntcService(){
	location.href = "<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>";
}
/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_egov_regist_CntcService(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateCntcService(form)){
			return;
		}else{
			form.submit();
		}
	}
}
/* ********************************************************
* CodeList 가져오기
******************************************************** */
function fn_egov_get_CodeList(form,choose){
	form.cmd.value = "";

	if(choose == 'insttId') {
		form.sysId.value = "";
		form.svcId.value = "";
	} else
	if(choose == 'sysId') {
		form.svcId.value = "";
	}

	form.submit();
}

-->
</script>
</head>

<body>
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<!-- 상단타이틀 -->
<form:form commandName="cntcService" name="cntcService" method="post">
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
<form:hidden path="insttId"/>
<form:hidden path="sysId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIis.cntcServiceRegist.pageTop.title"/></h2><!-- 연계서비스 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcServiceRegist.insttId"/> <span class="pilsu">*</span></th><!-- 기관 -->
			<td class="left">
			    <select name="insttId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiIis.cntcServiceRegist.insttId"/>">
				<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == cntcService.insttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcServiceRegist.sysId"/> <span class="pilsu">*</span></th><!-- 시스템 -->
			<td class="left">
			    <select name="sysId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiIis.cntcServiceRegist.sysId"/>"><!-- 시스템선택 -->
				<c:forEach var="result" items="${cntcSystemList}" varStatus="status">
				<option value='<c:out value="${result.sysId}"/>' <c:if test="${result.sysId == cntcService.sysId}">selected="selected"</c:if> ><c:out value="${result.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcServiceRegist.svcId"/> <span class="pilsu">*</span></th><!-- 서비스ID -->
			<td class="left">
			    <form:input  path="svcId" maxlength="20" title="<spring:message code='comSsiSyiIis.cntcServiceRegist.svcId'/>" readonly="true" cssStyle="width:128px"/><!-- 서비스ID -->
      			<form:errors path="svcId"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcServiceRegist.svcNm"/> <span class="pilsu">*</span></th><!-- 서비스명 -->
			<td class="left">
			    <form:input  path="svcNm" size="60" maxlength="60" title="<spring:message code='comSsiSyiIis.cntcServiceRegist.svcNm'/>"/><!-- 서비스명 -->
      			<form:errors path="svcNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcServiceRegist.requestMessageId"/></th><!-- 요청메시지ID -->
			<td class="left">
			    <select name="requestMessageId" class="select" title="<spring:message code="comSsiSyiIis.cntcServiceRegist.requestMessageId"/>"><!-- 요청메시지ID선택 -->
				<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
				<option value='<c:out value="${result.cntcMessageId}"/>' <c:if test="${result.cntcMessageId == cntcService.requestMessageId}">selected="selected"</c:if> ><c:out value="${result.cntcMessageNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcServiceRegist.rspnsMessageId"/></th><!-- 응답메시지ID -->
			<td class="left">
			    <select name="rspnsMessageId" class="select" title="<spring:message code="comSsiSyiIis.cntcServiceRegist.rspnsMessageId"/>"><!-- 응답메시지ID선택 -->
				<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
				<option value='<c:out value="${result.cntcMessageId}"/>' <c:if test="${result.cntcMessageId == cntcService.rspnsMessageId}">selected="selected"</c:if> ><c:out value="${result.cntcMessageNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_CntcService(document.cntcService);return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>
</DIV>
</body>
</html>
