<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovSmsInfoList.jsp
  * @Description : 문자메시지 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.06.18   한성곤          최초 생성
  * @ 2018.09.20   이정은          공통컴포넌트 3.8 개선
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.18
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_sms('1');
		}
	}

	function fn_egov_insert_sms() {
		document.frm.action = "<c:url value='/cop/sms/addSms.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_sms(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/sms/selectSmsList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_sms(smsId) {
		document.frm.smsId.value = smsId;
		document.frm.action = "<c:url value='/cop/sms/selectSms.do'/>";
		document.frm.submit();
	}
</script>
<title><spring:message code="cop.sms.textMassageList"/></title><!-- 문자메시지 목록 -->

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="cop.sms.textMassageList"/></h1><!-- 문자메시지 목록 -->
	<form name="frm" method="post" action="<c:url value='/cop/sms/selectSmsList.do'/>">
	<input type="hidden" name="smsId" value="<c:out value=""/>">
	<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">

	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="select.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="cop.sms.recptnTelno"/></option><!-- 수신전화번호 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="cop.sms.trnsmitCn"/></option><!-- 전송내용 -->
				</select>
				<input class="s_input2 vat" type="text" name="searchWrd" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="<spring:message code="title.search"/>" /><!-- 검색단어입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire"/>" title="<spring:message code="button.inquire"/>" onclick="fn_egov_select_sms('1'); return false;" /><!-- 조회 -->
				<span class="s_btn"><a href="<c:url value='/cop/sms/addSms.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="fn_egov_insert_sms(); return false;"><spring:message code="cop.sms.send"/></a></span><!-- 전송 -->
			</li>
		</ul>
	</div>
	</form>
	
	<table class="board_list">
		<caption><spring:message code="cop.sms.textMassageList"/></caption><!-- 문자메시지 목록 -->
		<colgroup>
			<col style="width:20%" />
			<col style="" />
			<col style="" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 번호 -->
			   <th scope="col"><spring:message code="cop.sms.frstRegisterPnttm"/></th><!-- 전송일시 -->
			   <th scope="col"><spring:message code="cop.sms.trnsmitTelno"/></th><!-- 발신전화번호 -->
			   <th scope="col"><spring:message code="cop.sms.recptnCnt"/></th><!-- 수신전화번호수 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td>	
				<a href="<c:url value='/cop/sms/selectSms.do'/>?smsId=${result.smsId}&pageIndex=${searchVO.pageIndex}" onClick="fn_egov_inqire_sms('<c:out value="${result.smsId}"/>');return false;"><c:out value='${result.frstRegisterPnttm}'/></a>	
				
				</td>
				<td><c:out value="${result.trnsmitTelno}"/></td>
				<td><c:out value="${result.recptnCnt}"/></td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="4"><spring:message code="common.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_sms"/>
		</ul>
	</div>
</div>

</body>
</html>
