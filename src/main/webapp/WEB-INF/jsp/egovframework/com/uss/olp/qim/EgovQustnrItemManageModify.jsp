<%--
  Class Name : EgovQustnrItemManageModify.jsp
  Description : 설문항목 수정 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.20    김예영          표준프레임워크 v3.7 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09
   
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQim.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="qustnrItemManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrItemManage(){
	
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrItemManage(){
	location.href = "<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrItemManage(){
	var varFrom = document.getElementById("qustnrItemManageVO");

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do' />";
		
		if(!validateQustnrItemManageVO(varFrom)){ 			
			return;
		}else{
			varFrom.submit();
		} 
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrItemManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="qustnrItemManageVO" name="qustnrItemManageVO" action="${pageContext.request.contextPath}/uss/olp/qim/EgovQustnrItemManageModify.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrItemManage(document.forms[0]); return false;">
 
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문정보 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnrCn , crlf , '<br/>')}" escapeXml="false" />
  				<input name="qestnrId" type="hidden" value="<c:out value='${resultList[0].qestnrId}' />">
  				<input name="qestnrTmplatId" type="hidden" value="<c:out value='${resultList[0].qestnrTmplatId}' />"> 
			</td>
		</tr>
		<!-- 설문문항정보 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.qestnrQesitmCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:out value="${fn:replace(resultList[0].qestnrQesitmCn , crlf , '<br/>')}" escapeXml="false" />
  				<input name="qestnrQesitmId" type="hidden" title="<spring:message code='comUssOlpQim.regist.qestnrQesitmCn'/>" value="<c:out value='${resultList[0].qestnrQesitmId}' />"><!-- title="설문문항정보" -->
			</td>
		</tr>
		<!-- 항목순번 -->	
		<c:set var="title"><spring:message code="comUssOlpQim.regist.iemSn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<input name="iemSn" type="text" size="5" title="<spring:message code='comUssOlpQim.regist.iemSn'/>" value="<c:out value='${resultList[0].iemSn}' />" maxlength="5" style="width:100px;"><!-- title="항목 순번" -->
  				<div><form:errors path="iemSn" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 항목내용 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.iemCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<textarea name="iemCn" class="textarea"  cols="75" rows="5" title="<spring:message code='comUssOlpQim.regist.iemCn'/>" style="width:100%;"><c:out value="${resultList[0].iemCn}" /></textarea><!-- title="항목 내용" -->
 				<div><form:errors path="iemCn" cssClass="error" /></div>  
    		</td>
		</tr>
		<!-- 기타답변여부 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.etcAnswerAt"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="etcAnswerAt" title="<spring:message code='comUssOlpQim.regist.etcAnswerAt'/>"><!-- title="기타답변여부" -->
   				<option value="N" <c:if test="${resultList[0].etcAnswerAt ==  'N'}">selected</c:if>>N</option>
   				<option value="Y" <c:if test="${resultList[0].etcAnswerAt ==  'Y'}">selected</c:if>>Y</option>
  				</select>
			</td>
		</tr>
		
	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_egov_save_QustnrItemManage(this.form); return false;"/>
	<!-- <span class="button"><input type="submit" value="저장" onclick="fn_egov_save_QustnrItemManage(document.forms[0]); return false;"></span> -->
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

<input name="qustnrIemId" type="hidden" value="${resultList[0].qustnrIemId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

</form:form>
</div>
</body>
</html>