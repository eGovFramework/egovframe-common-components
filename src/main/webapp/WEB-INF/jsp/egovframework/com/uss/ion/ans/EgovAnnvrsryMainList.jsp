<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovAnnvrsryMainList.java
 * @Description : EgovAnnvrsryMainList.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용                최초 생성
 * @ 2018.09.19    최 두 영       다국어처리 
 * @ 2018.10.01    최 두 영       디자인 및 메뉴 구조 변경
 *
 *  @author 이      용
 *  @since 2010.06.29
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
<title><spring:message code="comUssIonAns.annvrsryMainList.title"/></title><!-- 기념일 목록 메인리스트 -->
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*설명 : 기념일 안내문 조회 */
function fncSelectAnnvrsryGdcc(annId) {
	document.gdccForm.annId.value = annId;
    document.gdccForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryGdcc.do'/>";
    document.gdccForm.submit();   
}
-->
</script>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="board">
	
		<h1><spring:message code="comUssIonAns.annvrsryMainList.listTitle" /></h1><!-- 다가오는 기념일 목록 -->
		<span>※ <spring:message code="comUssIonAns.annvrsryMainList.guide" /></span>		
		
		<form name="gdccForm" method="post" action="<c:url value='/uss/ion/ans/selectAnnvrsryGdcc.do'/>">
		<input type="hidden" name="annId"       value=""/>
		
		<table class="board_list">
			
			<caption><spring:message code="comUssIonAns.annvrsryMainList.guide" /></caption><!-- 데이터는 930.기념일 관리에서 기념일 일자가 D-day와 현재 시간에 포함되었을 경우 출력. 기념일명 클릭 시 상세보기 가능 -->
			
			<colgroup>
				<col style="width:5%" />
				<col style="width:20%" />
			</colgroup>
			<thead>
				<tr>
				   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryNm"/></th><!-- 기념일제목 -->
				   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryTemp4"/></th><!-- 기념일자 -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="annvrsryGdcc" items="${annvrsryGdccList}" varStatus="status">
					<tr>
						<td><span class="link"><input type="text" title="<spring:message code="comUssIonAns.common.annvrsryNm"/>" value="<c:out value="${annvrsryGdcc.annvrsryNm}"/>" onclick="fncSelectAnnvrsryGdcc('<c:out value="${annvrsryGdcc.annId}"/>'); return false;"></span></td>
						<td><c:out value="${annvrsryGdcc.annvrsryDe}"/><c:if test="${!empty annvrsryGdcc.cldrSe }">(<c:if test='${annvrsryGdcc.cldrSe == "1"}'><spring:message code="comUssIonAns.annvrsryGdcc.cldrSe1"/></c:if><!-- 양 --><c:if test='${annvrsryGdcc.cldrSe == "2"}'><spring:message code="comUssIonAns.annvrsryGdcc.cldrSe2"/></c:if>)</c:if>	</td><!-- 음 -->
					</tr>   	          				 			   
				</c:forEach>		
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>