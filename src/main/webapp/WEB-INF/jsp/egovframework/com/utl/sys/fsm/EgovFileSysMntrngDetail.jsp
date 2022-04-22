<% 
/**
 * @Class Name : EgovFileSysMntrngDetail.jsp
 * @Description : 파일시스템모니터링 상세조회
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
<c:set var="ImgUrl" value="/images/egovframework/com/cmm/"/>
<c:set var="pageTitle"><spring:message code="comUtlSysFsm.fileSysMntrng.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="fileSysMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_FileSysMntrng(){
	
	}

	function fn_egov_update_filesysmntrng() {
		document.fileSysMntrngVO.action = "<c:url value='/utl/sys/fsm/modifyFileSysMntrng.do'/>";
		document.fileSysMntrngVO.submit();	
	}

	function fn_egov_delete_filesysmntrng(){
		if(confirm("<spring:message code='common.delete.msg' />")){
			document.fileSysMntrngVO.action = "<c:url value='/utl/sys/fsm/deleteFileSysMntrng.do'/>";
			document.fileSysMntrngVO.submit();
		}
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_filesysmntrng(){
		document.fileSysMntrngVO.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>";
		document.fileSysMntrngVO.submit();	
	}	

</script>
<title>${pageTitle} <spring:message code="title.detail" /></title>
</head>
<body onLoad="fn_egov_init_FileSysMntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="fileSysMntrngVO" name="fileSysMntrngVO" method="post" action="${pageContext.request.contextPath}/utl/sys/fsm/modifyFileSysMntrng.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.fileSysNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysManageNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.fileSysManageNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysMg.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.fileSysMg}'/>G
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysThrhld.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.fileSysThrhldRt}'/>% (<c:out value='${fileSysMntrngVO.fileSysThrhld}'/>G)
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.fileSysUsgQty.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.fileSysUsgRt}'/>% (<c:out value='${fileSysMntrngVO.fileSysUsgQty}'/>G)
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.mngrNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.mngrNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.mngrEmailAddr.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngVO.mngrEmailAddr}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.mntrngSttus.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${fileSysMntrngVO.mntrngSttus}" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrng.creatDt.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${fileSysMntrngVO.creatDt}" />&nbsp;
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_update_filesysmntrng()" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/fsm/deleteFileSysMntrng.do'/>?fileSysId=<c:out value='${fileSysMntrngVO.fileSysId}'/>" onclick="fn_egov_delete_filesysmntrng(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>?searchWrd=<c:out value='${fileSysMntrngVO.searchWrd}'/>&amp;searchCnd=<c:out value='${fileSysMntrngVO.searchCnd}'/>&amp;pageIndex=<c:out value='${fileSysMntrngVO.pageIndex}'/>" onclick="fn_egov_list_filesysmntrng(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	
</div>

	<input type="hidden" name="fileSysId" value="<c:out value='${fileSysMntrngVO.fileSysId}'/>" />
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${fileSysMntrngVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${fileSysMntrngVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${fileSysMntrngVO.pageIndex}'/>" />
    <!-- 검색조건 유지 -->
</form:form>
</body>
</html>