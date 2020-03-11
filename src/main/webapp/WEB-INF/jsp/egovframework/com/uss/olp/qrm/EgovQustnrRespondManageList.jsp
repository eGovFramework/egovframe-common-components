<%--
  Class Name : EgovQustnrRespondManageList.jsp
  Description : 응답자정보 목록 페이지
  Modification Information

       수정일               수정자               수정내용
    ----------   --------     ---------------------------
    2008.03.09   장동한               최초 생성
    2017.07.19   김예영               표준프레임워크 v3.7 개선
    2019.12.11   신용호               KISA 보안약점 조치 (크로스사이트 스크립트)

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQrm.title"/></c:set>
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
	document.listForm.action = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do'/>";
   	document.listForm.submit();
   	
   	/*
   	document.getElementById("pageIndex").value=pageNo;
   	document.getElementById("listForm").action = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do'/>";
   	document.getElementById("listForm").submit();
   	*/
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrRespondManage(){
	location.href = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageRegist.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_QustnrRespondManage(qestnrRespondId){
	
	
	//var vFrom = document.subForm;
	//vFrom.qestnrRespondId.value = qestnrRespondId;
	//document.getElementsByName("qestnrRespondId").value = qestnrRespondId;
	document.getElementById("qestnrRespondId").value = qestnrRespondId;
	document.getElementById("pageIndex").value = pageIndex;
	//vFrom.action = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageDetail.do' />";
	document.getElementById("listForm").action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageDetail.do' />";
	//vFrom.submit();
	document.getElementById("listForm").submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrRespondManage(){
	//var vFrom = document.listForm;
	var vFrom = document.getElementById("listForm");
	vFrom.action = "<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="wTableFrm">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" id="listForm" action="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageList.do'/>" method="post">

	
	<h1>${pageTitle} <spring:message code="title.list" /></h1><br>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="RESPOND_NM"  <c:if test="${searchVO.searchCondition == 'RESPOND_NM'}">selected="selected"</c:if> ><spring:message code="comUssOlpQrm.searchCondition.RESPOND_NM" /></option><!-- 응답자명 -->
					<option value="FRST_REGISTER_ID"  <c:if test="${searchVO.searchCondition == 'FRST_REGISTER_ID'}">selected="selected"</c:if> ><spring:message code="comUssOlpQrm.regist.registerName" /></option><!-- 등록자 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>" maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrRespondManage(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"><a href="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageRegist.do' />"  title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>
	
<%-- <input name="qestnrRespondId" type="hidden" value="${resultInfo.qestnrRespondId}">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
<input name="searchMode" type="hidden" value=""> --%>	
	
</form>


	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 40%;">
		
		<col style="width: 15%;">
		<col style="width: 15%;">
		
		<col style="width: 20%;">		
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQrm.regist.qestnrCnList" /></th><!-- 설문지정보(제목) -->

		<th><spring:message code="comUssOlpQrm.regist.respondNm" /></th><!-- 응답자명 -->
		<th><spring:message code="comUssOlpQrm.regist.registerName" /></th><!-- 등록자 -->
		<th><spring:message code="comUssOlpQrm.regist.registerDate" /></th><!-- 등록일자  -->
	</tr>
	</thead>
	<tbody class="ov">
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>	
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<!-- 설문지정보(제목) -->
		<td class="lt_text3L">
			<a href="<c:url value='/uss/olp/qrm/EgovQustnrRespondManageDetail.do'/>?qestnrRespondId=${resultInfo.qestnrRespondId}" onClick="fn_egov_detail_QustnrRespondInfo('<c:out value="${resultInfo.qestnrRespondId}"/>');return false;"><c:out value='${resultInfo.qestnrSj}'/></a>
		</td>
		<!-- 응답자명 -->
		<td class="lt_text3">${resultInfo.respondNm}</td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 등록자 -->
		<td class="lt_text3">${resultInfo.frstRegisterNm}</td>
		
		<!-- 등록일자 -->
		<td class="lt_text3">${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
	  </tr>	  
	</c:forEach>	
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>




	
</div><!-- end div wTableFrm -->



</body>
</html>