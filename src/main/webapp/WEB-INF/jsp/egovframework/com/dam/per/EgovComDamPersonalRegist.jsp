<%
 /**
  * @Class Name  : EgovComDamPersonalRegist.jsp
  * @Description : EgovComDamPersonalRegist 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.08.12  박종선          최초 생성
  *   2011.08.12    정진오		"지식유형명" 항목 필수 항목 표시 image가 보이도록 수정함
  *   2011.10.07    이기하		조직명 선택 후 화면 재로딩되는 오류 주석처리
  *   2018.09.11  신용호          공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스팀
  *  @since 2010.05.01
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comDamPer.comDamPersonalRegist.title"/></title><!-- 개인지식 등록 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="knoPersonal" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
<!--
function initCalendar(){
	$("#vcolYmd").datepicker(
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'both'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
	         , buttonImageOnly: true
	        
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
			
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
	$("#vcolYmd").change(function() {
		$("#colYmd").val($(this).val().replace(/-/gi,""));
	});
}

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_knoPersonal(){
	
	initCalendar();
	var maxFileNum = document.getElementById('posblAtchFileNumber').value;

	   if(maxFileNum==null || maxFileNum==""){

	     maxFileNum = 3;

	    }

	   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );

	   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );

	// 첫 입력란에 포커스..
	knoPersonal.orgnztId.focus();

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_KnoPersonal(){
	location.href = "<c:url value='/dam/per/EgovComDamPersonalList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_KnoPersonal(form){
	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateKnoPersonal(form)){
			return;
		}else{
			form.cmd.value = "Regist";
			form.action = "<c:url value='/dam/per/EgovComDamPersonalRegist.do'/>";
			form.submit();
		}
	}
}
/* ********************************************************
 * 지식유형 가져오기
 ******************************************************** */
function fn_egov_get_CodeId(form){
 	form.cmd.value = "";
 	form.submit();
}
-->
</script>
</head>

<body onLoad="fn_egov_initl_knoPersonal();">

<form:form commandName="knoPersonal" name="knoPersonal" method="post" enctype="multipart/form-data">

<div class="wTableFrm">
	<!-- 타이틀 -->
<h2><spring:message code="comDamPer.comDamPersonalRegist.pageTop.title"/></h2><!-- 개인지식 등록 -->

<!-- 등록폼 -->
<table class="wTable">
	<colgroup>
		<col style="width:16%" />
		<col style="" />
	</colgroup>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
		<td class="left">
		    <select name="orgnztId" class="select" >
			<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
			<c:forEach var="knoPersonal" items="${mapTeamList}" varStatus="status">
			<option value='<c:out value="${knoPersonal.orgnztId}"/>' <c:if test="${knoPersonal.orgnztId == mapMaterial.orgnztId}">selected="selected"</c:if> ><c:out value="${knoPersonal.orgnztNm}"/></option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
		<td class="left">
		    <select name="knoTypeCd" class="select">
			<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
			<c:forEach var="knoPersonal" items="${mapMaterialList}" varStatus="status">
			<option value='<c:out value="${knoPersonal.knoTypeCd}"/>'><c:out value="${knoPersonal.knoTypeNm}"/></option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
		<td class="left">
		    <form:input  path="knoNm" title="<spring:message code='comDamPer.comDamPersonalRegist.knoNm'/>" size="60" maxlength="60"/>
      		<form:errors path="knoNm"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
		<td class="left">
		    <form:textarea  path="knoCn" title="<spring:message code='comDamPer.comDamPersonalRegist.knoCn'/>" cols="300" rows="10" cssClass="txaClass"/><!-- 지식내용 -->
      		<form:errors path="knoCn"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.colYmd"/> <span class="pilsu">*</span></th><!-- 수집일자 -->
		<td class="left">
		    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
   			<input id="colYmd" name="colYmd" type="hidden" value=""/>
			<input id="vcolYmd" name="vcolYmd" type="text" title="<spring:message code="comDamPer.comDamPersonalRegist.colYmd"/>" value="" maxlength="10" readonly="readonly" style="width:70px"/><!-- 수집일자 -->
		</td>
	</tr>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.othbcAt"/></th><!-- 공개여부 -->
		<td class="left">
		    <spring:message code="comDamPer.comDamPersonalRegist.public" /> : <input type="radio" name="othbcAt" class="radio2" value="Y">&nbsp;
	     	<spring:message code="comDamPer.comDamPersonalRegist.private" /> : <input type="radio" name="othbcAt" class="radio2" value="N" checked="checked"/><br/>
	     	<form:errors path="othbcAt" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="comDamPer.comDamPersonalRegist.fileUpload"/></th><!-- 파일첨부 -->
		<td class="left">
		    <input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comDamPer.comDamPersonalRegist.fileUpload"/>" multiple/><!-- 첨부파일명 입력 -->
		    <div id="egovComFileList"></div>
		</td>
	</tr>
</table>

<!-- 하단 버튼 -->
<div class="btn">
	<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_KnoPersonal(document.knoPersonal); return false;" /><!-- 저장 -->
	<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_KnoPersonal(); return false;" /><!-- 목록 -->
</div>
<div style="clear:both;"></div>
</div>

<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
<!-- <input name="cmd" type="hidden" value="<c:out value='save'/>"> -->
<input name="cmd" type="hidden" value="<c:out value='Regist'/>">

</form:form>

</body>
</html>