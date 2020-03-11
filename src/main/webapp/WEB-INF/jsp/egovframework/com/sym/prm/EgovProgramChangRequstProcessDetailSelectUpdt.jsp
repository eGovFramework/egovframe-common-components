<!DOCTYPE html>
<%--
 /**
  * @Class Name : EgovProgramChangRequstProcessDetailSelectUpdt.jsp
  * @Description : 프로그램변경요청처리상세조회/수정
  * @Modification Information
  * @
  * @  수정일             수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용              최초 생성
  *   2011.09.14   서준식           변경처리 요청내용 Readonly로 변경
  *   2018.09.04   신용호           공통컴포넌트 3.8 개선
  
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="/images/egovframework/com/sym/prm/"/>
<c:set var="CssUrl" value="/css/egovframework/com/sym/prm/"/>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.title"/></title><!-- 프로그램변경요청처리상세조회/수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="progrmManageDtlVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function updateChangRequstProcess(form) {
	if(confirm("<spring:message code="common.save.msg"/>")){
		if(!validateProgrmManageDtlVO(form)){
			return;
		}else{
            form.action ="<c:url value='/sym/prm/EgovProgramChangRequstProcessDetailSelectUpdt.do' />";
			form.submit();
		}
	}
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function deleteChangRequstProcess(form) {
   form.action = "<c:url value='/sym/prm/EgovProgramChangeRequstProcessDelete.do' />";
   form.submit();
}

/* ********************************************************
 * 목록조회 처리 함수
 ******************************************************** */
function selectList(){
	location.href = "<c:url value='/sym/prm/EgovProgramChangeRequstProcessListSelect.do' />";
}

function initCalendar(){

	$("#processDe").datepicker( 
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

<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
</head>
<body onload="initCalendar()">
<c:set var="vprocessDe"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processDe"/></c:set>
<c:set var="vopetrId"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.opetrId"/></c:set>
<c:set var="vprocessSttus"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processSttus"/></c:set>
<c:set var="vrqesterProcessCn"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.rqesterProcessCn"/></c:set>
<c:set var="vchangerqesterCn"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.changerqesterCn"/></c:set>
<c:set var="vprocessSttusA"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processSttusA"/></c:set>
<c:set var="vprocessSttusP"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processSttusP"/></c:set>
<c:set var="vprocessSttusR"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processSttusR"/></c:set>
<c:set var="vprocessSttusC"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processSttusC"/></c:set>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="progrmManageDtlVO" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.pageTop.title"/></h2><!-- 프로그램변경요청처리상세조회/수정 -->
	
	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.title.sub1"/></h2><!-- 변경처리내역 -->

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processDe"/> <span class="pilsu">*</span></th><!-- 변경처리일자 -->
			<td class="left">
			    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
				<form:input path="processDe" cssClass="txaIpt" title="${vprocessDe}" readonly="true" cssStyle="width:70px"/>
				<form:errors path="processDe"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.opetrId"/> <span class="pilsu">*</span></th><!-- 변경처리자 -->
			<td class="left">
			    <form:input path="opetrId" cssClass="txaIpt" maxlength="30" title="${vopetrId}" readonly="true"/>
				<form:errors path="opetrId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.processSttus"/> <span class="pilsu">*</span></th><!-- 변경처리상태 -->
			<td class="left">
			    <form:select path="processSttus" title="${vprocessSttus}">
		            <form:option value=""  label="N/A"/>
		            <form:option value="A" label="${vprocessSttusA}"/><!-- 신청중 -->
		            <form:option value="P" label="${vprocessSttusP}"/><!-- 진행중 -->
		            <form:option value="R" label="${vprocessSttusR}"/><!-- 반려 -->
		            <form:option value="C" label="${vprocessSttusC}"/><!-- 처리완료 -->
		        </form:select>
		        <div><form:errors path="processSttus" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.rqesterProcessCn"/></th><!-- 변경처리내용 -->
			<td class="left">
			    <form:textarea path="rqesterProcessCn" rows="5" cols="75" cssClass="txaClass" title="${vrqesterProcessCn}"/>
      			<form:errors path="rqesterProcessCn"/>
			</td>
		</tr>
	</table>
	
	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.title.sub2"/></h2><!-- 변경요청내역 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.rqesterNo"/> <span class="pilsu">*</span></th><!-- 요청번호 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterNo}"/>
				<form:hidden path="rqesterNo" />
				<form:errors path="rqesterNo" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.progrmFileNm"/> <span class="pilsu">*</span></th><!-- 프로그램파일명 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.progrmFileNm}"/>
				<form:hidden path="progrmFileNm" />
				<form:errors path="progrmFileNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.rqesterPersonId"/> <span class="pilsu">*</span></th><!-- 요청자ID -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterPersonId}"/>
				<form:hidden path="rqesterPersonId" />
				<form:errors path="rqesterPersonId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.rqesterDe"/> <span class="pilsu">*</span></th><!-- 요청일자 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterDe}"/>
				<form:hidden path="rqesterDe" />
				<form:errors path="rqesterDe" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.rqesterSj"/> <span class="pilsu">*</span></th><!-- 요청제목 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterSj}" />
				<form:hidden path="rqesterSj" />
				<form:errors path="rqesterSj" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstProcessDetailSelectUpdt.changerqesterCn"/> <span class="pilsu">*</span></th><!-- 변경요청내용 -->
			<td class="left">
			    <form:textarea path="changerqesterCn" rows="5" cols="75" readonly="true" cssClass="txaClass" title="변경요청내용"/>
      			<form:errors path="changerqesterCn"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sym/prm/EgovProgramChangeRequstProcessListSelect.do'/>" onclick="selectList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="updateChangRequstProcess(document.forms[0]); return false;" /><!-- 수정 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
