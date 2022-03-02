<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovSystemCntcUpdt.jsp
  * @Description : EgovSystemCntcUpdt 화면
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comSsiSyiSim.systemCntcUpdt.title"/></title><!-- 시스템연계 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="systemCntc" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_SystemCntc(){
	location.href = "<c:url value='/ssi/syi/sim/getSystemCntcList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_SystemCntc(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateSystemCntc(form)){
			return;
		}else{
		    var validBeginDe = Number(document.getElementById('validBeginDe').value);
		    var validEndDe   = Number(document.getElementById('validEndDe'  ).value);
		    if(validEndDe < validBeginDe){
		        alert("<spring:message code="comSsiSyiSim.systemCntcUpdt.validate.checkValidDe"/>"); //유효시작일자는 유효종료일자 보다 클수 없습니다.
		        return;
		    }
			form.submit();
		}
	}
}
/* ********************************************************
* CodeList 가져오기
******************************************************** */
function fn_egov_get_CodeList(form,choose){
	form.cmd.value = "";

	if(choose == 'provdInsttId') {
		form.provdSysId.value = "";
		form.provdSvcId.value = "";
	} else
	if(choose == 'provdSysId') {
		form.provdSvcId.value = "";
	} else
	if(choose == 'requstInsttId') {
		form.requstSysId.value = "";
	}

	form.submit();
}
-->
</script>
</head>

<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="systemCntc" name="systemCntc" method="post">
<input     name="cmd" type="hidden" value="Modify">
<form:hidden path="cntcId"/>
<form:hidden path="provdInsttId"/>
<form:hidden path="provdSysId"/>
<form:hidden path="provdSvcId"/>
<form:hidden path="requstInsttId"/>
<form:hidden path="requstSysId"/>
<form:hidden path="confmAt"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiSim.systemCntcUpdt.pageTop.title"/></h2><!-- 시스템연계 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.cntcId"/> <span class="pilsu">*</span></th><!-- 연계ID -->
			<td class="left">
			    <c:out value="${systemCntc.cntcId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.cntcNm"/> <span class="pilsu">*</span></th><!-- 연계명 -->
			<td class="left">
			    <form:input  path="cntcNm" size="60" maxlength="60" title="<spring:message code='comSsiSyiSim.systemCntcUpdt.cntcNm'/>" /><!-- 연계명 -->
      			<form:errors path="cntcNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.cntcType"/> <span class="pilsu">*</span></th><!-- 연계유형 -->
			<td class="left">
			    <form:input  path="cntcType" size="60" maxlength="60" title="<spring:message code='comSsiSyiSim.systemCntcUpdt.cntcType'/>"/>
      			<form:errors path="cntcType"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.provdInsttId"/> <span class="pilsu">*</span></th><!-- 제공기관 -->
			<td class="left">
			    <select name="provdInsttId" class="select" onchange="fn_egov_get_CodeList(document.systemCntc,'provdInsttId');" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.selectProvdInsttId"/>"><!-- 제공기관선택 -->
				<option value=""></option>
				<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == systemCntc.provdInsttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.provdSysId"/> <span class="pilsu">*</span></th><!-- 제공시스템 -->
			<td class="left">
			    <select name="provdSysId" class="select" onchange="fn_egov_get_CodeList(document.systemCntc,'provdSysId');" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.selectProvdSysId"/>"><!-- 제공시스템선택 -->
				<option value=""></option>
				<c:forEach var="result" items="${cntcProvdSystemList}" varStatus="status">
				<option value='<c:out value="${result.sysId}"/>' <c:if test="${result.sysId == systemCntc.provdSysId}">selected="selected"</c:if> ><c:out value="${result.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.provdSvcId"/> <span class="pilsu">*</span></th><!-- 제공서비스 -->
			<td class="left">
			    <select name="provdSvcId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.selectProvdSvcId"/>"><!-- 제공서비스선택 -->
				<option value=""></option>
				<c:forEach var="result" items="${cntcProvdServiceList}" varStatus="status">
				<option value='<c:out value="${result.svcId}"/>' <c:if test="${result.svcId == systemCntc.provdSvcId}">selected="selected"</c:if> ><c:out value="${result.svcNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.requstInsttId"/> <span class="pilsu">*</span></th><!-- 요청기관 -->
			<td class="left">
			    <select name="requstInsttId" class="select" onchange="fn_egov_get_CodeList(document.systemCntc,'requstInsttId');" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.selectRequstInsttId"/>"><!-- 요청기관선택 -->
				<option value=""></option>
				<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == systemCntc.requstInsttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.requstSysId"/> <span class="pilsu">*</span></th><!-- 요청시스템 -->
			<td class="left">
			    <select name="requstSysId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.selectRequstSysId"/>"><!-- 요청시스템선택 -->
				<option value=""></option>
				<c:forEach var="result" items="${cntcRequstSystemList}" varStatus="status">
				<option value='<c:out value="${result.sysId}"/>' <c:if test="${result.sysId == systemCntc.requstSysId}">selected="selected"</c:if> ><c:out value="${result.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.validBeginDe"/> <span class="pilsu">*</span></th><!-- 유효시작일자 -->
			<td class="left">
				<form:hidden path="validBeginDe" />
				<input id="v_validBeginDe" name="v_validBeginDe" type="text" readonly="readonly" class="readOnlyClass" value="<c:out value='${fn:substring(systemCntc.validBeginDe, 0,4)}'/>-<c:out value='${fn:substring(systemCntc.validBeginDe, 4,6)}'/>-<c:out value='${fn:substring(systemCntc.validBeginDe, 6,8)}'/>"  maxlength="10" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.validBeginDe"/>" style="width:70px"/><!-- 시작일자입력 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.validEndDe"/> <span class="pilsu">*</span></th><!-- 유효종료일자 -->
			<td class="left">
			    <form:hidden path="validEndDe" />
				<input id="v_validEndDe" name="v_validEndDe" type="text" readonly="readonly" class="readOnlyClass" value="<c:out value='${fn:substring(systemCntc.validEndDe, 0,4)}'/>-<c:out value='${fn:substring(systemCntc.validEndDe, 4,6)}'/>-<c:out value='${fn:substring(systemCntc.validEndDe, 6,8)}'/>"  maxlength="10" title="<spring:message code="comSsiSyiSim.systemCntcUpdt.validEndDe"/>" style="width:70px"/><!-- 종료일자입력 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcUpdt.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
			    <form:select path="useAt" title="<spring:message code='comSsiSyiSim.systemCntcUpdt.selectUseAt'/>"><!-- 사용여부 -->
				<form:option value="Y" label="Y"/>
				<form:option value="N" label="N"/>
				</form:select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<% /** * 미승인 시에만 저장기능 제공 */ %>
		<c:if test="${systemCntc.confmAt eq 'N'}">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick="fn_egov_modify_SystemCntc(document.systemCntc); return false;"><!-- 저장 -->
		</c:if>
		<input class="s_submit" type="button" value="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick="fn_egov_list_SystemCntc(); return false;"><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
