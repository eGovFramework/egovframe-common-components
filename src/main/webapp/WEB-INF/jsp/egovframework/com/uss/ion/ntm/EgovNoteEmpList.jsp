<%--
  Class Name : EgovNoteEmpList.jsp
  Description : 수신자 /참조자 선택
  Modification Information

      수정일           수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.20    장동한          최초 생성
     2017.07.18    최두영          공통컴포넌트 3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.20

--%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUssIonNtm.NoteEmpList.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css' />">
<script type="text/javaScript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/ntm/listEgovNoteEmpListPopup.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_NoteEmp(){
	location.href = "<c:url value='/uss/olp/mgt/EgovNoteEmpRegist.do'/>";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_NoteEmp(){
	location.href = "<c:url value='/uss/olp/mgt/EgovNoteEmpModify.do'/>";
}
/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_egov_detail_NoteEmp(mtgId){
	var vFrom = document.listForm;
	vFrom.mtgId.value = mtgId;
	vFrom.action = "<c:url value='/uss/olp/mgt/EgovNoteEmpDetail.do'/>";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_NoteEmp(mtgId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code="common.delete.msg"/>?")){
		vFrom.mtgId.value = mtgId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/mgt/EgovNoteEmpList.do'/>";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
} 
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_NoteEmp(){
	var vFrom = document.listForm;
/* 	vFrom.pageIndex.value = "1"; */
	vFrom.action = "<c:url value='/uss/ion/ntm/listEgovNoteEmpListPopup.do'/>";
	vFrom.submit();
	if(e.keyCode == 13) action();
}
/* ********************************************************
* 화면 닫기 함수
******************************************************** */
function fn_egov_close_NoteEmp(){

	var FLength = document.getElementsByName("checkList").length;

	var strSplit;
	var arrSplit;
	var strRecptnSe;
	var strRecptnSeCode;
	//select 박스 수신자 객체
	var selRecptnEmp = opener.document.getElementById("recptnEmp");

	if( FLength == 1){
		if(document.getElementsByName("checkList")[0].checked == true){
			strSplit = document.getElementsByName("checkList")[0].value;
			arrSplit = strSplit.split("|");

			//수신 체크시
			if(opener.document.getElementsByName("recptnSe")[0].checked == true){
				strRecptnSe = "수신";
				strRecptnSeCode = "1";
			}else{
				strRecptnSe = "참조";
				strRecptnSeCode = "2";
			}
			//추가할 option 객체
			var option = document.createElement("option");
			option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
			option.setAttribute("value", arrSplit[0]);

			opener.fn_egov_recptnEmpOption_NoteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);
		}
	}else{
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName("checkList")[i].checked == true){

				strSplit = document.getElementsByName("checkList")[i].value;
				arrSplit = strSplit.split("|");

				//수신 체크시
				if(opener.document.getElementsByName("recptnSe")[0].checked == true){
					strRecptnSe = "수신";
					strRecptnSeCode = "1";
				}else{
					strRecptnSe = "참조";
					strRecptnSeCode = "2";
				}
				//추가할 option 객체
				var option = document.createElement("option");
				option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
				option.setAttribute("value", arrSplit[0]);

				opener.fn_egov_recptnEmpOption_NoteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);

			}
		}
	}

	window.close();

}
/* ********************************************************
* 이름/이이디 클릭시 단건 입력
******************************************************** */
function fn_egov_close_NoteEmpOne(i){

	var FLength = document.getElementsByName("checkList").length;

	var strSplit;
	var arrSplit;
	var strRecptnSe;
	var strRecptnSeCode;
	//select 박스 수신자 객체
	var selRecptnEmp = opener.document.getElementById("recptnEmp");

	if( FLength == 1){
		if(document.getElementsByName("checkList")[0] != null && document.getElementsByName("checkList")[0] != undefined){

			strSplit = document.getElementsByName("checkList")[0].value;
			arrSplit = strSplit.split("|");

			//수신 체크시
			if(opener.document.getElementsByName("recptnSe")[0].checked == true){
				strRecptnSe = "수신";
				strRecptnSeCode = "1";
			}else{
				strRecptnSe = "참조";
				strRecptnSeCode = "2";
			}
			//추가할 option 객체
			var option = document.createElement("option");
			option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
			option.setAttribute("value", arrSplit[0]);

			opener.fn_egov_recptnEmpOption_NoteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);
		}
	}else{
			if(document.getElementsByName("checkList")[i] != null && document.getElementsByName("checkList")[i] != undefined){

				strSplit = document.getElementsByName("checkList")[i].value;
				arrSplit = strSplit.split("|");

				//수신 체크시
				if(opener.document.getElementsByName("recptnSe")[0].checked == true){
					strRecptnSe = "수신";
					strRecptnSeCode = "1";
				}else{
					strRecptnSe = "참조";
					strRecptnSeCode = "2";
				}
				//추가할 option 객체
				var option = document.createElement("option");
				option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
				option.setAttribute("value", arrSplit[0]);

				opener.fn_egov_recptnEmpOption_NoteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);
			}
	}
	window.close();
}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_NoteEmp(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.getElementsByName("checkList")[0].checked = checkAllValue;
	}else{
		for(var i=0; i < FLength; i++)
		{
			document.getElementsByName("checkList")[i].checked = checkAllValue;
		}
	}
}
</script>
</head>
<body>
<!-- noscript 테그 -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="popup">
<form name="listForm" id="listForm" action="<c:url value='/uss/ion/ntm/listEgovNoteEmpListPopup.do'/>" method="post">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 상단 검색창 -->
	<div class="pop_search_box" title="<spring:message code="common.searchCondition.msg" />">
 		<ul>
 			<li>
				<select name="searchCondition" id="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>">
					<option value=''>--<spring:message code="input.select" />--</option>
					<option value='USER_NM' <c:if test="${searchVO.searchCondition == 'USER_NM'}">selected="selected"</c:if>><spring:message code="comUssIonNtm.NoteEmpList.name" /></option>
					<option value='EMPLYR_ID' <c:if test="${searchVO.searchCondition == 'EMPLYR_ID'}">selected="selected"</c:if>><spring:message code="comUssIonNtm.NoteEmpList.id" /></option>
					<option value='OFFM_TELNO' <c:if test="${searchVO.searchCondition == 'OFFM_TELNO'}">selected="selected"</c:if>><spring:message code="comUssIonNtm.NoteEmpList.tel" /></option>
	   			</select>
 			</li> 		
  			<li>
   				<input class="s_input" name="searchKeyword" title="<spring:message code="title.search"/>" type="text" size="35"  onkeyup="if(window.event.keyCode==13){fn_egov_search_NoteEmp(); return false;}" value='<c:out value="${mberVO.searchKeyword}"/>' maxlength="255"><!-- 검색창 -->
				<input type="button" class="s_btn" value="<spring:message code="title.inquire" />" onClick="fn_egov_search_NoteEmp(); return false; "/><!-- 조회 -->
  			</li>
		</ul>
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</div>
</form>
<!--  목록  -->
<form name="contentForm" id="contentForm" action="<c:url value='/uss/ion/ntm/listEgovNoteEmpListPopup.do'/>" method="post">

<table class="pop_board_list">
		<caption>${pageTitle} <spring:message code="title.list" /></caption><!-- 쪽지관리 -->
		<colgroup>
			<col style="width: 3%;">
			<col style="width: 9%;">
			<col style="width: 9%;">
			<col style="width: 10%;">
			<col style="width: 25%;">
			<col style="width: 3%;">
		</colgroup>
		<thead>
			<tr>
    			<th><spring:message code="comUssIonNtm.NoteEmpList.number" /></th><!-- 번호 -->
    			<th><spring:message code="comUssIonNtm.NoteEmpList.id" /></th><!-- 아이디 -->
    			<th><spring:message code="comUssIonNtm.NoteEmpList.name" /></th><!-- 이름 -->
  				<th><spring:message code="comUssIonNtm.NoteEmpList.tel" /></th><!-- 전화번호 -->
    			<th><spring:message code="comUssIonNtm.NoteEmpList.address" /></th><!-- 주소 -->
  				<th><input type="checkbox" name="checkAll" title="<spring:message code="comUssIonNtm.NoteEmpList.all" />" id="checkAll" value="1" onClick="fn_egov_checkAll_NoteEmp();"></th><!-- 전체선택 -->
			</tr>
		</thead>

	<tbody class="ov">
		<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
		<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="6"><spring:message code="common.nodata.msg" />	</td><!-- 자료가 없습니다. 다른 검색조건을 선택해주세요. -->
			</tr>
		</c:if>
		<%-- 데이터를 화면에 출력해준다  --%>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td class="center">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
				<td class="center"><a href="#LINK" onClick="fn_egov_close_NoteEmpOne(${status.count-1});">${resultInfo.emplyrId}</a></td>
				<td class="center"><a href="#LINK" onClick="fn_egov_close_NoteEmpOne(${status.count-1});">${resultInfo.emplyrNm}</a></td>
				<td class="center"><a href="#LINK" onClick="fn_egov_close_NoteEmpOne(${status.count-1});">${resultInfo.offmTelno}</a></td>
				<td class="left"><a href="#LINK" onClick="fn_egov_close_NoteEmpOne(${status.count-1});">${resultInfo.homeAdres} ${resultInfo.detailAdres}</a></td>
    			<td class="center"><input type="checkbox" name="checkList" title="<spring:message code="table.select" />" value="${resultInfo.uniqId}|${resultInfo.emplyrId}|${resultInfo.emplyrNm}"></td><!-- 선택 -->
			</tr>
		</c:forEach>
	</tbody>

</table>

	<!-- 페이징 처리 영역-->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
	<!-- 선택 및 닫기 버튼 -->
	<div class="btn">
		<button class="btn_style3" onclick="fn_egov_close_NoteEmp()" title="<spring:message code="button.select" />"><spring:message code="button.select" /></button><!-- 선택 -->
		<button class="btn_style3" onclick="window.close()" title="<spring:message code="button.close" />"><spring:message code="button.close" /></button><!-- 닫기 -->
		<div style="clear:both;"></div>
	</div>
</form>
</div>

</body>
</html>