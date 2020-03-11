<%@ page contentType="text/html; charset=utf-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovFacebookSignin.jsp
 * @Description : EgovFacebookSignin.jsp
 * @Modification Information
 * @
 * @  수정일             수정자                        수정내용
 * @ ---------     -----------------    -------------------------
 * @ 2014.11.10    표준프레임워크센터        최초생성
 * @ 2018.10.29    최 두 영                        3.8개선 
 *
 *  @author 표준프레임워크센터
 *  @since 2014.11.10
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2014 by MOGAHA  All right reserved.
 */

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Facebook <spring:message code="comUssIonFbk.facebookSignin.title"/></title><!-- Facebook Login-->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonFbk.facebookSignin.titleInfo"/></h2><!-- Facebook 연동 -->
	<span><spring:message code="comUssIonFbk.facebookSignin.description"/></span><br><br><!-- 기능 테스트를 위해서 사전에 https://developers.facebook.com에서 API등록을 해야 한다. -->
    <div id="border" style="width:730px">
		<form action="<c:url value="/signin/facebook" />" method="POST" target="_top">
		    <button class="btn_01" type="submit">Sign in with Facebook</button>
		    <input type="hidden" name="scope" value="user_posts,email,user_photos,user_status,email" />
		</form>
    </div>
</div>
</body>
</html>
