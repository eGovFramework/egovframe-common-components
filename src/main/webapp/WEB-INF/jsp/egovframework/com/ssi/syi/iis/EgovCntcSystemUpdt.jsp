<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcSystemUpdt.jsp
  * @Description : EgovCntcSystemUpdt 화면
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
<title><spring:message code="comSsiSyiIis.cntcSystemUpdt.title"/></title><!-- 연계시스템 수정 -->
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
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_CntcSystem(form){
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
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<!-- 상단타이틀 -->
<form:form commandName="cntcSystem" name="cntcSystem" method="post">
<input name="cmd" type="hidden" value="Modify">
<form:hidden path="insttId"/>
<form:hidden path="sysId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIis.cntcSystemUpdt.pageTop.title"/></h2><!-- 연계시스템 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemUpdt.insttId"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="insttId" class="select" disabled="disabled" title="<spring:message code="comSsiSyiIis.cntcSystemUpdt.insttId"/>">
					<c:forEach var="result" items="${cntcInsttList}" varStatus="status">
						<option value='<c:out value="${result.insttId}"/>' <c:if test="${result.insttId == cntcSystem.insttId}">selected="selected"</c:if> ><c:out value="${result.insttNm}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemUpdt.sysId"/> <span class="pilsu">*</span></th><!-- 시스템ID -->
			<td class="left">
			    <c:out value='${cntcSystem.sysId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemUpdt.sysNm"/> <span class="pilsu">*</span></th><!-- 시스템명 -->
			<td class="left">
			    <form:input  path="sysNm" size="60" maxlength="60" title="<spring:message code='comSsiSyiIis.cntcSystemUpdt.sysNm'/>"/><!-- 시스템명 -->
      			<form:errors path="sysNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcSystemUpdt.sysIp"/></th><!-- 시스템IP -->
			<td class="left">
			    <form:input  path="sysIp" maxlength="23" title="<spring:message code='comSsiSyiIis.cntcSystemUpdt.sysIp'/>" cssStyle="width:128px"/>
      			<form:errors path="sysIp"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_CntcSystem(document.cntcSystem);return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>

</DIV>

</body>
</html>
