<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovAdministCodeRecptnList.jsp
  * @Description : EgovAdministCodeRecptnList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
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
<html lang="ko">
<head>
<title>법정동코드수신 목록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/sym/ccm/acr/com.css">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/sym/ccm/acr/button.css">

<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/ccm/acr/getAdministCodeRecptnList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_AdministCodeRecptn(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_AdministCodeRecptn(administZoneSe,administZoneCode){
	var varForm				        = document.all["listForm"];
	varForm.action                  = "<c:url value='/sym/ccm/acr/getAdministCodeDetail.do'/>";
	varForm.administZoneCode.value  = administZoneCode;
	varForm.administZoneSe.value    = administZoneSe;
	varForm.submit();
}
-->
</script>
</head>

<body>
<DIV id="content" style="display">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form name="listForm" action="<c:url value='/sym/ccm/acr/getAdministCodeRecptnList.do'/>" method="post">
<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="40%"class="title_left">

   <img src="/images/egovframework/com/sym/ccm/acr/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;법정동코드수신 목록</td>
  <th>
  </th>
  <td width="10%">
   	<select title="검색조건" name="searchCondition" class="select">
		   <option selected value=''>--선택하세요--</option>
		   <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>기관명</option>
	   </select>
	</td>
  <td width="35%">
    <input title="검색어" name="searchKeyword" type="text" size="35" value="<c:out value="${searchVO.searchKeyword}"/>"  maxlength="35" >
  </td>
  <th width="10%">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><img src="/images/egovframework/com/sym/ccm/acr/btn/bu2_left.gif" alt="조회" width="8" height="20"></td>
      <td style="background-image:URL(<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />);" class="text_left" nowrap>
      <input type="submit" value="조회" onclick="fn_egov_search_AdministCodeRecptn(); return false;" class="btnNew" style="height:20px;width:26px;" ></td>
      <td><img src="/images/egovframework/com/sym/ccm/acr/btn/bu2_right.gif" alt="조회" width="8" height="20"></td>
    </tr>
   </table>
  </th>
 </tr>
</table>
<input type="hidden" name="administZoneSe">
<input type="hidden" name="administZoneCode">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>

<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>

<table width="700" cellpadding="0" class="table-list" border="0">
<thead>
<tr>
	<th class="title" width="10%" nowrap>순번</th>
	<th class="title" width="20%" nowrap>법정동코드</th>
	<th class="title" width="70%" nowrap>주소지명</th>
</tr>
</thead>

<tbody>
<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="3">
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
</c:if>

<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fn_egov_detail_AdministCodeRecptn('<c:out value="${resultInfo.administZoneSe}"/>','<c:out value="${resultInfo.administZoneCode}"/>');">
	<td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
	<td class="lt_text3" nowrap>
    	<form name="subForm" method="post" action="<c:url value='/sym/ccm/acr/getAdministCodeDetail.do'/>">
    	<input name="administZoneSe"   type="hidden" value="<c:out value="${resultInfo.administZoneSe}"/>">
    	<input name="administZoneCode" type="hidden" value="<c:out value="${resultInfo.administZoneCode}"/>">
    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
    	<span class="link"><input type="submit" value="<c:out value="${resultInfo.administZoneCode}"/>" onclick="fn_egov_detail_AdministCodeRecptn3('<c:out value="${resultInfo.administZoneSe}"/>','<c:out value="${resultInfo.administZoneCode}"/>'); return false;"></span>
    	</form>

	</td>
	<td class="lt_text"  nowrap><c:out value="${resultInfo.administZoneNm}"/></td>
</tr>
</c:forEach>

</tbody>
</table>

<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
<tr>
	<td>
		<div align="center">
			<div>
				<ui:pagination paginationInfo = "${paginationInfo}"
						type="image"
						jsFunction="fn_egov_pageview"
						/>
			</div>
		</div>
	</td>
</tr>
</table>

</DIV>

</body>
</html>