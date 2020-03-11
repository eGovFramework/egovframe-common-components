<%
 /**
  * @Class Name : EgovOnlinePollPartcptnStatistics.jsp
  * @Description : 온라인POLL관리 통계 페이지
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09	장동한		최초 생성
  *   2016.08.22 	장동한        표준프레임워크 v3.6 개선
  *  
  *  @author 공통서비스 개발팀 장동한
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpOpp.title"/></c:set>
<c:set var="ImgUrl" value="/images/egovframework/com/uss/olp/opp/"/>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="comUssOlpOpp.statistics.title" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uss/olp/opp/online_poll.css' />">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_OnlinePollPartcptnStatistics() {

}
</script>
</head>
<body onLoad="fn_egov_init_OnlinePollPartcptnStatistics()">
<div class="wTableFrm">

<!-- 상단타이틀 -->
<form name="QustnrQestnManageForm" action="<c:url value=''/>" method="post">

 <%-- 온라인POLL관리 목록 페이지 --%>
<c:if test="${linkType eq '1'}">
<h2><spring:message code="comUssOlpOpm.title" /> <spring:message code="comUssOlpOpp.statistics.title" /></h2>
</c:if>
<%-- 온라인POLL참여 목록 페이지 --%>
<c:if test="${linkType eq '2'}">
<h2><spring:message code="comUssOlpOpp.title" /> <spring:message code="comUssOlpOpp.statistics.title" /></h2>
</c:if>

<!-- 등록폼 -->
<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
<%-- 온라인POLL관리 목록 페이지 --%>
<c:if test="${linkType eq '1'}">
<caption><spring:message code="comUssOlpOpm.title" /> <spring:message code="comUssOlpOpp.statistics.title" /></caption>
</c:if>
<%-- 온라인POLL참여 목록 페이지 --%>
<c:if test="${linkType eq '2'}">
<caption><spring:message code="comUssOlpOpp.title" /> <spring:message code="comUssOlpOpp.statistics.title" /></caption>
</c:if>

<colgroup>
	<col style="width: 16%;"><col style="width: ;">
</colgroup>
<tbody>
	<!-- 입력 -->
	<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
	<!-- 선택 -->
	<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
	<!-- POLL명 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollNm"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
		<c:out value="${PollManageList[0].pollNm}" />
		</td>
	</tr>
	<!-- POLL시작일자 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollBeginDe"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
			<c:out value="${fn:substring(PollManageList[0].pollBeginDe, 0, 10)}"/>
		</td>
	</tr>
	<!-- POLL종료일자 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollEndDe"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
			<c:out value="${fn:substring(PollManageList[0].pollEndDe, 0, 10)}"/>
		</td>
	</tr>
	<!-- POLL종류 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollType"/></c:set>
	<tr>
		<th>${title}<span class="pilsu">*</span></th>
		<td class="nopd">
		<c:forEach items="${pollKindCodeList}" var="resultInfo" varStatus="pollKindStatus">
			<c:if test="${resultInfo.code eq PollManageList[0].pollKindCode}">
				<c:out value="${resultInfo.codeNm}" />
			</c:if>
		</c:forEach>
		</td>
	</tr>
	    		
	<tr>
		<td class="nopd" colspan="2">

	<div class="boxType2 mt20">
	<c:set var="chartCheck" value="0" />
	<dl class="poll_chart">
	<c:forEach items="${PollItemList}" var="resultInfo" varStatus="status2">
			<table>
			<th><c:out value="${resultInfo.pollIemNm}" escapeXml="false" /></td>
			<td>
				<ul>
					<c:forEach items="${statisticsList}" var="statisticsInfo" varStatus="status3">
					<c:if test="${resultInfo.pollIemId eq statisticsInfo.pollIemId}">
						<li>
							<span class="g_bar">
								<span class="g_org" style="width:${statisticsInfo.pollIemPercent}px;"></span>
							</span>
						</li>
					<li>(${statisticsInfo.pollIemIdCnt})건</li>
  					<c:set var="chartCheck" value="${chartCheck+1}" />
  					</c:if>
  					</c:forEach>
					<c:if test="${chartCheck eq '0'}">
					<li>
						<span class="g_bar">
							<span class="g_org" style="width:1px;"></span>
						</span>
					</li>
					<li>(0)건</li>
	   				</c:if>

				</ul>
			</td>

	   		<c:set var="chartCheck" value="${0}" />
	   		<table>
		</c:forEach>
		</dl>
	</div>

   		
		</td>
	</tr>
	
</tbody>
</table>



<!-- 하단 버튼 -->
<div class="btn">

<%-- 온라인POLL관리 목록 페이지 --%>
<c:if test="${linkType eq '1'}">
<span class="btn_s"><a href="<c:url value='/uss/olp/opm/listOnlinePollManage.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
</c:if>

<%-- 온라인POLL참여 목록 페이지 --%>
<c:if test="${linkType eq '2'}">

<span class="btn_s"><a href="<c:url value='/uss/olp/opp/listOnlinePollPartcptn.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>

</c:if>

</div><div style="clear:both;"></div>

</form>
</DIV>

</body>
</html>
