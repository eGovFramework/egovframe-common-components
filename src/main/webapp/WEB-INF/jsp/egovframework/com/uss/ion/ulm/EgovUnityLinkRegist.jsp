<%--
  Class Name : EgovUnityLinkRegist.jsp
  Description : 통합링크관리 등록 페이지
  Modification Information

      수정일               수정자           수정내용
   ----------   --------   ---------------------------
   2008.03.09   장동한			최초 생성
   2018.08.16   이정은			공통컴포넌트 3.8 개선
   2019.12.10   신용호			KISA 보안약점 조치 (HTMLArea Editor삭제)
   2024.10.29   권태성			불필요한 코드 삭제

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="uss.ion.ulm.unityLinkRegist.unityLinkRegist" /></title><!-- 통합링크관리 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>" ></script>
<validator:javascript formName="unityLink" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_UnityLink(){

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_UnityLink(){
	var varFrom = document.unityLink;
	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/uss/ion/ulm/registUnityLink.do' />";
		if(!validateUnityLink(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	}
}


</script>
</head>
<body onLoad="fn_egov_init_UnityLink();">

<%-- noscript 태그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다 -->

<form:form modelAttribute="unityLink" name="unityLink" action="${pageContext.request.contextPath}/uss/ion/ulm/registUnityLink.do" enctype="multipart/form-data" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.ulm.unityLinkRegist.unityLinkRegist" /></h2><!-- 통합링크관리 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkRegist.unityLinkNm" /><span class="pilsu">*</span></th><!-- 통합링크명 -->
			<td class="left">
			    <form:input path="unityLinkNm" size="73" cssClass="txaIpt" maxlength="255"/>
      			<form:errors path="unityLinkNm" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkRegist.unityLinkGroup" /><span class="pilsu">*</span></th><!-- 통합링크그룹 -->
			<td class="left">
			    <form:select path="unityLinkSeCode">
			    	<c:set var="cSelect"><spring:message code="input.cSelect"/></c:set>
		            <form:option value="" label="${cSelect}"/><!-- 선택 -->
		            <form:options items="${unityLinkSeCodeList}" itemValue="code" itemLabel="codeNm"/>
		        </form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkRegist.unityLinkUrl" /><span class="pilsu">*</span></th><!-- 통합링크URL -->
			<td class="left">
			    <form:input path="unityLinkUrl" size="73" cssClass="txaIpt" maxlength="255"/>
      			<form:errors path="unityLinkUrl" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkRegist.unityLinkDc" /><span class="pilsu">*</span></th><!-- 통합링크설명 -->
			<td class="left">
			    <form:textarea path="unityLinkDc" rows="75" cols="14" cssClass="txaClass2" cssStyle="height:356px"/>
				<form:errors path="unityLinkDc" cssClass="error"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_UnityLink(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ulm/listUnityLink.do' />" onclick=""><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</form:form>
</body>
</html>
