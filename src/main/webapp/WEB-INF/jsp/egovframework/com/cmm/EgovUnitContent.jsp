<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovUnitContent.jsp
  * @Description : 로그인 성공후 컨텐츠 영역
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
<title>eGovFrame <spring:message code="comCmm.unitContent.1"/></title>
<style type="text/css">
.pwdTitleClass .ui-widget-header
{
       background-color: #F9A7AE;
       background-image: none;
       color: Black;
}
</style>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">

	var $dialog;

    $(document).ready(function () {
	
    	// 파일검색 화면 호출 함수
    	//var page = $(this).attr("href");
        //var pagetitle = $(this).attr("title");
        var pagetitle = "<spring:message code="comCmm.unitContent.20"/>"; // 비밀번호 유효기간 만료 안내
        var page = "${pageContext.request.contextPath}/uat/uia/noticeExpirePwd.do";
        $dialog = $('<div style="overflow:hidden;padding: 0px 0px 0px 0px;"></div>')
				        .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
				        .dialog({
				        	autoOpen: false,
				            modal: true,
				            width: 600,
				            height: 550,
				            title: pagetitle,
				            dialogClass: 'pwdTitleClass'
				    	});
        
<c:if test="${loginVO != null}">
	if ( ${elapsedTimeExpiration} > 0 )
		$dialog.dialog('open');
</c:if>
    });
</script>
</head>
<body>
	<c:if test="${loginVO != null}">
		${loginVO.name}(${loginVO.id})<spring:message code="comCmm.unitContent.2"/> <a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do"><spring:message code="comCmm.unitContent.3"/></a>
		<!--
		<br>passedDay = ${passedDay}
		<br>expirePwdDay = ${expirePwdDay}
		<br>elapsedTimeExpiration = ${elapsedTimeExpiration}
		-->
		<script type="text/javaScript" language="javascript">
			parent.frames["_top"].location.reload();
		</script>
	</c:if>
	<c:if test="${loginVO == null }">
		<jsp:forward page="/uat/uia/egovLoginUsr.do"/>
	</c:if>
	<p/><p/><p/>
	<font color="red"><b><spring:message code="comCmm.unitContent.0"/></b></font><br />
	<a href="https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte4.0:fdl:crypto" target="_blank">실행환경 Crypto 설정간소화 관련 위키가이드 참조</a><br /><br /><br />
	<b><spring:message code="comCmm.unitContent.4"/></b><br/><br/><!-- 실행 시 오류 사항이 있으시면 표준프레임워크센터로 연락하시기 바랍니다. -->
	<b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> <spring:message code="comCmm.unitContent.5"/></b><p/><!-- 화면 설명 -->
	<spring:message code="comCmm.unitContent.6"/><p/><!-- 왼쪽 메뉴는 메뉴와 관련된 컴포넌트(메뉴관리, 사이트맵 등)들의 영향을 받지 않으며, -->
	<spring:message code="comCmm.unitContent.7"/><p/><!-- 각 컴포넌트를 쉽게 찾아볼 수 있는 바로 가기 링크페이지입니다. -->

	<br /><b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> egovframework.com.cmm.web.EgovComIndexController.java</b><p/>

	<spring:message code="comCmm.unitContent.8"/><p/><!-- 컴포넌트 설치 후 설치된 컴포넌트들을 IncludedInfo annotation을 통해 찾아낸 후 -->
	<spring:message code="comCmm.unitContent.9"/><p/><br /><!-- 화면에 표시할 정보를 처리하는 Controller 클래스입니다. -->
	<spring:message code="comCmm.unitContent.10"/><p/><!-- 개발 시 메뉴 구조가 잡히기 전에 배포 파일들에 포함된 공통 컴포넌트들의 목록성 화면에 URL을 제공하여 -->
	<spring:message code="comCmm.unitContent.11"/><p/><!-- 개발자가 편리하게 활용할 수 있도록 작성되었습니다. -->
	<spring:message code="comCmm.unitContent.12"/> <p/><!-- 운영 시에 본 컨트롤을 사용하여 메뉴를 구성하는 경우, -->
	<spring:message code="comCmm.unitContent.13"/><p/><!-- 성능 문제를 일으키거나 사용자별 메뉴 구성에 오류를 발생할 수 있기 때문에 -->
	<spring:message code="comCmm.unitContent.14"/><p /><!-- 실 운영 시에는 삭제해서 배포하는 것을 권장해 드립니다. -->
</body>
</html>