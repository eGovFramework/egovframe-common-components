<%--
/**
 * @Class Name  : EgovLeaderSchdulMonthList.java
 * @Description : EgovLeaderSchdulMonthList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    장철호     최초 생성
 * @ 2018.09.14    최두영     다국어처리
 *
 *  @author 장철호
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2010 by MOPAS  All right reserved.
 */
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="egovframework.com.cop.smt.lsm.service.LeaderSchdulVO"%>
<%@ page import='egovframework.com.utl.fcc.service.EgovNumberUtil' %>
<%@ page import='egovframework.com.utl.fcc.service.EgovStringUtil' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
java.util.Calendar cal = java.util.Calendar.getInstance();
String sImgUrl = "/images/egovframework/com/cop/smt/lsm/";
String sCssUrl = "/css/egovframework/com/cop/smt/lsm/";

String strYear = EgovStringUtil.removeMinusChar(request.getParameter("year"));
String strMonth = request.getParameter("month");

int year = cal.get(java.util.Calendar.YEAR);
int month = cal.get(java.util.Calendar.MONTH);
int date = cal.get(java.util.Calendar.DATE);

if(strYear != null && !strYear.equals("") && EgovNumberUtil.getNumberValidCheck(strYear))
{
  year = Integer.parseInt(strYear);
  month = Integer.parseInt(strMonth);
}else{
	
}
//연도/월 셋팅
cal.set(year, month, 1);

int startDay = cal.getMinimum(java.util.Calendar.DATE);
int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
int newLine = 0;

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtLsm.leaderSchdulMonthList.title" /></title><!-- 월별 간부일정 조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">

	var gOpener = parent.document.all? parent.document.leaderSchdulVO : parent.document.getElementById("leaderSchdulVO") ;
	/* ********************************************************
	* 간부일정 등록
	******************************************************** */
	function fn_egov_regist_LeaderSchdul(sDate){
	
		gOpener.schdulBgnDe.value = sDate;
		gOpener.schdulEndDe.value = sDate;
		gOpener.year.value = "<%=year%>";
		gOpener.month.value = "<%=month%>";
		gOpener.searchMode.value = "MONTH";
		gOpener.action = '<c:url value="/cop/smt/lsm/mng/addLeaderSchdul.do"/>';
		gOpener.submit();
	}


	/* ********************************************************
	* 간부일정 상세보기
	******************************************************** */
	function fn_egov_detail_LeaderSchdul(schdulId, schdulDe){

		gOpener.schdulId.value = schdulId;
		gOpener.schdulDe.value = schdulDe;
		gOpener.year.value = "<%=year%>";
		gOpener.month.value = "<%=month%>";
		gOpener.searchMode.value = "MONTH";
		gOpener.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />";
		gOpener.submit();
	}

	
	var ifr= parent.document.all? parent.document.all.SchdulView : parent.document.getElementById("SchdulView") ;

	function do_resize() {
		resizeFrame(1);
	}

	//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
	function resizeFrame(re) {

		if(ifr){

			var innerHeight = document.body.scrollHeight + (document.body.offsetHeight - document.body.clientHeight);
				
			if(ifr.style.height != innerHeight)
			{ifr.style.height = innerHeight;}
		}
	}

	function press(event) {
		if (event.keyCode==13) {
			fnEgovSchdulSelect();
		}
	}
	
	function fnEgovSchdulSe(setValue) {

		location.href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />?year=<%=year%>&month=<%=month%>&searchCondition=SCHDUL_SE&searchKeyword=" + setValue;

	}

	function fnEgovSchdulSelect() {

		var schdulSe = document.leaderSchdulVO.schdulSe.options[document.leaderSchdulVO.schdulSe.selectedIndex].value;
		var leaderName = document.leaderSchdulVO.leaderName.value;

		var newLocation = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />?year=<%=year%>&month=<%=month%>&searchCondition=LEADER_ID&searchKeyword=";
		newLocation += schdulSe;
		newLocation += "&searchKeywordEx=";
		newLocation += leaderName;

		document.leaderSchdulVO.action = newLocation;
		document.leaderSchdulVO.submit();

	}
	
	window.onload = function(){
		do_resize();
	}
	
</script>
</head>
<body style="margin:0"> 
<form name="leaderSchdulVO" id="leaderSchdulVO" action="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />?year=<%=year%>&month=<%=month%>&searchCondition=LEADER_ID" method="post" target="_self">
	<div id="content" style="width:712px;">
	
		<!-- 검색영역 -->
		<div class="search_box2 mb10">
			<label for="schdulSe"><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulSe" /> : </label><!-- 일정구분 -->
	     	<select name="schdulSe" class="select" id="schdulSe" title="<spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulSe" /><spring:message code="table.select" />" style="margin-right:10px"><!-- 일정구분선택 -->
	  		   <option selected value=''>-- <spring:message code="table.select" /> --</option><!-- 선택 -->
				<c:forEach var="result" items="${schdulSe}" varStatus="status">
					<option value='${result.code}' <c:if test="${searchKeyword == result.code}">selected</c:if>>${result.codeNm}</option>
				</c:forEach>			   			     		    		   
	  		</select>
	  		
	  		<label for="leaderName"><spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" /> : </label><!-- 간부명 -->
			<input id="leaderName" type="text" name="leaderName" value="<c:out value='${searchKeywordEx}'/>" title="<spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" />" maxlength="10" onkeypress="press(event);" style="width:70px" /><!-- 간부명 -->
			
			<input class="btns" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fnEgovSchdulSelect(); return false;" style="margin-right:60px" /><!-- 조회 -->
			
			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do'/>?year=<%=year-1%>&amp;month=<%=month%>" style="vertical-align:-4px"><img alt="이전연도" src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png'/>" /></a>
			<span class="t1"><%=year%><spring:message code="comCopSmtLsm.leaderSchdulMonthList.year" /></span><!-- 년 -->
			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do'/>?year=<%=year+1%>&amp;month=<%=month%>" style="margin-right:16px; vertical-align:-4px"><img alt="다음연도" src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png'/>" /></a>
			
			
			
			<%if(month > 0 ){ %>
				<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do'/>?year=<%=year%>&amp;month=<%=month-1%>" style="vertical-align:-4px"><img alt="이전월" src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png'/>" /></a>
			<%}%>
			<span class="t1"><%=month+1%><spring:message code="comCopSmtLsm.leaderSchdulMonthList.month" /></span><!-- 월 -->
			<%if(month < 11 ){ %>
				<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do'/>?year=<%=year%>&amp;month=<%=month+1%>" style="vertical-align:-4px"><img alt="다음월" src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png'/>" /></a>
			<%}%>
		</div>
	
		<table class="calendar">
			<caption class="blind"><spring:message code="comCopSmtLsm.leaderSchdulMonthList.list" /></caption>
			<colgroup>
				<col style="" />
				<col style="width:101.5px" />
				<col style="width:101.5px" />
				<col style="width:101.5px" />
				<col style="width:101.5px" />
				<col style="width:101.5px" />
				<col style="width:101.5px" />
			</colgroup>
			<tr class="cap">
				<td class="sun"><spring:message code="comCopSmtLsm.leaderSchdulMonthList.sun" /></td><!-- 일 -->
				<td><spring:message code="comCopSmtLsm.leaderSchdulMonthList.mon" /></td><!-- 월 -->
				<td><spring:message code="comCopSmtLsm.leaderSchdulMonthList.tue" /></td><!-- 화 -->
				<td><spring:message code="comCopSmtLsm.leaderSchdulMonthList.wed" /></td><!-- 수 -->
				<td><spring:message code="comCopSmtLsm.leaderSchdulMonthList.thu" /></td><!-- 목 -->
				<td><spring:message code="comCopSmtLsm.leaderSchdulMonthList.fri" /></td><!-- 금 -->
				<td class="sat"><spring:message code="comCopSmtLsm.leaderSchdulMonthList.sat" /></td><!-- 토 -->
			</tr>
			<tr class="day">
<%

List<LeaderSchdulVO> listResult = (List<LeaderSchdulVO>)request.getAttribute("resultList");
LeaderSchdulVO leaderSchdulVO = new LeaderSchdulVO();
//처음 빈공란 표시
for(int index = 1; index < start ; index++ )
{
  out.println("<td >&nbsp;</td>");
  newLine++;
}

for(int index = 1; index <= endDay; index++)
{
	String color = "";
	
	if(newLine == 0){			color = "red";
	}else if(newLine == 6){ 	color = "#529dbc";
	}else{						color = "black"; };
	
	String sUseDate = Integer.toString(year);
	
	sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1); 
	sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
	
	int iUseDate = Integer.parseInt(sUseDate);

	out.println("<td>");
%>	
	<a href="<c:url value='/cop/smt/lsm/mng/addLeaderSchdul.do' />?schdulBgnDe=<%=iUseDate%>&amp;schdulEndDe=<%=iUseDate%>&amp;searchMode=MONTH&amp;year=<%=year%>&amp;month=<%=month%>" target="_parent" onclick="JavaScript:fn_egov_regist_LeaderSchdul('<%=iUseDate%>');"><font color="<%=color%>"><%=index%></font></a>
<%	
	out.println("<br />");
	
	if(listResult != null){

		for(int i=0;i < listResult.size(); i++){
			leaderSchdulVO = (LeaderSchdulVO)listResult.get(i);
			
			//2010.10.27 3차 보안 점검 조치 사항 반영
			String schdulId = (String)leaderSchdulVO.getSchdulId();
			if(schdulId != null){
				schdulId = schdulId.replaceAll("<","&lt;");
				schdulId = schdulId.replaceAll(">","&gt;");
			}else{
				schdulId = "";
			}
			
			int iSchdulDate = Integer.parseInt(((String)leaderSchdulVO.getSchdulDe()).substring(0, 8));

			if(iUseDate == iSchdulDate){
%>
				<table>
					<tr>
						<td>
							<div class='divDotText'>
								<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=<%=schdulId%>&amp;schdulDe=<%=iUseDate%>&amp;searchMode=MONTH&amp;year=<%=year%>&amp;month=<%=month%>" target="_parent" onClick="JavaScript:fn_egov_detail_LeaderSchdul('<%=schdulId %>', '<%=iUseDate%>')">
									<font style="font-weight : solid"><%=(String)leaderSchdulVO.getSchdulNm()%></font>
								</a>
							</div>
						</td>
					</tr>
				</table>
<%
			}
		}
	}

	out.println("</td>");
	newLine++;
	
	if(newLine == 7)
	{
	  out.println("</tr>");
	  if(index <= endDay)
	  {
	    out.println("<tr class='day'>");
	  }
	  newLine=0;
	}
}
//마지막 공란 LOOP
while(newLine > 0 && newLine < 7)
{
  out.println("<td>&nbsp;</td>");
  newLine++;
}
%>
			</tr> 
		</table>
	</div>
	<input type="submit" value="" style="display:none;">
</form>
</body>
</html>
