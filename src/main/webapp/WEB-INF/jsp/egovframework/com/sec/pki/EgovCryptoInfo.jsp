<%
 /**
  * @Class Name : EgovCryptoInfo.jsp
  * @Description : 암호화/복호화 화면
  * @Modification Information
  * @
  * @  수정일                     수정자                    수정내용
  * @ -------       --------    ---------------------------
  * @ 2018.12.03    신용호             최초 생성
  *
  *  @author 실행환경 개발팀 홍길동
  *  @since 2018.12.03
  *  @version 3.8
  *  @see
  *
  */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comSecPki.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 암호화/복호화 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/pki/EgovCryptoInfo.do" method="post">
<div class="board">
	<h1>${pageTitle}</h1><!-- 암호화/복호화 -->
	<!-- 요청영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="comSecPki.cryptoInfo.plainText" />(Plain Text) : </div></li><!-- 원본 문자열 -->
			<!-- 암복호화 키워드 및 요청버튼 -->
			<li style="border: 0px solid #d2d2d2;">
				<input class="s_input" name="plainText" type="text"  size="500" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:if test="${plainText eq '' or plainText eq null}"><spring:message code="comSecPki.cryptoInfo.sampleText" /></c:if><c:if test="${plainText ne '' and plainText ne null}"><c:out value="${plainText}"/></c:if>'  maxlength="500" style="width:400px;">
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
			</li>
		</ul>
	</div>
	
	<!-- 상세내용 -->
	<table class="popwTable">
	<caption>${pageTitle}</caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: 80%;">
	</colgroup>
	<tbody>
		<!-- 암호화 방식 -->
		<tr>
			<th><spring:message code="comSecPki.cryptoInfo.cryptoType" /></th>
			<td class="left">ARIA</td>
		</tr>
		<!-- 원본문자열 -->
		<tr>
			<th><spring:message code="comSecPki.cryptoInfo.plainText" /></th>
			<td class="left"><c:out value="${plainText}"/></td>
		</tr>
		<!-- 원본문자열 길이 -->
		<tr>
			<th><spring:message code="comSecPki.cryptoInfo.plainTextLen" /></th>
			<td class="left"><c:out value="${plainTextLen}"/></td>
		</tr>
		<!-- 암호화문자열(Base64) -->
		<tr>
			<th><spring:message code="comSecPki.cryptoInfo.cryptText" /></th>
			<td class="left"><c:out value="${cryptText}"/></td>
		</tr>
		<!-- 복호화문자열 -->
		<tr>
			<th><spring:message code="comSecPki.cryptoInfo.decryptText" /></th>
			<td class="left"><c:out value="${decryptText}"/></td>
		</tr>
		<!-- 복호화문자열 길이 -->
		<tr>
			<th><spring:message code="comSecPki.cryptoInfo.decryptTextLen" /></th>
			<td class="left"><c:out value="${decryptTextLen}"/></td>
		</tr>
		
	</tbody>
	</table>


</div>

</form:form>













</body>
</html>