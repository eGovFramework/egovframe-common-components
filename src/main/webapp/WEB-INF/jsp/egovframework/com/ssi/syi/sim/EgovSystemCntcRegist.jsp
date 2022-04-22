<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovSystemCntcRegist.jsp
  * @Description : EgovSystemCntcRegist 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2018.09.10   신용호              표준프레임워크 v3.8 개선
  *   2019.12.13   신용호              등록 참고사항 안내문 추가
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
<title><spring:message code="comSsiSyiSim.systemCntcRegist.title"/></title><!-- 시스템연계 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="systemCntc" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--

function initCalendar(){
	$("#v_validBeginDe").datepicker(
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'both'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
	$("#v_validEndDe").datepicker(
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'both'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
	$("#v_validBeginDe").change(function() {
		$("#validBeginDe").val($(this).val().replace(/-/gi,""));
	});
	
	$("#v_validEndDe").change(function() {
		$("#validEndDe").val($(this).val().replace(/-/gi,""));
	});
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_SystemCntc(){
	location.href = "<c:url value='/ssi/syi/sim/getSystemCntcList.do'/>";
}
/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_egov_regist_SystemCntc(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		debugger;
		if(!validateSystemCntc(form)){
			return;
		}else{
		    var validBeginDe = Number(document.getElementById('validBeginDe').value);
		    var validEndDe   = Number(document.getElementById('validEndDe'  ).value);
		    if(validEndDe < validBeginDe){
		        alert("<spring:message code="comSsiSyiSim.systemCntcRegist.validate.checkValidDe"/>"); //유효시작일자는 유효종료일자 보다 클수 없습니다.
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

<body onload="initCalendar()">
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="systemCntc" name="systemCntc" method="post">
<input name="cmd"     type="hidden" value="<c:out value='Regist'/>"/>
<input name="confmAt" type="hidden" value="<c:out value='N'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiSim.systemCntcRegist.pageTop.title"/></h2><!-- 시스템연계 등록 -->
    <span>※ 등록 참고 사항<br> 
    	제공기관/요청기관 : "1240. 연계기관관리" 메뉴에서 사전 등록필요<br>
    	제공시스템/요청시스템 : "1240. 연계기관관리" 기관등록 → 기관상세보기에서 연계시스템 추가 등록가능<br>
    	제공서비스 : "1240. 연계기관관리" 기관등록  → 기관상세보기에서 연계시스템 추가 등록  → 연계서비스 추가 등록 가능<br></span>
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.cntcId"/> <span class="pilsu">*</span></th><!-- 연계ID -->
			<td class="left">
			    <form:input  path="cntcId" title="<spring:message code='comSsiSyiSim.systemCntcRegist.cntcId'/>" maxlength="20"  readonly="true" cssClass="readOnlyClass" style="width:128px"/><!-- 연계ID -->
      			<form:errors path="cntcId"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.cntcNm"/> <span class="pilsu">*</span></th><!-- 연계명 -->
			<td class="left">
			    <form:input  path="cntcNm" title="<spring:message code='comSsiSyiSim.systemCntcRegist.cntcNm'/>" size="60" maxlength="60"/><!-- 연계명 -->
      			<form:errors path="cntcNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.cntcType"/> <span class="pilsu">*</span></th><!-- 연계유형 -->
			<td class="left">
			    <form:input  path="cntcType" title="<spring:message code='comSsiSyiSim.systemCntcRegist.cntcType'/>" size="60" maxlength="60"/><!-- 연계유형 -->
      			<form:errors path="cntcType"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.provdInsttId"/> <span class="pilsu">*</span></th><!-- 제공기관 -->
			<td class="left">
			    <select name="provdInsttId" class="select" onChange="javascript:fn_egov_get_CodeList(document.systemCntc,'provdInsttId');" title="<spring:message code="comSsiSyiSim.systemCntcRegist.selectProvdInsttId"/>"><!-- 제공기관선택 -->
				<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == systemCntc.provdInsttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.provdSysId"/> <span class="pilsu">*</span></th><!-- 제공시스템 -->
			<td class="left">
			    <select name="provdSysId" class="select" onChange="javascript:fn_egov_get_CodeList(document.systemCntc,'provdSysId');" title="<spring:message code="comSsiSyiSim.systemCntcRegist.selectProvdSysId"/>"><!-- 제공시스템선택 -->
				<c:forEach var="result" items="${cntcProvdSystemList}" varStatus="status">
				<option value='<c:out value="${result.sysId}"/>' <c:if test="${result.sysId == systemCntc.provdSysId}">selected="selected"</c:if> ><c:out value="${result.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.provdSvcId"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="provdSvcId" class="select" title="<spring:message code="comSsiSyiSim.systemCntcRegist.selectProvdSvcId"/>"><!-- 제공서비스선택 -->
				<c:forEach var="result" items="${cntcProvdServiceList}" varStatus="status">
				<option value='<c:out value="${result.svcId}"/>' <c:if test="${result.svcId == systemCntc.provdSvcId}">selected="selected"</c:if> ><c:out value="${result.svcNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.requstInsttId"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="requstInsttId" class="select" onchange="fn_egov_get_CodeList(document.systemCntc,'requstInsttId');" title="<spring:message code="comSsiSyiSim.systemCntcRegist.selectRequstInsttId"/>"><!-- 요청기관선택 -->
				<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == systemCntc.requstInsttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.requstSysId"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="requstSysId" class="select" title="<spring:message code="comSsiSyiSim.systemCntcRegist.selectRequstSysId"/>"><!-- 요청시스템선택 -->
				<c:forEach var="result" items="${cntcRequstSystemList}" varStatus="status">
				<option value='<c:out value="${result.sysId}"/>' <c:if test="${result.sysId == systemCntc.requstSysId}">selected="selected"</c:if> ><c:out value="${result.sysNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.validBeginDe"/> <span class="pilsu">*</span></th>
			<td class="left">
				<form:hidden path="validBeginDe" />
				<input id="v_validBeginDe" name="v_validBeginDe" type="text" value=""  maxlength="10" readonly="readonly" class="readOnlyClass" title="<spring:message code="comSsiSyiSim.systemCntcRegist.validBeginDe"/>" style="width:70px"/><!-- 시작일자입력 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.validEndDe"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:hidden path="validEndDe" />
				<input id="v_validEndDe" name="v_validEndDe" type="text" value=""  maxlength="10" readonly="readonly" title="<spring:message code="comSsiSyiSim.systemCntcRegist.validEndDe"/>" style="width:70px"/><!-- 종료일자입력 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiSim.systemCntcRegist.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
			    <form:select path="useAt">
				<form:option value="Y" label="Y"/>
				<form:option value="N" label="N"/>
				</form:select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_regist_SystemCntc(document.systemCntc); return false;" title='<spring:message code="button.save" />' /><!-- 저장 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_SystemCntc(); return false;" title='<spring:message code="button.list" />' /><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</DIV>

</body>
</html>
