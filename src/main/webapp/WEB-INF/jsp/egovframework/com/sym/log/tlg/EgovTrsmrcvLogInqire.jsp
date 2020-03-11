<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymLogTlg.trsmrcvLog.title"/><spring:message code="title.detail" /></c:set>
<%
 /**
  * @Class Name : EgovTrsmrcvLogInqire.jsp
  * @Description : 송수신 로그 정보 상세조회 화면
  * @Modification Information
  * @
  * @  수정일         수정자          수정내용
  * @ -------        --------       ---------------------------
  * @ 2009.03.11      이삼섭          최초 생성
  * @ 2011.07.08      이기하          패키지 분리로 경로 수정(sym.log -> sym.log.tlg)
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.11
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
</head>
<body>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.requestId" /></th> <!-- 요청ID -->
			<td class="left">
			    <c:out value="${result.requstId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.occrrncDe" /></th> <!-- 발생일자 -->
			<td class="left">
			    <c:out value="${result.occrrncDe}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.trsmrcvType" /></th> <!-- 송수신구분 -->
			<td class="left">
			    <c:out value="${result.trsmrcvSeCodeNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.linkId" /></th> <!-- 연계ID -->
			<td class="left">
			    <c:out value="${result.cntcId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.providerId" /></th> <!-- 제공기관ID -->
			<td class="left">
			    <c:out value="${result.provdInsttId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.providerSystemId" /></th> <!-- 제공시스템ID -->
			<td class="left">
			    <c:out value="${result.provdSysId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.providerServiceId" /></th> <!-- 제공서비스ID -->
			<td class="left">
			    <c:out value="${result.provdSvcId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.requestOrgId" /></th> <!-- 요청기관ID -->
			<td class="left">
			    <c:out value="${result.requstInsttId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.requestSystemId" /></th> <!-- 요청시스템ID -->
			<td class="left">
			    <c:out value="${result.requstSysId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.requestNm" /></th> <!-- 요청자 -->
			<td class="left">
			    <c:out value="${result.rqsterNm}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<button class="btn_style3" onclick="window.close()" title="<spring:message code="button.close" />"><spring:message code="button.close" /></button></span>
	</div>
	<div style="clear:both;"></div>
</div>

</body>
</html>
