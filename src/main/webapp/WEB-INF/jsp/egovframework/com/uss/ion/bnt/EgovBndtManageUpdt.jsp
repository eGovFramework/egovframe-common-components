<%
/**
 * @Class Name : EgovBndtManageUpdt.java
 * @Description : EgovBndtManageUpdt jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영         퍼블리싱 검증
 * @ 2018.09.27    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtManageUpdt.title"/></title><!-- 당직  수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>";
}
/* ********************************************************
* 저장처리화면
******************************************************** */
function fncUpdtBndtManage() {
    var varFrom = document.getElementById("bndtManageVO");
    if(!validateBndtManage(varFrom)){           
        return;
    }
    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
        varFrom.submit();
    }
}
<c:if test="${!empty errorMessage}">alert("${errorMessage}");</c:if>
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="bndtManageVO" name="bndtManageVO" method="post" action="${pageContext.request.contextPath}/uss/ion/bnt/updtBndtManage.do">
<form:hidden  path="bndtId" />
<form:hidden  path="bndtDe" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonBnt.bndtManageUpdt.title"/></h2><!-- 당직 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtIdName"/> <span class="pilsu">*</span></th><!-- 당직자명 -->
			<td class="left">
			    <c:out value='${bndtManageVO.bndtTemp1}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${bndtManageVO.bndtTemp2}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtDe"/> <span class="pilsu">*</span></th><!-- 당직일자 -->
			<td class="left">
			    <c:out value='${bndtManageVO.bndtDe}'/>
			</td>
		</tr>
		<tr>
			<th><label for="remark"><spring:message code="comUssIonBnt.common.remark"/></label></th><!-- 비고 -->
			<td class="left">
			    <form:textarea path="remark" rows="4" cols="70" cssClass="txaClass" title="<spring:message code='comUssIonBnt.common.remark'/>" /><!-- 비고 -->
      			<div><form:errors path="remark" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtBndtManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>" onclick="fncBndtManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>

</body>
</html>
