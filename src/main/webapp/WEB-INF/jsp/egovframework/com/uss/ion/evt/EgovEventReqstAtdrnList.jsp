<%
/**
 * @Class Name : EgovEventReqstAtdrnList.java
 * @Description : EgovEventReqstAtdrnList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2011.08.17    	정진오		선택입력사항을 표시하는 이미지 icon 경로가
 * 								다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함 *
 * @ 2018.09.21    최 두 영     다국어처리
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventReqstAtdrnList.title"/></title><!-- 행사참석자 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
 /*설명 : 행사 목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				 = document.all["listForm"];
	varFormsearchCondition.value = "1";
	varFormpageIndex.value = pageNo;
	varFormaction = "<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>";
	varFormsubmit();
 }
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonEvt.eventReqstAtdrnList.title"/></h1><!-- 행사참석자 목록 -->

	<form name="listForm" action="#LINK" method="post">
    <div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonEvt.common.submit"/>" title="<spring:message code="comUssIonEvt.common.submit"/>"></div><!-- 전송 -->
	<input type=hidden name="eventId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty eventManageVO.pageIndex }">1</c:if><c:if test="${!empty eventManageVO.pageIndex }"><c:out value='${eventManageVO.pageIndex}'/></c:if>">
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="" />
			<col style="width:30%" />
			<col style="width:20%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonEvt.eventReqstAtdrnList.eventTemp1"/></th><!-- 참석자명 -->
			   <th scope="col"><spring:message code="comUssIonEvt.eventReqstAtdrnList.eventTemp2"/></th><!-- 소속 -->
			   <th scope="col">E-Mail</th>
			   <th scope="col"><spring:message code="comUssIonEvt.eventReqstAtdrnList.eventTemp4"/></th><!-- 구분 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.eventTemp1}"/></td>
				<td><c:out value="${resultInfo.eventTemp2}"/></td>
				<td><c:out value="${resultInfo.eventTemp3}"/></td>
				<td>
					<c:if test="${resultInfo.eventTemp4 eq 'A'}"><spring:message code="comUssIonEvt.eventReqstAtdrnList.eventTemp4.A"/></c:if><!-- 신청 -->
		 			<c:if test="${resultInfo.eventTemp4 eq 'C'}"><spring:message code="comUssIonEvt.eventReqstAtdrnList.eventTemp4.C"/></c:if><!-- 승인 -->
		 			<c:if test="${resultInfo.eventTemp4 eq 'R'}"><spring:message code="comUssIonEvt.eventReqstAtdrnList.eventTemp4.R"/></c:if><!-- 반려 -->
				</td>
			</tr>    
			</c:forEach>
		
				
			<c:if test="${fn:length(eventManageList) == 0}">
				<tr> 
					<td colspan=5>
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>
	</form>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>
</body>
</html>

