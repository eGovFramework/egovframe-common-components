<%--
  Class Name : EgovIndvdlInfoPolicyList.jsp
  Description : 개인정보보호정책 목록 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2018.09.03    이정은          공통컴포넌트 3.8 개선     
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09
    
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoPolicyList"/></title><!-- 개인정보보호정책 관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_IndvdlInfoPolicy(indvdlInfoPolicyId){
	var vFrom = document.listForm; 
	vFrom.indvdlInfoId.value = indvdlInfoPolicyId; 
	vFrom.action = "<c:url value='/uss/sam/ipm/detailIndvdlInfoPolicy.do' />"; 
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_IndvdlInfoPolicy(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do' />";
	vFrom.submit();
	
}
</script>
</head>
<body>
<DIV id="content" style="width:712px">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoPolicyList"/></h1><!-- 개인정보보호정책 관리 목록 -->
	
	<form name="listForm" action="<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do'/>" method="post">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다 -->
		<ul>
			<li>
				<select title="<spring:message code="select.searchCondition"/>" name="searchCondition" class="select"><!-- 개인정보보호정책조회 조건 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value='INDVDL_INFO_POLICY_NM' <c:if test="${searchCondition == 'INDVDL_INFO_POLICY_NM'}">selected</c:if>><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoPolicyNm"/></option><!-- 개인정보보호정책명 -->
					<option value='INDVDL_INFO_POLICY_CN' <c:if test="${searchCondition == 'INDVDL_INF_POLICY_CN'}">selected</c:if>><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoPolicyCn"/></option><!-- 개인정보보호정책내용 -->
				</select>
				<input class="s_input2 vat" type="text" name="searchKeyword" value="${searchVO.searchKeyword}" size="25" title="<spring:message code="title.search"/>" /><!-- 검색어 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fn_egov_search_IndvdlInfoPolicy(); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/sam/ipm/registIndvdlInfoPolicy.do' />" onclick="fn_userChk('USRCNFRM_00000000000')"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	
	<input name="indvdlInfoId" type="hidden" value="">
	<input name="searchMode" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	</form>

	<table class="board_list">
		<caption><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoPolicyList"/></caption><!-- 개인정보보호정책 관리 -->
		<colgroup>
			<col style="width:50px" />
			<col style="width:70px" />
			<col style="" />
			<col style="width:80px" />
			<col style="width:80px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoscrPolicyAgreAt"/></th><!-- 동의여부 -->
			   <th scope="col"><spring:message code="ussSamIpm.indvdlInfoPolicyList.indvdlInfoPolicyNm"/></th><!-- 개인정보보호정책명 -->
			   <th scope="col"><spring:message code="ussSamIpm.indvdlInfoPolicyList.frstRegisterNm"/></th><!-- 등록자 -->
			   <th scope="col"><spring:message code="ussSamIpm.indvdlInfoPolicyList.frstRegistPnttm"/></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				<tr>
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					<td>
						<c:if test="${resultInfo.indvdlInfoscrPolicyAgreAt == 'Y'}"><spring:message code="input.yes"/></c:if><!-- 예 -->
						<c:if test="${resultInfo.indvdlInfoscrPolicyAgreAt == 'N'}"><spring:message code="input.no"/></c:if><!-- 아니오 -->
					</td>
					<td>			
						<a href="<c:url value='/uss/sam/ipm/detailIndvdlInfoPolicy.do'/>?indvdlInfoId=${resultInfo.indvdlInfoPolicyId}&pageIndex=${searchVO.pageIndex}" onClick="fn_egov_detail_IndvdlInfoPolicy('<c:out value="${resultInfo.indvdlInfoPolicyId}"/>');return false;"><c:out value='${resultInfo.indvdlInfoPolicyNm}'/></a>	
					</td>
					<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
					<td><c:out value="${fn:substring(resultInfo.frstRegistPnttm, 0, 10)}"/></td>
				</tr>  
			</c:forEach>
			
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr> 
					<td class="lt_text3" colspan="5">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>

</DIV>

</body>
</html>
