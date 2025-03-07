<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovUnitTop.jsp
  * @Description : 상단 헤더 영역
  * @Modification Information
  * 
  * @수정일               수정자            수정내용
  *  ----------   --------   ---------------------------
  *  2020.06.23   신용호            세션만료시간 보여주기
  *
  *  @author 공통서비스 개발팀 신용호
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/main.css' />">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<title>eGovFrame 공통 컴포넌트</title>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<%

	String egovLatestServerTime = "";
	String egovExpireSessionTime = "";
	// 쿠키값 가져오기
	Cookie[] cookies = request.getCookies() ;
	if(cookies != null){
		for(int i=0; i < cookies.length; i++){
			Cookie c = cookies[i] ;
			// 저장된 쿠키 이름을 가져온다
			String cName = c.getName();
			// 쿠키값을 가져온다
			String cValue = c.getValue() ;
			if ("egovLatestServerTime".equals(cName)) {
				egovLatestServerTime = cValue;
			}
			if ("egovExpireSessionTime".equals(cName)) {
				egovExpireSessionTime = cValue;
			}
		}
	}

%>
<script type="text/javaScript" language="javascript" defer="defer">
	function getCookie(cname) {
 	  var name = cname + "=";
 	  var decodedCookie = decodeURIComponent(document.cookie);
 	  var ca = decodedCookie.split(';');
 	  for(var i = 0; i <ca.length; i++) {
 	    var c = ca[i];
 	    while (c.charAt(0) == ' ') {
 	      c = c.substring(1);
 	    }
 	    if (c.indexOf(name) == 0) {
 	      return c.substring(name.length, c.length);
 	    }
 	  }
 	  return "";
 	}
  
  	function pad(n, width) {
  	  n = n + '';
  	  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
  	}
  
	var objLeftTime;
	var objClickInfo;
	var latestTime;
	var expireTime;
	var timeInterval = 1000; // 1초 간격 호출
	var firstLocalTime = 0;
	var elapsedLocalTime = 0;
	var stateExpiredTime = false;
	var logoutUrl = "<c:url value='/uat/uia/actionLogout.do'/>";
	var timer;
  	
	function init() {
		objLeftTime = document.getElementById("leftTimeInfo");
		
		if (objLeftTime == null) {
			console.log("'leftTimeInfo' ID is not exist!");
			return;
		}
		objClickInfo = document.getElementById("clickInfo");
		//console.log(objLeftTime.textContent);

		latestTime = <%=egovLatestServerTime%>; //getCookie("egovLatestServerTime")
		expireTime = <%=egovExpireSessionTime%>; //getCookie("egovExpireSessionTime");
		console.log("latestServerTime = "+latestTime);
		console.log("expireSessionTime = "+expireTime);
		
		elapsedTime = 0;
		firstLocalTime = (new Date()).getTime();
		showRemaining();
		
		timer = setInterval(showRemaining, timeInterval); // 1초 간격 호출
	}

	function showRemaining() {
		elapsedLocalTime = (new Date()).getTime() - firstLocalTime;
		
		var timeRemaining = expireTime - latestTime - elapsedLocalTime;
		if ( timeRemaining < timeInterval ) {
			clearInterval(timer);
			objLeftTime.innerHTML = "00:00:00";
			objClickInfo.text = '<spring:message code="comCmm.top.expiredSessionTime"/>'; //시간만료
			stateExpiredTime = true;
			alert('<spring:message code="comCmm.top.expireSessionTimeInfo"/>');//로그인 세션시간이 만료 되었습니다.
			// reload content main page
			$("#sessionInfo").hide();

			parent.frames["_content"].location.href = logoutUrl;
			//parent.frames["_content"].location.reload();

			return;
		}
		var timeHour = Math.floor(timeRemaining/1000/60 / 60); 
		var timeMin = Math.floor((timeRemaining/1000/60) % 60);
		var timeSec = Math.floor((timeRemaining/1000) % 60);
		//objLeftTime.textContent = pad(timeHour,2) +":"+ pad(timeMin,2) +":"+ pad(timeSec,2);
		//objLeftTime.outerText = pad(timeHour,2) +":"+ pad(timeMin,2) +":"+ pad(timeSec,2);
		objLeftTime.innerHTML = pad(timeHour,2) +":"+ pad(timeMin,2) +":"+ pad(timeSec,2);
		//console.log("call showRemaining() = "+objLeftTime.innerHTML);
	}
  
	function reqTimeAjax() {
	  	
	  	if (stateExpiredTime==true) {
	  		alert('<spring:message code="comCmm.top.cantIncSessionTime"/>');//시간을 연장할수 없습니다.
	  		return;
	  	}
	  	
		$.ajax({
		    url:'${pageContext.request.contextPath}/uat/uia/refreshSessionTimeout.do', //request 보낼 서버의 경로
		    type:'get', // 메소드(get, post, put 등)
		    data:{}, //보낼 데이터
		    success: function(data) {
		        //서버로부터 정상적으로 응답이 왔을 때 실행
	        	latestTime = getCookie("egovLatestServerTime");
	        	expireTime = getCookie("egovExpireSessionTime");
	        	console.log("latestServerTime = "+latestTime);
	        	console.log("expireSessionTime = "+expireTime);
	        	init();
		        //alert("정상수신 , data = "+data);
		    },
		    error: function(err) {
		    	alert("err : "+err);
		        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
		    	//alert("오류발생 , error="+err.state());
		    }
		});
		return false;
	}
	
	function logout() {
		$("#sessionInfo").hide();

		parent.frames["_content"].location.href = logoutUrl;
	}
	
</script>
</head>
<body onload="init()">
<div id="header">
	<div class="header_box">
		<h1>
			<a href="<c:url value='/EgovContent.do' />" target="_content"><img src="<c:url value='/images/egovframework/com/cmm/main/top_logo.png' />" alt="eGovframe"></a>
		</h1>
		<div style="margin-top:4px;">
			<strong class="top_title_strong"><spring:message code="comCmm.top.title"/></strong>
		    <span id="sessionInfo">
		    	<c:if test="${loginVO != null}">
				<br><spring:message code="comCmm.top.leftSessionTime"/> - <span id="leftTimeInfo">00:00:00</span><!-- 세션만료 남은시간 -->
			    <a id="clickInfo" class="btn02" href="#"  onclick="reqTimeAjax();return false;"><spring:message code="comCmm.top.incSessionTime"/></a><!-- 시간연장 -->
			    <a class="btn02" href="#"  onclick="logout();return false;"><spring:message code="comCmm.unitContent.3"/></a><!-- 로그아웃 -->
			    </c:if>
		    </span>
	    </div>
	</div>
</div>
</body>
</html>