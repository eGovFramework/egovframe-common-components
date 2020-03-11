<%--
  Class Name : EgovQustnrItemManageRegist.jsp
  Description : 설문항목 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.18    김예영          표준프레임워크 v3.7 개선

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
<c:set var="pageTitle"><spring:message code="comUssOlpQim.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<validator:javascript formName="qustnrItemManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrItemManage(){

	document.getElementById("iemSn").value = "1";
	//document.getElementById("qestnrEndDe").value = "2008-01-30";
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrItemManage(){
	location.href = "<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrItemManage(){
	var varFrom = document.getElementById("qustnrItemManageVO");

	if(confirm("<spring:message code='common.save.msg'/>")){

		varFrom.action =  "<c:url value='/uss/olp/qim/EgovQustnrItemManageRegist.do' />";

		
		if(varFrom.qestnrCn.value == "" ||
				varFrom.qestnrTmplatId.value == "" ||
				varFrom.qestnrId.value == ""
				){
			alert("<spring:message code='comUssOlpQim.regist.qestnrCn'/><spring:message code='comUssOlpQim.alert.input'/>"); //설문정보를  입력해주세요!
			varFrom.qestnrCn.focus();
			return;

		}
		if(varFrom.qestnrQesitmCn.value == "" ||
				varFrom.qestnrQesitmId.value == ""
				){
			alert("<spring:message code='comUssOlpQim.regist.qestnrQesitmCn'/><spring:message code='comUssOlpQim.alert.input'/>"); //설문문항정보를  입력해주세요!
			varFrom.qestnrQesitmCn.focus();
			return;

		}
		

		if(!validateQustnrItemManageVO(varFrom)){
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

 	window.showModalDialog("<c:url value='/uss/olp/qmc/EgovQustnrManageListPopup.do' />", self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");

 }
 /* ********************************************************
  * 설문지문항정보 팝업창열기
  ******************************************************** */
  function fn_egov_QustnrQestnManageListPopup_QustnrItemManage(){

	var sParam = "";
	sParam = sParam + "searchCondition=QESTNR_ID";
	sParam = sParam + "&searchKeyword=" + document.getElementById("qestnrId").value;

  	window.showModalDialog("<c:url value='/uss/olp/qqm/EgovQustnrQestnManageListPopup.do' />?" + sParam, self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");

  }
</script>

</head>
<body onLoad="fn_egov_init_QustnrItemManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="qustnrItemManageVO" action="${pageContext.request.contextPath}/uss/olp/qim/EgovQustnrItemManageRegist.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrItemManage(document.forms[0]); return false;">
 
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
		<!-- 설문정보 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.qestnrCn"/></c:set>
		<tr>
			<th><label for="qestnrCn">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<input name="qestnrCn" id="qestnrCn" type="text" size="73" value="" title="<spring:message code='comUssOlpQim.regist.qestnrCn'/><spring:message code='input.input'/>" maxlength="4000" style="width:300px;" disabled="disabled"><!-- title="설문정보 입력" -->
   				<a href="#LINK" onClick="fn_egov_QustnrManageListPopup_QustnrItemManage()">
			    <img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" onClick="" align="middle" style="border:0px" alt="<spring:message code='comUssOlpQim.regist.qestnrCn'/>" title="<spring:message code='comUssOlpQim.regist.qestnrCn'/>"><!-- alt="설문정보" title="설문정보" -->
			    </a>
			    <input name="qestnrId" id="qestnrId" type="hidden" value="">
			    <input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="">
			</td>
		</tr>
		<!-- 설문문항정보 -->	
		<c:set var="title"><spring:message code="comUssOlpQim.regist.qestnrQesitmCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="nopd">
				<input name="qestnrQesitmCn" id="qestnrQesitmCn" type="text" title="<spring:message code='comUssOlpQim.regist.qestnrQesitmCn'/><spring:message code='input.input'/>" size="73" value="" maxlength="4000" style="width:300px;" disabled="disabled"><!-- title="설문문항정보 입력" -->
   				<a href="#LINK" onClick="fn_egov_QustnrQestnManageListPopup_QustnrItemManage()">
  				<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" alt="<spring:message code='comUssOlpQim.regist.qestnrQesitmCn'/>" title="<spring:message code='comUssOlpQim.regist.qestnrQesitmCn'/>"><!-- alt="설문문항정보" title="설문문항정보" -->
  				</a>
  				<input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="">
			</td>
		</tr>
		<!-- 항목순번 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.iemSn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<input name="iemSn" id="iemSn" type="text" size="5" title="<spring:message code='comUssOlpQim.regist.iemSn'/>" value="" maxlength="5" style="width:100px;"><!-- title="항목 순번" -->
  				<div><form:errors path="iemSn" cssClass="error" /></div>
			</td>
		</tr>

		<!-- 항목내용 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.iemCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<textarea name="iemCn" class="textarea" title="<spring:message code='comUssOlpQim.regist.iemCn'/><spring:message code='input.input'/>" cols="75" rows="5"  style="width:100%;"></textarea><!-- title="항목 내용 입력" -->
  				<div><form:errors path="iemCn" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 기타답변여부 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.etcAnswerAt"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="etcAnswerAt" title="<spring:message code='comUssOlpQim.regist.etcAnswerAt'/><spring:message code='input.cSelect'/>"><!-- title="기타답변여부 선택" -->
   				<option value="N">N</option>
   				<option value="Y">Y</option>
  				</select>
			</td>
		</tr>
		
	</tbody>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 등록버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code='button.create' />" title="<spring:message code='button.create' /> <spring:message code='input.button' />" />
		<!-- 목록버튼 -->
		<span class="btn_s"><a href="<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />"  title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div><!-- div end(wTableFrm)  -->

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>



</body>
</html>
