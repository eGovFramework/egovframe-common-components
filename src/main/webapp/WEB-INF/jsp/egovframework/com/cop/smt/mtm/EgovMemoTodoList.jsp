<%
/**
 * @Class Name : EgovMemoTodoList.jsp
 * @Description : 메모할일 목록조회
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
<title><spring:message code="comCopSmtMtm.memoTodoList.title"/></title><!-- 메모할일 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript">
	
	function initCalendar(){
		$("#searchBgnDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});			
	}
	
	function initCalendar2(){
		$("#searchEndDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});			
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_memotodo('1');
		}
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	 
	function fn_egov_select_memotodo(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>";

		var searchDe = document.frm.searchDe.value;
		var bgnDe = document.frm.searchBgnDe.value.split("-").join("");
		var endDe = document.frm.searchEndDe.value.split("-").join("");
		var searchCnd = document.frm.searchCnd.value;
		var searchWrd = document.frm.searchWrd.value;

		if(bgnDe != ""){
			if(searchDe == null || searchDe == "") {
				alert("<spring:message code="comCopSmtMtm.memoTodoList.searchDeVal"/>");
				return;
			}
			
			if(endDe == null || endDe == "") {
				alert("<spring:message code="comCopSmtMtm.memoTodoList.searchEndDeVal"/>");
				return;
			}

			if(isDate(bgnDe, "<spring:message code="comCopSmtMtm.memoTodoList.bgnDe"/>") == false) {/* 검색시작일자 */
		        return;
		    }
		}

		if(endDe != ""){
			if(searchDe == null || searchDe == "") {
				alert("<spring:message code="comCopSmtMtm.memoTodoList.searchDeVal"/>");
				return;
			}

			if(bgnDe == null || bgnDe == "") {
				alert("<spring:message code="comCopSmtMtm.memoTodoList.searchbgnDeVal"/>");
				return;
			}

			if(isDate(endDe, "<spring:message code="comCopSmtMtm.memoTodoList.endDe"/>") == false) {/* 검색종료일자 */
		        return;
		    }
		}

		if(bgnDe != "" && endDe != ""){
			if(eval(bgnDe) > eval(endDe)){
				alert("<spring:message code="comCopSmtMtm.memoTodoList.bgnDeEvalEndDe"/>");/* 검색종료일자가 검색시작일자보다 빠를수 없습니다. */
				return;
			}
		}
		
		if(searchWrd != "") {
			if(searchCnd == null || searchCnd == "") {
				alert("<spring:message code="comCopSmtMtm.memoTodoList.aearchCndVal"/>");
				return;
			}
		}

		document.frm.submit();
	}
	/* ********************************************************
	 * 오늘의할일 목록조회로 가기
	 ******************************************************** */
	function fn_egov_select_memotoday() {
		document.frm.action = "<c:url value='/cop/smt/mtm/selectMemoTodoListToday.do'/>";
		document.frm.submit();
	}
	/* ********************************************************
	 * 메모할일 상세조회로 가기
	 ******************************************************** */
	function fn_egov_inqire_memotodo(todoId) {
		document.frm.todoId.value = todoId;
		document.frm.action = "<c:url value='/cop/smt/mtm/selectMemoTodo.do'/>";
		document.frm.submit();
	}
	/* ********************************************************
	 * 메모할일 등록 가기
	 ******************************************************** */
	function fn_egov_insert_memotodo(){
		document.frm.action = "<c:url value='/cop/smt/mtm/addMemoTodo.do'/>";
		document.frm.submit();
	}
	/* ********************************************************
	 * 셀렉트박스 초기화 
	 ******************************************************** */
	function initSelect(type, value) {
		if(value == '' || value == null) {
			if(type == '1') {
				document.frm.searchBgnDe.value = '';
				document.frm.searchEndDe.value = '';
			} else {
				document.frm.searchWrd.value = '';
			}
		}
	}
</script>

</head>
<body onLoad="initCalendar(); initCalendar2();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">

	<form name="frm" method="post" action="<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>">
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="todoId">
	
	<h1><spring:message code="comCopSmtMtm.memoTodoList.title"/></h1><!-- 메모할일 목록조회 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchDe" class="select" onChange="javascript:initSelect('1',this.value);" title="<spring:message code="comCopSmtMtm.memoTodoList.searchDe"/>">
					<option value=''><spring:message code="comCopSmtMtm.memoTodoList.searchDe"/></option><!-- 일자조회선택 -->
					<option value="0" <c:if test="${searchVO.searchDe == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtMtm.memoTodoList.searchDe0"/></option><!-- 할일일자 -->
					<option value="1" <c:if test="${searchVO.searchDe == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtMtm.memoTodoList.searchDe1"/></option><!-- 작성일자 -->
				</select>
				
				<input name="searchBgnDe" id="searchBgnDe" type="text" size="10" maxlength="10" readonly="readonly" value="<c:out value="${searchVO.searchBgnDe}"/>" title="<spring:message code="comCopSmtMtm.memoTodoList.searchBgnDe"/>"/><!-- 조회시작일자 입력 -->
			    ~
			    <input name="searchEndDe" id="searchEndDe" type="text" size="10" maxlength="10" readonly="readonly" value="<c:out value="${searchVO.searchEndDe}"/>" title="<spring:message code="comCopSmtMrm.memoReprtList.searchEndDe"/>"/><!-- 조회종료일자 입력 -->
			    
			    <select name="searchCnd" class="select" onChange="javascript:initSelect('2',this.value);" title="조회조건 선택">
					<option value=''><spring:message code="comCopSmtMtm.memoTodoList.searchCnd"/></option><!-- 할일조건 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtMtm.memoTodoList.searchCnd0"/></option><!-- 할일제목 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtMtm.memoTodoList.searchCnd1"/></option><!-- 할일내용 -->
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' onkeypress="press(event);" title="<spring:message code="title.search"/> <spring:message code="input.input"/>" style="width:100px" /><!-- 검색어 입력-->
				
				<span class="btn_b"><a href="<c:url value='/cop/smt/mtm/selectMemoTodoListToday.do'/>" onclick="fn_egov_select_memotoday(); return false;" title="<spring:message code="comCopSmtMtm.memoTodoList.toDoListToday"/>"><spring:message code="comCopSmtMtm.memoTodoList.toDoListToday"/></a></span><!-- 오늘의 할일 -->
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="fn_egov_select_memotodo('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/cop/smt/mtm/addMemoTodo.do'/>" onclick="fn_egov_insert_memotodo('1'); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	</form>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%%" />
			<col style="width:25%" />
			<col style="width:35%" />
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
			    	<c:out value="${result.todoDe}"/>
			    	<c:out value="${result.todoBeginHour}"/>:<c:out value="${result.todoBeginMin}"/>
			    	~
			    	<c:out value="${result.todoEndHour}"/>:<c:out value="${result.todoEndMin}"/>
			    </td>
			    <td>
			     <form name="memoReprtVO" method="post" action="<c:url value='/cop/smt/mtm/selectMemoTodo.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
			    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
			    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
			    	<input name="searchSttus" type="hidden" value="<c:out value='${searchVO.searchDe}'/>">
					<input type="hidden" name="todoId" value="<c:out value="${result.todoId}"/>">
				 	<span class="link"><input type="submit" value="<c:out value="${result.todoNm}"/>" onclick="fn_egov_inqire_memotodo('<c:out value="${result.todoId}"/>'); return false;" style="text-align : left;"></span>
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

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_memotodo"/>
		</ul>
	</div>
</div>
</body>
</html>
