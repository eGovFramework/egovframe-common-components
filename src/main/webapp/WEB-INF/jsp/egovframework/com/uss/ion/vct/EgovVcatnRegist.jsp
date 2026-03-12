<%
/**
 * @Class Name : EgovVcatnRegist.java
 * @Description : EgovVcatnRegist.jsp
 * @Modification Information
 * @
 * @  수정일            수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영     퍼블리싱 점검
 * @ 2018.09.18    최두영       다국어처리& 달력처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<title><spring:message code="comUssIonVct.vcatnRegist.title"/></title><!-- 휴가신청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">
<!--
	$(function() {
		// bgnde datepicker 초기화
		var bgndeElement = $("#bgnde");
		if(bgndeElement.length > 0) {
			bgndeElement.datepicker({
				dateFormat:'yy-mm-dd'
				, showOn: 'button'
				, buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
				, buttonImageOnly: true
				, showMonthAfterYear: true
				, showOtherMonths: true
				, selectOtherMonths: true
				, changeMonth: true
				, changeYear: true
				, showButtonPanel: true
				, onSelect: function(dateText) {
					var bgndeValue = dateText.replace(/-/g, "");
					// form:hidden의 bgnde 값 설정
					var bgndeHidden = $("input[name='bgnde']");
					if(bgndeHidden.length > 0) {
						bgndeHidden.val(bgndeValue);
					}
				}
			});
		}
		
		// vcatnSe 값이 반차(02)인 경우에만 정오구분으로 전환, 그 외에는 종료일자 datepicker 항상 활성화
		var vcatnSeElement = $("#vcatnSe");
		if(vcatnSeElement.length > 0 && vcatnSeElement.val() == "02") {
			fncNoonSeSpan("02");
		} else {
			// 종료일자 datepicker 항상 활성화
			var enddeElement = $("#endde");
			if(enddeElement.length > 0) {
				enddeElement.datepicker({
					dateFormat:'yy-mm-dd'
					, showOn: 'button'
					, buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
					, buttonImageOnly: true
					, showMonthAfterYear: true
					, showOtherMonths: true
					, selectOtherMonths: true
					, changeMonth: true
					, changeYear: true
					, showButtonPanel: true
					, onSelect: function(dateText) {
						var enddeValue = dateText.replace(/-/g, "");
						// enddeHidden 값 설정
						var enddeHidden = document.getElementById("enddeHidden");
						if(enddeHidden) {
							enddeHidden.value = enddeValue;
						}
						// form의 endde 값 설정
						var enddeInputs = document.getElementsByName("endde");
						if(enddeInputs && enddeInputs.length > 0) {
							enddeInputs[0].value = enddeValue;
						}
					}
				});
			}
		}
	});

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEgovVcatnManageList(){
	location.href = "<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fncInsertEventVcatnManage(){
	var varForm = document.getElementById("vcatnManage") || document.forms["vcatnManage"];
	if (!validateVcatnManageVO(varForm)) {	
		return;
	}

	if(confirm("<spring:message code="common.save.msg" />")){
		// bgndeView 값을 bgnde로 복사 (날짜 형식 변환: yyyy-mm-dd -> yyyymmdd)
		var bgndeView = varForm.bgndeView;
		if(bgndeView && bgndeView.value) {
			var bgndeValue = bgndeView.value.replace(/-/g, "");
			var bgndeHidden = $("input[name='bgnde']");
			if(bgndeHidden.length > 0) {
				bgndeHidden.val(bgndeValue);
			}
		}
		
		if(varForm.vcatnSe && varForm.vcatnSe.value == "02"){
			// 반차인 경우 endde를 bgnde와 동일하게 설정
			var bgndeValue = "";
			if(bgndeView && bgndeView.value) {
				bgndeValue = bgndeView.value.replace(/-/g, "");
			} else {
				var bgndeHidden = $("input[name='bgnde']");
				if(bgndeHidden.length > 0) {
					bgndeValue = bgndeHidden.val();
				}
			}
			var enddeHidden = document.getElementById("enddeHidden");
			if(enddeHidden) {
				enddeHidden.value = bgndeValue;
			}
			var enddeInputs = document.getElementsByName("endde");
			if(enddeInputs && enddeInputs.length > 0) {
				enddeInputs[0].value = bgndeValue;
			}
		} else {
			// enddeView가 있으면 endde로 복사
			var enddeView = varForm.enddeView;
			var enddeHidden = document.getElementById("enddeHidden");
			if(enddeView && enddeView.value) {
				var enddeValue = enddeView.value.replace(/-/g, "");
				if(enddeHidden) {
					enddeHidden.value = enddeValue;
				}
				var enddeInputs = document.getElementsByName("endde");
				if(enddeInputs && enddeInputs.length > 0) {
					enddeInputs[0].value = enddeValue;
				}
			}
		}
		varForm.action = "<c:url value='/uss/ion/vct/insertVcatnManage.do'/>";
		varForm.submit();
	}
}

function fncNoonSeSpan(vValue){
	var nameSpan = document.getElementById("nameSpan");
	var noonSeSpan = document.getElementById("noonSeSpan");
	
	if(!nameSpan || !noonSeSpan) {
		return;
	}
	
	var vTemp = "";
	var enddeDisplayValue = "";
	var enddeHiddenValue = "";
	<c:if test="${vcatnManageVO != null}">
		<c:choose>
			<c:when test="${vcatnManageVO.tempEndde != null && !empty vcatnManageVO.tempEndde}">
				enddeDisplayValue = "<c:out value='${vcatnManageVO.tempEndde}'/>";
			</c:when>
			<c:when test="${vcatnManageVO.endde != null && fn:length(vcatnManageVO.endde) == 8}">
				enddeDisplayValue = "<c:out value='${fn:substring(vcatnManageVO.endde, 0, 4)}-${fn:substring(vcatnManageVO.endde, 4, 6)}-${fn:substring(vcatnManageVO.endde, 6, 8)}'/>";
			</c:when>
		</c:choose>
		<c:if test="${vcatnManageVO.endde != null}">
			enddeHiddenValue = "<c:out value='${vcatnManageVO.endde}'/>";
		</c:if>
	</c:if>
	
    if(vValue != "02"){
		vTemp += "  <input name='enddeView' id='endde' type='text' value='" + enddeDisplayValue + "' maxlength='10' readonly='readonly' style='width:70px' />";
		vTemp += "  <input type='hidden' name='endde' id='enddeHidden' value='" + enddeHiddenValue + "' />";
        nameSpan.innerHTML = "<label for='endde'><spring:message code="comUssIonVct.common.endDate"/></label>";/* 종료일자 */
        noonSeSpan.innerHTML = vTemp;
        
        setTimeout(function() {
        	var enddeInput = document.getElementById("endde");
        	if(enddeInput) {
        		$(enddeInput).datepicker({
        			dateFormat:'yy-mm-dd'
        			, showOn: 'button'
        			, buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
        			, buttonImageOnly: true
        			, showMonthAfterYear: true
        			, showOtherMonths: true
        			, selectOtherMonths: true
        			, changeMonth: true
        			, changeYear: true
        			, showButtonPanel: true
        			, onSelect: function(dateText) {
        				var hiddenInput = document.getElementById("enddeHidden");
        				if(hiddenInput) {
        					hiddenInput.value = dateText.replace(/-/g, "");
        				}
        				var enddeInputs = document.getElementsByName("endde");
        				if(enddeInputs && enddeInputs.length > 0) {
        					enddeInputs[0].value = dateText.replace(/-/g, "");
        				}
        			}
        		});
        	}
        }, 100);
    }
    else if(vValue == "02"){
    	vTemp += "  <input name='noonSe' type='radio' value='1' checked><spring:message code="comUssIonVct.common.noonSe1"/>";/* 오전 */
    	vTemp += "  <input name='noonSe' type='radio' value='2'><spring:message code="comUssIonVct.common.noonSe2"/>";/* 오후 */
    	vTemp += '   <input type="hidden" name="endde" id="enddeHidden" value=""/>';
        nameSpan.innerHTML = "<label for='noonSe'><spring:message code="comUssIonVct.common.noonSe"/></label>";/* 정오구분 */
        noonSeSpan.innerHTML = vTemp;
    }    
}

function modalDialogCallback(retVal) {
	if(retVal != null){
		var tmp = retVal.split(",");
		var f = document.getElementById("vcatnManage") || document.forms["vcatnManage"];
		if(!f) return;
		f.sanctnerId.value = tmp[0];
		document.getElementById("sanctnDtNm").value = tmp[2];
		document.getElementById("orgnztNm").value = tmp[3];
		$('.ui-dialog-content').dialog('close');
	}
}
function openVcatnSanctnerDialog(title) {
	var page = "<c:url value='/uss/ion/ism/selectSanctnerListNew.do'/>";
	var $dialog = $('<div></div>')
		.html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
		.dialog({
			autoOpen: false,
			modal: true,
			height: 750,
			width: 770,
			title: title
		});
	$dialog.dialog('open');
}
$(document).ready(function () {
	$('#VcatnSanctner').click(function (e) {
		e.preventDefault();
		openVcatnSanctnerDialog($(this).attr("data-dialog-title"));
	});
});

<c:if test="${!empty errorMessage}">
alert("<c:out value='${errorMessage}'/>");
</c:if>

-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<div class="wTableFrm">

	<form:form modelAttribute="vcatnManageVO" name="vcatnManage" id="vcatnManage" method="post">
	<form:hidden path="applcntId" id="applcntId"/>

	<!-- 타이틀 -->
	<h2 class=""><spring:message code="comUssIonVct.vcatnRegist.title"/></h2><!-- 휴가신청 -->

	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.vcatnRegist.vcatnAplyr"/></h3><!-- 휴가 신청자 -->
	
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="width:34%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.applcntNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.applcntNm != null ? vcatnManageVO.applcntNm : ""}'/>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.orgnztNm != null ? vcatnManageVO.orgnztNm : ""}'/>
			</td>
		</tr>
		<tr>
			<td class="left" colspan="4">
				<span>[<spring:message code="comUssIonVct.common.occrncYrycCo"/>: <c:out value="${vcatnManageVO.occrncYrycCo != null ? vcatnManageVO.occrncYrycCo : '0.0'}" />  ]</span><!-- 발생연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.useYrycCo"/>: <c:out value="${vcatnManageVO.useYrycCo != null ? vcatnManageVO.useYrycCo : '0.0'}" />  ]</span><!-- 사용연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.remndrYrycCo"/>: <c:out value="${vcatnManageVO.remndrYrycCo != null ? vcatnManageVO.remndrYrycCo : '0.0'}" />  ]</span><!-- 잔여연차 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnSe"/> <span class="pilsu">*</span></th><!-- 휴가구분 -->
			<td class="left" colspan="3">
				<form:select path="vcatnSe" onchange="javascript:fncNoonSeSpan(this.value);" title="휴가구분">
					<option value=""><spring:message code="common.select"/></option>
					<c:if test="${vcatnSeCode != null}">
						<form:options items="${vcatnSeCode}" itemValue="code" itemLabel="codeNm"/>
					</c:if>
				</form:select>
				<div><form:errors path="vcatnSe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.startDate"/> <span class="pilsu">*</span></th><!-- 시작일자 -->
			<td class="left">
				<c:set var="bgndeDisplayValue" value="" />
				<c:if test="${vcatnManageVO != null}">
					<c:choose>
						<c:when test="${vcatnManageVO.tempBgnde != null && !empty vcatnManageVO.tempBgnde}">
							<c:set var="bgndeDisplayValue" value="${vcatnManageVO.tempBgnde}" />
						</c:when>
						<c:when test="${vcatnManageVO.bgnde != null && fn:length(vcatnManageVO.bgnde) == 8}">
							<c:set var="bgndeDisplayValue" value="${fn:substring(vcatnManageVO.bgnde, 0, 4)}-${fn:substring(vcatnManageVO.bgnde, 4, 6)}-${fn:substring(vcatnManageVO.bgnde, 6, 8)}" />
						</c:when>
						<c:when test="${vcatnManageVO.bgnde != null && fn:length(vcatnManageVO.bgnde) == 10}">
							<c:set var="bgndeDisplayValue" value="${vcatnManageVO.bgnde}" />
						</c:when>
					</c:choose>
				</c:if>
				<input name="bgndeView" id="bgnde" type="text" value="<c:out value='${bgndeDisplayValue}'/>" maxlength="10" readonly="readonly" title="휴가 시작일자" style="width:70px" />
				<form:hidden path="bgnde" />
				<div><form:errors path="bgnde" cssClass="error" /></div>
			</td>
			<th><span id="nameSpan"><spring:message code="comUssIonVct.common.endDate"/></span> <span class="pilsu">*</span></th><!-- 종료일자 -->
			<td class="left">
				<span id="noonSeSpan"> 
					<c:set var="enddeDisplayValue" value="" />
					<c:set var="enddeHiddenValue" value="" />
					<c:if test="${vcatnManageVO != null}">
						<c:choose>
							<c:when test="${vcatnManageVO.tempEndde != null && !empty vcatnManageVO.tempEndde}">
								<c:set var="enddeDisplayValue" value="${vcatnManageVO.tempEndde}" />
							</c:when>
							<c:when test="${vcatnManageVO.endde != null && fn:length(vcatnManageVO.endde) == 8}">
								<c:set var="enddeDisplayValue" value="${fn:substring(vcatnManageVO.endde, 0, 4)}-${fn:substring(vcatnManageVO.endde, 4, 6)}-${fn:substring(vcatnManageVO.endde, 6, 8)}" />
							</c:when>
							<c:when test="${vcatnManageVO.endde != null && fn:length(vcatnManageVO.endde) == 10}">
								<c:set var="enddeDisplayValue" value="${vcatnManageVO.endde}" />
							</c:when>
						</c:choose>
						<c:if test="${vcatnManageVO.endde != null}">
							<c:set var="enddeHiddenValue" value="${fn:length(vcatnManageVO.endde) == 10 ? fn:replace(vcatnManageVO.endde, '-', '') : vcatnManageVO.endde}" />
						</c:if>
					</c:if>
					<input name="enddeView" id="endde" type="text" value="<c:out value='${enddeDisplayValue}'/>" maxlength="10" readonly="readonly" title="휴가 종료일자" style="width:70px" />
					<input type="hidden" name="endde" id="enddeHidden" value="<c:out value='${enddeHiddenValue}'/>" />
				</span>
				<div><form:errors path="endde" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnResn"/> <span class="pilsu">*</span></th><!-- 휴가사유 -->
			<td class="left" colspan="3">
			    <form:textarea path="vcatnResn" id="vcatnResn" class="txta01" rows="4" cols="70" title='<spring:message code="comUssIonVct.common.vcatnResn"/>' /><!-- 휴가사유 -->
			    <div><form:errors path="vcatnResn" cssClass="error" /></div>
			</td>
		</tr>
	</table>	
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="width:34%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.sanctnDtNm"/> <span class="pilsu">*</span></th><!-- 결재권자명 -->
			<td class="left">
			    <input name="sanctnDtNm" id="sanctnDtNm" type="text" value="<c:out value='${empty infSanctnDtNm ? vcatnManageVO.sanctnerNm : infSanctnDtNm}'/>" title='<spring:message code="comUssIonVct.common.sanctnDtNm"/>' readonly="readonly" style="width:128px" /><!-- 결재권자명 -->
		        <form:hidden path="sanctnerId" id="sanctnerId"/>
			    <span class="link">
			    <a id="VcatnSanctner" href="#LINK" title='<spring:message code="comUssIonRwd.common.searchNm"/>' data-dialog-title="<spring:message code="comUssIonVct.common.infrmlSanctnRegist"/>" style="selector-dummy: expression(this.hideFocus=false);">
			    <img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" style="vertical-align: middle" alt='<spring:message code="comUssIonVct.common.sanctnDtNm"/>' title='<spring:message code="comUssIonVct.common.sanctnDtNm"/>'></a><!-- 결재권자 지정 -->
			    </span>
			    <div><form:errors path="sanctnerId" cssClass="error" /></div>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <input name="orgnztNm" id="orgnztNm" type="text" value="<c:out value='${empty infOrgnztNm ? vcatnManageVO.sanctnerOrgnztNm : infOrgnztNm}'/>" title='<spring:message code="comUssIonVct.common.orgnztNm"/>' readonly="readonly"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncInsertEventVcatnManage(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>" title='<spring:message code="button.list" /> <spring:message code="input.button" />'><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>