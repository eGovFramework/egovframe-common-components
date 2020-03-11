<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<%

/**
 * @Class Name : EgovFacebookAlbums.jsp
 * @Description : EgovFacebookAlbums.jsp
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
<title>Facebook <spring:message code="comUssIonFbk.facebookAlbums.title"/></title><!-- 앨범목록 조회 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
</head>
<body>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>Your Facebook Photo Albums</h2>

	<!-- 등록폼 -->
	<table class="wTable">
	<colgroup>
			<col style="width:25%">
			<col style="width:auto">
		</colgroup>
	<tbody>
		<tr>
			<c:forEach items="${albums}" var="album">
			<td class="left" >
	       		<a class="btn_01" href="<c:url value="/uss/ion/fbk/album/${album.id}"/>"><c:out value="${album.name}"/></a>
	       	</td>
	        </c:forEach>
			</tr>
	</tbody>
	</table>
</div>
</body>
</html>