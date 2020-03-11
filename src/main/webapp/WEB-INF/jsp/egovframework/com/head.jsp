<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.cmm.util.EgovUserDetailsHelper" %>
<script language="text/javascript" src="<c:url value='/js/egovframework/com/main.js' />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/main_portal.css' />">
<script type="text/javascript">
	function fn_main_headPageMove(menuNo, url){
		document.selectOne.menuNo.value=menuNo;
		document.selectOne.chkURL.value=url;
	    document.selectOne.action = "<c:url value='/sym/mnu/mpm/EgovMainMenuIndex.do'/>";
	    document.selectOne.submit();
	}

	function actionLogout()
	{
		document.selectOne.action = "<c:url value='/uat/uia/actionLogout.do'/>";
		document.selectOne.submit();
		//top.document.location.href = "<c:url value='/j_spring_security_logout'/>";
	}
</script>

<form name="selectOne">
<input name="menuNo" type="hidden" />
<input name="chkURL" type="hidden" />
    <div id="gnb">
    <div id="top_logo"><a href="<c:url value='/sym/mnu/mpm/EgovMainMenuHome.do' />" target=_top><img src="<c:url value='/images/egovframework/com/cmm/main/logo_01.gif' />" alt="egovframe" /></a></div>
    <div id="use_descri">
            <ul>
                <li><spring:message code="comSymMnuMpm.head.head"/></li><!-- 공통서비스 테스트 사이트 -->
                <li><a href="javascript:actionLogout()"><img src="<c:url value='/images/egovframework/com/cmm/main/logout_btn.gif' />" alt="로그아웃" /></a></li>
            </ul>
    </div>
    </div>
    <div id="new_topnavi">
        <ul>
			<li><a href="<c:url value='/sym/mnu/mpm/EgovMainMenuHome.do' />" target="_top">HOME</a></li>
			<c:forEach var="result" items="${list_headmenu}" varStatus="status">
			   <li class="gap"> l </li>
			   <li><a href="javascript:fn_main_headPageMove('<c:out value="${result.menuNo}"/>','<c:out value="${result.chkURL}"/>')"><c:out value="${result.menuNm}"/></a></li>
			</c:forEach>
        </ul>
    </div>

</form>
