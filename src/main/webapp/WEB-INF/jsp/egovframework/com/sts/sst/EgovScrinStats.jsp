<!DOCTYPE html>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comStsSst.scrinStats.title"/></c:set>
<%
 /**
  * @Class Name : EgovScrinStats.jsp
  * @Description : 화면통계 조회 화면
  * @Modification Information
  * @
  * @ 수정일               수정자             수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.27   박지욱            최초 생성
  * @ 2011.07.17   이기하            패키지 분리(sts -> sts.sst)
  * @ 2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.27
  *  @version 1.0
  *  @see
  *
  */
%>

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
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
</script>
<script type="text/javascript" language="javascript1.2" src="<c:url value='/js/egovframework/com/sts/sst/treemenu.js' />" ></script>
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
/*********************************************************
 * 조회 처리
 *********************************************************/
function fnSearch(){
	var fromDate = document.listForm.fDate.value.replace(/-/gi,"");
	var toDate = document.listForm.tDate.value.replace(/-/gi,"");

	document.listForm.fromDate.value = fromDate;
	document.listForm.toDate.value = toDate;

	var pdKind = document.listForm.pdKind.value;

	if (fromDate == "") {
		alert("<spring:message code='comStsSst.validate.fromDateCheck' />");
		return;
	} else if (toDate == "") {
		alert("<spring:message code='comStsSst.validate.toDateCheck' />");
		return;
	} else if (pdKind == "") {
		alert("<spring:message code='comStsSst.validate.periodKindCheck' />");
		return;
	}

	document.listForm.action = "<c:url value='/sts/sst/selectScrinStats.do'/>";
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
		document.listForm.fromDate.value = toDay;
		document.listForm.toDate.value = toDay;
		toDay = year + "-" + mon + "-" + day;
		document.listForm.fDate.value = toDay;
		document.listForm.tDate.value = toDay;
	} else if (document.listForm.fDate.value != "" && document.listForm.tDate.value != "") {
		var fromDate = document.listForm.fromDate.value;
		var toDate = document.listForm.toDate.value;
		document.listForm.fDate.value = fromDate.substring(0, 4) + "-" + fromDate.substring(4, 6) + "-" + fromDate.substring(6, 8);
		document.listForm.tDate.value = toDate.substring(0, 4) + "-" + toDate.substring(4, 6) + "-" + toDate.substring(6, 8);
	}

	// 기간구분
	var pdKind = document.listForm.pdKind.value;
	var v_pdKind = document.getElementById("PD");
	for(var i = 0; i < v_pdKind.options.length; i++) {
		if (pdKind == v_pdKind.options[i].value) {
			v_pdKind.selectedIndex = i;
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
	<h1 class="mb5">${pageTitle}</h1>

	<h2 class="tit02" style="margin:0 0 5px 0">${pageTitle}</h2>
	
	<form name="listForm" action="<c:url value='/sts/sst/selectScrinStats.do'/>" method="post">
	    <input type="hidden" name="pdKind" value='<c:out value="${statsInfo.pdKind}"/>'/>
	    <input type="hidden" name="statsKind" value='<c:out value="${statsInfo.statsKind}"/>'/>
	    <input type="hidden" name="detailStatsKind" value=""/>
	    <input type="hidden" name="req_RetrunPath" value="/sym/mpm/EgovMenuList">
	<div class="search_box mb20" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for="">기간 : </label>
				<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
                  <input type="hidden" name="fromDate" value="<c:out value='${statsInfo.fromDate}'/>" />
                  <input type="hidden" name="toDate" value="<c:out value='${statsInfo.toDate}'/>" />
                  <input type="text" name="fDate" size="11" readonly="readonly" title="<spring:message code="comStsSst.scrinStats.fromDate" />" id="fDate" value="<c:out value='${statsInfo.fromDate}'/>"/> <!-- 시작일자 -->
                  <input type="text" name="tDate" size="11" readonly="readonly" tabindex="2" title="<spring:message code="comStsSst.scrinStats.toDate" />" id="tDate" value="<c:out value='${statsInfo.toDate}'/>"/> <!-- 종료일자 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comStsSst.scrinStats.periodKind"/> : </label> <!-- 기간구분 -->
				<select id="PD" name="PD" class="select" onchange="fnChangePdKind();" title="<spring:message code="comStsSst.scrinStats.periodKind"/>">
				    <option selected value=''><spring:message code="comStsSst.scrinStats.select"/></option> <!-- 선택 -->
				    <option value='Y'><spring:message code="comStsSst.scrinStats.byYear"/></option> <!-- 연도별 -->
				    <option value='M'><spring:message code="comStsSst.scrinStats.byMonth"/></option> <!-- 월별 -->
				    <option value='D'><spring:message code="comStsSst.scrinStats.byDay"/></option> <!-- 일별 -->
			      </select>
				
				<input class="s_btn" type="submit" value="<spring:message code="button.init" />" title="<spring:message code="button.init" />" onclick="fnInitAll(); return false;" /> <!-- 초기화 -->
			</li>
		</ul>
	</div>
	
	<table>
		<colgroup>
			<col style="width:400px" />
			<col style="" />
		</colgroup>
		<tr>
			<!-- 왼쪽 -->
			<td>
				<h2 class="tit02" style="margin:0 0 10px 0"><spring:message code="comStsSst.scrinStats.statResult"/></h2> <!-- 화면 통계 결과 -->
				
				<h3 style="margin-top:0"><spring:message code="comStsSst.scrinStats.subTitle1"/></h3> <!-- 그래프 (단위, 횟수) -->
				<!-- 막대그래프 시작 -->
			      <table class="e001 mb10">
			      	<colgroup>
				      	<col style="width:14%" />
						<col style="" />
					</colgroup>			        
			        <c:forEach items="${scrinStats}" var="resultInfo" varStatus="status">
				      <tr>
				        <td width="50" height="10" class="lt_text3" nowrap>${resultInfo.statsDate}</td>
				        <td width="350" height="10">
						  <img src="<c:url value='/images/egovframework/com/cmm/left_bg.gif' />" width="<c:out value='${resultInfo.statsCo * statsInfo.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;<spring:message code="comStsSst.scrinStats.results.unit"/> <!-- 회 -->
						</td>
				      </tr>
				    </c:forEach>
			      </table>
			      <!-- 막대그래프 끝 -->
				
				<h3><spring:message code="comStsSst.scrinStats.subTitle2"/></h3> <!-- 텍스트 (단위, 횟수) -->
				<!-- 테이블표 시작 -->
			      <table>
			        <c:forEach items="${scrinStats}" var="resultInfo" varStatus="status">
			        <tr>
			          <td>${resultInfo.statsDate} &nbsp;&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;<spring:message code="comStsSst.scrinStats.results.unit"/></td> <!-- 회 -->
			        </tr>
			        </c:forEach>
				    <c:if test="${fn:length(scrinStatsList) == 0}">
			   		<tr><td></td></tr>
			   		</c:if>
			      </table>
			      <!-- 테이블표 끝 -->
			</td>
			
			<!-- 오른쪽 -->
			<td>
		        <!-- 메뉴트리 시작 -->
			    <table width="300" cellpadding="8" class="table-search" border="0" summary="메뉴트리">
			      <tr>
				    <td class="title_left" valign="top">
				        <c:forEach var="result" items="${list_menulist}" varStatus="status" >
						<input type="hidden" name="tmp_menuNm" value="<c:out value='${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.menuNo}|${result.menuOrdr}|${result.menuNm}|${result.upperMenuId}|${result.menuDc}|${result.relateImagePath}|${result.relateImageNm}|${result.progrmFileNm}|'/>">
						</c:forEach>

						<div class="tree" style="position:absolute; top:220px">
						<script type="text/javaScript">
					    var chk_Object = true;
					    var chk_browse = "";
						if (eval(document.listForm.req_RetrunPath)=="[object]") chk_browse = "IE";
						if (eval(document.listForm.req_RetrunPath)=="[object NodeList]") chk_browse = "Fox";
						if (eval(document.listForm.req_RetrunPath)=="[object Collection]") chk_browse = "safai";

						var Tree = new Array;
						if(chk_browse=="IE"&&eval(document.listForm.tmp_menuNm)!="[object]"){
						   alert("<spring:message code='comStsSst.validate.menuCheck1'/>");
						   chk_Object = false;
						}
						if(chk_browse=="Fox"&&eval(document.listForm.tmp_menuNm)!="[object NodeList]"){
						   alert("<spring:message code='comStsSst.validate.menuCheck1'/>");
						   chk_Object = false;
						}
						if(chk_browse=="safai"&&eval(document.listForm.tmp_menuNm)!="[object Collection]"){
							   alert("<spring:message code='comStsSst.validate.menuCheck1'/>"); /* 메뉴 목록 데이타가 존재하지 않습니다. */
							   chk_Object = false;
						}
						if( chk_Object ){
							for (var j = 0; j < document.listForm.tmp_menuNm.length; j++) {
								Tree[j] = document.listForm.tmp_menuNm[j].value;
						    }
							createTree(Tree, false);
			            }else{
			                alert("<spring:message code='comStsSst.validate.menuCheck2'/>"); /* 메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요. */
			            }
						</script>
						</div>
				    </td>
				  </tr>
			    </table>
			    <!-- 메뉴트리 끝 -->
			</td>
		</tr>
	</table>
	
	

	</form>
</div>









</body>
</html>
