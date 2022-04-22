<%--
  Class Name : EgovQustnrRespondManageRegist.jsp
  Description : 응답자정보 등록 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.19    김예영          표준프레임워크 v3.7 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09
   
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQrm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="qustnrRespondManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrRespondManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrRespondManage(){
	location.href = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrRespondManage(){
		
	//var varFrom = document.forms[0];
		//document.qustnrRespondManageVO;
	var varFrom = document.getElementById("qustnrRespondManageVO")
	if(confirm("<spring:message code='common.save.msg'/>")){
		
		//varFrom.action =  "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageRegist.do'/>";
		//varFrom.action =  "${pageContext.request.contextPath}/uss/olp/qrm/EgovQustnrRespondManageRegist.do";
		document.getElementById("qustnrRespondManageVO").action = "${pageContext.request.contextPath}/uss/olp/qrm/EgovQustnrRespondManageRegist.do";
		//varFrom.brth.value = fn_egov_SelectBoxValue('brthYYYY') + "" + fn_egov_SelectBoxValue('brthMM') + "" + fn_egov_SelectBoxValue('brthDD');
		 
		if(document.getElementById("qestnrCn").value == "" ||
				document.getElementById("qestnrTmplatId").value == "" ||
				document.getElementById("qestnrId").value == ""  
				){
			alert("<spring:message code='comUssOlpQrm.regist.qestnrCn' /><spring:message code='comUssOlpQrm.alert.input' />"); //설문관리정보를  입력해주세요!
			document.getElementById("qestnrCn").focus();
			return;
		}
		
		if(!validateQustnrRespondManageVO(varFrom)){ 
			
			return;
		}else{
			
			//varFrom.submit();
			document.getElementById("qustnrRespondManageVO").submit();
		} 
		
	}
	
} 

/* ********************************************************
 * 설문지정보 팝업창열기
 ******************************************************** */
 function fn_egov_QustnrManageListPopup_QustnrItemManage(){

 	window.showModalDialog("<c:url value='/uss/olp/qmc/EgovQustnrManageListPopup.do' />", self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
 	
 }
/************************************************************************
//셀렉트박스 값 컨트롤 함수
************************************************************************/
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
</script>

</head>
<body onLoad="fn_egov_init_QustnrRespondManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="qustnrRespondManageVO" id="qustnrRespondManageVO" action="" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrRespondManage(); return false;">
 
<div class="wTableFrm">
	
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width:25%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문관리정보 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.qestnrCn"/></c:set>
		<tr>
			<th><label for="qestnrCn">${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<input name="qestnrCn" id="qestnrCn" type="text" title="<spring:message code='comUssOlpQrm.regist.qestnrCn' /><spring:message code='input.input' />" size="73" value="" maxlength="4000" style="width:300px;" disabled="disabled"><!-- title="설문관리정보 입력" -->
			    <a href="#LINK" onClick="fn_egov_QustnrManageListPopup_QustnrItemManage()">   	
				<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" onClick=""align="middle" style="border:0px" alt="<spring:message code='comUssOlpQrm.regist.qestnrCn' /><spring:message code='input.input' />" title="<spring:message code='comUssOlpQrm.regist.qestnrCn' /><spring:message code='input.input' />"><!-- alt="설문관리정보" title="설문관리정보" -->
				</a>
				<input name="qestnrId" id="qestnrId" type="hidden" value="">
				<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="">
			</td>
		</tr>
		<!-- 성별 -->	
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.sexdstnCode"/></c:set>
		<tr>
			<th><label for="sexdstnCode">${title}<span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="sexdstnCode" title="<spring:message code='comUssOlpQrm.regist.sexdstnCode' /><spring:message code='input.cSelect' />"><!-- title="성별 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<form:options items="${comCode014}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="sexdstnCode"/></div> 
			</td>
		</tr>
		<!-- 직업 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.occpTyCode"/></c:set>
		<tr>
			<th><label for="occpTyCode">${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:select path="occpTyCode" title="<spring:message code='comUssOlpQrm.regist.occpTyCode' /><spring:message code='input.cSelect' />"><!-- title="직업 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<form:options items="${comCode034}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="occpTyCode"/></div> 
			</td>
		</tr>
		<!-- 응답자명 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.respondNm"/></c:set>
		<tr>
			<th><label for="respondNm">${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<input name="respondNm" type="text" title="<spring:message code='comUssOlpQrm.regist.respondNm' /><spring:message code='input.input' />" size="73" value="" maxlength="50" style="width:120px;"><!-- title="응답자명 입력" -->
				<div><form:errors path="respondNm" cssClass="error" /></div>
			</td>
		</tr>

	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 등록버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code='button.create' />" title="<spring:message code='button.create' /> <spring:message code='input.button' />" />
		<!-- 목록버튼 -->
		<span class="btn_s"><a href="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do' />"  title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
	<input name="cmd" type="hidden" value="<c:out value='save'/>">



</div><!-- div end(wTableFrm)  -->
</form:form>



</body>
</html>