<%
/**
 * @Class Name : EgovPrivacyLogList.jsp
 * @Description : 개인정보조회 로그 정보목록 화면
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------        --------    ---------------------------
 * @ 2014.09.11      표준프레임워크          최초 생성
 * @ 2017.09.21		이정은					표준프레임워크 3.7 개선
 *  @author Vincent Han
 *  @since 2014.09.11
 *  @version 3.5
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comSymLogPlg.privacyLog.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.PrivacyLogForm.searchWord.focus();
}
/* ********************************************************
 * 달력
 ******************************************************** */
function fn_egov_init_date(){
	
	$("#searchBeginDate").datepicker(  
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


	$("#searchEndDate").datepicker( 
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
/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.PrivacyLogForm.pageIndex.value = pageNo;
	document.PrivacyLogForm.action = "<c:url value='/sym/log/plg/SelectPrivacyLogList.do'/>";
   	document.PrivacyLogForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_privacyLog(){
	var vFrom = document.PrivacyLogForm;

	 if(vFrom.searchEndDate.value != ""){
	     if(vFrom.searchBeginDate.value > vFrom.searchEndDate.value){
	         alert("<spring:message code="comSymLogPlg.validate.dateCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	         return;
		  }
	 }else{
		 vFrom.searchEndDate.value = "";
	 }

	vFrom.pageIndex.value = "1";
	vFrom.action = "<c:url value='/sym/log/plg/SelectPrivacyLogList.do'/>";
	vFrom.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_privacyLog(requestId) {
  	var width = 850;
  	var height = 380;
  	var top = 0;
  	var left = 0;
  	var url = "<c:url value='/sym/log/plg/SelectPrivacyLogDetail.do' />?requestId="+requestId;
  	var name = "PrivacyLogDetailPopup"
  	var openWindows = window.open(url,name, "width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");
}
</script>
<style>
.board_list tbody td img{align: middle;}
</style>
</head>

<body onload="fn_egov_init(); fn_egov_init_date();" >

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="PrivacyLogForm" action="<c:url value='/sym/log/plg/SelectPrivacyLogList.do'/>" method="post" onSubmit="fn_egov_search_privacyLog(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<!-- 발생일자 선택 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<spring:message code="comSymLogPlg.privacyLog.inquiryDatetime" />&nbsp;:&nbsp;<!-- 발생일자 -->
				<input type="text" name="searchBeginDate" id="searchBeginDate" size="15" maxlength="10" value="${searchVO.searchBeginDate}" title="<spring:message code="comSymLogPlg.seachWrd.searchBeginDate" />" > ~ <!-- 검색시작일  -->
				<input type="text" name="searchEndDate" id="searchEndDate" size="15" maxlength="10" value="${searchVO.searchEndDate}" title="<spring:message code="comSymLogPlg.seachWrd.searchEndDate" />" >&nbsp;<!-- 검색종료일  -->				
			</li>
			<li><div style="line-height:6px;">&nbsp;&nbsp;&nbsp;&nbsp;</div><div><spring:message code="comSymLogPlg.privacyLog.inquiryInfo" /> :  </div></li><!-- 조회정보-->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWord" type="text"  size="15" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWord}"/>'  maxlength="15" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: 10;">
		<col style="width: 10%;">
		<col style="width: ;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comSymLogPlg.privacyLog.inquiryDatetime" /></th><!-- 조회일자 -->
		<th><spring:message code="comSymLogPlg.privacyLog.serviceName" /></th><!-- 서비스명 -->
		<th><spring:message code="comSymLogPlg.privacyLog.inquiryInfo" /></th><!-- 조회정보 -->
		<th><spring:message code="comSymLogPlg.privacyLog.requesterName" /></th><!-- 요청자 -->
		<th><spring:message code="comSymLogPlg.privacyLog.requesterIp" /></th><!-- 요청자IP -->
		<th><spring:message code="comSymLogPlg.privacyLog.detail" /></th><!-- 상세보기 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td><c:out value='${fn:substring(result.inquiryDatetime,0,19)}'/></td>
		<td><c:out value='${result.serviceName}'/></td>
		<td><c:out value='${result.inquiryInfo}'/></td>
		<td><c:out value='${result.requesterName}'/></td>
		<td><c:out value='${result.requesterIp}'/></td>
		<td>
		<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" class="cursor" onclick="fn_egov_detail_privacyLog('<c:out value="${result.requestId}"/>'); return false;"  alt="<spring:message code="title.detail" />"  title="<spring:message code="title.detail" />">
		</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
		</ul>
	</div>
	 
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>