<%
 /**
  * @Class Name : EgovAdressBookList.jsp
  * @Description : 등록한 주소록목록 조회
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.09.25   윤성록        최초 생성
  *   2016.08.16   장동한        표준프레임워크 v3.6 개선
  *   2016.12.13   최두영        JSP명 변경
  *  @author 공통서비스팀
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comCopAdb.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_adbkInfs('1');
		}
	}

	function fn_egov_select_adbkInfs(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/adb/selectAdbkList.do'/>";
		document.frm.submit();
	}

	function fn_egov_search_adbkInfs(){
		document.frm.pageIndex.value = 1;
		document.frm.action = "<c:url value='/cop/adb/selectAdbkList.do'/>";
		document.frm.submit();
	}
	
	function fn_egov_addadbkInf(){
		location.href = "<c:url value='/cop/adb/addAdbkInf.do'/>";
	}

	function fn_egov_deleteAdbk(adbkId, frm){

		if(confirm("<spring:message code="common.delete.msg" />")){	
			frm.adbkId.value = adbkId;
			frm.action = "<c:url value='/cop/adb/deleteAdbkInf.do'/>";
			frm.submit();
		}

		return false;
	}

	function fn_egov_update_adbkInf(adbkId){
		document.item.adbkId.value = adbkId;
		document.item.action = "<c:url value='/cop/adb/updateAdbkInf.do'/>";
		document.item.submit();
		return true;
	}
</script>

</head>
<body>


<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

<!-- 검색영역 -->
<form:form name="frm" action="${pageContext.request.contextPath}/cop/adb/selectAdbkList.do" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
			<select name="searchCnd" class="select" title="<spring:message code="title.searchCondition" /><spring:message code="input.cSelect" />"> <!-- 검색조건선택 -->
				<option value="" <c:if test="${searchVO.searchCnd == ''}">selected="selected"</c:if>><spring:message code="input.select" /></option><!-- 선택하세요 -->
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopAdb.searchCondition.searchCnd0" /></option><!-- 주소록명 -->
				<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopAdb.searchCondition.searchCnd1" /></option><!-- 공개범위 -->
				<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comCopAdb.searchCondition.searchCnd2" /></option><!-- 등록자 -->
		   </select>
			</li><!-- 검색조건선택 -->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" onClick="fn_egov_search_adbkInfs();return false;"/>
				<span class="btn_b"><a href="<c:url value='/cop/adb/addAdbkInf.do'/>" onClick="javascript:fn_egov_addadbkInf();return false;"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
<input type="hidden" name="adbkId" value = "'<c:out value="${searchVO.adbkId}" />'" >
</form:form>


	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: ;">
		<col style="width: 20%;">
		
		
		<col style="width: 7%;">
		<col style="width: 10%;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopAdb.list.adbkNm" /></th><!-- 주소록명 -->
		<th><spring:message code="comCopAdb.list.othbcScope" /></th><!-- 공개범위 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
		<th><spring:message code="title.delete" /></th><!-- 삭제 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left">
	
		<form name="subForm" method="post" action="<c:url value='/cop/adb/updateAdbkInf.do'/>">
			<input name="adbkId" type="hidden" value="<c:out value="${result.adbkId}"/>">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			<span class="link"><input type="submit" value="<c:out value="${result.adbkNm}"/>" style="border:0px solid #e0e0e0;"></span>
		</form>

		</td>
		<td><c:out value="${result.othbcScope}"/></td>
		<td><c:out value="${result.wrterId}"/></td>
		<td><c:out value="${fn:substring(result.frstRegisterPnttm, 0, 10)}"/></td>
		<td>
		
		<form name="subFormDelete" method="post" action="<c:url value='/cop/adb/deleteAdbkInf.do'/>" onsubmit="fn_egov_deleteAdbk('<c:out value="${result.adbkId}" />', this.form); return false;">
		    <input name="adbkId" type="hidden" value="<c:out value="${result.adbkId}"/>">
		    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		    <input type="submit" class="btn_submit" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" />
		</form>
				
		</td>
		
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty searchVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_adbkInfs"/></ul>
		</div>
	</c:if>

</div><!-- end div board -->




</body>
</html>
