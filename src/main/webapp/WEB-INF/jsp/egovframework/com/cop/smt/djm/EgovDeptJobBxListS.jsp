<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovDeptJobBxList.jsp
 * @Description : 부서업무함 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.06   장철호          최초 생성
 * @ 2018.09.10   최두영           V3.8 퍼블리싱 점검
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
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function fn_egov_init_deptjobbx(){
		var opener = parent.window.dialogArguments;
	}
	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_deptjobbx('1');
		}
	}
	
	function fn_egov_select_deptjobbx(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>";
		document.frm.submit();	
	}

	function fn_egov_inqire_deptjobbx(deptId, deptNm, deptJobBxId, deptJobBxNm) {
		var opener = parent.window.dialogArguments;
		/*
		회의관리/주관자ID
		*/
		if(opener[1] == "typeDeptJobBx"){
			opener[0].document.getElementById("deptId").value = deptId;
			opener[0].document.getElementById("deptNm").value = deptNm;
			opener[0].document.getElementById("deptJobBxId").value = deptJobBxId;
			opener[0].document.getElementById("deptJobBxNm").value = deptJobBxNm;
		}
		parent.window.returnValue = true;
		parent.window.close();	
	}

</script>
<title><spring:message code="comCopSmtDjm.deptJobBxList.title" /></title><!-- 부서업무함 목록 -->
</head>
<body onLoad="fn_egov_init_deptjobbx()" style="margin-top:0">

<div class="board">
	<h1><spring:message code="comCopSmtDjm.deptJobBxList.title" /></h1><!-- 부서업무함 목록 -->
	
	<form name="frm" method="post" action="<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="deptId">
	<input type="hidden" name="deptJobBxId">
	<input type="hidden" name="popupCnd" value="Y">
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comCopSmtDjm.deptJobBxList.userNm" /> : </label><!-- 사용자명 -->
				<select name="searchCnd" class="select" title="조회조건 선택">
					<option value=''>--<spring:message code="input.select" />--</option>
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobBxList.deptNm" /></option><!-- 부서업무명 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobBxList.deptJobNm" /></option><!-- 부서업무함명 -->
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' size="35" onkeypress="press(event);" title="검색어 입력" />
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_deptjobbx('1'); return false;" />
			</li>
		</ul>
	</div>
	
	</form>
	
	<table class="board_list">
		<caption><spring:message code="comCopSmtDjm.deptJobBxList.title" /></caption><!-- 부서업무함 목록 -->
		<colgroup>
			<col style="width:10%" />
			<col style="width:35%" />
			<col style="" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comCopSmtDjm.deptJobBxList.deptNm" /></th><!-- 부서명 -->
			   <th scope="col"><spring:message code="comCopSmtDjm.deptJobRegist.deptJobBxNm" /></th><!-- 부서업무함명 -->
			   <th scope="col"><spring:message code="table.select" /></th><!-- 선택 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>		    
				<td><c:out value="${result.deptNm}"/></td>
				<td><c:out value="${result.deptJobBxNm}"/></td>
				<td>
					<input class="btn01" type="submit" value="<spring:message code="table.select" />" onclick="fn_egov_inqire_deptjobbx('<c:out value="${result.deptId}"/>', '<c:out value="${result.deptNm}"/>','<c:out value="${result.deptJobBxId}"/>', '<c:out value="${result.deptJobBxNm}"/>'); return false;">
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
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_deptjobbx"/>
		</ul>
	</div>
</div>
</body>
</html>
