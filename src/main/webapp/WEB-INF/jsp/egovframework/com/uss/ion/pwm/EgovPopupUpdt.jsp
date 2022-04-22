<%--
  Class Name : EgovPopupUpdt.jsp
  Description : 팝업창관리 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.09.16    장동한          최초 생성
     2018.08.29    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.09.16

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>                                                        
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonPwm.popupUpdt.popupUpdt"/></title><!-- 팝업창관리 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="popupManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_PopupManage(){

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_PopupManage(){

	var varFrom = document.popupManageVO;

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/uss/ion/pwm/updtPopup.do' />";
		if(!validatePopupManageVO(varFrom)){
			debugger;
			return;
		}else{

			var ntceBgndeYYYMMDD = document.getElementById('ntceBgndeYYYMMDD').value;
			var ntceEnddeYYYMMDD = document.getElementById('ntceEnddeYYYMMDD').value;

			var iChkBeginDe = Number( ntceBgndeYYYMMDD.replaceAll("-","") );
			var iChkEndDe = Number( ntceEnddeYYYMMDD.replaceAll("-","") );

			if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
				alert("<spring:message code="ussIonPwm.popupUpdt.validate.iChkDate"/>");/* 게시시작일자는 게시종료일자 보다 클수 없고, 게시종료일자는 게시시작일자 보다 작을수 없습니다. */
				return;
			}

			varFrom.ntceBgnde.value = ntceBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceBgndeHH') +  fn_egov_SelectBoxValue('ntceBgndeMM');
			varFrom.ntceEndde.value = ntceEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceEnddeHH') +  fn_egov_SelectBoxValue('ntceEnddeMM');

			varFrom.submit();
		}
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
/* ********************************************************
 * 달력
 ******************************************************** */
function fn_egov_init_date(){
	
	$("#ntceBgndeYYYMMDD").datepicker(  
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


	$("#ntceEnddeYYYMMDD").datepicker( 
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
</script>

</head>
<body onLoad="fn_egov_init_PopupManage(); fn_egov_init_date();">

<form:form modelAttribute="popupManageVO" name="popupManageVO" action="${pageContext.request.contextPath}/uss/ion/pwm/updtPopup.do" method="post" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonPwm.popupUpdt.popupUpdt"/></h2><!-- 팝업창관리 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:24%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonPwm.popupUpdt.popupTitleNm"/> <span class="pilsu">*</span></th><!-- 팝업창명 -->
			<td class="left">
			    <form:input path="popupTitleNm" size="73" cssClass="txaIpt" maxlength="255"/>
      			<form:errors path="popupTitleNm" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupUpdt.fileUrl"/> <span class="pilsu">*</span></th><!-- 팝업창URL -->
			<td class="left">
			    <form:input path="fileUrl" size="73" cssClass="txaIpt" maxlength="255"/>
      			<form:errors path="fileUrl" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupUpdt.popupLoca"/> <span class="pilsu">*</span></th><!-- 팝업창위치 -->
			<td class="left">
			    <spring:message code="ussIonPwm.popupUpdt.popupWlce"/> <form:input path="popupWlc" maxlength="10" cssStyle="width:38px"/>  <spring:message code="ussIonPwm.popupUpdt.popupHlc"/><form:input path="popupHlc" maxlength="10" cssStyle="width:38px"/>
			  <form:errors path="popupWlc" cssClass="error"/>
			  <form:errors path="popupHlc" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupUpdt.popupSize"/> <span class="pilsu">*</span></th><!-- 팝업창사이즈 -->
			<td class="left">
			    <spring:message code="ussIonPwm.popupUpdt.popupWSize"/> <form:input path="popupWSize" maxlength="10" cssStyle="width:38px"/>  <spring:message code="ussIonPwm.popupUpdt.popupHSize"/><form:input path="popupHSize" maxlength="10" cssStyle="width:38px"/>
				<form:errors path="popupWSize" cssClass="error"/>
				<form:errors path="popupHSize" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><label for="ntceBgndeYYYMMDD"><spring:message code="ussIonPwm.popupUpdt.ntcePeriod"/> <span class="pilsu">*</span></label></th><!-- 게시기간 -->
			<td class="left">
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			    <input type="text" name="ntceBgndeYYYMMDD" id="ntceBgndeYYYMMDD" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(popupManageVO.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 6, 8)}"/>" readonly="readonly" style="width:78px" />
			    <form:select path="ntceBgndeHH">
			        <form:options items="${ntceBgndeHH}" itemValue="code" itemLabel="codeNm"/>
			    </form:select>H
			    <form:select path="ntceBgndeMM">
			        <form:options items="${ntceBgndeMM}" itemValue="code" itemLabel="codeNm"/>
			    </form:select>M
			    &nbsp&nbsp~&nbsp&nbsp
			    <input type="text" name="ntceEnddeYYYMMDD" id="ntceEnddeYYYMMDD" size="10" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(popupManageVO.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 6, 8)}"/>" readonly="readonly" style="width:78px" />
			    <form:select path="ntceEnddeHH">
			        <form:options items="${ntceEnddeHH}" itemValue="code" itemLabel="codeNm"/>
			    </form:select>H
			    <form:select path="ntceEnddeMM">
			        <form:options items="${ntceEnddeMM}" itemValue="code" itemLabel="codeNm"/>
			    </form:select>M
			</td>
		</tr>
		<tr>
			<th><label for="stopVewAt"><spring:message code="ussIonPwm.popupUpdt.stopVewAt"/> <span class="pilsu">*</span></label></th><!-- 그만보기 설정 여부 -->
			<td class="left">
			    <input id= "stopVewAt" type="radio" name="stopVewAt" value="Y" <c:if test="${popupManageVO.stopVewAt eq 'Y'}">checked</c:if>>Y
				<input type="radio" name="stopVewAt" value="N" <c:if test="${popupManageVO.stopVewAt eq 'N'}">checked</c:if>>N
			</td>
		</tr>
		<tr>
			<th><label for="ntceAt"><spring:message code="ussIonPwm.popupUpdt.ntceAt"/> <span class="pilsu">*</span></label></th><!-- 게시 상태 -->
			<td class="left">
			    <input id="ntceAt" type="radio" name="ntceAt" value="Y" <c:if test="${popupManageVO.ntceAt eq 'Y'}">checked</c:if>>Y
				<input type="radio" name="ntceAt" value="N" <c:if test="${popupManageVO.ntceAt eq 'N'}">checked</c:if>>N
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_PopupManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/pwm/listPopup.do' />" onclick=""><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<form:hidden path="ntceBgnde" />
<form:hidden path="ntceEndde" />
<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</form:form>

</body>
</html>
