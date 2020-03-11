<%--
  Class Name : EgovQustnrRespondManageDetail.jsp
  Description : 응답자정보 상세보기
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.19    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlpQrm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrRespondManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrRespondManage(){
	location.href = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_QustnrRespondManage(){
	var vFrom = document.QustnrRespondManageForm;
	vFrom.cmd.value = '';
	//document.getElementById("cmd").value='';
	vFrom.action = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageModify.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_QustnrRespondManage(){
	var vFrom = document.QustnrRespondManageForm;
	//var vFrom = document.getElementById("QustnrRespondManageForm");
	if(confirm("<spring:message code='common.delete.msg' />")){ //삭제 하시겠습니까?
		vFrom.cmd.value = 'del';
		//document.getElementsByName("cmd").value="<c:out value='del'/>";
		vFrom.action = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageDetail.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
		//document.getElementsByName("cmd").value='';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrRespondManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<form name="QustnrRespondManageForm" id="QustnrRespondManageForm" action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageModify.do'/>" method="post">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:25%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문관리정보 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 성별 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.sexdstnCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:forEach items="${comCode014}" var="comCodeList" varStatus="status">
				<c:if test="${comCodeList.code eq resultList[0].sexdstnCode}">
				<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
				</c:if>
				</c:forEach>
			</td>
		</tr>
		<!-- 직업  -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.occpTyCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
				<c:if test="${comCodeList.code eq resultList[0].occpTyCode}">
				<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
				</c:if>
				</c:forEach>
			</td>
		</tr>
		<!-- 응답자명 -->
		<c:set var="title"><spring:message code="comUssOlpQrm.regist.respondNm"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].respondNm , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
	</tbody>
	</table>
	
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code='comUssOlpQrm.title.submit'/>" title="<spring:message code='comUssOlpQrm.title.submit'/>"></div><!-- value="전송" title="전송" -->
	
	</form>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 수정 버튼 -->
		<form name="formUpdt" action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageModify.do'/>" method="post" onsubmit="fn_egov_modify_QustnrRespondManage(document.forms[0]); return false;" style="float:left;">
			<input type="submit" class="s_submit" value="<spring:message code='button.update' />" title="<spring:message code='title.update' /> <spring:message code='input.button' />" />
			<input name="qestnrRespondId" type="hidden" value="${resultList[0].qestnrRespondId}">
		</form>
		<!-- 삭제 버튼 -->
		<form name="formDelete" action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageDetail.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code='button.delete' />" onclick="fn_egov_delete_QustnrRespondManage(document.forms[0]); return false;">
			<input name="qestnrRespondId" type="hidden" value="${resultList[0].qestnrRespondId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>">
		</form>
		<!-- 목록 버튼 -->
		<form name="formList" action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		  <input type="submit" class="s_submit" value="<spring:message code='button.list' />" onclick="fn_egov_list_QustnrRespondManage(document.forms[0]); return false;">
		</form>		
	</div>
	
</div>

</body>
</html>