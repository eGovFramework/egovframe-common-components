<%--
  Class Name : EgovQustnrRespondInfoManageRegist.jsp
  Description : 설문조사(설문등록) 등록 페이지
  Modification Information

      수정일               수정자             수정내용
   ----------   --------    ---------------------------
   2008.03.09   장동한             최초 생성
   2017.07.18   김예영             표준프레임워크 v3.7 개선
   2019.05.16   신용호             소스 정리

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
<c:set var="pageTitle"><spring:message code="comUssOlpQnn.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="qustnrRespondInfoManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrRespondInfo(){
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrRespondInfo(){
	location.href = "/uss/olp/qnn/EgovQustnrRespondInfoManageList.do";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrRespondInfo(){

	
	//var varFrom = document.getElementsByName("qustnrRespondInfoManage");
	var varFrom = document.getElementById("qustnrRespondInfoManage");
	
	//설문응답자  Validtation

	/* if(document.getElementsByName("sexdstnCode").selectedIndex == 0){
		alert('<spring:message code="comUssOlpQnn.alert.sexdstnCode"/>'); //설문응답자정보 성별을  택해주세요!
		varFrom.getElementsByName("sexdstnCode").focus();
		return;
	}else if(document.getElementsByName("occpTyCode").selectedIndex == 0){
		alert('<spring:message code="comUssOlpQnn.alert.occpTyCode"/>'); //설문응답자정보 직업을 선택해주세요!
		varFrom.getElementsByName("occpTyCode").focus();
		return;
	}else if(document.getElementsByName("respondNm").value == ""){
		alert('<spring:message code="comUssOlpQnn.alert.respondNm"/>'); //설문응답자정보 응답자명을 입력해주세요!
		varFrom.getElementsByName("respondNm").focus();
		return;
	} */
	
	/* if(document.getElementsByName("sexdstnCode").selectedIndex == 0){
		alert('<spring:message code="comUssOlpQnn.alert.sexdstnCode"/>'); //설문응답자정보 성별을  택해주세요!
		document.getElementsByName("sexdstnCode").focus();
		return;
	}else if(document.getElementsByName("occpTyCode").selectedIndex == 0){
		alert('<spring:message code="comUssOlpQnn.alert.occpTyCode"/>'); //설문응답자정보 직업을 선택해주세요!
		document.getElementsByName("occpTyCode").focus();
		return;
	}else if(document.getElementsByName("respondNm").value == ""){
		alert('<spring:message code="comUssOlpQnn.alert.respondNm"/>'); //설문응답자정보 응답자명을 입력해주세요!
		document.getElementsByName("respondNm").focus();
		return;
	}  */

	if(!validateQustnrRespondInfoManage(varFrom)){
		return;
	}
	
	//설문정보 Validtation
	<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
	<c:if test="${QestmInfo.qestnTyCode ==  '1'}">
	if((!fn_egov_selectBoxChecking("${QestmInfo.qestnrQesitmId}"))){
		alert('${status1.count}'+'<spring:message code="comUssOlpQnn.alert.qestnIem"/>'); //${status1.count}번 설문문항을 작성해 주세요!
		document.getElementsByName("${QestmInfo.qestnrQesitmId}")[0].focus();
		return;
	}

		<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status01">
		<c:if test="${QestmInfo.qestnrTmplatId eq QestmItem.qestnrTmplatId && QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">

			<c:if test="${QestmItem.etcAnswerAt eq  'Y'}">
			//기타답변을 선택했는지 체크
			if(fn_egov_RadioBoxValue("${QestmInfo.qestnrQesitmId}") == "${QestmItem.qustnrIemId}"){
				if(document.getElementById("ETC_${QestmItem.qustnrIemId}").value == ""){
					alert('${status1.count}'+'<spring:message code="comUssOlpQnn.alert.etcAnswer"/>'); //${status1.count}번 설문문항 기타답변을 작성해주세요!
					document.getElementById("ETC_${QestmItem.qustnrIemId}").focus();
					return;
				}
			}

			</c:if>
		</c:if>
		</c:forEach>
	</c:if>


	<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
	if( document.getElementById("${QestmInfo.qestnrQesitmId}").value == "" ){
		//alert('${status1.count}. ${QestmInfo.qestnCn}       \n\n설문문항을 작성해 주세요!');
		alert('${status1.count}'+'<spring:message code="comUssOlpQnn.alert.qestnIem"/>'); //${status1.count}번 설문문항을 작성해 주세요!
		document.getElementById("${QestmInfo.qestnrQesitmId}").focus();
		return;
	}
	</c:if>
	</c:forEach>

	
	if(confirm("<spring:message code='common.save.msg'/>")){
		//varFrom.brth.value = fn_egov_SelectBoxValue('brthYYYY') + "" + fn_egov_SelectBoxValue('brthMM') + "" + fn_egov_SelectBoxValue('brthDD');
		//varFrom.action =  "${pageContext.request.contextPath}/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do";
		varFrom.action =  "<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do'/>";
		varFrom.submit();
		
		
	}
	
}
/************************************************************************
//라디오박스 : 몇개선택했는데 체크해주는함수
************************************************************************/
function fn_egov_checkbox_amout_max( sbName){
	debugger;
	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	var reusltCount = 0;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reusltCount++;
		}
	}

	return reusltCount;

}
/************************************************************************
//라디오박스 : 최대선택건수 체크
************************************************************************/
function fn_egov_checkbox_amout( sbName, sbCount, sbObj){
debugger;
	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	var reusltCount = 0;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reusltCount++;
		}
	}

	if(reusltCount > sbCount){
	 	alert("<spring:message code='comUssOlpQnn.alert.mxmmChoiseCo' /> [" + sbCount + "]<spring:message code='comUssOlpQnn.alert.exceed' />" );
	 	sbObj.checked=false;
	 	return;
	}
}

/************************************************************************
//셀렉트 박스 선택했는 찾는 함수
************************************************************************/

function fn_egov_selectBoxChecking(sbName){

	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reuslt=true;
		}
	}

	return reuslt;
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

/************************************************************************
//라디오박스 체크 박스
************************************************************************/
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
</script>

</head>
<body onLoad="fn_egov_init_QustnrRespondInfo();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form name="qustnrRespondInfoManage" id="qustnrRespondInfoManage" action="${pageContext.request.contextPath}/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do" method="post">
  
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>
	<br>
	<!-- 서브타이틀  -->
	<h3><spring:message code="comUssOlpQnn.regist.respondInfo" /></h3><!-- 설문응답자 정보 -->
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 성별 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.sexdstnCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="sexdstnCode" title="<spring:message code='comUssOlpQnn.regist.sexdstnCode'/> <spring:message code='input.cSelect'/>"><!-- title="성별 선택" -->
					<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
					<c:forEach items="${comCode014}" var="comCodeList" varStatus="status">
					<option value="${comCodeList.code}" >${comCodeList.codeNm}</option>
					<!-- 선택 대신 '여자'가 select되어 있도록 할 때  -->
					<%-- <option value="${comCodeList.code}" <c:if test="${comCodeList.code eq Emplyrinfo.sexdstnCode}">selected</c:if>>${comCodeList.codeNm}</option> --%>
					</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 직업 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.occpTyCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="occpTyCode" title="<spring:message code='comUssOlpQnn.regist.occpTyCode'/> <spring:message code='input.cSelect'/>"><!-- title="직업 선택 "-->
					<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
					<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
					<option value="${comCodeList.code}">${comCodeList.codeNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 응답자명 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.respondNm"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<input name="respondNm" type="text" title="<spring:message code='comUssOlpQnn.regist.respondNm'/> <spring:message code='input.input'/>" size="73" value="${Emplyrinfo.emplyrNm}" maxlength="50" style="width:120px;"><!-- title="응답자명 입력" -->
			</td>
		</tr>
		
	</tbody>
	</table>
	<!-- 등록  폼 영역  -->
	<!-- 서브타이틀  -->
	<h3><spring:message code="comUssOlpQnn.regist.qestnrInfo"/></h3><!-- 설문정보 -->
	
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:20%;" >
		<col style="width: ;" >		
	</colgroup>
	<tbody >	
		<!-- 설문제목 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.qestnrSj"/></c:set>
		<tr>
			<th>${title}<span class="pilsu"></span></th>
			<td class="left">
  				<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 설문목적 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.qestnrPurps"/></c:set>
		<tr>
			<th>${title}<span class="pilsu"></span></th>
			<td class="left">
  				<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrPurps , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 설문작성 안내내용 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.qestnrWritngGuidanceCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu"></span></th>
			<td class="left">
  				<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 설문대상, 설문기간 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.qestnrTrgetAndDe"/></c:set>
		<tr>
			<!-- <th>  <span class="pilsu"></span></th> -->
			<td class="left">
				<!-- 설문대상 -->
  				<b><spring:message code="comUssOlpQnn.regist.qestnrTrget"/>  :</b>
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '1'}"><spring:message code="comUssOlpQnn.regist.student"/></c:if><!-- 학생 -->
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '2'}"><spring:message code="comUssOlpQnn.regist.univStudent"/></c:if><!-- 대학생 -->
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '3'}"><spring:message code="comUssOlpQnn.regist.salaryMan"/></c:if><!-- 직장인 -->
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '4'}"><spring:message code="comUssOlpQnn.regist.soldier"/></c:if><!-- 군인 -->
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '5'}"><spring:message code="comUssOlpQnn.regist.teacher"/></c:if><!-- 교사 -->
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '6'}"><spring:message code="comUssOlpQnn.regist.etc"/></c:if><!-- 기타 -->
			</td>
			<td class="left">
				<!-- 설문기간 -->
				<b><spring:message code="comUssOlpQnn.regist.qestnrDate"/>  :</b>
    				<c:out value="${Comtnqestnrinfo[0].qestnrBeginDe}" /> ~ <c:out value="${Comtnqestnrinfo[0].qestnrEndDe}" />
			</td>
		</tr>
		<!-- 설문조사 -->
		<c:set var="title"><spring:message code="comUssOlpQnn.regist.qestnrTmplatCours"/></c:set>
		<c:import charEncoding="utf-8" url="/uss/olp/qri/template/template.do" >
		<c:param name="templateUrl" value="${QustnrTmplatManage[0].qestnrTmplatCours}" />
		</c:import>
		
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 등록버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code='button.create' />" title="<spring:message code='button.create' /> <spring:message code='input.button' />"  onclick="fn_egov_save_QustnrRespondInfo(document.forms[0]); return false;"/>
		<%-- <span class="button"><input type="submit" value="<spring:message code='input.button' />" onclick="fn_egov_save_QustnrRespondInfo(document.forms[0]); return false;"></span> --%>
		<!-- 목록버튼 -->
		<span class="btn_s"><a href="<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageList.do' />"  title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qestnrId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">
	
</div><!-- div end(wTableFrm)  -->

</form:form>



</body>
</html>