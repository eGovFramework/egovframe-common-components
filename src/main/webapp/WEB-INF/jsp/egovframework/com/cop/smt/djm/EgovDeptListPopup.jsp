<%--
  Class Name : EgovDeptListPopup.jsp
  Description : 부서 목록 조회
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.06    장철호          최초 생성
     2018.09.14    최두영          다국어처리
 
    author   : 공통서비스 개발팀 장철호
    since    : 2010.07.06
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<title><spring:message code="comCopSmtDjm.deptListPopup.title" /></title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


<meta http-equiv="content-type" content="text/html; charset=utf-8" />


<script type="text/javaScript" language="javascript">


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


</script>
</head>
<body>
<DIV id="content" style="width:775px">
<!-- ------------------------------------------------------------------ iframe -->
<iframe id="DeptListView" src="<c:url value='/cop/smt/djm/selectDeptList.do' />" width="100%" height="475px" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" title="부서 목록">
</iframe>
</DIV>
</body>
</html>

