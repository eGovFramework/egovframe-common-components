<%
 /**
  * @Class Name : EgovAdressBookPopup.jsp
  * @Description : 주소록 구성원등록을 위한 사용자및 명함 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------   --------    ---------------------------
  * @ 2009.09.25   윤성록        최초 생성
  *   2016.08.16   장동한        표준프레임워크 v3.6 개선
  *   2016.12.13   최두영        JSP명 변경
  *  @author 공통컴포넌트팀  윤성록
  *  @since 2009.09.25
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${searchVO.searchCnd  == '0'}"><c:set var="pageTitle"><spring:message code="comCopAdb.popopUserList.title1"/></c:set></c:if>
<c:if test="${searchVO.searchCnd  == '1'}"><c:set var="pageTitle"><spring:message code="comCopAdb.popopUserList.title2"/></c:set></c:if>
		
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_userInfo('1');
		}
	}
	function fn_egov_returnUserInfo(uniqId){

		var retVal = uniqId;
		 parent.fn_egov_returnValue(retVal);

	}

	function fn_egov_select_userInfo(pageIndex){
		document.frm.pageIndex.value = pageIndex;
		document.frm.action = "<c:url value='/cop/adb/selectManList.do'/>";
		document.frm.submit();
	}

	function fn_egov_close(){
		parent.closeWindow();
	}
</script>
<title> <c:if test="${searchVO.searchCnd  == '0'}">사용자 목록</c:if>
		<c:if test="${searchVO.searchCnd  == '1'}">명함목록</c:if> </title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

</head>
<body>


<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="popup">
<!-- 타이틀 -->
<h1>${pageTitle}</h1>
<form name="frm"  method="post" action = "<c:url value='/cop/adb/selectManList.do'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
<input type="hidden" name="PopFlag" value="Y" >
<!-- 검색영역 -->
<div class="pop_search_box" title="<spring:message code="common.searchCondition.msg" />">
	<ul>
		<li>
		<select name="searchCnd" title="<spring:message code="select.searchCondition" />">
			<option <c:if test="${searchVO.searchCnd == ''}">selected="selected"</c:if>><spring:message code="input.select" /></option><!-- 선택하세요 -->
			<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopAdb.popopUserListSearchCondition.searchUser" /></option><!-- 사용자명 -->
			<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopAdb.popopUserListSearchCondition.searchMyNcrd" /></option><!-- 명함명 -->
	   </select>
		</li><!-- 검색조건선택 -->
		<!-- 검색키워드 및 조회버튼 -->
		<li>
			<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
			<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
		</li>
	</ul>
</div>
	
<!-- 목록영역 -->
<table class="pop_board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
<caption>${pageTitle} <spring:message code="title.list" /></caption>
<colgroup>
		<col style="width: 9%;">
		
		<col style="width: 13%;">
		<col style="width: 10%;">
		
		<col style="width: ;">	
		<col style="width: 11%;">
		<col style="width: 11%;">
		<col style="width: 11%;">
		<col style="width: 11%;">
		
		<col style="width: 10%;">
<thead>
<tr>
	<th><spring:message code="table.num" /></th><!-- 번호 -->
	<c:if test="${searchVO.searchCnd == '0'}">
	<th><spring:message code="comCopAdb.popopUserList.id" /></th><!-- 사용자아이디 -->
	<th><spring:message code="comCopAdb.popopUserList.name" /></th><!-- 사용자명 -->
	 </c:if>
	<c:if test="${searchVO.searchCnd == '1'}">
	<th><spring:message code="comCopAdb.popopUserList.myNcrdId" /></th><!-- 명함아이디 -->
	<th><spring:message code="comCopAdb.popopUserList.myNcrdNm" /></th><!-- 명함명 -->
	</c:if>
	<th><spring:message code="comCopAdb.popopUserList.email" /></th><!-- 이메일 -->
	<th><spring:message code="comCopAdb.popopUserList.homeTel" /></th><!-- 집전화번호 -->
	<th><spring:message code="comCopAdb.popopUserList.mobileTel" /></th><!-- 휴대폰번호 -->
	<th><spring:message code="comCopAdb.popopUserList.officeTel" /></th><!-- 회사번호 -->
	<th><spring:message code="comCopAdb.popopUserList.faxNum" /></th><!-- 팩스번호 -->
	
	<th><spring:message code="table.select" /></th><!-- 선택 -->
</tr>
</thead>
<tbody class="ov">
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
	    
	    <c:if test="${searchVO.searchCnd == '0'}">
	    	<td class="left" ><c:out value="${result.emplyrId}" /></td>
	    </c:if>
	    <c:if test="${searchVO.searchCnd == '1'}">
	    	<td class="left" nowrap><c:out value="${result.ncrdId}" /></td>
	    </c:if>
	    <td><c:out value="${result.nm}"/></td>
	    
	    <td><c:out value="${result.emailAdres}"/></td>
	    <td><c:out value="${result.homeTelno}"/></td>
	    <td><c:out value="${result.moblphonNo}"/></td>
	    <td><c:out value="${result.offmTelno}"/></td>
	    <td ><c:out value="${result.fxnum}"/></td>
	    
		<c:if test="${searchVO.searchCnd == '0'}">
		<td>
				<button class="btn_style3" onClick="javascript:fn_egov_returnUserInfo('<c:out value="${result.emplyrId}" />'); return false;" title="<spring:message code="button.select" /> <spring:message code="input.button" />"><spring:message code="button.select" /></button>
		</td>
		</c:if>
		 <c:if test="${searchVO.searchCnd == '1'}">
		<td>
			<button class="btn_style3" onClick="javascript:fn_egov_returnUserInfo('<c:out value="${result.ncrdId}" />'); return false;" title="<spring:message code="button.select" /> <spring:message code="input.button" />"><spring:message code="button.select" /></button>
		</td>
		</c:if>
	  </tr>
	 </c:forEach>
 </tbody>
</table>

<c:if test="${!empty searchVO.pageIndex }">
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_userInfo"/></ul>
	</div>
</c:if>

<!-- 하단 버튼 -->
<div class="btn">
	<button class="btn_style3" onClick="fn_egov_close();return false;" title="<spring:message code="button.close" /> <spring:message code="input.button" />"><spring:message code="button.close" /></button>
	<div style="clear:both;"></div>
</div>
</form>
</div>
</body>
</html>
