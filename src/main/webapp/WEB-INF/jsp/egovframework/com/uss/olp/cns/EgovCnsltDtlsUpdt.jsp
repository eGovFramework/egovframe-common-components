<%
 /**
  * @Class Name : EgovCnsltDtlsUpdt.jsp
  * @Description : 상담 수정 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2011.09.27   서준식              상담 수정시 validation 에러로 수정 되지 않는 문제 수정
  *   2016.06.18   장동한              표준프레임워크 v3.6 개선
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
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<c:set var="pageTitle"><spring:message code="comUssOlpCns.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cnsltManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_cnsltdtls(){

	//------------------------------------------
	//------------------------- 첨부파일 수정 Start
	//-------------------------------------------
	var maxFileNum = 3
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum);
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	//------------------------- 첨부파일 수정 End
	
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_cnsltdtls(form, cnsltId){

		if (!validateCnsltManageVO(form)) {

			return;

		} else {

			form.cnsltId.value = cnsltId;
			form.action = "<c:url value='/uss/olp/cns/CnsltDtlsUpdt.do'/>";
			form.submit();

		}

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnalist() {

	cnsltManageVO.action = "<c:url value='/uss/olp/cns/CnsltListInqire.do'/>";
	cnsltManageVO.submit();

}
</script>
</head>
<body onLoad="fn_egov_initl_cnsltdtls();">
<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form modelAttribute="cnsltManageVO" action="${pageContext.request.contextPath}/uss/olp/cns/CnsltDtlsUpdt.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_updt_cnsltdtls(document.forms[0],'${result.cnsltId}'); return false;">

<!-- CnsltDtlsUpdtView.do Call을 위한 처리  -->
<input name="cnsltId" type="hidden" value="<c:out value='${result.cnsltId}'/>">
<c:choose>
	<c:when test="${result.managtCn == null}">
		<input name="managtCn" type="hidden" value="미답변">
	</c:when>
	<c:otherwise>
		<input name="managtCn" type="hidden" value="<c:out value='${result.managtCn}'/>">
	</c:otherwise>
</c:choose>

<!-- 첨부파일 개수 설정을 위한 Hidden 설정 -->
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

<!-- 첨부파일 삭제 후 리턴 URL -->
<input type="hidden" name="returnUrl" value="<c:url value='/uss/olp/cns/CnsltDtlsUpdtView.do'/>"/>

<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
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
			    <form:input path="wrterNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="wrterNm" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 작성 비밀번호 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.writngPassword"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:password path="writngPassword" size="20" maxlength="20" title="${title} ${inputTxt}" />
				<div><form:errors path="writngPassword"/></div>
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
		        <form:input path="areaNo" size="4" maxlength="4" title="${telNo1}" style="width:30px;" />-<!-- 전화번호(지역) -->
		        <form:input path="middleTelno" size="4" maxlength="4" title="${telNo2}" style="width:30px;" />-<!-- 전화번호(국번) -->
		        <form:input path="endTelno" size="4" maxlength="4"  title="${telNo3}" style="width:30px;" /><!-- 전화번호(지번) -->
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
				<input name="firstMoblphonNo" 		type="text" size="5"  maxlength="5" title="<spring:message code="comUssOlpCns.regist.mobileNo1"/>" style="width:30px;">-<!-- 휴대폰전화번호(앞번) -->
				<input name="middleMbtlnum" 		type="text" size="5"  maxlength="5" title="<spring:message code="comUssOlpCns.regist.mobileNo2"/>" style="width:30px;">-<!-- 휴대폰전화번호(국번) -->
				<input name="endMbtlnum" 			type="text" size="5"  maxlength="5" title="<spring:message code="comUssOlpCns.regist.mobileNo3"/>" style="width:30px;"><!-- 휴대폰전화번호(지번) -->
			</td>
		</tr>
		<!-- 이메일 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.email"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> </th>
			<td class="nopd">
				<input name="emailAdres" 	type="text" size="30" value="<c:out value='${result.emailAdres}'/>" maxlength="30" title="${title} ${inputTxt}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="emailAnswerAt" type="checkbox" value="Y" title="<spring:message code="comUssOlpCns.regist.emailAt"/>"><spring:message code="comUssOlpCns.regist.emailAt"/><!-- 이메일답변여부 -->
			</td>
		</tr>
		<!-- 상담제목 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.cnsltSj"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		        <form:input path="cnsltSj" size="70" maxlength="70" title="${title} ${inputTxt}" />
		    	<div><form:errors path="cnsltSj"/></div>
			</td>
		</tr>
		<!-- 상담내용 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.cnsltCn"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="cnsltCn" title="${title} ${inputTxt}" cols="300" rows="30" style="height:200px;" />   
				<div><form:errors path="cnsltCn" cssClass="error" /></div>  
			</td>
		</tr>
		<!-- 파일첨부 -->
		<c:set var="title"><spring:message code="comUssOlpCns.regist.file"/></c:set>
		<tr>
			<th><label for="cnsltCn">${title}</label> </th>
			<td class="nopd">
			<!-- 첨부목록을 보여주기 위한 -->
			<c:if test="${result.atchFileId != ''}">
				<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
					<c:param name="param_atchFileId" value="${egovc:encrypt(result.atchFileId)}" />
				</c:import>		
			</c:if>
			
		    <!-- attached file Start -->
			<div>
				<input type="file" multiple name="file_1" id="egovComFileUploader">
				<div id="egovComFileList"></div>
			</div>
			<!-- attached file End -->
			
			</td>
		</tr>
	</tbody>
	</table>

 <c:if test="${result.atchFileId == null}">
  	<!-- <input type="hidden" name="fileListCnt" id="fileListCnt" value="0">  -->
  	<input name="atchFileAt" type="hidden" value="N">
  </c:if>

  <c:if test="${result.atchFileId != null}">
  	<input name="atchFileAt" type="hidden" value="Y">
  </c:if>


<!-- 하단 버튼 -->
<div class="btn">
	<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" />
	<span class="btn_s"><a href="<c:url value='/uss/olp/cns/CnsltListInqire.do' />" onclick="fn_egov_inqire_qnalist(); return false;"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

</form:form>
</DIV>

</body>
</html>
