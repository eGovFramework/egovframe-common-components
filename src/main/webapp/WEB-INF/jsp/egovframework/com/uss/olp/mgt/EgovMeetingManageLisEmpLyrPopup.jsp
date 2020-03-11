<%--
  Class Name : EgovMeetingManageList.jsp
  Description : 아이디 검색 팝업 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2018.09.13    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.userPopup"/></title><!-- 사용자 검색 팝업 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
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
 * 상세화면 처리 함수
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
	if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
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
<body style="margin-top:10px">

<div class="board">
<form name="listForm" id="listForm" action="<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do'/>" method="post">
	<h1><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.searchId"/></h1><!-- 아이디 검색 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="select.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value='USER_NM' <c:if test="${searchCondition == 'USER_NM'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.userNm"/></option><!-- 이름 -->
					<option value='EMPLYR_ID' <c:if test="${searchCondition == 'EMPLYR_ID'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.emplyrId"/></option><!-- 아이디 -->
					<option value='OFFM_TELNO' <c:if test="${searchCondition == 'OFFM_TELNO'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.offmTelno"/></option><!-- 전화번호 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="${searchKeyword}" size="10" title="<spring:message code="input.input"/>" /><!-- 검색단어입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fn_egov_search_MeetingManage(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:35px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="" />
			<col style="width:60px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.emplyrId"/></th><!-- 아이디 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.userNm"/></th><!-- 이름 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.offmTelno"/></th><!-- 전화번호 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageLisEmpLyrPopup.detailAdres"/></th><!-- 주소 -->
			   <th scope="col"></th>			   
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 화면에 출력해준다  --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
				<td>${resultInfo.emplyrId}</td>
				<td>${resultInfo.userNm}</td>
				<td>${resultInfo.offmTelno}</td>
				<td class="left">${resultInfo.homeadres} ${resultInfo.detailAdres}</td>
		    	<td>
		    		<input class="btn01" type="button" value="<spring:message code="input.cSelect"/>" onclick="fn_egov_open_Popup('${status.count}', '${resultInfo.esntlId}')" />
		    		<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.emplyrId}">
		    	</td>
			</tr>
			</c:forEach>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td class="lt_text3" colspan="9">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>

</form>

</DIV>
</body>
</html>
