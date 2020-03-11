<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovConfirmInfUpdt.jsp
  * @Description : 승인 정보 수정화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.13   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.13
  *  @version 1.0
  *  @see
  *
  */
%>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css"> 
<script type="text/javascript">
	function fn_egov_regist_updtConfirmInf() {
		if (confirm('<spring:message code="common.update.msg" />')) {
			document.frm.action = "<c:url value='/cop/com/updateConfirmRequest.do'/>";
			document.frm.submit();
		}
	}

	function fn_egov_select_confirmList() {
		var target = document.frm.trgetId.value;
		var actionUrl;

		if (target == '') {
			actionUrl = "<c:url value='/cop/com/selectConfirmRequest.do'/>";
		} else {
			actionUrl = "<c:url value='/cop/com/selectConfirmRequestByTrget.do'/>";
		}

		document.frm.action = actionUrl;
		document.frm.submit();
	}
</script>
<title>승인정보 수정</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>
<form name="frm" method="post" action="<c:url value='/cop/com/updateConfirmRequest.do'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="confmNumber" value="<c:out value='${historyVO.confmNumber}' />" />
<input type="hidden" name="trgetJobId" value="<c:out value='${historyVO.trgetJobId}' />" />
<input type="hidden" name="confmRqesterId" value="<c:out value='${historyVO.confmRqesterId}' />" />
<input type="hidden" name="confmerId" value="<c:out value='${historyVO.confmerId}' />" />
<input type="hidden" name="confmTyCode" value="<c:out value='${historyVO.confmTyCode}' />" />
<input type="hidden" name="opertId" value="<c:out value='${historyVO.opertId}' />" />

<input type="hidden" name="trgetId" value="<c:out value='${searchVO.trgetId}' />" />

<div id="border" style="width:730px">
	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="100%"class="title_left">
		<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목버튼이미지">&nbsp;승인정보수정
	  	</h1>
	  </td>
	 </tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register">
	  <tr>
	    <th width="20%" height="23" class="" nowrap >승인유형</th>
	    <td width="80%" nowrap>
	      <c:out value="${historyVO.confmTyCodeNm}" />
	    </td>
	  </tr>
	  <tr>
	    <th width="20%" height="23" class="" nowrap >승인요청자</th>
	    <td width="80%" nowrap>
	      <c:out value="${historyVO.confmRqesterNm}" />
	    </td>
	  </tr>
	  <tr>
	    <th height="23" class="required_text" >승인상태
	    <img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
	    <td>
	     	<select name="confmSttusCode" class="select" title="승인상태선택">
	  		   <option selected value=''>--선택하세요--</option>
			<c:forEach var="result"  items="${typeList}" varStatus="status">
				<option value='<c:out value="${result.code}"/>' <c:if test="${historyVO.confmSttusCode == result.code }">selected="selected"</c:if> >
				<c:out value="${result.codeNm}"/></option>
			</c:forEach>
	  	   </select>
	    </td>
	  </tr>

	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	<!-- 
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
      <a href="javascript:fn_egov_regist_updtConfirmInf();">수정</a>
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      <td width="10"></td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
      <a href="javascript:fn_egov_select_confirmList();">목록</a>
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td>
    -->
       <td><span class="button"><input type="submit" value="수정" title="수정" onclick="javascript:fn_egov_regist_updtConfirmInf();return false;"></span></td>
       <td width="10"></td>
       <td><span class="button"><input type="submit" value="목록" title="목록" onclick="javascript:fn_egov_select_confirmList();return false;"></span></td>
	</tr>
	</table>
	</div>
</div>
</form>
</body>
</html>
