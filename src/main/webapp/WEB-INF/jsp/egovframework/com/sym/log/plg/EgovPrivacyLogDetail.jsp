<%
/**
 * @Class Name : EgovPrivacyLogDetail.jsp
 * @Description : 개인정보조회 로그 정보 상세조회 화면
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------        --------    ---------------------------
 * @ 2014.09.11      표준프레임워크          최초 생성
 * @ 2017.09.21		이정은					표준프레임워크 3.7 개선
 *  @author Vincent Han
 *  @since 2014.09.11
 *  @version 3.5
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymLogPlg.privacyLog.title"/></c:set>
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
		<!-- 요청ID -->
		<tr>
			<th><spring:message code="comSymLogPlg.privacyLog.requesterId" /></th>
			<td class="left"><c:out value="${result.requesterId}"/></td>
		</tr>
		<!-- 조회일시 -->
		<tr>
			<th><spring:message code="comSymLogPlg.privacyLog.inquiryDatetime" /></th>
			<td class="left"><c:out value="${fn:substring(result.inquiryDatetime,0,19)}"/></td>
		</tr>
		<!-- 서비스명 -->
		<tr>
			<th><spring:message code="comSymLogPlg.privacyLog.serviceName" /></th>
			<td class="left"><c:out value="${result.serviceName}"/></td>
		</tr>
		<!-- 조회정보 -->
		<tr>
			<th><spring:message code="comSymLogPlg.privacyLog.inquiryInfo" /></th>
			<td class="left"><c:out value="${result.inquiryInfo}"/></td>
		</tr>
		<!-- 요청자 -->
		<tr>
			<th><spring:message code="comSymLogPlg.privacyLog.requesterName" /></th>
			<td class="left"><c:out value="${result.requesterName}"/></td>
		</tr>
		<!-- 요청자IP -->
		<tr>
			<th><spring:message code="comSymLogPlg.privacyLog.requesterIp" /></th>
			<td class="left"><c:out value="${result.requesterIp}"/></td>
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
