<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovAdministCodeDetail.jsp
  * @Description : EgovAdministCodeDetail 화면
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
<title>법정동코드수신 상세조회</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/sym/ccm/acr/com.css">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/sym/ccm/acr/button.css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_AdministCode(){
	location.href = "/sym/ccm/acr/getAdministCodeRecptnList.do.do";
}
-->
</script>
</head>
<body>
<form name="Form" action="" method="post">
	<input name="administZoneSe" type="hidden">
	<input name="administZoneCode" type="hidden">
</form>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="100%" class="title_left">
   <img src="/images/egovframework/com/sym/ccm/acr/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;법정동코드 상세조회</td>
 </tr>
</table>
<table width="700" border="0" cellpadding="0" cellspacing="1" class="table-register">
<c:if test="${result.administZoneCode == null}">
	<tr>
		<td class="lt_text3" >
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
</c:if>
<c:if test="${result.administZoneCode != null}">
  <tr>
    <th width="30%" height="23" class="required_text" nowrap >법정동코드<img src="/images/egovframework/com/sym/ccm/acr/icon/required.gif" alt="필수입력표시"  width="15" height="15"></th>
    <td><c:out value="${result.administZoneCode}"/></td>
  </tr>
  <tr>
    <th width="30%" height="23" class="required_text" nowrap >행정구역명<img src="/images/egovframework/com/sym/ccm/acr/icon/required.gif" alt="필수입력표시"  width="15" height="15"></th>
    <td><c:out value="${result.administZoneNm}"/></td>
  </tr>
  <tr>
    <th width="20%" height="23" class="required_text" nowrap >생성일자<img src="/images/egovframework/com/sym/ccm/acr/icon/required.gif" alt="필수입력표시"  width="15" height="15"></th>
    <td><c:out value='${fn:substring(result.creatDe, 0,4)}'/>-<c:out value='${fn:substring(result.creatDe, 4,6)}'/>-<c:out value='${fn:substring(result.creatDe, 6,8)}'/></td>
  </tr>
  <tr>
    <th width="20%" height="23" class="required_text" nowrap >폐지유무<img src="/images/egovframework/com/ssi/syi/sim/icon/required.gif" alt="필수입력표시"  width="15" height="15"></th>
    <td width="80%" nowrap>
	  <select title="폐지유무" name="ablEnnc" disabled="disabled">
	      <option value="0" <c:if test="${result.ablEnnc == '0'}">selected="selected"</c:if> >사용</option>
		  <option value="1" <c:if test="${result.ablEnnc == '1'}">selected="selected"</c:if> >말소</option>
	  </select>
    </td>
  </tr>
</c:if>
</table>
<table width="700" border="0" cellspacing="0" cellpadding="0">
<tr><td height="10px"></td></tr>
<tr>
	<td>
		<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>
				<form name="formList" action="<c:url value='/sym/ccm/acr/getAdministCodeRecptnList.do'/>" method="post">
				<span class="button"><input type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_AdministCodeRecptn(); return false;"></span>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<DIV id="content" style="display">
<form name="listForm" action="<c:url value='/sym/ccm/acr/EgovAdministCodeRecptnList.do'/>" method="post">
</form>

<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="40%"class="title_left">

   &nbsp;법정동코드수신 이력</td>
  <th>
  </th>
  <td width="10%"></td>
  <td width="35%"></td>
  <th width="10%">
  </th>
 </tr>
</table>

<table width="700" cellpadding="0" class="table-line" border="0">
<thead>
<tr>
	<th class="title" width="10%" nowrap>순번</th>
	<th class="title" width="10%" nowrap>발생일자</th>
	<th class="title" width="20%" nowrap>최하위행정구역명</th>
	<th class="title" width="15%" nowrap>변경구분</th>
	<th class="title" width="15%" nowrap>처리구분</th>
	<th class="title" width="10%" nowrap>생성일자</th>
	<th class="title" width="10%" nowrap>폐지유무</th>
	<th class="title" width="10%" nowrap>폐지일자</th>
</tr>
</thead>

<tbody>
<c:forEach items="${administCodeRecptnList}" var="resultInfo" varStatus="status">
<tr>
	<td class="lt_text3" nowrap><c:out value="${status.count}"/></td>
	<td class="lt_text3" nowrap><c:out value='${fn:substring(resultInfo.occrrDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.occrrDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.occrrDe, 6,8)}'/></td>
	<td class="lt_text"  nowrap><c:out value="${resultInfo.lowestAdministZoneNm}"/></td>
    <td class="lt_text" nowrap>
		<select title="변경구분" name="changeSeCode" class="select" disabled="disabled">
		   	<option value=''><c:out value="${resultInfo.changeSeCode}"/></option>
		    <c:forEach var="result" items="${changeSeCodeList}" varStatus="status">
			<option value='<c:out value="${result.code}"/>' <c:if test="${result.code == resultInfo.changeSeCode}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
			</c:forEach>
		</select>
    </td>
    <td class="lt_text" nowrap>
		<select title="처리구분" name="processSe" class="select" disabled="disabled">
		   	<option value=''><c:out value="${resultInfo.processSe}"/></option>
		    <c:forEach var="result" items="${processSeList}" varStatus="status">
			<option value='<c:out value="${result.code}"/>' <c:if test="${result.code == resultInfo.processSe}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
			</c:forEach>
		</select>
    </td>
	<td class="lt_text3"  nowrap><c:out value='${fn:substring(resultInfo.creatDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.creatDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.creatDe, 6,8)}'/></td>
    <td class="lt_text3"  nowrap>
	  <select title="폐지유무" name="ablEnnc" disabled="disabled">
	      <option value="0" <c:if test="${resultInfo.ablEnnc == '0'}">selected="selected"</c:if> >사용</option>
		  <option value="1" <c:if test="${resultInfo.ablEnnc == '1'}">selected="selected"</c:if> >말소</option>
	  </select>
    </td>
	<td class="lt_text3"  nowrap><c:out value='${fn:substring(resultInfo.ablDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.ablDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.ablDe, 6,8)}'/></td>
</tr>
</c:forEach>

<c:if test="${fn:length(administCodeRecptnList) == 0}">
	<tr>
		<td class="lt_text3" colspan="9">
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
</c:if>

</tbody>
</table>

</DIV>


</body>
</html>