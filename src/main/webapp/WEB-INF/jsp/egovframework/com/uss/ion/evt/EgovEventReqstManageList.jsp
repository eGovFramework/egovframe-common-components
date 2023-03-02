<%
/**
 * @Class Name : EgovEventReqstManageList.java
 * @Description : EgovEventReqstManageList jsp
 * @Modification Information
 * @
 * @  수정일           수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이     용                최초 생성
 * @ 2011.08.17    	정 진 오		선택입력사항을 표시하는 이미지 icon 경로가 다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함
 * @ 2018.08.13     최 두 영     3.8 퍼블리싱 점검 및 개선
 * @ 2018.09.19     최 두 영     다국어처리
 *
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
<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventReqstManageList.title"/></title><!-- 행사신청관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
 /*설명 : 행사 목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				  = document.all["listForm"];
	 if(varForm.searchMonth.value !=""){
		 if(varForm.searchYear.value ==""){
			 alert("<spring:message code="comUssIonEvt.common.validate.searchYearValue"/>");/* 전체연도에 월만 조회할 수 없습니다. 연도는 선택해주세요. */
			 return;
		 } 
	 }
	varForm.searchCondition.value = "1";
	varForm.pageIndex.value    = pageNo;
	varForm.action             = "<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>";
	varForm.target            ="_self";
	varForm.submit();
 }

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectEventManageList(pageNo){
	 var varForm				 = document.all["listForm"];
	 if(varForm.searchMonth.value !=""){
		 if(varForm.searchYear.value ==""){
			 alert("<spring:message code="comUssIonEvt.common.validate.searchYearValue"/>");/* 전체연도에 월만 조회할 수 없습니다. 연도는 선택해주세요. */
			 return;
		 } 
	 }
	 varForm.pageIndex.value = pageNo;
	 varForm.action = "<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>";
	 varForm.target           ="_self";
	 varForm.submit();
 }

/* ********************************************************
 * 등록 화면 호출 함수 
 ******************************************************** */
function fncEventReqstRegist(){
	location.href = "<c:url value='/uss/ion/evt/EgovEventReqstRegist.do'/>";
}

/* ********************************************************
 * 상세회면 호출함수
 ******************************************************** */
function fncEventManageDetail(eventId){
	var varForm				 = document.all["listForm"];
	varForm.eventId.value    = eventId;
	varForm.action           = "<c:url value='/uss/ion/evt/EgovEventReqstDetail.do'/>";
	varForm.target           ="_self";
	varForm.submit();
}

/* ********************************************************
* 참석자 목록 화면 호출함수
******************************************************** */
function fncEventReqstAtdrnList(eventId){
	var varForm				 = document.all["listForm"];
	var openParam = "left=10, top=0, width=750, height=350";
	varForm.eventId.value    = eventId;

	var myWin = window.open("about:blank","winName",openParam);
	varForm.method ="post";
	varForm.action = "<c:url value='/uss/ion/evt/EgovEventReqstAtdrnList.do'/>";
	varForm.target ="winName";
	varForm.submit();
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonEvt.eventReqstManageList.title"/></h1><!-- 행사신청관리 목록 -->
	
	<form name="listForm" action="<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>" method="post">

	<input type="hidden" name="searchCondition">
	<input type="hidden" name="eventId">
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
				<input name="searchNm" type="text" value="${eventManageVO.searchNm}"  maxlength="100" title="<spring:message code="comUssIonEvt.common.eventNm"/>" style="width:128px" /><!-- 행사명 -->		
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectEventManageList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/evt/EgovEventReqstRegist.do'/>?searchCondition=1" onclick="fncEventReqstRegist(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:14%" />
			<col style="width:10%%" />
			<col style="width:15%%" />
			<col style="width:14%" />
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
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(eventManageVO.pageIndex - 1) * eventManageVO.pageSize + status.count}"/></td>
				<td>
		        <form name="item" method="post" action="<c:url value='/uss/ion/evt/EgovEventReqstDetail.do'/>">
		        	<input type="hidden" name="eventId" value="<c:out value="${resultInfo.eventId}"/>">
		            <span class="link"><input type="submit" value="<c:out value="${resultInfo.eventNm}"/>" onclick="fncEventManageDetail('<c:out value="${resultInfo.eventId}"/>'); return false;" style="text-align : left;"></span>
		        </form>
				</td>
				<td><c:out value="${resultInfo.eventPlace}"/></td>
				<td><c:out value="${resultInfo.eventTemp3}"/></td>
				<td class="lt_textL" nowrap><c:out value="${resultInfo.eventBeginDe}"/> ~ <br><c:out value="${resultInfo.eventEndDe}"/></td>
				<td><c:out value="${resultInfo.eventTemp1}"/><spring:message code="comUssIonEvt.common.days"/></td><!-- 일간 -->
				<td>
		        <form name="item" method="post" action="<c:url value='/uss/ion/evt/EgovEventReqstAtdrnList.do'/>"  target="_blank">
		        	<input type=hidden name="eventId" value="<c:out value="${resultInfo.eventId      }"/>">
		            <span class="link"><input type="submit" value="<c:out value="${resultInfo.eventTemp2}"/>" onclick="fncEventReqstAtdrnList('<c:out value="${resultInfo.eventId}"/>'); return false;" style="width:20px; text-align:left;"></span> / <c:out value="${resultInfo.psncpa}"/>
		        </form></td>
				<td class="lt_textL" nowrap><c:out value="${resultInfo.rceptBeginDe}"/> ~ <br><c:out value="${resultInfo.rceptEndDe}"/></td>
			</tr>    
			</c:forEach>
			
			<c:if test="${fn:length(eventManageList) == 0}">
				<tr> 
					<td class="lt_text3" colspan="8">
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
