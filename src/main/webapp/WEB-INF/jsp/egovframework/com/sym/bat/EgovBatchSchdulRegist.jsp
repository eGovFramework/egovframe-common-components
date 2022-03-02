<%--
  Class Name : EgovBatchSchdulRegist.jsp
  Description : 배치스케줄등록 페이지
  Modification Information

       수정일              수정자             수정내용
    -------    --------    ---------------------------
    2010.08.23  김진만             최초 생성
    2018.08.03  신용호             fn_egov_remove_string을 replace function으로 삭제
    2018.09.06  신용호             공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 김진만

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymBat.batchSchdulRegist.title"/></title><!-- 배치스케줄등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<validator:javascript formName="batchSchdul" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function initCalendar(){

	$("#executSchdulDeNm").datepicker( 
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
function fn_egov_init(){
	initCalendar();
    fn_egov_executCycleOnChange();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_get_list(){
    var varForm = document.getElementById("batchSchdul");
    varForm.action = "<c:url value='/sym/bat/getBatchSchdulList.do'/>";
    varForm.submit()
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
    var varForm = document.getElementById("batchSchdul");
    varForm.action =  "<c:url value='/sym/bat/addBatchSchdul.do'/>";


    if(!confirm("<spring:message code='common.save.msg' />") ){
      return ;
    }


    // 실행스케줄일자 validation체크
    if (varForm.executCycle.value == "02") {
      var i = 0;
      for (i = 0 ; i < varForm.executSchdulDfkSes.length; i++) {
        if (batchSchdul.executSchdulDfkSes[i].checked == true) {
          break;
        }
      }
      if (i == varForm.executSchdulDfkSes.length) {
        alert("<spring:message code="comSymBat.batchSchdulRegist.validate.executSchdulDfkSes.length"/>"); //실행주기를 매주로 선택했을 때 요일은 필수 입력값입니다.
        return ;
      }
    }

    if (varForm.executCycle.value == "03") {
        if (varForm.executSchdulDay.value == "") {
            alert("<spring:message code="comSymBat.batchSchdulRegist.validate.cycleMonth.executSchdulDay"/>"); //실행주기가 매월일때 실행스케줄(일)은 필수 입력값입니다.
             return ;
        }
    }
    if (varForm.executCycle.value == "04") {
        if (varForm.executSchdulMonth.value == "")   {
            alert("<spring:message code="comSymBat.batchSchdulRegist.validate.cycleYear.executSchdulMonth"/>"); //실행주기가 매년일때 실행스케줄(월)은 필수 입력값입니다.
             return ;
        }
        if (varForm.executSchdulDay.value == "") {
            alert("<spring:message code="comSymBat.batchSchdulRegist.validate.cycleYear.executSchdulDay"/>"); //실행주기가 매년일때 실행스케줄(일)은 필수 입력값입니다.
             return ;
        }
         // 2월 29일도 입력가능하도록 윤년인 해를 년도값으로 사용
        if (!checkDate('0400', varForm.executSchdulMonth.value, varForm.executSchdulDay.value, "<spring:message code="comSymBat.batchSchdulRegist.validate.checkDate"/>"))   { //실행스케줄 (월/일)이 유효하지 않습니다.
             return ;
        }
   }
    if ( varForm.executCycle.value == "05" )  {
        // 스케줄주기가 한번만일때 스케줄일자값이 존재해야 한다.
        if (varForm.executSchdulDeNm.value == "")   {
            alert("<spring:message code="comSymBat.batchSchdulRegist.validate.executSchdulDeNm"/>"); //실행주기가 한번만일때 실행스케줄(일자)는 필수 입력값입니다.
             return ;
        }
        if (!isDate(varForm.executSchdulDeNm.value, "<spring:message code="comSymBat.batchSchdulRegist.validate.executSchdulDeNm.isDate"/>")) { //실행스케줄 (일자)가 유효하지 않습니다.
             return ;
        }
    }

    /* 폼전송 데이타 조립. */
    var executSchdulDe = "";
    if (varForm.executCycle.value == "03") {
      executSchdulDe = '0000' + '00' + varForm.executSchdulDay.value;
    } else if (varForm.executCycle.value == "04") {
      executSchdulDe = '0000' + varForm.executSchdulMonth.value + varForm.executSchdulDay.value;
    } else if ( varForm.executCycle.value == "05" ) {
      executSchdulDe = varForm.executSchdulDeNm.value;
      executSchdulDe = executSchdulDe.replace(/-/gi,"");
    }
    varForm.executSchdulDe.value = executSchdulDe;

    if(!validateBatchSchdul(varForm)){
        return;
    }else{
        varForm.submit();
    }
}



/* ********************************************************
* 배치작업목록조회 팝업화면
******************************************************** */
function fn_egov_popup_batch_opert_list(){

    var retVal;
    var url = "<c:url value='/sym/bat/getBatchOpertListPopup.do'/>";
    var openParam = "dialogWidth: 800px; dialogHeight: 530px; resizable: 0, scroll: 1, center: 1";

    retVal = window.showModalDialog(url,window,openParam);

}

/* ********************************************************
 * executCycle onChange 이벤트 핸들러
 ******************************************************** */
function fn_egov_executCycleOnChange() {

    var varForm = document.getElementById("batchSchdul");
    var i = 0;
    if (varForm.executCycle.value == "01") {
        // 실행주기가 매일인 경우
        fn_egov_displayExecutSchdulSpan(false, false, false, false, true);
        fn_egov_clearExecutSchdulValue(true, true, true, true, false);

    } else if (varForm.executCycle.value == "02") {
        // 실행주기가 매주인 경우
        fn_egov_displayExecutSchdulSpan(false, false, false, true, true);
        fn_egov_clearExecutSchdulValue(true, true, true, false, false);

    } else if (varForm.executCycle.value == "03") {
        // 실행주기가 매월인 경우
        fn_egov_displayExecutSchdulSpan(false, false, true, false, true);
        fn_egov_clearExecutSchdulValue(true, true, false, true, false);

    } else if (varForm.executCycle.value == "04") {
        // 실행주기가 매년인 경우
        fn_egov_displayExecutSchdulSpan(false, true, true, false, true);
        fn_egov_clearExecutSchdulValue(true, false, false, true, false);

    } else if (varForm.executCycle.value == "05") {
        // 실행주기가 한번만인 경우
        fn_egov_displayExecutSchdulSpan(true, false, false, false, true);
        fn_egov_clearExecutSchdulValue(false, true, true, true, false);
    }

}

/* ********************************************************
 * 실행스케줄관련 SPAN Visibility 조절 함수
 ******************************************************** */
function fn_egov_displayExecutSchdulSpan(bYyyyMMdd, bMonth, bDay, bDfk, bHHmmss) {
  if (bYyyyMMdd) {
    spnYyyyMMdd.style.visibility ="visible";
    spnYyyyMMdd.style.display ="inline";
  } else {
    spnYyyyMMdd.style.visibility ="hidden";
    spnYyyyMMdd.style.display ="none";
  }

  if (bMonth) {
    spnMonth.style.visibility ="visible";
    spnMonth.style.display ="inline";
  } else {
    spnMonth.style.visibility ="hidden";
    spnMonth.style.display ="none";
  }

  if (bDay) {
    spnDay.style.visibility ="visible";
    spnDay.style.display ="inline";
  } else {
    spnDay.style.visibility ="hidden";
    spnDay.style.display ="none";
  }

  if (bDfk) {
    spnDfk.style.visibility ="visible";
    spnDfk.style.display ="inline";
  } else {
    spnDfk.style.visibility ="hidden";
    spnDfk.style.display ="none";
  }

  if (bHHmmss) {
    spnHHmmss.style.visibility ="visible";
    spnHHmmss.style.display ="inline";
  } else {
    spnHHmmss.style.visibility ="hidden";
    spnHHmmss.style.display ="none";
  }

}

/* ********************************************************
 * 실행스케줄관련 컴포넌트 값 clear 함수
 ******************************************************** */
function fn_egov_clearExecutSchdulValue(bYyyyMMdd, bMonth, bDay, bDfk, bHHmmss) {
  if (bYyyyMMdd) {
    executSchdulDeNm = "";
  }

  if (bMonth) {
    batchSchdul.executSchdulMonth[0].selected = true;
  }

  if (bDay) {
    batchSchdul.executSchdulDay[0].selected = true;
  }

  if (bDfk) {
    // 실행스케줄 요일 값 클리어
    for (var i = 0 ; i < batchSchdul.executSchdulDfkSes.length; i++) {
        batchSchdul.executSchdulDfkSes[i].checked = false;
    }
  }

  if (bHHmmss) {
    // 시분초는 클리어 할 필요가 없다.

  }

}

</script>
</head>
<body onLoad="fn_egov_init();">
<c:set var="vexecutSchdulHour"><spring:message code="comSymBat.batchSchdulRegist.executSchdulHour"/></c:set>
<c:set var="vexecutSchdulMnt"><spring:message code="comSymBat.batchSchdulRegist.executSchdulMnt"/></c:set>
<c:set var="vexecutSchdulSecnd"><spring:message code="comSymBat.batchSchdulRegist.executSchdulSecnd"/></c:set>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<!-- 상단타이틀 -->
<form:form modelAttribute="batchSchdul"  action="<c:url value='/sym/bat/addBatchSchdul.do'/>" method="post">

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default="1"/>"/>
<!-- 히든 속성 -->
<input type="hidden" name="executSchdulDe" value="<c:out value='${batchSchdul.executSchdulDe}'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymBat.batchSchdulRegist.pageTop.title"/></h2><!-- 배치스케줄 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymBat.batchSchdulRegist.batchOpertId"/> <span class="pilsu">*</span></th><!-- 배치작업ID -->
			<td class="left">
			    <form:input path="batchOpertId" maxlength="20" readonly="true"  cssClass="readOnlyClass" cssStyle="width:128px"/>
		        <form:errors path="batchOpertId" cssClass="error" />
		        <a href="#LINK" onclick="fn_egov_popup_batch_opert_list(); return false;"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />" alt="배치작업조회팝업 제공"/></a>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchSchdulRegist.batchOpertNm"/> <span class="pilsu">*</span></th><!-- 배치작업명 -->
			<td class="left">
			    <form:input path="batchOpertNm" size="60" maxlength="60" readonly="true"  cssClass="readOnlyClass"/>
        		<form:errors path="batchOpertNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchSchdulRegist.executCycleNm"/> <span class="pilsu">*</span></th><!-- 실행주기 -->
			<td class="left">
			    <form:select path="executCycle" onchange="fn_egov_executCycleOnChange(); return false;" cssStyle="padding:0px">
		             <form:options items="${executCycleList}" itemValue="code" itemLabel="codeNm"/>
		         </form:select>
		         <form:errors path="executCycle" cssClass="error"/>
		        <span id="spnYyyyMMdd">
		        <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
		        <input name="executSchdulDeNm" id="executSchdulDeNm" type="text" maxlength="10" title="<spring:message code="comSymBat.batchSchdulRegist.executSchdulDeNm"/>" readonly="readonly" style="width:70px"><!-- 실행스케줄(일자) -->
		        </span>
		        <span id="spnMonth">
		            <select name="executSchdulMonth" id="executSchdulMonth" title="<spring:message code="comSymBat.batchSchdulRegist.executSchdulMonth"/>"><!-- 실행스케줄(월) -->
		                <option value="" selected="selected" ><spring:message code="input.cSelect"/></option><!-- 선택 -->
		                <c:forEach var="i" begin="1" end="12" step="1">
		                  <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
		                  <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
		                </c:forEach>
		            </select>
				<spring:message code="comSymBat.batchSchdulRegist.spnMonth"/><!-- 월 -->
		          </span>
		          <span id="spnDay">
		            <select name="executSchdulDay" id="executSchdulDay" title="<spring:message code="comSymBat.batchSchdulRegist.executSchdulDay"/>"><!-- 실행스케줄(일) -->
		                <option value="" selected="selected"><spring:message code="input.cSelect"/></option><!-- 선택 -->
		                <c:forEach var="i" begin="1" end="31" step="1">
		                  <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
		                  <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
		                </c:forEach>
		            </select>
				<spring:message code="comSymBat.batchSchdulRegist.spnDay"/><!-- 일 -->
		        </span><br />
		        <span id="spnDfk">
		        <form:checkboxes path="executSchdulDfkSes" items="${executSchdulDfkSeList}" itemValue="code" itemLabel="codeNm" cssClass="check2"  cssStyle="padding:0px; vertical-align : middle;"/>
		        <form:errors path="executSchdulDfkSes" cssClass="error"/>
		        </span>
		        <span id="spnHHmmss">
		        <form:select path="executSchdulHour" title="${vexecutSchdulHour}" cssStyle="padding:0px"><!-- 실행스케줄(시) -->
		            <form:options items="${executSchdulHourList}" />
		        </form:select>
		        <form:errors path="executSchdulHour" cssClass="error"/>
		          <spring:message code="comSymBat.batchSchdulRegist.spnHH"/><!-- 시 -->
		        <form:select path="executSchdulMnt" title="${vexecutSchdulMnt}" cssStyle="padding:0px"><!-- 실행스케줄(분) -->
		            <form:options items="${executSchdulMntList}" />
		        </form:select>
		        <form:errors path="executSchdulMnt" cssClass="error"/>
		          <spring:message code="comSymBat.batchSchdulRegist.spnMM"/><!-- 분 -->
		        <form:select path="executSchdulSecnd"  title="${vexecutSchdulSecnd}" cssStyle="padding:0px"><!-- 실행스케줄(초) -->
		            <form:options items="${executSchdulSecndList}" />
		        </form:select>
		        <form:errors path="executSchdulSecnd" cssClass="error"/>
		          <spring:message code="comSymBat.batchSchdulRegist.spnSS"/><!-- 초 -->
		        </span>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save(); return false;" />
		<span class="btn_s"><a href="<c:url value='/sym/bat/getBatchSchdulList.do'/>" onclick="fn_egov_get_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>