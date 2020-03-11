<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovRestdeDetail.jsp
  * @Description : EgovRestdeDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *   2011.08.12   서준식              CSS 경로 수정
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
<c:set var="pageTitle"><spring:message code="sym.cal.detail.title"/></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_Restde(){
	location.href = "<c:url value='/sym/cal/EgovRestdeList.do' />";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_modify_Restde(){
	var varForm				 = document.all["Form"];
	varForm.action           = "<c:url value='/sym/cal/EgovRestdeModify.do'/>";
	varForm.restdeNo.value   = "${result.restdeNo}";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_Restde(){
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		var varForm				 = document.all["Form"];
		varForm.action           = "<c:url value='/sym/cal/EgovRestdeRemove.do'/>";
		varForm.restdeNo.value   = "${result.restdeNo}";
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
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="sym.cal.restDay" /> <span class="pilsu">*</span></th><!-- 휴일일자 -->
			<td class="left">
			    <c:out value='${fn:substring(result.restdeDe, 0,4)}'/>-<c:out value='${fn:substring(result.restdeDe, 4,6)}'/>-<c:out value='${fn:substring(result.restdeDe, 6,8)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="sym.cal.restName" /> <span class="pilsu">*</span></th> <!-- 휴일명 -->
			<td class="left">
			    ${result.restdeNm}
			</td>
		</tr>
		<tr>
			<th><spring:message code="sym.cal.restDetail" /> <span class="pilsu">*</span></th><!-- 휴일설명 -->
			<td class="left">
			    <textarea class="textarea"  cols="75" rows="14"  style="height:190px;" disabled title="<spring:message code="sym.cal.restDetail" />">${result.restdeDc}</textarea><!-- 휴일설명 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="sym.cal.restCategory" /> <span class="pilsu">*</span></th><!-- 휴일구분 -->
			<td class="left">
			    ${result.restdeSe}
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_Restde(); return false;" /><!-- 수정 -->
		<span class="btn_s"><a href="#noscript" onclick="fn_egov_delete_Restde(); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="#noscript" onclick="fn_egov_list_Restde(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>


<form name="Form" method="post" action="">
	<input type=hidden name="restdeNo">
	<input type="submit" id="invisible" class="invisible">
</form>
</body>
</html>
