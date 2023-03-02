<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle"><spring:message code="comUtlSysDbm.dbMntrngLogList.title"/></c:set>
<%
/**
 * @Class Name : EgovDbMntrngLogList.jsp
 * @Description : DB서비스모니터링로그 목록조회
 * @Modification Information
 * @
 * @  수정일             수정자            수정내용
 * @ -------     --------   ---------------------------
 * @ 2010.08.07   김진만           최초 생성
 * @ 2018.08.03   신용호           fn_egov_remove_string을 replace function으로 삭제
 *
 *  @author 김진만
 *  @since 2010.08.07
 *  @version 1.0
 *  @see
 *
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js'/>" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

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
        for(i = 0; i < vForm.searchStartHour.options.length; i++) {
            if (vForm.searchStartHour.options[i].value == vForm.searchKeywordFrom.value.substring(8,10)) {
                vForm.searchStartHour.options[i].selected = true;
                break;
            }
        }
        for(i = 0; i < vForm.searchStartMin.options.length; i++) {
            if (vForm.searchStartMin.options[i].value == vForm.searchKeywordFrom.value.substring(10,12)) {
                vForm.searchStartMin.options[i].selected = true;
                break;
            }
        }
        // 조회종료일자
        vForm.searchEndDate.value = vForm.searchKeywordTo.value.substring(0,4) + '-' + vForm.searchKeywordTo.value.substring(4,6) + '-' + vForm.searchKeywordTo.value.substring(6,8);
        for(i = 0; i < vForm.searchEndHour.options.length; i++) {
            if (vForm.searchEndHour.options[i].value == vForm.searchKeywordTo.value.substring(8,10)) {
                vForm.searchEndHour.options[i].selected = true;
                break;
            }
        }
        for(i = 0; i < vForm.searchEndMin.options.length; i++) {
            if (vForm.searchEndMin.options[i].value == vForm.searchKeywordTo.value.substring(10,12)) {
                vForm.searchEndMin.options[i].selected = true;
                break;
            }
        }
    }
    
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
	
	vForm.searchKeyword.focus();
    
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
        fn_egov_get_db_mntrng_log_list('1');
    }
}

function fn_egov_get_db_mntrng_log_list(pageNo) {

    if (document.frm.searchKeyword.value != "") {
        if (document.frm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
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
    document.frm.searchKeywordFrom.value = startDate + document.frm.searchStartHour.value + document.frm.searchStartMin.value;
    document.frm.searchKeywordTo.value = endDate + document.frm.searchEndHour.value + document.frm.searchEndMin.value;

    /*
        검색조건 체크
    */

    if (document.frm.searchStartDate.value != "" || document.frm.searchEndDate.value != "")   {
        if (document.frm.searchStartDate.value == "") {
            alert("검색시작일자를 입력하세요");
            return ;
        }
        if (document.frm.searchEndDate.value == "") {
            alert("검색종료일자를 입력하세요");
            return ;
        }
        if(isDate(document.frm.searchStartDate.value, "조회시작일자") == false) {
            return;
        }

        if(isDate(document.frm.searchEndDate.value, "조회종료일자") == false) {
            return;
        }
        if(document.frm.searchKeywordFrom.value > document.frm.searchKeywordTo.value) {
            alert("검색종료시각가 검색시작시각보다 빠를수 없습니다.");
            return ;
        }
    }


    document.frm.pageIndex.value = pageNo;
    document.frm.action = "<c:url value='/utl/sys/dbm/getDbMntrngLogList.do'/>";
    document.frm.submit();
}

function fn_egov_get_db_mntrng_log(logId) {
    document.frm.logId.value = logId;
    document.frm.action = "<c:url value='/utl/sys/dbm/getDbMntrngLog.do'/>";
    document.frm.submit();
}

function fn_egov_select_db_mntrng(){
	document.frm.action = "<c:url value='/utl/sys/dbm/getDbMntrngList.do'/>";
    document.frm.submit();
}
</script>

</head>
<body onLoad="fn_egov_init();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>

    <form name="frm" id="frm" action="<c:url value='/utl/sys/dbm/getDbMntrngLogList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="검색조건구분">
					<option value=''>--<spring:message code="input.select" />--</option>
					<option value="0" <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comUtlSysDbm.dbMntrng.dataSourceName" /></option><!-- 데이타소스명 -->
					<option value="1" <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUtlSysDbm.dbMntrng.serverName" /></option><!-- 서버명 -->
					<option value="2" <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comUtlSysDbm.dbMntrng.managerName" /></option><!-- 관리자명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' onkeypress="press(event);" title="검색키워드" style="width:60px" />
				
				
				<label for=""><spring:message code="comUtlSysDbm.dbMntrng.period" /> : </label><!-- 기간 -->
	            <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
	            <input type="text" name="searchStartDate" id="searchStartDate" title="<spring:message code="comUtlSysDbm.dbMntrng.searchStartDate" />" style="width:70px; margin-right:-9px"><!-- 검색시작일자 -->
	            <select name="searchStartHour" id="searchStartHour" title="<spring:message code="comUtlSysDbm.dbMntrng.searchStartHour" />"><!-- 검색시작시간 -->
	                <option value=""><spring:message code="input.cSelect" /></option><!-- 선택 -->
	                <c:forEach var="h" begin="1" end="24" step="1">
	                <option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
	                </c:forEach>
	            </select>
	            :
	            <select name="searchStartMin" id="searchStartMin" title="<spring:message code="comUtlSysDbm.dbMntrng.searchStartMin" />"><!-- 검색시작분 -->
	                <option value=""><spring:message code="input.cSelect" /></option>
	                <c:forEach var="h" begin="0" end="59" step="1">
	                <option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
	                </c:forEach>
	            </select>
	        	~
	            <input type="text" name="searchEndDate" id="searchEndDate" title="<spring:message code="comUtlSysDbm.dbMntrng.searchEndDate" />" style="width:70px; margin-right:-9px"><!-- 검색종료일자 -->
	            <select name="searchEndHour" id="searchEndHour" title="<spring:message code="comUtlSysDbm.dbMntrng.searchEndHour" />"><!-- 검색종료시간 -->
	                <option value=""><spring:message code="input.cSelect" /></option>
	                <c:forEach var="h" begin="1" end="24" step="1">
	                <option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
	                </c:forEach>
	            </select>
	            :
	            <select name="searchEndMin" id="searchEndMin" title="<spring:message code="comUtlSysDbm.dbMntrng.searchEndMin" />"><!-- 검색종료분 -->
	                <option value=""><spring:message code="input.cSelect" /></option>
	                <c:forEach var="h" begin="0" end="59" step="1">
	                <option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
	                </c:forEach>
	            </select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_get_db_mntrng_log_list('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/dbm/getDbMntrngList.do'/>" onclick="fn_egov_select_db_mntrng(); return false;" title="<spring:message code="button.list" />"><spring:message code="button.list" /></a></span>
			</li>
		</ul>
	</div>
    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}' default="1"/>">
    <input name="searchKeywordFrom" type="hidden" value="<c:out value='${searchVO.searchKeywordFrom}'/>">
    <input name="searchKeywordTo" type="hidden" value="<c:out value='${searchVO.searchKeywordTo}'/>">
    <input name="logId" type="hidden" value="">
    </form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.dataSourceName" /></th><!-- 데이타소스명 -->
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.serverName" /></th><!-- 서버명 -->
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.dbms" /></th><!-- DBMS종류 -->
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.managerName" /></th><!-- 관리자명 -->
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.managerEmail" /></th><!-- 관리자이메일주소 -->
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.status" /></th><!-- 상태 -->
			   <th scope="col"><spring:message code="comUtlSysDbm.dbMntrng.monitoringDatetime" /></th><!-- 모니터링시각 -->
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
	         <%-- 데이터를 화면에 출력해준다 --%>
	        <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	          <tr>
                <td>
                    <form name="item" method="post" action="<c:url value='/utl/sys/dbm/getDbMntrngLog.do'/>">
                        <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
                        <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>">
                        <input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword}"/>">
                        <input type="hidden" name="logId" value="<c:out value="${resultInfo.logId}"/>">
                    <span class="link"><input type="submit" value="<c:out value="${resultInfo.dataSourcNm}"/>" onclick="fn_egov_get_db_mntrng_log('<c:out value="${resultInfo.logId}"/>'); return false;"  style="text-align : left;"></span>
                    </form>
                </td>
                <td>${resultInfo.serverNm}</td>
                <td class="lt_text3" nowrap>${resultInfo.dbmsKindNm}</td>
                <td>${resultInfo.mngrNm}</td>
                <td>${resultInfo.mngrEmailAddr}</td>
                <td>${resultInfo.mntrngSttusNm}</td>
                <td>${resultInfo.creatDt}</td>
	          </tr>
	        </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_get_db_mntrng_log_list"/>
		</ul>
	</div>
</div>

</body>
</html>