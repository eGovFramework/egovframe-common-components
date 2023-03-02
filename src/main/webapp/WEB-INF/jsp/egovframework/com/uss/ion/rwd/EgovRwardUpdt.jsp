<%
/**
 * @Class Name  : EgovRwardUpdt.java
 * @Description : EgovRwardUpdt.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.09.19    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonRwd.rwardUpdt.title"/></title><!-- 포상수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="rwardManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--

function initCalendar(){
	$("#rwardDe").datepicker(
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
function fncEgovRwardManageList(){
	location.href = "<c:url value='/uss/ion/rwd/selectRwardManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fncUpdtRwardManage() {
    var varFrom = document.getElementById("rwardManage");
    varFrom.action = "<c:url value='/uss/ion/rwd/updtRwardManage.do'/>";
    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
        if(!validateRwardManage(varFrom)){           
            return;
        }else{
        	varFrom.cmd.value="detail";
             varFrom.submit();
        } 
    }
}
-->
</script>
</head>

<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="rwardManage" name="rwardManage" method="post" enctype="multipart/form-data">
<form:hidden  path="rwardId"/>
<form:hidden  path="rwardManId"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="sanctnerId"/>
	<c:if test="${rwardManageVO.atchFileId eq null || rwardManageVO.atchFileId eq ''}">
	 	<input type="hidden" name="fileListCnt" value="0" />
	 	<input type="hidden" name="atchFileAt" value="N">
	</c:if> 
	
	<c:if test="${rwardManageVO.atchFileId ne null && rwardManageVO.atchFileId ne ''}">
	 	<input type="hidden" name="atchFileAt" value="Y"> 
	</c:if> 
<input type="hidden" name="cmd" value="updt"/>
<input type="hidden" name="returnUrl"           value="<c:url value='/uss/ion/rwd/EgovRwardManageDetail.do'/>" />
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />  
<input type="hidden" name="rwardManNm" value="<c:out value='${rwardManageVO.rwardManNm}'/>">
<input type="hidden" name="orgnztNm" value="<c:out value='${rwardManageVO.orgnztNm}'/>">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonRwd.rwardUpdt.title"/></h2><!-- 포상수정 -->

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonRwd.common.searchNm"/> <span class="pilsu">*</span></th><!-- 포상자 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardManNm}'/>
			</td>
			<th><spring:message code="comUssIonRwd.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${rwardManageVO.orgnztNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.searchKeyword"/> <span class="pilsu">*</span></th><!-- 포상구분 -->
			<td class="left" colspan="3">
   				<c:set var="rwardCd"><spring:message code="comUssIonRwd.common.searchKeyword"/></c:set>
				<form:select path="rwardCd" title="${rwardCd}"><!-- 포상구분 -->
			      <form:options items="${rwardCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.rwardNm"/> <span class="pilsu">*</span></th><!-- 포상명 -->
			<td class="left">
				<c:set var="rwardNm"><spring:message code="comUssIonRwd.common.rwardNm"/></c:set>
			    <form:input  path="rwardNm" id="rwardNm" title="${rwardNm}"/>
			</td>
			<th><spring:message code="comUssIonRwd.common.rwardDe"/> <span class="pilsu">*</span></th><!-- 포상일자 -->
			<td class="left">
				 <c:set var="rwardDe"><spring:message code="comUssIonRwd.common.rwardDe"/></c:set>
				<form:input path="rwardDe" maxlength="10" readonly="true"  title="${rwardDe}" cssStyle="width:70px"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.pblenCn"/> <span class="pilsu">*</span></th><!-- 공적사항 -->
			<td class="left" colspan="3">
				 <c:set var="pblenCn"><spring:message code="comUssIonRwd.common.pblenCn"/></c:set>
			    <form:textarea path="pblenCn" rows="4" cols="70" cssClass="txaClass" title="${pblenCn}"/><!-- 공적사항 -->
      			<form:errors   path="pblenCn"/>
			</td>
		</tr>
		<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
		<c:if test="${rwardManageVO.atchFileId ne null && rwardManageVO.atchFileId ne ''}">
		<tr>
			<th><spring:message code="comUssIonRwd.rwardUpdt.atchFileIdList"/></th><!-- 첨부파일목록 -->
			<td class="left" colspan="3">
			    <!-- c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" -->
				<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${egovc:encrypt(rwardManageVO.atchFileId)}" />
				</c:import>
				&nbsp
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.atchFileId"/></th><!-- 첨부파일 -->
			<td class="left">
				    <input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comUssIonRwd.common.atchFileId"/>"/><!-- 첨부파일명 입력 -->
			    	<div id="egovComFileList"></div>
			</td>
		</tr>
		</c:if>
		<!-- 첨부파일 테이블 레이아웃 End.-->
	</table>

	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonRwd.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 정보 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8">
		<c:param name="infrmlSanctnId" value="${rwardManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 정보 Include -->
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtRwardManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/rwd/selectRwardManageList.do'/>?searchCondition=1" onclick="fncEgovRwardManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
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