<%--
  Class Name : EgovNoteRecptnDetail.jsp
  Description : 받은쪽지함관리 상세보기
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
<c:set var="pageTitle"><spring:message code="comUssIonNtr.title"/></c:set>
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
function fn_egov_init_NoteRecptn(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_NoteRecptn(){
	location.href = "<c:url value='/uss/ion/ntr/listNoteRecptn.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_NoteRecptn(){
	var vFrom = document.NoteRecptnForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/ion/ntr/registNoteRecptn.do'/>";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_NoteRecptn(){
	var vFrom = document.NoteRecptnForm;
	if(confirm("<spring:message code="common.delete.msg" />")){	
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/ion/ntr/detailNoteRecptn.do'/>";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_NoteRecptn();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


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
			<th><spring:message code="comUssIonNtr.detail.noteSj" /></th>
			<td colspan="3" class="left">
				<c:set var="noteRecptnNoteSj" value="${fn:escapeXml(noteRecptn.noteSj)}"/>
				<c:set var="noteRecptnNoteSj" value="${fn:replace(noteRecptnNoteSj , crlf , '<br>')}"/>
				<c:out value="${noteRecptnNoteSj}" escapeXml="false" />
			</td>
		</tr>
		<!-- 발신자, 발신시각 -->
		<tr>
			<th><spring:message code="comUssIonNtr.detail.trnsmiterNm" /></th>
			<td class="left"><c:out value="${noteRecptn.trnsmiterNm}" /></td>
			<th><spring:message code="comUssIonNtr.detail.trnsmiterPnttm" /></th>
			<td class="left"><c:out value="${noteRecptn.trnsmiterPnttm}" /></td>
		</tr>
		<!-- 수신자, 수신시각 -->
		<tr>
			<th><spring:message code="comUssIonNtr.detail.rcverNm" /></th>
			<td class="left">
				<c:forEach items="${resultRecptnEmp}" var="resultInfo" varStatus="status">
			
					<c:if test="${noteRecptn.rcverNm eq resultInfo.rcverNm}">
						<b><c:out value="${noteRecptn.rcverNm}" /></b>
					</c:if>
					<c:if test="${noteRecptn.rcverNm ne resultInfo.rcverNm}">
						<c:out value="${resultInfo.rcverNm}" />
					</c:if>
					<c:if test="${fn:length(resultRecptnEmp) != status.count}">,</c:if>
				</c:forEach>
			</td>
			<th><spring:message code="comUssIonNtr.detail.rcverPnttm" /></th>
			<td class="left"><c:out value="${noteRecptn.rcverPnttm}" /></td>
		</tr>
		<!-- 쪽지내용 -->
		<tr>
			<th class="vtop"><spring:message code="comUssIonNtr.detail.noteRecptn" /></th>
			<td colspan="3" class="cnt">
				<c:set var="noteRecptnNoteCn" value="${fn:escapeXml(noteRecptn.noteCn)}"/>
				<c:set var="noteRecptnNoteCn" value="${fn:replace(noteRecptnNoteCn , crlf , '<br>')}"/>
				<c:out value="${noteRecptnNoteCn}" escapeXml="false" />
			</td>
		</tr>
	</tbody>
	</table>
	
	<input name="noteId" type="hidden" value="${noteRecptn.noteId}">
	<input name="noteTrnsmitId" type="hidden" value="${noteRecptn.noteTrnsmitId}">
	<input name="noteRecptnId" type="hidden" value="${noteRecptn.noteRecptnId}">
	<input name="cmd" type="hidden" value="<c:out value=''/>">
	</form>

	<!-- 하단 버튼 -->
	<div class="btn">

		<form name="formUpdt" action="<c:url value='/uss/ion/ntm/registEgovNoteManage.do'/>" method="post" style="float:left;">
			<input type="submit" class="s_submit" value="<spring:message code="comUssIonNtr.btn.replay" />">
			<input name="noteId" type="hidden" value="${noteRecptn.noteId}">
			<input name="cmd" type="hidden" value="<c:out value='reply'/>">
		</form>
		
		<form name="formDelete" action="<c:url value='/uss/ion/ntr/detailNoteRecptn.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" onClick="fn_egov_delete_NoteRecptn(); return false;">
			<input name="noteId" type="hidden" value="${noteRecptn.noteId}">
			<input name="noteTrnsmitId" type="hidden" value="${noteRecptn.noteTrnsmitId}">
			<input name="noteRecptnId" type="hidden" value="${noteRecptn.noteRecptnId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>">
		</form>
	
		<form name="formList" action="<c:url value='/uss/ion/ntr/listNoteRecptn.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.list" />">
		</form>
	
	</div><div style="clear:both;"></div>
	
</div>

</body>
</html>