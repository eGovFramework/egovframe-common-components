<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovDeptList.jsp
 * @Description : 부서 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.06   장철호          최초 생성
 * @ 2018.09.10   최두영           V3.8 퍼블리싱 점검
 * @ 2018.09.14   최두영           다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.06
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtDjm.deptList.title" /></title><!-- 부서목록 조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">
	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_dept('1');
		}
	}
	
	function fn_egov_select_dept(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/djm/selectDeptList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_dept(orgnztId, orgnztNm) {
		
		getDialogArguments();
		var opener = parent.window.dialogArguments;
		
		/*	회의관리/주관자ID*/
 		parent.opener.document.getElementById("deptId").value = orgnztId;
		parent.opener.document.getElementById("deptNm").value = orgnztNm; 
		
		setReturnValue(true);
		parent.window.returnValue = true;
		parent.window.close();	
	}
</script>
</head>
<body style="margin:0">

<div class="board">
	<h1><spring:message code="comCopSmtDjm.deptList.deptSelect" /></h1><!-- 부서 선택 -->
	
	<form name="frm" method="post" action="<c:url value='/cop/smt/djm/selectDeptList.do'/>">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	
		<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
			<ul>
				<li>
					<label for=""><spring:message code="select.searchCondition" /> : </label>
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition" />">
					<option value=''>--<spring:message code="input.select" />--</option>
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobBxList.deptNm" /></option><!-- 부서명 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptList.deptExplain" /></option><!-- 부서설명 -->
				</select>
					<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' size="25" onkeypress="press(event);" title="검색어 입력" />
					<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_dept('1'); return false;" /><!-- 조회 -->
				</li>
			</ul>
		</div>
	</form>
		
	<table class="board_list">
		<caption><spring:message code="comCopSmtDjm.deptListPopup.title" /></caption><!-- 부서목록 -->
		<colgroup>
			<col style="width:10%" />
			<col style="width:25%" />
			<col style="" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comCopSmtDjm.deptJobRegist.deptNm" /></th><!-- 부서 -->
			   <th scope="col"><spring:message code="comCopSmtDjm.deptList.deptExplain" /></th><!-- 부서설명 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					<td><c:out value="${result.orgnztNm}"/></td>
					<td class="left"><c:out value="${result.orgnztDc}"/></td>
					<td>
						<input class="btn01" type="submit" value="<spring:message code="table.select" />" onclick="fn_egov_inqire_dept('<c:out value="${result.orgnztId}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;">
					</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="4"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_dept"/>
		</ul>
	</div>
</div>
</body>
</html>
