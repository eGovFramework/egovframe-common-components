<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
 /**
  * @Class Name : EgovSiteMapng.jsp
  * @Description : 사이트맵 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.10    이용          최초 생성
  *   2011.07.29  서준식          사이트 맵 생성이 안되었을 때 발생하는 오류 수정
  *   2018.10.11  이정은          공통컴포넌트 3.8 개선(사이트 맵 생성이 안되었을 때 안내 메시지 추가, 다국어처리, 퍼블리싱)
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<title><spring:message code="comSymMnuStm.siteMapng.siteMap"/></title><!-- 사이트맵 -->
<!-- <style type="text/css">
.location{ font-family:"돋움"; font-size:8pt; color:#6d6d6d ;padding-top:1px; padding-left:3px; 
           vertical-align: middle; text-dacoration: none}
</style> -->
<script language="javascript1.2">
<!--
/* ********************************************************
 * 조회 함수
 ******************************************************** */
function fSiteMapng() {
	document.siteMapngForm.action = "<c:url value='/sym/mnu/stm/EgovSiteMapng.do'/>";
    document.siteMapngForm.submit();
}
/* ********************************************************
 * Url Call 함수
 ******************************************************** */
function fCallUrl(fURL){
	var vPath = "<c:url value='/'/>" +fURL;
	window.location = vPath.replace("//","/");
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
</head>
<body>

<title><spring:message code="comSymMnuStm.siteMapng.siteMap"/></title><!-- 사이트맵 -->


<div class="wTableFrm">
<form name="siteMapngForm" action ="javascript:fSiteMapng()" method="post">
<!-- 타이틀 -->
<h2><spring:message code="comSymMnuStm.siteMapng.siteMap"/></h2><!-- 사이트맵 -->
<!-- 가로배열 -->
<table>
  <tr>
    <input type="hidden" name="scrtyId" value="">
	<c:if test="${!empty resultVO.bndeFileNm}">
		<c:import url="${fn:trim(resultVO.bndeFileNm)}" />
	</c:if>
	<c:if test="${empty resultVO.bndeFileNm}">
		<span><spring:message code="comSymMnuStm.siteMapng.emptySiteMap"/></span>
		<!-- 1100.메뉴생성관리 > [메뉴생성]클릭 > 목록에서 체크선택 > [메뉴생성]클릭 > [사이트맵생성] 클릭 > 팝업에서 [사이트맵생성]클릭 과정을 거쳐야 사이트맵 생성이 됩니다. -->
	</c:if>
  </tr>
</table>
</div>
</form>
</body>
</html>

