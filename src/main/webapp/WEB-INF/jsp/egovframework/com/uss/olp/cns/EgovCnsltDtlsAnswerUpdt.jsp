<%
 /**
  * @Class Name : EgovCnsltCnAnswerUpdt.jsp
  * @Description : 상담내역답변 수정 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.18   장동한              표준프레임워크 v3.6 개선s
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
<c:set var="pageTitle"><spring:message code="comUssOlpCnm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cnsltManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_cnsltdtlsanswer(){

	// 첫 입력란에 포커스..
	cnsltManageVO.managtCn.focus();

}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_cnsltdtlsanswer(form, cnsltId){

	// 서버사이드 테스트용
	/*
		form.action = "<c:url value='/uss/olh/wor/WordDicaryRegist.do'/>";
		form.submit();
		return;
	*/

	if (!validateCnsltManageVO(form)) {

		return;

	} else {


		form.cnsltId.value = cnsltId;
		form.action = "<c:url value='/uss/olp/cnm/CnsltDtlsAnswerUpdt.do'/>";
		form.submit();

	}

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnaanswerlist() {

	cnsltManageVO.action = "<c:url value='/uss/olp/cnm/CnsltAnswerListInqire.do'/>";
	cnsltManageVO.submit();

}

/* ********************************************************
 * 메일발송을 위한 화면을 호출
 ******************************************************** */
function fn_egov_pop_mailform() {

	var url 	= "<c:url value='/ems/insertSndngMailView.do'/>";
	var	status 	= "width=640,height=480,top=200,left=200";

	window.open(url,'popup', status);

}


</script>
</head>
<body onLoad="fn_egov_initl_cnsltdtlsanswer();">
<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="cnsltManageVO" action="${pageContext.request.contextPath}/uss/olp/cnm/CnsltDtlsAnswerUpdt.do" method="post">

<!-- hidden 화일정의 -->
<input name="cnsltId" type="hidden" value="<c:out value='${result.cnsltId}'/>">

<input name="writngPassword" type="hidden" value="<c:out value='${result.writngPassword}'/>">

<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>


	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 작성자명 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.wrterNm"/></c:set>
		<tr>
			<th><label for="wrterNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="wrterNm" title="${title} ${inputTxt}" size="70" maxlength="70" value="${result.wrterNm}" />
   				<div><form:errors path="wrterNm" cssClass="error" /></div>     
			</td>
		</tr>

		<!-- 전화번호 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.telNo"/></c:set>
		<c:set var="telNo1"><spring:message code="comUssOlpCns.regist.telNo1"/></c:set>
		<c:set var="telNo2"><spring:message code="comUssOlpCns.regist.telNo2"/></c:set>
		<c:set var="telNo3"><spring:message code="comUssOlpCns.regist.telNo3"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		        <form:input path="areaNo" size="4" maxlength="4" title="${telNo1}" style="width:30px;" value="${result.areaNo}"/>-<!-- 전화번호(지역) -->
		        <form:input path="middleTelno" size="4" maxlength="4" title="${telNo2}" style="width:30px;" value="${result.middleTelno}"/>-<!-- 전화번호(국번) -->
		        <form:input path="endTelno" size="4" maxlength="4"  title="${telNo3}" style="width:30px;" value="${result.endTelno}"/><!-- 전화번호(지번) -->
		    	<div><form:errors path="areaNo"/></div>
		    	<div><form:errors path="middleTelno"/></div>
		    	<div><form:errors path="endTelno"/></div>
			</td>
		</tr>
		<!-- 휴대폰번호 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.mobileNo"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label></th>
			<td class="nopd">
				<input name="firstMoblphonNo" 	value="<c:out value='${result.firstMoblphonNo}'/>"	type="text" size="5"  maxlength="5" title="<spring:message code="comUssOlpCns.regist.mobileNo1"/>" style="width:30px;">-<!-- 휴대폰전화번호(앞번) -->
				<input name="middleMbtlnum" 	value="<c:out value='${result.middleMbtlnum}'/>" 	type="text" size="5"  maxlength="5" title="<spring:message code="comUssOlpCns.regist.mobileNo2"/>" style="width:30px;">-<!-- 휴대폰전화번호(국번) -->
				<input name="endMbtlnum" 		value="<c:out value='${result.endMbtlnum}'/>"		type="text" size="5"  maxlength="5" title="<spring:message code="comUssOlpCns.regist.mobileNo3"/>" style="width:30px;"><!-- 휴대폰전화번호(지번) -->
			</td>
		</tr>
		<!-- 이메일 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.email"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> </th>
			<td class="nopd">
				<input name="emailAdres" 	type="text" size="30" value="<c:out value='${result.emailAdres}'/>" maxlength="30" title="${title} ${inputTxt}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="emailAnswerAt" type="checkbox" disabled value="Y" <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if> title="<spring:message code="comUssOlpCns.regist.emailAt"/>"><spring:message code="comUssOlpCns.regist.emailAt"/><!-- 이메일답변여부 -->
				
				
			  	<c:if test="${result.emailAnswerAt == 'Y'}">
  			    &nbsp;&nbsp;&nbsp;
				<a href = "<c:url value='/images/egovframework/com/cmm/icon/search2.gif'/>" onClick="javascript:fn_egov_pop_mailform(); return false;" alt="발송메일 등록 팝업"></a>
 				</c:if>
			</td>
		</tr>
		<!-- 상담제목 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.cnsltSj"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		        <form:input path="cnsltSj" size="70" maxlength="70" title="${title} ${inputTxt}" value="${result.cnsltSj}" />
		    	<div><form:errors path="cnsltSj"/></div>
			</td>
		</tr>
		<!-- 상담내용 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.cnsltCn"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="cnsltCn" title="${title} ${inputTxt}" cols="300" rows="30" style="height:200px;" value="${result.cnsltCn}"/>   
				<div><form:errors path="cnsltCn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 처리상태 -->
		<c:set var="title"><spring:message code="comUssOlpCns.detail.qnaProcessSttusCodeNm"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		     	<select name="qnaProcessSttusCode" class="select" title="${title}">
		  		   <option selected value="<c:out value='${result.qnaProcessSttusCode}'/>"><c:out value='${result.qnaProcessSttusCodeNm}'/>&nbsp;&nbsp;</option>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
					</c:forEach>
		  		</select>
			</td>
		</tr>
		<!-- 답변내용 -->
		<c:set var="title"><spring:message code="comUssOlpCns.detail.managtCn"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="managtCn" title="${title} ${inputTxt}" cols="300" rows="30" style="height:200px;" />   
				<div><form:errors path="managtCn" cssClass="error" /></div>  
			</td>
		</tr>
		
	</tbody>
	</table>


<!-- 하단 버튼 -->
<div class="btn">
	<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" />
	<span class="btn_s"><a href="<c:url value='/uss/olp/cnm/CnsltAnswerListInqire.do' />" onclick="fn_egov_inqire_qnaanswerlist(); return false;"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>


</form:form>
</DIV>

</body>
</html>
