<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="egovframework.com.uss.ion.bnt.service.BndtManageVO"%>
<%@ page import='egovframework.com.utl.fcc.service.EgovNumberUtil' %>
<%@ page import='egovframework.com.utl.fcc.service.EgovStringUtil' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovMtgPlaceManageList.java
 * @Description : EgovMtgPlaceManageList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이     용              최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
 * @ 2018.09.27    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.06.29
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */

 java.util.Calendar cal = java.util.Calendar.getInstance();
//KISA 보안약점 조치 (2018-10-29, 윤창원)
 String strYear  = EgovStringUtil.removeMinusChar(request.getParameter("year"));
 String strMonth = request.getParameter("month");
 
 int year  = cal.get(java.util.Calendar.YEAR);
 int month = cal.get(java.util.Calendar.MONTH);
 int date  = cal.get(java.util.Calendar.DATE);

 if(strYear != null && strMonth != null && EgovNumberUtil.getNumberValidCheck(strYear))
 {
   year  = Integer.parseInt(strYear);
   month = Integer.parseInt(strMonth);
 }else{

 }
  
 //년도/월 셋팅
 cal.set(year, month, 1);

 int startDay = cal.getMinimum(java.util.Calendar.DATE);
 int endDay   = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
 int start    = cal.get(java.util.Calendar.DAY_OF_WEEK);
 int newLine  = 0;

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtManageList.title"/></title><!-- 당직관리  목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
	/*설명 : 당직  목록 조회 */
	function fncSelectBndtManageList(){
	    document.location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>?year=<%=year%>&amp;month=<%=month%>";
	}

	/*설명 : 당직 상세조회 */
	function fncSelectBndtManage(bndtDe,bndtId) {
	    document.listForm.bndtId.value = bndtId;
	    document.listForm.bndtDe.value = bndtDe;
	    document.listForm.action = "<c:url value='/uss/ion/bnt/EgovBndtManageDetail.do'/>";
	    document.listForm.submit();
	}
	/*설명 : 당직 신규등록 화면 호출 */
	function fncInsertBndtManage(bndtDe) {
	    document.listForm.bndtDe.value = bndtDe;
	    document.listForm.action = "<c:url value='/uss/ion/bnt/EgovBndtManageRegist.do'/>";
	    document.listForm.submit();
	}


	/*설명 : 당직일지 등록 /상세 화면 호출 */
	function fncSelectBndtDiary(bndtDe,bndtId, cmd) {
	    document.listForm.cmd.value = cmd;
		document.listForm.bndtId.value = bndtId;
	    document.listForm.bndtDe.value = bndtDe;
	    document.listForm.action = "<c:url value='/uss/ion/bnt/selectBndtDiary.do'/>";
	    document.listForm.submit();
	}

	/*설명 : 당직 엑셀등록 PopUp 화면 호출	 */
	function fncBndtManageBnde() {

		var varForm	   = document.all["listForm"];
		var openParam  = "left=10, top=0, width=750, height=500, scrollbars=1";
		var myWin      = window.open("about:blank","winName",openParam);
		varForm.method = "post";
		varForm.action = "<c:url value='/uss/ion/bnt/EgovBndtManageListPop.do'/>";
		varForm.target = "winName";
		varForm.submit();

		}


-->
</script>
<style TYPE="text/css">
	body {
	scrollbar-face-color: #F6F6F6;
	scrollbar-highlight-color: #bbbbbb;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-shadow-color: #bbbbbb;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #bbbbbb;
	margin-left:"0px"; margin-right:"0px"; margin-top:"0px"; margin-bottom:"0px";
	}

	td {font-family: "돋움"; font-size: 9pt; color:#595959;}
	th {font-family: "돋움"; font-size: 9pt; color:#000000;}
	select {font-family: "돋움"; font-size: 9pt; color:#595959;}


	.divDotText {
	overflow:hidden;
	text-overflow:ellipsis;
	}

A:link { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
A:visited { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
A:active { font-size:9pt; font-family:"돋움";color:red; text-decoration:none; }
A:hover { font-size:9pt; font-family:"돋움";color:red;text-decoration:none;}


</style>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div id="border" style="width:712px">

	<h1 class="tit01 mt20 mb8"><spring:message code="comUssIonBnt.bndtManageList.title"/></h1><!-- 당직관리 목록 -->
	
	<!-- 검색영역 -->
	<div class="search_box2 mb10" style="text-align:center">
		<label for="schdulSe"><spring:message code="comUssIonBnt.common.schdulSe"/> </label><!-- 당직년월 -->
		
		<a href="#LINK" onclick="location.href='<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>?year=<%=year-1%>&amp;month=<%=month%>'" style="margin-left:20px; vertical-align:-4px"><img src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png'/>" alt="<spring:message code="comUssIonBnt.common.year.prev"/>" style="margin-right:4px;border:0px;"></a><!-- 연도 이전 -->
		<span class="t1"><%=year%><spring:message code="comUssIonBnt.common.year"/></span><!-- 년 -->
		<a href="#LINK" onclick="location.href='<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>?year=<%=year+1%>&amp;month=<%=month%>'" style="margin-right:20px; vertical-align:-4px"><img src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png'/>"  alt="<spring:message code="comUssIonBnt.common.year.next"/>" style="margin-left:4px;border:0px;"></a><!-- 연도 이후 -->
		
		<%if(month > 0 ){ %>
		<a href="#LINK" onclick="location.href='<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>?year=<%=year%>&amp;month=<%=month-1%>'" style="vertical-align:-4px"><img src="<c:url value='/images/egovframework/com/cmm/icon/icon_prev.png'/>" alt="<spring:message code="comUssIonBnt.common.month.prev"/>" style="margin-right:4px;"></a><!-- 월 이전 -->
		<%}%>
		<span class="t1"><%=month+1%><spring:message code="comUssIonBnt.common.month"/></span><!-- 월 -->
		<%if(month < 11 ){ %>
		<a href="#LINK" onclick="location.href='<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>?year=<%=year%>&amp;month=<%=month+1%>'" style="margin-right:20px; vertical-align:-4px"><img src="<c:url value='/images/egovframework/com/cmm/icon/icon_next.png'/>"  alt="<spring:message code="comUssIonBnt.common.month.next"/>" style="margin-left:4px;"></a><!-- 월 이후 -->
		<%}%>
		
		<a class="btn_01" href="<c:url value='/uss/ion/bnt/EgovBndtManageListPop.do'/>" onclick="fncBndtManageBnde(); return false;" target="_blank" title="<spring:message code="comUssIonBnt.common.toNewWindow"/>"><spring:message code="comUssIonBnt.bndtManageList.bndtExcelRegist"/></a><!-- 새 창으로 이동 --><!-- 당직엑셀등록 -->
	</div>

<!-- ********** 여기서 부터 본문 내용 *************** -->
<form name="listForm" id="listForm" action="#LINK" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonBnt.common.submit"/>" title="<spring:message code="comUssIonBnt.common.submit"/>"></div><!-- 전송 -->
<input type="hidden" name="cmd" >
<input type="hidden" name="bndtDe" value ="<c:out value='${bndtManageVO.bndtDe}'/>">
<input type="hidden" name="bndtId" value ="<c:out value='${bndtManageVO.bndtId  }'/>"/>

<DIV id="content" style="width:712px;">
<br>
<table class="schedule" border="0" cellspacing="1" cellpadding="1" summary="<spring:message code="comUssIonBnt.bndtManageList.bndtList"/>" ><!-- 당직목록 -->
<colgroup>
	<col style="width:100px" />
	<col style="width:100px" />
	<col style="width:100px" />
	<col style="width:100px" />
	<col style="width:100px" />
	<col style="width:100px" />
	<col style="width:100px" />
</colgroup>
<thead>
<tr>
	<td class="sun"><spring:message code="comUssIonBnt.common.sun"/></td><!-- 일 -->
	<td><spring:message code="comUssIonBnt.common.mon"/></td><!-- 월 -->
	<td><spring:message code="comUssIonBnt.common.tues"/></td><!-- 화 -->
	<td><spring:message code="comUssIonBnt.common.wed"/></td><!-- 수 -->
	<td><spring:message code="comUssIonBnt.common.thurs"/></td><!-- 목 -->
	<td><spring:message code="comUssIonBnt.common.fri"/></td><!-- 금 -->
	<td class="sat"><spring:message code="comUssIonBnt.common.sat"/></td><!-- 토 -->
</tr>
</thead>
<tbody>
<tr>
<%
List<BndtManageVO> listResult = (List)request.getAttribute("bndtManageList");
int iBndtDe    = 0;
int iBndtCnt   = 0;
int iDiaryCnt  = 0;
//처음 빈공란 표시
for(int index = 1; index < start ; index++ )
{
  out.println("<TD >&nbsp;</TD>");
  newLine++;
}

for(int index = 1; index <= endDay; index++)
{
	iBndtCnt   = 0;
	String color = "";

	if(newLine == 0){			color = "RED";
	}else if(newLine == 6){ 	color = "#529dbc";
	}else{						color = "BLACK"; };

	String sUseDate = Integer.toString(year);

	sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1);
	sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
	int iUseDate = Integer.parseInt(sUseDate);

	out.println("<TD valign='top' align='left' height='92px' bgcolor='#EFEFEF' nowrap>");
	out.println("<font color='"+color+"'><b>"+index+"</b></font>");
	out.println("<BR>");

	if(listResult != null ){
		for(int i=0;i < listResult.size(); i++){
			BndtManageVO egovMap = listResult.get(i);
			iBndtDe = Integer.parseInt(((String)egovMap.getBndtDe()).substring(0, 8));
			if(iUseDate ==  iBndtDe){
				out.print("<table><tr><td nowrap><div class='divDotText' style='width:92px;border:solid 0px;'>");
				%>
				<spring:message code='comUssIonBnt.common.watcher'/>
				<%
				out.print(":<a href='#LINK' onclick=\"fncSelectBndtManage('" + (String)egovMap.getBndtDe() + "','"+ (String)egovMap.getBndtId() +"')\">");
				out.print((String)egovMap.getBndtTemp1());
				out.println("</a></div></td></tr><tr><td>&nbsp;</td></tr>");
				iDiaryCnt = Integer.parseInt((String)egovMap.getBndtTemp2());

				if(iDiaryCnt > 0 ){
					out.print("<tr><td nowrap><div class='divDotText' style='width:92px;border:solid 0px;'><a href='#LINK' onclick=\"fncSelectBndtDiary('" + (String)egovMap.getBndtDe() + "','"+ (String)egovMap.getBndtId() +"','detail')\">");
					%>
					<spring:message code='comUssIonBnt.common.writeComplete'/><!-- 작성완료 -->
					<%
					out.println("</a></div></td></tr>");
				}else{
					out.print("<tr><td nowrap><div class='divDotText' style='width:92px;border:solid 0px;'>");
					%>
					<spring:message code="comUssIonBnt.common.diary"/>:<%-- 일지 --%>
					<%
					out.print("<a class='btn_02' href='#LINK' onclick=\"fncSelectBndtDiary('" + sUseDate + "','"+ (String)egovMap.getBndtId() +"','insert')\">");
					%>
					<spring:message code="comUssIonBnt.common.insert"/><!-- 등록 -->
					<%
					out.print("</a>");
					out.println("</div></td></tr>");
				}
				out.println("</table>");
				iBndtCnt++;
			}
		}
	}
	if(	iBndtCnt == 0){
		out.print("<table><tr><td nowrap>");
		%>
		 <spring:message code="comUssIonBnt.common.watcher"/> <!-- 당직자 -->
		<%
		out.print(":<a class='btn_02' href='#LINK' onclick=\"fncInsertBndtManage('" +sUseDate+ "')\">");
		%>
		<spring:message code="comUssIonBnt.common.insert"/><!-- 등록 -->
		<%
		out.print("</a>");
		out.println("</td></tr><tr><td>&nbsp;</td></tr>");
		out.print("<tr><td nowrap><div class='divDotText' style='width:92px;border:solid 0px;'>");
		%>
		<spring:message code="comUssIonBnt.common.diary"/>:<spring:message code="comUssIonBnt.common.yetInsert"/><!--  일지 : 미등록  -->
		<%
		out.println("</div></td></tr></table>");
	}

	out.println("</TD>");
	newLine++;

	if(newLine == 7)
	{
	  out.println("</TR>");
	  if(index <= endDay)
	  {
	    out.println("<TR>");
	  }
	  newLine=0;
	}
}
//마지막 공란 LOOP
while(newLine > 0 && newLine < 7)
{
  out.println("<TD>&nbsp;</TD>");
  newLine++;
}
%>

</tr>
</tbody>
</table>
</DIV>
</form>
</div>
</BODY>
</HTML>
