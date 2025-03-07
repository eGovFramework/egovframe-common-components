<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comStsCst.conectStats.title"/></c:set>
<%
 /**
  * @Class Name : EgovConectStats.jsp
  * @Description : 접속통계 조회 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.23    박지욱		최초 생성
  * @ 2011.07.17    이기하		패키지 분리(sts -> sts.cst)
  * @ 2024.10.29    권태성		개인별 통계의 데이터가 없는 경우 안내 메시지 추가
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.23
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
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript">
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

/* ********************************************************
 * 기간구분 변경
 ******************************************************** */
function fnChangePdKind(){
	var v_pdKind = document.getElementById("PD");
	document.listForm.pdKind.value = v_pdKind.options[v_pdKind.selectedIndex].value;
}
/* ********************************************************
 * 통계구분 변경
 ******************************************************** */
function fnChangeStsKind(){
	var v_statsKind = document.getElementById("STKIND");
	document.listForm.statsKind.value = v_statsKind.options[v_statsKind.selectedIndex].value;
}

/* ********************************************************
 * 개인별 검색어 변경
 ******************************************************** */
function fnChangePerson(){
	document.listForm.detailStatsKind.value = document.listForm.person.value;
}
/*********************************************************
 * 조회 처리
 *********************************************************/
function fnSearch(){
	var fromDate = document.listForm.fDate.value.replace(/-/gi,"");
	var toDate = document.listForm.tDate.value.replace(/-/gi,"");
	
	document.listForm.fromDate.value = fromDate;
	document.listForm.toDate.value = toDate;

	var pdKind = document.listForm.pdKind.value;
	var statsKind = document.listForm.statsKind.value;
	var detailStatsKind = document.listForm.detailStatsKind.value;

	if (fromDate == "") {
		alert("<spring:message code='comStsCst.validate.fromDateCheck' />");
		return;
	} else if (toDate == "") {
		alert("<spring:message code='comStsCst.validate.toDateCheck' />");
		return;
	} else if (pdKind == "") {
		alert("<spring:message code='comStsCst.validate.periodKindCheck' />");
		return;
	} else if (statsKind == "") {
		alert("<spring:message code='comStsCst.validate.statKindCheck' />");
		return;
	}

	document.listForm.action = "<c:url value='/sts/cst/selectConectStats.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInitAll(){

	// 시작일자, 종료일자
	if (document.listForm.fDate.value == "" && document.listForm.tDate.value == "") {
		var now = new Date();
	    var year= now.getFullYear();
	    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		var toDay = year + mon + day;
		toDay = year + "-" + mon + "-" + day;
		document.listForm.fDate.value = toDay;
		document.listForm.tDate.value = toDay;
	} else if (document.listForm.fDate.value != "" && document.listForm.tDate.value != "") {
		var fromDate = document.listForm.fDate.value;
		var toDate = document.listForm.tDate.value;
		document.listForm.fDate.value = fromDate.substring(0, 4) + "-" + fromDate.substring(5, 7) + "-" + fromDate.substring(8, 10);
		document.listForm.tDate.value = toDate.substring(0, 4) + "-" + toDate.substring(5, 7) + "-" + toDate.substring(8, 10);
	}

	// 기간구분
	var pdKind = document.listForm.pdKind.value;
	var v_pdKind = document.getElementById("PD");
	for(var i = 0; i < v_pdKind.options.length; i++) {
		if (pdKind == v_pdKind.options[i].value) {
			v_pdKind.selectedIndex = i;
		}
	}

	// 통계구분
	var statsKind = document.listForm.statsKind.value;
	var v_statsKind = document.getElementById("STKIND");
	for(var j = 0; j < v_statsKind.options.length; j++) {
		if (statsKind == v_statsKind.options[j].value) {
			v_statsKind.selectedIndex = j;
			fnChangeStsKind();
		}
	}
	
	fn_egov_init_date();
}

function getNextWeek(v,t){
	var str=new Array();
	var b=v.split("-");
	var c=new Date(b[0],b[1]-1,b[2]);
	var d=c.valueOf()+1000*60*60*24*t;
	var e=new Date(d);

	str[str.length]=e.getYear();
	str[str.length]=e.getMonth()+1;
	str[str.length]=e.getDate();
	return str.join("");
}
</script>
</head>
<body onLoad="javascript:fnInitAll();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>

	<form name="listForm" action="<c:url value='/sts/cst/selectConectStats.do'/>" method="post">
	    <input type="hidden" name="pdKind" value='<c:out value="${statsInfo.pdKind}"/>'/>
	    <input type="hidden" name="statsKind" value='<c:out value="${statsInfo.statsKind}"/>'/>
	    <input type="hidden" name="detailStatsKind" value=""/>

	<div class="search_box mb10" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for="">기간 : </label>
				<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
                  <input type="hidden" name="fromDate" value="${statsInfo.fromDate}" />
                  <input type="hidden" name="toDate" value="${statsInfo.toDate}" />
                  <input type="text" name="fDate" size="10" title="<spring:message code="comStsCst.conectStats.fromDate" />" id="fDate" value="${fDate}"/> <!-- 시작일자 -->
                  <input type="text" name="tDate" size="10" tabindex="2" title="<spring:message code="comStsCst.conectStats.toDate" />" id="tDate" value="${tDate}"/> <!-- 종료일자 -->
				
				<label for="" style="margin-left:5px"><spring:message code="comStsCst.conectStats.periodKind"/> : </label> <!-- 기간구분 -->
				<select id="PD" name="PD" class="select" onchange="fnChangePdKind();" title="<spring:message code="comStsCst.conectStats.periodKind"/>">
				    <option selected value=''><spring:message code="comStsCst.conectStats.select"/></option> <!-- 선택 -->
				    <option value='Y'><spring:message code="comStsCst.conectStats.byYear"/></option> <!-- 연도별 -->
				    <option value='M'><spring:message code="comStsCst.conectStats.byMonth"/></option> <!-- 월별 -->
				    <option value='D'><spring:message code="comStsCst.conectStats.byDay"/></option> <!-- 일별 -->
			      </select>
				
				<label for="" style="margin-left:5px"><spring:message code="comStsCst.conectStats.statKind"/> : </label> <!-- 통계구분 -->
				<select id="STKIND" name="STKIND" class="select" onchange="fnChangeStsKind();" title="<spring:message code="comStsCst.conectStats.statKind"/>">
				    <option selected value=''><spring:message code="comStsCst.conectStats.selectPlease"/></option>
				    <option value='SERVICE'><spring:message code="comStsCst.conectStats.statKind1"/></option> <!-- 서비스별 -->
				    <option value='PRSONAL'><spring:message code="comStsCst.conectStats.statKind2"/></option> <!-- 개인별 -->
			      </select>
			      <input name="person" type="text" size="10" value="" onchange="fnChangePerson();" title="" id="person"/>
				
				<input class="s_btn" type="submit" value="<spring:message code="button.search" />" title="<spring:message code="button.search" />" onclick="fnSearch(); return false;" /> <!-- 검색 -->
				<span class="btn_b"><a href="#noscript" onclick="fnInitAll(); return false;" title="<spring:message code="button.init" />"><spring:message code="button.init" /></a></span> <!-- 초기화 -->
			</li>
		</ul>
	</div>

	<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comStsCst.conectStats.statResult"/></h2>

	<!-- 서비스별 결과 -->
	<c:if test='${statsInfo.statsKind == "SERVICE" }'>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:70px" />
			<col style="" />
			<col style="width:45px" />
			<col style="width:45px" />
			<col style="width:45px" />
			<col style="width:45px" />
			<col style="width:45px" />
			<col style="width:45px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col1"/></th> <!-- 일자     -->
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col2"/></th> <!-- 메소드명 -->   
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col3"/></th> <!-- 생성     -->
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col4"/></th> <!-- 수정     -->
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col5"/></th> <!-- 조회     -->
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col6"/></th> <!-- 삭제     -->
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col7"/></th> <!-- 출력     -->
			   <th scope="col"><spring:message code="comStsCst.conectStats.results.col8"/></th> <!-- 에러     -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(conectStats) == 0}">
			<tr>
			<td colspan="8">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>

	        <c:forEach items="${conectStats}" var="resultInfo" varStatus="status">
		    <tr>
		      <td>${resultInfo.statsDate}</td>
		      <td class="lt_text6" nowrap>&nbsp;${resultInfo.conectMethod}</td>
		      <td>${resultInfo.creatCo}</td>
		      <td>${resultInfo.updtCo}</td>
		      <td>${resultInfo.inqireCo}</td>
		      <td>${resultInfo.deleteCo}</td>
		      <td>${resultInfo.outptCo}</td>
		      <td>${resultInfo.errorCo}</td>
		    </tr>
		    </c:forEach>
		</table>
	</c:if>

	<!-- 개인별 결과 -->
	<c:if test='${statsInfo.statsKind == "PRSONAL" }'>
	<h3><spring:message code="comStsCst.conectStats.subTitle1" /></h3> <!-- 그래프 (단위, 횟수) -->
	<table class="e001 mb10">
		<colgroup>
			<col style="width:14%" />
			<col style="" />
		</colgroup>
		        <c:forEach items="${conectStats}" var="resultInfo" varStatus="status">
			      <tr>
			        <td width="100" height="10" class="lt_text3" nowrap>${resultInfo.statsDate}</td>
			        <td width="560" height="10">
					  <img src="<c:url value='/images/egovframework/com/cmm/left_bg.gif' />" width="<c:out value='${resultInfo.statsCo * statsInfo.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;<spring:message code="comStsCst.conectStats.results.unit"/> <!-- 회 -->
					</td>
			      </tr>
			    </c:forEach>
	    <c:if test="${fn:length(userStatsList) == 0}">
	    <tr>
	    	<td>
	    		<spring:message code="ussIonLsi.loginScrinImageList.failInquire"/>
	    	</td>
	    </tr>
	    </c:if>
	</table>

	<h3><spring:message code="comStsCst.conectStats.subTitle2" /></h3> <!-- 텍스트 (단위, 횟수) -->
	<table class="e001">
		<colgroup>
			<col style="width:14%" />
			<col style="" />
		</colgroup>
		        <c:forEach items="${conectStats}" var="resultInfo" varStatus="status">
		        <tr>
		          <td>${resultInfo.statsDate} &nbsp;&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;<spring:message code="comStsCst.conectStats.results.unit"/></td> <!-- 회 -->
		        </tr>
		        </c:forEach>
		        <c:if test="${fn:length(userStatsList) == 0}">
			    <tr>
				    <td>
				    	<spring:message code="ussIonLsi.loginScrinImageList.failInquire"/>
				    </td>
			    </tr>
			    </c:if>
	</table>
	</c:if>

	</form>
	
</div>



</body>
</html>
