<%--
/**
 * @Class Name  : EgovDtaUseStatsDetail.java
 * @Description : EgovDtaUseStatsDetail.jsp
 * @Modification Information
 * @
 * @  수정일       수정자          수정내용
 * @ -------      --------    ---------------------------
 * @ 2009.08.01    lee.m.j         최초 생성
 * @ 2011.07.17    이기하          패키지 분리(sts -> sts.dst)
 *
 *  @author lee.m.j
 *  @since 2009.08.17
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
<title>${pageTitle}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javaScript" language="javascript">

function fncSelectDtaUseStatsList() {
    var varFrom = document.getElementById("dtaUseStats");
    varFrom.action = "<c:url value='/sts/dst/selectDtaUseStatsList.do'/>";
    varFrom.submit();
}


</script>
</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="dtaUseStats" method="post" action="${pageContext.request.contextPath}/sts/dst/selectDtaUseStatsList.do">
<div class="board">
	<h1>${pageTitle}</h1>


	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.list" />' title='<spring:message code="button.list" />' onclick="fncSelectDtaUseStatsList(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:3%" />
			<col style="width:30%" />
			<col style="width:25%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:2%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"></th>
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.resultsDetail.col1"/></th> <!-- 글제목 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.resultsDetail.col2"/></th> <!-- 첨부파일명 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.resultsDetail.col3"/></th> <!-- 사용자ID -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.resultsDetail.col4"/></th> <!-- 사용자명 -->
			   <th scope="col"><spring:message code="comStsDst.dtaUseStats.resultsDetail.col5"/></th> <!-- 다운로드일시 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dtaUseStats" items="${dtaUseStatsList}" varStatus="status">
			   <tr>
			    <td></td>
			    <td><c:out value="${dtaUseStats.nttSj}"/></td>
			    <td><c:out value="${dtaUseStats.fileNm}"/></td>
			    <td><c:out value="${dtaUseStats.userId}"/></td>
			    <td><c:out value="${dtaUseStats.userNm}"/></td>
			    <td><c:out value="${dtaUseStats.regdate}"/></td>
			    <td></td>
			  </tr>
			 </c:forEach>
		</tbody>
	</table>

</div>

<input type="hidden" name="pmDateTy" value="<c:out value='${dtaUseStatsVO.pmDateTy}'/>">
<input type="hidden" name="pmFromDate" value="<c:out value='${dtaUseStatsVO.pmFromDate}'/>">
<input type="hidden" name="pmToDate" value="<c:out value='${dtaUseStatsVO.pmToDate}'/>">
<input type="hidden" name="searchCondition" value="<c:out value='${dtaUseStatsVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${dtaUseStatsVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${dtaUseStatsVO.pageIndex}'/>">
</form:form>

</body>
</html>

