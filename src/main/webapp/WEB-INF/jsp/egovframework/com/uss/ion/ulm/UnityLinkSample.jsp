<%--
  Class Name : EgovUnityLinkList.jsp
  Description : 통합링크관리 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2018.09.27    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="uss.ion.ulm.unityLinkSample.unityLink"/></title><!-- 통합링크 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_link_UnityLink(){

	var sLink = fn_egov_SelectBoxValue("selUnityLink");
	if(sLink == ""){
		alert("<spring:message code="uss.ion.ulm.unityLinkSample.validate.select"/>");/* 통합링크를 선택해주세요! */
		document.formUnityLink.selUnityLink.focus();
		return;
	}else{
		document.formUnityLink.action=sLink;
		document.formUnityLink.submit();
	}

}

/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){
			FValue=document.getElementById(sbName).options[i].value;
		}
	}
	return  FValue;
}
</script>
</head>
<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="formUnityLink" action="" method="post" target="_blank">

<div class="wTableFrm" style="width:310px">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.ulm.unityLinkSample.goUnityLinkSample"/></h2><!-- 통합링크 샘플페이지 이동 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:25%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="uss.ion.ulm.unityLinkSample.unityLink"/></th><!-- 통합링크 -->
			<td class="left">
				<select name="selUnityLink" id="selUnityLink" class="select" title="<spring:message code="input.cSelect"/>"><!-- 선택 -->
				   <option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
				   <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				   <option value='${resultInfo.unityLinkUrl}'>${resultInfo.unityLinkNm}</option>
				   </c:forEach>
			   </select>
			   <input class="btn01" type="submit" value="<spring:message code="uss.ion.ulm.unityLinkSample.moveButton"/>" onclick="fn_egov_link_UnityLink();" /><!-- 이동 -->
			</td>
		</tr>
	</table>

	<div style="clear:both;"></div>
</div>
</form>
</body>
</html>