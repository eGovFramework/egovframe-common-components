<%
 /**
  * @Class Name : EgovAdministrationWordUpdt.jsp
  * @Description : EgovAdministrationWordUpdt 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlhAwm.administrationWordVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="administrationWordVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.getElementById("administrationWordVO").administWordNm.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_administrationword(form){
	if (!validateAdministrationWordVO(form)) {		 			
		return false;		
	} else {
		
		if(confirm("<spring:message code="common.update.msg" />")){	
			form.submit();	
		}					
	}	
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_list() {
	administrationWordVO.action = "<c:url value='/uss/olh/awm/selectAdministrationWordManageList.do'/>";
	administrationWordVO.submit();	
}
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀 -->
<form:form modelAttribute="administrationWordVO" action="${pageContext.request.contextPath}/uss/olh/awm/updateAdministrationWord.do" method="post" onSubmit="fn_egov_updt_administrationword(document.forms[0]); return false;">  
<div class="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.update" /></h2>

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		
		<!-- 행정용어명 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.administWordNm"/> </c:set>
		<tr>
			<th><label for="administWordNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="administWordNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="administWordNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 행정용어영문명 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.administWordEngNm"/> </c:set>
		<tr>
			<th><label for="administWordEngNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="administWordEngNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="administWordEngNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 행정용어약어명 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.administWordAbrv"/> </c:set>
		<tr>
			<th><label for="administWordAbrv">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="administWordAbrv" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="administWordAbrv" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 주제영역 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.themaRelm"/> </c:set>
		<tr>
			<th><label for="themaRelm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="themaRelm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="themaRelm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 용어구분 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.wordDomn"/> </c:set>
		<tr>
			<th><label for="wordDomn">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="wordDomn" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:options items="${wordSeCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
				<div><form:errors path="wordDomn" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 관련표준용어 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.stdWord"/> </c:set>
		<tr>
			<th><label for="stdWord">${title} </label></th>
			<td class="left">
			    <form:input path="stdWord" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="stdWord" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 행정용어정의 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.administWordDf"/> </c:set>
		<tr>
			<th><label for="administWordDf">${title } </label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="administWordDf" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="administWordDf" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 행정용어설명 -->
		<c:set var="title"><spring:message code="comUssOlhAwm.administrationWordVO.administWordDc"/> </c:set>
		<tr>
			<th><label for="administWordDc">${title } </label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="administWordDc" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="administWordDc" cssClass="error" /></div>  
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olh/awm/selectAdministrationWordManageList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>


<input name="administWordId" type="hidden" value="<c:out value='${administrationWordVO.administWordId}'/>">
</form:form>

</body>
</html>
