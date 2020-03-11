<%
 /**
  * @Class Name : EgovQnaAnswerUpdt.jsp
  * @Description : EgovQnaAnswerUpdt 화면
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
<c:set var="pageTitle"><spring:message code="comUssOlhQna.qnaAnswerVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="qnaVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.getElementById("qnaVO").qestnSj.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_qna(form){
	if (!validateQnaVO(form)) {		 			
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
	qnaVO.action = "<c:url value='/uss/olh/qna/selectQnaAnswerList.do'/>";
	qnaVO.submit();	
}
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀 -->
<form:form commandName="qnaVO" action="${pageContext.request.contextPath}/uss/olh/qna/updateQnaAnswer.do" method="post" onSubmit="fn_egov_updt_qna(document.forms[0]); return false;">  
<div class="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		
		<!-- 작성자명 -->
		<c:set var="title"><spring:message code="table.reger"/> </c:set>
		<tr>
			<th><label for="wrterNm">${title} </label></th>
			<td class="left" colspan="3">
			    <form:input path="wrterNm" title="${title} ${inputTxt}" size="70" maxlength="70" readonly="true"/>
   				<div><form:errors path="wrterNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 전화번호 -->
		<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.telNo"/> </c:set>
		<tr>
			<th><label for="areaNo">${title} </label></th>
			<td class="left" colspan="3">
			    <form:input path="areaNo" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:30px;" readonly="true"/>&nbsp;-&nbsp;
			    <form:input path="middleTelno" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:30px;" readonly="true"/>&nbsp;-&nbsp;
			    <form:input path="endTelno" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:30px;" readonly="true"/>
   				<div><form:errors path="areaNo" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 이메일주소 -->
		<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.emailAdres"/> </c:set>
		<tr>
			<th><label for="emailAdres">${title}</label></th>
			<td class="left">
			    <form:input path="emailAdres" title="${title} ${inputTxt}" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="emailAdres" cssClass="error" /></div>     
			</td>
			<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.emailAnswerAt"/> </c:set>
			<th><label for="emailAnswerAt">${title}</label></th>
			<td class="left"><form:checkbox path="emailAnswerAt" value="Y" disabled="true"  />
			<div><form:errors path="emailAdres" cssClass="error" /></div></td>
		</tr>
		
		<!-- 질문제목 -->
		<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.qestnSj"/> </c:set>
		<tr>
			<th><label for="qestnSj">${title} </label></th>
			<td class="left" colspan="3">
			    <form:input path="qestnSj" title="${title} ${inputTxt}" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="qestnSj" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 질문내용 -->
		<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.qestnCn"/> </c:set>
		<tr>
			<th><label for="qestnCn">${title } </label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="qestnCn" title="${title} ${inputTxt}" cols="300" rows="20" readonly="true" />   
				<div><form:errors path="qestnCn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 답변상태코드 -->
		<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.qnaProcessSttusCode"/> </c:set>
		<tr>
			<th><label for="qnaProcessSttusCode">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="qnaProcessSttusCode" title="${title} ${inputTxt}" cssClass="txt">
					<form:options items="${qnaProcessSttusCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
				<div><form:errors path="qnaProcessSttusCode" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 답변내용 -->
		<c:set var="title"><spring:message code="comUssOlhQna.qnaVO.answerCn"/> </c:set>
		<tr>
			<th><label for="answerCn">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd" colspan="3">
				<form:textarea path="answerCn" title="${title} ${inputTxt}" cols="300" rows="20"  />   
				<div><form:errors path="answerCn" cssClass="error" /></div>  
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olh/qna/selectQnaAnswerList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>


<input name="qaId" type="hidden" value="<c:out value='${qnaVO.qaId}'/>">
</form:form>

</body>
</html>
