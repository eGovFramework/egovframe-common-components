<%
 /**
  * @Class Name : EgovTnextrlHrRegist.jsp
  * @Description : EgovTnextrlHrRegist 화면
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
<c:set var="pageTitle"><spring:message code="comUssIonEcc.tnextrlHrVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/popup.js'/>" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="tnextrlHrVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
$(function() {
	$("#brth").datepicker(   
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
	document.getElementById("tnextrlHrVO").extrlHrNm.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_hr(form){
	//input item Client-Side validate
	if (!validateTnextrlHrVO(form)) {	
		return false;
	} else {
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}

/* ********************************************************
 * 행사/이벤트/캠페인 팝업열기
 ******************************************************** */
 function fn_egov_popup_event(){

	fn_egov_popup("eventPopup","<c:url value='/uss/ion/ecc/selectEventCmpgnListPopup.do' />",800,580);
	
 }
</script>

</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="tnextrlHrVO" action="${pageContext.request.contextPath}/uss/ion/ecc/insertTnextrlHr.do" method="post" onSubmit="fn_egov_regist_hr(document.forms[0]); return false;"> 
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
		
		<!-- 행사/이벤트/캠페인 정보 -->
		<c:set var="title"><spring:message code="comUssIonEcc.eventCmpgnVO.title"/> </c:set>
		<tr>
			<th><label for="eventCn">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:textarea path="eventCn" title="${title} ${inputTxt}" cols="300" rows="20" readonly="true" style="width:94%;"/>   
			    <a href="#" onClick="fn_egov_popup_event()"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" style="border:0px" alt=<spring:message code="comUssIonEcc.eventCmpgnVO.findEv"/> title="<spring:message code="comUssIonEcc.eventCmpgnVO.findEv"/>"></a>
			    <div style="display:none"><form:input path="eventId" /></div>
   				<div><form:errors path="eventCn" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 성별 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.sexdstnCode"/> </c:set>
		<tr>
			<th><label for="sexdstnCode">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="sexdstnCode" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:options items="${sexdstnCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
				<div><form:errors path="sexdstnCode" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 외부인사명 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.extrlHrNm"/> </c:set>
		<tr>
			<th><label for="extrlHrNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="extrlHrNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="extrlHrNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 생년월일 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.brth"/> </c:set>
		<tr>
			<th><label for="brth">${title} <span class="pilsu">*</span></label></th>
			<td class="left" colspan="3">
				<form:input path="brth" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:70px;"/>
				<div><form:errors path="brth" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 직업유형 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.occpTyCode"/> </c:set>
		<tr>
			<th><label for="occpTyCode">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="occpTyCode" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:options items="${occpTyCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
				<div><form:errors path="occpTyCode" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 소속기관 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.psitnInsttNm"/> </c:set>
		<tr>
			<th><label for="psitnInsttNm">${title} </label></th>
			<td class="left">
			    <form:input path="psitnInsttNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="psitnInsttNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 연락처 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.telNo"/> </c:set>
		<tr>
			<th><label for="areaNo">${title} <span class="pilsu">*</span></label></th>
			<td class="left" colspan="3">
			    <form:input path="areaNo" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:30px;"/>&nbsp;-&nbsp;
			    <form:input path="middleTelno" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:30px;"/>&nbsp;-&nbsp;
			    <form:input path="endTelno" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:30px;"/>
   				<div><form:errors path="areaNo" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 이메일주소 -->
		<c:set var="title"><spring:message code="comUssIonEcc.tnextrlHrVO.emailAdres"/> </c:set>
		<tr>
			<th><label for="emailAdres">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="emailAdres" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="emailAdres" cssClass="error" /></div>     
			</td>
		</tr>
		
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ecc/selectTnextrlHrList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

</body>
</html>
