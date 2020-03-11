<%
 /**
  * @Class Name : EgovCcmCmmnCodeDetail.jsp
  * @Description : 공통코드 상세조회 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.08.09   이정은              표준프레임워크 v3.7 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comSymCcmCca.cmmnCodeVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_code(codeId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.CcmCodeForm.codeId.value = codeId;	
		document.CcmCodeForm.action = "<c:url value='/sym/ccm/cca/RemoveCcmCmmnCode.do'/>";
		document.CcmCodeForm.submit();
	}	
}	
</script>
</head>
<body>

<form name="CcmCodeForm" action="<c:url value='/sym/ccm/cca/UpdateCcmCmmnCodeView.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 분류코드명 -->
		<tr>
			<th><spring:message code="comSymCcmCca.cmmnCodeVO.clCodeNm" /></th>
			<td class="left"><c:out value="${result.clCodeNm}"/></td>
		</tr>
		<!-- 코드ID -->
		<tr>
			<th><spring:message code="comSymCcmCca.cmmnCodeVO.codeId" /></th>
			<td class="left"><c:out value="${result.codeId}"/></td>
		</tr>
		<!-- 코드ID명 -->
		<tr>
			<th><spring:message code="comSymCcmCca.cmmnCodeVO.codeIdNm" /></th>
			<td class="left"><c:out value="${result.codeIdNm}"/></td>
		</tr>
		<!-- 코드ID설명 -->
		<tr>
			<th class="vtop"><spring:message code="comSymCcmCca.cmmnCodeVO.codeIdDc" /></th>
			<td class="cnt">
				<c:out value="${fn:replace(result.codeIdDc , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 사용여부 -->
		<tr>
			<th><spring:message code="comSymCcmCca.cmmnCodeVO.useAt" /></th>
			<td class="left"><c:out value="${result.useAt}"/></td>
		</tr>
		
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/sym/ccm/cca/RemoveCcmCmmnCode.do?codeId=${result.codeId}' />" onClick="fn_egov_delete_code('<c:out value="${result.codeId}"/>'); return false;" title="<spring:message code="title.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/sym/ccm/cca/SelectCcmCmmnCodeList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<input name="codeId" type="hidden" value="<c:out value="${result.codeId}" />">
</form>

</body>
</html>
