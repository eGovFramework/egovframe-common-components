<%--
  Class Name : EgovNoteTrnsmitDetail.jsp
  Description : 보낸쪽지함 상세조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.09.16    장동한          최초 생성
     2017.09.14    장동한          공통컴포넌트 3.7 개선

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
<c:set var="pageTitle"><spring:message code="comUssIonNts.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_NoteTrnsmit(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_NoteTrnsmit(){
	location.href = "<c:url value='/uss/ion/nts/listNoteTrnsmit.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_NoteTrnsmit(){
	var vFrom = document.NoteTrnsmitForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/ion/ntm/registEgovNoteManage.do'/>";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_NoteTrnsmit(){
	var vFrom = document.NoteTrnsmitForm;
	if(confirm("삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/ion/nts/detailNoteTrnsmit.do'/>";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_NoteTrnsmit();">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<form name="NoteRecptnForm" action="<c:url value='/uss/ion/ntr/detailNoteRecptn.do'/>" method="post">
	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:16%;">
		<col style="width: ;">
		<col style="width:16%;">
		<col style="width:18%;">
	</colgroup>
	<tbody>
		<!-- 쪽지제목 -->
		<tr>
			<th><spring:message code="comUssIonNts.detail.noteSj" /></th>
			<td colspan="3" class="left">
				<c:set var="noteTrnsmitNoteSj" value="${fn:escapeXml(noteTrnsmit.noteSj)}"/>
				<c:set var="noteTrnsmitNoteSj" value="${fn:replace(noteTrnsmit.noteSj , crlf , '<br>')}"/>
				<c:out value="${noteTrnsmitNoteSj}" escapeXml="false" />
			</td>
		</tr>
		<!-- 발신자, 발신시각 -->
		<tr>
			<th><spring:message code="comUssIonNts.detail.trnsmitNm" /></th>
			<td class="left"><c:out value="${noteTrnsmit.frstRegisterNm}" /></td>
			<th><spring:message code="comUssIonNts.detail.trnsmitPnttm" /></th>
			<td class="left"><c:out value="${noteTrnsmit.frstRegisterPnttm}" /></td>
		</tr>
		<!-- 수신자, 전체, 미개봉 -->
		<tr>
			<th><spring:message code="comUssIonNts.detail.rcverNm" /></th>
			<td class="left">
				<c:forEach items="${resultRecptnEmp}" var="resultInfo" varStatus="status">
					<c:out value="${resultInfo.rcverNm}" />
					<c:if test="${fn:length(resultRecptnEmp) != status.count}">,</c:if>
				</c:forEach>
			</td>
			<td colspan="2">
				<font color="green"><!-- 전체 -->[<spring:message code="comUssIonNts.detail.trnsmitAll" />:${noteTrnsmit.rcverTotal}]</font>&nbsp;&nbsp;
				<font color="blue"><!-- 개봉 -->[<spring:message code="comUssIonNts.detail.trnsmitOpen" />:${noteTrnsmit.openY}]</font>&nbsp;&nbsp;
				<font color="red"><!-- 미개봉 -->[<spring:message code="comUssIonNts.detail.trnsmitNotOpen" />:${noteTrnsmit.openN}]</font>&nbsp;&nbsp;
			</td>

		</tr>
		<!-- 쪽지내용 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonNts.detail.noteRecptn" /></th>
			<td colspan="3" class="cnt">
				<c:set var="noteTrnsmitNoteCn" value="${fn:escapeXml(noteTrnsmit.noteCn)}"/>
				<c:set var="noteTrnsmitNoteCn" value="${fn:replace(noteTrnsmitNoteCn , crlf , '<br>')}"/>
				<c:out value="${noteTrnsmitNoteCn}" escapeXml="false" />
			</td>
		</tr>
	</tbody>
	</table>
	
	<input name="noteId" type="hidden" value="${noteTrnsmit.noteId}">
	<input name="noteTrnsmitId" type="hidden" value="${noteTrnsmit.noteTrnsmitId}">
	<input name="cmd" type="hidden" value="<c:out value=''/>">

	</form>

	<!-- 하단 버튼 -->
	<div class="btn">
	
		<form name="formDelete" action="<c:url value='/uss/ion/nts/detailNoteTrnsmit.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" onClick="fn_egov_delete_NoteTrnsmit(); return false;">
			<input name="noteId" type="hidden" value="${noteTrnsmit.noteId}">
			<input name="noteTrnsmitId" type="hidden" value="${noteTrnsmit.noteTrnsmitId}">
			<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>">
		</form>
	
		<form name="formList" action="<c:url value='/uss/ion/nts/listNoteTrnsmit.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.list" />">
		</form>
	
	</div><div style="clear:both;"></div>
	
</div>

</body>
</html>