<% 
/**
 * @Class Name : EgovNtwrkSvcMntrngDetail.jsp
 * @Description : 네트워크서비스모니터대상 상세보기
 * @Modification Information
 * @
 * @ 수정일               수정자          수정내용
 * @ ----------   --------  ---------------------------
 * @ 2010.08.17   장철호          최초 생성
 * @ 2018.11.02   신용호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.17
 *  @version 1.0 
 *  @see
 *  
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.detail" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ntwrkSvcMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_NtwrkSvcMntrng(){
	
	}

	function fn_egov_update_ntwrksvcmntrng() {
		document.ntwrkSvcMntrngVO.action = "<c:url value='/utl/sys/nsm/modifyNtwrkSvcMntrng.do'/>";
		document.ntwrkSvcMntrngVO.submit();	
	}

	function fn_egov_delete_ntwrksvcmntrng(){
		if(confirm("<spring:message code='common.delete.msg' />")){
			document.ntwrkSvcMntrngVO.action = "<c:url value='/utl/sys/nsm/deleteNtwrkSvcMntrng.do'/>";
			document.ntwrkSvcMntrngVO.submit();
		}
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_ntwrksvcmntrng(){
		document.ntwrkSvcMntrngVO.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>";
		document.ntwrkSvcMntrngVO.submit();	
	}	

</script>
</head>
<body onLoad="fn_egov_init_NtwrkSvcMntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="ntwrkSvcMntrngVO" name="ntwrkSvcMntrngVO" method="post" action="${pageContext.request.contextPath}/utl/sys/nsm/modifyNtwrkSvcMntrng.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
	<caption>네트워크서비스모니터대상 상세보기</caption>
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.sysIp.label" /> <span class="pilsu">*</span></th><!-- 시스템IP -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.sysIp1}" escapeXml="false" />.<c:out value="${ntwrkSvcMntrngVO.sysIp2}" escapeXml="false" />.<c:out value="${ntwrkSvcMntrngVO.sysIp3}" escapeXml="false" />.<c:out value="${ntwrkSvcMntrngVO.sysIp4}" escapeXml="false" />
				<div><form:errors path="sysIp1" cssClass="error"/></div>
				<div><form:errors path="sysIp2" cssClass="error"/></div>
				<div><form:errors path="sysIp3" cssClass="error"/></div>
				<div><form:errors path="sysIp4" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.sysPort.label" /> <span class="pilsu">*</span></th><!-- 시스템포트 -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.sysPort}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.sysNm.label" /> <span class="pilsu">*</span></th><!-- 시스템명 -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.sysNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.mngrNm.label" /> <span class="pilsu">*</span></th><!-- 관리자명 -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.mngrNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.mngrEmailAddr.label" /> <span class="pilsu">*</span></th><!-- 관리자이메일주소 -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.mngrEmailAddr}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.mntrngSttus.label" /> <span class="pilsu">*</span></th><!-- 모니터링상태 -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.mntrngSttus}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.creatDt.label" /> <span class="pilsu">*</span></th><!-- 마지막 생성일시  -->
			<td class="left">
			    <c:out value="${ntwrkSvcMntrngVO.creatDt}" escapeXml="false" />&nbsp;
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_update_ntwrksvcmntrng(); return false;" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/utl/sys/nsm/deleteNtwrkSvcMntrng.do'/>?sysIp=<c:out value='${ntwrkSvcMntrngVO.sysIp}'/>&amp;sysPort=<c:out value='${ntwrkSvcMntrngVO.sysPort}'/>" onclick="fn_egov_delete_ntwrksvcmntrng(); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>?searchWrd=<c:out value='${ntwrkSvcMntrngVO.searchWrd}'/>&amp;searchCnd=<c:out value='${ntwrkSvcMntrngVO.searchCnd}'/>&amp;pageIndex=<c:out value='${ntwrkSvcMntrngVO.pageIndex}'/>" onclick="fn_egov_list_ntwrksvcmntrng(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" id="sysIp" name="sysIp" value="<c:out value='${ntwrkSvcMntrngVO.sysIp}'/>"/>
<input type="hidden" id="sysPort" name="sysPort" value="<c:out value='${ntwrkSvcMntrngVO.sysPort}'/>"/>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchWrd" value="<c:out value='${ntwrkSvcMntrngVO.searchWrd}'/>" />
<input type="hidden" name="searchCnd" value="<c:out value='${ntwrkSvcMntrngVO.searchCnd}'/>" />
<input type="hidden" name="pageIndex" value="<c:out value='${ntwrkSvcMntrngVO.pageIndex}'/>" />
<!-- 검색조건 유지 -->
</form:form>

</body>
</html>