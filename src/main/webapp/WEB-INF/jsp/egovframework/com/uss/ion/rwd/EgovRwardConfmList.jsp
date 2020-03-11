<%
/**
 * @Class Name : EgovRwardConfmList.java
 * @Description : EgovRwardConfmList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
 * @ 2018.09.19    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonRwd.rwardConfmList.title"/></title><!-- 포상승인 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript">
<!--
	function initCalendar(){
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
 /*설명 : 휴가승인 목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				 = document.all["listForm"];
	 if(varForm.searchFromDate.value != ""){
	     if(varForm.searchFromDate.value > varForm.searchToDate.value){
	         alert("<spring:message code="comUssIonRwd.common.validate.searchFromDate"/>");/* 포상일자 검색조건의 시작일자가 종료일자보다  늦습니다. 포상일자를 확인하세요. */
	         return;
		 }
	 }else varForm.searchToDate.value = "";

	varForm.searchCondition.value = "1";
	varForm.pageIndex.value = pageNo;
	varForm.action = "<c:url value='/uss/ion/rwd/EgovRwardConfmList.do'/>";
	varForm.submit();
 }

/* ********************************************************
 * 조회 처리
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectRwardConfmList(){
	 var varForm				 = document.all["listForm"];
	 if(varForm.searchFromDate.value != ""){
	     if(varForm.searchFromDate.value > varForm.searchToDate.value){
	         alert("<spring:message code="comUssIonRwd.common.validate.searchFromDate"/>");/* 포상일자 검색조건의 시작일자가 종료일자보다  늦습니다. 포상일자를 확인하세요. */
	         return;
		 }
	 }else varForm.searchToDate.value = "";
	 varForm.action = "<c:url value='/uss/ion/rwd/EgovRwardConfmList.do'/>";
	 varForm.submit();
 }

/* ********************************************************
 * 승인처리회면 호출함수
 ******************************************************** */
function fncRwardManageConfm(rwardId,infrmlSanctnId){
	var varForm				   = document.all["listForm"];
	varForm.rwardId.value      = rwardId;
	varForm.infrmlSanctnId.value = infrmlSanctnId;
	varForm.action           = "<c:url value='/uss/ion/rwd/EgovRwardConfm.do'/>";
	varForm.submit();
}

-->
</script>
</head>
<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonRwd.rwardConfmList.rwardAgreeList"/></h1><!-- 포상승인관리 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
	
	<form name="listForm" action="<c:url value='/uss/ion/rwd/EgovRwardConfmList.do'/>" method="post">
	
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="rwardId">
    <input type="hidden" name="infrmlSanctnId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty rwardManageVO.pageIndex }">1</c:if><c:if test="${!empty rwardManageVO.pageIndex }"><c:out value='${rwardManageVO.pageIndex}'/></c:if>">
	
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonRwd.common.searchKeyword"/> : </label><!-- 포상구분 -->
				<select name="searchKeyword" title="<spring:message code="comUssIonRwd.common.searchKeyword"/>"><!-- 포상구분 -->
		        	<option value="" <c:if test="${rwardManageVO.searchKeyword eq '' }">selected</c:if>><spring:message code="comUssIonRwd.common.selectedAll"/></option><!-- 전체 -->
		            <c:forEach items="${rwardCodeList}" var="result" varStatus="status">
			       	   <option value="<c:out value="${result.code }"/>" <c:if test="${rwardManageVO.searchKeyword eq result.code }">selected</c:if>><c:out value="${result.codeNm }"/></option>
		            </c:forEach>
		      	</select>
				
				<label for=""><spring:message code="comUssIonRwd.common.rwardDate"/> : </label><!-- 포상일자 -->
				<c:if test="${!empty rwardManageVO.searchFromDate}">
					<c:set var="fromNow" value="${fn:substring(rwardManageVO.searchFromDate,0,4)}-${fn:substring(rwardManageVO.searchFromDate,4,6)}-${fn:substring(rwardManageVO.searchFromDate,6,8)}" />
				</c:if>
				<c:if test="${!empty rwardManageVO.searchToDate}">
					<c:set var="toNow" value="${fn:substring(rwardManageVO.searchToDate,0,4)}-${fn:substring(rwardManageVO.searchToDate,4,6)}-${fn:substring(rwardManageVO.searchToDate,6,8)}" />
				</c:if>
				
				<input name="searchFromDate"  id="searchFromDate" type="text" readonly="readonly"  maxlength="10" value="${fromNow}" title="<spring:message code="comUssIonRwd.common.searchFromDate"/>" style="width:68px; margin-right:-7px"  /><!-- 포상 시작일자 -->
				 ~
				<input name="searchToDate" id="searchToDate" type="text" readonly="readonly"  maxlength="10" value="${toNow}" title="<spring:message code="comUssIonRwd.common.searchToDate"/>" style="width:68px; margin-right:-7px" /><!-- 포상 종료일자 -->
				
				<label for=""><spring:message code="comUssIonRwd.common.searchNm"/> : </label><!-- 포상자 -->
				<input name="searchNm" type="text" value="${rwardManageVO.searchNm}"  maxlength="100" title="<spring:message code="comUssIonRwd.common.searchNm"/>" style="width:68px" /><!-- 포상자 -->
				
				<label for=""><spring:message code="comUssIonRwd.common.confmAt"/>  : </label><!-- 진행구분 -->
				<select name="searchConfmAt" title="<spring:message code="comUssIonRwd.common.confmAt"/>"><!-- 진행구분 -->
			       	<option value=""  <c:if test="${ctsnnManageVO.searchConfmAt eq '' }">selected</c:if>><spring:message code="comUssIonRwd.common.selectedAll"/></option><!-- 전체 -->
			       	<option value="A" <c:if test="${ctsnnManageVO.searchConfmAt eq 'A' }">selected</c:if>><spring:message code="comUssIonRwd.common.confmAt.A"/></option><!-- 신청중 -->
			       	<option value="C" <c:if test="${ctsnnManageVO.searchConfmAt eq 'C' }">selected</c:if>><spring:message code="comUssIonRwd.common.confmAt.C"/></option><!-- 승인 -->
			       	<option value="R" <c:if test="${ctsnnManageVO.searchConfmAt eq 'R' }">selected</c:if>><spring:message code="comUssIonRwd.common.confmAt.R"/></option><!-- 반려 -->
		      	</select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectRwardConfmList(); return false;" />
			</li>
		</ul>
		</form>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:10%" />
			<col style="" />
			<col style="width:13%" />
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comUssIonRwd.common.searchKeyword"/></th><!-- 포상구분 -->
			   <th scope="col"><spring:message code="comUssIonRwd.common.rwardNm"/></th><!-- 포상명 -->
			   <th scope="col"><spring:message code="comUssIonRwd.common.rwardDe"/></th><!-- 포상일자 -->
			   <th scope="col"><spring:message code="comUssIonRwd.common.rwardManNm"/></th><!-- 포상자명 -->
			   <th scope="col"><spring:message code="comUssIonRwd.common.orgnztNm"/></th><!-- 소속 -->
			   <th scope="col"><spring:message code="comUssIonRwd.common.confmAt"/></th><!-- 진행구분 -->
			   <th scope="col"><spring:message code="comUssIonRwd.rwardConfmList.rwardProgress"/></th><!-- 승인처리 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rwardManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(rwardManageVO.pageIndex - 1) * rwardManageVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.rwardCdNm    }"/></td>
				<td><c:out value="${resultInfo.rwardNm      }"/></td>
				<td><c:out value="${resultInfo.rwardDe      }"/></td>
				<td><c:out value="${resultInfo.rwardManNm   }"/></td>
				<td><c:out value="${resultInfo.orgnztNm}"/></td>
				<td>
		          <c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonRwd.common.confmAt.A"/></c:if><!-- 신청중 -->
		          <c:if test="${resultInfo.confmAt eq 'C'}"><spring:message code="comUssIonRwd.common.confmAt.C"/></c:if><!-- 승인 -->
		          <c:if test="${resultInfo.confmAt eq 'R'}"><spring:message code="comUssIonRwd.common.confmAt.R"/></c:if><!-- 반려 -->
				</td>
				<td>
				<form name="item" method="post" action="<c:url value='/uss/ion/rwd/EgovRwardConfm.do'/>">
		        	<input type="hidden" name="rwardId" value="<c:out value="${resultInfo.rwardId }"/>">
		            <input class="btn01" type="submit"
		                  value="<c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonRwd.rwardConfmList.rwardProgress"/></c:if><c:if test="${resultInfo.confmAt ne 'A'}"><spring:message code="comUssIonRwd.rwardConfmList.detailView"/> </c:if>"
		                  onclick="fncRwardManageConfm('<c:out value="${resultInfo.rwardId}"/>','<c:out value="${resultInfo.infrmlSanctnId}"/>'); return false;" style="padding:6px 10px 6px 10px; background-color:#4688d2; color:#fff; font-size:11px; border-radius:1px;"><!-- 승인처리  --><!-- 상세보기 -->
		        </form>
		        </td>
			</tr>
			</c:forEach>
		
			<c:if test="${fn:length(rwardManageList) == 0}">
				<tr>
					<td  colspan="8">
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
