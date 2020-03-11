<%--
  Class Name : EgovQustnrRespondInfoManageList.jsp
  Description : 설문조사(설문등록) 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2011.09.19    서준식           등록일자 Char 변경으로 fmt기능 사용안함
     2017.07.18    김예영          표준프레임워크 v3.7 개선
     
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQnn.title"/></c:set>
<!DOCTYPE html>
${resultScript}
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageList.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageList.do'/>";
	vFrom.submit();

}

/* ********************************************************
 * 선택한 설문지정보 -> 설문문항 바로가기
 ******************************************************** */

 function fn_egov_regist_QustnrRespondInfoManage(qestnrId, qestnrTmplatId){

 	 document.getElementById("qestnrId").value = qestnrId;
 	 document.getElementById("qestnrTmplatId").value = qestnrTmplatId;
 	 //document.listForm.action = "<c:url value='${pageContext.request.contextPath}/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do'/>";
 	 document.listForm.action = "<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do'/>";
 	 document.listForm.submit();
 }

 /* ********************************************************
  * 선택한 전체 통계보기
  ******************************************************** */
function fn_egov_statistics_QustnrRespondInfoManage(qestnrId, qestnrTmplatId){

 	 document.getElementById("qestnrId").value = qestnrId;
 	 document.getElementById("qestnrTmplatId").value = qestnrTmplatId;
 	 document.listForm.action = "<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics.do'/>";
 	 document.listForm.submit();
}
</script>
</head>
<body>

<div class="board">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" id="listForm" action="<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageList.do'/>" method="post" >

	
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QUSTNR_SJ"  <c:if test="${searchVO.searchCondition == 'QUSTNR_SJ'}">selected="selected"</c:if> ><spring:message code="comUssOlpQnn.searchCondition.QUSTNR_SJ" /></option><!-- 설문제목 -->
					<option value="FRST_REGISTER_ID"  <c:if test="${searchVO.searchCondition == 'FRST_REGISTER_ID'}">selected="selected"</c:if> ><spring:message code="comUssOlpQnn.searchCondition.FRST_REGISTER_ID" /></option><!-- 등록자 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onClick="fn_egov_search_QustnrManage(); return false;" />
				<!-- 등록버튼 -->
				<%-- <span class="btn_b"> <a href="<c:url value='/uss/olp/qim/EgovQustnrItemManageRegist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> --%> 
			</li>
		</ul>
	</div>



<!-- </form> -->

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 40%;">
		
		<col style="width: 40%;">
		<col style="width: 20%;">
		
		<col style="width: 20%;">	
		<col style="width: 20%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQnn.regist.qestnrSj" /></th><!-- 설문제목 -->

		<th><spring:message code="comUssOlpQnn.regist.qestnrDate" /></th><!-- 설문기간 -->
		<th><spring:message code="comUssOlpQnn.regist.statistics" /></th><!-- 통계 -->
		<th><spring:message code="comUssOlpQnn.regist.registerName" /></th><!-- 등록자 -->
		<th><spring:message code="comUssOlpQnn.regist.registerDate" /></th><!-- 등록일자  -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터가 없을때 화면에 메세지를 출력해준다 --%>
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
		<!-- 설문제목  -->
		<td class="lt_text3L">
			<a href="<c:url value='/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do'/>?qestnrId=${resultInfo.qestnrId}&qestnrTmplatId=${resultInfo.qestnrTmplatId}" onClick="fn_egov_regist_QustnrRespondInfoManage('<c:out value="${resultInfo.qestnrId}"/>','<c:out value="${resultInfo.qestnrTmplatId}"/>');return false;"><c:out value='${resultInfo.qestnrSj}'/></a> 
		</td>
		<!-- 설문기간 -->
		<td class="lt_text3">${resultInfo.qestnrBeginDe} ~ ${resultInfo.qestnrEndDe}</td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 통계  -->
	  	<td class="lt_text3">
	  	<span class="btn_b">
	  	<a href="#LINK" class="btn_submit" onclick="fn_egov_statistics_QustnrRespondInfoManage('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}')"><spring:message code="comUssOlpQnn.regist.example" /></a>
	  	</span>
	  	</td>
		<!-- 등록자  -->
	  	<td class="lt_text3">${resultInfo.frstRegisterNm}</td>
	  	<!-- 등록일자  -->
	  	<td class="lt_text3">${resultInfo.frstRegisterPnttm}</td>
		<!--<td class="lt_text3" nowrap>${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>-->
      </tr>	  
	</c:forEach>	
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>

<input name="qestnrId" id="qestnrId" type="hidden" value="">
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">	

</form>
</div><!-- end div board -->



</body>
</html>
