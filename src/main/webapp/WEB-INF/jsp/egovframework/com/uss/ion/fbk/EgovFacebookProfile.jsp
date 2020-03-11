<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<%

/**
 * @Class Name : EgovFacebookProfile.jsp
 * @Description : EgovFacebookProfile.jsp
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
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
<title>Facebook <spring:message code="comUssIonFbk.facebookProfile.title"/></title><!-- 프로필 조회 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
</head>
<body>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>Your Facebook Profile</h2>
	
	<p class="search_box" >
		<strong>Hello, <c:out value="${profile.firstName}"/>!</strong>
	</p>

	<!-- 등록폼 -->
	<table class="wTable">
	<colgroup>
			<col style="width:20%">
			<col style="width:auto">
		</colgroup>
		<tbody>
			<tr>
				<th>Facebook ID</th>
				<td class="left"><c:out value="${profile.id}"/>&nbsp;</td>
			</tr>
			<tr>
				<th>Name</th>
				<td class="left"><c:out value="${profile.name}"/>&nbsp;</td>
			</tr>
			<tr>
				<th>Email</th>
				<td class="left"><c:out value="${profile.email}"/>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn">
		<form id="disconnect" action="<c:url value="/uss/ion/fbk/facebookSignout.do"/>" method="post">
		<button class="btn_01" type="submit">Disconnect from Facebook</button>	
		<input type="hidden" name="_method" value="delete" />
		</form>
	</div>
</div>
</body>
</html>