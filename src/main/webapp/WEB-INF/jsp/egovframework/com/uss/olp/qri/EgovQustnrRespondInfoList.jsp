<%--
  Class Name : EgovQustnrRespondInfoList.jsp
  Description : 설문조사 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2008.03.09   장동한            최초 생성
    2017.07.24   김예영            표준프레임워크 v3.7 개선
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
<c:set var="pageTitle"><spring:message code="comUssOlpQri.title"/></c:set>
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
	document.listForm.action = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrRespondInfo(){
	location.href = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_QustnrRespondInfo(){
	location.href = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_QustnrRespondInfo(qestnrQesrspnsId){ 
	var vFrom = document.listForm;
	vFrom.qestnrQesrspnsId.value = qestnrQesrspnsId;
	//document.getElementById("qestnrQesrspnsId").value = qestnrQesrspnsId;
	vFrom.action = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_QustnrRespondInfo(qestnrId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code='title.list' />")){ // 삭제 하시겠습니까?
		vFrom.qestnrId.value = qestnrId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrRespondInfo(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do' />";
	vFrom.submit();

}

/* ********************************************************
 * 선택한 설문지정보 -> 설문문항 바로가기
 ******************************************************** */
function fn_egov_list_QustnrQestnManag(qestnrId, qestnrTmplatId){
	var vFrom = document.listForm;
	vFrom.qestnrId.value = qestnrId;
	vFrom.qestnrTmplatId.value = qestnrTmplatId;
	vFrom.searchCondition.options[0].selected = true;
	vFrom.searchKeyword.value = '';
	vFrom.searchMode.value = 'Y';
	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="board">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoList.do'/>" method="post" onSubmit="fn_egov_search_QustnrRespondInfo(); return false;">

	
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value="ETC_ANSWER_CN"  <c:if test="${searchVO.searchCondition == 'ETC_ANSWER_CN'}">selected="selected"</c:if> ><spring:message code="comUssOlpQri.searchCondition.ETC_ANSWER_CN" /></option><!-- 기타답변내용 -->
					<option value="RESPOND_ANSWER_CN"  <c:if test="${searchVO.searchCondition == 'RESPOND_ANSWER_CN'}">selected="selected"</c:if> ><spring:message code="comUssOlpQri.searchCondition.RESPOND_ANSWER_CN" /></option><!-- 응답자답변내용 -->
					<option value="RESPOND_NM"  <c:if test="${searchVO.searchCondition == 'RESPOND_NM'}">selected="selected"</c:if> ><spring:message code="comUssOlpQri.searchCondition.RESPOND_NM" /></option><!-- 응답자명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrRespondInfo(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"><a href="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoRegist.do' />"  title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 10%;">
		
		<col style="width: 40%;">
		<col style="width: 15%;">
		
		<col style="width: 10%;">
		<col style="width: 15%;">		
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQri.regist.qestnType" /></th><!-- 문항유형 -->

		<th><spring:message code="comUssOlpQri.regist.qustnrqestn" /></th><!-- 설문문항 -->
		<th><spring:message code="comUssOlpQri.regist.qustnrIem" /></th><!-- 설문항목 -->
		<th><spring:message code="comUssOlpQri.regist.writerName" /></th><!-- 작성자명 -->
		<th><spring:message code="comUssOlpQri.regist.registerDate" /></th><!-- 등록일자  -->
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
		<!-- 문항유형 -->
		<td class="lt_text3L">
			<c:if test="${resultInfo.qestnTyCode == '1'}"><spring:message code="comUssOlpQri.regist.objectiveQuest" /></c:if><!-- 객관식 -->
    		<c:if test="${resultInfo.qestnTyCode == '2'}"><spring:message code="comUssOlpQri.regist.subjectiveQuest" /></c:if><!-- 주관식 -->
		</td>
		<!-- 설문문항 -->
		<td class="lt_text3">
			<a href="<c:url value='/uss/olp/qri/EgovQustnrRespondInfoDetail.do'/>?qestnrQesrspnsId=${resultInfo.qestnrQesrspnsId}" onClick="fn_egov_detail_QustnrRespondInfo('<c:out value="${resultInfo.qestnrQesrspnsId}"/>');return false;"><c:out value='${resultInfo.qestnCn}'/></a>
		</td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 설문항목 -->
		<td class="lt_text3L">
			${resultInfo.iemCn}
		</td>
		<!-- 작성자명 -->
		<td class="lt_text3">${resultInfo.respondNm}</td>
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
	
<input name="qestnrId" type="hidden" value="">
<input name="qestnrTmplatId" type="hidden" value="">
<input name="qustnrIemId" type="hidden" value="">
<input name="qestnrQesrspnsId" type="hidden" value="">
<input name="searchMode" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

</form>
	
</div><!-- end div board -->



</body>
</html>