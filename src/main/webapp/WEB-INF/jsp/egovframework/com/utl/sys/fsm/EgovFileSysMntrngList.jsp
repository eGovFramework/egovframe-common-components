<%
/**
 * @Class Name : EgovFileSysMntrngList.jsp
 * @Description : 파일시스템모니터링 대상 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.18   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.18
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUtlSysFsm.fileSysMntrng.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript">

	function fn_egov_init_filesysmntrng(){

	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_filesysmntrng('1');
		}
	}

	function fn_egov_select_filesysmntrng(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_filesysmntrng(fileSysId) {
		document.frm.fileSysId.value = fileSysId;
		document.frm.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrng.do'/>";
		document.frm.submit();
	}

	function fn_egov_insert_filesysmntrng(){
		document.frm.action = "<c:url value='/utl/sys/fsm/addFileSysMntrng.do'/>";
		document.frm.submit();
	}

	function fn_egov_log_filesysmntrng(){
		document.frm.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngLogList.do'/>";
		document.frm.submit();
	}
</script>
</head>
<body onLoad="fn_egov_init_filesysmntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	
	<span>※ 1. <spring:message code="comUtlSysFsm.fileSysMntrngList.guideOne" /></span><br>
	<span>※ 2. <spring:message code="comUtlSysFsm.fileSysMntrngList.guideTwo" /></span>
	
	<form name="frm" method="post" action="<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="fileSysId">

	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCnd" class="select" title="<spring:message code='select.searchCondition' />">
					<option value="" <c:if test="${searchVO.searchCnd == ''}">selected="selected"</c:if>>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysNm.label" /></option>
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysManageNm.label" /></option>
					<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngList.mngrNm.label" /></option>
					<option value="3" <c:if test="${searchVO.searchCnd == '3'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngList.mntrngSttus.label" /></option>
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" size="27" onkeypress="press(event);" title="<spring:message code='title.search' /> <spring:message code='input.input' />" />
				
				<input class="s_btn" type="submit" value="<spring:message code='title.inquire' />" title="<spring:message code='title.inquire' />" onclick="fn_egov_select_filesysmntrng('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/fsm/addFileSysMntrng.do'/>" onclick="fn_egov_insert_filesysmntrng('1'); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				<span class="btn_b"><a href="<c:url value='/utl/sys/fsm/selectFileSysMntrngLogList.do'/>" onclick="fn_egov_log_filesysmntrng(); return false;" title='<spring:message code="button.log" />'><spring:message code="button.log" /></a></span>
			</li>
		</ul>
	</div>
	</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:20%" />
			<col style="width:8%" />
			<col style="width:11%" />
			<col style="width:11%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.seq.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysManageNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysMg.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysThrhld.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.fileSysUsgQty.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.mntrngSttus.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngList.mngrNm.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.fileSysNm}"/></td>
			    <td>
			     <form name="fileSysMntrngVO" method="post" action="<c:url value='/utl/sys/fsm/selectFileSysMntrng.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
					<input type="hidden" name="fileSysId" value="<c:out value="${result.fileSysId}"/>">
					<span class="link"><input type="submit" value="<c:out value="${result.fileSysManageNm}"/>" onclick="javascript:fn_egov_inqire_filesysmntrng('<c:out value="${result.fileSysId}"/>'); return false;" style="text-align : left;"></span>
				 </form>
				</td>
				<td><c:out value="${result.fileSysMg}"/>G</td>
				<td><c:out value="${result.fileSysThrhldRt}"/>%(<c:out value="${result.fileSysThrhld}"/>G)</td>
				<td><c:out value="${result.fileSysUsgRt}"/>%(<c:out value="${result.fileSysUsgQty}"/>G)</td>
				<td><c:out value="${result.mntrngSttus}"/></td>
			    <td><c:out value="${result.mngrNm}"/></td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="8"><spring:message code="common.nodata.msg" /></td>
			  </tr>
			 </c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_filesysmntrng"/>
		</ul>
	</div>
</div>
</body>
</html>