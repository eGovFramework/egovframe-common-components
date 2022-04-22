<%
 /**
  * @Class Name  : EgovComDamPersonalModify.jsp
  * @Description : EgovComDamPersonalModify 화면
  * @Modification Information
  * @
  * @ 수정일             수정자           수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.08.17  박종선          최초 생성
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comDamPer.comDamPersonalModify.title"/></title><!-- 개인지식 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="knoPersonal" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
<!--
function initCalendar(){
	$("#colYmd").datepicker(
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
	knoPersonal.knoNm.focus();

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
function fn_egov_modify_KnoPersonal(){

	var form = document.knoPersonal;

	if(confirm("<spring:message code="common.save.msg" />")){

		form.action = "<c:url value='/dam/per/EgovComDamPersonalModify.do'/>";

		if(!validateKnoPersonal(form)){
			return;
		}else{
			form.submit();
		}
	}
}
-->
</script>
</head>

<body onLoad="fn_egov_initl_knoPersonal();">


<!-- 파일첨부를 위한 폼명 및 Enctype 설정 -->
<form:form modelAttribute="knoPersonal" name="knoPersonal" action="<c:url value='/dam/per/EgovComDamPersonalModifyView.do'/>" method="post" enctype="multipart/form-data">

<input name="cmd" type="hidden" value="Modify">
<input name="knoId" type="hidden" value="<c:out value='${knoPersonal.knoId}'/>">
<form:hidden path="orgnztId"/>
<form:hidden path="knoTypeCd"/>
<form:hidden path="orgnztNm"/>
<form:hidden path="knoTypeNm"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comDamPer.comDamPersonalModify.pageTop.title"/></h2><!-- 개인지식 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
			<td class="left">
			    ${knoPersonal.orgnztNm}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
			<td class="left">
			    ${knoPersonal.knoTypeNm}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
			<td class="left">
			    <form:input  path="knoNm" title="<spring:message code='comDamPer.comDamPersonalModify.knoNm'/>" size="60" maxlength="60"/>
		      	<form:errors path="knoNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
			<td class="left">
			    <textarea name="knoCn" class="textarea" title="<spring:message code="comDamPer.comDamPersonalModify.knoCn"/>" cols="300" rows="10">${knoPersonal.knoCn}</textarea><!-- 지식내용 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.colYmd"/> <span class="pilsu">*</span></th><!-- 수집일자 -->
			<td class="left">
			    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
		  		<input id="colYmd" name="colYmd" type="text" title="<spring:message code="comDamPer.comDamPersonalModify.colYmdCalendar"/>" value="${knoPersonal.colYmd}"  readonly="readonly" style="width:70px" /><!-- 수집일달력 -->
	      		
	      		<div><form:errors path="colYmd"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.othbcAt"/></th><!-- 공개여부 -->
			<td class="left">
			    <spring:message code="comDamPer.comDamPersonalModify.public" /> : <input type="radio" name="othbcAt" class="radio2" value="Y"
		     	<c:if test="${knoPersonal.othbcAt == 'Y'}"> checked="checked"</c:if> />&nbsp;
		     	<spring:message code="comDamPer.comDamPersonalModify.private" /> : <input type="radio" name="othbcAt" class="radio2" value="N"
		     	<c:if test="${knoPersonal.othbcAt == 'N'}"> checked="checked"</c:if> />
		     	<br/><form:errors path="othbcAt" />
			</td>
		</tr>
		<!-- 첨부파일 테이블 레이아웃 설정 Start -->
		<c:if test="${knoPersonal.atchFileId ne ''}">
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalModify.atchFileId"/></th><!-- 첨부파일목록 -->
				<td>
					<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${knoPersonal.atchFileId}" />
					</c:import>
				</td>
			</tr>
		</c:if>
		<!-- 첨부파일 테이블 레이아웃 End -->
		<tr>
			<th><spring:message code="comDamPer.comDamPersonalModify.fileUpload"/></th><!-- 파일첨부 -->
			<td class="left">
			    <input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comDamPer.comDamPersonalModify.fileUpload"/>"/><!-- 첨부파일명 입력 -->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_KnoPersonal(); return false;" /><!-- 저장 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_KnoPersonal(); return false;" /><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	</div>

	<c:if test="${knoPersonal.atchFileId eq '' or knoPersonal.atchFileId eq null}">
 	<input type="hidden" name="fileListCnt" value="0" />
 	<input name="atchFileAt" type="hidden" value="N">
 	</c:if>

 	<c:if test="${knoPersonal.atchFileId ne '' and knoPersonal.atchFileId ne null}">
 	<input name="atchFileAt" type="hidden" value="Y">
 	</c:if>

<!-- 첨부파일 개수 설정을 위한 Hidden 설정 -->
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
<input type="hidden" name="returnUrl" value="<c:url value='/dam/per/EgovComDamPersonalModifyView.do'/>" >
</form:form>
	
</body>
</html>