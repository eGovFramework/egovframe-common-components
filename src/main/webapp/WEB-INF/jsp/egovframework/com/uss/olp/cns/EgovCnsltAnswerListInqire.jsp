<%
 /**
  * @Class Name : EgovCnsltAnswerListInqire.jsp
  * @Description : 상담답변 목록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.08.18   장동한              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpCnm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_cnsltanswerlist(){

	// 첫 입력란에 포커스..
	document.CnsltAnswerListForm.searchKeyword.focus();

}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){

	document.CnsltAnswerListForm.pageIndex.value = pageNo;
	document.CnsltAnswerListForm.action = "<c:url value='/uss/olp/cnm/CnsltAnswerListInqire.do'/>";
   	document.CnsltAnswerListForm.submit();

}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_cnsltdtlsanswer(){

	document.CnsltAnswerListForm.pageIndex.value = 1;
	document.CnsltAnswerListForm.submit();

}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_cnsltanswercn(){

	document.CnsltAnswerListForm.action = "<c:url value='/uss/olp/cnm/CnsltAnswerCnRegistView.do'/>";
	document.CnsltAnswerListForm.submit();

}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_cnsltanswerdetail(cnsltId) {

	// Q&A ID 셋팅.
	document.CnsltAnswerListForm.cnsltId.value = cnsltId;
    document.CnsltAnswerListForm.action = "<c:url value='/uss/olp/cnm/CnsltAnswerDetailInqire.do'/>";
  	document.CnsltAnswerListForm.submit();

}

</script>
</head>
<body onLoad="fn_egov_initl_cnsltanswerlist();">

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<form name="CnsltAnswerListForm" action="<c:url value='/uss/olp/cnm/CnsltAnswerListInqire.do'/>" method="post" onSubmit="fn_egov_search_cnsltdtlsanswer(); return false;">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value="wrterNm"  <c:if test="${searchVO.searchCondition == 'wrterNm'}">selected="selected"</c:if> ><spring:message code="comUssOlpCnm.searchCondition.wrterNm" /></option><!-- 작성자명 -->
					<option value="cnsltSj"  <c:if test="${searchVO.searchCondition == 'cnsltSj'}">selected="selected"</c:if> ><spring:message code="comUssOlpCnm.searchCondition.cnsltSj" /></option><!-- 상담제목 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
			</li>
		</ul>
	</div>
	<input name="cnsltId" type="hidden" value="">
	<input name="passwordConfirmAt" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: ;">
		
		<col style="width: 10%;">
		<col style="width: 13%;">
		
		<col style="width: 15%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpCnm.list.cnsltSj" /></th><!-- 상담제목 -->

		<th><spring:message code="table.reger" /></th><!-- 작성자 -->
		<th><spring:message code="table.regdate" /></th><!-- 작성일자 -->
		<th><spring:message code="comUssOlpCnm.list.qnaProcessSttusCodeNm" /></th><!-- 진행상태 -->
		<th><spring:message code="comUssOlpCnm.list.hit" /></th><!-- 조회수 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left">
		<form name="linkForm" method="post" action="<c:url value='/uss/olp/cnm/CnsltAnswerDetailInqire.do'/>">
    	<input name="cnsltId" type="hidden" value="${resultInfo.cnsltId}">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
    	<span class="link"><input type="submit" value="<c:out value="${resultInfo.cnsltSj}"/>"></span>
	    </form>
	    
		</td>
		<td><c:out value="${resultInfo.wrterNm}"/></td>
		<td><c:out value="${resultInfo.writngDe}"/></td>
		<td><c:out value="${resultInfo.qnaProcessSttusCodeNm}"/></td>
		<td><c:out value="${resultInfo.inqireCo}"/></td>
	  </tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/></ul>
	</div>
	
	
</div><!-- end div board -->



</body>
</html>
