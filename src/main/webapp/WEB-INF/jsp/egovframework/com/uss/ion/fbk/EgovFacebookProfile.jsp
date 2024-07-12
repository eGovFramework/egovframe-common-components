<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<spring:eval expression="@customProperties.getProperty('facebook.appId')" var="appId"/>
<%

/**
 * @Class Name : EgovFacebookProfile.jsp
 * @Description : EgovFacebookProfile.jsp
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------     -----------------    -------------------------
 * @ 2014.11.10    표준프레임워크센터        최초생성
 * @ 2018.10.29    최 두 영                        3.8개선    
 *
 *  @author 표준프레임워크센터
 *  @since 2014.11.10
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2014 by MOGAHA  All right reserved.
 */

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Facebook <spring:message code="comUssIonFbk.facebookProfile.title"/></title><!-- 프로필 조회 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
</head>
<body>
	<script>
		window.fbAsyncInit = function() {
			
			var appId = "<c:out value='${appId}' />";
			// https 페이지에서 호출하지 않을 시, accessToken과 userID 값은 임의로 설정이 필요하다.
			// https://developers.facebook.com/에서 그래프 API 탐색기를 통해 값을 확인한 후 설정
			var accessToken = "";
			var userID = ""
			
			FB.init({
				appId : appId,
				autoLogAppEvents : true,
				xfbml : true,
				version : 'v17.0' // 버전은 그래프 API GET 옆에 나타나는 버전과 일치시켜야 한다.
			});
			
			var callback = function(response) {
				console.log(response);
				accessToken = response.authResponse.accessToken;
				userID = response.authResponse.userID;
			}
			
			// 페이스북 로그인 여부 확인
			FB.getLoginStatus(callback);
			
			FB.api(
				  '/' + userID + '?fields=id,name,email,first_name',
				  'GET',
				  {"fields":"id,name,email,first_name"},
				  function(response) {
					console.log(response)
						
					 var data = response;
					 var html = ""
					 
						 html += '<h2>Your Facebook Profile</h2>';
						 html += '<p class="search_box" >';
						 html += '<strong>Hello, ' + data.first_name + '!</strong>';
						 html += '</p>';
						 
						 html += '<table class="wTable">';
						 html += '<colgroup><col style="width:25%"><col style="width:auto"></colgroup>';
						 html += '<tbody>';
						 html += '<tr>';
						 html += '<th>Facebook ID</th>';
						 html += '<td class="left">' + data.id + '&nbsp;</td>';
						 html += '</tr>';
						 html += '<tr>';
						 html += '<th>Name</th>';
						 html += '<td class="left">' + data.name + '&nbsp;</td>';
						 html += '</tr>';
						 html += '<tr>';
						 html += '<th>Email</th>';
						 html += '<td class="left">' + data.email + '&nbsp;</td>';
						 html += '</tr>'; 
						 html += '</tbody>';
						 html += '</table>';
						
						 document.querySelector('#dv').innerHTML  = html;
				  }, {access_token: accessToken}
				);
			};
	
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	
		// 페이스북 (로그아웃)
		function facebookLogout() {
			FB.logout(function(res) {
				window.history.back()
				});
		}
	</script>

	<div class="wTableFrm">
		<div id="dv"></div>
		<br />
		<input class="btn_01" type="button" id="logBtn" value="Disconnect from Facebook"/>
	</div>
</body>
<script>
//로그아웃 버튼 클릭시
document.querySelector('#logBtn').addEventListener('click', function(e) {
		facebookLogout();
});
</script>
</html>