<% 
/**
 * @Class Name : EgovMemoTodoDetail.jsp
 * @Description : 메모할일 상세보기
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.06   장철호          최초 생성
 * @ 2018.09.16   최두영          다국어처리
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtMtm.memoTodoDetail.title"/></title><!-- 메모할일 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="memoTodoVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

	function fn_egov_update_memotodo() {
		document.memoTodoVO.action = "<c:url value='/cop/smt/mtm/modifyMemoTodo.do'/>";
		document.memoTodoVO.submit();					
	}

	function fn_egov_delete_memotodo(){
		if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제하시겠습니까? */
			document.memoTodoVO.action = "<c:url value='/cop/smt/mtm/deleteMemoTodo.do'/>";
			document.memoTodoVO.submit();
		}
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_memotodo(){
		document.memoTodoVO.action = "<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>";
		document.memoTodoVO.submit();	
	}	

</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="memoTodoVO" name="memoTodoVO" method="post" action="${pageContext.request.contextPath}/cop/smt/mtm/modifyMemoTodo.do' />">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtMtm.memoTodoDetail.title"/></h2><!-- 메모할일 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.todoDe"/> <span class="pilsu">*</span></th><!-- 할일일자 -->
			<td class="left">
			    <c:out value="${memoTodo.todoDe}" escapeXml="false" />
				<c:out value="${memoTodo.todoBeginHour}" escapeXml="false" />:<c:out value="${memoTodo.todoBeginMin}" escapeXml="false" />
				~ 
				<c:out value="${memoTodo.todoEndHour}" escapeXml="false" />:<c:out value="${memoTodo.todoEndMin}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자명 -->
			<td class="left">
			    <c:out value="${memoTodo.wrterNm}" escapeXml="false" />
				<input type="hidden" name="wrterId" id="wrterId" value="${memoTodo.wrterId}"/>
				<input type="hidden" name="wrterNm" id="wrterNm" value="${memoTodo.wrterNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.todoNm"/> <span class="pilsu">*</span></th><!-- 할일제목 -->
			<td class="left">
			    <c:out value="${memoTodo.todoNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.todoCn"/> <span class="pilsu">*</span></th><!-- 할일내용 -->
			<td class="left">
			    <c:out value="${fn:replace(memoTodo.todoCn , crlf , '<br>')}" escapeXml="false" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">		
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_update_memotodo(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/mtm/deleteMemoTodo.do'/>?todoId=<c:out value='${memoTodoVO.todoId}'/>" onclick="fn_egov_delete_memotodo(); return false;"><spring:message code="button.delete" /></a></span>
      <% 
      	if(request.getHeader("REFERER").indexOf("Today") < 0){
      %>
		<span class="btn_s"><a href="<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>?searchWrd=<c:out value='${memoTodoVO.searchWrd}'/>&amp;searchCnd=<c:out value='${memoTodoVO.searchCnd}'/>&amp;pageIndex=<c:out value='${memoTodoVO.pageIndex}'/>&amp;searchDe=<c:out value='${memoTodoVO.searchDe}'/>&amp;searchBgnDe=<c:out value='${memoTodoVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${memoTodoVO.searchEndDe}'/>" onclick="fn_egov_list_memotodo(); return false;"><spring:message code="button.list" /></a></span>
	  <%
	  	}else{
	  %>
		<span class="btn_s"><a href="<c:url value='/cop/smt/mtm/selectMemoTodoListToday.do'/>" onclick=""><spring:message code="button.list" /></a></span>
	  <%
	  	}
      %>
	</div>
	<div style="clear:both;"></div>
</div>
	<form:hidden path="todoId" />
	
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${memoTodoVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${memoTodoVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${memoTodoVO.pageIndex}'/>" />
    <input type="hidden" name="searchDe" value="<c:out value='${memoTodoVO.searchDe}'/>" />
    <input type="hidden" name="searchBgnDe" value="<c:out value='${memoTodoVO.searchBgnDe}'/>" />
    <input type="hidden" name="searchEndDe" value="<c:out value='${memoTodoVO.searchEndDe}'/>" />
    <!-- 검색조건 유지 -->

</form:form>

</body>
</html>
