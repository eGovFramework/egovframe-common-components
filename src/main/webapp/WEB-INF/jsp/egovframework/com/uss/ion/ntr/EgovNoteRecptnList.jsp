<%--
  Class Name : EgovNoteRecptnList.jsp
  Description : 받은쪽지함관리 목록조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.05    장동한          최초 생성
     2017.09.14    장동한          공통컴포넌트 3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.05

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<c:set var="pageTitle"><spring:message code="comUssIonNtr.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	$("#searchFromDate").datepicker(  
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
	         , buttonImageOnly: true
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});


	$("#searchToDate").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/ntr/listNoteRecptn.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_NoteRecptn(noteId,noteTrnsmitId){
	var vFrom = document.listForm;
	vFrom.noteId.value = noteId;
	vFrom.noteTrnsmitId.value = noteTrnsmitId;
	vFrom.action = "<c:url value='/uss/ion/ntr/detailNoteRecptn.do'/>";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_NoteRecptn(){
	var vFrom = document.listForm;

	 if(vFrom.searchFromDate.value != ""){
	     if(vFrom.searchFromDate.value > vFrom.searchToDate.value){
	         alert("<spring:message code="comUssIonNtr.validate.dateFromCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	         return;
		  }
	 }else{
		 vFrom.searchToDate.value = "";
	 }

	vFrom.pageIndex.value = "1";
	vFrom.action = "<c:url value='/uss/ion/ntr/listNoteRecptn.do'/>";
	vFrom.submit();

}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_NoteRecptn(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.listForm.checkList.checked = checkAllValue;
	}else{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
	}

}
/* ********************************************************
* 체크 박스 선태 건수
******************************************************** */
var g_nDelCount  = 0;
function fn_egov_delCnt_NoteRecptn(){

	g_nDelCount = 0;
	var FLength = document.getElementsByName("checkList").length;

	//undefined
	if( FLength == 1){
		if(document.listForm.checkList.checked == true){g_nDelCount++;}
	}else{
			for(var i=0; i < FLength; i++)
			{
				if(document.getElementsByName("checkList")[i].checked == true){g_nDelCount++;}
			}
	}

	return g_nDelCount;

}
/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_egov_delete_NoteRecptn(){
	var vFrom = document.listForm;
	var checkList = vFrom.checkList;
	var noteId = new Array();
	var noteTrnsmitId = new Array();
	var noteRecptnId = new Array();
	var noteIdAll = vFrom.noteIdAll;
	var noteRecptnIdAll = vFrom.noteRecptnIdAll;
	var noteTrnsmitIdAll = vFrom.noteTrnsmitIdAll;

	if(fn_egov_delCnt_NoteRecptn() == 0){
		alert("<spring:message code="comUssIonNtr.validate.noDelList" />   "); //삭제할 목록을 선택해주세요!
		document.getElementById('checkAll').focus();
		return;
	}
	
	var checkedElements = vFrom.querySelectorAll('input[name="checkList"]:checked');
	Array.from(checkedElements).map(function(checkbox) {
		noteId.push(checkbox.parentElement.querySelector('input[name="noteId"]').value);
		noteTrnsmitId.push(checkbox.parentElement.querySelector('input[name="noteTrnsmitId"]').value);
		noteRecptnId.push(checkbox.parentElement.querySelector('input[name="noteRecptnId"]').value);
	});
	
	if(confirm("<spring:message code="comUssIonNts.validate.deleteCnfirmt" />")){ //선택된 보낸쪽지함을 삭제 하시겠습니까?
		noteIdAll.value = noteId;
		noteTrnsmitIdAll.value = noteTrnsmitId;
		noteRecptnIdAll.value = noteRecptnId;
		vFrom.action = "<c:url value='/uss/ion/ntr/listNoteRecptn.do'/>";
		vFrom.cmd.value = 'del';
		vFrom.submit();

	}
}
</script>
</head>
<body onload="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<form name="listForm" id="listForm" action="<c:url value='/uss/ion/ntr/listNoteRecptn.do'/>" method="post" onSubmit="fn_egov_search_NoteRecptn(); return false;"> 
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<spring:message code="comUssIonNtr.searchCondition.rcverDate" />&nbsp;:&nbsp;<!-- 보낸날짜 -->
				<input type="text" name="searchFromDate" id="searchFromDate" size="10" maxlength="10" value="${searchVO.searchFromDate}" title="<spring:message code="comUssIonNtr.searchCondition.searchFromDate" />" readonly> ~<!-- 보낸날짜 시작일자  -->
				<input type="text" name="searchToDate" id="searchToDate" size="10" maxlength="10" value="${searchVO.searchToDate}" title="<spring:message code="comUssIonNtr.searchCondition.searchToDate" />" readonly><!-- 보낸날짜 종료일자  -->				
			</li>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value='NOTE.NOTE_SJ' <c:if test="${searchCondition == 'NOTE.NOTE_SJ'}">selected</c:if>><spring:message code="comUssIonNtr.searchCondition.NOTE_SJ" /></option><!-- 쪽지제목  -->
		   			<option value='NOTE.NOTE_CN' <c:if test="${searchCondition == 'NOTE.NOTE_CN'}">selected</c:if>><spring:message code="comUssIonNtr.searchCondition.NOTE_CN" /></option><!-- 쪽지내용 -->
		   			<option value='NOTE_RNS.RCVER_NM' <c:if test="${searchCondition == 'NOTE_RNS.RCVER_NM'}">selected</c:if>><spring:message code="comUssIonNtr.searchCondition.RCVER_NM" /></option><!-- 보낸사람 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="javascript:fn_egov_delete_NoteRecptn();"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
			</li>
		</ul>
	</div>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 3%;">
		<col style="width: 5%;">
		<col style="width: ;">
		<col style="width: 13%;">
		<col style="width: 18%;">
	</colgroup>
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" id="checkAll" title="<spring:message code="input.selectAll.title" />" value="1" onClick="fn_egov_checkAll_NoteRecptn();"></th><!-- 전체선택 -->
		<th><spring:message code="comUssIonNtr.list.seq" /></th><!-- 순번 -->
		<th class="board_th_link"><spring:message code="comUssIonNtr.list.noteSj" />asdf</th><!-- 제목 -->
		<th><spring:message code="comUssIonNtr.list.rcverNm" /></th><!-- 보낸사람 -->
		<th><spring:message code="comUssIonNtr.list.rcverDateTime" /></th><!-- 보낸시각 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><input type="checkbox" name="checkList" title="<spring:message code="input.cSelect" />">
		<input type="hidden" name="noteId" value="<c:out value="${egovc:encryptId(resultInfo.noteId)}"/>" />
		<input type="hidden" name="noteTrnsmitId" value="<c:out value="${egovc:encryptId(resultInfo.noteTrnsmitId)}"/>" />
		<input type="hidden" name="noteRecptnId" value="<c:out value="${egovc:encryptId(resultInfo.noteRecptnId)}"/>" />
		</td>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left">
			<a href="<c:url value='/uss/ion/ntr/detailNoteRecptn.do'/>?pageIndex=${searchVO.pageIndex}&amp;noteId=${egovc:encryptId(resultInfo.noteId)}&amp;noteTrnsmitId=${egovc:encryptId(resultInfo.noteTrnsmitId)}&amp;noteRecptnId=${egovc:encryptId(resultInfo.noteRecptnId)}">
 				<c:set var="noteRecptnNoteSj" value='${fn:escapeXml(resultInfo.noteSj)}'/>
 				<c:set var="noteRecptnNoteCn" value="${fn:replace(resultInfo.noteSj, crlf , '<br>')}"/>
				<c:out value='${resultInfo.noteSj }' escapeXml='false'/>
			</a>
		</td>
		<td><c:out value="${resultInfo.trnsmiterNm}"/></td>
		<td><c:out value="${resultInfo.frstRegisterPnttm}"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/></ul>
	</div>
	

	<input name="cmd" type="hidden" value="">
	<input name="noteIdAll" type="hidden" value="">
	<input name="noteTrnsmitIdAll" type="hidden" value="">
	<input name="noteRecptnIdAll" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	</form>
</div><!-- end div board -->



</body>
</html>