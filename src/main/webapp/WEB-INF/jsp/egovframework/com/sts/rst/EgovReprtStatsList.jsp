<%--
/**
 * @Class Name  : EgovReprtStatsList.java
 * @Description : EgovReprtStatsList jsp
 * @Modification Information
 * @
 * @  수정일       수정자          수정내용
 * @ -------      --------    ---------------------------
 * @ 2009.08.01    lee.m.j         최초 생성
 * @ 2011.07.17    이기하          패키지 분리(sts -> sts.rst)
 *
 *  @author lee.m.j
 *  @since 2009.08.17
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 --%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comStsRst.reprtStats.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fn_egov_init_date(){
	
	$("#fDate").datepicker(  
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


	$("#tDate").datepicker( 
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

function fncSelectReprtStatsList(pageNo) {
	if(!checkDateTy()) return;
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sts/rst/selectReprtStatsList.do'/>";
    document.listForm.submit();
}

function fncSelectReprtStats(reprtTy, reprtSttus) {
    document.listForm.reprtTy.value = reprtTy;
    document.listForm.reprtSttus.value = reprtSttus;
    document.listForm.action = "<c:url value='/sts/rst/getReprtStats.do'/>";
    document.listForm.submit();
}

function fncAddReprtStatsInsert() {
    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    }
    document.listForm.action = "<c:url value='/sts/rst/addViewReprtStats.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
	if(!checkDateTy()) return;
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sts/rst/selectReprtStatsList.do'/>";
    document.listForm.submit();
}

function initDate() {

    var fromDate = '<c:out value="${pmReprtStats.pmFromDate}"/>';
    var toDate = '<c:out value="${pmReprtStats.pmToDate}"/>';

    var pmFromDate = document.listForm.pmFromDate.value;
    var pmToDate = document.listForm.pmToDate.value;

    if(document.listForm.pmFromDate.value == null || document.listForm.pmFromDate.value == '') {
        document.listForm.pmFromDate.value = fromDate;
        document.listForm.pmToDate.value = toDate;

        document.listForm.pmTyFromDate.value = fromDate.substring(0,4) + '-' + fromDate.substring(4,6) + '-' + fromDate.substring(6,8);
        document.listForm.pmTyToDate.value = toDate.substring(0,4) + '-' + toDate.substring(4,6) + '-' + toDate.substring(6,8);
    } else {
    	document.listForm.pmTyFromDate.value = pmFromDate.substring(0,4) + '-' + pmFromDate.substring(4,6) + '-' + pmFromDate.substring(6,8);
        document.listForm.pmTyToDate.value = pmToDate.substring(0,4) + '-' + pmToDate.substring(4,6) + '-' + pmToDate.substring(6,8);
    }
    
    fn_egov_init_date();
}

function checkDateTy() {

	var fromDate = document.listForm.pmTyFromDate.value.replace(/-/gi,"");
	var toDate = document.listForm.pmTyToDate.value.replace(/-/gi,"");
	
	document.listForm.pmFromDate.value = fromDate;
	document.listForm.pmToDate.value = toDate;

	
	if(document.listForm.pmDateTy.value == '') {
        alert("기간구분을 선택하세요.");
		return false;
	} else if(fromDate > toDate) {
        alert("종료일자는 시작일자보다  이후날짜로 선택하세요.");
        return false;
    }
	else
		return true;
}

function press() {

    if (event.keyCode==13) {
    	fncSelectReprtStatsList('1');
    }
}
-->
</script>

</head>
<body onLoad="javascript:initDate();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>

<form name="listForm" action="<c:url value='/sts/rst/selectReprtStatsList.do'/>" method="post">
<input type="hidden" name="pmFromDate" value="<c:out value="${reprtStatsVO.pmFromDate}"/>" >
<input type="hidden" name="pmToDate" value="<c:out value="${reprtStatsVO.pmToDate}"/>" >

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comStsRst.reprtStats.statKind"/> : </label> <!-- 유형 -->
				<select name="pmReprtTy" title="<spring:message code="comStsRst.reprtStats.statKind"/>" id = "pmReprtTy">
                     <option value="00"><spring:message code="title.all"/></option> <!-- 전체 -->
                     <c:forEach var="cmmCode040" items="${cmmCode040List}" varStatus="status">
                         <option value="<c:out value="${cmmCode040.code}"/>" <c:if test="${cmmCode040.code == reprtStatsVO.pmReprtTy}">selected</c:if> ><c:out value="${cmmCode040.codeNm}"/></option>
                     </c:forEach>
                 </select>
				
				<label for="" style="margin-left:10px"><spring:message code="comStsRst.reprtStats.periodKind"/> : </label> <!-- 기간구분 -->
				<select name="pmDateTy" title="<spring:message code="comStsRst.reprtStats.periodKind"/>" id="pmDateTy"> <!-- 기간구분 -->
                    <option value=""><spring:message code="comStsRst.reprtStats.select"/></option> <!-- 선택 -->
                   <c:forEach var="cmmCode042" items="${cmmCode042List}" varStatus="status">
                     <option value="<c:out value="${cmmCode042.code}"/>" <c:if test="${cmmCode042.code == reprtStatsVO.pmDateTy}">selected</c:if> ><c:out value="${cmmCode042.codeNm}"/></option>
                   </c:forEach>
                 </select>
				
				<label for="" style="margin-left:10px"><spring:message code="comStsRst.reprtStats.period"/> : </label> <!-- 기간 -->
				<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
                  <input type="text" name="pmTyFromDate" size="11" readonly="readonly" title="<spring:message code="comStsRst.reprtStats.fromDate" />" id="fDate" value="${reprtStatsVO.pmFromDate}"/> <!-- 시작일자 -->
                  <input type="text" name="pmTyToDate" size="11" readonly="readonly" tabindex="2" title="<spring:message code="comStsRst.reprtStats.toDate" />" id="tDate" value="${reprtStatsVO.pmToDate}"/> <!-- 종료일자 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectReprtStatsList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/sts/rst/addViewReprtStats.do'/>?pageIndex=<c:out value='${reprtStatsVO.pageIndex}'/>&amp;pmReprtTy=<c:out value="${reprtStatsVO.pmReprtTy}"/>&amp;pmDateTy=<c:out value="${reprtStatsVO.pmDateTy}"/>&amp;pmFromDate=<c:out value="${reprtStatsVO.pmFromDate}"/>&amp;pmToDate=<c:out value="${reprtStatsVO.pmToDate}"/>" onclick="fncAddReprtStatsInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input type="hidden" name="reprtTy">
<input type="hidden" name="reprtSttus">
<input type="hidden" name="pageIndex" value="<c:if test="${empty reprtStatsVO.pageIndex }">1</c:if><c:if test="${!empty reprtStatsVO.pageIndex }"><c:out value='${reprtStatsVO.pageIndex}'/></c:if>">
<input type="hidden" name="searchCondition" value="1">	
</form>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:50%" />
			<col style="width:30%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comStsRst.reprtStats.results.col1"/></th> <!-- 보고서유형 -->
			   <th scope="col"><spring:message code="comStsRst.reprtStats.results.col2"/></th> <!-- 진행상태 -->
			   <th scope="col"><spring:message code="comStsRst.reprtStats.results.col3"/></th> <!-- 건수 -->
			</tr>
		</thead>
		<tbody>
			
			 <c:forEach var="reprtStats" items="${reprtStatsList}" varStatus="status">
			  <tr>
			    <td class="lt_text3" nowrap>
			        <form name="item" method="post" action="<c:url value='/sts/rst/getReprtStats.do'/>">
			            <input type="hidden" name="reprtTy" value="<c:out value="${reprtStats.reprtTy}"/>">
			            <input type="hidden" name="reprtSttus" value="<c:out value="${reprtStats.reprtSttus}"/>">
			            <input type="hidden" name="pmFromDate" value="<c:out value="${reprtStatsVO.pmFromDate}"/>">
			            <input type="hidden" name="pmToDate" value="<c:out value='${reprtStatsVO.pmToDate}'/>">
			            <input type="hidden" name="pmReprtTy" value="<c:out value="${reprtStatsVO.pmReprtTy}"/>">
			            <input type="hidden" name="pmDateTy" value="<c:out value='${reprtStatsVO.pmDateTy}'/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${reprtStatsVO.pageIndex}'/>">
			            <span class="link"><input type="submit" value="<c:out value="${reprtStats.reprtTyNm}"/>" onclick="fncSelectReprtStats('<c:out value="${reprtStats.reprtTy}"/>', '<c:out value="${reprtStats.reprtSttus}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td class="lt_text3" nowrap><c:out value="${reprtStats.reprtSttusNm}"/></td>
			    <td class="lt_text3" nowrap><c:out value="${reprtStats.cnt}"/></td>
			  </tr>
			  </c:forEach>
			  <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(reprtStatsList) == 0}">
				<tr>
				<td colspan="3">
					<spring:message code="common.nodata.msg" />
				</td>
				</tr>
				</c:if>
			  
		</tbody>
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	
	<c:if test="${!empty reprtStatsVO.pageIndex }">
	<div align="center">
	    <div>
	        <ui:pagination paginationInfo = "${paginationInfo}"
	            type="default"
	            jsFunction="linkPage"
	            />
	    </div>
	</div>
	</c:if>

	
	
	
	<h3 class="tit02" style="margin:0 0 10px 0"><spring:message code="comStsRst.reprtStats.subTitle1"/></h3> <!-- 기간별 통계 결과 -->
	<table class="e001 mb10">
		<colgroup>
			<col style="width:14%" />
			<col style="" />
		</colgroup>
	    <c:forEach var="reprtStatsBar" items="${reprtStatsBarList}" varStatus="status">
	    <tr>
	      <td><c:out value="${reprtStatsBar.grpRegDate}"/></td>
	      <td>
	      	<img src="<c:url value='/images/egovframework/com/cmm/left_bg.gif' />" width="<c:out value='${reprtStatsBar.grpCnt * reprtStatsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;<c:out value="${reprtStatsBar.grpCnt}" /> <spring:message code="comStsRst.reprtStats.results.unit"/> <!-- 개 -->
	      </td>
	    </tr>
	    </c:forEach>
	</table>
	
	<h3 class="tit02" style="margin:0 0 10px 0"><spring:message code="comStsRst.reprtStats.subTitle2"/></h3> <!-- 보고서유형별 통계 결과 -->
	<table class="e001 mb10">
		<colgroup>
			<col style="width:14%" />
			<col style="" />
		</colgroup>
	    <c:forEach var="reprtStatsByReprtTy" items="${reprtStatsByReprtTyList}" varStatus="status">
	    <tr>
	      <td><c:out value="${reprtStatsByReprtTy.grpReprtTyNm}"/></td>
	      <td>
	      	<img src="<c:url value='/images/egovframework/com/cmm/left_bg.gif' />" width="<c:out value='${reprtStatsByReprtTy.grpReprtTyCnt * reprtStatsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;<c:out value="${reprtStatsByReprtTy.grpReprtTyCnt}" /> <spring:message code="comStsRst.reprtStats.results.unit"/>
	      </td>
	    </tr>
	    </c:forEach>
	</table>
	
	<h3 class="tit02" style="margin:0 0 10px 0"><spring:message code="comStsRst.reprtStats.subTitle3"/></h3> <!-- 진행상태별 통계 결과 -->
	<table class="e001 mb10">
		<colgroup>
			<col style="width:14%" />
			<col style="" />
		</colgroup>
	    <c:forEach var="reprtStatsByReprtSttus" items="${reprtStatsByReprtSttusList}" varStatus="status">
	    <tr>
	      <td><c:out value="${reprtStatsByReprtSttus.grpReprtSttusNm}"/></td>
	      <td>
	      	<img src="<c:url value='/images/egovframework/com/cmm/left_bg.gif' />" width="<c:out value='${reprtStatsByReprtSttus.grpReprtSttusCnt * reprtStatsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;<c:out value="${reprtStatsByReprtSttus.grpReprtSttusCnt}" /> <spring:message code="comStsRst.reprtStats.results.unit"/>
	      </td>
	    </tr>
	    </c:forEach>
	</table>
	

</div>

</body>
</html>
