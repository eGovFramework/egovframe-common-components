<% 
/**
 * @Class Name : EgovFileSysMntrngRegist.jsp
 * @Description : 파일시스템모니터대상 등록
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.18   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.18
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
<c:set var="pageTitle"><spring:message code="comUtlSysFsm.fileSysMntrng.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.create" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="fileSysMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_FileSysMntrng(){
		document.fileSysMntrngVO.fileSysMg.value = "<c:out value='${fileSysMgValue}'/>";
		if(document.fileSysMntrngVO.fileSysMg.value == ""){
			document.fileSysMntrngVO.fileSysMg.value = 0;
		}

		if("${notApplicableFileSys}" == "true"){
			alert("<spring:message code="comUtlSysFsm.fileSysMntrngRegist.notApplicableFileSysTrue"/>");/* 파일시스템명이 올바르지 않습니다. */
		}
	}

	function fn_egov_insert_filesysmntrng() {
		if (!validateFileSysMntrngVO(document.fileSysMntrngVO)){
			return;
		}

		if(eval(document.fileSysMntrngVO.fileSysMg.value) < 1){
			alert("<spring:message code="comUtlSysFsm.fileSysMntrngRegist.fileSysMg"/>");/* 파일시스템확인 버튼을 클릭하여, 파일시스템의 크기를 확인해야 합니다. */
			return;
		}

		if(eval(document.fileSysMntrngVO.fileSysThrhld.value) < 1){
			alert("<spring:message code="comUtlSysFsm.fileSysMntrngRegist.fileSysThrhldG"/>");/* 파일시스템 임계치를 1G 이상 입력하여야 합니다. */
			return;
		}
		
		if(eval(document.fileSysMntrngVO.fileSysThrhld.value) > eval(document.fileSysMntrngVO.fileSysMg.value)){
			alert("<spring:message code="comUtlSysFsm.fileSysMntrngRegist.fileSysThrhldLimit"/>");/* 파일시스템 임계치는 파일시스템 크기보다 클 수 없습니다. */
			return;
		}
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.fileSysMntrngVO.action = "<c:url value='/utl/sys/fsm/insertFileSysMntrng.do'/>";
			document.fileSysMntrngVO.submit();					
		}
	}

	function fn_egov_select_filesysmg() {
		document.fileSysMntrngVO.action = "<c:url value='/utl/sys/fsm/selectFileSysMg.do'/>";
		document.fileSysMntrngVO.submit();					
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_filesysmntrng(){
		document.fileSysMntrngVO.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>";
		document.fileSysMntrngVO.submit();	
	}	

</script>
</head>
<body onLoad="fn_egov_init_FileSysMntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="fileSysMntrngVO" name="fileSysMntrngVO" method="post" action="${pageContext.request.contextPath}/utl/sys/fsm/insertFileSysMntrng.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="fileSysNm" maxlength="60" title="<spring:message code='comUtlSysFsm.fileSysMntrng.fileSysNm.label' />" cssStyle="width:250px"/>
				<a class="btn02" href="<c:url value='/utl/sys/fsm/selectFileSysMg.do'/>?searchWrd=<c:out value='${fileSysMntrngVO.searchWrd}'/>&amp;searchCnd=<c:out value='${fileSysMntrngVO.searchCnd}'/>&amp;pageIndex=<c:out value='${fileSysMntrngVO.pageIndex}'/>" onclick="fn_egov_select_filesysmg(); return false;"><spring:message code="comUtlSysFsm.fileSysMntrngRegist.selectFileSysMg"/></a><!-- 파일시스템확인 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysManageNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="fileSysManageNm" size="65" maxlength="255" title="<spring:message code='comUtlSysFsm.fileSysMntrng.fileSysManageNm.label' />"/>
      			<div><form:errors path="fileSysManageNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysMg.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="fileSysMg" maxlength="8" title="<spring:message code='comUtlSysFsm.fileSysMntrng.fileSysMg.label' />" readonly="true" cssStyle="width:50px"/>G
      			<div><form:errors path="fileSysMg" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysThrhld.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="fileSysThrhld" maxlength="8" title="<spring:message code='comUtlSysFsm.fileSysMntrng.fileSysThrhld.label' />" cssStyle="width:50px"/>G
      			<div><form:errors path="fileSysThrhld" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.mngrNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="mngrNm" size="5" maxlength="60" title="<spring:message code='comUtlSysFsm.fileSysMntrng.mngrNm.label' />" cssStyle="width:100px"/>
      			<div><form:errors path="mngrNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.mngrEmailAddr.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="mngrEmailAddr" size="25" maxlength="50" title="<spring:message code='comUtlSysFsm.fileSysMntrng.mngrEmailAddr.label' />"/>
  				<div><form:errors path="mngrEmailAddr" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_insert_filesysmntrng(); return false;" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>?searchWrd=<c:out value='${fileSysMntrngVO.searchWrd}'/>&amp;searchCnd=<c:out value='${fileSysMntrngVO.searchCnd}'/>&amp;pageIndex=<c:out value='${fileSysMntrngVO.pageIndex}'/>" onclick="fn_egov_list_filesysmntrng(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

    <input type="hidden" name="searchWrd" value="<c:out value='${fileSysMntrngVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${fileSysMntrngVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${fileSysMntrngVO.pageIndex}'/>" />
</form:form>
</body>
</html>