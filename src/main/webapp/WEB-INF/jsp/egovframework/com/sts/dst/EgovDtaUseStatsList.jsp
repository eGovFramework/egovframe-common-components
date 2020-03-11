<%--
/**
 * @Class Name  : EgovDtaUseStatsList.java
 * @Description : EgovDtaUseStatsList jsp
 * @Modification Information
 * @
 * @  수정일       수정자          수정내용
 * @ -------      --------    ---------------------------
 * @ 2009.08.01    lee.m.j         최초 생성
 * @ 2011.07.17    이기하          패키지 분리(sts -> sts.dst)
 *   2011.09.16    서준식          기간에 날짜가 없을때도 -가 표시되는 현상 수정
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
<c:set var="pageTitle"><spring:message code="comStsDst.dtaUseStats.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fn_egov_init_date(){
	
	$("#pmTyFromDate").datepicker(  
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


	$("#pmTyToDate").datepicker( 
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

function initDate() {

    var fromDate = '<c:out value="${pmDtaUseStats.pmFromDate}"/>';
    var toDate = '<c:out value="${pmDtaUseStats.pmToDate}"/>';

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

function fncSelectDtaUseStatsList(pageNo) {
	if(!checkDateTy()) return;
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sts/dst/selectDtaUseStatsList.do'/>";
    document.listForm.submit();
}

function fncSelectDtaUseStats(bbsId, nttId, atchFileId, fileSn) {
    document.listForm.bbsId.value = bbsId;
    document.listForm.nttId.value = nttId;
    document.listForm.atchFileId.value = atchFileId;
    document.listForm.fileSn.value = fileSn;
    document.listForm.action = "<c:url value='/sts/dst/getDtaUseStats.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
	if(!checkDateTy()) return;
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sts/dst/selectDtaUseStatsList.do'/>";
    document.listForm.submit();
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
    	fncSelectDtaUseStatsList('1');
    }
}
-->
</script>

</head>
<body onLoad="javascript:initDate();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>
	
	<form name="listForm" action="<c:url value='/sts/dst/selectDtaUseStatsList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<input type="hidden" name="pmFromDate" value="<c:out value="${dtaUseStatsVO.pmFromDate}"/>" >
		<input type="hidden" name="pmToDate" value="<c:out value="${dtaUseStatsVO.pmToDate}"/>" >
		<ul>
			<!-- 검색키워드 및 조회버튼 -->
			<li style="border:0">
				<label for=""><spring:message code="comStsDst.dtaUseStats.boardName"/> : </label> <!-- 게시판 명 -->
				<input type="text" id="searchKeyword" name="searchKeyword" size="10" maxlength="20" title="<spring:message code="comStsDst.dtaUseStats.boardName"/>" value="<c:out value="${dtaUseStatsVO.searchKeyword}"/>" > <!-- 게시판명 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comStsDst.dtaUseStats.periodKind"/> : </label> <!-- 기간구분 -->
				<select id="pmDateTy" class="searchCondition" name="pmDateTy" title="<spring:message code="comStsDst.dtaUseStats.periodKind"/>">
					<option value=""><spring:message code="comStsDst.dtaUseStats.select"/></option> <!-- 선택 -->
                    <c:forEach var="cmmCode042" items="${cmmCode042List}" varStatus="status">
                      <option value="<c:out value="${cmmCode042.code}"/>" <c:if test="${cmmCode042.code == dtaUseStatsVO.pmDateTy}">selected</c:if> ><c:out value="${cmmCode042.codeNm}"/></option>
                    </c:forEach>
				</select>
				
				<label for="" style="margin-left:10px"><spring:message code="comStsDst.dtaUseStats.period"/> : </label> <!-- 기간 -->
				<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
                  <input type="text" name="pmTyFromDate" size="10" title="<spring:message code="comStsDst.dtaUseStats.fromDate" />" id="pmTyFromDate" value="${dtaUseStatsVO.pmFromDate}"/> <!-- 시작일자 -->
                  <input type="text" name="pmTyToDate" size="10" tabindex="2" title="<spring:message code="comStsDst.dtaUseStats.toDate" />" id="pmTyToDate" value="${dtaUseStatsVO.pmToDate}"/> <!-- 종료일자 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fncSelectDtaUseStatsList('1'); return false;" />
			</li>
		</ul>
	</div>
	
<input type="hidden" name="bbsId" >
<input type="hidden" name="nttId" >
<input type="hidden" name="atchFileId" >
<input type="hidden" name="fileSn" >
<input type="hidden" name="pageIndex" value="<c:if test="${empty dtaUseStatsVO.pageIndex }">1</c:if><c:if test="${!empty dtaUseStatsVO.pageIndex }"><c:out value='${dtaUseStatsVO.pageIndex}'/></c:if>">
<input type="hidden" name="searchCondition" value="1">	
	</form>
	<table class="board_list">
		<caption><spring:message code="title.list"/></caption> <!-- 목록 -->
		<colgroup>
			<col style="width:" />
			<col style="width:" />
			<col style="width:" />
			<col style="width:" />
			<col style="width:" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.results.col1"/></th> <!-- 게시판명 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.results.col2"/></th> <!-- 글번호 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.results.col3"/></th> <!-- 글제목 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.results.col4"/></th> <!-- 첨부파일명 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.results.col5"/></th> <!-- 다운횟수 -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(dtaUseStatsList) == 0}">
				<tr>
				<td colspan="5">
					<spring:message code="common.nodata.msg" />
				</td>
				</tr>
			</c:if>
			<c:forEach var="dtaUseStats" items="${dtaUseStatsList}" varStatus="status">
			<tr>
				<td class="lt_text3" nowrap>
					<form name="item" method="post" action="<c:url value='/sts/dst/getDtaUseStats.do'/>">
						<input type="hidden" name="bbsId" value="<c:out value="${dtaUseStats.bbsId}"/>">
						<input type="hidden" name="nttId" value="<c:out value="${dtaUseStats.nttId}"/>">
						<input type="hidden" name="atchFileId" value="<c:out value="${dtaUseStats.atchFileId}"/>">
						<input type="hidden" name="fileSn" value="<c:out value='${dtaUseStats.fileSn}'/>">
						<input type="hidden" name="pmDateTy" value="<c:out value='${dtaUseStatsVO.pmDateTy}'/>">
						<input type="hidden" name="pmFromDate" value="<c:out value='${dtaUseStatsVO.pmFromDate}'/>">
						<input type="hidden" name="pmToDate" value="<c:out value='${dtaUseStatsVO.pmToDate}'/>">
						<input type="hidden" name="searchKeyword" value="<c:out value='${dtaUseStatsVO.searchKeyword}'/>">
						<input type="hidden" name="pageIndex" value="<c:out value='${dtaUseStatsVO.pageIndex}'/>">
						<span class="link"><input type="submit" value="<c:out value="${dtaUseStats.bbsNm}"/>" onclick="fncSelectDtaUseStats('<c:out value="${dtaUseStats.bbsId}"/>', '<c:out value="${dtaUseStats.nttId}"/>', '<c:out value="${dtaUseStats.atchFileId}"/>', '<c:out value="${dtaUseStats.fileSn}"/>'); return false;"></span>
					</form>
				</td>
				<td class="lt_text3" nowrap><c:out value="${dtaUseStats.nttId}"/></td>
				<td class="lt_text3" nowrap><c:out value="${dtaUseStats.nttSj}"/></td>
				<td class="lt_text3" nowrap><c:out value="${dtaUseStats.fileNm}"/></td>
				<td class="lt_text3" nowrap><c:out value="${dtaUseStats.downCnt}"/></td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(dtaUseStatsList) == 0}">
			</c:if>
		</tbody>
	</table>
	
	<div class="pagination">
		<ul>
			<li class="current"><a onclick="return false;">1</a></li>
		</ul>
	</div>
</div>

<div class="board">
	
	<table width="100%" border="0" cellspacing="2" cellpadding="0">
	    <tr>
	      <td width="15%" class="title_left" colspan="2">
			<h3 class="tit02" style="margin:0 0 10px 0"><spring:message code="comStsDst.dtaUseStats.subTitle1"/></h3> <!-- 기간별 통계 결과 -->
		</td>
	    </tr>
	    <c:forEach var="dtaUseStatsBar" items="${dtaUseStatsBarList}" varStatus="status">
	    <tr>
	      <td class="lt_text3" width="10%"><c:out value="${dtaUseStatsBar.grpRegDate}"/></td>
	      <td width="90%"><img src="<c:url value='/images/egovframework/com/cmm/left_bg.gif'/>" width="<c:out value='${dtaUseStatsBar.grpCnt * dtaUseStatsVO.maxUnit}'/>" height="10" align="left" alt="">&nbsp;&nbsp;<c:out value="${dtaUseStatsBar.grpCnt}"/> <spring:message code="comStsDst.dtaUseStats.results.unit"/></td>
	    </tr>
	    </c:forEach>
	</table>
	
</div>




</body>
</html>
