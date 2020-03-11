<%--
/**
 * @Class Name  : EgovTroblReqstRegist.java
 * @Description : EgovTroblReqstRegist jsp
 * @Modification Information
 * @
 * @ 수정일                수정자             수정내용
 * @ ----------   --------    ---------------------------
 * @ 2010.07.01   lee.m.j     최초 생성
 *   2018.09.07   신용호             공통컴포넌트 3.8 개선
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
<title><spring:message code="comSymTbmTbr.troblReqstRegist.title"/></title><!-- 장애신청 등록 -->
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

function fncTroblReqstInsert() {

    var varFrom = document.getElementById("troblReqst");
    varFrom.action = "<c:url value='/sym/tbm/tbr/addTroblReqst.do'/>";

/*
    varFrom.troblOccrrncTime.value = varFrom.troblOccrrncD.value + ' ' +
                                     varFrom.troblOccrrncH.value + ':' +
                                     varFrom.troblOccrrncM.value + ':' +
                                     varFrom.troblOccrrncS.value;

    varFrom.troblRequstTime.value = varFrom.troblRequstD.value + ' ' +
                                    varFrom.troblRequstH.value + ':' +
                                    varFrom.troblRequstM.value + ':' +
                                    varFrom.troblRequstS.value;
*/

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
        alert("<spring:message code="comSymTbmTbr.troblReqstRegist.validate.info"/>"); //장애발생시간은 현재시간 이전으로 설정하셔야 합니다.
        return;
    }

    if(confirm("<spring:message code="comSymTbmTbr.troblReqstRegist.validate.save"/>")){ //저장 하시겠습니까?
        if(!validateTroblReqst(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
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

-->
</script>
</head>

<body onload="initCalendar()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymTbmTbr.troblReqstRegist.pageTop.title"/></h2><!-- 장애신청 등록 -->

	<form name="troblReqst" id="troblReqst" method="post" action="${pageContext.request.contextPath}/sym/tbm/tbr/addTroblReqst.do">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstRegist.troblNm"/> <span class="pilsu">*</span></th><!-- 장애명 -->
			<td class="left">
			    <label for="troblNm"><input name="troblNm" id="troblNm" type="text" maxLength="30" style="width:300px" />&nbsp;<form:errors path="troblNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstRegist.troblKnd"/> <span class="pilsu">*</span></th><!-- 장애종류 -->
			<td class="left">
			    <label for="troblKnd">
					<select name="troblKnd" title="<spring:message code="comSymTbmTbr.troblReqstRegist.troblKnd"/>"><!-- 장애종류 -->
						<c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
						<option value="<c:out value="${cmmCodeDetail.code}"/>"><c:out value="${cmmCodeDetail.codeNm}"/></option>
						</c:forEach>
					</select>
				</label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstRegist.troblDc"/> <span class="pilsu">*</span></th><!-- 장애설명 -->
			<td class="left">
			    <label for="troblDc"><textarea name="troblDc" rows="5" cols="80" title="<spring:message code="comSymTbmTbr.troblReqstRegist.troblDc"/>"></textarea>&nbsp;<form:errors path="troblDc" /></label><!-- 장애설명 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstRegist.troblOccrrncTime"/> <span class="pilsu">*</span></th><!-- 장애발생시간 -->
			<td class="left">
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
	            <label for="troblOccrrncD"><input type="text" name="troblOccrrncD" id="troblOccrrncD" maxlength="10" title="<spring:message code="comSymTbmTbr.troblReqstRegist.troblOccrrncTime"/>" style="width:70px" /></label><!-- 장애발생시간 -->
	            <input type="hidden" name="troblOccrrncDate" value=""/>
	            <label for="troblOccrrncH"><select name="troblOccrrncH" id="troblOccrrncH">
	              <c:forEach var="i" begin="0" end="23" step="1" varStatus="status">
	                <option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>"><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
	              </c:forEach>
	            </select><spring:message code="comSymTbmTbr.troblReqstRegist.hour"/><!-- 시 -->&nbsp;</label>
	            <label for="troblOccrrncM"><select name="troblOccrrncM" id="troblOccrrncM">
	              <c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
	                <option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>"><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
	              </c:forEach>
	            </select><spring:message code="comSymTbmTbr.troblReqstRegist.minute"/><!-- 분 -->&nbsp;</label>
	            <label for="troblOccrrncS"><select name="troblOccrrncS" id="troblOccrrncS">
	              <c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
	                <option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>"><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
	              </c:forEach>
	            </select><spring:message code="comSymTbmTbr.troblReqstRegist.second"/><!-- 초 --></label>
	            <input type="hidden" name="troblOccrrncTime" value=""/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstRegist.troblRqesterNm"/> <span class="pilsu">*</span></th><!-- 장애등록자 -->
			<td class="left">
			    <label for="troblRqesterNm"><input name="troblRqesterNm" id="troblRqesterNm" type="text" maxLength="30" style="width:200px" />&nbsp;<form:errors path="troblRqesterNm" /></label>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncTroblReqstInsert(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/tbm/tbr/selectTroblReqstList.do'/>?pageIndex=<c:out value='${troblReqstVO.pageIndex}'/>&amp;strTroblNm=<c:out value="${troblReqstVO.strTroblNm}"/>" onclick="fncSelectTroblReqstList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	
	<!-- 검색조건 유지 -->
    <input type="hidden" name="strTroblNm" value="<c:out value='${troblReqstVO.strTroblNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${troblReqstVO.pageIndex}'/>" >
    <input type="hidden" name="valiDay" />
	</form>
	
</div>

</body>
</html>

