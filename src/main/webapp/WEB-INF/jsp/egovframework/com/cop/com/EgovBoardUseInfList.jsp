<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBoardUseInfList.jsp
  * @Description : 게시판  사용정보  목록화면
  * @Modification Information
  * @
  * @  수정일     	     수정자            		수정내용
  * @ -------     --------    ---------------------------
  * @ 2009.04.02   이삼섭          최초 생성
  *   2011.09.15   서준식         동호회, 커뮤니티 사용 체크 로직 추가
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

	function fn_egov_select_bbsUseInfs(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/com/selectBBSUseInfs.do'/>";
		document.frm.submit();
	}
	function fn_egov_insert_addbbsUseInf(){
		document.frm.action = "<c:url value='/cop/com/addBBSUseInf.do'/>";
		document.frm.submit();
	}
	function fn_egov_select_bbsUseInf(bbsId, trgetId){
		document.frm.bbsId.value = bbsId;
		document.frm.trgetId.value = trgetId;
		document.frm.action = "<c:url value='/cop/com/selectBBSUseInf.do'/>";
		document.frm.submit();
	}

</script>
<title>게시판 사용정보 조회</title>

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

<form name="frm" method="post" action = "<c:url value='/cop/com/selectBBSUseInf.do'/>">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="bbsId" >
<input type="hidden" name="trgetId" >

	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="40%"class="title_left">
	  	<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">&nbsp;게시판 사용정보 조회
	  	</h1>
	  </td>
	  <th >
	  </th>
	  <td width="10%" >
		   <select name="searchCnd" class="select" title="선택">
			   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >게시판명</option>
			   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >사용 커뮤니티명</option>
			   <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >사용 동호회명</option>
		   </select>
		</td>
	  <td width="35%">
	    <input name="searchWrd" type="text" size="35" title="검색단어입력" value='<c:out value="${searchVO.searchWrd}" />'  maxlength="35" onkeypress="press(event);">
	  </td>
	  <th width="10%">
	   <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td><span class="button">
	      <a href="<c:url value='/cop/com/selectBBSUseInfs.do'/>" onclick="fn_egov_select_bbsUseInfs('1'); return false;">조회</a>
	      </span></td>
	      <td>&nbsp;&nbsp;</td>
	      <td><span class="button">
	      <a href="<c:url value='/cop/com/addBBSUseInf.do'/>" onclick="fn_egov_insert_addbbsUseInf(); return false;">등록</a>
	      </span></td>
	    </tr>
	   </table>
	  </th>
	 </tr>
	</table>

	<table width="100%" cellpadding="8" class="table-line"  summary="번호,게시판명,사용 커뮤니티 명,사용 동호회 명,등록일시,사용여부   목록입니다">
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th scope="col" class="title" width="10%" nowrap>번호</th>


	    <c:choose>
	    	<c:when test="${useCommunity == 'true'}">
	    		<th scope="col" class="title" width="30%" nowrap>게시판명</th>
			    <th scope="col" class="title" width="20%" nowrap>사용 커뮤니티 명</th>
			    <th scope="col" class="title" width="15%" nowrap>사용 동호회 명</th>
	    	</c:when>
	    	<c:otherwise>
	    		<th scope="col" class="title" width="65%" nowrap>게시판명</th>
	    	</c:otherwise>
	    </c:choose>

	    <th scope="col" class="title" width="15%" nowrap>등록일시</th>
	    <th scope="col" class="title" width="7%" nowrap>사용여부</th>
	  </tr>
	 </thead>
	 <tbody>
	 <c:forEach var="result" items="${resultList}" varStatus="status">
	 	<tr>
	    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
	    <!-- 2011.09.15 -->
	    <c:choose>
	    	<c:when test="${useCommunity == 'true'}">
	    		<td class="lt_text" nowrap>

			    	<!-- 2010.11.1
			    	<form name="item" method="post" action="<c:url value='/cop/com/selectBBSUseInf.do'/>"  target="_blank">
			        	<input type=hidden name="bbsId" value="<c:out value="${result.bbsId}"/>">
			        	<input type=hidden name="trgetId" value="<c:out value="${result.trgetId}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${result.bbsNm}"/>" onclick="fn_egov_select_bbsUseInf('<c:out value="${result.bbsId}"/>','<c:out value="${result.trgetId}"/>'); return false;"></span>
					</form>
					-->

		        	<input type=hidden name="bbsId" value="<c:out value="${result.bbsId}"/>">
		        	<input type=hidden name="trgetId" value="<c:out value="${result.trgetId}"/>">

					<a href="<c:url value='/cop/com/selectBBSUseInf.do'/>?bbsId=<c:out value='${result.bbsId}'/>&amp;trgetId=<c:out value='${result.trgetId}'/>" onclick="">
						<c:out value="${result.bbsNm}"/>
					</a>

			    </td>
			    <td class="lt_text3" nowrap><c:out value="${result.cmmntyNm}"/></td>
			    <td class="lt_text3" nowrap><c:out value="${result.clbNm}"/></td>
	    	</c:when>
	    	<c:otherwise>
	    		<td class="lt_text" nowrap>

		        	<input type=hidden name="bbsId" value="<c:out value="${result.bbsId}"/>">
		        	<input type=hidden name="trgetId" value="<c:out value="${result.trgetId}"/>">

					<a href="<c:url value='/cop/com/selectBBSUseInf.do'/>?bbsId=<c:out value='${result.bbsId}'/>&amp;trgetId=<c:out value='${result.trgetId}'/>" onclick="">
						<c:out value="${result.bbsNm}"/>
					</a>

			    </td>
	    	</c:otherwise>
	    </c:choose>



	    <td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td>
	    <td class="lt_text3" nowrap>
	    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    </td>
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
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_select_bbsUseInfs" />
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>
</div>

</body>
</html>
