<%
/**
 * @Class Name : EgovRwardManageDetail.java
 * @Description : EgovRwardManageDetail.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
 * @ 2018.09.19    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<title><spring:message code="comUssIonRwd.rwardManageDetail.title"/></title><!-- 휴가상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<validator:javascript formName="rwardManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEgovRwardManageList(){
	location.href = "<c:url value='/uss/ion/rwd/selectRwardManageList.do'/>";
}
<c:if test="${rwardManageVO.confmAt eq 'A' }">
/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fncEgovRwardManage() {
		var varFrom = document.getElementById("rwardManage");
		varFrom.cmd.value  = "updt";
		varFrom.action = "<c:url value='/uss/ion/rwd/EgovRwardManageDetail.do'/>";
		varFrom.submit();   
}
/* ********************************************************
* 삭제처리화면
******************************************************** */
function fncDeleteRwardManage() {
	    var varFrom = document.getElementById("rwardManage");
	    varFrom.action = "<c:url value='/uss/ion/rwd/deleteRwardManage.do'/>";
	    if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
            varFrom.submit();
	    }
}
</c:if>
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="rwardManage" id="rwardManage" method="post" >
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonRwd.common.submit"/>" title="<spring:message code="comUssIonRwd.common.submit"/>"></div><!-- 전송 -->
<input type="hidden" name="rwardId" value="<c:out value='${rwardManageVO.rwardId}'/>"/>
<input type="hidden" name="cmd" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonRwd.rwardManageDetail.title"/></h2><!-- 포상상세조회 -->
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonRwd.common.searchNm"/></h3><!-- 포상자 -->

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonRwd.common.searchNm"/> <span class="pilsu">*</span></th><!-- 포상자 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardManNm}'/>
			</td>
			<th><spring:message code="comUssIonRwd.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${rwardManageVO.orgnztNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.searchKeyword"/> <span class="pilsu">*</span></th><!-- 포상구분 -->
			<td class="left" colspan="3">
				<c:out value='${rwardManageVO.rwardCdNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.rwardNm"/> <span class="pilsu">*</span></th><!-- 포상명 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardNm}'/>
			</td>
			<th><spring:message code="comUssIonRwd.common.rwardDe"/> <span class="pilsu">*</span></th><!-- 포상일 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardDe}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.pblenCn"/> <span class="pilsu">*</span></th><!-- 공적사항 -->
			<td class="left" colspan="3">
			   <textarea id="remark" name="pblenCn" class="txaClass" rows="4" cols="70" title="<spring:message code="comUssIonRwd.common.pblenCn"/>" readOnly><c:out value='${rwardManageVO.pblenCn}' /></textarea><!-- 공적사항 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.atchFileId"/> </th><!-- 첨부파일 -->
			<td class="left" colspan="3">
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
					<c:param name="param_atchFileId" value="${rwardManageVO.atchFileId}" />
				</c:import>
				&nbsp
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonRwd.common.sanctnDtNm"/></h3><!-- 승인권자 -->
	
	<!-- 결재권자 지정 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8">
		<c:param name="infrmlSanctnId" value="${rwardManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 지정 Include -->
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${rwardManageVO.confmAt eq 'A' }">
		<span class="btn_s"><a href="<c:url value='/uss/ion/rwd/EgovRwardManageDetail.do'/>?cmd=updt&rwardId=<c:out value='${rwardManageVO.rwardId}'/>" onclick="fncEgovRwardManage(); return false;"><spring:message code="button.update" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/rwd/deleteRwardManage.do'/>?cmd=delete&rwardId=<c:out value='${rwardManageVO.rwardId}'/>&infrmlSanctnId=<c:out value='${rwardManageVO.infrmlSanctnId}'/>" onclick="fncDeleteRwardManage(); return false;"><spring:message code="button.delete" /></a></span>
        </c:if>
        <span class="btn_s"><a href="<c:url value='/uss/ion/rwd/selectRwardManageList.do'/>?searchCondition=1" onclick="fncEgovRwardManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form>
</body>
</html>