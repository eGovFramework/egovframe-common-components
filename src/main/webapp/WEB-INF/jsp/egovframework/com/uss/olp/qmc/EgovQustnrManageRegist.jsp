<%--
  Class Name : EgovQustnrManageRegist.jsp
  Description : 설문관리 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.14    김예영          표준프레임워크 v3.7 개선

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
<c:set var="pageTitle"><spring:message code="comUssOlpQmc.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="qustnrManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrManage(){

	//document.getElementById("qestnrBeginDe").value = "2008-01-01";
	//document.getElementById("qestnrEndDe").value = "2008-01-30";
	
	$("#qestnrBeginDe").datepicker(  
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


	$("#qestnrEndDe").datepicker( 
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
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrManage(){
	location.href = "<c:url value='/uss/olp/qmc/EgovQustnrManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrManage(form){

	var sStartDay = form.qestnrBeginDe.value.replaceAll("-","");
	var sEndDay = form.qestnrEndDe.value.replaceAll("-","");

	var iStartDay = parseInt(sStartDay);
	var iEndDay = parseInt(sEndDay);

	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateQustnrManageVO(form)){
			return;
		}else{
			if(iStartDay > iEndDay || iEndDay < iStartDay){
				alert("<spring:message code='comUssOlpQmc.alert.startDay'/>"+ "\n\n"+"<spring:message code='comUssOlpQmc.alert.endDay'/>");//설문기간  시작일은 종료일  보다 클수 없고 \n\n설문기간 종료일은 시작일 보다 작을수 없습니다!
				return;
			}
			form.submit();
		}
	}
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
<body onLoad="fn_egov_init_QustnrManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="qustnrManageVO" action="${pageContext.request.contextPath}/uss/olp/qmc/EgovQustnrManageRegist.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrManage(document.forms[0]); return false;">
<!-- 첨부파일 개수 설정을 위한 Hidden 설정 -->
 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>
	<span>※ <spring:message code='comUssOlpQmc.regist.qestnrTmplatDesc'/></span><!-- 템플릿 유형이 없는 경우 "설문템플릿관리"메뉴에서 등록을 해야 합니다. -->
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
		<!-- 설문제목 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrSj"/></c:set>
		<tr>
			<th><label for="qestnrSj">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<form:input type="text" path="qestnrSj" size="70" cssClass="txaIpt" maxlength="100" title="<spring:message code='comUssOlpQmc.regist.qestnrSj'/><spring:message code='input.input'/>" /><!-- title="설문제목 입력" -->
      			<div><form:errors path="qestnrSj"/></div>
			</td>
		</tr>
		<!-- 설문목적 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrPurps"/></c:set>
		<tr>
			<th><label for="qestnrPurps">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<form:textarea path="qestnrPurps" height="50" rows="50" cols="75" cssClass="txaClass" title="<spring:message code='comUssOlpQmc.regist.qestnrPurps'/><spring:message code='input.input'/>"/><!-- title="설문목적 입력" -->
    			<div><form:errors path="qestnrPurps"/></div>
			</td>
		</tr>
		<!-- 설문작성안내 내용 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrWritngGuidanceCn"/></c:set>
		<tr>
			<th><label for="qestnrWritngGuidanceCn">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<form:textarea path="qestnrWritngGuidanceCn" height="50" rows="50" cols="75" cssClass="txaClass" title="<spring:message code='comUssOlpQmc.regist.qestnrTmplatInfo'/><spring:message code='input.input'/>"/><!-- title="템플릿설명 입력" -->
    			<div><form:errors path="qestnrWritngGuidanceCn"/></div>
			</td>
		</tr>
		<!-- 설문대상 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrTrget"/></c:set>
		<tr>
			<th><label for="qestnrTrget">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<form:select path="qestnrTrget" title="<spring:message code='comUssOlpQmc.regist.qestnrTrget'/><spring:message code='input.cSelect'/>"><!-- title="설문대상 선택" -->
				<option value=""><spring:message code='input.cSelect'/></option><!-- 선택 -->
		    	<form:options items="${comCode034}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="qestnrTrget"/></div>
			</td>
		</tr>	
		<!-- 설문기간 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrDe"/></c:set>
		<tr>
			<th><label for="qestnrBeginDe">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:input path="qestnrBeginDe" size="10" maxlength="10" readonly="true" style="width:70px;" />
				<form:errors path="qestnrBeginDe" cssClass="error"/>
				 ~ <form:input path="qestnrEndDe" size="10" maxlength="10" readonly="true" style="width:70px;" />
				<form:errors path="qestnrEndDe" cssClass="error"/>
			</td>
		</tr>				
		<!-- 템플릿 유형 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrTmplatTy"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" >
				<table style="border:0px; cellspacing:0px; cellpadding:0px; align:center;" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
				<c:forEach items="${listQustnrTmplat}" var="resultQustnrTmplat" varStatus="status">
				<td><img src="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageImg.do' />?qestnrTmplatId=${resultQustnrTmplat.qestnrTmplatId}" align="middle" alt="<spring:message code='comUssOlpQmc.regist.qestnrTmplatTy'/><spring:message code='comUssOlpQmc.title.image'/>" title="<spring:message code='comUssOlpQmc.regist.qestnrTmplatTy'/><spring:message code='comUssOlpQmc.title.image'/>"></td><!-- alt="템플릿유형 이미지" title="템플릿유형 이미지" -->
				</c:forEach>
				</tr>
				<tr>
				<c:forEach items="${listQustnrTmplat}" var="resultQustnrTmplat" varStatus="status">
			 	<td height="10" align="center"><input type="radio" name="qestnrTmplatId" value="${resultQustnrTmplat.qestnrTmplatId}" style="border:0px" checked>${resultQustnrTmplat.qestnrTmplatTy}</td>
				</c:forEach>
				</tr>
				</table>
			</td>
		</tr>
		
	</tbody>
	</table>
		
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 등록버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code='button.create' />" title="<spring:message code='button.create' /> <spring:message code='input.button' />" />
		<!-- 목록버튼 -->
		<span class="btn_s"><a href="<c:url value='/uss/olp/qmc/EgovQustnrManageList.do' />"  title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>

<input name="cmd" type="hidden" value="<c:out value='save'/>">

	
</div><!-- div end(wTableFrm)  -->



</form:form>



</body>
</html>
