<%
/**
 * @Class Name : EgovEventRceptManageList.java
 * @Description : EgovEventRceptManageList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2011.08.17    	정진오		선택입력사항을 표시하는 이미지 icon 경로가
 * 								다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함*
 * @ 2018.09.27    최 두 영         다국어처리
 *  @author 이      용
 *  @since 2010.07.20
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

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventRceptManageList.title"/></title><!-- 행사접수관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
 /*설명 : 행사접수목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				 = document.all["listForm"];
	 if(varForm.searchMonth.value !=""){
		 if(varForm.searchYear.value ==""){
			 alert("<spring:message code="comUssIonEvt.common.validate.searchYearValue"/>");/* 전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요 */
			 return;
		 } 
	 }
	varForm.searchCondition.value = "1";
	varForm.pageIndex.value = pageNo;
	varForm.action = "<c:url value='/uss/ion/evt/EgovEventRcrptManageList.do'/>";
	varForm.submit();
 }

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectEventRceptManageList(pageNo){
	 var varForm				 = document.all["listForm"];
	 if(varForm.searchMonth.value !=""){
		 if(varForm.searchYear.value ==""){
			 alert("<spring:message code="comUssIonEvt.common.validate.searchYearValue"/>");/* 전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요 */
			 return;
		 } 
	 }
	 varForm.pageIndex.value = pageNo;
	 varForm.action = "<c:url value='/uss/ion/evt/EgovEventRcrptManageList.do'/>";
	 varForm.submit();
 }

/* ********************************************************
 * 등록 화면 호출 함수 
 ******************************************************** */
function fncEventRceptRegist(eventId){
	var varForm				 = document.all["listForm"];
	varForm.eventId.value          = eventId;
	varForm.action           = "<c:url value='/uss/ion/evt/EgovEventRceptRegist.do'/>";
	varForm.submit();
}

/* ********************************************************
 * 상세회면 호출함수
 ******************************************************** */
function fncEventRceptManageDetail(eventId, applcntId){
	var varForm				 = document.all["listForm"];
	varForm.eventId.value    = eventId;
	varForm.applcntId.value  = applcntId;
	varForm.action           = "<c:url value='/uss/ion/evt/EgovEventRcrptDetail.do'/>";
	varForm.submit();
}

/* ********************************************************
 * 행사 상세회면 팝업 호출함수
 ******************************************************** */
function fncEventReqstDetailPop(eventId){
	var varForm				 = document.all["listFormPop"];
	var openParam            = "left=10, top=0, width=750, height=420";
	varForm.eventId.value    = eventId;
	varForm.cmd.value        = "popup";
	var myWin = window.open("about:blank","EventReqstDetailPop",openParam);
	
	varForm.method ="post";
	varForm.action = "<c:url value='/uss/ion/evt/EgovEventReqstDetail.do'/>";
	varForm.target ="EventReqstDetailPop";
	varForm.submit();
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonEvt.eventRceptManageList.title"/></h1><!-- 행사접수관리 목록 -->
	
<form name="listForm" action="<c:url value='/uss/ion/evt/selectEventRceptList.do'/>" method="post">
	
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="eventId">
	<input type="hidden" name="applcntId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty eventManageVO.pageIndex }">1</c:if><c:if test="${!empty eventManageVO.pageIndex }"><c:out value='${eventManageVO.pageIndex}'/></c:if>">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonEvt.common.searchSe"/> : </label><!-- 행사구분 -->
				<select name="searchSe" title="<spring:message code="comUssIonEvt.common.searchSe"/>"><!-- 행사구분 -->
		        	<option value="" <c:if test="${eventManageVO.searchSe eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
		            <c:forEach items="${eventSeCode}" var="result" varStatus="status">
			       	   <option value="<c:out value="${result.code }"/>" <c:if test="${eventManageVO.searchSe eq result.code }">selected</c:if>><c:out value="${result.codeNm }"/></option>
		            </c:forEach>
		      	</select>
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonEvt.common.eventYearMonth"/> : </label><!-- 행사년월 -->
				<select name="searchYear" title="<spring:message code="comUssIonEvt.common.searchYear"/>"><!-- 행사년 -->
					<option value="" <c:if test="${eventManageVO.searchYear eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
					<c:forEach items="${yearList}" var="result" varStatus="status">
					   <option value="<c:out value="${result }"/>"  <c:if test="${eventManageVO.searchYear eq result}">selected</c:if>><c:out value="${result }"/></option>
					</c:forEach>
				</select><spring:message code="comUssIonEvt.common.year"/><!-- 년 -->
		        <select name="searchMonth" title="<spring:message code="comUssIonEvt.common.searchMonth"/>"><!-- 행사월  -->
					<option value="" <c:if test="${eventManageVO.searchMonth eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
					<option value="01" <c:if test="${eventManageVO.searchMonth eq '01' }">selected</c:if>>01</option>
					<option value="02" <c:if test="${eventManageVO.searchMonth eq '02' }">selected</c:if>>02</option>
					<option value="03" <c:if test="${eventManageVO.searchMonth eq '03' }">selected</c:if>>03</option>
					<option value="04" <c:if test="${eventManageVO.searchMonth eq '04' }">selected</c:if>>04</option>
					<option value="05" <c:if test="${eventManageVO.searchMonth eq '05' }">selected</c:if>>05</option>
					<option value="06" <c:if test="${eventManageVO.searchMonth eq '06' }">selected</c:if>>06</option>
					<option value="07" <c:if test="${eventManageVO.searchMonth eq '07' }">selected</c:if>>07</option>
					<option value="08" <c:if test="${eventManageVO.searchMonth eq '08' }">selected</c:if>>08</option>
					<option value="09" <c:if test="${eventManageVO.searchMonth eq '09' }">selected</c:if>>09</option>
					<option value="10" <c:if test="${eventManageVO.searchMonth eq '10' }">selected</c:if>>10</option>
					<option value="11" <c:if test="${eventManageVO.searchMonth eq '11' }">selected</c:if>>11</option>
					<option value="12" <c:if test="${eventManageVO.searchMonth eq '12' }">selected</c:if>>12</option>
		      	</select><spring:message code="comUssIonEvt.common.month"/><!-- 월 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonEvt.common.eventNm"/> : </label><!-- 행사명 -->
				<input name="searchNm" type="text" size="20" value="${eventManageVO.searchNm}"  maxlength="100" title="<spring:message code="comUssIonEvt.common.eventNm"/>" /><!-- 행사명 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonEvt.eventRceptManageList.proccessCategory"/> : </label><!-- 진행구분 -->
				<select name="searchConfmAt" title="<spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt"/>"><!-- 승인구분 -->
			       	<option value=""  <c:if test="${eventManageVO.searchConfmAt eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
			       	<option value="NON" <c:if test="${eventManageVO.searchConfmAt eq 'NON' }">selected</c:if>><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.non"/></option><!-- 신청전 -->
			       	<option value="A" <c:if test="${eventManageVO.searchConfmAt eq 'A' }">selected</c:if>><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.a"/></option><!-- 신청중 -->
			       	<option value="C" <c:if test="${eventManageVO.searchConfmAt eq 'C' }">selected</c:if>><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.c"/></option><!-- 승인 -->
			       	<option value="R" <c:if test="${eventManageVO.searchConfmAt eq 'R' }">selected</c:if>><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.r"/></option><!-- 반려 -->
		      	</select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectEventRceptManageList('1'); return false;" />
			</li>
		</ul>
	</div>
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="" />
			<col style="width:14%" />
			<col style="width:8%" />
			<col style="width:14%" />
			<col style="width:8%" />
			<col style="width:11%" />
			<col style="width:14%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventNm"/></th><!-- 행사명 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventPlace"/></th><!-- 행사장소 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventTemp3"/></th><!-- 행사구분 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventBeginDe.eventEndDe"/></th><!-- 행사일자 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventTemp1"/></th><!-- 기간 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.prcpPpl"/></th><!-- 참여/정원 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.rceptBeginDe.rceptEndDe"/></th><!-- 행사접수기간 -->
			   <th scope="col"><spring:message code="comUssIonEvt.eventRceptManageList.applyDetail"/></th><!-- 신청(상세) -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(eventManageVO.pageIndex - 1) * eventManageVO.pageSize + status.count}"/></td>
				<td><span class="link"><a href="<c:url value='/uss/ion/evt/EgovEventReqstDetail.do?cmd=popup&'/>eventId=${resultInfo.eventId}" target="_blank" title="<spring:message code="comUssIonEvt.common.toNewWindow"/>" onclick="fncEventReqstDetailPop('<c:out value="${resultInfo.eventId}"/>'); return false;"><c:out value="${resultInfo.eventNm}"/></a></span></td><!-- 새창으로 -->
				<td><c:out value="${resultInfo.eventPlace}"/></td>
				<td><c:out value="${resultInfo.eventTemp3}"/></td>
				<td class="lt_textL" nowrap><c:out value="${resultInfo.eventBeginDe}"/> ~ <br><c:out value="${resultInfo.eventEndDe}"/></td>
				<td><c:out value="${resultInfo.eventTemp1}"/><spring:message code="comUssIonEvt.common.days"/></td><!-- 일간 -->
				<td><c:out value="${resultInfo.eventTemp2}"/>/<c:out value="${resultInfo.psncpa}"/></td>
				<td class="lt_textL" nowrap><c:out value="${resultInfo.rceptBeginDe}"/> ~ <br><c:out value="${resultInfo.rceptEndDe}"/></td>
				<td>
				<c:if test="${empty resultInfo.confmAt}"> 
			        <form name="item" method="post" action="<c:url value='/uss/ion/evt/EgovEventRceptRegist.do'/>">
			        	<input type="hidden" name="eventId" value="<c:out value="${resultInfo.eventId}"/>">
			            <input type="submit" value="<spring:message code="comUssIonEvt.eventRceptManageList.confmAt"/>" onclick="fncEventRceptRegist('<c:out value="${resultInfo.eventId}"/>'); return false;" style="padding:6px 10px 6px 10px; background-color:#4688d2; color:#fff; font-size:11px; border-radius:1px;"><!-- 신청 -->
			        </form>
		        </c:if>
		        <c:if test="${!empty resultInfo.confmAt}">
		        <form name="item" method="post" action="<c:url value='/uss/ion/evt/EgovEventRcrptDetail.do'/>">
		        	<input type="hidden" name="eventId"   value="<c:out value="${resultInfo.eventId}"/>">
		        	<input type="hidden" name="applcntId" value="<c:out value="${resultInfo.applcntId}"/>">
		            <input type="submit" value="<c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.a"/></c:if><c:if test="${resultInfo.confmAt eq 'C'}"><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.c"/></c:if><c:if test="${resultInfo.confmAt eq 'R'}"><spring:message code="comUssIonEvt.eventRceptManageList.searchConfmAt.r"/></c:if>" onclick="fncEventRceptManageDetail('<c:out value="${resultInfo.eventId}"/>','<c:out value="${resultInfo.applcntId}"/>'); return false;" style="padding:6px 10px 6px 10px; background-color:#4688d2; color:#fff; font-size:11px; border-radius:1px;"><!-- 신청중  승인 반려 -->
		        </form>
		        </c:if>
				</td>
			</tr>    
			</c:forEach>
			
			<c:if test="${fn:length(eventManageList) == 0}">
				<tr> 
					<td class="lt_text3" colspan="9">
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
