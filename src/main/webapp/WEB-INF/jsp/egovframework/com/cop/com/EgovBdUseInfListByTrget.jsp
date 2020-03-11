<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBdUseInfListByTrget.jsp
  * @Description : 게시판  사용정보  목록화면
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
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_bbsUseInfs('1');
		}
	}

	function fn_egov_select_bbsUseInfs(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/com/selectCmyBBSUseInfsByTrget.do'/>";
		document.frm.submit();
	}

	function fn_egov_update_bbsUseInf(bbsId, trgetId) {
		if (confirm('<spring:message code="cop.use.msg" />')) {
			document.frm.bbsId.value = bbsId;
			document.frm.param_trgetId.value = trgetId;
			document.frm.useAt.value = 'Y';
			document.frm.action = "<c:url value='/cop/com/updateBBSUseInfByTrget.do'/>";
			document.frm.submit();
		}
	}

	function fn_egov_delete_bbsUseInf(bbsId, trgetId) {
		if (confirm('<spring:message code="cop.unuse.msg" />')) {
			document.frm.bbsId.value = bbsId;
			document.frm.param_trgetId.value = trgetId;
			document.frm.useAt.value = 'N';
			document.frm.action = "<c:url value='/cop/com/updateBBSUseInfByTrget.do'/>";
			document.frm.submit();
		}
	}

	function fn_egov_insert_bbsUseInf(bbsId, trgetId) {
		if (confirm('<spring:message code="cop.use.msg" />')) {
			document.frm.bbsId.value = bbsId;
			document.frm.param_trgetId.value = trgetId;
			document.frm.useAt.value = 'Y';
			document.frm.action = "<c:url value='/cop/com/insertBBSUseInfByTrget.do'/>";
			document.frm.submit();
		}
	}
</script>

<title>게시판 사용정보 조회</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

</head>
<body>
<form name="frm" method="post" action="<c:url value='/cop/com/selectCmyBBSUseInfsByTrget.do'/>">
<input type="hidden" name="bbsId" />
<input type="hidden" name="trgetId" value='<c:out value="${trgetId}"/>' />
<input type="hidden" name="useAt" />
<input type="hidden" name="param_trgetId" />
<input type="hidden" name="trgetType" value='<c:out value="${trgetType}"/>' />

<div id="border" style="width:730px">

	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="40%"class="title_left">
	   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목버튼이미지">
	   &nbsp;게시판 사용정보 조회</td>
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
	      <td><span class="button"><input type="submit" value="조회" title="조회" onclick="javascript:fn_egov_select_bbsUseInfs(('1');return false;"></span></td>
	    </tr>
	   </table>
	  </th>
	 </tr>
	</table>
	<table width="100%" cellpadding="8" class="table-line">
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th class="title" width="10%" nowrap>번호</th>
	    <th class="title" width="37%" nowrap>게시판명</th>
	    <th class="title" width="30%" nowrap>사용자명</th>
	    <th class="title" width="10%" nowrap>사용여부</th>
	    <th class="title" width="10%" nowrap>상태변경</th>
	  </tr>
	 </thead>
	 <tbody>
	 <c:forEach var="result" items="${resultList}" varStatus="status">
	 	<tr>
	    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
	    <td class="lt_text" nowrap><c:out value="${result.bbsNm}"/></td>
	    <td class="lt_text3" nowrap><c:out value="${result.userNm}"/></td>
    	<c:choose>
    		<c:when test="${result.useAt=='Y'}">
			    <td class="lt_text3" nowrap>
			    	<spring:message code="button.use" />
			    </td>
			    <td class="lt_text3" nowrap>
			    	<input type="button" value="사용중지" onClick="javascript:fn_egov_delete_bbsUseInf('<c:out value="${result.bbsId}"/>','<c:out value="${result.trgetId}"/>');" />
			    </td>
    		</c:when>
    		<c:when test="${result.useAt=='N'}">
			    <td class="lt_text3" nowrap>
			    	<spring:message code="button.notUsed" />
			    </td>
			    <td class="lt_text3" nowrap>
			    	<input type="button" value="사용" onClick="javascript:fn_egov_update_bbsUseInf('<c:out value="${result.bbsId}"/>','<c:out value="${result.trgetId}"/>');" />
			    </td>
    		</c:when>
    		<c:otherwise>
		 	    <td class="lt_text3" nowrap>
			    	미사용
			    </td>
			    <td class="lt_text3" nowrap>
			    	<input type="button" value="사용" onClick="javascript:fn_egov_insert_bbsUseInf('<c:out value="${result.bbsId}"/>','<c:out value="${result.trgetId}"/>');" />
			    </td>
    		</c:otherwise>
    	</c:choose>

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
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_bbsUseInfs" />
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</div>
</form>
</body>
</html>
