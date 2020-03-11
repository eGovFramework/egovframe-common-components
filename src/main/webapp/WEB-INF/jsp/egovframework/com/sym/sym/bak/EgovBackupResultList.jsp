<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
/**
 * @Class Name : EgovBackupResultList.jsp
 * @Description : 백업결과관리 목록조회
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ -------     --------    ---------------------------
 * @ 2010.09.07   김진만             최초 생성
 * @ 2018.08.03   신용호             fn_egov_remove_string을 replace function으로 삭제
 * @ 2018.08.20   신용호             표준프레임워크 v3.8 개선
 *
 *  @author 김진만
 *  @version 1.0
 *  @see
 *
 */
//  백업결과의 executBeginTime, executEndTime의 화면 표시용 임시 변수 ....
%>
<c:set var="tempDate" value=""/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymSymBak.backupResultList.title"/></title><!-- 백업결과관리 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">

function fn_egov_init_date(){
	
	$("#searchStartDate").datepicker(  
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

function fn_egov_init(){

	fn_egov_init_date();
	
    var vForm = document.frm;

    if (vForm.searchKeywordFrom.value == "") {
        // 조회일자에 현재날짜 세팅
        //vForm.searchStartDate.value = fn_egov_getToday();
        //vForm.searchEndDate.value = fn_egov_getToday();
        //vForm.searchEndHour.options[23].selected = true;
    } else {
        // 조회조건 지정된 것 설정하기.
        // 조회시작일자
        vForm.searchStartDate.value = vForm.searchKeywordFrom.value.substring(0,4) + '-' + vForm.searchKeywordFrom.value.substring(4,6) + '-' + vForm.searchKeywordFrom.value.substring(6,8);
        // 조회종료일자
        vForm.searchEndDate.value = vForm.searchKeywordTo.value.substring(0,4) + '-' + vForm.searchKeywordTo.value.substring(4,6) + '-' + vForm.searchKeywordTo.value.substring(6,8);
    }
}

/* ********************************************************
 * 현재날짜 가져오기
 ******************************************************** */
function fn_egov_getToday(){

    var today = new Date();
    var currentYear  = today.getYear();
    var currentMonth = (today.getMonth()+1).toString();
    var currentDay = today.getDate().toString();

    var currentToday = currentYear + "-" + fn_egov_getLpad(2,currentMonth) + "-" + fn_egov_getLpad(2,currentDay);

    return  currentToday;
}

/* ********************************************************
 * LPAD 처리
 ******************************************************** */
function fn_egov_getLpad(rtnSize, sourceStr)
{
    rtnStr = sourceStr;

    for( i= sourceStr.length; i<parseInt(rtnSize); i++)
        rtnStr =  "0" + rtnStr;

    return rtnStr;
}

function press(event) {
    if (event.keyCode==13) {
        fn_egov_get_list('1');
    }
}

function fn_egov_get_list(pageNo) {


    if (document.frm.searchKeyword.value != "") {
        if (document.frm.searchCondition.value == "") {
            alert("<spring:message code="comSymSymBak.backupResultList.validate.searchKeyword"/>"); //검색조건을 선택하세요.
            return;
        }
    }
    /* 폼전송 데이타 조립. */
    var startDate = "";
    var endDate = "";
    if (document.frm.searchStartDate.value != "")   {
        startDate = document.frm.searchStartDate.value;
        startDate = startDate.replace(/-/gi,"");
    }
    if (document.frm.searchEndDate.value != "")   {
        endDate = document.frm.searchEndDate.value;
        endDate = endDate.replace(/-/gi,"");
    }
    document.frm.searchKeywordFrom.value = startDate ;
    document.frm.searchKeywordTo.value = endDate ;

    /*
        검색조건 체크
    */

    if (document.frm.searchStartDate.value != "" || document.frm.searchEndDate.value != "")   {
        if (document.frm.searchStartDate.value == "") {
            alert("<spring:message code="comSymSymBak.backupResultList.validate.searchStartDate"/>"); //검색시작일자를 입력하세요.
            return ;
        }
        if (document.frm.searchEndDate.value == "") {
            alert("<spring:message code="comSymSymBak.backupResultList.validate.searchEndDate"/>"); //검색종료일자를 입력하세요.
            return ;
        }
        if(isDate(document.frm.searchStartDate.value, "<spring:message code="comSymSymBak.backupResultList.validate.searchStartDate.name"/>") == false) { //조회시작일자
            return;
        }

        if(isDate(document.frm.searchEndDate.value, "<spring:message code="comSymSymBak.backupResultList.validate.searchEndDate.name"/>") == false) { //조회종료일자
            return;
        }
        if(document.frm.searchKeywordFrom.value > document.frm.searchKeywordTo.value) {
            alert("<spring:message code="comSymSymBak.backupResultList.validate.searchKeywordFromTo"/>"); //검색종료시각이 검색시작시각보다 빠를수 없습니다.
            return ;
        }
    }

    document.frm.pageIndex.value = pageNo;
    document.frm.action = "<c:url value='/sym/sym/bak/getBackupResultList.do'/>";
    document.frm.submit();
}

function fn_egov_get_detail_view(backupResultId) {
    document.frm.backupResultId.value = backupResultId;
    document.frm.action = "<c:url value='/sym/sym/bak/getBackupResult.do'/>";
    document.frm.submit();
}
</script>

</head>
<body onLoad="fn_egov_init();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymSymBak.backupResultList.pageTop.title"/></h1><!-- 백업결과 목록 -->

	<form name="frm" id="frm" action="<c:url value='/sym/sym/bak/getBackupResultList.do'/>" method="post">
	
	<div class="search_box">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="comSymSymBak.backupResultList.searchCondition"/>"><!-- 검색조건구분 -->
					<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comSymSymBak.backupResultList.backupOpertNm"/></option><!-- 백업작업명 -->
					<option value="1" <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comSymSymBak.backupResultList.backupOpertId"/></option><!-- 백업작업ID -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' size="25" onkeypress="press(event);" title="<spring:message code="comSymSymBak.backupResultList.searchKeyword"/>" /><!-- 검색키워드 -->
				
				<label for=""><spring:message code="comSymSymBak.backupResultList.execStartDate"/> :</label><!-- 실행시작일자 -->
				<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
				<input type="text" id="searchStartDate" name="searchStartDate" size="10" title="<spring:message code="comSymSymBak.backupResultList.searchStartDate"/>" /><!-- 검색시작일자 -->
				~
				<input type="text" id="searchEndDate" name="searchEndDate" size="10" title="<spring:message code="comSymSymBak.backupResultList.searchEndDate"/>" /><!-- 검색종료일자 -->
				<select name=sttus class="select" title="<spring:message code="comSymSymBak.backupResultList.searchCondition"/>"><!-- 검색조건구분 -->
					<option value='00'><spring:message code="comSymSymBak.backupResultList.sttusAll"/></option><!-- 전체 -->
					<option value="01" <c:if test="${searchVO.sttus == '01'}">selected="selected"</c:if> ><spring:message code="comSymSymBak.backupResultList.sttusNormal"/></option><!-- 정상 -->
					<option value="02" <c:if test="${searchVO.sttus == '02'}">selected="selected"</c:if> ><spring:message code="comSymSymBak.backupResultList.sttusAbnormal"/></option><!-- 비정상 -->
					<option value="03" <c:if test="${searchVO.sttus == '03'}">selected="selected"</c:if> ><spring:message code="comSymSymBak.backupResultList.sttusPerforming"/></option><!-- 수행중 -->
				</select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="<spring:message code="title.inquire"/>" onclick="fn_egov_get_list('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}' default="1"/>">
    <input name="searchKeywordFrom" type="hidden" value="<c:out value='${searchVO.searchKeywordFrom}'/>">
    <input name="searchKeywordTo" type="hidden" value="<c:out value='${searchVO.searchKeywordTo}'/>">
    <input name="backupResultId" type="hidden" value="">
    </form>

	<table class="board_list">
		<caption><spring:message code="comSymSymBak.backupResultList.caption"/></caption><!-- 백업결과 목록 -->
		<colgroup>
			<col style="width:24%" />
			<col style="width:20%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:13%" />
			<col style="width:13%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymSymBak.backupResultList.backupResultId"/></th><!-- 백업결과ID -->
			   <th scope="col"><spring:message code="comSymSymBak.backupResultList.backupOpertId"/></th><!-- 백업작업ID -->
			   <th scope="col"><spring:message code="comSymSymBak.backupResultList.backupOpertNm"/></th><!-- 백업작업명 -->
			   <th scope="col"><spring:message code="comSymSymBak.backupResultList.sttusNm"/></th><!-- 상태 -->
			   <th scope="col"><spring:message code="comSymSymBak.backupResultList.executBeginTime"/></th><!-- 실행시작시각 -->
			   <th scope="col"><spring:message code="comSymSymBak.backupResultList.executEndTime"/></th><!-- 실행종료시각 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td>
					<form name="item" method="post" action="<c:url value='/sym/sym/bak/getBackupResult.do'/>">
						<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>">
						<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>">
						<input type="hidden" name="backupResultId" value="<c:out value='${resultInfo.backupResultId}'/>">
						<span class="link"><input type="submit" value="<c:out value='${resultInfo.backupResultId}'/>" onclick="fn_egov_get_detail_view('<c:out value="${resultInfo.backupResultId}"/>'); return false;"></span>
					</form>
				</td>
				<td>${resultInfo.backupOpertId}</td>
				<td class="lt_text6">${resultInfo.backupOpertNm}</td>
				<td>${resultInfo.sttusNm}</td>
				<td>
					<fmt:parseDate value="${resultInfo.executBeginTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
					<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:parseDate value="${resultInfo.executEndTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
					<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_get_list"/>
		</ul>
	</div>
</div>

</body>
</html>