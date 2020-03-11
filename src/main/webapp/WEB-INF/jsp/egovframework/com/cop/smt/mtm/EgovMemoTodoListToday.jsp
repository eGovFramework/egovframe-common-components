<% 
/**
 * @Class Name : EgovMemoTodoListToday.jsp
 * @Description : 메모할일 목록조회 (오늘의 할일) 
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.06   장철호          최초 생성
 * @ 2018.09.05    최두영          V3.8 업무 개선
 * @ 2018.09.15    최두영          다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.06
 *  @version 1.0 
 *  @see
 *  
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtMtm.memoTodoListToday.title"/></title><!-- 오늘의 할일 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">

	function fn_egov_init_memotodo(){
		
	}
	function fn_egov_inqire_memotodo(todoId) {
		document.frm.todoId.value = todoId;
		document.frm.action = "<c:url value='/cop/smt/mtm/selectMemoTodo.do'/>";
		document.frm.submit();	
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_memotodo(){
		document.frm.action = "<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>";
		document.frm.submit();	
	}	
</script>
</head>
<body onLoad="fn_egov_init_memotodo()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comCopSmtMtm.memoTodoListToday.title"/> [ <c:out value="${resultToday}"/> ]</h1><!-- 오늘의 할일 목록조회 -->

	<form name="frm" method="post" action="<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="todoId">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<span class="btn_b"><a href="#" onclick="fn_egov_list_memotodo(); return false;" title="<spring:message code="comCopSmtMtm.memoTodoListToday.allList"/>"><spring:message code="comCopSmtMtm.memoTodoListToday.allList"/></a></span><!-- 전체목록 -->
			</li>
		</ul>
	</div>
	</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:40%" />
			<col style="width:15%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comCopSmtMtm.memoToDo.validate.todoDe"/></th><!-- 할일일자 -->
			   <th scope="col"><spring:message code="comCopSmtMtm.memoToDo.validate.todoNm"/></th><!-- 할일제목 -->
			   <th scope="col"><spring:message code="comCopSmtMtm.memoToDo.validate.wrterNm"/></th><!-- 작성자명 -->
			   <th scope="col"><spring:message code="comCopSmtMtm.memoTodoList.frstRegisterPnttm"/></th><!-- 작성일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	
			    <td>
			    	<c:out value="${result.todoBeginHour}"/>:<c:out value="${result.todoBeginMin}"/>
			    	~
			    	<c:out value="${result.todoEndHour}"/>:<c:out value="${result.todoEndMin}"/>
			    </td>
			    <td class="lt_text2" nowrap>
			     <form name="memoReprtVO" method="post" action="<c:url value='/cop/smt/mtm/selectMemoTodo.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
			    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
			    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
			    	<input name="searchSttus" type="hidden" value="<c:out value='${searchVO.searchDe}'/>">
					<input type="hidden" name="todoId" value="<c:out value="${result.todoId}"/>">
					<span class="link"><input type="submit" value="<c:out value="${result.todoNm}"/>" onclick="fn_egov_inqire_memotodo('<c:out value="${result.todoId}"/>'); return false;"></span>
				 </form>
				</td>
				<td><c:out value="${result.wrterNm}"/></td>
			    <td><c:out value="${fn:substring(result.frstRegisterPnttm, 0, 10)}"/></td>
			  </tr>
			 </c:forEach>	  
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="5"><spring:message code="common.nodata.msg" /></td>  
			  </tr>		 
			 </c:if>
		</tbody>
	</table>
</div>
</body>
</html>
