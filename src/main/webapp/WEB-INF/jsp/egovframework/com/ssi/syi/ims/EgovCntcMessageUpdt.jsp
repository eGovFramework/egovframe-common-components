<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcMessageUpdt.jsp
  * @Description : EgovCntcMessageUpdt 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2011.09.14   서준식              저장버튼 CSS수정
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
<title><spring:message code="comSsiSyiIms.cntcMessageUpdt.title"/></title><!-- 연계메시지 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntcMessage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_CntcMessage(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateCntcMessage(form)){
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

<form:form modelAttribute="cntcMessage" name="cntcMessage" method="post">
<input name="cmd" type="hidden" value="Modify">
<form:hidden path="cntcMessageId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIms.cntcMessageUpdt.pageTop.title"/></h2><!-- 연계메시지 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageUpdt.cntcMessageId"/> <span class="pilsu">*</span></th><!-- 연계메시지ID -->
			<td class="left">
			    <c:out value='${cntcMessage.cntcMessageId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageUpdt.cntcMessageNm"/> <span class="pilsu">*</span></th><!-- 연계메시지명 -->
			<td class="left">
			    <form:input  path="cntcMessageNm" title="연계메시지명" size="60" maxlength="60"/><!-- 연계메시지명 -->
      			<form:errors path="cntcMessageNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIms.cntcMessageUpdt.upperCntcMessageId"/></th><!-- 상위연계메시지ID -->
			<td class="left">
			    <select name="upperCntcMessageId" class="select" title="상위연계메시지ID">
				<option selected value=''>--선택하세요--</option><!-- 선택하세요 -->
				<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
				<option value='<c:out value="${result.cntcMessageId}"/>' <c:if test="${result.cntcMessageId == cntcMessage.upperCntcMessageId}">selected="selected"</c:if> ><c:out value="${result.cntcMessageNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" title="<spring:message code="title.save" />" onclick="fn_egov_modify_CntcMessage(document.cntcMessage); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/ssi/syi/ims/getCntcMessageList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
