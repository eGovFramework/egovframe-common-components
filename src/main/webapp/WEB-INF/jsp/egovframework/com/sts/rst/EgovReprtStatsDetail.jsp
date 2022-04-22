<%--
/**
 * @Class Name  : EgovReprtStatsDetail.java
 * @Description : EgovReprtStatsDetail jsp
 * @Modification Information
 * @
 * @  수정일       수정자          수정내용
 * @ -------      --------    ---------------------------
 * @ 2009.08.01    lee.m.j         최초 생성
 * @ 2011.07.17    이기하          패키지 분리(sts -> sts.rst)
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<title>보고서통계 상세목록 조회</title>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javaScript" language="javascript">

function fncSelectReprtStatsList() {
    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/sts/rst/selectReprtStatsList.do'/>";
    varFrom.submit();
}

function fncReprtStatsInsert() {

    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/uss/ion/isg/addIntnetSvcGuidance.do'/>";

    if(confirm("저장 하시겠습니까?")){
        varFrom.submit();
    }
}

function fncReprtStatsDelete() {
    var varFrom = document.getElementById("reprtStats");
    varFrom.action = "<c:url value='/uss/ion/isg/removeIntnetSvcGuidance.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

</script>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form:form modelAttribute="reprtStats" method="post" action="${pageContext.request.contextPath}/sts/rst/selectReprtStatsList.do' />">

<div class="board">
	<h1>보고서통계상세정보</h1>


	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.list" />' title='<spring:message code="button.list" />' onclick="fncSelectReprtStatsList(); return false;" />
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
			   <th scope="col">보고서ID</th> <!-- 보고서ID -->
			   <th scope="col">보고서명</th> <!-- 보고서명 -->
			   <th scope="col">보고서유형</th> <!-- 보고서유형 -->
			   <th scope="col">진행상태</th> <!-- 진행상태 -->
			   <th scope="col">등록일시</th> <!-- 등록일시 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reprt" items="${reprtStats}" varStatus="status">
			   <tr>
			    <td></td>
			    <td><c:out value="${reprt.reprtId}"/></td>
			    <td><c:out value="${reprt.reprtNm}"/></td>
			    <td><c:out value="${reprt.reprtTyNm}"/></td>
			    <td><c:out value="${reprt.reprtSttusNm}"/></td>
			    <td><c:out value="${reprt.regDate}"/></td>
			    <td></td>
			  </tr>
			 </c:forEach>
		</tbody>
	</table>

</div>

<input type="hidden" name="pmReprtTy" value="<c:out value='${reprtStatsVO.pmReprtTy}'/>">
<input type="hidden" name="pmDateTy" value="<c:out value='${reprtStatsVO.pmDateTy}'/>">
<input type="hidden" name="pmFromDate" value="<c:out value='${reprtStatsVO.pmFromDate}'/>">
<input type="hidden" name="pmToDate" value="<c:out value='${reprtStatsVO.pmToDate}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${reprtStatsVO.pageIndex}'/>">
</form:form>

</body>
</html>

