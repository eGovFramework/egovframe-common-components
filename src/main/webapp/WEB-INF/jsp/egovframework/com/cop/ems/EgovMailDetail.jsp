<%
 /**
  * @Class Name : EgovMailDetail.jsp
  * @Description : 발송메일 상세 조회 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.11    박지욱          최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.11
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comCopSymEms.regist.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="comCopSymEms.regist.title" /> <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		document.detailForm.action = "<c:url value='/cop/ems/deleteSndngMail.do'/>";
		document.detailForm.target = "";
		document.detailForm.submit();
	}
}
/* ********************************************************
 * 뒤로 처리 함수
 ******************************************************** */
function fnBack(){
	document.detailForm.action = "<c:url value='/cop/ems/backSndngMailDetail.do'/>";
	document.detailForm.target = "";
	document.detailForm.submit();
}

/* ********************************************************
 * XML형태의 발송메일 보기
 ******************************************************** */
function fnSelectXml(){
	document.detailForm.action = "<c:url value='/cop/ems/selectSndngMailXml.do'/>";
	document.detailForm.target = "_blank";
	document.detailForm.submit();
}
</script>
</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 발송메일 상세조회 -->

<form name="detailForm" action ="<c:url value='/cop/ems/deleteSndngMail.action'/>" method="post">
<div class="wTableFrm">
<input name="mssageId" type="hidden" value="${resultInfo.mssageId}"/>
<input name="atchFileIdList" type="hidden" value="${resultInfo.atchFileId}"/>
<h2><spring:message code="comCopSymEms.regist.title" /> <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="comCopSymEms.inqire.summary" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:16%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
	<!-- 보낸는사람 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.sender" /></th>
		<td class="left">${resultInfo.dsptchPerson}</td>
	</tr>
	<!-- 받는사람 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.receiver" /></th>
		<td class="left">${resultInfo.recptnPerson}</td>
	</tr>
	<!-- 제목 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.title" /></th>
		<td class="left">${resultInfo.sj}</td>
	</tr>
	<!-- 발신내용 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.sendCn" /></th>
		<td class="left"><c:out value="${fn:replace(resultInfo.emailCn , crlf , '<br/>')}" escapeXml="false" /></td>
	</tr>
	<!-- 발신결과 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.sendResult" /></th>
		<td class="left">${resultInfo.sndngResultCode}</td>
	</tr>
	<!-- XML메일보기 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.xml" /></th>
		<td class="left"><a href="#noscript" onclick="fnSelectXml(); return false;">${resultInfo.mssageId}.xml</a></td>
	</tr>
	<!-- 첨부파일 -->
	<tr>
		<th><spring:message code="comCopSymEms.inqire.atch" /></th>
		<td class="left">
		<c:import url="/cmm/fms/selectFileInfs.do" >
			<c:param name="param_atchFileId" value="${resultInfo.atchFileId}" />
		</c:import>&nbsp;
		</td>
	</tr>
	
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		
		<span class="btn_s"><a href="<c:url value='/cop/ems/deleteSndngMail.do'/>" onClick="fnDelete(); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/cop/ems/selectSndngMailList.do' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	  
</div>
</form>	  
</body>
</html>

