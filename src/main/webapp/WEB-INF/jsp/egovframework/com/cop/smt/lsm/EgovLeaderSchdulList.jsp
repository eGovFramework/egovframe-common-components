<%--
  Class Name : EgovLeaderSchdulList.jsp
  Description : 긴부일정관리 월별/주간별/일별 조회
  Modification Information
 
       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2010.06.29   장철호            최초 생성
    2018.09.14   최두영            다국어처리
    2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
 
    author   : 공통서비스 개발팀 장철호
    since    : 2010.06.29
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtLsm.leaderSchdulList.title" /></title><!-- 간부일정 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.12.4.min.js"/>"></script>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fnTabMenuSelect(objArr){
	
	
	
/*
	document.getElementById("tabMenu0").bgColor = '#DDDDDD';
	document.getElementById("tabMenu1").bgColor = '#DDDDDD';
	document.getElementById("tabMenu2").bgColor = '#DDDDDD';
	*/
	//경로 이동
	$(".tab01 li a").removeClass("on");
	if(objArr == 0){
		document.getElementById('SchdulView').src="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />"; 
		//document.getElementById("tabMenu0").bgColor = '#BBBBBB';
		$(".tab01 li a").eq(0).addClass("on");
	}else if(objArr == 1){
		document.getElementById('SchdulView').src="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulWeekList.do' />";
		//document.getElementById("tabMenu1").bgColor = '#BBBBBB';
		$(".tab01 li a").eq(1).addClass("on");
	}else if(objArr == 2){
		document.getElementById('SchdulView').src="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />";
		//document.getElementById("tabMenu2").bgColor = '#BBBBBB';
		$(".tab01 li a").eq(2).addClass("on");
	}

}

/* ********************************************************
* 등록 처리 함수 
******************************************************** */
function fnInit(){

	/*
	document.getElementById("tabMenu0").bgColor = '#DDDDDD';
	document.getElementById("tabMenu1").bgColor = '#DDDDDD';
	document.getElementById("tabMenu2").bgColor = '#DDDDDD';
	
	var vFrom = document.leaderSchdulVO;
	var searchMode = vFrom.searchMode.value;
	if(searchMode == "MONTH"){
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />";
		document.getElementById("tabMenu0").bgColor = '#BBBBBB';
	}else if(searchMode == "WEEK"){
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulWeekList.do' />";
		document.getElementById("tabMenu1").bgColor = '#BBBBBB';
	}else if(searchMode == "DAILY"){
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />";
		document.getElementById("tabMenu2").bgColor = '#BBBBBB';
	}else{
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />"; 
		document.getElementById("tabMenu0").bgColor = '#BBBBBB';
	}
	vFrom.submit();
	*/
}


/* ********************************************************
* IFRAME AUTO HEIGHT
******************************************************** */
function do_resize() {
 resizeFrame("iframe_main",1);
}

function resizeFrame(ifr_id,re){
//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
 var ifr= document.getElementById(ifr_id) ;
 var innerBody = ifr.contentWindow.document.body;
 var innerHeight = innerBody.scrollHeight + (innerBody.offsetHeight - innerBody.clientHeight);
 //var innerWidth = document.body.scrollWidth + (document.body.offsetWidth - document.body.clientWidth);

 if (ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
 {
  ifr.style.height = innerHeight;
  //ifr.style.width = innerWidth;
 }

 if(!re) {
  try{
   	innerBody.attachEvent('onclick',parent.do_resize);
   	innerBody.attachEvent('onkeyup',parent.do_resize);
   //글작성 상황에서 클릭없이 타이핑하면서 창이 늘어나는 상황이면 윗줄 주석제거
  } catch(e) {
   innerBody.addEventListener("click", parent.do_resize, false);
   innerBody.addEventListener("keyup", parent.do_resize, false);
   //글작성 상황에서 클릭없이 타이핑하면서 창이 늘어나는 상황이면 윗줄 주석제거
  }
 }
}

$(function() {
	
	//init
	var vFrom = document.leaderSchdulVO;
	var searchMode = vFrom.searchMode.value;
	
	if(searchMode == "MONTH"){
		$(".tab01 li:eq(0) a").addClass("on");
	}else if(searchMode == "WEEK"){
		$(".tab01 li:eq(1) a").addClass("on");
	}else if(searchMode == "DAILY"){
		$(".tab01 li:eq(2) a").addClass("on");
	}else{
		$(".tab01 li:eq(0) a").addClass("on");
	}
	
	
	if(searchMode == "MONTH"){
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />";
		document.getElementById("tabMenu0").bgColor = '#BBBBBB';
	}else if(searchMode == "WEEK"){
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulWeekList.do' />";
		document.getElementById("tabMenu1").bgColor = '#BBBBBB';
	}else if(searchMode == "DAILY"){
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />";
		document.getElementById("tabMenu2").bgColor = '#BBBBBB';
	}else{
		vFrom.action = "<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />"; 
		document.getElementById("tabMenu0").bgColor = '#BBBBBB';
	}
	vFrom.submit();
});

</script>
</head>
<body>
<div id="content" style="width:712px">

	<h1 class="tit01 mt20 mb8"><spring:message code="comCopSmtLsm.leaderSchdulList.title" /></h1><!-- 간부일정 목록 -->
	
	<div class="tab01 mb10">
		<ul>
			<li><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />" target="SchdulView" onclick="fnTabMenuSelect(0);"><spring:message code="comCopSmtLsm.leaderSchdulList.title.selectLeaderSchdulMonthList" /></a></li><!-- 월별일정보기 -->
			<li><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulWeekList.do' />" target="SchdulView" onclick="fnTabMenuSelect(1);"><spring:message code="comCopSmtLsm.leaderSchdulList.title.selectLeaderSchdulWeekList" /></a></li><!-- 주간별일정보기 -->
			<li><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />" target="SchdulView" onclick="fnTabMenuSelect(2);"><spring:message code="comCopSmtLsm.leaderSchdulList.title.selectLeaderSchdulDailyList" /></a></li><!-- 일별일정보기 -->
		</ul>
	</div>
	

	<!-- 상단 텝 -->
	<%-- 
	<table border="0" cellspacing="0" cellpadding="0" summary="간부일정목록의 보기방식을 지정합니다.">
	<tr> 
	  <td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu0" ><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />" target="SchdulView" onClick="fnTabMenuSelect(0);"><b>월별일정보기</b></a></td>
	  <td height="20px" width="1x" bgcolor="#FFFFFF"></td>
	  <td height="20px" width="100px"  bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu1"><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulWeekList.do' />" target="SchdulView" onClick="fnTabMenuSelect(1);"><b>주간별일정보기</b></a></td>
	  <td height="20px" width="1x" bgcolor="#FFFFFF" ></td>
	  <td height="20px" width="100px" style="cursor:hand;cursor:pointer;" bgcolor="#DDDDDD" align="center" id="tabMenu2"><a href="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulDailyList.do' />" target="SchdulView" onClick="fnTabMenuSelect(2);"><b>일별일정보기</b></a></td>
	  <td height="20px" width="1x" bgcolor="#FFFFFF"> </td>
	</tr>
	</table>
	 --%>
	<!-- //상단 텝 -->
	
		
	<!-- iframe -->
	<iframe id="SchdulView" name="SchdulView" src="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulMonthList.do' />" width="100%" height="670px" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" title="월별/주별/일별 간부일정목록">
	</iframe>
	<!-- //iframe -->
	<form name="leaderSchdulVO" id="leaderSchdulVO" action="<c:url value='/cop/smt/lsm/usr/selectLeaderSchdulList.do' />" method="post" target="SchdulView">
		<input type="hidden" name="year" id="year" value="<c:out value='${leaderSchdulVO.year}'/>"> 
		<input type="hidden" name="month" id="month" value="<c:out value='${leaderSchdulVO.month}'/>"> 
		<input type="hidden" name="week" id="week" value="<c:out value='${leaderSchdulVO.week}'/>"> 
		<input type="hidden" name="day" id="day" value="<c:out value='${leaderSchdulVO.day}'/>"> 
		<input type="hidden" name="schdulId" id="schdulId" value="">  
		<input type="hidden" name="schdulDe" id="schdulDe" value="">  
		<input type="hidden" name="schdulBgnDe" id="schdulBgnDe" value="">
		<input type="hidden" name="schdulEndDe" id="schdulEndDe" value="">
		<input type="hidden" name="searchMode" id="searchMode" value="<c:out value='${leaderSchdulVO.searchMode}'/>"> 
		<input type="submit" value="" style="border : 0px solid;height : 0px;width : 0px;">
	</form>
</div>
</body>
</html>

