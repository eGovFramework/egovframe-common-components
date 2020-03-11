<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovProgramChgHstDetail.jsp
  * @Description : 프로그램변경이력 상세조회 화면
  * @Modification Information
  * @
  * @  수정일               수정자            수정내용
  * @ ----------    --------   ---------------------------
  * @ 2009.03.10    이용              최초 생성
  *   2018.09.04    신용호           공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/prm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/prm/button/";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymPrm.programChgHstDetail.title"/></title><!-- 프로그램변경이력상세 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function updateChangRequstProcess() {
	progrmChangRequstProcessForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function deleteChangRequstProcess() {
   document.progrmChangRequstProcessForm.action = "<c:url value='EgovProgramChangeRequstProcessDelete.do'/>";
   document.progrmChangRequstProcessForm.submit();
}

/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function selectList(){
	location.href = "<c:url value='/sym/prm/EgovProgramChgHstListSelect.do' />";
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="progrmChangeRequstForm" action ="<c:url value='/sym/prm/EgovProgramChgHstListSelect.do'/>" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymPrm.programChgHstDetail.pageTop.title"/></h2><!-- 프로그램변경이력상세조회 -->
	
	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comSymPrm.programChgHstDetail.title.sub1"/></h2><!-- 변경요청내역 -->

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.rqesterNo"/></th><!-- 요청번호 -->
			<td class="left">
			    <c:out value="${resultVO.rqesterNo}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.progrmFileNm"/></th><!-- 프로그램파일명 -->
			<td class="left">
			    <c:out value="${resultVO.progrmFileNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.rqesterPersonId"/></th><!-- 요청자ID -->
			<td class="left">
			    <c:out value="${resultVO.rqesterPersonId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.rqesterDe"/></th><!-- 요청일자 -->
			<td class="left">
			    <c:out value="${resultVO.rqesterDe}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.rqesterSj"/></th><!-- 요청제목 -->
			<td class="left">
			    <c:out value="${resultVO.rqesterSj}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.changerqesterCn"/></th><!-- 변경요청내용 -->
			<td class="left">
			    <textarea name="changerqesterCn" class="textarea"  cols="75" rows="5" readOnly="readOnly" style="width:450px;border:0;background-color:transparent;filter: chroma(color=ffffff);" title="<spring:message code="comSymPrm.programChgHstDetail.changerqesterCn"/>"><c:out value="${resultVO.changerqesterCn}"/></textarea>
			</td>
		</tr>
	</table>
	
	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comSymPrm.programChgHstDetail.title.sub2"/></h2><!-- 변경처리내역 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.processDe"/></th><!-- 변경처리일자 -->
			<td class="left">
			    <c:out value="${resultVO.processDe}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.opetrId"/></th><!-- 변경처리자 -->
			<td class="left">
			    <c:out value="${resultVO.opetrId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.processSttus"/></th><!-- 변경처리상태 -->
			<td class="left">
			    <c:if test="${empty resultVO.processSttus}">N/A</c:if>
				<c:if test="${resultVO.processSttus == 'A'}"><spring:message code="comSymPrm.programChgHstDetail.processSttusA"/></c:if><!-- 신청중 -->
				<c:if test="${resultVO.processSttus == 'P'}"><spring:message code="comSymPrm.programChgHstDetail.processSttusP"/></c:if><!-- 진행중 -->
				<c:if test="${resultVO.processSttus == 'R'}"><spring:message code="comSymPrm.programChgHstDetail.processSttusR"/></c:if><!-- 반려 -->
				<c:if test="${resultVO.processSttus == 'C'}"><spring:message code="comSymPrm.programChgHstDetail.processSttusC"/></c:if><!-- 처리완료 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChgHstDetail.rqesterProcessCn"/></th><!-- 변경처리내용 -->
			<td class="left">
			    <textarea name="rqesterProcessCn" class="textarea"  cols="75" rows="5" readOnly="readOnly" style="width:450px;border:0;background-color:transparent;filter: chroma(color=ffffff);" title="<spring:message code="comSymPrm.programChgHstDetail.rqesterProcessCn"/>"><c:out value="${resultVO.rqesterProcessCn}"/></textarea>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value=목록 onclick="selectList(); return false;" />
	</div>
	<div style="clear:both;"></div>
</div>

</form>

</body>
</html>
