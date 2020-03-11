<%--
  Class Name : EgovBackupResultDetail.jsp
  Description : 백업결과 상세조회 페이지
  Modification Information
 
        수정일                 수정자             수정내용
    ----------    --------    ---------------------------
    2010.09.07    김진만             최초 생성
    2018.08.20    신용호            표준프레임워크 v3.8 개선

    author   : 공통서비스 개발팀 김진만
    since    : 2010.09.07
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
//  백업결과의 executBeginTime, executEndTime의 화면 표시용 임시 변수 .... 
%>
<c:set var="tempDate" value=""/>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymSymBak.backupResultDetail.title"/></title><!-- 백업결과 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
    var varForm = document.getElementById("backupResultForm");
    varForm.action = "<c:url value='/sym/sym/bak/getBackupResultList.do' />";
    varForm.submit()
}
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
 function fn_egov_delete(){
        var vForm = document.backupResultForm;
        if(confirm("<spring:message code='common.delete.msg' />")){
            vForm.action = "<c:url value='/sym/sym/bak/deleteBackupResult.do' />";
            vForm.submit();
        }
}
</script>
</head>

<body >

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="backupResultForm"  id="backupResultForm" action="<c:url value='/sym/sym/bak/getBackupResult.do'/>" method="post">
    <input name="backupResultId" type="hidden" value="<c:out value='${resultInfo.backupResultId}'/>"/>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="searchKeywordFrom" value="<c:out value='${searchVO.searchKeywordFrom}'/>"/>
    <input type="hidden" name="searchKeywordTo" value="<c:out value='${searchVO.searchKeywordTo}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default="1"/>"/>


	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comSymSymBak.backupResultDetail.pageTop.title"/></h2><!-- 백업결과 상세조회 -->
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.backupResultId"/>	</th><!-- 백업결과ID -->
				<td class="left">
				    <c:out value="${resultInfo.backupResultId}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.backupOpertId"/></th><!-- 백업작업ID -->
				<td class="left">
				    <c:out value="${resultInfo.backupOpertId}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.backupOpertNm"/></th><!-- 백업작업명 -->
				<td class="left">
				    <c:out value="${resultInfo.backupOpertNm}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.backupOrginlDrctry"/></th><!-- 백업원본디렉토리 -->
				<td class="left">
				    <c:out value="${resultInfo.backupOrginlDrctry}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.backupStreDrctry"/></th><!-- 백업저장디렉토리 -->
				<td class="left">
				    <c:out value="${resultInfo.backupStreDrctry}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.backupFile"/></th><!-- 백업파일 -->
				<td class="left">
				    <c:out value="${resultInfo.backupFile}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.sttusNm"/></th><!-- 상태 -->
				<td class="left">
				    <c:out value="${resultInfo.sttusNm}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.errorInfo"/></th><!-- 에러정보 -->
				<td class="left">
				    <c:out value="${resultInfo.errorInfo}" escapeXml="false" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.executBeginTime"/></th><!-- 실행시작시각 -->
				<td class="left">
				    <fmt:parseDate value="${resultInfo.executBeginTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
            		<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comSymSymBak.backupResultDetail.executEndTime"/></th><!-- 실행종료시각 -->
				<td class="left">
				    <fmt:parseDate value="${resultInfo.executEndTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
            		<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete(); return false;" /><!-- 삭제 -->
			<span class="btn_s"><a href="<c:url value='/sym/sym/bak/getBackupResultList.do'></c:url>" onclick="fn_egov_list(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
	</div>

</form>

</body>
</html>