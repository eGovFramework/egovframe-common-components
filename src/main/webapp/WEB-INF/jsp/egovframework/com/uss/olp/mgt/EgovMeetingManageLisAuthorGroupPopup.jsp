<%--
  Class Name : EgovMeetingManageList.jsp
  Description : 부서 목록 팝업페이지
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
title><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnzPopup"/></title><!-- 부서 검색 팝업 -->
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
 * 검색 함수
 ******************************************************** */
function fn_egov_search_MeetingManage(){
	var vFrom = document.listForm;
	
	vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />";
	vFrom.submit();
	
}
/* ********************************************************
 * 선택 처리 함수
 ******************************************************** */
function fn_egov_open_Popup(cnt, groupId){
	
	getDialogArguments();
	/* var opener = window.dialogArguments */
	
	var opener;
	 
	if (window.dialogArguments) {
	    opener = window.dialogArguments; // Support IE
	} else {
	    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
	}
	/*
	회의관리/주관자부서
	*/
	if(opener[1] == "typeMeeting1"){
		opener[0].document.getElementById("mnaerDeptId").value = groupId;
		opener[0].document.getElementById("mnaerDeptNm").value = document.getElementById("iptText_"+ cnt).value;
	}else if(opener[1] == "typeMeeting2"){
		opener[0].document.getElementById("mngtDeptId").value = groupId;
		opener[0].document.getElementById("mngtDeptNm").value = document.getElementById("iptText_"+ cnt).value;		
	}else if(opener[1] == "typeDeptSchdule"){
		opener[0].document.getElementById("schdulDeptId").value = groupId;
		opener[0].document.getElementById("schdulDeptName").value = document.getElementById("iptText_"+ cnt).value;
	}
	
	
	window.returnValue=true;
	window.close();
	
}
</script>
</head>
<body style="margin-top:10px">

<div class="board">
<form name="listForm" id="listForm" action="<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do'/>" method="post">
	<h1><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnzList"/></h1><!-- 부서 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select class="select" name="searchCondition" title="<spring:message code="select.searchCondition"/>">
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value='ORGNZT_NM' <c:if test="${searchCondition == 'GROUP_NM'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnztNm"/></option><!-- 부서명 -->
					<option value='ORGNZT_DC' <c:if test="${searchCondition == 'GROUP_DC'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnztDc"/></option><!-- 부서설명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="${searchKeyword}" size="10" onkeypress="press();" title="<spring:message code="title.search"/>" />
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fn_egov_search_MeetingManage(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnzList"/></caption><!-- 부서 목록 조회 -->
		<colgroup>
			<col style="width:35px" />
			<col style="width:100px" />
			<col style="" />
			<col style="width:60px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnztNm"/></th><!-- 부서명 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageLisAuthorGroupPopup.orgnztDc"/></th><!-- 부서설명 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				<tr>
					<td>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
					<td>${resultInfo.orgnztNm}</td>
					<td class="left">${resultInfo.orgnztDc}</td>
					<td>
						<input class="btn01" type="button" value="<spring:message code="input.cSelect"/>" onclick="fn_egov_open_Popup('${status.count}', '${resultInfo.orgnztId}')" />
						<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.orgnztNm}">
					</td>
				</tr>   
			</c:forEach>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr> 
					<td class="lt_text3" colspan="4">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>
</div>

<input name="cmd" type="hidden" value="">
</form>

</body>
</html>
