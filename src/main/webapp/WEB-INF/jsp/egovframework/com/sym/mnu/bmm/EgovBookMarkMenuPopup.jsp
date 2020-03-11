<!DOCTYPE html>
<%--
 /**
  * @Class Name : EgovBookMarkMenuPopup.jsp
  * @Description : 바로가기메뉴 미리보기 화면
  * @Modification Information
  * @
  * @ 수정일               수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.09.25   윤성록           최초 생성
  *   2018.09.10   신용호           표준프레임워크 v3.8 개선
  *
  *  @author 공통컴포넌트팀 윤성록
  *  @since 2009.09.25
  *  @version 1.0
  *  @see
  *
  */

  /* Image Path 설정 */
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="/images/egovframework/com/sym/mnu/bmm/"/>
<c:set var="CssUrl" value="/css/egovframework/com/sym/mnu/bmm/"/>
<%
String imagePath_icon   = "/images/egovframework/com/sym/mnu/bmm/icon/";
String imagePath_button = "/images/egovframework/com/sym/mnu/bmm/button/";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymMnuBmm.BookMarkMenuPopup.title" /></title><!-- 바로가기메뉴미리보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">

<script type="text/javascript">

/* ********************************************************
 * 파일검색 화면 호출 함수
 ******************************************************** */

function fn_egov_move(progrmStrePath){

	var retVal = progrmStrePath;
	parent.fn_egov_returnValue(retVal);

}

</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="menuListForm" action ="" method="post">
<input type="hidden" name="req_RetrunPath" value="<c:url value='/sym/mnu/bmm/EgovMenuList'/>">

<div class="board">
	<h1><spring:message code="comSymMnuBmm.BookMarkMenuPopup.pageTop.title" /></h1><!-- 바로가기 메뉴관리 -->
	
	<h2><img src="<c:url value='/images/egovframework/com/cmm/utl/menu_base.gif' />" alt="base icon" /> <spring:message code="comSymMnuBmm.BookMarkMenuPopup.shortCut" /></h2><!-- 바로가기 -->
	
	<table class="e002">
		<colgroup>
			<col style="width:20px"/>
			<col style=""/>
		</colgroup>
		<c:forEach var="result" items="${list_menulist}" varStatus="status">
		<tr>
			<td><!-- 왼편여백 --></td>
			<td>
			    <img src="<c:url value='/images/egovframework/com/cmm/utl/menu_page.gif' />" alt="menu_page icon" />
			   	<a href="" onclick="fn_egov_move('<c:url value="${result.progrmStrePath}"/>'); return false;">
				<c:out value="${result.menuNm}" escapeXml="false"/></a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

</form>
</body>
</html>

