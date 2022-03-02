<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCcmAdministCodeRegist.jsp
  * @Description : EgovCcmAdministCodeRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comSymCcmAdc.ccmAdministCode.title"/> <spring:message code="title.create" /></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="administCode" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />" ></script>
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_AdministCode(){
		location.href = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeList.do' />";
	}
	
	/* ********************************************************
	 * 저장처리화면
	 ******************************************************** */
	 function fn_egov_regist_AdministCode(form){
		var creatDe = document.administCode.creatDe.value;
		var ablDe   = document.administCode.ablDe.value.replace(" ", "").replace("-","");
	
		if (creatDe > ablDe && (ablDe != "" && ablDe != "                    ")) {
			alert("생성일, 폐기일 전후가 잘못되었습니다.\n확인 후 처리하시오.");
			//abort;
			document.administCode.ablDe.value = "";
			administCode.vablDe.value = "";
			return false;
		}
		if(confirm("<spring:message code='common.save.msg'/>")){
			if(!validateAdministCode(form)){
				return;
			}else{
				form.cmd.value = "Regist";
				form.submit();
			}
		}
	}
	
	/* ********************************************************
	 * 행정코드 팝업 창 열기
	 ******************************************************** */
	function fn_egov_AdministCodePopup(upperAdministZoneCode,upperAdministZoneNm){
		//var administZoneSe = administCode.administZoneSe.value;
		var administZoneSe = document.getElementById("administCode").administZoneSe.value;
	
		if (administZoneSe == "") {
			alert("행정구역코드를 선택하시오.");
		}
	
		var retVal;
	
		var url = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodePopup.do' />";
	
		var varParam = new Object();
		varParam.administZoneSe = administZoneSe;
	
		// IE
		//var openParam = "dialogWidth:252px;dialogHeight:175px;scroll:no;status:no;center:yes;resizable:yes;";
		// FIREFOX
		var openParam = "dialogWidth:700px;dialogHeight:500px;scroll:no;status:no;center:yes;resizable:yes;";
	
		retVal = window.showModalDialog(url, varParam, openParam);
		
		if(retVal) {
			upperAdministZoneCode.value = retVal.administZoneCode;
			upperAdministZoneNm.value   = retVal.administZoneNm;
		}
	}

	function showModalDialogCallback(retVal) {
		 
		if(retVal) {
    		document.administCode.upperAdministZoneCode.value = retVal.administZoneCode;
    		document.administCode.upperAdministZoneNm.value   = retVal.administZoneNm;
		}
	}
	
	function fn_egov_Administ_Zone_Change() {
		document.administCode.upperAdministZoneCode.value = "";
		document.administCode.upperAdministZoneNm.value   = "";
	}
	
	/* ********************************************************
	* 서버 작업 후 혹은 마이페이지 로딩 시 메세지 화면에 보여주기
	******************************************************** */
	function fnShowMessg(){
	    if("<c:out value='${message}'/>" != ''){
	    alert("<c:out value='${message}'/>");
		document.administCode.creatDe.value = "";
		document.administCode.ablDe.value = "";
	    }
	}
</script>
</head>
<body onLoad="javascript:fnShowMessg();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="administCode" name="administCode" method="post">
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.cls" /> <span class="pilsu">*</span></th> <!-- 행정구역구분 -->
			<td class="left">
			    <select id="administZoneSe" name="administZoneSe" onchange="fn_egov_Administ_Zone_Change()" title="행정구역구분">
					<option value="1"><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddr" /></option>
					<option value="2"><spring:message code="comSymCcmAdc.ccmAdministCode.admAddr" /></option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.administCode" /> <span class="pilsu">*</span></th> <!-- 행정구역코드 -->
			<td class="left">
			    <form:input  path="administZoneCode" maxlength="10" title="행정구역코드" cssStyle="width:68px"/>
				<form:errors path="administZoneCode"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.administName" /> <span class="pilsu">*</span></th> <!-- 행정구역명 -->
			<td class="left">
			    <form:input  path="administZoneNm" maxlength="60" title="행정구역명" cssStyle="width:50%"/>
				<form:errors path="administZoneNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.createDate" /> <span class="pilsu">*</span></th> <!-- 생성일자 -->
			<td class="left">
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
				<input name="vcreatDe" maxlength="10" readonly="readonly" onclick="fn_egov_NormalCalendar(document.administCode, document.administCode.creatDe, document.administCode.vcreatDe);" title="생성일자(새창)" style="width:68px" />
				<a href="#noscript" onclick="fn_egov_NormalCalendar(document.administCode, document.administCode.creatDe, document.administCode.vcreatDe); return false;" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />" alt="달력창팝업버튼이미지"></a>
				<form:hidden path="creatDe"/>
				<form:errors path="creatDe"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.deleteDate" /></th> <!-- 폐기일자 -->
			<td class="left">
			    <input name="vablDe" maxlength="10" readonly="readonly" onclick="fn_egov_NormalCalendar(document.administCode, document.administCode.ablDe, document.administCode.vablDe);" title="폐기일자(새창)" style="width:68px"/>
				<a href="#noscript" onclick="fn_egov_NormalCalendar(document.administCode, document.administCode.ablDe, document.administCode.vablDe); return false;" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />" alt="달력창팝업버튼이미지"></a>
				<form:hidden path="ablDe"/>
				<form:errors path="ablDe"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.upperCode" /></th> <!-- 상위행정구역코드 -->
			<td class="left">
			    <input  name="upperAdministZoneNm" maxlength="60" readonly="readonly" onclick="fn_egov_AdministCodePopup(administCode.upperAdministZoneCode,administCode.upperAdministZoneNm);" title="상위행정구역코드"/>
				<a href="#noscript" onclick="fn_egov_AdministCodePopup(administCode.upperAdministZoneCode,administCode.upperAdministZoneNm); return false;" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />" alt="행정코드찾기"></a>
				<form:hidden  path="upperAdministZoneCode"/>
				<form:errors path="upperAdministZoneCode"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.useAt" /> <span class="pilsu">*</span></th> <!-- 사용여부 -->
			<td class="left">
			    <form:select path="useAt" title="사용여부">
					<form:option value="Y" label="Yes"/>
					<form:option value="N" label="No"/>
				</form:select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_AdministCode(document.administCode); return false;" /> <!-- 저장 -->
		<span class="btn_s"><a href="#noscript" onclick="fn_egov_list_AdministCode(); return false;"><spring:message code="title.list" /></a></span> <!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>




</form:form>

</body>
</html>
