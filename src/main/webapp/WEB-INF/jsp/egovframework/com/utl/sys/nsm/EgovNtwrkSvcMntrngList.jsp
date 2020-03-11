<% 
/**
 * @Class Name : EgovNtwrkSvcMntrngList.jsp
 * @Description : 네트워크서비스모니터링 목록조회
 * @Modification Information
 * @
 * @ 수정일               수정자          수정내용
 * @ ----------   --------  ---------------------------
 * @ 2010.08.17   장철호          최초 생성
 * @ 2018.11.02   신용호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.17
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
<c:set var="pageTitle"><spring:message code="comUtlSysNsm.ntwrkSvcMntrng.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript">

	function fn_egov_init_ntwrksvcmntrng(){
		
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_ntwrksvcmntrng('1');
		}
	}
	
	function fn_egov_select_ntwrksvcmntrng(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_ntwrksvcmntrng(sysIp, sysPort) {
		document.frm.sysIp.value = sysIp;
		document.frm.sysPort.value = sysPort;
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrng.do'/>";
		document.frm.submit();	
	}

	function fn_egov_insert_ntwrksvcmntrng(){	
		document.frm.action = "<c:url value='/utl/sys/nsm/addNtwrkSvcMntrng.do'/>";
		document.frm.submit();
	}

	function fn_egov_log_ntwrksvcmntrng(){	
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>";
		document.frm.submit();
	}
</script>
</head>
<body onLoad="fn_egov_init_ntwrksvcmntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

	<form name="frm" method="post" action="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="sysIp">
	<input type="hidden" name="sysPort">

	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCnd" class="select" title="<spring:message code='select.searchCondition' />">
				<option value="">--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.sysNm.label" /></option>
				<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.sysIp.label" /></option>
				<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.mngrNm.label" /></option>
				<option value="3" <c:if test="${searchVO.searchCnd == '3'}">selected="selected"</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.mntrngSttus.label" /></option>
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" size="27" onkeypress="press(event);" title="검색어 입력" />
				
				<input class="s_btn" type="submit" value="<spring:message code='title.inquire' />" title="<spring:message code='title.inquire' />" onclick="fn_egov_select_ntwrksvcmntrng('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/utl/sys/nsm/addNtwrkSvcMntrng.do'/>" onclick="fn_egov_insert_ntwrksvcmntrng('1'); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
				<span class="btn_b"><a href="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>" onclick="fn_egov_log_ntwrksvcmntrng(); return false;" title='<spring:message code="button.log" />'><spring:message code="button.log" /></a></span><!-- 로그 -->
			</li>
		</ul>
	</div>
	
	</form>

	<table class="board_list">
		<caption>네트워크서비스모니터링대상 목록</caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:30%" />
			<col style="width:15%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.num.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.sysIp.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.sysPort.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.sysNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.mntrngSttus.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngList.mngrNm.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	
			    <td><c:out value="${result.sysIp}"/></td>
			    <td><c:out value="${result.sysPort}"/></td>
			    <td>
			     <form name="ntwrkSvcMntrngVO" method="post" action="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrng.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
					<input type="hidden" name="sysIp" value="<c:out value="${result.sysIp}"/>">
					<input type="hidden" name="sysPort" value="<c:out value="${result.sysPort}"/>">
					<span class="link"><input type="submit" value="<c:out value="${result.sysNm}"/>" onclick="fn_egov_inqire_ntwrksvcmntrng('<c:out value="${result.sysIp}"/>', '<c:out value="${result.sysPort}"/>'); return false;" style="text-align : left;"></span>
				 </form>
				</td>
				<td><c:out value="${result.mntrngSttus}"/></td>
			    <td><c:out value="${result.mngrNm}"/></td>
			  </tr>
			 </c:forEach>	  
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="6"><spring:message code="common.nodata.msg" /></td>  
			  </tr>		 
			 </c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_ntwrksvcmntrng"/>
		</ul>
	</div>
</div>

</body>
</html>