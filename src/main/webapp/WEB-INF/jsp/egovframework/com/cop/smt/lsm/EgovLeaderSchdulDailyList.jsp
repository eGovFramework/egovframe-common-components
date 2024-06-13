<%--
/**
 * @Class Name  : EgovLeaderSchdulDailyList.java
 * @Description : EgovLeaderSchdulDailyList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    장철호     최초 생성
  * @ 2018.09.14   최두영      다국어처리 & 퍼블리싱 점검
 *
 *  @author 장철호
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2010 by MOPAS  All rights reserved.
 */
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="egovframework.com.cop.smt.lsm.service.LeaderSchdulVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
    public String DateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}
		
       return sOutput;
    }
%>

<%

int iNowYear = (Integer)request.getAttribute("year");
int iNowMonth = (Integer)request.getAttribute("month");
int iNowDay = (Integer)request.getAttribute("day");


java.util.Calendar cal = java.util.Calendar.getInstance();
//연도/월 셋팅
cal.set(iNowYear, iNowMonth, 1);

int iEndDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
%> 
<!DOCTYPE html>

<%@page import="egovframework.com.utl.fcc.service.EgovDateUtil"%><html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtLsm.leaderSchdulDailyList.title" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">

	var gOpener = parent.document.all? parent.document.leaderSchdulVO : parent.document.getElementById("leaderSchdulVO") ;
	/* ********************************************************
	* 간부일정 등록페이지
	******************************************************** */
	function fn_egov_regist_LeaderSchdul(sDate){
	
		gOpener.schdulBgnDe.value = sDate;
		gOpener.schdulEndDe.value = sDate;
		gOpener.action = '<c:url value="/cop/smt/lsm/mng/addLeaderSchdul.do" />';
		gOpener.submit();
	}


	/* ********************************************************
	* 간부일정 상세보기
	******************************************************** */
	function fn_egov_detail_LeaderSchdul(schdulId, schdulDe){

		gOpener.schdulId.value = schdulId;
		gOpener.schdulDe.value = schdulDe;
		gOpener.year.value = "<%=iNowYear%>";
		gOpener.month.value = "<%=iNowMonth%>";
		gOpener.day.value = "<%=iNowDay%>";
		gOpener.searchMode.value = "DAILY";
		gOpener.action = '<c:url value="/cop/smt/lsm/usr/selectLeaderSchdul.do" />';
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
				
			if(ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
			{ifr.style.height = innerHeight;}

			/*
			if(!re) {
				try{ document.body.attachEvent('onclick',do_resize);
				}catch(e){document.body.addEventListener("click", do_resize, false);}
			}
			*/
		}
	}

	function press(event) {
		if (event.keyCode==13) {
			fnEgovSchdulSelect();
		}
	}
	
	function fnEgovSchdulSe(setValue) {

		location.href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&day=<%=iNowDay%>&searchCondition=SCHDUL_SE&searchKeyword=" + setValue;
	}

	function fnEgovSchdulSelect() {

		var newLocation = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&day=<%=iNowDay%>&searchCondition=LEADER_ID&searchKeyword=" + document.leaderSchdulVO.schdulSe.options[document.leaderSchdulVO.schdulSe.selectedIndex].value + "&searchKeywordEx=" + document.leaderSchdulVO.leaderName.value;

		document.leaderSchdulVO.action = newLocation;
		document.leaderSchdulVO.submit();
	}
	
	window.onload = function(){
		do_resize();
	}
</script>
</head>
<body> 
<form name="leaderSchdulVO" id="leaderSchdulVO" action="<c:url value='/cop/smt/lsm/usr/EgovLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&day=<%=iNowDay%>&searchCondition=SCHDUL_SE" method="post">
<div id="content" style="width:712px;">

	<!-- 검색영역 -->
	<div class="search_box2 mb10">
		<label for="schdulSe"><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulSe" /> : </label><!-- 일정구분 -->
     	<select id="schdulSe" name="schdulSe" title="일정구분선택">
			<option selected value=''>-- <spring:message code="table.select" /> --</option>
			<c:forEach var="result" items="${schdulSe}" varStatus="status">
			<option value='${result.code}' <c:if test="${searchKeyword == result.code}">selected</c:if>>${result.codeNm}</option>
			</c:forEach>			   			     		    		   
  		</select>
  		
  		<label for="leaderName"><spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" /> : </label><!-- 간부명 -->
		<input id="leaderName" type="text" name="leaderName" value="<c:out value='${searchKeywordEx}'/>" title="<spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" />" maxlength="10" onkeypress="press(event);" style="width:70px" />
		
		<input class="btns" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fnEgovSchdulSelect(); return false;" style="margin-right:60px" />
		
		<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear-1%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay%>" style="vertical-align:3px"><img alt="이전연도" src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png' />" /></a>
		<span class="t1"><%=iNowYear%><spring:message code="comCopSmtLsm.leaderSchdulMonthList.year" /></span>
		<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear+1%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay%>" style="margin-right:16px; vertical-align:3px"><img alt="다음연도" src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png' />" /></a>
		
		<%if(iNowMonth > 0 ){ %>
			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth-1%>&amp;day=<%=iNowDay%>" style="vertical-align:3px"><img alt="이전월" src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png' />" /></a>
		<%}%>
		<span class="t1"><%=iNowMonth+1%><spring:message code="comCopSmtLsm.leaderSchdulMonthList.month" /></span>
		<%if(iNowMonth < 11 ){ %>
			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth+1%>&amp;day=<%=iNowDay%>" style="margin-right:16px; vertical-align:3px"><img alt="다음월" src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png' />" /></a> 
		<%}%>
		
		<%if(iNowDay > 1 ){ %>
			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay-1%>" style="vertical-align:3px"><img alt="이전주" src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png' />" />
		</a>
		<%}%>
		<span class="t1"><%=iNowDay%></span>
		<%if(iNowDay < iEndDay ){ %>
			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay+1%>" style="margin-right:16px; vertical-align:3px"><img alt="다음주" src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png' />" /></a> 
		<%}%>
	</div>

	<table class="board_list" summary="<spring:message code="comCopSmtLsm.leaderSchdulDailyList.summary" />">	<!-- 이표는 일별 간부일정 목록을 제공하며, 시간, 일정명, 간부 정보로 구성되어 있습니다. -->
		<caption class="blind"><spring:message code="comCopSmtLsm.leaderSchdulDailyList.list" /></caption><!-- 일별 간부 일정 목록 -->
		<colgroup>
			<col style="width:240px" />
			<col style="" />
			<col style="width:100px" />
		</colgroup>
		<tr>
			<th scope="col"><spring:message code="comCopSmtLsm.leaderSchdulWeekList.time" /></th><!-- 시간 -->
			<th scope="col"><spring:message code="comCopSmtLsm.leaderSchdul.validate.schdulNm" /></th><!-- 일정명 -->
			<th scope="col"><spring:message code="comCopSmtLsm.leaderSchdul.validate.leaderName" /></th><!-- 간부 -->
		</tr>
<%

List<LeaderSchdulVO> listResult = (List<LeaderSchdulVO>)request.getAttribute("resultList");
LeaderSchdulVO leaderSchdulVO = new LeaderSchdulVO();
String sUseDate = String.valueOf(iNowYear);
String sMonth = String.valueOf(iNowMonth + 1);
if(sMonth.length() == 1) sMonth = "0" + sMonth;
String sDay = String.valueOf(iNowDay);
if(sDay.length() == 1) sDay = "0" + sDay;
sUseDate = sUseDate + sMonth + sDay;
if(listResult != null){
	for(int i=0;i < listResult.size(); i++){
		leaderSchdulVO = (LeaderSchdulVO)listResult.get(i);
%>
		<tr>  
		    <td>
		       	<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=<%=(String)leaderSchdulVO.getSchdulId()%>&schdulDe=<%=sUseDate %>&searchMode=DAILY&year=<%=iNowYear %>&month=<%=iNowMonth %>&day=<%=iNowDay %>" target="parent" onClick="JavaScript:fn_egov_detail_LeaderSchdul('<%=leaderSchdulVO.getSchdulId() %>','<%=sUseDate %>')"/>
<%
    out.print( ((String)leaderSchdulVO.getSchdulBgnDe()).substring(8,10) +": ");
	out.print( ((String)leaderSchdulVO.getSchdulBgnDe()).substring(10,12) +" ~ ");
	out.print( ((String)leaderSchdulVO.getSchdulEndDe()).substring(8,10) +": ");
	out.print( ((String)leaderSchdulVO.getSchdulEndDe()).substring(10,12));
%>
    			</a>
		    </td>
		    <td>
<%	
	String sSchdulBgnDate = (String)leaderSchdulVO.getSchdulBgnDe().substring(0, 8);
	String sSchdulEndDate = (String)leaderSchdulVO.getSchdulEndDe().substring(0, 8);
	
	if(!sSchdulBgnDate.equals(sSchdulEndDate)){
		//out.print("<table><tr><td nowrap><div class='divDotText' style='width:350px;border:solid 0px;'><a href=\"<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=" + (String)leaderSchdulVO.getSchdulId() + "&amp;schdulDe=" + sUseDate + "&amp;searchMode=DAILY&amp;year=" + iNowYear + "&amp;month=" + iNowMonth + "&amp;day=" + iNowDay + "\" target=\"_parent\" onClick=\"JavaScript:fn_egov_detail_LeaderSchdul('" + leaderSchdulVO.getSchdulId() + "','" + sUseDate + "')\">");
%>
				<table>
					<tr>
						<td nowrap>
							<div class='divDotText' style='width:350px; border:0;'>
								<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=<%=(String)leaderSchdulVO.getSchdulId()%>&schdulDe=<%=sUseDate %>&searchMode=DAILY&year=<%=iNowYear %>&month=<%=iNowMonth %>&day=<%=iNowDay %>" target="parent" onClick="JavaScript:fn_egov_detail_LeaderSchdul('<%=leaderSchdulVO.getSchdulId() %>','<%=sUseDate %>')"/>
<%
		out.print("[");
		out.print(EgovDateUtil.formatDate(sSchdulBgnDate, "-"));
		out.print(" ~ ");
		out.print(EgovDateUtil.formatDate(sSchdulEndDate, "-"));
		out.print("]");
%>
								</a>
							</div>
						</td>
					</tr>
				</table>
<%
		//out.println("</a></div></td></tr></table>");		
	}
%>
    			<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=<%=(String)leaderSchdulVO.getSchdulId()%>&schdulDe=<%=sUseDate %>&searchMode=DAILY&year=<%=iNowYear %>&month=<%=iNowMonth %>&day=<%=iNowDay %>" target="parent" onClick="JavaScript:fn_egov_detail_LeaderSchdul('<%=leaderSchdulVO.getSchdulId() %>','<%=sUseDate %>')"/>
<%
		//out.print("<a href=\"<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=" + (String)leaderSchdulVO.getSchdulId() + "&amp;schdulDe=" + sUseDate + "&amp;searchMode=DAILY&amp;year=" + iNowYear + "&amp;month=" + iNowMonth + "&amp;day=" + iNowDay + "\" target=\"_parent\" onClick=\"JavaScript:fn_egov_detail_LeaderSchdul('" + leaderSchdulVO.getSchdulId() + "','" + sUseDate + "')\">");
		out.print((String)leaderSchdulVO.getSchdulNm());
		//out.println("</a>");
%>
				</a> 
		    </td>
		    <td>
		    	<a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=<%=(String)leaderSchdulVO.getSchdulId()%>&schdulDe=<%=sUseDate %>&searchMode=DAILY&year=<%=iNowYear %>&month=<%=iNowMonth %>&day=<%=iNowDay %>" target="parent" onClick="JavaScript:fn_egov_detail_LeaderSchdul('<%=leaderSchdulVO.getSchdulId() %>','<%=sUseDate %>')"/>
<%
		//out.print("<a href=\"<c:url value='/cop/smt/lsm/usr/selectLeaderSchdul.do' />?schdulId=" + (String)leaderSchdulVO.getSchdulId() + "&amp;schdulDe=" + sUseDate + "&amp;searchMode=DAILY&amp;year=" + iNowYear + "&amp;month=" + iNowMonth + "&amp;day=" + iNowDay + "\" target=\"_parent\" onClick=\"JavaScript:fn_egov_detail_LeaderSchdul('" + leaderSchdulVO.getSchdulId() + "','" + sUseDate + "')\">");
		out.print((String)leaderSchdulVO.getLeaderName());
		//out.println("</a>");
%>
				</a> 
			</td>  
		</tr>
<%
	}
}
%>
<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td nowrap colspan="3" align="center"><spring:message code="common.nodata.msg" /></td>  
		</tr>		 
</c:if>
	</table>
</div>
<input type="submit" value="" style="display:none;">
</form>
</body>
</html>
