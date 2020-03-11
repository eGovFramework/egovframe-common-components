<%
 /**
  * @Class Name : EgovStplatListInqire.jsp
  * @Description : EgovStplatListInqire 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2011.09.19   서준식              등록일자 Char 변경으로 fmt기능 사용안함
  *   2016.06.13   장동한              표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comUssSamStp.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.StplatListForm.searchCondition.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.StplatListForm.pageIndex.value = pageNo;
	document.StplatListForm.action = "<c:url value='/uss/sam/stp/StplatListInqire.do'/>";
   	document.StplatListForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_stplatcn(){
	document.StplatListForm.pageIndex.value = 1;
	document.StplatListForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_stplatdetail(useStplatId) {
	// 사이트 키값(siteId) 셋팅.
	document.StplatListForm.useStplatId.value = useStplatId;
  	document.StplatListForm.action = "<c:url value='/uss/sam/stp/StplatDetailInqire.do'/>";
  	document.StplatListForm.submit();
}
</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<form name="StplatListForm" action="<c:url value='/uss/sam/stp/StplatListInqire.do'/>" method="post" onSubmit="fn_egov_search_stplatcn(); return false;"> 
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value="useStplatNm"  <c:if test="${searchVO.searchCondition == 'useStplatNm'}">selected="selected"</c:if> ><spring:message code="comUssSamStp.list.useStplatNm" /></option><!-- 약관명 -->
					<option value="useStplatCn"  <c:if test="${searchVO.searchCondition == 'useStplatCn'}">selected="selected"</c:if> ><spring:message code="comUssSamStp.list.useStplatCn" /></option><!-- 약관내용 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="<c:url value='/uss/sam/stp/StplatCnRegistView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	<input name="useStplatId" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 20%;">
		<col style="width: ;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comUssSamStp.list.useStplatNm" /></th><!-- 약관명 -->
		<th class="board_th_link"><spring:message code="comUssSamStp.list.useStplatCn" /></th><!-- 약관내용 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td><c:out value="${resultInfo.useStplatNm}"/></td>
		<td class="left">

		<form name="subForm" method="post" action="<c:url value='/uss/sam/stp/StplatDetailInqire.do'/>">
			<input name="useStplatId" type="hidden" value="<c:out value="${resultInfo.useStplatId}"/>">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	    	<span class="link"><input type="submit" value="<c:out value='${fn:substring(resultInfo.useStplatCn, 0, 50)}'/>"></span>
	    </form>
	    
		</td>
		<td><c:out value='${resultInfo.frstRegistPnttm}'/></td>	
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/></ul>
	</div>
	
	<!-- 등록버튼 -->
	<!-- 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/sam/stp/StplatCnRegistView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
	</div>
	 -->
	
</div><!-- end div board -->



</body>
</html>