<%
 /**
  * @Class Name : EgovFaqRegist.jsp
  * @Description : EgovFaqRegist 화면
  * @Modification Information
  * @
  * @ 수정일               수정자                수정내용
  * @ ----------   ---------    ---------------------------
  * @ 2009.02.01   박정규			최초 생성
  * @ 2016.06.13   김연호			표준프레임워크 v3.6 개선
  * @ 2020.10.30   신용호			파일 확장자 및 사이즈 체크 누락 수정
  * @ 2024.10.29   권태성			element들의 올바른 id 값을 지정
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
<c:set var="pageTitle"><spring:message code="comUssOlhFaq.faqVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="faqVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	var maxFileNum = 3;
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	//------------------------- 첨부파일 등록 End
	
	// 첫 입력란에 포커스
	document.getElementById("faqVO").qestnSj.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_faq(form){
	
	var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
	if (!resultExtension) return true;
	var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
	if (!resultSize) return true;
	
	//input item Client-Side validate
	if (!validateFaqVO(form)) {	
		return false;
	} else {
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}
</script>

</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="faqVO" action="${pageContext.request.contextPath}/uss/olh/faq/insertFaq.do" method="post" onSubmit="fn_egov_regist_faq(document.forms[0]); return false;" enctype="multipart/form-data"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 질문 제목   -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.qestnSj"/> </c:set>
		<tr>
			<th><label for="qestnSj">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="qestnSj" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="qestnSj" cssClass="error" /></div>     
			</td>
		</tr>

		<!-- 질문 내용  -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.qestnCn"/> </c:set>
		<tr>
			<th><label for="qestnCn">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="qestnCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="qestnCn" cssClass="error" /></div>  
			</td>
		</tr>

		<!-- 답변 내용  -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.answerCn"/> </c:set>
		<tr>
			<th><label for="answerCn">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="answerCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="answerCn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 첨부파일  -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.atchFile"/></c:set>
		<tr>
			<th><label for="egovComFileUploader">${title}</label> </th>
			<td class="nopd">
			<!-- attached file Start -->
			<div>
				<input name="file_1" id="egovComFileUploader" type="file" multiple/><!-- 첨부파일명 입력 -->
				<div id="egovComFileList"></div>
			</div>
			<!-- attached file End -->
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olh/faq/selectFaqList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

</body>
</html>
