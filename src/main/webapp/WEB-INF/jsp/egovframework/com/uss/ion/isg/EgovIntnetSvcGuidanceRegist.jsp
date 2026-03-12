<%--
/**
 * @Class Name  : EgovIntnetSvcGuidanceRegist.jsp
 * @Description : EgovIntnetSvcGuidanceRegist.jsp
 * @Modification Information
 * @
 * @ 수정일                수정자           수정내용
 * @ ----------   --------   ---------------------------
 * @ 2009.02.01   lee.m.j    최초 생성
 * @ 2018.08.20   이정은            공통컴포넌트 3.8 개선
 * @ 2019.12.10   신용호            KISA 보안약점 조치 (HTMLArea Editor삭제)
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
<title><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegist" /></title>
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
 * 목록
 ******************************************************** */
function fncSelectIntnetSvcGuidanceList() {
    var varFrom = document.getElementById("intnetSvcGuidanceVO");
    varFrom.action = "<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>";
    varFrom.submit();
}
/* ********************************************************
 * 저장
 ******************************************************** */
function fncIntnetSvcGuidanceInsert(form){
	//input item Client-Side validate
	if (!validateIntnetSvcGuidance(form)) {	
		return;
	}
	
	if(confirm('<spring:message code="common.save.msg" />')){
		form.submit();
	}
}

</script>

</head>

<body>

<%-- noscript 태그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다 -->

<form:form modelAttribute="intnetSvcGuidanceVO" name="intnetSvcGuidanceVO" method="post" action="${pageContext.request.contextPath}/uss/ion/isg/addIntnetSvcGuidance.do" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegist"/></h2><!-- 인터넷서비스안내 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tbody>
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="titleId"><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceId" /></c:set>
		<c:set var="titleNm"><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceNm" /></c:set>
		<c:set var="titleDc"><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceDc" /></c:set>
		<c:set var="titleReflctAt"><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceReflctAt" /></c:set>
		<c:set var="titleRegDate"><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegDate" /></c:set>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceNm" /> <span class="pilsu">*</span></th><!-- 인터넷서비스명 -->
			<td class="left">
			    <form:input path="intnetSvcNm" title="${titleNm} ${inputTxt}" maxlength="20" />
			    <div><form:errors path="intnetSvcNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceDc" /><span class="pilsu">*</span></th><!-- 인터넷서비스설명 -->
			<td class="left">
			    <form:textarea path="intnetSvcDc" title="${titleDc} ${inputTxt}" cssClass="textarea" rows="15" cols="80" cssStyle="height:356px" />
			    <div><form:errors path="intnetSvcDc" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceReflctAt" /><span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
			    <form:select path="reflctAt" title="${titleReflctAt} ${inputTxt}">
		          <form:option value="Y">Y</form:option>
		          <form:option value="N">N</form:option>
		      </form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegDate" /><span class="pilsu">*</span></th><!-- 등록일시 -->
			<td class="left">
			    <form:input path="frstRegisterPnttm" id="frstRegisterPnttm" title="${titleRegDate} ${inputTxt}" maxlength="10" readonly="true" style="width:70px;"/>
			    <div><form:errors path="frstRegisterPnttm" cssClass="error" /></div>
			</td>
		</tr>
		</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncIntnetSvcGuidanceInsert(document.forms[0]); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>?pageIndex=<c:out value='${intnetSvcGuidanceVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${intnetSvcGuidanceVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectIntnetSvcGuidanceList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${intnetSvcGuidanceVO.pageIndex}'/>"/>
<input name="searchCondition" type="hidden" value="<c:out value='${intnetSvcGuidanceVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${intnetSvcGuidanceVO.searchKeyword}'/>"/>
</form:form>

</body>
</html>

