<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<%

/**
 * @Class Name : EgovBannerMainList.java
 * @Description : EgovBannerMainList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.02.01    lee.m.j     최초 생성
 * @ 2018.08.30    이정은               공통컴포넌트 3.8 개선 
 *
 *  @author lee.m.j
 *  @since 2009.03.21
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
<title><spring:message code="ussIonBnr.bannerList.bannerList"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
/* ********************************************************
 * 조회
 ******************************************************** */
function fncSelectBanner(bannerId) {
    document.items.bannerId.value = bannerId;
    document.items.action = "<c:url value='/uss/ion/bnr/getBanner.do'/>";
    document.items.submit();     
} 
</script>

</head>
<body>

<div class="board">
<!-- MYPAGE배너관리 -->
<form name="items" method="post" action="<c:url value='/uss/ion/bnr/getBanner.do'/>">
	<h1><spring:message code="ussIonBnr.bannerMainList.bannerMainList"/></h1>
	<span>※<spring:message code="ussIonBnr.bannerMainList.EgovBannerDc"/></span>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussIonBnr.bannerMainList.bannerNm"/></th>
			   <th scope="col"><spring:message code="input.cSelect"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="banner" items="${bannerList}" varStatus="status">
			<tr>
				<td class="left">
					<a href="" onclick="fncSelectBanner('<c:out value="${banner.bannerId}"/>');return false;"><c:out value="${banner.bannerNm}"/></a>
				</td>
				<td>
					<input class="btn01" type="button" value="수정" onclick="fncSelectBanner('<c:out value="${banner.bannerId}"/>');return false;" />
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<input type="hidden" name="bannerId" value="">
<input type="hidden" name="pageIndex" value="1">
<input type="hidden" name="searchCondition" value="1">
<input type="hidden" name="searchKeyword" value="">
</form>
</body>
</html>
