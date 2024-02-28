<%
 /**
  * @Class Name  : EgovWebStandardInspection.jsp
  * @Description : 웹표준검사
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2010.10.05  박종선                  최초 생성
  *
  *  @author 공통서비스팀 
  *  @since 2010.10.05
  *  @version 1.0
  *  @see
  *  
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="none"%>
<%@page import="java.util.*"  %>
<%@page import="java.util.regex.*"  %>
<%@page import="java.text.*"  %>
<%@page import="java.io.*"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>웹표준 검사</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/css/egovframework/com/cmm/utl/com.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/egovframework/com/uss/ion/rss/jquery.js"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 저장처리화면
 ******************************************************** a*/
function fn_egov_init_WebStandardInspection(){
	


	
}
/* ********************************************************
 * 화면 검사
 ******************************************************** a*/
function fn_egov_submit_WebStandardInspection(form, nNmm){
	
	document.getElementById('divErr'+nNmm).innerHTML='';
	document.getElementById('divWar'+nNmm).innerHTML='';
	
	//url 검사 모드시
	/*
	if(form.rdoUri[1].checked){
		var name=form.uri.value;
		var reg_name=/(127\.0\.0\.1|localhost)/g;     
		  
		if( reg_name.test(name) ){ 
		alert("Url 검사 에서는[localhost/127.0.0.1] 해당 주소를 검사 할수 없습니다.!");
		return false;
		} 
	
	}
	*/
	
	if(form.uri.value == ''){
		alert('웹표준검사 URL을 입력해주세요!');
		form.uri.focus();
		return false;
	} 
	
	if(form.rdoUri[0].checked == true){
		form.action='/EgovPageLink.do?linkIndexIndex=5';
	}else{
		form.action='/EgovPageLink.do?linkIndexIndex=6';
	}
	
	return true;
       
}

/* ********************************************************
 * 상세보기 스크립트
 ******************************************************** */
 function fn_egov_submit_WebStandardInspectionLink(form){
	
	if(form.uri.value == ''){
		alert('웹표준검사 URL을 입력해주세요!');
		form.uri.focus();
		return;
	} 
	
	 document.formHidden.uri.value = form.uri.value;

	 if(form.rdoUri[0].checked == true){
		 document.formHidden.action = "/EgovPageLink.do?linkIndex=7";
	 }else{
		 document.formHidden.action = "http://validator.w3.org/check";
	 }
	 
	 document.formHidden.submit();
}

</script>
<style type="text/css">
.btnNew {
border : 0 solid #000;
color : #000000;
background-image : url(/images/egovframework/com/cmm/uss/umt/button/bu2_bg.gif);
cursor : pointer;
}
</style>
</head>
<body onLoad="fn_egov_init_WebStandardInspection()">
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 상단 타이틀  영역 -->
<table width="100%" cellpadding="8" class="table-search" border="0" summary="상단 타이틀을 제공한다.">
 <tr>
  <td width="100%"class="title_left">
   <h1><img src="/images/egovframework/com/cmm/uss/umt/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align:middle; display:inline-block;" alt="">&nbsp;웹표준 검사</h1></td>
 </tr>
</table>

<!--  줄간격조정  -->
<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="줄간격을제공한다.">
<tr>
	<td height="3px"></td>
</tr>
</table>

<!--  상단  등롬폼 영역 START -->
<table width="100%" border="1" cellpadding="0" cellspacing="1" class="table-register" 
summary="이 표는 웹표준검사 대상 정보를 제공하며, URL명, Public IP 여부, Warniing, 상세보기 정보로 구성되어 있습니다 .">
<tr> 
	<th scope="col" height="23" class="required_text"><center>URL명</center></th>
	<th scope="col" width="170px" height="23" class="required_text" align="center"><center>Public IP 여부</center></th>
	<th scope="col" width="90px" height="23" class="required_text" align="center"><center>Errors</center></th>
	<th scope="col" width="90px" height="23" class="required_text" align="center"><center>Warniing</center></th>
	<th scope="col" width="70px" height="23" class="required_text" align="center"><center>상세보기</center></th>

</tr>
</table>
<%
	for(int i=1 ; i<10; i++){ 
%>
<form name="webInspection" method="post" action="/EgovPageLink.do?linkIndex=6" target="ifr_hidden">
<table width="100%" border="1" cellpadding="0" cellspacing="1" class="table-register" summary=" ">
<tr> 
	<td style="padding:2px 2px 2px 2px;" height="23">
	<input name="uri" type="text" size="73" value="" maxlength="250" style="width:98%;">
	</td>
	<td width="170px" align="center">
	<div style="float:left;">
	<input type="radio" name="rdoUri" value="dUri">Private<input type="radio" name="rdoUri" value="uri" checked>Public&nbsp;&nbsp;
	</div>
	<div style="float:left;">
	<table border="0" cellspacing="0" cellpadding="0" align="center" summary="목록/저장버튼을 제공한다.">
	<tr>       
	  <td>&nbsp;</td>
	  <td><img src="/images/egovframework/com/cmm/uss/umt/btn/bu2_left.gif"  width="8" height="20"></td>
	  <td class="btnBackground" nowrap><input type="submit" value="검색" name="btnSearch" onClick="if(fn_egov_submit_WebStandardInspection(this.form,'<%=i%>') == false){return false;};" class="btnNew" style="height:20px;width:26px;" > 
	  </td>
	  <td><img src="/images/egovframework/com/cmm/uss/umt/btn/bu2_right.gif"  width="8" height="20"></td>
	</tr>
	</table>

	</div>
	</td>
	<td width="90px" align="center"><div id="divErr<%=i%>">&nbsp;</div></td>
	<td width="90px" align="center"><div id="divWar<%=i%>">&nbsp;</div></td>
	<td width="70px" align="center">

	<table border="0" cellspacing="0" cellpadding="0" align="center" summary="목록/저장버튼을 제공한다.">
	<tr>       
	  <td>&nbsp;</td>
	  <td><img src="/images/egovframework/com/cmm/uss/umt/btn/bu2_left.gif"  width="8" height="20"></td>
	  <td class="btnBackground" nowrap><input type="button" value="보기" name="btnSearch" onClick="fn_egov_submit_WebStandardInspectionLink(this.form);" class="btnNew" style="height:20px;width:26px;" ></td>
	  <td><img src="/images/egovframework/com/cmm/uss/umt/btn/bu2_right.gif"  width="8" height="20"></td>
	</tr>
	</table>
	<input name="num" type="hidden" value="<%=i%>">
	</td>
</tr> 
</table>
</form>
<%
	}
%>

<!--  줄간격조정  -->
<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="화면 줄간격을 조정한다.">
<tr>
	<td height="3px"></td>
</tr>

</table>
<!--  Hiden frame  visibility: hidden;  -->
<iframe name="ifr_hidden" id="ifr_hidden1" src="about:blank;" style="width:100%;height:400px;visibility: hidden;"></iframe>
<form name="formHidden" id="formHidden" action="http://validator.w3.org/check" method="post" target="_blank" >
<input name="uri" type="hidden" value="">
</form>
</DIV>
</body>
</html>
