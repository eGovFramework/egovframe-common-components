<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovSysHistInqire.jsp
  * @Description : 시스템 이력 정보 상세조회 화면
  * @Modification Information
  * @
  * @ 수정일             수정자             수정내용
  * @ ----------  --------   ---------------------------
  * @ 2009.03.11  이삼섭             최초 생성
  *   2011.07.08  이기하             패키지 분리로 경로 수정(sym.log -> sym.log.slg)
  *   2018.09.28  신용호             공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.11
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymLogSlg.sysHistInqire.title"/></title><!-- 시스템 이력 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
function fn_egov_update_sysHist(){
	document.frm.action = "<c:url value='/sym/log/slg/ModifySysHistory.do'/>";
	document.frm.submit();
}

function fn_egov_delete_sysHist(){
	document.frm.action = "<c:url value='/sym/log/slg/DeleteSysHistory.do'/>";
	document.frm.submit();
}

function fn_egov_select_sysHist(){
	document.frm.action = "<c:url value='/sym/log/slg/SelectSysHistoryList.do'/>";
	document.frm.submit();
}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form name="frm" action ="<c:url value='/sym/log/slg/SelectSysHistoryList.do'/>" method="post">
<input name="pageIndex" type="hidden" value="1" />
<input type="hidden" name="histId" value="<c:out value='${result.histId}'/>" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymLogSlg.sysHistInqire.pageTop.title"/></h2><!-- 시스템 이력 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistInqire.histSeCodeNm"/></th><!-- 이력 구분 -->
			<td class="left">
			    <c:out value="${result.histSeCodeNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistInqire.sysNm"/></th><!-- 시스템명 -->
			<td class="left">
			    <c:out value="${result.sysNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistInqire.histCn"/></th><!-- 이력내용 -->
			<td class="left">
			    <textarea name="histCn" class="textarea"  cols="40" rows="8"  style="height:100px;" id="histCn" readonly><c:out value="${result.histCn}"/></textarea>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistInqire.frstRegisterPnttm"/></th><!-- 등록일자 -->
			<td class="left">
			    <c:out value="${result.frstRegisterPnttm}"/>
			</td>
		</tr>
		<c:if test="${result.atchFileId != ''}">
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistInqire.atchFileList"/></th><!-- 첨부파일목록 -->
			<td class="left">
			    <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
			</td>
		</tr>
		</c:if>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_update_sysHist();" />
		<span class="btn_s"><a href="" onclick="fn_egov_delete_sysHist(); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="" onclick="fn_egov_select_sysHist(); return false;"><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form>
</body>
</html>
