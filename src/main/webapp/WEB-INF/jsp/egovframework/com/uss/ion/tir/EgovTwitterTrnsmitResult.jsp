<%--
  Class Name : EgovTwitterTrnsmit.jsp
  Description : 트위터 송신(등록) 페이지
  Modification Information
 
       수정일               수정자              수정내용
    ----------   --------    ---------------------------
    2010.07.13   장동한              최초 생성
    2018.10.29   이정은              공통컴포넌트 3.8 개선
    2020.10.29   신용호              KISA 보안약점 조치 (크로스사이트 스크립트)
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.13
   
--%>
<%@page import="egovframework.com.uss.ion.tir.service.TwitterInfo"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.Status"%>
<%@ page import="twitter4j.User"%>
<%@page import="egovframework.com.cmm.EgovWebUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
TwitterInfo twitterInfo = (TwitterInfo) request.getAttribute("twitterInfo");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterTrnsmitResult.twitterTrnsmitResult"/></title><!-- 트위터(Twitter) 송신(등록)결과확인 -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css'/>">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css'/>">
<script  src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>"></script>
<script script type="text/javascript">
	
		function deleteTweet(){
			var tID = $("#tweetID").val();
			console.log("tID >>> " + tID);
			
			if(confirm("<spring:message code='ussIonTir.twitterTrnsmitResult.delete.confirmMessage' />")){
					$.ajax({
						url : "twitterDelete.do",
						data : {"tweetID" : JSON.stringify(tID)},
						dataType : "text",
						type : "POST",
						success : function(result){
							if(result){
								alert("<spring:message code='ussIonTir.twitterTrnsmitResult.delete.success' />");
								$("#tID").text("");
								$("#tText").text("");
								$("#tweetID").val("");
							}else{
								alert("<spring:message code='ussIonTir.twitterTrnsmitResult.delete.unsuccess' />");
							}
						},error : (function(result){
							console.log("Error");
						})
					});
					if($("#tweetID").val()){
						$(".btn_del").css("display", "none");
					};
				}
			}
	
</script>
</head>
<body>
<DIV id="content" style="width:712px">
<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonTir.twitterTrnsmitResult.twitterTrnsmitResult"/></h2><!-- 트위터 송신 결과 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.id" /></th>
			<!-- 등록ID -->
					<td class="left"><%=EgovWebUtil.clearXSSMinimum(Long.toString(twitterInfo.getTwitterId()))%></td>
				</tr>
				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.screenName" /></th> 
			<!-- 계정ID -->
					<td class="left"><%=EgovWebUtil.clearXSSMinimum(twitterInfo.getTwitterScreenName())%></td>
				</tr>
				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.name" /></th>
			<!-- 별명  -->
					<td class="left"><%=EgovWebUtil.clearXSSMinimum(twitterInfo.getTwitterNmae())%></td>
				</tr>
				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.tweetId" /></th>
					<!-- 트윗 아이디 -->
					<td class="left" id = "tID"><%=EgovWebUtil.clearXSSMinimum(Long.toString(twitterInfo.getTwitterTweetId()))%></td>
				</tr>
				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.text" /></th>
					<!-- 트윗 내용 -->
					<td class="left" id="tText" ><%=EgovWebUtil.clearXSSMinimum(twitterInfo.getTwitterText())%></td>
				</tr>

				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.profileImage" /></th> 
			<!-- Profile Image -->
					<td class="left"><img src="<%=EgovWebUtil.clearXSSMinimum(twitterInfo.getTwitterProfileImageURL())%>" title="profileImageUrl" alt="profileImageUrl"></td>
				</tr>
				<%
 				java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  				%>  
				<tr>
					<th><spring:message code="ussIonTir.twitterTrnsmitResult.createdAt" /></th>
			<!-- 등록일 -->
					<td class="left"><%=format.format(twitterInfo.getTwitterCreatedAt())%>
					</td>
				</tr>
	</table>

	<!-- 하단 버튼 -->
	<!--  작성된 트윗  확인(트위터 페이지) -->
			<div class ="btn" style = "float:none;">
				<span class = "btn_s" style="width:auto !important; padding: 0 5px !important;">
					<a href="https://twitter.com/egovtest2/status/<%=twitterInfo.getTwitterTweetId()%>"  target="_blank" >
						<spring:message code="ussIonTir.twitterTrnsmitResult.viewTweet" /></a></span><!-- 트윗 확인 -->
			</div>
			
			<!-- 트윗 삭제 -->			
			<div class="btn btn_del" >
					<span class="btn_s" style="width:auto; padding: 0 5px;"><a onclick="deleteTweet()" id="tweetDel"><spring:message code="ussIonTir.twitterTrnsmitResult.delete" /></a></span>
					<input type = "hidden" id ="tweetID" value = "<%=EgovWebUtil.clearXSSMinimum(Long.toString(twitterInfo.getTwitterTweetId()))%>">
			</div>
			
			<!--  트윗 작성 페이지로 돌아가기 -->
			<div class = "btn" style = "margin-right:5px">
				<span class="btn_s" style="width:auto; padding: 0 5px;">
				<a href = "/egovframework-all-in-one/uss/ion/tir/selectTwitterMain.do" id="tweetRe"><spring:message code="ussIonTir.twitterTrnsmitResult.reWrite" /></a></span><!-- 재작성 -->
			</div>
	<div style="clear:both;"></div>
</div>

</DIV>

</body>
</html>