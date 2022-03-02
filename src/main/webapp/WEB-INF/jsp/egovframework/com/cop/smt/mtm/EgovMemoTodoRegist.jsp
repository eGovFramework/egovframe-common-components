<%
/**
 * @Class Name : EgovMemoTodoRegist.jsp
 * @Description : 메모할일 등록
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtMtm.memoTodoRegist.title"/></title><!-- 메모할일 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="memoTodoVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function initCalendar(){
		$("#todoDe").datepicker(
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

	function fn_egov_insert_memotodo() {
		if (!validateMemoTodoVO(document.memoTodoVO)){
			return;
		}

		var todoBeginHourMin = document.memoTodoVO.todoBeginHour.value + document.memoTodoVO.todoBeginMin.value;
		var todoEndHourMin = document.memoTodoVO.todoEndHour.value + document.memoTodoVO.todoEndMin.value;

		if(todoBeginHourMin > todoEndHourMin){
			alert("<spring:message code="comCopSmtMtm.memoTodoRegist.todoHourMin"/>");/* 할일종료시분이 할일시작시분보다 빠를수 없습니다. */
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.memoTodoVO.action = "<c:url value='/cop/smt/mtm/insertMemoTodo.do'/>";
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
<body onLoad="initCalendar();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="memoTodoVO" name="memoTodoVO" method="post" action="${pageContext.request.contextPath}/cop/smt/mtm/insertMemoTodo.do' />">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtMtm.memoTodoRegist.title"/></h2><!-- 메모할일 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.todoDe"/> <span class="pilsu">*</span></th><!-- 할일일자 -->
			<td class="left">
				<c:set var="todoDe"><spring:message code="comCopSmtMtm.memoToDo.validate.todoDe"/></c:set>
			    <form:input path="todoDe" maxlength="10" readonly="true" title="${todoDe}" cssStyle="width:70px"/>
	
			    <form:select path="todoBeginHour" title="할일시작 시 선택">
		            <form:options items="${todoBeginHour}" itemValue="code" itemLabel="codeNm"/>
		        </form:select><spring:message code="comCopSmtMtm.memoTodoUpdt.hour"/><!-- 시 -->
		        <form:select path="todoBeginMin" title="할일시작 분 선택">
		            <form:options items="${todoBeginMin}" itemValue="code" itemLabel="codeNm"/>
		        </form:select><spring:message code="comCopSmtMtm.memoTodoUpdt.minutes"/><!-- 분 -->
				~
		        <form:select path="todoEndHour" title="할일종료 시 선택">
		            <form:options items="${todoEndHour}" itemValue="code" itemLabel="codeNm"/>
		        </form:select><spring:message code="comCopSmtMtm.memoTodoUpdt.hour"/><!-- 시 -->
		        <form:select path="todoEndMin" title="할일종료 분 선택">
		            <form:options items="${todoEndMin}" itemValue="code" itemLabel="codeNm"/>
		        </form:select><spring:message code="comCopSmtMtm.memoTodoUpdt.hour"/><!-- 시 -->
				<div><form:errors path="todoDe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자명 -->
			<td class="left">
			    <c:out value="${memoTodoVO.wrterNm}" escapeXml="false" />
				<input type="hidden" name="wrterId" id="wrterId" value="${memoTodoVO.wrterId}"/>
				<input type="hidden" name="wrterNm" id="wrterNm" value="${memoTodoVO.wrterNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.todoNm"/> <span class="pilsu">*</span></th><!-- 할일제목 -->
			<td class="left">
			    <form:input path="todoNm" size="75" maxlength="255" title="할일제목"/>
	      		<div><form:errors path="todoNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMtm.memoToDo.validate.todoCn"/> <span class="pilsu">*</span></th><!-- 할일내용 -->
			<td class="left">
			    <form:textarea path="todoCn" rows="5" cols="75" title="할일내용"/>
    	  		<div><form:errors path="todoCn" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_insert_memotodo(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/mtm/selectMemoTodoList.do'/>?searchWrd=<c:out value='${memoTodoVO.searchWrd}'/>&amp;searchCnd=<c:out value='${memoTodoVO.searchCnd}'/>&amp;pageIndex=<c:out value='${memoTodoVO.pageIndex}'/>&amp;searchDe=<c:out value='${memoTodoVO.searchDe}'/>&amp;searchBgnDe=<c:out value='${memoTodoVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${memoTodoVO.searchEndDe}'/>" onclick="fn_egov_list_memotodo(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

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
