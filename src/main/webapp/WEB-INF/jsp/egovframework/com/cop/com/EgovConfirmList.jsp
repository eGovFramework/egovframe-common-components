<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images/egovframework/com/cop/com/"/>
<%
 /**
  * @Class Name : EgovConfirmList.jsp
  * @Description : 승인 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.13   이삼섭          최초 생성
  *		2011.9.7	정진오	승인요청자 목록이 해당 커뮤니티 또는 해당 동호회에 한하여 조회되도록 수정함
  *							trgetId가 파라미터로 서버측으로 전송되어야 하나 누락되었음. 전송되도록 수정함
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
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_confirmList('1');
		}
	}

	function fn_egov_select_confirmList(pageNo) {
		var target = document.frm.trgetId.value;
		var actionUrl;

		if (target == '') {
			actionUrl = "<c:url value='/cop/com/selectConfirmRequest.do'/>";
		} else {
			actionUrl = "<c:url value='/cop/com/selectConfirmRequestByTrget.do'/>";
		}

		document.frm.pageIndex.value = pageNo;
		document.frm.action = actionUrl;
		document.frm.submit();
	}

	function fn_egov_inqire_confirmInfo(cnfmNo,trgetId) {
		document.frm.confmNumber.value = cnfmNo;
		document.frm.trgetId.value = trgetId;
		document.frm.action = "<c:url value='/cop/com/forUpdateConfirmRequest.do'/>";
		document.frm.submit();
	}
</script>
<title>승인 목록</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}

	A:link    { color: #000000; text-decoration:none; }
	A:visited { color: #000000; text-decoration:none; }
	A:active  { color: #000000; text-decoration:none; }
	A:hover   { color: #fa2e2e; text-decoration:none; }
</style>

</head>
<body>

<div id="border" style="width:730px">
<form name="frm" action ="<c:url value='/cop/com/forUpdateConfirmRequest.do' />" method="post">
	<input type="hidden" name="confmNumber" value="0" />
	<input type="hidden" name="confmerId" value='<c:out value="${searchVO.confmerId}"/>' />

	<input type="hidden" name="trgetId" value="<c:out value="${searchVO.trgetId}"/>" />
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="40%"class="title_left">
	   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">
	   &nbsp;승인 목록</td>
	  <th >
	  </th>
	  <td width="10%" >
	   		<select name="searchCnd" class="select" title="검색조건선택">
			   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >승인유형</option>
			   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >승인상태</option>
		   </select>
		</td>
	  <td width="35%">
	    <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="35" onkeypress="press(event);" title="검색어 입력">
	  </td>
	  <th width="10%">
	   <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
	    <!-- 
	      <td><img src="<c:out value="${ImgUrl}" />btn/bu2_left.gif"  width="8" height="20"></td>
	      <td class="btnBackground" nowrap><input type="submit" value="<spring:message code="button.inquire" />" onclick="fn_egov_select_confirmList('1'); return false;" style="height:20px;width:26px;padding:0px 0px 0px 0px;" ></td>
	      <td><img src="<c:out value="${ImgUrl}" />btn/bu2_right.gif"  width="8" height="20"></td>
	     -->
	     <td><span class="button"><input type="button" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fn_egov_select_confirmList('1');return false;"></span></td>
	    </tr>
	   </table>
	  </th>
	 </tr>
	</table>
</form>
	<table width="100%" cellpadding="8" class="table-line">
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th class="title" width="10%" nowrap>번호</th>
	    <th class="title" width="15%" nowrap>승인유형</th>
	    <th class="title" width="10%" nowrap>승인상태</th>
	    <th class="title" width="22%" nowrap>승인요청자</th>
	    <th class="title" width="20%" nowrap>대상업무유형</th>
	    <th class="title" width="10%" nowrap>승인일자</th>
	  </tr>
	 </thead>

	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		    <td class="lt_text3" nowrap>
		    	<c:choose>
		    		<c:when test="${result.confmSttusCode == 'AP01'}">
						<input type="hidden" name="confmNumber" value="0" />
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
						<!-- a href="<c:url value='/cop/cmy/selectCmmntyInf.do'/>?confmNumber=0"; onclick="">
							<c:out value="${result.confmTyCodeNm}"/>
						</a-->
						<!-- a href="<c:url value='/cop/com/forUpdateConfirmRequest.do'/>?confmNumber=0"; onclick="">
							<c:out value="${result.confmTyCodeNm}"/>
						</a-->
						<!-- 2011.9.7 수정분 -->
						<a href="javascript:fn_egov_inqire_confirmInfo(<c:out value='${result.confmNumber}'/>, '<c:out value='${result.trgetJobId}'/>');">
							<c:out value="${result.confmTyCodeNm}"/>
						</a>



		    		</c:when>
		    		<c:otherwise>
		    			<c:out value="${result.confmTyCodeNm}"/>
		    		</c:otherwise>
		    	</c:choose>

		    </td>
		    <td class="lt_text3" nowrap><c:out value="${result.confmSttusCodeNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.confmRqesterNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.opertTyCodeNm}"/></td>
			<td class="lt_text3" nowrap><c:out value="${result.confmDe}"/></td	>
		  </tr>
		 </c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td class="lt_text3" nowrap colspan="6" ><spring:message code="common.nodata.msg" /></td>
		  </tr>
		 </c:if>

	 </tbody>

	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_confirmList" />
	</div>
</div>
</body>
</html>
