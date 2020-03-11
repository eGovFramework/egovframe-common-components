<%--
  Class Name : EgovRecentSrchwrdModify.jsp
  Description : 최근검색어관리 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2011.12.14    이기하          최근검색어관리번호 추가
     2018.08.16    이정은          공통컴포넌트 3.8 개선  

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonRsm.recentSrchwrdUpdt.recentSrchwrdUpdt"/></title><!-- 최근검색어관리 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="recentSrchwrd" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_RecentSrchwrd(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_RecentSrchwrd(){
	location.href = "<c:url value='/uss/ion/rsm/listRecentSrchwrd.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_RecentSrchwrd(){
	var varFrom = document.recentSrchwrd;
	varFrom.action =  "<c:url value='/uss/ion/rsm/updtRecentSrchwrd.do' />";
	if(confirm("<spring:message code="common.save.msg" />")){

		if(!validateRecentSrchwrd(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	}
}


</script>
</head>
<body onLoad="fn_egov_init_RecentSrchwrd();">
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="recentSrchwrd" name="recentSrchwrd" action="${pageContext.request.contextPath}/uss/ion/rsm/updtRecentSrchwrd.do" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonRsm.recentSrchwrdUpdt.recentSrchwrdUpdt"/></h2><!-- 최근검색어관리 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:22%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdUpdt.srchwrdManageId"/></th><!-- 최근검색어관리번호 -->
			<td class="left">
			    <c:out value='${recentSrchwrd.srchwrdManageId}' />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdUpdt.srchwrdManageNm"/> <span class="pilsu">*</span></th><!-- 최근검색어관리명 -->
			<td class="left">
			    <form:input path="srchwrdManageNm" maxlength="255"/>
      			<form:errors path="srchwrdManageNm" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdUpdt.srchwrdManageUrl"/> <span class="pilsu">*</span></th><!-- 최근검색어관리URL -->
			<td class="left">
			    <form:input path="srchwrdManageUrl" maxlength="255"/>
      			<form:errors path="srchwrdManageUrl" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRsm.recentSrchwrdUpdt.srchwrdManageUseYn"/> <span class="pilsu">*</span></th><!-- 사용자검색여부 -->
			<td class="left">
			    <select name="srchwrdManageUseYn" title="<spring:message code="ussIonRsm.recentSrchwrdUpdt.srchwrdManageUseYn"/>">
					<option value="N" <c:if test="${recentSrchwrd.srchwrdManageUseYn == 'N'}">selected</c:if>>N</option>
					<option value="Y" <c:if test="${recentSrchwrd.srchwrdManageUseYn == 'Y'}">selected</c:if>>Y</option>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_RecentSrchwrd(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/rsm/listRecentSrchwrd.do' />" onclick=""><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<input name="srchwrdManageId" type="hidden" value="${recentSrchwrd.srchwrdManageId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</form:form>
</DIV>
</body>
</html>
