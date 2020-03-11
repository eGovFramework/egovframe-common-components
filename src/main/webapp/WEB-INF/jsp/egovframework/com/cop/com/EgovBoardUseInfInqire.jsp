<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovBoardUseInfInqire.jsp
  * @Description : 게시판  사용정보  조회화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.02   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.02
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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardUseInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_updt_bbsUseInf(){
		if (!validateBoardUseInf(document.boardUseInf)){
			return;
		}

		document.boardUseInf.action = "<c:url value='/cop/com/updateBBSUseInf.do'/>";
		document.boardUseInf.submit();
	}
	function fn_egov_select_bbsUseInfs(){
		document.boardUseInf.action = "<c:url value='/cop/com/selectBBSUseInfs.do'/>";
		document.boardUseInf.submit();
	}

</script>
<title>게시판 사용정보 수정</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

</head>
<body>
<form name="boardUseInf" method="post" action="<c:url value='/cop/com/updateBBSUseInf.do'/>">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input type="hidden" name="bbsId" value="<c:out value='${bdUseVO.bbsId}'/>" />
<input type="hidden" name="trgetId" value="<c:out value='${bdUseVO.trgetId}'/>" />

<div id="border" style="width:730px">
	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="100%"class="title_left">
	  	<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">&nbsp;게시판 사용정보 수정
	  	</h1>
	  </td>
	 </tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register"  summary="게시판명, 커뮤니티/ 동호회명, 사용여부  입니다" >
	  <tr>
	    <th scope="col" width="20%" height="23" class="" nowrap >게시판명</th>
	    <td width="80%" nowrap colspan="3">
	    <c:out value="${bdUseVO.bbsNm}" />
	    </td>
	  </tr>
	  <tr>
	    <th scope="col" width="20%" height="23" class="" nowrap >커뮤니티/ 동호회명</th>
	    <td width="80%" nowrap colspan="3">
	    <c:choose>
	    	<c:when test="${not empty bdUseVO.cmmntyNm}">
	    		<c:out value="${bdUseVO.cmmntyNm}" />
	    	</c:when>
	    	<c:when test="${not empty bdUseVO.clbNm}">
   				<c:out value="${bdUseVO.clbNm}" />
   			</c:when>
   			<c:otherwise>(시스템  활용)</c:otherwise>
		</c:choose>
	    </td>
	  </tr>
	  <tr>
	    <th scope="col" width="20%" height="23" class="required_text" nowrap >
	    	<label for="useAt">
	    		사용여부
	    	</label>
	    	<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
	    </th>
	    <td width="80%" nowrap colspan="3">
	     	<spring:message code="button.use" /> : <input type="radio" name="useAt" class="radio2" value="Y" <c:if test="${bdUseVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
	     	<spring:message code="button.notUsed" /> : <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${bdUseVO.useAt == 'N'}"> checked="checked"</c:if>>
	     	<br/><form:errors path="useAt" />
	    </td>

	  </tr>
	  <c:choose>
	  <c:when test="${not empty bdUseVO.provdUrl}">
	  <tr>
	    <th width="20%" height="23" class="" nowrap >제공 URL</th>
	    <td width="80%" nowrap colspan="3">
	    	<a href="<c:out value="${bdUseVO.provdUrl}" />" target="_new">
	    	   	<c:out value="${bdUseVO.provdUrl}" />
			</a>
	    </td>
	  </tr>
	  </c:when>
	  </c:choose>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
      <td><span class="button">
      <a href="<c:url value='/cop/com/updateBBSUseInf.do'/>" onclick="fn_egov_updt_bbsUseInf(); return false;">수정</a>
      </span></td>
      <td width="10"></td>
      <td><span class="button">
      <a href="<c:url value='/cop/com/selectBBSUseInfs.do'/>" onclick="fn_egov_select_bbsUseInfs(); return false;">목록</a>
      </span></td>
	</tr>
	</table>
	</div>
</div>
</form>
</body>
</html>
