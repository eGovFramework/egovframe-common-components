<%
/**
 * @Class Name : EgovBndtManageDetail.java
 * @Description : EgovBndtManageDetail jsp
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtManageDetail.title"/></title><!-- 당직  상세 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<validator:javascript formName="bndtManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>";
}

/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fncEgovBndtManage() {
	var varForm = document.getElementById("bndtManage");
	varForm.action = "<c:url value='/uss/ion/bnt/EgovBndtManageDetail.do'/>";
	varForm.submit();   
}
/* ********************************************************
* 삭제처리화면
******************************************************** */

function fncDeleteBndtManage() {
   var varFrom = document.getElementById("bndtManage");
   varFrom.action = "<c:url value='/uss/ion/bnt/deleteBndtManage.do'/>";
   if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
       varFrom.submit();
   }
}

-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="bndtManage" id="bndtManage" method="post">  
<input name="cmd" type="hidden" value="updt"/>
<input name="bndtId" type="hidden" value="<c:out value='${bndtManageVO.bndtId}'/>"/>
<input name="bndtDe" type="hidden" value="<c:out value='${bndtManageVO.bndtDe}'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonBnt.bndtManageDetail.title"/></h2><!-- 당직 상세 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
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
			<th><spring:message code="comUssIonBnt.common.remark"/></th><!-- 비고 -->
			<td class="left">
			    <textarea id="remark" name="remark" class="txaClass" rows="4" cols="70" title="<spring:message code="comUssIonBnt.common.remark"/>" readonly="readonly"><c:out value='${bndtManageVO.remark}'/></textarea><!-- 비고 -->
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="title.update"/>" onclick="fncEgovBndtManage(); return false;" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/deleteBndtManage.do'/>?bndtId=<c:out value='${bndtManageVO.bndtId}'/>&bndtDe=<c:out value='${bndtManageVO.bndtDe}'/>" onclick="fncDeleteBndtManage(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>" onclick="fncBndtManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form>

</body>
</html>
