<%--
  Class Name : EgovQustnrQestnManageList.jsp
  Description : 설문문항 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2008.03.09   장동한            최초 생성
    2017.07.17   김예영            표준프레임워크 v3.7 개선
    2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.19

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQqm.title"/></c:set>
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
	document.listForm.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrQestnManage(){
	var vFrom = document.listForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageRegist.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_QustnrQestnManage(){
	location.href = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_QustnrQestnManage(qestnrQesitmId){
	var vFrom = document.listForm;
	vFrom.qestnrQesitmId.value = qestnrQesitmId;
	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 문항통계
 ******************************************************** */
function fn_egov_statistics_QustnrQestnManage(qestnrQesitmId,qestnTyCode){
	var vFrom = document.listForm;
	vFrom.qestnrQesitmId.value = qestnrQesitmId;
	vFrom.qestnTyCode.value = qestnTyCode;
	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageStatistics.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_QustnrQestnManage(qestnrId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code='common.delete.msg'/>")){ //삭제 하시겠습니까?
		vFrom.qestnrId.value = qestnrId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrQestnManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="board">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do'/>" method="post" onSubmit="fn_egov_search_QustnrQestnManage(); return false;">

	
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QESTN_CN"  <c:if test="${searchVO.searchCondition == 'QESTN_CN'}">selected="selected"</c:if> ><spring:message code="comUssOlpQqm.regist.qestnCn" /></option><!-- 질문내용 -->
					<option value="MXMM_CHOISE_CO"  <c:if test="${searchVO.searchCondition == 'MXMM_CHOISE_CO'}">selected="selected"</c:if> ><spring:message code="comUssOlpQqm.regist.mxmmChoiseCo" /></option><!-- 최대선택건수 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrQestnManage(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"> <a href="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageRegist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>


<%--
<c:if test="${qustnrQestnManageVO.searchMode == 'Y'}">
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qustnrQestnManageVO.qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
<input name="searchMode" id="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">
</c:if>
--%>
<input name="qestnrQesitmId" type="hidden" value="">
<input name="cmd" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 40%;">
		
		<col style="width: 15%;">
		<col style="width: 10%;">
		
		<col style="width: 20%;">
		<col style="width: 10%;">
		<col style="width: 20%;">	
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 순번 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQqm.regist.qestnCn" /></th><!-- 질문내용 -->

		<th><spring:message code="comUssOlpQqm.regist.qestnTyCode" /></th><!-- 질문유형 -->
		<th><spring:message code="comUssOlpQqm.regist.qestnIem" /></th><!-- 질문항목 -->
		<th><spring:message code="comUssOlpQqm.regist.statistics" /></th><!-- 통계 -->
		<th><spring:message code="comUssOlpQqm.regist.registerName" /></th><!-- 등록자 -->
		<th><spring:message code="comUssOlpQqm.regist.registerDate" /></th><!-- 등록일자  -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<!-- 질문내용  -->
		<td class="lt_text3L">
			<a href="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageDetail.do'/>?qestnrQesitmId=${resultInfo.qestnrQesitmId}" onClick="fn_egov_detail_QustnrQestnManage('<c:out value="${resultInfo.qestnrQesitmId}"/>');return false;"><c:out value='${resultInfo.qestnCn}'/></a>
    	</td>
		<!-- 질문유형 -->
		<td class="lt_text3">
			<c:if test="${resultInfo.qestnTyCode == '1'}"><spring:message code="comUssOlpQqm.regist.objectiveQuest" /></c:if><!-- 객관식 -->
    		<c:if test="${resultInfo.qestnTyCode == '2'}"><spring:message code="comUssOlpQqm.regist.subjectiveQuest" /></c:if><!-- 주관식 -->
		</td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 질문항목 -->
		<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do'/>">
		    <!-- <input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="${resultInfo.qestnrQesitmId}">  -->
		    <input name="searchMode" type="hidden" value="Y">
		    <span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQqm.value.view'/>"></span><!-- value="보기" -->
		    </form>
		</td>
		<!-- 통계  -->
	  	<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageStatistics.do'/>">
			<c:if test="${qustnrQestnManageVO.searchMode == 'Y'}">
			<input name="qestnrTmplatId" type="hidden" value="${qustnrQestnManageVO.qestnrTmplatId}">
			<input name="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
			<input name="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">
			</c:if>
			<input name="qestnrQesitmId" type="hidden" value="${resultInfo.qestnrQesitmId}">
			<!-- EgovQustnrQestnManageStatistics.do 로 파라미터 값을 두개 전달하기 위해 아래와 같이 input넣고 span도 수정 -->
			<input name="qestnTyCode" type="hidden" value="${resultInfo.qestnTyCode}">
			<%-- <span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQqm.value.view'/>" onclick="fn_egov_statistics_QustnrQestnManage('${resultInfo.qestnrQesitmId}'); return false;"></span><!-- value="보기" --> --%>
			<span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQqm.value.view'/>" onclick="fn_egov_statistics_QustnrQestnManage('${resultInfo.qestnrQesitmId}','${resultInfo.qestnTyCode}'); return false;"></span><!-- value="보기" -->
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
