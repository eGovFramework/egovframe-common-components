<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Random"%>
<%@ page import="org.egovframe.rte.psl.dataaccess.util.EgovMap"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
Calendar calNow = Calendar.getInstance();
Calendar calBefore = Calendar.getInstance();
Calendar calNext = Calendar.getInstance();

String sImgUrl = "/images/egovframework/com/cop/smt/sdm/";
String sCssUrl = "/css/egovframework/com/cop/smt/sdm/";

int iNowYear = (Integer)request.getAttribute("year");
int iNowMonth = (Integer)request.getAttribute("month");
int iNowWeek = (Integer)request.getAttribute("week");

List listWeekGrop = (List)request.getAttribute("listWeekGrop");

for(int i=0; i < listWeekGrop.size(); i++){

	//out.println(listWeekGrop.get(i));
	//out.println("<br>");
}

ArrayList listWeek = new ArrayList();
listWeek = (ArrayList)listWeekGrop.get(iNowWeek);

//요일설정
String arrDateTitle[] = new String[7];

arrDateTitle[0] = "일요일";//일요일
arrDateTitle[1] = "월요일";//월요일
arrDateTitle[2] = "화요일";//화요일
arrDateTitle[3] = "수요일";//수요일
arrDateTitle[4] = "목요일";//목요일
arrDateTitle[5] = "금요일";//금요일
arrDateTitle[6] = "토요일";//토요일


//현재년월일 셋팅
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
String sTodate = formatter.format(new java.util.Date());
%>
<!DOCTYPE html>
<html>
<head>
<HEAD>
	<TITLE><spring:message code="comCopSmtSdm.Gbn.Weekly"/> <spring:message code="comCopSmtSdm.title"/></TITLE>
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
		location.href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&week=<%=iNowWeek%>&searchCondition=SCHDUL_SE&searchKeyword=" + setValue;
	}
	
	function fnEgovSchdulTodate() {
		location.href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?searchCondition=SCHDUL_SE&searchKeyword=" + document.deptSchdulManageVO.schdulSe.value;
	}
	
	window.onload = function(){
		do_resize();
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

		A:link { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
		A:visited { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
		A:active { font-size:9pt; font-family:"돋움";color:red; text-decoration:none; }
		A:hover { font-size:9pt; font-family:"돋움";color:red;text-decoration:none;}
	</style>
</HEAD>
<BODY>
<form name="deptSchdulManageVO" id="deptSchdulManageVO" action="" method="post">
<DIV id="content" style="width:712px;">

<!-- 날짜 네비게이션  -->
<div class="sort_area">
	<div class="select_group">
     	<select name="schdulSe" class="select" id="schdulSe" onChange="fnEgovSchdulSe(document.forms[0].schdulSe.options[document.forms[0].schdulSe.selectedIndex].value);" title="일정구분선택">
  		   <option selected value=''>-- <spring:message code="input.cSelect" /> --</option> <!-- 선택  -->
			<c:forEach var="result" items="${schdulSe}" varStatus="status">
				<option value='${result.code}' <c:if test="${searchKeyword == result.code}">selected</c:if>>${result.codeNm}</option>
			</c:forEach>
  		</select>
	</div>
	<div class="date_view">
		<ul>
			<li><a href="javascript:fnEgovSchdulTodate();" class="today"><spring:message code="comCopSmtSdm.Navi.Today"/></a></li><!-- 오늘 -->
			<li><a class="prev" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear-1%>&amp;month=<%=iNowMonth%>&amp;week=<%=iNowWeek%>">prev</a></li>
			<li class="date"><%=iNowYear%> <spring:message code="comCopSmtSdm.Navi.Year"/></li>
			<li><a class="next" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear+1%>&amp;month=<%=iNowMonth%>&amp;week=<%=iNowWeek%>">next</a></li>
			<li class="date">&nbsp;</li>
			<%if(iNowMonth > 0 ){ %>
			<li><a class="prev" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth-1%>&amp;week=<%=0%>">prev</a></li>
			<%}%>
			<li class="date"><%=iNowMonth+1%> <spring:message code="comCopSmtSdm.Navi.Month"/></li><!-- 월 -->
			<%if(iNowMonth < 11 ){ %>
			<li><a class="next" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth+1%>&amp;week=<%=0%>">next</a></li>
			<%}%>
			<li class="date">&nbsp;</li>
			<%if(iNowWeek > 0 ){ %>
			<li><a class="prev" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&week=<%=iNowWeek-1%>">prev</a></li>
			<%}%>
			<li class="date"><%=iNowWeek+1%> <spring:message code="comCopSmtSdm.Navi.Week"/> </li><!-- 주 -->
			<%if(iNowWeek < listWeekGrop.size()-1 ){ %>
			<li><a class="next" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageWeekList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&week=<%=iNowWeek+1%>">next</a></li>
			<%}%>

		</ul>
	</div>

</div>

<div class="week_calendar">
<table class="title">
<caption><spring:message code="comCopSmtSdm.Gbn.Weekly"/> <spring:message code="comCopSmtSdm.title"/></caption> <!-- 주간 부서일정관리 -->
<colgroup>
	<col style="width: 130px;">
	<col style="width: 170px;">
	<col style="width:;">
	<col style="width: 100px;">
</colgroup>
<thead>
<tr>
	<th><spring:message code="comCopSmtSdm.Weekly.date"/></th> <!-- 날짜 -->
	<th><spring:message code="comCopSmtSdm.Weekly.time"/></th> <!-- 시간 -->
	<th><spring:message code="comCopSmtSdm.Weekly.title"/></th> <!-- 제목 -->
	<th><spring:message code="comCopSmtSdm.Weekly.chargeName"/></th> <!-- 담당자 -->
</tr>
<tbody>
<%
List listResult = (List)request.getAttribute("resultList");
EgovMap egovMap = new EgovMap();

for(int i=0; i < listWeek.size(); i++){

	String sTmpDate = (String)listWeek.get(i);
	int iUseDate = Integer.parseInt(sTmpDate);

%>
  <tr>
    <td>
		    <%=sTmpDate.substring(0,4)%>년<!-- 년 --><%=sTmpDate.substring(4,6)%>월<!-- 월 --><%=sTmpDate.substring(6,8)%>일<!-- 일 -->  <%=arrDateTitle[i] %>
    </td>
    <td  valign="middle" height="50px">
    <%
    if(listResult != null){

	for(int j=0;j < listResult.size(); j++){
		egovMap = (EgovMap)listResult.get(j);
		int iBeginDate = Integer.parseInt( ((String)egovMap.get("schdulBgnde")).substring(0, 8) );
		int iBeginEnd = Integer.parseInt( ((String)egovMap.get("schdulEndde")).substring(0, 8) );

		if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
		out.print("<table><tr><td nowrap><div style='border:solid 0px;'><a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
		out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시");//시
		out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분<br/>~ ");//분
		out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시");//시
		out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");//분
		out.println("</a></div></td></tr></table>");
		}
	}
	}
    %>
    </td>
    <td style="text-align:left;padding-left:5px;">
    <%
    if(listResult != null){

	for(int j=0;j < listResult.size(); j++){
		egovMap = (EgovMap)listResult.get(j);
		int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
		int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
		if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
		out.print("<div style='border:solid 0px;'><a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
		out.print((String)egovMap.get("schdulNm"));
		out.println("</a></div>");
		}
	}
	}
    %>
    </td>
    <td>
	<%
    if(listResult != null){

	for(int j=0;j < listResult.size(); j++){
		egovMap = (EgovMap)listResult.get(j);
		int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
		int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
		if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
		out.print("<table><tr><td nowrap><div>");
		out.print((String)egovMap.get("schdulChargerName"));
		out.println("</div></td></tr></table>");
		}
	}
	}
    %>
    </td>
  </tr>
<TR class='line'>
<td></td>
<td></td>
<td></td>
<td></td>
</TR>
  
  <%
  }
  %>
</tbody>
</table>
</div>

</DIV>
</form>
</BODY>
</HTML>
