<%--
  Class Name : EgovQustnrItemManageDetail.jsp
  Description : 설문항목 상세보기
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
<c:set var="pageTitle"><spring:message code="comUssOlpQim.title"/></c:set>
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
function fn_egov_init_QustnrItemManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrItemManage(){
	location.href = "<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_QustnrItemManage(){
	var vFrom = document.QustnrItemManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_QustnrItemManage(){
	var vFrom = document.QustnrItemManageForm;
	if(confirm("<spring:message code='comUssOlpQim.alert.total'/>"+"\n"+"<spring:message code='comUssOlpQim.alert.delete'/>"+"\n\n"+"<spring:message code='common.delete.msg'/>")){ //삭제시   설문항목, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageDetail.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrItemManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<form name="QustnrItemManageForm" id="QustnrItemManageForm" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do'/>" method="post">
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
		<!-- 설문정보 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnrCn , crlf , '<br/>')}" escapeXml="false" />
  				<input name="qestnrId" type="hidden" value="<c:out value='${resultList[0].qestnrId}' />">
  				<input name="qestnrTmplatId" type="hidden" value="<c:out value='${resultList[0].qestnrTmplatId}' />">
			</td>
		</tr>
		<!-- 설문문항정보 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.qestnrQesitmCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:out value="${fn:replace(resultList[0].qestnrQesitmCn , crlf , '<br/>')}" escapeXml="false" />
  				<input name="qestnrQesitmId" type="hidden" value="<c:out value='${resultList[0].qestnrQesitmId}' />"> 
			</td>
		</tr>
		<!-- 항목순번  -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.iemSn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultList[0].iemSn}" />
			</td>
		</tr>
		<!-- 항목내용 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.iemCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].iemCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 기타답변여부 -->
		<c:set var="title"><spring:message code="comUssOlpQim.regist.etcAnswerAt"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value=" ${resultList[0].etcAnswerAt}" />
			</td>
		</tr>
	
	</tbody>
	</table>
	
	<input name="qustnrIemId" type="hidden" value="<c:out value='${resultList[0].qustnrIemId}' />">
	<input name="cmd" type="hidden" value="">
	
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code='comUssOlpQim.value.submit'/>" title="<spring:message code='comUssOlpQim.value.submit'/>"></div><!-- value="전송" title="전송" -->
	
	</form>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 수정 버튼 -->
		<form name="formUpdt" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do'/>" method="post" onsubmit="fn_egov_modify_QustnrItemManage(); return false;" style="float:left;">
			<input type="submit" class="s_submit" value="<spring:message code='button.update' />" title="<spring:message code='title.update' /> <spring:message code='input.button' />" />
			<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qustnrIemId}">
		</form>
		<!-- 삭제 버튼 -->
		<form name="formDelete" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageDetail.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code='button.delete' />" onclick="fn_egov_delete_QustnrItemManage(); return false;">
			<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qustnrIemId}">
			<input name="cmd" type="hidden" value="del">
		</form>
		<!-- 목록 버튼 -->
		<form name="formList" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		  <input type="submit" class="s_submit" value="<spring:message code='button.list' />" onclick="fn_egov_list_QustnrItemManage(); return false;">
		</form>
		
	</div>
	
</div>

</body>
</html>