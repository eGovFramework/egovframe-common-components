<%
 /**
  * @Class Name  : EgovCcmZipList.jsp
  * @Description : EgovCcmZipList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  * @ 2017.09.01   양희훈              표준프레임워크 v3.7 개선
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmZip.zipVO.title"/></c:set>
<html lang="ko">
<head>
<title>${pageTitle}<spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />" >
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/ccm/zip/EgovCcmZipList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_Zip(){
	sC1 = document.listForm.searchCondition.value;
	sC2 = document.listForm.searchCondition2.value;
	sK = document.listForm.searchKeyword.value;
	if (sC1 == "1" || sC2 == "1") {
		document.listForm.searchKeyword.value = sK.replace(/\-/, "");
	}
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_Zip(no){
//	location.href = "<c:url value='/sym/ccm/zip/EgovCcmZipRegist.do'/>";
	var varForm	   			 = document.getElementById("Form");
	varForm.action 			 = "<c:url value='/sym/ccm/zip/EgovCcmZipRegist.do'/>";
	varForm.searchList.value = no;
	varForm.submit();
}
/* ********************************************************
 * 엑셀등록 처리 함수
 ******************************************************** */
function fn_egov_regist_ExcelZip(no){
//	location.href = "<c:url value='/sym/ccm/zip/EgovCcmExcelZipRegist.do' />";
	var varForm	   			 = document.getElementById("Form");
	varForm.action 			 = "<c:url value='/sym/ccm/zip/EgovCcmExcelZipRegist.do'/>";
	varForm.searchList.value = no;
	varForm.submit();
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_Zip(){
	location.href = "";
}
/* ********************************************************
 * 상세회면 처리 함수(일반주소)
 ******************************************************** */
function fn_egov_detail_Zip(zip,sn){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/sym/ccm/zip/EgovCcmZipDetail.do'/>";
	varForm.zip.value        = zip;
	varForm.sn.value         = sn;
	varForm.searchList.value = "1";
	varForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수(도로명주소)
 ******************************************************** */
function fn_egov_detail_RdmnCode_Zip(rdmnCode, sn){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/sym/ccm/zip/EgovCcmZipDetail.do'/>";
	varForm.rdmnCode.value   = rdmnCode;
	varForm.sn.value         = sn;
	varForm.searchList.value = "2";
	varForm.submit();
}
/* ********************************************************
 * 목록회면 처리 함수
 ******************************************************** */
function fn_egov_list(){
	if (document.getElementById("searchList").value == 1) {
		document.getElementById("searchCondition").style.display="block";
		document.getElementById("searchCondition2").style.display="none";
	} else {
		document.getElementById("searchCondition").style.display="none";
		document.getElementById("searchCondition2").style.display="block";
	}
}
-->
</script>
</head>
<body onLoad="fn_egov_list()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript>
<form name="listForm" action="<c:url value='/sym/ccm/zip/EgovCcmZipList.do'/>" method="post">
<div class="board">
<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<div class="search_box">
		<ul>
			<li>
		   	<select name="searchList" id="searchList" class="select" title="searchList" onChange="fn_egov_list()"> 
			   <option value='1' <c:if test="${searchVO.searchList == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.SearchAddrr" /></option>		<!-- 주소 -->
			   <option value='2' <c:if test="${searchVO.searchList == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.SearchRdmn" /></option>			<!-- 우편번호 -->
		    </select>
		    </li>
			<li>
			<select name="searchCondition" id="searchCondition" class="select" title="searchCondition" style="display:none">
			   <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.zip" /></option>			<!-- 우편번호 -->
			   <option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.ctprvnNm" /></option>		<!-- 시도명 -->
			   <option value='3' <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.signguNm" /></option>		<!-- 시군구명 -->
			   <option value='4' <c:if test="${searchVO.searchCondition == '4'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.emdNm" /></option>			<!-- 읍면동명 -->
			   <option value='5' <c:if test="${searchVO.searchCondition == '5'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.liBuldNm" /></option>		<!-- 리건물명 -->
			</select>
			</li>
			<li>
		   	<select name="searchCondition2" id="searchCondition2" class="select" title="searchCondition" style="display:none">
			   <option value='1' <c:if test="${searchVO.searchCondition2 == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.zip" /></option>			<!-- 우편번호 -->
			   <option value='2' <c:if test="${searchVO.searchCondition2 == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.ctprvnNm" /></option>		<!-- 시도명 -->
			   <option value='3' <c:if test="${searchVO.searchCondition2 == '3'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.signguNm" /></option>		<!-- 시군구명 -->
			   <option value='4' <c:if test="${searchVO.searchCondition2 == '4'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.emdNm" /></option>		<!-- 읍면동명 -->
			   <option value='5' <c:if test="${searchVO.searchCondition2 == '5'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.liBuldNm" /></option>		<!-- 리건물명 -->
			   <option value='6' <c:if test="${searchVO.searchCondition2 == '6'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.detailBuldNm" /></option>	<!-- 상세건물명 -->
			</select>
			</li>
			<li>
		    <input name="searchKeyword" type="text" size="25" value="${searchVO.searchKeyword}"  maxlength="25" id="searchKeyword">
		    <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" onclick="fn_egov_search_Zip(); return false;">	<!-- 조회 -->
		    </li>
		    <li style="margin-top:5px;">
		    <input type="submit" class="s_btn" value="<spring:message code="comSymCcmZip.zipVO.SearchAddrr" /> <spring:message code="title.create" />" onclick="fn_egov_regist_Zip(1); return false;">	<!-- 일반주소 등록 -->
		    <input type="submit" class="s_btn" value="<spring:message code="comSymCcmZip.zipVO.SearchRdmn" /> <spring:message code="title.create" />" onclick="fn_egov_regist_Zip(2); return false;">	<!-- 도로명주소 등록 -->
		    <input type="submit" class="s_btn" value="<spring:message code="comSymCcmZip.zipVO.SearchAddrr" /> <spring:message code="comSymCcmZip.zipVO.excelFile" /> <spring:message code="title.create" />" onclick="fn_egov_regist_ExcelZip(1); return false;">	<!-- 일반주소 엑셀파일 등록 -->
		    <input type="submit" class="s_btn" value="<spring:message code="comSymCcmZip.zipVO.SearchRdmn" /> <spring:message code="comSymCcmZip.zipVO.excelFile" /> <spring:message code="title.create" />" onclick="fn_egov_regist_ExcelZip(2); return false;">	<!-- 도로명주소 엑셀파일 등록 -->
		    </li>
	  	<ul>
	  </div>

<table class="board_list" summary="<spring:message code="comSymCcmZip.zipVO.summaryList"/>"> <!-- 우편번호와 주소를 출력하는 우편번호 목록 테이블이다. -->
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 20%;">
			<col style="width: 70%;">
	    </colgroup>
	<thead>
	<tr>
		<th><spring:message code="comSymCcmZip.zipVO.Snum" /></th>			<!-- 순번 -->
		<th><spring:message code="comSymCcmZip.zipVO.zip" /></th>			<!-- 우편번호 -->
		<th><spring:message code="comSymCcmZip.zipVO.SearchAddr" /></th>	<!-- 주소 -->
	</tr>
	</thead>

<tbody>
<c:choose>
<c:when test="${searchVO.searchList != '2'}">
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fn_egov_detail_Zip('${resultInfo.zip}','${resultInfo.sn}');">
		<td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
		<td class="lt_text3" nowrap><c:out value='${fn:substring(resultInfo.zip, 0,5)}'/></td>
		<td class="lt_text"  nowrap>${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm} ${resultInfo.lnbrDongHo}</td>
	</tr>
	</c:forEach>
</c:when>
<c:otherwise>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fn_egov_detail_RdmnCode_Zip('${resultInfo.rdmnCode}','${resultInfo.sn}');">
		<td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
		<td class="lt_text3" nowrap><c:out value='${fn:substring(resultInfo.zip, 0,3)}'/>-<c:out value='${fn:substring(resultInfo.zip, 3,6)}'/></td>
		<td class="lt_text"  nowrap>${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.rdmn} ${resultInfo.bdnbrMnnm} <c:if test="${resultInfo.bdnbrSlno != ''}">- ${resultInfo.bdnbrSlno}</c:if> ${resultInfo.buldNm} ${resultInfo.detailBuldNm}</td>
	</tr>
	</c:forEach>
</c:otherwise>
</c:choose>

<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan=3>
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
</c:if>

</tbody>
</table>
<!-- paging navigation -->
<div class="pagination">
	<ul>
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
	</ul>
</div>


<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>
<form name="Form" id="Form" method="post" action="">
	<input type=hidden name="zip">
	<input type=hidden name="sn" value="0">
	<input type=hidden name="rdmnCode">
	<input type=hidden name="searchList">
	<input type="submit" id="invisible" class="invisible">
</form>
</div>
</body>
</html>
