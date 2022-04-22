<%
 /**
  * @Class Name :  EgovCcmCmmnDetailCodeUpdt.jsp
  * @Description :  공통상세코드 수정하는 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.09.04  이정은              표준프레임워크 v3.7 개선
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
<c:set var="pageTitle"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnDetailCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.getElementById("cmmnDetailCodeVO").codeNm.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_code(form){
	//input item Client-Side validate
	if (!validateCmmnDetailCodeVO(form)) {		 			
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
function fn_egov_inqire_code() {
	document.CcmDeCodeForm.action = "<c:url value='/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do'/>";
	document.CcmDeCodeForm.submit();	
}
</script>
</head>
<body onLoad="fn_egov_init();">


<!-- 상단타이틀 -->
<form:form modelAttribute="cmmnDetailCodeVO" action="${pageContext.request.contextPath}/sym/ccm/cde/UpdateCcmCmmnDetailCode.do" method="post" onSubmit="fn_egov_updt_code(document.forms[0]); return false;">  
<div class="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.update" /></h2>

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
		<!-- 코드ID -->
		<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId"/> </c:set>
		<tr>
			<th><label for="codeId">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="codeId" title="${title} ${inputSelect}" readonly="true"/>
   				<div><form:errors path="codeId" cssClass="error" /></div>     
			    
			</td>
		</tr>
		
		<!-- 상세코드 -->
		<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code"/> </c:set>
		<tr>
			<th><label for="code">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="code" title="${title} ${inputTxt}" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="code" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 상세코드명 -->
		<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm"/> </c:set>
		<tr>
			<th><label for="codeNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="codeNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="codeNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 상세코드설명 -->
		<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeDc"/> </c:set>
		<tr>
			<th><label for="codeDc">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="codeDc" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="codeDc" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.useAt"/> </c:set>
		<tr>
			<th><label for="useAt">${title } <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="useAt" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="Y"  label=" ${inputYes}"/>
					<form:option value="N"  label=" ${inputNo}"/>
				</form:select>
				<div><form:errors path="useAt" cssClass="error" /></div>       
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
		<a href="<c:url value='/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do' />" class="btn_s" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a>
	</div><div style="clear:both;"></div>
	
</div>
</form:form>

</body>
</html>