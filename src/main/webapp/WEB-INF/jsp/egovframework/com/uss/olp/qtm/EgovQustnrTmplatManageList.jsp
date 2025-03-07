<%--
  Class Name : EgovQustnrTmplatManageList.jsp
  Description : 설문템플릿 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한		최초 생성
     2017.06.21    김예영		표준프레임워크 v3.7 개선
     2024.10.29    권태성		템플릿 유형 이미지에 height 속성을 추가

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQtm.title"/></c:set>
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
	document.listForm.action = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrTmplatManage(){
	location.href = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_QustnrTmplatManage(){
	location.href = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_QustnrTmplatManage(qestnrTmplatId){
	var vFrom = document.listForm;
	vFrom.qestnrTmplatId.value = qestnrTmplatId;
	vFrom.action = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_QustnrTmplatManage(mtgId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code='common.delete.msg'/>")){ //삭제 하시겠습니까?
		vFrom.mtgId.value = mtgId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrTmplatManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageList.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="board">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageList.do'/>" method="post" onSubmit="fn_egov_search_QustnrTmplatManage(); return false;">

	<!-- 설문템플릿관리 목록 -->
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QUSTNR_TMPLAT_DC"  <c:if test="${searchVO.searchCondition == 'QUSTNR_TMPLAT_DC'}">selected="selected"</c:if> ><spring:message code="comUssOlpQtm.searchCondition.QUSTNR_TMPLAT_DC" /></option><!-- 템플릿 설명 -->
					<option value="QUSTNR_TMPLAT_TY"  <c:if test="${searchVO.searchCondition == 'QUSTNR_TMPLAT_TY'}">selected="selected"</c:if> ><spring:message code="comUssOlpQtm.searchCondition.QUSTNR_TMPLAT_TY" /></option><!-- 템플릿 유형 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrTmplatManage(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"><a href="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageRegist.do' />"  title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

<input name="qestnrTmplatId" type="hidden" value="">
<input name="passwordConfirmAt" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: 20%;">
		
		<col style="width: 40%;">
		<col style="width: 30%;">
		
		<col style="width: 20%;">
		<col style="width: 20%;">		
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQtm.regist.qestnrTmplatTy" /></th><!-- 템플릿 유형 -->

		<th><spring:message code="comUssOlpQtm.regist.egovfile.information" /></th><!-- 템플릿유형 이미지 정보 -->
		<th><spring:message code="comUssOlpQtm.regist.qestnrTmplatCn" /></th><!-- 템플릿  설명 -->
		<th><spring:message code="comUssOlpQtm.regist.frstRegisterNm" /></th><!-- 작성자명 -->
		<th><spring:message code="comUssOlpQtm.regist.frstRegisterPnttm" /></th><!-- 등록일자  -->
	</tr>
	</thead>
	<tbody class="ov">	
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<!-- 템플릿 유형 -->
		<td class="lt_text3L"><div class="divDotText" style="width:100px;">${resultInfo.qestnrTmplatTy}</div></td>
		<!-- 템플릿유형 이미지 정보 -->
		<td class="lt_text3"><img src="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageImg.do'/>?qestnrTmplatId=${resultInfo.qestnrTmplatId}" alt="${resultInfo.qestnrTmplatTy}<spring:message code='comUssOlpQtm.regist.qestnrTmplatImage' />" title="${resultInfo.qestnrTmplatTy}<spring:message code='comUssOlpQtm.regist.qestnrTmplatImage' />" height="100"></td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 템플릿 설명 -->
		<td class="lt_text3L">
		<a href="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageDetail.do'/>?qestnrTmplatId=${resultInfo.qestnrTmplatId}" onClick="fn_egov_detail_QustnrTmplatManage('<c:out value="${resultInfo.qestnrTmplatId}"/>');return false;"><c:out value='${resultInfo.qestnrTmplatCn}'/></a>
		</td>
		<!-- 작성자명 -->
		<td class="lt_text3">${resultInfo.frstRegisterNm}</td>
		<!-- 등록일자 -->
		<td class="lt_text3">${fn:substring(resultInfo.frstRegistPnttm, 0, 10)}</td>
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
