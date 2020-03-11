<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovRestdeRegist.jsp
  * @Description : EgovRestdeRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  *   2011.08.12   서준식              CSS 경로 수정
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="sym.cal.regist.title"/></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="restde" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_Restde(){
	location.href = "<c:url value='/sym/cal/EgovRestdeList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_regist_Restde(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateRestde(form)){
			return;
		}else{
			form.submit();
		}
	}
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="restde" name="restde" method="post">

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
			    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
		    	<form:hidden path="restdeDe" />
				<input name="vrestdeDe" type="text" value=""  maxlength="10" readonly="readonly" onclick="fn_egov_NormalCalendar(document.restde, document.restde.restdeDe, document.restde.vrestdeDe);" title="<spring:message code="sym.cal.restDay" />(새창)" style="width:70px"/>
				<a href="#noscript" onclick="fn_egov_NormalCalendar(document.restde, document.restde.restdeDe, document.restde.vrestdeDe); return false;"><img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />" alt="달력창팝업버튼이미지"/></a>
			</td>
		</tr>
		<tr>
			<th><spring:message code="sym.cal.restName" /> <span class="pilsu">*</span></th><!-- 휴일명 -->
			<td class="left">
			    <form:input  path="restdeNm" size="50" maxlength="50" title="<spring:message code='sym.cal.restName' />"/>
      			<form:errors path="restdeNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="sym.cal.restDetail" /> <span class="pilsu">*</span></th><!-- 휴일설명 -->
			<td class="left">
			    <form:textarea path="restdeDc" rows="3" cols="60" title="<spring:message code='sym.cal.restDetail' />"/>
      			<form:errors   path="restdeDc"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="sym.cal.restCategory" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:select path="restdeSeCode" title="<spring:message code='sym.cal.restCategory' />">
				<form:options items="${restdeCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_Restde(); return false;" /><!-- 목록 -->
		<span class="btn_s"><a href="#noscript" onclick="fn_egov_regist_Restde(document.restde); return false;"><spring:message code="button.save" /></a></span><!-- 저장 -->
	</div>
	<div style="clear:both;"></div>
</div>



<input name="cmd" type="hidden" value="<c:out value='save'/>"/>


</form:form>

</body>
</html>
