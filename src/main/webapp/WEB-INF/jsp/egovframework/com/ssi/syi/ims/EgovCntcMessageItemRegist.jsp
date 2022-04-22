<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcMessageItemRegist.jsp
  * @Description : EgovCntcMessageItemRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ ----------    --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2018.09.10   신용호              표준프레임워크 v3.8 개선
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comSsiSyiIms.cntcMessageItemRegist.title"/></title><!-- 연계메시지항목 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntcMessageItem" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_egov_regist_CntcMessageItem(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateCntcMessageItem(form)){
			return;
		}else{
			form.submit();
		}
	}
}
-->
</script>
</head>

<body>
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="cntcMessageItem" name="cntcMessageItem" method="post">
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
<form:hidden path="cntcMessageId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIms.cntcMessageItemRegist.pageTop.title"/></h2><!-- 연계메시지항목 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemRegist.cntcMessageList"/> <span class="pilsu">*</span></th><!-- 연계메시지 -->
			<td class="left">
			    <select name="cntcMessageId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiIms.cntcMessageItemRegist.cntcMessageList"/>"><!-- 연계메시지 -->
				<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
				<option value='<c:out value="${result.cntcMssageId}"/>' <c:if test="${result.cntcMessageId == cntcMessageItem.cntcMessageId}">selected="selected"</c:if> ><c:out value="${result.cntcMessageNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemRegist.itemId"/> <span class="pilsu">*</span></th><!-- 항목ID -->
			<td class="left">
			    <form:input  path="itemId" title="<spring:message code='comSsiSyiIms.cntcMessageItemRegist.itemId'/>" maxlength="20" readonly="true" cssClass="readOnlyClass" style="width:128px"/><!-- 항목ID -->
      			<form:errors path="itemId"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemRegist.itemNm"/> <span class="pilsu">*</span></th><!-- 항목명 -->
			<td class="left">
			    <form:input  path="itemNm" title="<spring:message code='comSsiSyiIms.cntcMessageItemRegist.itemNm'/>" maxlength="60" style="width:128px"/><!-- 항목명 -->
      			<form:errors path="itemNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemRegist.itemType"/></th><!-- 항목타입 -->
			<td class="left">
			    <form:input  path="itemType" title="<spring:message code='comSsiSyiIms.cntcMessageItemRegist.itemType'/>" maxlength="20" style="width:128px"/><!-- 항목타입 -->
      			<form:errors path="itemType"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemRegist.itemLt"/></th><!-- 항목길이 -->
			<td class="left">
			    <form:input  path="itemLt" title="<spring:message code='comSsiSyiIms.cntcMessageItemRegist.itemLt'/>" maxlength="8" style="width:68px"/><!-- 항목길이 -->
      			<form:errors path="itemLt"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" title="<spring:message code="title.save" />" onclick="fn_egov_regist_CntcMessageItem(document.cntcMessageItem); return false;" />
		<span class="btn_s"><a href="<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>" onclick=""><spring:message code="button.list"/></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>
</DIV>
</body>
</html>
