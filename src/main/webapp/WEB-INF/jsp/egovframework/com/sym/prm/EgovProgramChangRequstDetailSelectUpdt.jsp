<!DOCTYPE html>
<%--
 /**
  * @Class Name : EgovProgramChangRequstDetailSelectUpdt.jsp
  * @Description : 프로그램변경요청 상세조회및 수정 화면
  * @Modification Information
  * @
  * @  수정일              수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.03.10   이용                최초 생성
  *   2018.09.04   신용호             공통컴포넌트 3.8 개선
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
<title><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.title"/></title><!-- 프로그램변경요청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageDtlVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 파일검색 화면 호출 함수
 ******************************************************** */
function searchFileNm() {
	document.progrmChangeRequstForm.tmp_SearchElementName.value = "progrmFileNm";
	window.open("<c:url value='/sym/prm/EgovProgramListSearch.do' />",'','width=500,height=600');
}

/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function updateProgrmChangeRequst(form) {
	if(confirm("<spring:message code="common.save.msg"/>")){
		if(!validateProgrmManageDtlVO(form)){
			return;
		}else{
            form.action ="<c:url value='/sym/prm/EgovProgramChangRequstDetailSelectUpdt.do'/>";
			form.submit();
		}
	}
}


/* ********************************************************
 * 삭제처리 함수
 ******************************************************** */
function deleteProgrmChangeRequst(form) {
	form.action = "<c:url value='/sym/prm/EgovProgramChangRequstDelete.do'/>";
	form.submit();
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
<body>
<c:set var="vrqesterSj"><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterSj"/></c:set>
<c:set var="vchangerqesterCn"><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.changerqesterCn"/></c:set>
<c:set var="vrqesterProcessCn"><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterProcessCn"/></c:set>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="progrmManageDtlVO" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.pageTop.title"/></h2><!-- 프로그램변경요청 상세조회 /수정 -->

	<!-- 등록폼 -->
	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.title.sub1"/></h2><!-- 변경요청내역 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterNo"/> <span class="pilsu">*</span></th><!-- 요청번호 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterNo}"/>
				<form:hidden path="rqesterNo" />
				<form:errors path="rqesterNo" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.progrmFileNm"/> <span class="pilsu">*</span></th><!-- 프로그램파일명 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.progrmFileNm}"/>
				<form:hidden path="progrmFileNm" />
				<form:errors path="progrmFileNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterPersonId"/> <span class="pilsu">*</span></th><!-- 요청자ID -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterPersonId}"/>
				<form:hidden path="rqesterPersonId" />
				<form:errors path="rqesterPersonId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterDe"/> <span class="pilsu">*</span></th><!-- 요청일자 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.rqesterDe}"/>
				<form:hidden path="rqesterDe" />
				<form:errors path="rqesterDe" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterSj"/> <span class="pilsu">*</span></th><!-- 요청제목 -->
			<td class="left">
			    <form:input path="rqesterSj" size="60"  maxlength="60"  title="${vrqesterSj}"/>
				<form:errors path="rqesterSj" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.changerqesterCn"/> </th><!-- 변경요청내용 -->
			<td class="left">
			    <form:textarea path="changerqesterCn" rows="4" cols="75"  title="${vchangerqesterCn}"/><!-- 변경요청내용 -->
      			<form:errors path="changerqesterCn"/>
			</td>
		</tr>
	</table>
	
	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.title.sub2"/></h2><!-- 변경처리내역 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.processDe"/> <span class="pilsu">*</span></th><!-- 변경처리일자 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.processDe}"/>
		      	<form:hidden path="processDe" />
				<form:errors path="processDe" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.opetrId"/> <span class="pilsu">*</span></th><!-- 변경처리자 -->
			<td class="left">
			    <c:out value="${progrmManageDtlVO.opetrId}"/>
		      	<form:hidden path="opetrId" />
				<form:errors path="opetrId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.processSttus"/> <span class="pilsu">*</span></th><!-- 변경처리상태 -->
			<td class="left">
			    <c:if test="${empty progrmManageDtlVO.processSttus}">N/A</c:if>
		      <c:if test="${progrmManageDtlVO.processSttus == 'A'}"><spring:message code="comSymPrm.programChangeRequst.processSttusA"/></c:if><!-- 신청중 -->
		      <c:if test="${progrmManageDtlVO.processSttus == 'P'}"><spring:message code="comSymPrm.programChangeRequst.processSttusP"/></c:if><!-- 진행중 -->
		      <c:if test="${progrmManageDtlVO.processSttus == 'R'}"><spring:message code="comSymPrm.programChangeRequst.processSttusR"/></c:if><!-- 반려 -->
		      <c:if test="${progrmManageDtlVO.processSttus == 'C'}"><spring:message code="comSymPrm.programChangeRequst.processSttusC"/></c:if><!-- 처리완료 -->
		      	<form:hidden path="processSttus" />
				<form:errors path="processSttus" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymPrm.programChangRequstDetailSelectUpdt.rqesterProcessCn"/></th><!-- 변경처리내용 -->
			<td class="left">
			    <textarea id="rqesterProcessCn" name="rqesterProcessCn" rows="4" readonly cols="75" title="${vrqesterProcessCn}">${progrmManageDtlVO.rqesterProcessCn }</textarea><!-- 변경처리내용 -->
      			<form:errors path="rqesterProcessCn"/>
			</td>
		</tr>
	</table>
	

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sym/prm/EgovProgramChangeRequstSelect.do'/>" onclick="selectList(); return false;"><spring:message code="button.list"/></a></span><!-- 목록 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="updateProgrmChangeRequst(document.forms[0]); return false;" /><!-- 수정 -->
		<span class="btn_s"><a href="#LINK" onclick="deleteProgrmChangeRequst(document.forms[0]); return false;"><spring:message code="button.delete"/></a></span><!-- 삭제 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" name="tmp_SearchElementName" value="">
<input type="hidden" name="tmp_SearchElementVal" value="">

</form:form>

</body>
</html>
