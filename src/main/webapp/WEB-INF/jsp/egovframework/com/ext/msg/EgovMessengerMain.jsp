<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import='java.util.Random' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle"><spring:message code="comExtMsg.webSocket.Title"/></c:set>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle} main</title><!--WebSocket messenger main-->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/ext/msg/table.css'/>"/>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- <script src="resource/js/json2.js"></script>-->
<script>
	//chat 팝업창을 여러개 띄우기 위함	
	var webSocket = null;
	$(document).ready(function() {
		var url = 'ws://' + window.location.host + '${pageContext.request.contextPath}/usersServerEndpoint';
		webSocket = connection(url);
		var connectionType;
		
		webSocket.onopen = function(){ processOpen(); };
		webSocket.onmessage = function(message) { processMessage(message); };
		webSocket.onerror = function(message) { processError(message); };
		
	});
	//var webSocket = new WebSocket('ws://' + window.location.host + '/egov-messenger/usersServerEndpoint');
	
	
	function connection(url) {
		var webSocket = null;
		if ('WebSocket' in window) {
			webSocket = new WebSocket(url);
		} else if ('MozWebSocket' in window) {
			webSocket = new MozWebSocket(url);
		} else {
			Console.log('Error: WebSocket is not supported by this browser.');
            return null;
		}
		return webSocket;
	}
	
	function processOpen() {
		connectionType = "firstConnection";
		username = "${loginVO.name}";
		webSocket.send(JSON.stringify({ "connectionType" : connectionType, "username" : username }));
	}
		
	//server에서 메시지가 넘어왔을때
	function processMessage(message) {
		var jsonData = JSON.parse(message.data);
		
		if (jsonData.allUsers != null) {
			//다른 사용자 접속 시,
			displayUsers(jsonData.allUsers);
		} 
		
		if (jsonData.disconnectedUser != null) {
			//다른 사용자가 접속을 끊을 때,
			$("#"+jsonData.disconnectedUser).remove();
		}
		
		//다른 사용자와 대화하고자 시도할 때, 채팅창을 팝업
		if (jsonData.enterChatId != null) {
			var roomId = jsonData.enterChatId;
			$("#roomId").val(roomId);
			$("#username").val(jsonData.username);
			openPopup(roomId);
		}
	}
	
	function openPopup(roomId) {
		var popOptions = "width= 500, height= 700, resizable=yes, status= no, scrollbar= yes"; 
		var targetTitle = random(roomId); //두명의 사용자가 다른 팝업으로 뜨기 위해서 targetTitle을 랜덤으로 만들어준다.
		popupPost("<c:url value='/cop/msg/websocketMessengePopup.do'/>", targetTitle, popOptions);
	}
	
	function popupPost(url, target, option) {
		window.open("", target, option);
		
		var form = $("form[name=usersForm]");
		form.attr("target", target);
		form.attr("action", url);
		form.attr("method", "post");
		form.submit();
	}
	
	
	function displayUsers(userList) {
		var username;
		$("#users tr:not(:first)").remove();
		for (var i=0; i<userList.length; i++) {
			if("${loginVO.name}"==userList[i]) {
				username = userList[i]+"(me!)";
			} else{
				username = userList[i];
			}
			$.newTr = $("<tr id="+userList[i]+" onclick='trClick(this)'><td>"+username+"</td></tr>");
			//append
			$("#users").last().append($.newTr);
			
		}
	}
	
	//다른 사용자 선택 시, 선택한 사용자 값을 서버에 전달
	function trClick(selectedTr) {
		if (selectedTr.id != null) {
				connectionType = "chatConnection";
				webSocket.send(JSON.stringify({ "connectionType" : connectionType, "connectingUser" : selectedTr.id }));
			}
	}
	
	function random(roomId) {
		<%
			String rUid = "";
			Random rnd = new Random();
			for(int i=0; i<8; i++) {
				rnd.setSeed(System.currentTimeMillis());
				rUid += (char)((rnd.nextDouble()*26)+97);//KISA 보안약점 조치 (2018-10-29, 윤창원)
			}
		%>
		return roomId+"."+"<%=rUid%>";
	}
	
	function processError(message) {
		/* messagesTextArea.value += "error...\n"; */
	}

	window.onbeforeunload = function() {
		webSocket.close();
	};
</script>
</head>
<body>
	<!-- <textarea id="messagesTextArea" readonly="readonly" rows="10" cols="45"></textarea>
	<textarea id="usersTextArea" readonly="readonly" rows="10" cols="10"></textarea>
	<br />
	<br />
	<input id="textMessage" type="text" size="50" />
	<select id="locationSelect">
		<option value="US">US</option>
		<option value="Canada">Canada</option>
		<option value="Other">Other</option>
	</select> -->
	<form name="usersForm">
		<input type="hidden" id="roomId" name="roomId"/>
		<input type="hidden" id="username" name="username"/>
	<br/>
	<div id="content">Web MESSENGER!!</div>
	<spring:message code="comExtMsg.webSocketMain.msg"/> <br/><!-- 본인 외의 대화상태를 선택하면 대화창이 뜹니다. -->
	<!-- List -->
	<table id="users" name="users" cellspacing='0'><!-- cellspacing='0' is important, must stay -->
    	<tr><th>Web Messenger Users</th></tr><!-- Table Header -->
    	<tr><td>There is no one to chat</td></tr>
    </table>
	</form>
</body>
</html>
