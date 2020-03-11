<%
 /**
  * @Class Name : EgovCcmCmmnClCodeRegist.jsp
  * @Description : 공통분류코드 등록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.08.07   이정은              표준프레임워크 v3.7 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comSymCcmCcc.cmmnClCodeVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnClCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	// 첫 입력란에 포커스
	document.getElementById("cmmnClCodeVO").clCode.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_code(form){
	//input item Client-Side validate
	if (!validateCmmnClCodeVO(form)) {	
		return false;
	} else {
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}

/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
function fncShowMessg(){
	if("<c:out value='${message}'/>" != ''){
	alert("<c:out value='${message}'/>");
	}
}

</script>

</head>
<body onLoad="fn_egov_init(); fncShowMessg();">

<form:form commandName="cmmnClCodeVO" action="${pageContext.request.contextPath}/sym/ccm/ccc/RegistCcmCmmnClCode.do" method="post" onSubmit="fn_egov_regist_code(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
		<!-- 분류코드 -->
		<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCode"/> </c:set>
		<tr>
			<th><label for="clCode">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="clCode" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="clCode" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 분류코드명 -->
		<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCodeNm"/> </c:set>
		<tr>
			<th><label for="clCodeNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="clCodeNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="clCodeNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 분류코드설명 -->
		<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCodeDc"/> </c:set>
		<tr>
			<th><label for="clCodeDc">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="clCodeDc" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="clCodeDc" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.useAt"/> </c:set>
		<tr>
			<th>${title } <span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="useAt" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value="Y"  label=" ${inputYes}"/>
					<form:option value="N" label=" ${inputNo}"/>
				</form:select>
				<div><form:errors path="useAt" cssClass="error" /></div>       
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/sym/ccm/ccc/SelectCcmCmmnClCodeList.do' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

</body>
</html>
