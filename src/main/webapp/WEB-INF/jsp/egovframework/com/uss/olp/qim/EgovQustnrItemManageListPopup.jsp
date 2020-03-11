<%--
  Class Name : EgovQustnrItemManageListPopup.jsp
  Description : 설문항목 목록 팝업 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.20    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQim.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css'/>">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageListPopup.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrItemManage(){
	self.href = "<c:url value='/uss/olp/qim/EgovQustnrItemManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
//function fn_egov_modify_QustnrItemManage(){
//	self.href = "<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do' />";
//}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_QustnrItemManage(qustnrIemId){
	var vFrom = document.listForm;
	vFrom.qustnrIemId.value = qustnrIemId;
	vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_QustnrItemManage(qestnrId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code='common.delete.msg'/>")){ //삭제 하시겠습니까?
		vFrom.qestnrId.value = qestnrId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrItemManage(){
	var vFrom = document.listForm;

	if(vFrom.searchKeyword.value == ""){
		alert('<spring:message code="comUssOlpQim.alert.searchKeyword" /><spring:message code="comUssOlpQim.alert.input" />'); //검색어를 입력해주세요!
		vFrom.searchKeyword.focus();
		return;
	}

	if(vFrom.searchCondition.selectedIndex == 0){
		alert('<spring:message code="comUssOlpQim.alert.searchCondition" /><spring:message code="comUssOlpQim.alert.select" />'); //검색 구분을 선택해주세요!
		vFrom.searchCondition.focus();
		return;
	}

	vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do' />";
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

function fn_egov_open_QustnrItemManage(qestnrId, qestnrTmplatId, qustnrIemId, cnt){
	getDialogArguments();
	/* var opener = window.dialogArguments; */
	var opener;
 
	if (window.dialogArguments) {
	    opener = window.dialogArguments; // Support IE
	} else {
	    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
	}

	opener.document.getElementById("qestnrId").value = qestnrId;
	opener.document.getElementById("qestnrTmplatId").value = qestnrTmplatId;
	opener.document.getElementById("qustnrIemId").value = qustnrIemId;
	opener.document.getElementById("qustnrIemCn").value = document.getElementById("iptText_"+ cnt).value;
	window.returnValue=true;
	window.close();
}
</script>
</head>
<body>

<div class="popup">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="ListForm" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageListPopup.do'/>" method="post" onSubmit="fn_egov_search_QustnrItemManage(); return false;">

	<!-- pageTitle과 title.list를 넣을것 -->
	<h1>${pageTitle} <spring:message code="title.list" /> <spring:message code="comUssOlpQim.regist.popUp" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="IEM_CN"  <c:if test="${searchVO.searchCondition == 'IEM_CN'}">selected="selected"</c:if> ><spring:message code="comUssOlpQim.searchCondition.IEM_CN" /></option><!-- 설문항목 -->
					<option value="FRST_REGISTER_ID"  <c:if test="${searchVO.searchCondition == 'FRST_REGISTER_ID'}">selected="selected"</c:if> ><spring:message code="comUssOlpQim.searchCondition.FRST_REGISTER_ID" /></option><!-- 등록자 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<%-- <input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" > --%>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value=""  maxlength="155" >
				<input type="submit" class="btn_style3" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrItemManage(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_r" > <a href="<c:url value='/uss/olp/qim/EgovQustnrItemManageRegist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span>
				
			</li>
		</ul>
	</div>

</form>

	<!-- 목록영역 -->
	<table class="popwTable" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 40%;">
		
		<col style="width: 15%;">
		<col style="width: 10%;">
		
		<col style="width: 15%;">
		<col style="width: 10%;">	
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpQim.regist.iemCn" /></th><!-- 항목내용 -->

		<th><spring:message code="comUssOlpQim.regist.etcAnswerAt" /></th><!-- 기타답변여부 -->
		<th><spring:message code="comUssOlpQim.regist.registerName" /></th><!-- 등록자 -->
		<th><spring:message code="comUssOlpQim.regist.registerDate" /></th><!-- 등록일자  -->
		<th></th><!-- 선택  -->
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
		<!-- 항목내용  -->
		<td class="lt_text3L">
			<div class="divDotText" style="width:320px; border:solid 0px;">
    			<a href="#LINK" onClick="fn_egov_open_QustnrItemManage('${resultInfo.qestnrId}', '${resultInfo.qestnrTmplatId}', '${resultInfo.qustnrIemId}', '${status.count}')">${resultInfo.iemCn}</a>
    		</div>
		</td>
		<!-- 기타답변여부 -->
		<td class="lt_text3">
			${resultInfo.etcAnswerAt}
		</td>	
	  	<!-- 등록자  -->
	  	<td class="lt_text3">${resultInfo.frstRegisterNm}</td>
	  	<!-- 등록일자  -->
	  	<td class="lt_text3">${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
	  	<!-- 선택 -->
	  	<td class="lt_text3">
			<a href="#LINK" onClick="fn_egov_open_QustnrItemManage('${resultInfo.qestnrId}', '${resultInfo.qestnrTmplatId}', '${resultInfo.qustnrIemId}', '${status.count}')"><spring:message code="input.cSelect" /></a><!-- 선택 -->
			<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.iemCn}">
		</td>
	  </tr>	  
	</c:forEach>	
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
	<input name="qestnrId" id="qestnrId" type="hidden" value="">
	<input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="">
	<input name="qustnrIemId" id="qustnrIemId" type="hidden" value="">
	<input name="searchMode" id="searchMode" type="hidden" value="">	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	
</div><!-- end div board -->



</body>
</html>