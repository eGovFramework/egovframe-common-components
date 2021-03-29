<!DOCTYPE html>
<%
/**
 * @Class Name : EgovRwardRegist.java
 * @Description : EgovRwardRegist.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
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
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonRwd.rwardRegist.title"/></title><!-- 포상등록 -->
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
	function fncInsertEgovRwardManage() {
	    var varFrom = document.getElementById("rwardManage");
	    varFrom.action = "<c:url value='/uss/ion/rwd/insertRwardManage.do'/>";
	    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
	        if(!validateRwardManage(varFrom)){           
	            return;
	        }else{
	             varFrom.submit();
	        } 
	    }
	}
			
	/* ********************************************************
	* 아이디  팝업창열기
	* fn_egov_schdulCharger_LeaderSchdule
	* ID 폼명, 사원번호 폼명, 사원명 폼명, 부서명 폼명
	* ******************************************************** */
	function modalDialogCallback(retVal) {
		if(retVal != null){

			var tmp = retVal.split(",");
			
			document.rwardManage.rwardManId.value = tmp[0];
			document.rwardManage.rwardManNm.value =  tmp[2];
			document.rwardManage.rwardManOrgnztNm.value = tmp[3]
			
			document.rwardManage.action = "<c:url value='/uss/ion/rwd/EgovRwardRegist.do'/>";
			document.rwardManage.submit();
		}
	}
	
	 $(document).ready(function () {
	        $('#RwardRegist').click(function (e) {
	        	e.preventDefault();
	            
	            var pagetitle = $(this).attr("title");
	            var page = "<c:url value='/uss/ion/ism/selectSanctnerListNew.do'/>";
	        	
	            var $dialog = $('<div></div>')
		            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
		            .dialog({
		            	autoOpen: false,
		                modal: true,
		                height: 750,
		                width: 770,
		                title: pagetitle
		        	});
	        	$dialog.dialog('open');
	    	});
		});
	
</script>
</head>
<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="rwardManage" name="rwardManage" method="post" enctype="multipart/form-data">
<!-- 첨부파일 개수 설정을 위한 Hidden 설정 -->	
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonRwd.rwardRegist.rwardAppl"/></h2><!-- 포상신청 -->
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonRwd.common.searchNm"/></h3><!-- 포상자 -->

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
			    <%-- <input name="rwardManNm" id="rwardManNm" type="text" size="20" title="<spring:message code="comUssIonRwd.common.searchNm"/>" readonly="readonly" style="width:128px" /> --%><!-- 포상자 -->
		        <form:input path="rwardManNm"  readonly="true" maxlength="10" size="20" title="포상자명" cssStyle="width:128px" />
		        <form:hidden path="rwardManId"/>
			    <span class="link">
			    <a id="RwardRegist" title="<spring:message code="comUssIonRwd.common.searchNm"/>" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />"
	     			style="vertical-align: middle" alt="<spring:message code="comUssIonRwd.common.searchNm"/>" title="<spring:message code="comUssIonRwd.common.searchNm"/>"></a><!-- 포상자 -->
	     			</span>
			</td>
			<th><spring:message code="comUssIonRwd.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
				<input name="rwardManOrgnztNm" id="rwardManOrgnztNm" type="text" value="" title="<spring:message code="comUssIonRwd.common.orgnztNm"/>" readonly="readonly" style="width:128px" /><!-- 소속 -->
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
				 <c:set var="rwardNm"><spring:message code="comUssIonRwd.common.rwardNm"/></c:set><!-- 포상명 -->
			    <form:input  path="rwardNm" title="${rwardNm}"/>
			</td>
			<th><spring:message code="comUssIonRwd.common.rwardDe"/> <span class="pilsu">*</span></th><!-- 포상일자 -->
			<td class="left">
				 <c:set var="rwardDe"><spring:message code="comUssIonRwd.common.rwardDe"/></c:set>
				<form:input path="rwardDe" maxlength="10" readonly="true"  title="${rwardDe}" style="width:68px" /><!-- 포상일자 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.pblenCn"/> <span class="pilsu">*</span></th><!-- 공적사항 -->
			<td class="left" colspan="3">
				 <c:set var="pblenCn"><spring:message code="comUssIonRwd.common.pblenCn"/></c:set>
			    <form:textarea path="pblenCn" rows="4" cols="70" cssClass="txaClass" title="${pblenCn}"/><!-- 공적사항 -->
      			<form:errors path="pblenCn"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.atchFileId"/></th><!-- 첨부파일 -->
			<td class="left" colspan="3">
			    <input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comUssIonRwd.common.atchFileId"/>"/><!-- 첨부파일 -->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonRwd.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 지정 Include -->
	<c:import url="/WEB-INF/jsp/egovframework/com/uss/ion/ism/EgovInfrmlSanctnRegist.jsp" charEncoding="utf-8"/>
	<!-- //결재권자 지정 Include -->
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncInsertEgovRwardManage(); return false;" />
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