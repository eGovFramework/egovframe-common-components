<% 
/**
 * @Class Name : EgovFileSysMntrngLogDetail.jsp
 * @Description : 파일시스템모니터링로그 상세조회
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
<c:set var="pageTitle"><spring:message code="comUtlSysFsm.fileSysMntrngLog.title"/></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="fileSysMntrngLogVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_FileSysMntrng(){
	
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_filesysmntrnglog(){
		document.fileSysMntrngLogVO.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngLogList.do'/>";
		document.fileSysMntrngLogVO.submit();	
	}	

</script>
<title>${pageTitle} <spring:message code="title.detail" /></title>
</head>
<body onLoad="fn_egov_init_FileSysMntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>
	
	<form:form modelAttribute="fileSysMntrngLogVO" name="fileSysMntrngLogVO" method="post" action="${pageContext.request.contextPath}/utl/sys/fsm/selectFileSysMntrngLogList.do">

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.logId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value="${fileSysMntrngLog.logId}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.fileSysNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngLog.fileSysNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.fileSysManageNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngLog.fileSysManageNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.fileSysMg.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngLog.fileSysMg}'/>G
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.fileSysThrhld.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngLog.fileSysThrhldRt}'/>% (<c:out value='${fileSysMntrngLog.fileSysThrhld}'/>G)
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.fileSysUsgQty.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:out value='${fileSysMntrngLog.fileSysUsgRt}'/>% (<c:out value='${fileSysMntrngLog.fileSysUsgQty}'/>G)
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.mntrngSttus.label" /></th>
			<td class="left">
			    <c:out value="${fileSysMntrngLog.mntrngSttus}" escapeXml="false" />
			</td>
		</tr>
		<c:if test="${fileSysMntrngLog.mntrngSttus} ne '정상'">
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.logInfo.label" /></th>
			<td class="left">
			    <textarea id="logInfo" name="logInfo" rows="10" cols="75" title="<spring:message code='comUtlSysFsm.fileSysMntrngLog.logInfo.label' />" readonly><c:out value="${fileSysMntrngLog.logInfo}" escapeXml="false" /></textarea>
			</td>
		</tr>
		</c:if>
		<tr>
			<th><spring:message code="comUtlSysFsm.fileSysMntrngLog.creatDt.label" /></th>
			<td class="left">
			    <c:out value="${fileSysMntrngLog.creatDt}" escapeXml="false" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_filesysmntrnglog()" />
	</div>
	<div style="clear:both;"></div>
	
	<input type="hidden" name="fileSysId" value="<c:out value='${fileSysMntrngLog.fileSysId}'/>" />
	<input type="hidden" name="logId" value="<c:out value='${fileSysMntrngLog.logId}'/>" />
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${fileSysMntrngLogVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${fileSysMntrngLogVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${fileSysMntrngLogVO.pageIndex}'/>" />
    <input name="searchBgnDe" type="hidden" value="<c:out value='${fileSysMntrngLogVO.searchBgnDe}'/>">
   	<input name="searchEndDe" type="hidden" value="<c:out value='${fileSysMntrngLogVO.searchEndDe}'/>">
   	<input name="searchBgnHour" type="hidden" value="<c:out value='${fileSysMntrngLogVO.searchBgnHour}'/>">
   	<input name="searchEndHour" type="hidden" value="<c:out value='${fileSysMntrngLogVO.searchEndHour}'/>">
    <!-- 검색조건 유지 -->
</form:form>
</div>
</body>
</html>