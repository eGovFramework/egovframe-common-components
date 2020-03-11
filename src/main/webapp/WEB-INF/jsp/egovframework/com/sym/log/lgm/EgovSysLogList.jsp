<%
/**
 * @Class Name : EgovSysLogList.jsp
 * @Description : 시스템 로그 정보목록 화면
 * @Modification Information
 * @
 * @  수정일      수정자          수정내용
 * @  -------     --------    ---------------------------
 * @ 2009.03.11   이삼섭          최초 생성
 * @ 2011.07.08   이기하          패키지 분리, 경로수정(sym.log -> sym.log.lgm)
 * @ 2011.09.14   서준식          검색 후 화면에 검색일자 표시안되는 오류 수정
 * @ 2017.09.17   이정은          표준프레임워크 v3.7 개선
 * @author 공통서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comSymLogLgm.sysLog.title"/></c:set>
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
	document.SysLogForm.searchWrd.focus();
}
/* ********************************************************
 * 달력
 ******************************************************** */
function fn_egov_init_date(){
	
	$("#searchBgnDe").datepicker(  
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


	$("#searchEndDe").datepicker( 
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
	document.SysLogForm.pageIndex.value = pageNo;
	document.SysLogForm.action = "<c:url value='/sym/log/lgm/SelectSysLogList.do'/>";
   	document.SysLogForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_sysLog(){
	var vFrom = document.SysLogForm;

	 if(vFrom.searchEndDe.value != ""){
	     if(vFrom.searchBgnDe.value > vFrom.searchEndDe.value){
	         alert("<spring:message code="comSymLogLgm.validate.dateCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	         return;
		  }
	 }else{
		 vFrom.searchEndDe.value = "";
	 }

	vFrom.pageIndex.value = "1";
	vFrom.action = "<c:url value='/sym/log/lgm/SelectSysLogList.do'/>";
	vFrom.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_sysLog(requstId) {
  	var width = 850;
  	var height = 450;
  	var top = 0;
  	var left = 0;
  	var url = "<c:url value='/sym/log/lgm/SelectSysLogDetail.do' />?requstId="+requstId;
  	var name = "sysLogDetailPopup"
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

<form name="SysLogForm" action="<c:url value='/sym/log/lgm/SelectSysLogList.do'/>" method="post" onSubmit="fn_egov_search_sysLog(); return false;"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<!-- 발생일자 선택 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<spring:message code="comSymLogLgm.sysLog.occrrncDe" />&nbsp;:&nbsp;<!-- 발생일자 -->
				<input type="text" name="searchBgnDe" id="searchBgnDe" size="15" maxlength="10" value="${searchVO.searchBgnDe}" title="<spring:message code="comSymLogLgm.seachWrd.searchBgnDe" />" > ~ <!-- 검색시작일  -->
				<input type="text" name="searchEndDe" id="searchEndDe" size="15" maxlength="10" value="${searchVO.searchEndDe}" title="<spring:message code="comSymLogLgm.seachWrd.searchEndDe" />" >&nbsp;&nbsp;&nbsp;<!-- 검색종료일  -->				
			</li>
			<li><div style="line-height:6px;">&nbsp;&nbsp;&nbsp;&nbsp;</div><div><spring:message code="comSymLogLgm.sysLog.processSeCode" /> :  </div></li><!-- 처리구분-->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="15" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="15" >
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
		<col style="width: 15%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 10;">
		<col style="width: 10%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comSymLogLgm.sysLog.reqId" /></th><!-- 요청ID -->
		<th><spring:message code="comSymLogLgm.sysLog.occrrncDe" /></th><!-- 발생일자 -->
		<th><spring:message code="comSymLogLgm.sysLog.methodNm" /></th><!-- 메소드명 -->
		<th><spring:message code="comSymLogLgm.sysLog.processSeCode" /></th><!-- 처리구분 -->
		<th><spring:message code="comSymLogLgm.sysLog.processTime" /></th><!-- 처리시간 -->
		<th><spring:message code="comSymLogLgm.sysLog.rqesterId" /></th><!-- 요청자 -->
		<th><spring:message code="comSymLogLgm.sysLog.detail" /></th><!-- 상세보기 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td><c:out value='${result.requstId}'/></td>
		<td><c:out value='${fn:substring(result.occrrncDe,0,19)}'/></td>
		<td><c:out value='${result.methodNm}'/></td>
		<td><c:out value='${result.processSeCodeNm}'/></td>
		<td><c:out value='${result.processTime}'/></td>
		<td><c:out value='${result.rqsterNm}'/></td>
		<td>
		<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" class="cursor" onclick="fn_egov_inquire_sysLog('<c:out value="${result.requstId}"/>'); return false;" alt="<spring:message code="title.detail" />"  title="<spring:message code="title.detail" />">
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