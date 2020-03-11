<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EgovSmsInfoInqire.jsp
 * @Description : 문자메시지 상세조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.06.19   한성곤          최초 생성
 * @ 2018.09.21   이정은          공통컴포넌트 3.8 개선
 *
 *  @author 공통컴포넌트개발팀 한성곤
 *  @since 2009.06.19
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
	function fn_egov_select_smsList(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/sms/selectSmsList.do'/>";
		document.frm.submit();
	}
</script>
<title><spring:message code="cop.sms.textMassageDetail"/></title><!-- 문자메시지 상세보기 -->

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="cop.sms.textMassageDetail"/></h2><!-- 문자메시지 상세보기 -->
	
	<form name="frm" method="post" action="<c:url value='/cop/sms/selectSmsList.do'/>">

	<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="smsId" value="<c:out value='${result.smsId}'/>" >
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%;" />
			<col style="" />
			<col style="width:16%;" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="cop.sms.trnsmitTelno"/></th><!-- 발신전화번호 -->
			<td class="left" colspan="3">
			   <c:out value="${result.trnsmitTelno}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.sms.trnsmitCn"/></th><!-- 전송내용 -->
			<td class="left" colspan="3">
			    <c:out value="${result.trnsmitCn}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.sms.recptnResult"/></th><!-- 수신 및 전송결과 -->
			<td class="left" colspan="3">
			    <ul>
				<c:forEach var="recptn" items="${result.recptn}" varStatus="status">
					<li>
						<c:out value='${recptn.recptnTelno}'/> : (<c:out value='${recptn.resultCode}'/>) <c:out value='${recptn.resultMssage}'/>
					</li>
				</c:forEach>
				</ul>
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.sms.frstRegisterNm"/></th><!-- 전송자 -->
			<td class="left">
			   <c:out value="${result.frstRegisterNm}" />
			</td>
			<th><spring:message code="cop.sms.frstRegisterPnttm"/></th><!-- 전송일시 -->
			<td class="left">
			   <c:out value="${result.frstRegisterPnttm}" />
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/cop/sms/selectSmsList.do'/>?pageIndex=1" onclick="fn_egov_select_smsList('1'); return false;"><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	
	</form>
</div>

</body>
</html>
