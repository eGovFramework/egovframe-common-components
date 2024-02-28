<%@ page contentType="text/html; charset=utf-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval
	expression="@customProperties.getProperty('facebook.appId')"
	var="appId" />
<%
/**
 * @Class Name : EgovFacebookSignin.jsp
 * @Description : EgovFacebookSignin.jsp
 * @Modification Information
 * @
 * @  수정일             수정자                        수정내용
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
<title>Facebook <spring:message
		code="comUssIonFbk.facebookSignin.title" /></title>
<!-- Facebook Login-->
<link href="<c:url value="/css/egovframework/com/com.css"/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>"
	rel="stylesheet" type="text/css">

<body>

	<script>
		window.fbAsyncInit = function() {

			var appId = "<c:out value='${appId}' />";
			var accessToken = "";
			var userID = "";

			// 페이스북 로그인 기능 클라이언트 설정
			FB.init({
				appId : appId,
				autoLogAppEvents : true,
				xfbml : true,
				version : 'v17.0'
			});

			// 페이스북 로그인 여부 확인

			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});

			//로그인 상태에 따라 로그인 / 로그아웃 구분
			function statusChangeCallback(res) {

				var appId = "<c:out value='${appId}' />";

				if (res.status === 'connected') {
					document.querySelector('#logBtn').value = "logout";
					document.querySelector('#facebookLink').style.display = "block";

					document.querySelector('#accessToken').value = res.authResponse.accessToken;
					document.querySelector('#userID').value = res.authResponse.userID;

				} else {
					document.querySelector('#logBtn').value = "Sign in with Facebook";
				}
			}
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

		// 페이스북 (로그인)
		function facebookLogin() {
			// 로그인 정보 GET
			FB.login(function(res) {
				// 사용자 정보 GET
				FB.api(
						'/' + res.authResponse.userID + '/',
						'GET',
						{
							"fields" : "id,name,email"
						},
						function(res2) {
							accessToken = res.authResponse.accessToken // 엑세스 토큰
							// res.authResponse.graphDomain : 공급자 (페이스북)
							userID = res.authResponse.userID // 유저 아이디 구분 (숫자)
							// res2.name : 유저 이름
							// res2.email : 유저 이메일 정보
							document.querySelector('#logBtn').value = "logout";
							document.querySelector('#facebookLink').style.display = "block";
							console.log(res, res2);
						});
					});
		}

		// 페이스북 (로그아웃)
		function facebookLogout() {
			FB.logout(function(res) {
					document.querySelector('#logBtn').value = "Sign in with Facebook";
					document.querySelector('#facebookLink').style.display = "none";
				});
		}
	</script>

	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2>
			<spring:message code="comUssIonFbk.facebookSignin.titleInfo" />
		</h2>
		<!-- Facebook 연동 -->
		<span><spring:message
				code="comUssIonFbk.facebookSignin.description" /></span><br> <br>
		<!-- 기능 테스트를 위해서 사전에 https://developers.facebook.com에서 API등록을 해야 한다. -->
		<span><spring:message
				code="comUssIonFbk.facebookSignin.description_2" /></span><br> <br>
		<!-- 기능 테스트를 위해서 Https 페이지에서의 호출이 필요하다. -->		
		<div id="border" style="width: 730px">

			<input class="btn_01" type="button" id="logBtn" /> <br /> <br />

			<div id="facebookLink" style="display:none;">
				<table class="wTable">
					<colgroup>
						<col style="width: 16%">
						<col style="width: auto">
					</colgroup>
					<tbody>
					<colgroup>
						<col style="width: 16%">
						<col style="width: auto">
					</colgroup>
					<tr>
						<th>Feed</th>
						<td class="left">
							<form name=feedBtnForm
								action="<c:url value='/uss/ion/fbk/feed.do' />" 
								method="post"
								target="_self">
								<button class="btn_01" type="submit">
									<spring:message code="comUssIonFbk.facebookHome.viewFeed" />
								</button>
							</form>
						</td>
					</tr>
					<!-- 담벼락 보기 -->
					<tr>
						<th>Albums</th>
						<td class="left">
							<a href="<c:url value="/uss/ion/fbk/albums.do" />">
								<button class="btn_01">
									<spring:message code="comUssIonFbk.facebookHome.viewDetailAlbum" />
								</button>
							</a>
						</td>
					</tr>
					<!-- 사진앨범 상세보기 -->
					<tr>
						<th>Profile</th>
						<td class="left">
							<a href="<c:url value="/uss/ion/fbk/profile.do" />">
								<button class="btn_01">
									<spring:message code="comUssIonFbk.facebookHome.viewProfile" />
								</button>
							</a>
						</td>
					</tr>
					<!-- 프로필 정보보기 -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<!--UI 관련 스크립트-->
<script>
	// 로그인 버튼 클릭시
	document.querySelector('#logBtn').addEventListener('click', function(e) {
		if (e.target.value === 'Sign in with Facebook') {
			facebookLogin();
		} else {
			facebookLogout();
		}
	});
</script>
</html>
