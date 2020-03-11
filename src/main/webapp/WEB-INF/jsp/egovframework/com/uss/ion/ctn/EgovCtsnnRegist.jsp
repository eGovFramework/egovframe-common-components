<%
/**
 * @Class Name : EgovCtsnnRegist.java
 * @Description : EgovCtsnnRegist.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.16    최 두 영     퍼블리싱 점검/오류개선
 * @ 2018.09.18    최 두 영     다국어처리
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonCtn.ctsnnRegist.title"/></title><!-- 경조사 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="ctsnnManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

	function initCalendar(){
		$("#occrrDe").datepicker(
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
		
		$("#brth").datepicker(
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
	function fncEgovCtsnnManageList(){
		location.href = "<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>";
	}

	/* ********************************************************
	 * 저장처리화면
	 ******************************************************** */
	function fncInsertEgovCtsnnManage() {
	    var varFrom = document.getElementById("ctsnnManage");
	    varFrom.action = "<c:url value='/uss/ion/ctn/insertCtsnnManage.do'/>";
	    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
	        if(!validateCtsnnManage(varFrom)){           
	            return;
	        }else{
	           varFrom.submit();
	        } 
	    }
	}
	
	function modalDialogCallback(retVal) {
		if(retVal != null){

			var tmp = retVal.split(",");
			document.ctsnnManage.usid.value = tmp[0];
			document.getElementById("usNm").value=tmp[2];
			document.getElementById("usOrgnztNm").value=tmp[3];
			
			document.ctsnnManage.action = "<c:url value='/uss/ion/ctn/EgovCtsnnRegist.do'/>";
			$('.ui-dialog-content').dialog('close');
		}
	}
	 $(document).ready(function () {
	        $('#CtsnnRegist').click(function (e) {
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

<form:form commandName="ctsnnManage" name="ctsnnManage" method="post" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonCtn.ctsnnRegist.ctsnnApply"/></h2><!-- 경조사 신청 -->
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnRegist.ctsnnApply"/></h3><!-- 경조 신청자 -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.usNm"/> <span class="pilsu">*</span></th><!-- 신청자 -->
			<td class="left">
			    <input name="usNm" id="usNm" type="text" title="<spring:message code="comUssIonCtn.ctsnnRegist.ctsnnApply"/>" readonly="readonly" style="width:128px" /><!-- 경조신청자 -->
		        <form:hidden path="usid"/>
			    <span class="link">
			    <a id="CtsnnRegist" title="<spring:message code="comUssIonRwd.common.searchNm"/>" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />"
	     			style="vertical-align: middle" alt="<spring:message code="comUssIonCtn.ctsnnRegist.ctsnnApply"/>" title="<spring:message code="comUssIonCtn.ctsnnRegist.ctsnnApply"/>"></a><!-- 경조신청자 -->
	     		</span>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.usOrgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <input name="usOrgnztNm" id="usOrgnztNm" type="text" value="" title="<spring:message code="comUssIonCtn.ctsnnRegist.usOrgnztNm"/>" readonly="readonly"/>
			</td>
		</tr>
	</table>

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.ctsnnNm"/> <span class="pilsu">*</span></th><!-- 경조명 -->
			<td class="left" colspan="3">
			    <form:input  path="ctsnnNm" title="경조명" maxlength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.ctsnnCd"/> <span class="pilsu">*</span></th><!-- 경조구분 -->
			<td class="left">
			    <form:select path="ctsnnCd" title="경조구분">
			      <form:options items="${ctsnnCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.occrrDe"/> <span class="pilsu">*</span></th><!-- 발생일 -->
			<td class="left">
			    <form:input path="occrrDe" title="경조 발생일" maxlength="10" readonly="true"  cssStyle="width:70px"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.trgterNm"/> <span class="pilsu">*</span></th><!-- 대상자명 -->
			<td class="left" colspan="3">
			    <form:input  path="trgterNm" title="대상자명" maxlength="20" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.relate"/> <span class="pilsu">*</span></th><!-- 관계 -->
			<td class="left">
			    <form:select path="relate" title="관계">
			      <form:options items="${relateCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.brth"/> <span class="pilsu">*</span></th><!-- 생년월일 -->
			<td class="left">
			    <form:input path="brth" title="생년월일" maxlength="10" readonly="true"  cssStyle="width:70px"/>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.remark"/> </th><!-- 비고 -->
			<td class="left" colspan="3">
			    <form:textarea path="remark" rows="4" cols="70" cssClass="txaClass" title="비고"/>
      			<form:errors   path="remark"/>
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnId"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 지정 Include -->
	<c:import url="/WEB-INF/jsp/egovframework/com/uss/ion/ism/EgovInfrmlSanctnRegist.jsp" charEncoding="utf-8"/>
	<!-- //결재권자 지정 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fncInsertEgovCtsnnManage(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>?searchCondition=1" onclick="fncEgovCtsnnManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>