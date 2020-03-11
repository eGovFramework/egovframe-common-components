<%
/**
 * @Class Name : EgovBndtCeckManageList.java
 * @Description : EgovBndtCeckManageList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용             최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
 * @ 2018.09.27    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtCeckManageList.title"/></title><!-- 당직체크관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
 /*설명 : 회의실 목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				 = document.all["listForm"];
	varForm.searchCondition.value = "1";
	varForm.pageIndex.value = pageNo;
	varForm.action = "<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>";
	varForm.submit();
 }

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectMtgPlaceManageList(pageNo){
	 var varForm				 = document.all["listForm"];
	 //varForm.searchCondition.value = "1";
	 varForm.pageIndex.value = pageNo;
	 varForm.action = "<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>";
	 varForm.submit();
 }

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fncBndtCeckManageRegist(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtCeckManageRegist.do'/>";
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fncBndtCeckManageDetail(bndtCeckSe, bndtCeckCd){
	var varForm				 = document.all["listForm"];
	varForm.bndtCeckSe.value = bndtCeckSe;
	varForm.bndtCeckCd.value = bndtCeckCd;
	varForm.action           = "<c:url value='/uss/ion/bnt/EgovBndtCeckManage.do'/>";
	varForm.submit();
}

-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonBnt.bndtCeckManageList.title"/></h1><!-- 당직체크관리 목록 -->

	<form name="listForm" action="<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>" method="post">
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="bndtCeckSe">
	<input type="hidden" name="bndtCeckCd">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty bndtCeckManageVO.pageIndex }">1</c:if><c:if test="${!empty bndtCeckManageVO.pageIndex }"><c:out value='${bndtCeckManageVO.pageIndex}'/></c:if>">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonBnt.common.searchBndtCeckSe"/> : </label><!-- 당직구분 -->
				<select name="searchBndtCeckSe" title="<spring:message code="comUssIonBnt.common.searchBndtCeckSe"/>"><!-- 당직구분 -->
		        	<option value="" <c:if test="${bndtCeckManageVO.searchBndtCeckSe eq '' }">selected</c:if>><spring:message code="comUssIonBnt.common.selectedAll"/></option><!-- 전체 -->
		            <c:forEach items="${bndtCeckSeList}" var="result" varStatus="status">
			       	   <option value="<c:out value="${result.code}"/>" <c:if test="${result.code eq bndtCeckManageVO.searchBndtCeckSe}">selected</c:if>><c:out value="${result.codeNm }"/></option>
		            </c:forEach>
		      	</select>
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonBnt.common.bndtCeckCdName"/> : </label><!-- 당직체크코드명 -->
				<input name="searchKeyword" type="text" size="20" value="${bndtCeckManageVO.searchKeyword}"  maxlength="100" title="<spring:message code="comUssIonBnt.common.bndtCeckCdName"/>"/><!-- 당직체크코드명 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonBnt.common.useAt"/> : </label><!-- 사용여부 -->
				<select name="searchUseAt" title="<spring:message code="comUssIonBnt.common.useAt"/>"><!-- 사용여부 -->
			       	<option value=""  <c:if test="${bndtCeckManageVO.searchUseAt eq '' }">selected</c:if>><spring:message code="comUssIonBnt.common.selectedAll"/></option><!-- 전체 -->
			       	<option value="Y" <c:if test="${bndtCeckManageVO.searchUseAt eq 'Y' }">selected</c:if>><spring:message code="comUssIonBnt.common.useAt.y"/></option><!-- 사용 -->
			       	<option value="N" <c:if test="${bndtCeckManageVO.searchUseAt eq 'N' }">selected</c:if>><spring:message code="comUssIonBnt.common.useAt.n"/></option><!-- 미사용 -->
		      	</select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectMtgPlaceManageList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/bnt/EgovBndtCeckManageRegist.do'/>?searchCondition=1" onclick="fncBndtCeckManageRegist(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:30%" />
			<col style="width:30%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckTemp1"/></th><!-- 당직체크구분 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCd"/></th><!-- 당직체크코드 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCdName"/></th><!-- 당직체크코드명 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.useAt"/></th><!-- 사용여부 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bndtCeckManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(bndtCeckManageVO.pageIndex - 1) * bndtCeckManageVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.bndtCeckTemp1}"/></td>
				<td><c:out value="${resultInfo.bndtCeckCd}"/></td>
				<td>
		        <form name="item" method="post" action="<c:url value='/uss/ion/bnt/EgovBndtCeckManage.do'/>">
		        	<input type="hidden" name="bndtCeckSe" value="<c:out value="${resultInfo.bndtCeckSe      }"/>">
			        <input type="hidden" name="bndtCeckCd" value="<c:out value="${resultInfo.bndtCeckCd      }"/>">
		            <span class="link"><input type="submit" value="<c:out value="${resultInfo.bndtCeckCdNm}"/>" onclick="fncBndtCeckManageDetail('<c:out value="${resultInfo.bndtCeckSe}"/>','<c:out value="${resultInfo.bndtCeckCd}"/>'); return false;" style="text-align : left;"></span>
		        </form></td>
				<td><c:if test="${resultInfo.useAt == 'Y'}"><spring:message code="comUssIonBnt.common.useAt.y"/></c:if><c:if test="${resultInfo.useAt == 'N'}"><spring:message code="comUssIonBnt.common.useAt.n"/></c:if></td><!-- 사용  /미사용 -->
			</tr>   
			</c:forEach>
			
			<c:if test="${fn:length(bndtCeckManageList) == 0}">
				<tr> 
					<td colspan="5">
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
</body>
</html>
