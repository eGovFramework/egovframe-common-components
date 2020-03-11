<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : EgovSmsInfoRegist.jsp
 * @Description : 문자메시지 등록화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.06.18   한성곤          최초 생성
 * @ 2018.09.14   이정은          공통컴포넌트 3.8 개선
 *
 *  @author 공통컴포넌트개발팀 한성곤
 *  @since 2009.06.18
 *  @version 1.0 
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="cop.sms.textMassageRegist"/></title><!-- 문자메시지 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="sms" staticJavascript="false" xhtml="true" cdata="false"/>

<c:choose>
<c:when test="${not empty msg}">
<script type="text/javascript">
function loading() {
	alert("<c:out value='${msg}'/>");
}
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
function loading() {
	// no-op
}
</script>
</c:otherwise>
</c:choose>

<script type="text/javascript">	
	function fn_egov_regist_sms() {
		if (!validateSms(document.sms)){
			return;
		}

		var checked = false;
		for (var i = 0; i < document.sms.recptnTelno.length; i++) {
			if (document.sms.recptnTelno[i].value != '') {
				checked = true;
				break;
			}
		}

		if (!checked) {
			alert('<spring:message code="cop.sms.recptnTelno.msg" />');
			document.sms.recptnTelno[0].focus();
			return;
		}
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			form = document.sms;
			form.action = "<c:url value='/cop/sms/insertSms.do'/>";
			
			form.submit();					
		}
	}
	
	function fn_egov_select_sms() {
		form = document.sms;
		form.action = "<c:url value='/cop/sms/selectSmsList.do'/>";
		form.submit();	
	}
	
</script>
</head>
<body onload="loading();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="sms" name="sms" method="post" action="${pageContext.request.contextPath}/cop/sms/insertSms.do' />">

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="cop.sms.textMassageRegist"/></h2><!-- 문자메시지 전송 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="cop.sms.trnsmitTelno" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="trnsmitTelno" size="14" maxlength="14" cssStyle="width:100%" />
				<br /><form:errors path="trnsmitTelno" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.sms.trnsmitCn" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:textarea path="trnsmitCn" cols="75" rows="2" cssStyle="width:100%" />
				<br /><form:errors path="trnsmitCn" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.sms.recptnTelno" /> <span class="pilsu">*</span></th>
			<td class="left">
			    1 : <form:input id="recptnTelno" path="recptnTelno" maxlength="14" cssStyle="width:100px; margin-bottom:2px"/><br>
	   	  		<c:forEach begin="2" end="5" step="1" var="index">
	   	  		<c:out value='${index}'/> : <form:input id="recptnTelno${index-1}" path="recptnTelno" maxlength="14" cssStyle="width:100px; margin-bottom:2px" /><br>
	   	  		</c:forEach>
	   	  		<br /><form:errors path="recptnTelno" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="cop.sms.send" />' onclick="fn_egov_regist_sms(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/sms/selectSmsList.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="fn_egov_select_sms(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
