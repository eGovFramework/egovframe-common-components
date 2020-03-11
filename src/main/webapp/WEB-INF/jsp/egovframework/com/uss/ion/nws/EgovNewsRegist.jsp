<%
 /**
  * @Class Name : EgovNewsRegist.jsp
  * @Description : EgovNewsRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssIonNws.newsVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="newsVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

$(function() {
	$("#ntceDe").datepicker(   
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
});

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	//------------------------------------------
	//------------------------- 첨부파일 등록 Start
	//-------------------------------------------
	//var maxFileNum = 3;
	//var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum, 'file_label' );
	//multi_selector.addElement( document.getElementById( 'egovfile_1' ) );
	//------------------------- 첨부파일 등록 End
	
	// 첫 입력란에 포커스
	document.getElementById("newsVO").newsSj.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_news(form){
	
	//input item Client-Side validate
	if (!validateNewsVO(form)) {	
		return false;
	} else {
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}

</script>

</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="newsVO" action="${pageContext.request.contextPath}/uss/ion/nws/insertNews.do" method="post" onSubmit="fn_egov_regist_news(document.forms[0]); return false;" enctype="multipart/form-data"> 
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 뉴스 제목   -->
		<c:set var="title"><spring:message code="comUssIonNws.newsVO.newsSj"/> </c:set>
		<tr>
			<th><label for="newsSj">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="newsSj" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="newsSj" cssClass="error" /></div>     
			</td>
		</tr>

		<!-- 뉴스 내용  -->
		<c:set var="title"><spring:message code="comUssIonNws.newsVO.newsCn"/> </c:set>
		<tr>
			<th><label for="newsCn">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="newsCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="newsCn" cssClass="error" /></div>  
			</td>
		</tr>

		<!-- 뉴스 출처   -->
		<c:set var="title"><spring:message code="comUssIonNws.newsVO.newsOrigin"/> </c:set>
		<tr>
			<th><label for="newsOrigin">${title} </label></th>
			<td class="left">
			    <form:input path="newsOrigin" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="newsOrigin" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 게시날짜  -->
		<c:set var="title"><spring:message code="comUssIonNws.newsVO.ntceDe"/> </c:set>
		<tr>
			<th><label for="ntceDe">${title} </label></th>
			<td class="left" colspan="3">
				<form:input path="ntceDe" title="${title} ${inputTxt}" maxlength="10" readonly="true" style="width:70px;"/>
				<div><form:errors path="ntceDe" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 첨부파일  -->
		<c:set var="title"><spring:message code="comUssIonNws.newsVO.atchFile"/></c:set>
		<tr>
			<th><label for="file_1">${title}</label> </th>
			<td class="nopd">
				<input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comUssIonNws.newsVO.atchFile"/>"/><!-- 첨부파일 -->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/nws/selectNewsList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->  
<script type="text/javascript">
var maxFileNum = document.getElementById('posblAtchFileNumber').value;
   if(maxFileNum==null || maxFileNum==""){
        maxFileNum = 3;
   }
   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script> 
<!-- 첨부파일 업로드 가능화일 설정 End.-->

</body>
</html>
