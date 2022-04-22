<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCcmAdministCodeModify.jsp
  * @Description : EgovCcmAdministCodeModify 화면
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
<c:set var="pageTitle"><spring:message code="comSymCcmAdc.ccmAdministCode.title"/> <spring:message code="title.update" /></c:set>
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
	 function fn_egov_modify_AdministCode(form){
			var creatDe = document.administCode.creatDe.value;
			var ablDe   = document.administCode.ablDe.value;
			
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
				form.submit();
			}
		}
	}
	
	/* ********************************************************
	 * 행정코드 팝업 창 열기
	 ******************************************************** */
	function fn_egov_AdministCodePopup(upperAdministZoneCode,upperAdministZoneNm){
	
		var retVal;
	
		var url = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodePopup.do' />";
	
		var varParam = new Object();
		//varParam.administZoneSe = administCode.administZoneSe.value;
		varParam.administZoneSe = ${administCode.administZoneSe};
		
		// IE
		//var openParam = "dialogWidth:252px;dialogHeight:175px;scroll:no;status:no;center:yes;resizable:yes;";
	
		// FIREFOX
		var openParam = "dialogWidth:750px;dialogHeight:500px;scroll:no;status:no;center:yes;resizable:yes;";
	
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
	
	function fnInit() {
		document.administCode.vcreatDe.value = "<c:out value='${fn:substring(administCode.creatDe, 0,4)}'/>-<c:out value='${fn:substring(administCode.creatDe, 4,6)}'/>-<c:out value='${fn:substring(administCode.creatDe, 6,8)}'/>";
		document.administCode.vablDe.value   = "<c:out value='${fn:substring(administCode.ablDe, 0,4)}'/>-<c:out value='${fn:substring(administCode.ablDe, 4,6)}'/>-<c:out value='${fn:substring(administCode.ablDe, 6,8)}'/>";
	}

</script>
</head>
<body onLoad="fnInit();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="administCode" name="administCode" method="post">
<input name="cmd" type="hidden" value="Modify">
<form:hidden path="administZoneSe"/>
<form:hidden path="administZoneCode"/>

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
			    <select name="administZoneSe" disabled title="행정구역구분">
					<option value="1" <c:if test="${administCode.administZoneSe == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddr" /></option>
					<option value="2" <c:if test="${administCode.administZoneSe == '2'}">selected="selected"</c:if> ><spring:message code="comSymCcmAdc.ccmAdministCode.admAddr" /></option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.administCode" /> <span class="pilsu">*</span></th> <!-- 행정구역코드 -->
			<td class="left">
			    ${administCode.administZoneCode}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.administName" /> <span class="pilsu">*</span></th> <!-- 행정구역명 -->
			<td class="left">
			    <form:input  path="administZoneNm" size="60" maxlength="60" title="행정구역명"/>
				<form:errors path="administZoneNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.createDate" />  <span class="pilsu">*</span></th> <!-- 생성일자 -->
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
			    <input  name="vablDe" size="10" maxlength="10" readonly="readonly" onclick="fn_egov_NormalCalendar(document.administCode, document.administCode.ablDe, document.administCode.vablDe);" title="폐기일자(새창)" style="width:68px" />
				<a href="#noscript" onclick="fn_egov_NormalCalendar(document.administCode, document.administCode.ablDe, document.administCode.vablDe); return false;" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />" alt="달력창팝업버튼이미지"></a>
				<form:hidden path="ablDe"/>
				<form:errors path="ablDe"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.upperCode" /></th> <!-- 상위행정구역코드 -->
			<td class="left">
			    <input  name="upperAdministZoneNm" maxlength="60" value="${administCode.upperAdministZoneNm}" readonly="readonly" onclick="fn_egov_AdministCodePopup(administCode.upperAdministZoneCode,administCode.upperAdministZoneNm)" title="상위행정구역코드" style="width:300px" />
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
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_AdministCode(document.administCode); return false;" />
		<input class="s_submit" type="submit" value="<spring:message code="title.list" />" onclick="fn_egov_list_AdministCode(); return false;" />
	</div>
	<div style="clear:both;"></div>
</div>



</form:form>

</body>
</html>
