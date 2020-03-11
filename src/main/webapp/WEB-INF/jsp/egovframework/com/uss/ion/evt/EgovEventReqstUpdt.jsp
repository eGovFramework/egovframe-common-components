<%
/**
 * @Class Name : EgovEventReqstUpdt.java
 * @Description : EgovEventReqstUpdt jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 *   2011.08.17    	정진오		선택입력사항을 표시하는 이미지 icon 경로가
 * 								다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함 *
 * @ 2108.09.20    최 두 영     다국어처리
 *  @author 이      용
 *  @since 2010.07.20
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventReqstUpdt.title"/></title><!-- 행사수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="eventManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
	
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
 * 목록 으로 가기
 ******************************************************** */
function fncEventReqstManageList(){
	location.href = "<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>";
}
/* ********************************************************
* 저장처리화면
******************************************************** */
function fncUpdtEventReqstManage() {
    var varFrom = document.getElementById("eventManage");
    varFrom.action = "<c:url value='/uss/ion/evt/EgovEventReqstSave.do'/>";
    vEventBeginDe = varFrom.eventBeginDe.value.split("-").join("");
    vEventEndDe   = varFrom.eventEndDe.value.split("-").join("");
    vRceptBeginDe = varFrom.rceptBeginDe.value.split("-").join("");
    vRceptEndDe   = varFrom.rceptEndDe.value.split("-").join("");
    vRefrnUrl     = varFrom.refrnUrl.value.split("-").join("");
    vPsncpa       = varFrom.psncpa.value;
    vPartcptCt    = varFrom.partcptCt.value;

    if(vEventBeginDe > vEventEndDe){
	    alert("<spring:message code="comUssIonEvt.common.validate.vEventBeginDefastervEventEndDe"/>");/* 행사시작일이  행사종료일보다 늦습니다. 행사기간을 확인해 주세요. */
	    return;
    }
    if(vRceptBeginDe > vRceptEndDe){
	    alert("<spring:message code="comUssIonEvt.common.validate.vRceptBeginDeFastervRceptEndDe"/>");/* 행사접수시작일이  행사접수종료일보다 늦습니다. 행사접수기간을  확인해 주세요 */
	    return;
    }
    
    if(vRceptEndDe > vEventBeginDe){
	    alert("<spring:message code="comUssIonEvt.common.validate.vRceptEndDeFastervEventBeginDe"/>");/* 행사접수는 행사시작일 이전에  접수종료되어어 합니다.  행사기간/행사접수기간을  확인해 주세요 */
	    return;
    }

	if(!urlCheck(vRefrnUrl) && vRefrnUrl!=""){
		alert("<spring:message code="comUssIonEvt.common.validate.urlCheckvRefrnUrl"/>");/* 참조URL의 형식이 URL 형식과 틀립니다. 확인해 주세요. */
		return;
	}

    if(isNaN(vPartcptCt)){
	        alert("<spring:message code="comUssIonEvt.common.validate.isNaNvPartcptCt"/>");/* 참가비용은 숫자만 입력가능합니다. */
    	return;
	}

    if(varFrom.ctOccrrncAt[1].checked){
		if(vPartcptCt <= 0){
			alert("<spring:message code="comUssIonEvt.common.validate.vPartcptCtZero"/>");/* 참가비용이 유료인 경우  0원 이상 입력하셔야 합니다. 확안해 주세요. */
			return;
		}
	}
    if(isNaN(vPsncpa)){
	         alert("<spring:message code="comUssIonEvt.common.validate.isNaNvPsncpa"/>");/* 정원은 숫자만 입력가능합니다. */
        return;
   }
	if(vPsncpa <= 0){
		alert("<spring:message code="comUssIonEvt.common.validate.vPsncpaZero"/>");/* 정원은  0명을 이상 입력하셔야 합니다. 확인해 주세요 */
		return;
	}
	
    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까?" */
       if(!validateEventManage(varFrom)){           
          return;
       }else{
          varFrom.submit();
       } 
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
/* ********************************************************
 * URL 여부 체크
 ******************************************************** */
function urlCheck(vValue){
 return vValue.search(/^\s*['http://']+[\w\~\-\.]+\.[\w\~\-]+(\.[\w\~\-]+)+\s*$/g)>=0;
}

/* ********************************************************
 * 숫자 여부 체크
 ******************************************************** */
function checkNum(inputValue) 
{
	alert(isNaN(inputValue));
   var checkCode = inputValue.charCodeAt(inputValue.length-1); 
   var str; 
   alert(checkCode)
   if(checkCode >= 33 && checkCode <= 47 || checkCode >= 58 && checkCode <= 125) 
   {
	      return false;
   }
   return true;
}
-->
</script>
</head>

<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="eventManage" name="eventManage" method="post" >
<input name="cmd" type="hidden" value="updt"/>
<form:hidden  path="eventNm" />
<form:hidden  path="eventSe" />
<form:hidden  path="eventId" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonEvt.eventReqstUpdt.title"/></h2><!-- 행사관리 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonEvt.common.searchSe"/><!-- 행사구분 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${eventManageVO.eventTemp3}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventNm"/><!-- 행사명 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${eventManageVO.eventNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPurps"/><!-- 행사목적 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="eventPurps"><spring:message code="comUssIonEvt.common.eventPurps"/></c:set>
				<form:input  path="eventPurps" maxlength="200" title="${eventPurps}"/>
				<form:errors path="eventPurps"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventDe"/><!-- 행사기간 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
				<c:set var="eventBeginDe"><spring:message code="comUssIonEvt.common.eventBeginDe"/></c:set>
				<form:input path="eventBeginDe" maxlength="10" title="${eventBeginDe}" cssStyle="width:70px"/>
				~
				<c:set var="eventEndDe"><spring:message code="comUssIonEvt.common.eventEndDe"/></c:set>
				<form:input path="eventEndDe" maxlength="10" title="${eventEndDe}" cssStyle="width:70px"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventAuspcInsttNm"/><!-- 행사주최 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="eventAuspcInsttNm"><spring:message code="comUssIonEvt.common.eventAuspcInsttNm"/></c:set>
				<form:input  path="eventAuspcInsttNm" size="60" maxlength="60" title="${eventAuspcInsttNm }"/>
				<form:errors path="eventAuspcInsttNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventMngtInsttNm"/><!-- 행사주관 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="eventMngtInsttNm"><spring:message code="comUssIonEvt.common.eventMngtInsttNm"/></c:set>
				<form:input  path="eventMngtInsttNm" maxlength="60" title="${eventMngtInsttNm}"/>
				<form:errors path="eventMngtInsttNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPlace"/><!-- 행사장소 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="eventPlace"><spring:message code="comUssIonEvt.common.eventPlace"/></c:set>
				<form:input  path="eventPlace" maxlength="200" title="${eventPlace}"/>
				<form:errors path="eventPlace"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.refrnUrl"/><!-- 참조URL --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="refrnUrl"><spring:message code="comUssIonEvt.common.refrnUrl"/></c:set>
				<form:input  path="refrnUrl" maxlength="1024" title="${refrnUrl}"/>
				<form:errors path="refrnUrl"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventCn"/><!-- 행사내용 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:textarea path="eventCn" rows="4" cols="70" cssClass="txaClass" title="${eventCn}"/>
      			<form:errors path="eventCn"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.ctOccrrncAt"/><!-- 참가비용 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="ctOccrrncAt" type="radio" value="1" onclick="fncOccrrncAt(this.value)" <c:if test="${eventManageVO.ctOccrrncAt == '1'}">checked</c:if> title="<spring:message code="comUssIonEvt.common.free"/>"><spring:message code="comUssIonEvt.common.free"/><!-- 무료 -->
				<input name="ctOccrrncAt" type="radio" value="2" onclick="fncOccrrncAt(this.value)" <c:if test="${eventManageVO.ctOccrrncAt == '2'}">checked</c:if> title="<spring:message code="comUssIonEvt.common.fee"/>"><spring:message code="comUssIonEvt.common.fee"/><!-- 유료 -->
				<c:set var="feePrice"><spring:message code="comUssIonEvt.common.feePrice"/></c:set>
				<form:input  path="partcptCt" maxlength="9" title="${feePrice} " cssStyle="width:50px"/><!-- 유료금액 -->
				<form:errors path="partcptCt"/><spring:message code="comUssIonEvt.common.feeUnit"/><!-- 만원 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.psncpa"/><!-- 정원 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="psncpa"><spring:message code="comUssIonEvt.common.psncpa"/></c:set>	
				<form:input  path="psncpa" size="10" maxlength="9" title="${psncpa} "/>
				<form:errors path="psncpa"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.rceptBeginDe.rceptEndDe"/><!-- 행사접수기간 --> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:set var="rceptBeginDe"><spring:message code="comUssIonEvt.common.rceptBeginDe"/></c:set>	<!-- 행사접수 시작일자 -->
				<form:input path="rceptBeginDe" maxlength="10" title="${rceptBeginDe}" cssStyle="width:70px"/>
				~
				<c:set var="rceptEndDe"><spring:message code="comUssIonEvt.common.rceptEndDe"/></c:set>	
				<form:input path="rceptEndDe" maxlength="10" title="${rceptEndDe}" cssStyle="width:70px"/><!-- 행사접수 종료일자 -->
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtEventReqstManage();" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>?searchCondition=1" onclick="fncEventReqstManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>

</body>
</html>
