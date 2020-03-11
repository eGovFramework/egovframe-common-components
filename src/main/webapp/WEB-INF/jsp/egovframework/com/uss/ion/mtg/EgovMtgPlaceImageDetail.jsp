<%
/**
 * @Class Name : EgovMtgPlaceImageDetail.java
 * @Description : EgovMtgPlaceImageDetail jsp
 * @Modification Information
 * @
 * @  수정일             수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.08.02    이      용          최초 생성
 * @ 2018.08.20    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.07    최 두 영           CSS변경
 * @ 2018.09.12    최 두 영           다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.02
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<title><spring:message code="comUssIonMtg.mtgPlaceResveRegist.image" /></title><!-- 회의실 이미지 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * Window Close()  처리
 ******************************************************** */
 /*설명 : 중복체크 결과값 리턴및 화면 close */
 function fn_egov_image_close() {
		parent.window.close();
	}
 
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
	<div class="wTableFrm" style="width:625px; ">
		<h2><spring:message code="comUssIonMtg.mtgPlaceResveRegist.image" /></h2><!-- 회의실 이미지 -->
	
		<form name="imageForm" method="post">
			<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonMtg.mtgPlaceResveDetail.submit" />" title="<spring:message code="comUssIonMtg.mtgPlaceResveDetail.submit" />"></div>
		</form>
	
			<!--  등록  폼 영역  -->
			<div style="width:600px; height:290px; text-align:center">
				<c:forEach var="fileVO" items="${fileList}" varStatus="status">
			 		  <img style="max-width:100%" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&fileSn=<c:out value="${fileVO.fileSn}"/>'  width="500" />
				</c:forEach>
			</div>
			
			<!-- 첨부파일 테이블 레이아웃 End.-->
			<div style="padding-top:18px; text-align:center">
				<span class="btn_s"><a href="" onclick="javascript:fn_egov_image_close(); return false;"><spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.close" /></a></span>
			</div>
	<!-- ********** 여기까지 내용 *************** -->  
	</div>
</body>
</html>