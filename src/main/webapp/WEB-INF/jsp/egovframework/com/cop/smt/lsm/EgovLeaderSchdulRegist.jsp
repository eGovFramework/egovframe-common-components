<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%--
  Class Name : EgovLeaderSchdulRegist.jsp
  Description : 간부일정관리 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.06.29    장철호          최초 생성
     2018.09.14    최두영          다국어 처리
     2019.01.14    최두영          레이어 팝업 처리

    author   : 공통서비스 개발팀 장철호
    since    : 2010.06.29

--%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtLsm.leaderSchdulRegist.title" /></title><!-- 간부일정관리 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="leaderSchdulVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" language="javascript">
function initCalendar(){
	$("#schdulBgndeYYYMMDD").datepicker(
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

	$("#schdulEnddeYYYMMDD").datepicker(
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
function fn_egov_init_LeaderSchdul(){

	//document.getElementsByName('reptitSeCode')[0].checked = true;

	if("${leaderSchdulVO.reptitSeCode}".length == 0 ) {
		document.getElementsByName('reptitSeCode')[0].checked = true;
		document.getElementById("endDay").style.display = "none";
	}

	if("${leaderSchdulVO.schdulBgnDe}".length > 0){
		var schdulBgnde = "${leaderSchdulVO.schdulBgnDe}";
		document.getElementById("schdulBgndeYYYMMDD").value = schdulBgnde.substring(0,4) + "-" + schdulBgnde.substring(4,6) + "-" + schdulBgnde.substring(6,8);
	}

	if("${leaderSchdulVO.schdulEndDe}".length > 0){
		var schdulEndde = "${leaderSchdulVO.schdulEndDe}";
		document.getElementById("schdulEnddeYYYMMDD").value = schdulEndde.substring(0,4) + "-" + schdulEndde.substring(4,6) + "-" + schdulEndde.substring(6,8);
	}

	/* document.getElementById("schdulChargerId").value = "${schdulChargerId}";
	document.getElementById("schdulChargerName").value = "${schdulChargerName}"; */
	/* document.getElementsByName('reptitSeCode')[0].checked = true;
	document.getElementById("endDay").style.display = "none"; */

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_LeaderSchdul(){
	var vFrom = document.leaderSchdulVO;
	vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulList.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_LeaderSchdul(form){
	//form.submit();return;

	if(form.reptitSeCode[0].checked){
		form.schdulEnddeYYYMMDD.value = form.schdulBgndeYYYMMDD.value;
	}

	if(!validateLeaderSchdulVO(form)){
		return;
	}

	var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
	var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;

	if(isDate(schdulBgndeYYYMMDD, "<spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulBgndeYYYMMDD" />") == false) {/* 일정시작일자 */
        return;
    }

    if(isDate(schdulEnddeYYYMMDD, "<spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulEnddeYYYMMDD" />") == false) {/* 일정종료일자 */
        return;
    }

    var schdulBgnHourMin = fn_egov_SelectBoxValue('schdulBgndeHH') +  fn_egov_SelectBoxValue('schdulBgndeMM');
	var schdulEndHourMin = fn_egov_SelectBoxValue('schdulEnddeHH') +  fn_egov_SelectBoxValue('schdulEnddeMM');

	if(schdulBgnHourMin > schdulEndHourMin){
		alert("<spring:message code="comCopSmtLsm.leaderSchdulModify.validate.timeCompare" />");/* 일정 종료 시 분이 일정 시작 시 분보다 빠를수 없습니다. */
		return;
	}

	form.schdulBgnDe.value = schdulBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('schdulBgndeHH') +  fn_egov_SelectBoxValue('schdulBgndeMM');
	form.schdulEndDe.value = schdulEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('schdulEnddeHH') +  fn_egov_SelectBoxValue('schdulEnddeMM');

	if(parseInt(form.schdulBgnDe.value) > parseInt(form.schdulEndDe.value)){
		alert("<spring:message code="comCopSmtLsm.leaderSchdulModify.validate.dayCompare" />");/* 일정 종료 일시가 일정 시작 일시보다 빠를수 없습니다. */
		return;
	}

	if(confirm("<spring:message code="common.save.msg" />")){
		form.submit();
	}
}

/* ********************************************************
* 레이어  팝업창 열기
* param ID 폼명, 사원번호 폼명, 사원명 폼명, 부서명 폼명
******************************************************** */
//간부명과 담당자에 대한 레이어 팝업 구분 코드
var popUpCode = 0;

function modalDialogCallback(retVal) {
	if(retVal != null){

		var tmp = retVal.split(",");		
		
		if(popUpCode == 0){
			document.leaderSchdulVO.leaderId.value = tmp[0];
			document.leaderSchdulVO.leaderName.value = tmp[2];
		}else if(popUpCode == 1){
 			document.leaderSchdulVO.schdulChargerId.value  = tmp[0];
			document.leaderSchdulVO.schdulChargerName.value = tmp[2];
		}else{
			alert("Error")
		}
		
		document.leaderSchdulVO.action = "<c:url value='/cop/smt/lsm/mng/addLeaderSchdul.do' />";
		document.leaderSchdulVO.submit();
		
		}
		}

function layerPopUp(){
       var pagetitle = $(this).attr("title");
       var page = "<c:url value='/cop/smt/lsm/selectEmplyrList.do' />";
       var $dialog = $('<div></div>')
       .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
       .dialog({
       	autoOpen: false,
           modal: true,
           height: 750,
           width: 770,
           title: pagetitle
   	});
   	$dialog.dialog('open');		
};

	$(document).ready(function() { 		
   			
    $('#popupleaderName').click(function (e) {	
    	popUpCode = 0;
    	e.preventDefault();
    	layerPopUp();
	});
    
    $('#popupschdulChargerName').click(function (e) {
    	popUpCode = 1;
    	e.preventDefault();
    	layerPopUp();
	});	
    
}); 

function viewEndDay(num){
	if(num == "1"){
		document.getElementById("endDay").style.display = "none";
	}else{
		document.getElementById("endDay").style.display = "";
	}
}

/* ********************************************************
* RADIO BOX VALUE FUNCTION
******************************************************** */
function fn_egov_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	return FValue;
}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){

			FValue=document.getElementById(sbName).options[i].value;
		}
	}

	return  FValue;
}
/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
</script>
</head>
<body onLoad="fn_egov_init_LeaderSchdul(); initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div id="content" style="width:712px">
<form:form commandName="leaderSchdulVO" action="${pageContext.request.contextPath}/cop/smt/lsm/mng/insertLeaderSchdul.do" name="leaderSchdulVO" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtLsm.leaderSchdulRegist.title" /></h2><!-- 간부일정관리 등록 -->
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulSe" /> <span class="pilsu">*</span></th><!-- 일정구분 -->
			<td class="left">
		          <c:set var="select"><spring:message code="input.cSelect" /></c:set><!-- 선택 -->
			    <form:select path="schdulSe" title="일정구분 선택">
		            <form:option value="" label="${select}"/>
		            <form:options items="${schdulSe}" itemValue="code" itemLabel="codeNm"/>
		        </form:select>
		        <div><form:errors path="schdulSe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" /> <span class="pilsu">*</span></th><!-- 간부명 -->
			<td class="left">
			    <form:input path="leaderName"  readonly="true" maxlength="10" title="간부명" cssStyle="width:98px" />
			    	<a id="popupleaderName" title="<spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" />"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" /></a>
			    <!-- 2018.07.19 추가 시작 -->
			    <div><form:errors path="leaderName" cssClass="error"/></div> 
       		   <form:hidden path="leaderId" />
       			<!-- 2018.07.19 추가 끝 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulNm" /> <span class="pilsu">*</span></th><!-- 일정명 -->
			<td class="left">
			    <form:input path="schdulNm" size="92" maxlength="255" title="일정명"/>
      			<div><form:errors path="schdulNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulCn" /> <span class="pilsu">*</span></th><!-- 일정내용 -->
			<td class="left">
				<form:textarea path="schdulCn" rows="5" cols="90" title="일정내용"/>
				<div><form:errors path="schdulCn" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdulDetail.schdulPlace" /> <!-- <span class="pilsu">*</span> --></th><!-- 일정장소 -->
			<td class="left">
				<form:input path="schdulPlace" size="92" maxlength="255" title="일정장소"/>
				<div><form:errors path="schdulPlace" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdulDetail.reptitSeCode" /> <!-- <span class="pilsu">*</span> --></th><!-- 반복구문 -->
			<td class="left">
				<form:radiobutton path="reptitSeCode" value="1" onchange="viewEndDay('1')" onclick="viewEndDay('1')"/><spring:message code="comCopSmtLsm.leaderSchdulModify.noRepeat" /><!-- 반복없음 -->
				<form:radiobutton path="reptitSeCode" value="2" onchange="viewEndDay('2')" onclick="viewEndDay('2')" cssStyle="margin-left:10px"/><spring:message code="comCopSmtLsm.leaderSchdulModify.everyday" /><!-- 매일 -->
				<form:radiobutton path="reptitSeCode" value="3" onchange="viewEndDay('3')" onclick="viewEndDay('3')" cssStyle="margin-left:10px"/><spring:message code="comCopSmtLsm.leaderSchdulModify.everyweek" /><!-- 매주 -->
				<form:radiobutton path="reptitSeCode" value="4" onchange="viewEndDay('4')" onclick="viewEndDay('4')" cssStyle="margin-left:10px"/><spring:message code="comCopSmtLsm.leaderSchdulModify.everymonth" /><!-- 매월 -->
				<div><form:errors path="reptitSeCode" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdulDetail.datetime" /> <!-- <span class="pilsu">*</span> --></th><!-- 날짜/시간 -->
			<td class="left">
				<form:input path="schdulBgndeYYYMMDD" size="10" maxlength="10" title="일정시작일자" cssStyle="width:98px" />
				<span id="endDay">
					~ 
				    <form:input path="schdulEnddeYYYMMDD" size="10" maxlength="10" title="일정종료일자" cssStyle="width:98px" />
				</span>
				
				<form:select path="schdulBgndeHH" title="일정시작 시 선택" cssStyle="margin-left:10px">
					<form:options items="${schdulBgndeHH}" itemValue="code" itemLabel="codeNm"/>
					</form:select><spring:message code="comCopSmtLsm.leaderSchdulDetail.hour" /><!-- 시 -->
					<form:select path="schdulBgndeMM" title="일정시작 분 선택">
					<form:options items="${schdulBgndeMM}" itemValue="code" itemLabel="codeNm"/>
				</form:select><spring:message code="comCopSmtLsm.leaderSchdulDetail.minute" /><!-- 분 -->
				~
				<form:select path="schdulEnddeHH" title="일정종료 시 선택">
					<form:options items="${schdulEnddeHH}" itemValue="code" itemLabel="codeNm"/>
					</form:select><spring:message code="comCopSmtLsm.leaderSchdulDetail.hour" /><!-- 시 -->
					<form:select path="schdulEnddeMM" title="일정종료 분 선택">
					<form:options items="${schdulEnddeMM}" itemValue="code" itemLabel="codeNm"/>
				</form:select><spring:message code="comCopSmtLsm.leaderSchdulDetail.minute" /><!-- 분 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulChargerName" /> <span class="pilsu">*</span></th><!-- 담당자 -->
			<td class="left">
				<form:input path="schdulChargerName" size="15" readonly="true" maxlength="10" title="담당자명" cssStyle="width:98px"/>
					<a id="popupschdulChargerName" title="<spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulChargerName" />"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" /></a>
				<div><form:errors path="schdulChargerName" cssClass="error"/></div>
				<form:hidden path="schdulChargerId" />
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_save_LeaderSchdul(document.forms[0]); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulList.do'/>" onclick="fn_egov_list_LeaderSchdul(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input name="searchMode" id="searchMode" type="hidden" value="<c:out value='${searchMode}'/>">
<input name="year" id="year" type="hidden" value="<c:out value='${year}'/>">
<input name="month" id="month" type="hidden" value="<c:out value='${month}'/>">
<input name="week" id="week" type="hidden" value="<c:out value='${week}'/>">
<input name="day" id="day" type="hidden" value="<c:out value='${day}'/>">
<input name="schdulBgnDe" id="schdulBgnDe" type="hidden">
<input name="schdulEndDe" id="schdulEndDe" type="hidden">
<input name="cmd" id="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>
</div>

</body>
</html>
