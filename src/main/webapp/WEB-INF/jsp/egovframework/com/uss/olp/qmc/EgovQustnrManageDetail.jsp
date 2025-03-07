<%--
  Class Name : EgovQustnrManageDetail.jsp
  Description : 설문관리 상세보기
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한		최초 생성
     2017.07.19    김예영		표준프레임워크 v3.7 개선
     2024.10.29    권태성		템플릿 유형 이미지에 width 속성을 추가, 수정 페이지 신규 경로로 변경

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlpQmc.title"/></c:set>
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
function fn_egov_init_QustnrManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrManage(){
	location.href = "<c:url value='/uss/olp/qmc/EgovQustnrManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_QustnrManage(){
	var vFrom = document.QustnrManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qmc/EgovQustnrManageModifyView.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_QustnrManage(){
	var vFrom = document.QustnrManageForm;

	if(confirm("<spring:message code='comUssOlpQmc.alert.total'/>"+"\n"+"<spring:message code='comUssOlpQmc.alert.delete'/>"+"\n\n"+"<spring:message code='common.delete.msg'/>")){  // 삭제시 설문관리, 설문항목, 설문문항, 설문응답자관리, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qmc/EgovQustnrManageDetail.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<form name="QustnrTmplatManageForm" id="QustnrTmplatManageForm" action="<c:url value='/uss/olp/qmc/EgovQustnrManageModifyView.do'/>" method="post">
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
		<!-- 설문제목 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrSj"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultList[0].qestnrSj}"/>
			</td>
		</tr>
		<!-- 설문목적 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrPurps"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:out value="${fn:replace(resultList[0].qestnrPurps , crlf , '<br/>')}" escapeXml="false" /> 
			</td>
		</tr>
		<!-- 설문작성안내 내용  -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrWritngGuidanceCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 설문대상 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrTrget"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
				<c:if test="${comCodeList.code eq resultList[0].qestnrTrget}">
				<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
				</c:if>
				</c:forEach>
			</td>
		</tr>
		<!-- 설문기간 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrDe"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<%-- ${resultList[0].qestnrBeginDe}~${resultList[0].qestnrEndDe} --%>
  				<c:out value="${resultList[0].qestnrBeginDe}~${resultList[0].qestnrEndDe}"/>  				
			</td>
		</tr>
		<!-- 템플릿 유형 -->
		<c:set var="title"><spring:message code="comUssOlpQmc.regist.qestnrTmplatTy"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultList[0].qestnrTmplatTy}" /> <img src="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageImg.do' />?qestnrTmplatId=${resultList[0].qestnrTmplatId}" align="middle" alt="<spring:message code='comUssOlpQmc.regist.qestnrTmplatTy'/><spring:message code='comUssOlpQmc.title.image'/>" title="<spring:message code='comUssOlpQmc.regist.qestnrTmplatTy'/><spring:message code='comUssOlpQmc.title.image'/>" width="100%"><!-- alt="템플릿유형 이미지" title="템플릿유형 이미지" -->
			</td>
		</tr>
		

	</tbody>
	</table>
	
	<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code='comUssOlpQmc.title.submit'/>" title="<spring:message code='comUssOlpQmc.title.submit'/>"></div><!-- value="전송" title="전송" -->
	
	</form>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 수정 버튼 -->
		<form name="formUpdt" action="<c:url value='/uss/olp/qmc/EgovQustnrManageModifyView.do'/>" method="post" onsubmit="fn_egov_modify_QustnrManage(); return false;" style="float:left;">
		<input type="submit" class="s_submit" value="<spring:message code='button.update' />" title="<spring:message code='title.update' /> <spring:message code='input.button' />" />
		<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
		</form>
		<!-- 삭제 버튼 -->
		<form name="formDelete" action="<c:url value='/uss/olp/qmc/EgovQustnrManageDetail.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code='button.delete' />" onclick="fn_egov_delete_QustnrManage(); return false;">
			<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
		<!-- 목록 버튼 -->
		<form name="formList" action="<c:url value='/uss/olp/qmc/EgovQustnrManageList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		  <input type="submit" class="s_submit" value="<spring:message code='button.list' />" onclick="fn_egov_list_QustnrManage(); return false;">
		</form>
		
	</div>
	
</div>

</body>
</html>