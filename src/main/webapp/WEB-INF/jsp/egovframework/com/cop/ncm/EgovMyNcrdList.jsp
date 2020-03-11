<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovMyNcrdList.jsp
  * @Description : 내가 등록한 명함목록 조회
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.30   이삼섭          최초 생성
  * @ 2018.09.13   최두영          다국어처리
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.30
  *  @version 1.0
  *  @see
  *
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopNcm.myNcrdList.title" /></title><!-- 나의 명함목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_ncrdInfs('1');
		}
	}
	function fn_egov_select_ncrdInfs(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/ncm/selectMyNcrdUseInf.do'/>";
		document.frm.submit();
	}
	function fn_egov_addNcrdInf(){
		document.frm.action = "<c:url value='/cop/ncm/addNcrdInf.do'/>";
		document.frm.submit();
	}
	function fn_egov_update_ncrdInf(ncrdId){
		document.frm.ncrdId.value = ncrdId;
		document.frm.action = "<c:url value='/cop/ncm/selectNcrdInf.do'/>";
		document.frm.submit();
	}
	function fn_egov_popupNcrdInf(ncrdId){
		window.open("<c:url value='/cop/ncm/selectNcrdInfPopup.do' />?ncrdId="+ncrdId,"<spring:message code="comCopNcm.ncrdList.nrcdSearch" />","width=640, height=350");
	}
</script>
</head>
<body>
<form name="frm" method="post" action="<c:url value='/cop/ncm/selectMyNcrdUseInf.do'/>">
<input type="hidden" name="ncrdId" />

<div class="board">
	<h1><spring:message code="comCopNcm.myNcrdList.title" /></h1><!-- 나의 명함목록 -->
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />"><!-- 검색조건선택 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="cop.ncrdNm" /></option><!-- 이름 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="cop.cmpnyNm" /></option><!-- 회사명 -->
					<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="cop.deptNm" /></option><!-- 부서명 -->
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value="<c:out value="${searchVO.searchWrd}"/>" title="<spring:message code="comCopNcm.ncrdList.searchWordInput" />" size="35" maxlength="35" onkeypress="press(event);" /><!-- 검색단어입력 -->
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_ncrdInfs('1'); return false;" /><!-- 조회 -->
				<input class="s_btn" type="button" value="<spring:message code="input.input" />" title="<spring:message code="input.input" />" onclick="fn_egov_addNcrdInf();" /><!-- 등록 -->
			</li>
		</ul>
	</div>
	
	<table class="board_list">
		<caption><spring:message code="comCopNcm.myNcrdList.title" /></caption><!-- 나의 명함목록 -->
		<colgroup>
			<col style="width:8%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="cop.ncrdNm" /></th><!-- 이름 -->
			   <th scope="col"><spring:message code="cop.cmpnyNm" /></th><!-- 회사명 -->
			   <th scope="col"><spring:message code="cop.deptNm" /></th><!-- 부서명 -->
			   <th scope="col"><spring:message code="table.regdate" /></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td>
					<c:choose>
			    		<c:when test="${result.frstRegisterId == uniqId}">
							<a href="<c:url value='/cop/ncm/selectNcrdInf.do?ncrdId='/><c:out value='${result.ncrdId}'/>">
							<c:out value="${result.ncrdNm}"/></a>
			    		</c:when>
			    		<c:otherwise>
							<a href="<c:url value='/cop/ncm/selectNcrdInfPopup.do?ncrdId='/><c:out value='${result.ncrdId}'/>" >
			    			<c:out value="${result.ncrdNm}"/></a>
			    		</c:otherwise>
			    	</c:choose>
				</td>
				<td><c:out value="${result.cmpnyNm}"/></td>
				<td><c:out value="${result.deptNm}"/></td>
				<td><c:out value="${result.creatDt}"/></td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="5" ><spring:message code="common.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_ncrdInfs"/>
		</ul>
	</div>

</div>

</form>
</body>
</html>
