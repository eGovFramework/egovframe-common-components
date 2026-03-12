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
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonCtn.ctsnnRegist.title"/></title><!-- 경조사 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
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
	    var varForm = document.getElementById("ctsnnManageVO") || document.forms["ctsnnManageVO"];
	    if(!validateCtsnnManage(varForm)){           
		return;
	    }
	    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
	    	varForm.action = "<c:url value='/uss/ion/ctn/insertCtsnnManage.do'/>";
	    	varForm.submit(); 
	    }
	}
	
	function modalDialogCallback(retVal) {
		if(retVal != null){
			var tmp = retVal.split(",");
			var f = document.getElementById("ctsnnManageVO") || document.forms["ctsnnManageVO"];
			if(!f) return;
			f.sanctnerId.value = tmp[0];
			document.getElementById("sanctnDtNm").value = tmp[2];
			document.getElementById("orgnztNm").value = tmp[3];
			$('.ui-dialog-content').dialog('close');
		}
	}
	function openCtsnnSanctnerDialog(title) {
		var page = "<c:url value='/uss/ion/ism/selectSanctnerListNew.do'/>";
		var $dialog = $('<div></div>')
			.html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
			.dialog({
				autoOpen: false,
				modal: true,
				height: 750,
				width: 770,
				title: title
			});
		$dialog.dialog('open');
	}
	$(document).ready(function () {
		initCalendar();
		$('#CtsnnSanctner').click(function (e) {
			e.preventDefault();
			openCtsnnSanctnerDialog($(this).attr("data-dialog-title"));
		});
	});	
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="ctsnnManageVO" name="ctsnnManageVO" id="ctsnnManageVO" method="post" >

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
			<th><spring:message code="comUssIonCtn.ctsnnRegist.usNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.usNm != null ? ctsnnManageVO.usNm : ""}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.usOrgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.orgnztNm != null ? ctsnnManageVO.orgnztNm : ""}'/>
			</td>
		</tr>
	</table>
	<form:hidden path="usid"/>

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
			    <form:input path="ctsnnNm" title="경조명" maxlength="100" />
			    <div><form:errors path="ctsnnNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.ctsnnCd"/> <span class="pilsu">*</span></th><!-- 경조구분 -->
			<td class="left">
			    <form:select path="ctsnnCd" title="경조구분">
			      <form:options items="${ctsnnCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			    <div><form:errors path="ctsnnCd" cssClass="error" /></div>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.occrrDe"/> <span class="pilsu">*</span></th><!-- 발생일 -->
			<td class="left">
			    <form:input path="occrrDe" title="경조 발생일" maxlength="10" readonly="true" cssStyle="width:70px"/>
			    <div><form:errors path="occrrDe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.trgterNm"/> <span class="pilsu">*</span></th><!-- 대상자명 -->
			<td class="left" colspan="3">
			    <form:input path="trgterNm" title="대상자명" maxlength="20" />
			    <div><form:errors path="trgterNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.relate"/> <span class="pilsu">*</span></th><!-- 관계 -->
			<td class="left">
			    <form:select path="relate" title="관계">
			      <form:options items="${relateCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			    <div><form:errors path="relate" cssClass="error" /></div>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.brth"/> <span class="pilsu">*</span></th><!-- 생년월일 -->
			<td class="left">
			    <form:input path="brth" title="생년월일" maxlength="10" readonly="true" cssStyle="width:70px"/>
			    <div><form:errors path="brth" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.remark"/> </th><!-- 비고 -->
			<td class="left" colspan="3">
			    <form:textarea path="remark" rows="4" cols="70" cssClass="txaClass" title="비고"/>
			    <div><form:errors path="remark" cssClass="error" /></div>
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnId"/></h3><!-- 결재권자 -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnNm"/> <span class="pilsu">*</span></th><!-- 결재권자명 -->
			<td class="left">
			    <input name="sanctnDtNm" id="sanctnDtNm" type="text" value="<c:out value='${ctsnnManageVO.sanctnerNm}'/>" title='<spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnNm"/>' readonly="readonly" style="width:128px" /><!-- 결재권자명 -->
		        <form:hidden path="sanctnerId" id="sanctnerId"/>
			    <span class="link">
			    <a id="CtsnnSanctner" href="#LINK" title='<spring:message code="comUssIonRwd.common.searchNm"/>' data-dialog-title="<spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnId"/>" style="selector-dummy: expression(this.hideFocus=false);">
			    <img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" style="vertical-align: middle" alt='<spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnNm"/>' title='<spring:message code="comUssIonCtn.ctsnnRegist.infrmlSanctnNm"/>'></a><!-- 결재권자 지정 -->
			    </span>
			    <div><form:errors path="sanctnerId" cssClass="error" /></div>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnRegist.usOrgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <input name="orgnztNm" id="orgnztNm" type="text" value="<c:out value='${ctsnnManageVO.sanctnerOrgnztNm}'/>" title='<spring:message code="comUssIonCtn.ctsnnRegist.usOrgnztNm"/>' readonly="readonly"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncInsertEgovCtsnnManage(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>?searchCondition=1" onclick="fncEgovCtsnnManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>