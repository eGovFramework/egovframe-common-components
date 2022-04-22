<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcSystemRegist.jsp
  * @Description : EgovCntcSystemRegist 화면
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
<title><spring:message code="comSsiSyiIis.cntcSystemRegist.title"/></title><!-- 연계시스템 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntcSystem" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CntcSystem(){
	location.href = "<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>";
}
/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_egov_regist_CntcSystem(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateCntcSystem(form)){
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

<form:form modelAttribute="cntcSystem" name="cntcSystem" method="post">
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
<form:hidden path="insttId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIis.cntcSystemRegist.pageTop.title"/></h2><!-- 연계시스템 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemRegist.insttId"/> <span class="pilsu">*</span></th><!-- 기관 -->
			<td class="left">
			    <select name="insttId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiIis.cntcSystemRegist.insttId"/>"><!-- 기관선택 -->
				<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
				<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == cntcSystem.insttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemRegist.sysId"/> <span class="pilsu">*</span></th><!-- 시스템ID -->
			<td class="left">
			    <form:input  path="sysId" maxlength="20" readonly="true" title="<spring:message code='comSsiSyiIis.cntcSystemRegist.sysId'/>" cssClass="readOnlyClass" style="width:128px"/>
      			<form:errors path="sysId"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemRegist.sysNm"/> <span class="pilsu">*</span></th><!-- 시스템명 -->
			<td class="left">
			    <form:input  path="sysNm" size="60" maxlength="60" title="<spring:message code='comSsiSyiIis.cntcSystemRegist.sysNm'/>"/><!-- 시스템명 -->
      			<form:errors path="sysNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemRegist.sysIp"/></th><!-- 시스템IP -->
			<td class="left">
			    <form:input  path="sysIp" size="23" maxlength="23" title="<spring:message code='comSsiSyiIis.cntcSystemRegist.sysIp'/>" style="width:200px"/><!-- 시스템IP -->
      			<form:errors path="sysIp"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" title="<spring:message code="title.save" />" onclick="fn_egov_regist_CntcSystem(document.cntcSystem); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
