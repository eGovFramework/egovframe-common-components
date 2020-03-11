<%--
  Class Name : EgovQustnrRespondInfoDetail.jsp
  Description : 설문조사 상세보기
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.24    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlpQri.title"/></c:set>
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
function fn_egov_init_QustnrRespondInfo(){
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrRespondInfo(){
	location.href = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do' />";
}
/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_modify_QustnrRespondInfo(qestnrQesrspnsId){
	var vFrom = document.QustnrRespondInfoForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoModify.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_QustnrRespondInfo(qestnrQesrspnsId){
	var vFrom = document.QustnrRespondInfoForm;
	if(confirm("<spring:message code='common.delete.msg'/>")){ // 삭제 하시겠습니까?
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoDetail.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<form name="QustnrRespondInfoForm" id="QustnrRespondInfoForm" action="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoModify.do'/>" method="post">
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
		<c:set var="title"><spring:message code="comUssOlpQri.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
				<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
				<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
			</td>
		</tr>
		<!-- 설문문항정보 -->
		<c:set var="title"><spring:message code="comUssOlpQri.regist.qestnrQesitmCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:out value="${fn:replace(resultList[0].qestnCn , crlf , '<br/>')}" escapeXml="false" />
				<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}"> 
			</td>
		</tr>
		<!-- 설문유형 -->
		<c:set var="title"><spring:message code="comUssOlpQri.regist.qestnTyCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:if test="${resultList[0].qestnTyCode == '1'}"><spring:message code="comUssOlpQri.regist.objectiveQuest"/></c:if> <!-- 객관식 -->
    			<c:if test="${resultList[0].qestnTyCode == '2'}"><spring:message code="comUssOlpQri.regist.subjectiveQuest"/></c:if><!-- 주관식 -->
			</td>
		</tr>
		<!-- 설문항목정보 -->
		<c:set var="title"><spring:message code="comUssOlpQri.regist.qustnrIemCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].iemCn , crlf , '<br/>')}" escapeXml="false" />
				<input name="qustnrIemId" type="hidden" value="${resultList[0].qustnrIemId}">
			</td>
		</tr>
		<!-- 응답자답변내용 -->
		<c:set var="title"><spring:message code="comUssOlpQri.regist.respondAnswerCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 기타답변내용 -->
		<c:set var="title"><spring:message code="comUssOlpQri.regist.etcAnswerCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].etcAnswerCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 응답자명 -->
		<c:set var="title"><spring:message code="comUssOlpQri.regist.respondNm"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].respondNm , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>

	</tbody>
	</table>
	
	<input name="qestnrQesrspnsId" type="hidden" value="<c:out value='${resultList[0].qestnrQesrspnsId}' />">
	<input name="cmd" type="hidden" value="">
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code='comUssOlpQri.title.submit' />" title="<spring:message code='comUssOlpQri.title.submit' />"></div><!--  value="전송" title="전송" -->	
	
	</form>
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 수정 버튼 -->
		<form name="formUpdt" action="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoModify.do'/>" method="post" onsubmit="fn_egov_modify_QustnrRespondInfo('${resultList[0].qestnrQesrspnsId}'); return false;" style="float:left;">
		<input type="submit" class="s_submit" value="<spring:message code='button.update' />" title="<spring:message code='title.update' /> <spring:message code='input.button' />" />
		<input name="qestnrQesrspnsId" type="hidden" value="${resultList[0].qestnrQesrspnsId}">
		</form>
		<!-- 삭제 버튼 -->
		<form name="formDelete" action="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoDetail.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code='button.delete' />" onclick="fn_egov_delete_QustnrRespondInfo('${resultList[0].qestnrQesrspnsId}'); return false;">
			<input name="qestnrQesrspnsId" type="hidden" value="${resultList[0].qestnrQesrspnsId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
		<!-- 목록 버튼 -->
		<form name="formList" action="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		  <input type="submit" class="s_submit" value="<spring:message code='button.list' />" onclick="fn_egov_list_QustnrRespondInfo(); return false;">
		</form>
		
	</div>
	
</div>

</body>
</html>