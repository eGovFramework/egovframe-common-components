<%--
  Class Name : EgovLeaderSchdulDetail.jsp
  Description : 간부일정관리 상세보기
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.06.29    장철호          최초 생성
     2010.09.01    장철호          수정 (보안관련 분리)
     2017.12.07    이정은          시큐어코딩(ES) - 크로스사이트 스크립트[CWE-374, CWE-79]
     2018.09.14    최두영          다국어처리
 
    author   : 공통서비스 개발팀 장철호
    since    : 2010.06.29
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
String sLinkType = request.getParameter("linkType") == null ? "" : (String)request.getParameter("linkType");
%>
<c:set var="sLinkType" value="<%=EgovWebUtil.clearXSSMaximum(sLinkType)%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtLsm.leaderSchdulDetail.title" /></title><!-- 간부일정관리 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_LeaderSchdul(){

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_LeaderSchdul(){
	var vFrom = document.leaderSchdulVO;
	vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulList.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 수정화면으로가기
 ******************************************************** */
function fn_egov_modify_LeaderSchdul(){
	var vFrom = document.leaderSchdulVO;
	vFrom.action = "<c:url value='/cop/smt/lsm/mng/modifyLeaderSchdul.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_LeaderSchdul(){
	var vFrom = document.leaderSchdulVO;
	if(confirm("<spring:message code="common.delete.msg" />")){/* 삭제하시겠습니까? */
		vFrom.action = "<c:url value='/cop/smt/lsm/mng/deleteLeaderSchdul.do' />";
		vFrom.submit();
	}
}
</script>
</head>
<body onLoad="fn_egov_init_LeaderSchdul();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtLsm.leaderSchdulDetail.title" /></h2><!-- 간부일정관리 상세보기 -->

	<form name="leaderSchdulVO" id="leaderSchdulVO" action="<c:url value='/cop/smt/lsm/mng/modifyLeaderSchdul.do'/>" method="post">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulSe" /> <span class="pilsu">*</span></th><!-- 일정구분 -->
			<td class="left">
				<c:forEach items="${schdulSe}" var="schdulSeInfo" varStatus="status">
				<c:if test="${schdulSeInfo.code eq leaderSchdulVO.schdulSe}">	
				<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
				</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" /> <span class="pilsu">*</span></th><!-- 간부명 -->
			<td class="left">
			    <c:out value="${fn:replace(leaderSchdulVO.leaderName , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulNm" /> <span class="pilsu">*</span></th><!-- 일정명 -->
			<td class="left">
			    <c:out value="${fn:replace(leaderSchdulVO.schdulNm , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulCn" /> <span class="pilsu">*</span></th><!-- 일정내용 -->
			<td class="left">
			    <br/>
		    	<c:out value="${fn:replace(leaderSchdulVO.schdulCn , crlf , '<br/>')}" escapeXml="false" />
		    	<br/><br/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdulDetail.schdulPlace" /></th> <!-- 일정장소 -->
			<td class="left">
			    <c:out value="${fn:replace(leaderSchdulVO.schdulPlace , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdulDetail.reptitSeCode" /> <span class="pilsu">*</span></th><!-- 반복구문 -->
			<td class="left">
			    <c:forEach items="${reptitSeCode}" var="schdulSeInfo" varStatus="status">
				<c:if test="${schdulSeInfo.code eq leaderSchdulVO.reptitSeCode}">	
				<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
				</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdulDetail.datetime" /> <span class="pilsu">*</span></th><!-- 날짜/시간 -->
			<td class="left">
			    ${fn:substring(leaderSchdulVO.schdulBgnDe, 0, 4)}-${fn:substring(leaderSchdulVO.schdulBgnDe, 4, 6)}-${fn:substring(leaderSchdulVO.schdulBgnDe, 6, 8)}  
			    ~ 
			    <c:choose>
			    <c:when test="${leaderSchdulVO.reptitSeCode eq '1'}">	
			    	
			    </c:when>
			    <c:otherwise>
				${fn:substring(leaderSchdulVO.schdulEndDe, 0, 4)}-${fn:substring(leaderSchdulVO.schdulEndDe, 4, 6)}-${fn:substring(leaderSchdulVO.schdulEndDe, 6, 8)}  
				</c:otherwise>   
			    </c:choose>
			    ${fn:substring(leaderSchdulVO.schdulBgnDe, 8, 10)}<spring:message code="comCopSmtLsm.leaderSchdulDetail.hour" />  ${fn:substring(leaderSchdulVO.schdulBgnDe, 10, 12)}<spring:message code="comCopSmtLsm.leaderSchdulDetail.minute" />
			    ~
			    ${fn:substring(leaderSchdulVO.schdulEndDe, 8, 10)}<spring:message code="comCopSmtLsm.leaderSchdulDetail.hour" />  ${fn:substring(leaderSchdulVO.schdulEndDe, 10, 12)}<spring:message code="comCopSmtLsm.leaderSchdulDetail.minute" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulChargerName" /> <span class="pilsu">*</span></th><!-- 담당자 -->
			<td class="left">
			    <c:out value="${fn:replace(leaderSchdulVO.schdulChargerName , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_LeaderSchdul(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/lsm/mng/deleteLeaderSchdul.do'/>?schdulId=<c:out value='${leaderSchdulVO.schdulId}'/>" onclick="fn_egov_delete_LeaderSchdul(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulList.do'/>" onclick="fn_egov_list_LeaderSchdul(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>

	<input name="schdulId" type="hidden" value="${leaderSchdulVO.schdulId}">
	<input name="schdulDe" type="hidden" value="${leaderSchdulVO.schdulDe}">
	<input name="linkType" type="hidden" value="${sLinkType}">
	<input name="searchMode" type="hidden" value="${leaderSchdulVO.searchMode}">
	<input name="year" type="hidden" value="${leaderSchdulVO.year}">
	<input name="month" type="hidden" value="${leaderSchdulVO.month}">
	<input name="week" type="hidden" value="${leaderSchdulVO.week}">
	<input name="day" type="hidden" value="${leaderSchdulVO.day}">
</form>
</div>
</body>
</html>
