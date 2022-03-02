<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Random"%>
<%@ page import="org.egovframe.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import='egovframework.com.utl.fcc.service.EgovNumberUtil' %>
<%@ page import='egovframework.com.utl.fcc.service.EgovStringUtil' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
java.util.Calendar cal = java.util.Calendar.getInstance();
java.util.Calendar cBeginDate = java.util.Calendar.getInstance();
java.util.Calendar cEndDate = java.util.Calendar.getInstance();

String sImgUrl = "/images/egovframework/com/cmm/";
String sCssUrl = "/css/egovframework/com/cmm/";
//KISA 보안약점 조치 (2018-10-29, 윤창원)
String strYear = EgovStringUtil.removeMinusChar(request.getParameter("year"));
String strMonth = request.getParameter("month");

int year = cal.get(java.util.Calendar.YEAR);
int month = cal.get(java.util.Calendar.MONTH);
int date = cal.get(java.util.Calendar.DATE);

if(strYear != null && EgovNumberUtil.getNumberValidCheck(strYear))
{
  year = Integer.parseInt(strYear);
  month = Integer.parseInt(strMonth);
}else{

}
//년도,월 셋팅
cal.set(year, month, 1);

int startDay = cal.getMinimum(java.util.Calendar.DATE);
int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
int newLine = 0;

//현재년월일 셋팅
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
String sTodate = formatter.format(new java.util.Date());
%>
<!DOCTYPE html>
<html>
<head>
<HEAD>
	<TITLE><spring:message code="comCopSmtSdm.Gbn.Daily"/> <spring:message code="comCopSmtSdm.title"/></TITLE>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css'/>">
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cop/smt/sdm/dept_schdule_manage.css'/>">
	<script type="text/javaScript" language="javascript">

	var gOpener = parent.document.all? parent.document.deptSchdulManageVO : parent.document.getElementById("deptSchdulManageVO") ;
	/* ********************************************************
	* 주관 부서 팝업창열기
	******************************************************** */
	function fn_egov_regist_DeptSchdulManage(sDate){

		gOpener.schdulBgnde.value = sDate;
		gOpener.schdulEndde.value = sDate;
		gOpener.action = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageRegist.do' />";
		gOpener.submit();
	}


	/* ********************************************************
	* 주관 부서 팝업창열기
	******************************************************** */
	function fn_egov_detail_DeptSchdulManage(schdulId){

		gOpener.schdulId.value = schdulId;
		gOpener.action = "<c:url value='/cop/smt/sdm/EgovDeptSchdulManageDetail.do' />";
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
	function fnEgovSchdulSe(setValue) {

		location.href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageMonthList.do' />?year=<%=year%>&month=<%=month%>&searchCondition=SCHDUL_SE&searchKeyword=" + setValue;

	}

	function fnEgovSchdulTodate() {

		location.href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageMonthList.do' />?year=<%=sTodate.substring(0, 4)%>&month=<%=Integer.valueOf(sTodate.substring(4, 6))-1%>&searchCondition=SCHDUL_SE&searchKeyword=" + document.deptSchdulManageVO.schdulSe.value;

	}
	
	function fnEgovTodateSelect(){
		
		var obj = document.getElementById("id_<%=sTodate%>");
		//console.trace("fnEgovTodateSelect obj>"+obj);
		if(obj != null && obj != undefined){
			obj.setAttribute("style","background-color:#87CEFA;"); //opacity:0.5;
		}
		
	} 
	
	
	window.onload = function(){
		do_resize();
		fnEgovTodateSelect();
	}
	
	
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

		.divWeekBar {
		overflow:hidden;
		text-overflow:ellipsis;
		background-color: #FF69B4;  /* #8394c 파랑 #8B008B 자주  	#228B22초록	#FF69B4 핫핑크 		#FFD700 골드  	#B22222 나무색 */ 
		-moz-box-shadow: 2px 3px 2px grey;
		-webkit-box-shadow: 2px 3px 2px grey;
		box-shadow: 2px 3px 2px grey;
		}


		.linkWeek:link{color:#fff; font-size:9pt; font-family:"돋움"; font-weight: normal;}
		.linkWeek:visited{color:#fff; font-size:9pt; font-family:"돋움"; font-weight: normal;}
		.linkWeek:active{color:#fff; font-size:9pt; font-family:"돋움"; font-weight: normal;}
		.linkWeek:hover{color:#fff; font-size:9pt; font-family:"돋움"; font-weight: normal;}
		
		
		A:link { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
		A:visited { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
		A:active { font-size:9pt; font-family:"돋움";color:red; text-decoration:none; }
		A:hover { font-size:9pt; font-family:"돋움";color:red;text-decoration:none;}


	</style>
</HEAD>
<BODY style="border: 0px solid #dedede;">
<form name="deptSchdulManageVO" id="deptSchdulManageVO" action="" method="post">
<DIV id="content" style="width:712px;">

<div class="sort_area">
	<div class="select_group">
     	<select name="schdulSe" class="select" id="schdulSe" onChange="fnEgovSchdulSe(document.forms[0].schdulSe.options[document.forms[0].schdulSe.selectedIndex].value);" title="일정구분선택">
  		   <option selected value=''>-- <spring:message code="input.cSelect" /> --</option> <!-- 선택  -->
			<c:forEach var="result" items="${schdulSe}" varStatus="status">
				<option value='${result.code}' <c:if test="${searchKeyword == result.code}">selected</c:if>>${result.codeNm}</option>
			</c:forEach>
  		</select>
	</div>
	<div class="date_view" style="left:39%;">
		<ul>
			<li><a href="javascript:fnEgovSchdulTodate();" class="today"><spring:message code="comCopSmtSdm.Navi.Today"/></a></li><!-- 오늘 -->
			
			<li><a class="prev" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageMonthList.do' />?year=<%=year-1%>&amp;month=<%=month%>">prev</a></li>
			<li class="date"><%=year%> <spring:message code="comCopSmtSdm.Navi.Year"/></li><!-- 년 -->
			<li><a class="next" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageMonthList.do' />?year=<%=year+1%>&amp;month=<%=month%>">next</a></li>
			<li class="date">&nbsp;</li>
			
			<%if(month > 0 ){ %>
			<li><a href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageMonthList.do' />?year=<%=year%>&month=<%=month-1%>" title="<spring:message code="comCopSmtSdm.goPrevMonth"/>" class="prev">prev</a></li><!-- 이전달로 가기 -->
			<%}%>
			<li class="date"><%=month+1%> <spring:message code="comCopSmtSdm.Navi.Month"/></li><!-- 월 -->
			<%if(month < 11 ){ %>
			<li><a href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageMonthList.do' />?year=<%=year%>&month=<%=month+1%>" class="next" title="<spring:message code="comCopSmtSdm.goNextMonth"/>">next</a></li><!-- 다음달로 가기 -->
			<%}%>

		</ul>
	</div>

</div>

<table class="month">
	<caption><spring:message code="comCopSmtSdm.Gbn.Daily"/> <spring:message code="comCopSmtSdm.title"/></caption> <!-- 월간 부서일정관리 -->
	<colgroup>
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
		<col style="width: 100px;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="comCopSmtSdm.Monthly.sunday"/></th> <!-- 일 -->
			<th><spring:message code="comCopSmtSdm.Monthly.monday"/></th> <!-- 월 -->
			<th><spring:message code="comCopSmtSdm.Monthly.tuesday"/></th> <!-- 화 -->
			<th><spring:message code="comCopSmtSdm.Monthly.wednesday"/></th> <!-- 수 -->
			<th><spring:message code="comCopSmtSdm.Monthly.thursday"/></th> <!-- 목 -->
			<th><spring:message code="comCopSmtSdm.Monthly.friday"/></th> <!-- 금 -->
			<th><spring:message code="comCopSmtSdm.Monthly.saturday"/></th> <!-- 토 -->
		</tr>
	</thead>
<TBODY>
<TR>
<%

List listResult = (List)request.getAttribute("resultList");
EgovMap egovMap = new EgovMap();
Random random = new Random();

int iBeginDate = 0;
int iBeginEnd = 0;
//게시물카운트
int iDayPreviewCount = 0;
int iDayCount = 0;
int iSchdulWeekFirest=0;

String[] arrWeekColorList={
	"#8394c" //파랑 
	,"#8B008B" // 자주
	,"#228B22" //초록
	,"#FF69B4" //핫핑크
	,"#FFD700" //골드
	,"#B22222" //나무색
	,"#00BFFF" //DeepSkyBlue 
	,"#9400D3" //DarkViolet 
	,"#00CED1" //DarkTurquoise 
	,"#2F4F4F" //DarkSlateGrey 
	,"#FF8C00" //DarkOrange 
	,"#BDB76B" //DarkKhaki 	 
};

String[] arrWeekColorList2={
		"Tomato"
		,"Orange"
		,"DodgerBlue"
		,"MediumSeaGreen"
		,"Gray"
		,"SlateBlue"
		,"Violet"
		,"LightGray"	
};

//처음 빈공란 표시
for(int index = 1; index < start ; index++ )
{
  out.println("<TD >&nbsp;</TD>");
  newLine++;
}

for(int index = 1; index <= endDay; index++)
{
	String color = "";
	iDayCount = 0;
	iDayPreviewCount = 0;

	if(newLine == 0){			color = "RED";
	}else if(newLine == 6){ 	color = "#529dbc";
	}else{						color = "BLACK"; };

	String sUseDate = Integer.toString(year);

	sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1);
	sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);

	int iUseDate = Integer.parseInt(sUseDate);

	//일별게시껀
	if(listResult != null){   
		for(int i=0;i < listResult.size(); i++){
			egovMap = (EgovMap)listResult.get(i);
			iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
			iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
			if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
				iDayPreviewCount++;
			}
		}
	}

	if(iDayPreviewCount > 4){
		out.println("<TD id='id_"+iUseDate+"' valign='top' align='left' height='"+(92+((iDayPreviewCount-4)*24))+"px' nowrap>");
	}else{
		out.println("<TD id='id_"+iUseDate+"' valign='top' align='left' height='92px' nowrap>");
	}

	out.println("<a href=\"JavaScript:fn_egov_regist_DeptSchdulManage('"+iUseDate+"');\"><font color='"+color+"'>"+index+"</font></a>");
	out.println("<BR>");

	if(listResult != null){   

		for(int i=0;i < listResult.size(); i++){
			egovMap = (EgovMap)listResult.get(i);

			iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
			iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
			
			int iBeginYear = Integer.parseInt(String.valueOf(iBeginDate).substring(0, 4));			
			int iBeginMonth = Integer.parseInt(String.valueOf(iBeginDate).substring(4, 6)) -1;
			int iBeginDay = Integer.parseInt(String.valueOf(iBeginDate).substring(6, 8));
			
			if(month > iBeginMonth){
				iBeginMonth = month;
				iBeginDay = 01;
			}			
				
			int iEndYear = Integer.parseInt(String.valueOf(iBeginEnd).substring(0, 4));
			int iEndMonth = Integer.parseInt(String.valueOf(iBeginEnd).substring(4, 6)) -1;
			int iEndDay = Integer.parseInt(String.valueOf(iBeginEnd).substring(6, 8));
			
			if(month < iEndMonth){
				iEndMonth = month;
				iEndDay = endDay; //해당 달의 마지막 날짜 계산
			}
			
			cBeginDate.set(iBeginYear, iBeginMonth, iBeginDay);
			cEndDate.set(iEndYear, iEndMonth, iEndDay);
						
			long remainingDays = (long)Math.ceil((float) (cEndDate.getTimeInMillis() - cBeginDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));
			
			
			//스캐줄연속갯수
			int iSchdulCurrent = 0;
			int iSchdulCurrentTotal = 0;
			if(egovMap.get("schdulCurrent") == null){				
				iSchdulCurrent = Integer.parseInt(String.valueOf(remainingDays)); //iBeginEnd-iBeginDate;
				iSchdulCurrentTotal = Integer.parseInt(String.valueOf(remainingDays)); //iBeginEnd-iBeginDate;
			}else{
				iSchdulCurrent = (Integer)egovMap.get("schdulCurrent");
			}
			//주의첫일정갯수
			if(egovMap.get("schdulWeekFirest") == null){
				iSchdulWeekFirest = 0;
			}else{
				iSchdulWeekFirest = (Integer)egovMap.get("schdulWeekFirest");
			}
			
			//if( >= iUseDate && iUseDate <= iBeginEnd){
			if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
				
				//이전 Dispaly 방식
				//out.print("<table><tr><td nowrap><div class='divDotText' style='width:92px;border:solid 0px;'><a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
				//out.print((String)egovMap.get("schdulNm"));
				//out.println("</a></div></td></tr></table>");

				//표준프레임워크 3.6 Display 방식
				//스케줄연속갯수 감소
				iSchdulCurrent--;
				egovMap.put("schdulCurrent", iSchdulCurrent);
				
				//주의첫일정갯수 증가
				if(newLine == 6){
				 	iSchdulWeekFirest = 0;
				}else if(newLine >= 0){ 	
					iSchdulWeekFirest++;
				}
				egovMap.put("schdulWeekFirest", iSchdulWeekFirest);
				
				int iDotTextWidth=0;
				if(iSchdulWeekFirest == 1 
						|| (iSchdulWeekFirest == 0 && iSchdulCurrent==iSchdulCurrentTotal-1)){ //첫 토요일 보정 처리
					
					if((iSchdulCurrent+1) < 6){
						iDotTextWidth = 100*(iSchdulCurrent+2);
					}else{
						iDotTextWidth = 100*(7-newLine);
					}
						
					if(egovMap.get("schdulWeekColor") == null){
						//egovMap.put("schdulWeekColor", arrWeekColorList[random.nextInt(11)]);
						egovMap.put("schdulWeekColor", arrWeekColorList2[iDayCount]);
					}
					
					out.print("<div class='divWeekBar' style='margin-top:"+(iDayCount*20)+"px;position:absolute;width:"+iDotTextWidth+"px;border:solid 0px;background-color:"+ egovMap.get("schdulWeekColor") +";'><a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\" class='linkWeek'>");
					out.print((String)egovMap.get("schdulNm"));
					out.println("</a></div>");
				}
			//일별스케줄갯수
			iDayCount++;
			}
		}
	}

	out.println("</TD>");
	newLine++;

	if(newLine == 7)
	{
	  out.println("</TR>");
	  out.println("<TR class='line'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></TR>");
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
</TR>

</TBODY>
</TABLE>
</DIV>
</form>
</BODY>
</HTML>
