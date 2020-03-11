<%
/**
 * @Class Name : EgovMtgPlaceResveRegist.java
 * @Description : EgovMtgPlaceResveRegist.jsp
 * @Modification Information 
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용                최초 생성
 * @ 2018.08.21    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.12    최 두 영           다국어처리 & datepicker 적용
 *
 *  @author 이      용
 *  @since 2010.06.29
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonMtg.mtgPlaceResveManageList.title" /></title><!-- 회의실예약관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
	function initCalendar(){
		$("#resveDeView").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'both'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});		
		$("#resveDeView").change(function() {
			$("#resveDe").val($(this).val().replace(/-/gi,""));
		});

	}

/*설명 : 회의실  예약목록 조회 */
function fncSelectMtgPlaceResveManageList(pageNo){
	document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>";
    document.listForm.submit();
    
}

/*설명 : 회의실 예약 화면 호출 */
function fncInsertMtgPlaceResve() {	
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	} 
    document.listForm.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>";
    document.listForm.submit(); 
}
-->
</script>
</head>
<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comUssIonMtg.mtgPlaceResveManageList.title" /></h1><!-- 회의실예약관리 목록 -->
	<form name="listForm" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>" method="post"> 
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonMtg.mtgPlaceResveManageList.meetingDate" /> : </label><!-- 회의일자 -->
				<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
				<input id="resveDe" name="resveDe" type="hidden" value="<c:out value='${mtgPlaceManageVO.resveDe}'/>"/>
				<input id="resveDeView" name="resveDeView" type="text" value="${mtgPlaceManageVO.resveDeView}" readonly="readonly" title="<spring:message code="comUssIonMtg.mtgPlaceResveManageList.meetingDate" />" style="width:80px; margin-right:-8px" />
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectMtgPlaceResveManageList('1'); return false;" />
			</li>
		</ul>
	</div>
	</form>
	<p class="mb5">※<spring:message code="comUssIonMtg.mtgPlaceResveManageList.interfaceSearch" /><br /><!-- 회의일자 변경시 조회 버튼 클릭하셔야 예약 리스트가 조회됩니다. -->
	※<spring:message code="comUssIonMtg.mtgPlaceResveManageList.interfaceDetail" /></p><!-- 회의실 예약은 회의실의 색이 없는 빈 시간을 클릭하시면 예약신청화면으로 이동합니다. (그래프 클릭시 상세화면 이동.) -->
	
	<table class="table-line" cellpadding="0" summary="<spring:message code="comUssIonMtg.mtgPlaceResveManageList.title" />" ><!-- 회의실 예약관리 목록 -->
	<caption class="blind"><spring:message code="comUssIonMtg.mtgPlaceResveManageList.title" /></caption>
	 <thead>
		<colgroup>
			<col style="width:200px" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
			<col style="" />
		</colgroup>
	  <tr>
	    <th class="title" scope="col">회의실명</th>
	    <th class="title" colspan="2" scope="col">08</th>
	    <th class="title" colspan="2" scope="col">09</th>
	    <th class="title" colspan="2" scope="col">10</th>
	    <th class="title" colspan="2" scope="col">11</th>
	    <th class="title" colspan="2" scope="col">12</th>
	    <th class="title" colspan="2" scope="col">13</th>
	    <th class="title" colspan="2" scope="col">14</th>
	    <th class="title" colspan="2" scope="col">15</th>
	    <th class="title" colspan="2" scope="col">16</th>
	    <th class="title" colspan="2" scope="col">17</th>
	    <th class="title" colspan="2" scope="col">18</th>
	    <th class="title" colspan="2" scope="col">19</th>
	    <th class="title" colspan="2" scope="col">20</th>
	  </tr>
	 </thead>    
	
	 <tbody>
	 <c:forEach var="mtgPlaceResveManage" items="${mtgPlaceManageList}" varStatus="status">
	  <tr>
	    <td class="al"><c:out value='${mtgPlaceResveManage.mtgPlaceNm}'/></td> 
	    
	    <c:if test="${mtgPlaceResveManage.resveTemp0800!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0800,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0800,20,fn:length(mtgPlaceResveManage.resveTemp0800))}'/>">
	            <input type="submit" value="" style="width:100%;" onclick="fncSelectMtgPlaceResveManage('<c:out value="${mtgPlaceResveManage.mtgPlaceId}"/>',
	                                                                                  '<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0830,0,20) }'/>','updt'); return false;"></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp0800=='0' }">
	    <td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId}'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="0800">
	            <input type="hidden" name="resveEndTm"   value="0800">
	            <input type="hidden" name="resveDe"    value="<c:out value='${mtgPlaceManageVO.resveDe}'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="   " style="width:100%; height:100%;" ></span>
	        </form>
	   </td>
	   </c:if>
	   
	    <c:if test="${mtgPlaceResveManage.resveTemp0830!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0830,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0830,20,fn:length(mtgPlaceResveManage.resveTemp0830))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp0830=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="0830">
	            <input type="hidden" name="resveEndTm"   value="0830">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp0900!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0900,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;" >
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0900,20,fn:length(mtgPlaceResveManage.resveTemp0900))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;"  ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp0900=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="0900">
	            <input type="hidden" name="resveEndTm"   value="0900">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp0930!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0930,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp0930,20,fn:length(mtgPlaceResveManage.resveTemp0930))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp0930=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="0930">
	            <input type="hidden" name="resveEndTm"   value="0930">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1000!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1000,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1000,20,fn:length(mtgPlaceResveManage.resveTemp1000))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1000=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1000">
	            <input type="hidden" name="resveEndTm"   value="1000">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1030!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1030,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1030,20,fn:length(mtgPlaceResveManage.resveTemp1030))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1030=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1030">
	            <input type="hidden" name="resveEndTm"   value="1030">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1100!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1100,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1100,20,fn:length(mtgPlaceResveManage.resveTemp1100))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1100=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1100">
	            <input type="hidden" name="resveEndTm"   value="1100">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1130!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1130,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1130,20,fn:length(mtgPlaceResveManage.resveTemp1130))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1130=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1130">
	            <input type="hidden" name="resveEndTm"   value="1130">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1200!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1200,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1200,20,fn:length(mtgPlaceResveManage.resveTemp1200))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1200=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1200">
	            <input type="hidden" name="resveEndTm"   value="1200">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1230!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1230,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1230,20,fn:length(mtgPlaceResveManage.resveTemp1230))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1230=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1230">
	            <input type="hidden" name="resveEndTm"   value="1230">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1300!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1300,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1300,20,fn:length(mtgPlaceResveManage.resveTemp1300))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1300=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1300">
	            <input type="hidden" name="resveEndTm"   value="1300">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1330!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1330,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1330,20,fn:length(mtgPlaceResveManage.resveTemp1330))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1330=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1330">
	            <input type="hidden" name="resveEndTm"   value="1330">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1400!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1400,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1400,20,fn:length(mtgPlaceResveManage.resveTemp1400))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1400=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1400">
	            <input type="hidden" name="resveEndTm"   value="1400">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1430!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1430,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1430,20,fn:length(mtgPlaceResveManage.resveTemp1430))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1430=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1430">
	            <input type="hidden" name="resveEndTm"   value="1430">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1500!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1500,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1500,20,fn:length(mtgPlaceResveManage.resveTemp1500))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1500=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1500">
	            <input type="hidden" name="resveEndTm"   value="1500">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1530!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1530,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1530,20,fn:length(mtgPlaceResveManage.resveTemp1530))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1530=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1530">
	            <input type="hidden" name="resveEndTm"   value="1530">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1600!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1600,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1600,20,fn:length(mtgPlaceResveManage.resveTemp1600))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1600=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1600">
	            <input type="hidden" name="resveEndTm"   value="1600">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1630!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1630,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1630,20,fn:length(mtgPlaceResveManage.resveTemp1630))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1630=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1630">
	            <input type="hidden" name="resveEndTm"   value="1630">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1700!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1700,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1700,20,fn:length(mtgPlaceResveManage.resveTemp1700))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1700=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1700">
	            <input type="hidden" name="resveEndTm"   value="1700">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1730!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1730,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1730,20,fn:length(mtgPlaceResveManage.resveTemp1730))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1730=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1730">
	            <input type="hidden" name="resveEndTm"   value="1730">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1800!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1800,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1800,20,fn:length(mtgPlaceResveManage.resveTemp1800))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1800=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1800">
	            <input type="hidden" name="resveEndTm"   value="1800">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1830!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1830,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1830,20,fn:length(mtgPlaceResveManage.resveTemp1830))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1830=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1830">
	            <input type="hidden" name="resveEndTm"   value="1830">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1900!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1900,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1900,20,fn:length(mtgPlaceResveManage.resveTemp1900))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1900=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1900">
	            <input type="hidden" name="resveEndTm"   value="1900">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1930!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1930,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp1930,20,fn:length(mtgPlaceResveManage.resveTemp1930))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp1930=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="1930">
	            <input type="hidden" name="resveEndTm"   value="1930">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp2000!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp2000,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp2000,20,fn:length(mtgPlaceResveManage.resveTemp2000))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp2000=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="2000">
	            <input type="hidden" name="resveEndTm"   value="2000">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp2030!='0' }">
	    <td   bgcolor="#431508"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>">
	            <input type="hidden" name="resveId"    value="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp2030,0,20) }'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="cmd" value="detail">
	            <span  class="link"  style="background:pink;">
	            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveManage.resveTemp2030,20,fn:length(mtgPlaceResveManage.resveTemp2030))}'/>">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></Acronym></span>
	        </form>
	    </td>
	    </c:if>
	    <c:if test="${mtgPlaceResveManage.resveTemp2030=='0' }"><td   bgcolor="#FFFFFF"  >
	        <form name="item" method="post" action="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>">
	            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveManage.mtgPlaceId      }'/>">
	            <input type="hidden" name="resveId"    value="">
	            <input type="hidden" name="cmd"        value="insert">
	            <input type="hidden" name="resveBeginTm" value="2030">
	            <input type="hidden" name="resveEndTm"   value="2030">
	            <input type="hidden" name="resveDe" value="<c:out value='${mtgPlaceManageVO.resveDe         }'/>">
	            <span  class="link"  style="background:#ffffff;">
	            <input type="submit" value="    " style="width:100%; height:100%;" ></span>
	        </form>
	    </td></c:if>
	  </tr>   
	 </c:forEach>
	 </tbody>  
	</table>
</div>

</body>
</html>