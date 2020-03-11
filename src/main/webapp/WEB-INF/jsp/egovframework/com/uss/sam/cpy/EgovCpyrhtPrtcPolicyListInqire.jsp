<%
 /**
  * @Class Name : EgovCpyrhtPrtcPolicyListInqire.jsp
  * @Description : EgovCpyrhtPrtcPolicyListInqire 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  * @ 2018.09.03   이정은              공통컴포넌트 3.8 개선 
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussSamCpy.cpyrhtPrtcPolicyListInqire.cpyrhtPrtcPolicyListInqire"/></title><!-- 저작권보호정책 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_cpyrhtprtcpolicylist(){

	// 첫 입력란에 포커스..
	document.CpyrhtPrtcPolicyForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.CpyrhtPrtcPolicyForm.pageIndex.value = pageNo;
	document.CpyrhtPrtcPolicyForm.action = "<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do'/>";
   	document.CpyrhtPrtcPolicyForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_cpyrhtprtcpolicycn(){

	document.CpyrhtPrtcPolicyForm.pageIndex.value = 1;
	document.CpyrhtPrtcPolicyForm.submit();
	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_cpyrhtprtcpolicycn(){

	document.CpyrhtPrtcPolicyForm.action = "<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyCnRegistView.do'/>";
	document.CpyrhtPrtcPolicyForm.submit();	
	
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_cpyrhtprtcpolicydetail(cpyrhtId) {		

	// 사이트 키값(siteId) 셋팅.
	document.CpyrhtPrtcPolicyForm.cpyrhtId.value = cpyrhtId;
  	document.CpyrhtPrtcPolicyForm.action = "<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyDetailInqire.do'/>";
  	document.CpyrhtPrtcPolicyForm.submit();	
	   	   		
}


</script>
</head>
<body onLoad="fn_egov_initl_cpyrhtprtcpolicylist();">

<div class="board">
	<h1><spring:message code="ussSamCpy.cpyrhtPrtcPolicyListInqire.cpyrhtPrtcPolicyListInqire"/></h1><!-- 저작권보호정책 목록 -->
<form name="CpyrhtPrtcPolicyForm" action="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="select.searchCondition"/>"><!-- 조회조건 선택 -->
					<option selected value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value="cpyrhtPrtcPolicyCn"  <c:if test="${searchVO.searchCondition == 'cpyrhtPrtcPolicyCn'}">selected="selected"</c:if> ><spring:message code="ussSamCpy.cpyrhtPrtcPolicyListInqire.cpyrhtPrtcPolicyCn"/></option>	<!-- 저작권보호정책내용 -->		   
				</select> 
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' size="25" maxlength="35" onkeypress="press();" title="<spring:message code="title.search"/>" /><!-- 검색어 입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="fn_egov_search_cpyrhtprtcpolicycn(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyCnRegistView.do'/>" onclick="fn_egov_regist_cpyrhtprtcpolicycn(); return false;"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input name="cpyrhtId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="${searchVO.pageIndex}">
</form>
	
	<table class="board_list">
		<caption><spring:message code="ussSamCpy.cpyrhtPrtcPolicyListInqire.cpyrhtPrtcPolicyListInqire"/></caption><!-- 저작권보호정책 목록 -->
		<colgroup>
			<col style="width:20%" />
			<col style="width:45%" />
			<col style="width:25%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussSamCpy.cpyrhtPrtcPolicyListInqire.cpyrhtPrtcPolicyCn"/></th><!-- 저작권보호정책내용 -->
			   <th scope="col"><spring:message code="ussSamCpy.cpyrhtPrtcPolicyListInqire.lastUpdtPnttm"/></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>			        
				<td>
					<a href="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyDetailInqire.do'/>?cpyrhtId=${resultInfo.cpyrhtId}&pageIndex=${searchVO.pageIndex}" onClick="fn_egov_inquire_cpyrhtprtcpolicydetail('<c:out value="${resultInfo.cpyrhtId}"/>');return false;"><c:out value='${fn:substring(resultInfo.cpyrhtPrtcPolicyCn, 0, 40)}'/></a>
				</td>		
				<td><fmt:formatDate value="${resultInfo.lastUpdtPnttm}"  pattern="yyyy-MM-dd"/></td>			
			</tr>   
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr> 
				<td colspan="3">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
		</ul>
	</div>

</div>

</body>
</html>

