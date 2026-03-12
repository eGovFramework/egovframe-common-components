<%
/**
 * @Class Name  : EgovVcatnUpdt.java
 * @Description : EgovVcatnUpdt.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영     퍼블리싱 점검
 * @ 2018.09.18    최두영       다국어처리
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
<title><spring:message code="comUssIonVct.vcatnUpdt.title"/></title><!-- 휴가수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEgovVcatnManageList(){
	location.href = "<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면 (휴가사유 등만 수정, 시작/종료일자는 수정 불가)
 ******************************************************** */
function fncUpdtVcatnManage(){
	var varForm = document.getElementById("vcatnManage") || document.forms["vcatnManage"];
	if (!validateVcatnManageVO(varForm)) {	
		return;
	}
	if(confirm("<spring:message code="common.save.msg" />")){
    	varForm.action = "<c:url value='/uss/ion/vct/updtVcatnManage.do'/>";
    	varForm.submit();
    }
}

function modalDialogCallback(retVal) {
	if(retVal != null){
		var tmp = retVal.split(",");
		var f = document.getElementById("vcatnManage") || document.forms["vcatnManage"];
		if(!f) return;
		var sanctnerIdEl = document.getElementById("sanctnerId") || f["vcatnManageVO.sanctnerId"] || f.sanctnerId;
		if(sanctnerIdEl) sanctnerIdEl.value = tmp[0];
		var sanctnDtNmEl = document.getElementById("sanctnDtNm");
		var orgnztNmEl = document.getElementById("orgnztNm");
		if(sanctnDtNmEl) sanctnDtNmEl.value = tmp[2];
		if(orgnztNmEl) orgnztNmEl.value = tmp[3];
		$('.ui-dialog-content').dialog('close');
	}
}
function openVcatnSanctnerDialog(title) {
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
	$('#VcatnSanctner').click(function (e) {
		e.preventDefault();
		openVcatnSanctnerDialog($(this).attr("data-dialog-title"));
	});
});
<c:if test="${!empty errorMessage}">
alert("<c:out value='${errorMessage}'/>");
</c:if>
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<div class="wTableFrm">

<form:form modelAttribute="vcatnManageVO" name="vcatnManage" id="vcatnManage" method="post">
<input type="hidden" name="applcntIdKey" value="<c:out value='${vcatnManageVO.applcntId}'/>" />
<input type="hidden" name="vcatnSeKey"   value="<c:out value='${vcatnManageVO.vcatnSe}'/>" />
<input type="hidden" name="bgndeKey"     value="<c:out value='${vcatnManageVO.bgnde}'/>" />
<input type="hidden" name="enddeKey"     value="<c:out value='${vcatnManageVO.endde}'/>" />
<form:hidden  path="applcntId" id="applcntId"/>
<form:hidden  path="bgnde"/>
<form:hidden  path="endde"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="occrrncYear"/>
<form:hidden  path="sanctnerId" id="sanctnerId"/>

	<!-- 타이틀 -->
	<h2 class=""><spring:message code="comUssIonVct.vcatnUpdt.title"/></h2><!-- 	휴가수정 -->
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.vcatnUpdt.vcatnAplyr"/></h3><!-- 휴가 신청자 -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="width:34%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.applcntNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.applcntNm}'/>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.orgnztNm}'/>
			</td>
		</tr>
	</table>

	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="width:34%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnTotalInfo"/></th><!-- 신청자 연차정보 -->
			<td class="left" colspan="4">
				<span>[<spring:message code="comUssIonVct.common.occrncYrycCo"/>: <c:out value="${vcatnManageVO.occrncYrycCo != null ? vcatnManageVO.occrncYrycCo : '0.0'}" />  ]</span><!-- 발생연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.useYrycCo"/>: <c:out value="${vcatnManageVO.useYrycCo != null ? vcatnManageVO.useYrycCo : '0.0'}" />  ]</span><!-- 사용연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.remndrYrycCo"/>: <c:out value="${vcatnManageVO.remndrYrycCo != null ? vcatnManageVO.remndrYrycCo : '0.0'}" />  ]</span><!-- 잔여연차 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnSe"/> <span class="pilsu">*</span></th><!-- 휴가구분 -->
			<td class="left" colspan="3">
				<c:if test="${vcatnManageVO.confmAt eq 'A'}">
					<form:select path="vcatnSe" title="휴가구분">
						<option value=""><spring:message code="common.select"/></option>
						<c:if test="${vcatnSeCode != null}">
							<form:options items="${vcatnSeCode}" itemValue="code" itemLabel="codeNm"/>
						</c:if>
					</form:select>
				</c:if>
				<c:if test="${vcatnManageVO.confmAt ne 'A'}">
					<c:out value='${vcatnManageVO.vcatnSeNm}'/>
					<form:hidden path="vcatnSe" />
				</c:if>
				<div><form:errors path="vcatnSe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.startDate"/> <span class="pilsu">*</span></th><!-- 시작일자 -->
			<td class="left">
				<c:out value='${vcatnManageVO.bgnde}'/>
			</td>
			<c:if test="${vcatnManageVO.vcatnSe ne '02'}">
				<th><spring:message code="comUssIonVct.common.endDate"/> <span class="pilsu">*</span></th><!-- 종료일자 -->
				<td class="left">
					<c:out value='${vcatnManageVO.endde}'/>
				</td>
			</c:if>
			<c:if test="${vcatnManageVO.vcatnSe eq '02'}">
				<th><spring:message code="comUssIonVct.common.noonSe"/> <span class="pilsu">*</span></th><!-- 정오구분 -->
				<td class="left">
					<c:if test="${vcatnManageVO.noonSe eq '1'}"><spring:message code="comUssIonVct.common.noonSe1"/></c:if><!-- 오전 -->
					<c:if test="${vcatnManageVO.noonSe eq '2'}"><spring:message code="comUssIonVct.common.noonSe2"/></c:if><!-- 오후 -->
				</td>
			</c:if>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnResn"/> <span class="pilsu">*</span></th><!-- 휴가사유 -->
			<td class="left" colspan="3">
			    <form:textarea path="vcatnResn" id="vcatnResn" class="txaClass" rows="4" cols="70" title='<spring:message code="comUssIonVct.common.vcatnResn"/>' /><!-- 휴가사유 -->
			    <div><form:errors path="vcatnResn" cssClass="error" /></div>
			</td>
		</tr>
	</table>	
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="width:34%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.sanctnDtNm"/> <span class="pilsu">*</span></th><!-- 결재권자명 -->
			<td class="left">
			    <input name="sanctnDtNm" id="sanctnDtNm" type="text" value="<c:out value='${vcatnManageVO.sanctnerNm}'/>" title='<spring:message code="comUssIonVct.common.sanctnDtNm"/>' readonly="readonly" style="width:128px" /><!-- 결재권자명 -->
			    <span class="link">
			    <a id="VcatnSanctner" href="#LINK" title='<spring:message code="comUssIonRwd.common.searchNm"/>' data-dialog-title="<spring:message code="comUssIonVct.common.infrmlSanctnRegist"/>" style="selector-dummy: expression(this.hideFocus=false);">
			    <img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" style="vertical-align: middle" alt='<spring:message code="comUssIonVct.common.sanctnDtNm"/>' title='<spring:message code="comUssIonVct.common.sanctnDtNm"/>'></a><!-- 결재권자 지정 -->
			    </span>
			    <div><form:errors path="sanctnerId" cssClass="error" /></div>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <input name="orgnztNm" id="orgnztNm" type="text" value="<c:out value='${empty infOrgnztNm ? vcatnManageVO.sanctnerOrgnztNm : infOrgnztNm}'/>" title='<spring:message code="comUssIonVct.common.orgnztNm"/>' readonly="readonly"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtVcatnManage(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>" title='<spring:message code="button.list" /> <spring:message code="input.button" />'><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>