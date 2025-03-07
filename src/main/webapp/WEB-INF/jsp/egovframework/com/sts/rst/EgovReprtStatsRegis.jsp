<%--
/**
 * @Class Name  : EgovReprtStatsRegis.java
 * @Description : EgovReprtStatsRegis jsp
 * @Modification Information
 * @
 * @  수정일       수정자          수정내용
 * @ -------      --------    ---------------------------
 * @ 2009.02.01    lee.m.j         최초 생성
 * @ 2011.07.17    이기하          패키지 분리(sts -> sts.rst)
 *
 *  @author lee.m.j
 *  @since 2009.07.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comStsDst.dtaUseStats.title"/> <spring:message code="title.detail"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<title>보고서통계자료 등록</title>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="reprtStats" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectReprtStatsList() {
    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/sts/rst/selectReprtStatsList.do'/>";
    varFrom.submit();
}

function fncReprtStatsInsert() {

    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/sts/rst/addReprtStats.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateReprtStats(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncReprtStatsUpdate() {
    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/sts/rst/updtReprtStats.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateRoleManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncReprtStatsDelete() {
    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/sts/rst/removeReprtStats.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

</script>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form:form modelAttribute="reprtStats" method="post" action="${pageContext.request.contextPath}/sts/rst/addReprtStats.do' />">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>보고서 등록</h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<tr>
			<th><label for="reprtNm">보고서명 <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<input name="reprtNm" id="reprtNm" type="text" maxLength="10" size="30" title="<spring:message code="sts.title" />">&nbsp;<form:errors path="reprtNm" />
			</td>
		</tr>
		<tr>
			<th><label for="reprtTy">보고서유형 <span class="pilsu">*</span></label></th>
			<td class="left">
		        <select name="reprtTy" id="reprtTy" title="<spring:message code="sts.category" />">
		            <c:forEach var="cmmCode040" items="${cmmCode040List}" varStatus="status">
		            <option value="<c:out value="${cmmCode040.code}"/>" ><c:out value="${cmmCode040.codeNm}"/></option>
		            </c:forEach>
		        </select>
			</td>
		</tr>
		<tr>
			<th><label for="reprtSttus">진행상태 <span class="pilsu">*</span></label></th>
			<td class="left">
		        <select name="reprtSttus" id="reprtSttus" title="<spring:message code="sts.status" />">
		            <c:forEach var="cmmCode036" items="${cmmCode036List}" varStatus="status">
		            <option value="<c:out value="${cmmCode036.code}"/>" ><c:out value="${cmmCode036.codeNm}"/></option>
		            </c:forEach>
		        </select>
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" onclick="fncReprtStatsInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/sts/rst/selectReprtStatsList.do'/>?pageIndex=<c:out value='${reprtStatsVO.pageIndex}'/>&amp;pmReprtTy=<c:out value="${reprtStatsVO.pmReprtTy}"/>&amp;pmDateTy=<c:out value="${reprtStatsVO.pmDateTy}"/>&amp;pmFromDate=<c:out value="${reprtStatsVO.pmFromDate}"/>&amp;pmToDate=<c:out value="${reprtStatsVO.pmToDate}"/>" onclick="fncSelectReprtStatsList(); return false;"><spring:message code="button.list" /></a></a></span>
	</div><div style="clear:both;"></div>
	
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="pmReprtTy" value="<c:out value='${reprtStatsVO.pmReprtTy}'/>">
<input type="hidden" name="pmDateTy" value="<c:out value='${reprtStatsVO.pmDateTy}'/>">
<input type="hidden" name="pmFromDate" value="<c:out value='${reprtStatsVO.pmFromDate}'/>">
<input type="hidden" name="pmToDate" value="<c:out value='${reprtStatsVO.pmToDate}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${reprtStatsVO.pageIndex}'/>">
<!-- 검색조건 유지 -->
</form:form>

</body>
</html>

