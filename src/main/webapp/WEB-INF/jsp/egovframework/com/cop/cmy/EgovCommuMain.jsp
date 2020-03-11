<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comCopCmy.communityUseMgrMain.title"/></c:set>
<%
 /**
  * @Class Name : EgovCmmntyBaseTmpl.jsp
  * @Description : 커뮤니티 기본 템플릿
  * @Modification Information
  * @
  * @  수정일     		 수정자            		수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.07   이삼섭       최초 생성
  * @ 2011.9.7		정진오		익명게시판 화면이 표출되도록 수정
  * @ 2018.10.15	최두영		기능 개선

  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.07
  *  @version 1.0
  *  @see
  *
  */
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title>${pageTitle}</title><!-- 커뮤니티 메인 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/cop/cmy/community.css' />" rel="stylesheet" type="text/css">
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">
<!--
	function fn_egov_goCmmntyHome(){
	}

	function fn_egov_loadMngrMenu(url){
		
	}

	function fn_egov_loadBdList(bbsId, bbsAttrbCode, bbsTyCode){
	}

	function fn_egov_loadClub(clbId){
	}

	function fn_egov_registUser(cmmntyId){
	}

	function fn_egov_deleteUser(cmmntyId){
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
<!--
	function fn_egov_goCmmntyHome(){
		var url = "<c:url value='/cop/cmy/cmmntyMainContents.do'/>";
		url += "?cmmntyId=" + document.frm.cmmntyId.value;
		document.getElementById("contentFrame").src = url;
	}

	function fn_egov_loadMngrMenu(url){
		url = url+"?cmmntyId="+document.frm.cmmntyId.value;
		document.getElementById("contentFrame").src = url;
	}

	function fn_egov_loadBdList(bbsId, bbsAttrbCode, bbsTyCode){
		var url = "<c:url value='/cop/bbs/selectArticleList.do' />?bbsId="+bbsId+"&bbsAttrbCode="+bbsAttrbCode;
		document.getElementById("contentFrame").src = url;
	}

	function fn_egov_registUser(cmmntyId){
		document.frm.action = "<c:url value='/cop/cmy/insertCommuUserBySelf.do' />";
		document.frm.submit();
	}

	function fn_egov_deleteUser(cmmntyId){
		document.frm.action = "<c:url value='/cop/cmy/deleteCommuUserBySelf.do' />";
		document.frm.submit();
	}
	
	function fn_alert_returnMsg() {
		var returnMsg = "<c:out value='${returnMsg}'/>";
		if (returnMsg != "") alert(returnMsg);
	}
//-->
</script>
</c:otherwise>
</c:choose>
</head>
<body onload="fn_alert_returnMsg()">
<form action="" name="frm" method="post" >
	<input type="hidden" name="cmmntyId" value="<c:out value='${cmmntyVO.cmmntyId}' />" />
	<input type="hidden" name="cmmntyNm" value="<c:out value='${cmmntyVO.cmmntyNm}' />" />
	<input type="hidden" name="clbId" value="" />
	<input type="hidden" name="param_clbId" />
</form>

<!-- 프레임워크 커뮤니티 영역 -->
<div id="cmnt_wrap">
	<!-- header -->
	<div id="cmnt_header">
		<div class="visual_default">
			<h1><c:out value='${cmmntyVO.cmmntyNm}' /></h1>
			<p><c:out value='${cmmntyVO.cmmntyIntrcn}' /></p>
		</div>
		<div class="h_menu">
			<ul class="gnb">
				<li class="home"><a class="on" href="" onclick="fn_egov_goCmmntyHome(); return false;">Home</a></li>
             	<!-- 관리자 메뉴 부분 Start -->
                <c:if test="${cmmntyUser.mngrAt == 'Y'}">
                <li><a href="" onclick="fn_egov_loadMngrMenu('<c:url value="/cop/bbs/selectBBSMasterInfs.do"/>'); return false;"><spring:message code="comCopCmy.communityUseMgrMain.btnBoard" /></a></li> <!-- 게시판관리  -->
                <li><a href="" onclick="fn_egov_loadMngrMenu('<c:url value="/cop/cmy/selectCommuUserList.do"/>'); return false;"><spring:message code="comCopCmy.communityUseMgrMain.btnUser" /></a></li> <!-- 사용자관리  -->
                </c:if>
                <!-- 관리자 메뉴 부분 End -->
			</ul>
		</div>
	</div>
	<!-- header //-->
	
	<!-- container -->
	<div id="cmnt_container">
	
		<!-- lnb 영역 -->
		<div id="left_menu">
			<h2><spring:message code="comCopCmy.commuMasterVO.list.board" /></h2><!-- 게시판목록 -->
			<ul>
				<!-- 게시판 목록 부분 : Start -->
				<c:forEach var="bbs" items="${bbsList}" varStatus="status">
	            	<li><a href="" onclick="fn_egov_loadBdList('<c:out value="${bbs.bbsId}"/>','<c:out value="${bbs.bbsTyCode}"/>'); return false;"><c:out value="${bbs.bbsNm}" /></a></li>
				</c:forEach>
				<!-- 게시판 목록 부분 : End -->
			</ul>
			
			<!-- 회원관련 부분 : Start -->
			<c:if test="${isAuthenticated=='Y'}">
				<c:choose>
					<c:when test="${cmmntyUser.useAt=='Y'}">
						<div class="left_btn mt20"><a href="" onclick="fn_egov_deleteUser('<c:out value="${cmmntyVO.cmmntyId}" />'); return false;"><spring:message code="comCopCmy.communityUseMgrMain.btnMemberWithdrawal" /></a></div><!-- 탈퇴신청 -->
					</c:when>
					<c:otherwise>
						<div class="left_btn mt20"><a href="" onclick="fn_egov_registUser('<c:out value="${cmmntyVO.cmmntyId}" />'); return false;"><spring:message code="comCopCmy.communityUseMgrMain.btnMemberJoin" /></a></div><!-- 가입신청  -->
					</c:otherwise>
				</c:choose>
			</c:if>
			<!-- 회원관련 부분 : End -->
		</div>
		<!-- lnb 영역 -->
		
		<!-- contents 영역 -->
		<div id="cmnt_content">
		<iframe id="contentFrame" src="<c:url value='/cop/cmy/cmmntyMainContents.do'/>?cmmntyId=<c:out value='${cmmntyVO.cmmntyId}' />" width="768" height="680" title="<spring:message code="comCopCmy.communityUseMgrMain.titleContents" />"></iframe><!-- 컨텐츠영역 -->
		</div>
		<!-- contents 영역 //-->
	</div>
	<!-- container //-->
</div>
<!-- 프레임워크 커뮤니티 영역 //-->

</body>
</html>
