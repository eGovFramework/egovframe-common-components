<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<%

/**
 * @Class Name : EgovFacebookAlbum.jsp
 * @Description : EgovFacebookAlbum.jsp
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
<title>Facebook <spring:message code="comUssIonFbk.facebookAlbum.title"/></title><!-- 앨범 조회 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>Your Facebook Photo Album: <c:out value="${album.name}"/></h2>

	<!-- 등록폼 -->
	<table class="wTable">
	<colgroup>
			<col style="width:25%">
			<col style="width:auto">
		</colgroup>
	<tbody>
		<c:forEach items="${photos}" var="photo">
		<tr>
			<td class="left"  style="padding:20px 8px">
	       		<img src="${photo.source}" alt="<c:out value="${album.name}"/>" align="middle"/>
	       	</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
</div>
</body>
</html>