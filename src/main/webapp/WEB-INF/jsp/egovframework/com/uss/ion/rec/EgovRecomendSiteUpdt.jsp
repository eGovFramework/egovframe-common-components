<%
 /**
  * @Class Name : EgovRecomendSiteUpdt.jsp
  * @Description : EgovRecomendSiteUpdt 화면
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
<c:set var="pageTitle"><spring:message code="comUssIonRec.recomendSiteVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="recomendSiteVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

$(function() {
	$("#confmDe").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'   
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
});

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.getElementById("recomendSiteVO").recomendSiteNm.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_site(form){
	if (!validateRecomendSiteVO(form)) {		 			
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
	recomendSiteVO.action = "<c:url value='/uss/ion/rec/selectRecomendSiteList.do'/>";
	recomendSiteVO.submit();	
}
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀 -->
<form:form modelAttribute="recomendSiteVO" action="${pageContext.request.contextPath}/uss/ion/rec/updateRecomendSite.do" method="post" onSubmit="fn_egov_updt_site(document.forms[0]); return false;">  
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
		
		<!-- 추천사이트명 -->
		<c:set var="title"><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteNm"/> </c:set>
		<tr>
			<th><label for="recomendSiteNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="recomendSiteNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="recomendSiteNm" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 추천사이트URL -->
		<c:set var="title"><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteUrl"/> </c:set>
		<tr>
			<th><label for="recomendSiteUrl">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="recomendSiteUrl" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="recomendSiteUrl" cssClass="error" /></div>     
			</td>
		</tr>
		
		<!-- 추천사이트설명 -->
		<c:set var="title"><spring:message code="comUssIonRec.recomendSiteVO.recomendSiteDc"/> </c:set>
		<tr>
			<th><label for="recomendSiteDc">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="recomendSiteDc" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="recomendSiteDc" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 추천사유내용 -->
		<c:set var="title"><spring:message code="comUssIonRec.recomendSiteVO.recomendResnCn"/> </c:set>
		<tr>
			<th><label for="recomendResnCn">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="recomendResnCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="recomendResnCn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 추천승인여부 -->
		<c:set var="title"><spring:message code="comUssIonRec.recomendSiteVO.recomendConfmAt"/> </c:set>
		<tr>
			<th><label for="recomendConfmAt">${title } </label></th>
			<td class="left">
				<form:select path="recomendConfmAt" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:option value="Y"  label="예" />
	  		   		<form:option value='N'>아니오</form:option>
				</form:select>
				<div><form:errors path="recomendConfmAt" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 승인일자 -->
		<c:set var="title"><spring:message code="comUssIonRec.recomendSiteVO.confmDe"/> </c:set>
		<tr>
			<th><label for="confmDe">${title} </label></th>
			<td class="left" colspan="3">
				<form:input path="confmDe" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:70px;"/>
				<div><form:errors path="confmDe" cssClass="error" /></div>       
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/rec/selectRecomendSiteList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>


<input name="recomendSiteId" type="hidden" value="<c:out value='${recomendSiteVO.recomendSiteId}'/>">
</form:form>

</body>
</html>
