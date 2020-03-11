<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovEmplyrList.jsp
 * @Description : 사용자 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.09.03   장철호          최초 생성
 * @ 2018.09.14   최두영          다국어처리
 * @ 2019.01.14   최두영          레이어팝업처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.09.03
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtLsm.emplyrList.title" /></title><!-- 사용자 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_emplyr('1');
		}
	}
	
	function fn_egov_select_emplyr(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/lsm/selectEmplyrList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_emplyr(uniqId, emplNo, emplyrNm, orgnztNm) {
		
		var retVal = uniqId+","+emplNo+","+emplyrNm+","+orgnztNm;
	
		parent.modalDialogCallback(retVal);
		
	}
</script>
</head>
<body style="margin:0">

<form name="frm" method="post" action="<c:url value='/cop/smt/lsm/selectEmplyrList.do'/>">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

	<div class="board">
		<h1><spring:message code="table.select" /></h1>
	</div>
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for="searchCnd"><spring:message code="select.searchCondition" /> : </label><!-- 검색조건 선택 -->
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition" />">
					<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtLsm.emplyrList.dept" /></option><!-- 부서명 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtLsm.emplyrList.employrName" /></option><!-- 사원명 -->
				</select>
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fncSelectLoginPolicyList('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	
	<table class="board_list">
		<caption><spring:message code="comCopSmtLsm.emplyrList.leaderName" /></caption><!-- 간부관리-->
		<colgroup>
			<col style="width:10%" />
			<col style="width:25%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="comCopSmtLsm.emplyrList.no" /></th><!-- 번호 -->
				<th scope="col"><spring:message code="comCopSmtLsm.emplyrList.orgnztNm" /></th><!-- 부서 -->
				<th scope="col"><spring:message code="comCopSmtLsm.emplyrList.ofcpsNm" /></th><!-- 직위 -->
				<th scope="col"><spring:message code="comCopSmtLsm.emplyrList.emplNo" /></th><!-- 사번 -->
				<th scope="col"><spring:message code="comCopSmtLsm.emplyrList.emplyrNm" /></th><!-- 사원명 -->
				<th scope="col"><spring:message code="input.select" /></th><!-- 선택 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>		    
					<td><c:out value="${result.orgnztNm}"/></td>
					<td><c:out value="${result.ofcpsNm}"/></td>
					<td><c:out value="${result.emplNo}"/></td>
					<td><c:out value="${result.emplyrNm}"/></td>
					<td>
						<input class="btn01" type="submit" value="<spring:message code="comCopSmtLsm.emplyrList.emplyrNm" />" onclick="fn_egov_inqire_emplyr('<c:out value="${result.uniqId}"/>', '<c:out value="${result.emplNo}"/>', '<c:out value="${result.emplyrNm}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;">
					</td>
				</tr>
			</c:forEach>	  
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="6"><spring:message code="common.nodata.msg" /></td>  
				</tr>
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_emplyr"/>
		</ul>
	</div>

</form>

</body>
</html>
