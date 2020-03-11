<%
/**
 * @Class Name : EgovMtgPlaceResveDplactCeck.java
 * @Description : EgovMtgPlaceResveDplactCeck jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.08.02    이      용                최초 생성
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
<title><spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.title" /></title><!-- 회의실 중복체크 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	 * Window Close()  처리
	 ******************************************************** */
	 /*설명 : 중복체크 결과값 리턴및 화면 close */
	 function fn_egov_dplact_ceck() {
		getDialogArguments();	
		var opener = parent.window.dialogArguments;
		var sDplactCeck = null;
		if(dplactCeckForm.dplactCeck.value == 0 ) sDplactCeck = "Y";
		else sDplactCeck = "N";
		opener[0].document.getElementById("dplactCeck").value = sDplactCeck;
		parent.window.close();
	}
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div id="border" style="width:420px">
<table>
  <tr>
    <td width="400">
<!-- ********** 여기서 부터 본문 내용 *************** -->
<form name="dplactCeckForm" method="post">
    <input type="hidden" name="dplactCeck" value="<c:out value='${dplactCeck}'/>">
</form>

<table width="100%" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="30%"class="title_left"></td>
  <td width="50%">&nbsp;</td>
  <th width="20%" align="right">
  </th>  
 </tr>
</table>
<!--  등록  폼 영역  -->
<table width="400" border="0" cellpadding="0" cellspacing="0"  summary="중복여부" >
<caption><spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.doubleCheck" /></caption>
  <tr>
    <td width="100%">
    	<c:if test="${dplactCeck > 0}">
			<b>
			<font size="4">
			<spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.impossible" /><P>
			<spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.doubleTime" />
			</font>
			</b>
        </c:if>
    	<c:if test="${dplactCeck == 0}">
			<b>
			<font size="4">
			<spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.possible" />
			</font>
			</b>
        </c:if> 
    </td>    
  </tr> 
</table>
<table border="0" cellspacing="0" cellpadding="0">
 <tr> 
   <td width="45%">&nbsp;</td>     
   <td width="10%">&nbsp;</td> 
   <td width="45%">&nbsp;</td> 
 </tr>
  <tr> 
   <td width="45%">&nbsp;</td>     
   <td width="10%">&nbsp;</td> 
   <td width="45%">&nbsp;</td> 
 </tr>
 <tr> 
   <td width="45%">&nbsp;</td>     
   <td width="10%" align="center"><span class="btn_s"><a href="" onclick="javascript:fn_egov_dplact_ceck(); return false;"><spring:message code="comUssIonMtg.mtgPlaceResveDplactCeck.close" /></a></span></td>     	
   <td width="45%">&nbsp;</td> 
 </tr>
</table>
<!-- ********** 여기까지 내용 *************** -->
</td>
</tr>
</table>   
</div>
</body>
</html>