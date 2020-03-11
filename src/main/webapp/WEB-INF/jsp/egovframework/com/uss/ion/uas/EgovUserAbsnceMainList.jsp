<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovUserAbsnceList.java
 * @Description : EgovUserAbsnceList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.07.01    lee.m.j     최초 생성
 *
 *  @author lee.m.j
 *  @since 2009.07.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>사용자부재 목록조회</title>
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/egovframework/com/" />
<link type="text/css" rel="stylesheet" href="${CssUrl}com.css">
<link type="text/css" rel="stylesheet" href="${CssUrl}button.css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectUserAbsnce(userId, regYn) {
	if(regYn == 'N') {
        location.replace("<c:url value='/uss/ion/uas/addViewUserAbsnce.do'/>?userId="+userId);
	}
    document.listForms.userId.value = userId;
    document.listForms.regYn.value = regYn;
    document.listForms.action = "<c:url value='/uss/ion/uas/getUserAbsnce.do'/>";
    document.listForms.submit();
}

-->
</script>
</head>

<body>
<form name="listForms" action="<c:url value='/uss/ion/uas/getUserAbsnce.do'/>" method="post">
<div id="all" style="margin:0 auto;width:200px;" class="divDotText" align="left">
<table width="200" cellpadding="8" class="table-line" summary="사용자부재에 대한 목록을 제공한다.">
    <tbody>
    <c:forEach var="userAbsnce" items="${userAbsnceList}" varStatus="status">
    <tr>
        <td class="lt_text6">
                <span class="link">
                    <input type="text" value="<c:out value="${userAbsnce.userNm}"/>" onclick="fncSelectUserAbsnce('<c:out value="${userAbsnce.userId}"/>','<c:out value="${userAbsnce.regYn}"/>');" title="사용자부재여부">
                <c:if test="${userAbsnce.userAbsnceAt eq 'Y'}" >(<c:out value="부재중"/>)</c:if>
                </span>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</DIV>
<input type="hidden" name="userId" value="" />
<input type="hidden" name="selAbsnceAt" value="A" />
<input type="hidden" name="regYn" value="" />
<input type="hidden" name="pageIndex" value="1"/>
<input type="hidden" name="searchCondition" value="1"/>
</form>
</body>
</html>
