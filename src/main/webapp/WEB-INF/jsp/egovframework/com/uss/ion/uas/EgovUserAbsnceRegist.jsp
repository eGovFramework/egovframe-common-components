<%--
/**
 * @Class Name  : EgovUserAbsnceRegist.java
 * @Description : EgovUserAbsnceRegist jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 * @ 2018.09.10        이정은             		공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonUas.userAbsnceRegist.userAbsnceRegist"/></title><!-- 사용자부재 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value="/css/egovframework/com/cmm/jqueryui.css" />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jqueryui.js" />"></script>
<script type="text/javascript">
$(function() {
	$("#frstRegisterPnttm").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value="/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif"/>'   
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
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_userAbsnce(form){
	//input item Client-Side validate
	/*if (!validateUserAbsnceVO(form)) {	
		return;
	}*/

	if(confirm('<spring:message code="common.regist.msg" />')){	
		form.submit();
	}
}
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
<form:form modelAttribute="userAbsnceVO" action="${pageContext.request.contextPath}/uss/ion/uas/addUserAbsnce.do" method="post" onSubmit="fn_egov_regist_userAbsnce(document.forms[0]); return false;"> 

	<!-- 타이틀 -->
	<h2><spring:message code="ussIonUas.userAbsnceRegist.userAbsnceRegist"/></h2><!-- 사용자부재 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="width:84%" />
		</colgroup>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 사용자ID -->
		<c:set var="title"><spring:message code="ussIonUas.userAbsnceRegist.userId"/></c:set>
		<tr>
			<th><label for="userId">${title} <span class="pilsu">*</span></label></th><!-- 사용자ID -->
			<td class="left">
			    <form:input path="userId" id="userId" title="${title} ${inputTxt}" class="readOnlyClass" readonly="true" style="width:128px" />
			    <div><form:errors path="userId" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사용자명 -->
		<c:set var="title"><spring:message code="ussIonUas.userAbsnceRegist.userNm"/></c:set>
		<tr>
			<th><label for="userNm">${title} <span class="pilsu">*</span></label></th><!-- 사용자명 -->
			<td class="left">
			    <form:input path="userNm" id="userNm" title="${title} ${inputTxt}" class="readOnlyClass" readonly="true" style="width:128px" />
			    <div><form:errors path="userNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 부재여부 -->
		<c:set var="title"><spring:message code="ussIonUas.userAbsnceRegist.userAbsnceAt"/></c:set>
		<tr>
			<th><label for="userAbsnceAt">${title} <span class="pilsu">*</span></label></th><!-- 부재여부 -->
			<td class="left">
			    <form:select path="userAbsnceAt" id="userAbsnceAt" title="${title} ${inputTxt}">
				<form:option value="Y">Y</form:option>
				<form:option value="N">N</form:option>
				</form:select>
			    <div><form:errors path="userAbsnceAt" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 등록일시 -->
		<c:set var="title"><spring:message code="ussIonUas.userAbsnceRegist.frstRegisterPnttm"/></c:set>
		<tr>
			<th><label for="frstRegisterPnttm">${title} <span class="pilsu">*</span></label></th><!-- 등록일시 -->
			<td class="left">
			    <form:input path="frstRegisterPnttm" id="frstRegisterPnttm" title="${title} ${inputTxt}" maxlength="10" readonly="true" style="width:70px;"/>
			    <div><form:errors path="frstRegisterPnttm" cssClass="error" /></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/uas/selectUserAbsnceList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${userAbsnceVO.pageIndex}'/>"/>
<input name="searchCondition" type="hidden" value="<c:out value='${userAbsnceVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${userAbsnceVO.searchKeyword}'/>"/>
<input name="selAbsnceAt" type="hidden" value="<c:out value='${userAbsnceVO.selAbsnceAt}'/>"/>
</form:form>

</body>
</html>

