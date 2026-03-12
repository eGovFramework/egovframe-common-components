<%
/**
 * @Class Name : EgovMtgPlaceResveRegist.java
 * @Description : EgovMtgPlaceResveRegist.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용                최초 생성
 * @ 2018.08.21    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.12    최 두 영           다국어처리 & showModalDialog.js 오류 개선 & datepicker 적용
 *
 *  @author 이      용
 *  @since 2010.06.29
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonMtg.mtgPlaceResveRegist.title" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">
	function initCalendar(){
		$("#resveDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
		         , changeMonth: true
		         , changeYear: true
		         , showButtonPanel: true
		});
	}
	$(function(){
		initCalendar();
		if (typeof applyMtgPlaceResveOpenTimeFilter === 'function') {
			applyMtgPlaceResveOpenTimeFilter(document.getElementById('mtgPlaceResveVO') || document.forms['mtgPlaceResveVO']);
		}
	});

	function fncSelectMtgPlaceResveManageList() {
	    var varFrom = document.getElementById("mtgPlaceResveVO") || document.forms["mtgPlaceResveVO"];
	    if (varFrom) { varFrom.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>"; varFrom.submit(); }
	}
	
	function fncSaveMtgPlaceResve() {
	     var varFrom = document.getElementById("mtgPlaceResveVO") || document.forms["mtgPlaceResveVO"];
	     if (!varFrom) return;
	     if (!validateMtgPlaceResve(varFrom)) return;
	     if(confirm("<spring:message code="common.save.msg" />")){
		     varFrom.action = "<c:url value='/uss/ion/mtg/insertMtgPlaceResve.do'/>";
		     varFrom.submit();
	     }
	}
	
	function fn_egov_dplact_ceck(){
		var varFrom = document.getElementById("mtgPlaceResveVO") || document.forms["mtgPlaceResveVO"];
		if (!varFrom) return;
		var beginTm = varFrom.resveBeginTm.value;
		var endTm   = varFrom.resveEndTm.value;
		if((endTm-beginTm) > 0){
			var arrParam = new Array(1);
			arrParam[0] = window;
		    sTempValue = "sTmResveDe="+varFrom.resveDe.value+"&sTmResveBeginTm="+varFrom.resveBeginTm.value+"&sTmResveEndTm="+varFrom.resveEndTm.value+"&sTmMtgPlaceId="+varFrom.mtgPlaceId.value+"&sTmResveId=";
		 	window.showModalDialog("<c:url value='/uss/ion/mtg/mtgPlaceResveDplactCeck.do'/>?"+sTempValue, arrParam,"dialogWidth=450px;dialogHeight=150px;resizable=yes;center=yes");
	   }
	   else alert("<spring:message code="comUssIonMtg.mtgPlaceResveUpdt.timeCheck" />");
	}
	
	function fn_mtgPlace_image(){
		var varFrom = document.getElementById("mtgPlaceResveVO") || document.forms["mtgPlaceResveVO"];
		if (!varFrom) return;
		var arrParam = new Array(1);
		arrParam[0] = window;
	    sTempValue = "sTmMtgPlaceId="+varFrom.mtgPlaceId.value;
	 	window.showModalDialog("<c:url value='/uss/ion/mtg/selectMtgPlaceImage.do'/>?"+sTempValue, arrParam,"dialogWidth=720px;dialogHeight=400px;resizable=yes;center=yes");
	}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="mtgPlaceResveVO" name="mtgPlaceResveVO" id="mtgPlaceResveVO" method="post" action="${pageContext.request.contextPath}/uss/ion/mtg/insertMtgPlaceResve.do">
<input type="hidden" name="cmd" value="insert" >
<input type="hidden" name="dplactCeck" id="dplactCeck">
<input type="hidden" name="mtgPlaceId" value ="<c:out value='${mtgPlaceManageVO.mtgPlaceId}'/>">
<input type="hidden" name="opnBeginTm" value="<c:out value='${mtgPlaceManageVO.opnBeginTm}'/>">
<input type="hidden" name="opnEndTm" value="<c:out value='${mtgPlaceManageVO.opnEndTm}'/>">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonMtg.mtgPlaceResveRegist.title" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgSj" /> <span class="pilsu">*</span></th><!-- 제목 -->
			<td class="left">
			    <form:input path="mtgSj" maxlength="70" title='<spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgSj" />' />
			    <div><form:errors path="mtgSj" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /> <span class="pilsu">*</span></th><!-- 회의실명 -->
			<td class="left">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.location" /> <span class="pilsu">*</span></th><!-- 회의실 위치 -->
			<td class="left">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceTemp3}'/> <c:out value='${mtgPlaceManageVO.lcDetail}'/>
			    <c:if test="${!empty mtgPlaceManageVO.atchFileId}">
			    	<input class="btn01" type="button" value="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.image" />" onclick="fn_mtgPlace_image(); return false;" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.newWindow" />" style="margin-left:5px; vertical-align:0 !important" /><!-- 회의실 이미지 --><!-- 새창으로 -->
			    </c:if>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.reserver" /> <span class="pilsu">*</span></th><!-- 예약자 -->
			<td class="left">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceTemp4}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveTime" /> <span class="pilsu">*</span></th><!-- 예약시간 -->
			<td class="left">
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
				<form:input path="resveDe" id="resveDe" title='<spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveDe" />' maxlength="10" readonly="true" style="width:70px;"/><!-- 예약일자 -->
				<div><form:errors path="resveDe" cssClass="error" /></div>
				<select name="resveBeginTm" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveBeginTm" />"><!-- 예약시작시간 (유효성 오류 시 mtgPlaceResveVO 값 유지) -->
					<option value="800" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceResveVO.resveBeginTm == '800' or mtgPlaceResveVO.resveBeginTm == '0800')) or (empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceManageVO.resveBeginTm == '800' or mtgPlaceManageVO.resveBeginTm == '0800'))}"> selected </c:if>>08:00</option>
					<option value="830" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceResveVO.resveBeginTm == '830' or mtgPlaceResveVO.resveBeginTm == '0830')) or (empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceManageVO.resveBeginTm == '830' or mtgPlaceManageVO.resveBeginTm == '0830'))}"> selected </c:if>>08:30</option>
					<option value="900" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceResveVO.resveBeginTm == '900' or mtgPlaceResveVO.resveBeginTm == '0900')) or (empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceManageVO.resveBeginTm == '900' or mtgPlaceManageVO.resveBeginTm == '0900'))}"> selected </c:if>>09:00</option>
					<option value="930" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceResveVO.resveBeginTm == '930' or mtgPlaceResveVO.resveBeginTm == '0930')) or (empty mtgPlaceResveVO.resveBeginTm and (mtgPlaceManageVO.resveBeginTm == '930' or mtgPlaceManageVO.resveBeginTm == '0930'))}"> selected </c:if>>09:30</option>
					<option value="1000" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1000') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1000')}"> selected </c:if>>10:00</option>
					<option value="1030" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1030') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1030')}"> selected </c:if>>10:30</option>
					<option value="1100" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1100') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1100')}"> selected </c:if>>11:00</option>
					<option value="1130" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1130') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1130')}"> selected </c:if>>11:30</option>
					<option value="1200" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1200') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1200')}"> selected </c:if>>12:00</option>
					<option value="1230" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1230') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1230')}"> selected </c:if>>12:30</option>
					<option value="1300" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1300') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1300')}"> selected </c:if>>13:00</option>
					<option value="1330" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1330') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1330')}"> selected </c:if>>13:30</option>
					<option value="1400" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1400') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1400')}"> selected </c:if>>14:00</option>
					<option value="1430" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1430') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1430')}"> selected </c:if>>14:30</option>
					<option value="1500" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1500') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1500')}"> selected </c:if>>15:00</option>
					<option value="1530" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1530') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1530')}"> selected </c:if>>15:30</option>
					<option value="1600" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1600') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1600')}"> selected </c:if>>16:00</option>
					<option value="1630" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1630') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1630')}"> selected </c:if>>16:30</option>
					<option value="1700" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1700') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1700')}"> selected </c:if>>17:00</option>
					<option value="1730" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1730') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1730')}"> selected </c:if>>17:30</option>
					<option value="1800" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1800') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1800')}"> selected </c:if>>18:00</option>
					<option value="1830" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1830') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1830')}"> selected </c:if>>18:30</option>
					<option value="1900" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1900') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1900')}"> selected </c:if>>19:00</option>
					<option value="1930" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '1930') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '1930')}"> selected </c:if>>19:30</option>
					<option value="2000" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '2000') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '2000')}"> selected </c:if>>20:00</option>
					<option value="2030" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '2030') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '2030')}"> selected </c:if>>20:30</option>
					<option value="2100" <c:if test="${(not empty mtgPlaceResveVO.resveBeginTm and mtgPlaceResveVO.resveBeginTm == '2100') or (empty mtgPlaceResveVO.resveBeginTm and mtgPlaceManageVO.resveBeginTm == '2100')}"> selected </c:if>>21:00</option>
				</select>
				~
				<select name="resveEndTm" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveEndTm" />"><!-- 예약종료시간 (유효성 오류 시 mtgPlaceResveVO 값 유지) -->
					<option value="800" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and (mtgPlaceResveVO.resveEndTm == '800' or mtgPlaceResveVO.resveEndTm == '0800')) or (empty mtgPlaceResveVO.resveEndTm and (mtgPlaceManageVO.resveEndTm == '800' or mtgPlaceManageVO.resveEndTm == '0800'))}"> selected </c:if>>08:00</option>
					<option value="830" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and (mtgPlaceResveVO.resveEndTm == '830' or mtgPlaceResveVO.resveEndTm == '0830')) or (empty mtgPlaceResveVO.resveEndTm and (mtgPlaceManageVO.resveEndTm == '830' or mtgPlaceManageVO.resveEndTm == '0830'))}"> selected </c:if>>08:30</option>
					<option value="900" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and (mtgPlaceResveVO.resveEndTm == '900' or mtgPlaceResveVO.resveEndTm == '0900')) or (empty mtgPlaceResveVO.resveEndTm and (mtgPlaceManageVO.resveEndTm == '900' or mtgPlaceManageVO.resveEndTm == '0900'))}"> selected </c:if>>09:00</option>
					<option value="930" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and (mtgPlaceResveVO.resveEndTm == '930' or mtgPlaceResveVO.resveEndTm == '0930')) or (empty mtgPlaceResveVO.resveEndTm and (mtgPlaceManageVO.resveEndTm == '930' or mtgPlaceManageVO.resveEndTm == '0930'))}"> selected </c:if>>09:30</option>
					<option value="1000" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1000') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1000')}"> selected </c:if>>10:00</option>
					<option value="1030" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1030') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1030')}"> selected </c:if>>10:30</option>
					<option value="1100" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1100') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1100')}"> selected </c:if>>11:00</option>
					<option value="1130" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1130') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1130')}"> selected </c:if>>11:30</option>
					<option value="1200" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1200') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1200')}"> selected </c:if>>12:00</option>
					<option value="1230" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1230') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1230')}"> selected </c:if>>12:30</option>
					<option value="1300" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1300') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1300')}"> selected </c:if>>13:00</option>
					<option value="1330" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1330') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1330')}"> selected </c:if>>13:30</option>
					<option value="1400" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1400') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1400')}"> selected </c:if>>14:00</option>
					<option value="1430" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1430') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1430')}"> selected </c:if>>14:30</option>
					<option value="1500" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1500') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1500')}"> selected </c:if>>15:00</option>
					<option value="1530" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1530') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1530')}"> selected </c:if>>15:30</option>
					<option value="1600" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1600') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1600')}"> selected </c:if>>16:00</option>
					<option value="1630" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1630') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1630')}"> selected </c:if>>16:30</option>
					<option value="1700" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1700') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1700')}"> selected </c:if>>17:00</option>
					<option value="1730" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1730') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1730')}"> selected </c:if>>17:30</option>
					<option value="1800" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1800') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1800')}"> selected </c:if>>18:00</option>
					<option value="1830" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1830') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1830')}"> selected </c:if>>18:30</option>
					<option value="1900" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1900') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1900')}"> selected </c:if>>19:00</option>
					<option value="1930" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '1930') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '1930')}"> selected </c:if>>19:30</option>
					<option value="2000" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '2000') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '2000')}"> selected </c:if>>20:00</option>
					<option value="2030" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '2030') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '2030')}"> selected </c:if>>20:30</option>
					<option value="2100" <c:if test="${(not empty mtgPlaceResveVO.resveEndTm and mtgPlaceResveVO.resveEndTm == '2100') or (empty mtgPlaceResveVO.resveEndTm and mtgPlaceManageVO.resveEndTm == '2100')}"> selected </c:if>>21:00</option>
				</select>
		
				<input class="btn01" type="button" value="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.sameCheck" />" onclick="fn_egov_dplact_ceck(); return false;" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.newWindow" />" /><!-- 중복체크 -->
				<div><form:errors path="resveBeginTm" cssClass="error" /><form:errors path="resveTimeRangeValid" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.attendPeople" /></th><!-- 참석인원 -->
			<td class="left">
			    <input name="atndncNmpr" type="text" value="<c:out value='${not empty mtgPlaceResveVO.atndncNmpr ? mtgPlaceResveVO.atndncNmpr : mtgPlaceManageVO.atndncNmpr}'/>" maxlength="3" style="width:30px;" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.attendPeople" />" /> <spring:message code="comUssIonMtg.mtgPlaceManageList.persons" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgCn" /></th><!-- 회의내용 -->
			<td class="left">
			    <form:textarea path="mtgCn" rows="4" cols="70" title="회의내용"/>
      			<form:errors path="mtgCn"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncSaveMtgPlaceResve(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>?searchCondition=1" onclick="fncSelectMtgPlaceResveManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>