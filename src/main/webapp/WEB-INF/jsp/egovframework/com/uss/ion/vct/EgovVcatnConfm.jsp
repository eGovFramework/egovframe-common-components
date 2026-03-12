<%
/**
 * @Class Name  : EgovVcatnConfm.java
 * @Description : EgovVcatnConfm.jsp
 * @Modification Information
 * @
 * @  수정일       수정자       수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이용			최초 생성
 * @ 2018.08.16    최두영		퍼블리싱 점검
 * @ 2018.09.18    최두영		다국어처리
 * @ 2024.10.29    권태성		debugger 코드 제거(modalDialogCallback())
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
<title><spring:message code="comUssIonVct.vcatnConfm.title"/></title><!-- 휴가승인 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 휴가승인목록 으로 가기
 ******************************************************** */
function fncEgovVcatnConfmList(){
	location.href = "<c:url value='/uss/ion/vct/EgovVcatnConfmList.do'/>";
}

/* 결재권자 선택 모달 */
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

function modalDialogCallback(retVal) {
	if(retVal == null) return;
	var tmp = retVal.split(",");
	var varForm = document.getElementById("vcatnManage") || document.forms["vcatnManage"];
	if(!varForm) return;
	/* 결재권자 선택: uniqId,emplNo,emplyrNm,orgnztNm (4개) */
	if(tmp.length >= 4) {
		varForm.sanctnerId.value = tmp[0];
		var sanctnDtNmEl = document.getElementById("sanctnDtNm");
		var orgnztNmEl = document.getElementById("orgnztNm");
		if(sanctnDtNmEl) sanctnDtNmEl.value = tmp[2];
		if(orgnztNmEl) orgnztNmEl.value = tmp[3];
		$('.ui-dialog-content').dialog('close');
		return;
	}
	/* 승인/반려: returnResn,confmAt (2개) */
	<c:if test="${vcatnManageVO.confmAt eq 'A' }">
	if(tmp.length >= 2) {
		varForm.returnResn.value = tmp[0];
		varForm.confmAt.value = tmp[1];
		varForm.action = "<c:url value='/uss/ion/vct/updtVcatnConfm.do'/>";
		varForm.submit();
	}
	</c:if>
}

<c:if test="${vcatnManageVO.confmAt eq 'A' }">
var popUpCode = 0;
function layerPopUp(){
	var pagetitle = $(this).attr("data-dialog-title") || $(this).attr("title") || "";
	var page;
	if(popUpCode == 0){
		page = "<c:url value='/uss/ion/ism/EgovConfmPopupNew.do'/>";
	}else if(popUpCode == 1){
		page = "<c:url value='/uss/ion/ism/EgovReturnPopupNew.do'/>";
	}else{
		alert("page Null");
		return;
	}
	var $dialog = $('<div></div>')
		.html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
		.dialog({
			autoOpen: false,
			modal: true,
			height: 250,
			width: 800,
			title: pagetitle
		});
	$dialog.dialog('open');
}
$(document).ready(function () {
	$('#confirmAgree').click(function (e) {
		popUpCode = 0;
		e.preventDefault();
		layerPopUp.call(this);
	});
	$('#confirmDisAgree').click(function (e) {
		popUpCode = 1;
		e.preventDefault();
		layerPopUp.call(this);
	});
});
</c:if>

$(document).ready(function () {
	$('#VcatnSanctner').click(function (e) {
		e.preventDefault();
		openVcatnSanctnerDialog($(this).attr("data-dialog-title"));
	});
});
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2 class=""><spring:message code="comUssIonVct.vcatnConfm.title"/></h2><!-- 휴가승인 -->
	
	<form:form modelAttribute="vcatnManageVO" name="vcatnManage" id="vcatnManage" method="post" action="${pageContext.request.contextPath}/uss/ion/vct/EgovVcatnConfm.do">
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value='<spring:message code="comUssIonVct.vcatnConfm.submit"/>' title='<spring:message code="comUssIonVct.vcatnConfm.submit"/>'></div><!-- 전송 -->
	<form:hidden  path="applcntId"/>
	<form:hidden  path="vcatnSe"/>
	<form:hidden  path="bgnde"/>
	<form:hidden  path="endde"/>
	<form:hidden  path="infrmlSanctnId"/>
	<form:hidden  path="occrrncYear"/>
	<form:hidden  path="confmAt"/>
	<form:hidden  path="sanctnerId" id="sanctnerId"/>
	<form:hidden  path="returnResn"/>
	<input type="hidden" name="vcatnResn" value="사유"/>
		
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.vcatnConfm.title"/></h3><!-- 휴가승인 -->
	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
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
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnTotalInfo"/></th><!-- 신청자 연차정보 -->
			<td class="left" colspan="4">
				<span>[<spring:message code="comUssIonVct.common.occrncYrycCo"/>: ${vcatnManageVO.occrncYrycCo}  ]</span><!-- 발생연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.useYrycCo"/>: ${vcatnManageVO.useYrycCo   }  ]</span><!-- 사용연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.remndrYrycCo"/>: ${vcatnManageVO.remndrYrycCo}  ]</span><!-- 잔여연차 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnSe"/> <span class="pilsu">*</span></th><!-- 휴가구분 -->
			<td class="left" colspan="3">
				<c:out value='${vcatnManageVO.vcatnSeNm}'/>
			</td>
		</tr>
		<tr>
			<th><label for="bgnde"><spring:message code="comUssIonVct.common.startDate"/> </label><span class="pilsu">*</span></th><!-- 시작일자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.bgnde}'/>
			</td>
			<c:if test="${vcatnManageVO.vcatnSe ne '02' }">
			<th><span id="nameSpan"><label for="endde"><spring:message code="comUssIonVct.common.endDate"/></label></span> <span class="pilsu">*</span></th><!-- 종료일자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.endde}'/>
			</td>
			</c:if>
			<c:if test="${vcatnManageVO.vcatnSe eq '02' }">
			<th><spring:message code="comUssIonVct.common.noonSe"/> <span class="pilsu">*</span></th><!-- 정오구분 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.endde}'/>
			</td>
			</c:if>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnResn"/> <span class="pilsu">*</span></th><!-- 휴가사유 -->
			<td class="left" colspan="3">
			    <textarea id="vcatnResnView" name="vcatnResnView" class="txta01" rows="4" cols="70" title='<spring:message code="comUssIonVct.common.vcatnResn"/>' readonly="readonly"><c:out value='${vcatnManageVO.vcatnResn}' escapeXml="false"/></textarea>
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
				<input name="sanctnDtNm" id="sanctnDtNm" type="text" value="<c:out value='${empty infSanctnDtNm ? vcatnManageVO.sanctnerNm : infSanctnDtNm}'/>" title='<spring:message code="comUssIonVct.common.sanctnDtNm"/>' readonly="readonly" style="width:128px" /><!-- 결재권자명 -->
				<span class="link">
				<a id="VcatnSanctner" href="#LINK" title='<spring:message code="comUssIonRwd.common.searchNm"/>' data-dialog-title="<spring:message code="comUssIonVct.common.infrmlSanctnRegist"/>" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" style="vertical-align: middle" alt='<spring:message code="comUssIonVct.common.sanctnDtNm"/>' title='<spring:message code="comUssIonVct.common.sanctnDtNm"/>'></a><!-- 결재권자 지정 -->
				</span>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
				<input name="orgnztNm" id="orgnztNm" type="text" value="<c:out value='${empty infOrgnztNm ? vcatnManageVO.sanctnerOrgnztNm : infOrgnztNm}'/>" title='<spring:message code="comUssIonVct.common.orgnztNm"/>' readonly="readonly"/>
			</td>
		</tr>
	</table>
		
	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${vcatnManageVO.confmAt eq 'A' }">
		<input id="confirmAgree" class="s_submit" type="submit" value='<spring:message code="comUssIonVct.common.agree"/>' title='<spring:message code="comUssIonVct.common.agree"/>' data-dialog-title="<spring:message code="comUssIonVct.vcatnConfm.title"/> - <spring:message code="comUssIonVct.common.agree"/>" /><!-- 승인 -->
		<span class="btn_s"><a id="confirmDisAgree" href="#LINK" title='<spring:message code="comUssIonVct.common.disagree"/>' data-dialog-title="<spring:message code="comUssIonVct.vcatnConfm.title"/> - <spring:message code="comUssIonVct.common.disagree"/>"><spring:message code="comUssIonVct.common.disagree"/></a></span><!-- 반려 -->
	    </c:if>
	    <span class="btn_s"><a href="<c:url value='/uss/ion/vct/EgovVcatnConfmList.do'/>?searchCondition=1" onclick="fncEgovVcatnConfmList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	</form:form>
</div>
</body>
</html>