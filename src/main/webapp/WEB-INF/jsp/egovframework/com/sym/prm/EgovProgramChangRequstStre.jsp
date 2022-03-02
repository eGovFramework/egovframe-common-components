<!DOCTYPE html>
<%--
 /**
  * @Class Name : EgovProgramChangRequstStre.jsp
  * @Description : 프로그램변경요청 등록 화면
  * @Modification Information
  * @
  * @  수정일               수정자              수정내용
  * @ ----------    --------    ---------------------------
  * @ 2009.03.10    이용                최초 생성
  *   2018.09.04    신용호             공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  //String imagePath_icon   = "/images/egovframework/com/sym/prm/icon/";
  //String imagePath_button = "/images/egovframework/com/sym/prm/button/";
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
<title><spring:message code="comSymPrm.programChangRequstStre.title"/></title><!-- 프로그램변경요청 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageDtlVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 파일목록조회  함수
 ******************************************************** */
function searchFileNm() {
	document.all.tmp_SearchElementName.value = "progrmFileNm";
	window.open("<c:url value='/sym/prm/EgovProgramListSearch.do' />",'','width=500,height=600');
}

/* ********************************************************
 * 입력 처리 함수
 ******************************************************** */
function insertProgrmChangeRequst(form) {
	if(confirm("<spring:message code="common.save.msg" />")){

		if(!validateProgrmManageDtlVO(form)){
			return;
		}else{
            form.action = "<c:url value='/sym/prm/EgovProgramChangRequstStre.do'/>";
			form.submit();
		}
	}
//	progrmListRegistForm.submit();
}

function initCalendar(){

	$("#rqesterDe").datepicker( 
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
 * 목록조회 처리 함수
 ******************************************************** */
function selectList(){
	location.href = "<c:url value='/sym/prm/EgovProgramChangeRequstSelect.do' />";
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
</head>
<body onload="initCalendar()">
<c:set var="vrqesterNo"><spring:message code="comSymPrm.programChangRequstStre.rqesterNo"/></c:set>
<c:set var="vrqesterPersonId"><spring:message code="comSymPrm.programChangRequstStre.rqesterPersonId"/></c:set>
<c:set var="vrqesterSj"><spring:message code="comSymPrm.programChangRequstStre.rqesterSj"/></c:set>
<c:set var="vchangerqesterCn"><spring:message code="comSymPrm.programChangRequstStre.changerqesterCn"/></c:set>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="progrmManageDtlVO" name="progrmManageDtlVO" action ="<c:url value='/sym/prm/EgovProgramChangRequstStre.do'/>" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymPrm.programChangRequstStre.pageTop.title"/></h2><!-- 프로그램변경요청 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstStre.rqesterNo"/> <span class="pilsu">*</span></th><!-- 요청번호 -->
			<td class="left">
			    <form:input path="rqesterNo" size="50"  maxlength="50"  title="${vrqesterNo}" readonly="true"/><!-- 요청번호 -->
				<form:errors path="rqesterNo" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstStre.progrmFileNm"/> <span class="pilsu">*</span></th><!-- 프로그램파일명 -->
			<td class="left">
			    <input id="progrmFileNm" name="progrmFileNm" maxlength="50"  title="<spring:message code="comSymPrm.programChangRequstStre.progrmFileNm"/>" readonly="readonly"/><!-- 프로그램파일명 -->
				<form:errors path="progrmFileNm" />
				<a href="<c:url value='/sym/prm/EgovProgramListSearch.do'/>?tmp_SearchElementName=progrmFileNm" target="_blank" onclick="searchFileNm(); return false;" style="selector-dummy:expression(this.hideFocus=false);"  title="새 창으로 이동"><!-- 새 창으로 이동 -->
				<img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />" alt='(<spring:message code="comSymPrm.programChangRequstStre.searchProgrmFileNm"/>)' width="15" height="15" /></a><!-- 프로그램파일명 검색 -->
				(<spring:message code="comSymPrm.programChangRequstStre.searchProgrmFileNm"/>)<!-- 프로그램파일명 검색 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstStre.rqesterPersonId"/> <span class="pilsu">*</span></th><!-- 요청자 -->
			<td class="left">
			    <form:input path="rqesterPersonId" size="20"  maxlength="20"  title="${vrqesterPersonId}" readonly="true"/><!-- 요청자 -->
				<form:errors path="rqesterPersonId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstStre.rqesterDe"/> <span class="pilsu">*</span></th><!-- 요청일자 -->
			<td class="left">
			    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
				<input id="rqesterDe" name="rqesterDe" maxlength="50"  title="<spring:message code="comSymPrm.programChangRequstStre.rqesterDe"/>" readonly="readonly" style="width:70px"/><!-- 요청일자 -->
				<form:errors path="rqesterDe"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstStre.rqesterSj"/> <span class="pilsu">*</span></th><!-- 요청제목 -->
			<td class="left">
			    <form:input path="rqesterSj" size="50"  maxlength="50"  title="${vrqesterSj}"/><!-- 요청제목 -->
				<form:errors path="rqesterSj" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstStre.changerqesterCn"/></th><!-- 변경요청내용 -->
			<td class="left">
			    <form:textarea path="changerqesterCn" rows="14" cols="75" title="${changerqesterCn}"/><!-- 변경요청내용 -->
     			<form:errors path="changerqesterCn"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.create" />' onclick="insertProgrmChangeRequst(document.forms[0]); return false;" /><!-- 등록 -->
		<span class="btn_s"><a href="<c:url value='/sym/prm/EgovProgramChangeRequstSelect.do'/>" onclick="selectList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<form:hidden path="rqesterProcessCn" />
<form:hidden path="opetrId" />
<form:hidden path="processSttus" />
<form:hidden path="processDe" />
<input name="cmd" type="hidden" value="insert"/>
</form:form>
<input type="hidden" name="tmp_SearchElementName"/>
<input type="hidden" name="tmp_SearchElementVal"/>

</body>
</html>
