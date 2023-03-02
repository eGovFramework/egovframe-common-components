<%
/**
 * @Class Name : EgovCmtManageList.java
 * @Description : EgovCmtManageList jsp
 * @Modification Information
 * @
 * @  수정일        수정자                      수정내용
 * @ ---------     --------            ---------------------------
 * @ 2014.08.29    표준프레임워크센터          최초 생성
 * @ 2018.09.10    이정은                          공통컴포넌트 3.8 개선
 *
 *  @author 표준프레임워크센터
 *  @since 2014.08.29
 *  @version 3.5
 *  @see
 *  
 *  Copyright (C) 2014 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussCmt.cmtManageList.cmtManage"/></title><!-- 출퇴근 관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectCmtManageList(pageNo){
	 var varForm				 = document.all["listForm"];
	 //varForm.searchCondition.value = "1";
// 	 varForm.pageIndex.value = pageNo;
	 varForm.action = "<c:url value='/uss/cmt/EgovCmtManageList.do'/>";
	 varForm.submit();
 }

/* ********************************************************
 * 출근 등록 화면 호출 함수 
 ******************************************************** */
function fncCmtWrkStartInsert(){
	location.href = "<c:url value='/uss/cmt/EgovCmtWrkStartInsert.do' />";
	
}

/* ********************************************************
 * 퇴근 등록  화면 호출 함수 
 ******************************************************** */
function fncCmtWrkEndInsert(){
	location.href = "<c:url value='/uss/cmt/EgovCmtWrkEndInsert.do' />"; 
	
}
/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
function fncShowMessg(){
	if("<c:out value='${message}'/>" != ''){
	alert("<c:out value='${message}'/>");
	}
}

</script>
</head>
<body onLoad="fncShowMessg();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="listForm" action="<c:url value='/uss/cmt/EgovCmtManageList.do'/>" method="post">
<input type="hidden" name="searchCondition">
	<h1><spring:message code="ussCmt.cmtManageList.cmtManage"/></h1><!-- 출퇴근 관리 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectCmtManageList('1'); return false;" />
				<input class="s_btn" type="submit" value='<spring:message code="button.wrkstart" />' title='<spring:message code="button.wrkstart" />' onclick="fncCmtWrkStartInsert(); return false;" />
				<input class="s_btn" type="submit" value='<spring:message code="button.wrkend" />' title='<spring:message code="button.wrkend" />' onclick="fncCmtWrkEndInsert(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:20%" />
			<col style="width:20%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.wrktDt"/></th><!-- 일자 -->
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.emplyrId"/></th><!-- 사원ID -->
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.wrkStartTime"/></th><!-- 출근시간 -->
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.wrkEndTime"/></th><!-- 퇴근시간 -->
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.ovtmwrkHours"/></th><!-- 야근시간 -->
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.wrkStartStatus"/></th><!-- 출근상태 -->
			   <th scope="col"><spring:message code="ussCmt.cmtManageList.wrkEndStatus"/></th><!-- 퇴근상태 -->
			</tr>
		</thead>
		<tbody>
		<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="7">
				<spring:message code="common.nodata.msg" />
			</td>
		</tr>
		</c:if>
		<c:forEach var="cmtManage" items="${resultList}" varStatus="status">
		<tr>
			<td><c:out value="${fn:substring(cmtManage.wrktDt, 0, 4)}"/>-<c:out value="${fn:substring(cmtManage.wrktDt, 4, 6)}"/>-<c:out value="${fn:substring(cmtManage.wrktDt, 6, 8)}"/></td>
			<td><c:out value="${cmtManage.emplyrId}"/></td>
			<td><c:out value="${cmtManage.wrkStartTime}"/></td>
			<td><c:out value="${cmtManage.wrkEndTime}"/></td>  
			<td><c:out value="${cmtManage.ovtmwrkHours}"/></td>  
			<td><c:out value="${cmtManage.wrkStartStatus}"/></td>  
			<td><c:out value="${cmtManage.wrkEndStatus}"/></td> 
		</tr>
		</c:forEach> 
		</tbody>
	</table>

	<p style="padding-top:10px; color:#595959"><spring:message code="ussCmt.cmtManageList.wrkInfo"/></p><!-- ※ 1일 중복 클릭시 오류로 처리한다. -->
</div>
</form>
</body>
</html>
