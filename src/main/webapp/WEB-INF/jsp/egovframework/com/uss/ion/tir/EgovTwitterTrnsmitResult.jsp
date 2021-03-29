<%--
  Class Name : EgovTwitterTrnsmit.jsp
  Description : 트위터 송신(등록) 페이지
  Modification Information
 
       수정일               수정자              수정내용
    ----------   --------    ---------------------------
    2010.07.13   장동한              최초 생성
    2018.10.29   이정은              공통컴포넌트 3.8 개선
    2020.10.29   신용호              KISA 보안약점 조치 (크로스사이트 스크립트)
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.13
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.Status"%>
<%@ page import="twitter4j.User"%>
<%@page import="egovframework.com.cmm.EgovWebUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%

Status status = (Status)request.getAttribute("status");

User user = (User)status.getUser();


%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterTrnsmitResult.twitterTrnsmitResult"/></title><!-- 트위터(Twitter) 송신(등록)결과확인 -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css'/>">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css'/>">
</head>
<body>
<DIV id="content" style="width:712px">
<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonTir.twitterTrnsmitResult.twitterTrnsmitResult"/></h2><!-- 트위터 송신 결과 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.id"/></th><!-- 등록ID -->
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(""+user.getId()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.screenName"/></th><!-- 계정ID -->
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(user.getScreenName()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.name"/></th><!-- 별명 -->
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(user.getName()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.url"/></th><!-- URL -->
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(user.getURL()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.text"/></th><!-- 트위터 내용 -->
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(status.getText()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.profileImage"/></th><!-- Profile Image -->
			<td class="left">
			    <img src="<%=EgovWebUtil.clearXSSMinimum(user.getProfileImageURL()) %>" title="profileImageUrl" alt="profileImageUrl">
			</td>
		</tr>
		<%
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		%>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.createdAt"/></th><!-- 등록일 -->
			<td class="left">
			    <%=format.format(user.getCreatedAt()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.isFavorited"/></th><!-- favorite -->
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(""+status.isFavorited()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.isRetweet"/></th>
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(""+status.isRetweet()) %>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmitResult.isTruncated"/></th>
			<td class="left">
			    <%=EgovWebUtil.clearXSSMinimum(""+status.isTruncated()) %>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/ion/tir/registTwitterTrnsmit.do'/>" onclick=""><spring:message code="ussIonTir.twitterTrnsmitResult.confirm"/></a></span><!-- 확인 -->
	</div>
	<div style="clear:both;"></div>
</div>

</DIV>

</body>
</html>