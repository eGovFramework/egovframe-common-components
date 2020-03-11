<%--
  Class Name : EgovQustnrManageList.jsp
  Description : 설문관리 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2008.03.09   장동한            최초 생성
    2017.07.14   김예영            표준프레임워크 v3.7 개선
    2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQmc.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/olp/qmc/EgovQustnrManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrManage(){
	location.href = "<c:url value='/uss/olp/qmc/EgovQustnrManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_QustnrManage(){
	location.href = "<c:url value='/uss/olp/qmc/EgovQustnrManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_QustnrManage(qestnrId){
	var vFrom = document.listForm;
	vFrom.qestnrId.value = qestnrId;
	vFrom.action = "<c:url value='/uss/olp/qmc/EgovQustnrManageDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_QustnrManage(qestnrId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code='common.delete.msg'/> ")){ //삭제 하시겠습니까?
		vFrom.qestnrId.value = qestnrId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qmc/EgovQustnrManageList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/qmc/EgovQustnrManageList.do' />";
	vFrom.submit();

}

/* ********************************************************
 * 선택한 설문지정보 -> 설문문항 바로가기
 ******************************************************** */
function fn_egov_list_QustnrQestnManag(qestnrId, qestnrTmplatId, Type){
	var vFrom = document.listForm;
	var sAction = "";
	vFrom.qestnrId.value = qestnrId;
	vFrom.qestnrTmplatId.value = qestnrTmplatId;
	vFrom.searchCondition.options[0].selected = true;
	vFrom.searchKeyword.value = '';
	vFrom.searchMode.value = 'Y';

	//QRM QQM QRI
	if(Type == 'QRM'){ //설문응답자정보
		sAction  = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do' />";
	}else if(Type == 'QQM'){ //설문문항
		sAction  = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
	}else if(Type == 'QRI'){ //응답자결과
		sAction  = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do' />";
	}

	vFrom.action = sAction;
	vFrom.submit();

}
 /* ********************************************************
  * 통계
  ******************************************************** */
function fn_egov_statistics_QustnrQestnManag(qestnrId, qestnrTmplatId){
	var vFrom = document.listForm;
	vFrom.qestnrId.value = qestnrId;
	vFrom.qestnrTmplatId.value = qestnrTmplatId;
	vFrom.searchCondition.options[0].selected = true;
	vFrom.searchKeyword.value = '';
	vFrom.action = "<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics.do' />";
	vFrom.submit();
}
</script>
</head>
<body>

<div class="board">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/uss/olp/qmc/EgovQustnrManageList.do'/>" method="post" onSubmit="fn_egov_search_QustnrManage(); return false;">

	
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QUSTNR_SJ"  <c:if test="${searchVO.searchCondition == 'QUSTNR_SJ'}">selected="selected"</c:if> ><spring:message code="comUssOlpQmc.searchCondition.QUSTNR_SJ" /></option><!-- 설문제목 -->
					<option value="FRST_REGISTER_ID"  <c:if test="${searchVO.searchCondition == 'FRST_REGISTER_ID'}">selected="selected"</c:if> ><spring:message code="comUssOlpQmc.searchCondition.FRST_REGISTER_ID" /></option><!-- 등록자 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrManage(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"> <a href="<c:url value='/uss/olp/qmc/EgovQustnrManageRegist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

<input name="qestnrId" type="hidden" value="">
<input name="qestnrTmplatId" type="hidden" value="">
<input name="searchMode" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 35%;">
		<col style="width: 35%;">
		
		<col style="width: 11%;">
		<col style="width: 11%;">
		<col style="width: 11%;">	
		<col style="width: 11%;">
		
		<col style="width: 15%;">
		<col style="width: 20%;">	
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQmc.regist.qestnrSj" /></th><!-- 설문제목 -->

		<th><spring:message code="comUssOlpQmc.regist.qestnrDe" /></th><!-- 설문기간 -->
		<th><spring:message code="comUssOlpQmc.regist.respondentInfo" /></th><!-- 설문응답자정보 -->
		<th><spring:message code="comUssOlpQmc.regist.qustnrqestn" /></th><!-- 설문문항 -->
		<th><spring:message code="comUssOlpQmc.regist.qustnrInvestigation" /></th><!-- 설문조사  -->
		<th><spring:message code="comUssOlpQmc.regist.statistics" /></th><!-- 통계 -->
		<th><spring:message code="comUssOlpQmc.regist.registerName" /></th><!-- 등록자 -->
		<th><spring:message code="comUssOlpQmc.regist.registerDate" /></th><!-- 등록일자  -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<!-- 설문제목  -->
		<td class="lt_text3L">
			<a href="<c:url value='/uss/olp/qmc/EgovQustnrManageDetail.do'/>?qestnrId=${resultInfo.qestnrId}" onClick="fn_egov_detail_QustnrManage('<c:out value="${resultInfo.qestnrId}"/>');return false;"><c:out value='${resultInfo.qestnrSj}'/></a>
    	</td>
		<!-- 설문기간 -->
		<td class="lt_text3">${resultInfo.qestnrBeginDe}~${resultInfo.qestnrEndDe}</td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 설문응답자 정보 -->
		<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do'/>">
				<input name="qestnrId" type="hidden" value="${resultInfo.qestnrId}">
				<input name="qestnrTmplatId" type="hidden" value="${resultInfo.qestnrTmplatId}">
				<input name="searchMode" type="hidden" value="Y">
				<span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQmc.value.view'/>" onclick="fn_egov_list_QustnrQestnManag('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QRM'); return false;"></span><!-- value="보기" -->
			</form>
		</td>
		<!-- 설문문항 -->
		<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do'/>">
				<input name="qestnrId" type="hidden" value="${resultInfo.qestnrId}">
				<input name="qestnrTmplatId" type="hidden" value="${resultInfo.qestnrTmplatId}">
				<input name="searchMode" type="hidden" value="Y">
				<span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQmc.value.view'/>" onclick="fn_egov_list_QustnrQestnManag('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QQM'); return false;"></span><!-- value="보기" -->
			</form>
		</td>
		<!-- 설문조사 -->
		<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do'/>">
				<input name="qestnrId" type="hidden" value="${resultInfo.qestnrId}">
				<input name="qestnrTmplatId" type="hidden" value="${resultInfo.qestnrTmplatId}">
				<input name="searchMode" type="hidden" value="Y">
				<span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQmc.value.view'/>" onclick="fn_egov_list_QustnrQestnManag('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QRI'); return false;"></span><!-- value="보기" -->
			</form>
		</td>
	  	<!-- 통계  -->
	  	<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics.do'/>">
				<input name="qestnrId" type="hidden" value="${resultInfo.qestnrId}">
				<input name="qestnrTmplatId" type="hidden" value="${resultInfo.qestnrTmplatId}">
				<input name="searchMode" type="hidden" value="">
				<span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQmc.value.view'/>" onclick="fn_egov_statistics_QustnrQestnManag('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}'); return false;"></span><!-- value="보기" -->
			</form>
		</td>
	  	<!-- 등록자  -->
	  	<td class="lt_text3">${resultInfo.frstRegisterNm}</td>
	  	<!-- 등록일자  -->
	  	<td class="lt_text3">${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
	  </tr>	  
	</c:forEach>	
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
	
</div><!-- end div board -->



</body>
</html>
