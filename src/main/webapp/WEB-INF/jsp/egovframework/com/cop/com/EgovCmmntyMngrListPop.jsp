<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovCmmntyMngrList.jsp
  * @Description :  커뮤니티 관리자 목록화면
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
<script type="text/javascript">
	function fn_egov_returnUserInfo(uniqId) {
		document.frm.emplyrId.value = uniqId;
		document.frm.action = "<c:url value='/cop/cmy/deleteCmmntyUserBySelf.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_userInfo(pageIndex) {
		var _target = document.frm.targetMethod.value;
		var actionUrl;

		if (_target == 'selectClubOprtrList') {
			actionUrl = "<c:url value='/cop/com/selectClubOprtrList.do'/>";
		} else if (_target == 'selectClubUserList') {
			actionUrl = "<c:url value='/cop/com/selectClubUserList.do'/>";
		} else if (_target == 'selectCmmntyMngrList') {
			actionUrl = "<c:url value='/cop/com/selectCmmntyMngrList.do'/>";
		} else if (_target == 'selectCmmntyUserList') {
			actionUrl = "<c:url value='/cop/com/selectCmmntyUserList.do'/>";
		} else {
			actionUrl = "<c:url value='/cop/com/selectUserList.do'/>";
		}
		document.frm.pageIndex.value = pageIndex;
		document.frm.action = actionUrl;
		document.frm.submit();
	}

	function fn_egov_close(){
		window.close();
	}
</script>
<title>승인자 선택</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>
<form name="frm" action ="" method="post">
<input type="hidden" name="targetMethod" value="${targetMethod}" />
<input type="hidden" name="trgetId" value="${trgetId}" />
<input type="hidden" name="PopFlag" value="S" />
<input type="hidden" name="cmmntyId" value="${trgetId}" />
<input type="hidden" name="emplyrId" />

	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="100%"class="title_left">
	   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle" alt="제목버튼이미지">
	   &nbsp;승인자 선택</td>
	 </tr>
	</table>
	<table width="100%" cellpadding="8" class="table-line">
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th class="title" width="10%" nowrap>번호</th>
	    <th class="title" width="30%" nowrap>사용자아이디</th>
	    <th class="title" width="40%" nowrap>사용자명</th>
 	    <th class="title" width="20%" nowrap>선택</th>
	  </tr>
	 </thead>
	 <tbody>

		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.userId}" /></td>
		    <td class="lt_text3" nowrap><c:out value="${result.userNm}" /></td>
			<td class="lt_text3" nowrap>
			<c:if test="${result.useAt == 'Y'}">
	    		<input type="button" name="selectUser" value="선택"
		    			onClick="javascript:fn_egov_returnUserInfo('<c:out value="${result.uniqId}" />');"  />
			</c:if>
			</td>
		  </tr>
		 </c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td class="lt_text3" nowrap colspan="4" ><spring:message code="common.nodata.msg" /></td>
		  </tr>
		 </c:if>

	 </tbody>
	 <!--tfoot>
	  <tr class="">
	   <td colspan=6 align="center"></td>
	  </tr>
	 </tfoot -->
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_userInfo" />
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
      <a href="javascript:fn_egov_close()">닫기</a>
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td>
	</tr>
	</table>
	</div>
</form>
</body>
</html>
