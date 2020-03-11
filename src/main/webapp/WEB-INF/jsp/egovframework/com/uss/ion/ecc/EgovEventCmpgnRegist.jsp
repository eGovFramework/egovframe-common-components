<%
 /**
  * @Class Name : EgovEventCmpgnRegist.jsp
  * @Description : EgovEventCmpgnRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssIonEcc.eventCmpgnVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="eventCmpgnVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

$(function() {
	$("#eventConfmDe").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'   
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
	$("#eventSvcBeginDe").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'   
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
	$("#eventSvcEndDe").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'   
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
});

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	// 첫 입력란에 포커스
	document.getElementById("eventCmpgnVO").eventCn.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_event(form){
	//input item Client-Side validate
	if (!validateEventCmpgnVO(form)) {	
		return false;
	} else {
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}
</script>

</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="eventCmpgnVO" action="${pageContext.request.contextPath}/uss/ion/ecc/insertEventCmpgn.do" method="post" onSubmit="fn_egov_regist_event(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		
		<!-- 행사유형 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.eventTyCode"/> </c:set>
		<tr>
			<th><label for="eventTyCode">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="eventTyCode" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:options items="${eventTyCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
				<div><form:errors path="eventTyCode" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 행사내용 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.eventCn"/> </c:set>
		<tr>
			<th><label for="eventCn">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="eventCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="eventCn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 행사시작일자  -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.eventSvcBeginDe"/> </c:set>
		<tr>
			<th><label for="eventSvcBeginDe">${title} <span class="pilsu">*</span></label></th>
			<td class="left" colspan="3">
				<form:input path="eventSvcBeginDe" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:70px;"/>
				<div><form:errors path="eventSvcBeginDe" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 행사종료일자  -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.eventSvcEndDe"/> </c:set>
		<tr>
			<th><label for="eventSvcEndDe">${title} <span class="pilsu">*</span></label></th>
			<td class="left" colspan="3">
				<form:input path="eventSvcEndDe" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:70px;"/>
				<div><form:errors path="eventSvcEndDe" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 서비스이용인원수 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.svcUseNmprCo"/> </c:set>
		<tr>
			<th><label for="svcUseNmprCo">${title} </label></th>
			<td class="left">
			    <form:input path="svcUseNmprCo" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="svcUseNmprCo" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 담당자명 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.chargerNm"/> </c:set>
		<tr>
			<th><label for="chargerNm">${title} </label></th>
			<td class="left">
			    <form:input path="chargerNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="chargerNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 준비물내용 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.prparetgCn"/> </c:set>
		<tr>
			<th><label for="prparetgCn">${title } </label></th>
			<td class="nopd">
				<form:textarea path="prparetgCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="prparetgCn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 승인여부 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.eventConfmAt"/> </c:set>
		<tr>
			<th><label for="eventConfmAt">${title } </label></th>
			<td class="left">
				<form:select path="eventConfmAt" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:option value="Y"  label="예" />
	  		   		<form:option value='N'>아니오</form:option>
				</form:select>
				<div><form:errors path="eventConfmAt" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 승인일자  -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.eventConfmDe"/> </c:set>
		<tr>
			<th><label for="eventConfmDe">${title} <span class="pilsu">*</span></label></th>
			<td class="left" colspan="3">
				<form:input path="eventConfmDe" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:70px;"/>
				<div><form:errors path="eventConfmDe" cssClass="error" /></div>       
			</td>
		</tr>
		
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ecc/selectEventCmpgnList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

</body>
</html>
