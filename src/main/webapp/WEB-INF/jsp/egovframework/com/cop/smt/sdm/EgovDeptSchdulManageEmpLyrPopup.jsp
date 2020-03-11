<%
 /**
  * @Class Name : EgovDeptSchdulManageEmpLyrPopup.jsp
  * @Description : 사용자 검색 팝업	
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09    장동한        최초 생성
  *   2016.09.09 	장동한        표준프레임워크 v3.6 개선
  *  
  *  @author 공통서비스 개발팀 장동한
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comCopSmtSdm.empLyrTitle"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<base target="_self">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_MeetingManage(){
	location.href = "<c:url value='/uss/olp/mgt/EgovMeetingManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_MeetingManage(){
	location.href = "<c:url value='/uss/olp/mgt/EgovMeetingManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_MeetingManage(mtgId){
	var vFrom = document.listForm;
	vFrom.mtgId.value = mtgId;
	vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_MeetingManage(mtgId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code="comCopSmtSdm.validate.alert.delete" />")){ //삭제 하시겠습니까?
		vFrom.mtgId.value = mtgId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_MeetingManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do' />";
	vFrom.submit();

}
/* ********************************************************
 * 선택 처리 함수
 ******************************************************** */
function fn_egov_open_Popup(cnt, esntlId){

	getDialogArguments();
	/* var opener = window.dialogArguments; */
	var opener;
	 
	if (window.dialogArguments) {
	    opener = window.dialogArguments; // Support IE
	} else {
	    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
	}
	/*
	회의관리/주관자ID
	*/
	if(opener[1] == "typeMeeting"){
		opener[0].document.getElementById("mnaerId").value = esntlId;
		opener[0].document.getElementById("mnaerNm").value = document.getElementById("iptText_"+ cnt).value;
	}else if(opener[1] == "typeDeptSchdule"){
		opener[0].document.getElementById("schdulChargerId").value = esntlId;
		opener[0].document.getElementById("schdulChargerName").value = document.getElementById("iptText_"+ cnt).value;
	}

	window.returnValue=true;
	window.close();

}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="popup">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

	<form name="listForm" id="listForm" action="<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do'/>" method="post">
	<!-- 검색영역 -->
	<div class=pop_search_box title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
			   		<option value='USER_NM' <c:if test="${searchCondition == 'USER_NM'}">selected</c:if>><spring:message code="comCopSmtSdm.empLyrList.id" /></option><!-- 이름 -->
			   		<option value='EMPLYR_ID' <c:if test="${searchCondition == 'EMPLYR_ID'}">selected</c:if>><spring:message code="comCopSmtSdm.empLyrList.name" /></option><!-- 아이디 -->
			   		<option value='OFFM_TELNO' <c:if test="${searchCondition == 'OFFM_TELNO'}">selected</c:if>><spring:message code="comCopSmtSdm.empLyrList.tel" /></option><!-- 전화번호 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>


	<!-- 목록영역 -->
	<table class="pop_board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: ;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comCopSmtSdm.empLyrList.id" /></th><!-- 아이디 -->
		<th><spring:message code="comCopSmtSdm.empLyrList.name" /></th><!-- 이름 -->
		<th><spring:message code="comCopSmtSdm.empLyrList.tel" /></th><!-- 전화번호 -->
		<th><spring:message code="comCopSmtSdm.empLyrList.addr" /></th><!-- 주소 -->
		<th></th><!-- 선택 -->
		
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<td class="left">${resultInfo.emplyrId}</td>
		<td class="left">${resultInfo.userNm}</td>
		<td class="left">${resultInfo.offmTelno}</td>
		<td class="left">${resultInfo.homeadres} ${resultInfo.detailAdres}</td>
	   	<td>
	   		<button class="btn_style3" onClick="javascript:fn_egov_open_Popup('${status.count}', '${resultInfo.esntlId}'); return false;" title="<spring:message code="button.select" /> <spring:message code="input.button" />"><spring:message code="button.select" /></button>
	   		<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.emplyrId}">
	   	</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>


	<input name="cmd" type="hidden" value="">
	</form>
</DIV>
</body>
</html>
