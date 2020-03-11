<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovCmmntyView.jsp
  * @Description : 커뮤니티 목록 초기화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.08   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.08
  *  @version 1.0
  *  @see
  *
  */
%>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">

<title>커뮤니티 및 동호회 목록</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>

<div id="border" style="width:730px">
	<table width="100%" cellpadding="8" class="table-search" border="0">
	<tr>
	<th align=left><a href="#cmyList">커뮤니티 목록 바로가기</a></th>
	</tr>
	<tr>
	<th align=left><a href="#clubList">동호회 목록 바로가기</a></th>
	</tr>
	</table>
	<br>
	<table width="100%" cellpadding="8" class="table-search" border="0" id="cmyList" summary="커뮤니티 목록입니다.">
	 <tr>
	  <td width="100%"class="title_left">
	  	<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" alt="">&nbsp;커뮤니티 목록
	  	</h1>
	  </td>
	 </tr>
	 <tr>
	 	<td><c:import url="/cop/cus/CmmntyListPortlet.do" charEncoding="utf-8" />
	 	</td>
	 </tr>
	</table>
	<br>
	<table width="100%" cellpadding="8" class="table-search" border="0" id="clubList" summary="동호회 목록입니다.">
	 <tr>
	  <td width="100%"class="title_left">
	  	<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" alt="">&nbsp;동호회 목록
	  	</h1>
	  </td>
	 </tr>
	 <tr>
	 	<td><c:import url="/cop/cus/ClubListPortlet.do" charEncoding="utf-8" />
	 	</td>
	 </tr>
	</table>

</div>
</body>
</html>
