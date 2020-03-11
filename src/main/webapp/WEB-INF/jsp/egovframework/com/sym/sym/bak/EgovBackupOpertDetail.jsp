<%--
  Class Name : EgovBackupOpertDetail.jsp
  Description : 백업작업 상세조회 페이지
  Modification Information

        수정일                  수정자            수정내용
    -----------    --------   ---------------------------
    2010.08.25     김진만            최초 생성
    2018.08.20     신용호            표준프레임워크 v3.8 개선

    author   : 공통서비스 개발팀 김진만
    since    : 2010.08.25

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymSymBak.backupOpertDetail.title"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
    var varForm = document.getElementById("backupOpertForm");
    varForm.action = "<c:url value='/sym/sym/bak/getBackupOpertList.do' />";
    varForm.submit()
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_update_view(){
    var varForm = document.getElementById("backupOpertForm");

    varForm.action = "<c:url value='/sym/sym/bak/getBackupOpertForUpdate.do' />";
    varForm.submit();
}
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
 function fn_egov_delete(){
        var vForm = document.backupOpertForm;
        if(confirm("<spring:message code='common.delete.msg' />")){
            vForm.action = "<c:url value='/sym/sym/bak/deleteBackupOpert.do' />";
            vForm.submit();
        }
}
</script>
</head>

<body >

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="backupOpertForm"  id="backupOpertForm" action="<c:url value='/sym/sym/bak/getBackupOpert.do'/>" method="post">
    <input name="backupOpertId" type="hidden" value="<c:out value='${resultInfo.backupOpertId}'/>"/>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default="1"/>"/>

	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comSymSymBak.backupOpertDetail.pageTop.title"/></h2><!-- 백업작업 상세조회 -->
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comSymSymBak.backupOpertDetail.backupOpertId"/></th><!-- 백업작업ID -->
				<td class="left">
					<c:out value="${resultInfo.backupOpertId}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupOpertDetail.backupOpertNm"/></th><!-- 백업작업명 -->
				<td class="left">
					<c:out value="${resultInfo.backupOpertNm}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupOpertDetail.backupOrginlDrctry"/></th><!-- 백업원본디렉토리 -->
				<td class="left">
					<c:out value="${resultInfo.backupOrginlDrctry}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupOpertDetail.backupStreDrctry"/></th><!-- 백업저장디렉토리 -->
				<td class="left">
					<c:out value="${resultInfo.backupStreDrctry}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupOpertDetail.executCycleNm"/></th><!-- 실행주기 -->
				<td class="left">
					<c:out value="${resultInfo.executCycleNm}" escapeXml="false" />&nbsp;<c:out value="${resultInfo.executSchdul}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupOpertDetail.cmprsSeNm"/></th><!-- 압축구분 -->
				<td class="left">
					<c:out value="${resultInfo.cmprsSeNm}" escapeXml="false" />
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<span class="btn_s"><a href="<c:url value='/sym/sym/bak/getBackupOpertForUpdate.do'></c:url>" onclick="fn_egov_update_view(); return false;"><spring:message code="button.update" /></a></span><!-- 수정 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete(); return false;" /><!-- 삭제 -->
			<span class="btn_s"><a href="<c:url value='/sym/sym/bak/getBackupOpertList.do'></c:url>" onclick="fn_egov_list(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
	</div>

</form>
</body>
</html>