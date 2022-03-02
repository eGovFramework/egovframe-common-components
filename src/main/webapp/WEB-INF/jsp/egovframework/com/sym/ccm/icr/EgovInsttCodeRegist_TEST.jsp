<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovInsttCodeRegist.jsp
  * @Description : EgovInsttCodeRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
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
<title>기관코드수신 등록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="InsttCode" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_InsttCode(){
	location.href = "<c:url value='/sym/ccm/icr/getInsttCodeRecptnList.do'/>";
}
/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_egov_regist_InsttCode(form){
	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateInsttCode(form)){
			return;
		}else{
			form.submit();
		}
	}
}
-->
</script>
</head>

<body>
<DIV id="content" style="width:712px">
<!-- 상단타이틀 -->
<form:form modelAttribute="InsttCode" name="InsttCode" method="post">
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
<input name="insttId" type="hidden" value="<c:out value='AUTO'/>"/>

<!-- 상단 타이틀  영역 -->
<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="100%"class="title_left">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;기관코드수신 등록</td>
 </tr>
</table>
<!-- 줄간격조정  -->
<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>

<!-- 등록  폼 영역  -->
<table width="700" border="0" cellpadding="0" cellspacing="1" class="table-register">
  <tr>
    <th width="50%" height="23" class="required_text" nowrap >파일내용취합...<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" alt="필수입력표시"  width="15" height="15"></th>
    <td width="50%" nowrap>    <textarea title="" rows="10" cols="55">${buf}</textarea>    </td>
  </tr>

  <tr>
    <th width="50%" height="23" class="required_text"  >${systemCmdFull_LIST}<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" alt="필수입력표시"  width="15" height="15"></th>
    <td>    <textarea title="" rows="10" cols="55">${systemCmdFull_LIST_LOG}</textarea>    </td>
  <tr>
    <th width="50%" height="23" class="required_text"  >${systemCmdFull_MSG}<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" alt="필수입력표시"  width="15" height="15"></th>
    <td>    <textarea title="" rows="10" cols="55">${systemCmdFull_MSG_LOG}</textarea>    </td>
  </tr>

  <tr>
    <td colspan="2">    <textarea title="" rows="10" cols="55"> </textarea>    </td>
  </tr>


</table>
<table width="700" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10"></td>
  </tr>
</table>

<!-- 줄간격조정  -->
<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>
<!-- 목록/저장버튼  -->
<table border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
  <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" alt="목록" width="8" height="20"></td>
  <td style="background-image:URL("<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />") class="text_left" nowrap><a href="javascript:fn_egov_list_InsttCode()">목록</a></td>
  <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" alt="목록" width="8" height="20"></td>
  <td width="10"></td>

  <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" alt="저장" width="8" height="20"></td>
  <td style="background-image:URL("<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />") class="text_left" nowrap><a href="JavaScript:javascript:fn_egov_regist_InsttCode(document.InsttCode);">저장</a></td>
  <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" alt="저장" width="8" height="20"></td>
</tr>
</table>

</form:form>
</DIV>
</body>
</html>
