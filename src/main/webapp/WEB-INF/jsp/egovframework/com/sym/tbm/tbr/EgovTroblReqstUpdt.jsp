<%--
/**
 * @Class Name  : EgovTroblReqstUpdt.java
 * @Description : EgovTroblReqstUpdt jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.0		lee.m.j     최초 생성
 * @ 2024.10.29		권태성		validateTroblReqst 함수 추가
 *
 *  @author lee.m.j
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymTbmTbr.troblReqstUpdt.title"/></title><!-- 장애신청 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="troblReqst" staticJavascript="false" xhtml="true" cdata="false"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function initCalendar(){
	$("#troblOccrrncD").datepicker( 
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

	$("#troblOccrrncD").change(function() {
		$("#troblOccrrncDate").val($(this).val().replace(/-/gi,""));
	});
}

function fncSelectTroblReqstList() {
    var varFrom = document.getElementById("troblReqst");
    varFrom.action = "<c:url value='/sym/tbm/tbr/selectTroblReqstList.do'/>";
    varFrom.submit();
}

function fncTroblReqstUpdate() {

    var varFrom = document.getElementById("troblReqst");
    varFrom.action = "<c:url value='/sym/tbm/tbr/updtTroblReqst.do'/>";

    varFrom.troblOccrrncTime.value = varFrom.troblOccrrncD.value +
                                     varFrom.troblOccrrncH.value +
                                     varFrom.troblOccrrncM.value +
                                     varFrom.troblOccrrncS.value;
/*
    varFrom.troblRequstTime.value = varFrom.troblRequstD.value +
                                    varFrom.troblRequstH.value +
                                    varFrom.troblRequstM.value +
                                    varFrom.troblRequstS.value;
*/

	fncCheckValiDay();

	var tmp = varFrom.troblOccrrncTime.value;
	tmp = tmp.substring(0,4) + tmp.substring(5,7) + tmp.substring(8,16);

	if(varFrom.valiDay.value < tmp) {
	    alert("<spring:message code="comSymTbmTbr.troblReqstUpdt.validate.info"/>"); //장애발생시간은 현재시간 이전으로 설정하셔야 합니다.
	    return;
	}


    if(confirm("<spring:message code="comSymTbmTbr.troblReqstUpdt.validate.save"/>")){ //저장 하시겠습니까?
        if(!validateTroblReqst(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncTroblReqstDelete() {

    var varFrom = document.getElementById("troblReqst");
    varFrom.action = "<c:url value='/sym/tbm/tbr/removeTroblReqst.do'/>";
    if(confirm("<spring:message code="comSymTbmTbr.troblReqstUpdt.validate.delete"/>")){ //삭제 하시겠습니까?
        varFrom.submit();
    }
}

function initDate() {

	var varFrom = document.getElementById("troblReqst");

	// 장애발생시간
    var troblOccrrncTime = '<c:out value="${troblReqst.troblOccrrncTime}"/>';


    // 장애요청시간
    // var troblRequstTime = '<c:out value="${troblReqst.troblRequstTime}"/>';

    varFrom.troblOccrrncD.value = troblOccrrncTime.substring(0,10);
    varFrom.troblOccrrncHtmp.value = troblOccrrncTime.substring(11,13);
    varFrom.troblOccrrncMtmp.value = troblOccrrncTime.substring(14,16);
    varFrom.troblOccrrncStmp.value = troblOccrrncTime.substring(17,19);

    if(varFrom.troblOccrrncHtmp.value < 10) varFrom.troblOccrrncHtmp.value = troblOccrrncTime.substring(12,13);
    if(varFrom.troblOccrrncMtmp.value < 10) varFrom.troblOccrrncMtmp.value = troblOccrrncTime.substring(15,16);
    if(varFrom.troblOccrrncStmp.value < 10) varFrom.troblOccrrncStmp.value = troblOccrrncTime.substring(18,19);
}

function fncCheckValiDay() {
    var varFrom = document.getElementById("troblReqst");

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();

    if(month < 10) month = "0" + month;
    if(day < 10) day = "0" + day;
    if(hour < 10) hour = "0" + hour;
    if(minute < 10) minute = "0" + minute;
    if(second < 10) second = "0" + second;

    // return year+month+day+hour+minute+second;
    varFrom.valiDay.value = year+""+month+""+day+""+hour+""+minute+""+second;

}

function validateTroblReqst(varFrom) {
    if (varFrom.troblNm.value.trim() === "") {
        alert("장애명을 입력해주세요.");
        varFrom.troblNm.focus();
        return false;
    }
    if (varFrom.troblDc.value.trim() === "") {
        alert("장애 설명을 입력해주세요.");
        varFrom.troblDc.focus();
        return false;
    }
    if (varFrom.troblOccrrncD.value.trim() === "") {
        alert("장애 발생 일자를 입력해주세요.");
        varFrom.troblOccrrncD.focus();
        return false;
    }
    if (varFrom.troblRqesterNm.value.trim() === "") {
        alert("장애 등록자를 입력해주세요.");
        varFrom.troblRqesterNm.focus();
        return false;
    }
    if (varFrom.troblKnd.value === "") {
        alert("장애 종류를 선택해주세요.");
        varFrom.troblKnd.focus();
        return false;
    }
    return true;
}
-->
</script>
</head>

<c:set var="troblOccrrncTime" value='${troblReqst.troblOccrrncTime}' />
<c:set var="troblOccrrncDtmp" value="${fn:substring(troblOccrrncTime,0,10)}" />
<c:set var="troblOccrrncHtmp" value="${fn:substring(troblOccrrncTime,11,13)}" />
<c:set var="troblOccrrncMtmp" value="${fn:substring(troblOccrrncTime,14,16)}" />
<c:set var="troblOccrrncStmp" value="${fn:substring(troblOccrrncTime,17,19)}" />
<c:if test="${troblOccrrncHtmp < 10}"><c:set var="troblOccrrncHtmp" value="${fn:substring(troblOccrrncTime,12,13)}" /></c:if>
<c:if test="${troblOccrrncMtmp < 10}"><c:set var="troblOccrrncMtmp" value="${fn:substring(troblOccrrncTime,15,16)}" /></c:if>
<c:if test="${troblOccrrncStmp < 10}"><c:set var="troblOccrrncStmp" value="${fn:substring(troblOccrrncTime,18,19)}" /></c:if>

<!--
<c:set var="troblRequstTime" value='${troblReqst.troblRequstTime}' />
<c:set var="troblRequstDtmp" value="${fn:substring(troblRequstTime,0,10)}" />
<c:set var="troblRequstHtmp" value="${fn:substring(troblRequstTime,11,13)}" />
<c:set var="troblRequstMtmp" value="${fn:substring(troblRequstTime,14,16)}" />
<c:set var="troblRequstStmp" value="${fn:substring(troblRequstTime,17,19)}" />
<c:if test="${troblRequstHtmp < 10}"><c:set var="troblRequstHtmp" value="${fn:substring(troblRequstTime,12,13)}" /></c:if>
<c:if test="${troblRequstMtmp < 10}"><c:set var="troblRequstMtmp" value="${fn:substring(troblRequstTime,15,16)}" /></c:if>
<c:if test="${troblRequstStmp < 10}"><c:set var="troblRequstStmp" value="${fn:substring(troblRequstTime,18,19)}" /></c:if>
-->

<body onload="initCalendar()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymTbmTbr.troblReqstUpdt.pageTop.title"/></h2><!-- 장애신청 수정 -->

    <form name="troblReqst" id="troblReqst" method="post" action="${pageContext.request.contextPath}/sym/tbm/tbr/updtTroblReqst.do">

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstUpdt.troblId"/> <span class="pilsu">*</span></th><!-- 장애ID -->
			<td class="left">
			    <input name="troblId" id="troblId" type="text" value="<c:out value='${troblReqst.troblId}'/>" class="readOnlyClass" readonly="readonly" style="width:180px"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstUpdt.troblNm"/> <span class="pilsu">*</span></th><!-- 장애명 -->
			<td class="left">
			    <input name="troblNm" id="troblNm" type="text" value="<c:out value='${troblReqst.troblNm}'/>" maxLength="30"  style="width:180px" />&nbsp;<form:errors path="troblNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstUpdt.troblKnd"/> <span class="pilsu">*</span></th><!-- 장애종류 -->
			<td class="left">
			    <select name="troblKnd" title="<spring:message code="comSymTbmTbr.troblReqstUpdt.troblKnd"/>">
				<c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
				<option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.code == troblReqst.troblKnd}">selected</c:if>><c:out value="${cmmCodeDetail.codeNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstUpdt.troblDc"/> <span class="pilsu">*</span></th><!-- 장애설명 -->
			<td class="left">
			    <textarea name="troblDc" rows="5" cols="80" title="<spring:message code="comSymTbmTbr.troblReqstUpdt.troblDc"/>"><c:out value='${troblReqst.troblDc}'/></textarea><form:errors path="troblDc" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstUpdt.troblOccrrncTime"/> <span class="pilsu">*</span></th><!-- 장애발생시간 -->
			<td class="left">
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			    <input type="text" name="troblOccrrncD" id="troblOccrrncD" value="<c:out value="${troblOccrrncDtmp}" />" maxlength="10" title="<spring:message code="comSymTbmTbr.troblReqstUpdt.troblOccrrncTime"/>" style="width:70px" />
			    <input type="hidden" name="troblOccrrncDate" value=""/>
			    <select name="troblOccrrncH" id="troblOccrrncH">
			      <c:forEach var="i" begin="0" end="23" step="1" varStatus="status">
				<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblOccrrncHtmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
			      </c:forEach>
			    </select><spring:message code="comSymTbmTbr.troblReqstUpdt.hour"/><!-- 시 -->&nbsp;
			    <select name="troblOccrrncM" id="troblOccrrncM">
			      <c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
				<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblOccrrncMtmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
			      </c:forEach>
			    </select><spring:message code="comSymTbmTbr.troblReqstUpdt.minute"/><!-- 분 -->&nbsp;
			    <select name="troblOccrrncS" id="troblOccrrncS">
			      <c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
				<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblOccrrncStmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
			      </c:forEach>
			    </select><spring:message code="comSymTbmTbr.troblReqstUpdt.second"/><!-- 초 -->
			    <input type="hidden" name="troblOccrrncTime" />&nbsp;<form:errors path="troblOccrrncTime" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstUpdt.troblRqesterNm"/> <span class="pilsu">*</span></th><!-- 장애등록자 -->
			<td class="left">
			    <input name="troblRqesterNm" id="troblRqesterNm" type="text" value="<c:out value='${troblReqst.troblRqesterNm}'/>" maxLength="30" style="width:180px">&nbsp;<form:errors path="troblRqesterNm" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncTroblReqstUpdate(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/tbm/selectTroblReqstList.do'/>?pageIndex=<c:out value='${troblReqstVO.pageIndex}'/>&amp;strTroblNm=<c:out value="${troblReqstVO.strTroblNm}"/>" onclick="fncSelectTroblReqstList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>

    <input type="hidden" name="processSttus" value="<c:out value='${troblReqst.processSttus}'/>" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strTroblNm" value="<c:out value='${troblReqstVO.strTroblNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${troblReqstVO.pageIndex}'/>" >
    <input type="hidden" name="valiDay" />
	</form>

</div>

</body>
</html>

