<%--
  Class Name : EgovPopupDetail.jsp
  Description : 팝업창관리 상세보기
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.09.16    장동한		최초 생성
     2018.08.29    이정은		공통컴포넌트 3.8 개선
     2024.10.29    권태성		formUpdt form 내에 cmd input box를 추가
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.09.16
   
    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonPwm.popupDetail.popupDetail"/></title><!-- 팝업창관리 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_PopupManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_PopupManage(){
	location.href = "<c:url value='/uss/ion/pwm/listPopup.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_PopupManage(){
	var vFrom = document.formUpdt;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/ion/pwm/updtPopup.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_PopupManage(){
	var vFrom = document.formDelete;
	if(confirm('<spring:message code="common.delete.msg"/>')){/* 삭제 하시겠습니까? */
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/ion/pwm/detailPopup.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_PopupManage();">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonPwm.popupDetail.popupDetail"/></h2><!-- 팝업창관리 상세보기 -->

	<form name="PopupManageForm" action="<c:url value='/uss/ion/pwm/detailPopup.do'/>" method="post">
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:24%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.popupTitleNm"/> <span class="pilsu">*</span></th><!-- 팝업창명 -->
			<td class="left">
			    <c:out value="${popupManageVO.popupTitleNm}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.fileUrl"/> <span class="pilsu">*</span></th><!-- 팝업창URL -->
			<td class="left">
			    <c:out value="${popupManageVO.fileUrl}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.popupLoca"/> <span class="pilsu">*</span></th><!-- 팝업창위치 -->
			<td class="left">
			    <spring:message code="ussIonPwm.popupDetail.popupWlce"/><c:out value="${popupManageVO.popupWlc}" />  <spring:message code="ussIonPwm.popupDetail.popupHlc"/><c:out value="${popupManageVO.popupHlc}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.popupSize"/> <span class="pilsu">*</span></th><!-- 팝업창사이즈 -->
			<td class="left">
			    <spring:message code="ussIonPwm.popupDetail.popupWSize"/> : <c:out value="${popupManageVO.popupWSize}" />  <spring:message code="ussIonPwm.popupDetail.popupHSize"/> : <c:out value="${popupManageVO.popupHSize}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.ntcePeriod"/> <span class="pilsu">*</span></th><!-- 게시 기간 -->
			<td class="left">
			    <c:out value="${fn:substring(popupManageVO.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 6, 8)}"/> <c:out value="${fn:substring(popupManageVO.ntceBgnde, 8, 10)}"/>H <c:out value="${fn:substring(popupManageVO.ntceBgnde, 10, 12)}"/>M 
			 	~
			 	<c:out value="${fn:substring(popupManageVO.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 6, 8)}"/> <c:out value="${fn:substring(popupManageVO.ntceEndde, 8, 10)}"/>H <c:out value="${fn:substring(popupManageVO.ntceEndde, 10, 12)}"/>M
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.stopVewAt"/> <span class="pilsu">*</span></th><!-- 그만보기 설정 여부 -->
			<td class="left">
			    <c:out value="${popupManageVO.stopVewAt}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonPwm.popupDetail.ntceAt"/> <span class="pilsu">*</span></th><!-- 게시 상태 -->
			<td class="left">
			    <c:out value="${popupManageVO.ntceAt}"/>
			</td>
		</tr>
	</table>
	<input name="cmd" type="hidden" value="<c:out value=''/>">
	</form>

	<!-- 하단 버튼 -->
	<div class="btn">		
		<form name="formUpdt" action="<c:url value='/uss/ion/pwm/updtPopup.do'/>" method="post" style="display:inline">
			<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_PopupManage(); return false;" />
			<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
			<input name="cmd" type="hidden" value="<c:out value=''/>">
			</form>
		
		<form name="formDelete" action="<c:url value='/uss/ion/pwm/detailPopup.do'/>" method="post" style="display:inline">
			<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete_PopupManage(); return false;" />
			<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
		 
		<form name="formList" action="<c:url value='/uss/ion/pwm/listPopup.do'/>" method="post" style="display:inline">
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_PopupManage(); return false;" />
		</form>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>
