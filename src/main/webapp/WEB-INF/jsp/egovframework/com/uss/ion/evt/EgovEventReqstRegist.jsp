<%
/**
 * @Class Name : EgovEventReqstRegist.java
 * @Description : EgovEventReqstRegist jsp
 * @Modification Information
 * @
 * @  수정일            수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이     용      최초 생성
 * @ 2011.08.17   	정 진 오		선택입력사항을 표시하는 이미지 icon 경로가 다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함*
 * 	@	2018.08.13    최 두 영		3.8 퍼블리싱 점검 및 개선				     
 * @ 2018.09.19     최 두 영     다국어처리
 *  @author 이      용
 *  @since 2010.07.20
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
<title><spring:message code="comUssIonEvt.eventReqstRegist.title"/></title><!-- 행사  등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">

	function initCalendar(){
		$("#eventBeginDe").datepicker(
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
		$("#eventEndDe").datepicker(
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
		$("#rceptBeginDe").datepicker(
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
		$("#rceptEndDe").datepicker(
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
	}

	/* ********************************************************
	 * 초기화
	 ******************************************************** */
	function fncEventReqstClear() {
		var varFrom = document.getElementById("eventManage");
		varFrom.eventSe[0].selected      = true;
		varFrom.eventNm.value           = "";
		varFrom.eventPurps.value        = "";
		varFrom.eventBeginDe.value      = "";
		varFrom.eventEndDe.value        = "";
		varFrom.eventAuspcInsttNm.value = "";
		varFrom.eventMngtInsttNm.value  = "";
		varFrom.eventPlace.value        = "";
		varFrom.refrnUrl.value          = "";
		varFrom.eventCn.value           = "";
		varFrom.ctOccrrncAt[0].checked  = true;
		varFrom.partcptCt.value         = 0;
		varFrom.psncpa.value            = 0;
		varFrom.rceptBeginDe.value      = "";
		varFrom.rceptEndDe.value        = "";
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fncEventReqstManageList(){
		location.href = "<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>";
	}
	 /* ********************************************************
	 * 저장처리화면 (EgovValidation.js validateEventManage 방식)
	 ******************************************************** */
	 function fncInsertEventReqstManage() {
		    var varFrom = document.getElementById("eventManage");
		    varFrom.action = "<c:url value='/uss/ion/evt/insertEventManage.do' />";
		    if (!validateEventManage(varFrom)) {
		        return;
		    }
		    if (confirm("<spring:message code="common.save.msg" />")) {
		        varFrom.submit();
		    }
		}
	
	 /* ********************************************************
	  * 참가비용체크
	  ******************************************************** */
	  function fncOccrrncAt(vValue) {
		  var varFrom = document.getElementById("eventManage");
		  if(vValue == "1"){ //무료
			  varFrom.partcptCt.value = 0;
		      varFrom.partcptCt.readOnly = true;
		  }else if(vValue == "2"){ //유료
			  varFrom.partcptCt.value = 0;
		      varFrom.partcptCt.readOnly = false;
		  }
	  }
	
</script>
</head>

<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="eventManage" name="eventManage" method="post" >
<input name="cmd" type="hidden" value="insert"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonEvt.eventReqstRegist.title"/></h2><!-- 행사 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonEvt.common.searchSe"/> <span class="pilsu">*</span></th><!-- 행사구분 -->
			<td class="left">
			<c:set var="eventSe"><spring:message code="comUssIonEvt.common.searchSe"/></c:set>
			    <form:select path="eventSe" title="${eventSe}">
					<form:options items="${eventSeCode}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="eventSe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventNm"/> <span class="pilsu">*</span></th><!-- 행사명 -->
			<td class="left">
			<c:set var="eventNm"><spring:message code="comUssIonEvt.common.eventNm"/></c:set>
			    <form:input  path="eventNm" maxlength="60" title="${eventNm}"/>
			    <div><form:errors path="eventNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPurps"/> <span class="pilsu">*</span></th><!-- 행사목적 -->
			<td class="left">
			<c:set var="eventPurps"><spring:message code="comUssIonEvt.common.eventPurps"/></c:set>
			    <form:input  path="eventPurps" maxlength="200" title="${eventPurps}"/>
			    <div><form:errors path="eventPurps" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventDe"/> <span class="pilsu">*</span></th><!-- 행사기간 -->
			<td class="left">
				<c:set var="eventBeginDe"><spring:message code="comUssIonEvt.common.eventBeginDe"/></c:set><!-- 행사 시작일자 -->
				<form:input path="eventBeginDe" maxlength="10" title="${eventBeginDe}" cssStyle="width:68px"/>
				~
				<c:set var="eventEndDe"><spring:message code="comUssIonEvt.common.eventEndDe"/></c:set><!-- 행사 종료일자 -->
				<form:input path="eventEndDe" size="10" maxlength="10" title="${eventEndDe}" cssStyle="width:68px"/>
				
				<c:set var="errors" value="${requestScope['org.springframework.validation.BindingResult.eventManage']}"/>
				<c:if test="${not empty errors && errors.hasFieldErrors('eventBeginDe')}">
				    <div class="error">${errors.getFieldError('eventBeginDe').defaultMessage}</div>
				</c:if>
				<c:if test="${not empty errors && !errors.hasFieldErrors('eventBeginDe') && errors.hasFieldErrors('eventEndDe')}">
				    <div class="error">${errors.getFieldError('eventEndDe').defaultMessage}</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventAuspcInsttNm"/> <span class="pilsu">*</span></th><!-- 행사주최 -->
			<td class="left">
				<c:set var="eventAuspcInsttNm"><spring:message code="comUssIonEvt.common.eventAuspcInsttNm"/></c:set>
			    <form:input  path="eventAuspcInsttNm" maxlength="60" title="${eventAuspcInsttNm}"/>
			    <div><form:errors path="eventAuspcInsttNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventMngtInsttNm"/> <span class="pilsu">*</span></th><!-- 행사주관 -->
			<td class="left">
				<c:set var="eventMngtInsttNm"><spring:message code="comUssIonEvt.common.eventMngtInsttNm"/></c:set>
			    <form:input  path="eventMngtInsttNm" size="60" maxlength="60" title="${eventMngtInsttNm}"/>
			    <div><form:errors path="eventMngtInsttNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPlace"/> <span class="pilsu">*</span></th><!-- 행사장소 -->
			<td class="left">
				<c:set var="eventPlace"><spring:message code="comUssIonEvt.common.eventPlace"/></c:set>
			    <form:input  path="eventPlace" size="80" maxlength="200" title="${eventPlace}"/>
			    <div><form:errors path="eventPlace" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.refrnUrl"/></th><!-- 참조URL -->
			<td class="left">
				<c:set var="refrnUrl"><spring:message code="comUssIonEvt.common.refrnUrl"/></c:set>
			    <form:input  path="refrnUrl" maxlength="1024" title="${refrnUrl}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventCn"/> <span class="pilsu">*</span></th><!-- 행사내용 -->
			<td class="left">
				<c:set var="eventCn"><spring:message code="comUssIonEvt.common.eventCn"/></c:set>
			    <form:textarea path="eventCn" rows="4" cols="70" cssClass="txaClass" title="${eventCn}"/>
			    <div><form:errors path="eventCn" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.ctOccrrncAt"/> <span class="pilsu">*</span></th><!-- 참가비용 -->
			<td class="left">
			    <input name="ctOccrrncAt" type="radio" value="1" onclick="fncOccrrncAt(this.value)" title="<spring:message code="comUssIonEvt.common.free"/>" checked="checked" /><spring:message code="comUssIonEvt.common.free"/><!-- 무료 -->
				<input name="ctOccrrncAt" type="radio" value="2" onClick="fncOccrrncAt(this.value)" title="<spring:message code="comUssIonEvt.common.fee"/>" /><spring:message code="comUssIonEvt.common.fee"/><!-- 유료 -->
				<c:set var="feePrice"><spring:message code="comUssIonEvt.common.feePrice"/></c:set>				
				<form:input  path="partcptCt" maxlength="9" title="${feePrice}" cssStyle="width:68px"/><!-- 유료금액 -->
				<div><form:errors path="partcptCt" cssClass="error"/></div>
				<spring:message code="comUssIonEvt.common.feeUnit"/><!-- 만원 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.psncpa"/> <span class="pilsu">*</span></th><!-- 정원 -->
			<td class="left">
				<c:set var="psncpa"><spring:message code="comUssIonEvt.common.psncpa"/></c:set>	
			    <form:input  path="psncpa" maxlength="9" title="${psncpa}" cssStyle="width:68px" value="1" />
			    <div><form:errors path="psncpa" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.rceptBeginDe.rceptEndDe"/> <span class="pilsu">*</span></th><!-- 행사접수기간 -->
			<td class="left">
				<c:set var="rceptBeginDe"><spring:message code="comUssIonEvt.common.rceptBeginDe"/></c:set>	<!-- 행사접수 시작일자 -->
			    <form:input path="rceptBeginDe" maxlength="10" title="${rceptBeginDe}" cssStyle="width:68px"/>
				~
              	<c:set var="rceptEndDe"><spring:message code="comUssIonEvt.common.rceptEndDe"/></c:set>	
			    <form:input path="rceptEndDe" maxlength="10" title="${rceptEndDe}" cssStyle="width:68px"/><!-- 행사접수 종료일자 -->
			    
   				<c:set var="errors" value="${requestScope['org.springframework.validation.BindingResult.eventManage']}"/>
				<c:if test="${not empty errors && errors.hasFieldErrors('rceptBeginDe')}">
				    <div class="error">${errors.getFieldError('rceptBeginDe').defaultMessage}</div>
				</c:if>
				<c:if test="${not empty errors && !errors.hasFieldErrors('rceptBeginDe') && errors.hasFieldErrors('rceptEndDe')}">
				    <div class="error">${errors.getFieldError('rceptEndDe').defaultMessage}</div>
				</c:if>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="" onclick="fncEventReqstClear(); return false;"><spring:message code="comUssIonEvt.common.init"/></a></span><!-- 초기화 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.create" />' onclick="fncInsertEventReqstManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>?searchCondition=1" onclick="fncEventReqstManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>
</body>
</html>
