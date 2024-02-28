<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<spring:eval expression="@customProperties.getProperty('facebook.appId')" var="appId"/>
<%

/**
 * @Class Name : EgovFacebookAlbums.jsp
 * @Description : EgovFacebookAlbums.jsp
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
<title>Facebook <spring:message code="comUssIonFbk.facebookAlbums.title"/></title><!-- 앨범목록 조회 -->
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
				  '/me/albums',
				  'GET',
				  {"fields":"id,name"},
				  function(response) {
					  
					 var data = response.data;
					 var html = "";
					 
						html += '<table class="wTable">';
						html += '<colgroup><col style="width:25%"><col style="width:auto"></colgroup>';
						html += '<tbody>';
					 
					 for (var i = 0; i < data.length; i++) { 
						 html += '<tr>';
						 html += '<td class="left" >';
						 html += '<a href="<c:url value="/uss/ion/fbk/album/' + data[i].id + '"/>' + '">'; 
						 html += '<button class="btn_01">';
						 html += data[i].name;
						 html += '</button>';
						 html += '</a>';
						 html += '</td>';
						 html += '</tr>';
					 }
					 
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
	
	</script>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>Your Facebook Photo Albums</h2>
	<div id="dv"></div>
</div>
</body>
</html>