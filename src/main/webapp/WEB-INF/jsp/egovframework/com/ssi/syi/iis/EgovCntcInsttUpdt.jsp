<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcInsttUpdt.jsp
  * @Description : EgovCntcInsttUpdt 화면
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
<title><spring:message code="comSsiSyiIis.cntcInsttUpdt.title"/></title><!-- 연계기관 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntcInstt" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CntcInstt(){
	location.href = "<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_CntcInstt(form){
	if(confirm("<spring:message code='common.save.msg' />")){
		if(!validateCntcInstt(form)){
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

<form:form commandName="cntcInstt" name="cntcInstt" method="post">
<input name="cmd" type="hidden" value="Modify">
<form:hidden path="insttId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIis.cntcInsttUpdt.pageTop.title"/></h2><!-- 연계기관 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcInsttUpdt.insttId"/> <span class="pilsu">*</span></th><!-- 기관ID -->
			<td class="left">
			    <c:out value='${cntcInstt.insttId}'/>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcInsttUpdt.insttNm"/> <span class="pilsu">*</span></th><!-- 기관명 -->
			<td class="left">
			    <form:input  path="insttNm" size="60" maxlength="60" title="<spring:message code='comSsiSyiIis.cntcInsttUpdt.insttNm'/>"/><!-- 기관명 -->
      			<form:errors path="insttNm"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_CntcInstt(document.cntcInstt)" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
