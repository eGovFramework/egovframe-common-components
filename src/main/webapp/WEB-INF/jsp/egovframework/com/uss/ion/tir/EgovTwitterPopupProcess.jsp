<%--
  Class Name : EgovTwitterRecptn.jsp
  Description : 트위터 수신(목록) 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.07    장동한          최초 생성

    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.07

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.Twitter"%>
<%@ page import="twitter4j.TwitterFactory"%>
<%@ page import="twitter4j.auth.AccessToken"%>
<%@ page import="twitter4j.TwitterException"%>
<%@ page import="twitter4j.auth.RequestToken"%>
<%@ page import="twitter4j.conf.Configuration"%>
<%@ page import="twitter4j.conf.ConfigurationBuilder"%>

<%@ page import="egovframework.com.cmm.util.EgovBasicLogger"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
	//조회 모드 세션에서 가져오기
	String sCmd = session.getAttribute("sTWITTER_SE") == null ? "" : (String) session.getAttribute("sTWITTER_SE");

	String sCONSUMER_KEY = session.getAttribute("sCONSUMER_KEY").toString().trim();
	String sCONSUMER_SECRET = session.getAttribute("sCONSUMER_SECRET").toString().trim();
	
	Twitter twitter = new TwitterFactory().getInstance();
	twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
	
	String oauthToken = request.getParameter("oauth_token");
	String oauthVerifier = request.getParameter("oauth_verifier");
	
	String rtoken = session.getAttribute("rtoken").toString().trim();
	String rstoken = session.getAttribute("rstoken").toString().trim();
	RequestToken requestToken = new RequestToken(rtoken,rstoken);

	AccessToken accessToken = null;
	if (rtoken.equals(oauthToken)) {
		try {
			accessToken = twitter.getOAuthAccessToken(requestToken,oauthVerifier);
		} catch (TwitterException te) {
			//te.printStackTrace();// KISA 보안취약점 조치 (2018-12-10, 신용호)
			EgovBasicLogger.debug("Twitter Access Token getting error", te);
		} catch (Exception e) {
			//e.printStackTrace();// KISA 보안취약점 조치 (2018-12-10, 신용호)
			EgovBasicLogger.debug("error", e);
		}
	}
	twitter.setOAuthAccessToken(accessToken);
	
	//해당키 저장
	if (accessToken != null) {
		session.setAttribute("atoken", accessToken.getToken());
		session.setAttribute("astoken", accessToken.getTokenSecret());
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>트위터(Twitter)-인증</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javaScript" language="javascript">
<%if (sCmd.equals("RECPTN")) {%>
opener.location.href = '<c:url value="/uss/ion/tir/listTwitterRecptn.do"/>';
<%} else {%>
opener.location.href = '<c:url value="/uss/ion/tir/registTwitterTrnsmit.do"/>';
<%}%>
window.close();
</script>
</head>
<body>
</body>
</html>
