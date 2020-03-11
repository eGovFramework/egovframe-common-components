<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcMessageItemUpdt.jsp
  * @Description : EgovCntcMessageItemUpdt 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
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
<title><spring:message code="comSsiSyiIms.cntcMessageRegist.title"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntcMessageItem" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_CntcMessageItem(form){
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

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="cntcMessageItem" name="cntcMessageItem" method="post">
<input name="cmd" type="hidden" value="Modify">
<form:hidden path="cntcMessageId"/>
<form:hidden path="itemId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIms.cntcMessageItemUpdt.pageTop.title"/></h2><!-- 연계메시지항목 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemUpdt.cntcMessageList"/> <span class="pilsu">*</span></th><!-- 연계메시지 -->
			<td class="left">
			    <select name="cntcMessageId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiIms.cntcMessageItemUpdt.cntcMessageList"/>"><!-- 연계메시지선택 -->
				<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
				<option value='<c:out value="${result.cntcMessageId}"/>' <c:if test="${cntcMessageItem.cntcMessageId == result.cntcMessageId}">selected="selected"</c:if> ><c:out value="${result.cntcMessageNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemUpdt.itemId"/> <span class="pilsu">*</span></th><!-- 항목ID -->
			<td class="left">
			    <c:out value='${cntcMessageItem.itemId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemUpdt.itemNm"/> <span class="pilsu">*</span></th><!-- 항목명 -->
			<td class="left">
			    <form:input  path="itemNm" title="<spring:message code='comSsiSyiIms.cntcMessageItemUpdt.itemNm'/>" size="100" maxlength="100"/><!-- 항목명 -->
      			<form:errors path="itemNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemUpdt.itemType"/></th><!-- 항목타입 -->
			<td class="left">
			    <form:input  path="itemType" title="<spring:message code='comSsiSyiIms.cntcMessageItemUpdt.itemType'/>" maxlength="20" style="width:128px"/><!-- 항목타입 -->
      			<form:errors path="itemType"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageItemUpdt.itemLt"/></th><!-- 항목길이 -->
			<td class="left">
			    <form:input  path="itemLt" title="<spring:message code='comSsiSyiIms.cntcMessageItemUpdt.itemLt'/>" maxlength="8" style="width:68px"/><!-- 항목길이 -->
      			<form:errors path="itemLt"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" title="<spring:message code="title.save" />" onclick="fn_egov_regist_CntcMessageItem(document.cntcMessageItem); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
