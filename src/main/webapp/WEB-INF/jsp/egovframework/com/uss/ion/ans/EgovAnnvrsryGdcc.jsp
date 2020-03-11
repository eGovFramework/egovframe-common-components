<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovAnnvrsryGdcc.java
 * @Description : EgovAnnvrsryGdcc.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.30    이      용      최초 생성
 * @ 2018.09.19    최 두 영       다국어처리 
 * @ 2018.10.04    최 두 영       퍼블리싱 점검 
 *
 *  @author 이      용
 *  @since 2010.06.30
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
<title><spring:message code="comUssIonAns.annvrsryGdcc.title"/></title><!-- 기념일 상세 조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/*설명 : 기념일 목록 조회 */
function fncSelectAnnvrsryManageList(pageNo){
    document.DetailForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>";
    document.DetailForm.submit();
}

-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">

	<form name="DetailForm" action="<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>" method="post">    
		<input type="hidden" name="cmd" >
		<input type="hidden" name="annId"       value="<c:out value="${annvrsryManageVO.annId}"/>">
		<input type="hidden" name="usid"       value="<c:out value="${annvrsryManageVO.usid}"/>">
		<input type="hidden" name="annvrsrySe" value="<c:out value="${annvrsryManageVO.annvrsrySe}"/>">
		<input type="hidden" name="annvrsryNm" value="<c:out value="${annvrsryManageVO.annvrsryNm}"/>">
		<input type="hidden" name="annvrsryDe" value="<c:out value="${annvrsryManageVO.annvrsryDe}"/>">
		<input type="hidden" name="cldrSe"     value="<c:out value="${annvrsryManageVO.cldrSe}"/>">
	</form>

	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonAns.annvrsryGdcc.intro"/></h2><!-- 기념일 안내 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp1"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value="${annvrsryManageVO.annvrsryTemp1}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp2"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value="${annvrsryManageVO.annvrsryTemp2}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp4"/></th><!-- 기념일자 -->
			<td class="left">
			    <c:out value="${annvrsryManageVO.annvrsryTemp4}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryNm"/></th><!-- 기념일명 -->
			<td class="left">
			   <c:out value="${annvrsryManageVO.annvrsryNm}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.memo"/></th><!-- 메모 -->
			<td class="left">
			    <textarea id="textArea" title="<spring:message code="comUssIonAns.common.memo"/>" style="width:100%;height:100px;" readOnly><c:out value="${annvrsryManageVO.memo}"/></textarea>
			</td>
		</tr>
		<tr>
			<th>D-day</th><!-- 메모 -->
			<td class="left">
			    D-<c:out value="${annvrsryManageVO.annvrsryBeginDe}"/>&nbsp;
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>?searchCondition=1" onclick="fncSelectAnnvrsryManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>
