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
	System.out.println("sTWITTER_SE1 : " +session.getAttribute("sTWITTER_SE"));
	String sCmd = session.getAttribute("sTWITTER_SE") == null ? "" : (String) session.getAttribute("sTWITTER_SE");
	System.out.println("sTWITTER_SE2 : " +session.getAttribute("sTWITTER_SE"));

	String sCONSUMER_KEY = session.getAttribute("sCONSUMER_KEY").toString().trim();
	String sCONSUMER_SECRET = session.getAttribute("sCONSUMER_SECRET").toString().trim();
	System.out.println("3");
	
	Twitter twitter = new TwitterFactory().getInstance();
	System.out.println("4");
	twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
	System.out.println("5");
	
	String oauthToken = request.getParameter("oauth_token");
	System.out.println("6");
	String oauthVerifier = request.getParameter("oauth_verifier");
	System.out.println("7");
	
	String rtoken = session.getAttribute("rtoken").toString().trim();
	System.out.println("8");
	String rstoken = session.getAttribute("rstoken").toString().trim();
	System.out.println("9");
	RequestToken requestToken = new RequestToken(rtoken,rstoken);
	System.out.println("10");
	
	System.out.println("=============================");
	//System.out.println("sCONSUMER_KEY : "+sCONSUMER_KEY);
	//System.out.println("sCONSUMER_SECRET : "+sCONSUMER_SECRET);
	//System.out.println("oauthToken : "+oauthToken);
	//System.out.println("oauthVerifier : "+oauthVerifier);
	//System.out.println("rtoken : "+rtoken);
	//System.out.println("rstoken : "+rstoken);
	
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
