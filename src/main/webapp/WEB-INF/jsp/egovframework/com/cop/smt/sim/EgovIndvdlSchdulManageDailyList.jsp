<%
 /**
  * @Class Name : EgovIndvdlSchdulManageDailyList.jsp
  * @Description : 일정관리 일간 목록
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09   장동한              최초 생성
  *   2016.10.27   장동한              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

String sImgUrl = "/images/egovframework/com/cmm/";
String sCssUrl = "/css/egovframework/com/cmm/";

int iNowYear = (Integer)request.getAttribute("year");
int iNowMonth = (Integer)request.getAttribute("month");
int iNowDay = (Integer)request.getAttribute("day");


java.util.Calendar cal = java.util.Calendar.getInstance();
//년도/월 셋팅
cal.set(iNowYear, iNowMonth, 1);

int iEndDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);


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

	var gOpener = parent.document.all? parent.document.IndvdlSchdulManageVO : parent.document.getElementById("IndvdlSchdulManageVO") ;
	/* ********************************************************
	* 주관 부서 팝업창열기
	******************************************************** */
	function fn_egov_regist_DeptSchdulManage(sDate){

		gOpener.schdulBgnde.value = sDate;
		gOpener.schdulEndde.value = sDate;
		gOpener.action = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageRegist.do' />";
		gOpener.submit();
	}


	/* ********************************************************
	* 주관 부서 팝업창열기
	******************************************************** */
	function fn_egov_detail_DeptSchdulManage(schdulId){

		gOpener.schdulId.value = schdulId;
		gOpener.action = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />";
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

		location.href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />?year=<%=iNowYear%>&month=<%=iNowMonth%>&day=<%=iNowDay%>&searchCondition=SCHDUL_SE&searchKeyword=" + setValue;
	}
	
	function fnEgovSchdulTodate() {

		location.href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageDailyList.do' />?year=<%=sTodate.substring(0, 4)%>&month=<%=Integer.valueOf(sTodate.substring(4, 6))-1%>&day=<%=sTodate.substring(6, 8)%>&searchCondition=SCHDUL_SE&searchKeyword=" + document.deptSchdulManageVO.schdulSe.value;

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
			<li><a href="javascript:fnEgovSchdulTodate();" class="today"><spring:message code="comCopSmtSdm.Navi.Today"/></a></li> <!-- 오늘 -->
			
			<li><a class="prev" href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />?year=<%=iNowYear-1%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay%>">prev</a></li>
			<li class="date"><%=iNowYear%> <spring:message code="comCopSmtSdm.Navi.Year"/> </li>  <!-- 년 -->
			<li><a class="next" href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />?year=<%=iNowYear+1%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay%>">next</a></li>
			
			<li class="date">&nbsp;</li>
			
			<%if(iNowMonth > 0 ){ %>
			<li><a class="prev" href="<c:url value='/cop/smt/sdm/EgovDeptSchdulManageDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth-1%>&amp;day=<%=iNowDay%>">prev</a></li>
			<%}%>
			<li class="date"><%=iNowMonth+1%> <spring:message code="comCopSmtSdm.Navi.Month"/> </li> <!-- 월 -->
			<%if(iNowMonth < 11 ){ %>
			<li><a class="next" href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth+1%>&amp;day=<%=iNowDay%>">next</a></li>
			<%}%>
			<li class="date">&nbsp;</li>
			<%if(iNowDay > 1 ){ %>
			<li><a class="prev" href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay-1%>">prev</a></li>
			<%}%>
			<li class="date"><%=iNowDay%> <spring:message code="comCopSmtSdm.Navi.Day"/> </li> <!-- 일 -->
			<%if(iNowDay < iEndDay ){ %>
			<li><a class="next" href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDailyList.do' />?year=<%=iNowYear%>&amp;month=<%=iNowMonth%>&amp;day=<%=iNowDay+1%>">next</a></li>
			<%}%>		
		</ul>
	</div>
</div>


<div class="week_calendar">
<table class="title">
<caption><spring:message code="comCopSmtSdm.Gbn.Daily"/> <spring:message code="comCopSmtSdm.title"/></caption> <!-- 일간 부서일정관리 -->
<colgroup>
	<col style="width: 170px;">
	<col style="width:;">
	<col style="width: 100px;">
</colgroup>
<thead>
<tr>
	<th><spring:message code="comCopSmtSdm.Weekly.time"/></th> <!-- 시간 -->
	<th><spring:message code="comCopSmtSdm.Weekly.title"/></th> <!-- 제목 -->
	<th><spring:message code="comCopSmtSdm.Weekly.chargeName"/></th> <!-- 담당자 -->
</tr>
<tbody>
<%

List listResult = (List)request.getAttribute("resultList");
EgovMap egovMap = new EgovMap();
if(listResult != null){
	for(int i=0;i < listResult.size(); i++){
	egovMap = (EgovMap)listResult.get(i);
%>
  <tr>
    <td height="50px">
    <%
    //out.print("<a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
    //out.print("<a href=\"<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />?schdulId=" + (String)egovMap.get("schdulId") + "\" target=\"_top\">");
    %>
	<a href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />?schdulId=<%=(String)egovMap.get("schdulId")%>" target="_parent">
	<%
	out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시");
	out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분<br/>~");
	out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시");
	out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");
	out.println("</a>");
    %>
    </td>
    <td style="text-align:left;padding-left:5px;">
	<%
	//out.print("<a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
    //out.print("<a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
    %>
	<a href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />?schdulId=<%=(String)egovMap.get("schdulId")%>" target="_parent">
	<%=(String)egovMap.get("schdulNm")%>
	</a>
    </td>
    <td>
    	<%=(String)egovMap.get("schdulChargerName")%>
    </td>
  </tr>
  <TR class='line'><td></td><td></td><td></td></TR>
 <%
	}
}
%>
  <c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td bgcolor="#FFFFFFFF" nowrap colspan="3" align="center" style="padding-center:2px;font-size:11px;" height="30px"><spring:message code="common.nodata.msg" /></td>
	</tr>
	<TR class='line'><td></td><td></td><td></td></TR>
  </c:if>
</tbody>
</table>
</div>

</DIV>
</form>
</BODY>
</HTML>
