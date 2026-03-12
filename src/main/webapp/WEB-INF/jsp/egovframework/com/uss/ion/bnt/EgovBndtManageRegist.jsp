<%
/**
 * @Class Name : EgovBndtManageRegist.java
 * @Description : EgovBndtManageRegist jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영         퍼블리싱 검증
 * @ 2018.09.27    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
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
<title><spring:message code="comUssIonBnt.bndtManageRegist.title"/></title><!-- 당직  등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_bndtManage(form){
	//input item Client-Side validate
	if (!validateBndtManage(form)) {	
		return;
	}
	if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
		form.submit();
	}
}

function modalDialogCallback(retVal) {
	if(retVal != null){
		var tmp = retVal.split(",");
		document.getElementById("bndtId").value = tmp[0];
		document.getElementById("bndtIdName").value=tmp[2];
		$('.ui-dialog-content').dialog('close');
	}
}

$(document).ready(function () {
	$('#BndtRegist').click(function (e) {
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

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form modelAttribute="bndtManageVO" method="post" action="${pageContext.request.contextPath}/uss/ion/bnt/insertBndtManage.do" onSubmit="fn_egov_regist_bndtManage(document.forms[0]); return false;">
<input name="cmd" type="hidden" value="insert"/>
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonBnt.bndtManageRegist.title"/></h2><!-- 당직  등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tr>
			<th><label for="bndtId"><spring:message code="comUssIonBnt.common.bndtIdName"/> <span class="pilsu">*</span></label></th><!-- 당직자명 -->
			<td class="left">
			  <input name="bndtIdName" id="bndtIdName" type="text"  title="<spring:message code="comUssIonBnt.common.bndtIdName"/>" readonly="readonly" style="width:128px"/><!-- 당직자명 -->
		      <a id="BndtRegist" href="#" title="<spring:message code="comUssIonBnt.common.bndtIdName"/>" style="selector-dummy: expression(this.hideFocus=false);">
		      <img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" style="vertical-align: middle" alt="<spring:message code="comUssIonBnt.common.bndt"/>" title="<spring:message code="comUssIonBnt.common.bndt"/>"></a>
		      <form:hidden  path="bndtId"/>
		      <div><form:errors path="bndtId" cssClass="error"/></div>		      
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <input name="orgnztNm" id="orgnztNm" type="text" title="<spring:message code="comUssIonBnt.common.orgnztNm"/>" class="readOnlyClass" readonly="readonly" style="width:128px" /><!-- 소속 -->
			</td>
		</tr>
		<tr>
			<th><label for="bndtDe"><spring:message code="comUssIonBnt.common.bndtDe"/> <span class="pilsu">*</span></label></th><!-- 당직일자 -->
			<td class="left">
			    <form:input  path="bndtDe" size="10" maxlength="10" title="<spring:message code='comUssIonBnt.common.bndtDe'/>" readonly="true" style="width:70px" /><!-- 당직일자 -->
      			<div><form:errors path="bndtDe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><label for="remark"><spring:message code="comUssIonBnt.common.remark"/></label></th><!-- 비고 -->
			<td class="left">
			    <form:textarea path="remark" rows="4" cols="70" cssClass="txaClass" title="<spring:message code='comUssIonBnt.common.remark'/>" /><!-- 비고 -->
      			<div><form:errors path="remark" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>" onclick="fncBndtManageList(); return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>

</body>
</html>
