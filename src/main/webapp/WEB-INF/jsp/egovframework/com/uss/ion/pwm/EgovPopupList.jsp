<%--
  Class Name : EgovPopupList.jsp
  Description : 팝업창관리 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2009.09.16   장동한            최초 생성
    2018.08.23   이정은            공통컴포넌트 3.8 개선
    2019.01.07   이정은            체크박스 미선택 시 alert창 추가
    2019.12.10   신용호            KISA 보안약점 조치
    

    author   : 공통서비스 개발팀 장동한
    since    : 2009.09.16

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussIonPwm.popupList.popupList"/></title><!-- 팝업창관리 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/pwm/listPopup.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_PopupManage(){
	location.href = "<c:url value='/uss/ion/pwm/registPopup.do' />";
}
/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_egov_detail_PopupManage(popupId){
	var vFrom = document.listForm;
	vFrom.popupId.value = popupId;
	vFrom.action = "<c:url value='/uss/ion/pwm/detailPopup.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_PopupManage(){
	var vFrom = document.listForm;
	
	vFrom.action = "<c:url value='/uss/ion/pwm/listPopup.do' />";
	vFrom.submit();

}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_PopupManage(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.getElementById("checkList").checked = checkAllValue;
	}{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
		}

}
/* ********************************************************
* 팝업창 미리보기
******************************************************** */
function fn_egov_view_PopupManage(){


	var FLength = document.getElementsByName("checkList").length;
	var check = 0;
	
	if( FLength == 1){
		if(document.getElementById("checkList").checked == true){
			fn_egov_ajaxPopupInfo_PopupManage( document.getElementById("checkList").value );
			check ++;
		}
	}else{
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName("checkList")[i].checked == true){
				check ++;
				fn_egov_ajaxPopupInfo_PopupManage( document.getElementsByName("checkList")[i].value );
				}
		}
		}
	
	if(check == 0){
		alert("<spring:message code="ussIonPwm.popupList.checkAlert"/>");/* 체크박스를 하나 이상 선택해 주세요. */
			}
	
	return;

}
/* ********************************************************
* 팝업창 정보 Ajax통신으로 정보 획득
******************************************************** */
function fn_egov_ajaxPopupInfo_PopupManage(popupIds){
	var ajaxUrl = "<c:url value='/uss/ion/pwm/ajaxPopupManageInfo.do' />";

	var param = {
			popupId: popupIds
	};

	$.ajax({
		url: ajaxUrl
		,type: 'post'
		,data: param
		,dataType: 'text'
		,success: function(data) {
	    	var returnValueArr = data.split("||");

    	   	fn_egov_popupOpen_PopupManage(popupIds,
       	    	returnValueArr[0],
       	    	returnValueArr[1],
       	    	returnValueArr[2],
       	    	returnValueArr[3],
       	    	returnValueArr[4],
       	    	returnValueArr[5]);
		} ,
		error: function(err) {
			alert("ERROR : "+err.statusText);
		}
	});

}

/* ********************************************************
* 팝업창  오픈
******************************************************** */
function fn_egov_popupOpen_PopupManage(popupId,fileUrl,width,height,top,left,stopVewAt){

	var url = "<c:url value='/uss/ion/pwm/openPopupManage.do' />?";
	url = url + "fileUrl=" + fileUrl;
	url = url + "&stopVewAt=" + stopVewAt;
	url = url + "&popupId=" + popupId;
	var name = popupId;
	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus()}
}

/* ********************************************************
* 팝업창  오픈 쿠키 정보 OPEN
******************************************************** */
function fnGetCookie(name) {
	  var prefix = name + "=";

	  var cookieStartIndex = document.cookie.indexOf(prefix);
	  if (cookieStartIndex == -1) return null;
	  var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	  if (cookieEndIndex == -1) cookieEndIndex = document.cookie.length;


	  return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
}

</script>
</head>
<body>

<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/pwm/listPopup.do'/>" method="post">
	<h1><spring:message code="ussIonPwm.popupList.popupList"/></h1><!-- 팝업창관리 목록 -->
	<span><spring:message code="ussIonPwm.popupList.guide"/> </span>

	<div class="search_box" title="<spring:message code="common.noScriptTitle.msg"/>"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="select.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value='POPUP_SJ_NM' <c:if test="${searchCondition == 'POPUP_SJ_NM'}">selected</c:if>><spring:message code="ussIonPwm.popupList.popupTitleNm"/></option><!-- 팝업창명 -->
					<option value='FILE_URL' <c:if test="${searchCondition == 'FILE_URL'}">selected</c:if>><spring:message code="ussIonPwm.popupList.fileUrl"/></option><!-- 팝업창URL -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="" size="10" onkeypress="press();" title="<spring:message code="input.input"/>" /><!-- 검색단어입력 -->
				
				
				<span class="btn_b"><a href="" onclick="fn_egov_view_PopupManage(); return false;" title="새창 열림"><spring:message code="button.preview"/></a></span><!-- 미리보기 -->
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fn_egov_search_PopupManage(); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/pwm/registPopup.do'/>" onclick="" title="<spring:message code="button.create" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input name="popupId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${popupManageVO.pageIndex}'/>"/>
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:35px" />
			<col style="width:30px" />
			<col style="width:120px" />
			<col style="width:180px" />
			<col style="" />
			<col style="width:70px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 순번 -->
			   <th scope="col"><input type="checkbox" name="checkAll" id="checkAll" class="check2" value="1" onClick="fn_egov_checkAll_PopupManage();"/></th>
			   <th scope="col"><spring:message code="ussIonPwm.popupList.popupTitleNm"/></th><!-- 제목 -->
			   <th scope="col"><spring:message code="ussIonPwm.popupList.ntcePeriod"/></th><!-- 게시기간 -->
			   <th scope="col"><spring:message code="ussIonPwm.popupList.fileUrl"/></th><!-- 파일 -->
			   <th scope="col"><spring:message code="ussIonPwm.popupList.ntceAt"/></th><!-- 게시상태 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(popupManageVO.pageIndex-1) * popupManageVO.pageSize + status.count}"/></td>
				<td>
					<input type="checkbox" name="checkList" id="checkList" class="check2" value="${resultInfo.popupId}"/>
				</td>
				<td>
					<form name="subForm" method="post" action="<c:url value='/uss/ion/pwm/detailPopup.do'/>">
						<input name="popupId" type="hidden" value="${resultInfo.popupId}">
						<input name="pageIndex" type="hidden" value="<c:out value='${popupManageVO.pageIndex}'/>"/>
						<span class="link"><input type="submit" style="width:200px;text-align:left;" value="<c:out value="${resultInfo.popupTitleNm}"/>" onclick="fn_egov_detail_PopupManage('${resultInfo.popupId}'); return false;"></span>
					</form>
				</td>
				<td>
					<c:out value="${fn:substring(resultInfo.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(resultInfo.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(resultInfo.ntceBgnde, 6, 8)}"/> <c:out value="${fn:substring(resultInfo.ntceBgnde, 8, 10)}"/>H <c:out value="${fn:substring(resultInfo.ntceBgnde, 10, 12)}"/>M
					~
					<c:out value="${fn:substring(resultInfo.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(resultInfo.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(resultInfo.ntceEndde, 6, 8)}"/> <c:out value="${fn:substring(resultInfo.ntceEndde, 8, 10)}"/>H <c:out value="${fn:substring(resultInfo.ntceEndde, 10, 12)}"/>M
				<td>
					<c:out value="${resultInfo.fileUrl}"/>
				</td>
				<td>
					<c:out value="${resultInfo.ntceAt}"/>
				</td>
			</tr>
			</c:forEach>
			
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="6">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>

</body>
</html>
