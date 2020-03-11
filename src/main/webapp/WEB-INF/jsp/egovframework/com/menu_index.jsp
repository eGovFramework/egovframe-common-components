<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 /**
  * @Class Name : EgovMainMenuIndex.jsp
  * @Description : MainMenuIndex Page
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.10    이용          최초 생성
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *  ?vStartP=<c:out value="${result.menuNo}"/> <c:out value="${result.chkURL}"/>
  */

%>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Menu Index frame</title>
</head>

<frameset rows="122,*,50" frameborder="0" >
	<frame src="<c:url value='/sym/mnu/mpm/EgovMainMenuHead.do' />" name="main_top" marginwidth="0" marginheight="0">
	<frameset cols="273,*" frameborder="0">
		<frame src="<c:url value='/sym/mnu/mpm/EgovMainMenuLeft.do' />?vStartP=<c:out value="${resultVO.menuNo}" />" scrolling="no" name="main_left" marginwidth="0" marginheight="0">
		<frame src="<c:url value='/sym/mnu/mpm/EgovMainMenuRight.do' />?vStartP=<c:out value="${resultVO.menuNo}" />" name="main_right" marginwidth="0" marginheight="0">
	</frameset>
	<frame src="<c:url value='/EgovPageLink.do' />?link=/egovframework/com/main_bottom" scrolling="no" name="main_bottom" marginwidth="0" marginheight="0">
</frameset>
</html>
