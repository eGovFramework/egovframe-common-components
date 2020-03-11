<%
/**
 * @Class Name : EgovIndvdlYrycManageList.java
 * @Description : EgovIndvdlYrycManageList jsp
 * @Modification Information
 * @
 * @  수정일        수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2014.11.14    표준프레임워크         최초 생성
 * @ 2018.09.13    최두영                     퍼블리싱점검&다국어처리
 *
 *  @author 표준프레임워크
 *  @since 2014.11.14
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2014 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonYrc.indvdlYrycManageList.title" /></title><!-- 개인연차관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 등록 화면 호출 함수 
 ******************************************************** */
function fncIndvdlYrycRegist(){
	location.href = "<c:url value='/uss/ion/yrc/EgovIndvdlYrycRegist.do'/>";
}

-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comUssIonYrc.indvdlYrycManageList.title" /></h1><!-- 개인연차관리 목록 -->
	
	<span>${messageTemp}</span> <!-- /uss/ion/vct/web/EgovVcatnManageController.java 휴가 등록 시 개인연차가 없을 경우 메세지를 받음. -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>			
				<span class="btn_b">
					<a href="<c:url value='/uss/ion/yrc/EgovIndvdlYrycRegist.do'/>" onclick="fncIndvdlYrycRegist(); return false;" title="">
					<c:if test="${fn:length(resultList) == 0}">
                        <spring:message code="button.create" />
                    </c:if>
                    <c:if test="${fn:length(resultList) != 0}">
                        <spring:message code="button.update" />
                    </c:if>
					</a>
				</span>
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:30%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonYrc.indvdlYrycManageList.occrrncYear" /></th><!-- 발생연도 -->
			   <th scope="col"><spring:message code="comUssIonYrc.indvdlYrycManageList.occrncYrycCo" /></th><!-- 발생연차 -->
			   <th scope="col"><spring:message code="comUssIonYrc.indvdlYrycManageList.useYrycCo" /></th><!-- 사용연차 -->
			   <th scope="col"><spring:message code="comUssIonYrc.indvdlYrycManageList.remndrYrycCo" /></th><!-- 잔여연차 -->
			   <th scope="col"><spring:message code="comUssIonYrc.indvdlYrycManageList.mberNm" /> </th><!-- 사용자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
        	<tr>
        		<td><c:out value="${resultInfo.occrrncYear}"/></td>
        		<td><c:out value="${resultInfo.occrncYrycCo}"/></td>
        		<td><c:out value="${resultInfo.useYrycCo}"/></td>
        		<td><c:out value="${resultInfo.remndrYrycCo}"/></td>
        		<td><c:out value="${resultInfo.mberNm}"/></td>
        	</tr>    
        	</c:forEach>
        	<c:if test="${fn:length(resultList) == 0}">
        		<tr> 
        			<td colspan="5">
        				<spring:message code="info.nodata.msg" />
        			</td>
        		</tr>   	          				 			   
        	</c:if>
		</tbody>
	</table>
</div>
</body>
</html>
