<%
 /**
  * @Class Name : EgovStplatDetailInqure.jsp
  * @Description : EgovStplatDetailInqure 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   장동한              표준프레임워크 v3.6 개선
  *
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
<c:set var="pageTitle"><spring:message code="comUssSamStp.title"/></c:set>
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
 function fn_egov_delete_stplatcn(useStplatId, frm){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		frm.submit();	
	}	
}	
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:16%;">
		<col style="width: ;">
		<col style="width:16%;">
		<col style="width:18%;">
	</colgroup>
	<tbody>
		<!-- 약관명 -->
		<tr>
			<th><spring:message code="comUssSamStp.list.useStplatNm" /></th>
			<td colspan="3" class="left"><c:out value="${result.useStplatNm}"/></td>
		</tr>
		<!-- 등록자, 등록일 -->
		<tr>
			<th><spring:message code="table.reger" /></th>
			<td class="left"><c:out value="${result.frstRegisterNm}"/></td>
			<th><spring:message code="table.regdate" /></th>
			<td class="left"><c:out value="${result.frstRegisterPnttm}"/></td>
		</tr>
		<!-- 약관내용 -->
		<tr>
			<th class="vtop"><spring:message code="comUssSamStp.list.useStplatCn" /></th>
			<td colspan="3" class="cnt">
				<c:out value="${fn:replace(result.useStplatCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 정보제공동의내용 -->
		<tr>
			<th class="vtop"><spring:message code="comUssSamStp.list.infoProvdAgreCn" /></th>
			<td colspan="3" class="cnt">
				<c:out value="${fn:replace(result.infoProvdAgreCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">

		<form name="formUpdt" action="<c:url value='/uss/sam/stp/StplatCnUpdtView.do'/>" method="post" style="float:left;">
			<input type="submit" class="s_submit" value="<spring:message code="button.update" />">
			<input name="useStplatId" type="hidden" value="<c:out value="${result.useStplatId}" />">
			<input name="cmd" type="hidden" value="">
		</form>
		
		<form name="formDelete" action="<c:url value='/uss/sam/stp/StplatCnDelete.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" onClick="fn_egov_delete_stplatcn('<c:out value="${result.useStplatId}"/>', this.form); return false;">
			<input name="useStplatId" type="hidden" value="<c:out value="${result.useStplatId}" />">
			<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
	
		<form name="formList" action="<c:url value='/uss/sam/stp/StplatListInqire.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.list" />">
		</form>
	
	</div><div style="clear:both;"></div>
	
</div>



</body>
</html>
