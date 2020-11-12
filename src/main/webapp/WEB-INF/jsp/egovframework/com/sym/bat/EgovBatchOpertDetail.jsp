<%--
  Class Name : EgovBatchOpertDetail.jsp
  Description : 배치작업 상세조회 페이지
  Modification Information
 
      수정일                  수정자             수정내용
    ----------    --------    ---------------------------
     2010.07.07   김진만             최초 생성
     2018.09.04   신용호             공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.07.07
   
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
<title><spring:message code="comSymBat.batchOpertDetail.title"/></title><!-- 배치작업 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
    var varForm = document.getElementById("batchOpertForm");
    varForm.action = "<c:url value='/sym/bat/getBatchOpertList.do'/>";
    varForm.submit()
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_update_view(){
    var varForm = document.getElementById("batchOpertForm");
    
    varForm.action = "<c:url value='/sym/bat/getBatchOpertForUpdate.do'/>";
    varForm.submit();
}
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
 function fn_egov_delete(){
        var vForm = document.batchOpertForm;
        if(confirm("<spring:message code='common.delete.msg' />")){
            vForm.action = "<c:url value='/sym/bat/deleteBatchOpert.do'/>";
            vForm.submit();
        }
}
</script>
</head>

<body >

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymBat.BatchOpertDetail.pageTop.title"/></h2><!-- 배치작업 상세조회 -->
	<span>※ "배치프로그램은 globals.properties > SHELL.(UNIX/WINDOWS).batchShellFiles에 미리 등록하여야 실행이 가능하다.(WhiteList)</span>

	<form name="batchOpertForm"  id="batchOpertForm" action="<c:url value='/sym/bat/getBatchOpert.do'/>" method="post">
    <input name="batchOpertId" type="hidden" value="<c:out value='${resultInfo.batchOpertId}'/>"/>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default="1"/>"/>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymBat.batchOpertDetail.batchOpertId"/></th><!-- 배치작업ID -->
			<td class="left">
				<c:out value="${resultInfo.batchOpertId}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchOpertDetail.batchOpertNm"/></th><!-- 배치작업명 -->
			<td class="left">
				<c:out value="${resultInfo.batchOpertNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchOpertDetail.batchProgrm"/></th><!-- 배치프로그램 -->
			<td class="left">
			    <c:out value="${resultInfo.batchProgrm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchOpertDetail.paramtr"/></th><!-- 파라미터 -->
			<td class="left">
			    <c:out value="${resultInfo.paramtr}" escapeXml="false" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s2"><a href="<c:url value='/sym/bat/getBatchOpertForUpdate.do'></c:url>" onclick="fn_egov_update_view(); return false;"><spring:message code="button.update" /></a></span><!-- 수정 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete(); return false;" /><!-- 삭제 -->
		<span class="btn_s"><a href="<c:url value='/sym/bat/getBatchOpertList.do'></c:url>" onclick="fn_egov_list(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>

	</form>
</div>

</body>
</html>