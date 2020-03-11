<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
 /**
  * @Class Name : EgovNotificationData.jsp
  * @Description : 정보알림이 표시XML 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.06.17   한성곤          최초 생성
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.17
  *  @version 1.0 
  *  @see
  *  
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
--%>
<root>
<c:forEach var="result" items="${list}" varStatus="status">
	<list>
		<time><c:out value='${result.ntfcTime}'/></time>
		<title><c:out value='${result.ntfcSj}'/></title>
		<contents><c:out value='${result.ntfcCn}'/></contents>
	</list>
</c:forEach>
</root>
