<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCcmAdministCodeDetail.jsp
  * @Description : EgovCcmAdministCodeDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmAdc.ccmAdministCode.title"/> <spring:message code="title.detail" /></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInit(){
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fnList(){
	location.href = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeList.do' />";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fnModify(){
	var varForm				       = document.getElementById("Form");
	varForm.action                 = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeModify.do'/>";
	varForm.administZoneSe.value   = "${result.administZoneSe}";
	varForm.administZoneCode.value = "${result.administZoneCode}";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		var varForm				       = document.getElementById("Form");
		varForm.action                 = "<c:url value='/sym/ccm/adc/EgovCcmAdministCodeRemove.do'/>";
		varForm.administZoneSe.value   = "${result.administZoneSe}";
		varForm.administZoneCode.value = "${result.administZoneCode}";
		varForm.submit();
	}
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.cls" /> <span class="pilsu">*</span></th> <!-- 행정구역구분 -->
			<td class="left">
			    <select name="administZoneSe" disabled id="administZoneSe">
					<option value="1" <c:if test="${result.administZoneSe == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddr" /></option>
					<option value="2" <c:if test="${result.administZoneSe == '2'}">selected="selected"</c:if> ><spring:message code="comSymCcmAdc.ccmAdministCode.admAddr" /></option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.administCode" /> <span class="pilsu">*</span></th> <!-- 행정구역코드 -->
			<td class="left">
			    ${result.administZoneCode}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.administName" /> <span class="pilsu">*</span></th> <!-- 행정구역명 -->
			<td class="left">
			    ${result.administZoneNm}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.createDate" /> <span class="pilsu">*</span></th> <!-- 생성일자 -->
			<td class="left">
			    <c:out value='${fn:substring(result.creatDe, 0,4)}'/>-<c:out value='${fn:substring(result.creatDe, 4,6)}'/>-<c:out value='${fn:substring(result.creatDe, 6,8)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.deleteDate" /></th> <!-- 폐기일자 -->
			<td class="left">
			    <c:out value='${fn:substring(result.ablDe, 0,4)}'/>-<c:out value='${fn:substring(result.ablDe, 4,6)}'/>-<c:out value='${fn:substring(result.ablDe, 6,8)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.upperCode" /></th> <!-- 상위행정구역코드 -->
			<td class="left">
			    ${result.upperAdministZoneNm}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmAdc.ccmAdministCode.useAt" /> <span class="pilsu">*</span></th> <!-- 사용여부 -->
			<td class="left">
			    <select name="useAt" id="useAt" disabled>
					<option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >Yes</option>
					<option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >No</option>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="title.update" />" onclick="fnModify(); return false;" /> <!-- 수정 -->
		<input class="s_submit" type="submit" value="<spring:message code="title.delete" />" onclick="fnDelete(); return false;" /> <!-- 삭제 -->
		<input class="s_submit" type="submit" value="<spring:message code="title.list" />" onclick="fnList(); return false;" /> <!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>



<form name="Form" id="Form" method="post" action="<c:url value='/sym/ccm/adc/EgovCcmAdministCodeModify.do'/>">
	<input type=hidden name="administZoneSe">
	<input type=hidden name="administZoneCode">
	<input type="submit" id="invisible" class="invisible"/>
</form>
</body>
</html>
