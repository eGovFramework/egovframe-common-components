<%
/**
 * @Class Name : EgovLoginbLogDetail.jsp
 * @Description : 접속로그 정보 상세조회 화면
 * @Modification Information
 * @
 * @  수정일      수정자          수정내용
 * @  -------    --------       ---------------------------
 * @ 2009.03.11   이삼섭          최초 생성
 * @ 2011.07.08   이기하          패키지 분리, 경로수정(sym.log -> sym.log.clg)
 * @ 2017.09.21   이정은          표준프레임워크 v3.7 개선
 * 
 *	@author 공통서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymLogClg.loginLog.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css' />">
<script type="text/javascript">
</script>
</head>
<body>

<div class="popup">
	<!-- 타이틀 -->
	<h1>${pageTitle} <spring:message code="title.detail" /></h1>

	<!-- 상세조회 -->
	<table class="popwTable">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 로그ID -->
		<tr>
			<th><spring:message code="comSymLogClg.loginLog.logId" /></th>
			<td class="left"><c:out value="${result.logId}"/></td>
		</tr>
		<!-- 발생일자 -->
		<tr>
			<th><spring:message code="comSymLogClg.loginLog.occrrncDe" /></th>
			<td class="left"><c:out value="${fn:substring(result.creatDt,0,10)}"/></td>
		</tr>
		<!-- 로그유형 -->
		<tr>
			<th><spring:message code="comSymLogClg.loginLog.loginMthd" /></th>
			<td class="left"><c:out value="${result.loginMthd}"/></td>
		</tr>
		<!-- 사용자명 -->
		<tr>
			<th><spring:message code="comSymLogClg.loginLog.loginNm" /></th>
			<td class="left"><c:out value="${result.loginNm}"/></td>
		</tr>
		<!-- 접속IP -->
		<tr>
			<th><spring:message code="comSymLogClg.loginLog.loginIp" /></th>
			<td class="left"><c:out value="${result.loginIp}"/></td>
		</tr>
		
	</tbody>
	</table>
	<!-- 닫기 버튼 -->
	<div class="btn">
		<button class="btn_style3" onclick="window.close()" title="<spring:message code="button.close" />"><spring:message code="button.close" /></button><!-- 닫기 -->
		<div style="clear:both;"></div>
	</div>

</div>
</body>
</html>
