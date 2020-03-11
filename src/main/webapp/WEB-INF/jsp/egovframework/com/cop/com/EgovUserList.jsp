<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovUserList.jsp
  * @Description : 사용자 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.06   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.06
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
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_userInfo('1');
		}
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

	function fn_egov_deleteUser(emplyrId) {
		var _target = document.frm.targetMethod.value;
		var url;

		if (confirm('<spring:message code="cop.withdraw.msg" />')) {

			if (_target == 'selectCmmntyUserList') {
				url = "<c:url value='/cop/com/deleteCmmntyUser.do'/>";
				document.frm.param_cmmntyId.value = document.frm.trgetId.value;
			} else if (_target == 'selectClubUserList') {
				url = "<c:url value='/cop/com/deleteClbUser.do'/>";
				document.frm.param_clbId.value = document.frm.trgetId.value;
			}
			document.frm.param_emplyrId.value = emplyrId;
			document.frm.action = url;
			document.frm.submit();
		}
	}

	function fn_egov_reRegistUser(emplyrId){
		var _target = document.frm.targetMethod.value;
		var url;

		if (confirm('<spring:message code="cop.reregist.msg" />')) {
			if (_target == 'selectCmmntyUserList') {
				url = "<c:url value='/cop/com/reRegistCmmntyUser.do'/>";
				document.frm.param_cmmntyId.value = document.frm.trgetId.value;
			} else if (_target == 'selectClubUserList') {
				url = "<c:url value='/cop/com/reRegistClbUser.do'/>";
				document.frm.param_clbId.value = document.frm.trgetId.value;
			}

			document.frm.param_emplyrId.value = emplyrId;
			document.frm.action = url;
			document.frm.submit();
		}
	}

	function fn_egov_registManager(emplyrId) {
		var _target = document.frm.targetMethod.value;
		var url;

		if (confirm('<spring:message code="cop.registmanager.msg" />')) {
			if (_target == 'selectCmmntyUserList') {
				url = "<c:url value='/cop/com/registCmmntyManager.do'/>";
				document.frm.param_cmmntyId.value = document.frm.trgetId.value;
			} else if (_target == 'selectClubUserList') {
				url = "<c:url value='/cop/com/registClbOprtr.do'/>";
				document.frm.param_clbId.value = document.frm.trgetId.value;
			}
			document.frm.param_emplyrId.value = emplyrId;
			document.frm.action = url;
			document.frm.submit();
		}
	}
</script>
<title>사용자 목록</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>
<form name="frm" action ="" method="post">
<input type="hidden" name="targetMethod" value="${targetMethod}" />
<input type="hidden" name="trgetId" value="${trgetId}" />
<input type="hidden" name="param_emplyrId" />
<input type="hidden" name="param_cmmntyId" />
<input type="hidden" name="param_clbId" />

<div id="border" style="width:730px">
	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="40%"class="title_left">
	   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목버튼이미지">
	   &nbsp;사용자 목록</td>
	  <th >
	  </th>
	  <td width="10%" >
	   		<select name="searchCnd" class="select" title="검색조건선택">
			   <!-- option selected value=''--><!--선택하세요--><!-- /option-->
			   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >사용자명</option>
		   </select>
		</td>
	  <td width="35%">
	    <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색단어입력">
	  </td>
	  <th width="10%">
	   <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
	    <!-- 
	      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="button left"></td>
	      <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
	      <a href="javascript:fn_egov_select_userInfo('1')">조회</a>
	      </td>
	      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td>
	     -->
		  <td><span class="button"><input type="submit" value="조회" title="조회" onclick="javascript:fn_egov_select_userInfo('1');return false;"></span></td>
	    </tr>
	   </table>
	  </th>
	 </tr>
	</table>
	<table width="100%" cellpadding="8" class="table-line"  summary="번호, 사용자아이디 , 사용자명, 주소, 이메일, 비고   목록입니다">
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th scope="col" class="title" width="8%" nowrap>번호</th>
	    <th scope="col" class="title" width="12%" nowrap>사용자아이디</th>
	    <th scope="col" class="title" width="13%" nowrap>사용자명</th>
	    <th scope="col" class="title" width="35%" nowrap>주소</th>
	    <th scope="col" class="title" width="12%" nowrap>이메일</th>
	    <th scope="col" class="title" width="20%" nowrap>비고</th>
	  </tr>
	 </thead>
	 <tbody>

		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.userId}" /></td>
		    <td class="lt_text3" nowrap><c:out value="${result.userNm}" /></td>
		    <td class="lt_text3" nowrap><c:out value="${result.userAdres}" /></td>
		    <td class="lt_text3" nowrap><c:out value="${result.userEmail}" /></td>
		    <td class="lt_text3" nowrap>
		    	<c:choose>
		    		<c:when test="${result.useAt == 'Y'}">
		    			<input type="button" value="탈퇴"  onClick="javascript:fn_egov_deleteUser('<c:out value="${result.uniqId}"/>')" />
		    			|<input type="button" value="운영진등록" onClick="javascript:fn_egov_registManager('<c:out value="${result.uniqId}"/>');" />
		    		</c:when>
		    		<c:otherwise>
		    			|<input type="button" value="재가입" onClick="javascript:fn_egov_reRegistUser('<c:out value="${result.uniqId}"/>');" />
		    		</c:otherwise>
		    	</c:choose>
		    </td>
		  </tr>
		 </c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td class="lt_text3" nowrap colspan="6" ><spring:message code="common.nodata.msg" /></td>
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
</div>
</form>
</body>
</html>
