<%--
  Class Name : EgovTwitterRecptn.jsp
  Description : 트위터 수신(목록) 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.07    장동한          최초 생성
     2018.10.26    이정은          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.07
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%

String sCmd = request.getParameter("cmd") == null ? "": (String)request.getParameter("cmd");
String sVerify = request.getParameter("verify") == null ? "": (String)request.getParameter("verify");


String sConsumerKey = request.getAttribute("consumerKey") == null ? "": (String)request.getAttribute("consumerKey");
String sConsumerSecret = request.getAttribute("consumerSecret") == null ? "": (String)request.getAttribute("consumerSecret");

String sAt = request.getParameter("at") == null ? "": (String)request.getParameter("at");

//웹보안을 위한 변수 설정
String sCmds = "";
if(sCmd.equals("RECPTN") || sCmd.equals("TRNSMIT")){
	sCmds = sCmd;
}

if(!sConsumerKey.equals("") && !sConsumerSecret.equals("") && !sAt.equals("1")){
	
	// 2011.10.25 보안점검 후속조치
    sCmds = sCmds.replaceAll("\r", "").replaceAll("\n", "");
    sConsumerKey = sConsumerKey.replaceAll("\r", "").replaceAll("\n", "");
	sConsumerSecret = sConsumerSecret.replaceAll("\r", "").replaceAll("\n", "");
	
	response.sendRedirect("<c:url value='/uss/ion/tir/selectTwitterPopupActor.do'/>?chkKey=1&at=1&cmd="+sCmds+"&ConsumerKey="+sConsumerKey+"&ConsumerSecret="+sConsumerSecret);
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterPopup.twitterPopup"/></title><!-- 트위터(Twitter) 인증요청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
//초기화
function fn_init_TwitterPopup(){

}
/* ********************************************************
* 전송 체크
******************************************************** */
function fn_egov_search_TwitterRecptn(){
	var vFrom = document.twitterInfo;

	if(vFrom.ConsumerKey.value == ""){
		alert("<spring:message code="ussIonTir.twitterPopup.consumerKey"/>");/* Consumer key 를 입력해주세요! */
		vFrom.ConsumerKey.focus();
		return;
	}

	if(vFrom.ConsumerSecret.value == ""){
		alert("<spring:message code="ussIonTir.twitterPopup.consumerSecret"/>");/* Consumer secret 를 입력해주세요! */
		vFrom.ConsumerSecret.focus();
		return;
	}
	
	vFrom.submit();
}
</script>
</head>
<body onLoad="fn_init_TwitterPopup()">
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form id="twitterInfo" name="twitterInfo" action="<c:url value='/uss/ion/tir/selectTwitterPopupActor.do'/>" method="post" enctype="multipart/form-data">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonTir.twitterPopup.twitterPopupKey"/></h2><!-- 트위터(Twitter) 인증요청 - 인증키 입력 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:25%" />
			<col style="" />
		</colgroup>
		<tr>
			<th>Consumer key <span class="pilsu">*</span></th>
			<td class="left">
			    <input type="text" id="ConsumerKey" name="ConsumerKey" title="ConsumerKey" value="gCrpRrhlKCq9iqqV447NW551N" maxlength="255" style="width:100%">
			</td>
		</tr>
		<tr>
			<th>Consumer secret <span class="pilsu">*</span></th>
			<td class="left">
			    <input type="password" id="ConsumerSecret" name="ConsumerSecret" title="ConsumerSecret" value="DWsv7EAVzkBxEZvjhtGwgDpgKQULsZoBZ9v1eSYSTOgtkm6xeg" maxlength="255" style="width:100%; height:24px;">
			</td>
		</tr>
		<tr>
			<td class="left" colspan="2">
			    <input type="checkbox" name="chkKey" id="chkKey" value="1" title="인증키 저장 여부 체크박스" onclick="fn_chkKey_TwitterPopup(this)" <%if(!sConsumerKey.equals("") && !sConsumerSecret.equals("")){ %>checked<%}%>><label for="chkKey"><spring:message code="ussIonTir.twitterPopup.saveKey"/></label><!-- Consumer key/Consumer secret 키 값 저장 -->
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="ussIonTir.twitterPopup.send"/>" onclick="fn_egov_search_TwitterRecptn()" />
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" name="cmd" value="<c:out value="${sCmd}"/>">
</form>

</DIV>
</body>
</html>
