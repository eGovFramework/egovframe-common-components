<%--
  Class Name : EgovQustnrRespondManageModify.jsp
  Description : 응답자정보 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.20    김예영          표준프레임워크 v3.7 개선

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
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
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
	var varFrom = document.qustnrRespondManageVO;

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageModify.do' />";

		//varFrom.brth.value = fn_egov_SelectBoxValue('brthYYYY') + "" + fn_egov_SelectBoxValue('brthMM') + "" + fn_egov_SelectBoxValue('brthDD');

		if(!validateQustnrRespondManageVO(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	}
}
/* ********************************************************
 * 설문지정보 팝업창열기
 ******************************************************** */
 function fn_egov_QustnrManageListPopup_QustnrItemManage(){

 	window.showModalDialog("<c:url value='/uss/olp/qmc/EgovQustnrManageListPopup.do' />", self,"dialogWidth800px;dialogHeight500px;resizableyes;centeryes");

 }
 /* ********************************************************
  * 설문지문항정보 팝업창열기
  ******************************************************** */
  function fn_egov_QustnrQestnManageListPopup_QustnrItemManage(){

	var sParam = "";
	sParam = sParam + "searchCondition=QESTNR_ID";
	sParam = sParam + "&searchKeyword=" + document.getElementById("qestnrId").value;

  	window.showModalDialog("<c:url value='/uss/olp/qrm/EgovQustnrQestnManageListPopup.do' />?" + sParam, self,"dialogWidth800px;dialogHeight500px;resizableyes;centeryes");

  }
/* ********************************************************
 * 설문항목정보 팝업창열기
 ******************************************************** */
function fn_egov_QustnrItemManageListPopup_QustnrItemManage(){

	var sParam = "";
	sParam = sParam + "searchCondition=QESTNR_QESITM_ID";
	sParam = sParam + "&searchKeyword=" + document.getElementById("qestnrQesitmId").value;

  	window.showModalDialog("<c:url value='/uss/olp/qim/EgovQustnrItemManageListPopup.do' />?" + sParam, self,"dialogWidth800px;dialogHeight500px;resizableyes;centeryes");

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
<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form modelAttribute="qustnrRespondManageVO" name="qustnrRespondManageVO" action="${pageContext.request.contextPath}/uss/olp/qrm/EgovQustnrRespondManageModify.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrItemManage(document.forms[0]); return false;">
 
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문관리정보 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 성별 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.sexdstnCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<select name="sexdstnCode" title="<spring:message code='comUssOlpQrm.regist.sexdstnCode'/><spring:message code='input.cSelect'/>"> <!-- title="성별 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach items="${comCode014}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList[0].sexdstnCode}">selected</c:if>>${comCodeList.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 직업 -->	
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.occpTyCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="occpTyCode" title="<spring:message code='comUssOlpQrm.regist.occpTyCode'/><spring:message code='input.cSelect'/>"><!-- title="직업 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList[0].occpTyCode}">selected</c:if>>${comCodeList.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 응답자명 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.respondNm"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<input name="respondNm" type="text" title="<spring:message code='comUssOlpQrm.regist.respondNm'/><spring:message code='input.cSelect'/>" size="73" value="${resultList[0].respondNm}" maxlength="50" style="width:120px;"><!-- title="응답자명 입력 "-->
				<div><form:errors path="respondNm" cssClass="error" /></div> 
    		</td>
		</tr>		
	</tbody>
	</table>


<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_egov_save_QustnrRespondManage(this.form); return false;"/>
	<!-- <span class="button"><input type="submit" value="저장" onclick="fn_egov_save_QustnrRespondManage(document.forms[0]); return false;"></span> -->
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

<input name="qestnrRespondId" type="hidden" value="${resultList[0].qestnrRespondId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

</form:form>
</body>
</html>