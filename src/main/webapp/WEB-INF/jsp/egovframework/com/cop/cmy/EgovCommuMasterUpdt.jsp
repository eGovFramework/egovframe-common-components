<%
 /**
  * @Class Name : EgovCommuMasterUpdt.jsp
  * @Description : EgovCommuMasterUpdt 화면
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
<c:set var="pageTitle"><spring:message code="comCopCmy.commuMasterVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title><!-- 커뮤니티 마스터 수정 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="commuMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>
<c:set var="updtYes"><spring:message code="comCopCmy.commuMasterVO.updt.yes" /></c:set>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.getElementById("commuMasterVO").cmmntyNm.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_commu(form, cmmntyId){
	if (!validateCommuMasterVO(form)) {		 			
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
function fn_egov_inqire_commulist() {
	commuMasterVO.action = "<c:url value='/cop/cmy/selectCommuMasterList.do'/>";
	comnuMasterVO.submit();	
}
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀 -->
<form:form commandName="commuMasterVO" action="${pageContext.request.contextPath}/cop/cmy/updateCommuMaster.do" method="post" onSubmit="fn_egov_updt_commu(document.forms[0]); return false;">  
<div class="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.update" /></h2><!-- 커뮤니티 마스터 수정 -->

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 커뮤니티명 -->
		<c:set var="title"><spring:message code="comCopCmy.commuMasterVO.updt.cmmntyNm"/> </c:set>
		<tr>
			<th><label for="cmmntyNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="cmmntyNm" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="cmmntyNm" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 커뮤니티 소개내용 -->
		<c:set var="title"><spring:message code="comCopCmy.commuMasterVO.updt.cmmntyIntrcn"/> </c:set>
		<tr>
			<th><label for="cmmntyIntrcn">${title} <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="cmmntyIntrcn" title="${title} ${inputTxt}" cols="300" rows="20"/>   
				<div><form:errors path="cmmntyIntrcn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="comCopCmy.commuMasterVO.updt.useAt"/> </c:set>
		<tr>
			<th><label for="useAt">${title } <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="useAt" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="Y" label="${updtYes}" />
	  		   		<form:option value='N'><spring:message code='comCopCmy.commuMasterVO.updt.no'/></form:option>
				</form:select>
				<div><form:errors path="useAt" cssClass="error" /></div>       
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/cop/cmy/selectCommuMasterList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmmntyId" type="hidden" value="${commuMasterVO.cmmntyId}">
</form:form>

</body>
</html>
