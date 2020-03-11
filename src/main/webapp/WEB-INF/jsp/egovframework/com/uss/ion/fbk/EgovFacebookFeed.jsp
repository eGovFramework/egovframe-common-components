<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<%

/**
 * @Class Name : EgovFacebookFeed.jsp
 * @Description : EgovFacebookFeed.jsp
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
<title>Facebook <spring:message code="comUssIonFbk.facebookFeed.title"/></title><!-- 담벼락 조회 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>Your Facebook Feed</h2>

	<!-- 등록폼 -->
	<table class="wTable">
	<colgroup>
			<col style="width:25%">
			<col style="width:auto">
		</colgroup>
	<tbody>
		<c:forEach items="${feed}" var="post">
			<tr>
				<th><c:out value="${post.message}" /> <c:out value="${post.name}" /></th>
				<td class="left" style="padding:20px 8px">
					<c:if test="${not empty post.picture}"><img src="<c:out value="${post.picture}"/>" alt="<c:out value="${post.name}" />" align="top"/></c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</div>
</body>
</html>