<% 
/**
 * @Class Name : EgovNtwrkSvcMntrngRegist.jsp
 * @Description : 네트워크서비스모니터대상 등록
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.17   장철호          최초 생성
 * @ 2010.09.01   장철호          수정 (보안관련 분리)
 *   2018.11.02   신용호          표준프레임워크 v3.8 개선
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.create" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ntwrkSvcMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_NtwrkSvcMntrng(){
		if("${ntwrkSvcMntrngDuplicated}" == "true"){
			alert("<spring:message code="comUtlSysNsm.ntwrkSvcMntrng.validate.alert.duplicate" />"); //중복된 네트워크서비스의 IP와 Port가 있습니다.
		}
	}

	function fn_egov_insert_ntwrksvcmntrng() {
		if (!validateNtwrkSvcMntrngVO(document.ntwrkSvcMntrngVO)){
			return;
		}
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.ntwrkSvcMntrngVO.action = "<c:url value='/utl/sys/nsm/insertNtwrkSvcMntrng.do'/>";
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

<form:form modelAttribute="ntwrkSvcMntrngVO" name="ntwrkSvcMntrngVO" method="post" action="${pageContext.request.contextPath}/utl/sys/nsm/insertNtwrkSvcMntrng.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
	<caption>네트워크서비스모니터대상 등록</caption>
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.sysIp.label" /> <span class="pilsu">*</span></th><!-- 시스템IP -->
			<td class="left">
			    <form:input path="sysIp1" maxlength="3" title="시스템IP1" cssStyle="width:30px" /> . 
				<form:input path="sysIp2" maxlength="3" title="시스템IP2" cssStyle="width:30px" /> . 
				<form:input path="sysIp3" maxlength="3" title="시스템IP3" cssStyle="width:30px" /> .
				<form:input path="sysIp4" maxlength="3" title="시스템IP4" cssStyle="width:30px" />
				<div><form:errors path="sysIp1" cssClass="error"/></div>
				<div><form:errors path="sysIp2" cssClass="error"/></div>
				<div><form:errors path="sysIp3" cssClass="error"/></div>
				<div><form:errors path="sysIp4" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.sysPort.label" /> <span class="pilsu">*</span></th><!-- 시스템포트 -->
			<td class="left">
			    <form:input path="sysPort" maxlength="5" title="<spring:message code='comUtlSysNsm.ntwrkSvcMntrng.sysPort.label' />" cssStyle="width:40px" />
      			<div><form:errors path="sysPort" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.sysNm.label" /> <span class="pilsu">*</span></th><!-- 시스템명 -->
			<td class="left">
			    <form:input path="sysNm" size="65" maxlength="255" title="<spring:message code='comUtlSysNsm.ntwrkSvcMntrng.sysNm.label' />"/>
      			<div><form:errors path="sysNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.mngrNm.label" /> <span class="pilsu">*</span></th><!-- 관리자명 -->
			<td class="left">
			    <form:input path="mngrNm" size="5" maxlength="60" title="<spring:message code='comUtlSysNsm.ntwrkSvcMntrng.mngrNm.label' />" cssStyle="width:100px"/>
      			<div><form:errors path="mngrNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.mngrEmailAddr.label" /> <span class="pilsu">*</span></th><!-- 관리자이메일주소 -->
			<td class="left">
			    <form:input path="mngrEmailAddr" size="25" maxlength="50" title="<spring:message code='comUtlSysNsm.ntwrkSvcMntrng.mngrEmailAddr.label' />"/>
  				<div><form:errors path="mngrEmailAddr" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_insert_ntwrksvcmntrng(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>?searchWrd=<c:out value='${ntwrkSvcMntrngVO.searchWrd}'/>&amp;searchCnd=<c:out value='${ntwrkSvcMntrngVO.searchCnd}'/>&amp;pageIndex=<c:out value='${ntwrkSvcMntrngVO.pageIndex}'/>" onclick="fn_egov_list_ntwrksvcmntrng(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchWrd" value="<c:out value='${ntwrkSvcMntrngVO.searchWrd}'/>" />
<input type="hidden" name="searchCnd" value="<c:out value='${ntwrkSvcMntrngVO.searchCnd}'/>" />
<input type="hidden" name="pageIndex" value="<c:out value='${ntwrkSvcMntrngVO.pageIndex}'/>" />
<!-- 검색조건 유지 -->

</form:form>

</body>
</html>