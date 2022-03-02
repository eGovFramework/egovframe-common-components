<%
/**
 * @Class Name : EgovBndtCeckManageDetail.java
 * @Description : EgovBndtCeckManageDetail jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
 * @ 2018.09.27    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtCeckManageDetail.title"/></title><!-- 당직체크 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInit(){
}
/* ********************************************************
* 목록 으로 가기
******************************************************** */
function fncBndtCeckManageList() {
    var varFrom = document.getElementById("bndtCeckManage");
    varFrom.action = "<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>";
    varFrom.submit();       
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
 function fncEgovBndtCeckManage() {
		var varFrom = document.getElementById("bndtCeckManage");
		varFrom.cmd.value  = "updt";
		varFrom.action = "<c:url value='/uss/ion/bnt/EgovBndtCeckManage.do'/>";
		varFrom.submit();   
 }

 /* ********************************************************
  * 삭제처리화면
  ******************************************************** */

 function fncDeleteBndtCeckManage() {
     var varFrom = document.getElementById("bndtCeckManage");
     varFrom.action = "<c:url value='/uss/ion/bnt/deleteBndtCeckManage.do'/>";
     if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
         varFrom.submit();
     }
 }
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="bndtCeckManage" name="bndtCeckManage" method="post" > 
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonBnt.common.submit"/>" title="<spring:message code="comUssIonBnt.common.submit"/>"></div><!-- 전송 -->
<input type="hidden" name="cmd" value="updt" >
<input type="hidden" name="bndtCeckSe" value ="<c:out value='${bndtCeckManageVO.bndtCeckSe}'/>">
<input type="hidden" name="bndtCeckCd" value ="<c:out value='${bndtCeckManageVO.bndtCeckCd}'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonBnt.bndtCeckManageDetail.title"/></h2><!-- 당직체크 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtCeckTemp1"/> <span class="pilsu">*</span></th><!-- 당직체크구분 -->
			<td class="left">
			    <c:out value='${bndtCeckManageVO.bndtCeckTemp1}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtCeckCd"/> <span class="pilsu">*</span></th><!--당직체크코드 -->
			<td class="left">
			    <c:out value='${bndtCeckManageVO.bndtCeckCd}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtCeckCdName"/> <span class="pilsu">*</span></th><!-- 당직체크코드명 -->
			<td class="left">
			    <c:out value='${bndtCeckManageVO.bndtCeckCdNm}'/>
			</td>
		</tr>
		
		<tr>
			<th><spring:message code="comUssIonBnt.common.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
			    <c:if test="${bndtCeckManageVO.useAt == 'Y' }"> <spring:message code="comUssIonBnt.common.useAt.y"/> </c:if><!-- 사용 -->
         		<c:if test="${bndtCeckManageVO.useAt == 'N' }"> <spring:message code="comUssIonBnt.common.useAt.n"/> </c:if><!-- 미사용 -->
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/EgovBndtCeckManage.do'/>?cmd=updt&bndtCeckSe=<c:out value='${bndtCeckManageVO.bndtCeckSe}'/>&bndtCeckCd=<c:out value='${bndtCeckManageVO.bndtCeckCd}'/>" onclick="fncEgovBndtCeckManage(); return false;"><spring:message code="button.update" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/deleteBndtCeckManage.do'/>?bndtCeckSe=<c:out value='${bndtCeckManageVO.bndtCeckSe}'/>&bndtCeckCd=<c:out value='${bndtCeckManageVO.bndtCeckCd}'/>" onclick="fncDeleteBndtCeckManage(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>" onclick="fncBndtCeckManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>
